package b.g.a;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String[] f747a;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<String> f748a = new ArrayList(20);

        public a a(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("name == null");
            }
            if (str.isEmpty()) {
                throw new IllegalArgumentException("name is empty");
            }
            int length = str.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                char cCharAt = str.charAt(i2);
                if (cCharAt <= 31 || cCharAt >= 127) {
                    throw new IllegalArgumentException(String.format("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i2), str));
                }
            }
            if (str2 == null) {
                throw new IllegalArgumentException("value == null");
            }
            int length2 = str2.length();
            for (int i3 = 0; i3 < length2; i3++) {
                char cCharAt2 = str2.charAt(i3);
                if (cCharAt2 <= 31 || cCharAt2 >= 127) {
                    throw new IllegalArgumentException(String.format("Unexpected char %#04x at %d in header value: %s", Integer.valueOf(cCharAt2), Integer.valueOf(i3), str2));
                }
            }
            while (i < this.f748a.size()) {
                if (str.equalsIgnoreCase(this.f748a.get(i))) {
                    this.f748a.remove(i);
                    this.f748a.remove(i);
                    i -= 2;
                }
                i += 2;
            }
            this.f748a.add(str);
            this.f748a.add(str2.trim());
            return this;
        }

        public b a() {
            return new b(this, null);
        }
    }

    public /* synthetic */ b(a aVar, b.g.a.a aVar2) {
        this.f747a = (String[]) aVar.f748a.toArray(new String[aVar.f748a.size()]);
    }

    public String a(int i) {
        int i2 = i * 2;
        if (i2 < 0) {
            return null;
        }
        String[] strArr = this.f747a;
        if (i2 >= strArr.length) {
            return null;
        }
        return strArr[i2];
    }

    public String b(int i) {
        int i2 = (i * 2) + 1;
        if (i2 < 0) {
            return null;
        }
        String[] strArr = this.f747a;
        if (i2 >= strArr.length) {
            return null;
        }
        return strArr[i2];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int length = this.f747a.length / 2;
        for (int i = 0; i < length; i++) {
            sb.append(a(i));
            sb.append(": ");
            sb.append(b(i));
            sb.append("\n");
        }
        return sb.toString();
    }
}
