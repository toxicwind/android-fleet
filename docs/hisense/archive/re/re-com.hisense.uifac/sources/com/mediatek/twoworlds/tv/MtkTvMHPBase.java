package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvMHPBase {
    public static final String TAG = "MtkTvMHPBase";

    public MtkTvMHPBase() {
        Log.d(TAG, "MtkTvMHPBase object created");
    }

    public int enter() {
        Log.d(TAG, "enter entered");
        new MtkTvMHPBase();
        int iEnter_native = TVNativeWrapper.enter_native();
        Log.d(TAG, "enter exit " + iEnter_native);
        return iEnter_native;
    }
}
