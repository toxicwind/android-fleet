package com.google.android.exoplayer.util;

import android.media.MediaCodec;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import com.google.android.exoplayer.DummyTrackRenderer;
import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.MediaCodecTrackRenderer;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.TrackRenderer;
import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.TextRenderer;
import com.google.android.exoplayer.upstream.FrameworkDataSource;
import com.mediatek.twoworlds.tv.MtkTvScanCeBase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes.dex */
public class MtkPlayerControl extends PlayerControl implements ExoPlayer.Listener, TextRenderer, Handler.Callback, MediaCodecVideoTrackRenderer.EventListener {
    private CaptionListener captionListener;
    private final ExoPlayer exoPlayer;
    private final Handler handler;
    private boolean inSeek;
    private int lastReportedPlaybackState;
    private int lastSelectedProgramIndex;
    private final CopyOnWriteArraySet<Listener> listeners;
    private int nextTrickPosition;
    private List<Integer> programList;
    private boolean renderedFirstFrame;
    private TrackRenderer[] renderers;
    private long seekBeginTime;
    private final HandlerThread seekingControlThread;
    private int[] selectedTracks;
    private SourceController sourceController;
    private volatile int speed;
    private boolean subtitleOnCue;
    private boolean trickInSeekToBegin;
    private volatile boolean trickInSeekToPause;
    private volatile boolean trickInSeekToPlay;
    private int trickMode;

    public interface CaptionListener {
        void onCues(List<Cue> list);
    }

    public interface Listener {
        void onPrepared();

        void onSeekReadyInTrick();

        void onTrackStateChanged(int i, int i2, boolean z);

        void onTrickEnded(int i, int i2);
    }

    private double getRealSpeed(int i) {
        switch (i) {
            case 4:
            case 9:
                return 2.0d;
            case 5:
            case 10:
                return 4.0d;
            case 6:
            case 11:
                return 8.0d;
            case 7:
            case 12:
                return 16.0d;
            case 8:
            case 13:
                return 32.0d;
            case 14:
            case 19:
                return 0.5d;
            case 15:
            case 20:
                return 0.25d;
            case 16:
            case 21:
                return 0.125d;
            case 17:
            case 22:
                return 0.0625d;
            case 18:
            case 23:
                return 0.03125d;
            default:
                return 1.0d;
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer.EventListener
    public void onCryptoError(MediaCodec.CryptoException cryptoException) {
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer.EventListener
    public void onDecoderInitializationError(MediaCodecTrackRenderer.DecoderInitializationException decoderInitializationException) {
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer.EventListener
    public void onDecoderInitialized(String str, long j, long j2) {
    }

    @Override // com.google.android.exoplayer.MediaCodecVideoTrackRenderer.EventListener
    public void onDrawnToSurface(Surface surface) {
    }

    @Override // com.google.android.exoplayer.MediaCodecVideoTrackRenderer.EventListener
    public void onDroppedFrames(int i, long j) {
    }

    @Override // com.google.android.exoplayer.ExoPlayer.Listener
    public void onPlayWhenReadyCommitted() {
    }

    @Override // com.google.android.exoplayer.ExoPlayer.Listener
    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
    }

    @Override // com.google.android.exoplayer.MediaCodecVideoTrackRenderer.EventListener
    public void onVideoSizeChanged(int i, int i2, int i3, float f) {
    }

    public final class SourceController {
        private final FrameworkDataSource frameworkDataSource;

        public SourceController(MtkPlayerControl mtkPlayerControl, FrameworkDataSource frameworkDataSource) {
            this.frameworkDataSource = frameworkDataSource;
        }

        protected void pause() {
            FrameworkDataSource frameworkDataSource = this.frameworkDataSource;
            if (frameworkDataSource != null) {
                frameworkDataSource.tryDisconnect();
            }
        }

        protected void start(long j) {
            FrameworkDataSource frameworkDataSource = this.frameworkDataSource;
            if (frameworkDataSource != null) {
                frameworkDataSource.tryResume(j);
            }
        }
    }

    public MtkPlayerControl(ExoPlayer exoPlayer) {
        super(exoPlayer);
        this.lastSelectedProgramIndex = -1;
        this.nextTrickPosition = Integer.MIN_VALUE;
        this.seekBeginTime = 0L;
        this.exoPlayer = exoPlayer;
        exoPlayer.addListener(this);
        this.listeners = new CopyOnWriteArraySet<>();
        int[] iArr = new int[6];
        this.selectedTracks = iArr;
        iArr[2] = -1;
        PriorityHandlerThread priorityHandlerThread = new PriorityHandlerThread(MtkPlayerControl.class.getSimpleName() + ":Handler", -4);
        this.seekingControlThread = priorityHandlerThread;
        priorityHandlerThread.start();
        this.handler = new Handler(this.seekingControlThread.getLooper(), this);
        this.renderedFirstFrame = false;
        this.speed = 3;
        this.trickMode = -1;
        this.lastReportedPlaybackState = 1;
        this.programList = null;
        this.inSeek = false;
        this.subtitleOnCue = false;
        this.trickInSeekToPlay = false;
        this.trickInSeekToBegin = false;
        this.trickInSeekToPause = false;
    }

    public void updateTrackRenderers(TrackRenderer[] trackRendererArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("update newest TrackRenderers, is null(");
        sb.append(trackRendererArr == null);
        sb.append(")");
        Log.i("MtkPlayerControl", sb.toString());
        this.renderers = trackRendererArr;
        if (trackRendererArr == null || trackRendererArr[0] == null || !(trackRendererArr[0] instanceof MediaCodecVideoTrackRenderer)) {
            return;
        }
        ((MediaCodecVideoTrackRenderer) trackRendererArr[0]).addEventListener(this);
    }

    public void setSourceController(SourceController sourceController) {
        this.sourceController = sourceController;
    }

    @Override // com.google.android.exoplayer.util.PlayerControl, android.widget.MediaController.MediaPlayerControl
    public void start() {
        SourceController sourceController;
        MtkDebugLogUtil.log("MtkPlayerControl", "start called", 1);
        if (this.lastReportedPlaybackState == 1) {
            MtkDebugLogUtil.log("MtkPlayerControl", "Wrong State: " + this.lastReportedPlaybackState + " start end!", 1);
            return;
        }
        if (this.renderers == null) {
            Log.e("MtkPlayerControl", "renderers is null!");
            return;
        }
        if (this.speed == 1 && (sourceController = this.sourceController) != null) {
            sourceController.start(this.exoPlayer.getCurrentPosition());
        }
        MtkDebugLogUtil.log("MtkPlayerControl", "trickMode=" + this.trickMode + ",trickInSeekToPlay=" + this.trickInSeekToPlay + ",speed=" + this.speed, 1);
        if (this.trickMode == 0 && !this.trickInSeekToPlay && !this.trickInSeekToBegin) {
            this.trickInSeekToPlay = true;
            if (this.trickInSeekToPause) {
                this.trickInSeekToPause = false;
                return;
            }
            return;
        }
        if (this.trickInSeekToBegin) {
            this.trickInSeekToBegin = false;
        }
        if (videoOnly() && this.exoPlayer.isTunnelingPlayback() && this.trickMode == -1) {
            this.exoPlayer.sendMessage(this.renderers[0], MtkTvScanCeBase.OPERATOR_BEIJING_GEHUAYOUXIAN, 3);
        } else if (this.exoPlayer.isTunnelingPlayback() && this.trickMode == -1 && this.speed == 1) {
            this.exoPlayer.sendMessage(this.renderers[0], MtkTvScanCeBase.OPERATOR_DALIAN_GUANGDIAN, 3);
        }
        if (this.speed != 3) {
            this.speed = 3;
            this.exoPlayer.setTrickMode(this.speed, 0, -1);
            int i = this.trickMode;
            if (i == 2 || i == 4 || i == 3) {
                MtkDebugLogUtil.log("MtkPlayerControl", "trickMode:" + this.trickMode + ",FF2X to play, seekNextPosition", 1);
                seekNextPosition(3);
            }
            if (this.renderers[1] != null && (!getRendererEnabled(1) || getRendererChanged(1))) {
                setRendererEnabled(1, true);
            }
            if (this.renderers[2] != null && !getRendererEnabled(2)) {
                setRendererEnabled(2, true);
            }
            this.trickMode = -1;
        }
        super.start();
        MtkDebugLogUtil.log("MtkPlayerControl", "start called end ", 1);
    }

    @Override // com.google.android.exoplayer.util.PlayerControl, android.widget.MediaController.MediaPlayerControl
    public void pause() {
        MtkDebugLogUtil.log("MtkPlayerControl", "pause called", 1);
        if (this.trickMode == 0 && !this.trickInSeekToPause) {
            this.trickInSeekToPause = true;
            if (this.trickInSeekToPlay) {
                this.trickInSeekToPlay = false;
                return;
            }
            return;
        }
        super.pause();
        if (this.speed != 1) {
            this.speed = 1;
            this.exoPlayer.setTrickMode(this.speed, 0, -1);
        }
        SourceController sourceController = this.sourceController;
        if (sourceController != null) {
            sourceController.pause();
        }
        if (videoOnly() && this.exoPlayer.isTunnelingPlayback() && this.trickMode == -1) {
            this.exoPlayer.sendMessage(this.renderers[0], MtkTvScanCeBase.OPERATOR_BEIJING_GEHUAYOUXIAN, 1);
        }
        this.trickMode = -1;
        MtkDebugLogUtil.log("MtkPlayerControl", "pause called end", 1);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i) {
        long jMin = this.exoPlayer.getDuration() == -1 ? 0L : Math.min(Math.max(0, i), getDuration());
        MtkDebugLogUtil.log("MtkPlayerControl", "seekTo called, seekPosition(Ms): " + jMin, 1);
        MtkDebugLogUtil.log("MtkPlayerControl", "[Performance]seekTo:" + i + "(ms)", 32);
        this.renderedFirstFrame = false;
        if (this.speed == 2) {
            this.speed = 1;
            this.exoPlayer.setTrickMode(this.speed, 0, -1);
        } else if (this.speed == 3 && !this.exoPlayer.getPlayWhenReady() && this.exoPlayer.isTunnelingPlayback() && this.trickMode == -1) {
            this.speed = 1;
            MtkDebugLogUtil.log("MtkPlayerControl", "Last memory pause seek, set speed=" + this.speed, 1);
        }
        this.exoPlayer.seekTo(jMin);
        this.inSeek = true;
    }

    public boolean audioOnly() {
        TrackRenderer[] trackRendererArr = this.renderers;
        return trackRendererArr[0] == null || (trackRendererArr[0] instanceof DummyTrackRenderer) || this.exoPlayer.getTrackCount(0) == 0 || (!getRendererEnabled(0) && getRendererEnabled(1));
    }

    private boolean videoOnly() {
        TrackRenderer[] trackRendererArr = this.renderers;
        return trackRendererArr[1] == null || (trackRendererArr[1] instanceof DummyTrackRenderer) || this.exoPlayer.getTrackCount(1) == 0;
    }

    public int getCurrentSpeed() {
        return this.speed;
    }

    public void setSelectedTrack(int i, int i2) {
        MtkDebugLogUtil.log("MtkPlayerControl", "setSelectedTrack called, rendererType: " + i + ", trackIndex: " + i2, 1);
        if (this.trickMode != -1 || this.speed == 1) {
            MtkDebugLogUtil.log("MtkPlayerControl", "tricking or pause, just remember the selected track index:" + i2, 1);
            this.selectedTracks[i] = i2;
            return;
        }
        this.exoPlayer.setSelectedTrack(i, i2);
        this.selectedTracks[i] = i2;
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onTrackStateChanged(i, i2, i2 != -1);
        }
    }

    public int getTrackCount(int i) {
        return this.exoPlayer.getTrackCount(i);
    }

    public int getSelectedTrack(int i) {
        if (this.trickMode != -1) {
            int i2 = this.selectedTracks[i];
            MtkDebugLogUtil.log("MtkPlayerControl", "tricking, return the remembered selected track index:" + i2, 1);
            return i2;
        }
        if (getTrackCount(i) == 0) {
            return -1;
        }
        return this.exoPlayer.getSelectedTrack(i);
    }

    public MediaFormat getTrackFormat(int i, int i2) {
        return this.exoPlayer.getTrackFormat(i, i2);
    }

    public void setRendererEnabled(int i, boolean z) {
        if (z) {
            this.exoPlayer.setSelectedTrack(i, this.selectedTracks[i]);
        } else {
            this.exoPlayer.setSelectedTrack(i, -1);
        }
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onTrackStateChanged(i, this.selectedTracks[i], z);
        }
    }

    public boolean getRendererEnabled(int i) {
        return this.exoPlayer.getSelectedTrack(i) >= 0;
    }

    public boolean getRendererChanged(int i) {
        return this.selectedTracks[i] != this.exoPlayer.getSelectedTrack(i);
    }

    public void setVideoOutput(String str) {
        TrackRenderer[] trackRendererArr = this.renderers;
        if (trackRendererArr == null || trackRendererArr[0] == null || str == null) {
            Log.i("MtkPlayerControl", "Video renderer is null, output selection is invalid!");
            return;
        }
        if (!"OUTPUT_VIDEO_MAIN".equals(str) && !"OUTPUT_VIDEO_SUB".equals(str) && !"OUTPUT_GRAPHIC".equals(str)) {
            throw new UnsupportedOperationException("Unsupported output selection: " + str);
        }
        Log.i("MtkPlayerControl", "setVideoOutput device is " + str);
        Bundle bundle = new Bundle();
        bundle.putString("X-tv-output-path", str);
        this.exoPlayer.sendMessage(this.renderers[0], MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHENGWANG, bundle);
    }

    public void maybeSelectProgramForNativeTS(int i) {
        int i2;
        int i3;
        this.programList = new ArrayList();
        TrackRenderer[] trackRendererArr = this.renderers;
        if (trackRendererArr[0] == null || (trackRendererArr[0] instanceof DummyTrackRenderer)) {
            i2 = -1;
        } else {
            int trackCount = this.exoPlayer.getTrackCount(0);
            i2 = -1;
            for (int i4 = 0; i4 < trackCount; i4++) {
                MediaFormat trackFormat = this.exoPlayer.getTrackFormat(0, i4);
                int i5 = trackFormat.group;
                if (i5 == -1) {
                    if (!"1".equals(Util.getProp("vendor.mtk.auto_test", "")) || getTrackCount(2) <= 0) {
                        return;
                    }
                    setSelectedTrack(2, 0);
                    return;
                }
                if (!this.programList.contains(Integer.valueOf(i5))) {
                    this.programList.add(Integer.valueOf(trackFormat.group));
                }
                if (i2 == -1 && i < this.programList.size() && trackFormat.group == this.programList.get(i).intValue()) {
                    i2 = i4;
                }
            }
        }
        TrackRenderer[] trackRendererArr2 = this.renderers;
        if (trackRendererArr2[1] != null && !(trackRendererArr2[1] instanceof DummyTrackRenderer)) {
            int trackCount2 = this.exoPlayer.getTrackCount(1);
            i3 = 0;
            while (true) {
                if (i3 >= trackCount2) {
                    i3 = -1;
                    break;
                }
                MediaFormat trackFormat2 = this.exoPlayer.getTrackFormat(1, i3);
                int i6 = trackFormat2.group;
                if (i6 == -1) {
                    if (!"1".equals(Util.getProp("vendor.mtk.auto_test", "")) || getTrackCount(2) <= 0) {
                        return;
                    }
                    setSelectedTrack(2, 0);
                    return;
                }
                if (!this.programList.contains(Integer.valueOf(i6))) {
                    this.programList.add(Integer.valueOf(trackFormat2.group));
                }
                if (i < this.programList.size() && trackFormat2.group == this.programList.get(i).intValue()) {
                    break;
                } else {
                    i3++;
                }
            }
        } else {
            i3 = -1;
            break;
        }
        if (i >= this.programList.size()) {
            Log.w("MtkPlayerControl", "[NativeTS] select program index(" + i + ") is invalid!");
            return;
        }
        Log.i("MtkPlayerControl", "[NativeTS] program(" + this.programList.get(i) + "), videoTrackIndex(" + i2 + "), audioTrackIndex(" + i3 + ")");
        setSelectedTrack(0, i2);
        setSelectedTrack(1, i3);
        if ("1".equals(Util.getProp("vendor.mtk.auto_test", "")) && getTrackCount(2) > 0) {
            setSelectedTrack(2, 0);
        } else {
            setSelectedTrack(2, -1);
        }
        if (this.lastSelectedProgramIndex >= 0) {
            seekTo(0);
        }
        this.lastSelectedProgramIndex = i;
    }

    public List<Integer> getProgramList() {
        return this.programList;
    }

    private int getDirection(int i) {
        if ((i >= 4 && i <= 8) || (i >= 14 && i <= 18)) {
            return 1;
        }
        if (i >= 9 && i <= 13) {
            return -1;
        }
        if (i >= 19 && i <= 23) {
            return -1;
        }
        Log.e("MtkPlayerControl", "currentSpeed " + i + " is not a trick speed!");
        return 1;
    }

    private int getNextTrickPosition(int i) {
        int currentPosition = getCurrentPosition();
        int direction = ((int) (((double) getDirection(i)) * getRealSpeed(i) * 1000.0d)) + currentPosition;
        if (direction < 0) {
            return currentPosition > 0 ? 0 : Integer.MIN_VALUE;
        }
        long duration = this.exoPlayer.getDuration();
        return ((long) direction) > duration ? (int) duration : direction;
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void setCaptionListener(CaptionListener captionListener) {
        this.captionListener = captionListener;
    }

    @Override // com.google.android.exoplayer.text.TextRenderer
    public void onCues(List<Cue> list) {
        if (this.captionListener != null) {
            if ((this.selectedTracks[2] != -1 && getRendererEnabled(2)) || (this.selectedTracks[5] != -1 && getRendererEnabled(5))) {
                this.captionListener.onCues(list);
                if (this.subtitleOnCue) {
                    return;
                }
                this.subtitleOnCue = true;
                MtkDebugLogUtil.log("MtkPlayerControl", "EXO_SUBTITLE_PASS", 32);
                return;
            }
            this.captionListener.onCues(Collections.emptyList());
        }
    }

    @Override // com.google.android.exoplayer.ExoPlayer.Listener
    public void onPlayerStateChanged(boolean z, int i) {
        int i2;
        MtkDebugLogUtil.log("MtkPlayerControl", "onPlayerStateChanged trickMode is " + this.trickMode + "State is " + i, 1);
        if (i == 4 && this.speed != 1 && this.lastReportedPlaybackState != 4 && ((this.renderedFirstFrame || (audioOnly() && this.inSeek)) && ((i2 = this.trickMode) == 0 || i2 == 3))) {
            if (this.renderedFirstFrame) {
                this.renderedFirstFrame = false;
            }
            this.handler.sendEmptyMessage(1);
        }
        int i3 = this.lastReportedPlaybackState;
        if ((i3 == 1 || i3 == 2) && (i == 3 || i == 4 || i == 5)) {
            Iterator<Listener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onPrepared();
            }
        }
        if (i == 4) {
            MtkDebugLogUtil.log("MtkPlayerControl", "EXO_PLAY_PASS", 32);
            if ("1".equals(Util.getProp("vendor.mtk.auto_test", ""))) {
                Log.d("MtkPlayerControl", "EXO_PLAY_PASS");
            }
            if (this.inSeek) {
                this.inSeek = false;
                MtkDebugLogUtil.log("MtkPlayerControl", "EXO_SEEK_PASS", 32);
            }
            if (audioOnly()) {
                MtkDebugLogUtil.log("MtkPlayerControl", "EXO_AUDIO_ONLY_PASS", 32);
            }
        }
        this.lastReportedPlaybackState = i;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 1) {
            return false;
        }
        seekNextPosition(this.speed);
        return true;
    }

    private void seekNextPosition(int i) {
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onSeekReadyInTrick();
        }
        if (audioOnly()) {
            if (this.trickInSeekToPlay) {
                start();
                this.trickInSeekToPlay = false;
                this.trickInSeekToPause = false;
                return;
            } else if (this.trickInSeekToPause) {
                pause();
                this.trickInSeekToPause = false;
                return;
            }
        }
        if (i >= 5 && i <= 13) {
            try {
                long jElapsedRealtime = android.os.SystemClock.elapsedRealtime() - this.seekBeginTime;
                if (jElapsedRealtime < 980) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("sleep time = ");
                    long j = 980 - jElapsedRealtime;
                    sb.append(j);
                    Log.i("MtkPlayerControl", sb.toString());
                    Thread.sleep(j);
                } else {
                    Log.i("MtkPlayerControl", "trickInSeekDelayTime = " + jElapsedRealtime);
                }
                this.seekBeginTime = android.os.SystemClock.elapsedRealtime();
            } catch (InterruptedException e) {
                Log.i("MtkPlayerControl", "Thread interrupted, ignore next seek.\n" + e.toString());
                return;
            }
        }
        if (i == 4 && !audioOnly()) {
            if (!this.exoPlayer.getPlayWhenReady()) {
                this.exoPlayer.setPlayWhenReady(true);
            }
            setRendererEnabled(1, false);
            this.exoPlayer.setTrickMode(i, 0, this.trickMode);
            return;
        }
        if (this.trickInSeekToPlay && !audioOnly()) {
            start();
            this.trickInSeekToPlay = false;
            this.trickInSeekToPause = false;
            return;
        }
        if (this.trickInSeekToPause && !audioOnly()) {
            pause();
            this.trickInSeekToPause = false;
            return;
        }
        long duration = this.exoPlayer.getDuration();
        if (this.nextTrickPosition == 0) {
            for (Listener listener : this.listeners) {
                this.trickInSeekToBegin = true;
                listener.onTrickEnded(i, 0);
            }
            this.nextTrickPosition = Integer.MIN_VALUE;
            return;
        }
        int nextTrickPosition = getNextTrickPosition(i);
        this.nextTrickPosition = nextTrickPosition;
        if (nextTrickPosition == 0) {
            setRendererEnabled(1, true);
        }
        int i2 = this.nextTrickPosition;
        if (i2 == Integer.MIN_VALUE) {
            for (Listener listener2 : this.listeners) {
                this.trickInSeekToBegin = true;
                listener2.onTrickEnded(i, 0);
            }
            return;
        }
        if (i2 >= 0 && i2 <= duration) {
            if (i >= 19 && i <= 23 && i2 < this.exoPlayer.getCurrentPosition()) {
                try {
                    Thread.sleep((long) ((this.exoPlayer.getCurrentPosition() - ((long) this.nextTrickPosition)) / getRealSpeed(i)));
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
            if (this.speed != 3) {
                seekTo(this.nextTrickPosition);
            }
            if (this.nextTrickPosition == duration) {
                this.handler.removeMessages(1);
                for (Listener listener3 : this.listeners) {
                    Log.i("MtkPlayerControl", "seek for trick, notify TRICK_TO_END!");
                    listener3.onTrickEnded(i, 1);
                }
                return;
            }
            return;
        }
        Log.e("MtkPlayerControl", "unexpected next trick position, " + this.nextTrickPosition + "ms");
    }

    public void release() {
        this.handler.removeCallbacksAndMessages(null);
        this.seekingControlThread.quit();
        this.listeners.clear();
        this.captionListener = null;
    }

    @Override // com.google.android.exoplayer.MediaCodecVideoTrackRenderer.EventListener
    public void onRenderFirstFrame() {
        this.renderedFirstFrame = true;
        MtkDebugLogUtil.log("MtkPlayerControl", "onRenderFirstFrame trickMode is " + this.trickMode + "State is " + this.exoPlayer.getPlaybackState(), 1);
        if (this.speed == 1 || this.trickMode != 0) {
            return;
        }
        this.handler.sendEmptyMessage(1);
    }
}
