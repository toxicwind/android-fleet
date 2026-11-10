package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvCec extends MtkTvCecBase {
    private static MtkTvCec mtkTvCEC;

    private MtkTvCec() {
    }

    public static MtkTvCec getInstance() {
        MtkTvCec mtkTvCec = mtkTvCEC;
        if (mtkTvCec != null) {
            return mtkTvCec;
        }
        MtkTvCec mtkTvCec2 = new MtkTvCec();
        mtkTvCEC = mtkTvCec2;
        return mtkTvCec2;
    }
}
