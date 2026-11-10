package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.common.MtkTvExceptionBase;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvOADBase {
    private static final String TAG = "MtkTvOAD";
    public static int dataType = 0;
    public static String mscheduleInfo = null;
    private static final int typeOtherInfo = 3;
    private static final int typeScheduleInfo = 1;
    private static final int typeUriInfo = 2;

    public void setScheduleString(String str) {
        Log.i(TAG, "setScheduleString enterdstr:" + str);
        mscheduleInfo = str;
        Log.i(TAG, "setScheduleString exit");
    }

    public MtkTvOADBase() {
        Log.d(TAG, "MtkTvOADBase object created");
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
        } catch (MtkTvExceptionBase e) {
            e.printStackTrace();
        }
        Log.d(TAG, "- startManualDetect");
        return iStartManualDetect_native;
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
        } catch (MtkTvExceptionBase e) {
            e.printStackTrace();
        }
        Log.d(TAG, "- getScheduleInfo");
        return scheduleInfo_native;
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
        } catch (MtkTvExceptionBase e) {
            e.printStackTrace();
        }
        Log.d(TAG, "- getDataInfo" + mscheduleInfo);
        return mscheduleInfo;
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
        } catch (MtkTvExceptionBase e) {
            e.printStackTrace();
        }
        Log.d(TAG, "- stopManualDetect");
        return iStopManualDetect_native;
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
        } catch (MtkTvExceptionBase e) {
            e.printStackTrace();
        }
        Log.d(TAG, "- startDownload");
        return iStartDownload_native;
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
        } catch (MtkTvExceptionBase e) {
            e.printStackTrace();
        }
        Log.d(TAG, "- stopDownload");
        return iStopDownload_native;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v3, types: [int] */
    /* JADX WARN: Type inference failed for: r5v7 */
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
                        } catch (MtkTvExceptionBase e) {
                            e = e;
                            z2 = z;
                            e.printStackTrace();
                            r5 = z2;
                            Log.d(TAG, "- setAutoDownload");
                            return r5;
                        }
                    }
                }
            } catch (MtkTvExceptionBase e2) {
                e = e2;
                e.printStackTrace();
                r5 = z2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
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
        } catch (MtkTvExceptionBase e) {
            e.printStackTrace();
        }
        Log.d(TAG, "- startFlash");
        return iStartFlash_native;
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
        } catch (MtkTvExceptionBase e) {
            e.printStackTrace();
        }
        Log.d(TAG, "- acceptRestart");
        return iAcceptRestart_native;
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
        } catch (MtkTvExceptionBase e) {
            e.printStackTrace();
        }
        Log.d(TAG, "- remindMeLater");
        return iRemindMeLater_native;
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
        } catch (MtkTvExceptionBase e) {
            e.printStackTrace();
        }
        Log.d(TAG, "- acceptSchedule");
        return iAcceptSchedule_native;
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
        } catch (MtkTvExceptionBase e) {
            e.printStackTrace();
        }
        Log.d(TAG, "- startJumpChannel");
        return iStartJumpChannel_native;
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
        } catch (MtkTvExceptionBase e) {
            e.printStackTrace();
        }
        Log.d(TAG, "- getPowerOnStatus");
        return powerOnStatus_native;
    }

    public int setPkgPathname(String str) throws Throwable {
        int pkgPathname_native;
        Log.d(TAG, "+ setPkgPathname,pathname:" + str);
        int i = 0;
        String strSubstring = str.substring(0, str.lastIndexOf("/"));
        Log.d(TAG, "filePathStr:" + strSubstring);
        try {
            synchronized (this) {
                try {
                    Log.d(TAG, "setPkgPathname_native begin");
                    if (!new File(strSubstring).exists()) {
                        Log.e(TAG, "the file path is not exist,set PKG Pathname Fail!");
                        return -1;
                    }
                    pkgPathname_native = TVNativeWrapper.setPkgPathname_native(str);
                    try {
                        if (pkgPathname_native != 0) {
                            throw new MtkTvExceptionBase(pkgPathname_native, "TVNativeWrapper setPkgPathname fail");
                        }
                        Log.d(TAG, "- setPkgPathname");
                        return pkgPathname_native;
                    } catch (Throwable th) {
                        i = pkgPathname_native;
                        th = th;
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (MtkTvExceptionBase e) {
            e.printStackTrace();
            pkgPathname_native = i;
        }
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
        } catch (MtkTvExceptionBase e) {
            e.printStackTrace();
        }
        Log.d(TAG, "- clearOadVersion");
        return iClearOadVersion_native;
    }
}
