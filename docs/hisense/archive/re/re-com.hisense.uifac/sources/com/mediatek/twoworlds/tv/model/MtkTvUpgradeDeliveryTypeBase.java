package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public enum MtkTvUpgradeDeliveryTypeBase {
    USB(0),
    INTERNET(1),
    OAD(2),
    ULI(3),
    UNKNOWN(4);

    private int mDeliveryValue;

    MtkTvUpgradeDeliveryTypeBase(int i) {
        this.mDeliveryValue = i;
    }

    public int getDeliveryTypeValue() {
        return this.mDeliveryValue;
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = this.mDeliveryValue;
        if (i == 0) {
            return "USB";
        }
        if (i == 1) {
            return "Internet";
        }
        if (i != 2) {
            return i != 3 ? "Unknown" : "ULI";
        }
        return "OAD";
    }
}
