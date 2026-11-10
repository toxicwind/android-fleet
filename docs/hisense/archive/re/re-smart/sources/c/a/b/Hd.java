package c.a.b;

/* JADX INFO: loaded from: classes2.dex */
public class Hd implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Id.a f1000a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Id.b f1001b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f1002c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Id f1003d;

    public Hd(Id id, Id.a aVar, Id.b bVar, Object obj) {
        this.f1003d = id;
        this.f1000a = aVar;
        this.f1001b = bVar;
        this.f1002c = obj;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.f1003d) {
            if (this.f1000a.f1029b == 0) {
                try {
                    this.f1001b.a(this.f1002c);
                    this.f1003d.f1025b.remove(this.f1001b);
                    if (this.f1003d.f1025b.isEmpty()) {
                        this.f1003d.f1027d.shutdown();
                        this.f1003d.f1027d = null;
                    }
                } catch (Throwable th) {
                    this.f1003d.f1025b.remove(this.f1001b);
                    if (this.f1003d.f1025b.isEmpty()) {
                        this.f1003d.f1027d.shutdown();
                        this.f1003d.f1027d = null;
                    }
                    throw th;
                }
            }
        }
    }
}
