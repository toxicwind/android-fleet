package c.a.b;

import c.a.C0219ea;

/* JADX INFO: loaded from: classes2.dex */
final class H extends Z {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ c.b.b f987b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ C0219ea f988c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ L.a f989d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H(L.a aVar, c.b.b bVar, C0219ea c0219ea) {
        super(L.this.h);
        this.f989d = aVar;
        this.f987b = bVar;
        this.f988c = c0219ea;
    }

    @Override // c.a.b.Z
    public void a() {
        c.b.c.f1949a.b("ClientCall$Listener.headersRead", L.this.f1055d);
        c.b.c.f1949a.a(this.f987b);
        try {
            L.a aVar = this.f989d;
            if (aVar.f1059b == null) {
                try {
                    aVar.f1058a.onHeaders(this.f988c);
                } catch (Throwable th) {
                    L.a.a(this.f989d, c.a.za.f1937d.b(th).b("Failed to read headers"));
                }
            }
        } finally {
            c.b.c.f1949a.c("ClientCall$Listener.headersRead", L.this.f1055d);
        }
    }
}
