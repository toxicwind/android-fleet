package c.a.b;

import c.a.EnumC0234s;

/* JADX INFO: loaded from: classes2.dex */
final class Xb implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Runnable f1186a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ EnumC0234s f1187b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ C0130ic f1188c;

    public Xb(C0130ic c0130ic, Runnable runnable, EnumC0234s enumC0234s) {
        this.f1188c = c0130ic;
        this.f1186a = runnable;
        this.f1187b = enumC0234s;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1188c.C.a(this.f1186a, this.f1188c.q, this.f1187b);
    }
}
