package com.google.android.exoplayer;

/* JADX INFO: loaded from: classes.dex */
public final class DummyTrackRenderer extends TrackRenderer {
    @Override // com.google.android.exoplayer.TrackRenderer
    protected boolean doPrepare(long j) throws ExoPlaybackException {
        return true;
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected int getTrackCount() {
        return 0;
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected MediaFormat getFormat(int i) {
        throw new IllegalStateException();
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected boolean isEnded() {
        throw new IllegalStateException();
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected boolean isReady() {
        throw new IllegalStateException();
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected void seekTo(long j) {
        throw new IllegalStateException();
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected void doSomeWork(long j, long j2) {
        throw new IllegalStateException();
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected void maybeThrowError() {
        throw new IllegalStateException();
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected long getDurationUs() {
        throw new IllegalStateException();
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected long getBufferedPositionUs() {
        throw new IllegalStateException();
    }
}
