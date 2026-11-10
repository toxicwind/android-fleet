package c.a.b;

import c.a.C0219ea;

/* JADX INFO: renamed from: c.a.b.vd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0195vd implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c.a.za f1496a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ N.a f1497b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ C0219ea f1498c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ AbstractC0171qd.i f1499d;

    public RunnableC0195vd(AbstractC0171qd.i iVar, c.a.za zaVar, N.a aVar, C0219ea c0219ea) {
        this.f1499d = iVar;
        this.f1496a = zaVar;
        this.f1497b = aVar;
        this.f1498c = c0219ea;
    }

    @Override // java.lang.Runnable
    public void run() {
        AbstractC0171qd.this.A = true;
        AbstractC0171qd.this.v.a(this.f1496a, this.f1497b, this.f1498c);
    }
}
