package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvISDBCloseCaptionBase {
    public static final String TAG = "MtkTvISDBCloseCaption";

    public MtkTvISDBCloseCaptionBase() {
        Log.d(TAG, "MtkTvISDBCloseCaptionBase object created");
    }

    public int ISDBCCEnable(boolean z) {
        Log.d(TAG, "ISDBCCEnable: " + z + " \n");
        return TVNativeWrapper.ISDBCCEnable_native(z);
    }

    public int ISDBCCNextStream() {
        Log.d(TAG, "ISDBCCNextStream. \n");
        return TVNativeWrapper.ISDBCCNextStream_native();
    }

    public int ISDBCCGetCCString() {
        Log.d(TAG, "ISDBCCGetCCString \n");
        return TVNativeWrapper.ISDBCCGetCCString_native();
    }
}
