package com.ju.lib.datacommunication.network.http.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private y f100a;
    private File b;
    private long c;
    private long d;
    private long e;
    private boolean f;
    private boolean g;
    private w h;
    private SSLSocketFactory i;
    private X509TrustManager j;
    private HostnameVerifier k;
    private com.ju.lib.datacommunication.network.http.g.l l;
    private List m = new ArrayList();
    private List n = new ArrayList();
    private com.ju.lib.datacommunication.network.http.f o;

    public void A(SSLSocketFactory sSLSocketFactory) {
        this.i = sSLSocketFactory;
    }

    public void B(X509TrustManager x509TrustManager) {
        this.j = x509TrustManager;
    }

    public w a() {
        return this.h;
    }

    public File b() {
        return this.b;
    }

    public long c() {
        return this.c;
    }

    public long d() {
        return this.e;
    }

    public List e() {
        return this.m;
    }

    public List f() {
        return this.n;
    }

    public y g() {
        return this.f100a;
    }

    public com.ju.lib.datacommunication.network.http.g.l h() {
        return this.l;
    }

    public HostnameVerifier i() {
        return this.k;
    }

    public com.ju.lib.datacommunication.network.http.f j() {
        return this.o;
    }

    public long k() {
        return this.d;
    }

    public SSLSocketFactory l() {
        return this.i;
    }

    public X509TrustManager m() {
        return this.j;
    }

    public boolean n() {
        return this.f;
    }

    public boolean o() {
        return this.g;
    }

    public void p(w wVar) {
        this.h = wVar;
    }

    public void q(File file, long j) {
        this.b = file;
        this.c = j;
    }

    public void r(long j) {
        this.e = j;
    }

    public void s(List list) {
        this.m = list;
    }

    public void t(List list) {
        this.n = list;
    }

    public String toString() {
        return "HttpConfig: Dns = " + this.f100a + ", CacheDirectory = " + this.b + ", CacheSize = " + this.c + ", ReadTimeOut = " + this.d + ", ConnectTimeOut = " + this.e + ", EnableLog = " + this.f + ", EnableRetry = " + this.g;
    }

    public void u(y yVar) {
        this.f100a = yVar;
    }

    public void v(com.ju.lib.datacommunication.network.http.g.l lVar) {
        this.l = lVar;
    }

    public void w(boolean z) {
        this.f = z;
    }

    public void x(boolean z) {
        this.g = z;
    }

    public void y(com.ju.lib.datacommunication.network.http.f fVar) {
        this.o = fVar;
    }

    public void z(long j) {
        this.d = j;
    }
}
