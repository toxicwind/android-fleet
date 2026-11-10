package com.vt.tv.usbota;

import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.util.Log;

/* JADX INFO: renamed from: com.vt.tv.usbota.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class HandlerC0003d extends Handler {
    final /* synthetic */ l a;

    HandlerC0003d(l lVar) {
        this.a = lVar;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x00d8  */
    @Override // android.os.Handler
    public void handleMessage(Message message) {
        String str;
        if (!Thread.currentThread().isInterrupted()) {
            Log.i("SysUpgradeManager", "***msg=" + message.what);
            int i = message.what;
            if (i == 1) {
                this.a.j++;
                Log.i("SysUpgradeManager", "selfProgress =" + this.a.j);
                l lVar = this.a;
                if (lVar.j > 99 || lVar.k != 0) {
                    str = "self progress = 100 or NEED_WAIT_REBOOT state come";
                    Log.i("SysUpgradeManager", str);
                } else {
                    if (SysUpgradeState.INSTALE_FAILED.equals(m.a().b())) {
                        Log.i("SysUpgradeManager", "Install failed, Do not update self progress ");
                    } else {
                        Log.i("SysUpgradeManager", "Update false progress ");
                        this.a.g.c(this.a.j);
                        this.a.b(1, 2800);
                    }
                }
            } else if (i == 2) {
                this.a.i++;
                Log.i("SysUpgradeManager", "Last A/B update is break off, Breakoff_Progress =" + this.a.i);
                l lVar2 = this.a;
                if (lVar2.i > 99 || lVar2.k != 0) {
                    str = " NEED_WAIT_REBOOT state come";
                    Log.i("SysUpgradeManager", str);
                } else {
                    if (SysUpgradeState.INSTALE_FAILED.equals(m.a().b())) {
                        Log.i("SysUpgradeManager", "Install failed, Do not update self progress ");
                    } else {
                        Log.i("SysUpgradeManager", "Update false progress ");
                        this.a.g.c(this.a.i);
                        this.a.b(2, 1500);
                    }
                }
            } else if (i == 3) {
                SysUpgradeState sysUpgradeStateB = m.a().b();
                if (SysUpgradeState.INSTALE_FAILED.equals(sysUpgradeStateB) || SysUpgradeState.VERIFY_FAILED.equals(sysUpgradeStateB)) {
                    str = "state wrong do not set prop ";
                    Log.i("SysUpgradeManager", str);
                } else {
                    Long lValueOf = Long.valueOf(System.currentTimeMillis() / 1000);
                    Log.i("SysUpgradeManager", "current_time =  " + lValueOf);
                    SystemProperties.set("sys.vt.ota.time.flag", Long.toString(lValueOf.longValue()));
                    this.a.b(3, 5000);
                }
            }
        }
        super.handleMessage(message);
    }
}
