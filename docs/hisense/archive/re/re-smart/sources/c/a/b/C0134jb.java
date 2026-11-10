package c.a.b;

import c.a.AbstractC0230n;
import c.a.C0219ea;
import c.a.C0220f;
import c.a.C0223ga;
import com.google.common.base.Preconditions;

/* JADX INFO: renamed from: c.a.b.jb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0134jb implements O {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AbstractC0230n.a f1319a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ O f1320b;

    public C0134jb(AbstractC0230n.a aVar, O o) {
        this.f1319a = aVar;
        this.f1320b = o;
    }

    @Override // c.a.b.O
    public M a(C0223ga<?, ?> c0223ga, C0219ea c0219ea, C0220f c0220f, AbstractC0230n[] abstractC0230nArr) {
        AbstractC0230n.c.a aVarA = AbstractC0230n.c.a();
        aVarA.a(c0220f);
        AbstractC0230n abstractC0230nA = C0149mb.a(this.f1319a, aVarA.a(), c0219ea);
        Preconditions.checkState(abstractC0230nArr[abstractC0230nArr.length - 1] == C0149mb.m, "lb tracer already assigned");
        abstractC0230nArr[abstractC0230nArr.length - 1] = abstractC0230nA;
        return this.f1320b.a(c0223ga, c0219ea, c0220f, abstractC0230nArr);
    }

    @Override // c.a.N
    public c.a.O a() {
        return this.f1320b.a();
    }
}
