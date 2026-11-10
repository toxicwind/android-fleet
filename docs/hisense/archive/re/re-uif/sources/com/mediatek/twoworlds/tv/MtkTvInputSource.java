package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvInputSource extends MtkTvInputSourceBase {
    private static MtkTvInputSource mtkTvInputSource;

    private MtkTvInputSource() {
    }

    public static MtkTvInputSource getInstance() {
        MtkTvInputSource mtkTvInputSource2 = mtkTvInputSource;
        if (mtkTvInputSource2 != null) {
            return mtkTvInputSource2;
        }
        MtkTvInputSource mtkTvInputSource3 = new MtkTvInputSource();
        mtkTvInputSource = mtkTvInputSource3;
        return mtkTvInputSource3;
    }
}
