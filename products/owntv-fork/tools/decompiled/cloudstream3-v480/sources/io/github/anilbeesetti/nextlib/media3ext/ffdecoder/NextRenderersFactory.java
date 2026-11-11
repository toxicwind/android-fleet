package io.github.anilbeesetti.nextlib.media3ext.ffdecoder;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.util.Log;
import androidx.media3.exoplayer.DefaultRenderersFactory;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.text.TextOutput;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import io.github.anilbeesetti.nextlib.media3ext.renderer.NextTextRenderer;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NextRenderersFactory.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000 !2\u00020\u0001:\u0001!B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005JX\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017H\u0014JX\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017H\u0014J6\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0014¨\u0006\""}, d2 = {"Lio/github/anilbeesetti/nextlib/media3ext/ffdecoder/NextRenderersFactory;", "Landroidx/media3/exoplayer/DefaultRenderersFactory;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "buildAudioRenderers", "", "extensionRendererMode", "", "mediaCodecSelector", "Landroidx/media3/exoplayer/mediacodec/MediaCodecSelector;", "enableDecoderFallback", "", "audioSink", "Landroidx/media3/exoplayer/audio/AudioSink;", "eventHandler", "Landroid/os/Handler;", "eventListener", "Landroidx/media3/exoplayer/audio/AudioRendererEventListener;", "out", "Ljava/util/ArrayList;", "Landroidx/media3/exoplayer/Renderer;", "Lkotlin/collections/ArrayList;", "buildVideoRenderers", "Landroidx/media3/exoplayer/video/VideoRendererEventListener;", "allowedVideoJoiningTimeMs", "", "buildTextRenderers", "output", "Landroidx/media3/exoplayer/text/TextOutput;", "outputLooper", "Landroid/os/Looper;", "Companion", "media3ext"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class NextRenderersFactory extends DefaultRenderersFactory {
    public static final String TAG = "NextRenderersFactory";

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NextRenderersFactory(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // androidx.media3.exoplayer.DefaultRenderersFactory
    protected void buildAudioRenderers(Context context, int extensionRendererMode, MediaCodecSelector mediaCodecSelector, boolean enableDecoderFallback, AudioSink audioSink, Handler eventHandler, AudioRendererEventListener eventListener, ArrayList<Renderer> out) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaCodecSelector, "mediaCodecSelector");
        Intrinsics.checkNotNullParameter(audioSink, "audioSink");
        Intrinsics.checkNotNullParameter(eventHandler, "eventHandler");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        Intrinsics.checkNotNullParameter(out, "out");
        super.buildAudioRenderers(context, extensionRendererMode, mediaCodecSelector, enableDecoderFallback, audioSink, eventHandler, eventListener, out);
        if (extensionRendererMode == 0) {
            return;
        }
        int size = out.size();
        if (extensionRendererMode == 2) {
            size--;
        }
        try {
            out.add(size, new FfmpegAudioRenderer(eventHandler, eventListener, audioSink));
            Log.i(TAG, "Loaded FfmpegAudioRenderer.");
        } catch (Exception e) {
            throw new RuntimeException("Error instantiating Ffmpeg extension", e);
        }
    }

    @Override // androidx.media3.exoplayer.DefaultRenderersFactory
    protected void buildVideoRenderers(Context context, int extensionRendererMode, MediaCodecSelector mediaCodecSelector, boolean enableDecoderFallback, Handler eventHandler, VideoRendererEventListener eventListener, long allowedVideoJoiningTimeMs, ArrayList<Renderer> out) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaCodecSelector, "mediaCodecSelector");
        Intrinsics.checkNotNullParameter(eventHandler, "eventHandler");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        Intrinsics.checkNotNullParameter(out, "out");
        super.buildVideoRenderers(context, extensionRendererMode, mediaCodecSelector, enableDecoderFallback, eventHandler, eventListener, allowedVideoJoiningTimeMs, out);
        if (extensionRendererMode == 0) {
            return;
        }
        int size = out.size();
        if (extensionRendererMode == 2) {
            size--;
        }
        try {
            out.add(size, new FfmpegVideoRenderer(allowedVideoJoiningTimeMs, eventHandler, eventListener, 50));
            Log.i(TAG, "Loaded FfmpegVideoRenderer.");
        } catch (Exception e) {
            throw new RuntimeException("Error instantiating Ffmpeg extension", e);
        }
    }

    @Override // androidx.media3.exoplayer.DefaultRenderersFactory
    protected void buildTextRenderers(Context context, TextOutput output, Looper outputLooper, int extensionRendererMode, ArrayList<Renderer> out) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(outputLooper, "outputLooper");
        Intrinsics.checkNotNullParameter(out, "out");
        out.add(new NextTextRenderer(output, outputLooper, null, null, 12, null));
    }
}
