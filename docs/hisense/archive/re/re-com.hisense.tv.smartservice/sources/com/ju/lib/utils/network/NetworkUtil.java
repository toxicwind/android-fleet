package com.ju.lib.utils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkUtil {
    public static boolean isNetConnect(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        if (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }

    public static boolean isWifiConnected(Context context) {
        NetworkInfo networkInfo;
        if (context == null || (networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1)) == null) {
            return false;
        }
        return networkInfo.isAvailable();
    }
}
