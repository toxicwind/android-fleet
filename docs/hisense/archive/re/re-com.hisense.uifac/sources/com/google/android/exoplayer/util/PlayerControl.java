package com.google.android.exoplayer.util;

import android.widget.MediaController;
import com.google.android.exoplayer.ExoPlayer;

/* JADX INFO: loaded from: classes.dex */
public class PlayerControl implements MediaController.MediaPlayerControl {
    private final ExoPlayer exoPlayer;

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return true;
    }

    public PlayerControl(ExoPlayer exoPlayer) {
        this.exoPlayer = exoPlayer;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        throw new UnsupportedOperationException();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        return this.exoPlayer.getBufferedPercentage();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        if (this.exoPlayer.getDuration() == -1) {
            return 0;
        }
        return (int) this.exoPlayer.getCurrentPosition();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        if (this.exoPlayer.getDuration() == -1) {
            return 0;
        }
        return (int) this.exoPlayer.getDuration();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return this.exoPlayer.getPlayWhenReady();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void start() {
        this.exoPlayer.setPlayWhenReady(true);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        this.exoPlayer.setPlayWhenReady(false);
    }
}
