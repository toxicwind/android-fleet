package com.google.android.exoplayer.demo.player;

import android.content.Context;
import android.os.Handler;
import com.google.android.exoplayer.DefaultLoadControl;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecSelector;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.SampleSource;
import com.google.android.exoplayer.TrackRenderer;
import com.google.android.exoplayer.audio.AudioCapabilities;
import com.google.android.exoplayer.chunk.ChunkSampleSource;
import com.google.android.exoplayer.chunk.FormatEvaluator;
import com.google.android.exoplayer.drm.DrmSessionManager;
import com.google.android.exoplayer.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer.drm.MediaDrmCallback;
import com.google.android.exoplayer.drm.StreamingDrmSessionManager;
import com.google.android.exoplayer.drm.UnsupportedDrmException;
import com.google.android.exoplayer.smoothstreaming.DefaultSmoothStreamingTrackSelector;
import com.google.android.exoplayer.smoothstreaming.SmoothStreamingChunkSource;
import com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifest;
import com.google.android.exoplayer.smoothstreaming.SmoothStreamingManifestParser;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.text.TextTrackRenderer;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;
import com.google.android.exoplayer.util.ManifestFetcher;
import com.google.android.exoplayer.util.Util;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class SmoothStreamingRendererBuilder implements DemoPlayer.RendererBuilder {
    private final Context context;
    private AsyncRendererBuilder currentAsyncBuilder;
    public String customData;
    private final MediaDrmCallback drmCallback;
    public String drmHeader;
    private DefaultHttpDataSource.Callback httpDataSourceCallback;
    private final String url;
    private final String userAgent;

    @Override // com.google.android.exoplayer.demo.player.DemoPlayer.RendererBuilder
    public boolean sourceIsLocal() {
        return false;
    }

    public SmoothStreamingRendererBuilder(Context context, String str, String str2, MediaDrmCallback mediaDrmCallback) {
        this.context = context;
        this.userAgent = str;
        if (!Util.toLowerInvariant(str2).endsWith("/manifest")) {
            str2 = str2 + "/Manifest";
        }
        this.url = str2;
        this.drmCallback = mediaDrmCallback;
    }

    @Override // com.google.android.exoplayer.demo.player.DemoPlayer.RendererBuilder
    public void buildRenderers(DemoPlayer demoPlayer) {
        AsyncRendererBuilder asyncRendererBuilder = new AsyncRendererBuilder(this.context, this.userAgent, this.url, this.drmCallback, demoPlayer, this.drmHeader, this.customData);
        this.currentAsyncBuilder = asyncRendererBuilder;
        asyncRendererBuilder.setHttpDataSourceCallback(this.httpDataSourceCallback);
        this.currentAsyncBuilder.init();
    }

    @Override // com.google.android.exoplayer.demo.player.DemoPlayer.RendererBuilder
    public void cancel() {
        AsyncRendererBuilder asyncRendererBuilder = this.currentAsyncBuilder;
        if (asyncRendererBuilder != null) {
            asyncRendererBuilder.cancel();
            this.currentAsyncBuilder = null;
        }
    }

    private static final class AsyncRendererBuilder implements ManifestFetcher.ManifestCallback<SmoothStreamingManifest> {
        private boolean canceled;
        private final Context context;
        public String customData;
        private final MediaDrmCallback drmCallback;
        private DefaultHttpDataSource.Callback httpDataSourceCallback;
        private final ManifestFetcher<SmoothStreamingManifest> manifestFetcher;
        private final DemoPlayer player;
        private final String userAgent;

        public AsyncRendererBuilder(Context context, String str, String str2, MediaDrmCallback mediaDrmCallback, DemoPlayer demoPlayer, String str3, String str4) {
            this.context = context;
            this.userAgent = str;
            this.drmCallback = mediaDrmCallback;
            this.player = demoPlayer;
            this.customData = str4;
            this.manifestFetcher = new ManifestFetcher<>(str2, new DefaultHttpDataSource(str, null, null, 8000, 8000, false, this.httpDataSourceCallback), new SmoothStreamingManifestParser(str3));
        }

        public void init() {
            this.manifestFetcher.singleLoad(this.player.getMainHandler().getLooper(), this);
        }

        public void cancel() {
            this.canceled = true;
        }

        @Override // com.google.android.exoplayer.util.ManifestFetcher.ManifestCallback
        public void onSingleManifestError(IOException iOException) {
            if (this.canceled) {
                return;
            }
            this.player.onRenderersError(iOException);
        }

        @Override // com.google.android.exoplayer.util.ManifestFetcher.ManifestCallback
        public void onSingleManifest(SmoothStreamingManifest smoothStreamingManifest) {
            if (this.canceled) {
                return;
            }
            Handler mainHandler = this.player.getMainHandler();
            DefaultLoadControl defaultLoadControl = new DefaultLoadControl(new DefaultAllocator(MtkTvScanDvbsBase.SB_DVBS_CONFIG_BGM_SCAN));
            DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter(mainHandler, this.player);
            StreamingDrmSessionManager<FrameworkMediaCrypto> streamingDrmSessionManagerNewPlayReadyInstance = null;
            if (smoothStreamingManifest.protectionElement != null) {
                if (Util.SDK_INT < 18) {
                    this.player.onRenderersError(new UnsupportedDrmException(1));
                    return;
                }
                try {
                    streamingDrmSessionManagerNewPlayReadyInstance = StreamingDrmSessionManager.newPlayReadyInstance(this.player.getPlaybackLooper(), this.drmCallback, this.customData, this.player.getMainHandler(), this.player);
                } catch (UnsupportedDrmException e) {
                    this.player.onRenderersError(e);
                    return;
                }
            }
            StreamingDrmSessionManager<FrameworkMediaCrypto> streamingDrmSessionManager = streamingDrmSessionManagerNewPlayReadyInstance;
            MediaCodecVideoTrackRenderer mediaCodecVideoTrackRenderer = new MediaCodecVideoTrackRenderer(this.context, new ChunkSampleSource(new SmoothStreamingChunkSource(this.manifestFetcher, DefaultSmoothStreamingTrackSelector.newVideoInstance(this.context, true, false), new DefaultHttpDataSource(this.userAgent, null, defaultBandwidthMeter, 8000, 8000, false, this.httpDataSourceCallback), new FormatEvaluator.AdaptiveEvaluator(defaultBandwidthMeter), 30000L), defaultLoadControl, 13107200, mainHandler, this.player, 0), MediaCodecSelector.DEFAULT, 1, 5000L, streamingDrmSessionManager, true, mainHandler, this.player, 50);
            MediaCodecAudioTrackRenderer mediaCodecAudioTrackRenderer = new MediaCodecAudioTrackRenderer((SampleSource) new ChunkSampleSource(new SmoothStreamingChunkSource(this.manifestFetcher, DefaultSmoothStreamingTrackSelector.newAudioInstance(), new DefaultUriDataSource(this.context, defaultBandwidthMeter, this.userAgent), null, 30000L), defaultLoadControl, 3538944, mainHandler, this.player, 1), MediaCodecSelector.DEFAULT, (DrmSessionManager) streamingDrmSessionManager, true, mainHandler, (MediaCodecAudioTrackRenderer.EventListener) this.player, AudioCapabilities.getCapabilities(this.context), 3);
            TextTrackRenderer textTrackRenderer = new TextTrackRenderer(new ChunkSampleSource(new SmoothStreamingChunkSource(this.manifestFetcher, DefaultSmoothStreamingTrackSelector.newTextInstance(), new DefaultUriDataSource(this.context, defaultBandwidthMeter, this.userAgent), null, 30000L), defaultLoadControl, 131072, mainHandler, this.player, 2), this.player.getMtkPlayerControl(), mainHandler.getLooper(), new SubtitleParser[0]);
            TrackRenderer[] trackRendererArr = new TrackRenderer[6];
            trackRendererArr[0] = mediaCodecVideoTrackRenderer;
            trackRendererArr[1] = mediaCodecAudioTrackRenderer;
            trackRendererArr[2] = textTrackRenderer;
            this.player.onRenderers(trackRendererArr, defaultBandwidthMeter);
        }

        public void setHttpDataSourceCallback(DefaultHttpDataSource.Callback callback) {
            this.httpDataSourceCallback = callback;
        }
    }

    @Override // com.google.android.exoplayer.demo.player.DemoPlayer.RendererBuilder
    public void setHttpDataSourceCallback(DefaultHttpDataSource.Callback callback) {
        this.httpDataSourceCallback = callback;
    }

    public SmoothStreamingRendererBuilder(Context context, String str, String str2, MediaDrmCallback mediaDrmCallback, String str3, String str4) {
        this(context, str, str2, mediaDrmCallback);
        this.drmHeader = str3;
        this.customData = str4;
    }
}
