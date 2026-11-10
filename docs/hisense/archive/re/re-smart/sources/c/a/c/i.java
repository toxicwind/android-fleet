package c.a.c;

import c.a.b.C0176s;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes2.dex */
public class i implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0176s.a f1712a;

    public i(h.d dVar, C0176s.a aVar) {
        this.f1712a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0176s.a aVar = this.f1712a;
        long j = aVar.f1455a;
        long jMax = Math.max(2 * j, j);
        if (C0176s.this.f1454c.compareAndSet(aVar.f1455a, jMax)) {
            C0176s.f1452a.log(Level.WARNING, "Increased {0} to {1}", new Object[]{C0176s.this.f1453b, Long.valueOf(jMax)});
        }
    }
}
