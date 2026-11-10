package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvBroadcast extends MtkTvBroadcastBase {
    private static MtkTvBroadcast mtkTvBroadcast;

    private MtkTvBroadcast() {
    }

    public static synchronized MtkTvBroadcast getInstance() {
        if (mtkTvBroadcast == null) {
            MtkTvBroadcast mtkTvBroadcast2 = new MtkTvBroadcast();
            mtkTvBroadcast = mtkTvBroadcast2;
            return mtkTvBroadcast2;
        }
        return mtkTvBroadcast;
    }
}
