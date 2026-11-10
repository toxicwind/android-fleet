package com.google.android.exoplayer.hls;

import android.os.Handler;
import android.os.SystemClock;
import com.google.android.exoplayer.LoadControl;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.MediaFormatHolder;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.SampleSource;
import com.google.android.exoplayer.chunk.BaseChunkSampleSourceEventListener;
import com.google.android.exoplayer.chunk.Chunk;
import com.google.android.exoplayer.chunk.ChunkOperationHolder;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.upstream.Loader;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public final class HlsSampleSource implements SampleSource, SampleSource.SampleSourceReader, Loader.Callback {
    private final int bufferSizeContribution;
    private final ChunkOperationHolder chunkOperationHolder;
    private final HlsChunkSource chunkSource;
    private int[] chunkSourceTrackIndices;
    private long currentLoadStartTimeMs;
    private Chunk currentLoadable;
    private IOException currentLoadableException;
    private int currentLoadableExceptionCount;
    private long currentLoadableExceptionTimestamp;
    private TsChunk currentTsLoadable;
    private Format downstreamFormat;
    private MediaFormat[] downstreamMediaFormats;
    private long downstreamPositionUs;
    private int enabledTrackCount;
    private final Handler eventHandler;
    private final EventListener eventListener;
    private final int eventSourceId;
    private boolean[] extractorTrackEnabledStates;
    private int[] extractorTrackIndices;
    private final LinkedList<HlsExtractorWrapper> extractors;
    private long lastSeekPositionUs;
    private final LoadControl loadControl;
    private boolean loadControlRegistered;
    private Loader loader;
    private boolean loadingFinished;
    private final int minLoadableRetryCount;
    private boolean[] pendingDiscontinuities;
    private long pendingResetPositionUs;
    private boolean prepared;
    private TsChunk previousTsLoadable;
    private int remainingReleaseCount;
    private int trackCount;
    private boolean[] trackEnabledStates;
    private MediaFormat[] trackFormats;

    public interface EventListener extends BaseChunkSampleSourceEventListener {
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void setTrickMode(boolean z) {
    }

    public HlsSampleSource(HlsChunkSource hlsChunkSource, LoadControl loadControl, int i, Handler handler, EventListener eventListener, int i2) {
        this(hlsChunkSource, loadControl, i, handler, eventListener, i2, 3);
    }

    public HlsSampleSource(HlsChunkSource hlsChunkSource, LoadControl loadControl, int i, Handler handler, EventListener eventListener, int i2, int i3) {
        this.chunkSource = hlsChunkSource;
        this.loadControl = loadControl;
        this.bufferSizeContribution = i;
        this.minLoadableRetryCount = i3;
        this.eventHandler = handler;
        this.eventListener = eventListener;
        this.eventSourceId = i2;
        this.pendingResetPositionUs = Long.MIN_VALUE;
        this.currentLoadableExceptionCount = 0;
        this.extractors = new LinkedList<>();
        this.chunkOperationHolder = new ChunkOperationHolder();
    }

    @Override // com.google.android.exoplayer.SampleSource
    public SampleSource.SampleSourceReader register() {
        this.remainingReleaseCount++;
        return this;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public boolean prepare(long j) {
        if (this.prepared) {
            return true;
        }
        if (!this.chunkSource.prepare()) {
            return false;
        }
        if (!this.extractors.isEmpty()) {
            while (true) {
                HlsExtractorWrapper first = this.extractors.getFirst();
                if (first.isPrepared()) {
                    buildTracks(first);
                    this.prepared = true;
                    maybeStartLoading();
                    return true;
                }
                if (this.extractors.size() <= 1) {
                    break;
                }
                this.extractors.removeFirst().clear();
            }
        }
        if (this.loader == null) {
            this.loader = new Loader("Loader:HLS");
            this.loadControl.register(this, this.bufferSizeContribution);
            this.loadControlRegistered = true;
        }
        if (!this.loader.isLoading()) {
            this.pendingResetPositionUs = j;
            this.downstreamPositionUs = j;
        }
        maybeStartLoading();
        return false;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public int getTrackCount() {
        Assertions.checkState(this.prepared);
        MtkDebugLogUtil.log("HlsSampleSource", "trackCount[" + this.trackCount + "]", 4);
        return this.trackCount;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public MediaFormat getFormat(int i) {
        Assertions.checkState(this.prepared);
        return this.trackFormats[i];
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void enable(int i, long j) {
        Assertions.checkState(this.prepared);
        setTrackEnabledState(i, true);
        this.downstreamMediaFormats[i] = null;
        this.pendingDiscontinuities[i] = false;
        this.downstreamFormat = null;
        boolean z = this.loadControlRegistered;
        if (!z) {
            this.loadControl.register(this, this.bufferSizeContribution);
            this.loadControlRegistered = true;
        }
        if (this.chunkSource.isLive()) {
            j = 0;
        }
        int i2 = this.chunkSourceTrackIndices[i];
        if (i2 != -1 && i2 != this.chunkSource.getSelectedTrackIndex()) {
            this.chunkSource.selectTrack(i2);
            seekToInternal(j);
        } else if (this.enabledTrackCount == 1) {
            this.lastSeekPositionUs = j;
            if (z && this.downstreamPositionUs == j) {
                maybeStartLoading();
            } else {
                this.downstreamPositionUs = j;
                restartFrom(j);
            }
        }
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void disable(int i) {
        Assertions.checkState(this.prepared);
        setTrackEnabledState(i, false);
        if (this.enabledTrackCount == 0) {
            this.chunkSource.reset();
            this.downstreamPositionUs = Long.MIN_VALUE;
            if (this.loadControlRegistered) {
                this.loadControl.unregister(this);
                this.loadControlRegistered = false;
            }
            if (this.loader.isLoading()) {
                this.loader.cancelLoading();
            } else {
                clearState();
                this.loadControl.trimAllocator();
            }
        }
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public boolean continueBuffering(int i, long j) {
        Assertions.checkState(this.prepared);
        Assertions.checkState(this.trackEnabledStates[i]);
        this.downstreamPositionUs = j;
        if (!this.extractors.isEmpty()) {
            discardSamplesForDisabledTracks(getCurrentExtractor(), this.downstreamPositionUs);
        }
        maybeStartLoading();
        if (this.loadingFinished) {
            return true;
        }
        if (!isPendingReset() && !this.extractors.isEmpty()) {
            for (int i2 = 0; i2 < this.extractors.size(); i2++) {
                HlsExtractorWrapper hlsExtractorWrapper = this.extractors.get(i2);
                if (!hlsExtractorWrapper.isPrepared()) {
                    break;
                }
                if (hlsExtractorWrapper.hasSamples(this.extractorTrackIndices[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public long readDiscontinuity(int i) {
        boolean[] zArr = this.pendingDiscontinuities;
        if (!zArr[i]) {
            return Long.MIN_VALUE;
        }
        zArr[i] = false;
        return this.lastSeekPositionUs;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public int readData(int i, long j, MediaFormatHolder mediaFormatHolder, SampleHolder sampleHolder) {
        Assertions.checkState(this.prepared);
        this.downstreamPositionUs = j;
        if (!this.pendingDiscontinuities[i] && !isPendingReset()) {
            HlsExtractorWrapper currentExtractor = getCurrentExtractor();
            if (!currentExtractor.isPrepared()) {
                return -2;
            }
            Format format = currentExtractor.format;
            if (!format.equals(this.downstreamFormat)) {
                notifyDownstreamFormatChanged(format, currentExtractor.trigger, currentExtractor.startTimeUs);
            }
            this.downstreamFormat = format;
            if (this.extractors.size() > 1) {
                currentExtractor.configureSpliceTo(this.extractors.get(1));
            }
            int i2 = this.extractorTrackIndices[i];
            int i3 = 0;
            do {
                i3++;
                if (this.extractors.size() > i3 && !currentExtractor.hasSamples(i2)) {
                    currentExtractor = this.extractors.get(i3);
                } else {
                    MediaFormat mediaFormat = currentExtractor.getMediaFormat(i2);
                    if (mediaFormat != null) {
                        if (!mediaFormat.equals(this.downstreamMediaFormats[i])) {
                            mediaFormatHolder.format = mediaFormat;
                            this.downstreamMediaFormats[i] = mediaFormat;
                            return -4;
                        }
                        this.downstreamMediaFormats[i] = mediaFormat;
                    }
                    if (sampleHolder == null) {
                        return -2;
                    }
                    if (currentExtractor.getSample(i2, sampleHolder)) {
                        sampleHolder.flags |= sampleHolder.timeUs < this.lastSeekPositionUs ? 134217728 : 0;
                        return -3;
                    }
                    if (this.loadingFinished) {
                        return -1;
                    }
                }
            } while (currentExtractor.isPrepared());
            return -2;
        }
        return -2;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void maybeThrowError() throws IOException {
        IOException iOException = this.currentLoadableException;
        if (iOException != null && this.currentLoadableExceptionCount > this.minLoadableRetryCount) {
            throw iOException;
        }
        if (this.currentLoadable == null) {
            this.chunkSource.maybeThrowError();
        }
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void seekToUs(long j) {
        Assertions.checkState(this.prepared);
        Assertions.checkState(this.enabledTrackCount > 0);
        if (this.chunkSource.isLive()) {
            j = 0;
        }
        long j2 = isPendingReset() ? this.pendingResetPositionUs : this.downstreamPositionUs;
        this.downstreamPositionUs = j;
        this.lastSeekPositionUs = j;
        if (j2 == j) {
            return;
        }
        MtkDebugLogUtil.log("HlsSampleSource", "seekToUs[" + j + "]", 4);
        seekToInternal(j);
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public long getBufferedPositionUs() {
        Assertions.checkState(this.prepared);
        Assertions.checkState(this.enabledTrackCount > 0);
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.loadingFinished) {
            return -3L;
        }
        long largestParsedTimestampUs = this.extractors.getLast().getLargestParsedTimestampUs();
        if (this.extractors.size() > 1) {
            LinkedList<HlsExtractorWrapper> linkedList = this.extractors;
            largestParsedTimestampUs = Math.max(largestParsedTimestampUs, linkedList.get(linkedList.size() - 2).getLargestParsedTimestampUs());
        }
        return largestParsedTimestampUs == Long.MIN_VALUE ? this.downstreamPositionUs : largestParsedTimestampUs;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void release() {
        Assertions.checkState(this.remainingReleaseCount > 0);
        int i = this.remainingReleaseCount - 1;
        this.remainingReleaseCount = i;
        if (i != 0 || this.loader == null) {
            return;
        }
        if (this.loadControlRegistered) {
            this.loadControl.unregister(this);
            this.loadControlRegistered = false;
        }
        this.loader.release();
        this.loader = null;
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Callback
    public void onLoadCompleted(Loader.Loadable loadable) {
        Assertions.checkState(loadable == this.currentLoadable);
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j = jElapsedRealtime - this.currentLoadStartTimeMs;
        this.chunkSource.onChunkLoadCompleted(this.currentLoadable);
        if (isTsChunk(this.currentLoadable)) {
            Assertions.checkState(this.currentLoadable == this.currentTsLoadable);
            this.previousTsLoadable = this.currentTsLoadable;
            long jBytesLoaded = this.currentLoadable.bytesLoaded();
            TsChunk tsChunk = this.currentTsLoadable;
            notifyLoadCompleted(jBytesLoaded, tsChunk.type, tsChunk.trigger, tsChunk.format, tsChunk.startTimeUs, tsChunk.endTimeUs, jElapsedRealtime, j);
        } else {
            long jBytesLoaded2 = this.currentLoadable.bytesLoaded();
            Chunk chunk = this.currentLoadable;
            notifyLoadCompleted(jBytesLoaded2, chunk.type, chunk.trigger, chunk.format, -1L, -1L, jElapsedRealtime, j);
        }
        clearCurrentLoadable();
        maybeStartLoading();
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Callback
    public void onLoadCanceled(Loader.Loadable loadable) {
        notifyLoadCanceled(this.currentLoadable.bytesLoaded());
        if (this.enabledTrackCount > 0) {
            restartFrom(this.pendingResetPositionUs);
        } else {
            clearState();
            this.loadControl.trimAllocator();
        }
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Callback
    public void onLoadError(Loader.Loadable loadable, IOException iOException) {
        MtkDebugLogUtil.log("HlsSampleSource", "onLoadError", 4);
        if (this.chunkSource.onChunkLoadError(this.currentLoadable, iOException)) {
            if (this.previousTsLoadable == null && !isPendingReset()) {
                this.pendingResetPositionUs = this.lastSeekPositionUs;
            }
            clearCurrentLoadable();
        } else {
            this.currentLoadableException = iOException;
            this.currentLoadableExceptionCount++;
            MtkDebugLogUtil.log("HlsSampleSource", "currentLoadableExceptionCount[" + this.currentLoadableExceptionCount + "]", 4);
            this.currentLoadableExceptionTimestamp = SystemClock.elapsedRealtime();
        }
        notifyLoadError(iOException);
        maybeStartLoading();
    }

    private void buildTracks(HlsExtractorWrapper hlsExtractorWrapper) {
        char c;
        String muxedCaptionLanguage;
        int trackCount = hlsExtractorWrapper.getTrackCount();
        int i = -1;
        int i2 = 0;
        char c2 = 0;
        while (true) {
            c = 1;
            if (i2 >= trackCount) {
                break;
            }
            String str = hlsExtractorWrapper.getMediaFormat(i2).mimeType;
            if (MimeTypes.isVideo(str)) {
                c = 3;
            } else if (MimeTypes.isAudio(str)) {
                c = 2;
            } else if (!MimeTypes.isText(str)) {
                c = 0;
            }
            if (c > c2) {
                i = i2;
                c2 = c;
            } else if (c == c2 && i != -1) {
                i = -1;
            }
            i2++;
        }
        int trackCount2 = this.chunkSource.getTrackCount();
        c = i == -1 ? (char) 0 : (char) 1;
        this.trackCount = trackCount;
        if (c != 0) {
            this.trackCount = (trackCount2 - 1) + trackCount;
        }
        int i3 = this.trackCount;
        this.trackFormats = new MediaFormat[i3];
        this.trackEnabledStates = new boolean[i3];
        this.pendingDiscontinuities = new boolean[i3];
        this.downstreamMediaFormats = new MediaFormat[i3];
        this.chunkSourceTrackIndices = new int[i3];
        this.extractorTrackIndices = new int[i3];
        this.extractorTrackEnabledStates = new boolean[trackCount];
        long durationUs = this.chunkSource.getDurationUs();
        int i4 = 0;
        for (int i5 = 0; i5 < trackCount; i5++) {
            MediaFormat mediaFormatCopyWithDurationUs = hlsExtractorWrapper.getMediaFormat(i5).copyWithDurationUs(durationUs);
            if (MimeTypes.isAudio(mediaFormatCopyWithDurationUs.mimeType)) {
                muxedCaptionLanguage = this.chunkSource.getMuxedAudioLanguage();
            } else {
                muxedCaptionLanguage = "application/eia-608".equals(mediaFormatCopyWithDurationUs.mimeType) ? this.chunkSource.getMuxedCaptionLanguage() : null;
            }
            if (i5 == i) {
                int i6 = 0;
                while (i6 < trackCount2) {
                    this.extractorTrackIndices[i4] = i5;
                    this.chunkSourceTrackIndices[i4] = i6;
                    Variant fixedTrackVariant = this.chunkSource.getFixedTrackVariant(i6);
                    int i7 = i4 + 1;
                    this.trackFormats[i4] = fixedTrackVariant == null ? mediaFormatCopyWithDurationUs.copyAsAdaptive(null) : copyWithFixedTrackInfo(mediaFormatCopyWithDurationUs, fixedTrackVariant.format, muxedCaptionLanguage);
                    i6++;
                    i4 = i7;
                }
            } else {
                this.extractorTrackIndices[i4] = i5;
                this.chunkSourceTrackIndices[i4] = -1;
                this.trackFormats[i4] = mediaFormatCopyWithDurationUs.copyWithLanguage(muxedCaptionLanguage);
                i4++;
            }
        }
    }

    private void setTrackEnabledState(int i, boolean z) {
        Assertions.checkState(this.trackEnabledStates[i] != z);
        int i2 = this.extractorTrackIndices[i];
        Assertions.checkState(this.extractorTrackEnabledStates[i2] != z);
        this.trackEnabledStates[i] = z;
        this.extractorTrackEnabledStates[i2] = z;
        this.enabledTrackCount += z ? 1 : -1;
    }

    private static MediaFormat copyWithFixedTrackInfo(MediaFormat mediaFormat, Format format, String str) {
        int i = format.width;
        int i2 = i == -1 ? -1 : i;
        int i3 = format.height;
        int i4 = i3 == -1 ? -1 : i3;
        String str2 = format.language;
        return mediaFormat.copyWithFixedTrackInfo(format.id, format.bitrate, i2, i4, str2 == null ? str : str2);
    }

    private void seekToInternal(long j) {
        this.lastSeekPositionUs = j;
        this.downstreamPositionUs = j;
        Arrays.fill(this.pendingDiscontinuities, true);
        this.chunkSource.seek();
        restartFrom(j);
    }

    private HlsExtractorWrapper getCurrentExtractor() {
        HlsExtractorWrapper hlsExtractorWrapper;
        HlsExtractorWrapper first = this.extractors.getFirst();
        while (true) {
            hlsExtractorWrapper = first;
            if (this.extractors.size() <= 1 || haveSamplesForEnabledTracks(hlsExtractorWrapper)) {
                break;
            }
            this.extractors.removeFirst().clear();
            first = this.extractors.getFirst();
        }
        return hlsExtractorWrapper;
    }

    private void discardSamplesForDisabledTracks(HlsExtractorWrapper hlsExtractorWrapper, long j) {
        if (!hlsExtractorWrapper.isPrepared()) {
            return;
        }
        int i = 0;
        while (true) {
            boolean[] zArr = this.extractorTrackEnabledStates;
            if (i >= zArr.length) {
                return;
            }
            if (!zArr[i]) {
                hlsExtractorWrapper.discardUntil(i, j);
            }
            i++;
        }
    }

    private boolean haveSamplesForEnabledTracks(HlsExtractorWrapper hlsExtractorWrapper) {
        if (!hlsExtractorWrapper.isPrepared()) {
            return false;
        }
        int i = 0;
        while (true) {
            boolean[] zArr = this.extractorTrackEnabledStates;
            if (i >= zArr.length) {
                return false;
            }
            if (zArr[i] && hlsExtractorWrapper.hasSamples(i)) {
                return true;
            }
            i++;
        }
    }

    private void restartFrom(long j) {
        this.pendingResetPositionUs = j;
        this.loadingFinished = false;
        if (this.loader.isLoading()) {
            this.loader.cancelLoading();
        } else {
            clearState();
            maybeStartLoading();
        }
    }

    private void clearState() {
        for (int i = 0; i < this.extractors.size(); i++) {
            this.extractors.get(i).clear();
        }
        this.extractors.clear();
        clearCurrentLoadable();
        this.previousTsLoadable = null;
    }

    private void clearCurrentLoadable() {
        this.currentTsLoadable = null;
        this.currentLoadable = null;
        this.currentLoadableException = null;
        this.currentLoadableExceptionCount = 0;
    }

    private void maybeStartLoading() {
        int i;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long nextLoadPositionUs = getNextLoadPositionUs();
        boolean z = this.currentLoadableException != null;
        boolean zUpdate = this.loadControl.update(this, this.downstreamPositionUs, nextLoadPositionUs, this.loader.isLoading() || z);
        if (z && !livePlaylist() && (i = this.currentLoadableExceptionCount) < this.minLoadableRetryCount) {
            if (jElapsedRealtime - this.currentLoadableExceptionTimestamp >= getRetryDelayMillis(i)) {
                this.currentLoadableException = null;
                this.loader.startLoading(this.currentLoadable, this);
                return;
            }
            return;
        }
        if (this.loader.isLoading() || !zUpdate) {
            return;
        }
        if (this.prepared && this.enabledTrackCount == 0) {
            return;
        }
        HlsChunkSource hlsChunkSource = this.chunkSource;
        TsChunk tsChunk = this.previousTsLoadable;
        long j = this.pendingResetPositionUs;
        if (j == Long.MIN_VALUE) {
            j = this.downstreamPositionUs;
        }
        hlsChunkSource.getChunkOperation(tsChunk, j, this.chunkOperationHolder);
        ChunkOperationHolder chunkOperationHolder = this.chunkOperationHolder;
        boolean z2 = chunkOperationHolder.endOfStream;
        Chunk chunk = chunkOperationHolder.chunk;
        chunkOperationHolder.clear();
        if (z2) {
            this.loadingFinished = true;
            this.loadControl.update(this, this.downstreamPositionUs, -1L, false);
            return;
        }
        if (chunk == null) {
            MtkDebugLogUtil.log("HlsSampleSource", "nextLoadable is null", 4);
            return;
        }
        if (this.loader.isLoading()) {
            MtkDebugLogUtil.log("HlsSampleSource", "Loader is working", 4);
            return;
        }
        this.currentLoadStartTimeMs = jElapsedRealtime;
        this.currentLoadable = chunk;
        if (isTsChunk(chunk)) {
            TsChunk tsChunk2 = (TsChunk) this.currentLoadable;
            if (isPendingReset()) {
                this.pendingResetPositionUs = Long.MIN_VALUE;
            }
            HlsExtractorWrapper hlsExtractorWrapper = tsChunk2.extractorWrapper;
            if (this.extractors.isEmpty() || this.extractors.getLast() != hlsExtractorWrapper) {
                hlsExtractorWrapper.init(this.loadControl.getAllocator());
                this.extractors.addLast(hlsExtractorWrapper);
            }
            notifyLoadStarted(tsChunk2.dataSpec.length, tsChunk2.type, tsChunk2.trigger, tsChunk2.format, tsChunk2.startTimeUs, tsChunk2.endTimeUs);
            this.currentTsLoadable = tsChunk2;
        } else {
            Chunk chunk2 = this.currentLoadable;
            notifyLoadStarted(chunk2.dataSpec.length, chunk2.type, chunk2.trigger, chunk2.format, -1L, -1L);
        }
        this.loader.startLoading(this.currentLoadable, this);
    }

    private long getNextLoadPositionUs() {
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.loadingFinished || (this.prepared && this.enabledTrackCount == 0)) {
            return -1L;
        }
        TsChunk tsChunk = this.currentTsLoadable;
        return tsChunk != null ? tsChunk.endTimeUs : this.previousTsLoadable.endTimeUs;
    }

    private boolean isTsChunk(Chunk chunk) {
        return chunk instanceof TsChunk;
    }

    private boolean isPendingReset() {
        return this.pendingResetPositionUs != Long.MIN_VALUE;
    }

    private long getRetryDelayMillis(long j) {
        return Math.min((j - 1) * 1000, 5000L);
    }

    long usToMs(long j) {
        return j / 1000;
    }

    private void notifyLoadStarted(final long j, final int i, final int i2, final Format format, final long j2, final long j3) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.hls.HlsSampleSource.1
            @Override // java.lang.Runnable
            public void run() {
                MtkDebugLogUtil.log("HlsSampleSource", "notifyLoadStarted.run", 4);
                HlsSampleSource.this.eventListener.onLoadStarted(HlsSampleSource.this.eventSourceId, j, i, i2, format, HlsSampleSource.this.usToMs(j2), HlsSampleSource.this.usToMs(j3));
            }
        });
    }

    private void notifyLoadCompleted(final long j, final int i, final int i2, final Format format, final long j2, final long j3, final long j4, final long j5) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.hls.HlsSampleSource.2
            @Override // java.lang.Runnable
            public void run() {
                MtkDebugLogUtil.log("HlsSampleSource", "notifyLoadCompleted", 4);
                HlsSampleSource.this.eventListener.onLoadCompleted(HlsSampleSource.this.eventSourceId, j, i, i2, format, HlsSampleSource.this.usToMs(j2), HlsSampleSource.this.usToMs(j3), j4, j5);
            }
        });
    }

    private void notifyLoadCanceled(final long j) {
        MtkDebugLogUtil.log("HlsSampleSource", "notifyLoadCanceled", 4);
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.hls.HlsSampleSource.3
            @Override // java.lang.Runnable
            public void run() {
                HlsSampleSource.this.eventListener.onLoadCanceled(HlsSampleSource.this.eventSourceId, j);
            }
        });
    }

    private void notifyLoadError(final IOException iOException) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.hls.HlsSampleSource.4
            @Override // java.lang.Runnable
            public void run() {
                HlsSampleSource.this.eventListener.onLoadError(HlsSampleSource.this.eventSourceId, iOException);
            }
        });
    }

    private void notifyDownstreamFormatChanged(final Format format, final int i, final long j) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.hls.HlsSampleSource.5
            @Override // java.lang.Runnable
            public void run() {
                HlsSampleSource.this.eventListener.onDownstreamFormatChanged(HlsSampleSource.this.eventSourceId, format, i, HlsSampleSource.this.usToMs(j));
            }
        });
    }

    private boolean livePlaylist() {
        return this.chunkSource.livePlaylist();
    }
}
