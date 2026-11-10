package com.google.android.exoplayer;

import android.os.Handler;
import com.google.android.exoplayer.util.MimeTypes;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class TsSiTableTrackRenderer extends SampleSourceTrackRenderer {
    protected final Handler eventHandler;
    private final EventListener eventListener;
    private MediaFormatHolder formatHolder;
    private boolean inputStreamEnded;
    private SampleHolder sampleHolder;

    public interface EventListener {
        void onTsSiTableNotify(ByteBuffer byteBuffer);
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected boolean isReady() {
        return true;
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer
    protected void onDiscontinuity(long j) throws ExoPlaybackException {
    }

    public TsSiTableTrackRenderer(SampleSource sampleSource, Handler handler, EventListener eventListener) {
        super(sampleSource);
        this.eventHandler = handler;
        this.eventListener = eventListener;
        this.sampleHolder = new SampleHolder(0);
        this.formatHolder = new MediaFormatHolder();
        this.inputStreamEnded = false;
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer
    protected boolean handlesTrack(MediaFormat mediaFormat) throws MediaCodecUtil.DecoderQueryException {
        return MimeTypes.isTsSi(mediaFormat.mimeType);
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected boolean isEnded() {
        return this.inputStreamEnded;
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer
    protected void doSomeWork(long j, long j2, boolean z) throws ExoPlaybackException {
        ByteBuffer byteBuffer;
        if (this.inputStreamEnded) {
            return;
        }
        int source = readSource(0L, this.formatHolder, this.sampleHolder);
        if (source == -3 && (byteBuffer = this.sampleHolder.data) != null) {
            notifyTsSiTable(byteBuffer);
        } else if (source == -1) {
            this.inputStreamEnded = true;
        }
    }

    private void notifyTsSiTable(final ByteBuffer byteBuffer) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.TsSiTableTrackRenderer.1
            @Override // java.lang.Runnable
            public void run() {
                TsSiTableTrackRenderer.this.eventListener.onTsSiTableNotify(byteBuffer);
            }
        });
    }
}
