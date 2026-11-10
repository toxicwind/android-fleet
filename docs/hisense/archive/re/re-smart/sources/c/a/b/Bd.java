package c.a.b;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@VisibleForTesting
public final class Bd extends c.a.ia.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f914a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f915b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f916c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0191v f917d;

    public Bd(boolean z, int i, int i2, C0191v c0191v) {
        this.f914a = z;
        this.f915b = i;
        this.f916c = i2;
        Preconditions.checkNotNull(c0191v, "autoLoadBalancerFactory");
        this.f917d = c0191v;
    }

    @Override // c.a.ia.g
    public c.a.ia.b a(Map<String, ?> map) {
        Object obj;
        try {
            c.a.ia.b bVarA = this.f917d.a(map);
            if (bVarA == null) {
                obj = null;
            } else {
                c.a.za zaVar = bVarA.f1861a;
                if (zaVar != null) {
                    return new c.a.ia.b(zaVar);
                }
                obj = bVarA.f1862b;
            }
            return new c.a.ia.b(Bc.a(map, this.f914a, this.f915b, this.f916c, obj));
        } catch (RuntimeException e2) {
            return new c.a.ia.b(c.a.za.f1938e.b("failed to parse service config").b(e2));
        }
    }
}
