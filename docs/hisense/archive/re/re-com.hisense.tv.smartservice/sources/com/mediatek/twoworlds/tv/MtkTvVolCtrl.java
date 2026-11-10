package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvVolCtrl extends MtkTvVolCtrlBase {
    public static MtkTvVolCtrl mtkTvVolCtrl;

    public static MtkTvVolCtrl getInstance() {
        MtkTvVolCtrl mtkTvVolCtrl2 = mtkTvVolCtrl;
        if (mtkTvVolCtrl2 != null) {
            return mtkTvVolCtrl2;
        }
        mtkTvVolCtrl = new MtkTvVolCtrl();
        return mtkTvVolCtrl;
    }
}
