package c.a.b;

import c.a.AbstractC0226j;
import c.a.C0219ea;

/* JADX INFO: loaded from: classes2.dex */
class G extends Z {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AbstractC0226j.a f971b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f972c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ L f973d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public G(L l, AbstractC0226j.a aVar, String str) {
        super(l.h);
        this.f973d = l;
        this.f971b = aVar;
        this.f972c = str;
    }

    @Override // c.a.b.Z
    public void a() {
        this.f973d.a(this.f971b, c.a.za.k.b(String.format("Unable to find compressor by name %s", this.f972c)), new C0219ea());
    }
}
