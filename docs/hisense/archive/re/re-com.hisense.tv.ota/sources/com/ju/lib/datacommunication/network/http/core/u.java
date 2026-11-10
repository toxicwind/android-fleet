package com.ju.lib.datacommunication.network.http.core;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f98a;
    private Set b;
    private Set c;
    private long d;
    private long e;

    public u() {
        this.b = new LinkedHashSet();
        this.c = new LinkedHashSet();
    }

    private u(v vVar) {
        this.f98a = vVar.f99a;
        this.b = new LinkedHashSet(vVar.g());
        this.c = new LinkedHashSet(vVar.i());
        this.d = vVar.d;
        this.e = vVar.e;
    }

    public u f(Collection collection) {
        this.b.addAll(collection);
        return this;
    }

    public u g(Collection collection) {
        this.c.addAll(collection);
        return this;
    }

    public v h() {
        return new v(this);
    }

    public u i(long j) {
        this.e = j;
        return this;
    }

    public u j(String str) {
        this.f98a = str;
        return this;
    }

    public u k(long j) {
        this.d = j;
        return this;
    }
}
