package c.a.b;

import c.a.C0219ea;

/* JADX INFO: loaded from: classes2.dex */
final class J extends Z {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ c.b.b f1031b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ c.a.za f1032c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ C0219ea f1033d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ L.a f1034e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public J(L.a aVar, c.b.b bVar, c.a.za zaVar, C0219ea c0219ea) {
        super(L.this.h);
        this.f1034e = aVar;
        this.f1031b = bVar;
        this.f1032c = zaVar;
        this.f1033d = c0219ea;
    }

    @Override // c.a.b.Z
    public void a() {
        c.b.c.f1949a.b("ClientCall$Listener.onClose", L.this.f1055d);
        c.b.c.f1949a.a(this.f1031b);
        try {
            b();
        } finally {
            c.b.c.f1949a.c("ClientCall$Listener.onClose", L.this.f1055d);
        }
    }

    public final void b() {
        c.a.za zaVar = this.f1032c;
        C0219ea c0219ea = this.f1033d;
        c.a.za zaVar2 = this.f1034e.f1059b;
        if (zaVar2 != null) {
            c0219ea = new C0219ea();
            zaVar = zaVar2;
        }
        L.this.m = true;
        try {
            L.this.a(this.f1034e.f1058a, zaVar, c0219ea);
        } finally {
            L.this.b();
            L.this.g.a(zaVar.c());
        }
    }
}
