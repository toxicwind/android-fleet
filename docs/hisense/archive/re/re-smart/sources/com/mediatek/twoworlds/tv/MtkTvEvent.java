package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvEvent extends MtkTvEventBase {
    public static MtkTvEvent mtkTvEvent;

    public static MtkTvEvent getInstance() {
        MtkTvEvent mtkTvEvent2 = mtkTvEvent;
        if (mtkTvEvent2 != null) {
            return mtkTvEvent2;
        }
        mtkTvEvent = new MtkTvEvent();
        return mtkTvEvent;
    }
}
