package com.hisense.tv.ota;

import android.util.Log;

/* JADX INFO: renamed from: com.hisense.tv.ota.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0019t {
    private static C0019t b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SysUpgradeState f49a = SysUpgradeState.IDLE;

    public static C0019t a() {
        if (b == null) {
            synchronized (C0019t.class) {
                if (b == null) {
                    b = new C0019t();
                }
            }
        }
        return b;
    }

    public synchronized SysUpgradeState b() {
        return this.f49a;
    }

    public synchronized void c(SysUpgradeState sysUpgradeState) {
        Log.i("SysUpgradeStateManager", "set state from " + this.f49a + " to " + sysUpgradeState);
        this.f49a = sysUpgradeState;
    }
}
