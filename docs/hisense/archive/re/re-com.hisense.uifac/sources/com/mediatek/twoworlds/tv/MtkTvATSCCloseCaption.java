package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvATSCCloseCaption extends MtkTvATSCCloseCaptionBase {
    private static MtkTvATSCCloseCaption mtkTvATSCCloseCaption;

    private MtkTvATSCCloseCaption() {
    }

    public static MtkTvATSCCloseCaption getInstance() {
        MtkTvATSCCloseCaption mtkTvATSCCloseCaption2 = mtkTvATSCCloseCaption;
        if (mtkTvATSCCloseCaption2 != null) {
            return mtkTvATSCCloseCaption2;
        }
        MtkTvATSCCloseCaption mtkTvATSCCloseCaption3 = new MtkTvATSCCloseCaption();
        mtkTvATSCCloseCaption = mtkTvATSCCloseCaption3;
        return mtkTvATSCCloseCaption3;
    }
}
