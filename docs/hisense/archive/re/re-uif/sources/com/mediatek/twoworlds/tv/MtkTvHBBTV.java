package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvHBBTV extends MtkTvHBBTVBase {
    private static MtkTvHBBTV mtkTvHBBTV;

    private MtkTvHBBTV() {
    }

    public static MtkTvHBBTV getInstance() {
        MtkTvHBBTV mtkTvHBBTV2 = mtkTvHBBTV;
        if (mtkTvHBBTV2 != null) {
            return mtkTvHBBTV2;
        }
        MtkTvHBBTV mtkTvHBBTV3 = new MtkTvHBBTV();
        mtkTvHBBTV = mtkTvHBBTV3;
        return mtkTvHBBTV3;
    }
}
