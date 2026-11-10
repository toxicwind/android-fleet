package com.ju.lib.datacommunication.network.http.dns;

import android.os.SystemClock;
import com.ju.lib.datacommunication.network.http.core.u;
import com.ju.lib.datacommunication.network.http.core.v;
import java.net.InetAddress;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes.dex */
class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile Map f114a;
    private Set b;

    private c() {
        this.f114a = new ConcurrentHashMap();
        this.b = new CopyOnWriteArraySet();
    }

    public static final c d() {
        return b.f113a;
    }

    private boolean e(v vVar) {
        return SystemClock.elapsedRealtime() - vVar.j() > vVar.k() * 1000;
    }

    public void a(String str) {
        this.b.add(str);
    }

    public synchronized void b(String str, InetAddress inetAddress, long j) {
        v vVar = (v) this.f114a.get(str);
        if (vVar != null && vVar.l() == 1 && vVar.k() > j) {
            if (((vVar.k() * 1000) + vVar.j()) - SystemClock.elapsedRealtime() > 1000 * j) {
                Map map = this.f114a;
                String strE = vVar.e();
                u uVarD = vVar.d();
                uVarD.k(j);
                uVarD.i(SystemClock.elapsedRealtime());
                map.put(strE, uVarD.h());
            }
        }
    }

    public synchronized v c(String str) {
        try {
            v vVar = (v) this.f114a.get(str);
            if (vVar == null) {
                return null;
            }
            if (!e(vVar)) {
                return vVar;
            }
            this.f114a.remove(str);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        throw th;
    }

    public boolean f(String str) {
        return this.b.contains(str);
    }

    public synchronized void g(v vVar) {
        this.f114a.put(vVar.e(), vVar);
    }
}
