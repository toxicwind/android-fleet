package c.a.e;

import c.a.AbstractC0222g;
import c.a.C0220f;
import c.a.e.c;

/* JADX INFO: loaded from: classes2.dex */
public abstract class c<S extends c<S>> extends d<S> {
    public static final /* synthetic */ boolean $assertionsDisabled = false;

    public c(AbstractC0222g abstractC0222g, C0220f c0220f) {
        super(abstractC0222g, c0220f);
    }

    public static <T extends d<T>> T newStub(d.a<T> aVar, AbstractC0222g abstractC0222g) {
        return (T) newStub(aVar, abstractC0222g, C0220f.f1812a);
    }

    public static <T extends d<T>> T newStub(d.a<T> aVar, AbstractC0222g abstractC0222g, C0220f c0220f) {
        return (T) aVar.newStub(abstractC0222g, c0220f.a(g.f1772b, g.f.FUTURE));
    }
}
