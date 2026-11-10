package c.a.b;

import com.google.common.base.MoreObjects;

/* JADX INFO: loaded from: classes2.dex */
public final class Yb extends c.a.U.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c.a.U.d f1193a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Throwable f1194b;

    public Yb(C0130ic c0130ic, Throwable th) {
        this.f1194b = th;
        this.f1193a = c.a.U.d.a(c.a.za.k.b("Panic! This is a bug!").b(this.f1194b));
    }

    @Override // c.a.U.h
    public c.a.U.d a(c.a.U.e eVar) {
        return this.f1193a;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Class<?>) Yb.class).add("panicPickResult", this.f1193a).toString();
    }
}
