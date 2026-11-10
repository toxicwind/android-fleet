package c.a.b;

import c.a.AbstractC0226j;

/* JADX INFO: renamed from: c.a.b.ea, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0108ea implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c.a.za f1248a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0138ka f1249b;

    public RunnableC0108ea(C0138ka c0138ka, c.a.za zaVar) {
        this.f1249b = c0138ka;
        this.f1248a = zaVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        AbstractC0226j abstractC0226j = this.f1249b.h;
        c.a.za zaVar = this.f1248a;
        abstractC0226j.cancel(zaVar.q, zaVar.r);
    }
}
