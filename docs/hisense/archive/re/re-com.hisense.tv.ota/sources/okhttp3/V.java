package okhttp3;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes.dex */
final class V extends okhttp3.g0.b {
    private final InterfaceC0054n b;
    final /* synthetic */ W c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    V(W w, InterfaceC0054n interfaceC0054n) {
        super("OkHttp %s", w.i());
        this.c = w;
        this.b = interfaceC0054n;
    }

    @Override // okhttp3.g0.b
    protected void k() {
        boolean z;
        Throwable th;
        IOException e;
        this.c.c.k();
        try {
            try {
                z = true;
                try {
                    this.b.a(this.c, this.c.g());
                } catch (IOException e2) {
                    e = e2;
                    IOException iOExceptionJ = this.c.j(e);
                    if (z) {
                        okhttp3.g0.k.k.l().t(4, "Callback failure for " + this.c.k(), iOExceptionJ);
                    } else {
                        this.c.d.b(this.c, iOExceptionJ);
                        this.b.b(this.c, iOExceptionJ);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    this.c.c();
                    if (!z) {
                        this.b.b(this.c, new IOException("canceled due to " + th));
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                this.c.f170a.j().e(this);
                throw th3;
            }
        } catch (IOException e3) {
            z = false;
            e = e3;
        } catch (Throwable th4) {
            z = false;
            th = th4;
        }
        this.c.f170a.j().e(this);
    }

    void l(ExecutorService executorService) {
        try {
            try {
                executorService.execute(this);
            } catch (RejectedExecutionException e) {
                InterruptedIOException interruptedIOException = new InterruptedIOException("executor rejected");
                interruptedIOException.initCause(e);
                this.c.d.b(this.c, interruptedIOException);
                this.b.b(this.c, interruptedIOException);
                this.c.f170a.j().e(this);
            }
        } catch (Throwable th) {
            this.c.f170a.j().e(this);
            throw th;
        }
    }

    W m() {
        return this.c;
    }

    String n() {
        return this.c.e.k().l();
    }
}
