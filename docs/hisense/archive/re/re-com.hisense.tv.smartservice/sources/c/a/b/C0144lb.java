package c.a.b;

import c.a.AbstractC0230n;
import c.a.C0086b;
import c.a.C0219ea;
import com.google.common.base.Preconditions;

/* JADX INFO: renamed from: c.a.b.lb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0144lb extends Xa {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC0230n f1340a = new C0139kb(this);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile AbstractC0230n f1341b = this.f1340a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ AbstractC0230n.a f1342c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ AbstractC0230n.c f1343d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ C0219ea f1344e;

    public C0144lb(AbstractC0230n.a aVar, AbstractC0230n.c cVar, C0219ea c0219ea) {
        this.f1342c = aVar;
        this.f1343d = cVar;
        this.f1344e = c0219ea;
    }

    public void a(AbstractC0230n.c cVar, C0219ea c0219ea) {
        if (this.f1341b != this.f1340a) {
            return;
        }
        synchronized (this) {
            if (this.f1341b == this.f1340a) {
                this.f1341b = ((C0156nd) this.f1342c).f1382a;
            }
        }
    }

    @Override // c.a.AbstractC0230n
    public void a(C0086b c0086b, C0219ea c0219ea) {
        AbstractC0230n.c.a aVarB = this.f1343d.b();
        Preconditions.checkNotNull(c0086b, "transportAttrs cannot be null");
        aVarB.f1879a = c0086b;
        a(aVarB.a(), c0219ea);
        this.f1341b.a(c0086b, c0219ea);
    }

    @Override // c.a.Ca
    public void a(c.a.za zaVar) {
        a(this.f1343d, this.f1344e);
        this.f1341b.a(zaVar);
    }
}
