package c.a.b;

import c.a.C0219ea;

/* JADX INFO: renamed from: c.a.b.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0092b implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c.a.za f1226a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ N.a f1227b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ C0219ea f1228c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ AbstractC0087a.c f1229d;

    public RunnableC0092b(AbstractC0087a.c cVar, c.a.za zaVar, N.a aVar, C0219ea c0219ea) {
        this.f1229d = cVar;
        this.f1226a = zaVar;
        this.f1227b = aVar;
        this.f1228c = c0219ea;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1229d.a(this.f1226a, this.f1227b, this.f1228c);
    }
}
