package c.a.c;

import c.a.b.Ed;
import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics;
import d.B;
import d.E;
import java.io.IOException;
import java.net.Socket;
import java.util.Queue;

/* JADX INFO: loaded from: classes2.dex */
public final class d implements B {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Ed f1680c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final e.a f1681d;
    public B h;
    public Socket i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1678a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d.g f1679b = new d.g();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1682e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1683f = false;
    public boolean g = false;

    /* JADX INFO: Access modifiers changed from: private */
    abstract class a implements Runnable {
        public /* synthetic */ a(C0214a c0214a) {
        }

        public abstract void a();

        @Override // java.lang.Runnable
        public final void run() {
            try {
                if (d.this.h == null) {
                    throw new IOException("Unable to perform write due to unavailable sink.");
                }
                a();
            } catch (Exception e2) {
                ((r) d.this.f1681d).a(e2);
            }
        }
    }

    public d(Ed ed, e.a aVar) {
        Preconditions.checkNotNull(ed, "executor");
        this.f1680c = ed;
        Preconditions.checkNotNull(aVar, "exceptionHandler");
        this.f1681d = aVar;
    }

    @Override // d.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.g) {
            return;
        }
        this.g = true;
        Ed ed = this.f1680c;
        c cVar = new c(this);
        Queue<Runnable> queue = ed.f958d;
        Preconditions.checkNotNull(cVar, "'r' must not be null.");
        queue.add(cVar);
        ed.a(cVar);
    }

    @Override // d.B, java.io.Flushable
    public void flush() throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        c.b.c.f1949a.a("AsyncSink.flush");
        try {
            synchronized (this.f1678a) {
                if (this.f1683f) {
                    c.b.c.f1949a.b("AsyncSink.flush");
                    return;
                }
                this.f1683f = true;
                Ed ed = this.f1680c;
                b bVar = new b(this);
                Queue<Runnable> queue = ed.f958d;
                Preconditions.checkNotNull(bVar, "'r' must not be null.");
                queue.add(bVar);
                ed.a(bVar);
                c.b.c.f1949a.b("AsyncSink.flush");
            }
        } catch (Throwable th) {
            c.b.c.f1949a.b("AsyncSink.flush");
            throw th;
        }
    }

    @Override // d.B
    public E timeout() {
        return E.NONE;
    }

    @Override // d.B
    public void write(d.g gVar, long j) throws IOException {
        Preconditions.checkNotNull(gVar, FirebaseAnalytics.Param.SOURCE);
        if (this.g) {
            throw new IOException("closed");
        }
        c.b.c.f1949a.a("AsyncSink.write");
        try {
            synchronized (this.f1678a) {
                this.f1679b.write(gVar, j);
                if (!this.f1682e && !this.f1683f && this.f1679b.m() > 0) {
                    this.f1682e = true;
                    Ed ed = this.f1680c;
                    C0214a c0214a = new C0214a(this);
                    Queue<Runnable> queue = ed.f958d;
                    Preconditions.checkNotNull(c0214a, "'r' must not be null.");
                    queue.add(c0214a);
                    ed.a(c0214a);
                    c.b.c.f1949a.b("AsyncSink.write");
                    return;
                }
                c.b.c.f1949a.b("AsyncSink.write");
            }
        } catch (Throwable th) {
            c.b.c.f1949a.b("AsyncSink.write");
            throw th;
        }
    }

    public void a(B b2, Socket socket) {
        Preconditions.checkState(this.h == null, "AsyncSink's becomeConnected should only be called once.");
        Preconditions.checkNotNull(b2, "sink");
        this.h = b2;
        Preconditions.checkNotNull(socket, "socket");
        this.i = socket;
    }
}
