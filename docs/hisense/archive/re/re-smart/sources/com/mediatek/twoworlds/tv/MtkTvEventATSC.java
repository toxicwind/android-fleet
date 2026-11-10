package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvEventATSC extends MtkTvEventATSCBase {
    public static MtkTvEventATSC mtkTvEventATSC;

    public static MtkTvEventATSC getInstance() {
        MtkTvEventATSC mtkTvEventATSC2 = mtkTvEventATSC;
        if (mtkTvEventATSC2 != null) {
            return mtkTvEventATSC2;
        }
        mtkTvEventATSC = new MtkTvEventATSC();
        return mtkTvEventATSC;
    }
}
