package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvAppTV extends MtkTvAppTVBase {
    public static MtkTvAppTV mtkTvAppTV;

    public static MtkTvAppTV getInstance() {
        MtkTvAppTV mtkTvAppTV2 = mtkTvAppTV;
        if (mtkTvAppTV2 != null) {
            return mtkTvAppTV2;
        }
        mtkTvAppTV = new MtkTvAppTV();
        return mtkTvAppTV;
    }
}
