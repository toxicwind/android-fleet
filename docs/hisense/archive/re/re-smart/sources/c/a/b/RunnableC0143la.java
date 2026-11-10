package c.a.b;

import c.a.C0219ea;

/* JADX INFO: renamed from: c.a.b.la, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0143la implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0219ea f1338a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0138ka.b f1339b;

    public RunnableC0143la(C0138ka.b bVar, C0219ea c0219ea) {
        this.f1339b = bVar;
        this.f1338a = c0219ea;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1339b.f1333a.onHeaders(this.f1338a);
    }
}
