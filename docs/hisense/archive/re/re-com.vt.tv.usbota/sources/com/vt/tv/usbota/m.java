package com.vt.tv.usbota;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class m {
    private static m b;
    private SysUpgradeState a = SysUpgradeState.IDLE;

    public static m a() {
        if (b == null) {
            synchronized (m.class) {
                if (b == null) {
                    b = new m();
                }
            }
        }
        return b;
    }

    public synchronized SysUpgradeState b() {
        return this.a;
    }

    public synchronized void c(SysUpgradeState sysUpgradeState) {
        Log.i("SysUpgradeStateManager", "set state from " + this.a + " to " + sysUpgradeState);
        this.a = sysUpgradeState;
    }
}
