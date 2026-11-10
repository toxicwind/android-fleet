package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvEvent extends MtkTvEventBase {
    private static MtkTvEvent mtkTvEvent;

    private MtkTvEvent() {
    }

    public static MtkTvEvent getInstance() {
        MtkTvEvent mtkTvEvent2 = mtkTvEvent;
        if (mtkTvEvent2 != null) {
            return mtkTvEvent2;
        }
        MtkTvEvent mtkTvEvent3 = new MtkTvEvent();
        mtkTvEvent = mtkTvEvent3;
        return mtkTvEvent3;
    }
}
