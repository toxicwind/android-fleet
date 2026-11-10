package com.hisense.tv.ota;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.util.Log;

/* JADX INFO: renamed from: com.hisense.tv.ota.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class HandlerC0007h extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ C0016q f38a;

    HandlerC0007h(C0016q c0016q) {
        this.f38a = c0016q;
    }

    /* JADX WARN: Code duplicated, block: B:39:0x0110  */
    @Override // android.os.Handler
    public void handleMessage(Message message) {
        String str;
        C0016q c0016q;
        int i;
        if (!Thread.currentThread().isInterrupted()) {
            int i2 = message.what;
            if (i2 == 1) {
                this.f38a.k++;
                Log.i("SysUpgradeManager", "selfProgress =" + this.f38a.k);
                C0016q c0016q2 = this.f38a;
                if (c0016q2.k > 99 || c0016q2.l != 0) {
                    str = "self progress = 100 or NEED_WAIT_REBOOT state come";
                    Log.i("SysUpgradeManager", str);
                } else {
                    if (SysUpgradeState.INSTALE_FAILED.equals(C0019t.a().b())) {
                        Log.i("SysUpgradeManager", "Install failed, Do not update self progress ");
                    } else {
                        Log.i("SysUpgradeManager", "Update false progress ");
                        this.f38a.h.a(this.f38a.k);
                        this.f38a.d(1, 2700);
                    }
                }
            } else if (i2 != 2) {
                int i3 = 3;
                if (i2 != 3) {
                    i3 = 4;
                    if (i2 == 4) {
                        if (com.hisense.tv.ota.W.k.a()) {
                            Log.i("SysUpgradeManager", "within 2min TV screen on again,do nothing ");
                        } else {
                            Log.i("SysUpgradeManager", "check screen status every 2s ");
                            int i4 = C0016q.v;
                            if (i4 < 60) {
                                C0016q.v = i4 + 1;
                                c0016q = this.f38a;
                                i = 2000;
                            } else {
                                Log.i("SysUpgradeManager", "Tv screen off already 2min, OTA begin to work ");
                                this.f38a.a();
                            }
                        }
                        C0016q.v = 0;
                    } else if (i2 == 5) {
                        Log.i("SysUpgradeManager", "In store mode now, always show no new version message");
                        Q.r((Context) this.f38a.f46a.get()).v(-2);
                        Q.r((Context) this.f38a.f46a.get()).b();
                    }
                } else {
                    SysUpgradeState sysUpgradeStateB = C0019t.a().b();
                    if (SysUpgradeState.INSTALE_FAILED.equals(sysUpgradeStateB) || SysUpgradeState.VERIFY_FAILED.equals(sysUpgradeStateB)) {
                        str = "state wrong do not set prop ";
                        Log.i("SysUpgradeManager", str);
                    } else {
                        SystemProperties.set("sys.vt.ota.time.flag", Long.toString(Long.valueOf(System.currentTimeMillis() / 1000).longValue()));
                        c0016q = this.f38a;
                        i = 5000;
                    }
                }
                c0016q.d(i3, i);
            } else {
                this.f38a.j++;
                Log.i("SysUpgradeManager", "Last A/B update is break off, Breakoff_Progress =" + this.f38a.j);
                C0016q c0016q3 = this.f38a;
                if (c0016q3.j > 99 || c0016q3.l != 0) {
                    str = " NEED_WAIT_REBOOT state come";
                    Log.i("SysUpgradeManager", str);
                } else {
                    if (SysUpgradeState.INSTALE_FAILED.equals(C0019t.a().b())) {
                        Log.i("SysUpgradeManager", "Install failed, Do not update self progress ");
                    } else {
                        Log.i("SysUpgradeManager", "Update false progress ");
                        this.f38a.h.a(this.f38a.j);
                        this.f38a.d(2, 1500);
                    }
                }
            }
        }
        super.handleMessage(message);
    }
}
