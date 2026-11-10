package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvTimeFormatBase {
    public static final int SECONDS_PER_HOUR = 3600;
    public static final String TAG = "MtkTvTimeFormat";
    public int hour;
    public boolean isDstOn;
    public boolean isGmt;
    public int minute;
    public int month;
    public int monthDay;
    public int second;
    public int weekDay;
    public int year;

    public MtkTvTimeFormatBase() {
        this.second = 0;
        this.minute = 0;
        this.hour = 0;
        this.monthDay = 1;
        this.month = 0;
        this.year = 1970;
        this.weekDay = 0;
        this.isGmt = true;
    }

    public MtkTvTimeFormatBase(MtkTvTimeFormatBase mtkTvTimeFormatBase) {
        set(mtkTvTimeFormatBase);
    }

    public void setByUtc(long j) {
        TVNativeWrapper.convertUTCSecToDTG_native(j, this);
    }

    public void setByUtcAndConvertToLocalTime(long j) {
        TVNativeWrapper.convertMillisToLocalTime_native(j, this);
    }

    public void set(MtkTvTimeFormatBase mtkTvTimeFormatBase) {
        this.second = mtkTvTimeFormatBase.second;
        this.minute = mtkTvTimeFormatBase.minute;
        this.hour = mtkTvTimeFormatBase.hour;
        this.monthDay = mtkTvTimeFormatBase.monthDay;
        this.month = mtkTvTimeFormatBase.month;
        this.year = mtkTvTimeFormatBase.year;
        this.weekDay = mtkTvTimeFormatBase.weekDay;
        this.isGmt = mtkTvTimeFormatBase.isGmt;
    }

    public void set(int i, int i2, int i3, int i4, int i5, int i6) {
        this.second = i;
        this.minute = i2;
        this.hour = i3;
        this.monthDay = i4;
        this.month = i5;
        this.year = i6;
    }

    public long toSeconds() {
        return TVNativeWrapper.convertDTGToSeconds_native(this);
    }

    public void print(String str) {
        Log.d(str, "" + this.year + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING + this.month + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING + this.monthDay + " " + this.hour + ":" + this.minute + ":" + this.second + "  weekofday:" + this.weekDay + " isDst:" + this.isDstOn + "   isGmt:" + this.isGmt);
    }

    public long toMillis() {
        return TVNativeWrapper.convertLocalTimeToMillis_native(this);
    }

    public void set(long j) {
        setByUtcAndConvertToLocalTime(j);
    }
}
