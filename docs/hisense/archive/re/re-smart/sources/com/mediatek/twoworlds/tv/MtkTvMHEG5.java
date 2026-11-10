package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvMHEG5 extends MtkTvMHEG5Base {
    public static MtkTvMHEG5 mtkTvMHEG5;

    public static synchronized MtkTvMHEG5 getInstance() {
        if (mtkTvMHEG5 != null) {
            return mtkTvMHEG5;
        }
        mtkTvMHEG5 = new MtkTvMHEG5();
        return mtkTvMHEG5;
    }
}
