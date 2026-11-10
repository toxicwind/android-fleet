package com.hisense.tv.ota;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.hisense.tv.ota.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class C0010k implements com.hisense.tv.ota.V.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List f41a = new ArrayList();
    final /* synthetic */ C0016q b;

    C0010k(C0016q c0016q) {
        this.b = c0016q;
    }

    @Override // com.hisense.tv.ota.V.c
    public void a(String str) {
        synchronized (this.f41a) {
            Iterator it = this.f41a.iterator();
            while (it.hasNext()) {
                ((com.hisense.tv.ota.V.c) it.next()).a(str);
            }
        }
        C0019t.a().c(SysUpgradeState.DOWNLOAD_CANCEL);
        C0019t.a().c(SysUpgradeState.IDLE);
    }

    @Override // com.hisense.tv.ota.V.c
    public void b(String str, int i) {
        com.hisense.tv.ota.W.k.N((Context) this.b.f46a.get(), "06");
        Log.i("SysUpgradeManager", "Download error upload EVENT_CODE_DOWNLOAD_NG to server code =" + i);
        C0019t.a().c(SysUpgradeState.DOWNLOAD_FAILED);
        synchronized (this.f41a) {
            Iterator it = this.f41a.iterator();
            while (it.hasNext()) {
                ((com.hisense.tv.ota.V.c) it.next()).b(str, i);
            }
        }
        if (this.b.c.f() && this.b.c.e().k()) {
            Log.i("SysUpgradeManager", "Download error, but in silent mode, just set status as IDLE");
            C0019t.a().c(SysUpgradeState.IDLE);
        } else if (com.hisense.tv.ota.W.k.y((Context) this.b.f46a.get()) || com.hisense.tv.ota.W.k.F()) {
            Log.i("SysUpgradeManager", "Download error, in storemode or reach MaxACtimes, just set status as IDLE");
        } else {
            Log.i("SysUpgradeManager", "Download error, in foreground mode showErrorUI ");
            if (i == -1) {
                Q.r((Context) this.b.f46a.get()).v(-1);
            } else if (i != -3 ? this.b.c.i() : this.b.c.i()) {
                Q.r((Context) this.b.f46a.get()).v(-10);
            } else {
                Q.r((Context) this.b.f46a.get()).v(-4);
            }
        }
        if (C0016q.u == 1) {
            com.hisense.tv.ota.W.n.b((Context) this.b.f46a.get()).c();
            Log.i("SysUpgradeManager", "download error, release the weak lock");
            C0016q.u = 0;
        }
        if (this.b.c.i()) {
            C0019t.a().c(SysUpgradeState.IDLE);
            this.b.D("next");
            this.b.c.k();
            this.b.c.q(false);
        }
    }

    @Override // com.hisense.tv.ota.V.c
    public void c(String str) {
        synchronized (this.f41a) {
            Iterator it = this.f41a.iterator();
            while (it.hasNext()) {
                ((com.hisense.tv.ota.V.c) it.next()).c(str);
            }
        }
    }

    @Override // com.hisense.tv.ota.V.c
    public void d(String str) {
        Log.i("SysUpgradeManager", "DownloadListenerManager onFinish download finfish filePath =" + str);
        com.hisense.tv.ota.W.k.N((Context) this.b.f46a.get(), "05");
        Log.i("SysUpgradeManager", "Download finfished upload EVENT_CODE_DOWNLOAD_OK to server ");
        C0019t.a().c(SysUpgradeState.DOWNLOADED);
        SystemProperties.set("sys.vt.ota.state", "3");
        String strReplaceAll = str.replaceAll("tmp", "zip");
        File file = new File(str);
        File file2 = new File(strReplaceAll);
        if (!file.renameTo(file2)) {
            Log.e("SysUpgradeManager", "rename file failed");
            return;
        }
        synchronized (this.f41a) {
            Iterator it = this.f41a.iterator();
            while (it.hasNext()) {
                ((com.hisense.tv.ota.V.c) it.next()).d(strReplaceAll);
            }
        }
        com.hisense.tv.ota.W.a.a(strReplaceAll);
        Log.i("SysUpgradeManager", "DownloadListenerManager onFinish ChangeModeforOTAZipPackage  ");
        Log.i("SysUpgradeManager", "DownloadListenerManager onFinish setPkgFile completeFile =" + file2);
        this.b.c.r(file2);
        com.hisense.tv.ota.W.k.H((Context) this.b.f46a.get(), this.b.c.c().getAbsolutePath());
        if (!this.b.c.g()) {
            Log.i("SysUpgradeManager", "DownloadListenerManager onFinish start to showVerifyUI ");
            if (this.b.c.i()) {
                Q.r((Context) this.b.f46a.get()).z(this.b.c);
            } else {
                Q.r((Context) this.b.f46a.get()).D(this.b.c);
            }
        }
        this.b.N();
    }

    @Override // com.hisense.tv.ota.V.c
    public void e(String str, int i) {
        Log.i("SysUpgradeManager", "download progress: " + i);
        synchronized (this.f41a) {
            Iterator it = this.f41a.iterator();
            while (it.hasNext()) {
                ((com.hisense.tv.ota.V.c) it.next()).e(str, i);
            }
        }
    }

    public void f(com.hisense.tv.ota.V.c cVar) {
        if (cVar != null) {
            synchronized (this.f41a) {
                if (!this.f41a.contains(cVar)) {
                    this.f41a.add(cVar);
                }
            }
        }
    }
}
