package c.a.b;

/* JADX INFO: renamed from: c.a.b.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0107e implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c.b.b f1245a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f1246b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ AbstractC0112f.a f1247c;

    public RunnableC0107e(AbstractC0112f.a aVar, c.b.b bVar, int i) {
        this.f1247c = aVar;
        this.f1245a = bVar;
        this.f1246b = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        c.b.c.f1949a.a("AbstractStream.request");
        c.b.c.f1949a.a(this.f1245a);
        try {
            try {
                this.f1247c.f1251a.b(this.f1246b);
            } catch (Throwable th) {
                ((c.a.c.k.b) this.f1247c).a(th);
            }
        } finally {
            c.b.c.f1949a.b("AbstractStream.request");
        }
    }
}
