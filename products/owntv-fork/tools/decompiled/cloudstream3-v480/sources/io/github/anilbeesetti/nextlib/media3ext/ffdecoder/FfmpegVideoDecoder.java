package io.github.anilbeesetti.nextlib.media3ext.ffdecoder;

import android.util.Log;
import android.view.Surface;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.DecoderOutputBuffer;
import androidx.media3.decoder.SimpleDecoder;
import androidx.media3.decoder.VideoDecoderOutputBuffer;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: loaded from: classes7.dex */
final class FfmpegVideoDecoder extends SimpleDecoder<DecoderInputBuffer, VideoDecoderOutputBuffer, FfmpegDecoderException> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "FfmpegVideoDecoder";
    private static final int VIDEO_DECODER_ERROR_INVALID_DATA = -1;
    private static final int VIDEO_DECODER_ERROR_OTHER = -2;
    private static final int VIDEO_DECODER_ERROR_READ_FRAME = -3;
    private static final int VIDEO_DECODER_SUCCESS = 0;
    private final String codecName;
    private final byte[] extraData;
    private Format format;
    private long nativeContext;
    private volatile int outputMode;

    private native long ffmpegInitialize(String str, byte[] bArr, int i);

    private native int ffmpegReceiveFrame(long j, int i, VideoDecoderOutputBuffer videoDecoderOutputBuffer, boolean z);

    private native void ffmpegRelease(long j);

    private native int ffmpegRenderFrame(long j, Surface surface, VideoDecoderOutputBuffer videoDecoderOutputBuffer, int i, int i2);

    private native long ffmpegReset(long j);

    private native int ffmpegSendPacket(long j, ByteBuffer byteBuffer, int i, long j2);

    public FfmpegVideoDecoder(int i, int i2, int i3, int i4, Format format) throws FfmpegDecoderException {
        super(new DecoderInputBuffer[i], new VideoDecoderOutputBuffer[i2]);
        if (!FfmpegLibrary.isAvailable()) {
            throw new FfmpegDecoderException("Failed to load decoder native library.");
        }
        String str = (String) Assertions.checkNotNull(FfmpegLibrary.getCodecName(format.sampleMimeType));
        this.codecName = str;
        byte[] extraData = getExtraData(format.sampleMimeType, format.initializationData);
        this.extraData = extraData;
        this.format = format;
        long jFfmpegInitialize = ffmpegInitialize(str, extraData, i4);
        this.nativeContext = jFfmpegInitialize;
        if (jFfmpegInitialize == 0) {
            throw new FfmpegDecoderException("Failed to initialize decoder.");
        }
        setInitialInputBufferSize(i3);
    }

    private static byte[] getExtraData(String str, List<byte[]> list) {
        if (list.isEmpty()) {
            return null;
        }
        str.hashCode();
        if (str.equals("video/hevc")) {
            return list.get(0);
        }
        if (!str.equals("video/avc")) {
            return null;
        }
        byte[] bArr = list.get(0);
        byte[] bArr2 = list.get(1);
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    @Override // androidx.media3.decoder.Decoder
    public String getName() {
        return "ffmpeg" + FfmpegLibrary.getVersion() + "-" + this.codecName;
    }

    public void setOutputMode(int i) {
        this.outputMode = i;
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    protected DecoderInputBuffer createInputBuffer() {
        return new DecoderInputBuffer(2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.decoder.SimpleDecoder
    public VideoDecoderOutputBuffer createOutputBuffer() {
        return new VideoDecoderOutputBuffer(new DecoderOutputBuffer.Owner() { // from class: io.github.anilbeesetti.nextlib.media3ext.ffdecoder.FfmpegVideoDecoder$$ExternalSyntheticLambda0
            @Override // androidx.media3.decoder.DecoderOutputBuffer.Owner
            public final void releaseOutputBuffer(DecoderOutputBuffer decoderOutputBuffer) {
                this.f$0.releaseOutputBuffer((VideoDecoderOutputBuffer) decoderOutputBuffer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.decoder.SimpleDecoder
    public FfmpegDecoderException createUnexpectedDecodeException(Throwable th) {
        return new FfmpegDecoderException("Unexpected decode error", th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.decoder.SimpleDecoder
    public FfmpegDecoderException decode(DecoderInputBuffer decoderInputBuffer, VideoDecoderOutputBuffer videoDecoderOutputBuffer, boolean z) {
        if (z) {
            long jFfmpegReset = ffmpegReset(this.nativeContext);
            this.nativeContext = jFfmpegReset;
            if (jFfmpegReset == 0) {
                return new FfmpegDecoderException("Error resetting (see logcat).");
            }
        }
        ByteBuffer byteBuffer = (ByteBuffer) Util.castNonNull(decoderInputBuffer.data);
        int iFfmpegSendPacket = ffmpegSendPacket(this.nativeContext, byteBuffer, byteBuffer.limit(), decoderInputBuffer.timeUs);
        if (iFfmpegSendPacket == -1) {
            videoDecoderOutputBuffer.shouldBeSkipped = true;
            return null;
        }
        if (iFfmpegSendPacket == -3) {
            Log.d(TAG, "VIDEO_DECODER_ERROR_READ_FRAME: timeUs=" + decoderInputBuffer.timeUs);
        } else if (iFfmpegSendPacket == -2) {
            return new FfmpegDecoderException("ffmpegDecode error: (see logcat)");
        }
        boolean zIsAtLeastOutputStartTimeUs = isAtLeastOutputStartTimeUs(decoderInputBuffer.timeUs);
        int iFfmpegReceiveFrame = ffmpegReceiveFrame(this.nativeContext, this.outputMode, videoDecoderOutputBuffer, !zIsAtLeastOutputStartTimeUs);
        if (iFfmpegReceiveFrame == -2) {
            return new FfmpegDecoderException("ffmpegDecode error: (see logcat)");
        }
        if (iFfmpegReceiveFrame == -1) {
            videoDecoderOutputBuffer.shouldBeSkipped = true;
        }
        if (zIsAtLeastOutputStartTimeUs) {
            videoDecoderOutputBuffer.format = decoderInputBuffer.format;
        }
        return null;
    }

    @Override // androidx.media3.decoder.SimpleDecoder, androidx.media3.decoder.Decoder
    public void release() {
        super.release();
        ffmpegRelease(this.nativeContext);
        this.nativeContext = 0L;
    }

    public void renderToSurface(VideoDecoderOutputBuffer videoDecoderOutputBuffer, Surface surface) throws FfmpegDecoderException {
        if (videoDecoderOutputBuffer.mode != 1) {
            throw new FfmpegDecoderException("Invalid output mode.");
        }
        if (ffmpegRenderFrame(this.nativeContext, surface, videoDecoderOutputBuffer, videoDecoderOutputBuffer.width, videoDecoderOutputBuffer.height) == -2) {
            throw new FfmpegDecoderException("Buffer render error: ");
        }
    }
}
