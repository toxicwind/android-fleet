package b.d.a.a.d;

/* JADX INFO: loaded from: classes.dex */
public final class d extends f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f178c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f179d;

    public d(String str, int i, int i2, int i3) {
        super(str, i);
        this.f178c = i2;
        this.f179d = i3;
    }

    @Override // b.d.a.a.d.f
    public boolean a(int i) {
        return false;
    }

    @Override // b.d.a.a.d.f
    public boolean a(int i, int i2) {
        return i == this.f178c && i2 == this.f179d;
    }

    @Override // b.d.a.a.d.f
    public boolean a(int[] iArr, int i) {
        return i == 2 && iArr[0] == this.f178c && iArr[1] == this.f179d;
    }
}
