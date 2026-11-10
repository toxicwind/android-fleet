package c.a.b;

/* JADX INFO: loaded from: classes2.dex */
public class Ab implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Bb f888a;

    public Ab(Bb bb) {
        this.f888a = bb;
    }

    @Override // java.lang.Runnable
    public void run() {
        Ib ib = this.f888a.f900b;
        Cc cc = ib.r;
        ib.q = null;
        ib.r = null;
        cc.b(c.a.za.l.b("InternalSubchannel closed transport due to address change"));
    }
}
