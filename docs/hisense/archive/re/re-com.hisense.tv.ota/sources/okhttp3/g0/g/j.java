package okhttp3.g0.g;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import okio.B;

/* JADX INFO: loaded from: classes.dex */
final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final String f192a;
    final long[] b;
    final File[] c;
    final File[] d;
    boolean e;
    i f;
    long g;
    final /* synthetic */ l h;

    j(l lVar, String str) {
        this.h = lVar;
        this.f192a = str;
        int i = lVar.h;
        this.b = new long[i];
        this.c = new File[i];
        this.d = new File[i];
        StringBuilder sb = new StringBuilder(str);
        sb.append('.');
        int length = sb.length();
        for (int i2 = 0; i2 < lVar.h; i2++) {
            sb.append(i2);
            this.c[i2] = new File(lVar.b, sb.toString());
            sb.append(".tmp");
            this.d[i2] = new File(lVar.b, sb.toString());
            sb.setLength(length);
        }
    }

    private IOException a(String[] strArr) throws IOException {
        throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
    }

    void b(String[] strArr) throws IOException {
        if (strArr.length != this.h.h) {
            a(strArr);
            throw null;
        }
        for (int i = 0; i < strArr.length; i++) {
            try {
                this.b[i] = Long.parseLong(strArr[i]);
            } catch (NumberFormatException unused) {
                a(strArr);
                throw null;
            }
        }
    }

    k c() {
        if (!Thread.holdsLock(this.h)) {
            throw new AssertionError();
        }
        B[] bArr = new B[this.h.h];
        long[] jArr = (long[]) this.b.clone();
        for (int i = 0; i < this.h.h; i++) {
            try {
                bArr[i] = this.h.f194a.c(this.c[i]);
            } catch (FileNotFoundException unused) {
                for (int i2 = 0; i2 < this.h.h && bArr[i2] != null; i2++) {
                    okhttp3.g0.e.g(bArr[i2]);
                }
                try {
                    this.h.Y(this);
                    return null;
                } catch (IOException unused2) {
                    return null;
                }
            }
        }
        return new k(this.h, this.f192a, this.g, bArr, jArr);
    }

    void d(okio.i iVar) {
        for (long j : this.b) {
            iVar.q(32).k(j);
        }
    }
}
