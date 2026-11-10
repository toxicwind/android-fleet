package com.google.android.exoplayer.demo.player;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecSelector;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.SampleSource;
import com.google.android.exoplayer.SingleSampleSource;
import com.google.android.exoplayer.TrackRenderer;
import com.google.android.exoplayer.TsSiTableTrackRenderer;
import com.google.android.exoplayer.audio.AudioCapabilities;
import com.google.android.exoplayer.demo.DemoUtil;
import com.google.android.exoplayer.drm.DrmSessionManager;
import com.google.android.exoplayer.nbfss.NonBlockingFrameworkSampleSource;
import com.google.android.exoplayer.text.TextTrackRenderer;
import com.google.android.exoplayer.text.dvb.DvbParser;
import com.google.android.exoplayer.text.eia608.Eia608TrackRenderer;
import com.google.android.exoplayer.text.pgs.external.ExtPgsParser;
import com.google.android.exoplayer.text.pgs.internal.IntPgsParser;
import com.google.android.exoplayer.text.smi.SmiParser;
import com.google.android.exoplayer.text.srt.InternalSrtParser;
import com.google.android.exoplayer.text.srt.SrtParser;
import com.google.android.exoplayer.text.ssa.InternalSsaParser;
import com.google.android.exoplayer.text.ssa.SsaParser;
import com.google.android.exoplayer.text.sub.SubParser;
import com.google.android.exoplayer.text.ttx.TeletextParser;
import com.google.android.exoplayer.text.tx3g.Tx3gParser;
import com.google.android.exoplayer.text.txt.TxtParser;
import com.google.android.exoplayer.text.vobsub.InternalVobSubParser;
import com.google.android.exoplayer.text.vobsub.VobSubParser;
import com.google.android.exoplayer.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer.upstream.DefaultHttpFrameworkDataSource;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;
import com.google.android.exoplayer.upstream.FrameworkDataSource;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class DefaultRendererBuilder implements DemoPlayer.RendererBuilder {
    private final Context context;
    private FrameworkDataSource dataSource;
    private DefaultFrameworkDataSource defaultFrameworkDataSource;
    private DefaultHttpFrameworkDataSource defaultHttpFrameworkDataSource;
    private String[] externalSubtitlePaths;
    private final Uri uri;

    class DefaultFrameworkDataSource extends FrameworkDataSource {
        @Override // com.google.android.exoplayer.upstream.FrameworkDataSource
        protected abstract void close();
    }

    @Override // com.google.android.exoplayer.demo.player.DemoPlayer.RendererBuilder
    public void setHttpDataSourceCallback(DefaultHttpDataSource.Callback callback) {
    }

    public DefaultRendererBuilder(Context context, Uri uri) {
        this.context = context;
        this.uri = uri;
    }

    @Override // com.google.android.exoplayer.demo.player.DemoPlayer.RendererBuilder
    public void buildRenderers(DemoPlayer demoPlayer) {
        NonBlockingFrameworkSampleSource nonBlockingFrameworkSampleSource;
        NonBlockingFrameworkSampleSource nonBlockingFrameworkSampleSource2;
        MediaCodecVideoTrackRenderer mediaCodecVideoTrackRenderer;
        MediaCodecAudioTrackRenderer mediaCodecAudioTrackRenderer;
        TextTrackRenderer textTrackRenderer;
        NonBlockingFrameworkSampleSource nonBlockingFrameworkSampleSource3;
        if (this.dataSource == null) {
            if ("http".equals(this.uri.getScheme()) || "https".equals(this.uri.getScheme())) {
                Log.i("DefaultRendererBuilder", "new defaultHttpFrameworkDataSource in");
                try {
                    this.defaultHttpFrameworkDataSource = new DefaultHttpFrameworkDataSource(Util.getUserAgent(this.context, "ExoPlayer"), this.uri);
                    nonBlockingFrameworkSampleSource3 = new NonBlockingFrameworkSampleSource(this.context, this.defaultHttpFrameworkDataSource);
                    Log.i("DefaultRendererBuilder", "new defaultHttpFrameworkDataSource out");
                } catch (IOException unused) {
                    Log.e("DefaultRendererBuilder", "File not found. uri:" + this.uri.getPath());
                    return;
                }
            } else {
                nonBlockingFrameworkSampleSource3 = new NonBlockingFrameworkSampleSource(this.context, this.uri, null);
            }
            nonBlockingFrameworkSampleSource2 = nonBlockingFrameworkSampleSource3;
            nonBlockingFrameworkSampleSource = null;
        } else {
            nonBlockingFrameworkSampleSource = new NonBlockingFrameworkSampleSource(this.context, this.dataSource);
            nonBlockingFrameworkSampleSource2 = null;
        }
        boolean[] renderersUnsupported = demoPlayer.getRenderersUnsupported();
        if (renderersUnsupported[0]) {
            mediaCodecVideoTrackRenderer = null;
        } else {
            mediaCodecVideoTrackRenderer = new MediaCodecVideoTrackRenderer(this.context, this.dataSource != null ? nonBlockingFrameworkSampleSource : nonBlockingFrameworkSampleSource2, MediaCodecSelector.DEFAULT, demoPlayer.getVideoScalingMode(), 5000L, null, true, demoPlayer.getMainHandler(), demoPlayer, 50);
        }
        if (renderersUnsupported[1]) {
            mediaCodecAudioTrackRenderer = null;
        } else {
            mediaCodecAudioTrackRenderer = new MediaCodecAudioTrackRenderer((SampleSource) (this.dataSource != null ? nonBlockingFrameworkSampleSource : nonBlockingFrameworkSampleSource2), MediaCodecSelector.DEFAULT, (DrmSessionManager) null, true, demoPlayer.getMainHandler(), (MediaCodecAudioTrackRenderer.EventListener) demoPlayer, AudioCapabilities.getCapabilities(this.context), 3);
        }
        TsSiTableTrackRenderer tsSiTableTrackRenderer = new TsSiTableTrackRenderer(this.dataSource != null ? nonBlockingFrameworkSampleSource : nonBlockingFrameworkSampleSource2, demoPlayer.getMainHandler(), demoPlayer);
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.dataSource != null ? nonBlockingFrameworkSampleSource : nonBlockingFrameworkSampleSource2);
        String[] strArr = this.externalSubtitlePaths;
        if (strArr != null && strArr.length > 0) {
            for (String str : strArr) {
                String strSubstring = str.substring(str.lastIndexOf("."));
                String subtitleMimeType = DemoUtil.getSubtitleMimeType(strSubstring);
                if (subtitleMimeType == null) {
                    Log.d("DefaultRendererBuilder", "Can not find type = " + strSubstring);
                } else {
                    MediaFormat mediaFormatCreateTextFormat = MediaFormat.createTextFormat(null, subtitleMimeType, 0, -2L, "");
                    Uri uri = Uri.parse(str);
                    Context context = this.context;
                    arrayList.add(new SingleSampleSource(uri, new DefaultUriDataSource(context, Util.getUserAgent(context, "ExoPlayerDemo")), mediaFormatCreateTextFormat));
                }
            }
        }
        SampleSource[] sampleSourceArr = new SampleSource[arrayList.size()];
        arrayList.toArray(sampleSourceArr);
        if (nonBlockingFrameworkSampleSource2 != 0) {
            textTrackRenderer = new TextTrackRenderer(sampleSourceArr, demoPlayer.getMtkPlayerControl(), demoPlayer.getMainHandler().getLooper(), new InternalSsaParser(), new InternalVobSubParser(), new InternalSrtParser(), new DvbParser(), new TeletextParser(), new IntPgsParser(), new ExtPgsParser(), new SmiParser(), new SrtParser(), new SsaParser(), new SubParser(), new TxtParser(), new VobSubParser(this.uri.getPath()), new Tx3gParser());
        } else {
            textTrackRenderer = new TextTrackRenderer(sampleSourceArr, demoPlayer.getMtkPlayerControl(), demoPlayer.getMainHandler().getLooper(), new InternalSsaParser(), new InternalVobSubParser(), new InternalSrtParser(), new DvbParser(), new TeletextParser(), new IntPgsParser(), new ExtPgsParser(), new SmiParser(), new SrtParser(), new SsaParser(), new SubParser(), new TxtParser(), new Tx3gParser());
        }
        Eia608TrackRenderer eia608TrackRenderer = nonBlockingFrameworkSampleSource2 != 0 ? new Eia608TrackRenderer(nonBlockingFrameworkSampleSource2, demoPlayer.getMtkPlayerControl(), demoPlayer.getMainHandler().getLooper()) : null;
        TrackRenderer[] trackRendererArr = new TrackRenderer[6];
        trackRendererArr[0] = mediaCodecVideoTrackRenderer;
        trackRendererArr[1] = mediaCodecAudioTrackRenderer;
        trackRendererArr[4] = tsSiTableTrackRenderer;
        trackRendererArr[2] = textTrackRenderer;
        trackRendererArr[5] = eia608TrackRenderer;
        demoPlayer.onRenderers(trackRendererArr, null, this.dataSource);
    }

    @Override // com.google.android.exoplayer.demo.player.DemoPlayer.RendererBuilder
    public void cancel() {
        DefaultFrameworkDataSource defaultFrameworkDataSource = this.defaultFrameworkDataSource;
        if (defaultFrameworkDataSource != null) {
            defaultFrameworkDataSource.close();
            this.defaultFrameworkDataSource = null;
        }
        DefaultHttpFrameworkDataSource defaultHttpFrameworkDataSource = this.defaultHttpFrameworkDataSource;
        if (defaultHttpFrameworkDataSource != null) {
            defaultHttpFrameworkDataSource.close();
            this.defaultHttpFrameworkDataSource = null;
        }
    }

    public void addExternalSubtitle(String... strArr) {
        this.externalSubtitlePaths = strArr;
    }

    public void setDataSource(FrameworkDataSource frameworkDataSource) {
        this.dataSource = frameworkDataSource;
    }

    @Override // com.google.android.exoplayer.demo.player.DemoPlayer.RendererBuilder
    public boolean sourceIsLocal() {
        return (this.dataSource != null || "http".equals(this.uri.getScheme()) || "https".equals(this.uri.getScheme())) ? false : true;
    }
}
