package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvOAD extends MtkTvOADBase {
    public static MtkTvOAD mtkTvOAD;

    public static MtkTvOAD getInstance() {
        MtkTvOAD mtkTvOAD2 = mtkTvOAD;
        if (mtkTvOAD2 != null) {
            return mtkTvOAD2;
        }
        mtkTvOAD = new MtkTvOAD();
        return mtkTvOAD;
    }
}
