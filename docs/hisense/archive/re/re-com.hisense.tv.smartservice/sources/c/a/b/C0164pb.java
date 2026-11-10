package c.a.b;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/* JADX INFO: renamed from: c.a.b.pb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0164pb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1404a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f1405b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Set<c.a.za.a> f1406c;

    public C0164pb(int i, long j, Set<c.a.za.a> set) {
        this.f1404a = i;
        this.f1405b = j;
        this.f1406c = ImmutableSet.copyOf((Collection) set);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C0164pb.class != obj.getClass()) {
            return false;
        }
        C0164pb c0164pb = (C0164pb) obj;
        return this.f1404a == c0164pb.f1404a && this.f1405b == c0164pb.f1405b && Objects.equal(this.f1406c, c0164pb.f1406c);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f1404a), Long.valueOf(this.f1405b), this.f1406c});
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("maxAttempts", this.f1404a).add("hedgingDelayNanos", this.f1405b).add("nonFatalStatusCodes", this.f1406c).toString();
    }
}
