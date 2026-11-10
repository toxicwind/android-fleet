package com.google.android.exoplayer.chunk;

import android.os.Handler;
import android.os.SystemClock;
import com.google.android.exoplayer.LoadControl;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.MediaFormatHolder;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.SampleSource;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.extractor.DefaultTrackOutput;
import com.google.android.exoplayer.upstream.Loader;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ChunkSampleSource implements SampleSource, SampleSource.SampleSourceReader, Loader.Callback {
    private final int bufferSizeContribution;
    private final ChunkSource chunkSource;
    private long currentLoadStartTimeMs;
    private IOException currentLoadableException;
    private int currentLoadableExceptionCount;
    private long currentLoadableExceptionTimestamp;
    private final ChunkOperationHolder currentLoadableHolder;
    private DrmInitData downstreamDrmInitData;
    private Format downstreamFormat;
    private MediaFormat downstreamMediaFormat;
    private long downstreamPositionUs;
    private int enabledTrackCount;
    private final Handler eventHandler;
    private final EventListener eventListener;
    private final int eventSourceId;
    private long lastPerformedBufferOperation;
    private long lastSeekPositionUs;
    private final LoadControl loadControl;
    private Loader loader;
    private boolean loadingFinished;
    private final LinkedList<BaseMediaChunk> mediaChunks;
    private final int minLoadableRetryCount;
    private boolean pendingDiscontinuity;
    private long pendingResetPositionUs;
    private final List<BaseMediaChunk> readOnlyMediaChunks;
    protected final DefaultTrackOutput sampleQueue;
    private int state;

    public interface EventListener extends BaseChunkSampleSourceEventListener {
    }

    protected void onSampleRead(MediaChunk mediaChunk, SampleHolder sampleHolder) {
    }

    public ChunkSampleSource(ChunkSource chunkSource, LoadControl loadControl, int i, Handler handler, EventListener eventListener, int i2) {
        this(chunkSource, loadControl, i, handler, eventListener, i2, 3);
    }

    public ChunkSampleSource(ChunkSource chunkSource, LoadControl loadControl, int i, Handler handler, EventListener eventListener, int i2, int i3) {
        this.chunkSource = chunkSource;
        this.loadControl = loadControl;
        this.bufferSizeContribution = i;
        this.eventHandler = handler;
        this.eventListener = eventListener;
        this.eventSourceId = i2;
        this.minLoadableRetryCount = i3;
        this.currentLoadableHolder = new ChunkOperationHolder();
        LinkedList<BaseMediaChunk> linkedList = new LinkedList<>();
        this.mediaChunks = linkedList;
        this.readOnlyMediaChunks = Collections.unmodifiableList(linkedList);
        this.sampleQueue = new DefaultTrackOutput(loadControl.getAllocator());
        this.state = 0;
        this.pendingResetPositionUs = Long.MIN_VALUE;
    }

    @Override // com.google.android.exoplayer.SampleSource
    public SampleSource.SampleSourceReader register() {
        Assertions.checkState(this.state == 0);
        this.state = 1;
        return this;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public boolean prepare(long j) {
        int i = this.state;
        Assertions.checkState(i == 1 || i == 2);
        if (this.state == 2) {
            return true;
        }
        if (!this.chunkSource.prepare()) {
            return false;
        }
        if (this.chunkSource.getTrackCount() > 0) {
            this.loader = new Loader("Loader:" + this.chunkSource.getFormat(0).mimeType);
        }
        this.state = 2;
        return true;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public int getTrackCount() {
        int i = this.state;
        Assertions.checkState(i == 2 || i == 3);
        return this.chunkSource.getTrackCount();
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public MediaFormat getFormat(int i) {
        int i2 = this.state;
        Assertions.checkState(i2 == 2 || i2 == 3);
        return this.chunkSource.getFormat(i);
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void enable(int i, long j) {
        Assertions.checkState(this.state == 2);
        int i2 = this.enabledTrackCount;
        this.enabledTrackCount = i2 + 1;
        Assertions.checkState(i2 == 0);
        this.state = 3;
        this.chunkSource.enable(i);
        this.loadControl.register(this, this.bufferSizeContribution);
        this.downstreamFormat = null;
        this.downstreamMediaFormat = null;
        this.downstreamDrmInitData = null;
        this.downstreamPositionUs = j;
        this.lastSeekPositionUs = j;
        this.pendingDiscontinuity = false;
        restartFrom(j);
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void disable(int i) {
        Assertions.checkState(this.state == 3);
        int i2 = this.enabledTrackCount - 1;
        this.enabledTrackCount = i2;
        Assertions.checkState(i2 == 0);
        this.state = 2;
        try {
            this.chunkSource.disable(this.mediaChunks);
        } finally {
            this.loadControl.unregister(this);
            if (this.loader.isLoading()) {
                this.loader.cancelLoading();
            } else {
                this.sampleQueue.clear();
                this.mediaChunks.clear();
                clearCurrentLoadable();
                this.loadControl.trimAllocator();
            }
        }
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public boolean continueBuffering(int i, long j) {
        Assertions.checkState(this.state == 3);
        this.downstreamPositionUs = j;
        this.chunkSource.continueBuffering(j);
        updateLoadControl();
        return this.loadingFinished || !this.sampleQueue.isEmpty();
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public long readDiscontinuity(int i) {
        if (!this.pendingDiscontinuity) {
            return Long.MIN_VALUE;
        }
        this.pendingDiscontinuity = false;
        return this.lastSeekPositionUs;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public int readData(int i, long j, MediaFormatHolder mediaFormatHolder, SampleHolder sampleHolder) {
        Assertions.checkState(this.state == 3);
        this.downstreamPositionUs = j;
        if (this.pendingDiscontinuity || isPendingReset()) {
            return -2;
        }
        boolean z = !this.sampleQueue.isEmpty();
        BaseMediaChunk first = this.mediaChunks.getFirst();
        while (z && this.mediaChunks.size() > 1 && this.mediaChunks.get(1).getFirstSampleIndex() <= this.sampleQueue.getReadIndex()) {
            this.mediaChunks.removeFirst();
            first = this.mediaChunks.getFirst();
        }
        Format format = first.format;
        if (!format.equals(this.downstreamFormat)) {
            notifyDownstreamFormatChanged(format, first.trigger, first.startTimeUs);
        }
        this.downstreamFormat = format;
        if (z || first.isMediaFormatFinal) {
            MediaFormat mediaFormat = first.getMediaFormat();
            DrmInitData drmInitData = first.getDrmInitData();
            if (!mediaFormat.equals(this.downstreamMediaFormat) || !Util.areEqual(this.downstreamDrmInitData, drmInitData)) {
                float f = this.downstreamFormat.frameRate;
                if (f > 0.0f) {
                    mediaFormat.setFps((long) f);
                }
                mediaFormatHolder.format = mediaFormat;
                mediaFormatHolder.drmInitData = drmInitData;
                this.downstreamMediaFormat = mediaFormat;
                this.downstreamDrmInitData = drmInitData;
                return -4;
            }
            this.downstreamMediaFormat = mediaFormat;
            this.downstreamDrmInitData = drmInitData;
        }
        if (!z) {
            return this.loadingFinished ? -1 : -2;
        }
        if (!this.sampleQueue.getSample(sampleHolder)) {
            return -2;
        }
        sampleHolder.flags |= sampleHolder.timeUs < this.lastSeekPositionUs ? 134217728 : 0;
        onSampleRead(first, sampleHolder);
        return -3;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void seekToUs(long j) {
        boolean z = false;
        Assertions.checkState(this.state == 3);
        long j2 = isPendingReset() ? this.pendingResetPositionUs : this.downstreamPositionUs;
        this.downstreamPositionUs = j;
        this.lastSeekPositionUs = j;
        if (j2 == j) {
            return;
        }
        if (!isPendingReset() && this.sampleQueue.skipToKeyframeBefore(j)) {
            z = true;
        }
        if (z) {
            boolean z2 = !this.sampleQueue.isEmpty();
            while (z2 && this.mediaChunks.size() > 1 && this.mediaChunks.get(1).getFirstSampleIndex() <= this.sampleQueue.getReadIndex()) {
                this.mediaChunks.removeFirst();
            }
        } else {
            restartFrom(j);
        }
        this.pendingDiscontinuity = true;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void maybeThrowError() throws IOException {
        IOException iOException = this.currentLoadableException;
        if (iOException != null && this.currentLoadableExceptionCount > this.minLoadableRetryCount) {
            throw iOException;
        }
        if (this.currentLoadableHolder.chunk == null) {
            this.chunkSource.maybeThrowError();
        }
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public long getBufferedPositionUs() {
        Assertions.checkState(this.state == 3);
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.loadingFinished) {
            return -3L;
        }
        long largestParsedTimestampUs = this.sampleQueue.getLargestParsedTimestampUs();
        return largestParsedTimestampUs == Long.MIN_VALUE ? this.downstreamPositionUs : largestParsedTimestampUs;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void release() {
        Assertions.checkState(this.state != 3);
        Loader loader = this.loader;
        if (loader != null) {
            loader.release();
            this.loader = null;
        }
        this.state = 0;
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Callback
    public void onLoadCompleted(Loader.Loadable loadable) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j = jElapsedRealtime - this.currentLoadStartTimeMs;
        Chunk chunk = this.currentLoadableHolder.chunk;
        this.chunkSource.onChunkLoadCompleted(chunk);
        if (isMediaChunk(chunk)) {
            BaseMediaChunk baseMediaChunk = (BaseMediaChunk) chunk;
            notifyLoadCompleted(chunk.bytesLoaded(), baseMediaChunk.type, baseMediaChunk.trigger, baseMediaChunk.format, baseMediaChunk.startTimeUs, baseMediaChunk.endTimeUs, jElapsedRealtime, j);
        } else {
            notifyLoadCompleted(chunk.bytesLoaded(), chunk.type, chunk.trigger, chunk.format, -1L, -1L, jElapsedRealtime, j);
        }
        clearCurrentLoadable();
        updateLoadControl();
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Callback
    public void onLoadCanceled(Loader.Loadable loadable) {
        notifyLoadCanceled(this.currentLoadableHolder.chunk.bytesLoaded());
        clearCurrentLoadable();
        if (this.state == 3) {
            restartFrom(this.pendingResetPositionUs);
            return;
        }
        this.sampleQueue.clear();
        this.mediaChunks.clear();
        clearCurrentLoadable();
        this.loadControl.trimAllocator();
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Callback
    public void onLoadError(Loader.Loadable loadable, IOException iOException) {
        this.currentLoadableException = iOException;
        this.currentLoadableExceptionCount++;
        this.currentLoadableExceptionTimestamp = SystemClock.elapsedRealtime();
        notifyLoadError(iOException);
        this.chunkSource.onChunkLoadError(this.currentLoadableHolder.chunk, iOException);
        updateLoadControl();
    }

    private void restartFrom(long j) {
        this.pendingResetPositionUs = j;
        this.loadingFinished = false;
        if (this.loader.isLoading()) {
            this.loader.cancelLoading();
            return;
        }
        this.sampleQueue.clear();
        this.mediaChunks.clear();
        clearCurrentLoadable();
        updateLoadControl();
    }

    private void clearCurrentLoadable() {
        this.currentLoadableHolder.chunk = null;
        clearCurrentLoadableException();
    }

    private void clearCurrentLoadableException() {
        this.currentLoadableException = null;
        this.currentLoadableExceptionCount = 0;
    }

    private void updateLoadControl() {
        long j;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long nextLoadPositionUs = getNextLoadPositionUs();
        boolean z = this.currentLoadableException != null;
        boolean z2 = this.loader.isLoading() || z;
        if (z2 || ((this.currentLoadableHolder.chunk != null || nextLoadPositionUs == -1) && jElapsedRealtime - this.lastPerformedBufferOperation <= 2000)) {
            j = nextLoadPositionUs;
        } else {
            this.lastPerformedBufferOperation = jElapsedRealtime;
            doChunkOperation();
            boolean zDiscardUpstreamMediaChunks = discardUpstreamMediaChunks(this.currentLoadableHolder.queueSize);
            if (this.currentLoadableHolder.chunk == null) {
                j = -1;
            } else {
                if (zDiscardUpstreamMediaChunks) {
                    nextLoadPositionUs = getNextLoadPositionUs();
                }
                j = nextLoadPositionUs;
            }
        }
        boolean zUpdate = this.loadControl.update(this, this.downstreamPositionUs, j, z2);
        if (z) {
            if (jElapsedRealtime - this.currentLoadableExceptionTimestamp >= getRetryDelayMillis(this.currentLoadableExceptionCount)) {
                resumeFromBackOff();
            }
        } else {
            if (this.loader.isLoading() || !zUpdate) {
                return;
            }
            maybeStartLoading();
        }
    }

    private long getNextLoadPositionUs() {
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.loadingFinished) {
            return -1L;
        }
        return this.mediaChunks.getLast().endTimeUs;
    }

    private void resumeFromBackOff() {
        this.currentLoadableException = null;
        Chunk chunk = this.currentLoadableHolder.chunk;
        if (!isMediaChunk(chunk)) {
            doChunkOperation();
            discardUpstreamMediaChunks(this.currentLoadableHolder.queueSize);
            if (this.currentLoadableHolder.chunk == chunk) {
                this.loader.startLoading(chunk, this);
                return;
            } else {
                notifyLoadCanceled(chunk.bytesLoaded());
                maybeStartLoading();
                return;
            }
        }
        if (chunk == this.mediaChunks.getFirst()) {
            this.loader.startLoading(chunk, this);
            return;
        }
        BaseMediaChunk baseMediaChunkRemoveLast = this.mediaChunks.removeLast();
        Assertions.checkState(chunk == baseMediaChunkRemoveLast);
        doChunkOperation();
        this.mediaChunks.add(baseMediaChunkRemoveLast);
        if (this.currentLoadableHolder.chunk == chunk) {
            this.loader.startLoading(chunk, this);
            return;
        }
        notifyLoadCanceled(chunk.bytesLoaded());
        discardUpstreamMediaChunks(this.currentLoadableHolder.queueSize);
        clearCurrentLoadableException();
        maybeStartLoading();
    }

    private void maybeStartLoading() {
        Chunk chunk = this.currentLoadableHolder.chunk;
        if (chunk == null) {
            return;
        }
        this.currentLoadStartTimeMs = SystemClock.elapsedRealtime();
        if (isMediaChunk(chunk)) {
            BaseMediaChunk baseMediaChunk = (BaseMediaChunk) chunk;
            baseMediaChunk.init(this.sampleQueue);
            this.mediaChunks.add(baseMediaChunk);
            if (isPendingReset()) {
                this.pendingResetPositionUs = Long.MIN_VALUE;
            }
            notifyLoadStarted(baseMediaChunk.dataSpec.length, baseMediaChunk.type, baseMediaChunk.trigger, baseMediaChunk.format, baseMediaChunk.startTimeUs, baseMediaChunk.endTimeUs);
        } else {
            ((InitializationChunk) chunk).initTrackOutput(this.sampleQueue);
            notifyLoadStarted(chunk.dataSpec.length, chunk.type, chunk.trigger, chunk.format, -1L, -1L);
        }
        this.loader.startLoading(chunk, this);
    }

    private void doChunkOperation() {
        ChunkOperationHolder chunkOperationHolder = this.currentLoadableHolder;
        chunkOperationHolder.endOfStream = false;
        chunkOperationHolder.queueSize = this.readOnlyMediaChunks.size();
        ChunkSource chunkSource = this.chunkSource;
        List<BaseMediaChunk> list = this.readOnlyMediaChunks;
        long j = this.pendingResetPositionUs;
        if (j == Long.MIN_VALUE) {
            j = this.downstreamPositionUs;
        }
        chunkSource.getChunkOperation(list, j, this.currentLoadableHolder);
        this.loadingFinished = this.currentLoadableHolder.endOfStream;
    }

    private boolean discardUpstreamMediaChunks(int i) {
        if (this.mediaChunks.size() <= i) {
            return false;
        }
        long j = 0;
        long j2 = this.mediaChunks.getLast().endTimeUs;
        BaseMediaChunk baseMediaChunkRemoveLast = null;
        while (this.mediaChunks.size() > i) {
            baseMediaChunkRemoveLast = this.mediaChunks.removeLast();
            j = baseMediaChunkRemoveLast.startTimeUs;
            this.loadingFinished = false;
        }
        this.sampleQueue.discardUpstreamSamples(baseMediaChunkRemoveLast.getFirstSampleIndex());
        notifyUpstreamDiscarded(j, j2);
        return true;
    }

    private boolean isMediaChunk(Chunk chunk) {
        return chunk instanceof BaseMediaChunk;
    }

    private boolean isPendingReset() {
        return this.pendingResetPositionUs != Long.MIN_VALUE;
    }

    private long getRetryDelayMillis(long j) {
        return Math.min((j - 1) * 1000, 5000L);
    }

    protected final long usToMs(long j) {
        return j / 1000;
    }

    private void notifyLoadStarted(final long j, final int i, final int i2, final Format format, final long j2, final long j3) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.chunk.ChunkSampleSource.1
            @Override // java.lang.Runnable
            public void run() {
                ChunkSampleSource.this.eventListener.onLoadStarted(ChunkSampleSource.this.eventSourceId, j, i, i2, format, ChunkSampleSource.this.usToMs(j2), ChunkSampleSource.this.usToMs(j3));
            }
        });
    }

    private void notifyLoadCompleted(final long j, final int i, final int i2, final Format format, final long j2, final long j3, final long j4, final long j5) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.chunk.ChunkSampleSource.2
            @Override // java.lang.Runnable
            public void run() {
                ChunkSampleSource.this.eventListener.onLoadCompleted(ChunkSampleSource.this.eventSourceId, j, i, i2, format, ChunkSampleSource.this.usToMs(j2), ChunkSampleSource.this.usToMs(j3), j4, j5);
            }
        });
    }

    private void notifyLoadCanceled(final long j) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.chunk.ChunkSampleSource.3
            @Override // java.lang.Runnable
            public void run() {
                ChunkSampleSource.this.eventListener.onLoadCanceled(ChunkSampleSource.this.eventSourceId, j);
            }
        });
    }

    private void notifyLoadError(final IOException iOException) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.chunk.ChunkSampleSource.4
            @Override // java.lang.Runnable
            public void run() {
                ChunkSampleSource.this.eventListener.onLoadError(ChunkSampleSource.this.eventSourceId, iOException);
            }
        });
    }

    private void notifyUpstreamDiscarded(final long j, final long j2) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.chunk.ChunkSampleSource.5
            @Override // java.lang.Runnable
            public void run() {
                ChunkSampleSource.this.eventListener.onUpstreamDiscarded(ChunkSampleSource.this.eventSourceId, ChunkSampleSource.this.usToMs(j), ChunkSampleSource.this.usToMs(j2));
            }
        });
    }

    private void notifyDownstreamFormatChanged(final Format format, final int i, final long j) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.chunk.ChunkSampleSource.6
            @Override // java.lang.Runnable
            public void run() {
                ChunkSampleSource.this.eventListener.onDownstreamFormatChanged(ChunkSampleSource.this.eventSourceId, format, i, ChunkSampleSource.this.usToMs(j));
            }
        });
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void setTrickMode(boolean z) {
        this.chunkSource.setTrickMode(z);
    }
}
