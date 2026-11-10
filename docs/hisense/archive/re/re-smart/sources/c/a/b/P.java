package c.a.b;

import c.a.AbstractC0225i;
import c.a.C0086b;
import com.google.common.base.Objects;
import java.io.Closeable;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes2.dex */
public interface P extends Closeable {

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f1098a = "unknown-authority";

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public C0086b f1099b = C0086b.f876a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f1100c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public c.a.H f1101d;

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f1098a.equals(aVar.f1098a) && this.f1099b.equals(aVar.f1099b) && Objects.equal(this.f1100c, aVar.f1100c) && Objects.equal(this.f1101d, aVar.f1101d);
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.f1098a, this.f1099b, this.f1100c, this.f1101d});
        }
    }

    X a(SocketAddress socketAddress, a aVar, AbstractC0225i abstractC0225i);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    ScheduledExecutorService n();
}
