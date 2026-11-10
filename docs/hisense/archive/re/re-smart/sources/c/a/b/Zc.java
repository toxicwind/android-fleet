package c.a.b;

/* JADX INFO: loaded from: classes2.dex */
public class Zc implements Thread.UncaughtExceptionHandler {
    public Zc(AbstractC0171qd abstractC0171qd) {
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        throw c.a.za.a(th).b("Uncaught exception in the SynchronizationContext. Re-thrown.").b();
    }
}
