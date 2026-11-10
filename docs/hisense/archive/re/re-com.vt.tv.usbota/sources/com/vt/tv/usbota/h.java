package com.vt.tv.usbota;

import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class h implements com.vt.tv.usbota.G.c {
    private List a = new ArrayList();
    final /* synthetic */ l b;

    h(l lVar) {
        this.b = lVar;
    }

    @Override // com.vt.tv.usbota.G.c
    public void a(int i, Object... objArr) {
        m.a().c(SysUpgradeState.INSTALE_FAILED);
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((com.vt.tv.usbota.G.c) it.next()).a(i, objArr);
            }
        }
        Log.i("SysUpgradeManager", "if install error to delete all the downloaded zip file");
        E.j((Context) this.b.a.get()).m(i == 4 ? 1 : 11);
    }

    @Override // com.vt.tv.usbota.G.c
    public void b() {
        m.a().c(SysUpgradeState.INSTALLED);
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((com.vt.tv.usbota.G.c) it.next()).b();
            }
        }
        E.j((Context) this.b.a.get()).p(this.b.c);
    }

    @Override // com.vt.tv.usbota.G.c
    public void c(int i) {
        Log.i("SysUpgradeManager", "InstallListenerManager onInstallProgress = " + i);
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((com.vt.tv.usbota.G.c) it.next()).c(i);
            }
        }
    }

    public void d(com.vt.tv.usbota.G.c cVar) {
        if (cVar != null) {
            synchronized (this.a) {
                if (!this.a.contains(cVar)) {
                    this.a.add(cVar);
                }
            }
        }
    }
}
