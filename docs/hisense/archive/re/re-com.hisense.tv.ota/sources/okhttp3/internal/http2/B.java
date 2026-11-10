package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
final class B implements Closeable {
    static final Logger e = Logger.getLogger(C0043f.class.getName());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final okio.j f226a;
    private final z b;
    private final boolean c;
    final C0040c d;

    B(okio.j jVar, boolean z) {
        this.f226a = jVar;
        this.c = z;
        z zVar = new z(jVar);
        this.b = zVar;
        this.d = new C0040c(4096, zVar);
    }

    private void B(A a2, int i, byte b, int i2) throws IOException {
        if (i2 == 0) {
            C0043f.d("PROTOCOL_ERROR: TYPE_DATA streamId == 0", new Object[0]);
            throw null;
        }
        boolean z = (b & 1) != 0;
        if ((b & 32) != 0) {
            C0043f.d("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
            throw null;
        }
        short sH = (b & 8) != 0 ? (short) (this.f226a.H() & 255) : (short) 0;
        a2.f(z, i2, this.f226a, a(i, b, sH));
        this.f226a.m(sH);
    }

    private void M(A a2, int i, byte b, int i2) throws IOException {
        if (i < 8) {
            C0043f.d("TYPE_GOAWAY length < 8: %s", Integer.valueOf(i));
            throw null;
        }
        if (i2 != 0) {
            C0043f.d("TYPE_GOAWAY streamId != 0", new Object[0]);
            throw null;
        }
        int iL = this.f226a.L();
        int iL2 = this.f226a.L();
        int i3 = i - 8;
        ErrorCode errorCodeA = ErrorCode.a(iL2);
        if (errorCodeA == null) {
            C0043f.d("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(iL2));
            throw null;
        }
        ByteString byteStringD = ByteString.d;
        if (i3 > 0) {
            byteStringD = this.f226a.d(i3);
        }
        a2.a(iL, errorCodeA, byteStringD);
    }

    private List N(int i, short s, byte b, int i2) {
        z zVar = this.b;
        zVar.e = i;
        zVar.b = i;
        zVar.f = s;
        zVar.c = b;
        zVar.d = i2;
        this.d.k();
        return this.d.e();
    }

    private void O(A a2, int i, byte b, int i2) throws IOException {
        if (i2 == 0) {
            C0043f.d("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
            throw null;
        }
        boolean z = (b & 1) != 0;
        short sH = (b & 8) != 0 ? (short) (this.f226a.H() & 255) : (short) 0;
        if ((b & 32) != 0) {
            R(a2, i2);
            i -= 5;
        }
        a2.d(z, i2, -1, N(a(i, b, sH), sH, b, i2));
    }

    static int P(okio.j jVar) {
        return (jVar.H() & 255) | ((jVar.H() & 255) << 16) | ((jVar.H() & 255) << 8);
    }

    private void Q(A a2, int i, byte b, int i2) throws IOException {
        if (i != 8) {
            C0043f.d("TYPE_PING length != 8: %s", Integer.valueOf(i));
            throw null;
        }
        if (i2 != 0) {
            C0043f.d("TYPE_PING streamId != 0", new Object[0]);
            throw null;
        }
        a2.h((b & 1) != 0, this.f226a.L(), this.f226a.L());
    }

    private void R(A a2, int i) {
        int iL = this.f226a.L();
        a2.c(i, iL & Integer.MAX_VALUE, (this.f226a.H() & 255) + 1, (Integer.MIN_VALUE & iL) != 0);
    }

    private void S(A a2, int i, byte b, int i2) throws IOException {
        if (i != 5) {
            C0043f.d("TYPE_PRIORITY length: %d != 5", Integer.valueOf(i));
            throw null;
        }
        if (i2 != 0) {
            R(a2, i2);
        } else {
            C0043f.d("TYPE_PRIORITY streamId == 0", new Object[0]);
            throw null;
        }
    }

    private void T(A a2, int i, byte b, int i2) throws IOException {
        if (i2 == 0) {
            C0043f.d("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
            throw null;
        }
        short sH = (b & 8) != 0 ? (short) (this.f226a.H() & 255) : (short) 0;
        a2.b(i2, this.f226a.L() & Integer.MAX_VALUE, N(a(i - 4, b, sH), sH, b, i2));
    }

    private void U(A a2, int i, byte b, int i2) throws IOException {
        if (i != 4) {
            C0043f.d("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(i));
            throw null;
        }
        if (i2 == 0) {
            C0043f.d("TYPE_RST_STREAM streamId == 0", new Object[0]);
            throw null;
        }
        int iL = this.f226a.L();
        ErrorCode errorCodeA = ErrorCode.a(iL);
        if (errorCodeA != null) {
            a2.g(i2, errorCodeA);
        } else {
            C0043f.d("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(iL));
            throw null;
        }
    }

    private void V(A a2, int i, byte b, int i2) throws IOException {
        if (i2 != 0) {
            C0043f.d("TYPE_SETTINGS streamId != 0", new Object[0]);
            throw null;
        }
        if ((b & 1) != 0) {
            if (i == 0) {
                a2.e();
                return;
            } else {
                C0043f.d("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                throw null;
            }
        }
        if (i % 6 != 0) {
            C0043f.d("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(i));
            throw null;
        }
        L l = new L();
        for (int i3 = 0; i3 < i; i3 += 6) {
            int iT = this.f226a.t() & 65535;
            int iL = this.f226a.L();
            if (iT == 2) {
                if (iL != 0 && iL != 1) {
                    C0043f.d("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                    throw null;
                }
            } else if (iT == 3) {
                iT = 4;
            } else if (iT == 4) {
                iT = 7;
                if (iL < 0) {
                    C0043f.d("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                    throw null;
                }
            } else if (iT == 5 && (iL < 16384 || iL > 16777215)) {
                C0043f.d("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(iL));
                throw null;
            }
            l.i(iT, iL);
        }
        a2.j(false, l);
    }

    private void W(A a2, int i, byte b, int i2) throws IOException {
        if (i != 4) {
            C0043f.d("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(i));
            throw null;
        }
        long jL = ((long) this.f226a.L()) & 2147483647L;
        if (jL != 0) {
            a2.i(i2, jL);
        } else {
            C0043f.d("windowSizeIncrement was 0", Long.valueOf(jL));
            throw null;
        }
    }

    static int a(int i, byte b, short s) throws IOException {
        if ((b & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        C0043f.d("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
        throw null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f226a.close();
    }

    public boolean e(boolean z, A a2) throws IOException {
        try {
            this.f226a.J(9L);
            int iP = P(this.f226a);
            if (iP < 0 || iP > 16384) {
                C0043f.d("FRAME_SIZE_ERROR: %s", Integer.valueOf(iP));
                throw null;
            }
            byte bH = (byte) (this.f226a.H() & 255);
            if (z && bH != 4) {
                C0043f.d("Expected a SETTINGS frame but was %s", Byte.valueOf(bH));
                throw null;
            }
            byte bH2 = (byte) (this.f226a.H() & 255);
            int iL = this.f226a.L() & Integer.MAX_VALUE;
            if (e.isLoggable(Level.FINE)) {
                e.fine(C0043f.b(true, iL, iP, bH, bH2));
            }
            switch (bH) {
                case 0:
                    B(a2, iP, bH2, iL);
                    return true;
                case 1:
                    O(a2, iP, bH2, iL);
                    return true;
                case 2:
                    S(a2, iP, bH2, iL);
                    return true;
                case 3:
                    U(a2, iP, bH2, iL);
                    return true;
                case 4:
                    V(a2, iP, bH2, iL);
                    return true;
                case 5:
                    T(a2, iP, bH2, iL);
                    return true;
                case 6:
                    Q(a2, iP, bH2, iL);
                    return true;
                case 7:
                    M(a2, iP, bH2, iL);
                    return true;
                case 8:
                    W(a2, iP, bH2, iL);
                    return true;
                default:
                    this.f226a.m(iP);
                    return true;
            }
        } catch (IOException unused) {
            return false;
        }
    }

    public void p(A a2) throws IOException {
        if (this.c) {
            if (e(true, a2)) {
                return;
            }
            C0043f.d("Required SETTINGS preface not received", new Object[0]);
            throw null;
        }
        ByteString byteStringD = this.f226a.d(C0043f.f240a.r());
        if (e.isLoggable(Level.FINE)) {
            e.fine(okhttp3.g0.e.r("<< CONNECTION %s", byteStringD.j()));
        }
        if (C0043f.f240a.equals(byteStringD)) {
            return;
        }
        C0043f.d("Expected a connection header but was %s", byteStringD.w());
        throw null;
    }
}
