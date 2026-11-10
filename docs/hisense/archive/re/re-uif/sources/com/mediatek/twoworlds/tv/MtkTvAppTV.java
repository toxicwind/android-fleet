package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvAppTV extends MtkTvAppTVBase {
    private static MtkTvAppTV mtkTvAppTV;

    private MtkTvAppTV() {
    }

    public static MtkTvAppTV getInstance() {
        MtkTvAppTV mtkTvAppTV2 = mtkTvAppTV;
        if (mtkTvAppTV2 != null) {
            return mtkTvAppTV2;
        }
        MtkTvAppTV mtkTvAppTV3 = new MtkTvAppTV();
        mtkTvAppTV = mtkTvAppTV3;
        return mtkTvAppTV3;
    }
}
