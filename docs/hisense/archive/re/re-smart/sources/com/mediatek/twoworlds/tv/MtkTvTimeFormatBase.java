package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.google.firebase.installations.Utils;

/* JADX INFO: loaded from: classes2.dex */
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

    public void print(String str) {
        StringBuilder sbA = a.a("");
        sbA.append(this.year);
        sbA.append(MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING);
        sbA.append(this.month);
        sbA.append(MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING);
        sbA.append(this.monthDay);
        sbA.append(" ");
        sbA.append(this.hour);
        sbA.append(Utils.APP_ID_IDENTIFICATION_SUBSTRING);
        sbA.append(this.minute);
        sbA.append(Utils.APP_ID_IDENTIFICATION_SUBSTRING);
        sbA.append(this.second);
        sbA.append("  weekofday:");
        sbA.append(this.weekDay);
        sbA.append(" isDst:");
        sbA.append(this.isDstOn);
        sbA.append("   isGmt:");
        sbA.append(this.isGmt);
        Log.d(str, sbA.toString());
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

    public void setByUtc(long j) {
        TVNativeWrapper.convertUTCSecToDTG_native(j, this);
    }

    public void setByUtcAndConvertToLocalTime(long j) {
        TVNativeWrapper.convertMillisToLocalTime_native(j, this);
    }

    public long toMillis() {
        return TVNativeWrapper.convertLocalTimeToMillis_native(this);
    }

    public long toSeconds() {
        return TVNativeWrapper.convertDTGToSeconds_native(this);
    }

    public void set(int i, int i2, int i3, int i4, int i5, int i6) {
        this.second = i;
        this.minute = i2;
        this.hour = i3;
        this.monthDay = i4;
        this.month = i5;
        this.year = i6;
    }

    public MtkTvTimeFormatBase(MtkTvTimeFormatBase mtkTvTimeFormatBase) {
        set(mtkTvTimeFormatBase);
    }

    public void set(long j) {
        setByUtcAndConvertToLocalTime(j);
    }
}
