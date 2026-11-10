package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvMHP extends MtkTvMHPBase {
    public static MtkTvMHP mtkTvMHP;

    public static MtkTvMHP getInstance() {
        MtkTvMHP mtkTvMHP2 = mtkTvMHP;
        if (mtkTvMHP2 != null) {
            return mtkTvMHP2;
        }
        mtkTvMHP = new MtkTvMHP();
        return mtkTvMHP;
    }
}
