package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.common.MtkTvExceptionBase;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvOADBase {
    public static final String TAG = "MtkTvOAD";
    public static int dataType = 0;
    public static String mscheduleInfo = null;
    public static final int typeOtherInfo = 3;
    public static final int typeScheduleInfo = 1;
    public static final int typeUriInfo = 2;

    public MtkTvOADBase() {
        Log.d(TAG, "MtkTvOADBase object created");
    }

    public int acceptRestart() {
        Log.d(TAG, "+ acceptRestart");
        int iAcceptRestart_native = 0;
        try {
            synchronized (this) {
                Log.d(TAG, "acceptRestart_native begin");
                iAcceptRestart_native = TVNativeWrapper.acceptRestart_native();
                if (iAcceptRestart_native != 0) {
                    throw new MtkTvExceptionBase(iAcceptRestart_native, "TVNativeWrapper acceptRestart_native fail");
                }
            }
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        Log.d(TAG, "- acceptRestart");
        return iAcceptRestart_native;
    }

    public int acceptSchedule() {
        Log.d(TAG, "+ acceptSchedule");
        int iAcceptSchedule_native = 0;
        try {
            synchronized (this) {
                Log.d(TAG, "acceptSchedule_native begin");
                iAcceptSchedule_native = TVNativeWrapper.acceptSchedule_native();
                if (iAcceptSchedule_native != 0) {
                    throw new MtkTvExceptionBase(iAcceptSchedule_native, "TVNativeWrapper acceptSchedule_native fail");
                }
            }
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        Log.d(TAG, "- acceptSchedule");
        return iAcceptSchedule_native;
    }

    public int clearOadVersion() {
        Log.d(TAG, "+ getPowerOnStatus");
        int iClearOadVersion_native = 0;
        try {
            synchronized (this) {
                Log.d(TAG, "clearOadVersion_native begin");
                iClearOadVersion_native = TVNativeWrapper.clearOadVersion_native();
                if (iClearOadVersion_native != 0) {
                    throw new MtkTvExceptionBase(iClearOadVersion_native, "TVNativeWrapper clearOadVersion_native fail");
                }
            }
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        Log.d(TAG, "- clearOadVersion");
        return iClearOadVersion_native;
    }

    public String getDataInfo(int i) {
        Log.d(TAG, "+ startManualDetect");
        try {
            synchronized (this) {
                Log.d(TAG, "getDataInfo_native begin");
                int dataInfo_native = TVNativeWrapper.getDataInfo_native(this, i);
                if (dataInfo_native != 0) {
                    throw new MtkTvExceptionBase(dataInfo_native, "TVNativeWrapper getDataInfo_native fail");
                }
            }
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        StringBuilder sbA = a.a("- getDataInfo");
        sbA.append(mscheduleInfo);
        Log.d(TAG, sbA.toString());
        return mscheduleInfo;
    }

    public int getPowerOnStatus() {
        Log.d(TAG, "+ getPowerOnStatus");
        int powerOnStatus_native = 0;
        try {
            synchronized (this) {
                Log.d(TAG, "getPowerOnStatus_native begin");
                powerOnStatus_native = TVNativeWrapper.getPowerOnStatus_native();
                if (powerOnStatus_native != 0) {
                    throw new MtkTvExceptionBase(powerOnStatus_native, "TVNativeWrapper getPowerOnStatus_native fail");
                }
            }
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        Log.d(TAG, "- getPowerOnStatus");
        return powerOnStatus_native;
    }

    public int getScheduleInfo() {
        Log.d(TAG, "+ startManualDetect");
        int scheduleInfo_native = 0;
        try {
            synchronized (this) {
                Log.d(TAG, "getScheduleInfo_native begin");
                scheduleInfo_native = TVNativeWrapper.getScheduleInfo_native(this);
                if (scheduleInfo_native != 0) {
                    throw new MtkTvExceptionBase(scheduleInfo_native, "TVNativeWrapper getScheduleInfo_native fail");
                }
            }
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        Log.d(TAG, "- getScheduleInfo");
        return scheduleInfo_native;
    }

    public int remindMeLater() {
        Log.d(TAG, "+ remindMeLater");
        int iRemindMeLater_native = 0;
        try {
            synchronized (this) {
                Log.d(TAG, "remindMeLater_native begin");
                iRemindMeLater_native = TVNativeWrapper.remindMeLater_native();
                if (iRemindMeLater_native != 0) {
                    throw new MtkTvExceptionBase(iRemindMeLater_native, "TVNativeWrapper remindMeLater_native fail");
                }
            }
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        Log.d(TAG, "- remindMeLater");
        return iRemindMeLater_native;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v6, types: [int] */
    public int setAutoDownload(boolean z) throws Throwable {
        ?? r5;
        Log.d(TAG, "+ setAutoDownload");
        boolean z2 = false;
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "setAutoDownload_native begin");
                        int autoDownload_native = TVNativeWrapper.setAutoDownload_native(z);
                        if (autoDownload_native != 0) {
                            throw new MtkTvExceptionBase(autoDownload_native, "TVNativeWrapper setAutoDownload_native fail");
                        }
                        r5 = autoDownload_native;
                        Log.d(TAG, "- setAutoDownload");
                        return r5;
                    } catch (Throwable th) {
                        th = th;
                        z = false;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e2) {
                            z2 = z;
                            e = e2;
                            e.printStackTrace();
                            r5 = z2;
                            Log.d(TAG, "- setAutoDownload");
                            return r5;
                        }
                    }
                }
            } catch (MtkTvExceptionBase e3) {
                e = e3;
                e.printStackTrace();
                r5 = z2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x005e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int setPkgPathname(java.lang.String r5) throws java.lang.Throwable {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "+ setPkgPathname,pathname:"
            r0.append(r1)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "MtkTvOAD"
            android.util.Log.d(r1, r0)
            java.lang.String r0 = "/"
            int r0 = r5.lastIndexOf(r0)
            r1 = 0
            java.lang.String r0 = r5.substring(r1, r0)
            java.lang.String r2 = "filePathStr:"
            java.lang.String r3 = "MtkTvOAD"
            b.a.a.a.a.c(r2, r0, r3)
            monitor-enter(r4)     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L60
            java.lang.String r2 = "MtkTvOAD"
            java.lang.String r3 = "setPkgPathname_native begin"
            android.util.Log.d(r2, r3)     // Catch: java.lang.Throwable -> L59
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L59
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L59
            boolean r0 = r2.exists()     // Catch: java.lang.Throwable -> L59
            if (r0 != 0) goto L45
            java.lang.String r5 = "MtkTvOAD"
            java.lang.String r0 = "the file path is not exist,set PKG Pathname Fail!"
            android.util.Log.e(r5, r0)     // Catch: java.lang.Throwable -> L59
            r5 = -1
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L59
            return r5
        L45:
            int r5 = com.mediatek.twoworlds.tv.TVNativeWrapper.setPkgPathname_native(r5)     // Catch: java.lang.Throwable -> L59
            if (r5 != 0) goto L4d
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L55
            goto L65
        L4d:
            com.mediatek.twoworlds.tv.common.MtkTvExceptionBase r0 = new com.mediatek.twoworlds.tv.common.MtkTvExceptionBase     // Catch: java.lang.Throwable -> L55
            java.lang.String r1 = "TVNativeWrapper setPkgPathname fail"
            r0.<init>(r5, r1)     // Catch: java.lang.Throwable -> L55
            throw r0     // Catch: java.lang.Throwable -> L55
        L55:
            r0 = move-exception
            r1 = r5
        L57:
            r5 = r4
            goto L5c
        L59:
            r5 = move-exception
            r0 = r5
            goto L57
        L5c:
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L5e
            throw r0     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L60
        L5e:
            r0 = move-exception
            goto L5c
        L60:
            r5 = move-exception
            r5.printStackTrace()
            r5 = r1
        L65:
            java.lang.String r0 = "MtkTvOAD"
            java.lang.String r1 = "- setPkgPathname"
            android.util.Log.d(r0, r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.twoworlds.tv.MtkTvOADBase.setPkgPathname(java.lang.String):int");
    }

    public void setScheduleString(String str) {
        Log.i(TAG, "setScheduleString enterdstr:" + str);
        mscheduleInfo = str;
        Log.i(TAG, "setScheduleString exit");
    }

    public int startDownload() {
        Log.d(TAG, "+ startDownload");
        int iStartDownload_native = 0;
        try {
            synchronized (this) {
                Log.d(TAG, "startDownload_native begin");
                iStartDownload_native = TVNativeWrapper.startDownload_native();
                if (iStartDownload_native != 0) {
                    throw new MtkTvExceptionBase(iStartDownload_native, "TVNativeWrapper startDownload_native fail");
                }
            }
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        Log.d(TAG, "- startDownload");
        return iStartDownload_native;
    }

    public int startFlash() {
        Log.d(TAG, "+ startFlash");
        int iStartFlash_native = 0;
        try {
            synchronized (this) {
                Log.d(TAG, "startFlash_native begin");
                iStartFlash_native = TVNativeWrapper.startFlash_native();
                if (iStartFlash_native != 0) {
                    throw new MtkTvExceptionBase(iStartFlash_native, "TVNativeWrapper startFlash_native fail");
                }
            }
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        Log.d(TAG, "- startFlash");
        return iStartFlash_native;
    }

    public int startJumpChannel() {
        Log.d(TAG, "+ startJumpChannel");
        int iStartJumpChannel_native = 0;
        try {
            synchronized (this) {
                Log.d(TAG, "startJumpChannel_native begin");
                iStartJumpChannel_native = TVNativeWrapper.startJumpChannel_native();
                if (iStartJumpChannel_native != 0) {
                    throw new MtkTvExceptionBase(iStartJumpChannel_native, "TVNativeWrapper startJumpChannel_native fail");
                }
            }
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        Log.d(TAG, "- startJumpChannel");
        return iStartJumpChannel_native;
    }

    public int startManualDetect() {
        Log.d(TAG, "+ startManualDetect");
        int iStartManualDetect_native = 0;
        try {
            synchronized (this) {
                Log.d(TAG, "startManualDetect_native begin");
                iStartManualDetect_native = TVNativeWrapper.startManualDetect_native();
                if (iStartManualDetect_native != 0) {
                    throw new MtkTvExceptionBase(iStartManualDetect_native, "TVNativeWrapper startManualDetect_native fail");
                }
            }
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        Log.d(TAG, "- startManualDetect");
        return iStartManualDetect_native;
    }

    public int stopDownload() {
        Log.d(TAG, "+ stopDownload");
        int iStopDownload_native = 0;
        try {
            synchronized (this) {
                Log.d(TAG, "stopDownload_native begin");
                iStopDownload_native = TVNativeWrapper.stopDownload_native();
                if (iStopDownload_native != 0) {
                    throw new MtkTvExceptionBase(iStopDownload_native, "TVNativeWrapper stopDownload_native fail");
                }
            }
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        Log.d(TAG, "- stopDownload");
        return iStopDownload_native;
    }

    public int stopManualDetect() {
        Log.d(TAG, "+ stopManualDetect");
        int iStopManualDetect_native = 0;
        try {
            synchronized (this) {
                Log.d(TAG, "stopManualDetect_native begin");
                iStopManualDetect_native = TVNativeWrapper.stopManualDetect_native();
                if (iStopManualDetect_native != 0) {
                    throw new MtkTvExceptionBase(iStopManualDetect_native, "TVNativeWrapper stopManualDetect_native fail");
                }
            }
        } catch (MtkTvExceptionBase e2) {
            e2.printStackTrace();
        }
        Log.d(TAG, "- stopManualDetect");
        return iStopManualDetect_native;
    }
}
