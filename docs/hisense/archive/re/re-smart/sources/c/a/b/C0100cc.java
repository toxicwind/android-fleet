package c.a.b;

import c.a.AbstractC0225i;
import c.a.EnumC0234s;
import java.util.logging.Level;

/* JADX INFO: renamed from: c.a.b.cc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0100cc implements Thread.UncaughtExceptionHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0130ic f1238a;

    public C0100cc(C0130ic c0130ic) {
        this.f1238a = c0130ic;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        C0130ic.f1271a.log(Level.SEVERE, b.a.a.a.a.a(b.a.a.a.a.a("["), this.f1238a.i, "] Uncaught exception in the SynchronizationContext. Panic!"), th);
        C0130ic c0130ic = this.f1238a;
        if (c0130ic.K) {
            return;
        }
        c0130ic.K = true;
        c0130ic.b(true);
        c0130ic.c(false);
        c0130ic.a(new Yb(c0130ic, th));
        c0130ic.Z.a(AbstractC0225i.a.ERROR, "PANIC! Entering TRANSIENT_FAILURE");
        c0130ic.C.a(EnumC0234s.TRANSIENT_FAILURE);
    }
}
