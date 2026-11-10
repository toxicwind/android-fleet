package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvPipPop extends MtkTvPipPopBase {
    private static MtkTvPipPop mtkTvPipPop;

    private MtkTvPipPop() {
    }

    public static MtkTvPipPop getInstance() {
        MtkTvPipPop mtkTvPipPop2 = mtkTvPipPop;
        if (mtkTvPipPop2 != null) {
            return mtkTvPipPop2;
        }
        MtkTvPipPop mtkTvPipPop3 = new MtkTvPipPop();
        mtkTvPipPop = mtkTvPipPop3;
        return mtkTvPipPop3;
    }
}
