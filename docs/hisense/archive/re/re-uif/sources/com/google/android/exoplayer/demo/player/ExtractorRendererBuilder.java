package com.google.android.exoplayer.demo.player;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecSelector;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.SampleSource;
import com.google.android.exoplayer.TrackRenderer;
import com.google.android.exoplayer.audio.AudioCapabilities;
import com.google.android.exoplayer.drm.DrmSessionManager;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorSampleSource;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.text.TextTrackRenderer;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;

/* JADX INFO: loaded from: classes.dex */
public class ExtractorRendererBuilder implements DemoPlayer.RendererBuilder {
    private final Context context;
    private DefaultHttpDataSource.Callback httpDataSourceCallback;
    private final Uri uri;
    private final String userAgent;

    @Override // com.google.android.exoplayer.demo.player.DemoPlayer.RendererBuilder
    public void cancel() {
    }

    public ExtractorRendererBuilder(Context context, String str, Uri uri) {
        this.context = context;
        this.userAgent = str;
        this.uri = uri;
    }

    @Override // com.google.android.exoplayer.demo.player.DemoPlayer.RendererBuilder
    public void buildRenderers(DemoPlayer demoPlayer) {
        DataSource defaultHttpDataSource;
        DefaultAllocator defaultAllocator = new DefaultAllocator(MtkTvScanDvbsBase.SB_DVBS_CONFIG_BGM_SCAN);
        Handler mainHandler = demoPlayer.getMainHandler();
        DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter(demoPlayer.getMainHandler(), null);
        if ("http".equals(this.uri.getScheme()) || "https".equals(this.uri.getScheme())) {
            defaultHttpDataSource = new DefaultHttpDataSource(this.userAgent, null, null, 8000, 8000, false, this.httpDataSourceCallback);
        } else {
            defaultHttpDataSource = new DefaultUriDataSource(this.context, defaultBandwidthMeter, this.userAgent);
        }
        ExtractorSampleSource extractorSampleSource = new ExtractorSampleSource(this.uri, defaultHttpDataSource, defaultAllocator, 16777216, mainHandler, demoPlayer, 0, new Extractor[0]);
        MediaCodecVideoTrackRenderer mediaCodecVideoTrackRenderer = new MediaCodecVideoTrackRenderer(this.context, extractorSampleSource, MediaCodecSelector.DEFAULT, 1, 5000L, mainHandler, demoPlayer, 50);
        MediaCodecAudioTrackRenderer mediaCodecAudioTrackRenderer = new MediaCodecAudioTrackRenderer((SampleSource) extractorSampleSource, MediaCodecSelector.DEFAULT, (DrmSessionManager) null, true, mainHandler, (MediaCodecAudioTrackRenderer.EventListener) demoPlayer, AudioCapabilities.getCapabilities(this.context), 3);
        TextTrackRenderer textTrackRenderer = new TextTrackRenderer(extractorSampleSource, demoPlayer, mainHandler.getLooper(), new SubtitleParser[0]);
        TrackRenderer[] trackRendererArr = new TrackRenderer[6];
        trackRendererArr[0] = mediaCodecVideoTrackRenderer;
        trackRendererArr[1] = mediaCodecAudioTrackRenderer;
        trackRendererArr[2] = textTrackRenderer;
        demoPlayer.onRenderers(trackRendererArr, defaultBandwidthMeter);
    }

    @Override // com.google.android.exoplayer.demo.player.DemoPlayer.RendererBuilder
    public void setHttpDataSourceCallback(DefaultHttpDataSource.Callback callback) {
        this.httpDataSourceCallback = callback;
    }

    @Override // com.google.android.exoplayer.demo.player.DemoPlayer.RendererBuilder
    public boolean sourceIsLocal() {
        return ("http".equals(this.uri.getScheme()) || "https".equals(this.uri.getScheme())) ? false : true;
    }
}
