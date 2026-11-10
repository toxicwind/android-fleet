package com.google.android.gms.common.util;

import android.os.Build;
import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
@KeepForSdk
public final class PlatformVersion {
    @KeepForSdk
    public static boolean isAtLeastHoneycomb() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastHoneycombMR1() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastIceCreamSandwich() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastIceCreamSandwichMR1() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastJellyBean() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastJellyBeanMR1() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastJellyBeanMR2() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastKitKat() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastKitKatWatch() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastLollipop() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastLollipopMR1() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastM() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastN() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastO() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastP() {
        return Build.VERSION.SDK_INT >= 28;
    }

    @KeepForSdk
    public static boolean isAtLeastQ() {
        return Build.VERSION.SDK_INT >= 29;
    }

    @KeepForSdk
    public static boolean isAtLeastR() {
        return Build.VERSION.SDK_INT >= 30;
    }

    @KeepForSdk
    public static boolean isAtLeastS() {
        if (Build.VERSION.SDK_INT >= 31) {
            return true;
        }
        return isAtLeastR() && Build.VERSION.CODENAME.length() == 1 && Build.VERSION.CODENAME.charAt(0) >= 'S' && Build.VERSION.CODENAME.charAt(0) <= 'Z';
    }

    @KeepForSdk
    public static boolean isAtLeastT() {
        return isAtLeastS() && Build.VERSION.CODENAME.charAt(0) >= 'T' && Build.VERSION.CODENAME.charAt(0) <= 'Z';
    }
}
