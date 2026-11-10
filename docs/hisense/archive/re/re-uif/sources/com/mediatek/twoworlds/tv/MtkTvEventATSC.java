package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvEventATSC extends MtkTvEventATSCBase {
    private static MtkTvEventATSC mtkTvEventATSC;

    private MtkTvEventATSC() {
    }

    public static MtkTvEventATSC getInstance() {
        MtkTvEventATSC mtkTvEventATSC2 = mtkTvEventATSC;
        if (mtkTvEventATSC2 != null) {
            return mtkTvEventATSC2;
        }
        MtkTvEventATSC mtkTvEventATSC3 = new MtkTvEventATSC();
        mtkTvEventATSC = mtkTvEventATSC3;
        return mtkTvEventATSC3;
    }
}
