package b.d.a.a.d;

/* JADX INFO: loaded from: classes.dex */
public final class c extends f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c f176c = new c("", 0, 0);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f177d;

    public c(String str, int i, int i2) {
        super(str, i);
        this.f177d = i2;
    }

    @Override // b.d.a.a.d.f
    public boolean a(int i) {
        return i == this.f177d;
    }

    @Override // b.d.a.a.d.f
    public boolean a(int i, int i2) {
        return i == this.f177d && i2 == 0;
    }

    @Override // b.d.a.a.d.f
    public boolean a(int[] iArr, int i) {
        return i == 1 && iArr[0] == this.f177d;
    }
}
