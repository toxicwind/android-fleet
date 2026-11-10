package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvTimeshift extends MtkTvTimeshiftBase {
    private static MtkTvTimeshift mInstance;

    private MtkTvTimeshift() {
    }

    public static MtkTvTimeshift getInstance() {
        MtkTvTimeshift mtkTvTimeshift = mInstance;
        if (mtkTvTimeshift != null) {
            return mtkTvTimeshift;
        }
        MtkTvTimeshift mtkTvTimeshift2 = new MtkTvTimeshift();
        mInstance = mtkTvTimeshift2;
        return mtkTvTimeshift2;
    }
}
