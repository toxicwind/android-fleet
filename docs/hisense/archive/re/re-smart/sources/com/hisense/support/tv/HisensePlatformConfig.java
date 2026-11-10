package com.hisense.support.tv;

import android.os.RemoteException;
import android.util.Log;
import f.a.a.a.a.a.a;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class HisensePlatformConfig {
    public static final String TAG = "HisensePlatformConfig";
    public static a mHidlPlatformConfig = null;
    public static ArrayList<Integer> mItemData = null;
    public static int mReturnVal = -1;
    public static long mSize = -1;
    public static String mVersion = "";

    public HisensePlatformConfig() {
        if (mHidlPlatformConfig == null) {
            try {
                mHidlPlatformConfig = a.getService(true);
            } catch (Exception e2) {
                b.a.a.a.a.c("get HisPlatform HIDL Service exception", e2, TAG);
            }
        }
    }

    public static int checkPlayready40KeyBox() {
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).a();
        } catch (Exception e2) {
            b.a.a.a.a.c("checkPlayready40KeyBox: ", e2, TAG);
            return -1;
        }
    }

    public static int checkWideVineKeyBox() {
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).b();
        } catch (Exception e2) {
            b.a.a.a.a.c("checkWideVineKeyBox: ", e2, TAG);
            return -1;
        }
    }

    public static int factoryWriteAttestationKey(String str) {
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).a(str);
        } catch (Exception e2) {
            b.a.a.a.a.c("factoryWriteAttestationKey: ", e2, TAG);
            return -1;
        }
    }

    public static String getEmmcCid() {
        getService();
        try {
            ((a.C0040a) mHidlPlatformConfig).a(new a.b() { // from class: com.hisense.support.tv.HisensePlatformConfig.4
                @Override // f.a.a.a.a.a.a.b
                public void onValues(int i, String str) {
                    if (i == 0) {
                        HisensePlatformConfig.mVersion = str;
                    }
                }
            });
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        return mVersion;
    }

    public static String getEmmcFwrev() {
        getService();
        try {
            ((a.C0040a) mHidlPlatformConfig).a(new a.c() { // from class: com.hisense.support.tv.HisensePlatformConfig.6
                @Override // f.a.a.a.a.a.a.c
                public void onValues(int i, String str) {
                    if (i == 0) {
                        HisensePlatformConfig.mVersion = str;
                    }
                }
            });
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        return mVersion;
    }

    public static String getEmmcHwrev() {
        getService();
        try {
            ((a.C0040a) mHidlPlatformConfig).a(new a.d() { // from class: com.hisense.support.tv.HisensePlatformConfig.5
                @Override // f.a.a.a.a.a.a.d
                public void onValues(int i, String str) {
                    if (i == 0) {
                        HisensePlatformConfig.mVersion = str;
                    }
                }
            });
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        return mVersion;
    }

    public static int[] getEmmcLifeTime() {
        int[] iArr = new int[3];
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).c();
        } catch (Exception e2) {
            b.a.a.a.a.c("getEmmcLifeTime: ", e2, TAG);
            return iArr;
        }
    }

    public static ArrayList<Integer> getEmmcSmartReport() {
        getService();
        try {
            ((a.C0040a) mHidlPlatformConfig).a(new a.h() { // from class: com.hisense.support.tv.HisensePlatformConfig.2
                @Override // f.a.a.a.a.a.a.h
                public void onValues(ArrayList<Integer> arrayList, int i) {
                    if (i != 0 || arrayList == null || arrayList.size() <= 0) {
                        b.a.a.a.a.b("getemmcsmartreport ret =", i, HisensePlatformConfig.TAG);
                    } else {
                        HisensePlatformConfig.mItemData = (ArrayList) arrayList.clone();
                    }
                }
            });
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        return mItemData;
    }

    public static int getEmmcType() {
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).d();
        } catch (Exception e2) {
            b.a.a.a.a.c("getEmmcType: ", e2, TAG);
            return -1;
        }
    }

    public static long getEmmcWrittenSize() {
        long j = -1;
        try {
            getService();
            long jE = ((a.C0040a) mHidlPlatformConfig).e();
            if (jE < 0) {
                return jE;
            }
            j = jE >> 1;
            Log.d(TAG, "getEmmcWrittenSize, size is " + j + " KB.\n");
            return j;
        } catch (Exception e2) {
            b.a.a.a.a.c("getEmmcWrittenSize: ", e2, TAG);
            return j;
        }
    }

    public static long getEmmcWrittenSizeByDriver() {
        getService();
        try {
            ((a.C0040a) mHidlPlatformConfig).a(new a.e() { // from class: com.hisense.support.tv.HisensePlatformConfig.3
                @Override // f.a.a.a.a.a.a.e
                public void onValues(int i, long j) {
                    if (i == 0) {
                        HisensePlatformConfig.mSize = j;
                    }
                }
            });
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        return mSize;
    }

    public static String getKernelPanicFileDir() {
        getService();
        try {
            ((a.C0040a) mHidlPlatformConfig).a(new a.f() { // from class: com.hisense.support.tv.HisensePlatformConfig.7
                @Override // f.a.a.a.a.a.a.f
                public void onValues(int i, String str) {
                    if (i == 0) {
                        HisensePlatformConfig.mVersion = str;
                    }
                }
            });
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        return mVersion;
    }

    public static ArrayList<Integer> getLaserManualGraphCorrectionUSB(int i) {
        try {
            getService();
            ((a.C0040a) mHidlPlatformConfig).a(i, new a.g() { // from class: com.hisense.support.tv.HisensePlatformConfig.1
                @Override // f.a.a.a.a.a.a.g
                public void onValues(ArrayList<Integer> arrayList, int i2) {
                    HisensePlatformConfig.mReturnVal = i2;
                    HisensePlatformConfig.mItemData = (ArrayList) arrayList.clone();
                    b.a.a.a.a.b(b.a.a.a.a.a("getLaserManualGraphCorrectionUSB ret ="), HisensePlatformConfig.mReturnVal, HisensePlatformConfig.TAG);
                }
            });
        } catch (Exception e2) {
            b.a.a.a.a.c("getLaserManualGraphCorrectionUSB: ", e2, TAG);
        }
        return mItemData;
    }

    public static int getLogoHotelFromTv2Udisk(String str) {
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).b(str);
        } catch (Exception e2) {
            b.a.a.a.a.c("getLogoHotelFromTv2Udisk: ", e2, TAG);
            return -1;
        }
    }

    public static int getService() {
        if (mHidlPlatformConfig == null) {
            try {
                mHidlPlatformConfig = a.getService(true);
                return 0;
            } catch (Exception e2) {
                b.a.a.a.a.c("get HisPlatform HIDL Service exception", e2, TAG);
            }
        }
        return -1;
    }

    public static int getStrPowerOffCount() {
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).f();
        } catch (Exception e2) {
            b.a.a.a.a.c("getStrPowerOffCount: ", e2, TAG);
            return -1;
        }
    }

    public static int getUsbUpgradeFlag(int i) {
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).a(i);
        } catch (Exception e2) {
            b.a.a.a.a.c("GetUsbUpgradeFlag: ", e2, TAG);
            return -1;
        }
    }

    public static int isFileOrDirExist(String str) {
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).c(str);
        } catch (Exception e2) {
            b.a.a.a.a.c("writeDrmKey: ", e2, TAG);
            return -1;
        }
    }

    public static String readFactoryLogFile(String str) {
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).d(str);
        } catch (Exception e2) {
            b.a.a.a.a.c("readFactoryLogFile: ", e2, TAG);
            return "";
        }
    }

    public static String readFile(String str) {
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).e(str);
        } catch (Exception e2) {
            b.a.a.a.a.c("readFile: ", e2, TAG);
            return "";
        }
    }

    public static int removeFile(String str) {
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).f(str);
        } catch (Exception e2) {
            b.a.a.a.a.c("removeFile: ", e2, TAG);
            return -1;
        }
    }

    public static int resetGraphCorrectionUSB() {
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).g();
        } catch (Exception e2) {
            b.a.a.a.a.c("resetGraphCorrectionUSB: ", e2, TAG);
            return -1;
        }
    }

    public static int saveGraphCorrectionUSB() {
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).h();
        } catch (Exception e2) {
            b.a.a.a.a.c("saveGraphCorrectionUSB: ", e2, TAG);
            return -1;
        }
    }

    public static int setLaserAutoGraphCorrectionUSB(ArrayList<Integer> arrayList) {
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).a(arrayList);
        } catch (Exception e2) {
            b.a.a.a.a.c("setLaserAutoGraphCorrectionUSB: ", e2, TAG);
            return -1;
        }
    }

    public static int setLaserManualGraphCorrectionUSB(ArrayList<Integer> arrayList) {
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).b(arrayList);
        } catch (Exception e2) {
            b.a.a.a.a.c("setLaserManualGraphCorrectionUSB: ", e2, TAG);
            return -1;
        }
    }

    public static int setLogoHotelFromUdisk2Tv(String str) {
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).g(str);
        } catch (Exception e2) {
            b.a.a.a.a.c("setLogoHotelFromUdisk2Tv: ", e2, TAG);
            return -1;
        }
    }

    public static int startLaserManualGraphCorrectionUSB() {
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).i();
        } catch (Exception e2) {
            b.a.a.a.a.c("startLaserManualGraphCorrectionUSB: ", e2, TAG);
            return -1;
        }
    }

    public static int writeDrmKey() {
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).j();
        } catch (Exception e2) {
            b.a.a.a.a.c("writeDrmKey: ", e2, TAG);
            return -1;
        }
    }

    public static int writeFactoryLogFileAppend(String str, String str2, int i) {
        int iA = -1;
        try {
            getService();
            Log.d(TAG, "writeFactoryLogFileAppend start...");
            iA = ((a.C0040a) mHidlPlatformConfig).a(str, str2, i);
            Log.d(TAG, "writeFactoryLogFileAppend end...");
            return iA;
        } catch (Exception e2) {
            b.a.a.a.a.c("writeFileAppend: ", e2, TAG);
            return iA;
        }
    }

    public static int writeFile(String str, String str2, boolean z, boolean z2) {
        try {
            getService();
            return ((a.C0040a) mHidlPlatformConfig).a(str, str2, z, z2);
        } catch (Exception e2) {
            b.a.a.a.a.c("writeFile: ", e2, TAG);
            return -1;
        }
    }
}
