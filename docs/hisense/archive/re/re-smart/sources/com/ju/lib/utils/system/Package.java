package com.ju.lib.utils.system;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/* JADX INFO: loaded from: classes2.dex */
public class Package {
    public static PackageInfo getPackageInfo(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 16384);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static int getVersionCode(Context context, String str) {
        PackageInfo packageInfo = getPackageInfo(context, str);
        if (packageInfo == null) {
            return -1;
        }
        return packageInfo.versionCode;
    }

    public static String getVersionName(Context context, String str) {
        PackageInfo packageInfo = getPackageInfo(context, str);
        if (packageInfo == null) {
            return null;
        }
        return packageInfo.versionName;
    }

    public static boolean isInstalled(Context context, String str) {
        return getPackageInfo(context, str) != null;
    }
}
