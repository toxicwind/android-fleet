package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
final class G implements Closeable {
    private static final Logger g = Logger.getLogger(C0043f.class.getName());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final okio.i f231a;
    private final boolean b;
    private final okio.h c;
    private int d;
    private boolean e;
    final C0041d f;

    G(okio.i iVar, boolean z) {
        this.f231a = iVar;
        this.b = z;
        okio.h hVar = new okio.h();
        this.c = hVar;
        this.f = new C0041d(hVar);
        this.d = 16384;
    }

    private void W(int i, long j) {
        while (j > 0) {
            int iMin = (int) Math.min(this.d, j);
            long j2 = iMin;
            j -= j2;
            M(i, iMin, (byte) 9, j == 0 ? (byte) 4 : (byte) 0);
            this.f231a.o(this.c, j2);
        }
    }

    private static void X(okio.i iVar, int i) {
        iVar.q((i >>> 16) & 255);
        iVar.q((i >>> 8) & 255);
        iVar.q(i & 255);
    }

    void B(int i, byte b, okio.h hVar, int i2) {
        M(i, i2, (byte) 0, b);
        if (i2 > 0) {
            this.f231a.o(hVar, i2);
        }
    }

    public void M(int i, int i2, byte b, byte b2) {
        if (g.isLoggable(Level.FINE)) {
            g.fine(C0043f.b(false, i, i2, b, b2));
        }
        int i3 = this.d;
        if (i2 > i3) {
            C0043f.c("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(i3), Integer.valueOf(i2));
            throw null;
        }
        if ((Integer.MIN_VALUE & i) != 0) {
            C0043f.c("reserved bit set: %s", Integer.valueOf(i));
            throw null;
        }
        X(this.f231a, i2);
        this.f231a.q(b & 255);
        this.f231a.q(b2 & 255);
        this.f231a.h(i & Integer.MAX_VALUE);
    }

    public synchronized void N(int i, ErrorCode errorCode, byte[] bArr) {
        if (this.e) {
            throw new IOException("closed");
        }
        if (errorCode.httpCode == -1) {
            C0043f.c("errorCode.httpCode == -1", new Object[0]);
            throw null;
        }
        M(0, bArr.length + 8, (byte) 7, (byte) 0);
        this.f231a.h(i);
        this.f231a.h(errorCode.httpCode);
        if (bArr.length > 0) {
            this.f231a.g(bArr);
        }
        this.f231a.flush();
    }

    void O(boolean z, int i, List list) throws IOException {
        if (this.e) {
            throw new IOException("closed");
        }
        this.f.g(list);
        long jC0 = this.c.c0();
        int iMin = (int) Math.min(this.d, jC0);
        long j = iMin;
        byte b = jC0 == j ? (byte) 4 : (byte) 0;
        if (z) {
            b = (byte) (b | 1);
        }
        M(i, iMin, (byte) 1, b);
        this.f231a.o(this.c, j);
        if (jC0 > j) {
            W(i, jC0 - j);
        }
    }

    public int P() {
        return this.d;
    }

    public synchronized void Q(boolean z, int i, int i2) {
        if (this.e) {
            throw new IOException("closed");
        }
        M(0, 8, (byte) 6, z ? (byte) 1 : (byte) 0);
        this.f231a.h(i);
        this.f231a.h(i2);
        this.f231a.flush();
    }

    public synchronized void R(int i, int i2, List list) {
        if (this.e) {
            throw new IOException("closed");
        }
        this.f.g(list);
        long jC0 = this.c.c0();
        int iMin = (int) Math.min(this.d - 4, jC0);
        long j = iMin;
        M(i, iMin + 4, (byte) 5, jC0 == j ? (byte) 4 : (byte) 0);
        this.f231a.h(i2 & Integer.MAX_VALUE);
        this.f231a.o(this.c, j);
        if (jC0 > j) {
            W(i, jC0 - j);
        }
    }

    public synchronized void S(int i, ErrorCode errorCode) {
        if (this.e) {
            throw new IOException("closed");
        }
        if (errorCode.httpCode == -1) {
            throw new IllegalArgumentException();
        }
        M(i, 4, (byte) 3, (byte) 0);
        this.f231a.h(errorCode.httpCode);
        this.f231a.flush();
    }

    public synchronized void T(L l) {
        int i;
        if (this.e) {
            throw new IOException("closed");
        }
        int i2 = 0;
        M(0, l.j() * 6, (byte) 4, (byte) 0);
        while (i2 < 10) {
            if (l.g(i2)) {
                if (i2 == 4) {
                    i = 3;
                } else {
                    i = i2 == 7 ? 4 : i2;
                }
                this.f231a.x(i);
                this.f231a.h(l.b(i2));
            }
            i2++;
        }
        this.f231a.flush();
    }

    public synchronized void U(boolean z, int i, int i2, List list) {
        if (this.e) {
            throw new IOException("closed");
        }
        O(z, i, list);
    }

    public synchronized void V(int i, long j) {
        if (this.e) {
            throw new IOException("closed");
        }
        if (j == 0 || j > 2147483647L) {
            C0043f.c("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
            throw null;
        }
        M(i, 4, (byte) 8, (byte) 0);
        this.f231a.h((int) j);
        this.f231a.flush();
    }

    public synchronized void a(L l) {
        if (this.e) {
            throw new IOException("closed");
        }
        this.d = l.f(this.d);
        if (l.c() != -1) {
            this.f.e(l.c());
        }
        M(0, 0, (byte) 4, (byte) 1);
        this.f231a.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.e = true;
        this.f231a.close();
    }

    public synchronized void e() {
        if (this.e) {
            throw new IOException("closed");
        }
        if (this.b) {
            if (g.isLoggable(Level.FINE)) {
                g.fine(okhttp3.g0.e.r(">> CONNECTION %s", C0043f.f240a.j()));
            }
            this.f231a.g(C0043f.f240a.v());
            this.f231a.flush();
        }
    }

    public synchronized void flush() {
        if (this.e) {
            throw new IOException("closed");
        }
        this.f231a.flush();
    }

    public synchronized void p(boolean z, int i, okio.h hVar, int i2) {
        if (this.e) {
            throw new IOException("closed");
        }
        B(i, z ? (byte) 1 : (byte) 0, hVar, i2);
    }
}
