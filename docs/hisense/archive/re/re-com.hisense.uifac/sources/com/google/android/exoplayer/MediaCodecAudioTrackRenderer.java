package com.google.android.exoplayer;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.PlaybackParams;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import com.google.android.exoplayer.audio.AudioCapabilities;
import com.google.android.exoplayer.audio.AudioTrack;
import com.google.android.exoplayer.drm.DrmSessionManager;
import com.google.android.exoplayer.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.Util;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(16)
public class MediaCodecAudioTrackRenderer extends MediaCodecTrackRenderer implements MediaClock {
    private boolean allowPositionDiscontinuity;
    private int audioSessionId;
    private final AudioTrack audioTrack;
    private boolean audioTrackHasData;
    private long currentPositionUs;
    private final EventListener eventListener;
    private boolean firstOutputBufferAfterFormatChange;
    private long lastFeedElapsedRealtimeMs;
    private boolean passthroughEnabled;
    private android.media.MediaFormat passthroughMediaFormat;
    private int pcmEncoding;

    public interface EventListener extends MediaCodecTrackRenderer.EventListener {
        void onAudioTrackInitializationError(AudioTrack.InitializationException initializationException);

        void onAudioTrackUnderrun(int i, long j, long j2);

        void onAudioTrackWriteError(AudioTrack.WriteException writeException);
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected MediaClock getMediaClock() {
        return this;
    }

    protected void handleAudioTrackDiscontinuity() {
    }

    protected void onAudioSessionId(int i) {
    }

    public MediaCodecAudioTrackRenderer(SampleSource sampleSource, MediaCodecSelector mediaCodecSelector, DrmSessionManager drmSessionManager, boolean z, Handler handler, EventListener eventListener, AudioCapabilities audioCapabilities, int i) {
        this(new SampleSource[]{sampleSource}, mediaCodecSelector, drmSessionManager, z, handler, eventListener, audioCapabilities, i);
    }

    public MediaCodecAudioTrackRenderer(SampleSource[] sampleSourceArr, MediaCodecSelector mediaCodecSelector, DrmSessionManager drmSessionManager, boolean z, Handler handler, EventListener eventListener, AudioCapabilities audioCapabilities, int i) {
        super(sampleSourceArr, mediaCodecSelector, (DrmSessionManager<FrameworkMediaCrypto>) drmSessionManager, z, handler, eventListener);
        this.firstOutputBufferAfterFormatChange = false;
        this.eventListener = eventListener;
        this.audioSessionId = 0;
        this.audioTrack = new AudioTrack(audioCapabilities, i);
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected boolean handlesTrack(MediaCodecSelector mediaCodecSelector, MediaFormat mediaFormat) throws MediaCodecUtil.DecoderQueryException {
        String str = mediaFormat.mimeType;
        if (MimeTypes.isAudio(str)) {
            return "audio/x-unknown".equals(str) || (allowPassthrough(str) && mediaCodecSelector.getPassthroughDecoderInfo() != null && AudioCapabilities.isDirectPlaybackSupported(mediaFormat)) || mediaCodecSelector.getDecoderInfo(str, false) != null || "audio/eac3-joc".equals(str);
        }
        return false;
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected DecoderInfo getDecoderInfo(MediaCodecSelector mediaCodecSelector, String str, boolean z) throws MediaCodecUtil.DecoderQueryException {
        return getDecoderInfo(mediaCodecSelector, str, z, false);
    }

    protected boolean allowPassthrough(String str) {
        return this.audioTrack.isPassthroughSupported(str);
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected void configureCodec(MediaCodec mediaCodec, boolean z, android.media.MediaFormat mediaFormat, MediaCrypto mediaCrypto) {
        String string = mediaFormat.getString("mime");
        if (mediaFormat.containsKey("encoder-delay")) {
            mediaFormat.setInteger("encoder-delay", 0);
        }
        if (mediaFormat.containsKey("encoder-padding")) {
            mediaFormat.setInteger("encoder-padding", 0);
        }
        if (this.passthroughEnabled) {
            mediaFormat.setString("mime", "audio/raw");
            mediaCodec.configure(mediaFormat, (Surface) null, mediaCrypto, 0);
            mediaFormat.setString("mime", string);
            this.passthroughMediaFormat = mediaFormat;
            return;
        }
        mediaCodec.configure(mediaFormat, (Surface) null, mediaCrypto, 0);
        this.passthroughMediaFormat = null;
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected void onInputFormatChanged(MediaFormatHolder mediaFormatHolder) throws ExoPlaybackException {
        this.pcmEncoding = "audio/raw".equals(mediaFormatHolder.format.mimeType) ? mediaFormatHolder.format.pcmEncoding : 2;
        super.onInputFormatChanged(mediaFormatHolder);
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected void onOutputFormatChanged(MediaCodec mediaCodec, android.media.MediaFormat mediaFormat) {
        boolean z = this.passthroughMediaFormat != null;
        String string = z ? this.passthroughMediaFormat.getString("mime") : "audio/raw";
        if (z) {
            mediaFormat = this.passthroughMediaFormat;
        }
        this.audioTrack.configure(string, mediaFormat, this.pcmEncoding, getTunnelingAudioSessionId());
        this.firstOutputBufferAfterFormatChange = true;
        Log.d("MediaCodecAudioTrackRenderer", "Audio onOutputFormatChanged");
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onStarted() {
        super.onStarted();
        this.audioTrack.play();
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onStopped() {
        this.audioTrack.pause();
        super.onStopped();
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected boolean isEnded() {
        return super.isEnded() && !this.audioTrack.hasPendingData();
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected boolean isReady() {
        return this.audioTrack.hasPendingData() || super.isReady();
    }

    @Override // com.google.android.exoplayer.MediaClock
    public long getPositionUs() {
        long currentPositionUs = this.audioTrack.getCurrentPositionUs(isEnded());
        if (currentPositionUs != Long.MIN_VALUE) {
            if (!this.allowPositionDiscontinuity) {
                currentPositionUs = Math.max(this.currentPositionUs, currentPositionUs);
            }
            this.currentPositionUs = currentPositionUs;
            this.allowPositionDiscontinuity = false;
        }
        return this.currentPositionUs;
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onDisabled(long j) throws ExoPlaybackException {
        this.audioSessionId = 0;
        try {
            this.audioTrack.release();
        } finally {
            super.onDisabled(j);
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.SampleSourceTrackRenderer
    protected void onDiscontinuity(long j) throws ExoPlaybackException {
        if (j < 0) {
            j = 0 - j;
        }
        super.onDiscontinuity(j);
        this.audioTrack.reset();
        this.currentPositionUs = j;
        this.allowPositionDiscontinuity = true;
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected boolean processOutputBuffer(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo, int i, boolean z) throws ExoPlaybackException {
        if (this.passthroughEnabled && (bufferInfo.flags & 2) != 0) {
            mediaCodec.releaseOutputBuffer(i, false);
            return true;
        }
        if (this.passthroughEnabled && this.firstOutputBufferAfterFormatChange) {
            mediaCodec.releaseOutputBuffer(i, false);
            Log.d("MediaCodecAudioTrackRenderer", "firstOutputBufferAfterFormatChange should release bufferIndex:" + i + ", PTS:" + bufferInfo.presentationTimeUs + ", Size:" + bufferInfo.size);
            this.firstOutputBufferAfterFormatChange = false;
            return true;
        }
        if (z) {
            mediaCodec.releaseOutputBuffer(i, false);
            this.codecCounters.skippedOutputBufferCount++;
            this.audioTrack.handleDiscontinuity();
            return true;
        }
        int tunnelingAudioSessionId = getTunnelingAudioSessionId();
        if (tunnelingAudioSessionId != -1) {
            this.audioTrack.setTunnelingAudioSessionId(tunnelingAudioSessionId);
        }
        if (!this.audioTrack.isInitialized()) {
            try {
                if (this.audioSessionId != 0) {
                    this.audioTrack.initialize(this.audioSessionId);
                } else {
                    int iInitialize = this.audioTrack.initialize();
                    this.audioSessionId = iInitialize;
                    onAudioSessionId(iInitialize);
                }
                this.audioTrackHasData = false;
                if (getState() == 3) {
                    this.audioTrack.play();
                }
            } catch (AudioTrack.InitializationException e) {
                notifyAudioTrackInitializationError(e);
                throw new ExoPlaybackException(e, 1);
            }
        } else {
            boolean z2 = this.audioTrackHasData;
            boolean zHasPendingData = this.audioTrack.hasPendingData();
            this.audioTrackHasData = zHasPendingData;
            if (z2 && !zHasPendingData && getState() == 3) {
                long jElapsedRealtime = SystemClock.elapsedRealtime() - this.lastFeedElapsedRealtimeMs;
                long bufferSizeUs = this.audioTrack.getBufferSizeUs();
                notifyAudioTrackUnderrun(this.audioTrack.getBufferSize(), bufferSizeUs != -1 ? bufferSizeUs / 1000 : -1L, jElapsedRealtime);
            }
        }
        try {
            int iHandleBuffer = this.audioTrack.handleBuffer(byteBuffer, bufferInfo.offset, bufferInfo.size, bufferInfo.presentationTimeUs);
            this.lastFeedElapsedRealtimeMs = SystemClock.elapsedRealtime();
            if ((iHandleBuffer & 1) != 0) {
                handleAudioTrackDiscontinuity();
                this.allowPositionDiscontinuity = true;
            }
            if ((iHandleBuffer & 2) == 0) {
                return false;
            }
            try {
                mediaCodec.releaseOutputBuffer(i, false);
                this.codecCounters.renderedOutputBufferCount++;
                return true;
            } catch (IllegalStateException e2) {
                throw new ExoPlaybackException(e2, 1);
            }
        } catch (AudioTrack.WriteException e3) {
            notifyAudioTrackWriteError(e3);
            throw new ExoPlaybackException(e3, 1);
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected void onOutputStreamEnded() {
        this.audioTrack.handleEndOfStream();
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer, com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer, com.google.android.exoplayer.ExoPlayer.ExoPlayerComponent
    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i == 1) {
            this.audioTrack.setVolume(((Float) obj).floatValue());
            return;
        }
        if (i == 2) {
            this.audioTrack.setPlaybackParams((PlaybackParams) obj);
            return;
        }
        if (i == 3) {
            if (this.audioTrack.setStreamType(((Integer) obj).intValue())) {
                this.audioSessionId = 0;
                return;
            }
            return;
        }
        super.handleMessage(i, obj);
    }

    private void notifyAudioTrackInitializationError(final AudioTrack.InitializationException initializationException) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.MediaCodecAudioTrackRenderer.1
            @Override // java.lang.Runnable
            public void run() {
                MediaCodecAudioTrackRenderer.this.eventListener.onAudioTrackInitializationError(initializationException);
            }
        });
    }

    private void notifyAudioTrackWriteError(final AudioTrack.WriteException writeException) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.MediaCodecAudioTrackRenderer.2
            @Override // java.lang.Runnable
            public void run() {
                MediaCodecAudioTrackRenderer.this.eventListener.onAudioTrackWriteError(writeException);
            }
        });
    }

    private void notifyAudioTrackUnderrun(final int i, final long j, final long j2) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.MediaCodecAudioTrackRenderer.3
            @Override // java.lang.Runnable
            public void run() {
                MediaCodecAudioTrackRenderer.this.eventListener.onAudioTrackUnderrun(i, j, j2);
            }
        });
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    public void setTunnelingAudioSessionId(int i) throws IllegalArgumentException {
        Assertions.checkArgument(i > 0);
        super.setTunnelingAudioSessionId(i);
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected boolean isTunneledSupported(MediaFormat mediaFormat) {
        boolean zEquals;
        if (Util.SDK_INT >= 28) {
            zEquals = "audio/mp4a-latm-hw".equals(mediaFormat.mimeType);
        } else {
            zEquals = "audio/mp4a-latm".equals(mediaFormat.mimeType) && mediaFormat.getFrameworkMediaFormatV16().containsKey("is-adts") && mediaFormat.getFrameworkMediaFormatV16().getInteger("is-adts") == 0;
        }
        try {
            DecoderInfo decoderInfo = getDecoderInfo(MediaCodecSelector.DEFAULT, mediaFormat.mimeType, false, zEquals);
            if (decoderInfo != null && decoderInfo.name != null) {
                return allowPassthrough(mediaFormat.mimeType) || mediaFormat.channelCount <= 2;
            }
            return false;
        } catch (MediaCodecUtil.DecoderQueryException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected boolean hasPendingData() {
        return this.audioTrack.hasPendingData();
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer
    protected DecoderInfo getDecoderInfo(MediaCodecSelector mediaCodecSelector, String str, boolean z, boolean z2) throws MediaCodecUtil.DecoderQueryException {
        DecoderInfo passthroughDecoderInfo;
        if (allowPassthrough(str) && (passthroughDecoderInfo = mediaCodecSelector.getPassthroughDecoderInfo()) != null) {
            this.passthroughEnabled = true;
            return passthroughDecoderInfo;
        }
        this.passthroughEnabled = false;
        return super.getDecoderInfo(mediaCodecSelector, str, z, z2);
    }
}
