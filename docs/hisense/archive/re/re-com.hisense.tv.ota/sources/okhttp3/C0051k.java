package okhttp3;

import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: okhttp3.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0051k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f247a;
    boolean b;
    int c = -1;
    int d = -1;
    int e = -1;
    boolean f;
    boolean g;
    boolean h;

    public C0052l a() {
        return new C0052l(this);
    }

    public C0051k b(int i, TimeUnit timeUnit) {
        if (i >= 0) {
            long seconds = timeUnit.toSeconds(i);
            this.d = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
            return this;
        }
        throw new IllegalArgumentException("maxStale < 0: " + i);
    }

    public C0051k c() {
        this.f247a = true;
        return this;
    }

    public C0051k d() {
        this.f = true;
        return this;
    }
}
