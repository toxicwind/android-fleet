package b.d.a.a.c;

import b.d.a.a.k;
import com.google.common.base.Ascii;
import java.io.ByteArrayInputStream;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.d.a.a.b.c f124a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InputStream f125b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[] f126c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f127d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f128e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f129f;
    public boolean g;
    public int h;

    public a(b.d.a.a.b.c cVar, InputStream inputStream) {
        this.g = true;
        this.h = 0;
        this.f124a = cVar;
        this.f125b = inputStream;
        cVar.a((Object) cVar.f92e);
        byte[] bArrA = cVar.f91d.a(0);
        cVar.f92e = bArrA;
        this.f126c = bArrA;
        this.f127d = 0;
        this.f128e = 0;
        this.f129f = true;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0083  */
    /* JADX WARN: Code duplicated, block: B:30:0x0087  */
    /* JADX WARN: Code duplicated, block: B:31:0x008a  */
    /* JADX WARN: Code duplicated, block: B:33:0x0090  */
    /* JADX WARN: Code duplicated, block: B:35:0x0096  */
    /* JADX WARN: Code duplicated, block: B:37:0x009c  */
    /* JADX WARN: Code duplicated, block: B:39:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:42:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:45:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:47:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:55:0x00d7  */
    public b.d.a.a.g a(int i, k kVar, b.d.a.a.d.a aVar, b.d.a.a.d.b bVar, int i2) throws CharConversionException {
        int i3;
        b.d.a.a.a aVar2;
        Reader inputStreamReader;
        boolean z;
        boolean z2;
        boolean z3 = false;
        if (b(4)) {
            byte[] bArr = this.f126c;
            int i4 = this.f127d;
            int i5 = i4 + 2;
            int i6 = i4 + 3;
            int i7 = (bArr[i6] & 255) | (bArr[i4] << Ascii.CAN) | ((bArr[i4 + 1] & 255) << 16) | ((bArr[i5] & 255) << 8);
            if (i7 == -16842752) {
                a("3412");
                throw null;
            }
            if (i7 == -131072) {
                this.f127d = i4 + 4;
                this.h = 4;
                this.g = false;
            } else if (i7 == 65279) {
                this.g = true;
                this.f127d = i4 + 4;
                this.h = 4;
            } else {
                if (i7 == 65534) {
                    a("2143");
                    throw null;
                }
                int i8 = i7 >>> 16;
                if (i8 == 65279) {
                    this.f127d = i5;
                    this.h = 2;
                    this.g = true;
                } else if (i8 == 65534) {
                    this.f127d = i5;
                    this.h = 2;
                    this.g = false;
                } else {
                    if ((i7 >>> 8) == 15711167) {
                        this.f127d = i6;
                        this.h = 1;
                        this.g = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        z3 = true;
                    } else {
                        if ((i7 >> 8) == 0) {
                            this.g = true;
                        } else {
                            if ((16777215 & i7) == 0) {
                                this.g = false;
                            } else {
                                if (((-16711681) & i7) != 0) {
                                    a("3412");
                                    throw null;
                                }
                                if (((-65281) & i7) != 0) {
                                    a("2143");
                                    throw null;
                                }
                                z2 = false;
                            }
                            if (z2 || a(i7 >>> 16)) {
                                z3 = true;
                            }
                        }
                        this.h = 4;
                        z2 = true;
                        if (z2) {
                            z3 = true;
                        } else {
                            z3 = true;
                        }
                    }
                }
            }
            z = true;
            if (z) {
                if ((i7 >> 8) == 0) {
                    this.g = true;
                } else {
                    if ((16777215 & i7) == 0) {
                        this.g = false;
                    } else {
                        if (((-16711681) & i7) != 0) {
                            a("3412");
                            throw null;
                        }
                        if (((-65281) & i7) != 0) {
                            a("2143");
                            throw null;
                        }
                        z2 = false;
                    }
                    if (z2) {
                        z3 = true;
                    } else {
                        z3 = true;
                    }
                }
                this.h = 4;
                z2 = true;
                if (z2) {
                    z3 = true;
                } else {
                    z3 = true;
                }
            } else {
                z3 = true;
            }
        } else if (b(2)) {
            byte[] bArr2 = this.f126c;
            int i9 = this.f127d;
            if (a((bArr2[i9 + 1] & 255) | ((bArr2[i9] & 255) << 8))) {
                z3 = true;
            }
        }
        if (!z3 || (i3 = this.h) == 1) {
            aVar2 = b.d.a.a.a.UTF8;
        } else if (i3 == 2) {
            aVar2 = this.g ? b.d.a.a.a.UTF16_BE : b.d.a.a.a.UTF16_LE;
        } else {
            if (i3 != 4) {
                throw new RuntimeException("Internal error");
            }
            aVar2 = this.g ? b.d.a.a.a.UTF32_BE : b.d.a.a.a.UTF32_LE;
        }
        this.f124a.a(aVar2);
        if (aVar2 == b.d.a.a.a.UTF8 && b.d.a.a.b.a.CANONICALIZE_FIELD_NAMES.a(i2)) {
            return new h(this.f124a, i, this.f125b, aVar.c(i2), this.f126c, this.f127d, this.f128e, this.f129f);
        }
        b.d.a.a.b.c cVar = this.f124a;
        b.d.a.a.a aVarC = cVar.c();
        int i10 = aVarC.i;
        if (i10 == 8 || i10 == 16) {
            InputStream eVar = this.f125b;
            if (eVar == null) {
                eVar = new ByteArrayInputStream(this.f126c, this.f127d, this.f128e);
            } else {
                int i11 = this.f127d;
                int i12 = this.f128e;
                if (i11 < i12) {
                    eVar = new b.d.a.a.b.e(this.f124a, eVar, this.f126c, i11, i12);
                }
            }
            inputStreamReader = new InputStreamReader(eVar, aVarC.a());
        } else {
            if (i10 != 32) {
                throw new RuntimeException("Internal error");
            }
            b.d.a.a.b.c cVar2 = this.f124a;
            inputStreamReader = new b.d.a.a.b.i(cVar2, this.f125b, this.f126c, this.f127d, this.f128e, cVar2.c().h);
        }
        return new f(cVar, i, inputStreamReader, bVar.c(i2));
    }

    public boolean b(int i) throws IOException {
        int i2;
        int i3 = this.f128e - this.f127d;
        while (i3 < i) {
            InputStream inputStream = this.f125b;
            if (inputStream == null) {
                i2 = -1;
            } else {
                byte[] bArr = this.f126c;
                int i4 = this.f128e;
                i2 = inputStream.read(bArr, i4, bArr.length - i4);
            }
            if (i2 < 1) {
                return false;
            }
            this.f128e += i2;
            i3 += i2;
        }
        return true;
    }

    public a(b.d.a.a.b.c cVar, byte[] bArr, int i, int i2) {
        this.g = true;
        this.h = 0;
        this.f124a = cVar;
        this.f125b = null;
        this.f126c = bArr;
        this.f127d = i;
        this.f128e = i + i2;
        this.f129f = false;
    }

    public final boolean a(int i) {
        if ((65280 & i) == 0) {
            this.g = true;
        } else {
            if ((i & 255) != 0) {
                return false;
            }
            this.g = false;
        }
        this.h = 2;
        return true;
    }

    public final void a(String str) throws CharConversionException {
        throw new CharConversionException(b.a.a.a.a.a("Unsupported UCS-4 endianness (", str, ") detected"));
    }
}
