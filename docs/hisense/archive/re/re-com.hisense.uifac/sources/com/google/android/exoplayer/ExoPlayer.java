package com.google.android.exoplayer;

import android.annotation.TargetApi;
import android.media.AudioManager;
import android.os.Looper;
import com.google.android.exoplayer.drm.DrmSessionManager;

/* JADX INFO: loaded from: classes.dex */
public interface ExoPlayer {

    public interface ExoPlayerComponent {
        void handleMessage(int i, Object obj) throws ExoPlaybackException;
    }

    public interface Listener {
        void onPlayWhenReadyCommitted();

        void onPlayerError(ExoPlaybackException exoPlaybackException);

        void onPlayerStateChanged(boolean z, int i);
    }

    void addListener(Listener listener);

    void blockingSendMessage(ExoPlayerComponent exoPlayerComponent, int i, Object obj);

    int getBufferedPercentage();

    long getCurrentPosition();

    long getDuration();

    boolean getPlayWhenReady();

    Looper getPlaybackLooper();

    int getPlaybackState();

    int getSelectedTrack(int i);

    int getTrackCount(int i);

    MediaFormat getTrackFormat(int i, int i2);

    @TargetApi(21)
    boolean isTunnelingPlayback();

    void prepare(TrackRenderer... trackRendererArr);

    void release();

    void seekTo(long j);

    void sendMessage(ExoPlayerComponent exoPlayerComponent, int i, Object obj);

    void setDrmSessionManager(DrmSessionManager drmSessionManager);

    @TargetApi(21)
    void setMediaTunneling(int i, AudioManager audioManager);

    void setPlayWhenReady(boolean z);

    void setSeekMode(int i);

    void setSelectedTrack(int i, int i2);

    void setTrickMode(int i, int i2, int i3);

    void stop();

    public static final class Factory {
        public static ExoPlayer newInstance(int i, int i2, int i3) {
            return new ExoPlayerImpl(i, i2, i3);
        }
    }
}
