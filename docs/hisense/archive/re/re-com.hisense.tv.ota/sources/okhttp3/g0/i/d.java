package okhttp3.g0.i;

import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okhttp3.M;

/* JADX INFO: loaded from: classes.dex */
class d extends b {
    private final M e;
    private long f;
    private boolean g;
    final /* synthetic */ h h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    d(h hVar, M m) {
        super(hVar);
        this.h = hVar;
        this.f = -1L;
        this.g = true;
        this.e = m;
    }

    private void e() throws ProtocolException {
        if (this.f != -1) {
            this.h.c.E();
        }
        try {
            this.f = this.h.c.A();
            String strTrim = this.h.c.E().trim();
            if (this.f < 0 || !(strTrim.isEmpty() || strTrim.startsWith(";"))) {
                throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f + strTrim + "\"");
            }
            if (this.f == 0) {
                this.g = false;
                okhttp3.g0.h.g.g(this.h.f205a.i(), this.e, this.h.n());
                a(true, null);
            }
        } catch (NumberFormatException e) {
            throw new ProtocolException(e.getMessage());
        }
    }

    @Override // okhttp3.g0.i.b, okio.B
    public long K(okio.h hVar, long j) throws ProtocolException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.b) {
            throw new IllegalStateException("closed");
        }
        if (!this.g) {
            return -1L;
        }
        long j2 = this.f;
        if (j2 == 0 || j2 == -1) {
            e();
            if (!this.g) {
                return -1L;
            }
        }
        long jK = super.K(hVar, Math.min(j, this.f));
        if (jK != -1) {
            this.f -= jK;
            return jK;
        }
        ProtocolException protocolException = new ProtocolException("unexpected end of stream");
        a(false, protocolException);
        throw protocolException;
    }

    @Override // okio.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.b) {
            return;
        }
        if (this.g && !okhttp3.g0.e.p(this, 100, TimeUnit.MILLISECONDS)) {
            a(false, null);
        }
        this.b = true;
    }
}
