package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvTimeshift extends MtkTvTimeshiftBase {
    public static MtkTvTimeshift mInstance;

    public static MtkTvTimeshift getInstance() {
        MtkTvTimeshift mtkTvTimeshift = mInstance;
        if (mtkTvTimeshift != null) {
            return mtkTvTimeshift;
        }
        mInstance = new MtkTvTimeshift();
        return mInstance;
    }
}
