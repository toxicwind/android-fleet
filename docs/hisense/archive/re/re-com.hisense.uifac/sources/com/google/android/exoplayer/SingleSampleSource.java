package com.google.android.exoplayer;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.upstream.Loader;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class SingleSampleSource implements SampleSource, SampleSource.SampleSourceReader, Loader.Callback, Loader.Loadable {
    private IOException currentLoadableException;
    private int currentLoadableExceptionCount;
    private long currentLoadableExceptionTimestamp;
    private final DataSource dataSource;
    private final Handler eventHandler;
    private final EventListener eventListener;
    private final int eventSourceId;
    private final MediaFormat format;
    private Loader loader;
    private boolean loadingFinished;
    private final int minLoadableRetryCount;
    private long pendingDiscontinuityPositionUs;
    private byte[] sampleData;
    private int sampleSize;
    private int state;
    private final Uri uri;

    public interface EventListener {
        void onLoadError(int i, IOException iOException);
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Loadable
    public void cancelLoad() {
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Loadable
    public boolean isLoadCanceled() {
        return false;
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Callback
    public void onLoadCanceled(Loader.Loadable loadable) {
    }

    @Override // com.google.android.exoplayer.SampleSource
    public SampleSource.SampleSourceReader register() {
        return this;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void setTrickMode(boolean z) {
    }

    public SingleSampleSource(Uri uri, DataSource dataSource, MediaFormat mediaFormat) {
        this(uri, dataSource, mediaFormat, 3);
    }

    public SingleSampleSource(Uri uri, DataSource dataSource, MediaFormat mediaFormat, int i) {
        this(uri, dataSource, mediaFormat, i, null, null, 0);
    }

    public SingleSampleSource(Uri uri, DataSource dataSource, MediaFormat mediaFormat, int i, Handler handler, EventListener eventListener, int i2) {
        this.uri = uri;
        this.dataSource = dataSource;
        this.format = mediaFormat;
        this.minLoadableRetryCount = i;
        this.eventHandler = handler;
        this.eventListener = eventListener;
        this.eventSourceId = i2;
        this.sampleData = new byte[1];
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public boolean prepare(long j) {
        if (this.loader != null) {
            return true;
        }
        this.loader = new Loader("Loader:" + this.format.mimeType);
        return true;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public int getTrackCount() throws Throwable {
        Log.d("SingleSampleSource", "mimeType = " + this.format.mimeType);
        if (!this.format.mimeType.equals("text/ext/vobsub")) {
            return 1;
        }
        int i = 0;
        BufferedReader bufferedReader = null;
        try {
            try {
                try {
                    Log.d("SingleSampleSource", "uri = " + this.uri.getPath());
                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader(this.uri.getPath()));
                    while (true) {
                        try {
                            String line = bufferedReader2.readLine();
                            if (line == null) {
                                break;
                            }
                            if (line.indexOf("index:") > 0) {
                                i++;
                            }
                        } catch (IOException e) {
                            e = e;
                            bufferedReader = bufferedReader2;
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            Log.d("SingleSampleSource", "count = " + i);
                            return i;
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException unused) {
                                }
                            }
                            throw th;
                        }
                    }
                    bufferedReader2.close();
                } catch (IOException unused2) {
                }
            } catch (IOException e2) {
                e = e2;
            }
            Log.d("SingleSampleSource", "count = " + i);
            return i;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public MediaFormat getFormat(int i) {
        return this.format;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void enable(int i, long j) {
        this.state = 0;
        this.pendingDiscontinuityPositionUs = Long.MIN_VALUE;
        clearCurrentLoadableException();
        maybeStartLoading();
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public boolean continueBuffering(int i, long j) {
        maybeStartLoading();
        return this.loadingFinished;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void maybeThrowError() throws IOException {
        IOException iOException = this.currentLoadableException;
        if (iOException != null && this.currentLoadableExceptionCount > this.minLoadableRetryCount) {
            throw iOException;
        }
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public long readDiscontinuity(int i) {
        long j = this.pendingDiscontinuityPositionUs;
        this.pendingDiscontinuityPositionUs = Long.MIN_VALUE;
        return j;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public int readData(int i, long j, MediaFormatHolder mediaFormatHolder, SampleHolder sampleHolder) {
        int i2 = this.state;
        if (i2 == 2) {
            return -1;
        }
        if (i2 == 0) {
            mediaFormatHolder.format = this.format;
            this.state = 1;
            return -4;
        }
        Assertions.checkState(i2 == 1);
        if (!this.loadingFinished || sampleHolder == null) {
            return -2;
        }
        sampleHolder.timeUs = 0L;
        int i3 = this.sampleSize;
        sampleHolder.size = i3;
        sampleHolder.flags = 1;
        sampleHolder.ensureSpaceForWrite(i3);
        sampleHolder.data.put(this.sampleData, 0, this.sampleSize);
        this.state = 2;
        return -3;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void seekToUs(long j) {
        if (this.state == 2) {
            this.pendingDiscontinuityPositionUs = j;
            this.state = 1;
        }
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public long getBufferedPositionUs() {
        return this.loadingFinished ? -3L : 0L;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void disable(int i) {
        this.state = 2;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void release() {
        Loader loader = this.loader;
        if (loader != null) {
            loader.release();
            this.loader = null;
        }
    }

    private void maybeStartLoading() {
        if (this.loadingFinished || this.state == 2 || this.loader.isLoading()) {
            return;
        }
        if (this.currentLoadableException != null) {
            if (SystemClock.elapsedRealtime() - this.currentLoadableExceptionTimestamp < getRetryDelayMillis(this.currentLoadableExceptionCount)) {
                return;
            } else {
                this.currentLoadableException = null;
            }
        }
        this.loader.startLoading(this, this);
    }

    private void clearCurrentLoadableException() {
        this.currentLoadableException = null;
        this.currentLoadableExceptionCount = 0;
    }

    private long getRetryDelayMillis(long j) {
        return Math.min((j - 1) * 1000, 5000L);
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Callback
    public void onLoadCompleted(Loader.Loadable loadable) {
        this.loadingFinished = true;
        clearCurrentLoadableException();
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Callback
    public void onLoadError(Loader.Loadable loadable, IOException iOException) {
        this.currentLoadableException = iOException;
        this.currentLoadableExceptionCount++;
        this.currentLoadableExceptionTimestamp = SystemClock.elapsedRealtime();
        notifyLoadError(iOException);
        maybeStartLoading();
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Loadable
    public void load() throws InterruptedException, IOException {
        int i = 0;
        this.sampleSize = 0;
        try {
            this.dataSource.open(new DataSpec(this.uri));
            while (i != -1) {
                int i2 = this.sampleSize + i;
                this.sampleSize = i2;
                if (i2 == this.sampleData.length) {
                    this.sampleData = Arrays.copyOf(this.sampleData, this.sampleData.length * 2);
                }
                i = this.dataSource.read(this.sampleData, this.sampleSize, this.sampleData.length - this.sampleSize);
            }
        } finally {
            Util.closeQuietly(this.dataSource);
        }
    }

    private void notifyLoadError(final IOException iOException) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.SingleSampleSource.1
            @Override // java.lang.Runnable
            public void run() {
                SingleSampleSource.this.eventListener.onLoadError(SingleSampleSource.this.eventSourceId, iOException);
            }
        });
    }
}
