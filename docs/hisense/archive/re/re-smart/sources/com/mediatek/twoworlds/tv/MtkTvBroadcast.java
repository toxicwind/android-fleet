package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvBroadcast extends MtkTvBroadcastBase {
    public static MtkTvBroadcast mtkTvBroadcast;

    public static synchronized MtkTvBroadcast getInstance() {
        if (mtkTvBroadcast != null) {
            return mtkTvBroadcast;
        }
        mtkTvBroadcast = new MtkTvBroadcast();
        return mtkTvBroadcast;
    }
}
