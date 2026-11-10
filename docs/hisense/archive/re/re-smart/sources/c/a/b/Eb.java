package c.a.b;

/* JADX INFO: loaded from: classes2.dex */
public class Eb implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ X f937a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f938b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Ib f939c;

    public Eb(Ib ib, X x, boolean z) {
        this.f939c = ib;
        this.f937a = x;
        this.f938b = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f939c.t.a(this.f937a, this.f938b);
    }
}
