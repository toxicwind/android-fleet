package b.d.a.a.b;

import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal<SoftReference<d>> f94a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.d.a.a.e.b f95b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final char[] f96c = new char[6];

    static {
        f94a = new ThreadLocal<>();
    }

    public d() {
        char[] cArr = this.f96c;
        cArr[0] = '\\';
        cArr[2] = '0';
        cArr[3] = '0';
    }

    public static void a(int i) {
        throw new IllegalArgumentException(j.d(i));
    }
}
