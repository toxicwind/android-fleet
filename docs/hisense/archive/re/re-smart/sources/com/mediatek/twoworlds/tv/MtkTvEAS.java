package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvEAS {
    public static MtkTvEAS mtkTvEAS;

    public static MtkTvEAS getInstance() {
        MtkTvEAS mtkTvEAS2 = mtkTvEAS;
        if (mtkTvEAS2 != null) {
            return mtkTvEAS2;
        }
        mtkTvEAS = new MtkTvEAS();
        return mtkTvEAS;
    }
}
