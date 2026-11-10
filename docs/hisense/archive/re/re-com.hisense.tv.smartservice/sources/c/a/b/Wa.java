package c.a.b;

import c.a.C0219ea;
import com.google.common.base.MoreObjects;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Wa implements N {
    @Override // c.a.b.N
    public void a(C0219ea c0219ea) {
        ((Gb) this).f975a.a(c0219ea);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", ((Gb) this).f975a).toString();
    }

    @Override // c.a.b.Nd
    public void a(Nd.a aVar) {
        ((Gb) this).f975a.a(aVar);
    }

    @Override // c.a.b.Nd
    public void a() {
        ((Gb) this).f975a.a();
    }
}
