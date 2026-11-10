package c.a.b;

/* JADX INFO: renamed from: c.a.b.tb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0183tb implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ O.a f1470a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Throwable f1471b;

    public RunnableC0183tb(O.a aVar, Throwable th) {
        this.f1470a = aVar;
        this.f1471b = th;
    }

    @Override // java.lang.Runnable
    public void run() {
        O.a aVar = this.f1470a;
        Throwable th = this.f1471b;
        ((Qb) aVar).f1123a.f1132a.a(c.a.za.l.b("Keepalive failed. The connection is likely gone"));
    }
}
