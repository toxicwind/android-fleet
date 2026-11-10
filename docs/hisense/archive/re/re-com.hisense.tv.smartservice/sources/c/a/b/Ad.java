package c.a.b;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class Ad {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f889a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f890b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f891c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final double f892d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Long f893e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Set<c.a.za.a> f894f;

    public Ad(int i, long j, long j2, double d2, Long l, Set<c.a.za.a> set) {
        this.f889a = i;
        this.f890b = j;
        this.f891c = j2;
        this.f892d = d2;
        this.f893e = l;
        this.f894f = ImmutableSet.copyOf((Collection) set);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Ad)) {
            return false;
        }
        Ad ad = (Ad) obj;
        return this.f889a == ad.f889a && this.f890b == ad.f890b && this.f891c == ad.f891c && Double.compare(this.f892d, ad.f892d) == 0 && Objects.equal(this.f893e, ad.f893e) && Objects.equal(this.f894f, ad.f894f);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f889a), Long.valueOf(this.f890b), Long.valueOf(this.f891c), Double.valueOf(this.f892d), this.f893e, this.f894f});
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("maxAttempts", this.f889a).add("initialBackoffNanos", this.f890b).add("maxBackoffNanos", this.f891c).add("backoffMultiplier", this.f892d).add("perAttemptRecvTimeoutNanos", this.f893e).add("retryableStatusCodes", this.f894f).toString();
    }
}
