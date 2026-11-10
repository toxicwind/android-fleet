package c.a;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class J {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f817a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f818b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f819c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final N f820d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final N f821e;

    public enum a {
        CT_UNKNOWN,
        CT_INFO,
        CT_WARNING,
        CT_ERROR
    }

    public /* synthetic */ J(String str, a aVar, long j, N n, N n2, I i) {
        this.f817a = str;
        Preconditions.checkNotNull(aVar, "severity");
        this.f818b = aVar;
        this.f819c = j;
        this.f820d = n;
        this.f821e = n2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof J)) {
            return false;
        }
        J j = (J) obj;
        return Objects.equal(this.f817a, j.f817a) && Objects.equal(this.f818b, j.f818b) && this.f819c == j.f819c && Objects.equal(this.f820d, j.f820d) && Objects.equal(this.f821e, j.f821e);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f817a, this.f818b, Long.valueOf(this.f819c), this.f820d, this.f821e});
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("description", this.f817a).add("severity", this.f818b).add("timestampNanos", this.f819c).add("channelRef", this.f820d).add("subchannelRef", this.f821e).toString();
    }
}
