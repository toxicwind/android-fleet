package b.d.a.a.d;

import java.util.Arrays;
import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f167a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f168b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f169c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f170d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f171e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String[] f172f;
    public a[] g;
    public int h;
    public int i;
    public int j;
    public int k;
    public boolean l;
    public BitSet m;

    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f173a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final a f174b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f175c;

        public a(String str, a aVar) {
            this.f173a = str;
            this.f174b = aVar;
            this.f175c = aVar != null ? 1 + aVar.f175c : 1;
        }

        public String a(char[] cArr, int i, int i2) {
            if (this.f173a.length() != i2) {
                return null;
            }
            int i3 = 0;
            while (this.f173a.charAt(i3) == cArr[i + i3]) {
                i3++;
                if (i3 >= i2) {
                    return this.f173a;
                }
            }
            return null;
        }
    }

    public b() {
        this.f171e = true;
        this.f170d = -1;
        this.l = true;
        this.f169c = 0;
        this.k = 0;
        b(64);
    }

    public static b a() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        return f167a.d((((int) jCurrentTimeMillis) + ((int) (jCurrentTimeMillis >>> 32))) | 1);
    }

    public final void b(int i) {
        this.f172f = new String[i];
        this.g = new a[i >> 1];
        this.j = i - 1;
        this.h = 0;
        this.k = 0;
        this.i = i - (i >> 2);
    }

    public b c(int i) {
        String[] strArr;
        a[] aVarArr;
        int i2;
        int i3;
        int i4;
        synchronized (this) {
            strArr = this.f172f;
            aVarArr = this.g;
            i2 = this.h;
            i3 = this.f169c;
            i4 = this.k;
        }
        return new b(this, i, strArr, aVarArr, i2, i3, i4);
    }

    public final b d(int i) {
        return new b(null, -1, this.f172f, this.g, this.h, i, this.k);
    }

    public final void a(b bVar) {
        if (bVar.h > 12000) {
            synchronized (this) {
                b(256);
                this.l = false;
            }
        } else {
            if (bVar.h <= this.h) {
                return;
            }
            synchronized (this) {
                this.f172f = bVar.f172f;
                this.g = bVar.g;
                this.h = bVar.h;
                this.i = bVar.i;
                this.j = bVar.j;
                this.k = bVar.k;
                this.l = false;
            }
        }
    }

    public b(b bVar, int i, String[] strArr, a[] aVarArr, int i2, int i3, int i4) {
        this.f168b = bVar;
        this.f170d = i;
        this.f171e = b.d.a.a.b.a.CANONICALIZE_FIELD_NAMES.a(i);
        this.f172f = strArr;
        this.g = aVarArr;
        this.h = i2;
        this.f169c = i3;
        int length = strArr.length;
        this.i = length - (length >> 2);
        this.j = length - 1;
        this.k = i4;
        this.l = false;
    }

    public String a(char[] cArr, int i, int i2, int i3) {
        String strA;
        if (i2 < 1) {
            return "";
        }
        if (!this.f171e) {
            return new String(cArr, i, i2);
        }
        int iA = (i3 + (i3 >>> 15)) & this.j;
        String str = this.f172f[iA];
        if (str != null) {
            if (str.length() == i2) {
                int i4 = 0;
                while (str.charAt(i4) == cArr[i + i4]) {
                    i4++;
                    if (i4 == i2) {
                        return str;
                    }
                }
            }
            a aVar = this.g[iA >> 1];
            if (aVar != null) {
                String strA2 = aVar.a(cArr, i, i2);
                if (strA2 != null) {
                    return strA2;
                }
                a aVar2 = aVar.f174b;
                while (true) {
                    if (aVar2 == null) {
                        strA = null;
                        break;
                    }
                    strA = aVar2.a(cArr, i, i2);
                    if (strA != null) {
                        break;
                    }
                    aVar2 = aVar2.f174b;
                }
                if (strA != null) {
                    return strA;
                }
            }
        }
        if (!this.l) {
            String[] strArr = this.f172f;
            this.f172f = (String[]) Arrays.copyOf(strArr, strArr.length);
            a[] aVarArr = this.g;
            this.g = (a[]) Arrays.copyOf(aVarArr, aVarArr.length);
            this.l = true;
        } else if (this.h >= this.i) {
            String[] strArr2 = this.f172f;
            int length = strArr2.length;
            int i5 = length + length;
            if (i5 > 65536) {
                this.h = 0;
                this.f171e = false;
                this.f172f = new String[64];
                this.g = new a[32];
                this.j = 63;
                this.l = true;
            } else {
                a[] aVarArr2 = this.g;
                this.f172f = new String[i5];
                this.g = new a[i5 >> 1];
                this.j = i5 - 1;
                this.i = i5 - (i5 >> 2);
                int i6 = 0;
                int iMax = 0;
                for (String str2 : strArr2) {
                    if (str2 != null) {
                        i6++;
                        int iA2 = a(a(str2));
                        String[] strArr3 = this.f172f;
                        if (strArr3[iA2] == null) {
                            strArr3[iA2] = str2;
                        } else {
                            int i7 = iA2 >> 1;
                            a aVar3 = new a(str2, this.g[i7]);
                            this.g[i7] = aVar3;
                            iMax = Math.max(iMax, aVar3.f175c);
                        }
                    }
                }
                int i8 = length >> 1;
                for (int i9 = 0; i9 < i8; i9++) {
                    for (a aVar4 = aVarArr2[i9]; aVar4 != null; aVar4 = aVar4.f174b) {
                        i6++;
                        String str3 = aVar4.f173a;
                        int iA3 = a(a(str3));
                        String[] strArr4 = this.f172f;
                        if (strArr4[iA3] == null) {
                            strArr4[iA3] = str3;
                        } else {
                            int i10 = iA3 >> 1;
                            a aVar5 = new a(str3, this.g[i10]);
                            this.g[i10] = aVar5;
                            iMax = Math.max(iMax, aVar5.f175c);
                        }
                    }
                }
                this.k = iMax;
                this.m = null;
                if (i6 != this.h) {
                    throw new Error(b.a.a.a.a.a(b.a.a.a.a.a("Internal error on SymbolTable.rehash(): had "), this.h, " entries; now have ", i6, "."));
                }
            }
            int i11 = i2 + i;
            int i12 = this.f169c;
            for (int i13 = i; i13 < i11; i13++) {
                i12 = (i12 * 33) + cArr[i13];
            }
            if (i12 == 0) {
                i12 = 1;
            }
            iA = a(i12);
        }
        String str4 = new String(cArr, i, i2);
        if (b.d.a.a.b.a.INTERN_FIELD_NAMES.a(this.f170d)) {
            str4 = b.d.a.a.e.e.f204a.a(str4);
        }
        this.h++;
        String[] strArr5 = this.f172f;
        if (strArr5[iA] == null) {
            strArr5[iA] = str4;
        } else {
            int i14 = iA >> 1;
            a aVar6 = new a(str4, this.g[i14]);
            int i15 = aVar6.f175c;
            if (i15 > 100) {
                BitSet bitSet = this.m;
                if (bitSet == null) {
                    this.m = new BitSet();
                    this.m.set(i14);
                } else if (bitSet.get(i14)) {
                    if (!b.d.a.a.b.a.FAIL_ON_SYMBOL_HASH_OVERFLOW.a(this.f170d)) {
                        this.f171e = false;
                    } else {
                        throw new IllegalStateException(b.a.a.a.a.a(b.a.a.a.a.a("Longest collision chain in symbol table (of size "), this.h, ") now exceeds maximum, ", 100, " -- suspect a DoS attack based on hash collisions"));
                    }
                } else {
                    this.m.set(i14);
                }
                this.f172f[i14 + i14] = aVar6.f173a;
                this.g[i14] = null;
                this.h -= aVar6.f175c;
                this.k = -1;
            } else {
                this.g[i14] = aVar6;
                this.k = Math.max(i15, this.k);
            }
        }
        return str4;
    }

    public int a(int i) {
        return (i + (i >>> 15)) & this.j;
    }

    public int a(String str) {
        int length = str.length();
        int iCharAt = this.f169c;
        for (int i = 0; i < length; i++) {
            iCharAt = (iCharAt * 33) + str.charAt(i);
        }
        if (iCharAt == 0) {
            return 1;
        }
        return iCharAt;
    }
}
