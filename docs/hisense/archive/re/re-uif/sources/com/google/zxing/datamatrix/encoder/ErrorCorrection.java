package com.google.zxing.datamatrix.encoder;

import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;

/* JADX INFO: loaded from: classes.dex */
public final class ErrorCorrection {
    private static final int[] FACTOR_SETS = {5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};
    private static final int[][] FACTORS = {new int[]{228, 48, 15, 111, 62}, new int[]{23, 68, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_OFF, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_INP_SIGNAL_UNKNOWN, 240, 92, 254}, new int[]{28, 24, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INV_BUSINESS_TKN, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_18_PLUS_LOCKED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_MARLIN_KEY_FORMATERROR, 248, 116, 255, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_END, 61}, new int[]{MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_AUDIO_SWITCH_DONE, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_NOT_DETECT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CMPB_QUERY, 12, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INV_USR_AGENT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CONNECTION_ANA_FREQ, 39, 245, 60, 97, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RANGE_AB_DONE}, new int[]{41, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REACH_BOUND, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STRG_FULL, 91, 61, 42, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_FILE_OPER_ERROR, 97, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_VIDEO_NUM_READY, 100, 242}, new int[]{MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_UNAUTHORIZED, 97, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED, 252, 95, 9, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BLOCKED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_NO_CONTENT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_NOT_DETECT, 45, 18, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CERT_EXPIRED, 83, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INV_BUSINESS_TKN}, new int[]{83, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_UNSUPPORTED_VER, 100, 39, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_SRV_END, 75, 66, 61, 241, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_FILE_OPER_ERROR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_START, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PVR_PLAYING_PARENT_RATING, 94, 254, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_NAUTILUS_MARLIN, 48, 90, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_SRV_END}, new int[]{15, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_UNSUPPORTED_VER, 244, 9, 233, 71, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CONNECTION_ANA_FREQ, 2, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_SRV_END, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_NOT_SUPPORTED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REACH_BOUND, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STARTED, 253, 79, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REACH_VALID_RANGE_END, 82, 27, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_CLEAR_EOF, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CERT_EXPIRED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_UNDERFLOW}, new int[]{52, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_SRV_BUSY, 88, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CMPB_QUERY, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_START, 39, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GET_IFRAME, 21, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BUF_FULL, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INV_SRV_TKN, 251, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_MARLIN_KEY_FORMATERROR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BUF_FULL, 21, 5, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_UNDERFLOW, 254, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_URI_FILE_OPENED, 12, 181, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_SRVDATA_ACCESS_FAIL, 96, 50, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_UNKNOWN_SRV_ERR}, new int[]{MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CONN_PMT_UPDATED, 231, 43, 97, 71, 96, 103, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_CLEAR_EOF, 37, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_PIN_CAP_CHANGE, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_BUF_READY, 53, 75, 34, 249, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_VIDEO_PRESENT, 17, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_NOT_DETECT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_END, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_FILE_OPER_ERROR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_BRDCSTING_SVC, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_NOT_DETECT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RANGE_AB_DONE, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_PIN_CAP_CHANGE, 233, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CONNECTION_ANA_FREQ, 93, 255}, new int[]{245, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_UNBLOCKED, 242, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_INVALID, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PVR_PROG_BLOCKED, 250, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_INTERNAL_ERROR, 181, 102, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RANGE_AB_DONE, 84, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_MSGQ_FULL, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CAD_INVALID, 251, 80, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_UNKNOWN_ERR, 229, 18, 2, 4, 68, 33, 101, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_DETECT, 95, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_NO_CONTENT, 115, 44, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_AUDIO_SWITCH_DONE, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_SRVDATA_ACCESS_FAIL, 59, 25, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_NAUTILUS_MARLIN, 98, 81, 112}, new int[]{77, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_UNKNOWN_SRV_ERR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_DETECT, 31, 19, 38, 22, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REACH_BOUND, 247, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_DRM_NOT_SUPPORT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_VS_BIT_RATE, 2, 245, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PAUSE_STATUS, 242, 8, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_AUDIO_SWITCH_DONE, 95, 100, 9, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CHAPTER_CHANGE, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_DRM_NOT_SUPPORT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_INPUT_LOCKED, 111, 57, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_VIDEO_PRESENT, 21, 1, 253, 57, 54, 101, 248, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_SERVER_CONNECTION_ERROR, 69, 50, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_STOP, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_STREAM_OPENED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_PLAYREADY, 5, 9, 5}, new int[]{245, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_AFT_THUMB, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_UNDERFLOW, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_MARLIN_KEY_FORMATERROR, 96, 32, 117, 22, 238, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PAUSE_STATUS, 238, 231, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CMPB_QUERY, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_SRV_END, 237, 87, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_DEV_NOT_REG, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_SUBTITLE_FMT_UPDATE, 16, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_PAUSED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_FRAMERATE_NOT_SUPPORT, 23, 37, 90, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_BUF_READY, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CMPB_QUERY, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_DUR_THUMB, 88, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RANGE_AB_DONE, 100, 66, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_NOT_DETECT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CERT_EXPIRED, 240, 82, 44, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GET_IFRAME, 87, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LIC_NOT_ALLOW, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_PAUSED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_NOT_SUPPORTED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_AUDIO_SWITCH_DONE, 69, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_FILE_OPER_ERROR, 92, 253, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_NAUTILUS_MARLIN, 19}, new int[]{MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_AUDIO_SWITCH_DONE, 9, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_MARLIN_KEY_FORMATERROR, 238, 12, 17, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CAD_INVALID, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_VIDEO_STREAM_CTR_NOT_SUPPORT, 100, 29, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_AUDIO_SWITCH_DONE, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_BUF_READY, 230, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_PSI_TABLE_UPDATE, 235, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_STOP, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BITRATE_TOO_HIGH, 36, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_MARLIN_KEY_FORMATERROR, 38, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_EXPIRED_ERROR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_AFT_THUMB, 54, 228, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STOPPED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_INVALID, 234, 117, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TOKEN_ACQUISITION_ERROR, 29, 232, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_OFF, 238, 22, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_STOP, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_ACQUISITION_ERROR, 117, 62, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CC_ATTR_CHANGED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_CHANNEL_LOCKED, 13, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_DETECT, 245, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_UNBLOCKED, 67, 247, 28, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BUF_FULL, 43, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TOKEN_ACQUISITION_ERROR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REACH_VALID_RANGE_START, 233, 53, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_ON, 46}, new int[]{242, 93, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_ANA_AUTO_DETECTED_AUD_SYS, 50, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_OFF, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_VIDEO_WFD_ERROR, 39, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_FRAMERATE_NOT_SUPPORT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_SERVER_CONNECTION_ERROR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_SRV_END, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_ACQUISITION_ERROR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_SRV_UNAVAIL, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_ON, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REACH_VALID_RANGE_END, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INV_CONTENT_TYPE, 37, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INV_BUSINESS_TKN, 112, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_INP_SIGNAL_UNKNOWN, 230, 245, 63, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INV_SRV_TKN, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_SRV_BUSY, 250, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_SUBTITLE_FMT_UPDATE, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INV_BUSINESS_TKN, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_NO_MARLIN_KEY, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_AUDIO_SWITCH_DONE, 64, 114, 71, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STRG_ERROR, 44, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_PAUSED, 6, 27, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_INVALID, 51, 63, 87, 10, 40, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PVR_PROG_BLOCKED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_SRV_END, 17, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BAD_TICK, 31, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GET_IFRAME, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_BUF_READY, 4, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REACH_VALID_RANGE_START, 232, 7, 94, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_18_PLUS_LOCKED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_MARLIN, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_URI_FILE_OPENED, 86, 47, 11, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CAD_ACQUISITION_ERROR}, new int[]{MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CAD_INVALID, 228, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_EOF, 89, 251, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_ES_LIST, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BITRATE_TOO_HIGH, 56, 89, 33, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_PAUSED, 244, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REACH_TIME_BOUND, 36, 73, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_UNBLOCKED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_FILE_OPER_ERROR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_NOT_DETECT, 248, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_MSGQ_EMPTY, 234, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INV_SRV_TKN, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STRG_FULL, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_STREAM_OPENED, 68, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_VS_BIT_RATE, 93, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_FILE_OPER_ERROR, 15, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_NOT_SUPPORTED, 227, 236, 66, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_ON, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REACH_BOUND, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INV_BUSINESS_TKN, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_SERVER_CONNECTION_ERROR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CHAPTER_CHANGE, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_MSGQ_FULL, 25, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CAD_INVALID, 232, 96, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_VIDEO_WFD_ERROR, 231, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_NOT_DETECT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_MARLIN_KEY_FORMATERROR, 239, 181, 241, 59, 52, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_UNDERFLOW, 25, 49, 232, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CONN_PMT_UPDATED, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_SRV_UNAVAIL, 64, 54, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REACH_VALID_RANGE_END, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REACH_BOUND, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_AFT_THUMB, 63, 96, 103, 82, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CERT_EXPIRED}};
    private static final int[] LOG = new int[256];
    private static final int[] ALOG = new int[255];

    static {
        int i = 1;
        for (int i2 = 0; i2 < 255; i2++) {
            ALOG[i2] = i;
            LOG[i] = i2;
            i <<= 1;
            if (i >= 256) {
                i ^= 301;
            }
        }
    }

    public static String encodeECC200(String str, SymbolInfo symbolInfo) {
        if (str.length() != symbolInfo.getDataCapacity()) {
            throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
        }
        StringBuilder sb = new StringBuilder(symbolInfo.getDataCapacity() + symbolInfo.getErrorCodewords());
        sb.append(str);
        int interleavedBlockCount = symbolInfo.getInterleavedBlockCount();
        if (interleavedBlockCount == 1) {
            sb.append(createECCBlock(str, symbolInfo.getErrorCodewords()));
        } else {
            sb.setLength(sb.capacity());
            int[] iArr = new int[interleavedBlockCount];
            int[] iArr2 = new int[interleavedBlockCount];
            int[] iArr3 = new int[interleavedBlockCount];
            int i = 0;
            while (i < interleavedBlockCount) {
                int i2 = i + 1;
                iArr[i] = symbolInfo.getDataLengthForInterleavedBlock(i2);
                iArr2[i] = symbolInfo.getErrorLengthForInterleavedBlock(i2);
                iArr3[i] = 0;
                if (i > 0) {
                    iArr3[i] = iArr3[i - 1] + iArr[i];
                }
                i = i2;
            }
            for (int i3 = 0; i3 < interleavedBlockCount; i3++) {
                StringBuilder sb2 = new StringBuilder(iArr[i3]);
                for (int i4 = i3; i4 < symbolInfo.getDataCapacity(); i4 += interleavedBlockCount) {
                    sb2.append(str.charAt(i4));
                }
                String strCreateECCBlock = createECCBlock(sb2.toString(), iArr2[i3]);
                int i5 = i3;
                int i6 = 0;
                while (i5 < iArr2[i3] * interleavedBlockCount) {
                    sb.setCharAt(symbolInfo.getDataCapacity() + i5, strCreateECCBlock.charAt(i6));
                    i5 += interleavedBlockCount;
                    i6++;
                }
            }
        }
        return sb.toString();
    }

    private static String createECCBlock(CharSequence charSequence, int i) {
        return createECCBlock(charSequence, 0, charSequence.length(), i);
    }

    private static String createECCBlock(CharSequence charSequence, int i, int i2, int i3) {
        int[] iArr = LOG;
        int[] iArr2 = ALOG;
        int i4 = 0;
        while (true) {
            int[] iArr3 = FACTOR_SETS;
            if (i4 >= iArr3.length) {
                i4 = -1;
                break;
            }
            if (iArr3[i4] == i3) {
                break;
            }
            i4++;
        }
        if (i4 < 0) {
            throw new IllegalArgumentException("Illegal number of error correction codewords specified: " + i3);
        }
        int[] iArr4 = FACTORS[i4];
        char[] cArr = new char[i3];
        for (int i5 = 0; i5 < i3; i5++) {
            cArr[i5] = 0;
        }
        for (int i6 = i; i6 < i + i2; i6++) {
            int i7 = i3 - 1;
            int iCharAt = cArr[i7] ^ charSequence.charAt(i6);
            while (i7 > 0) {
                if (iCharAt != 0 && iArr4[i7] != 0) {
                    cArr[i7] = (char) (cArr[i7 - 1] ^ iArr2[(iArr[iCharAt] + iArr[iArr4[i7]]) % 255]);
                } else {
                    cArr[i7] = cArr[i7 - 1];
                }
                i7--;
            }
            if (iCharAt != 0 && iArr4[0] != 0) {
                cArr[0] = (char) iArr2[(iArr[iCharAt] + iArr[iArr4[0]]) % 255];
            } else {
                cArr[0] = 0;
            }
        }
        char[] cArr2 = new char[i3];
        for (int i8 = 0; i8 < i3; i8++) {
            cArr2[i8] = cArr[(i3 - i8) - 1];
        }
        return String.valueOf(cArr2);
    }
}
