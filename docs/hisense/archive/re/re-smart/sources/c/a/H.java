package c.a;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class H extends na {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SocketAddress f809a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InetSocketAddress f810b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f811c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f812d;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public SocketAddress f813a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public InetSocketAddress f814b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f815c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f816d;

        public /* synthetic */ a(G g) {
        }

        public a a(SocketAddress socketAddress) {
            Preconditions.checkNotNull(socketAddress, "proxyAddress");
            this.f813a = socketAddress;
            return this;
        }

        public a a(InetSocketAddress inetSocketAddress) {
            Preconditions.checkNotNull(inetSocketAddress, "targetAddress");
            this.f814b = inetSocketAddress;
            return this;
        }

        public H a() {
            return new H(this.f813a, this.f814b, this.f815c, this.f816d, null);
        }
    }

    public /* synthetic */ H(SocketAddress socketAddress, InetSocketAddress inetSocketAddress, String str, String str2, G g) {
        Preconditions.checkNotNull(socketAddress, "proxyAddress");
        Preconditions.checkNotNull(inetSocketAddress, "targetAddress");
        if (socketAddress instanceof InetSocketAddress) {
            Preconditions.checkState(!((InetSocketAddress) socketAddress).isUnresolved(), "The proxy address %s is not resolved", socketAddress);
        }
        this.f809a = socketAddress;
        this.f810b = inetSocketAddress;
        this.f811c = str;
        this.f812d = str2;
    }

    public static a b() {
        return new a(null);
    }

    public InetSocketAddress a() {
        return this.f810b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof H)) {
            return false;
        }
        H h = (H) obj;
        return Objects.equal(this.f809a, h.f809a) && Objects.equal(this.f810b, h.f810b) && Objects.equal(this.f811c, h.f811c) && Objects.equal(this.f812d, h.f812d);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f809a, this.f810b, this.f811c, this.f812d});
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("proxyAddr", this.f809a).add("targetAddr", this.f810b).add("username", this.f811c).add("hasPassword", this.f812d != null).toString();
    }
}
