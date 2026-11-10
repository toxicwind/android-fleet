package c.a.c;

/* JADX INFO: loaded from: classes2.dex */
public class b extends d.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c.b.b f1675b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ d f1676c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(d dVar) {
        super(null);
        this.f1676c = dVar;
        this.f1675b = c.b.c.a();
    }

    @Override // c.a.c.d.a
    public void a() {
        c.b.c.f1949a.a("WriteRunnable.runFlush");
        c.b.c.f1949a.a(this.f1675b);
        d.g gVar = new d.g();
        try {
            synchronized (this.f1676c.f1678a) {
                gVar.write(this.f1676c.f1679b, this.f1676c.f1679b.f2102c);
                this.f1676c.f1683f = false;
            }
            this.f1676c.h.write(gVar, gVar.f2102c);
            this.f1676c.h.flush();
            c.b.c.f1949a.b("WriteRunnable.runFlush");
        } catch (Throwable th) {
            c.b.c.f1949a.b("WriteRunnable.runFlush");
            throw th;
        }
    }
}
