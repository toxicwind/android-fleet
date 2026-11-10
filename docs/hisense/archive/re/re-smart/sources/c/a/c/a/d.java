package c.a.c.a;

import javax.security.auth.x500.X500Principal;

/* JADX INFO: loaded from: classes2.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1633a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1634b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1635c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1636d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1637e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1638f;
    public char[] g;

    public d(X500Principal x500Principal) {
        this.f1633a = x500Principal.getName("RFC2253");
        this.f1634b = this.f1633a.length();
    }

    public final char a() {
        int i;
        this.f1635c++;
        int i2 = this.f1635c;
        if (i2 == this.f1634b) {
            StringBuilder sbA = b.a.a.a.a.a("Unexpected end of DN: ");
            sbA.append(this.f1633a);
            throw new IllegalStateException(sbA.toString());
        }
        char c2 = this.g[i2];
        if (c2 != ' ' && c2 != '%' && c2 != '\\' && c2 != '_' && c2 != '\"' && c2 != '#') {
            switch (c2) {
                case '*':
                case '+':
                case ',':
                    break;
                default:
                    switch (c2) {
                        case ';':
                        case '<':
                        case '=':
                        case '>':
                            break;
                        default:
                            int iA = a(i2);
                            this.f1635c++;
                            if (iA >= 128) {
                                if (iA < 192 || iA > 247) {
                                    return '?';
                                }
                                if (iA <= 223) {
                                    iA &= 31;
                                    i = 1;
                                } else if (iA <= 239) {
                                    i = 2;
                                    iA &= 15;
                                } else {
                                    i = 3;
                                    iA &= 7;
                                }
                                for (int i3 = 0; i3 < i; i3++) {
                                    this.f1635c++;
                                    int i4 = this.f1635c;
                                    if (i4 == this.f1634b || this.g[i4] != '\\') {
                                        return '?';
                                    }
                                    this.f1635c = i4 + 1;
                                    int iA2 = a(this.f1635c);
                                    this.f1635c++;
                                    if ((iA2 & 192) != 128) {
                                        return '?';
                                    }
                                    iA = (iA << 6) + (iA2 & 63);
                                }
                            }
                            return (char) iA;
                    }
                    break;
            }
        }
        return this.g[this.f1635c];
    }

    public final String b() {
        while (true) {
            int i = this.f1635c;
            if (i >= this.f1634b || this.g[i] != ' ') {
                break;
            }
            this.f1635c = i + 1;
        }
        int i2 = this.f1635c;
        if (i2 == this.f1634b) {
            return null;
        }
        this.f1636d = i2;
        this.f1635c = i2 + 1;
        while (true) {
            int i3 = this.f1635c;
            if (i3 >= this.f1634b) {
                break;
            }
            char[] cArr = this.g;
            if (cArr[i3] == '=' || cArr[i3] == ' ') {
                break;
            }
            this.f1635c = i3 + 1;
        }
        int i4 = this.f1635c;
        if (i4 >= this.f1634b) {
            StringBuilder sbA = b.a.a.a.a.a("Unexpected end of DN: ");
            sbA.append(this.f1633a);
            throw new IllegalStateException(sbA.toString());
        }
        this.f1637e = i4;
        if (this.g[i4] == ' ') {
            while (true) {
                int i5 = this.f1635c;
                if (i5 >= this.f1634b) {
                    break;
                }
                char[] cArr2 = this.g;
                if (cArr2[i5] == '=' || cArr2[i5] != ' ') {
                    break;
                }
                this.f1635c = i5 + 1;
            }
            char[] cArr3 = this.g;
            int i6 = this.f1635c;
            if (cArr3[i6] != '=' || i6 == this.f1634b) {
                StringBuilder sbA2 = b.a.a.a.a.a("Unexpected end of DN: ");
                sbA2.append(this.f1633a);
                throw new IllegalStateException(sbA2.toString());
            }
        }
        this.f1635c++;
        while (true) {
            int i7 = this.f1635c;
            if (i7 >= this.f1634b || this.g[i7] != ' ') {
                break;
            }
            this.f1635c = i7 + 1;
        }
        int i8 = this.f1637e;
        int i9 = this.f1636d;
        if (i8 - i9 > 4) {
            char[] cArr4 = this.g;
            if (cArr4[i9 + 3] == '.' && (cArr4[i9] == 'O' || cArr4[i9] == 'o')) {
                char[] cArr5 = this.g;
                int i10 = this.f1636d;
                if (cArr5[i10 + 1] == 'I' || cArr5[i10 + 1] == 'i') {
                    char[] cArr6 = this.g;
                    int i11 = this.f1636d;
                    if (cArr6[i11 + 2] == 'D' || cArr6[i11 + 2] == 'd') {
                        this.f1636d += 4;
                    }
                }
            }
        }
        char[] cArr7 = this.g;
        int i12 = this.f1636d;
        return new String(cArr7, i12, this.f1637e - i12);
    }

    public final int a(int i) {
        int i2;
        int i3;
        int i4 = i + 1;
        if (i4 < this.f1634b) {
            char c2 = this.g[i];
            if (c2 >= '0' && c2 <= '9') {
                i2 = c2 - '0';
            } else if (c2 >= 'a' && c2 <= 'f') {
                i2 = c2 - 'W';
            } else {
                if (c2 < 'A' || c2 > 'F') {
                    StringBuilder sbA = b.a.a.a.a.a("Malformed DN: ");
                    sbA.append(this.f1633a);
                    throw new IllegalStateException(sbA.toString());
                }
                i2 = c2 - '7';
            }
            char c3 = this.g[i4];
            if (c3 >= '0' && c3 <= '9') {
                i3 = c3 - '0';
            } else if (c3 >= 'a' && c3 <= 'f') {
                i3 = c3 - 'W';
            } else {
                if (c3 < 'A' || c3 > 'F') {
                    StringBuilder sbA2 = b.a.a.a.a.a("Malformed DN: ");
                    sbA2.append(this.f1633a);
                    throw new IllegalStateException(sbA2.toString());
                }
                i3 = c3 - '7';
            }
            return (i2 << 4) + i3;
        }
        StringBuilder sbA3 = b.a.a.a.a.a("Malformed DN: ");
        sbA3.append(this.f1633a);
        throw new IllegalStateException(sbA3.toString());
    }
}
