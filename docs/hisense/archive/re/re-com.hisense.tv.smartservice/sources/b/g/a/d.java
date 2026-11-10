package b.g.a;

import com.google.common.net.InetAddresses;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f750a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f751b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f752c;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f753a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f756d;
        public List<String> g;
        public String h;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f754b = "";

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f755c = "";

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f757e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final List<String> f758f = new ArrayList();

        /* JADX INFO: renamed from: b.g.a.d$a$a, reason: collision with other inner class name */
        enum EnumC0005a {
            SUCCESS,
            MISSING_SCHEME,
            UNSUPPORTED_SCHEME,
            INVALID_PORT,
            INVALID_HOST
        }

        public a() {
            this.f758f.add("");
        }

        public int a() {
            int i = this.f757e;
            return i != -1 ? i : d.a(this.f753a);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f753a);
            sb.append("://");
            if (!this.f754b.isEmpty() || !this.f755c.isEmpty()) {
                sb.append(this.f754b);
                if (!this.f755c.isEmpty()) {
                    sb.append(InetAddresses.IPV6_DELIMITER);
                    sb.append(this.f755c);
                }
                sb.append('@');
            }
            if (this.f756d.indexOf(58) != -1) {
                sb.append('[');
                sb.append(this.f756d);
                sb.append(']');
            } else {
                sb.append(this.f756d);
            }
            int iA = this.f757e;
            if (iA == -1) {
                iA = d.a(this.f753a);
            }
            if (iA != d.a(this.f753a)) {
                sb.append(InetAddresses.IPV6_DELIMITER);
                sb.append(iA);
            }
            d.b(sb, this.f758f);
            if (this.g != null) {
                sb.append('?');
                d.a(sb, this.g);
            }
            if (this.h != null) {
                sb.append('#');
                sb.append(this.h);
            }
            return sb.toString();
        }
    }

    static {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    public /* synthetic */ d(a aVar, c cVar) {
        String str = aVar.f753a;
        a(aVar.f754b, false);
        a(aVar.f755c, false);
        this.f750a = aVar.f756d;
        this.f751b = aVar.a();
        a(aVar.f758f, false);
        List<String> list = aVar.g;
        if (list != null) {
            a(list, true);
        }
        String str2 = aVar.h;
        if (str2 != null) {
            a(str2, false);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(aVar.f753a);
        sb.append("://");
        if (!aVar.f754b.isEmpty() || !aVar.f755c.isEmpty()) {
            sb.append(aVar.f754b);
            if (!aVar.f755c.isEmpty()) {
                sb.append(InetAddresses.IPV6_DELIMITER);
                sb.append(aVar.f755c);
            }
            sb.append('@');
        }
        if (aVar.f756d.indexOf(58) != -1) {
            sb.append('[');
            sb.append(aVar.f756d);
            sb.append(']');
        } else {
            sb.append(aVar.f756d);
        }
        int iA = aVar.a();
        if (iA != a(aVar.f753a)) {
            sb.append(InetAddresses.IPV6_DELIMITER);
            sb.append(iA);
        }
        b(sb, aVar.f758f);
        if (aVar.g != null) {
            sb.append('?');
            a(sb, aVar.g);
        }
        if (aVar.h != null) {
            sb.append('#');
            sb.append(aVar.h);
        }
        this.f752c = sb.toString();
    }

    public static int a(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - '0';
        }
        char c3 = 'a';
        if (c2 < 'a' || c2 > 'f') {
            c3 = 'A';
            if (c2 < 'A' || c2 > 'F') {
                return -1;
            }
        }
        return (c2 - c3) + 10;
    }

    public static int a(String str) {
        if (str.equals("http")) {
            return 80;
        }
        return str.equals("https") ? 443 : -1;
    }

    public static void b(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sb.append('/');
            sb.append(list.get(i));
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof d) && ((d) obj).f752c.equals(this.f752c);
    }

    public int hashCode() {
        return this.f752c.hashCode();
    }

    public String toString() {
        return this.f752c;
    }

    public static void a(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            String str = list.get(i);
            String str2 = list.get(i + 1);
            if (i > 0) {
                sb.append('&');
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
        }
    }

    public static String a(String str, boolean z) {
        return a(str, 0, str.length(), z);
    }

    public final List<String> a(List<String> list, boolean z) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String next = it.next();
            arrayList.add(next != null ? a(next, 0, next.length(), z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0052  */
    public static String a(String str, int i, int i2, boolean z) {
        int i3;
        int iCharCount = i;
        while (iCharCount < i2) {
            char cCharAt = str.charAt(iCharCount);
            if (cCharAt == '%' || (cCharAt == '+' && z)) {
                d.g gVar = new d.g();
                gVar.a(str, i, iCharCount);
                while (iCharCount < i2) {
                    int iCodePointAt = str.codePointAt(iCharCount);
                    if (iCodePointAt == 37 && (i3 = iCharCount + 2) < i2) {
                        int iA = a(str.charAt(iCharCount + 1));
                        int iA2 = a(str.charAt(i3));
                        if (iA != -1 && iA2 != -1) {
                            gVar.writeByte((iA << 4) + iA2);
                            iCharCount = i3;
                        } else {
                            gVar.b(iCodePointAt);
                        }
                    } else if (iCodePointAt == 43 && z) {
                        gVar.writeByte(32);
                    } else {
                        gVar.b(iCodePointAt);
                    }
                    iCharCount += Character.charCount(iCodePointAt);
                }
                return gVar.o();
            }
            iCharCount++;
        }
        return str.substring(i, i2);
    }
}
