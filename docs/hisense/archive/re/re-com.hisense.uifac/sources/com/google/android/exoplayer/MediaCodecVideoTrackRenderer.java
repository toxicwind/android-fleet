package com.google.android.exoplayer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import com.google.android.exoplayer.drm.DrmSessionManager;
import com.google.android.exoplayer.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.PriorityHandlerThread;
import com.google.android.exoplayer.util.TraceUtil;
import com.google.android.exoplayer.util.Util;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(16)
public class MediaCodecVideoTrackRenderer extends MediaCodecTrackRenderer implements MediaClock, MediaCodec.OnFrameRenderedListener, Handler.Callback {
    private final long allowedJoiningTimeUs;
    private int consecutiveDroppedFrameCount;
    private int currentHeight;
    private float currentPixelWidthHeightRatio;
    private int currentUnappliedRotationDegrees;
    private int currentWidth;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrameCount;
    private final CopyOnWriteArraySet<EventListener> eventListeners;
    private final VideoFrameReleaseTimeHelper frameReleaseTimeHelper;
    private boolean hasRenderedFrame;
    private boolean isSetup;
    private long joiningDeadlineUs;
    private long lastElapsedRealtimeUs;
    private long lastPresentationTimeUs;
    private int lastReportedHeight;
    private float lastReportedPixelWidthHeightRatio;
    private int lastReportedUnappliedRotationDegrees;
    private int lastReportedWidth;
    private final int maxDroppedFrameCountToNotify;
    private boolean notifyTunnelRenderFirstFrame;
    private Bundle params;
    private float pendingPixelWidthHeightRatio;
    private int pendingRotationDegrees;
    private boolean receiveTunnelEos;
    private final ArrayBlockingQueue<Long> recentRenderedPresentTimestamps;
    private Handler renderFrameHandler;
    private HandlerThread renderFrameThread;
    private boolean renderedFirstFrame;
    private boolean renderedSecondFrame;
    private boolean reportedDrawnToSurface;
    private Surface surface;
    private final int videoScalingMode;

    public interface EventListener extends MediaCodecTrackRenderer.EventListener {
        void onDrawnToSurface(Surface surface);

        void onDroppedFrames(int i, long j);

        void onRenderFirstFrame();

        void onVideoSizeChanged(int i, int i2, int i3, float f);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        return false;
    }

    protected boolean shouldDropOutputBuffer(long j, long j2) {
        return j < -30000;
    }

    public MediaCodecVideoTrackRenderer(Context context, SampleSource sampleSource, MediaCodecSelector mediaCodecSelector, int i, long j, Handler handler, EventListener eventListener, int i2) {
        this(context, sampleSource, mediaCodecSelector, i, j, null, false, handler, eventListener, i2);
    }

    public MediaCodecVideoTrackRenderer(Context context, SampleSource sampleSource, MediaCodecSelector mediaCodecSelector, int i, long j, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z, Handler handler, EventListener eventListener, int i2) {
        super(sampleSource, mediaCodecSelector, drmSessionManager, z, handler, eventListener);
        this.receiveTunnelEos = false;
        this.renderedSecondFrame = false;
        this.notifyTunnelRenderFirstFrame = false;
        this.frameReleaseTimeHelper = new VideoFrameReleaseTimeHelper(context);
        this.videoScalingMode = i;
        this.allowedJoiningTimeUs = 1000 * j;
        CopyOnWriteArraySet<EventListener> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
        this.eventListeners = copyOnWriteArraySet;
        if (eventListener != null) {
            copyOnWriteArraySet.add(eventListener);
        }
        this.maxDroppedFrameCountToNotify = i2;
        this.joiningDeadlineUs = -1L;
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.pendingPixelWidthHeightRatio = -1.0f;
        this.lastReportedWidth = -1;
        this.lastReportedHeight = -1;
        this.lastReportedPixelWidthHeightRatio = -1.0f;
        this.lastPresentationTimeUs = -1L;
        this.lastElapsedRealtimeUs = -1L;
        this.recentRenderedPresentTimestamps = new ArrayBlockingQueue<>(3);
        this.receiveTunnelEos = false;
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected boolean handlesTrack(MediaCodecSelector mediaCodecSelector, MediaFormat mediaFormat) throws MediaCodecUtil.DecoderQueryException {
        String strFindDecoderForFormat;
        String str = mediaFormat.mimeType;
        if ("video/dolby-vision".equals(str)) {
            mediaFormat.getFrameworkMediaFormatV16();
            if (Util.SDK_INT < 24 || (strFindDecoderForFormat = MediaCodecUtil.findDecoderForFormat(mediaFormat.getFrameworkMediaFormatV16(), false)) == null) {
                return false;
            }
            str = str + "#" + strFindDecoderForFormat;
        }
        if (MimeTypes.isVideo(str)) {
            return "video/x-unknown".equals(str) || mediaCodecSelector.getDecoderInfo(str, false) != null;
        }
        return false;
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onEnabled(int i, long j, boolean z) throws ExoPlaybackException {
        if (this.renderFrameThread == null) {
            PriorityHandlerThread priorityHandlerThread = new PriorityHandlerThread(MediaCodecVideoTrackRenderer.class.getSimpleName() + ":Handler", -4);
            this.renderFrameThread = priorityHandlerThread;
            priorityHandlerThread.start();
            this.renderFrameHandler = new Handler(this.renderFrameThread.getLooper(), this);
            this.isSetup = false;
        }
        super.onEnabled(i, j, z);
        if (z && this.allowedJoiningTimeUs > 0) {
            this.joiningDeadlineUs = (SystemClock.elapsedRealtime() * 1000) + this.allowedJoiningTimeUs;
        }
        this.frameReleaseTimeHelper.enable();
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.SampleSourceTrackRenderer
    protected void onDiscontinuity(long j) throws ExoPlaybackException {
        super.onDiscontinuity(j);
        if (j >= 0) {
            this.renderedFirstFrame = false;
        }
        this.renderedFirstFrame = false;
        this.consecutiveDroppedFrameCount = 0;
        this.joiningDeadlineUs = -1L;
        this.renderedSecondFrame = false;
        this.needResubmitCsdData = !this.outputFormatChanged;
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected boolean isReady() {
        if (super.isReady() && (this.renderedFirstFrame || ((!codecInitialized() && !super.isVideoOnly()) || getSourceState() == 2))) {
            this.joiningDeadlineUs = -1L;
            return true;
        }
        if (this.joiningDeadlineUs == -1) {
            return false;
        }
        if (SystemClock.elapsedRealtime() * 1000 < this.joiningDeadlineUs) {
            return true;
        }
        this.joiningDeadlineUs = -1L;
        return false;
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onStarted() {
        super.onStarted();
        this.droppedFrameCount = 0;
        this.droppedFrameAccumulationStartTimeMs = SystemClock.elapsedRealtime();
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onStopped() {
        this.joiningDeadlineUs = -1L;
        maybeNotifyDroppedFrameCount();
        this.isSetup = false;
        super.onStopped();
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onDisabled(long j) throws ExoPlaybackException {
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.pendingPixelWidthHeightRatio = -1.0f;
        this.lastReportedWidth = -1;
        this.lastReportedHeight = -1;
        this.lastReportedPixelWidthHeightRatio = -1.0f;
        this.frameReleaseTimeHelper.disable();
        this.renderFrameThread.quit();
        this.renderFrameThread = null;
        this.isSetup = false;
        this.receiveTunnelEos = false;
        super.onDisabled(j);
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer, com.google.android.exoplayer.ExoPlayer.ExoPlayerComponent
    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i == 1) {
            setSurface((Surface) obj);
            return;
        }
        if (i == 1001) {
            this.params = (Bundle) obj;
            return;
        }
        if (i == 1002) {
            Map map = (Map) obj;
            if (map.get("rendererSpeed") != null) {
                int iIntValue = ((Integer) map.get("rendererSpeed")).intValue();
                if (iIntValue == 2) {
                    this.renderedFirstFrame = false;
                }
                setvendorparam(iIntValue, false);
            }
            super.handleMessage(i, obj);
            return;
        }
        if (i == 1008) {
            setvendorparam(((Integer) obj).intValue(), true);
        } else if (i == 1009) {
            setRotation(((Integer) obj).intValue());
        } else {
            super.handleMessage(i, obj);
        }
    }

    public void setInSeek(int i) {
        if (i == 1) {
            this.isSeeking = 1;
        } else if (i == 3) {
            this.isSeeking = 2;
        } else if (i > 4) {
            this.isSeeking = 3;
        } else {
            this.isSeeking = 0;
        }
        Log.d("MediaCodecVideoTrackRenderer", "setInSeek: current speed:" + i + ", isSeeking:" + this.isSeeking);
    }

    private void setSurface(Surface surface) throws ExoPlaybackException {
        if (this.surface == surface) {
            return;
        }
        this.surface = surface;
        this.reportedDrawnToSurface = false;
        int state = getState();
        if (state == 2 || state == 3) {
            releaseCodec();
            maybeInitCodec();
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected boolean shouldInitCodec() {
        Surface surface;
        return super.shouldInitCodec() && (surface = this.surface) != null && surface.isValid();
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected void configureCodec(MediaCodec mediaCodec, boolean z, android.media.MediaFormat mediaFormat, MediaCrypto mediaCrypto) {
        maybeSetMaxInputSize(mediaFormat, z);
        Bundle bundle = this.params;
        if (bundle != null && bundle.size() > 0) {
            mediaCodec.setParameters(this.params);
        }
        int tunnelingAudioSessionId = getTunnelingAudioSessionId();
        if (Util.SDK_INT >= 21 && tunnelingAudioSessionId != -1) {
            setIsTunnelingVideo(true);
            mediaFormat.setFeatureEnabled("tunneled-playback", true);
            if (tunnelingAudioSessionId != 0) {
                mediaFormat.setInteger("audio-session-id", tunnelingAudioSessionId);
            }
        }
        mediaFormat.setInteger("push-blank-buffers-on-shutdown", 1);
        if (this.format.timestampMode == 1) {
            mediaFormat.setString("vendor.vdecomx.feature", "usesortedtimestamp");
        }
        mediaCodec.configure(mediaFormat, this.surface, mediaCrypto, 0);
        mediaCodec.setOnFrameRenderedListener(this, this.renderFrameHandler);
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected void onInputFormatChanged(MediaFormatHolder mediaFormatHolder) throws ExoPlaybackException {
        super.onInputFormatChanged(mediaFormatHolder);
        float f = mediaFormatHolder.format.pixelWidthHeightRatio;
        if (f == -1.0f) {
            f = 1.0f;
        }
        this.pendingPixelWidthHeightRatio = f;
        int i = mediaFormatHolder.format.rotationDegrees;
        if (i == -1) {
            i = 0;
        }
        this.pendingRotationDegrees = i;
    }

    protected final boolean haveRenderedFirstFrame() {
        return this.renderedFirstFrame;
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected void onOutputFormatChanged(MediaCodec mediaCodec, android.media.MediaFormat mediaFormat) {
        int integer;
        int integer2;
        boolean z = mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top");
        if (z) {
            integer = (mediaFormat.getInteger("crop-right") - mediaFormat.getInteger("crop-left")) + 1;
        } else {
            integer = mediaFormat.getInteger("width");
        }
        this.currentWidth = integer;
        if (z) {
            integer2 = (mediaFormat.getInteger("crop-bottom") - mediaFormat.getInteger("crop-top")) + 1;
        } else {
            integer2 = mediaFormat.getInteger("height");
        }
        this.currentHeight = integer2;
        this.currentPixelWidthHeightRatio = this.pendingPixelWidthHeightRatio;
        if (Util.SDK_INT >= 21) {
            int i = this.pendingRotationDegrees;
            if (i == 90 || i == 270) {
                int i2 = this.currentWidth;
                this.currentWidth = this.currentHeight;
                this.currentHeight = i2;
                this.currentPixelWidthHeightRatio = 1.0f / this.currentPixelWidthHeightRatio;
                this.currentUnappliedRotationDegrees = this.pendingRotationDegrees;
            }
        } else {
            this.currentUnappliedRotationDegrees = this.pendingRotationDegrees;
        }
        mediaCodec.setVideoScalingMode(this.videoScalingMode);
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected boolean canReconfigureCodec(MediaCodec mediaCodec, boolean z, MediaFormat mediaFormat, MediaFormat mediaFormat2) {
        return mediaFormat2.mimeType.equals(mediaFormat.mimeType) && (z || (mediaFormat.width == mediaFormat2.width && mediaFormat.height == mediaFormat2.height));
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected boolean processOutputBuffer(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo, int i, boolean z) throws ExoPlaybackException {
        boolean z2;
        if (z && this.speed != 4) {
            skipOutputBuffer(mediaCodec, i);
            this.consecutiveDroppedFrameCount = 0;
            return true;
        }
        if (!this.renderedFirstFrame && isRecentRendered(bufferInfo.presentationTimeUs)) {
            skipOutputBuffer(mediaCodec, i);
            this.consecutiveDroppedFrameCount = 0;
            return true;
        }
        if (this.speed == 4) {
            this.lastSeekPositionUs = j;
        }
        if (!this.renderedFirstFrame) {
            if (Util.SDK_INT >= 21) {
                renderOutputBufferV21(mediaCodec, i, System.nanoTime());
            } else {
                renderOutputBuffer(mediaCodec, i);
            }
            if (this.speed == 2) {
                this.lastSeekPositionUs = j;
            }
            maybeNotifyRenderFirstFrame();
            long j3 = bufferInfo.presentationTimeUs;
            this.lastPresentationTimeUs = j3;
            this.consecutiveDroppedFrameCount = 0;
            addToRecentRendered(j3);
            return true;
        }
        int i2 = this.speed;
        if (i2 > 4 && i2 <= 8 && this.onlyPushIFrame) {
            if ((bufferInfo.presentationTimeUs - this.lastPresentationTimeUs) / ((long) ExoPlayerImplInternal.getRealSpeed(i2)) > j2 - this.lastElapsedRealtimeUs) {
                return false;
            }
            this.lastElapsedRealtimeUs = j2;
            this.lastPresentationTimeUs = bufferInfo.presentationTimeUs;
            if (Util.SDK_INT >= 21) {
                renderOutputBufferV21(mediaCodec, i, System.nanoTime());
            } else {
                renderOutputBuffer(mediaCodec, i);
            }
            addToRecentRendered(bufferInfo.presentationTimeUs);
            return true;
        }
        int i3 = this.speed;
        if (i3 >= 14 && i3 <= 18) {
            long realSpeed = (bufferInfo.presentationTimeUs - this.lastPresentationTimeUs) * ((long) (1.0d / ExoPlayerImplInternal.getRealSpeed(i3)));
            if (realSpeed < 0) {
                dropOutputBuffer(mediaCodec, i);
                return true;
            }
            if (realSpeed > j2 - this.lastElapsedRealtimeUs) {
                return false;
            }
            this.lastElapsedRealtimeUs = j2;
            this.lastPresentationTimeUs = bufferInfo.presentationTimeUs;
            renderOutputBuffer(mediaCodec, i);
            this.lastSeekPositionUs = j;
            addToRecentRendered(bufferInfo.presentationTimeUs);
            return true;
        }
        if (this.speed == 4 && this.consecutiveDroppedFrameCount >= 4) {
            this.lastElapsedRealtimeUs = j2;
            this.lastPresentationTimeUs = bufferInfo.presentationTimeUs;
            this.consecutiveDroppedFrameCount = 0;
            renderOutputBuffer(mediaCodec, i);
            addToRecentRendered(bufferInfo.presentationTimeUs);
            return true;
        }
        if (getState() != 3) {
            return false;
        }
        long jElapsedRealtime = (bufferInfo.presentationTimeUs - j) - ((SystemClock.elapsedRealtime() * 1000) - j2);
        long jNanoTime = System.nanoTime();
        long jAdjustReleaseTime = this.frameReleaseTimeHelper.adjustReleaseTime(bufferInfo.presentationTimeUs, (jElapsedRealtime * 1000) + jNanoTime);
        long j4 = (jAdjustReleaseTime - jNanoTime) / 1000;
        MtkDebugLogUtil.log("MediaCodecVideoTrackRenderer", "presentationTimeUs[" + bufferInfo.presentationTimeUs + "], bufferIndex[" + i + "], positionUs[" + j + "], earlyUs[" + j4 + "], adjustedReleaseTimeNs[" + jAdjustReleaseTime + "]", 1);
        if (shouldDropOutputBuffer(j4, j2)) {
            MtkDebugLogUtil.log("MediaCodecVideoTrackRenderer", "dropOutputBuffer, presentationTimeUs[" + bufferInfo.presentationTimeUs + "], bufferIndex[" + i + "]", 1);
            dropOutputBuffer(mediaCodec, i);
            return true;
        }
        if (Util.SDK_INT < 21) {
            if (j4 >= 30000) {
                return false;
            }
            if (j4 > 11000) {
                try {
                    Thread.sleep((j4 - 10000) / 1000);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
            renderOutputBuffer(mediaCodec, i);
            long j5 = bufferInfo.presentationTimeUs;
            this.lastPresentationTimeUs = j5;
            this.consecutiveDroppedFrameCount = 0;
            addToRecentRendered(j5);
            return true;
        }
        if (j4 >= 50000) {
            return false;
        }
        MtkDebugLogUtil.log("MediaCodecVideoTrackRenderer", "renderOutputBufferV21, presentationTimeUs[" + bufferInfo.presentationTimeUs + "], bufferIndex[" + i + "]", 1);
        MediaFormat mediaFormat = this.format;
        if (mediaFormat == null || !"video/dolby-vision".equals(mediaFormat.mimeType) || this.renderedSecondFrame) {
            z2 = true;
        } else {
            if (j4 > 0) {
                long j6 = (bufferInfo.presentationTimeUs - this.lastPresentationTimeUs) - j4;
                if (j6 > 0) {
                    jAdjustReleaseTime += j6 * 1000;
                }
            }
            z2 = true;
            this.renderedSecondFrame = true;
        }
        renderOutputBufferV21(mediaCodec, i, jAdjustReleaseTime);
        long j7 = bufferInfo.presentationTimeUs;
        this.lastPresentationTimeUs = j7;
        this.consecutiveDroppedFrameCount = 0;
        addToRecentRendered(j7);
        return z2;
    }

    protected void skipOutputBuffer(MediaCodec mediaCodec, int i) {
        TraceUtil.beginSection("skipVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        this.codecCounters.skippedOutputBufferCount++;
    }

    protected void dropOutputBuffer(MediaCodec mediaCodec, int i) {
        TraceUtil.beginSection("dropVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        CodecCounters codecCounters = this.codecCounters;
        codecCounters.droppedOutputBufferCount++;
        this.droppedFrameCount++;
        int i2 = this.consecutiveDroppedFrameCount + 1;
        this.consecutiveDroppedFrameCount = i2;
        codecCounters.maxConsecutiveDroppedOutputBufferCount = Math.max(i2, codecCounters.maxConsecutiveDroppedOutputBufferCount);
        if (this.droppedFrameCount == this.maxDroppedFrameCountToNotify) {
            maybeNotifyDroppedFrameCount();
        }
    }

    protected void renderOutputBuffer(MediaCodec mediaCodec, int i) {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, true);
        TraceUtil.endSection();
        this.codecCounters.renderedOutputBufferCount++;
        this.renderedFirstFrame = true;
        this.hasRenderedFrame = true;
        maybeNotifyDrawnToSurface();
    }

    @TargetApi(21)
    protected void renderOutputBufferV21(MediaCodec mediaCodec, int i, long j) throws ExoPlaybackException {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        try {
            mediaCodec.releaseOutputBuffer(i, j);
            TraceUtil.endSection();
            this.codecCounters.renderedOutputBufferCount++;
            this.renderedFirstFrame = true;
            this.hasRenderedFrame = true;
            maybeNotifyDrawnToSurface();
        } catch (MediaCodec.CodecException e) {
            Log.e("MediaCodecVideoTrackRenderer", "Catch MediaCodec.CodecException, error:" + e.getErrorCode());
            throw e;
        } catch (IllegalStateException e2) {
            throw new ExoPlaybackException(e2, 0);
        }
    }

    /* JADX WARN: Code duplicated, block: B:58:0x00e2  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @SuppressLint({"InlinedApi"})
    private void maybeSetMaxInputSize(android.media.MediaFormat mediaFormat, boolean z) {
        int i;
        if ("1".equals(Util.getProp("mtk.exoplayer.dv.seamless", "0")) && mediaFormat.getString("mime").contains("video/dolby-vision")) {
            if (!mediaFormat.containsKey("max-height")) {
                mediaFormat.setInteger("max-height", 2160);
            }
            if (!mediaFormat.containsKey("max-width")) {
                mediaFormat.setInteger("max-width", 3840);
            }
        }
        if (mediaFormat.containsKey("max-input-size")) {
            return;
        }
        int integer = mediaFormat.getInteger("height");
        if (z && mediaFormat.containsKey("max-height")) {
            integer = Math.max(integer, mediaFormat.getInteger("max-height"));
        }
        int integer2 = mediaFormat.getInteger("width");
        if (z && mediaFormat.containsKey("max-width")) {
            integer2 = Math.max(integer2, mediaFormat.getInteger("max-width"));
        }
        String string = mediaFormat.getString("mime");
        byte b = -1;
        int i2 = 4;
        switch (string.hashCode()) {
            case -1664118616:
                if (string.equals("video/3gpp")) {
                    b = 0;
                }
                break;
            case -1662541442:
                if (string.equals("video/hevc")) {
                    b = 4;
                }
                break;
            case 1187890754:
                if (string.equals("video/mp4v-es")) {
                    b = 1;
                }
                break;
            case 1331836730:
                if (string.equals("video/avc")) {
                    b = 2;
                }
                break;
            case 1599127256:
                if (string.equals("video/x-vnd.on2.vp8")) {
                    b = 3;
                }
                break;
            case 1599127257:
                if (string.equals("video/x-vnd.on2.vp9")) {
                    b = 5;
                }
                break;
        }
        if (b == 0 || b == 1) {
            i = integer2 * integer;
            i2 = 2;
        } else if (b == 2) {
            if ("BRAVIA 4K 2015".equals(Util.MODEL)) {
                return;
            }
            i = ((integer2 + 15) / 16) * ((integer + 15) / 16) * 16 * 16;
            i2 = 2;
        } else if (b == 3) {
            i = integer2 * integer;
            i2 = 2;
        } else if (b != 4 && b != 5) {
            return;
        } else {
            i = integer2 * integer;
        }
        mediaFormat.setInteger("max-input-size", (i * 3) / (i2 * 2));
    }

    private void maybeNotifyVideoSizeChanged() {
        if (this.eventHandler == null || this.eventListeners.size() == 0) {
            return;
        }
        if (this.lastReportedWidth == this.currentWidth && this.lastReportedHeight == this.currentHeight && this.lastReportedUnappliedRotationDegrees == this.currentUnappliedRotationDegrees && this.lastReportedPixelWidthHeightRatio == this.currentPixelWidthHeightRatio) {
            return;
        }
        final int i = this.currentWidth;
        final int i2 = this.currentHeight;
        final int i3 = this.currentUnappliedRotationDegrees;
        final float f = this.currentPixelWidthHeightRatio;
        this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer.MediaCodecVideoTrackRenderer.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = MediaCodecVideoTrackRenderer.this.eventListeners.iterator();
                while (it.hasNext()) {
                    ((EventListener) it.next()).onVideoSizeChanged(i, i2, i3, f);
                }
            }
        });
        this.lastReportedWidth = i;
        this.lastReportedHeight = i2;
        this.lastReportedUnappliedRotationDegrees = i3;
        this.lastReportedPixelWidthHeightRatio = f;
    }

    private void maybeNotifyDrawnToSurface() {
        if (this.eventHandler == null || this.eventListeners.size() == 0 || this.reportedDrawnToSurface) {
            return;
        }
        final Surface surface = this.surface;
        this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer.MediaCodecVideoTrackRenderer.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = MediaCodecVideoTrackRenderer.this.eventListeners.iterator();
                while (it.hasNext()) {
                    ((EventListener) it.next()).onDrawnToSurface(surface);
                }
            }
        });
        this.reportedDrawnToSurface = true;
    }

    private void maybeNotifyDroppedFrameCount() {
        if (this.eventHandler == null || this.eventListeners.size() == 0 || this.droppedFrameCount == 0) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        final int i = this.droppedFrameCount;
        final long j = jElapsedRealtime - this.droppedFrameAccumulationStartTimeMs;
        this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer.MediaCodecVideoTrackRenderer.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = MediaCodecVideoTrackRenderer.this.eventListeners.iterator();
                while (it.hasNext()) {
                    ((EventListener) it.next()).onDroppedFrames(i, j);
                }
            }
        });
        this.droppedFrameCount = 0;
        this.droppedFrameAccumulationStartTimeMs = jElapsedRealtime;
    }

    private void maybeNotifyRenderFirstFrame() {
        if (this.eventHandler == null || this.eventListeners.size() == 0) {
            return;
        }
        this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer.MediaCodecVideoTrackRenderer.4
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = MediaCodecVideoTrackRenderer.this.eventListeners.iterator();
                while (it.hasNext()) {
                    ((EventListener) it.next()).onRenderFirstFrame();
                }
            }
        });
    }

    @Override // com.google.android.exoplayer.MediaClock
    public long getPositionUs() {
        return this.lastPresentationTimeUs;
    }

    protected void setupVideoMediaTunnelingOutput() {
        if (this.isSetup) {
            return;
        }
        int i = this.lastReportedWidth;
        int i2 = this.lastReportedHeight;
        float f = this.lastReportedPixelWidthHeightRatio;
        maybeNotifyVideoSizeChanged();
        if (i == this.lastReportedWidth && i2 == this.lastReportedHeight && f == this.lastReportedPixelWidthHeightRatio && !this.isSetup) {
            return;
        }
        this.renderedFirstFrame = true;
        this.hasRenderedFrame = true;
        this.isSetup = true;
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    public void setTunnelingAudioSessionId(int i) throws IllegalArgumentException {
        Assertions.checkArgument(i >= 0);
        super.setTunnelingAudioSessionId(i);
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected boolean isTunneledSupported(MediaFormat mediaFormat) {
        DecoderInfo decoderInfo;
        try {
            decoderInfo = getDecoderInfo(MediaCodecSelector.DEFAULT, mediaFormat.mimeType, false);
        } catch (MediaCodecUtil.DecoderQueryException e) {
            e.printStackTrace();
            decoderInfo = null;
        }
        return decoderInfo != null && decoderInfo.tunneled;
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void maybeThrowError() throws ExoPlaybackException {
        super.maybeThrowError();
        if (super.isEnded() && !this.hasRenderedFrame && this.format.durationUs - this.lastSeekPositionUs > 1000000) {
            throw new ExoPlaybackException(new ExoPlaybackException("Cannot render any video frame when data is ended!"), 0);
        }
    }

    private void addToRecentRendered(long j) {
        ArrayBlockingQueue<Long> arrayBlockingQueue = this.recentRenderedPresentTimestamps;
        if (arrayBlockingQueue == null) {
            return;
        }
        if (arrayBlockingQueue.size() == 3) {
            this.recentRenderedPresentTimestamps.poll();
        }
        this.recentRenderedPresentTimestamps.add(Long.valueOf(j));
    }

    private boolean isRecentRendered(long j) {
        ArrayBlockingQueue<Long> arrayBlockingQueue = this.recentRenderedPresentTimestamps;
        if (arrayBlockingQueue == null) {
            return false;
        }
        return arrayBlockingQueue.contains(Long.valueOf(j));
    }

    @Override // android.media.MediaCodec.OnFrameRenderedListener
    public void onFrameRendered(MediaCodec mediaCodec, long j, long j2) {
        if (!this.notifyTunnelRenderFirstFrame) {
            Log.d("MediaCodecVideoTrackRenderer", "[Performance]onFrameRendered, presentationTimeUs=" + j + ", nanoTime=" + j2);
        } else {
            MtkDebugLogUtil.log("MediaCodecVideoTrackRenderer", "onFrameRendered, presentationTimeUs=" + j + ", nanoTime=" + j2 + ", renderedFirstFrame=" + this.renderedFirstFrame, 1);
        }
        boolean z = false;
        int tunnelingAudioSessionId = getTunnelingAudioSessionId();
        if (tunnelingAudioSessionId != -1 && !this.renderedFirstFrame && j >= this.lastSeekPositionUs) {
            this.renderedFirstFrame = true;
            z = true;
        }
        if (!this.notifyTunnelRenderFirstFrame) {
            this.notifyTunnelRenderFirstFrame = true;
            z = true;
        }
        if (z) {
            maybeNotifyRenderFirstFrame();
        }
        if (Util.SDK_INT < 21 || tunnelingAudioSessionId == -1) {
            return;
        }
        if (j == LongCompanionObject.MAX_VALUE) {
            this.receiveTunnelEos = true;
        } else {
            this.lastPresentationTimeUs = j;
        }
    }

    public boolean getTunnelEos() {
        return this.receiveTunnelEos;
    }

    public void addEventListener(EventListener eventListener) {
        if (eventListener != null) {
            this.eventListeners.add(eventListener);
        }
    }

    private void setRotation(int i) throws ExoPlaybackException {
        MediaFormat mediaFormat = this.format;
        if (mediaFormat.rotationDegrees == i) {
            return;
        }
        mediaFormat.rotationDegrees = i;
        mediaFormat.getFrameworkMediaFormatV16().setInteger("rotation-degrees", i);
        int state = getState();
        if (state == 2 || state == 3) {
            releaseCodec();
            maybeInitCodec();
            this.pendingRotationDegrees = i;
            this.currentUnappliedRotationDegrees = i;
            if (getTunnelingAudioSessionId() != -1) {
                if (i == 0 || i == 180) {
                    MediaFormat mediaFormat2 = this.format;
                    this.currentWidth = mediaFormat2.width;
                    this.currentHeight = mediaFormat2.height;
                } else {
                    MediaFormat mediaFormat3 = this.format;
                    this.currentWidth = mediaFormat3.height;
                    this.currentHeight = mediaFormat3.width;
                }
                maybeNotifyVideoSizeChanged();
            }
        }
    }
}
