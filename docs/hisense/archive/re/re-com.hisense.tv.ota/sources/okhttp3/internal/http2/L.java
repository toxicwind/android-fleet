package okhttp3.internal.http2;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f235a;
    private final int[] b = new int[10];

    void a() {
        this.f235a = 0;
        Arrays.fill(this.b, 0);
    }

    int b(int i) {
        return this.b[i];
    }

    int c() {
        if ((this.f235a & 2) != 0) {
            return this.b[1];
        }
        return -1;
    }

    int d() {
        if ((this.f235a & 128) != 0) {
            return this.b[7];
        }
        return 65535;
    }

    int e(int i) {
        return (this.f235a & 16) != 0 ? this.b[4] : i;
    }

    int f(int i) {
        return (this.f235a & 32) != 0 ? this.b[5] : i;
    }

    boolean g(int i) {
        return (this.f235a & (1 << i)) != 0;
    }

    void h(L l) {
        for (int i = 0; i < 10; i++) {
            if (l.g(i)) {
                i(i, l.b(i));
            }
        }
    }

    L i(int i, int i2) {
        if (i >= 0) {
            int[] iArr = this.b;
            if (i < iArr.length) {
                this.f235a = (1 << i) | this.f235a;
                iArr[i] = i2;
            }
        }
        return this;
    }

    int j() {
        return Integer.bitCount(this.f235a);
    }
}
