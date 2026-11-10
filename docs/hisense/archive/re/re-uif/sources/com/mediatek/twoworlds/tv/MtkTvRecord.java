package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvRecord extends MtkTvRecordBase {
    private static MtkTvRecord mInstance;

    private MtkTvRecord() {
    }

    public static MtkTvRecord getInstance() {
        MtkTvRecord mtkTvRecord = mInstance;
        if (mtkTvRecord != null) {
            return mtkTvRecord;
        }
        MtkTvRecord mtkTvRecord2 = new MtkTvRecord();
        mInstance = mtkTvRecord2;
        return mtkTvRecord2;
    }
}
