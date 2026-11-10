package c.a.b;

import c.a.AbstractC0230n;
import c.a.C0219ea;
import c.a.C0220f;
import c.a.C0223ga;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class Ua implements O {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @VisibleForTesting
    public final c.a.za f1164a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final N.a f1165b;

    public Ua(c.a.za zaVar, N.a aVar) {
        Preconditions.checkArgument(!zaVar.c(), "error must not be OK");
        this.f1164a = zaVar;
        this.f1165b = aVar;
    }

    @Override // c.a.b.O
    public M a(C0223ga<?, ?> c0223ga, C0219ea c0219ea, C0220f c0220f, AbstractC0230n[] abstractC0230nArr) {
        return new Ta(this.f1164a, this.f1165b, abstractC0230nArr);
    }

    @Override // c.a.N
    public c.a.O a() {
        throw new UnsupportedOperationException("Not a real transport");
    }
}
