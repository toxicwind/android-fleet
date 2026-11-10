package b.d.a.a;

/* JADX INFO: loaded from: classes.dex */
public abstract class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f220a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f221b;

    public final int a() {
        int i = this.f221b;
        if (i < 0) {
            return 0;
        }
        return i;
    }

    public final String b() {
        int i = this.f220a;
        if (i == 0) {
            return "ROOT";
        }
        if (i != 1) {
            return i != 2 ? "?" : "OBJECT";
        }
        return "ARRAY";
    }

    public final boolean c() {
        return this.f220a == 1;
    }

    public final boolean d() {
        return this.f220a == 2;
    }

    public final boolean e() {
        return this.f220a == 0;
    }
}
