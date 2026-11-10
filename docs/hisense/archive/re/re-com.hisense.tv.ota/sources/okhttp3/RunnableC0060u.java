package okhttp3;

/* JADX INFO: renamed from: okhttp3.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class RunnableC0060u implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ C0061v f255a;

    RunnableC0060u(C0061v c0061v) {
        this.f255a = c0061v;
    }

    @Override // java.lang.Runnable
    public void run() {
        while (true) {
            long jA = this.f255a.a(System.nanoTime());
            if (jA == -1) {
                return;
            }
            if (jA > 0) {
                long j = jA / 1000000;
                long j2 = jA - (1000000 * j);
                synchronized (this.f255a) {
                    try {
                        this.f255a.wait(j, (int) j2);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }
    }
}
