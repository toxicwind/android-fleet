package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes2.dex */
public enum MtkTvUpgradeMethodBase {
    PARTITION(0),
    FILE(1);

    public int mMethodValue;

    MtkTvUpgradeMethodBase(int i) {
        this.mMethodValue = i;
    }

    public int getMethodValue() {
        return this.mMethodValue;
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = this.mMethodValue;
        if (i != 0) {
            return i != 1 ? "Unknown" : "File";
        }
        return "Partition";
    }
}
