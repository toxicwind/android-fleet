package b.d.a.a.d;

import java.util.Arrays;
import java.util.BitSet;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f151a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AtomicReference<b> f152b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f153c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f154d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f155e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f156f;
    public int g;
    public int h;
    public int[] i;
    public f[] j;
    public C0004a[] k;
    public int l;
    public int m;
    public transient boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public BitSet r;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b.d.a.a.d.a$a, reason: collision with other inner class name */
    public static final class C0004a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final f f157a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final C0004a f158b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f159c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f160d;

        public C0004a(f fVar, C0004a c0004a) {
            this.f157a = fVar;
            this.f158b = c0004a;
            this.f160d = c0004a != null ? 1 + c0004a.f160d : 1;
            this.f159c = fVar.f184b;
        }

        public f a(int i, int i2, int i3) {
            if (this.f159c == i && this.f157a.a(i2, i3)) {
                return this.f157a;
            }
            for (C0004a c0004a = this.f158b; c0004a != null; c0004a = c0004a.f158b) {
                if (c0004a.f159c == i) {
                    f fVar = c0004a.f157a;
                    if (fVar.a(i2, i3)) {
                        return fVar;
                    }
                }
            }
            return null;
        }
    }

    public a(int i, boolean z, int i2, boolean z2) {
        this.f151a = null;
        this.f153c = i2;
        this.f154d = z;
        this.f155e = z2;
        int i3 = 16;
        if (i >= 16) {
            if (((i - 1) & i) != 0) {
                while (i3 < i) {
                    i3 += i3;
                }
            } else {
                i3 = i;
            }
        }
        this.f152b = new AtomicReference<>(b(i3));
    }

    public final void a(b bVar) {
        int i = bVar.f161a;
        b bVar2 = this.f152b.get();
        if (i == bVar2.f161a) {
            return;
        }
        if (i > 6000) {
            bVar = b(64);
        }
        this.f152b.compareAndSet(bVar2, bVar);
    }

    public final b b(int i) {
        return new b(0, i - 1, new int[i], new f[i], null, 0, 0, 0);
    }

    public a c(int i) {
        return new a(this, b.d.a.a.b.a.INTERN_FIELD_NAMES.a(i), this.f153c, b.d.a.a.b.a.FAIL_ON_SYMBOL_HASH_OVERFLOW.a(i), this.f152b.get());
    }

    public f b(int[] iArr, int i) {
        if (i < 3) {
            return b(iArr[0], i >= 2 ? iArr[1] : 0);
        }
        int iA = a(iArr, i);
        int i2 = this.h & iA;
        int i3 = this.i[i2];
        if ((((i3 >> 8) ^ iA) << 8) == 0) {
            f fVar = this.j[i2];
            if (fVar == null || fVar.a(iArr, i)) {
                return fVar;
            }
        } else if (i3 == 0) {
            return null;
        }
        int i4 = i3 & 255;
        if (i4 <= 0) {
            return null;
        }
        C0004a c0004a = this.k[i4 - 1];
        if (c0004a == null) {
            return null;
        }
        if (c0004a.f159c == iA && c0004a.f157a.a(iArr, i)) {
            return c0004a.f157a;
        }
        while (true) {
            c0004a = c0004a.f158b;
            if (c0004a == null) {
                return null;
            }
            if (c0004a.f159c == iA) {
                f fVar2 = c0004a.f157a;
                if (fVar2.a(iArr, i)) {
                    return fVar2;
                }
            }
        }
    }

    public int a(int i) {
        int i2 = i ^ this.f153c;
        int i3 = i2 + (i2 >>> 15);
        return i3 ^ (i3 >>> 9);
    }

    public a(a aVar, boolean z, int i, boolean z2, b bVar) {
        this.f151a = aVar;
        this.f153c = i;
        this.f154d = z;
        this.f155e = z2;
        this.f152b = null;
        this.f156f = bVar.f161a;
        this.h = bVar.f162b;
        this.i = bVar.f163c;
        this.j = bVar.f164d;
        this.k = bVar.f165e;
        this.l = bVar.f166f;
        this.m = bVar.g;
        this.g = bVar.h;
        this.n = false;
        this.o = true;
        this.p = true;
        this.q = true;
    }

    public int a(int i, int i2) {
        int i3 = this.f153c ^ ((i2 * 33) + (i ^ (i >>> 15)));
        return i3 + (i3 >>> 7);
    }

    public int a(int[] iArr, int i) {
        if (i >= 3) {
            int i2 = iArr[0] ^ this.f153c;
            int i3 = (((i2 + (i2 >>> 9)) * 33) + iArr[1]) * 65599;
            int i4 = (i3 + (i3 >>> 15)) ^ iArr[2];
            int i5 = i4 + (i4 >>> 17);
            for (int i6 = 3; i6 < i; i6++) {
                int i7 = (i5 * 31) ^ iArr[i6];
                int i8 = i7 + (i7 >>> 3);
                i5 = i8 ^ (i8 << 7);
            }
            int i9 = i5 + (i5 >>> 15);
            return (i9 << 9) ^ i9;
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f161a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f162b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int[] f163c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final f[] f164d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final C0004a[] f165e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final int f166f;
        public final int g;
        public final int h;

        public b(int i, int i2, int[] iArr, f[] fVarArr, C0004a[] c0004aArr, int i3, int i4, int i5) {
            this.f161a = i;
            this.f162b = i2;
            this.f163c = iArr;
            this.f164d = fVarArr;
            this.f165e = c0004aArr;
            this.f166f = i3;
            this.g = i4;
            this.h = i5;
        }

        public b(a aVar) {
            this.f161a = aVar.f156f;
            this.f162b = aVar.h;
            this.f163c = aVar.i;
            this.f164d = aVar.j;
            this.f165e = aVar.k;
            this.f166f = aVar.l;
            this.g = aVar.m;
            this.h = aVar.g;
        }
    }

    public final void a() {
        C0004a[] c0004aArr = this.k;
        this.k = (C0004a[]) Arrays.copyOf(c0004aArr, c0004aArr.length * 2);
    }

    public f b(int i, int i2) {
        int i3;
        if (i2 == 0) {
            int i4 = this.f153c ^ i;
            int i5 = i4 + (i4 >>> 15);
            i3 = i5 ^ (i5 >>> 9);
        } else {
            int i6 = this.f153c ^ ((i2 * 33) + ((i >>> 15) ^ i));
            i3 = i6 + (i6 >>> 7);
        }
        int i7 = this.h & i3;
        int i8 = this.i[i7];
        if ((((i8 >> 8) ^ i3) << 8) == 0) {
            f fVar = this.j[i7];
            if (fVar == null) {
                return null;
            }
            if (fVar.a(i, i2)) {
                return fVar;
            }
        } else if (i8 == 0) {
            return null;
        }
        int i9 = i8 & 255;
        if (i9 > 0) {
            C0004a c0004a = this.k[i9 - 1];
            if (c0004a != null) {
                return c0004a.a(i3, i, i2);
            }
        }
        return null;
    }

    public final int b() {
        C0004a[] c0004aArr = this.k;
        int i = this.m;
        int i2 = Integer.MAX_VALUE;
        int i3 = -1;
        for (int i4 = 0; i4 < i; i4++) {
            C0004a c0004a = c0004aArr[i4];
            if (c0004a == null) {
                return i4;
            }
            int i5 = c0004a.f160d;
            if (i5 < i2) {
                if (i5 == 1) {
                    return i4;
                }
                i3 = i4;
                i2 = i5;
            }
        }
        return i3;
    }
}
