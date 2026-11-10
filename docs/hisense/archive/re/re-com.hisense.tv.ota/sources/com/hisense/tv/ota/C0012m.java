package com.hisense.tv.ota;

import android.content.Context;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.hisense.tv.ota.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class C0012m implements com.hisense.tv.ota.W.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List f43a = new ArrayList();
    final /* synthetic */ C0016q b;

    C0012m(C0016q c0016q) {
        this.b = c0016q;
    }

    @Override // com.hisense.tv.ota.W.i
    public void a(int i) {
        Log.i("SysUpgradeManager", "InstallListenerManager onInstallProgress = " + i);
        synchronized (this.f43a) {
            Iterator it = this.f43a.iterator();
            while (it.hasNext()) {
                ((com.hisense.tv.ota.W.i) it.next()).a(i);
            }
        }
    }

    @Override // com.hisense.tv.ota.W.i
    public void b() {
        String str;
        String str2;
        com.hisense.tv.ota.W.k.N((Context) this.b.f46a.get(), "71");
        Log.i("SysUpgradeManager", "Install OK, Upload EVENT_CODE_INSTALL_OK to server ");
        C0019t.a().c(SysUpgradeState.INSTALLED);
        SystemProperties.set("sys.vt.ota.state", "7");
        synchronized (this.f43a) {
            Iterator it = this.f43a.iterator();
            while (it.hasNext()) {
                ((com.hisense.tv.ota.W.i) it.next()).b();
            }
        }
        if (this.b.c.i()) {
            Log.i("SysUpgradeManager", "Fte bring OTA, just to call reboot ");
            this.b.M();
            return;
        }
        if (!com.hisense.tv.ota.W.k.y((Context) this.b.f46a.get()) && !com.hisense.tv.ota.W.k.F()) {
            if (this.b.c.e().k()) {
                Log.i("SysUpgradeManager", "install OK mScreenOnTimesDuringWorkCycle =" + C0016q.t);
                if (this.b.c.f() && !com.hisense.tv.ota.W.k.a() && C0016q.t == 1) {
                    Log.i("SysUpgradeManager", "silent upgrade all OK, we call quiescent reboot now");
                    SystemClock.sleep(3000L);
                    this.b.L();
                    return;
                } else if (!com.hisense.tv.ota.W.k.a() && this.b.c.f()) {
                    Log.i("SysUpgradeManager", "[auto-OTA] during install,screen On and then screen off, we wait 2min again");
                    this.b.d(4, 2000);
                    return;
                } else if (this.b.c.f()) {
                    str = "SysUpgradeManager";
                    str2 = "wait next screen off, we do reboot action";
                } else {
                    Log.i("SysUpgradeManager", "[manual-OTA][silent mode] show reboot UI ");
                }
            } else {
                Log.i("SysUpgradeManager", "not silent mode, show rebootUI");
                SystemClock.sleep(2000L);
            }
            Q.r((Context) this.b.f46a.get()).C(this.b.c);
            return;
        }
        str = "SysUpgradeManager";
        str2 = "in storemode, natural switch with AC ON/OFF . ";
        Log.i(str, str2);
    }

    @Override // com.hisense.tv.ota.W.i
    public void c(int i, Object... objArr) {
        Q qR;
        int i2;
        com.hisense.tv.ota.W.k.N((Context) this.b.f46a.get(), "72");
        Log.i("SysUpgradeManager", "Install wrong, Upload EVENT_CODE_INSTALL_NG to server ");
        C0019t.a().c(SysUpgradeState.INSTALE_FAILED);
        synchronized (this.f43a) {
            Iterator it = this.f43a.iterator();
            while (it.hasNext()) {
                ((com.hisense.tv.ota.W.i) it.next()).c(i, objArr);
            }
        }
        Log.i("SysUpgradeManager", "if install error to delete all the downloaded zip file");
        if (!this.b.c.c().delete()) {
            Log.e("SysUpgradeManager", "delete package file failed");
        }
        if (this.b.c.b() != null) {
            Log.i("SysUpgradeManager", "delete Eula zip file");
            this.b.c.b().delete();
            if (this.b.c.a() != null) {
                Log.i("SysUpgradeManager", "delete Eula text file");
                this.b.c.a().delete();
            }
        }
        com.hisense.tv.ota.W.k.M((Context) this.b.f46a.get(), this.b.c.e().e());
        if (this.b.c.f() && this.b.c.e().k()) {
            Log.i("SysUpgradeManager", "install error, but in silent mode, just set status as IDLE");
            C0019t.a().c(SysUpgradeState.IDLE);
        } else if (com.hisense.tv.ota.W.k.y((Context) this.b.f46a.get()) || com.hisense.tv.ota.W.k.F()) {
            Log.i("SysUpgradeManager", "Download error, in storemode or reach MaxACtimes, just set status as IDLE");
        } else {
            Log.i("SysUpgradeManager", "install error, in foreground mode showErrorUI ");
            if (i == -7) {
                qR = Q.r((Context) this.b.f46a.get());
                i2 = -1;
            } else {
                qR = Q.r((Context) this.b.f46a.get());
                i2 = -9;
            }
            qR.v(i2);
            if (this.b.c.i()) {
                C0019t.a().c(SysUpgradeState.IDLE);
                this.b.D("next");
                this.b.c.k();
                this.b.c.q(false);
            }
        }
        if (C0016q.u == 1) {
            com.hisense.tv.ota.W.n.b((Context) this.b.f46a.get()).c();
            Log.i("SysUpgradeManager", "install error, release the weak lock");
            C0016q.u = 0;
        }
    }

    public void d(com.hisense.tv.ota.W.i iVar) {
        if (iVar != null) {
            synchronized (this.f43a) {
                if (!this.f43a.contains(iVar)) {
                    this.f43a.add(iVar);
                }
            }
        }
    }
}
