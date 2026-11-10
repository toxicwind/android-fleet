package com.hisense.tv.ota;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.util.Log;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
class C extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WeakReference f6a;
    private Context b;

    public C(Looper looper, Q q, Context context) {
        super(looper);
        this.f6a = new WeakReference(q);
        this.b = context;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        Dialog dialog;
        WeakReference weakReference = this.f6a;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        if (com.hisense.tv.ota.W.k.y(this.b)) {
            if (!SystemProperties.getBoolean("sys.vt.ota.manual.start", false)) {
                return;
            } else {
                Log.i("SysUpgradeUIManager", "in storemode,but manual start, should show no new version message");
            }
        }
        if (com.hisense.tv.ota.W.k.F()) {
            return;
        }
        Q q = (Q) this.f6a.get();
        Object obj = message.obj;
        C0002c c0002c = null;
        if (obj != null && (obj instanceof C0002c)) {
            c0002c = (C0002c) obj;
        }
        switch (message.what) {
            case 1:
                if (!q.j.isShowing()) {
                    if (c0002c != null) {
                        q.j.o(c0002c.e().g());
                        q.j.k(c0002c.e().f());
                        q.j.n(c0002c.e().e());
                        q.j.l(c0002c.e().b());
                        q.j.m(c0002c.e().i());
                    }
                    dialog = q.j;
                    dialog.show();
                }
                break;
            case 2:
                if (!q.k.isShowing()) {
                    dialog = q.k;
                    dialog.show();
                }
                break;
            case 3:
                if (!q.l.isShowing()) {
                    dialog = q.l;
                    dialog.show();
                }
                break;
            case 4:
                if (!q.m.isShowing()) {
                    dialog = q.m;
                    dialog.show();
                }
                break;
            case 5:
                if (!q.o.isShowing()) {
                    if (message.obj != null) {
                        q.o.g(((Integer) message.obj).intValue());
                        q.o.h(q.u);
                    }
                    dialog = q.o;
                    dialog.show();
                }
                break;
            case 6:
                if (!q.p.isShowing()) {
                    dialog = q.p;
                    dialog.show();
                }
                break;
            case 7:
                if (!q.n.isShowing()) {
                    dialog = q.n;
                    dialog.show();
                }
                break;
            case 8:
                if (!q.q.isShowing()) {
                    dialog = q.q;
                    dialog.show();
                }
                break;
            case 9:
                if (!q.r.isShowing()) {
                    dialog = q.r;
                    dialog.show();
                }
                break;
            case 10:
                if (!q.s.isShowing()) {
                    dialog = q.s;
                    dialog.show();
                }
                break;
            case 11:
                if (!q.t.isShowing()) {
                    dialog = q.t;
                    dialog.show();
                }
                break;
        }
        super.handleMessage(message);
    }
}
