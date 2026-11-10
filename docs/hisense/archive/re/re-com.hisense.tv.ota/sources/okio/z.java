package okio;

/* JADX INFO: loaded from: classes.dex */
final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static y f281a;
    static long b;

    private z() {
    }

    static void a(y yVar) {
        if (yVar.f != null || yVar.g != null) {
            throw new IllegalArgumentException();
        }
        if (yVar.d) {
            return;
        }
        synchronized (z.class) {
            if (b + 8192 > 65536) {
                return;
            }
            b += 8192;
            yVar.f = f281a;
            yVar.c = 0;
            yVar.b = 0;
            f281a = yVar;
        }
    }

    static y b() {
        synchronized (z.class) {
            if (f281a == null) {
                return new y();
            }
            y yVar = f281a;
            f281a = yVar.f;
            yVar.f = null;
            b -= 8192;
            return yVar;
        }
    }
}
