package b.d.a.a.b;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/* JADX INFO: loaded from: classes.dex */
public final class j extends Writer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f118a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public OutputStream f119b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f120c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f121d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f122e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f123f = 0;

    public j(c cVar, OutputStream outputStream) {
        this.f118a = cVar;
        this.f119b = outputStream;
        cVar.a((Object) cVar.f93f);
        byte[] bArrA = cVar.f91d.a(1);
        cVar.f93f = bArrA;
        this.f120c = bArrA;
        this.f121d = this.f120c.length - 4;
        this.f122e = 0;
    }

    public static void c(int i) throws IOException {
        throw new IOException(d(i));
    }

    public static String d(int i) {
        if (i > 1114111) {
            StringBuilder sbA = b.a.a.a.a.a("Illegal character point (0x");
            sbA.append(Integer.toHexString(i));
            sbA.append(") to output; max is 0x10FFFF as per RFC 4627");
            return sbA.toString();
        }
        if (i < 55296) {
            StringBuilder sbA2 = b.a.a.a.a.a("Illegal character point (0x");
            sbA2.append(Integer.toHexString(i));
            sbA2.append(") to output");
            return sbA2.toString();
        }
        if (i <= 56319) {
            StringBuilder sbA3 = b.a.a.a.a.a("Unmatched first part of surrogate pair (0x");
            sbA3.append(Integer.toHexString(i));
            sbA3.append(")");
            return sbA3.toString();
        }
        StringBuilder sbA4 = b.a.a.a.a.a("Unmatched second part of surrogate pair (0x");
        sbA4.append(Integer.toHexString(i));
        sbA4.append(")");
        return sbA4.toString();
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(char c2) throws IOException {
        write(c2);
        return this;
    }

    public int b(int i) throws IOException {
        int i2 = this.f123f;
        this.f123f = 0;
        if (i >= 56320 && i <= 57343) {
            return (i - 56320) + ((i2 - 55296) << 10) + 65536;
        }
        StringBuilder sbA = b.a.a.a.a.a("Broken surrogate pair: first char 0x");
        sbA.append(Integer.toHexString(i2));
        sbA.append(", second 0x");
        sbA.append(Integer.toHexString(i));
        sbA.append("; illegal combination");
        throw new IOException(sbA.toString());
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        OutputStream outputStream = this.f119b;
        if (outputStream != null) {
            int i = this.f122e;
            if (i > 0) {
                outputStream.write(this.f120c, 0, i);
                this.f122e = 0;
            }
            OutputStream outputStream2 = this.f119b;
            this.f119b = null;
            byte[] bArr = this.f120c;
            if (bArr != null) {
                this.f120c = null;
                this.f118a.b(bArr);
            }
            outputStream2.close();
            int i2 = this.f123f;
            this.f123f = 0;
            if (i2 <= 0) {
                return;
            }
            c(i2);
            throw null;
        }
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        OutputStream outputStream = this.f119b;
        if (outputStream != null) {
            int i = this.f122e;
            if (i > 0) {
                outputStream.write(this.f120c, 0, i);
                this.f122e = 0;
            }
            this.f119b.flush();
        }
    }

    @Override // java.io.Writer
    public void write(char[] cArr) throws IOException {
        int length = cArr.length;
        int i = 1;
        if (length < 2) {
            if (length == 1) {
                write(cArr[0]);
                return;
            }
            return;
        }
        if (this.f123f > 0) {
            length--;
            write(b(cArr[0]));
        } else {
            i = 0;
        }
        int i2 = this.f122e;
        byte[] bArr = this.f120c;
        int i3 = this.f121d;
        int i4 = length + i;
        while (i < i4) {
            if (i2 >= i3) {
                this.f119b.write(bArr, 0, i2);
                i2 = 0;
            }
            int i5 = i + 1;
            char c2 = cArr[i];
            if (c2 < 128) {
                int i6 = i2 + 1;
                bArr[i2] = (byte) c2;
                int i7 = i4 - i5;
                int i8 = i3 - i6;
                if (i7 > i8) {
                    i7 = i8;
                }
                int i9 = i7 + i5;
                while (true) {
                    i = i5;
                    i2 = i6;
                    if (i >= i9) {
                        continue;
                    } else {
                        i5 = i + 1;
                        c2 = cArr[i];
                        if (c2 < 128) {
                            i6 = i2 + 1;
                            bArr[i2] = (byte) c2;
                        }
                    }
                }
            }
            if (c2 < 2048) {
                int i10 = i2 + 1;
                bArr[i2] = (byte) ((c2 >> 6) | 192);
                i2 = i10 + 1;
                bArr[i10] = (byte) ((c2 & '?') | 128);
                i = i5;
            } else if (c2 < 55296 || c2 > 57343) {
                int i11 = i2 + 1;
                bArr[i2] = (byte) ((c2 >> '\f') | 224);
                int i12 = i11 + 1;
                bArr[i11] = (byte) (((c2 >> 6) & 63) | 128);
                bArr[i12] = (byte) ((c2 & '?') | 128);
                i = i5;
                i2 = i12 + 1;
            } else {
                if (c2 > 56319) {
                    this.f122e = i2;
                    c(c2);
                    throw null;
                }
                this.f123f = c2;
                if (i5 >= i4) {
                    break;
                }
                i = i5 + 1;
                int iB = b(cArr[i5]);
                if (iB > 1114111) {
                    this.f122e = i2;
                    c(iB);
                    throw null;
                }
                int i13 = i2 + 1;
                bArr[i2] = (byte) ((iB >> 18) | 240);
                int i14 = i13 + 1;
                bArr[i13] = (byte) (((iB >> 12) & 63) | 128);
                int i15 = i14 + 1;
                bArr[i14] = (byte) (((iB >> 6) & 63) | 128);
                i2 = i15 + 1;
                bArr[i15] = (byte) ((iB & 63) | 128);
            }
        }
        this.f122e = i2;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Appendable append(char c2) throws IOException {
        write(c2);
        return this;
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) throws IOException {
        if (i2 < 2) {
            if (i2 == 1) {
                write(cArr[i]);
                return;
            }
            return;
        }
        if (this.f123f > 0) {
            i2--;
            write(b(cArr[i]));
            i++;
        }
        int i3 = this.f122e;
        byte[] bArr = this.f120c;
        int i4 = this.f121d;
        int i5 = i2 + i;
        while (i < i5) {
            if (i3 >= i4) {
                this.f119b.write(bArr, 0, i3);
                i3 = 0;
            }
            int i6 = i + 1;
            char c2 = cArr[i];
            if (c2 < 128) {
                int i7 = i3 + 1;
                bArr[i3] = (byte) c2;
                int i8 = i5 - i6;
                int i9 = i4 - i7;
                if (i8 > i9) {
                    i8 = i9;
                }
                int i10 = i8 + i6;
                while (true) {
                    i = i6;
                    i3 = i7;
                    if (i >= i10) {
                        continue;
                    } else {
                        i6 = i + 1;
                        c2 = cArr[i];
                        if (c2 < 128) {
                            i7 = i3 + 1;
                            bArr[i3] = (byte) c2;
                        }
                    }
                }
            }
            if (c2 < 2048) {
                int i11 = i3 + 1;
                bArr[i3] = (byte) ((c2 >> 6) | 192);
                i3 = i11 + 1;
                bArr[i11] = (byte) ((c2 & '?') | 128);
                i = i6;
            } else if (c2 < 55296 || c2 > 57343) {
                int i12 = i3 + 1;
                bArr[i3] = (byte) ((c2 >> '\f') | 224);
                int i13 = i12 + 1;
                bArr[i12] = (byte) (((c2 >> 6) & 63) | 128);
                bArr[i13] = (byte) ((c2 & '?') | 128);
                i = i6;
                i3 = i13 + 1;
            } else if (c2 <= 56319) {
                this.f123f = c2;
                if (i6 >= i5) {
                    break;
                }
                i = i6 + 1;
                int iB = b(cArr[i6]);
                if (iB <= 1114111) {
                    int i14 = i3 + 1;
                    bArr[i3] = (byte) ((iB >> 18) | 240);
                    int i15 = i14 + 1;
                    bArr[i14] = (byte) (((iB >> 12) & 63) | 128);
                    int i16 = i15 + 1;
                    bArr[i15] = (byte) (((iB >> 6) & 63) | 128);
                    i3 = i16 + 1;
                    bArr[i16] = (byte) ((iB & 63) | 128);
                } else {
                    this.f122e = i3;
                    c(iB);
                    throw null;
                }
            } else {
                this.f122e = i3;
                c(c2);
                throw null;
            }
        }
        this.f122e = i3;
    }

    @Override // java.io.Writer
    public void write(int i) throws IOException {
        int i2;
        if (this.f123f > 0) {
            i = b(i);
        } else if (i >= 55296 && i <= 57343) {
            if (i <= 56319) {
                this.f123f = i;
                return;
            } else {
                c(i);
                throw null;
            }
        }
        int i3 = this.f122e;
        if (i3 >= this.f121d) {
            this.f119b.write(this.f120c, 0, i3);
            this.f122e = 0;
        }
        if (i < 128) {
            byte[] bArr = this.f120c;
            int i4 = this.f122e;
            this.f122e = i4 + 1;
            bArr[i4] = (byte) i;
            return;
        }
        int i5 = this.f122e;
        if (i < 2048) {
            byte[] bArr2 = this.f120c;
            int i6 = i5 + 1;
            bArr2[i5] = (byte) ((i >> 6) | 192);
            i2 = i6 + 1;
            bArr2[i6] = (byte) ((i & 63) | 128);
        } else if (i <= 65535) {
            byte[] bArr3 = this.f120c;
            int i7 = i5 + 1;
            bArr3[i5] = (byte) ((i >> 12) | 224);
            int i8 = i7 + 1;
            bArr3[i7] = (byte) (((i >> 6) & 63) | 128);
            bArr3[i8] = (byte) ((i & 63) | 128);
            i2 = i8 + 1;
        } else if (i <= 1114111) {
            byte[] bArr4 = this.f120c;
            int i9 = i5 + 1;
            bArr4[i5] = (byte) ((i >> 18) | 240);
            int i10 = i9 + 1;
            bArr4[i9] = (byte) (((i >> 12) & 63) | 128);
            int i11 = i10 + 1;
            bArr4[i10] = (byte) (((i >> 6) & 63) | 128);
            i2 = i11 + 1;
            bArr4[i11] = (byte) ((i & 63) | 128);
        } else {
            c(i);
            throw null;
        }
        this.f122e = i2;
    }

    @Override // java.io.Writer
    public void write(String str) throws IOException {
        int length = str.length();
        int i = 1;
        if (length < 2) {
            if (length == 1) {
                write(str.charAt(0));
                return;
            }
            return;
        }
        if (this.f123f > 0) {
            length--;
            write(b(str.charAt(0)));
        } else {
            i = 0;
        }
        int i2 = this.f122e;
        byte[] bArr = this.f120c;
        int i3 = this.f121d;
        int i4 = length + i;
        while (i < i4) {
            if (i2 >= i3) {
                this.f119b.write(bArr, 0, i2);
                i2 = 0;
            }
            int i5 = i + 1;
            char cCharAt = str.charAt(i);
            if (cCharAt < 128) {
                int i6 = i2 + 1;
                bArr[i2] = (byte) cCharAt;
                int i7 = i4 - i5;
                int i8 = i3 - i6;
                if (i7 > i8) {
                    i7 = i8;
                }
                int i9 = i7 + i5;
                while (true) {
                    i = i5;
                    i2 = i6;
                    if (i >= i9) {
                        continue;
                    } else {
                        i5 = i + 1;
                        cCharAt = str.charAt(i);
                        if (cCharAt < 128) {
                            i6 = i2 + 1;
                            bArr[i2] = (byte) cCharAt;
                        }
                    }
                }
            }
            if (cCharAt < 2048) {
                int i10 = i2 + 1;
                bArr[i2] = (byte) ((cCharAt >> 6) | 192);
                i2 = i10 + 1;
                bArr[i10] = (byte) ((cCharAt & '?') | 128);
                i = i5;
            } else if (cCharAt < 55296 || cCharAt > 57343) {
                int i11 = i2 + 1;
                bArr[i2] = (byte) ((cCharAt >> '\f') | 224);
                int i12 = i11 + 1;
                bArr[i11] = (byte) (((cCharAt >> 6) & 63) | 128);
                bArr[i12] = (byte) ((cCharAt & '?') | 128);
                i = i5;
                i2 = i12 + 1;
            } else if (cCharAt <= 56319) {
                this.f123f = cCharAt;
                if (i5 >= i4) {
                    break;
                }
                i = i5 + 1;
                int iB = b(str.charAt(i5));
                if (iB <= 1114111) {
                    int i13 = i2 + 1;
                    bArr[i2] = (byte) ((iB >> 18) | 240);
                    int i14 = i13 + 1;
                    bArr[i13] = (byte) (((iB >> 12) & 63) | 128);
                    int i15 = i14 + 1;
                    bArr[i14] = (byte) (((iB >> 6) & 63) | 128);
                    i2 = i15 + 1;
                    bArr[i15] = (byte) ((iB & 63) | 128);
                } else {
                    this.f122e = i2;
                    c(iB);
                    throw null;
                }
            } else {
                this.f122e = i2;
                c(cCharAt);
                throw null;
            }
        }
        this.f122e = i2;
    }

    @Override // java.io.Writer
    public void write(String str, int i, int i2) throws IOException {
        if (i2 < 2) {
            if (i2 == 1) {
                write(str.charAt(i));
                return;
            }
            return;
        }
        if (this.f123f > 0) {
            i2--;
            write(b(str.charAt(i)));
            i++;
        }
        int i3 = this.f122e;
        byte[] bArr = this.f120c;
        int i4 = this.f121d;
        int i5 = i2 + i;
        while (i < i5) {
            if (i3 >= i4) {
                this.f119b.write(bArr, 0, i3);
                i3 = 0;
            }
            int i6 = i + 1;
            char cCharAt = str.charAt(i);
            if (cCharAt < 128) {
                int i7 = i3 + 1;
                bArr[i3] = (byte) cCharAt;
                int i8 = i5 - i6;
                int i9 = i4 - i7;
                if (i8 > i9) {
                    i8 = i9;
                }
                int i10 = i8 + i6;
                while (true) {
                    i = i6;
                    i3 = i7;
                    if (i >= i10) {
                        continue;
                    } else {
                        i6 = i + 1;
                        cCharAt = str.charAt(i);
                        if (cCharAt < 128) {
                            i7 = i3 + 1;
                            bArr[i3] = (byte) cCharAt;
                        }
                    }
                }
            }
            if (cCharAt < 2048) {
                int i11 = i3 + 1;
                bArr[i3] = (byte) ((cCharAt >> 6) | 192);
                i3 = i11 + 1;
                bArr[i11] = (byte) ((cCharAt & '?') | 128);
                i = i6;
            } else if (cCharAt < 55296 || cCharAt > 57343) {
                int i12 = i3 + 1;
                bArr[i3] = (byte) ((cCharAt >> '\f') | 224);
                int i13 = i12 + 1;
                bArr[i12] = (byte) (((cCharAt >> 6) & 63) | 128);
                bArr[i13] = (byte) ((cCharAt & '?') | 128);
                i = i6;
                i3 = i13 + 1;
            } else if (cCharAt <= 56319) {
                this.f123f = cCharAt;
                if (i6 >= i5) {
                    break;
                }
                i = i6 + 1;
                int iB = b(str.charAt(i6));
                if (iB <= 1114111) {
                    int i14 = i3 + 1;
                    bArr[i3] = (byte) ((iB >> 18) | 240);
                    int i15 = i14 + 1;
                    bArr[i14] = (byte) (((iB >> 12) & 63) | 128);
                    int i16 = i15 + 1;
                    bArr[i15] = (byte) (((iB >> 6) & 63) | 128);
                    i3 = i16 + 1;
                    bArr[i16] = (byte) ((iB & 63) | 128);
                } else {
                    this.f122e = i3;
                    c(iB);
                    throw null;
                }
            } else {
                this.f122e = i3;
                c(cCharAt);
                throw null;
            }
        }
        this.f122e = i3;
    }
}
