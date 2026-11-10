package com.ju.lib.datacommunication.network.http.core;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class e {
    private static int c;
    private static int d = SystemProperties.getInt("persist.jhk.debug.netdelay", 60);
    private static List e = new ArrayList();
    private static long f = 60000;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final A f86a;
    private Map b = new HashMap();

    public e(A a2) {
        this.f86a = a2;
        if (g()) {
            synchronized (e) {
                e.add(new C0027a(this));
                if (f >= 0) {
                    new Handler(Looper.getMainLooper()).postDelayed(new RunnableC0028b(this), f);
                }
                f = -2L;
            }
        }
        Log.w("HTTP2.HiHttpClient", "sMaxResumeRequestDuration = " + f);
        Log.w("HTTP2.HiHttpClient", "default mDelay = " + d);
        Log.w("HTTP2.HiHttpClient", "default mPauseAllRequest = " + c);
    }

    private static boolean g() {
        return c > 0;
    }

    private static synchronized void h(com.ju.lib.datacommunication.network.http.b bVar) {
        c++;
        if (f != -1) {
            return;
        }
        long j = 0;
        if (bVar.b < 0) {
            f = j;
        } else {
            j = 60000;
            if (bVar.b > 60000) {
                f = j;
            } else {
                f = bVar.b;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.b.size() == 0) {
            return;
        }
        Log.w("HTTP2.", "mQuenue size = " + this.b.size());
        synchronized (this.b) {
            for (Map.Entry entry : this.b.entrySet()) {
                this.f86a.b((n) entry.getKey(), (B) entry.getValue());
            }
            this.b.clear();
        }
    }

    private static synchronized void j() {
        if (c == 0) {
            return;
        }
        c--;
    }

    public static void k() {
        j();
        if (g()) {
            return;
        }
        synchronized (e.class) {
            Iterator it = e.iterator();
            while (it.hasNext()) {
                ((d) it.next()).a();
            }
            e.clear();
        }
    }

    public static void l(com.ju.lib.datacommunication.network.http.b bVar) {
        if (bVar == null) {
            return;
        }
        int i = c.f85a[bVar.f76a.ordinal()];
        if (i == 1) {
            h(bVar);
        } else if (i == 2 && SystemClock.elapsedRealtime() / 1000 < d) {
            h(bVar);
        }
    }

    private void m() {
        while (g()) {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
    }

    public s d(n nVar) {
        m();
        return this.f86a.c(nVar);
    }

    public A e() {
        return this.f86a;
    }

    public void f() {
        A a2 = this.f86a;
        if (a2 != null) {
            a2.e();
        }
    }
}
