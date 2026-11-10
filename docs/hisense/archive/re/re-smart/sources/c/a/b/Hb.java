package c.a.b;

/* JADX INFO: loaded from: classes2.dex */
public class Hb extends Va {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ M f992a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Ib.a f993b;

    public Hb(Ib.a aVar, M m) {
        this.f993b = aVar;
        this.f992a = m;
    }

    @Override // c.a.b.M
    public void a(N n) {
        A a2 = this.f993b.f1015b;
        a2.f883b.add(1L);
        ((Od) a2.f882a).a();
        this.f992a.a(new Gb(this, n));
    }
}
