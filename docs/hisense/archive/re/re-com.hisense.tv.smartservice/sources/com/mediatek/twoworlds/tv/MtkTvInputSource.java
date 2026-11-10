package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvInputSource extends MtkTvInputSourceBase {
    public static MtkTvInputSource mtkTvInputSource;

    public static MtkTvInputSource getInstance() {
        MtkTvInputSource mtkTvInputSource2 = mtkTvInputSource;
        if (mtkTvInputSource2 != null) {
            return mtkTvInputSource2;
        }
        mtkTvInputSource = new MtkTvInputSource();
        return mtkTvInputSource;
    }
}
