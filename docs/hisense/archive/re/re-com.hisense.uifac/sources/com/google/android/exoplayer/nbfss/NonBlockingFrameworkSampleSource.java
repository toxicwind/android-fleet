package com.google.android.exoplayer.nbfss;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaExtractor;
import android.net.Uri;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.exoplayer.FrameworkSampleSource;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.MediaFormatHolder;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.SampleSource;
import com.google.android.exoplayer.upstream.FrameworkDataSource;
import com.google.android.exoplayer.upstream.Loader;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(16)
public class NonBlockingFrameworkSampleSource implements SampleSource, SampleSource.SampleSourceReader, Loader.Callback {
    public static Class<?> DATA_SOURCE_INTERFACE;
    private static Method setDataSourceMethod;
    private String containerFormat;
    private final Context context;
    private FrameworkDataSource dataSource;
    private ExtractorLoadableHandler extractorLoadableHandler;
    private boolean[] firstFrameAfterSeek;
    private final Map<String, String> headers;
    private long lastActualSeekTimeUs;
    private long lastSeekPositionUs;
    private Object nativeExtractorDataSource;
    private boolean[] pendingDiscontinuities;
    private IOException preparationError;
    private boolean prepared;
    private boolean readAnySample;
    private int remainingReleaseCount;
    private Lock seekLock;
    private final boolean singleExtractor;
    private long[] trackDurationUs;
    private int[] trackStates;
    private final Uri uri;

    @Override // com.google.android.exoplayer.upstream.Loader.Callback
    public void onLoadCanceled(Loader.Loadable loadable) {
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Callback
    public void onLoadCompleted(Loader.Loadable loadable) {
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Callback
    public void onLoadError(Loader.Loadable loadable, IOException iOException) {
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void setTrickMode(boolean z) {
    }

    public NonBlockingFrameworkSampleSource(Context context, Uri uri, Map<String, String> map) {
        this(context, uri, map, null, true);
    }

    @TargetApi(22)
    public NonBlockingFrameworkSampleSource(Context context, FrameworkDataSource frameworkDataSource) {
        this(context, null, null, frameworkDataSource, true);
    }

    private NonBlockingFrameworkSampleSource(Context context, Uri uri, Map<String, String> map, FrameworkDataSource frameworkDataSource, boolean z) {
        this.context = context;
        this.uri = uri;
        this.headers = map;
        this.dataSource = frameworkDataSource;
        this.singleExtractor = z;
        if (frameworkDataSource != null) {
            this.nativeExtractorDataSource = frameworkDataSource.getDataSourceForNativeExtractor();
        }
        this.seekLock = new ReentrantLock();
        this.lastSeekPositionUs = -1L;
        this.lastActualSeekTimeUs = -1L;
    }

    @Override // com.google.android.exoplayer.SampleSource
    public SampleSource.SampleSourceReader register() {
        this.remainingReleaseCount++;
        return this;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void maybeThrowError() throws IOException {
        IOException iOException = this.preparationError;
        if (iOException != null) {
            throw iOException;
        }
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public boolean prepare(long j) throws Throwable {
        PersistableBundle persistableBundle;
        if (this.prepared) {
            return true;
        }
        MediaExtractor mediaExtractor = null;
        try {
            MediaExtractor mediaExtractor2 = new MediaExtractor();
            try {
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                if (setDataSourceMethod != null && this.nativeExtractorDataSource != null) {
                    MtkDebugLogUtil.log("NonBlockingFSS", "setDataSourceMethod", 8);
                    try {
                        setDataSourceMethod.invoke(mediaExtractor2, this.nativeExtractorDataSource);
                    } catch (IllegalAccessException unused) {
                    } catch (InvocationTargetException e) {
                        if (e.getCause() instanceof IOException) {
                            this.preparationError = new IOException(e.getCause());
                            return false;
                        }
                    }
                } else {
                    mediaExtractor2.setDataSource(this.context, this.uri, this.headers);
                    MtkDebugLogUtil.log("NonBlockingFSS", "uri = " + this.uri.toString(), 8);
                }
                Log.d("NonBlockingFSS", "[Performance]extractor.setDataSource() costTimeMs=" + (SystemClock.elapsedRealtime() - jElapsedRealtime));
                int trackCount = mediaExtractor2.getTrackCount();
                MtkDebugLogUtil.log("NonBlockingFSS", "trackCount = " + trackCount, 8);
                this.trackDurationUs = new long[trackCount];
                this.pendingDiscontinuities = new boolean[trackCount];
                this.firstFrameAfterSeek = new boolean[trackCount];
                this.trackStates = new int[trackCount];
                for (int i = 0; i < trackCount; i++) {
                    MediaFormat mediaFormatCreateMediaFormat = FrameworkSampleSource.createMediaFormat(mediaExtractor2.getTrackFormat(i));
                    this.trackDurationUs[i] = mediaFormatCreateMediaFormat.durationUs;
                    this.firstFrameAfterSeek[i] = true;
                    this.trackStates[i] = 0;
                    MtkDebugLogUtil.log("NonBlockingFSS", "track[" + i + "] format:" + mediaFormatCreateMediaFormat.toString(), 8);
                }
                if (Util.SDK_INT >= 26) {
                    try {
                        Method method = mediaExtractor2.getClass().getMethod("getMetrics", null);
                        if (method != null && (persistableBundle = (PersistableBundle) method.invoke(mediaExtractor2, null)) != null) {
                            this.containerFormat = persistableBundle.getString("android.media.mediaextractor.fmt");
                            Log.d("NonBlockingFSS", "container format:" + this.containerFormat);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (this.singleExtractor) {
                    ExtractorLoadableHandler.SingleExtractorLoadableHandler singleExtractorLoadableHandler = new ExtractorLoadableHandler.SingleExtractorLoadableHandler(mediaExtractor2, this);
                    this.extractorLoadableHandler = singleExtractorLoadableHandler;
                    singleExtractorLoadableHandler.prepare(j);
                    this.prepared = true;
                    System.currentTimeMillis();
                    setAvgBitrate();
                    return this.prepared;
                }
                mediaExtractor2.release();
                return false;
            } catch (IOException e3) {
                e = e3;
                mediaExtractor = mediaExtractor2;
                mediaExtractor.release();
                this.preparationError = e;
                return false;
            }
        } catch (IOException e4) {
            e = e4;
        }
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public int getTrackCount() {
        return this.trackStates.length;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public MediaFormat getFormat(int i) {
        return this.extractorLoadableHandler.getFormat(i);
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void enable(int i, long j) {
        MtkDebugLogUtil.log("NonBlockingFSS", "enable track = " + i, 8);
        this.trackStates[i] = 1;
        MediaFormat format = getFormat(i);
        if (this.dataSource != null && MimeTypes.isVideo(format.mimeType)) {
            this.dataSource.seekTo(j);
        }
        this.extractorLoadableHandler.enable(i, j);
        if (MimeTypes.isVideo(format.mimeType)) {
            this.pendingDiscontinuities[i] = true;
            this.firstFrameAfterSeek[i] = true;
            this.lastSeekPositionUs = j * (-1);
        }
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public boolean continueBuffering(int i, long j) {
        Assertions.checkState(this.prepared);
        Assertions.checkState(this.extractorLoadableHandler != null);
        long[] jArr = this.trackDurationUs;
        if (jArr[i] <= 0 || jArr[i] >= Math.abs(this.lastSeekPositionUs)) {
            return this.extractorLoadableHandler.continueBuffering(i, j);
        }
        return true;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public long readDiscontinuity(int i) {
        boolean[] zArr = this.pendingDiscontinuities;
        if (!zArr[i]) {
            return Long.MIN_VALUE;
        }
        zArr[i] = false;
        long j = this.lastActualSeekTimeUs;
        return j > 0 ? j : this.lastSeekPositionUs;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public int readData(int i, long j, MediaFormatHolder mediaFormatHolder, SampleHolder sampleHolder) {
        if (this.pendingDiscontinuities[i] || !this.seekLock.tryLock()) {
            return -2;
        }
        if (this.trackStates[i] != 2) {
            mediaFormatHolder.format = getFormat(i);
            mediaFormatHolder.drmInitData = null;
            this.trackStates[i] = 2;
            this.seekLock.unlock();
            return -4;
        }
        if (sampleHolder == null) {
            return -2;
        }
        if (sampleHolder.data == null) {
            sampleHolder.ensureSpaceForWrite(6291456);
        }
        if (!this.extractorLoadableHandler.isEndOfStream(i)) {
            long[] jArr = this.trackDurationUs;
            if (jArr[i] <= 0 || jArr[i] >= Math.abs(this.lastSeekPositionUs)) {
                int data = this.extractorLoadableHandler.readData(i, j, sampleHolder);
                if (data == -3) {
                    int i2 = (sampleHolder.timeUs < this.lastSeekPositionUs ? 134217728 : 0) | sampleHolder.flags;
                    sampleHolder.flags = i2;
                    boolean[] zArr = this.firstFrameAfterSeek;
                    if (zArr[i]) {
                        zArr[i] = false;
                        sampleHolder.flags = i2 | 67108864;
                        if ("ESExtractor".equals(this.containerFormat)) {
                            sampleHolder.timeUs = this.lastSeekPositionUs;
                        }
                    }
                    this.readAnySample = true;
                    MimeTypes.isVideo(getFormat(i).mimeType);
                } else {
                    sampleHolder.clearData();
                }
                this.seekLock.unlock();
                return data;
            }
        }
        MtkDebugLogUtil.log("NonBlockingFSS", "track[" + i + "] reach EOS. durationUs:" + this.trackDurationUs[i] + ", lastSeekPositionUs:" + this.lastSeekPositionUs + ", isEndOfStream=" + this.extractorLoadableHandler.isEndOfStream(i), 8);
        this.seekLock.unlock();
        return -1;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void seekToUs(long j) {
        MtkDebugLogUtil.log("NonBlockingFSS", "seekToUs position = " + j + ", lastSeekPositionUs=" + this.lastSeekPositionUs + ", readAnySample=" + this.readAnySample, 8);
        if (this.readAnySample || this.lastSeekPositionUs != j) {
            this.seekLock.lock();
            this.lastSeekPositionUs = j;
            this.lastActualSeekTimeUs = -1L;
            this.readAnySample = false;
            FrameworkDataSource frameworkDataSource = this.dataSource;
            if (frameworkDataSource != null) {
                frameworkDataSource.seekTo(j);
            }
            this.lastActualSeekTimeUs = this.extractorLoadableHandler.seekToUs(j);
            for (int i = 0; i < getTrackCount(); i++) {
                if (this.trackStates[i] != 0) {
                    this.pendingDiscontinuities[i] = true;
                    this.firstFrameAfterSeek[i] = true;
                }
            }
            this.seekLock.unlock();
        }
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public long getBufferedPositionUs() {
        return this.extractorLoadableHandler.getBufferedPositionUs();
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void disable(int i) {
        disable(i, -1L);
    }

    public void disable(int i, long j) {
        MtkDebugLogUtil.log("NonBlockingFSS", "disable track = " + i + ", positionUs = " + j, 8);
        this.trackStates[i] = 0;
        this.extractorLoadableHandler.disable(i, j);
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void release() {
        ExtractorLoadableHandler extractorLoadableHandler;
        MtkDebugLogUtil.log("NonBlockingFSS", "release remainingReleaseCount = " + this.remainingReleaseCount, 8);
        int i = this.remainingReleaseCount + (-1);
        this.remainingReleaseCount = i;
        if (i != 0 || (extractorLoadableHandler = this.extractorLoadableHandler) == null) {
            return;
        }
        extractorLoadableHandler.release();
    }

    private boolean setAvgBitrate() throws Throwable {
        long length;
        int i;
        long j;
        Uri uri = this.uri;
        if (uri == null || "http".equals(uri.getScheme()) || "https".equals(this.uri.getScheme())) {
            return true;
        }
        RandomAccessFile randomAccessFile = null;
        try {
            try {
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(this.uri.getPath(), "r");
                try {
                    length = randomAccessFile2.length();
                    try {
                        randomAccessFile2.close();
                        while (true) {
                            j = -1;
                            if (i >= getTrackCount()) {
                                break;
                            }
                            MediaFormat format = getFormat(i);
                            if (format.durationUs != -1 && MimeTypes.isVideo(format.mimeType)) {
                                j = format.durationUs;
                                break;
                            }
                            i++;
                        }
                    } catch (IOException unused) {
                        MtkDebugLogUtil.log("NonBlockingFSS", "file close fail uri: " + this.uri.getPath(), 8);
                    }
                } catch (IOException unused2) {
                    randomAccessFile = randomAccessFile2;
                    MtkDebugLogUtil.log("NonBlockingFSS", "get file length fail uri: " + this.uri.getPath(), 8);
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException unused3) {
                            MtkDebugLogUtil.log("NonBlockingFSS", "file close fail uri: " + this.uri.getPath(), 8);
                        }
                    }
                    length = 0;
                } catch (Throwable th) {
                    th = th;
                    randomAccessFile = randomAccessFile2;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException unused4) {
                            MtkDebugLogUtil.log("NonBlockingFSS", "file close fail uri: " + this.uri.getPath(), 8);
                        }
                    }
                    throw th;
                }
            } catch (IOException unused5) {
            }
            i = 0;
            if (j > 0 && j >= 1000000) {
                this.extractorLoadableHandler.setAvgBitrate((length / (j / 1000000)) * 8);
            }
            return true;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    static {
        try {
            Class<?> cls = Class.forName("android.media.DataSource");
            DATA_SOURCE_INTERFACE = cls;
            if (cls != null) {
                MtkDebugLogUtil.log("NonBlockingFSS", "android.media.DataSource(API 22) found", 8);
            }
        } catch (ClassNotFoundException unused) {
            MtkDebugLogUtil.log("NonBlockingFSS", "android.media.DataSource(API 22) not found", 8);
        }
        try {
            if (Util.SDK_INT == 22) {
                Method method = MediaExtractor.class.getMethod("setDataSource", DATA_SOURCE_INTERFACE);
                setDataSourceMethod = method;
                if (method != null) {
                    MtkDebugLogUtil.log("NonBlockingFSS", "setDataSource(DataSource dataSource)(API 22) found", 8);
                    return;
                }
                return;
            }
            if (Util.SDK_INT >= 23) {
                Class<?> cls2 = null;
                try {
                    cls2 = Class.forName("android.media.MediaDataSource");
                } catch (ClassNotFoundException unused2) {
                    MtkDebugLogUtil.log("NonBlockingFSS", "android.media.MediaDataSource(API 23) not found", 8);
                }
                if (cls2 != null) {
                    setDataSourceMethod = MediaExtractor.class.getMethod("setDataSource", cls2);
                }
                if (setDataSourceMethod != null) {
                    MtkDebugLogUtil.log("NonBlockingFSS", "setDataSource(MediaDataSource dataSource)(API 23) found", 8);
                }
            }
        } catch (NoSuchMethodException unused3) {
            MtkDebugLogUtil.log("NonBlockingFSS", "setDataSource method(API 22/23) not found", 8);
        }
    }
}
