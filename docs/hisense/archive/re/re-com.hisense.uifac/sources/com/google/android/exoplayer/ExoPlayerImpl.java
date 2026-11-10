package com.google.android.exoplayer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.exoplayer.drm.DrmSessionManager;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes.dex */
final class ExoPlayerImpl implements ExoPlayer {
    private final Handler eventHandler;
    private final ExoPlayerImplInternal internalPlayer;
    private final CopyOnWriteArraySet<ExoPlayer.Listener> listeners;
    private int pendingPlayWhenReadyAcks;
    private boolean playWhenReady;
    private int playbackState;
    private final int[] selectedTrackIndices;
    private final MediaFormat[][] trackFormats;

    @SuppressLint({"HandlerLeak"})
    public ExoPlayerImpl(int i, int i2, int i3) {
        Log.i("ExoPlayerImpl", "Init 1.5.15, MTK version 0.6");
        this.playWhenReady = false;
        this.playbackState = 1;
        this.listeners = new CopyOnWriteArraySet<>();
        this.trackFormats = new MediaFormat[i][];
        this.selectedTrackIndices = new int[i];
        Handler handler = new Handler() { // from class: com.google.android.exoplayer.ExoPlayerImpl.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                ExoPlayerImpl.this.handleEvent(message);
            }
        };
        this.eventHandler = handler;
        this.internalPlayer = new ExoPlayerImplInternal(handler, this.playWhenReady, this.selectedTrackIndices, i2, i3);
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public Looper getPlaybackLooper() {
        return this.internalPlayer.getPlaybackLooper();
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public void addListener(ExoPlayer.Listener listener) {
        this.listeners.add(listener);
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public int getPlaybackState() {
        return this.playbackState;
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public void prepare(TrackRenderer... trackRendererArr) {
        Arrays.fill(this.trackFormats, (Object) null);
        this.internalPlayer.prepare(trackRendererArr);
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public int getTrackCount(int i) {
        MediaFormat[][] mediaFormatArr = this.trackFormats;
        if (mediaFormatArr[i] != null) {
            return mediaFormatArr[i].length;
        }
        return 0;
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public MediaFormat getTrackFormat(int i, int i2) {
        if (i >= 0) {
            MediaFormat[][] mediaFormatArr = this.trackFormats;
            if (i < mediaFormatArr.length && i2 >= 0 && mediaFormatArr[i] != null && i2 < mediaFormatArr[i].length) {
                return mediaFormatArr[i][i2];
            }
        }
        return null;
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public void setSelectedTrack(int i, int i2) {
        int[] iArr = this.selectedTrackIndices;
        if (iArr[i] != i2) {
            iArr[i] = i2;
            this.internalPlayer.setRendererSelectedTrack(i, i2);
        }
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public int getSelectedTrack(int i) {
        return this.selectedTrackIndices[i];
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public void setPlayWhenReady(boolean z) {
        if (this.playWhenReady != z) {
            this.playWhenReady = z;
            this.pendingPlayWhenReadyAcks++;
            this.internalPlayer.setPlayWhenReady(z);
            this.eventHandler.obtainMessage(2, this.playbackState, 0).sendToTarget();
        }
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public boolean getPlayWhenReady() {
        return this.playWhenReady;
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public void seekTo(long j) {
        this.internalPlayer.seekTo(j);
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public void stop() {
        this.internalPlayer.stop();
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public void release() {
        Log.i("ExoPlayerImpl", "Release 1.5.15, MTK version 0.6");
        this.internalPlayer.release();
        this.eventHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public void sendMessage(ExoPlayer.ExoPlayerComponent exoPlayerComponent, int i, Object obj) {
        this.internalPlayer.sendMessage(exoPlayerComponent, i, obj);
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public void blockingSendMessage(ExoPlayer.ExoPlayerComponent exoPlayerComponent, int i, Object obj) {
        this.internalPlayer.blockingSendMessage(exoPlayerComponent, i, obj);
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public long getDuration() {
        return this.internalPlayer.getDuration();
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public long getCurrentPosition() {
        return this.internalPlayer.getCurrentPosition();
    }

    public long getBufferedPosition() {
        return this.internalPlayer.getBufferedPosition();
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public int getBufferedPercentage() {
        long bufferedPosition = getBufferedPosition();
        long duration = getDuration();
        if (bufferedPosition == -1 || duration == -1) {
            return 0;
        }
        return (int) (duration != 0 ? (bufferedPosition * 100) / duration : 100L);
    }

    void handleEvent(Message message) {
        int i = message.what;
        if (i == 1) {
            Object obj = message.obj;
            MediaFormat[][] mediaFormatArr = this.trackFormats;
            System.arraycopy(obj, 0, mediaFormatArr, 0, mediaFormatArr.length);
            this.playbackState = message.arg1;
            Iterator<ExoPlayer.Listener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onPlayerStateChanged(this.playWhenReady, this.playbackState);
            }
            return;
        }
        if (i == 2) {
            this.playbackState = message.arg1;
            Iterator<ExoPlayer.Listener> it2 = this.listeners.iterator();
            while (it2.hasNext()) {
                it2.next().onPlayerStateChanged(this.playWhenReady, this.playbackState);
            }
            return;
        }
        if (i != 3) {
            if (i != 4) {
                return;
            }
            ExoPlaybackException exoPlaybackException = (ExoPlaybackException) message.obj;
            Iterator<ExoPlayer.Listener> it3 = this.listeners.iterator();
            while (it3.hasNext()) {
                it3.next().onPlayerError(exoPlaybackException);
            }
            return;
        }
        int i2 = this.pendingPlayWhenReadyAcks - 1;
        this.pendingPlayWhenReadyAcks = i2;
        if (i2 == 0) {
            Iterator<ExoPlayer.Listener> it4 = this.listeners.iterator();
            while (it4.hasNext()) {
                it4.next().onPlayWhenReadyCommitted();
            }
        }
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public void setTrickMode(int i, int i2, int i3) {
        this.internalPlayer.setTrickMode(i, i2, i3);
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public void setDrmSessionManager(DrmSessionManager drmSessionManager) {
        this.internalPlayer.setDrmSessionManager(drmSessionManager);
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    @TargetApi(21)
    public void setMediaTunneling(int i, AudioManager audioManager) {
        this.internalPlayer.setMediaTunneling(i, audioManager);
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    @TargetApi(21)
    public boolean isTunnelingPlayback() {
        return this.internalPlayer.isTunnelingPlayback();
    }

    @Override // com.google.android.exoplayer.ExoPlayer
    public void setSeekMode(int i) {
        this.internalPlayer.setSeekMode(i);
    }
}
