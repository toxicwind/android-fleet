package com.ju.lib.datacommunication.network.http.core;

/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f95a;
    private final String b;
    private final p c;

    private s(q qVar) {
        n unused = qVar.f93a;
        this.f95a = qVar.b;
        this.b = qVar.c;
        qVar.d.c();
        this.c = qVar.e;
        r unused2 = qVar.f;
    }

    public p a() {
        return this.c;
    }

    public int b() {
        return this.f95a;
    }

    public String c() {
        return this.b;
    }

    public boolean d() {
        int i = this.f95a;
        return i >= 200 && i < 300;
    }
}
