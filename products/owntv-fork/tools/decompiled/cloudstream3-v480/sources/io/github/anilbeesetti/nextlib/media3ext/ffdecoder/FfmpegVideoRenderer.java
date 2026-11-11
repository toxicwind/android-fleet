package io.github.anilbeesetti.nextlib.media3ext.ffdecoder;

import android.os.Handler;
import android.view.Surface;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.decoder.Decoder;
import androidx.media3.decoder.DecoderException;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.VideoDecoderOutputBuffer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.video.DecoderVideoRenderer;
import androidx.media3.exoplayer.video.VideoRendererEventListener;

/* JADX INFO: loaded from: classes7.dex */
public final class FfmpegVideoRenderer extends DecoderVideoRenderer {
    private static final int DEFAULT_INPUT_BUFFER_SIZE = ((Util.ceilDivide(1280, 64) * Util.ceilDivide(720, 64)) * 6144) / 2;
    private static final int DEFAULT_NUM_OF_INPUT_BUFFERS = 4;
    private static final int DEFAULT_NUM_OF_OUTPUT_BUFFERS = 4;
    private static final String TAG = "FfmpegVideoRenderer";
    private FfmpegVideoDecoder decoder;
    private final int numInputBuffers;
    private final int numOutputBuffers;
    private final int threads;

    public FfmpegVideoRenderer(long j, Handler handler, VideoRendererEventListener videoRendererEventListener, int i) {
        this(j, handler, videoRendererEventListener, i, Runtime.getRuntime().availableProcessors(), 4, 4);
    }

    public FfmpegVideoRenderer(long j, Handler handler, VideoRendererEventListener videoRendererEventListener, int i, int i2, int i3, int i4) {
        super(j, handler, videoRendererEventListener, i);
        this.threads = i2;
        this.numInputBuffers = i3;
        this.numOutputBuffers = i4;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public String getName() {
        return TAG;
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public final int supportsFormat(Format format) {
        String str = (String) Assertions.checkNotNull(format.sampleMimeType);
        if (!FfmpegLibrary.isAvailable() || !MimeTypes.isVideo(str)) {
            return 0;
        }
        if (!FfmpegLibrary.supportsFormat(format.sampleMimeType)) {
            return RendererCapabilities.CC.create(1);
        }
        if (format.drmInitData != null) {
            return RendererCapabilities.CC.create(2);
        }
        return RendererCapabilities.CC.create(4, 16, 0);
    }

    @Override // androidx.media3.exoplayer.video.DecoderVideoRenderer
    protected void renderOutputBufferToSurface(VideoDecoderOutputBuffer videoDecoderOutputBuffer, Surface surface) throws FfmpegDecoderException {
        FfmpegVideoDecoder ffmpegVideoDecoder = this.decoder;
        if (ffmpegVideoDecoder == null) {
            throw new FfmpegDecoderException("Failed to render output buffer to surface: decoder is not initialized.");
        }
        ffmpegVideoDecoder.renderToSurface(videoDecoderOutputBuffer, surface);
        videoDecoderOutputBuffer.release();
    }

    @Override // androidx.media3.exoplayer.video.DecoderVideoRenderer
    protected Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> createDecoder(Format format, CryptoConfig cryptoConfig) throws DecoderException {
        TraceUtil.beginSection("createFfmpegVideoDecoder");
        FfmpegVideoDecoder ffmpegVideoDecoder = new FfmpegVideoDecoder(this.numInputBuffers, this.numOutputBuffers, format.maxInputSize != -1 ? format.maxInputSize : DEFAULT_INPUT_BUFFER_SIZE, this.threads, format);
        this.decoder = ffmpegVideoDecoder;
        TraceUtil.endSection();
        return ffmpegVideoDecoder;
    }

    @Override // androidx.media3.exoplayer.video.DecoderVideoRenderer
    protected void setDecoderOutputMode(int i) {
        FfmpegVideoDecoder ffmpegVideoDecoder = this.decoder;
        if (ffmpegVideoDecoder != null) {
            ffmpegVideoDecoder.setOutputMode(i);
        }
    }
}
