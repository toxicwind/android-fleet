package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvTimeFormat extends MtkTvTimeFormatBase {
    private static MtkTvTimeFormat mMtkTvTimeFormat;

    private MtkTvTimeFormat() {
    }

    public static synchronized MtkTvTimeFormat getInstance() {
        if (mMtkTvTimeFormat == null) {
            mMtkTvTimeFormat = new MtkTvTimeFormat();
            Log.d(MtkTvTimeFormatBase.TAG, "New class MtkTvTime\n");
        }
        return mMtkTvTimeFormat;
    }
}
