package com.vt.tv.usbota;

import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class k implements j {
    private List a = new ArrayList();
    final /* synthetic */ l b;

    k(l lVar) {
        this.b = lVar;
    }

    @Override // com.vt.tv.usbota.j
    public void a(int i) {
        E eJ;
        int i2;
        Log.i("SysUpgradeManager", "VersionCheckListener onResult resultCode = " + i);
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((j) it.next()).a(i);
            }
        }
        if (1 == i) {
            E.j((Context) this.b.a.get()).m(3);
            return;
        }
        if (2 == i) {
            E.j((Context) this.b.a.get()).m(4);
            return;
        }
        if (3 == i) {
            eJ = E.j((Context) this.b.a.get());
            i2 = 5;
        } else {
            if (4 != i) {
                E.j((Context) this.b.a.get()).o(this.b.c);
                m.a().c(SysUpgradeState.FOUND_NEW_VERSION);
                this.b.b(3, 1000);
                return;
            }
            eJ = E.j((Context) this.b.a.get());
            i2 = 6;
        }
        eJ.m(i2);
    }

    public void b(j jVar) {
        if (jVar != null) {
            synchronized (this.a) {
                if (!this.a.contains(jVar)) {
                    this.a.add(jVar);
                }
            }
        }
    }
}
