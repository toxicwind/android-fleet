package c.a.b;

import c.a.AbstractC0225i;
import c.a.EnumC0234s;

/* JADX INFO: renamed from: c.a.b.lc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
final class RunnableC0145lc implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c.a.U.h f1345a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ EnumC0234s f1346b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ C0130ic.h f1347c;

    public RunnableC0145lc(C0130ic.h hVar, c.a.U.h hVar2, EnumC0234s enumC0234s) {
        this.f1347c = hVar;
        this.f1345a = hVar2;
        this.f1346b = enumC0234s;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0130ic.h hVar = this.f1347c;
        if (hVar != C0130ic.this.I) {
            return;
        }
        C0130ic.a(C0130ic.this, this.f1345a);
        if (this.f1346b != EnumC0234s.SHUTDOWN) {
            C0130ic.this.Z.a(AbstractC0225i.a.INFO, "Entering {0} state with picker: {1}", this.f1346b, this.f1345a);
            C0130ic.this.C.a(this.f1346b);
        }
    }
}
