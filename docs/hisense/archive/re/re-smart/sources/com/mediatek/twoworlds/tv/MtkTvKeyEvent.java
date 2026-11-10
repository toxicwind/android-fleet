package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvKeyEvent extends MtkTvKeyEventBase {
    public static MtkTvKeyEvent mtkTvKeyEvent;

    public static MtkTvKeyEvent getInstance() {
        MtkTvKeyEvent mtkTvKeyEvent2 = mtkTvKeyEvent;
        if (mtkTvKeyEvent2 != null) {
            return mtkTvKeyEvent2;
        }
        mtkTvKeyEvent = new MtkTvKeyEvent();
        return mtkTvKeyEvent;
    }
}
