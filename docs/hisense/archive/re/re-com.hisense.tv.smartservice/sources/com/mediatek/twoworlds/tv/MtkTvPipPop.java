package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvPipPop extends MtkTvPipPopBase {
    public static MtkTvPipPop mtkTvPipPop;

    public static MtkTvPipPop getInstance() {
        MtkTvPipPop mtkTvPipPop2 = mtkTvPipPop;
        if (mtkTvPipPop2 != null) {
            return mtkTvPipPop2;
        }
        mtkTvPipPop = new MtkTvPipPop();
        return mtkTvPipPop;
    }
}
