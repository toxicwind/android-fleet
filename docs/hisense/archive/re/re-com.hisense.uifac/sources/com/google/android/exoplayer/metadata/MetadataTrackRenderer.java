package com.google.android.exoplayer.metadata;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.MediaFormatHolder;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.SampleSource;
import com.google.android.exoplayer.SampleSourceTrackRenderer;
import com.google.android.exoplayer.util.Assertions;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class MetadataTrackRenderer<T> extends SampleSourceTrackRenderer implements Handler.Callback {
    private final MediaFormatHolder formatHolder;
    private boolean inputStreamEnded;
    private final Handler metadataHandler;
    private final MetadataParser<T> metadataParser;
    private final MetadataRenderer<T> metadataRenderer;
    private T pendingMetadata;
    private long pendingMetadataTimestamp;
    private final SampleHolder sampleHolder;

    public interface MetadataRenderer<T> {
        void onMetadata(T t);
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected long getBufferedPositionUs() {
        return -3L;
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected boolean isReady() {
        return true;
    }

    public MetadataTrackRenderer(SampleSource sampleSource, MetadataParser<T> metadataParser, MetadataRenderer<T> metadataRenderer, Looper looper) {
        super(sampleSource);
        Assertions.checkNotNull(metadataParser);
        this.metadataParser = metadataParser;
        Assertions.checkNotNull(metadataRenderer);
        this.metadataRenderer = metadataRenderer;
        this.metadataHandler = looper == null ? null : new Handler(looper, this);
        this.formatHolder = new MediaFormatHolder();
        this.sampleHolder = new SampleHolder(1);
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer
    protected boolean handlesTrack(MediaFormat mediaFormat) {
        return this.metadataParser.canParse(mediaFormat.mimeType);
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer
    protected void onDiscontinuity(long j) {
        this.pendingMetadata = null;
        this.inputStreamEnded = false;
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer
    protected void doSomeWork(long j, long j2, boolean z) throws ExoPlaybackException {
        if (!this.inputStreamEnded && this.pendingMetadata == null) {
            this.sampleHolder.clearData();
            int source = readSource(j, this.formatHolder, this.sampleHolder);
            if (source == -3) {
                SampleHolder sampleHolder = this.sampleHolder;
                this.pendingMetadataTimestamp = sampleHolder.timeUs;
                try {
                    this.pendingMetadata = this.metadataParser.parse(sampleHolder.data.array(), this.sampleHolder.size);
                } catch (IOException e) {
                    throw new ExoPlaybackException(e);
                }
            } else if (source == -1) {
                this.inputStreamEnded = true;
            }
        }
        T t = this.pendingMetadata;
        if (t == null || this.pendingMetadataTimestamp > j) {
            return;
        }
        invokeRenderer(t);
        this.pendingMetadata = null;
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onDisabled(long j) throws ExoPlaybackException {
        this.pendingMetadata = null;
        super.onDisabled(j);
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected boolean isEnded() {
        return this.inputStreamEnded;
    }

    private void invokeRenderer(T t) {
        Handler handler = this.metadataHandler;
        if (handler != null) {
            handler.obtainMessage(0, t).sendToTarget();
        } else {
            invokeRendererInternal(t);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 0) {
            return false;
        }
        invokeRendererInternal(message.obj);
        return true;
    }

    private void invokeRendererInternal(T t) {
        this.metadataRenderer.onMetadata(t);
    }
}
