package c.a.c;

/* JADX INFO: renamed from: c.a.c.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0214a extends d.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c.b.b f1552b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ d f1553c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0214a(d dVar) {
        super(null);
        this.f1553c = dVar;
        this.f1552b = c.b.c.a();
    }

    @Override // c.a.c.d.a
    public void a() {
        c.b.c.f1949a.a("WriteRunnable.runWrite");
        c.b.c.f1949a.a(this.f1552b);
        d.g gVar = new d.g();
        try {
            synchronized (this.f1553c.f1678a) {
                gVar.write(this.f1553c.f1679b, this.f1553c.f1679b.m());
                this.f1553c.f1682e = false;
            }
            this.f1553c.h.write(gVar, gVar.f2102c);
            c.b.c.f1949a.b("WriteRunnable.runWrite");
        } catch (Throwable th) {
            c.b.c.f1949a.b("WriteRunnable.runWrite");
            throw th;
        }
    }
}
