package d;

/* JADX INFO: loaded from: classes2.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static y f2144a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static long f2145b;

    public static y a() {
        synchronized (z.class) {
            if (f2144a == null) {
                return new y();
            }
            y yVar = f2144a;
            f2144a = yVar.f2143f;
            yVar.f2143f = null;
            f2145b -= 8192;
            return yVar;
        }
    }

    public static void a(y yVar) {
        if (yVar.f2143f == null && yVar.g == null) {
            if (yVar.f2141d) {
                return;
            }
            synchronized (z.class) {
                if (f2145b + 8192 > 65536) {
                    return;
                }
                f2145b += 8192;
                yVar.f2143f = f2144a;
                yVar.f2140c = 0;
                yVar.f2139b = 0;
                f2144a = yVar;
                return;
            }
        }
        throw new IllegalArgumentException();
    }
}
