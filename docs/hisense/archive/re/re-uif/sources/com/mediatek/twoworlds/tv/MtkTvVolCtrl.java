package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvVolCtrl extends MtkTvVolCtrlBase {
    private static MtkTvVolCtrl mtkTvVolCtrl;

    private MtkTvVolCtrl() {
    }

    public static MtkTvVolCtrl getInstance() {
        MtkTvVolCtrl mtkTvVolCtrl2 = mtkTvVolCtrl;
        if (mtkTvVolCtrl2 != null) {
            return mtkTvVolCtrl2;
        }
        MtkTvVolCtrl mtkTvVolCtrl3 = new MtkTvVolCtrl();
        mtkTvVolCtrl = mtkTvVolCtrl3;
        return mtkTvVolCtrl3;
    }
}
