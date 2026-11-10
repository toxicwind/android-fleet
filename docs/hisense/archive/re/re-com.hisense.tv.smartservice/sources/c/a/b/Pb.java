package c.a.b;

import com.google.common.util.concurrent.DirectExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class Pb implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Rb f1116a;

    public Pb(Rb rb) {
        this.f1116a = rb;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        synchronized (this.f1116a) {
            this.f1116a.h = null;
            if (this.f1116a.f1131f == Rb.c.PING_SCHEDULED) {
                z = true;
                this.f1116a.f1131f = Rb.c.PING_SENT;
                this.f1116a.g = this.f1116a.f1127b.schedule(this.f1116a.i, this.f1116a.l, TimeUnit.NANOSECONDS);
            } else {
                if (this.f1116a.f1131f == Rb.c.PING_DELAYED) {
                    this.f1116a.h = this.f1116a.f1127b.schedule(this.f1116a.j, this.f1116a.k - this.f1116a.f1128c.elapsed(TimeUnit.NANOSECONDS), TimeUnit.NANOSECONDS);
                    this.f1116a.f1131f = Rb.c.PING_SCHEDULED;
                }
                z = false;
            }
        }
        if (z) {
            Rb.a aVar = (Rb.a) this.f1116a.f1129d;
            aVar.f1132a.a(new Qb(aVar), DirectExecutor.INSTANCE);
        }
    }
}
