package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvCI extends MtkTvCIBase {
    public static MtkTvCI mtkTvCI;

    public MtkTvCI(int i) {
        super(i);
    }

    public static MtkTvCI getInstance(int i) {
        MtkTvCI mtkTvCI2 = mtkTvCI;
        if (mtkTvCI2 != null) {
            return mtkTvCI2;
        }
        mtkTvCI = new MtkTvCI(i);
        return mtkTvCI;
    }
}
