package com.hisense.tv.ota;

import android.os.SystemProperties;
import android.os.UpdateEngineCallback;
import android.util.Log;

/* JADX INFO: renamed from: com.hisense.tv.ota.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class UpdateEngineCallbackC0011l extends UpdateEngineCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ C0016q f42a;

    UpdateEngineCallbackC0011l(C0016q c0016q) {
        this.f42a = c0016q;
    }

    public void onPayloadApplicationComplete(int i) {
        Log.i("SysUpgradeManager", "HisenseOTAUpdateEngineCallbackImpl onPayloadApplicationComplete errorCode = " + i);
        if (i == 0) {
            this.f42a.b();
            return;
        }
        if (SysUpgradeState.INSTALE_FAILED.equals(C0019t.a().b())) {
            Log.i("SysUpgradeManager", "onInstallError already called ");
            return;
        }
        C0019t.a().c(SysUpgradeState.INSTALE_FAILED);
        C0012m c0012m = this.f42a.h;
        if (i == 60) {
            c0012m.c(-7, new Object[0]);
        } else {
            c0012m.c(-6, new Object[0]);
        }
    }

    public void onStatusUpdate(int i, float f) {
        C0012m c0012m;
        Log.i("SysUpgradeManager", "HisenseOTAUpdateEngineCallbackImpl onStatusUpdate status= " + i);
        Log.i("SysUpgradeManager", "HisenseOTAUpdateEngineCallbackImpl onStatusUpdate percent = " + f);
        int i2 = (int) (100.0f * f);
        Log.i("SysUpgradeManager", "HisenseOTAUpdateEngineCallbackImpl progress =  " + i2);
        if (i != 3) {
            if (i == 6) {
                this.f42a.l = 1;
                Log.i("SysUpgradeManager", "A/B update status is waiting reboot");
                this.f42a.b();
                return;
            } else {
                if (i != 5) {
                    this.f42a.c(i);
                    return;
                }
                Log.i("SysUpgradeManager", "A/B update status is finished");
                if (this.f42a.m == 1 && SystemProperties.getInt("sys.vt.ota.update.progress", -1) == -1) {
                    Log.i("SysUpgradeManager", "Last A/B update is break of, show flase progress ");
                    this.f42a.d(2, 200);
                    this.f42a.h.a(0);
                    return;
                }
                return;
            }
        }
        if (i2 > 0) {
            C0016q c0016q = this.f42a;
            if (c0016q.m != 1) {
                Log.i("SysUpgradeManager", "OTA Full upgrade now");
                c0012m = this.f42a.h;
            } else {
                if (i2 > 96) {
                    if (c0016q.o != 0) {
                        Log.i("SysUpgradeManager", "self progress already showing ");
                        return;
                    }
                    Log.i("SysUpgradeManager", "begin to show  self progress percent = " + f);
                    this.f42a.h.a(49);
                    this.f42a.o = 1;
                    SystemProperties.set("sys.vt.ota.update.progress", Integer.toString(i2));
                    this.f42a.d(1, 2500);
                    return;
                }
                if (i2 < 4) {
                    return;
                }
                c0012m = c0016q.h;
                i2 /= 2;
            }
            c0012m.a(i2);
        }
    }
}
