package com.mediatek.twoworlds.tv.model;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvTimeRawDataBase {
    public static final String TAG = "MtkTvTimeRawDataBase";
    public boolean isBrdcstTimeAvailable;
    public boolean isTotLtoMatched;
    public long localTimeOffset;
    public long nextTimeOffset;
    public long timeOfChanage;

    public MtkTvTimeRawDataBase() {
        this.isTotLtoMatched = false;
        this.localTimeOffset = 0L;
        this.nextTimeOffset = 0L;
        this.timeOfChanage = 0L;
        this.isBrdcstTimeAvailable = false;
    }

    public MtkTvTimeRawDataBase(boolean z, boolean z2, long j, long j2, long j3) {
        this.isTotLtoMatched = z;
        this.localTimeOffset = j;
        this.nextTimeOffset = j2;
        this.timeOfChanage = j3;
        this.isBrdcstTimeAvailable = z2;
    }

    public void print(String str) {
        Log.d(str, "    isTotLtoMatched :" + this.isTotLtoMatched + "    localTimeOffset:" + this.localTimeOffset + "    nextTimeOffset:" + this.nextTimeOffset + "    timeOfChanage:" + this.timeOfChanage + "    isBrdcstTimeAvailable:" + this.isBrdcstTimeAvailable);
    }
}
