package com.mediatek.twoworlds.tv.model;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvCecTimeInfoBase {
    private static final String TAG = "MtkTvCecTimeInfo";
    protected int dayOfMonth;
    protected int duration;
    protected int monthOfYear;
    protected int recSequence;
    protected int startTime;

    public MtkTvCecTimeInfoBase() {
        Log.d(TAG, "MtkTvCecTimeInfo Constructor\n");
    }

    public int getDayOfMonth() {
        return this.dayOfMonth;
    }

    protected void setDayOfMonth(int i) {
        this.dayOfMonth = i;
    }

    public int getMonthOfYear() {
        return this.monthOfYear;
    }

    protected void setMonthOfYear(int i) {
        this.monthOfYear = i;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public void setStartTime(int i) {
        this.startTime = i;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public int getRecSequence() {
        return this.recSequence;
    }

    public void setRecSequence(int i) {
        this.recSequence = i;
    }
}
