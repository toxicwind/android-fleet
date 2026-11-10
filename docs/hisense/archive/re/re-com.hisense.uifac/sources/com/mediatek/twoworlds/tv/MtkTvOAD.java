package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvOAD extends MtkTvOADBase {
    private static MtkTvOAD mtkTvOAD;

    private MtkTvOAD() {
    }

    public static MtkTvOAD getInstance() {
        MtkTvOAD mtkTvOAD2 = mtkTvOAD;
        if (mtkTvOAD2 != null) {
            return mtkTvOAD2;
        }
        MtkTvOAD mtkTvOAD3 = new MtkTvOAD();
        mtkTvOAD = mtkTvOAD3;
        return mtkTvOAD3;
    }
}
