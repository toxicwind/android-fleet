package okhttp3.internal.http2;

import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Socket f242a;
    String b;
    okio.j c;
    okio.i d;
    s e = s.f243a;
    K f = K.f234a;
    boolean g;
    int h;

    public p(boolean z) {
        this.g = z;
    }

    public y a() {
        return new y(this);
    }

    public p b(s sVar) {
        this.e = sVar;
        return this;
    }

    public p c(int i) {
        this.h = i;
        return this;
    }

    public p d(Socket socket, String str, okio.j jVar, okio.i iVar) {
        this.f242a = socket;
        this.b = str;
        this.c = jVar;
        this.d = iVar;
        return this;
    }
}
