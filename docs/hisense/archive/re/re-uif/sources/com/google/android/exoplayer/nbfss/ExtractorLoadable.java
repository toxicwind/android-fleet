package com.google.android.exoplayer.nbfss;

import android.annotation.TargetApi;
import android.media.MediaExtractor;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.exoplayer.FrameworkSampleSource;
import com.google.android.exoplayer.MediaCodecSelector;
import com.google.android.exoplayer.MediaCodecUtil;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.extractor.DefaultTrackOutput;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.upstream.Loader;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(17)
interface ExtractorLoadable {
    ExtractorLoadable cloneTrack();

    boolean continueBuffering(int i, long j);

    MediaExtractor disable(int i, long j);

    void enable(int i, long j);

    long getBufferedPositionUs(int i);

    MediaFormat getFormat(int i);

    boolean isEmpty(int i);

    boolean isEnable(int i);

    boolean isEndOfStream(int i);

    boolean isInputEnded(int i);

    int readData(int i, long j, SampleHolder sampleHolder);

    void release();

    boolean seekInCachedSamples(long j);

    long seekTo(long j, boolean z);

    void setAvgBitrate(long j);

    void tryToNextLoad();

    public static class TracksLoadable implements Loader.Loadable, ExtractorLoadable {
        private int MAX_ALLOCATION_SIZE;
        private long MAX_LOADING_TIME_US_FOR_PRIMARY_TRACK;
        private final Allocator allocator;
        private boolean anyTrackEnabled;
        private long avgBitrate;
        private boolean cloned;
        private ExtractorLoadable clonedThis;
        private String containerFormat;
        private final boolean[] enableTracks;
        private int enabledAudioTrackIndex;
        private int enabledVideoTrackIndex;
        private MediaExtractor extractor;
        private boolean firstBuffering;
        private long firstLoadedSampleTimeUs;
        private boolean isCanceled;
        private boolean isInputEnded;
        private boolean isVideoOnly;
        private final long[] lastLoadedSampleTimeUsList;
        private volatile int[] lastReadSampleCacheIndex;
        private final long[] lastReadSampleTimeUsList;
        private boolean lastSeekInCacheResult;
        private long lastSeekInCacheTimeUs;
        private long lastSeekTimeUs;
        private boolean[] loadedFirstSample;
        private final Object lock;
        private long maxVideoAheadTimeUs;
        private boolean[] pendingSyncList;
        private long[] previousSampleDataDoneTime;
        private final boolean[] primaryTracks;
        private volatile SparseArray<LinkedList<SampleHolder>> sampleCacheLists;
        private long[] sampleDataDoneTime;
        private int selectedGroup;
        private final Semaphore semaphore;
        private final int trackCount;
        private final SparseArray<DefaultTrackOutput> trackOutputs;

        TracksLoadable(MediaExtractor mediaExtractor, Allocator allocator, int i) {
            PersistableBundle persistableBundle;
            this.enabledVideoTrackIndex = -1;
            this.enabledAudioTrackIndex = -1;
            MtkDebugLogUtil.log("TracksLoadable", "create TracksLoadable", 8);
            this.extractor = mediaExtractor;
            this.allocator = allocator;
            this.trackCount = i;
            this.lock = new Object();
            this.semaphore = new Semaphore(1);
            this.trackOutputs = new SparseArray<>(i);
            this.previousSampleDataDoneTime = new long[i];
            this.sampleDataDoneTime = new long[i];
            this.loadedFirstSample = new boolean[i];
            this.enableTracks = new boolean[i];
            this.primaryTracks = new boolean[i];
            this.lastReadSampleTimeUsList = new long[i];
            this.lastLoadedSampleTimeUsList = new long[i];
            this.pendingSyncList = new boolean[i];
            this.lastSeekTimeUs = -1L;
            this.selectedGroup = -1;
            this.lastReadSampleCacheIndex = new int[i];
            boolean z = false;
            for (int i2 = 0; i2 < i; i2++) {
                this.trackOutputs.put(i2, new DefaultTrackOutput(allocator));
                this.pendingSyncList[i2] = true;
                MediaFormat format = getFormat(i2);
                this.trackOutputs.valueAt(i2).format(format);
                this.lastReadSampleTimeUsList[i2] = -1;
                this.lastLoadedSampleTimeUsList[i2] = -1;
                this.maxVideoAheadTimeUs = -1L;
                this.primaryTracks[i2] = MimeTypes.isAudio(format.mimeType) || MimeTypes.isVideo(format.mimeType);
                this.lastReadSampleCacheIndex[i2] = -1;
                if (MimeTypes.isText(format.mimeType) || ((MimeTypes.isAudio(format.mimeType) && !"audio/x-unknown".equals(format.mimeType) && !"audio/true-hd".equals(format.mimeType)) || "application/eia-608".equals(format.mimeType) || (!z && MimeTypes.isVideo(format.mimeType)))) {
                    if (MimeTypes.isVideo(format.mimeType)) {
                        try {
                            if (!handlesTrack(format) || "video/x-unknown".equals(format.mimeType)) {
                            }
                        } catch (MediaCodecUtil.DecoderQueryException e) {
                            e.printStackTrace();
                        }
                    } else {
                        MimeTypes.isAudio(format.mimeType);
                    }
                    int i3 = this.selectedGroup;
                    if (i3 == -1 || format.group == i3) {
                        mediaExtractor.selectTrack(i2);
                        if (MimeTypes.isAudio(format.mimeType)) {
                            this.enabledAudioTrackIndex = i2;
                        } else if (MimeTypes.isVideo(format.mimeType)) {
                            this.enabledVideoTrackIndex = i2;
                            z = true;
                        }
                        if (this.selectedGroup == -1) {
                            this.selectedGroup = format.group;
                        }
                    }
                }
            }
            this.sampleCacheLists = new SparseArray<>(i);
            if (Util.SDK_INT >= 26) {
                try {
                    Method method = mediaExtractor.getClass().getMethod("getMetrics", null);
                    if (method == null || (persistableBundle = (PersistableBundle) method.invoke(mediaExtractor, null)) == null) {
                        return;
                    }
                    this.containerFormat = persistableBundle.getString("android.media.mediaextractor.fmt");
                    Log.d("TracksLoadable", "container format:" + this.containerFormat);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        TracksLoadable(TracksLoadable tracksLoadable) {
            this.enabledVideoTrackIndex = -1;
            this.enabledAudioTrackIndex = -1;
            MtkDebugLogUtil.log("TracksLoadable", "copy TracksLoadable", 8);
            this.extractor = tracksLoadable.extractor;
            this.allocator = tracksLoadable.allocator;
            this.trackCount = tracksLoadable.trackCount;
            this.lock = new Object();
            this.semaphore = new Semaphore(1);
            this.trackOutputs = tracksLoadable.trackOutputs;
            this.MAX_ALLOCATION_SIZE = tracksLoadable.MAX_ALLOCATION_SIZE;
            this.MAX_LOADING_TIME_US_FOR_PRIMARY_TRACK = tracksLoadable.MAX_LOADING_TIME_US_FOR_PRIMARY_TRACK;
            this.avgBitrate = tracksLoadable.avgBitrate;
            this.previousSampleDataDoneTime = tracksLoadable.previousSampleDataDoneTime;
            this.sampleDataDoneTime = tracksLoadable.sampleDataDoneTime;
            this.enableTracks = tracksLoadable.enableTracks;
            this.primaryTracks = tracksLoadable.primaryTracks;
            this.lastReadSampleTimeUsList = tracksLoadable.lastReadSampleTimeUsList;
            this.lastLoadedSampleTimeUsList = tracksLoadable.lastLoadedSampleTimeUsList;
            this.maxVideoAheadTimeUs = tracksLoadable.maxVideoAheadTimeUs;
            this.pendingSyncList = tracksLoadable.pendingSyncList;
            this.lastSeekTimeUs = tracksLoadable.lastSeekTimeUs;
            this.anyTrackEnabled = tracksLoadable.anyTrackEnabled;
            this.isVideoOnly = tracksLoadable.isVideoOnly;
            this.enabledVideoTrackIndex = tracksLoadable.enabledVideoTrackIndex;
            this.enabledAudioTrackIndex = tracksLoadable.enabledAudioTrackIndex;
            this.isInputEnded = tracksLoadable.isInputEnded;
            this.selectedGroup = tracksLoadable.selectedGroup;
            this.containerFormat = tracksLoadable.containerFormat;
            this.loadedFirstSample = tracksLoadable.loadedFirstSample;
            this.sampleCacheLists = tracksLoadable.sampleCacheLists;
            this.lastReadSampleCacheIndex = tracksLoadable.lastReadSampleCacheIndex;
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadable
        public long seekTo(long j, boolean z) {
            MtkDebugLogUtil.log("TracksLoadable", "[Tracks] seekTo lastSeekTimeUs = " + this.lastSeekTimeUs + ", position = " + j + ", this = " + toString(), 8);
            if (this.lastSeekTimeUs == j && !z) {
                return j;
            }
            MtkDebugLogUtil.log("TracksLoadable", "[Tracks] seekTo position = " + j, 8);
            this.extractor.seekTo(j, 0);
            for (int i = 0; i < this.trackCount; i++) {
                this.trackOutputs.valueAt(i).clear();
                this.lastReadSampleTimeUsList[i] = -1;
                this.lastLoadedSampleTimeUsList[i] = -1;
                this.lastReadSampleCacheIndex[i] = -1;
                this.pendingSyncList[i] = true;
                if (this.sampleCacheLists != null && this.sampleCacheLists.indexOfKey(i) >= 0) {
                    this.sampleCacheLists.get(i).clear();
                }
            }
            this.maxVideoAheadTimeUs = -1L;
            this.firstBuffering = true;
            this.isInputEnded = false;
            this.lastSeekTimeUs = j;
            return this.extractor.getSampleTime();
        }

        /* JADX WARN: Code duplicated, block: B:20:0x0057 A[PHI: r2
          0x0057: PHI (r2v3 boolean) = (r2v2 boolean), (r2v8 boolean), (r2v8 boolean) binds: [B:14:0x002e, B:16:0x003d, B:18:0x0052] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadable
        public boolean seekInCachedSamples(long j) {
            long j2;
            String str = this.containerFormat;
            if (str != null && str.toLowerCase().contains("ape")) {
                for (int i = 0; i < this.trackCount; i++) {
                    this.trackOutputs.valueAt(i).clear();
                }
                return false;
            }
            if (this.lastSeekInCacheTimeUs != j) {
                int i2 = this.enabledVideoTrackIndex;
                boolean zSkipToKeyframeBefore = true;
                if (i2 < 0 || !(true && this.trackOutputs.valueAt(i2).skipToKeyframeBefore(j))) {
                    j2 = j;
                } else {
                    SampleHolder sampleHolder = new SampleHolder(0);
                    if (this.trackOutputs.valueAt(this.enabledVideoTrackIndex).getCurrentSampleInfo(sampleHolder)) {
                        j2 = sampleHolder.timeUs;
                    } else {
                        j2 = j;
                    }
                }
                for (int i3 = 0; i3 < this.trackCount; i3++) {
                    if (this.enableTracks[i3] && i3 != this.enabledVideoTrackIndex) {
                        zSkipToKeyframeBefore = this.trackOutputs.valueAt(i3).skipToKeyframeBefore(j2) & zSkipToKeyframeBefore;
                    }
                }
                this.lastSeekInCacheTimeUs = j;
                this.lastSeekInCacheResult = zSkipToKeyframeBefore;
                return zSkipToKeyframeBefore;
            }
            return this.lastSeekInCacheResult;
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadable
        public long getBufferedPositionUs(int i) {
            String str;
            if (this.isVideoOnly || !this.primaryTracks[i] || this.trackOutputs.valueAt(i).getFormat().durationUs == -1) {
                return -1L;
            }
            long largestParsedTimestampUs = this.trackOutputs.valueAt(i).getLargestParsedTimestampUs();
            if (SystemClock.elapsedRealtime() >= 120000 || (str = this.containerFormat) == null || !str.toLowerCase().contains("matroska") || this.primaryTracks.length <= 6 || this.lastSeekTimeUs > 0 || largestParsedTimestampUs >= 3500000.0d) {
                return largestParsedTimestampUs;
            }
            return Long.MIN_VALUE;
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadable
        public boolean isEmpty(int i) {
            return this.primaryTracks[i] && this.trackOutputs.valueAt(i).isEmpty();
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadable
        public void enable(int i, long j) {
            int i2;
            if (this.enableTracks[i]) {
                return;
            }
            String str = this.trackOutputs.valueAt(i).getFormat().mimeType;
            int i3 = this.trackOutputs.valueAt(i).getFormat().group;
            if (MimeTypes.isVideo(str) && (i2 = this.enabledVideoTrackIndex) != -1 && i2 != i) {
                this.extractor.unselectTrack(i);
                this.enabledVideoTrackIndex = -1;
                this.trackOutputs.valueAt(i).clear();
            }
            if (MimeTypes.isVideo(str)) {
                this.extractor.selectTrack(i);
            } else if (i3 != this.selectedGroup) {
                for (int i4 = 0; i4 < this.extractor.getTrackCount(); i4++) {
                    MediaFormat format = this.trackOutputs.valueAt(i4).getFormat();
                    if (!MimeTypes.isVideo(format.mimeType)) {
                        int i5 = format.group;
                        if (i5 == this.selectedGroup) {
                            this.extractor.unselectTrack(i4);
                            this.trackOutputs.valueAt(i4).clear();
                        } else if (i5 == i3) {
                            this.extractor.selectTrack(i4);
                        }
                    }
                }
                this.selectedGroup = i3;
            } else {
                int i6 = this.enabledVideoTrackIndex;
                if (i6 >= 0) {
                    j = Math.min(j, this.lastReadSampleTimeUsList[i6]);
                }
                discardSamplesForDisabledTracks(j);
            }
            this.enableTracks[i] = true;
            this.anyTrackEnabled = true;
            if (this.extractor.getTrackCount() == 1 && MimeTypes.isVideo(str)) {
                this.isVideoOnly = true;
            }
            if (MimeTypes.isVideo(str)) {
                this.enabledVideoTrackIndex = i;
                return;
            }
            if (MimeTypes.isAudio(str)) {
                this.enabledAudioTrackIndex = i;
                if (this.sampleCacheLists == null || this.sampleCacheLists.get(i) != null) {
                    return;
                }
                this.sampleCacheLists.put(i, new LinkedList<>());
            }
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadable
        public MediaExtractor disable(int i, long j) {
            this.enableTracks[i] = false;
            this.lastReadSampleTimeUsList[i] = -1;
            this.lastReadSampleCacheIndex[i] = -1;
            boolean z = false;
            for (boolean z2 : this.enableTracks) {
                z |= z2;
            }
            this.anyTrackEnabled = z;
            MediaFormat format = getFormat(i);
            DefaultTrackOutput defaultTrackOutputValueAt = this.trackOutputs.valueAt(i);
            if (MimeTypes.isVideo(format.mimeType)) {
                this.extractor.unselectTrack(i);
                this.enabledVideoTrackIndex = -1;
                if (defaultTrackOutputValueAt == null) {
                    return null;
                }
                defaultTrackOutputValueAt.clear();
                return null;
            }
            if (MimeTypes.isAudio(format.mimeType)) {
                this.enabledAudioTrackIndex = -1;
            }
            if (j < 0) {
                return null;
            }
            discardSamplesForDisabledTracks(j);
            if (this.sampleCacheLists == null) {
                return null;
            }
            dropCacheBefore(this.sampleCacheLists.get(i), j);
            return null;
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadable
        public void release() {
            MediaExtractor mediaExtractor = this.extractor;
            if (mediaExtractor != null) {
                mediaExtractor.release();
                this.extractor = null;
            }
            for (int i = 0; i < this.trackOutputs.size(); i++) {
                DefaultTrackOutput defaultTrackOutputValueAt = this.trackOutputs.valueAt(i);
                if (defaultTrackOutputValueAt != null) {
                    defaultTrackOutputValueAt.clear();
                }
            }
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadable
        public boolean isEnable(int i) {
            return this.enableTracks[i];
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadable
        public int readData(int i, long j, SampleHolder sampleHolder) {
            DefaultTrackOutput defaultTrackOutputValueAt = this.trackOutputs.valueAt(i);
            if (this.sampleCacheLists != null && this.sampleCacheLists.indexOfKey(i) >= 0 && this.sampleCacheLists.get(i).size() > 0 && (this.lastReadSampleTimeUsList[i] == -1 || this.lastReadSampleCacheIndex[i] >= 0)) {
                MtkDebugLogUtil.log("TracksLoadable", "[" + defaultTrackOutputValueAt.getFormat().mimeType + "] readData sampleCacheLists.get(track).size() = " + this.sampleCacheLists.get(i).size() + ", lastReadSampleTimeUsList[track] = " + this.lastReadSampleTimeUsList[i] + ", lastReadSampleCacheIndex[track] = " + this.lastReadSampleCacheIndex[i], 8);
                if (this.lastReadSampleCacheIndex[i] < this.sampleCacheLists.get(i).size() - 1) {
                    int i2 = this.lastReadSampleCacheIndex[i] >= 0 ? this.lastReadSampleCacheIndex[i] + 1 : 0;
                    sampleHolder.copyFrom(this.sampleCacheLists.get(i).get(i2));
                    MtkDebugLogUtil.log("TracksLoadable", "[" + defaultTrackOutputValueAt.getFormat().mimeType + "] readData from cache timeUs = " + sampleHolder.timeUs + ", size = " + sampleHolder.size + ", flags = " + sampleHolder.flags, 8);
                    this.lastReadSampleCacheIndex[i] = i2;
                    this.lastReadSampleTimeUsList[i] = sampleHolder.timeUs;
                    return -3;
                }
                this.lastReadSampleCacheIndex[i] = -1;
            }
            if (!defaultTrackOutputValueAt.isEmpty() && defaultTrackOutputValueAt.getSample(sampleHolder)) {
                MtkDebugLogUtil.log("TracksLoadable", "[" + defaultTrackOutputValueAt.getFormat().mimeType + "] readData timeUs = " + sampleHolder.timeUs + ", size = " + sampleHolder.size + ", flags = " + sampleHolder.flags, 8);
                long[] jArr = this.lastReadSampleTimeUsList;
                jArr[i] = Math.max(sampleHolder.timeUs, jArr[i]);
                if (this.sampleCacheLists != null && this.sampleCacheLists.indexOfKey(i) >= 0) {
                    SampleHolder sampleHolder2 = new SampleHolder(1);
                    sampleHolder2.copyFrom(sampleHolder);
                    this.sampleCacheLists.get(i).addLast(sampleHolder2);
                    dropCacheBefore(this.sampleCacheLists.get(i), j);
                }
                return -3;
            }
            if (!isInputEnded(i)) {
                return -2;
            }
            Log.d("TracksLoadable", "Audio ends earlier than video. Return EOS.");
            return -1;
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadable
        public boolean isInputEnded(int i) {
            int i2;
            DefaultTrackOutput defaultTrackOutputValueAt = this.trackOutputs.valueAt(i);
            if (!this.isInputEnded && MimeTypes.isAudio(defaultTrackOutputValueAt.getFormat().mimeType) && defaultTrackOutputValueAt.isEmpty()) {
                long j = defaultTrackOutputValueAt.getFormat().durationUs;
                if ((j > 0 && (i2 = this.enabledVideoTrackIndex) >= 0 && this.trackOutputs.valueAt(i2).getFormat().durationUs - j >= 10000000 && this.lastReadSampleTimeUsList[i] - j >= -500000) || this.maxVideoAheadTimeUs > 5000000) {
                    return true;
                }
            }
            return this.isInputEnded;
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadable
        public boolean isEndOfStream(int i) {
            if (!this.isInputEnded) {
                return false;
            }
            if (this.primaryTracks[i]) {
                return this.enableTracks[i] && this.trackOutputs.valueAt(i).isEmpty();
            }
            boolean zIsEmpty = this.trackOutputs.valueAt(i).isEmpty();
            int i2 = 0;
            while (true) {
                boolean[] zArr = this.enableTracks;
                if (i2 >= zArr.length) {
                    return zIsEmpty;
                }
                zIsEmpty &= (zArr[i2] && this.primaryTracks[i2] && !this.trackOutputs.valueAt(i2).isEmpty()) ? false : true;
                i2++;
            }
        }

        public boolean shouldBuffering() {
            int i = 0;
            boolean zIsEmpty = false;
            while (true) {
                if (i < this.trackCount) {
                    DefaultTrackOutput defaultTrackOutputValueAt = this.trackOutputs.valueAt(i);
                    if (this.enableTracks[i]) {
                        if (this.primaryTracks[i]) {
                            if (defaultTrackOutputValueAt.isEmpty()) {
                                MtkDebugLogUtil.log("TracksLoadable", "enabled primary track is empty, shouldBuffering = true", 8);
                                return true;
                            }
                            zIsEmpty |= defaultTrackOutputValueAt.getLargestParsedTimestampUs() - this.lastReadSampleTimeUsList[i] < 10000000;
                        } else {
                            zIsEmpty |= defaultTrackOutputValueAt.isEmpty();
                        }
                    }
                    i++;
                } else {
                    boolean z = zIsEmpty & (this.allocator.getTotalBytesAllocated() <= this.MAX_ALLOCATION_SIZE);
                    MtkDebugLogUtil.log("TracksLoadable", "shouldBuffering = " + z, 8);
                    return z;
                }
            }
        }

        public boolean continueLoading(long j, int i) {
            boolean zIsEmpty;
            if (this.firstBuffering) {
                if (this.isVideoOnly) {
                    if (i > 60) {
                        return false;
                    }
                } else if (j - this.firstLoadedSampleTimeUs > 4000000) {
                    return false;
                }
                return true;
            }
            boolean z = false;
            for (int i2 = 0; i2 < this.trackCount; i2++) {
                DefaultTrackOutput defaultTrackOutputValueAt = this.trackOutputs.valueAt(i2);
                long largestParsedTimestampUs = defaultTrackOutputValueAt.getLargestParsedTimestampUs() - this.lastReadSampleTimeUsList[i2];
                if (this.enableTracks[i2]) {
                    if (this.primaryTracks[i2]) {
                        if (defaultTrackOutputValueAt.isEmpty()) {
                            MtkDebugLogUtil.log("TracksLoadable", "enabled primary track is empty, continueLoading = true", 8);
                            return true;
                        }
                        MtkDebugLogUtil.log("TracksLoadable", "track[" + i2 + "] : [" + defaultTrackOutputValueAt.getFormat().mimeType + "] sampledTimeUs = " + largestParsedTimestampUs, 8);
                        zIsEmpty = largestParsedTimestampUs < this.MAX_LOADING_TIME_US_FOR_PRIMARY_TRACK;
                    } else {
                        zIsEmpty = defaultTrackOutputValueAt.isEmpty();
                    }
                    z |= zIsEmpty;
                }
            }
            if (this.allocator.getTotalBytesAllocated() <= this.MAX_ALLOCATION_SIZE) {
                return z;
            }
            MtkDebugLogUtil.log("TracksLoadable", "continueLoading ring buffer size = " + this.allocator.getTotalBytesAllocated(), 8);
            return false;
        }

        private void configParams() {
            int i;
            long j = this.avgBitrate;
            if (j > 0) {
                if (j >= 200000000) {
                    this.MAX_ALLOCATION_SIZE = 209715200;
                    this.MAX_LOADING_TIME_US_FOR_PRIMARY_TRACK = 10000000L;
                } else if (j >= 150000000) {
                    this.MAX_ALLOCATION_SIZE = 157286400;
                    this.MAX_LOADING_TIME_US_FOR_PRIMARY_TRACK = 8000000L;
                } else if (j >= 100000000) {
                    this.MAX_ALLOCATION_SIZE = 104857600;
                    this.MAX_LOADING_TIME_US_FOR_PRIMARY_TRACK = 6000000L;
                } else {
                    this.MAX_ALLOCATION_SIZE = 62914560;
                    this.MAX_LOADING_TIME_US_FOR_PRIMARY_TRACK = 2000000L;
                }
                MtkDebugLogUtil.log("TracksLoadable", "avgBitrate=" + this.avgBitrate + "bps, config: MAX_ALLOCATION_SIZE=" + (this.MAX_ALLOCATION_SIZE / 1048576) + "M, MAX_LOADING_TIME_US_FOR_PRIMARY_TRACK=" + (this.MAX_LOADING_TIME_US_FOR_PRIMARY_TRACK / 1000000) + "s", 8);
                return;
            }
            int i2 = 0;
            int i3 = 0;
            while (true) {
                if (i3 >= this.trackOutputs.size()) {
                    i = 0;
                    break;
                } else {
                    if (MimeTypes.isVideo(this.trackOutputs.valueAt(i3).getFormat().mimeType)) {
                        i2 = this.trackOutputs.valueAt(i3).getFormat().width;
                        i = this.trackOutputs.valueAt(i3).getFormat().height;
                        break;
                    }
                    i3++;
                }
            }
            if (i2 >= 7680 || i >= 3840) {
                this.MAX_ALLOCATION_SIZE = 209715200;
                this.MAX_LOADING_TIME_US_FOR_PRIMARY_TRACK = 10000000L;
            } else if (i2 >= 3840 || i >= 2160) {
                this.MAX_ALLOCATION_SIZE = 157286400;
                this.MAX_LOADING_TIME_US_FOR_PRIMARY_TRACK = 8000000L;
            } else if (i2 >= 1920 || i >= 1080) {
                this.MAX_ALLOCATION_SIZE = 104857600;
                this.MAX_LOADING_TIME_US_FOR_PRIMARY_TRACK = 6000000L;
            } else {
                this.MAX_ALLOCATION_SIZE = 62914560;
                this.MAX_LOADING_TIME_US_FOR_PRIMARY_TRACK = 2000000L;
            }
            MtkDebugLogUtil.log("TracksLoadable", "width=" + i2 + ", height=" + i + ", config: MAX_ALLOCATION_SIZE=" + (this.MAX_ALLOCATION_SIZE / 1048576) + "M, MAX_LOADING_TIME_US_FOR_PRIMARY_TRACK=" + (this.MAX_LOADING_TIME_US_FOR_PRIMARY_TRACK / 1000000) + "s", 8);
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadable
        public void setAvgBitrate(long j) {
            this.avgBitrate = j;
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadable
        public void tryToNextLoad() {
            MtkDebugLogUtil.log("TracksLoadable", "[Tracks] tryToNextLoad", 8);
            if (shouldBuffering() && this.semaphore.availablePermits() == 0) {
                this.semaphore.release();
            }
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadable
        public ExtractorLoadable cloneTrack() {
            MtkDebugLogUtil.log("TracksLoadable", "cloned = " + this.cloned + ", clonedThis = " + this.clonedThis, 8);
            if (!this.cloned) {
                this.clonedThis = new TracksLoadable(this);
                this.cloned = true;
            }
            return this.clonedThis;
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadable
        public MediaFormat getFormat(int i) {
            String str;
            MediaFormat format = this.trackOutputs.valueAt(i).hasFormat() ? this.trackOutputs.valueAt(i).getFormat() : FrameworkSampleSource.createMediaFormat(this.extractor.getTrackFormat(i));
            if (("video/wvc1".equals(format.mimeType) || "video/VC1".equals(format.mimeType)) && (str = this.containerFormat) != null && (str.toLowerCase().contains("matroska") || this.containerFormat.toLowerCase().contains("avi"))) {
                format.timestampMode = 1;
            }
            return format;
        }

        @Override // com.google.android.exoplayer.upstream.Loader.Loadable
        public void cancelLoad() {
            synchronized (this.lock) {
                MtkDebugLogUtil.log("TracksLoadable", "[Tracks] cancelLoad", 8);
                this.isCanceled = true;
            }
        }

        @Override // com.google.android.exoplayer.upstream.Loader.Loadable
        public boolean isLoadCanceled() {
            return this.isCanceled;
        }

        /* JADX WARN: Code duplicated, block: B:26:0x00bf  */
        @Override // com.google.android.exoplayer.upstream.Loader.Loadable
        public void load() throws InterruptedException, IOException {
            long j;
            long j2;
            boolean z;
            int i;
            MtkDebugLogUtil.log("TracksLoadable", "[Tracks] load", 8);
            configParams();
            if (this.semaphore.availablePermits() == 0) {
                this.semaphore.release();
            }
            int i2 = 0;
            int i3 = 0;
            while (true) {
                j = 1000;
                if (i3 >= this.trackCount) {
                    break;
                }
                this.loadedFirstSample[i3] = false;
                this.sampleDataDoneTime[i3] = SystemClock.elapsedRealtimeNanos() / 1000;
                this.previousSampleDataDoneTime[i3] = SystemClock.elapsedRealtimeNanos() / 1000;
                i3++;
            }
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(16777216);
            ParsableByteArray parsableByteArray = new ParsableByteArray(byteBufferAllocate.array());
            while (!this.isCanceled && this.anyTrackEnabled) {
                int i4 = i2;
                while (true) {
                    byteBufferAllocate.clear();
                    parsableByteArray.reset();
                    long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
                    int sampleTrackIndex = this.extractor.getSampleTrackIndex();
                    if (sampleTrackIndex < 0) {
                        this.isInputEnded = true;
                        byteBufferAllocate = byteBufferAllocate;
                        j2 = j;
                        break;
                    }
                    int sampleData = this.extractor.readSampleData(byteBufferAllocate, i2);
                    long sampleTime = this.extractor.getSampleTime();
                    int sampleFlags = this.extractor.getSampleFlags();
                    parsableByteArray.setLimit(sampleData);
                    int i5 = this.enabledVideoTrackIndex;
                    if (i5 >= 0 && sampleTrackIndex == i5 && (i = this.enabledAudioTrackIndex) >= 0) {
                        long[] jArr = this.lastLoadedSampleTimeUsList;
                        if (jArr[i] >= 0) {
                            this.maxVideoAheadTimeUs = Math.max(this.maxVideoAheadTimeUs, sampleTime - jArr[i]);
                            MtkDebugLogUtil.log("TracksLoadable", "maxVideoAheadTimeUs = " + this.maxVideoAheadTimeUs, 8);
                        }
                    }
                    this.lastLoadedSampleTimeUsList[sampleTrackIndex] = sampleTime;
                    synchronized (this.lock) {
                        if (this.isCanceled) {
                            sampleTime = sampleTime;
                            z = false;
                        } else {
                            if (this.pendingSyncList[sampleTrackIndex]) {
                                sampleFlags |= 1;
                                this.pendingSyncList[sampleTrackIndex] = false;
                                this.firstLoadedSampleTimeUs = sampleTime;
                            }
                            if (this.primaryTracks[sampleTrackIndex] || !"null".equals(parsableByteArray.readString(4))) {
                                if (!this.primaryTracks[sampleTrackIndex]) {
                                    parsableByteArray.setPosition(0);
                                }
                                if (!MimeTypes.isVideo(this.trackOutputs.valueAt(sampleTrackIndex).getFormat().mimeType)) {
                                    sampleFlags |= 1;
                                }
                                int i6 = sampleFlags;
                                this.trackOutputs.valueAt(sampleTrackIndex).sampleData(parsableByteArray, sampleData);
                                this.trackOutputs.valueAt(sampleTrackIndex).sampleMetadata(sampleTime, i6, sampleData, 0, null);
                                this.sampleDataDoneTime[sampleTrackIndex] = SystemClock.elapsedRealtimeNanos() / 1000;
                                MtkDebugLogUtil.log("TracksLoadable", "sample track[" + this.trackOutputs.valueAt(sampleTrackIndex).getFormat().mimeType + "] data size=" + sampleData + ", consume time=" + (this.sampleDataDoneTime[sampleTrackIndex] - this.previousSampleDataDoneTime[sampleTrackIndex]), 8);
                                this.previousSampleDataDoneTime[sampleTrackIndex] = this.sampleDataDoneTime[sampleTrackIndex];
                                sampleFlags = i6;
                                z = false;
                            } else {
                                z = true;
                            }
                            this.extractor.advance();
                        }
                    }
                    int i7 = i4 + 1;
                    long j3 = sampleTime;
                    boolean zContinueLoading = continueLoading(j3, i4);
                    if (z) {
                        j2 = 1000;
                    } else if (!this.loadedFirstSample[sampleTrackIndex]) {
                        Log.d("TracksLoadable", "[Performance]track " + sampleTrackIndex + ": [" + this.trackOutputs.valueAt(sampleTrackIndex).getFormat().mimeType + "] 1st sample put into queue: timeUs = " + j3 + ", size = " + sampleData + ", flags = " + sampleFlags + ", allocator.getTotalBytesAllocated() = " + this.allocator.getTotalBytesAllocated() + ", continueLoading = " + zContinueLoading + ", durationUs = " + ((SystemClock.elapsedRealtimeNanos() - jElapsedRealtimeNanos) / 1000));
                        this.loadedFirstSample[sampleTrackIndex] = true;
                        j2 = 1000;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("track ");
                        sb.append(sampleTrackIndex);
                        sb.append(": [");
                        sb.append(this.trackOutputs.valueAt(sampleTrackIndex).getFormat().mimeType);
                        sb.append("] sample put into queue: timeUs = ");
                        sb.append(j3);
                        sb.append(", size = ");
                        sb.append(sampleData);
                        sb.append(", flags = ");
                        sb.append(sampleFlags);
                        sb.append(", allocator.getTotalBytesAllocated() = ");
                        sb.append(this.allocator.getTotalBytesAllocated());
                        sb.append(", continueLoading = ");
                        sb.append(zContinueLoading);
                        sb.append(", durationUs = ");
                        j2 = 1000;
                        sb.append((SystemClock.elapsedRealtimeNanos() - jElapsedRealtimeNanos) / 1000);
                        MtkDebugLogUtil.log("TracksLoadable", sb.toString(), 8);
                    }
                    if (this.isCanceled || this.isInputEnded || !zContinueLoading) {
                        break;
                    }
                    j = j2;
                    byteBufferAllocate = byteBufferAllocate;
                    i4 = i7;
                    i2 = 0;
                }
                if (this.firstBuffering) {
                    this.firstBuffering = false;
                }
                if (!this.isCanceled) {
                    MtkDebugLogUtil.log("TracksLoadable", "semaphore.acquire()", 8);
                    this.semaphore.acquire();
                }
                i2 = 0;
                j = j2;
                byteBufferAllocate = byteBufferAllocate;
            }
            byteBufferAllocate.clear();
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadable
        public boolean continueBuffering(int i, long j) {
            if (this.allocator.getTotalBytesAllocated() <= this.MAX_ALLOCATION_SIZE) {
                return false;
            }
            discardSamplesForDisabledTracks(j);
            return false;
        }

        private void discardSamplesForDisabledTracks(long j) {
            for (int i = 0; i < this.trackCount; i++) {
                if (!this.enableTracks[i]) {
                    if (!this.trackOutputs.valueAt(i).isEmpty()) {
                        this.trackOutputs.valueAt(i).discardUntil(j);
                        MtkDebugLogUtil.log("TracksLoadable", "track[" + i + "] discardUntil " + j, 8);
                    }
                    if (this.sampleCacheLists != null && this.sampleCacheLists.indexOfKey(i) >= 0) {
                        dropCacheBefore(this.sampleCacheLists.get(i), j);
                    }
                }
            }
        }

        private boolean handlesTrack(MediaFormat mediaFormat) throws MediaCodecUtil.DecoderQueryException {
            String strFindDecoderForFormat;
            String str = mediaFormat.mimeType;
            if ("video/dolby-vision".equals(str)) {
                mediaFormat.getFrameworkMediaFormatV16();
                if (Util.SDK_INT < 24 || (strFindDecoderForFormat = MediaCodecUtil.findDecoderForFormat(mediaFormat.getFrameworkMediaFormatV16(), false)) == null) {
                    return false;
                }
                str = str + "#" + strFindDecoderForFormat;
            }
            if (MimeTypes.isVideo(str)) {
                return "video/x-unknown".equals(str) || MediaCodecSelector.DEFAULT.getDecoderInfo(str, false) != null;
            }
            return false;
        }

        private void dropCacheBefore(LinkedList<SampleHolder> linkedList, long j) {
            if (linkedList == null || linkedList.size() == 0) {
                return;
            }
            Iterator<SampleHolder> it = linkedList.iterator();
            while (it.hasNext() && it.next().timeUs < j) {
                it.remove();
            }
        }
    }
}
