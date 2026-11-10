package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvISDBCloseCaption extends MtkTvISDBCloseCaptionBase {
    public static MtkTvISDBCloseCaption mtkTvISDBCloseCaption;

    public static MtkTvISDBCloseCaption getInstance() {
        MtkTvISDBCloseCaption mtkTvISDBCloseCaption2 = mtkTvISDBCloseCaption;
        if (mtkTvISDBCloseCaption2 != null) {
            return mtkTvISDBCloseCaption2;
        }
        mtkTvISDBCloseCaption = new MtkTvISDBCloseCaption();
        return mtkTvISDBCloseCaption;
    }
}
