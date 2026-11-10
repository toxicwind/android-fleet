package c.a.b;

import c.a.AbstractC0226j;
import c.a.C0220f;
import c.a.C0223ga;
import c.a.EnumC0234s;
import com.google.common.base.MoreObjects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class Kc extends c.a.X implements c.a.N<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Ib f1044a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c.a.O f1045b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f1046c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0182ta f1047d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Executor f1048e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ScheduledExecutorService f1049f;
    public final CountDownLatch g;
    public final A h;
    public final L.b i;

    static {
        Logger.getLogger(Kc.class.getName());
    }

    @Override // c.a.AbstractC0222g
    public <RequestT, ResponseT> AbstractC0226j<RequestT, ResponseT> a(C0223ga<RequestT, ResponseT> c0223ga, C0220f c0220f) {
        return new L(c0223ga, c0220f.f1814c == null ? this.f1048e : c0220f.f1814c, c0220f, this.i, this.f1049f, this.h, null);
    }

    @Override // c.a.AbstractC0222g
    public String b() {
        return this.f1046c;
    }

    @Override // c.a.X
    public c.a.X d() {
        this.f1047d.b(c.a.za.l.b("OobChannel.shutdown() called"));
        return this;
    }

    @Override // c.a.X
    public c.a.X e() {
        this.f1047d.a(c.a.za.l.b("OobChannel.shutdownNow() called"));
        return this;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.f1045b.f839d).add("authority", this.f1046c).toString();
    }

    @Override // c.a.X
    public boolean a(long j, TimeUnit timeUnit) {
        return this.g.await(j, timeUnit);
    }

    @Override // c.a.X
    public EnumC0234s a(boolean z) {
        Ib ib = this.f1044a;
        if (ib == null) {
            return EnumC0234s.IDLE;
        }
        return ib.w.f1906a;
    }

    @Override // c.a.N
    public c.a.O a() {
        return this.f1045b;
    }
}
