package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvTeletext extends MtkTvTeletextBase {
    public static MtkTvTeletext mtkTvTeletext;

    public static MtkTvTeletext getInstance() {
        MtkTvTeletext mtkTvTeletext2 = mtkTvTeletext;
        if (mtkTvTeletext2 != null) {
            return mtkTvTeletext2;
        }
        mtkTvTeletext = new MtkTvTeletext();
        return mtkTvTeletext;
    }
}
