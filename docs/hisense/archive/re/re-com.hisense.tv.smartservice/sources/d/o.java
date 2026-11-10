package d;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes2.dex */
public final class o implements C {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f2120a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Inflater f2121b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2122c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2123d;

    public o(i iVar, Inflater inflater) {
        if (iVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (inflater == null) {
            throw new IllegalArgumentException("inflater == null");
        }
        this.f2120a = iVar;
        this.f2121b = inflater;
    }

    @Override // d.C, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f2123d) {
            return;
        }
        this.f2121b.end();
        this.f2123d = true;
        this.f2120a.close();
    }

    public final void j() {
        int i = this.f2122c;
        if (i == 0) {
            return;
        }
        int remaining = i - this.f2121b.getRemaining();
        this.f2122c -= remaining;
        this.f2120a.skip(remaining);
    }

    @Override // d.C
    public long read(g gVar, long j) throws IOException {
        boolean z;
        if (j < 0) {
            throw new IllegalArgumentException(b.a.a.a.a.a("byteCount < 0: ", j));
        }
        if (this.f2123d) {
            throw new IllegalStateException("closed");
        }
        if (j == 0) {
            return 0L;
        }
        do {
            z = false;
            if (this.f2121b.needsInput()) {
                j();
                if (this.f2121b.getRemaining() != 0) {
                    throw new IllegalStateException("?");
                }
                if (this.f2120a.e()) {
                    z = true;
                } else {
                    y yVar = this.f2120a.a().f2101b;
                    int i = yVar.f2140c;
                    int i2 = yVar.f2139b;
                    this.f2122c = i - i2;
                    this.f2121b.setInput(yVar.f2138a, i2, this.f2122c);
                }
            }
            try {
                y yVarA = gVar.a(1);
                int iInflate = this.f2121b.inflate(yVarA.f2138a, yVarA.f2140c, (int) Math.min(j, 8192 - yVarA.f2140c));
                if (iInflate > 0) {
                    yVarA.f2140c += iInflate;
                    long j2 = iInflate;
                    gVar.f2102c += j2;
                    return j2;
                }
                if (!this.f2121b.finished() && !this.f2121b.needsDictionary()) {
                }
                j();
                if (yVarA.f2139b != yVarA.f2140c) {
                    return -1L;
                }
                gVar.f2101b = yVarA.a();
                z.a(yVarA);
                return -1L;
            } catch (DataFormatException e2) {
                throw new IOException(e2);
            }
        } while (!z);
        throw new EOFException("source exhausted prematurely");
    }

    @Override // d.C
    public E timeout() {
        return this.f2120a.timeout();
    }
}
