package com.ju.lib.datacommunication.network.http.core;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List f89a;

    private i(g gVar) {
        this.f89a = new ArrayList(gVar.f87a);
    }

    public String a(int i) {
        return ((h) this.f89a.get(i)).f88a;
    }

    public String b(int i) {
        return ((h) this.f89a.get(i)).b;
    }

    public int c() {
        return this.f89a.size();
    }

    public boolean equals(Object obj) {
        if (obj instanceof i) {
            return this.f89a.equals(((i) obj).f89a);
        }
        return false;
    }

    public int hashCode() {
        return this.f89a.hashCode();
    }
}
