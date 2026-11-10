package okhttp3;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class J {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final List f161a = new ArrayList(20);

    public J a(String str, String str2) {
        K.a(str);
        K.b(str2, str);
        c(str, str2);
        return this;
    }

    J b(String str) {
        int iIndexOf = str.indexOf(":", 1);
        if (iIndexOf != -1) {
            c(str.substring(0, iIndexOf), str.substring(iIndexOf + 1));
            return this;
        }
        if (str.startsWith(":")) {
            c("", str.substring(1));
            return this;
        }
        c("", str);
        return this;
    }

    J c(String str, String str2) {
        this.f161a.add(str);
        this.f161a.add(str2.trim());
        return this;
    }

    public K d() {
        return new K(this);
    }

    public String e(String str) {
        for (int size = this.f161a.size() - 2; size >= 0; size -= 2) {
            if (str.equalsIgnoreCase((String) this.f161a.get(size))) {
                return (String) this.f161a.get(size + 1);
            }
        }
        return null;
    }

    public J f(String str) {
        int i = 0;
        while (i < this.f161a.size()) {
            if (str.equalsIgnoreCase((String) this.f161a.get(i))) {
                this.f161a.remove(i);
                this.f161a.remove(i);
                i -= 2;
            }
            i += 2;
        }
        return this;
    }

    public J g(String str, String str2) {
        K.a(str);
        K.b(str2, str);
        f(str);
        c(str, str2);
        return this;
    }
}
