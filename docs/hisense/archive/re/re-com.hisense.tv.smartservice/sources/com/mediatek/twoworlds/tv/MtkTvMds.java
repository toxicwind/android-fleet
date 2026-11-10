package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvMds extends MtkTvMdsBase {
    public static MtkTvMds mtkTvMds;

    public static MtkTvMds getInstance() {
        MtkTvMds mtkTvMds2 = mtkTvMds;
        if (mtkTvMds2 != null) {
            return mtkTvMds2;
        }
        mtkTvMds = new MtkTvMds();
        return mtkTvMds;
    }
}
