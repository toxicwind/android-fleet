package com.google.android.exoplayer.nbfss;

import android.annotation.TargetApi;
import android.media.MediaExtractor;
import android.os.Looper;
import android.util.Log;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.upstream.Loader;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;

/* JADX INFO: loaded from: classes.dex */
abstract class ExtractorLoadableHandler {
    protected Allocator allocator = new DefaultAllocator(MtkTvScanDvbsBase.SB_DVBS_CONFIG_BGM_SCAN);

    public abstract boolean continueBuffering(int i, long j);

    public abstract void disable(int i, long j);

    public abstract void enable(int i, long j);

    public abstract long getBufferedPositionUs();

    public abstract MediaFormat getFormat(int i);

    public abstract boolean isEndOfStream(int i);

    public abstract void prepare(long j);

    public abstract int readData(int i, long j, SampleHolder sampleHolder);

    public abstract long seekToUs(long j);

    public abstract void setAvgBitrate(long j);

    ExtractorLoadableHandler() {
    }

    public void release() {
        this.allocator.trim(0);
    }

    @TargetApi(16)
    static class SingleExtractorLoadableHandler extends ExtractorLoadableHandler {
        private MediaExtractor extractor;
        private Looper looper = Looper.myLooper();
        private NonBlockingFrameworkSampleSource nonBlockingFrameworkSampleSource;
        private Loader singleExtractorLoader;
        private ExtractorLoadable singleExtractorTracksLoadable;
        private int trackCount;

        SingleExtractorLoadableHandler(MediaExtractor mediaExtractor, NonBlockingFrameworkSampleSource nonBlockingFrameworkSampleSource) {
            this.extractor = mediaExtractor;
            this.nonBlockingFrameworkSampleSource = nonBlockingFrameworkSampleSource;
            this.trackCount = mediaExtractor.getTrackCount();
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadableHandler
        public void enable(int i, long j) {
            MtkDebugLogUtil.log("SingleExtractorHandler", "enable track = " + i + ", positionUs = " + j + ", isEndOfStream=" + this.singleExtractorTracksLoadable.isEndOfStream(i), 8);
            if (this.singleExtractorLoader.isLoading()) {
                MtkDebugLogUtil.log("SingleExtractorHandler", "Loader is loading. Cancel it.", 8);
                this.singleExtractorLoader.cancelLoading();
            }
            if (((Loader.Loadable) this.singleExtractorTracksLoadable).isLoadCanceled()) {
                this.singleExtractorTracksLoadable = this.singleExtractorTracksLoadable.cloneTrack();
            }
            this.singleExtractorTracksLoadable.enable(i, j);
            MediaFormat format = this.singleExtractorTracksLoadable.getFormat(i);
            if (j != 0) {
                if ((format == null || !MimeTypes.isVideo(format.mimeType)) && !this.singleExtractorTracksLoadable.isEndOfStream(i)) {
                    return;
                }
                this.singleExtractorTracksLoadable.seekTo(j, true);
            }
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadableHandler
        public void disable(int i, long j) {
            MtkDebugLogUtil.log("SingleExtractorHandler", "disable track = " + i, 8);
            if (this.singleExtractorLoader.isLoading()) {
                MtkDebugLogUtil.log("SingleExtractorHandler", "Loader is loading. Cancel it.", 8);
                this.singleExtractorLoader.cancelLoading();
            }
            if (((Loader.Loadable) this.singleExtractorTracksLoadable).isLoadCanceled()) {
                this.singleExtractorTracksLoadable = this.singleExtractorTracksLoadable.cloneTrack();
            }
            this.singleExtractorTracksLoadable.disable(i, j);
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadableHandler
        public long seekToUs(long j) {
            if (this.singleExtractorTracksLoadable.seekInCachedSamples(j)) {
                return j;
            }
            MtkDebugLogUtil.log("SingleExtractorHandler", "Can not find in cached samples", 8);
            if (this.singleExtractorLoader.isLoading()) {
                MtkDebugLogUtil.log("SingleExtractorHandler", "Loader is loading. Cancel it.", 8);
                this.singleExtractorLoader.cancelLoading();
            }
            if (((Loader.Loadable) this.singleExtractorTracksLoadable).isLoadCanceled()) {
                this.singleExtractorTracksLoadable = this.singleExtractorTracksLoadable.cloneTrack();
            }
            return this.singleExtractorTracksLoadable.seekTo(j, true);
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadableHandler
        public void prepare(long j) {
            this.singleExtractorTracksLoadable = new ExtractorLoadable.TracksLoadable(this.extractor, this.allocator, this.trackCount);
            this.singleExtractorLoader = new Loader("single-loader");
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadableHandler
        public MediaFormat getFormat(int i) {
            return this.singleExtractorTracksLoadable.getFormat(i);
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadableHandler
        public int readData(int i, long j, SampleHolder sampleHolder) {
            return this.singleExtractorTracksLoadable.readData(i, j, sampleHolder);
        }

        public void tryToStartLoadable(int i) {
            if (!this.singleExtractorLoader.isLoading()) {
                Log.d("SingleExtractorHandler", "[Performance]startLoading");
                this.singleExtractorLoader.startLoading(this.looper, (Loader.Loadable) this.singleExtractorTracksLoadable, this.nonBlockingFrameworkSampleSource);
                return;
            }
            this.singleExtractorTracksLoadable.tryToNextLoad();
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadableHandler
        public long getBufferedPositionUs() {
            if (this.singleExtractorTracksLoadable.isInputEnded(0)) {
                return -3L;
            }
            long jMax = Long.MIN_VALUE;
            for (int i = 0; i < this.trackCount; i++) {
                if (this.singleExtractorTracksLoadable.isEnable(i)) {
                    jMax = Math.max(this.singleExtractorTracksLoadable.getBufferedPositionUs(i), jMax);
                }
            }
            return jMax;
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadableHandler
        public boolean continueBuffering(int i, long j) {
            if (this.singleExtractorTracksLoadable.isInputEnded(i)) {
                return true;
            }
            this.singleExtractorTracksLoadable.continueBuffering(i, j);
            tryToStartLoadable(i);
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append(" !singleExtractorTracksLoadable.isEmpty(track) =  ");
            sb.append(!this.singleExtractorTracksLoadable.isEmpty(i));
            MtkDebugLogUtil.log("SingleExtractorHandler", sb.toString(), 8);
            return !this.singleExtractorTracksLoadable.isEmpty(i);
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadableHandler
        public void release() {
            if (this.singleExtractorLoader.isLoading()) {
                this.singleExtractorLoader.cancelLoading();
            }
            this.singleExtractorLoader.release();
            this.singleExtractorTracksLoadable.release();
            super.release();
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadableHandler
        public boolean isEndOfStream(int i) {
            return this.singleExtractorTracksLoadable.isEndOfStream(i);
        }

        @Override // com.google.android.exoplayer.nbfss.ExtractorLoadableHandler
        public void setAvgBitrate(long j) {
            this.singleExtractorTracksLoadable.setAvgBitrate(j);
            MtkDebugLogUtil.log("SingleExtractorHandler", "setting avg bitrate=" + j, 8);
        }
    }
}
