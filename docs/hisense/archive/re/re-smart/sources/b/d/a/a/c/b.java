package b.d.a.a.c;

import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f130a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f131b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f132c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public HashSet<String> f133d;

    public b(Object obj) {
        this.f130a = obj;
    }

    public b a() {
        return new b(this.f130a);
    }

    public boolean a(String str) {
        String str2 = this.f131b;
        if (str2 == null) {
            this.f131b = str;
            return false;
        }
        if (str.equals(str2)) {
            return true;
        }
        String str3 = this.f132c;
        if (str3 == null) {
            this.f132c = str;
            return false;
        }
        if (str.equals(str3)) {
            return true;
        }
        if (this.f133d == null) {
            this.f133d = new HashSet<>(16);
            this.f133d.add(this.f131b);
            this.f133d.add(this.f132c);
        }
        return !this.f133d.add(str);
    }
}
