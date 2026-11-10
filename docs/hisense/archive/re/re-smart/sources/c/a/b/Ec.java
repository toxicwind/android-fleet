package c.a.b;

import c.a.InterfaceC0231o;
import c.a.InterfaceC0239x;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.io.Closeable;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes2.dex */
public class Ec implements Closeable, InterfaceC0088aa {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f940a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f941b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Ld f942c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Td f943d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public InterfaceC0239x f944e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public C0159ob f945f;
    public byte[] g;
    public int h;
    public boolean k;
    public W l;
    public long n;
    public int q;
    public d i = d.HEADER;
    public int j = 5;
    public W m = new W();
    public boolean o = false;
    public int p = -1;
    public boolean r = false;
    public volatile boolean s = false;

    public interface a {
        void a(int i);

        void a(Nd.a aVar);

        void a(Throwable th);

        void a(boolean z);
    }

    private static class b implements Nd.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public InputStream f946a;

        public /* synthetic */ b(InputStream inputStream, Dc dc) {
            this.f946a = inputStream;
        }

        @Override // c.a.b.Nd.a
        public InputStream next() {
            InputStream inputStream = this.f946a;
            this.f946a = null;
            return inputStream;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    enum d {
        HEADER,
        BODY
    }

    public Ec(a aVar, InterfaceC0239x interfaceC0239x, int i, Ld ld, Td td) {
        Preconditions.checkNotNull(aVar, "sink");
        this.f940a = aVar;
        Preconditions.checkNotNull(interfaceC0239x, "decompressor");
        this.f944e = interfaceC0239x;
        this.f941b = i;
        Preconditions.checkNotNull(ld, "statsTraceCtx");
        this.f942c = ld;
        Preconditions.checkNotNull(td, "transportTracer");
        this.f943d = td;
    }

    public void a(a aVar) {
        this.f940a = aVar;
    }

    @Override // c.a.b.InterfaceC0088aa
    public void b(int i) {
        Preconditions.checkArgument(i > 0, "numMessages must be > 0");
        if (isClosed()) {
            return;
        }
        this.n += (long) i;
        q();
    }

    @Override // c.a.b.InterfaceC0088aa
    public void c(int i) {
        this.f941b = i;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, c.a.b.InterfaceC0088aa
    public void close() {
        if (isClosed()) {
            return;
        }
        W w = this.l;
        boolean z = w != null && w.h > 0;
        try {
            if (this.f945f != null) {
                if (!z) {
                    C0159ob c0159ob = this.f945f;
                    Preconditions.checkState(!c0159ob.i, "GzipInflatingBuffer is closed");
                    z = c0159ob.f1388c.c() != 0 || c0159ob.h != C0159ob.b.HEADER;
                }
                C0159ob c0159ob2 = this.f945f;
                if (!c0159ob2.i) {
                    c0159ob2.i = true;
                    c0159ob2.f1386a.close();
                    Inflater inflater = c0159ob2.g;
                    if (inflater != null) {
                        inflater.end();
                        c0159ob2.g = null;
                    }
                }
            }
            if (this.m != null) {
                this.m.close();
            }
            if (this.l != null) {
                this.l.close();
            }
            this.f945f = null;
            this.m = null;
            this.l = null;
            this.f940a.a(z);
        } catch (Throwable th) {
            this.f945f = null;
            this.m = null;
            this.l = null;
            throw th;
        }
    }

    public boolean isClosed() {
        return this.m == null && this.f945f == null;
    }

    @Override // c.a.b.InterfaceC0088aa
    public void p() {
        if (isClosed()) {
            return;
        }
        if (r()) {
            close();
        } else {
            this.r = true;
        }
    }

    public final void q() {
        if (this.o) {
            return;
        }
        this.o = true;
        while (!this.s && this.n > 0 && u()) {
            try {
                int iOrdinal = this.i.ordinal();
                if (iOrdinal == 0) {
                    t();
                } else {
                    if (iOrdinal != 1) {
                        throw new AssertionError("Invalid state: " + this.i);
                    }
                    s();
                    this.n--;
                }
            } catch (Throwable th) {
                this.o = false;
                throw th;
            }
        }
        if (this.s) {
            close();
            this.o = false;
        } else {
            if (this.r && r()) {
                close();
            }
            this.o = false;
        }
    }

    public final boolean r() {
        C0159ob c0159ob = this.f945f;
        if (c0159ob == null) {
            return this.m.h == 0;
        }
        Preconditions.checkState(true ^ c0159ob.i, "GzipInflatingBuffer is closed");
        return c0159ob.o;
    }

    public final void s() {
        InputStream inputStreamA;
        Ld ld = this.f942c;
        int i = this.p;
        long j = this.q;
        for (c.a.Ca ca : ld.f1069a) {
            ca.a(i, j, -1L);
        }
        this.q = 0;
        if (this.k) {
            InterfaceC0239x interfaceC0239x = this.f944e;
            if (interfaceC0239x == InterfaceC0231o.b.f1883a) {
                throw c.a.za.k.b("Can't decode compressed gRPC message as compression not configured").b();
            }
            try {
                inputStreamA = new c(interfaceC0239x.a(Vc.a((Tc) this.l, true)), this.f941b, this.f942c);
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        } else {
            this.f942c.a(this.l.h);
            inputStreamA = Vc.a((Tc) this.l, true);
        }
        this.l = null;
        this.f940a.a(new b(inputStreamA, null));
        this.i = d.HEADER;
        this.j = 5;
    }

    public final void t() {
        int unsignedByte = this.l.readUnsignedByte();
        if ((unsignedByte & 254) != 0) {
            throw c.a.za.k.b("gRPC frame header malformed: reserved bits not zero").b();
        }
        this.k = (unsignedByte & 1) != 0;
        W w = this.l;
        w.b(4);
        this.j = w.readUnsignedByte() | (w.readUnsignedByte() << 24) | (w.readUnsignedByte() << 16) | (w.readUnsignedByte() << 8);
        int i = this.j;
        if (i < 0 || i > this.f941b) {
            throw c.a.za.i.b(String.format("gRPC message exceeds maximum size %d: %d", Integer.valueOf(this.f941b), Integer.valueOf(this.j))).b();
        }
        this.p++;
        Ld ld = this.f942c;
        int i2 = this.p;
        for (c.a.Ca ca : ld.f1069a) {
            ca.a(i2);
        }
        Td td = this.f943d;
        td.h.add(1L);
        ((Od) td.f1158b).a();
        this.i = d.BODY;
    }

    public final boolean u() throws Throwable {
        int i;
        Throwable th;
        int i2;
        try {
            if (this.l == null) {
                this.l = new W();
            }
            i2 = 0;
            i = 0;
            while (true) {
                try {
                    int i3 = this.j - this.l.h;
                    if (i3 <= 0) {
                        if (i2 > 0) {
                            this.f940a.a(i2);
                            if (this.i == d.BODY) {
                                if (this.f945f != null) {
                                    this.f942c.b(i);
                                    this.q += i;
                                } else {
                                    this.f942c.b(i2);
                                    this.q += i2;
                                }
                            }
                        }
                        return true;
                    }
                    if (this.f945f != null) {
                        try {
                            try {
                                if (this.g == null || this.h == this.g.length) {
                                    this.g = new byte[Math.min(i3, 2097152)];
                                    this.h = 0;
                                }
                                int iB = this.f945f.b(this.g, this.h, Math.min(i3, this.g.length - this.h));
                                C0159ob c0159ob = this.f945f;
                                int i4 = c0159ob.m;
                                c0159ob.m = 0;
                                i2 += i4;
                                C0159ob c0159ob2 = this.f945f;
                                int i5 = c0159ob2.n;
                                c0159ob2.n = 0;
                                i += i5;
                                if (iB == 0) {
                                    if (i2 > 0) {
                                        this.f940a.a(i2);
                                        if (this.i == d.BODY) {
                                            if (this.f945f != null) {
                                                this.f942c.b(i);
                                                this.q += i;
                                            } else {
                                                this.f942c.b(i2);
                                                this.q += i2;
                                            }
                                        }
                                    }
                                    return false;
                                }
                                this.l.a(Vc.a(this.g, this.h, iB));
                                this.h += iB;
                            } catch (IOException e2) {
                                throw new RuntimeException(e2);
                            }
                        } catch (DataFormatException e3) {
                            throw new RuntimeException(e3);
                        }
                    } else {
                        if (this.m.h == 0) {
                            if (i2 > 0) {
                                this.f940a.a(i2);
                                if (this.i == d.BODY) {
                                    if (this.f945f != null) {
                                        this.f942c.b(i);
                                        this.q += i;
                                    } else {
                                        this.f942c.b(i2);
                                        this.q += i2;
                                    }
                                }
                            }
                            return false;
                        }
                        int iMin = Math.min(i3, this.m.h);
                        i2 += iMin;
                        this.l.a(this.m.a(iMin));
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (i2 > 0) {
                        this.f940a.a(i2);
                        if (this.i == d.BODY) {
                            if (this.f945f != null) {
                                this.f942c.b(i);
                                this.q += i;
                            } else {
                                this.f942c.b(i2);
                                this.q += i2;
                            }
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th3) {
            i = 0;
            th = th3;
            i2 = 0;
        }
    }

    @Override // c.a.b.InterfaceC0088aa
    public void a(InterfaceC0239x interfaceC0239x) {
        Preconditions.checkState(this.f945f == null, "Already set full stream decompressor");
        Preconditions.checkNotNull(interfaceC0239x, "Can't pass an empty decompressor");
        this.f944e = interfaceC0239x;
    }

    @VisibleForTesting
    static final class c extends FilterInputStream {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f947a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Ld f948b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f949c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f950d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f951e;

        public c(InputStream inputStream, int i, Ld ld) {
            super(inputStream);
            this.f951e = -1L;
            this.f947a = i;
            this.f948b = ld;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public synchronized void mark(int i) {
            ((FilterInputStream) this).in.mark(i);
            this.f951e = this.f950d;
        }

        public final void p() {
            long j = this.f950d;
            long j2 = this.f949c;
            if (j > j2) {
                long j3 = j - j2;
                for (c.a.Ca ca : this.f948b.f1069a) {
                    ca.a(j3);
                }
                this.f949c = this.f950d;
            }
        }

        public final void q() {
            long j = this.f950d;
            int i = this.f947a;
            if (j > i) {
                throw c.a.za.i.b(String.format("Decompressed gRPC message exceeds maximum size %d", Integer.valueOf(i))).b();
            }
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            int i = ((FilterInputStream) this).in.read();
            if (i != -1) {
                this.f950d++;
            }
            q();
            p();
            return i;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public synchronized void reset() {
            if (!((FilterInputStream) this).in.markSupported()) {
                throw new IOException("Mark not supported");
            }
            if (this.f951e == -1) {
                throw new IOException("Mark not set");
            }
            ((FilterInputStream) this).in.reset();
            this.f950d = this.f951e;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public long skip(long j) throws IOException {
            long jSkip = ((FilterInputStream) this).in.skip(j);
            this.f950d += jSkip;
            q();
            p();
            return jSkip;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3 = ((FilterInputStream) this).in.read(bArr, i, i2);
            if (i3 != -1) {
                this.f950d += (long) i3;
            }
            q();
            p();
            return i3;
        }
    }

    @Override // c.a.b.InterfaceC0088aa
    public void a(Tc tc) throws Throwable {
        Preconditions.checkNotNull(tc, "data");
        boolean z = false;
        boolean z2 = true;
        try {
            if (isClosed() || this.r) {
                z = true;
            } else {
                if (this.f945f != null) {
                    C0159ob c0159ob = this.f945f;
                    Preconditions.checkState(!c0159ob.i, "GzipInflatingBuffer is closed");
                    c0159ob.f1386a.a(tc);
                    c0159ob.o = false;
                } else {
                    this.m.a(tc);
                }
                try {
                    q();
                } catch (Throwable th) {
                    z2 = false;
                    th = th;
                    if (z2) {
                        tc.close();
                    }
                    throw th;
                }
            }
            if (z) {
                tc.close();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
