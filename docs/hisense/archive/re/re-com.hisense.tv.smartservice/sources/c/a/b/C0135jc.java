package c.a.b;

import c.a.AbstractC0226j;
import c.a.C0219ea;

/* JADX INFO: renamed from: c.a.b.jc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0135jc extends Z {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AbstractC0226j.a f1321b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ c.a.za f1322c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0135jc(C0130ic.b bVar, AbstractC0226j.a aVar, c.a.za zaVar) {
        super(bVar.f1282e);
        this.f1321b = aVar;
        this.f1322c = zaVar;
    }

    @Override // c.a.b.Z
    public void a() {
        this.f1321b.onClose(this.f1322c, new C0219ea());
    }
}
