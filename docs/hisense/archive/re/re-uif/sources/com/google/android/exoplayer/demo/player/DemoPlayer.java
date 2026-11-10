package com.google.android.exoplayer.demo.player;

import android.media.AudioManager;
import android.media.MediaCodec;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import com.google.android.exoplayer.CodecCounters;
import com.google.android.exoplayer.DummyTrackRenderer;
import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecTrackRenderer;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.SingleSampleSource;
import com.google.android.exoplayer.TimeRange;
import com.google.android.exoplayer.TrackRenderer;
import com.google.android.exoplayer.audio.AudioTrack;
import com.google.android.exoplayer.chunk.ChunkSampleSource;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.dash.DashChunkSource;
import com.google.android.exoplayer.demo.PlayreadyTestMediaDrmCallback;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.drm.StreamingDrmSessionManager;
import com.google.android.exoplayer.drm.UnsupportedDrmException;
import com.google.android.exoplayer.extractor.ExtractorSampleSource;
import com.google.android.exoplayer.hls.HlsSampleSource;
import com.google.android.exoplayer.metadata.MetadataTrackRenderer;
import com.google.android.exoplayer.metadata.id3.Id3Frame;
import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.TextRenderer;
import com.google.android.exoplayer.upstream.BandwidthMeter;
import com.google.android.exoplayer.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer.upstream.FrameworkDataSource;
import com.google.android.exoplayer.util.MtkPlayerControl;
import com.google.android.exoplayer.util.PlayerControl;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Unexpected interfaces in signature: [com.google.android.exoplayer.TsSiTableTrackRenderer$EventListener] */
/* JADX INFO: loaded from: classes.dex */
public class DemoPlayer implements ExoPlayer.Listener, ChunkSampleSource.EventListener, HlsSampleSource.EventListener, ExtractorSampleSource.EventListener, SingleSampleSource.EventListener, BandwidthMeter.EventListener, MediaCodecVideoTrackRenderer.EventListener, MediaCodecAudioTrackRenderer.EventListener, StreamingDrmSessionManager.EventListener, DashChunkSource.EventListener, TextRenderer, MetadataTrackRenderer.MetadataRenderer<List<Id3Frame>>, Object {
    private AudioManager audioManager;
    private CaptionListener captionListener;
    private DefaultHttpDataSource.Callback httpDataSourceCallback;
    private Id3MetadataListener id3MetadataListener;
    private InfoListener infoListener;
    private InternalErrorListener internalErrorListener;
    private boolean lastReportedPlayWhenReady;
    private int lastReportedPlaybackState;
    private final CopyOnWriteArrayList<Listener> listeners;
    private final Handler mainHandler;
    private int mediaTunneling;
    private String outputPath;
    private final ExoPlayer player;
    private final PlayerControl playerControl;
    private final RendererBuilder rendererBuilder;
    private int rendererBuildingState;
    private Surface surface;
    private boolean tsSiTableEnabled;
    private TrackRenderer videoRenderer;
    private boolean[] renderersUnsupported = new boolean[6];
    private int videoScalingMode = 1;

    public interface CaptionListener {
        void onCues(List<Cue> list);
    }

    public interface Id3MetadataListener {
        void onId3Metadata(List<Id3Frame> list);
    }

    public interface InfoListener {
        void onAudioFormatEnabled(Format format, int i, long j);

        void onAvailableRangeChanged(int i, TimeRange timeRange);

        void onBandwidthSample(int i, long j, long j2);

        void onDecoderInitialized(String str, long j, long j2);

        void onDroppedFrames(int i, long j);

        void onLoadCompleted(int i, long j, int i2, int i3, Format format, long j2, long j3, long j4, long j5);

        void onLoadStarted(int i, long j, int i2, int i3, Format format, long j2, long j3);

        void onVideoFormatEnabled(Format format, int i, long j);
    }

    public interface InternalErrorListener {
        void onAudioTrackInitializationError(AudioTrack.InitializationException initializationException);

        void onAudioTrackUnderrun(int i, long j, long j2);

        void onAudioTrackWriteError(AudioTrack.WriteException writeException);

        void onCryptoError(MediaCodec.CryptoException cryptoException);

        void onDecoderInitializationError(MediaCodecTrackRenderer.DecoderInitializationException decoderInitializationException);

        void onDrmSessionManagerError(Exception exc);

        void onLoadError(int i, IOException iOException);

        void onRendererInitializationError(Exception exc);
    }

    public interface Listener {
        void onError(Exception exc);

        void onInfo(int i);

        void onStateChanged(boolean z, int i);

        void onVideoSizeChanged(int i, int i2, int i3, float f);
    }

    public interface RendererBuilder {
        void buildRenderers(DemoPlayer demoPlayer);

        void cancel();

        void setHttpDataSourceCallback(DefaultHttpDataSource.Callback callback);

        boolean sourceIsLocal();
    }

    @Override // com.google.android.exoplayer.MediaCodecVideoTrackRenderer.EventListener
    public void onDrawnToSurface(Surface surface) {
    }

    @Override // com.google.android.exoplayer.drm.StreamingDrmSessionManager.EventListener
    public void onDrmKeysLoaded() {
    }

    @Override // com.google.android.exoplayer.chunk.BaseChunkSampleSourceEventListener
    public void onLoadCanceled(int i, long j) {
    }

    @Override // com.google.android.exoplayer.ExoPlayer.Listener
    public void onPlayWhenReadyCommitted() {
    }

    @Override // com.google.android.exoplayer.chunk.BaseChunkSampleSourceEventListener
    public void onUpstreamDiscarded(int i, long j, long j2) {
    }

    public DemoPlayer(RendererBuilder rendererBuilder) {
        this.rendererBuilder = rendererBuilder;
        ExoPlayer exoPlayerNewInstance = ExoPlayer.Factory.newInstance(6, rendererBuilder.sourceIsLocal() ? 0 : 1000, rendererBuilder.sourceIsLocal() ? 0 : 5000);
        this.player = exoPlayerNewInstance;
        exoPlayerNewInstance.addListener(this);
        this.playerControl = new MtkPlayerControl(this.player);
        this.mainHandler = new Handler();
        this.listeners = new CopyOnWriteArrayList<>();
        this.lastReportedPlaybackState = 1;
        this.rendererBuildingState = 1;
        this.player.setSelectedTrack(2, -1);
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void setInfoListener(InfoListener infoListener) {
        this.infoListener = infoListener;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
        pushSurface(false);
    }

    public void blockingClearSurface() {
        this.surface = null;
        pushSurface(true);
    }

    public int getTrackCount(int i) {
        return this.player.getTrackCount(i);
    }

    public MediaFormat getTrackFormat(int i, int i2) {
        return this.player.getTrackFormat(i, i2);
    }

    public int getSelectedTrack(int i) {
        return getMtkPlayerControl().getSelectedTrack(i);
    }

    public void setSelectedTrack(int i, int i2) {
        CaptionListener captionListener;
        if ((this.rendererBuilder instanceof DashRendererBuilder) && trackSelection(i, i2)) {
            return;
        }
        getMtkPlayerControl().setSelectedTrack(i, i2);
        if (i != 2 || i2 >= 0 || (captionListener = this.captionListener) == null) {
            return;
        }
        captionListener.onCues(Collections.emptyList());
    }

    public void prepare() {
        if (this.rendererBuildingState == 3) {
            this.player.stop();
        }
        this.rendererBuilder.cancel();
        this.videoRenderer = null;
        this.rendererBuildingState = 2;
        maybeReportPlayerState();
        this.rendererBuilder.setHttpDataSourceCallback(this.httpDataSourceCallback);
        this.rendererBuilder.buildRenderers(this);
    }

    void onRenderers(TrackRenderer[] trackRendererArr, BandwidthMeter bandwidthMeter) {
        for (int i = 0; i < 6; i++) {
            if (trackRendererArr[i] == null) {
                trackRendererArr[i] = new DummyTrackRenderer();
            }
        }
        TrackRenderer trackRenderer = trackRendererArr[0];
        this.videoRenderer = trackRenderer;
        if (trackRenderer instanceof MediaCodecTrackRenderer) {
            CodecCounters codecCounters = ((MediaCodecTrackRenderer) trackRenderer).codecCounters;
        } else if (trackRendererArr[1] instanceof MediaCodecTrackRenderer) {
            CodecCounters codecCounters2 = ((MediaCodecTrackRenderer) trackRendererArr[1]).codecCounters;
        }
        getMtkPlayerControl().updateTrackRenderers(trackRendererArr);
        this.player.setMediaTunneling(this.mediaTunneling, this.audioManager);
        setSeekMode(!"1".equals(Util.getProp("vendor.mtk.exoplayer.seek.mode", "")) ? 1 : 0);
        if (!this.tsSiTableEnabled) {
            this.player.setSelectedTrack(4, -1);
        }
        getMtkPlayerControl().setVideoOutput(this.outputPath);
        this.player.setSelectedTrack(5, -1);
        pushSurface(false);
        this.player.prepare(trackRendererArr);
        this.rendererBuildingState = 3;
    }

    void onRenderers(TrackRenderer[] trackRendererArr, BandwidthMeter bandwidthMeter, FrameworkDataSource frameworkDataSource) {
        MtkPlayerControl mtkPlayerControl = getMtkPlayerControl();
        MtkPlayerControl mtkPlayerControl2 = getMtkPlayerControl();
        Objects.requireNonNull(mtkPlayerControl2);
        mtkPlayerControl.setSourceController(new MtkPlayerControl.SourceController(mtkPlayerControl2, frameworkDataSource));
        onRenderers(trackRendererArr, bandwidthMeter);
    }

    void onRenderersError(Exception exc) {
        InternalErrorListener internalErrorListener = this.internalErrorListener;
        if (internalErrorListener != null) {
            internalErrorListener.onRendererInitializationError(exc);
        }
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onError(exc);
        }
        this.rendererBuildingState = 1;
        maybeReportPlayerState();
    }

    public void setPlayWhenReady(boolean z) {
        this.player.setPlayWhenReady(z);
    }

    public void seekTo(long j) {
        this.player.seekTo(j);
    }

    public void release() {
        this.rendererBuilder.cancel();
        this.rendererBuildingState = 1;
        this.surface = null;
        PlayerControl playerControl = this.playerControl;
        if (playerControl instanceof MtkPlayerControl) {
            ((MtkPlayerControl) playerControl).release();
        }
        this.listeners.clear();
        this.player.release();
    }

    public int getPlaybackState() {
        int playbackState = this.player.getPlaybackState();
        if (this.rendererBuildingState == 2 && playbackState == 1) {
            return 2;
        }
        if (this.rendererBuildingState == 3 && playbackState == 1) {
            return 2;
        }
        return playbackState;
    }

    public boolean getPlayWhenReady() {
        return this.player.getPlayWhenReady();
    }

    Looper getPlaybackLooper() {
        return this.player.getPlaybackLooper();
    }

    Handler getMainHandler() {
        return this.mainHandler;
    }

    @Override // com.google.android.exoplayer.ExoPlayer.Listener
    public void onPlayerStateChanged(boolean z, int i) {
        maybeReportPlayerState();
    }

    @Override // com.google.android.exoplayer.ExoPlayer.Listener
    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        this.rendererBuildingState = 1;
        for (Listener listener : this.listeners) {
            int i = exoPlaybackException.codecErrorType;
            if (i == 1) {
                Log.w("DemoPlayer", "Catch audio codec error, message: " + exoPlaybackException.getMessage());
                this.renderersUnsupported[1] = true;
            } else if (i == 0) {
                Log.w("DemoPlayer", "Catch video codec error, message: " + exoPlaybackException.getMessage());
                this.renderersUnsupported[0] = true;
            }
            listener.onError(exoPlaybackException);
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecVideoTrackRenderer.EventListener
    public void onVideoSizeChanged(int i, int i2, int i3, float f) {
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onVideoSizeChanged(i, i2, i3, f);
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecVideoTrackRenderer.EventListener
    public void onDroppedFrames(int i, long j) {
        InfoListener infoListener = this.infoListener;
        if (infoListener != null) {
            infoListener.onDroppedFrames(i, j);
        }
    }

    @Override // com.google.android.exoplayer.upstream.BandwidthMeter.EventListener
    public void onBandwidthSample(int i, long j, long j2) {
        InfoListener infoListener = this.infoListener;
        if (infoListener != null) {
            infoListener.onBandwidthSample(i, j, j2);
        }
    }

    @Override // com.google.android.exoplayer.chunk.BaseChunkSampleSourceEventListener
    public void onDownstreamFormatChanged(int i, Format format, int i2, long j) {
        InfoListener infoListener = this.infoListener;
        if (infoListener == null) {
            return;
        }
        if (i == 0) {
            infoListener.onVideoFormatEnabled(format, i2, j);
        } else if (i == 1) {
            infoListener.onAudioFormatEnabled(format, i2, j);
        }
    }

    @Override // com.google.android.exoplayer.drm.StreamingDrmSessionManager.EventListener
    public void onDrmSessionManagerError(Exception exc) {
        InternalErrorListener internalErrorListener = this.internalErrorListener;
        if (internalErrorListener != null) {
            internalErrorListener.onDrmSessionManagerError(exc);
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer.EventListener
    public void onDecoderInitializationError(MediaCodecTrackRenderer.DecoderInitializationException decoderInitializationException) {
        InternalErrorListener internalErrorListener = this.internalErrorListener;
        if (internalErrorListener != null) {
            internalErrorListener.onDecoderInitializationError(decoderInitializationException);
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecAudioTrackRenderer.EventListener
    public void onAudioTrackInitializationError(AudioTrack.InitializationException initializationException) {
        InternalErrorListener internalErrorListener = this.internalErrorListener;
        if (internalErrorListener != null) {
            internalErrorListener.onAudioTrackInitializationError(initializationException);
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecAudioTrackRenderer.EventListener
    public void onAudioTrackWriteError(AudioTrack.WriteException writeException) {
        InternalErrorListener internalErrorListener = this.internalErrorListener;
        if (internalErrorListener != null) {
            internalErrorListener.onAudioTrackWriteError(writeException);
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecAudioTrackRenderer.EventListener
    public void onAudioTrackUnderrun(int i, long j, long j2) {
        InternalErrorListener internalErrorListener = this.internalErrorListener;
        if (internalErrorListener != null) {
            internalErrorListener.onAudioTrackUnderrun(i, j, j2);
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer.EventListener
    public void onCryptoError(MediaCodec.CryptoException cryptoException) {
        InternalErrorListener internalErrorListener = this.internalErrorListener;
        if (internalErrorListener != null) {
            internalErrorListener.onCryptoError(cryptoException);
        }
    }

    @Override // com.google.android.exoplayer.MediaCodecTrackRenderer.EventListener
    public void onDecoderInitialized(String str, long j, long j2) {
        InfoListener infoListener = this.infoListener;
        if (infoListener != null) {
            infoListener.onDecoderInitialized(str, j, j2);
        }
    }

    @Override // com.google.android.exoplayer.chunk.BaseChunkSampleSourceEventListener, com.google.android.exoplayer.extractor.ExtractorSampleSource.EventListener, com.google.android.exoplayer.SingleSampleSource.EventListener
    public void onLoadError(int i, IOException iOException) {
        InternalErrorListener internalErrorListener = this.internalErrorListener;
        if (internalErrorListener != null) {
            internalErrorListener.onLoadError(i, iOException);
        }
    }

    @Override // com.google.android.exoplayer.text.TextRenderer
    public void onCues(List<Cue> list) {
        if (this.playerControl == null || getSelectedTrack(2) == -1) {
            return;
        }
        getMtkPlayerControl().onCues(list);
    }

    @Override // com.google.android.exoplayer.metadata.MetadataTrackRenderer.MetadataRenderer
    public void onMetadata(List<Id3Frame> list) {
        if (this.id3MetadataListener == null || getSelectedTrack(3) == -1) {
            return;
        }
        this.id3MetadataListener.onId3Metadata(list);
    }

    @Override // com.google.android.exoplayer.dash.DashChunkSource.EventListener
    public void onAvailableRangeChanged(int i, TimeRange timeRange) {
        InfoListener infoListener = this.infoListener;
        if (infoListener != null) {
            infoListener.onAvailableRangeChanged(i, timeRange);
        }
    }

    @Override // com.google.android.exoplayer.chunk.BaseChunkSampleSourceEventListener
    public void onLoadStarted(int i, long j, int i2, int i3, Format format, long j2, long j3) {
        InfoListener infoListener = this.infoListener;
        if (infoListener != null) {
            infoListener.onLoadStarted(i, j, i2, i3, format, j2, j3);
        }
    }

    @Override // com.google.android.exoplayer.chunk.BaseChunkSampleSourceEventListener
    public void onLoadCompleted(int i, long j, int i2, int i3, Format format, long j2, long j3, long j4, long j5) {
        InfoListener infoListener = this.infoListener;
        if (infoListener != null) {
            infoListener.onLoadCompleted(i, j, i2, i3, format, j2, j3, j4, j5);
        }
    }

    private void maybeReportPlayerState() {
        boolean playWhenReady = this.player.getPlayWhenReady();
        int playbackState = getPlaybackState();
        if (isPlayerPrepared(playbackState)) {
            Iterator<Listener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onInfo(3);
            }
            if (this.player.getTrackCount(0) > 0 && this.player.getTrackCount(1) == 0) {
                Iterator<Listener> it2 = this.listeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onInfo(1);
                }
            }
            getMtkPlayerControl().maybeSelectProgramForNativeTS(0);
        }
        if (this.lastReportedPlayWhenReady == playWhenReady && this.lastReportedPlaybackState == playbackState) {
            return;
        }
        Iterator<Listener> it3 = this.listeners.iterator();
        while (it3.hasNext()) {
            it3.next().onStateChanged(playWhenReady, playbackState);
        }
        this.lastReportedPlayWhenReady = playWhenReady;
        this.lastReportedPlaybackState = playbackState;
    }

    private void pushSurface(boolean z) {
        TrackRenderer trackRenderer = this.videoRenderer;
        if (trackRenderer == null) {
            return;
        }
        if (z) {
            this.player.blockingSendMessage(trackRenderer, 1, this.surface);
        } else {
            this.player.sendMessage(trackRenderer, 1, this.surface);
        }
    }

    public MtkPlayerControl getMtkPlayerControl() {
        PlayerControl playerControl = this.playerControl;
        if (playerControl instanceof MtkPlayerControl) {
            return (MtkPlayerControl) playerControl;
        }
        return null;
    }

    @Override // com.google.android.exoplayer.MediaCodecVideoTrackRenderer.EventListener
    public void onRenderFirstFrame() {
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onInfo(2);
        }
    }

    public void setOutputPath(String str) {
        this.outputPath = str;
    }

    public boolean[] getRenderersUnsupported() {
        return this.renderersUnsupported;
    }

    private boolean isPlayerPrepared(int i) {
        int i2 = this.lastReportedPlaybackState;
        return (i2 == 1 || i2 == 2) && (i == 3 || i == 4 || i == 5);
    }

    public void setMediaTunneling(int i, AudioManager audioManager) {
        this.mediaTunneling = i;
        this.audioManager = audioManager;
    }

    @Override // com.google.android.exoplayer.dash.DashChunkSource.EventListener
    public void onDrmInitData(DrmInitData drmInitData) {
        if (drmInitData.get(StreamingDrmSessionManager.PLAYREADY_UUID) != null && (drmInitData instanceof DrmInitData.Mapped)) {
            try {
                this.player.setDrmSessionManager(StreamingDrmSessionManager.newPlayReadyInstance(getPlaybackLooper(), new PlayreadyTestMediaDrmCallback(), null, getMainHandler(), this));
            } catch (UnsupportedDrmException e) {
                e.printStackTrace();
            }
        }
        if (drmInitData.get(StreamingDrmSessionManager.VERYMATRIX_UUID) != null) {
            boolean z = drmInitData instanceof DrmInitData.Mapped;
        }
    }

    public void onTsSiTableNotify(ByteBuffer byteBuffer) {
        Log.i("DemoPlayer", "onSiTableNotify");
    }

    private boolean forceSubtitle(int i) {
        RendererBuilder rendererBuilder = this.rendererBuilder;
        if (rendererBuilder instanceof DashRendererBuilder) {
            return ((DashRendererBuilder) rendererBuilder).forceSubtitle(i);
        }
        return false;
    }

    private boolean trackSelection(int i, int i2) {
        if (i == 2 && i2 == -1) {
            MediaFormat trackFormat = getTrackFormat(1, getSelectedTrack(1));
            MediaFormat trackFormat2 = getTrackFormat(2, getSelectedTrack(2));
            String str = trackFormat.language;
            if (str != null && str.equals(trackFormat2.language)) {
                getMtkPlayerControl().setSelectedTrack(2, getSelectedTrack(2));
                return true;
            }
        }
        if (i == 1 && i2 >= 0) {
            MediaFormat trackFormat3 = getTrackFormat(1, i2);
            if (trackFormat3.language != null) {
                for (int i3 = 0; i3 < getTrackCount(2); i3++) {
                    if (trackFormat3.language.equals(getTrackFormat(2, i3).language) && forceSubtitle(i3)) {
                        getMtkPlayerControl().setSelectedTrack(2, i3);
                        break;
                    }
                }
            }
        }
        return false;
    }

    public int getVideoScalingMode() {
        return this.videoScalingMode;
    }

    public boolean isTunnlingMode() {
        return this.player.isTunnelingPlayback();
    }

    public void setSeekMode(int i) {
        this.player.setSeekMode(i);
    }
}
