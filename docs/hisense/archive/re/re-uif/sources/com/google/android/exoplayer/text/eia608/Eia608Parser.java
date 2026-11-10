package com.google.android.exoplayer.text.eia608;

import android.util.Log;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.util.ParsableBitArray;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.util.ArrayList;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public final class Eia608Parser {
    private static final int[] BASIC_CHARACTER_SET = {32, 33, 34, 35, 36, 37, 38, 39, 40, 41, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_NAUTILUS_MARLIN, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, 243, 250, 97, 98, 99, 100, 101, 102, 103, 104, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_DRM_NOT_SUPPORT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_SUBTITLE_FMT_UPDATE, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REACH_VALID_RANGE_START, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REACH_VALID_RANGE_END, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_START, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_END, 111, 112, 113, 114, 115, 116, 117, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_FRAMERATE_NOT_SUPPORT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_NO_CONTENT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RANGE_AB_DONE, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_VIDEO_PRESENT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_VS_BIT_RATE, 231, 247, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_AUDIO_STREAM_CTR_NOT_SUPPORT, 241, 9632};
    private static final int[] SPECIAL_CHARACTER_SET = {MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_CLEAR_EOF, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GET_IFRAME, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_SRV_UNAVAIL, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_DEV_NOT_REG, 8482, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_INTERNAL_ERROR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BAD_TICK, 9834, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_MARLIN, 32, 232, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_PLAYREADY, 234, 238, 244, 251};
    private static final int[] SPECIAL_ES_FR_CHARACTER_SET = {MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_UNKNOWN_SRV_ERR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_ACQUISITION_ERROR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CONN_PMT_UPDATED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_INVALID, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CAD_INVALID, 252, 8216, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STRG_ERROR, 42, 39, 8212, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_ANA_AUTO_DETECTED_AUD_SYS, 8480, 8226, 8220, 8221, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INV_USR_AGENT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INIT_ERROR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_EXPIRED_ERROR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_SERVER_CONNECTION_ERROR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TOKEN_ACQUISITION_ERROR, 235, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CC_IDX_CHANGED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CC_ATTR_CHANGED, 239, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CAM_CARD_REMOVE, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_UNKNOW_DRMTYPE, 249, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_GET_KEY_ERROR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_TIME_UPDATE, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LIC_NOT_ALLOW};
    private static final int[] SPECIAL_PT_DE_CHARACTER_SET = {MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_UNSUPPORTED_VER, 227, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CMPB_QUERY, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CAD_ACQUISITION_ERROR, 236, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_VIDEO_WFD_ERROR, 242, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_FILE_OPER_ERROR, 245, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_REPLAY, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_DESC_ALLOCED, 92, 94, 95, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_URI_FILE_OPENED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_BLOCKED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INV_CONTENT_TYPE, 228, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_INPUT_LOCKED, 246, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_MARLIN_KEY_FORMATERROR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_PIN_ERROR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_CHANNEL_LOCKED, 9474, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INV_SRV_TKN, 229, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INVALID_ARG, 248, 9484, 9488, 9492, 9496};
    private final ParsableBitArray seiBuffer = new ParsableBitArray();
    private final StringBuilder stringBuilder = new StringBuilder();
    private final ArrayList<ClosedCaption> captions = new ArrayList<>();

    Eia608Parser() {
    }

    boolean canParse(String str) {
        Log.d("Eia608Parser", "Eia608 canParse" + str.equals("application/eia-608"));
        return str.equals("application/eia-608");
    }

    ClosedCaptionList parse(SampleHolder sampleHolder) {
        if (sampleHolder.size < 10) {
            return null;
        }
        this.captions.clear();
        this.stringBuilder.setLength(0);
        this.seiBuffer.reset(sampleHolder.data.array());
        this.seiBuffer.skipBits(67);
        int bits = this.seiBuffer.readBits(5);
        this.seiBuffer.skipBits(8);
        for (int i = 0; i < bits; i++) {
            this.seiBuffer.skipBits(5);
            if (!this.seiBuffer.readBit()) {
                this.seiBuffer.skipBits(18);
            } else if (this.seiBuffer.readBits(2) != 0) {
                this.seiBuffer.skipBits(16);
            } else {
                this.seiBuffer.skipBits(1);
                byte bits2 = (byte) this.seiBuffer.readBits(7);
                this.seiBuffer.skipBits(1);
                byte bits3 = (byte) this.seiBuffer.readBits(7);
                if (bits2 != 0 || bits3 != 0) {
                    if ((bits2 == 17 || bits2 == 25) && (bits3 & 112) == 48) {
                        this.stringBuilder.append(getSpecialChar(bits3));
                    } else if ((bits2 == 18 || bits2 == 26) && (bits3 & 96) == 32) {
                        backspace();
                        this.stringBuilder.append(getExtendedEsFrChar(bits3));
                    } else if ((bits2 == 19 || bits2 == 27) && (bits3 & 96) == 32) {
                        backspace();
                        this.stringBuilder.append(getExtendedPtDeChar(bits3));
                    } else if (bits2 < 32) {
                        addCtrl(bits2, bits3);
                    } else {
                        this.stringBuilder.append(getChar(bits2));
                        if (bits3 >= 32) {
                            this.stringBuilder.append(getChar(bits3));
                        }
                    }
                }
            }
        }
        addBufferedText();
        if (this.captions.isEmpty()) {
            return null;
        }
        ClosedCaption[] closedCaptionArr = new ClosedCaption[this.captions.size()];
        this.captions.toArray(closedCaptionArr);
        return new ClosedCaptionList(sampleHolder.timeUs, sampleHolder.isDecodeOnly(), closedCaptionArr);
    }

    private static char getChar(byte b) {
        return (char) BASIC_CHARACTER_SET[(b & ByteCompanionObject.MAX_VALUE) - 32];
    }

    private static char getSpecialChar(byte b) {
        return (char) SPECIAL_CHARACTER_SET[b & 15];
    }

    private static char getExtendedEsFrChar(byte b) {
        return (char) SPECIAL_ES_FR_CHARACTER_SET[b & 31];
    }

    private static char getExtendedPtDeChar(byte b) {
        return (char) SPECIAL_PT_DE_CHARACTER_SET[b & 31];
    }

    private void addBufferedText() {
        if (this.stringBuilder.length() > 0) {
            this.captions.add(new ClosedCaptionText(this.stringBuilder.toString()));
            this.stringBuilder.setLength(0);
        }
    }

    private void addCtrl(byte b, byte b2) {
        addBufferedText();
        this.captions.add(new ClosedCaptionCtrl(b, b2));
    }

    private void backspace() {
        addCtrl((byte) 20, (byte) 33);
    }

    public static boolean isSeiMessageEia608(int i, int i2, ParsableByteArray parsableByteArray) {
        if (i != 4 || i2 < 8) {
            return false;
        }
        int position = parsableByteArray.getPosition();
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int i3 = parsableByteArray.readInt();
        int unsignedByte2 = parsableByteArray.readUnsignedByte();
        parsableByteArray.setPosition(position);
        return unsignedByte == 181 && unsignedShort == 49 && i3 == 1195456820 && unsignedByte2 == 3;
    }
}
