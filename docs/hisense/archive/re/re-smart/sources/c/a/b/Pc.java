package c.a.b;

import c.a.C0219ea;
import c.a.C0220f;
import c.a.C0223ga;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class Pc extends c.a.U.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0220f f1117a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0219ea f1118b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final C0223ga<?, ?> f1119c;

    public Pc(C0223ga<?, ?> c0223ga, C0219ea c0219ea, C0220f c0220f) {
        Preconditions.checkNotNull(c0223ga, FirebaseAnalytics.Param.METHOD);
        this.f1119c = c0223ga;
        Preconditions.checkNotNull(c0219ea, "headers");
        this.f1118b = c0219ea;
        Preconditions.checkNotNull(c0220f, "callOptions");
        this.f1117a = c0220f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Pc.class != obj.getClass()) {
            return false;
        }
        Pc pc = (Pc) obj;
        return Objects.equal(this.f1117a, pc.f1117a) && Objects.equal(this.f1118b, pc.f1118b) && Objects.equal(this.f1119c, pc.f1119c);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f1117a, this.f1118b, this.f1119c});
    }

    public final String toString() {
        StringBuilder sbA = b.a.a.a.a.a("[method=");
        sbA.append(this.f1119c);
        sbA.append(" headers=");
        sbA.append(this.f1118b);
        sbA.append(" callOptions=");
        return b.a.a.a.a.a(sbA, this.f1117a, "]");
    }
}
