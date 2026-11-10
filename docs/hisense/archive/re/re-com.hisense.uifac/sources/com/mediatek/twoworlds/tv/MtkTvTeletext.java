package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvTeletext extends MtkTvTeletextBase {
    private static MtkTvTeletext mtkTvTeletext;

    private MtkTvTeletext() {
    }

    public static MtkTvTeletext getInstance() {
        MtkTvTeletext mtkTvTeletext2 = mtkTvTeletext;
        if (mtkTvTeletext2 != null) {
            return mtkTvTeletext2;
        }
        MtkTvTeletext mtkTvTeletext3 = new MtkTvTeletext();
        mtkTvTeletext = mtkTvTeletext3;
        return mtkTvTeletext3;
    }
}
