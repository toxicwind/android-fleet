package com.vt.tv.usbota;

import android.app.Dialog;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
class u extends Handler {
    private WeakReference a;

    public u(Looper looper, E e) {
        super(looper);
        this.a = new WeakReference(e);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        Dialog dialog;
        WeakReference weakReference = this.a;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        E e = (E) this.a.get();
        Object obj = message.obj;
        C0000a c0000a = null;
        if (obj != null && (obj instanceof C0000a)) {
            c0000a = (C0000a) obj;
        }
        Log.i("SysUpgradeUIManager", "handleMessage ");
        int i = message.what;
        if (i != 1) {
            if (i == 3) {
                Log.i("SysUpgradeUIManager", "1 MSG_TYPE_SHOW_VERIDY_UI ");
                if (!e.g.isShowing()) {
                    dialog = e.g;
                    dialog.show();
                }
            } else if (i != 4) {
                if (i != 5) {
                    if (i != 6) {
                        if (i == 7 && !e.i.isShowing()) {
                            dialog = e.i;
                            dialog.show();
                        }
                    } else if (!e.k.isShowing()) {
                        dialog = e.k;
                        dialog.show();
                    }
                } else if (!e.j.isShowing()) {
                    if (message.obj != null) {
                        e.j.g(((Integer) message.obj).intValue());
                    }
                    dialog = e.j;
                    dialog.show();
                }
            } else if (!e.h.isShowing()) {
                dialog = e.h;
                dialog.show();
            }
        } else if (!e.f.isShowing()) {
            if (c0000a != null) {
                e.f.k(c0000a.d());
                e.f.h(c0000a.b());
                e.f.j(c0000a.a());
                e.f.i(false);
            }
            dialog = e.f;
            dialog.show();
        }
        super.handleMessage(message);
    }
}
