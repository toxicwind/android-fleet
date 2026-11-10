package com.google.android.exoplayer.text.pgs;

import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;

/* JADX INFO: loaded from: classes.dex */
public enum PgsCompositionState {
    NORMAL(0),
    ACQU_POINT(64),
    EPOCH_START(128),
    EPOCH_CONTINUE(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED),
    INVALID(-1);

    private final int type;

    PgsCompositionState(int i) {
        this.type = i;
    }

    public int getType() {
        return this.type;
    }
}
