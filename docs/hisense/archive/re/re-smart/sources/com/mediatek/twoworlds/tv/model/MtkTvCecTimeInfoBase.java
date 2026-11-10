package com.mediatek.twoworlds.tv.model;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvCecTimeInfoBase {
    public static final String TAG = "MtkTvCecTimeInfo";
    public int dayOfMonth;
    public int duration;
    public int monthOfYear;
    public int recSequence;
    public int startTime;

    public MtkTvCecTimeInfoBase() {
        Log.d(TAG, "MtkTvCecTimeInfo Constructor\n");
    }

    public int getDayOfMonth() {
        return this.dayOfMonth;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getMonthOfYear() {
        return this.monthOfYear;
    }

    public int getRecSequence() {
        return this.recSequence;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public void setDayOfMonth(int i) {
        this.dayOfMonth = i;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public void setMonthOfYear(int i) {
        this.monthOfYear = i;
    }

    public void setRecSequence(int i) {
        this.recSequence = i;
    }

    public void setStartTime(int i) {
        this.startTime = i;
    }
}
