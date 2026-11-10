package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvAVMode extends MtkTvAVModeBase {
    public static MtkTvAVMode mtkTvAVMode;

    public static MtkTvAVMode getInstance() {
        MtkTvAVMode mtkTvAVMode2 = mtkTvAVMode;
        if (mtkTvAVMode2 != null) {
            return mtkTvAVMode2;
        }
        mtkTvAVMode = new MtkTvAVMode();
        return mtkTvAVMode;
    }
}
