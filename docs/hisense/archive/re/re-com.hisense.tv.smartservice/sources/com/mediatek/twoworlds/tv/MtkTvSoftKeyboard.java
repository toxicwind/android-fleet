package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvSoftKeyboard extends MtkTvSoftKeyboardBase {
    public static MtkTvSoftKeyboard mtkTvSoftKeyBoard;

    public static MtkTvSoftKeyboard getInstance() {
        MtkTvSoftKeyboard mtkTvSoftKeyboard = mtkTvSoftKeyBoard;
        if (mtkTvSoftKeyboard != null) {
            return mtkTvSoftKeyboard;
        }
        mtkTvSoftKeyBoard = new MtkTvSoftKeyboard();
        return mtkTvSoftKeyBoard;
    }
}
