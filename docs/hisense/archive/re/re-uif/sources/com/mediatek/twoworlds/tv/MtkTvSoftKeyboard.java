package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvSoftKeyboard extends MtkTvSoftKeyboardBase {
    private static MtkTvSoftKeyboard mtkTvSoftKeyBoard;

    private MtkTvSoftKeyboard() {
    }

    public static MtkTvSoftKeyboard getInstance() {
        MtkTvSoftKeyboard mtkTvSoftKeyboard = mtkTvSoftKeyBoard;
        if (mtkTvSoftKeyboard != null) {
            return mtkTvSoftKeyboard;
        }
        MtkTvSoftKeyboard mtkTvSoftKeyboard2 = new MtkTvSoftKeyboard();
        mtkTvSoftKeyBoard = mtkTvSoftKeyboard2;
        return mtkTvSoftKeyboard2;
    }
}
