package com.hisense.serialInfoTest.model;

import android.app.Instrumentation;
import com.hisense.serialInfoTest.util.Logger;

/* JADX INFO: loaded from: classes.dex */
public class KeySwitcher {
    private static KeySwitcher commandSwitcher = new KeySwitcher();
    private int mKeyEvent = 0;

    private KeySwitcher() {
    }

    public static KeySwitcher getInstance() {
        if (commandSwitcher == null) {
            commandSwitcher = new KeySwitcher();
        }
        return commandSwitcher;
    }

    public void sendKeyEvent(int i) {
        this.mKeyEvent = i;
        Logger.i("Sendkeycode: " + this.mKeyEvent);
        new Thread(new Runnable() { // from class: com.hisense.serialInfoTest.model.KeySwitcher.1
            @Override // java.lang.Runnable
            public void run() {
                Logger.i("Sendkeycode runnable");
                new Instrumentation().sendKeyDownUpSync(KeySwitcher.this.mKeyEvent);
            }
        }).start();
    }

    public boolean shortPressKey(int i) {
        try {
            sendKeyEvent(i);
            return true;
        } catch (Exception e) {
            Logger.e("shortPressKey Error" + e.getMessage());
            return false;
        }
    }
}
