package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvGinga extends MtkTvGingaBase {
    public static MtkTvGinga mtkTvGinga;

    public static MtkTvGinga getInstance() {
        MtkTvGinga mtkTvGinga2 = mtkTvGinga;
        if (mtkTvGinga2 != null) {
            return mtkTvGinga2;
        }
        mtkTvGinga = new MtkTvGinga();
        return mtkTvGinga;
    }
}
