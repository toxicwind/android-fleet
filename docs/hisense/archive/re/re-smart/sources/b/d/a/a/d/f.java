package b.d.a.a.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f183a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f184b;

    public f(String str, int i) {
        this.f183a = str;
        this.f184b = i;
    }

    public abstract boolean a(int i);

    public abstract boolean a(int i, int i2);

    public abstract boolean a(int[] iArr, int i);

    public boolean equals(Object obj) {
        return obj == this;
    }

    public final int hashCode() {
        return this.f184b;
    }

    public String toString() {
        return this.f183a;
    }
}
