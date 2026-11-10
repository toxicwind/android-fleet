package com.ju.lib.datacommunication.network.http.core;

/* JADX INFO: loaded from: classes.dex */
public abstract class A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected z f78a;

    protected void a(n nVar, x xVar) {
        if (nVar == null) {
            throw new NullPointerException("HiRequest is null! ");
        }
        if (xVar == null) {
            throw new NullPointerException("Cancelable is null! ");
        }
        synchronized (this) {
            if (nVar.b() != null) {
                throw new IllegalArgumentException("HiRequest is binded! ");
            }
            nVar.j(xVar);
        }
    }

    public abstract void b(n nVar, B b);

    public abstract s c(n nVar);

    public z d() {
        return this.f78a;
    }

    public abstract void e();
}
