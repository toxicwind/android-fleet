package c.a.b;

/* JADX INFO: loaded from: classes2.dex */
public final class A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Pd f882a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Tb f883b = b.b.a.c.a.a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Tb f884c = b.b.a.c.a.a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Tb f885d = b.b.a.c.a.a();

    public interface a {
        A a();
    }

    public A(Pd pd) {
        this.f882a = pd;
    }

    public void a(boolean z) {
        if (z) {
            this.f884c.add(1L);
        } else {
            this.f885d.add(1L);
        }
    }
}
