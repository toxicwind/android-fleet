package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvRecord extends MtkTvRecordBase {
    public static MtkTvRecord mInstance;

    public static MtkTvRecord getInstance() {
        MtkTvRecord mtkTvRecord = mInstance;
        if (mtkTvRecord != null) {
            return mtkTvRecord;
        }
        mInstance = new MtkTvRecord();
        return mInstance;
    }
}
