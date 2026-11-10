package com.mediatek.twoworlds.tv.model;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
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

    public void print(String str) {
        StringBuilder sbA = a.a("    isTotLtoMatched :");
        sbA.append(this.isTotLtoMatched);
        sbA.append("    localTimeOffset:");
        sbA.append(this.localTimeOffset);
        sbA.append("    nextTimeOffset:");
        sbA.append(this.nextTimeOffset);
        sbA.append("    timeOfChanage:");
        sbA.append(this.timeOfChanage);
        sbA.append("    isBrdcstTimeAvailable:");
        sbA.append(this.isBrdcstTimeAvailable);
        Log.d(str, sbA.toString());
    }

    public MtkTvTimeRawDataBase(boolean z, boolean z2, long j, long j2, long j3) {
        this.isTotLtoMatched = z;
        this.localTimeOffset = j;
        this.nextTimeOffset = j2;
        this.timeOfChanage = j3;
        this.isBrdcstTimeAvailable = z2;
    }
}
