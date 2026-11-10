package c.a;

import com.google.common.base.Preconditions;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0086b.C0007b<String> f782a = new C0086b.C0007b<>("io.grpc.EquivalentAddressGroup.authorityOverride");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List<SocketAddress> f783b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final C0086b f784c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f785d;

    public B(List<SocketAddress> list, C0086b c0086b) {
        Preconditions.checkArgument(!list.isEmpty(), "addrs is empty");
        this.f783b = Collections.unmodifiableList(new ArrayList(list));
        Preconditions.checkNotNull(c0086b, "attrs");
        this.f784c = c0086b;
        this.f785d = this.f783b.hashCode();
    }

    public C0086b a() {
        return this.f784c;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof B)) {
            return false;
        }
        B b2 = (B) obj;
        if (this.f783b.size() != b2.f783b.size()) {
            return false;
        }
        for (int i = 0; i < this.f783b.size(); i++) {
            if (!this.f783b.get(i).equals(b2.f783b.get(i))) {
                return false;
            }
        }
        return this.f784c.equals(b2.f784c);
    }

    public int hashCode() {
        return this.f785d;
    }

    public String toString() {
        StringBuilder sbA = b.a.a.a.a.a("[");
        sbA.append(this.f783b);
        sbA.append("/");
        return b.a.a.a.a.a(sbA, this.f784c, "]");
    }

    public B(SocketAddress socketAddress) {
        this(Collections.singletonList(socketAddress), C0086b.f876a);
    }
}
