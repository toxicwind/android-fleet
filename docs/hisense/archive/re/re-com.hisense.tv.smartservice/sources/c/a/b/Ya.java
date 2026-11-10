package c.a.b;

import com.google.common.base.MoreObjects;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Ya implements X {
    @Override // c.a.b.Cc
    public Runnable a(Cc.a aVar) {
        return b().a(aVar);
    }

    public abstract X b();

    @Override // c.a.b.Cc
    public void b(c.a.za zaVar) {
        b().b(zaVar);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", b()).toString();
    }

    @Override // c.a.b.Cc
    public void a(c.a.za zaVar) {
        b().a(zaVar);
    }

    @Override // c.a.b.O
    public void a(O.a aVar, Executor executor) {
        b().a(aVar, executor);
    }

    @Override // c.a.N
    public c.a.O a() {
        return b().a();
    }
}
