package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvNetwork extends MtkTvNetworkBase {
    public static MtkTvNetwork mInstance;
    public final String TAG = "MtkTvNetwork";

    public static MtkTvNetwork getInstance() {
        if (mInstance == null) {
            mInstance = new MtkTvNetwork();
        }
        return mInstance;
    }
}
