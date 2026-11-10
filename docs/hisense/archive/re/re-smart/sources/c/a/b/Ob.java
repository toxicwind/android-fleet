package c.a.b;

/* JADX INFO: loaded from: classes2.dex */
public class Ob implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Rb f1097a;

    public Ob(Rb rb) {
        this.f1097a = rb;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        synchronized (this.f1097a) {
            if (this.f1097a.f1131f != Rb.c.DISCONNECTED) {
                this.f1097a.f1131f = Rb.c.DISCONNECTED;
                z = true;
            } else {
                z = false;
            }
        }
        if (z) {
            ((Rb.a) this.f1097a.f1129d).f1132a.a(c.a.za.l.b("Keepalive failed. The connection is likely gone"));
        }
    }
}
