package c.a.b;

/* JADX INFO: loaded from: classes2.dex */
final class K extends Z {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ c.b.b f1039b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ L.a f1040c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public K(L.a aVar, c.b.b bVar) {
        super(L.this.h);
        this.f1040c = aVar;
        this.f1039b = bVar;
    }

    @Override // c.a.b.Z
    public void a() {
        c.b.c.f1949a.b("ClientCall$Listener.onReady", L.this.f1055d);
        c.b.c.f1949a.a(this.f1039b);
        try {
            L.a aVar = this.f1040c;
            if (aVar.f1059b == null) {
                try {
                    aVar.f1058a.onReady();
                } catch (Throwable th) {
                    L.a.a(this.f1040c, c.a.za.f1937d.b(th).b("Failed to call onReady."));
                }
            }
            c.b.c.f1949a.c("ClientCall$Listener.onReady", L.this.f1055d);
        } catch (Throwable th2) {
            c.b.c.f1949a.c("ClientCall$Listener.onReady", L.this.f1055d);
            throw th2;
        }
    }
}
