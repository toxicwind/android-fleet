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
import com.google.android.exoplayer.drm.DrmSessionManager;
import com.google.android.exoplayer.hls.DefaultHlsTrackSelector;
import com.google.android.exoplayer.hls.HlsChunkSource;
import com.google.android.exoplayer.hls.HlsMasterPlaylist;
import com.google.android.exoplayer.hls.HlsPlaylist;
import com.google.android.exoplayer.hls.HlsPlaylistParser;
import com.google.android.exoplayer.hls.HlsSampleSource;
import com.google.android.exoplayer.hls.PtsTimestampAdjusterProvider;
import com.google.android.exoplayer.metadata.MetadataTrackRenderer;
import com.google.android.exoplayer.metadata.id3.Id3Parser;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.text.TextTrackRenderer;
import com.google.android.exoplayer.text.eia608.Eia608TrackRenderer;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;
import com.google.android.exoplayer.util.ManifestFetcher;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class HlsRendererBuilder implements DemoPlayer.RendererBuilder {
    private final Context context;
    private AsyncRendererBuilder currentAsyncBuilder;
    private DefaultHttpDataSource.Callback httpDataSourceCallback;
    private final String url;
    private final String userAgent;

    @Override // com.google.android.exoplayer.demo.player.DemoPlayer.RendererBuilder
    public boolean sourceIsLocal() {
        return false;
    }

    public HlsRendererBuilder(Context context, String str, String str2) {
        this.context = context;
        this.userAgent = str;
        this.url = str2;
    }

    @Override // com.google.android.exoplayer.demo.player.DemoPlayer.RendererBuilder
    public void buildRenderers(DemoPlayer demoPlayer) {
        AsyncRendererBuilder asyncRendererBuilder = new AsyncRendererBuilder(this.context, this.userAgent, this.url, demoPlayer);
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

    private static final class AsyncRendererBuilder implements ManifestFetcher.ManifestCallback<HlsPlaylist> {
        private boolean canceled;
        private final Context context;
        private DefaultHttpDataSource.Callback httpDataSourceCallback;
        private final DemoPlayer player;
        private final ManifestFetcher<HlsPlaylist> playlistFetcher;
        private final String userAgent;

        public AsyncRendererBuilder(Context context, String str, String str2, DemoPlayer demoPlayer) {
            this.context = context;
            this.userAgent = str;
            this.player = demoPlayer;
            this.playlistFetcher = new ManifestFetcher<>(str2, new DefaultUriDataSource(context, str), new HlsPlaylistParser());
        }

        public void init() {
            this.playlistFetcher.singleLoad(this.player.getMainHandler().getLooper(), this);
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
        public void onSingleManifest(HlsPlaylist hlsPlaylist) {
            boolean z;
            boolean z2;
            char c;
            DefaultBandwidthMeter defaultBandwidthMeter;
            HlsSampleSource hlsSampleSource;
            int i;
            MediaCodecAudioTrackRenderer mediaCodecAudioTrackRenderer;
            int i2;
            TrackRenderer eia608TrackRenderer;
            if (this.canceled) {
                return;
            }
            Handler mainHandler = this.player.getMainHandler();
            DefaultLoadControl defaultLoadControl = new DefaultLoadControl(new DefaultAllocator(MtkTvScanDvbsBase.SB_DVBS_CONFIG_BGM_SCAN));
            DefaultBandwidthMeter defaultBandwidthMeter2 = new DefaultBandwidthMeter();
            PtsTimestampAdjusterProvider ptsTimestampAdjusterProvider = new PtsTimestampAdjusterProvider();
            if (hlsPlaylist instanceof HlsMasterPlaylist) {
                HlsMasterPlaylist hlsMasterPlaylist = (HlsMasterPlaylist) hlsPlaylist;
                boolean z3 = !hlsMasterPlaylist.subtitles.isEmpty();
                z = !hlsMasterPlaylist.audios.isEmpty();
                z2 = z3;
            } else {
                z = false;
                z2 = false;
            }
            HlsSampleSource hlsSampleSource2 = new HlsSampleSource(new HlsChunkSource(true, new DefaultHttpDataSource(this.userAgent, null, defaultBandwidthMeter2, 8000, 8000, false, this.httpDataSourceCallback), hlsPlaylist, DefaultHlsTrackSelector.newDefaultInstance(this.context), defaultBandwidthMeter2, ptsTimestampAdjusterProvider), defaultLoadControl, 16646144, mainHandler, this.player, 0);
            MediaCodecVideoTrackRenderer mediaCodecVideoTrackRenderer = new MediaCodecVideoTrackRenderer(this.context, hlsSampleSource2, MediaCodecSelector.DEFAULT, 1, 5000L, mainHandler, this.player, 50);
            MetadataTrackRenderer metadataTrackRenderer = new MetadataTrackRenderer(hlsSampleSource2, new Id3Parser(), this.player, mainHandler.getLooper());
            if (z) {
                defaultBandwidthMeter = defaultBandwidthMeter2;
                hlsSampleSource = hlsSampleSource2;
                i = 0;
                c = 2;
                mediaCodecAudioTrackRenderer = new MediaCodecAudioTrackRenderer(new SampleSource[]{hlsSampleSource, new HlsSampleSource(new HlsChunkSource(false, new DefaultUriDataSource(this.context, defaultBandwidthMeter2, this.userAgent), hlsPlaylist, DefaultHlsTrackSelector.newAudioInstance(), defaultBandwidthMeter, ptsTimestampAdjusterProvider), defaultLoadControl, 3538944, mainHandler, this.player, 1)}, MediaCodecSelector.DEFAULT, (DrmSessionManager) null, true, this.player.getMainHandler(), (MediaCodecAudioTrackRenderer.EventListener) this.player, AudioCapabilities.getCapabilities(this.context), 3);
            } else {
                c = 2;
                defaultBandwidthMeter = defaultBandwidthMeter2;
                hlsSampleSource = hlsSampleSource2;
                i = 0;
                mediaCodecAudioTrackRenderer = new MediaCodecAudioTrackRenderer((SampleSource) hlsSampleSource, MediaCodecSelector.DEFAULT, (DrmSessionManager) null, true, this.player.getMainHandler(), (MediaCodecAudioTrackRenderer.EventListener) this.player, AudioCapabilities.getCapabilities(this.context), 3);
            }
            MediaCodecAudioTrackRenderer mediaCodecAudioTrackRenderer2 = mediaCodecAudioTrackRenderer;
            if (z2) {
                i2 = i;
                eia608TrackRenderer = new TextTrackRenderer(new HlsSampleSource(new HlsChunkSource(false, new DefaultUriDataSource(this.context, defaultBandwidthMeter, this.userAgent), hlsPlaylist, DefaultHlsTrackSelector.newSubtitleInstance(), defaultBandwidthMeter, ptsTimestampAdjusterProvider), defaultLoadControl, 131072, mainHandler, this.player, 2), this.player.getMtkPlayerControl(), mainHandler.getLooper(), new SubtitleParser[i2]);
            } else {
                i2 = i;
                eia608TrackRenderer = new Eia608TrackRenderer(hlsSampleSource, this.player.getMtkPlayerControl(), mainHandler.getLooper());
            }
            TrackRenderer[] trackRendererArr = new TrackRenderer[6];
            trackRendererArr[i2] = mediaCodecVideoTrackRenderer;
            trackRendererArr[1] = mediaCodecAudioTrackRenderer2;
            trackRendererArr[3] = metadataTrackRenderer;
            trackRendererArr[c] = eia608TrackRenderer;
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
}
