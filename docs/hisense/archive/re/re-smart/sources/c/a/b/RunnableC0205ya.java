package c.a.b;

import c.a.C0240y;

/* JADX INFO: renamed from: c.a.b.ya, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0205ya implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0240y f1514a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Na f1515b;

    public RunnableC0205ya(Na na, C0240y c0240y) {
        this.f1515b = na;
        this.f1514a = c0240y;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1515b.f1088c.a(this.f1514a);
    }
}
