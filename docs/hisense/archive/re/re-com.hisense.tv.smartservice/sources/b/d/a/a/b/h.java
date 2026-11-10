package b.d.a.a.b;

import b.d.a.a.m;
import java.io.Serializable;
import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: classes.dex */
public class h implements m, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f110a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f111b;

    public h(String str) {
        if (str == null) {
            throw new IllegalStateException("Null String illegal for SerializedString");
        }
        this.f110a = str;
    }

    public final byte[] a() {
        int i;
        byte[] bArr = this.f111b;
        if (bArr == null) {
            SoftReference<d> softReference = d.f94a.get();
            d dVar = softReference == null ? null : softReference.get();
            if (dVar == null) {
                dVar = new d();
                d.f94a.set(new SoftReference<>(dVar));
            }
            String str = this.f110a;
            b.d.a.a.e.b bVar = dVar.f95b;
            if (bVar == null) {
                bVar = new b.d.a.a.e.b(null);
                dVar.f95b = bVar;
            }
            int length = str.length();
            bVar.reset();
            byte[] bArr2 = bVar.f201d;
            int length2 = bArr2.length;
            byte[] bArr3 = bArr2;
            int i2 = 0;
            int i3 = 0;
            loop0: while (i2 < length) {
                int i4 = i2 + 1;
                int iCharAt = str.charAt(i2);
                while (iCharAt <= 127) {
                    if (i3 >= length2) {
                        bVar.p();
                        byte[] bArr4 = bVar.f201d;
                        i3 = 0;
                        bArr3 = bArr4;
                        length2 = bArr4.length;
                    }
                    int i5 = i3 + 1;
                    bArr3[i3] = (byte) iCharAt;
                    if (i4 >= length) {
                        i3 = i5;
                        break loop0;
                    }
                    char cCharAt = str.charAt(i4);
                    i4++;
                    iCharAt = cCharAt;
                    i3 = i5;
                }
                if (i3 >= length2) {
                    bVar.p();
                    bArr3 = bVar.f201d;
                    length2 = bArr3.length;
                    i3 = 0;
                }
                if (iCharAt < 2048) {
                    bArr3[i3] = (byte) ((iCharAt >> 6) | 192);
                    i = i3 + 1;
                } else if (iCharAt < 55296 || iCharAt > 57343) {
                    int i6 = i3 + 1;
                    bArr3[i3] = (byte) ((iCharAt >> 12) | 224);
                    if (i6 >= length2) {
                        bVar.p();
                        bArr3 = bVar.f201d;
                        length2 = bArr3.length;
                        i6 = 0;
                    }
                    i = i6 + 1;
                    bArr3[i6] = (byte) (((iCharAt >> 6) & 63) | 128);
                } else {
                    if (iCharAt > 56319) {
                        d.a(iCharAt);
                        throw null;
                    }
                    if (i4 >= length) {
                        d.a(iCharAt);
                        throw null;
                    }
                    int i7 = i4 + 1;
                    char cCharAt2 = str.charAt(i4);
                    if (cCharAt2 < 56320 || cCharAt2 > 57343) {
                        StringBuilder sbA = b.a.a.a.a.a("Broken surrogate pair: first char 0x");
                        sbA.append(Integer.toHexString(iCharAt));
                        sbA.append(", second 0x");
                        sbA.append(Integer.toHexString(cCharAt2));
                        sbA.append("; illegal combination");
                        throw new IllegalArgumentException(sbA.toString());
                    }
                    iCharAt = ((iCharAt - 55296) << 10) + 65536 + (cCharAt2 - 56320);
                    if (iCharAt > 1114111) {
                        d.a(iCharAt);
                        throw null;
                    }
                    int i8 = i3 + 1;
                    bArr3[i3] = (byte) ((iCharAt >> 18) | 240);
                    if (i8 >= length2) {
                        bVar.p();
                        bArr3 = bVar.f201d;
                        length2 = bArr3.length;
                        i8 = 0;
                    }
                    int i9 = i8 + 1;
                    bArr3[i8] = (byte) (((iCharAt >> 12) & 63) | 128);
                    if (i9 >= length2) {
                        bVar.p();
                        byte[] bArr5 = bVar.f201d;
                        i9 = 0;
                        bArr3 = bArr5;
                        length2 = bArr5.length;
                    }
                    bArr3[i9] = (byte) (((iCharAt >> 6) & 63) | 128);
                    i = i9 + 1;
                    i4 = i7;
                }
                if (i >= length2) {
                    bVar.p();
                    byte[] bArr6 = bVar.f201d;
                    i = 0;
                    bArr3 = bArr6;
                    length2 = bArr6.length;
                }
                bArr3[i] = (byte) ((iCharAt & 63) | 128);
                i2 = i4;
                i3 = i + 1;
            }
            b.d.a.a.e.b bVar2 = dVar.f95b;
            bVar2.f202e = i3;
            int i10 = bVar2.f200c + bVar2.f202e;
            if (i10 == 0) {
                bArr = b.d.a.a.e.b.f198a;
            } else {
                byte[] bArr7 = new byte[i10];
                int i11 = 0;
                for (byte[] bArr8 : bVar2.f199b) {
                    int length3 = bArr8.length;
                    System.arraycopy(bArr8, 0, bArr7, i11, length3);
                    i11 += length3;
                }
                System.arraycopy(bVar2.f201d, 0, bArr7, i11, bVar2.f202e);
                int i12 = i11 + bVar2.f202e;
                if (i12 != i10) {
                    throw new RuntimeException(b.a.a.a.a.a("Internal error: total len assumed to be ", i10, ", copied ", i12, " bytes"));
                }
                if (!bVar2.f199b.isEmpty()) {
                    bVar2.reset();
                }
                bArr = bArr7;
            }
            this.f111b = bArr;
        }
        return bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != h.class) {
            return false;
        }
        return this.f110a.equals(((h) obj).f110a);
    }

    public final int hashCode() {
        return this.f110a.hashCode();
    }

    public final String toString() {
        return this.f110a;
    }
}
