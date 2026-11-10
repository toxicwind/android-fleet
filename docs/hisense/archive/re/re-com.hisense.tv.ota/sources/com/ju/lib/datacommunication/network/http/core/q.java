package com.ju.lib.datacommunication.network.http.core;

/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private n f93a;
    private String c;
    private p e;
    private r f;
    private int b = -1;
    private g d = new g();

    public q g(String str, String str2) {
        this.d.b(str, str2);
        return this;
    }

    public q h(p pVar) {
        this.e = pVar;
        return this;
    }

    public s i() {
        if (this.b >= 0) {
            return new s(this);
        }
        throw new IllegalStateException("code < 0: " + this.b);
    }

    public q j(int i) {
        this.b = i;
        return this;
    }

    public q k(String str) {
        this.c = str;
        return this;
    }

    public q l(n nVar) {
        this.f93a = nVar;
        return this;
    }

    public q m(r rVar) {
        this.f = rVar;
        return this;
    }
}
