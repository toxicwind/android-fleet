package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvSoftKeyboardBase {
    public static final int ACTION_REQUEST_HIDE = 1;
    public static final int ACTION_REQUEST_SHOW = 0;
    public static final int STATUS_CHANGE_FROM_HIDE_TO_SHOW = 2;
    public static final int STATUS_CHANGE_FROM_SHOW_TO_HIDE_BY_CANCEL_ACTION = 1;
    public static final int STATUS_CHANGE_FROM_SHOW_TO_HIDE_BY_CONFIRM_ACTION = 0;
    public static final String TAG = "MtkTvSoftKeyboard";
    public static OnSoftKeyboardActionListener mOnSoftKeyboardActionListener;

    public interface OnSoftKeyboardActionListener {
        void onHide();

        void onShow(int i);
    }

    public MtkTvSoftKeyboardBase() {
        TVNativeWrapper.softKeyboardInit_native();
    }

    public static void receiveSoftKeyboardActionRequest(int i, int i2) {
        OnSoftKeyboardActionListener onSoftKeyboardActionListener;
        OnSoftKeyboardActionListener onSoftKeyboardActionListener2;
        a.a("receiveSoftKeyboardActionRequest ", i, "\n", TAG);
        if (i == 0 && (onSoftKeyboardActionListener2 = mOnSoftKeyboardActionListener) != null) {
            onSoftKeyboardActionListener2.onShow(i2);
        }
        if (i != 1 || (onSoftKeyboardActionListener = mOnSoftKeyboardActionListener) == null) {
            return;
        }
        onSoftKeyboardActionListener.onHide();
    }

    public boolean hasOnClickListeners() {
        return mOnSoftKeyboardActionListener != null;
    }

    public void notifySoftKeyboardStatusChange(int i) {
        Log.d(TAG, "notifySoftKeyboardStatusChange\n");
        TVNativeWrapper.notifySoftKeyboardStatusChange_native(i);
    }

    public void notifyTextChange(String str) {
        Log.d(TAG, "notifyTextChange\n");
        TVNativeWrapper.notifyTextChange_native(str);
    }

    public void setOnSoftKeyboardActionListener(OnSoftKeyboardActionListener onSoftKeyboardActionListener) {
        mOnSoftKeyboardActionListener = onSoftKeyboardActionListener;
    }
}
