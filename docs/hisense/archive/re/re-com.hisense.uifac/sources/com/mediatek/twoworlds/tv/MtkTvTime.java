package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvTime extends MtkTvTimeBase {
    private static MtkTvTime mMtkTvTime;

    private MtkTvTime() {
    }

    public static synchronized MtkTvTime getInstance() {
        if (mMtkTvTime == null) {
            mMtkTvTime = new MtkTvTime();
            Log.d(MtkTvTimeBase.TAG, "New class MtkTvTime\n");
        }
        return mMtkTvTime;
    }
}
