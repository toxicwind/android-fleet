package c.a.b;

import c.a.AbstractC0226j;
import c.a.C0220f;
import c.a.C0223ga;
import c.a.EnumC0234s;
import com.google.common.base.MoreObjects;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public abstract class _a extends c.a.X {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c.a.X f1205a;

    public _a(c.a.X x) {
        this.f1205a = x;
    }

    @Override // c.a.X
    public boolean a(long j, TimeUnit timeUnit) {
        return this.f1205a.a(j, timeUnit);
    }

    @Override // c.a.AbstractC0222g
    public String b() {
        return this.f1205a.b();
    }

    @Override // c.a.X
    public void c() {
        this.f1205a.c();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", this.f1205a).toString();
    }

    @Override // c.a.AbstractC0222g
    public <RequestT, ResponseT> AbstractC0226j<RequestT, ResponseT> a(C0223ga<RequestT, ResponseT> c0223ga, C0220f c0220f) {
        return this.f1205a.a(c0223ga, c0220f);
    }

    @Override // c.a.X
    public EnumC0234s a(boolean z) {
        return this.f1205a.a(z);
    }

    @Override // c.a.X
    public void a(EnumC0234s enumC0234s, Runnable runnable) {
        this.f1205a.a(enumC0234s, runnable);
    }
}
