package d;

import com.google.common.base.Ascii;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class j implements Serializable, Comparable<j> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f2109a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final j f2110b = a(new byte[0]);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[] f2111c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public transient int f2112d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public transient String f2113e;

    public j(byte[] bArr) {
        this.f2111c = bArr;
    }

    public static j a(byte... bArr) {
        if (bArr != null) {
            return new j((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    public static j d(String str) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        j jVar = new j(str.getBytes(F.f2092a));
        jVar.f2113e = str;
        return jVar;
    }

    public String b() {
        byte[] bArr = this.f2111c;
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        for (byte b2 : bArr) {
            int i2 = i + 1;
            char[] cArr2 = f2109a;
            cArr[i] = cArr2[(b2 >> 4) & 15];
            i = i2 + 1;
            cArr[i2] = cArr2[b2 & Ascii.SI];
        }
        return new String(cArr);
    }

    public final j c(String str) {
        try {
            return a(MessageDigest.getInstance(str).digest(this.f2111c));
        } catch (NoSuchAlgorithmException e2) {
            throw new AssertionError(e2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
    
        if (r0 < r1) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:?, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0025, code lost:
    
        if (r7 < r8) goto L9;
     */
    @Override // java.lang.Comparable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int compareTo(d.j r10) {
        /*
            r9 = this;
            d.j r10 = (d.j) r10
            int r0 = r9.g()
            int r1 = r10.g()
            int r2 = java.lang.Math.min(r0, r1)
            r3 = 0
            r4 = r3
        L10:
            r5 = -1
            r6 = 1
            if (r4 >= r2) goto L2b
            byte r7 = r9.a(r4)
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r8 = r10.a(r4)
            r8 = r8 & 255(0xff, float:3.57E-43)
            if (r7 != r8) goto L25
            int r4 = r4 + 1
            goto L10
        L25:
            if (r7 >= r8) goto L29
        L27:
            r3 = r5
            goto L31
        L29:
            r3 = r6
            goto L31
        L2b:
            if (r0 != r1) goto L2e
            goto L31
        L2e:
            if (r0 >= r1) goto L29
            goto L27
        L31:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: d.j.compareTo(java.lang.Object):int");
    }

    public j e() {
        return c("SHA-1");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof j) {
            j jVar = (j) obj;
            int iG = jVar.g();
            byte[] bArr = this.f2111c;
            if (iG == bArr.length && jVar.a(0, bArr, 0, bArr.length)) {
                return true;
            }
        }
        return false;
    }

    public j f() {
        return c("SHA-256");
    }

    public int g() {
        return this.f2111c.length;
    }

    public j h() {
        int i = 0;
        while (true) {
            byte[] bArr = this.f2111c;
            if (i >= bArr.length) {
                return this;
            }
            byte b2 = bArr[i];
            if (b2 >= 65 && b2 <= 90) {
                byte[] bArr2 = (byte[]) bArr.clone();
                bArr2[i] = (byte) (b2 + 32);
                for (int i2 = i + 1; i2 < bArr2.length; i2++) {
                    byte b3 = bArr2[i2];
                    if (b3 >= 65 && b3 <= 90) {
                        bArr2[i2] = (byte) (b3 + 32);
                    }
                }
                return new j(bArr2);
            }
            i++;
        }
    }

    public int hashCode() {
        int i = this.f2112d;
        if (i != 0) {
            return i;
        }
        int iHashCode = Arrays.hashCode(this.f2111c);
        this.f2112d = iHashCode;
        return iHashCode;
    }

    public byte[] i() {
        return (byte[]) this.f2111c.clone();
    }

    public String j() {
        String str = this.f2113e;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.f2111c, F.f2092a);
        this.f2113e = str2;
        return str2;
    }

    public String toString() {
        if (this.f2111c.length == 0) {
            return "[size=0]";
        }
        String strJ = j();
        int length = strJ.length();
        int length2 = 0;
        int i = 0;
        while (true) {
            if (length2 >= length) {
                length2 = strJ.length();
                break;
            }
            if (i != 64) {
                int iCodePointAt = strJ.codePointAt(length2);
                if ((Character.isISOControl(iCodePointAt) && iCodePointAt != 10 && iCodePointAt != 13) || iCodePointAt == 65533) {
                    length2 = -1;
                    break;
                }
                i++;
                length2 += Character.charCount(iCodePointAt);
            } else {
                break;
            }
        }
        if (length2 != -1) {
            String strReplace = strJ.substring(0, length2).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
            if (length2 >= strJ.length()) {
                return b.a.a.a.a.a("[text=", strReplace, "]");
            }
            StringBuilder sbA = b.a.a.a.a.a("[size=");
            sbA.append(this.f2111c.length);
            sbA.append(" text=");
            sbA.append(strReplace);
            sbA.append("…]");
            return sbA.toString();
        }
        if (this.f2111c.length <= 64) {
            StringBuilder sbA2 = b.a.a.a.a.a("[hex=");
            sbA2.append(b());
            sbA2.append("]");
            return sbA2.toString();
        }
        StringBuilder sbA3 = b.a.a.a.a.a("[size=");
        sbA3.append(this.f2111c.length);
        sbA3.append(" hex=");
        sbA3.append(a(0, 64).b());
        sbA3.append("…]");
        return sbA3.toString();
    }

    public static j a(String str, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        if (charset != null) {
            return new j(str.getBytes(charset));
        }
        throw new IllegalArgumentException("charset == null");
    }

    public byte[] c() {
        return this.f2111c;
    }

    public j d() {
        return c("MD5");
    }

    public static j b(String str) {
        if (str != null) {
            if (str.length() % 2 == 0) {
                byte[] bArr = new byte[str.length() / 2];
                for (int i = 0; i < bArr.length; i++) {
                    int i2 = i * 2;
                    bArr[i] = (byte) (a(str.charAt(i2 + 1)) + (a(str.charAt(i2)) << 4));
                }
                return a(bArr);
            }
            throw new IllegalArgumentException(b.a.a.a.a.a("Unexpected hex string: ", str));
        }
        throw new IllegalArgumentException("hex == null");
    }

    public String a() {
        byte[] bArr = this.f2111c;
        byte[] bArr2 = C0245d.f2097a;
        byte[] bArr3 = new byte[((bArr.length + 2) / 3) * 4];
        int length = bArr.length - (bArr.length % 3);
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int i3 = i + 1;
            bArr3[i] = bArr2[(bArr[i2] & 255) >> 2];
            int i4 = i3 + 1;
            int i5 = i2 + 1;
            bArr3[i3] = bArr2[((bArr[i2] & 3) << 4) | ((bArr[i5] & 255) >> 4)];
            int i6 = i4 + 1;
            int i7 = (bArr[i5] & Ascii.SI) << 2;
            int i8 = i2 + 2;
            bArr3[i4] = bArr2[i7 | ((bArr[i8] & 255) >> 6)];
            i = i6 + 1;
            bArr3[i6] = bArr2[bArr[i8] & 63];
        }
        int length2 = bArr.length % 3;
        if (length2 == 1) {
            int i9 = i + 1;
            bArr3[i] = bArr2[(bArr[length] & 255) >> 2];
            int i10 = i9 + 1;
            bArr3[i9] = bArr2[(bArr[length] & 3) << 4];
            bArr3[i10] = 61;
            bArr3[i10 + 1] = 61;
        } else if (length2 == 2) {
            int i11 = i + 1;
            bArr3[i] = bArr2[(bArr[length] & 255) >> 2];
            int i12 = i11 + 1;
            int i13 = (bArr[length] & 3) << 4;
            int i14 = length + 1;
            bArr3[i11] = bArr2[((bArr[i14] & 255) >> 4) | i13];
            bArr3[i12] = bArr2[(bArr[i14] & Ascii.SI) << 2];
            bArr3[i12 + 1] = 61;
        }
        try {
            return new String(bArr3, "US-ASCII");
        } catch (UnsupportedEncodingException e2) {
            throw new AssertionError(e2);
        }
    }

    public static j a(String str) {
        if (str != null) {
            byte[] bArrA = C0245d.a(str);
            if (bArrA != null) {
                return new j(bArrA);
            }
            return null;
        }
        throw new IllegalArgumentException("base64 == null");
    }

    public static int a(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - '0';
        }
        char c3 = 'a';
        if (c2 < 'a' || c2 > 'f') {
            c3 = 'A';
            if (c2 < 'A' || c2 > 'F') {
                throw new IllegalArgumentException("Unexpected hex digit: " + c2);
            }
        }
        return (c2 - c3) + 10;
    }

    public j a(int i, int i2) {
        if (i >= 0) {
            byte[] bArr = this.f2111c;
            if (i2 > bArr.length) {
                throw new IllegalArgumentException(b.a.a.a.a.a(b.a.a.a.a.a("endIndex > length("), this.f2111c.length, ")"));
            }
            int i3 = i2 - i;
            if (i3 >= 0) {
                if (i == 0 && i2 == bArr.length) {
                    return this;
                }
                byte[] bArr2 = new byte[i3];
                System.arraycopy(this.f2111c, i, bArr2, 0, i3);
                return new j(bArr2);
            }
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        throw new IllegalArgumentException("beginIndex < 0");
    }

    public byte a(int i) {
        return this.f2111c[i];
    }

    public void a(g gVar) {
        byte[] bArr = this.f2111c;
        gVar.write(bArr, 0, bArr.length);
    }

    public boolean a(int i, j jVar, int i2, int i3) {
        return jVar.a(i2, this.f2111c, i, i3);
    }

    public boolean a(int i, byte[] bArr, int i2, int i3) {
        if (i >= 0) {
            byte[] bArr2 = this.f2111c;
            if (i <= bArr2.length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && F.a(bArr2, i, bArr, i2, i3)) {
                return true;
            }
        }
        return false;
    }

    public final boolean a(j jVar) {
        return a(0, jVar, 0, jVar.g());
    }
}
