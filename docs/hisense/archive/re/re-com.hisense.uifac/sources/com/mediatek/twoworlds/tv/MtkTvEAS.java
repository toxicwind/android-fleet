package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvEAS {
    private static MtkTvEAS mtkTvEAS;

    private MtkTvEAS() {
    }

    public static MtkTvEAS getInstance() {
        MtkTvEAS mtkTvEAS2 = mtkTvEAS;
        if (mtkTvEAS2 != null) {
            return mtkTvEAS2;
        }
        MtkTvEAS mtkTvEAS3 = new MtkTvEAS();
        mtkTvEAS = mtkTvEAS3;
        return mtkTvEAS3;
    }
}
