package c.a.b;

import c.a.AbstractC0225i;
import c.a.EnumC0234s;

/* JADX INFO: renamed from: c.a.b.ac, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
final class RunnableC0090ac implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0130ic f1220a;

    public RunnableC0090ac(C0130ic c0130ic) {
        this.f1220a = c0130ic;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1220a.Z.a(AbstractC0225i.a.INFO, "Entering SHUTDOWN state");
        this.f1220a.C.a(EnumC0234s.SHUTDOWN);
    }
}
