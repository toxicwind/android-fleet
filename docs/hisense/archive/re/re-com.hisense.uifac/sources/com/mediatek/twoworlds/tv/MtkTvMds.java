package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvMds extends MtkTvMdsBase {
    private static MtkTvMds mtkTvMds;

    private MtkTvMds() {
    }

    public static MtkTvMds getInstance() {
        MtkTvMds mtkTvMds2 = mtkTvMds;
        if (mtkTvMds2 != null) {
            return mtkTvMds2;
        }
        MtkTvMds mtkTvMds3 = new MtkTvMds();
        mtkTvMds = mtkTvMds3;
        return mtkTvMds3;
    }
}
