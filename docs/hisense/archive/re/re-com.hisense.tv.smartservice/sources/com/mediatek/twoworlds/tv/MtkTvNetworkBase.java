package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvNetworkBase {
    public final String TAG = "MtkTvNetworkBase";

    public boolean getEthernetWolCtl() {
        Log.d("MtkTvNetworkBase", "Enter getEthernetWolCtl Here..");
        return TVNativeWrapper.getEthernetWolCtl_native();
    }

    public boolean getWifiWolCtl() {
        Log.d("MtkTvNetworkBase", "Enter getWifiWolCtl Here..");
        return TVNativeWrapper.getWifiWolCtl_native();
    }

    public boolean setEthernetWolCtl(boolean z) {
        Log.d("MtkTvNetworkBase", "Enter setEthernetWolCtl Here : " + z);
        return TVNativeWrapper.setEthernetWolCtl_native(z);
    }

    public boolean setWifiWolCtl(boolean z) {
        Log.d("MtkTvNetworkBase", "Enter setWifiWolCtl Here : " + z);
        return TVNativeWrapper.setWifiWolCtl_native(z);
    }
}
