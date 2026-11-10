package c.a.b;

import c.a.C0219ea;

/* JADX INFO: renamed from: c.a.b.pd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0166pd implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c.a.za f1408a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AbstractC0171qd f1409b;

    public RunnableC0166pd(AbstractC0171qd abstractC0171qd, c.a.za zaVar) {
        this.f1409b = abstractC0171qd;
        this.f1408a = zaVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1409b.A = true;
        this.f1409b.v.a(this.f1408a, N.a.PROCESSED, new C0219ea());
    }
}
