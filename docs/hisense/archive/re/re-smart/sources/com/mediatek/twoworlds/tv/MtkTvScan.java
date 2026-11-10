package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvScan extends MtkTvScanBase {
    public static MtkTvScan mtkTvScan;

    public static MtkTvScan getInstance() {
        MtkTvScan mtkTvScan2 = mtkTvScan;
        if (mtkTvScan2 != null) {
            return mtkTvScan2;
        }
        mtkTvScan = new MtkTvScan();
        return mtkTvScan;
    }
}
