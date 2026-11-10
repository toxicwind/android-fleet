package com.google.android.exoplayer;

import android.annotation.TargetApi;
import android.media.AudioManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer.drm.DrmSessionManager;
import com.google.android.exoplayer.text.TextTrackRenderer;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.PriorityHandlerThread;
import com.google.android.exoplayer.util.TraceUtil;
import com.google.android.exoplayer.util.Util;
import com.mediatek.twoworlds.tv.MtkTvScanCeBase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
final class ExoPlayerImplInternal implements Handler.Callback {
    private AudioManager audioManager;
    private TrackRenderer audioRenderer;
    private long elapsedRealtimeUs;
    private final List<TrackRenderer> enabledRenderers;
    private final Handler eventHandler;
    private final Handler handler;
    private final HandlerThread internalPlaybackThread;
    private long lastSeekPositionMs;
    private int lastTrickMode;
    private final long minBufferUs;
    private final long minRebufferUs;
    private boolean playWhenReady;
    private volatile long positionUs;
    private boolean rebuffering;
    private boolean released;
    private MediaClock rendererMediaClock;
    private TrackRenderer rendererMediaClockSource;
    private TrackRenderer[] renderers;
    private final int[] selectedTrackIndices;
    private final MediaFormat[][] trackFormats;
    private Map<String, Object> trickMap;
    private int trickSpeed;
    private int customMessagesSent = 0;
    private int customMessagesProcessed = 0;
    private int seekMode = 1;
    private boolean isSeeking = false;
    private boolean isVideoOnly = false;
    private boolean isAudioOnly = false;
    private boolean audioEnd = false;
    private int mediaTunneling = 0;
    private int state = 1;
    private volatile long durationUs = -1;
    private volatile long bufferedPositionUs = -1;
    private final StandaloneMediaClock standaloneMediaClock = new StandaloneMediaClock();
    private final AtomicInteger pendingSeekCount = new AtomicInteger();

    static double getRealSpeed(int i) {
        switch (i) {
            case 4:
                return 2.0d;
            case 5:
                return 4.0d;
            case 6:
                return 8.0d;
            case 7:
                return 16.0d;
            case 8:
                return 32.0d;
            case 9:
                return 2.0d;
            case 10:
                return 4.0d;
            case 11:
                return 8.0d;
            case 12:
                return 16.0d;
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

    public ExoPlayerImplInternal(Handler handler, boolean z, int[] iArr, int i, int i2) {
        this.eventHandler = handler;
        this.playWhenReady = z;
        this.minBufferUs = ((long) i) * 1000;
        this.minRebufferUs = ((long) i2) * 1000;
        this.selectedTrackIndices = Arrays.copyOf(iArr, iArr.length);
        this.enabledRenderers = new ArrayList(iArr.length);
        this.trackFormats = new MediaFormat[iArr.length][];
        PriorityHandlerThread priorityHandlerThread = new PriorityHandlerThread("ExoPlayerImplInternal:Handler", -16);
        this.internalPlaybackThread = priorityHandlerThread;
        priorityHandlerThread.start();
        this.handler = new Handler(this.internalPlaybackThread.getLooper(), this);
        this.lastTrickMode = -1;
        this.trickMap = null;
        this.trickSpeed = 3;
    }

    public Looper getPlaybackLooper() {
        return this.internalPlaybackThread.getLooper();
    }

    public long getCurrentPosition() {
        return this.pendingSeekCount.get() > 0 ? this.lastSeekPositionMs : this.positionUs / 1000;
    }

    public long getBufferedPosition() {
        if (this.bufferedPositionUs == -1) {
            return -1L;
        }
        return this.bufferedPositionUs / 1000;
    }

    public long getDuration() {
        if (this.durationUs == -1) {
            return -1L;
        }
        return this.durationUs / 1000;
    }

    public void prepare(TrackRenderer... trackRendererArr) {
        this.handler.obtainMessage(1, trackRendererArr).sendToTarget();
    }

    public void setPlayWhenReady(boolean z) {
        this.handler.obtainMessage(3, z ? 1 : 0, 0).sendToTarget();
    }

    public void seekTo(long j) {
        this.lastSeekPositionMs = j;
        this.pendingSeekCount.incrementAndGet();
        this.handler.obtainMessage(6, Util.getTopInt(j), Util.getBottomInt(j)).sendToTarget();
    }

    public void stop() {
        this.handler.sendEmptyMessage(4);
    }

    public void setRendererSelectedTrack(int i, int i2) {
        this.handler.obtainMessage(8, i, i2).sendToTarget();
    }

    public void sendMessage(ExoPlayer.ExoPlayerComponent exoPlayerComponent, int i, Object obj) {
        this.customMessagesSent++;
        this.handler.obtainMessage(9, i, 0, Pair.create(exoPlayerComponent, obj)).sendToTarget();
    }

    public synchronized void blockingSendMessage(ExoPlayer.ExoPlayerComponent exoPlayerComponent, int i, Object obj) {
        try {
            if (this.released) {
                Log.w("ExoPlayerImplInternal", "Sent message(" + i + ") after release. Message ignored.");
                return;
            }
            int i2 = this.customMessagesSent;
            this.customMessagesSent = i2 + 1;
            this.handler.obtainMessage(9, i, 0, Pair.create(exoPlayerComponent, obj)).sendToTarget();
            while (this.customMessagesProcessed <= i2) {
                if (this.released) {
                    Log.d("ExoPlayerImplInternal", "blockingSendMessage released true, break while");
                    break;
                } else {
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void release() {
        if (this.released) {
            return;
        }
        this.handler.removeMessages(7);
        this.handler.sendEmptyMessage(5);
        while (!this.released) {
            try {
                wait();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
        this.internalPlaybackThread.quit();
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        try {
            int i = message.what;
            switch (i) {
                case 1:
                    prepareInternal((TrackRenderer[]) message.obj);
                    return true;
                case 2:
                    incrementalPrepareInternal();
                    return true;
                case 3:
                    setPlayWhenReadyInternal(message.arg1 != 0);
                    return true;
                case 4:
                    stopInternal();
                    return true;
                case 5:
                    releaseInternal();
                    return true;
                case 6:
                    seekToInternal(Util.getLong(message.arg1, message.arg2));
                    return true;
                case 7:
                    doSomeWork();
                    return true;
                case 8:
                    setRendererSelectedTrackInternal(message.arg1, message.arg2);
                    return true;
                case 9:
                    sendMessageInternal(message.arg1, message.obj);
                    return true;
                default:
                    switch (i) {
                        case MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHENGWANG /* 1001 */:
                            setTrickModeInternal(message.arg1, message.arg2, ((Integer) message.obj).intValue());
                            return true;
                        case MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHIWANG /* 1002 */:
                            setDrmSessionManagerInternal((DrmSessionManager) message.obj);
                            return true;
                        case MtkTvScanCeBase.OPERATOR_HUNAN_GUANGDIAN /* 1003 */:
                            incrementalPrepareExternalText();
                            return true;
                        case MtkTvScanCeBase.OPERATOR_CHANGSHA_GUOAN /* 1004 */:
                            if (this.state != 1) {
                                throw ((ExoPlaybackException) message.obj);
                            }
                            return false;
                        default:
                            return false;
                    }
            }
        } catch (ExoPlaybackException e) {
            Log.e("ExoPlayerImplInternal", "Internal track renderer error.", e);
            this.eventHandler.obtainMessage(4, e).sendToTarget();
            stopInternal();
            return true;
        } catch (RuntimeException e2) {
            Log.e("ExoPlayerImplInternal", "Internal runtime error.", e2);
            this.eventHandler.obtainMessage(4, new ExoPlaybackException((Throwable) e2, true)).sendToTarget();
            stopInternal();
            return true;
        }
    }

    private void setState(int i) {
        if (this.state != i) {
            this.state = i;
            this.eventHandler.obtainMessage(2, i, 0).sendToTarget();
            for (int i2 = 0; i2 < this.enabledRenderers.size(); i2++) {
                sendMessage(this.enabledRenderers.get(i2), MtkTvScanCeBase.OPERATOR_XIAN_GUANGDIAN, Integer.valueOf(i));
            }
        }
    }

    private void prepareInternal(TrackRenderer[] trackRendererArr) throws ExoPlaybackException {
        resetInternal();
        this.renderers = trackRendererArr;
        Arrays.fill(this.trackFormats, (Object) null);
        setState(2);
        incrementalPrepareInternal();
    }

    /* JADX WARN: Code duplicated, block: B:75:0x011f  */
    private void incrementalPrepareInternal() throws ExoPlaybackException {
        boolean z;
        int i;
        MediaCodecVideoTrackRenderer mediaCodecVideoTrackRenderer;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        int i2 = 0;
        boolean z2 = true;
        while (true) {
            TrackRenderer[] trackRendererArr = this.renderers;
            if (i2 >= trackRendererArr.length) {
                break;
            }
            TrackRenderer trackRenderer = trackRendererArr[i2];
            if (trackRenderer.getState() == 0 && trackRenderer.prepare(this.positionUs) == 0) {
                trackRenderer.maybeThrowError();
                z2 = false;
            }
            i2++;
        }
        if (!z2) {
            scheduleNextOperation(2, jElapsedRealtime, 10L);
            return;
        }
        setTrackRenderSeekMode();
        long jMax = 0;
        int i3 = 0;
        int i4 = -1;
        boolean z3 = true;
        boolean z4 = true;
        while (true) {
            TrackRenderer[] trackRendererArr2 = this.renderers;
            if (i3 >= trackRendererArr2.length) {
                break;
            }
            TrackRenderer trackRenderer2 = trackRendererArr2[i3];
            int trackCount = trackRenderer2.getTrackCount();
            MediaFormat[] mediaFormatArr = new MediaFormat[trackCount];
            for (int i5 = 0; i5 < trackCount; i5++) {
                mediaFormatArr[i5] = trackRenderer2.getFormat(i5);
                if ((trackRenderer2 instanceof TextTrackRenderer) && "text/ext/sub".equals(mediaFormatArr[i5].mimeType)) {
                    mediaFormatArr[i5].frameRate = i4;
                    Log.d("ExoPlayerImplInternal", "set frame rate " + i4 + " to text track " + i5);
                }
            }
            this.trackFormats[i3] = mediaFormatArr;
            if (trackCount > 0) {
                if (jMax != -1 && !(trackRenderer2 instanceof TextTrackRenderer)) {
                    long durationUs = trackRenderer2.getDurationUs();
                    if (durationUs == -1) {
                        jMax = -1;
                    } else if (durationUs != -2) {
                        jMax = Math.max(jMax, durationUs);
                    }
                }
                int i6 = this.selectedTrackIndices[i3];
                if (i6 >= 0 && i6 < trackCount) {
                    enableRenderer(trackRenderer2, i6, false);
                    z3 = z3 && trackRenderer2.isEnded();
                    z4 = z4 && rendererReadyOrEnded(trackRenderer2);
                    if (trackRenderer2 instanceof MediaCodecTrackRenderer) {
                        ((MediaCodecTrackRenderer) trackRenderer2).setInternalPlayer(this);
                        if (trackRenderer2 instanceof MediaCodecVideoTrackRenderer) {
                            i4 = mediaFormatArr[i6].frameRate;
                        }
                    }
                }
            }
            i3++;
        }
        this.durationUs = jMax;
        MediaCodecVideoTrackRenderer mediaCodecVideoTrackRenderer2 = null;
        boolean z5 = false;
        for (int i7 = 0; i7 < this.enabledRenderers.size(); i7++) {
            if (this.enabledRenderers.get(i7) instanceof MediaCodecAudioTrackRenderer) {
                z5 = true;
            } else if (this.enabledRenderers.get(i7) instanceof MediaCodecVideoTrackRenderer) {
                mediaCodecVideoTrackRenderer2 = (MediaCodecVideoTrackRenderer) this.enabledRenderers.get(i7);
            }
        }
        if (z5) {
            z = false;
        } else {
            MediaFormat[][] mediaFormatArr2 = this.trackFormats;
            if (mediaFormatArr2[1] == null || mediaFormatArr2[1].length == 0) {
                z = true;
            } else {
                z = false;
            }
        }
        this.isVideoOnly = z;
        this.isAudioOnly = mediaCodecVideoTrackRenderer2 == null;
        Log.d("ExoPlayerImplInternal", "isVideoOnly:" + this.isVideoOnly + " isAudioOnly:" + this.isAudioOnly);
        boolean z6 = this.isVideoOnly;
        if (z6 && mediaCodecVideoTrackRenderer2 != null) {
            this.handler.obtainMessage(9, MtkTvScanCeBase.OPERATOR_WUHAN_SHIWANG, 0, Pair.create(mediaCodecVideoTrackRenderer2, Boolean.valueOf(z6))).sendToTarget();
        }
        if (z3 && (jMax == -1 || jMax <= this.positionUs)) {
            this.state = 5;
        } else {
            this.state = z4 ? 4 : 3;
        }
        configureTunnelingAudioSessionId();
        if (this.isVideoOnly) {
            TrackRenderer[] trackRendererArr3 = this.renderers;
            i = 0;
            if ((trackRendererArr3[0] instanceof MediaCodecVideoTrackRenderer) && (mediaCodecVideoTrackRenderer = (MediaCodecVideoTrackRenderer) trackRendererArr3[0]) != null && mediaCodecVideoTrackRenderer.getTunnelingAudioSessionId() != -1) {
                mediaCodecVideoTrackRenderer.setTunnelingAudioSessionId(0);
            }
        } else {
            i = 0;
        }
        this.eventHandler.obtainMessage(1, this.state, i, this.trackFormats).sendToTarget();
        if (this.playWhenReady && this.state == 4) {
            startRenderers();
        }
        this.handler.sendEmptyMessage(7);
    }

    private void enableRenderer(TrackRenderer trackRenderer, int i, boolean z) throws ExoPlaybackException {
        trackRenderer.enable(i, this.positionUs, z);
        this.enabledRenderers.add(trackRenderer);
        MediaClock mediaClock = trackRenderer.getMediaClock();
        if (mediaClock != null) {
            Assertions.checkState(this.rendererMediaClock == null);
            this.rendererMediaClock = mediaClock;
            this.rendererMediaClockSource = trackRenderer;
        }
    }

    private boolean rendererReadyOrEnded(TrackRenderer trackRenderer) {
        if (trackRenderer.isEnded()) {
            return true;
        }
        if (!trackRenderer.isReady()) {
            return false;
        }
        if (this.state == 4) {
            return true;
        }
        long durationUs = trackRenderer.getDurationUs();
        long bufferedPositionUs = trackRenderer.getBufferedPositionUs();
        long j = this.rebuffering ? this.minRebufferUs : this.minBufferUs;
        if (j < 0) {
            return true;
        }
        if ((this.lastTrickMode == -1 || j > 0) && bufferedPositionUs != -1 && bufferedPositionUs != -3 && bufferedPositionUs < this.positionUs + j) {
            return (durationUs == -1 || durationUs == -2 || bufferedPositionUs < durationUs) ? false : true;
        }
        return true;
    }

    private void setPlayWhenReadyInternal(boolean z) throws ExoPlaybackException {
        try {
            this.rebuffering = false;
            this.playWhenReady = z;
            if (!z) {
                stopRenderers();
                updatePositionUs();
            } else if (this.state == 4) {
                startRenderers();
                this.handler.sendEmptyMessage(7);
            } else if (this.state == 3) {
                this.handler.sendEmptyMessage(7);
            }
        } finally {
            this.eventHandler.obtainMessage(3).sendToTarget();
        }
    }

    private void startRenderers() throws ExoPlaybackException {
        this.rebuffering = false;
        this.standaloneMediaClock.start();
        for (int i = 0; i < this.enabledRenderers.size(); i++) {
            if (this.enabledRenderers.get(i).getState() != 3) {
                this.enabledRenderers.get(i).start();
            }
        }
    }

    private void stopRenderers() throws ExoPlaybackException {
        this.standaloneMediaClock.stop();
        for (int i = 0; i < this.enabledRenderers.size(); i++) {
            ensureStopped(this.enabledRenderers.get(i));
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0068  */
    /* JADX WARN: Code duplicated, block: B:36:0x008d  */
    private void updatePositionUs() {
        long positionUs;
        if (this.rendererMediaClock != null && this.enabledRenderers.contains(this.rendererMediaClockSource) && !this.rendererMediaClockSource.isEnded()) {
            if (this.isAudioOnly || this.playWhenReady) {
                this.positionUs = this.rendererMediaClock.getPositionUs();
                this.standaloneMediaClock.setPositionUs(this.positionUs);
            } else {
                this.positionUs = this.standaloneMediaClock.getPositionUs();
            }
        } else {
            int i = this.lastTrickMode;
            if (i == 2 || i == 1 || i == 4 || (i == 3 && isTunnelingPlayback())) {
                Object[] objArr = this.renderers;
                if (objArr[0] != null) {
                    this.positionUs = ((MediaClock) objArr[0]).getPositionUs();
                    this.standaloneMediaClock.setPositionUs(this.positionUs);
                } else {
                    if (this.isVideoOnly && this.lastTrickMode == -1 && this.renderers[0].isReady()) {
                        positionUs = ((MediaClock) this.renderers[0]).getPositionUs();
                        if (this.lastSeekPositionMs > positionUs / 1000) {
                            this.standaloneMediaClock.setPositionUs(positionUs);
                        }
                    }
                    this.positionUs = this.standaloneMediaClock.getPositionUs();
                }
            } else {
                if (this.isVideoOnly) {
                    positionUs = ((MediaClock) this.renderers[0]).getPositionUs();
                    if (this.lastSeekPositionMs > positionUs / 1000) {
                        this.standaloneMediaClock.setPositionUs(positionUs);
                    }
                }
                this.positionUs = this.standaloneMediaClock.getPositionUs();
            }
        }
        this.elapsedRealtimeUs = SystemClock.elapsedRealtime() * 1000;
    }

    /* JADX WARN: Code duplicated, block: B:47:0x00ab  */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4, types: [boolean] */
    private void doSomeWork() throws ExoPlaybackException {
        ?? r3;
        long jMin;
        TraceUtil.beginSection("doSomeWork");
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.durationUs != -1 ? this.durationUs : LongCompanionObject.MAX_VALUE;
        updatePositionUs();
        boolean z = true;
        boolean z2 = true;
        int i = 0;
        MediaCodecVideoTrackRenderer mediaCodecVideoTrackRenderer = null;
        boolean z3 = false;
        while (i < this.enabledRenderers.size()) {
            TrackRenderer trackRenderer = this.enabledRenderers.get(i);
            long j2 = jElapsedRealtime;
            long j3 = j;
            trackRenderer.doSomeWork(this.positionUs, this.elapsedRealtimeUs);
            if (!(trackRenderer instanceof TextTrackRenderer)) {
                z = z && trackRenderer.isEnded();
            }
            boolean z4 = trackRenderer instanceof MediaCodecAudioTrackRenderer;
            if (z4 && this.playWhenReady && ((MediaCodecTrackRenderer) trackRenderer).getTunnelingAudioSessionId() != -1 && trackRenderer.isEnded() && !this.audioEnd) {
                if (mediaCodecVideoTrackRenderer != null) {
                    sendMessage(mediaCodecVideoTrackRenderer, MtkTvScanCeBase.OPERATOR_SHAOXING_GUANGDIAN, Long.valueOf(this.positionUs));
                }
                this.audioEnd = true;
                MtkDebugLogUtil.log("ExoPlayerImplInternal", "Audio ends, notify video renderer.", 1);
            } else if (trackRenderer instanceof MediaCodecVideoTrackRenderer) {
                mediaCodecVideoTrackRenderer = (MediaCodecVideoTrackRenderer) trackRenderer;
            }
            boolean zRendererReadyOrEnded = rendererReadyOrEnded(trackRenderer);
            if (z4) {
                this.audioRenderer = trackRenderer;
                z3 = zRendererReadyOrEnded;
            }
            if (!zRendererReadyOrEnded || ((trackRenderer instanceof MediaCodecVideoTrackRenderer) && trackRenderer.isEnded())) {
                trackRenderer.maybeThrowError();
            }
            z2 = z2 && zRendererReadyOrEnded;
            if (j3 == -1) {
                jMin = j3;
            } else {
                long durationUs = trackRenderer.getDurationUs();
                long bufferedPositionUs = trackRenderer.getBufferedPositionUs();
                if (bufferedPositionUs == -1) {
                    jMin = -1;
                } else if (bufferedPositionUs == -3 || !(durationUs == -1 || durationUs == -2 || bufferedPositionUs < durationUs)) {
                    jMin = j3;
                } else {
                    jMin = Math.min(j3, bufferedPositionUs);
                }
            }
            i++;
            j = jMin;
            jElapsedRealtime = j2;
        }
        long j4 = jElapsedRealtime;
        this.bufferedPositionUs = j;
        if (z) {
            setState(5);
            stopRenderers();
        } else {
            int i2 = this.state;
            if (i2 == 3) {
                if (this.playWhenReady || !isTunnelingPlayback() || this.lastTrickMode != -1 || mediaCodecVideoTrackRenderer == null) {
                    r3 = 0;
                } else {
                    if (z2) {
                        if (this.isVideoOnly && !this.isSeeking && !this.handler.hasMessages(3)) {
                            sendMessage(mediaCodecVideoTrackRenderer, MtkTvScanCeBase.OPERATOR_BEIJING_GEHUAYOUXIAN, 1);
                        }
                    } else if (this.isSeeking) {
                        sendMessage(mediaCodecVideoTrackRenderer, MtkTvScanCeBase.OPERATOR_HUBEI_HUANGSHI, null);
                        r3 = 0;
                        this.isSeeking = false;
                    }
                    r3 = 0;
                }
                if (z2) {
                    if (this.isSeeking) {
                        this.isSeeking = r3;
                        if (this.lastTrickMode == -1) {
                            Object[] objArr = this.renderers;
                            if (objArr[r3] != null && !(objArr[r3] instanceof DummyTrackRenderer)) {
                                this.standaloneMediaClock.setPositionUs(((MediaClock) objArr[r3]).getPositionUs());
                            }
                        }
                    }
                    setState(4);
                    if (this.playWhenReady) {
                        startRenderers();
                    }
                } else if (z3 && isTunnelingPlayback() && this.playWhenReady && this.audioRenderer.getState() != 3) {
                    this.audioRenderer.start();
                }
            } else if (i2 == 4 && !z2) {
                this.rebuffering = this.playWhenReady;
                setState(3);
                stopRenderers();
            }
        }
        this.handler.removeMessages(7);
        if ((this.playWhenReady && this.state == 4) || this.state == 3) {
            scheduleNextOperation(7, j4, 10L);
        } else if (!this.enabledRenderers.isEmpty()) {
            scheduleNextOperation(7, j4, 1000L);
        }
        TraceUtil.endSection();
    }

    private void scheduleNextOperation(int i, long j, long j2) {
        long jElapsedRealtime = (j + j2) - SystemClock.elapsedRealtime();
        if (jElapsedRealtime <= 0) {
            this.handler.sendEmptyMessage(i);
        } else {
            this.handler.sendEmptyMessageDelayed(i, jElapsedRealtime);
        }
    }

    private void seekToInternal(long j) throws ExoPlaybackException {
        try {
            if (j == this.positionUs / 1000) {
                setState(3);
                setState(4);
            } else {
                this.rebuffering = false;
                this.positionUs = j * 1000;
                this.standaloneMediaClock.stop();
                this.standaloneMediaClock.setPositionUs(this.positionUs);
                if (this.state != 1 && this.state != 2) {
                    for (int i = 0; i < this.enabledRenderers.size(); i++) {
                        TrackRenderer trackRenderer = this.enabledRenderers.get(i);
                        ensureStopped(trackRenderer);
                        trackRenderer.seekTo(this.positionUs);
                    }
                    this.isSeeking = true;
                    this.audioEnd = false;
                    for (int i2 = 0; i2 < this.enabledRenderers.size(); i2++) {
                        TrackRenderer trackRenderer2 = this.enabledRenderers.get(i2);
                        if (trackRenderer2 instanceof MediaCodecVideoTrackRenderer) {
                            ((MediaCodecVideoTrackRenderer) trackRenderer2).setInSeek(this.trickSpeed);
                            break;
                        }
                    }
                    setState(3);
                    this.handler.sendEmptyMessage(7);
                }
            }
        } finally {
            this.pendingSeekCount.decrementAndGet();
        }
    }

    private void stopInternal() {
        resetInternal();
        setState(1);
    }

    private void releaseInternal() {
        resetInternal();
        setState(1);
        synchronized (this) {
            this.released = true;
            notifyAll();
        }
    }

    private void resetInternal() {
        this.handler.removeMessages(7);
        this.handler.removeMessages(2);
        int i = 0;
        this.rebuffering = false;
        this.standaloneMediaClock.stop();
        if (this.renderers == null) {
            return;
        }
        while (true) {
            TrackRenderer[] trackRendererArr = this.renderers;
            if (i < trackRendererArr.length) {
                TrackRenderer trackRenderer = trackRendererArr[i];
                stopAndDisable(trackRenderer);
                release(trackRenderer);
                i++;
            } else {
                this.renderers = null;
                this.rendererMediaClock = null;
                this.rendererMediaClockSource = null;
                this.enabledRenderers.clear();
                return;
            }
        }
    }

    private void stopAndDisable(TrackRenderer trackRenderer) {
        try {
            ensureDisabled(trackRenderer);
        } catch (ExoPlaybackException e) {
            Log.e("ExoPlayerImplInternal", "Stop failed.", e);
        } catch (RuntimeException e2) {
            Log.e("ExoPlayerImplInternal", "Stop failed.", e2);
        }
    }

    private void release(TrackRenderer trackRenderer) {
        try {
            trackRenderer.release();
        } catch (ExoPlaybackException e) {
            Log.e("ExoPlayerImplInternal", "Release failed.", e);
        } catch (RuntimeException e2) {
            Log.e("ExoPlayerImplInternal", "Release failed.", e2);
        }
    }

    private <T> void sendMessageInternal(int i, Object obj) throws ExoPlaybackException {
        try {
            Pair pair = (Pair) obj;
            ((ExoPlayer.ExoPlayerComponent) pair.first).handleMessage(i, pair.second);
            if (this.state != 1 && this.state != 2) {
                this.handler.sendEmptyMessage(7);
            }
            synchronized (this) {
                this.customMessagesProcessed++;
                notifyAll();
            }
        } catch (Throwable th) {
            synchronized (this) {
                this.customMessagesProcessed++;
                notifyAll();
                throw th;
            }
        }
    }

    private void setRendererSelectedTrackInternal(int i, int i2) throws ExoPlaybackException {
        TrackRenderer trackRenderer;
        int state;
        int[] iArr = this.selectedTrackIndices;
        if (iArr[i] == i2) {
            return;
        }
        iArr[i] = i2;
        int i3 = this.state;
        if (i3 == 1 || i3 == 2 || (state = (trackRenderer = this.renderers[i]).getState()) == 0 || state == -1 || trackRenderer.getTrackCount() == 0) {
            return;
        }
        boolean z = state == 2 || state == 3;
        boolean z2 = i2 >= 0 && i2 < this.trackFormats[i].length;
        if (z) {
            if (!z2 && trackRenderer == this.rendererMediaClockSource) {
                this.standaloneMediaClock.setPositionUs(this.rendererMediaClock.getPositionUs());
            }
            ensureDisabled(trackRenderer);
            this.enabledRenderers.remove(trackRenderer);
        }
        if (z2) {
            boolean z3 = this.playWhenReady && this.state == 4;
            enableRenderer(trackRenderer, i2, !z && z3);
            if (z3) {
                trackRenderer.start();
            }
            if ((trackRenderer instanceof MediaCodecAudioTrackRenderer) && z) {
                TrackRenderer[] trackRendererArr = this.renderers;
                if (trackRendererArr[0] != null && this.enabledRenderers.contains(trackRendererArr[0])) {
                    MediaCodecVideoTrackRenderer mediaCodecVideoTrackRenderer = (MediaCodecVideoTrackRenderer) this.renderers[0];
                    mediaCodecVideoTrackRenderer.setLastSeekPositionUs(mediaCodecVideoTrackRenderer.getPositionUs());
                }
            }
            this.handler.sendEmptyMessage(7);
        }
    }

    private void ensureStopped(TrackRenderer trackRenderer) throws ExoPlaybackException {
        if (trackRenderer.getState() == 3) {
            trackRenderer.stop();
        }
    }

    private void ensureDisabled(TrackRenderer trackRenderer) throws ExoPlaybackException {
        ensureStopped(trackRenderer);
        if (trackRenderer.getState() == 2) {
            trackRenderer.disable(this.positionUs);
            if (trackRenderer == this.rendererMediaClockSource) {
                this.rendererMediaClock = null;
                this.rendererMediaClockSource = null;
            }
        }
    }

    public void setTrickMode(int i, int i2, int i3) {
        this.handler.obtainMessage(MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHENGWANG, i, i2, Integer.valueOf(i3)).sendToTarget();
    }

    private void setTrickModeInternal(int i, int i2, int i3) {
        Boolean bool = Boolean.TRUE;
        Boolean bool2 = Boolean.FALSE;
        Log.i("ExoPlayerImplInternal", "setTrickModeInternal speed:" + i + ", trickMode:" + i3);
        this.trickSpeed = i;
        if ((this.lastTrickMode != i3 || i3 == 1 || i3 == 4 || i3 == -1) && this.state != 1) {
            this.handler.removeMessages(7);
            HashMap map = new HashMap();
            this.trickMap = map;
            this.audioEnd = false;
            switch (i) {
                case 1:
                    if (this.lastTrickMode == 3) {
                        this.standaloneMediaClock.stopSpeeding();
                    }
                    break;
                case 2:
                    int i4 = this.lastTrickMode;
                    if (i4 == 3) {
                        this.standaloneMediaClock.stopSpeeding();
                    } else if (i4 == 2) {
                        map.put("enableIFrameMode", bool2);
                    }
                    break;
                case 3:
                    int i5 = this.lastTrickMode;
                    if (i5 == 3) {
                        this.standaloneMediaClock.stopSpeeding();
                    } else if (i5 == 2) {
                        map.put("enableIFrameMode", bool2);
                    }
                    TrackRenderer[] trackRendererArr = this.renderers;
                    if (trackRendererArr[0] != null && ((trackRendererArr[0].getState() == 2 || this.renderers[0].getState() == 3) && (this.renderers[0] instanceof MediaClock) && !isTunnelingPlayback())) {
                        this.positionUs = ((MediaClock) this.renderers[0]).getPositionUs();
                    }
                    break;
                case 4:
                    Object[] objArr = this.renderers;
                    if (objArr[0] != null) {
                        this.standaloneMediaClock.setPositionUs(((MediaClock) objArr[0]).getPositionUs());
                    }
                    if (!isTunnelingPlayback()) {
                        this.standaloneMediaClock.setSpeed(getRealSpeed(i));
                        this.standaloneMediaClock.startSpeeding();
                    } else {
                        this.standaloneMediaClock.setSpeed(getRealSpeed(3));
                        this.standaloneMediaClock.startSpeeding();
                    }
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                    int i6 = this.lastTrickMode;
                    if (i6 == 3) {
                        this.standaloneMediaClock.stopSpeeding();
                        if (i3 == 2) {
                            this.trickMap.put("enableIFrameMode", bool);
                        }
                    } else if (i3 == 2 && i6 == 0) {
                        map.put("enableIFrameMode", bool);
                    } else if (this.lastTrickMode == 2 && i3 == 0) {
                        this.trickMap.put("enableIFrameMode", bool2);
                    }
                    break;
                case 9:
                    int i7 = this.lastTrickMode;
                    if (i7 == 3) {
                        this.standaloneMediaClock.stopSpeeding();
                    } else if (i7 == 2) {
                        map.put("enableIFrameMode", bool2);
                    }
                    break;
                case 10:
                case 11:
                case 12:
                case 13:
                default:
                    Log.i("ExoPlayerImplInternal", "speed is not handled in here !");
                    break;
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                    int i8 = this.lastTrickMode;
                    if (i8 == 2) {
                        map.put("enableIFrameMode", bool2);
                    } else if (i8 == 3) {
                        this.standaloneMediaClock.stopSpeeding();
                    }
                    break;
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                    int i9 = this.lastTrickMode;
                    if (i9 == 3) {
                        this.standaloneMediaClock.stopSpeeding();
                    } else if (i9 == 2) {
                        map.put("enableIFrameMode", bool2);
                    }
                    break;
            }
            this.trickMap.put("rendererSpeed", Integer.valueOf(i));
            sendMessage(this.renderers[i2], MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHIWANG, this.trickMap);
            this.lastTrickMode = i3;
            scheduleNextOperation(7, SystemClock.elapsedRealtime(), 100L);
        }
    }

    public void setDrmSessionManager(DrmSessionManager drmSessionManager) {
        this.handler.obtainMessage(MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHIWANG, drmSessionManager).sendToTarget();
    }

    private void setDrmSessionManagerInternal(DrmSessionManager drmSessionManager) {
        for (int i = 0; i < this.enabledRenderers.size(); i++) {
            TrackRenderer trackRenderer = this.enabledRenderers.get(i);
            if (trackRenderer instanceof MediaCodecTrackRenderer) {
                ((MediaCodecTrackRenderer) trackRenderer).setDrmSessionManager(drmSessionManager);
            }
        }
    }

    private void incrementalPrepareExternalText() throws ExoPlaybackException {
        boolean z;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (this.renderers[2].getState() == 0 && this.renderers[2].prepare(this.positionUs) == 0) {
            this.renderers[2].maybeThrowError();
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            scheduleNextOperation(2, jElapsedRealtime, 10L);
            return;
        }
        TrackRenderer trackRenderer = this.renderers[2];
        int trackCount = trackRenderer.getTrackCount();
        MediaFormat[] mediaFormatArr = new MediaFormat[trackCount];
        for (int i = 0; i < trackCount; i++) {
            mediaFormatArr[i] = trackRenderer.getFormat(i);
        }
        MediaFormat[][] mediaFormatArr2 = this.trackFormats;
        mediaFormatArr2[2] = mediaFormatArr;
        this.eventHandler.obtainMessage(1, this.state, 0, mediaFormatArr2).sendToTarget();
    }

    @TargetApi(21)
    public void setMediaTunneling(int i, AudioManager audioManager) {
        this.mediaTunneling = i;
        this.audioManager = audioManager;
        MtkDebugLogUtil.log("ExoPlayerImplInternal", "tunneling is " + i, 1);
    }

    private void configureTunnelingAudioSessionId() {
        if (this.mediaTunneling == 0 || this.audioManager == null) {
            return;
        }
        TrackRenderer[] trackRendererArr = this.renderers;
        int iGenerateAudioSessionId = 0;
        TrackRenderer trackRenderer = trackRendererArr[0];
        TrackRenderer trackRenderer2 = trackRendererArr[1];
        if (trackRenderer == null || (trackRenderer instanceof DummyTrackRenderer)) {
            return;
        }
        MediaFormat[][] mediaFormatArr = this.trackFormats;
        if (mediaFormatArr[0] == null || mediaFormatArr[0].length == 0) {
            return;
        }
        boolean zIsTunneledSupported = ((MediaCodecTrackRenderer) trackRenderer).isTunneledSupported(mediaFormatArr[0][0]);
        Log.i("ExoPlayerImplInternal", "video tunneled judgement: mimeType[" + this.trackFormats[0][0].mimeType + "] tunneled=" + zIsTunneledSupported);
        if (zIsTunneledSupported) {
            if (trackRenderer2 != null && !(trackRenderer2 instanceof DummyTrackRenderer)) {
                MediaFormat[][] mediaFormatArr2 = this.trackFormats;
                if (mediaFormatArr2[1] != null && mediaFormatArr2[1].length > 0) {
                    boolean zIsTunneledSupported2 = ((MediaCodecTrackRenderer) trackRenderer2).isTunneledSupported(mediaFormatArr2[1][0]);
                    Log.i("ExoPlayerImplInternal", "audio tunneled judgement: mimeType[" + this.trackFormats[1][0].mimeType + "] tunneled=" + zIsTunneledSupported2);
                    if (!zIsTunneledSupported2) {
                        return;
                    }
                }
            }
            MediaFormat[][] mediaFormatArr3 = this.trackFormats;
            if (mediaFormatArr3[1] != null && mediaFormatArr3[1].length != 0) {
                iGenerateAudioSessionId = this.audioManager.generateAudioSessionId();
            }
            try {
                if (this.mediaTunneling == 1) {
                    ((MediaCodecTrackRenderer) trackRenderer).setTunnelingAudioSessionId(iGenerateAudioSessionId);
                    if (trackRenderer2 == null || !(trackRenderer2 instanceof MediaCodecAudioTrackRenderer)) {
                        return;
                    }
                    ((MediaCodecTrackRenderer) trackRenderer2).setTunnelingAudioSessionId(iGenerateAudioSessionId);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    @TargetApi(21)
    public boolean isTunnelingPlayback() {
        int i = this.state;
        if (i == 1 || i == 2 || i == 5) {
            Log.w("ExoPlayerImplInternal", "Check tunnel state while player has not been prepared or has ended.");
        }
        TrackRenderer trackRenderer = this.renderers[0];
        if (trackRenderer != null && !(trackRenderer instanceof DummyTrackRenderer)) {
            MediaFormat[][] mediaFormatArr = this.trackFormats;
            if (mediaFormatArr[0] != null && mediaFormatArr[0].length != 0 && ((MediaCodecTrackRenderer) trackRenderer).getTunnelingAudioSessionId() != -1) {
                return true;
            }
        }
        return false;
    }

    public void throwMediaCodecTrackRendererException(ExoPlaybackException exoPlaybackException) {
        this.handler.obtainMessage(MtkTvScanCeBase.OPERATOR_CHANGSHA_GUOAN, exoPlaybackException).sendToTarget();
    }

    public void setSeekMode(int i) {
        this.seekMode = i;
    }

    private void setTrackRenderSeekMode() {
        TrackRenderer[] trackRendererArr = this.renderers;
        if (trackRendererArr == null) {
            return;
        }
        for (TrackRenderer trackRenderer : trackRendererArr) {
            if (trackRenderer != null) {
                trackRenderer.setSeekMode(this.seekMode);
                Log.d("ExoPlayerImplInternal", "setTrackRenderSeekMode() seekMode=" + this.seekMode + "-renderer=" + trackRenderer);
            }
        }
    }
}
