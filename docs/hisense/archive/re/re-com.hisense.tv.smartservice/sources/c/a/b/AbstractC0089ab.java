package c.a.b;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

/* JADX INFO: renamed from: c.a.b.ab, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0089ab extends c.a.ia {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c.a.ia f1219a;

    public AbstractC0089ab(c.a.ia iaVar) {
        Preconditions.checkNotNull(iaVar, "delegate can not be null");
        this.f1219a = iaVar;
    }

    @Override // c.a.ia
    public void a(c.a.ia.d dVar) {
        this.f1219a.a(dVar);
    }

    @Override // c.a.ia
    public void b() {
        this.f1219a.b();
    }

    @Override // c.a.ia
    public void c() {
        this.f1219a.c();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", this.f1219a).toString();
    }
}
