package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvKeyEvent extends MtkTvKeyEventBase {
    private static MtkTvKeyEvent mtkTvKeyEvent;

    private MtkTvKeyEvent() {
    }

    public static MtkTvKeyEvent getInstance() {
        MtkTvKeyEvent mtkTvKeyEvent2 = mtkTvKeyEvent;
        if (mtkTvKeyEvent2 != null) {
            return mtkTvKeyEvent2;
        }
        MtkTvKeyEvent mtkTvKeyEvent3 = new MtkTvKeyEvent();
        mtkTvKeyEvent = mtkTvKeyEvent3;
        return mtkTvKeyEvent3;
    }
}
