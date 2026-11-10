package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvMHEG5 extends MtkTvMHEG5Base {
    private static MtkTvMHEG5 mtkTvMHEG5;

    private MtkTvMHEG5() {
    }

    public static synchronized MtkTvMHEG5 getInstance() {
        if (mtkTvMHEG5 == null) {
            MtkTvMHEG5 mtkTvMHEG6 = new MtkTvMHEG5();
            mtkTvMHEG5 = mtkTvMHEG6;
            return mtkTvMHEG6;
        }
        return mtkTvMHEG5;
    }
}
