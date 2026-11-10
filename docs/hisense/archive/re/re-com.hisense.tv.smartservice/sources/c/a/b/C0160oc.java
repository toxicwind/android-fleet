package c.a.b;

import c.a.AbstractC0222g;
import c.a.AbstractC0226j;
import c.a.C0220f;
import c.a.C0223ga;

/* JADX INFO: renamed from: c.a.b.oc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0160oc extends AbstractC0222g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0130ic.j f1399a;

    public C0160oc(C0130ic.j jVar) {
        this.f1399a = jVar;
    }

    @Override // c.a.AbstractC0222g
    public <RequestT, ResponseT> AbstractC0226j<RequestT, ResponseT> a(C0223ga<RequestT, ResponseT> c0223ga, C0220f c0220f) {
        L l = new L(c0223ga, C0130ic.this.a(c0220f), c0220f, C0130ic.this.pa, C0130ic.this.U ? null : C0130ic.this.o.n(), C0130ic.this.X, null);
        l.s = C0130ic.this.x;
        l.t = C0130ic.this.y;
        l.u = C0130ic.this.z;
        return l;
    }

    @Override // c.a.AbstractC0222g
    public String b() {
        return this.f1399a.f1298b;
    }
}
