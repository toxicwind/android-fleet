package com.mediatek.twoworlds.tv;

import android.util.Log;
import android.view.KeyEvent;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvKeyEventBase {
    public static final String TAG = "MtkTvKeyEvent";
    private static KeyMapReader mKeyMapReader;

    public MtkTvKeyEventBase() {
        if (!TVNativeWrapper.is_emulator()) {
            if (mKeyMapReader == null) {
                try {
                    mKeyMapReader = new KeyMapReader("/system/usr/keylayout/ttxkeymap.ini");
                } catch (Exception e) {
                    e.printStackTrace();
                    mKeyMapReader = null;
                }
            }
            if (mKeyMapReader == null) {
                try {
                    mKeyMapReader = new KeyMapReader("/system/vendor/usr/keylayout/ttxkeymap.ini");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            Log.d(TAG, "KeyMap Reader init successful");
            return;
        }
        Log.d(TAG, "KeyMap Reader init successful (emulator)");
    }

    public int sendKeyClick(int i) {
        Log.d(TAG, "sendKeyClick " + i);
        TVNativeWrapper.SendKeyClick_native(i);
        return 0;
    }

    public int sendKey(int i, int i2) {
        if (i != 0 && i != 1) {
            return -1;
        }
        Log.d(TAG, "sendKey " + i + " " + i2);
        TVNativeWrapper.SendKey_native(i, i2);
        return 0;
    }

    public int sendMouseMove(int i, int i2, int i3, int i4) {
        if (i < 0 || i >= i3 || i2 < 0 || i2 >= i4 || i3 < 0 || i3 > 3840 || i4 < 0 || i4 > 2160) {
            return -1;
        }
        TVNativeWrapper.SendMouseMove_native(i3, i4, i, i2);
        return 0;
    }

    public int sendMouseButton(int i, int i2) {
        if (i != 0 && i != 1) {
            return -1;
        }
        if (i2 != 1 && i2 != 2 && i2 != 4) {
            return -1;
        }
        Log.d(TAG, "sendMouseButton " + i + " " + i2);
        TVNativeWrapper.SendMouseButton_native(i, i2);
        return 0;
    }

    public int androidKeyToDFBkey(int i) {
        KeyMapReader keyMapReader = mKeyMapReader;
        int mTKKeyCode = keyMapReader != null ? keyMapReader.getMTKKeyCode(i) : 0;
        Log.d(TAG, "androidKeyToDFBkey From " + i + " To " + mTKKeyCode);
        return mTKKeyCode;
    }

    public int androidKeyToDFBkey(int i, KeyEvent keyEvent) {
        if (keyEvent == null) {
            return -1;
        }
        KeyMapReader keyMapReader = mKeyMapReader;
        int mTKKeyCode = keyMapReader != null ? keyMapReader.getMTKKeyCode(i) : 0;
        if (keyEvent.getDeviceId() == 1 && mTKKeyCode != -1) {
            mTKKeyCode |= 268435456;
        }
        Log.d(TAG, "androidKeyToDFBkey From " + i + " To " + mTKKeyCode);
        return mTKKeyCode;
    }
}
