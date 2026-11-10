package b.g.a.a.a;

import b.g.a.e;
import com.google.common.base.Ascii;
import java.net.ProtocolException;

/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f744a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f745b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f746c;

    public a(e eVar, int i, String str) {
        this.f744a = eVar;
        this.f745b = i;
        this.f746c = str;
    }

    public static a a(String str) throws ProtocolException {
        e eVar;
        String strSubstring;
        int i = 9;
        if (str.startsWith("HTTP/1.")) {
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException(b.a.a.a.a.a("Unexpected status line: ", str));
            }
            int iCharAt = str.charAt(7) - '0';
            if (iCharAt == 0) {
                eVar = e.HTTP_1_0;
            } else {
                if (iCharAt != 1) {
                    throw new ProtocolException(b.a.a.a.a.a("Unexpected status line: ", str));
                }
                eVar = e.HTTP_1_1;
            }
        } else {
            if (!str.startsWith("ICY ")) {
                throw new ProtocolException(b.a.a.a.a.a("Unexpected status line: ", str));
            }
            eVar = e.HTTP_1_0;
            i = 4;
        }
        int i2 = i + 3;
        if (str.length() < i2) {
            throw new ProtocolException(b.a.a.a.a.a("Unexpected status line: ", str));
        }
        try {
            int i3 = Integer.parseInt(str.substring(i, i2));
            if (str.length() <= i2) {
                strSubstring = "";
            } else {
                if (str.charAt(i2) != ' ') {
                    throw new ProtocolException(b.a.a.a.a.a("Unexpected status line: ", str));
                }
                strSubstring = str.substring(i + 4);
            }
            return new a(eVar, i3, strSubstring);
        } catch (NumberFormatException unused) {
            throw new ProtocolException(b.a.a.a.a.a("Unexpected status line: ", str));
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f744a == e.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1");
        sb.append(Ascii.CASE_MASK);
        sb.append(this.f745b);
        if (this.f746c != null) {
            sb.append(Ascii.CASE_MASK);
            sb.append(this.f746c);
        }
        return sb.toString();
    }
}
