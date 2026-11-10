package c.a.b;

import c.a.AbstractC0226j;
import c.a.C0219ea;

/* JADX INFO: loaded from: classes2.dex */
class F extends Z {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AbstractC0226j.a f961b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ L f962c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public F(L l, AbstractC0226j.a aVar) {
        super(l.h);
        this.f962c = l;
        this.f961b = aVar;
    }

    @Override // c.a.b.Z
    public void a() {
        L l = this.f962c;
        l.a(this.f961b, b.b.a.c.a.a(l.h), new C0219ea());
    }
}
