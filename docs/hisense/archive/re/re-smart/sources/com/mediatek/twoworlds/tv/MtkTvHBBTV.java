package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvHBBTV extends MtkTvHBBTVBase {
    public static MtkTvHBBTV mtkTvHBBTV;

    public static MtkTvHBBTV getInstance() {
        MtkTvHBBTV mtkTvHBBTV2 = mtkTvHBBTV;
        if (mtkTvHBBTV2 != null) {
            return mtkTvHBBTV2;
        }
        mtkTvHBBTV = new MtkTvHBBTV();
        return mtkTvHBBTV;
    }
}
