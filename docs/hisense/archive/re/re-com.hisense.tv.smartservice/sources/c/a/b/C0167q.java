package c.a.b;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Queue;

/* JADX INFO: renamed from: c.a.b.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0167q implements Ec.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f1410a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Ec.a f1411b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Queue<InputStream> f1412c = new ArrayDeque();

    /* JADX INFO: renamed from: c.a.b.q$a */
    public interface a {
    }

    public C0167q(Ec.a aVar, a aVar2) {
        Preconditions.checkNotNull(aVar, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.f1411b = aVar;
        Preconditions.checkNotNull(aVar2, "transportExecutor");
        this.f1410a = aVar2;
    }

    @Override // c.a.b.Ec.a
    public void a(int i) {
        ((c.a.c.k.b) this.f1410a).a(new RunnableC0152n(this, i));
    }

    @Override // c.a.b.Ec.a
    public void a(Nd.a aVar) {
        while (true) {
            InputStream next = aVar.next();
            if (next == null) {
                return;
            } else {
                this.f1412c.add(next);
            }
        }
    }

    @Override // c.a.b.Ec.a
    public void a(boolean z) {
        ((c.a.c.k.b) this.f1410a).a(new RunnableC0157o(this, z));
    }

    @Override // c.a.b.Ec.a
    public void a(Throwable th) {
        ((c.a.c.k.b) this.f1410a).a(new RunnableC0162p(this, th));
    }
}
