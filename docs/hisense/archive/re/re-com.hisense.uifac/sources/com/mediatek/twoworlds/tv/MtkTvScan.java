package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvScan extends MtkTvScanBase {
    private static MtkTvScan mtkTvScan;

    private MtkTvScan() {
    }

    public static MtkTvScan getInstance() {
        MtkTvScan mtkTvScan2 = mtkTvScan;
        if (mtkTvScan2 != null) {
            return mtkTvScan2;
        }
        MtkTvScan mtkTvScan3 = new MtkTvScan();
        mtkTvScan = mtkTvScan3;
        return mtkTvScan3;
    }
}
