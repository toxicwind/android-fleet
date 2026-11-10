package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvMHP extends MtkTvMHPBase {
    private static MtkTvMHP mtkTvMHP;

    private MtkTvMHP() {
    }

    public static MtkTvMHP getInstance() {
        MtkTvMHP mtkTvMHP2 = mtkTvMHP;
        if (mtkTvMHP2 != null) {
            return mtkTvMHP2;
        }
        MtkTvMHP mtkTvMHP3 = new MtkTvMHP();
        mtkTvMHP = mtkTvMHP3;
        return mtkTvMHP3;
    }
}
