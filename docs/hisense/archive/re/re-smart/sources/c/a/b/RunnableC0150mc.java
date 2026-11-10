package c.a.b;

/* JADX INFO: renamed from: c.a.b.mc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
final class RunnableC0150mc implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c.a.za f1370a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0130ic.i f1371b;

    public RunnableC0150mc(C0130ic.i iVar, c.a.za zaVar) {
        this.f1371b = iVar;
        this.f1370a = zaVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1371b.b(this.f1370a);
    }
}
