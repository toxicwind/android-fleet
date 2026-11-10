package com.mediatek.twoworlds.tv.model;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvChannelQuery {
    public static final int QUERY_BY_FREQ = 2;
    public static final int QUERY_BY_RFIDX = 1;
    public static final int QUERY_BY_UNKN = 0;
    public static final String TAG = "MtkTvChannelQuery";
    public int queryType = 0;
    public int rfIdx = 0;
    public int frequency = 0;

    public int getFrequency() {
        return this.frequency;
    }

    public int getQueryType() {
        return this.queryType;
    }

    public int getRfIdx() {
        return this.rfIdx;
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }

    public void setQueryType(int i) {
        this.queryType = i;
    }

    public void setRfIdx(int i) {
        this.rfIdx = i;
    }

    public String toString() {
        int i = this.queryType;
        if (i != 1) {
            return i != 2 ? a.a(a.b(TAG, "[UNKNOWN="), this.queryType, "]") : a.a(a.b(TAG, "[Frequency="), this.frequency, "]");
        }
        return a.a(a.b(TAG, "[RfIdx="), this.rfIdx, "]");
    }
}
