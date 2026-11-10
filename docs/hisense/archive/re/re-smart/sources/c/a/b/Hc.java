package c.a.b;

import c.a.AbstractC0218e;
import c.a.AbstractC0230n;
import c.a.C0219ea;
import c.a.C0220f;
import c.a.C0223ga;
import c.a.C0236u;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class Hc extends AbstractC0218e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final O f994a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0223ga<?, ?> f995b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final C0219ea f996c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0220f f997d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final a f999f;
    public final AbstractC0230n[] g;
    public M i;
    public boolean j;
    public Na k;
    public final Object h = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0236u f998e = C0236u.c();

    public interface a {
    }

    public Hc(O o, C0223ga<?, ?> c0223ga, C0219ea c0219ea, C0220f c0220f, a aVar, AbstractC0230n[] abstractC0230nArr) {
        this.f994a = o;
        this.f995b = c0223ga;
        this.f996c = c0219ea;
        this.f997d = c0220f;
        this.f999f = aVar;
        this.g = abstractC0230nArr;
    }

    @Override // c.a.AbstractC0218e.a
    public void a(C0219ea c0219ea) {
        Preconditions.checkState(!this.j, "apply() or fail() already called");
        Preconditions.checkNotNull(c0219ea, "headers");
        this.f996c.a(c0219ea);
        C0236u c0236uA = this.f998e.a();
        try {
            M mA = this.f994a.a(this.f995b, this.f996c, this.f997d, this.g);
            this.f998e.a(c0236uA);
            a(mA);
        } catch (Throwable th) {
            this.f998e.a(c0236uA);
            throw th;
        }
    }

    @Override // c.a.AbstractC0218e.a
    public void a(c.a.za zaVar) {
        Preconditions.checkArgument(!zaVar.c(), "Cannot fail with OK status");
        Preconditions.checkState(!this.j, "apply() or fail() already called");
        a(new Ta(zaVar, N.a.PROCESSED, this.g));
    }

    public final void a(M m) {
        boolean z;
        Preconditions.checkState(!this.j, "already finalized");
        this.j = true;
        synchronized (this.h) {
            if (this.i == null) {
                this.i = m;
                z = true;
            } else {
                z = false;
            }
        }
        if (z) {
            ((C0200x) this.f999f).a();
            return;
        }
        Preconditions.checkState(this.k != null, "delayedStream is null");
        Runnable runnableB = this.k.b(m);
        if (runnableB != null) {
            runnableB.run();
        }
        ((C0200x) this.f999f).a();
    }

    public M a() {
        synchronized (this.h) {
            if (this.i == null) {
                this.k = new Na();
                Na na = this.k;
                this.i = na;
                return na;
            }
            return this.i;
        }
    }
}
