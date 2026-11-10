package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvCI extends MtkTvCIBase {
    private static MtkTvCI mtkTvCI;

    private MtkTvCI(int i) {
        super(i);
    }

    public static MtkTvCI getInstance(int i) {
        MtkTvCI mtkTvCI2 = mtkTvCI;
        if (mtkTvCI2 != null) {
            return mtkTvCI2;
        }
        MtkTvCI mtkTvCI3 = new MtkTvCI(i);
        mtkTvCI = mtkTvCI3;
        return mtkTvCI3;
    }
}
