package com.google.zxing.oned;

import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;

/* JADX INFO: loaded from: classes.dex */
public final class Code39Reader extends OneDReader {
    static final int[] CHARACTER_ENCODINGS;

    static {
        "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".toCharArray();
        int[] iArr = {52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_UNKNOWN_SRV_ERR, 448, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STARTED, 400, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_VIDEO_STREAM_CTR_NOT_SUPPORT, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PAUSE_STATUS, 388, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INV_CONTENT_TYPE, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_SOURCE_READY, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CONNECTION_ANA_FREQ, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_INTERNAL_ERROR, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_NOT_DETECT, 42};
        CHARACTER_ENCODINGS = iArr;
        int i = iArr[39];
    }
}
