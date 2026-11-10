package com.google.android.exoplayer.util;

import com.google.android.exoplayer.MediaFormat;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class Ac3Util {
    private static final int[] BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD = {1, 2, 3, 6};
    private static final int[] SAMPLE_RATE_BY_FSCOD = {48000, 44100, 32000};
    private static final int[] SAMPLE_RATE_BY_FSCOD2 = {24000, 22050, 16000};
    private static final int[] CHANNEL_COUNT_BY_ACMOD = {2, 1, 2, 3, 3, 4, 4, 5};
    private static final int[] BITRATE_BY_HALF_FRMSIZECOD = {32, 40, 48, 56, 64, 80, 96, 112, 128, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_NOT_SUPPORTED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_MARLIN, 256, 320, 384, 448, 512, 576, 640};
    private static final int[] SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1 = {69, 87, 104, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_VIDEO_PRESENT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_ON, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_CLEAR_EOF, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_VIDEO_STREAM_CTR_NOT_SUPPORT, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};

    public static int getAc3SyncframeAudioSampleCount() {
        return 1536;
    }

    public static MediaFormat parseAc3AnnexFFormat(ParsableByteArray parsableByteArray, String str, long j, String str2) {
        int i = SAMPLE_RATE_BY_FSCOD[(parsableByteArray.readUnsignedByte() & MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED) >> 6];
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i2 = CHANNEL_COUNT_BY_ACMOD[(unsignedByte & 56) >> 3];
        if ((unsignedByte & 4) != 0) {
            i2++;
        }
        return MediaFormat.createAudioFormat(str, "audio/ac3", -1, -1, j, i2, i, null, str2);
    }

    public static MediaFormat parseEAc3AnnexFFormat(ParsableByteArray parsableByteArray, String str, long j, String str2) {
        parsableByteArray.skipBytes(2);
        int i = SAMPLE_RATE_BY_FSCOD[(parsableByteArray.readUnsignedByte() & MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED) >> 6];
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i2 = CHANNEL_COUNT_BY_ACMOD[(unsignedByte & 14) >> 1];
        if ((unsignedByte & 1) != 0) {
            i2++;
        }
        return MediaFormat.createAudioFormat(str, "audio/eac3", -1, -1, j, i2, i, null, str2);
    }

    public static MediaFormat parseAc3SyncframeFormat(ParsableBitArray parsableBitArray, String str, long j, String str2) {
        parsableBitArray.skipBits(32);
        int bits = parsableBitArray.readBits(2);
        parsableBitArray.skipBits(14);
        int bits2 = parsableBitArray.readBits(3);
        if ((bits2 & 1) != 0 && bits2 != 1) {
            parsableBitArray.skipBits(2);
        }
        if ((bits2 & 4) != 0) {
            parsableBitArray.skipBits(2);
        }
        if (bits2 == 2) {
            parsableBitArray.skipBits(2);
        }
        return MediaFormat.createAudioFormat(str, "audio/ac3", -1, -1, j, CHANNEL_COUNT_BY_ACMOD[bits2] + (parsableBitArray.readBit() ? 1 : 0), SAMPLE_RATE_BY_FSCOD[bits], null, str2);
    }

    public static MediaFormat parseEac3SyncframeFormat(ParsableBitArray parsableBitArray, String str, long j, String str2) {
        int i;
        parsableBitArray.skipBits(32);
        int bits = parsableBitArray.readBits(2);
        if (bits == 3) {
            i = SAMPLE_RATE_BY_FSCOD2[parsableBitArray.readBits(2)];
        } else {
            parsableBitArray.skipBits(2);
            i = SAMPLE_RATE_BY_FSCOD[bits];
        }
        return MediaFormat.createAudioFormat(str, "audio/eac3", -1, -1, j, CHANNEL_COUNT_BY_ACMOD[parsableBitArray.readBits(3)] + (parsableBitArray.readBit() ? 1 : 0), i, null, str2);
    }

    public static int parseAc3SyncframeSize(byte[] bArr) {
        return getAc3SyncframeSize((bArr[4] & 192) >> 6, bArr[4] & 63);
    }

    public static int parseEAc3SyncframeSize(byte[] bArr) {
        return (((bArr[2] & 7) << 8) + (bArr[3] & 255) + 1) * 2;
    }

    public static int parseEAc3SyncframeAudioSampleCount(byte[] bArr) {
        return (((bArr[4] & 192) >> 6) != 3 ? BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[(bArr[4] & 48) >> 4] : 6) * 256;
    }

    public static int parseEAc3SyncframeAudioSampleCount(ByteBuffer byteBuffer) {
        return (((byteBuffer.get(byteBuffer.position() + 4) & 192) >> 6) != 3 ? BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[(byteBuffer.get(byteBuffer.position() + 4) & 48) >> 4] : 6) * 256;
    }

    private static int getAc3SyncframeSize(int i, int i2) {
        int i3 = SAMPLE_RATE_BY_FSCOD[i];
        if (i3 == 44100) {
            return (SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1[i2 / 2] + (i2 % 2)) * 2;
        }
        int i4 = BITRATE_BY_HALF_FRMSIZECOD[i2 / 2];
        return i3 == 32000 ? i4 * 6 : i4 * 4;
    }
}
