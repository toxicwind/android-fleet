package com.ju.lib.datacommunication.network.http.dns;

import com.ju.lib.datacommunication.network.http.core.z;

/* JADX INFO: loaded from: classes.dex */
public class k {
    public static double a(double d) {
        if (d < 0.5d) {
            return 0.5d;
        }
        if (d > 5.0d) {
            return 5.0d;
        }
        return d;
    }

    public static int b(int i) {
        if (i < 150) {
            return 150;
        }
        if (i > 300) {
            return 300;
        }
        return i;
    }

    public static com.ju.lib.datacommunication.network.http.impl.l c(long j, com.ju.lib.datacommunication.network.http.impl.l lVar) {
        z zVar = new z();
        zVar.r(j);
        zVar.z(j);
        zVar.w(false);
        zVar.x(false);
        return new com.ju.lib.datacommunication.network.http.impl.l(zVar, lVar);
    }
}
