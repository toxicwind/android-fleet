package b.d.a.a.d;

/* JADX INFO: loaded from: classes.dex */
public final class e extends f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f180c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f181d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f182e;

    public e(String str, int i, int i2, int i3, int i4) {
        super(str, i);
        this.f180c = i2;
        this.f181d = i3;
        this.f182e = i4;
    }

    @Override // b.d.a.a.d.f
    public boolean a(int i) {
        return false;
    }

    @Override // b.d.a.a.d.f
    public boolean a(int i, int i2) {
        return false;
    }

    @Override // b.d.a.a.d.f
    public boolean a(int[] iArr, int i) {
        return i == 3 && iArr[0] == this.f180c && iArr[1] == this.f181d && iArr[2] == this.f182e;
    }
}
