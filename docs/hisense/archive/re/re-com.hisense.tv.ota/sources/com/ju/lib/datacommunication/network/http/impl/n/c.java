package com.ju.lib.datacommunication.network.http.impl.n;

import okhttp3.N;
import okhttp3.O;
import okhttp3.c0;
import okhttp3.logging.HttpLoggingInterceptor$Level;

/* JADX INFO: loaded from: classes.dex */
public class c implements O {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final okhttp3.logging.b f154a;

    public c() {
        okhttp3.logging.b bVar = new okhttp3.logging.b(new b(this));
        this.f154a = bVar;
        bVar.e(HttpLoggingInterceptor$Level.HEADERS);
    }

    @Override // okhttp3.O
    public c0 a(N n) {
        Object[] objArr = new Object[2];
        objArr[0] = "Connection: ";
        objArr[1] = n.e().a() != null ? n.e().a().toString() : "null";
        com.ju.lib.datacommunication.network.http.i.b.d("HTTP2.LogInterceptor", objArr);
        return this.f154a.a(n);
    }
}
