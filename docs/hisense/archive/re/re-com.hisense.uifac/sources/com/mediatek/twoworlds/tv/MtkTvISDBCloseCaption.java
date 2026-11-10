package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvISDBCloseCaption extends MtkTvISDBCloseCaptionBase {
    private static MtkTvISDBCloseCaption mtkTvISDBCloseCaption;

    private MtkTvISDBCloseCaption() {
    }

    public static MtkTvISDBCloseCaption getInstance() {
        MtkTvISDBCloseCaption mtkTvISDBCloseCaption2 = mtkTvISDBCloseCaption;
        if (mtkTvISDBCloseCaption2 != null) {
            return mtkTvISDBCloseCaption2;
        }
        MtkTvISDBCloseCaption mtkTvISDBCloseCaption3 = new MtkTvISDBCloseCaption();
        mtkTvISDBCloseCaption = mtkTvISDBCloseCaption3;
        return mtkTvISDBCloseCaption3;
    }
}
