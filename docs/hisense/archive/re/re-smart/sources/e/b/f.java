package e.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f2164a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f2165b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f2166c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f2167d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public char f2168e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Reader f2169f;
    public boolean g;

    public f(String str) {
        StringReader stringReader = new StringReader(str);
        this.f2169f = stringReader.markSupported() ? stringReader : new BufferedReader(stringReader);
        this.f2165b = false;
        this.g = false;
        this.f2168e = (char) 0;
        this.f2166c = 0L;
        this.f2164a = 1L;
        this.f2167d = 1L;
    }

    public void a() {
        if (!this.g) {
            long j = this.f2166c;
            if (j > 0) {
                this.f2166c = j - 1;
                this.f2164a--;
                this.g = true;
                this.f2165b = false;
                return;
            }
        }
        throw new b("Stepping back two steps is not supported");
    }

    public char b() {
        int i = 0;
        if (this.g) {
            this.g = false;
            i = this.f2168e;
        } else {
            try {
                int i2 = this.f2169f.read();
                if (i2 <= 0) {
                    this.f2165b = true;
                } else {
                    i = i2;
                }
            } catch (IOException e2) {
                throw new b(e2);
            }
        }
        this.f2166c++;
        if (this.f2168e == '\r') {
            this.f2167d++;
            this.f2164a = i == 10 ? 0L : 1L;
        } else if (i == 10) {
            this.f2167d++;
            this.f2164a = 0L;
        } else {
            this.f2164a++;
        }
        this.f2168e = (char) i;
        return this.f2168e;
    }

    public char c() {
        char cB;
        do {
            cB = b();
            if (cB == 0) {
                break;
            }
        } while (cB <= ' ');
        return cB;
    }

    public Object d() {
        char c2 = c();
        if (c2 != '\"' && c2 != '\'') {
            if (c2 == '[') {
                a();
                return new a(this);
            }
            if (c2 == '{') {
                a();
                return new d(this);
            }
            StringBuilder sb = new StringBuilder();
            while (c2 >= ' ' && ",:]}/\\\"[{;=#".indexOf(c2) < 0) {
                sb.append(c2);
                c2 = b();
            }
            a();
            String strTrim = sb.toString().trim();
            if ("".equals(strTrim)) {
                throw a("Missing value");
            }
            return d.o(strTrim);
        }
        StringBuilder sb2 = new StringBuilder();
        while (true) {
            char cB = b();
            if (cB == 0 || cB == '\n' || cB == '\r') {
                break;
            }
            if (cB == '\\') {
                char cB2 = b();
                if (cB2 == '\"' || cB2 == '\'' || cB2 == '/' || cB2 == '\\') {
                    sb2.append(cB2);
                } else if (cB2 == 'b') {
                    sb2.append('\b');
                } else if (cB2 == 'f') {
                    sb2.append('\f');
                } else if (cB2 == 'n') {
                    sb2.append('\n');
                } else if (cB2 == 'r') {
                    sb2.append('\r');
                } else if (cB2 == 't') {
                    sb2.append('\t');
                } else {
                    if (cB2 != 'u') {
                        throw a("Illegal escape.");
                    }
                    try {
                        char[] cArr = new char[4];
                        for (int i = 0; i < 4; i++) {
                            cArr[i] = b();
                            if (this.f2165b && !this.g) {
                                throw a("Substring bounds error");
                            }
                        }
                        sb2.append((char) Integer.parseInt(new String(cArr), 16));
                    } catch (NumberFormatException e2) {
                        StringBuilder sbA = b.a.a.a.a.a("Illegal escape.");
                        sbA.append(toString());
                        throw new b(sbA.toString(), e2);
                    }
                }
            } else {
                if (cB == c2) {
                    return sb2.toString();
                }
                sb2.append(cB);
            }
        }
        throw a("Unterminated string");
    }

    public String toString() {
        StringBuilder sbA = b.a.a.a.a.a(" at ");
        sbA.append(this.f2166c);
        sbA.append(" [character ");
        sbA.append(this.f2164a);
        sbA.append(" line ");
        sbA.append(this.f2167d);
        sbA.append("]");
        return sbA.toString();
    }

    public b a(String str) {
        StringBuilder sbA = b.a.a.a.a.a(str);
        sbA.append(toString());
        return new b(sbA.toString());
    }
}
