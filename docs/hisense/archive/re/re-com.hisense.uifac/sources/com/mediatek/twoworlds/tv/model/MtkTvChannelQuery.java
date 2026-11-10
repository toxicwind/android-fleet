package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvChannelQuery {
    public static final int QUERY_BY_FREQ = 2;
    public static final int QUERY_BY_RFIDX = 1;
    public static final int QUERY_BY_UNKN = 0;
    private static final String TAG = "MtkTvChannelQuery";
    private int queryType = 0;
    private int rfIdx = 0;
    private int frequency = 0;

    public int getQueryType() {
        return this.queryType;
    }

    public void setQueryType(int i) {
        this.queryType = i;
    }

    public int getRfIdx() {
        return this.rfIdx;
    }

    public void setRfIdx(int i) {
        this.rfIdx = i;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }

    public String toString() {
        int i = this.queryType;
        if (i == 1) {
            return TAG + "[RfIdx=" + this.rfIdx + "]";
        }
        if (i == 2) {
            return TAG + "[Frequency=" + this.frequency + "]";
        }
        return TAG + "[UNKNOWN=" + this.queryType + "]";
    }
}
