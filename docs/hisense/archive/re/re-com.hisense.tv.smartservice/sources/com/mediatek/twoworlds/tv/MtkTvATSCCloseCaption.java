package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvATSCCloseCaption extends MtkTvATSCCloseCaptionBase {
    public static MtkTvATSCCloseCaption mtkTvATSCCloseCaption;

    public static MtkTvATSCCloseCaption getInstance() {
        MtkTvATSCCloseCaption mtkTvATSCCloseCaption2 = mtkTvATSCCloseCaption;
        if (mtkTvATSCCloseCaption2 != null) {
            return mtkTvATSCCloseCaption2;
        }
        mtkTvATSCCloseCaption = new MtkTvATSCCloseCaption();
        return mtkTvATSCCloseCaption;
    }
}
