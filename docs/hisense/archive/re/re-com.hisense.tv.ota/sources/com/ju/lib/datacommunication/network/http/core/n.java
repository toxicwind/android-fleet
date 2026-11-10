package com.ju.lib.datacommunication.network.http.core;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class n {
    private static final AtomicInteger j = new AtomicInteger(0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f92a;
    private final int b;
    private final String c;
    private final HiRequest$Method d;
    private final i e;
    private final k f;
    private final m g;
    private final com.ju.lib.datacommunication.network.http.core.signature.b h;
    private x i;

    private n(l lVar) {
        this.f92a = j.getAndIncrement();
        this.c = lVar.f90a;
        this.d = lVar.b;
        this.e = lVar.c.c();
        this.f = lVar.d;
        int unused = lVar.e;
        Object unused2 = lVar.f;
        this.b = lVar.h;
        this.g = lVar.g;
        this.h = lVar.i;
    }

    /* synthetic */ n(l lVar, j jVar) {
        this(lVar);
    }

    public k a() {
        return this.f;
    }

    x b() {
        return this.i;
    }

    public i c() {
        return this.e;
    }

    public int d() {
        return this.f92a;
    }

    public HiRequest$Method e() {
        return this.d;
    }

    public m f() {
        return this.g;
    }

    public int g() {
        return this.b;
    }

    public com.ju.lib.datacommunication.network.http.core.signature.b h() {
        return this.h;
    }

    public String i() {
        return this.c;
    }

    void j(x xVar) {
        this.i = xVar;
    }
}
