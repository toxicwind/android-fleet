package com.hisense.support.tv;

import android.util.Log;
import java.util.ArrayList;
import vendor.hisense.support.tv.hs_platform_service.V1_0.IHisPlatformConfig;

/* JADX INFO: loaded from: classes.dex */
public class HisensePlatformConfig {
    private static IHisPlatformConfig mHidlPlatformConfig;

    public static int getService() {
        if (mHidlPlatformConfig == null) {
            try {
                mHidlPlatformConfig = IHisPlatformConfig.getService(true);
                return 0;
            } catch (Exception e) {
                Log.e("HisensePlatformConfig", "get HisPlatform HIDL Service exception" + e);
            }
        }
        return -1;
    }

    public static String readFile(String str) {
        try {
            getService();
            return mHidlPlatformConfig.readFile(str);
        } catch (Exception e) {
            Log.e("HisensePlatformConfig", "readFile: " + e);
            return "";
        }
    }

    public static int writeFile(String str, String str2, boolean z, boolean z2) {
        try {
            getService();
            return mHidlPlatformConfig.writeFile(str, str2, z, z2);
        } catch (Exception e) {
            Log.e("HisensePlatformConfig", "writeFile: " + e);
            return -1;
        }
    }

    public static int removeFile(String str) {
        try {
            getService();
            return mHidlPlatformConfig.removeFile(str);
        } catch (Exception e) {
            Log.e("HisensePlatformConfig", "removeFile: " + e);
            return -1;
        }
    }

    public static int writeFactoryLogFileAppend(String str, String str2, int i) {
        int iWriteFactoryLogFileAppend = -1;
        try {
            getService();
            Log.d("HisensePlatformConfig", "writeFactoryLogFileAppend start...");
            iWriteFactoryLogFileAppend = mHidlPlatformConfig.writeFactoryLogFileAppend(str, str2, i);
            Log.d("HisensePlatformConfig", "writeFactoryLogFileAppend end...");
            return iWriteFactoryLogFileAppend;
        } catch (Exception e) {
            Log.e("HisensePlatformConfig", "writeFileAppend: " + e);
            return iWriteFactoryLogFileAppend;
        }
    }

    public static String readFactoryLogFile(String str) {
        try {
            getService();
            return mHidlPlatformConfig.readFactoryLogFile(str);
        } catch (Exception e) {
            Log.e("HisensePlatformConfig", "readFactoryLogFile: " + e);
            return "";
        }
    }

    public static int getEmmcType() {
        try {
            getService();
            return mHidlPlatformConfig.getEmmcType();
        } catch (Exception e) {
            Log.e("HisensePlatformConfig", "getEmmcType: " + e);
            return -1;
        }
    }

    public static int setLaserAutoGraphCorrectionUSB(ArrayList<Integer> arrayList) {
        try {
            getService();
            return mHidlPlatformConfig.setLaserAutoGraphCorrectionUSB(arrayList);
        } catch (Exception e) {
            Log.e("HisensePlatformConfig", "setLaserAutoGraphCorrectionUSB: " + e);
            return -1;
        }
    }

    public static int resetGraphCorrectionUSB() {
        try {
            getService();
            return mHidlPlatformConfig.resetGraphCorrectionUSB();
        } catch (Exception e) {
            Log.e("HisensePlatformConfig", "resetGraphCorrectionUSB: " + e);
            return -1;
        }
    }

    public static int writeDrmKey() {
        try {
            getService();
            return mHidlPlatformConfig.writeDrmKey();
        } catch (Exception e) {
            Log.e("HisensePlatformConfig", "writeDrmKey: " + e);
            return -1;
        }
    }

    public static int isFileOrDirExist(String str) {
        try {
            getService();
            return mHidlPlatformConfig.isFileOrDirExist(str);
        } catch (Exception e) {
            Log.e("HisensePlatformConfig", "writeDrmKey: " + e);
            return -1;
        }
    }
}
