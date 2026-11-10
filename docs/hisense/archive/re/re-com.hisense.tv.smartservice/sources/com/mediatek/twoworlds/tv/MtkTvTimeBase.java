package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import com.mediatek.twoworlds.tv.model.MtkTvTimeRawDataBase;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvTimeBase {
    public static final int MTK_TV_TIME_CVT_TYPE_BRDCST_LOCAL_TO_BRDCST_UTC = 6;
    public static final int MTK_TV_TIME_CVT_TYPE_BRDCST_LOCAL_TO_SYS_UTC = 2;
    public static final int MTK_TV_TIME_CVT_TYPE_BRDCST_UTC_TO_BRDCST_LOCAL = 5;
    public static final int MTK_TV_TIME_CVT_TYPE_BRDCST_UTC_TO_SYS_UTC = 1;
    public static final int MTK_TV_TIME_CVT_TYPE_SYS_UTC_TO_BRDCST_LOCAL = 4;
    public static final int MTK_TV_TIME_CVT_TYPE_SYS_UTC_TO_BRDCST_UTC = 3;
    public static final int MTK_TV_TIME_NOT_SYNC = 0;
    public static final int MTK_TV_TIME_SLEEP_TIMER_120_MIN = 6;
    public static final int MTK_TV_TIME_SLEEP_TIMER_15_MIN = 1;
    public static final int MTK_TV_TIME_SLEEP_TIMER_30_MIN = 2;
    public static final int MTK_TV_TIME_SLEEP_TIMER_45_MIN = 3;
    public static final int MTK_TV_TIME_SLEEP_TIMER_60_MIN = 4;
    public static final int MTK_TV_TIME_SLEEP_TIMER_90_MIN = 5;
    public static final int MTK_TV_TIME_SLEEP_TIMER_OFF = 0;
    public static final int MTK_TV_TIME_SYNC_FROM_NTP = 2;
    public static final int MTK_TV_TIME_SYNC_FROM_TS = 1;
    public static final String TAG = "MtkTvTime";
    public static int mLastDtCond = -1;

    public int convertTime(int i, MtkTvTimeFormatBase mtkTvTimeFormatBase, MtkTvTimeFormatBase mtkTvTimeFormatBase2) {
        return TVNativeWrapper.convertTime_native(i, mtkTvTimeFormatBase, mtkTvTimeFormatBase2);
    }

    public MtkTvTimeRawDataBase getBrdcstRawData() {
        return TVNativeWrapper.getBrdcstRawData_native();
    }

    public MtkTvTimeFormatBase getBroadcastLocalTime() {
        Log.v(TAG, "getBroadcastLocalTime \n");
        MtkTvTimeFormatBase mtkTvTimeFormatBase = new MtkTvTimeFormatBase();
        long broadcastTime_native = TVNativeWrapper.getBroadcastTime_native(mtkTvTimeFormatBase);
        Log.v(TAG, "broadcastTime in seconds: " + broadcastTime_native + "\n");
        mtkTvTimeFormatBase.setByUtcAndConvertToLocalTime(broadcastTime_native);
        return mtkTvTimeFormatBase;
    }

    public MtkTvTimeFormatBase getBroadcastTime() {
        MtkTvTimeFormatBase mtkTvTimeFormatBase = new MtkTvTimeFormatBase();
        long broadcastTime_native = TVNativeWrapper.getBroadcastTime_native(mtkTvTimeFormatBase);
        Log.d(TAG, "broadcastTime in seconds: " + broadcastTime_native + "\n");
        mtkTvTimeFormatBase.set(broadcastTime_native);
        return mtkTvTimeFormatBase;
    }

    public long getBroadcastTimeInUtcSeconds() {
        long broadcastTime_native = TVNativeWrapper.getBroadcastTime_native(new MtkTvTimeFormatBase());
        Log.d(TAG, "broadcastTime in seconds: " + broadcastTime_native);
        return broadcastTime_native;
    }

    public MtkTvTimeFormatBase getBroadcastUtcTime() {
        MtkTvTimeFormatBase mtkTvTimeFormatBase = new MtkTvTimeFormatBase();
        TVNativeWrapper.getBroadcastTime_native(mtkTvTimeFormatBase);
        Log.d(TAG, "broadcastTime in utc with MtkTvTimeFormatBase instance: \n");
        return mtkTvTimeFormatBase;
    }

    public long getCurrentTimeInUtcMilliSeconds() {
        long utcTime_native = TVNativeWrapper.getUtcTime_native();
        Log.d(TAG, "getTime milliSeconds:" + utcTime_native);
        return utcTime_native;
    }

    public long getCurrentTimeInUtcSeconds() {
        long utcTime_native = TVNativeWrapper.getUtcTime_native() / 1000;
        Log.d(TAG, "getTime seconds:" + utcTime_native);
        return utcTime_native;
    }

    public int getDst() {
        return (int) TVNativeWrapper.getTimeOffset_native();
    }

    public MtkTvTimeFormatBase getLocalTime() {
        long utcTime_native = TVNativeWrapper.getUtcTime_native() / 1000;
        MtkTvTimeFormatBase mtkTvTimeFormatBase = new MtkTvTimeFormatBase();
        mtkTvTimeFormatBase.set(utcTime_native);
        return mtkTvTimeFormatBase;
    }

    public int getSleepTimer() {
        return getSleepTimer(true);
    }

    public int getSleepTimerRemainingTime() {
        Log.d(TAG, "getSleepTimerRemainingTime \n");
        return TVNativeWrapper.getSleepTimerRemainingTime_native();
    }

    public int getTimeSyncSource() {
        Log.d(TAG, "getTimeSyncSource ");
        return TVNativeWrapper.getTimeSyncSource_native();
    }

    public long getTimeZone() {
        Log.d(TAG, "getTimeZone ");
        return TVNativeWrapper.getTimeZone_native();
    }

    public int getTotalOperationTime() {
        Log.d(TAG, "totalOperationTime \n");
        return TVNativeWrapper.getConfigValue_native(-1, MtkTvConfigTypeBase.CFG_MISC_TOTAL_OP_TIME);
    }

    public boolean isDstEditable() {
        Log.d(TAG, "isDstEditable \n");
        return TVNativeWrapper.isTimeOffsetEditable_native();
    }

    public boolean isLocalTimeEditable() {
        Log.d(TAG, "isLocalTimeEditable \n");
        return TVNativeWrapper.isTimeEditable_native();
    }

    public boolean isReceivedTOT() {
        Log.d(TAG, "isReceivedTOT \n");
        return TVNativeWrapper.isReceivedTOT_native();
    }

    public boolean isTimeZoneEditable() {
        Log.d(TAG, "isTimeZoneEditable ");
        return TVNativeWrapper.isTimeZoneEditable_native();
    }

    public void setCurrentTimeInUtcSeconds(long j) {
        Log.d(TAG, "setTime seconds = " + j);
        TVNativeWrapper.setUtcTime_native(j);
    }

    public void setDst(int i) {
        a.b("setDst = ", i, TAG);
        TVNativeWrapper.setTimeOffset_native(i);
    }

    public void setLocalTime(MtkTvTimeFormatBase mtkTvTimeFormatBase) {
        Log.d(TAG, "setLocalTime \n");
        long millis = mtkTvTimeFormatBase.toMillis();
        Log.d(TAG, "setTime:" + millis + "\n");
        TVNativeWrapper.setUtcTime_native(millis);
    }

    public void setLocalTimeZone(String str) {
        TVNativeWrapper.setLocalTimeZone_native(str);
        Log.d(TAG, "setLocalTimeZone: " + str);
    }

    public void setSleepTimer(int i) {
        a.a("setSleepTimer to:", i, " \n", TAG);
        if (i < 0 || i > 6) {
            throw new IllegalArgumentException("arg is out of range");
        }
        TVNativeWrapper.setSleepTimer_native(i);
    }

    public void setTimeSyncSource(int i) {
        Log.d(TAG, "setTimeSyncSource ");
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("arg is out of range");
        }
        TVNativeWrapper.setTimeSyncSource_native(i);
    }

    public void setTimeZone(long j) {
        Log.d(TAG, "setTimeZone ");
        TVNativeWrapper.setTimeZone_native(j);
    }

    public int getSleepTimer(boolean z) {
        int sleepTimer_native = TVNativeWrapper.getSleepTimer_native(z);
        a.a("sleepTimer :", sleepTimer_native, "\n", TAG);
        return sleepTimer_native;
    }
}
