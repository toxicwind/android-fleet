package com.ju.lib.datacommunication.network.http.dns;

/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f120a;
    double b;

    public n(Integer num, Double d) {
        this.f120a = -1;
        this.b = -1.0d;
        this.f120a = num.intValue();
        this.b = d.doubleValue();
    }

    public static n a(n nVar) {
        if (nVar == null) {
            return null;
        }
        if (nVar.f120a < 0 && nVar.b < 0.0d) {
            return null;
        }
        if (nVar.f120a < 0) {
            nVar.f120a = 180;
        }
        if (nVar.b < 0.0d) {
            nVar.b = 1.2d;
        }
        return nVar;
    }

    public long b() {
        return p.j(this.f120a);
    }

    public long c() {
        return p.j(this.b);
    }

    public boolean d(n nVar) {
        return nVar != null && this.f120a == nVar.f120a && this.b == nVar.b;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("timeout = ");
        stringBuffer.append(this.b);
        stringBuffer.append(", coolTime = ");
        stringBuffer.append(this.f120a);
        return stringBuffer.toString();
    }
}
