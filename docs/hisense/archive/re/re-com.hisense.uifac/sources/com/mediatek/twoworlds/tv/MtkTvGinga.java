package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvGinga extends MtkTvGingaBase {
    private static MtkTvGinga mtkTvGinga;

    private MtkTvGinga() {
    }

    public static MtkTvGinga getInstance() {
        MtkTvGinga mtkTvGinga2 = mtkTvGinga;
        if (mtkTvGinga2 != null) {
            return mtkTvGinga2;
        }
        MtkTvGinga mtkTvGinga3 = new MtkTvGinga();
        mtkTvGinga = mtkTvGinga3;
        return mtkTvGinga3;
    }
}
