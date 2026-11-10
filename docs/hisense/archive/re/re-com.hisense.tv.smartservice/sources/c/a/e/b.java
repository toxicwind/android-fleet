package c.a.e;

import c.a.AbstractC0222g;
import c.a.C0220f;
import c.a.e.b;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b<S extends b<S>> extends d<S> {
    public static final /* synthetic */ boolean $assertionsDisabled = false;

    public b(AbstractC0222g abstractC0222g, C0220f c0220f) {
        super(abstractC0222g, c0220f);
    }

    public static <T extends d<T>> T newStub(d.a<T> aVar, AbstractC0222g abstractC0222g) {
        return (T) newStub(aVar, abstractC0222g, C0220f.f1812a);
    }

    public static <T extends d<T>> T newStub(d.a<T> aVar, AbstractC0222g abstractC0222g, C0220f c0220f) {
        return (T) aVar.newStub(abstractC0222g, c0220f.a(g.f1772b, g.f.BLOCKING));
    }
}
