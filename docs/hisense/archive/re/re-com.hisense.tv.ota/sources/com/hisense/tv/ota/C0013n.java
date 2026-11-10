package com.hisense.tv.ota;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.hisense.tv.ota.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class C0013n implements com.hisense.tv.ota.W.j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List f44a = new ArrayList();
    final /* synthetic */ C0016q b;

    C0013n(C0016q c0016q) {
        this.b = c0016q;
    }

    @Override // com.hisense.tv.ota.W.j
    public void a(int i, Object... objArr) {
        com.hisense.tv.ota.W.k.N((Context) this.b.f46a.get(), "08");
        Log.i("SysUpgradeManager", "Verified error upload EVENT_CODE_VERIFY_NG to server ");
        C0019t.a().c(SysUpgradeState.VERIFY_FAILED);
        synchronized (this.f44a) {
            Iterator it = this.f44a.iterator();
            while (it.hasNext()) {
                ((com.hisense.tv.ota.W.j) it.next()).a(i, objArr);
            }
        }
        com.hisense.tv.ota.W.k.M((Context) this.b.f46a.get(), this.b.c.e().e());
        Log.i("SysUpgradeManager", "if Verified error to delete all the downloaded zip file");
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
        if (this.b.c.f() && this.b.c.e().k()) {
            Log.i("SysUpgradeManager", "verify error, but in silent mode, just set status as IDLE");
            C0019t.a().c(SysUpgradeState.IDLE);
        } else if (com.hisense.tv.ota.W.k.y((Context) this.b.f46a.get()) || com.hisense.tv.ota.W.k.F()) {
            Log.i("SysUpgradeManager", "Download error, in storemode or reach MaxACtimes, just set status as IDLE");
        } else {
            Log.i("SysUpgradeManager", "verify error, in foreground mode showErrorUI ");
            Q.r((Context) this.b.f46a.get()).v(-6);
            if (this.b.c.i()) {
                C0019t.a().c(SysUpgradeState.IDLE);
                this.b.D("next");
                this.b.c.k();
                this.b.c.q(false);
            }
        }
        if (C0016q.u == 1) {
            com.hisense.tv.ota.W.n.b((Context) this.b.f46a.get()).c();
            Log.i("SysUpgradeManager", "verify error, release the weak lock");
            C0016q.u = 0;
        }
    }

    @Override // com.hisense.tv.ota.W.j
    public void b() {
        String str;
        String str2;
        com.hisense.tv.ota.W.k.N((Context) this.b.f46a.get(), "07");
        Log.i("SysUpgradeManager", "verified OK Upload EVENT_CODE_VERIFY_OK to server ");
        C0019t.a().c(SysUpgradeState.VERIFIED);
        synchronized (this.f44a) {
            Iterator it = this.f44a.iterator();
            while (it.hasNext()) {
                ((com.hisense.tv.ota.W.j) it.next()).b();
            }
        }
        if (!this.b.c.g()) {
            Log.i("SysUpgradeManager", "VerfiyListenerManager onFinish start to showInstallUI ");
            if (this.b.c.i()) {
                Q.r((Context) this.b.f46a.get()).y(this.b.c);
            } else {
                Q.r((Context) this.b.f46a.get()).A(this.b.c);
            }
        }
        if (this.b.c.b() != null) {
            Log.i("SysUpgradeManager", "delete Eula zip file");
            this.b.c.b().delete();
            if (this.b.c.a() != null) {
                Log.i("SysUpgradeManager", "delete Eula text file");
                this.b.c.a().delete();
            }
        }
        String str3 = SystemProperties.get("ro.build.type");
        if (str3.contains("debug")) {
            Log.i("SysUpgradeManager", "Native Version Build type=" + str3 + " maybe install failed");
        }
        if (this.b.c.f()) {
            if (!com.hisense.tv.ota.W.k.y((Context) this.b.f46a.get()) && !com.hisense.tv.ota.W.k.F()) {
                if (this.b.c.e().k()) {
                    Log.i("SysUpgradeManager", "verify OK mScreenOnTimesDuringWorkCycle =" + C0016q.t);
                    if (C0016q.t != 1 || com.hisense.tv.ota.W.k.a()) {
                        if (com.hisense.tv.ota.W.k.a()) {
                            Log.i("SysUpgradeManager", "[auto-OTA]do install action when next screen off");
                            return;
                        } else {
                            Log.i("SysUpgradeManager", "[auto-OTA] during download and verify,screen On and then screen off, we wait 2min");
                            this.b.d(4, 2000);
                            return;
                        }
                    }
                    str = "SysUpgradeManager";
                    str2 = "[auto-OTA][silent mode] all ok, do install action";
                } else {
                    str = "SysUpgradeManager";
                    str2 = "not silent mode, always do install action";
                }
            }
            this.b.K();
        }
        str = "SysUpgradeManager";
        str2 = "manual OTA,  always do install action";
        Log.i(str, str2);
        this.b.K();
    }

    public void c(com.hisense.tv.ota.W.j jVar) {
        if (jVar != null) {
            synchronized (this.f44a) {
                if (!this.f44a.contains(jVar)) {
                    this.f44a.add(jVar);
                }
            }
        }
    }

    @Override // com.hisense.tv.ota.W.j
    public void onProgress(int i) {
        synchronized (this.f44a) {
            Iterator it = this.f44a.iterator();
            while (it.hasNext()) {
                ((com.hisense.tv.ota.W.j) it.next()).onProgress(i);
            }
        }
    }
}
