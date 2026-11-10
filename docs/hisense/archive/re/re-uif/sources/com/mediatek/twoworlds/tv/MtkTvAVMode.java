package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvAVMode extends MtkTvAVModeBase {
    private static MtkTvAVMode mtkTvAVMode;

    private MtkTvAVMode() {
    }

    public static MtkTvAVMode getInstance() {
        MtkTvAVMode mtkTvAVMode2 = mtkTvAVMode;
        if (mtkTvAVMode2 != null) {
            return mtkTvAVMode2;
        }
        MtkTvAVMode mtkTvAVMode3 = new MtkTvAVMode();
        mtkTvAVMode = mtkTvAVMode3;
        return mtkTvAVMode3;
    }
}
