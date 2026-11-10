package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvTimeFormat extends MtkTvTimeFormatBase {
    public static MtkTvTimeFormat mMtkTvTimeFormat;

    public static synchronized MtkTvTimeFormat getInstance() {
        if (mMtkTvTimeFormat == null) {
            mMtkTvTimeFormat = new MtkTvTimeFormat();
            Log.d(MtkTvTimeFormatBase.TAG, "New class MtkTvTime\n");
        }
        return mMtkTvTimeFormat;
    }
}
