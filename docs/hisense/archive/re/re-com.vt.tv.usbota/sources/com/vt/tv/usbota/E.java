package com.vt.tv.usbota;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class E {
    private static E r;
    private WeakReference a;
    private Handler b;
    private com.vt.tv.usbota.F.j f;
    private com.vt.tv.usbota.F.q g;
    private com.vt.tv.usbota.F.l h;
    private com.vt.tv.usbota.F.h i;
    private com.vt.tv.usbota.F.e j;
    private com.vt.tv.usbota.F.s k;
    private D c = new D(this);
    private z d = new z(this);
    private w e = new w(this);
    private com.vt.tv.usbota.F.m l = new o(this);
    private com.vt.tv.usbota.F.m m = new p(this);
    private com.vt.tv.usbota.F.m n = new q(this);
    private com.vt.tv.usbota.F.m o = new C0005r(this);
    private com.vt.tv.usbota.F.m p = new s(this);
    private com.vt.tv.usbota.F.m q = new t(this);

    private E(Context context) {
        this.a = new WeakReference(context);
        u uVar = new u(context.getMainLooper(), this);
        this.b = uVar;
        uVar.post(new n(this));
    }

    public static E j(Context context) {
        if (r == null) {
            synchronized (E.class) {
                if (r == null) {
                    r = new E(context);
                }
            }
        }
        return r;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        com.vt.tv.usbota.F.j jVar = new com.vt.tv.usbota.F.j((Context) this.a.get());
        this.f = jVar;
        jVar.e(this.l);
        com.vt.tv.usbota.F.q qVar = new com.vt.tv.usbota.F.q((Context) this.a.get());
        this.g = qVar;
        qVar.e(this.m);
        this.c.d(this.g);
        l.k((Context) this.a.get()).q(this.c);
        com.vt.tv.usbota.F.h hVar = new com.vt.tv.usbota.F.h((Context) this.a.get());
        this.i = hVar;
        hVar.e(this.n);
        this.d.e(this.i);
        l.k((Context) this.a.get()).p(this.d);
        com.vt.tv.usbota.F.l lVar = new com.vt.tv.usbota.F.l((Context) this.a.get());
        this.h = lVar;
        lVar.e(this.o);
        com.vt.tv.usbota.F.e eVar = new com.vt.tv.usbota.F.e((Context) this.a.get());
        this.j = eVar;
        eVar.e(this.p);
        com.vt.tv.usbota.F.s sVar = new com.vt.tv.usbota.F.s((Context) this.a.get());
        this.k = sVar;
        sVar.e(this.q);
        this.e.c(this.k);
        l.k((Context) this.a.get()).o(this.e);
    }

    private boolean l() {
        WeakReference weakReference = this.a;
        return weakReference == null || weakReference.get() == null;
    }

    public void m(int i) {
        this.b.obtainMessage(5, Integer.valueOf(i)).sendToTarget();
    }

    public void n(C0000a c0000a) {
        if (c0000a == null) {
            Log.e("SysUpgradeUIManager", "show Install ui error, session is null");
        } else {
            this.b.obtainMessage(7, c0000a).sendToTarget();
        }
    }

    public void o(C0000a c0000a) {
        if (c0000a == null) {
            Log.e("SysUpgradeUIManager", "show new version ui error, session is null");
        } else if (l()) {
            Log.e("SysUpgradeUIManager", "show new version ui error, context is empty");
        } else {
            this.b.obtainMessage(1, c0000a).sendToTarget();
        }
    }

    public void p(C0000a c0000a) {
        if (c0000a == null) {
            Log.e("SysUpgradeUIManager", "show reboot ui error, session is null");
        } else {
            this.b.obtainMessage(4, c0000a).sendToTarget();
        }
    }

    public void q(C0000a c0000a) {
        if (c0000a == null) {
            Log.e("SysUpgradeUIManager", "show verify ui error, session is null");
        } else {
            this.b.obtainMessage(3, c0000a).sendToTarget();
        }
    }

    public void r(C0000a c0000a) {
        if (c0000a == null) {
            Log.e("SysUpgradeUIManager", "show version checking ui error, session is null");
        } else if (l()) {
            Log.e("SysUpgradeUIManager", "show new version ui error, context is empty");
        } else {
            Log.e("SysUpgradeUIManager", "showVersionCheckingUI");
            this.b.obtainMessage(6, c0000a).sendToTarget();
        }
    }
}
