package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvMHPBase {
    public static final String TAG = "MtkTvMHPBase";

    public MtkTvMHPBase() {
        Log.d(TAG, "MtkTvMHPBase object created");
    }

    public int enter() {
        Log.d(TAG, "enter entered");
        Log.d(TAG, "MtkTvMHPBase object created");
        int iEnter_native = TVNativeWrapper.enter_native();
        a.b("enter exit ", iEnter_native, TAG);
        return iEnter_native;
    }
}
