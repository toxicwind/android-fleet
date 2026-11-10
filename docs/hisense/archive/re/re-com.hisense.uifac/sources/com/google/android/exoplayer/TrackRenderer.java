package com.google.android.exoplayer;

import com.google.android.exoplayer.util.Assertions;

/* JADX INFO: loaded from: classes.dex */
public abstract class TrackRenderer implements ExoPlayer.ExoPlayerComponent {
    protected int seekMode = 1;
    private int state;

    protected abstract boolean doPrepare(long j) throws ExoPlaybackException;

    protected abstract void doSomeWork(long j, long j2) throws ExoPlaybackException;

    protected abstract long getBufferedPositionUs();

    protected abstract long getDurationUs();

    protected abstract MediaFormat getFormat(int i);

    protected MediaClock getMediaClock() {
        return null;
    }

    protected abstract int getTrackCount();

    @Override // com.google.android.exoplayer.ExoPlayer.ExoPlayerComponent
    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
    }

    protected abstract boolean isEnded();

    protected abstract boolean isReady();

    protected abstract void maybeThrowError() throws ExoPlaybackException;

    protected void onDisabled(long j) throws ExoPlaybackException {
    }

    protected void onEnabled(int i, long j, boolean z) throws ExoPlaybackException {
    }

    protected void onReleased() throws ExoPlaybackException {
    }

    protected void onStarted() throws ExoPlaybackException {
    }

    protected void onStopped() throws ExoPlaybackException {
    }

    protected abstract void seekTo(long j) throws ExoPlaybackException;

    protected final int getState() {
        return this.state;
    }

    final int prepare(long j) throws ExoPlaybackException {
        Assertions.checkState(this.state == 0);
        boolean zDoPrepare = doPrepare(j);
        this.state = zDoPrepare ? 1 : 0;
        return zDoPrepare ? 1 : 0;
    }

    final void enable(int i, long j, boolean z) throws ExoPlaybackException {
        Assertions.checkState(this.state == 1);
        this.state = 2;
        onEnabled(i, j, z);
    }

    final void start() throws ExoPlaybackException {
        Assertions.checkState(this.state == 2);
        this.state = 3;
        onStarted();
    }

    final void stop() throws ExoPlaybackException {
        Assertions.checkState(this.state == 3);
        this.state = 2;
        onStopped();
    }

    final void disable(long j) throws ExoPlaybackException {
        Assertions.checkState(this.state == 2);
        this.state = 1;
        onDisabled(j);
    }

    final void release() throws ExoPlaybackException {
        int i = this.state;
        Assertions.checkState((i == 2 || i == 3 || i == -1) ? false : true);
        this.state = -1;
        onReleased();
    }

    public void setSeekMode(int i) {
        this.seekMode = i;
    }
}
