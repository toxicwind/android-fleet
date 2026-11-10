package com.hisense.tv.ota;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.hisense.tv.ota.ui.ViewOnClickListenerC0025e;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class Q {
    private static Q C;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WeakReference f19a;
    private Handler b;
    private com.hisense.tv.ota.ui.w j;
    private ViewOnClickListenerC0025e k;
    private com.hisense.tv.ota.ui.D l;
    private com.hisense.tv.ota.ui.y m;
    private com.hisense.tv.ota.ui.t n;
    private com.hisense.tv.ota.ui.h o;
    private com.hisense.tv.ota.ui.F p;
    private com.hisense.tv.ota.ui.q q;
    private com.hisense.tv.ota.ui.l r;
    private com.hisense.tv.ota.ui.p s;
    private com.hisense.tv.ota.ui.n t;
    private I c = new I(this);
    private I d = new I(this);
    private P e = new P(this);
    private P f = new P(this);
    private L g = new L(this);
    private L h = new L(this);
    private E i = new E(this);
    private boolean u = false;
    private com.hisense.tv.ota.ui.z v = new v(this);
    private com.hisense.tv.ota.ui.z w = new w(this);
    private com.hisense.tv.ota.ui.z x = new x(this);
    private com.hisense.tv.ota.ui.z y = new y(this);
    private com.hisense.tv.ota.ui.z z = new z(this);
    private com.hisense.tv.ota.ui.z A = new A(this);
    private com.hisense.tv.ota.ui.z B = new B(this);

    private Q(Context context) {
        this.f19a = new WeakReference(context);
        C c = new C(context.getMainLooper(), this, context);
        this.b = c;
        c.post(new RunnableC0020u(this));
    }

    public static Q r(Context context) {
        if (C == null) {
            synchronized (Q.class) {
                if (C == null) {
                    C = new Q(context);
                }
            }
        }
        return C;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        com.hisense.tv.ota.ui.w wVar = new com.hisense.tv.ota.ui.w((Context) this.f19a.get());
        this.j = wVar;
        wVar.e(this.v);
        ViewOnClickListenerC0025e viewOnClickListenerC0025e = new ViewOnClickListenerC0025e((Context) this.f19a.get());
        this.k = viewOnClickListenerC0025e;
        viewOnClickListenerC0025e.e(this.w);
        this.c.g(this.k);
        C0016q.t((Context) this.f19a.get()).z(this.c);
        com.hisense.tv.ota.ui.l lVar = new com.hisense.tv.ota.ui.l((Context) this.f19a.get());
        this.r = lVar;
        this.d.g(lVar);
        C0016q.t((Context) this.f19a.get()).z(this.d);
        com.hisense.tv.ota.ui.D d = new com.hisense.tv.ota.ui.D((Context) this.f19a.get());
        this.l = d;
        d.e(this.x);
        this.e.d(this.l);
        C0016q.t((Context) this.f19a.get()).B(this.e);
        com.hisense.tv.ota.ui.p pVar = new com.hisense.tv.ota.ui.p((Context) this.f19a.get());
        this.s = pVar;
        this.f.d(pVar);
        C0016q.t((Context) this.f19a.get()).B(this.f);
        com.hisense.tv.ota.ui.t tVar = new com.hisense.tv.ota.ui.t((Context) this.f19a.get());
        this.n = tVar;
        tVar.e(this.y);
        this.g.e(this.n);
        C0016q.t((Context) this.f19a.get()).A(this.g);
        com.hisense.tv.ota.ui.n nVar = new com.hisense.tv.ota.ui.n((Context) this.f19a.get());
        this.t = nVar;
        this.h.e(nVar);
        C0016q.t((Context) this.f19a.get()).A(this.h);
        com.hisense.tv.ota.ui.y yVar = new com.hisense.tv.ota.ui.y((Context) this.f19a.get());
        this.m = yVar;
        yVar.e(this.z);
        com.hisense.tv.ota.ui.h hVar = new com.hisense.tv.ota.ui.h((Context) this.f19a.get());
        this.o = hVar;
        hVar.e(this.A);
        com.hisense.tv.ota.ui.F f = new com.hisense.tv.ota.ui.F((Context) this.f19a.get());
        this.p = f;
        f.e(this.B);
        this.i.d(this.p);
        C0016q.t((Context) this.f19a.get()).y(this.i);
        this.q = new com.hisense.tv.ota.ui.q((Context) this.f19a.get());
    }

    private boolean t() {
        WeakReference weakReference = this.f19a;
        return weakReference == null || weakReference.get() == null;
    }

    public void A(C0002c c0002c) {
        if (c0002c == null) {
            Log.e("SysUpgradeUIManager", "show Install ui error, session is null");
        } else {
            this.b.obtainMessage(7, c0002c).sendToTarget();
        }
    }

    public void B(C0002c c0002c) {
        if (c0002c == null) {
            Log.e("SysUpgradeUIManager", "show new version ui error, session is null");
            return;
        }
        if (t()) {
            Log.e("SysUpgradeUIManager", "show new version ui error, context is empty");
            return;
        }
        if (c0002c.i()) {
            this.u = true;
            Log.i("SysUpgradeUIManager", "set Ftemode as true ");
        }
        this.b.obtainMessage(1, c0002c).sendToTarget();
    }

    public void C(C0002c c0002c) {
        if (c0002c == null) {
            Log.e("SysUpgradeUIManager", "show reboot ui error, session is null");
        } else {
            this.b.obtainMessage(4, c0002c).sendToTarget();
        }
    }

    public void D(C0002c c0002c) {
        if (c0002c == null) {
            Log.e("SysUpgradeUIManager", "show verify ui error, session is null");
        } else {
            this.b.obtainMessage(3, c0002c).sendToTarget();
        }
    }

    public void E(C0002c c0002c) {
        if (c0002c == null) {
            Log.e("SysUpgradeUIManager", "show version checking ui error, session is null");
        } else if (t()) {
            Log.e("SysUpgradeUIManager", "show new version ui error, context is empty");
        } else {
            Log.e("SysUpgradeUIManager", "show version checking UI");
            this.b.obtainMessage(6, c0002c).sendToTarget();
        }
    }

    public void a() {
        this.q.dismiss();
    }

    public void b() {
        this.p.dismiss();
    }

    public void u(C0002c c0002c) {
        if (c0002c == null) {
            Log.e("SysUpgradeUIManager", "show download ui error, session is null");
        } else {
            this.b.obtainMessage(2, c0002c).sendToTarget();
        }
    }

    public void v(int i) {
        this.b.obtainMessage(5, Integer.valueOf(i)).sendToTarget();
    }

    public void w(C0002c c0002c) {
        if (c0002c == null) {
            Log.e("SysUpgradeUIManager", "show Ftedownload ui error, session is null");
        } else {
            this.b.obtainMessage(9, c0002c).sendToTarget();
        }
    }

    public void x(C0002c c0002c) {
        if (c0002c == null) {
            Log.e("SysUpgradeUIManager", "show FTE version checking ui error, session is null");
        } else if (t()) {
            Log.e("SysUpgradeUIManager", "show FTE new version ui error, context is empty");
        } else {
            this.b.obtainMessage(8, c0002c).sendToTarget();
        }
    }

    public void y(C0002c c0002c) {
        if (c0002c == null) {
            Log.e("SysUpgradeUIManager", "show Fte Install ui error, session is null");
        } else {
            this.b.obtainMessage(11, c0002c).sendToTarget();
        }
    }

    public void z(C0002c c0002c) {
        if (c0002c == null) {
            Log.e("SysUpgradeUIManager", "show Fte verify ui error, session is null");
        } else {
            this.b.obtainMessage(10, c0002c).sendToTarget();
        }
    }
}
