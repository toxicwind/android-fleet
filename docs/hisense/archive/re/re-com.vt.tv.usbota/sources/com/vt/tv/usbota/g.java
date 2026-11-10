package com.vt.tv.usbota;

import android.os.SystemProperties;
import android.os.UpdateEngineCallback;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
class g extends UpdateEngineCallback {
    final /* synthetic */ l a;

    g(l lVar) {
        this.a = lVar;
    }

    public void onPayloadApplicationComplete(int i) {
        Log.i("SysUpgradeManager", "HisenseOTAUpdateEngineCallbackImpl onPayloadApplicationComplete errorCode = " + i);
        if (i == 0) {
            this.a.g.c(100);
            this.a.g.b();
            return;
        }
        if (SysUpgradeState.INSTALE_FAILED.equals(m.a().b())) {
            Log.i("SysUpgradeManager", "onInstallError already called ");
            return;
        }
        m.a().c(SysUpgradeState.INSTALE_FAILED);
        h hVar = this.a.g;
        if (i == 60) {
            hVar.a(4, new Object[0]);
        } else {
            hVar.a(5, new Object[0]);
        }
    }

    public void onStatusUpdate(int i, float f) {
        h hVar;
        Log.i("SysUpgradeManager", "HisenseOTAUpdateEngineCallbackImpl onStatusUpdate status= " + i);
        Log.i("SysUpgradeManager", "HisenseOTAUpdateEngineCallbackImpl onStatusUpdate percent = " + f);
        int i2 = (int) (100.0f * f);
        Log.i("SysUpgradeManager", "HisenseOTAUpdateEngineCallbackImpl progress =  " + i2);
        if (i != 3) {
            if (i == 6) {
                this.a.k = 1;
                Log.i("SysUpgradeManager", "A/B update status is waiting reboot");
                this.a.g.c(100);
                this.a.g.b();
                return;
            }
            if (i != 5) {
                this.a.a(i);
                return;
            }
            Log.i("SysUpgradeManager", "A/B update status is finfished");
            if (this.a.l == 1 && SystemProperties.getInt("sys.vt.ota.update.progress", -1) == -1) {
                Log.i("SysUpgradeManager", "Last A/B update is break of, show flase progress ");
                this.a.b(2, 200);
                this.a.g.c(0);
                return;
            }
            return;
        }
        if (i2 > 0) {
            l lVar = this.a;
            if (lVar.l != 1) {
                Log.i("SysUpgradeManager", "OTA Full upgrade now");
                hVar = this.a.g;
            } else {
                if (i2 > 96) {
                    if (lVar.m != 0) {
                        Log.i("SysUpgradeManager", "self progress already showing ");
                        return;
                    }
                    Log.i("SysUpgradeManager", "begin to show  self progress percent = " + f);
                    this.a.g.c(49);
                    this.a.m = 1;
                    SystemProperties.set("sys.vt.ota.update.progress", Integer.toString(i2));
                    this.a.b(1, 2500);
                    return;
                }
                if (i2 < 4) {
                    return;
                }
                hVar = lVar.g;
                i2 /= 2;
            }
            hVar.c(i2);
        }
    }
}
