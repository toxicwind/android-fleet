package d;

import java.io.OutputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class v implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g f2131a = new g();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final B f2132b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2133c;

    public v(B b2) {
        if (b2 == null) {
            throw new NullPointerException("sink == null");
        }
        this.f2132b = b2;
    }

    @Override // d.h
    public g a() {
        return this.f2131a;
    }

    @Override // d.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.f2133c) {
            return;
        }
        if (this.f2131a.f2102c > 0) {
            this.f2132b.write(this.f2131a, this.f2131a.f2102c);
        }
        th = null;
        try {
            this.f2132b.close();
        } catch (Throwable th) {
            if (th == null) {
                th = th;
            }
        }
        this.f2133c = true;
        if (th == null) {
            return;
        }
        F.a(th);
        throw null;
    }

    @Override // d.h
    public h e(long j) {
        if (this.f2133c) {
            throw new IllegalStateException("closed");
        }
        this.f2131a.e(j);
        k();
        return this;
    }

    @Override // d.h
    public h f(long j) {
        if (this.f2133c) {
            throw new IllegalStateException("closed");
        }
        this.f2131a.f(j);
        k();
        return this;
    }

    @Override // d.h, d.B, java.io.Flushable
    public void flush() {
        if (this.f2133c) {
            throw new IllegalStateException("closed");
        }
        g gVar = this.f2131a;
        long j = gVar.f2102c;
        if (j > 0) {
            this.f2132b.write(gVar, j);
        }
        this.f2132b.flush();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f2133c;
    }

    @Override // d.h
    public h j() {
        if (this.f2133c) {
            throw new IllegalStateException("closed");
        }
        g gVar = this.f2131a;
        long j = gVar.f2102c;
        if (j > 0) {
            this.f2132b.write(gVar, j);
        }
        return this;
    }

    @Override // d.h
    public h k() {
        if (this.f2133c) {
            throw new IllegalStateException("closed");
        }
        long jM = this.f2131a.m();
        if (jM > 0) {
            this.f2132b.write(this.f2131a, jM);
        }
        return this;
    }

    @Override // d.h
    public OutputStream l() {
        return new u(this);
    }

    @Override // d.B
    public E timeout() {
        return this.f2132b.timeout();
    }

    public String toString() {
        return b.a.a.a.a.a(b.a.a.a.a.a("buffer("), this.f2132b, ")");
    }

    @Override // d.B
    public void write(g gVar, long j) {
        if (this.f2133c) {
            throw new IllegalStateException("closed");
        }
        this.f2131a.write(gVar, j);
        k();
    }

    @Override // d.h
    public h writeByte(int i) {
        if (this.f2133c) {
            throw new IllegalStateException("closed");
        }
        this.f2131a.writeByte(i);
        k();
        return this;
    }

    @Override // d.h
    public h writeInt(int i) {
        if (this.f2133c) {
            throw new IllegalStateException("closed");
        }
        this.f2131a.writeInt(i);
        k();
        return this;
    }

    @Override // d.h
    public h writeShort(int i) {
        if (this.f2133c) {
            throw new IllegalStateException("closed");
        }
        this.f2131a.writeShort(i);
        k();
        return this;
    }

    @Override // d.h
    public h a(j jVar) {
        if (this.f2133c) {
            throw new IllegalStateException("closed");
        }
        this.f2131a.a(jVar);
        k();
        return this;
    }

    @Override // d.h
    public h write(byte[] bArr) {
        if (!this.f2133c) {
            this.f2131a.write(bArr);
            k();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // d.h
    public h a(String str) {
        if (!this.f2133c) {
            this.f2131a.a(str);
            k();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // d.h
    public h write(byte[] bArr, int i, int i2) {
        if (!this.f2133c) {
            this.f2131a.write(bArr, i, i2);
            k();
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // d.h
    public long a(C c2) {
        if (c2 == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long j2 = c2.read(this.f2131a, 8192L);
            if (j2 == -1) {
                return j;
            }
            j += j2;
            k();
        }
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        if (!this.f2133c) {
            int iWrite = this.f2131a.write(byteBuffer);
            k();
            return iWrite;
        }
        throw new IllegalStateException("closed");
    }
}
