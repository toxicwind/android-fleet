package b.d.a.a.b;

import com.google.common.base.Ascii;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* JADX INFO: loaded from: classes.dex */
public class i extends Reader {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f112a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public InputStream f113b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f114c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f115d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f116e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f117f;
    public final boolean j;
    public char g = 0;
    public int h = 0;
    public int i = 0;
    public char[] k = null;

    public i(c cVar, InputStream inputStream, byte[] bArr, int i, int i2, boolean z) {
        this.f112a = cVar;
        this.f113b = inputStream;
        this.f114c = bArr;
        this.f115d = i;
        this.f116e = i2;
        this.f117f = z;
        this.j = inputStream != null;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.f113b;
        if (inputStream != null) {
            this.f113b = null;
            p();
            inputStream.close();
        }
    }

    public final void p() {
        byte[] bArr = this.f114c;
        if (bArr != null) {
            this.f114c = null;
            this.f112a.a(bArr);
        }
    }

    public final void q() throws IOException {
        throw new IOException("Strange I/O stream, returned 0 bytes on read");
    }

    /* JADX WARN: Code duplicated, block: B:52:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:72:0x016d A[LOOP:0: B:54:0x00ab->B:72:0x016d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:82:0x0171 A[EDGE_INSN: B:82:0x0171->B:74:0x0171 BREAK  A[LOOP:0: B:54:0x00ab->B:72:0x016d], SYNTHETIC] */
    @Override // java.io.Reader
    public int read() throws IOException {
        int i;
        int i2;
        boolean z;
        int i3;
        int i4;
        int i5;
        int i6;
        if (this.k == null) {
            this.k = new char[1];
        }
        char[] cArr = this.k;
        byte[] bArr = this.f114c;
        if (bArr == null) {
            i = -1;
        } else {
            if (1 > cArr.length) {
                throw new ArrayIndexOutOfBoundsException(b.a.a.a.a.a(b.a.a.a.a.b("read(buf,", 0, ",", 1, "), cbuf["), cArr.length, "]"));
            }
            char c2 = this.g;
            if (c2 != 0) {
                cArr[0] = c2;
                this.g = (char) 0;
                i2 = 1;
            } else {
                int i7 = this.f116e;
                int i8 = this.f115d;
                int i9 = i7 - i8;
                if (i9 < 4) {
                    this.i = (i7 - i9) + this.i;
                    if (i9 > 0) {
                        if (i8 > 0) {
                            System.arraycopy(bArr, i8, bArr, 0, i9);
                            this.f115d = 0;
                        }
                        this.f116e = i9;
                    } else {
                        this.f115d = 0;
                        InputStream inputStream = this.f113b;
                        int i10 = inputStream == null ? -1 : inputStream.read(bArr);
                        if (i10 < 1) {
                            this.f116e = 0;
                            if (i10 >= 0) {
                                q();
                                throw null;
                            }
                            if (this.j) {
                                p();
                            }
                            z = false;
                        } else {
                            this.f116e = i10;
                        }
                        if (!z) {
                            i = -1;
                        }
                    }
                    while (true) {
                        int i11 = this.f116e;
                        if (i11 >= 4) {
                            z = true;
                            break;
                        }
                        InputStream inputStream2 = this.f113b;
                        if (inputStream2 == null) {
                            i3 = -1;
                        } else {
                            byte[] bArr2 = this.f114c;
                            i3 = inputStream2.read(bArr2, i11, bArr2.length - i11);
                        }
                        if (i3 < 1) {
                            if (i3 >= 0) {
                                q();
                                throw null;
                            }
                            if (this.j) {
                                p();
                            }
                            int i12 = this.f116e;
                            throw new CharConversionException(b.a.a.a.a.a(b.a.a.a.a.b("Unexpected EOF in the middle of a 4-byte UTF-32 char: got ", i12, ", needed ", 4, ", at char #"), this.h, ", byte #", this.i + i12, ")"));
                        }
                        this.f116e += i3;
                    }
                    if (!z) {
                        i = -1;
                    }
                }
                i2 = 0;
            }
            while (true) {
                if (i2 >= 1) {
                    i4 = i2;
                    break;
                }
                int i13 = this.f115d;
                if (this.f117f) {
                    byte[] bArr3 = this.f114c;
                    i5 = (bArr3[i13] << Ascii.CAN) | ((bArr3[i13 + 1] & 255) << 16) | ((bArr3[i13 + 2] & 255) << 8);
                    i6 = bArr3[i13 + 3] & 255;
                } else {
                    byte[] bArr4 = this.f114c;
                    i5 = (bArr4[i13] & 255) | ((bArr4[i13 + 1] & 255) << 8) | ((bArr4[i13 + 2] & 255) << 16);
                    i6 = bArr4[i13 + 3] << Ascii.CAN;
                }
                int i14 = i6 | i5;
                this.f115d += 4;
                if (i14 <= 65535) {
                    i4 = i2 + 1;
                    cArr[i2] = (char) i14;
                    if (this.f115d >= this.f116e) {
                        break;
                    }
                    i2 = i4;
                } else {
                    if (i14 > 1114111) {
                        StringBuilder sbA = b.a.a.a.a.a("(above ");
                        sbA.append(Integer.toHexString(1114111));
                        sbA.append(") ");
                        String string = sbA.toString();
                        int i15 = (this.i + this.f115d) - 1;
                        int i16 = this.h;
                        StringBuilder sbA2 = b.a.a.a.a.a("Invalid UTF-32 character 0x");
                        sbA2.append(Integer.toHexString(i14));
                        sbA2.append(string);
                        sbA2.append(" at char #");
                        sbA2.append(i16 + (i2 - 0));
                        sbA2.append(", byte #");
                        throw new CharConversionException(b.a.a.a.a.a(sbA2, i15, ")"));
                    }
                    int i17 = i14 - 65536;
                    i4 = i2 + 1;
                    cArr[i2] = (char) ((i17 >> 10) + 55296);
                    i14 = (i17 & 1023) | 56320;
                    if (i4 >= 1) {
                        this.g = (char) i14;
                        break;
                    }
                    i2 = i4;
                    i4 = i2 + 1;
                    cArr[i2] = (char) i14;
                    if (this.f115d >= this.f116e) {
                        break;
                        break;
                    }
                    i2 = i4;
                }
            }
            i = i4 + 0;
            this.h += i;
        }
        if (i < 1) {
            return -1;
        }
        return this.k[0];
    }

    /* JADX WARN: Code duplicated, block: B:52:0x00a3 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:72:0x0167 A[LOOP:0: B:54:0x00a5->B:72:0x0167, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:79:0x016b A[SYNTHETIC] */
    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        byte[] bArr = this.f114c;
        if (bArr == null) {
            return -1;
        }
        boolean z = true;
        if (i2 < 1) {
            return i2;
        }
        if (i >= 0 && (i3 = i + i2) <= cArr.length) {
            char c2 = this.g;
            if (c2 != 0) {
                i4 = i + 1;
                cArr[i] = c2;
                this.g = (char) 0;
            } else {
                int i9 = this.f116e;
                int i10 = this.f115d;
                int i11 = i9 - i10;
                if (i11 < 4) {
                    this.i = (i9 - i11) + this.i;
                    if (i11 > 0) {
                        if (i10 > 0) {
                            System.arraycopy(bArr, i10, bArr, 0, i11);
                            this.f115d = 0;
                        }
                        this.f116e = i11;
                    } else {
                        this.f115d = 0;
                        InputStream inputStream = this.f113b;
                        int i12 = inputStream == null ? -1 : inputStream.read(bArr);
                        if (i12 < 1) {
                            this.f116e = 0;
                            if (i12 < 0) {
                                if (this.j) {
                                    p();
                                }
                                z = false;
                            } else {
                                q();
                                throw null;
                            }
                        } else {
                            this.f116e = i12;
                        }
                        if (!z) {
                            return -1;
                        }
                    }
                    while (true) {
                        int i13 = this.f116e;
                        if (i13 >= 4) {
                            break;
                        }
                        InputStream inputStream2 = this.f113b;
                        if (inputStream2 == null) {
                            i5 = -1;
                        } else {
                            byte[] bArr2 = this.f114c;
                            i5 = inputStream2.read(bArr2, i13, bArr2.length - i13);
                        }
                        if (i5 < 1) {
                            if (i5 < 0) {
                                if (this.j) {
                                    p();
                                }
                                int i14 = this.f116e;
                                throw new CharConversionException(b.a.a.a.a.a(b.a.a.a.a.b("Unexpected EOF in the middle of a 4-byte UTF-32 char: got ", i14, ", needed ", 4, ", at char #"), this.h, ", byte #", this.i + i14, ")"));
                            }
                            q();
                            throw null;
                        }
                        this.f116e += i5;
                    }
                    if (!z) {
                        return -1;
                    }
                }
                i4 = i;
            }
            while (i4 < i3) {
                int i15 = this.f115d;
                if (this.f117f) {
                    byte[] bArr3 = this.f114c;
                    i7 = (bArr3[i15] << Ascii.CAN) | ((bArr3[i15 + 1] & 255) << 16) | ((bArr3[i15 + 2] & 255) << 8);
                    i8 = bArr3[i15 + 3] & 255;
                } else {
                    byte[] bArr4 = this.f114c;
                    i7 = (bArr4[i15] & 255) | ((bArr4[i15 + 1] & 255) << 8) | ((bArr4[i15 + 2] & 255) << 16);
                    i8 = bArr4[i15 + 3] << Ascii.CAN;
                }
                int i16 = i8 | i7;
                this.f115d += 4;
                if (i16 <= 65535) {
                    i6 = i4 + 1;
                    cArr[i4] = (char) i16;
                    if (this.f115d >= this.f116e) {
                        i4 = i6;
                    }
                } else if (i16 <= 1114111) {
                    int i17 = i16 - 65536;
                    i6 = i4 + 1;
                    cArr[i4] = (char) ((i17 >> 10) + 55296);
                    i16 = (i17 & 1023) | 56320;
                    if (i6 >= i3) {
                        this.g = (char) i16;
                    } else {
                        i4 = i6;
                        i6 = i4 + 1;
                        cArr[i4] = (char) i16;
                        if (this.f115d >= this.f116e) {
                            i4 = i6;
                        }
                    }
                } else {
                    int i18 = i4 - i;
                    StringBuilder sbA = b.a.a.a.a.a("(above ");
                    sbA.append(Integer.toHexString(1114111));
                    sbA.append(") ");
                    String string = sbA.toString();
                    int i19 = (this.i + this.f115d) - 1;
                    int i20 = this.h + i18;
                    StringBuilder sbA2 = b.a.a.a.a.a("Invalid UTF-32 character 0x");
                    sbA2.append(Integer.toHexString(i16));
                    sbA2.append(string);
                    sbA2.append(" at char #");
                    sbA2.append(i20);
                    sbA2.append(", byte #");
                    throw new CharConversionException(b.a.a.a.a.a(sbA2, i19, ")"));
                }
                int i21 = i6 - i;
                this.h += i21;
                return i21;
            }
            i6 = i4;
            int i22 = i6 - i;
            this.h += i22;
            return i22;
        }
        throw new ArrayIndexOutOfBoundsException(b.a.a.a.a.a(b.a.a.a.a.b("read(buf,", i, ",", i2, "), cbuf["), cArr.length, "]"));
    }
}
