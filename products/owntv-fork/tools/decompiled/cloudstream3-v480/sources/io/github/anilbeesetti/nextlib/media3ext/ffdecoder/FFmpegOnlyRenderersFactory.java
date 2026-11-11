package io.github.anilbeesetti.nextlib.media3ext.ffdecoder;

import android.content.Context;
import android.os.Handler;
import androidx.media3.exoplayer.DefaultRenderersFactory;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes7.dex */
public class FFmpegOnlyRenderersFactory extends DefaultRenderersFactory {
    public FFmpegOnlyRenderersFactory(Context context) {
        super(context);
    }

    @Override // androidx.media3.exoplayer.DefaultRenderersFactory
    protected void buildVideoRenderers(Context context, int i, MediaCodecSelector mediaCodecSelector, boolean z, Handler handler, VideoRendererEventListener videoRendererEventListener, long j, ArrayList<Renderer> arrayList) {
        arrayList.add(new FfmpegVideoRenderer(j, handler, videoRendererEventListener, 50));
    }

    @Override // androidx.media3.exoplayer.DefaultRenderersFactory
    protected void buildAudioRenderers(Context context, int i, MediaCodecSelector mediaCodecSelector, boolean z, AudioSink audioSink, Handler handler, AudioRendererEventListener audioRendererEventListener, ArrayList<Renderer> arrayList) {
        arrayList.add(new FfmpegAudioRenderer(handler, audioRendererEventListener, audioSink));
    }
}
