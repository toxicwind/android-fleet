package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvPWDDialogBase {
    public static final String TAG = "TV_MtkTvPWDDialogBase";

    public boolean checkPWD(String str) {
        Log.d(TAG, "checkPWD\n");
        return TVNativeWrapper.checkPWD_native(str);
    }

    public int PWDShow() {
        Log.d(TAG, "PWDShow \n");
        return TVNativeWrapper.PWDShow_native();
    }
}
