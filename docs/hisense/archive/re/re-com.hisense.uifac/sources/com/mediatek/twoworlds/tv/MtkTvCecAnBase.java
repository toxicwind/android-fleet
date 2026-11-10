package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvCecAnBase {
    public static final String TAG = "TV_MtkTvCecAn";

    public boolean getEarcCaps() {
        return TVNativeWrapper.getEarcCaps_native();
    }

    public int setEarcEnable(boolean z) {
        return TVNativeWrapper.setEarcEnable_native(z);
    }

    public boolean getEarcStatus(int i) {
        return TVNativeWrapper.getEarcStatus_native(i);
    }
}
