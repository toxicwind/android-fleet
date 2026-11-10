package com.hisense.tv.ota;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.hisense.tv.ota.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class C0015p implements InterfaceC0014o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List f45a = new ArrayList();
    final /* synthetic */ C0016q b;

    C0015p(C0016q c0016q) {
        this.b = c0016q;
    }

    @Override // com.hisense.tv.ota.InterfaceC0014o
    public void a() {
        Log.i("SysUpgradeManager", "user canceled check version");
        C0019t.a().c(SysUpgradeState.IDLE);
        synchronized (this.f45a) {
            Iterator it = this.f45a.iterator();
            while (it.hasNext()) {
                ((InterfaceC0014o) it.next()).a();
            }
        }
    }

    @Override // com.hisense.tv.ota.InterfaceC0014o
    public void b(int i, int i2, VersionData versionData) {
        Intent intent;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = Integer.valueOf(i2);
        objArr[2] = versionData != null ? versionData.toString() : "null";
        Log.i("SysUpgradeManager", String.format("version check onResult, type:[%d]  code:[%d]  data:[%s]", objArr));
        if (i2 != 0) {
            if (i == 0 && -5 == i2) {
                str = "SysUpgradeManager";
                str2 = "check version data failed, will check sys version, set state to be IDLE";
                Log.i(str, str2);
                C0019t.a().c(SysUpgradeState.IDLE);
                C0016q c0016q = this.b;
                c0016q.I(c0016q.c.f(), 1, false);
                return;
            }
            synchronized (this.f45a) {
                Iterator it = this.f45a.iterator();
                while (it.hasNext()) {
                    ((InterfaceC0014o) it.next()).b(i, i2, versionData);
                }
            }
            C0019t.a().c(SysUpgradeState.CHECK_FAILED);
            if (this.b.c.f()) {
                C0019t.a().c(SysUpgradeState.IDLE);
            } else if (this.b.c.i()) {
                C0019t.a().c(SysUpgradeState.IDLE);
                Q.r((Context) this.b.f46a.get()).a();
                this.b.D("next");
            } else {
                Q.r((Context) this.b.f46a.get()).v(-3);
            }
            com.hisense.tv.ota.W.k.h((Context) this.b.f46a.get(), false, null, null);
            if (!this.b.c.i()) {
                if (this.b.c.f()) {
                    Log.i("SysUpgradeManager", "some error :network not connect and so on,stop ota service ");
                    intent = new Intent("com.hisense.action.ota");
                    intent.setPackage("com.hisense.tv.ota");
                    ((Context) this.b.f46a.get()).stopService(intent);
                    return;
                }
                return;
            }
            this.b.c.k();
            this.b.c.q(false);
            return;
        }
        if (versionData != null) {
            this.b.c.u(versionData);
            if (com.hisense.tv.ota.W.k.b((Context) this.b.f46a.get(), this.b.c.i(), versionData.j(), versionData.k(), this.b.c.f(), versionData.i(), com.hisense.tv.ota.W.k.C((Context) this.b.f46a.get(), versionData.e()), com.hisense.tv.ota.W.k.D((Context) this.b.f46a.get(), versionData.e()))) {
                synchronized (this.f45a) {
                    Iterator it2 = this.f45a.iterator();
                    while (it2.hasNext()) {
                        ((InterfaceC0014o) it2.next()).b(i, i2, versionData);
                    }
                }
                com.hisense.tv.ota.W.k.I((Context) this.b.f46a.get());
                com.hisense.tv.ota.W.k.G((Context) this.b.f46a.get(), versionData.e());
                if (!com.hisense.tv.ota.W.k.y((Context) this.b.f46a.get())) {
                    if (versionData.k() && this.b.c.f()) {
                        Log.i("SysUpgradeManager", "In user mode and in silent upgrade mode, do not show New Version UI ");
                    } else {
                        Log.i("SysUpgradeManager", "In user mode and not silent mode, it should notify user ");
                        if (this.b.c.i()) {
                            Q.r((Context) this.b.f46a.get()).a();
                        }
                        if (versionData.i() && this.b.c.f()) {
                            Log.i("SysUpgradeManager", "It should show New Version UI ");
                        } else if (this.b.c.f() && !versionData.i()) {
                            Log.i("SysUpgradeManager", "send notification ");
                            ((Context) this.b.f46a.get()).startService(new Intent((Context) this.b.f46a.get(), (Class<?>) SysUpgradeNotifyService.class));
                            C0019t.a().c(SysUpgradeState.FOUND_NEW_VERSION);
                        }
                        Q.r((Context) this.b.f46a.get()).B(this.b.c);
                    }
                }
                File fileN = com.hisense.tv.ota.W.k.n((Context) this.b.f46a.get(), versionData.f(), versionData.e(), versionData.d());
                Log.i("SysUpgradeManager", " existsPackage = " + fileN);
                if (fileN != null) {
                    Log.i("SysUpgradeManager", "the package has been downloaded, path: " + fileN.getPath());
                    this.b.c.n(true);
                    this.b.c.r(fileN);
                    this.b.d(3, 50);
                } else {
                    this.b.c.n(false);
                }
                C0019t.a().c(SysUpgradeState.FOUND_NEW_VERSION);
                if (com.hisense.tv.ota.W.k.y((Context) this.b.f46a.get()) || com.hisense.tv.ota.W.k.F()) {
                    com.hisense.tv.ota.W.k.N((Context) this.b.f46a.get(), "03");
                    Log.i("SysUpgradeManager", " upload EVENT_CODE_SELECT_DOWNLOAD to server ");
                    if (this.b.c.h()) {
                        Log.i("SysUpgradeManager", "package exists, won't download, just to verify ");
                        C0019t.a().c(SysUpgradeState.DOWNLOADED);
                        C0016q.t((Context) this.b.f46a.get()).E(true);
                        this.b.N();
                    } else {
                        Log.i("SysUpgradeManager", "begin to startDownload Pkg ");
                        this.b.J();
                        C0016q.t((Context) this.b.f46a.get()).E(true);
                    }
                } else {
                    if (!com.hisense.tv.ota.W.k.y((Context) this.b.f46a.get()) && versionData.k() && this.b.c.f()) {
                        if (C0016q.u == 0) {
                            com.hisense.tv.ota.W.n.b((Context) this.b.f46a.get()).a();
                            Log.i("SysUpgradeManager", "OTA begin to hold the wake lock now");
                            C0016q.u = 1;
                            if (SystemProperties.getInt("sys.vt.ota.state", 0) == 0) {
                                SystemProperties.set("persist.vt.ota.ACboot.number", Integer.toString(SystemProperties.getInt("persist.vt.ota.ACboot.number", 0) + 1));
                            }
                            this.b.d(3, 100);
                            if (!com.hisense.tv.ota.W.k.a()) {
                                this.b.d(4, 50);
                            }
                        } else {
                            str5 = "SysUpgradeManager";
                            str6 = "OTA already hold the weak lock";
                        }
                    } else if (this.b.c.f()) {
                        str5 = "SysUpgradeManager";
                        str6 = "In user mode and not silent mode, wait user action ";
                    } else {
                        str5 = "SysUpgradeManager";
                        str6 = "Manual start, alway show NewVerionUI";
                    }
                    Log.i(str5, str6);
                }
                int i3 = SystemProperties.getInt("sys.vt.ota.state", 0);
                if (i3 != 2 && i3 != 7) {
                    SystemProperties.set("sys.vt.ota.state", "1");
                    return;
                } else {
                    str3 = "SysUpgradeManager";
                    str4 = "In stalling or installed state now";
                }
            } else {
                if (i == 0) {
                    Log.i("SysUpgradeManager", "found new data version ,but depend on policy, won't notify user");
                    str = "SysUpgradeManager";
                    str2 = "new data version is ignored, will check sys version, set state to be IDLE";
                    Log.i(str, str2);
                    C0019t.a().c(SysUpgradeState.IDLE);
                    C0016q c0016q2 = this.b;
                    c0016q2.I(c0016q2.c.f(), 1, false);
                    return;
                }
                Log.i("SysUpgradeManager", "found new system version ,but depend on policy, won't notify user");
                com.hisense.tv.ota.W.k.h((Context) this.b.f46a.get(), false, null, null);
                C0019t.a().c(SysUpgradeState.NOT_FOUND_NEW_VERSION);
                C0019t.a().c(SysUpgradeState.IDLE);
                if (this.b.c.i()) {
                    Q.r((Context) this.b.f46a.get()).a();
                    this.b.D("next");
                    this.b.c.k();
                    this.b.c.q(false);
                    return;
                }
                if (this.b.c.f()) {
                    SystemClock.sleep(15000L);
                }
                if (!SystemProperties.get("sys.vt.ota.upload.double.flag", "0").equals("0") || !SystemProperties.get("sys.vt.ota.merge.state", "none").equals("done")) {
                    Log.i("SysUpgradeManager", "found new data version ,but depend on policy, won't notify user,stop ota service");
                    intent = new Intent("com.hisense.action.ota");
                    intent.setPackage("com.hisense.tv.ota");
                    ((Context) this.b.f46a.get()).stopService(intent);
                    return;
                }
                str3 = "SysUpgradeManager";
                str4 = "not found new  version ,do not stop myself,alway wait for upload event";
            }
        } else {
            if (i == 0) {
                str = "SysUpgradeManager";
                str2 = "not found new data version, will check sys version, set state to be IDLE";
                Log.i(str, str2);
                C0019t.a().c(SysUpgradeState.IDLE);
                C0016q c0016q3 = this.b;
                c0016q3.I(c0016q3.c.f(), 1, false);
                return;
            }
            Log.i("SysUpgradeManager", "new upgrade version not found from server");
            C0019t.a().c(SysUpgradeState.NOT_FOUND_NEW_VERSION);
            if (this.b.c.f()) {
                C0019t.a().c(SysUpgradeState.IDLE);
            } else {
                synchronized (this.f45a) {
                    Iterator it3 = this.f45a.iterator();
                    while (it3.hasNext()) {
                        ((InterfaceC0014o) it3.next()).b(i, i2, versionData);
                    }
                }
                if (this.b.c.i()) {
                    C0019t.a().c(SysUpgradeState.IDLE);
                    Q.r((Context) this.b.f46a.get()).a();
                    this.b.D("next");
                } else {
                    Q.r((Context) this.b.f46a.get()).v(-2);
                }
            }
            com.hisense.tv.ota.W.k.h((Context) this.b.f46a.get(), false, null, null);
            if (this.b.c.i()) {
                Log.i("SysUpgradeManager", "FTE bring OTA, not found new version");
                this.b.c.k();
                this.b.c.q(false);
                return;
            }
            if (this.b.c.f()) {
                SystemClock.sleep(15000L);
            }
            if (!SystemProperties.get("sys.vt.ota.upload.double.flag", "0").equals("0") || !SystemProperties.get("sys.vt.ota.merge.state", "none").equals("done")) {
                Log.i("SysUpgradeManager", "not found new data version ,stop ota service");
                intent = new Intent("com.hisense.action.ota");
                intent.setPackage("com.hisense.tv.ota");
                ((Context) this.b.f46a.get()).stopService(intent);
                return;
            }
            str3 = "SysUpgradeManager";
            str4 = "not found new  version ,do not stop myself,alway wait for upload event";
        }
        Log.i(str3, str4);
    }

    public void c(InterfaceC0014o interfaceC0014o) {
        if (interfaceC0014o != null) {
            synchronized (this.f45a) {
                if (!this.f45a.contains(interfaceC0014o)) {
                    this.f45a.add(interfaceC0014o);
                }
            }
        }
    }
}
