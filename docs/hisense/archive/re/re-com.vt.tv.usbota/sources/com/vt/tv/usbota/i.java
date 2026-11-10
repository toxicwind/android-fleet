package com.vt.tv.usbota;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class i implements com.vt.tv.usbota.G.d {
    private List a = new ArrayList();
    final /* synthetic */ l b;

    i(l lVar) {
        this.b = lVar;
    }

    @Override // com.vt.tv.usbota.G.d
    public void a(int i, Object... objArr) {
        m.a().c(SysUpgradeState.VERIFY_FAILED);
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((com.vt.tv.usbota.G.d) it.next()).a(i, objArr);
            }
        }
        E.j((Context) this.b.a.get()).m(i == 1 ? 13 : 8);
    }

    @Override // com.vt.tv.usbota.G.d
    public void b() {
        m.a().c(SysUpgradeState.VERIFIED);
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((com.vt.tv.usbota.G.d) it.next()).b();
            }
        }
        if (!this.b.c.e()) {
            E.j((Context) this.b.a.get()).n(this.b.c);
        }
        this.b.u();
    }

    public void c(com.vt.tv.usbota.G.d dVar) {
        if (dVar != null) {
            synchronized (this.a) {
                if (!this.a.contains(dVar)) {
                    this.a.add(dVar);
                }
            }
        }
    }

    @Override // com.vt.tv.usbota.G.d
    public void onProgress(int i) {
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((com.vt.tv.usbota.G.d) it.next()).onProgress(i);
            }
        }
    }
}
