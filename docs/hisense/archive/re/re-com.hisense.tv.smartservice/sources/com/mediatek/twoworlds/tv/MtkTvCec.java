package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvCec extends MtkTvCecBase {
    public static MtkTvCec mtkTvCEC;

    public static MtkTvCec getInstance() {
        MtkTvCec mtkTvCec = mtkTvCEC;
        if (mtkTvCec != null) {
            return mtkTvCec;
        }
        mtkTvCEC = new MtkTvCec();
        return mtkTvCEC;
    }
}
