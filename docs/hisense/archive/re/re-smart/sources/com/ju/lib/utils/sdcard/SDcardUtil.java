package com.ju.lib.utils.sdcard;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

/* JADX INFO: loaded from: classes2.dex */
public class SDcardUtil {
    public static String getSDcardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static long getSdcardFreeSpace() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        int i = Build.VERSION.SDK_INT;
        return statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
    }

    public static long getSdcardTotalSpace() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        int i = Build.VERSION.SDK_INT;
        return statFs.getBlockSizeLong() * statFs.getBlockCountLong();
    }

    public static boolean isSdcardAmount() {
        return Environment.getExternalStorageState().equals("mounted");
    }
}
