package b.d.a.a.d;

/* JADX INFO: loaded from: classes.dex */
public final class g extends f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f185c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f186d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f187e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f188f;
    public final int g;
    public final int[] h;

    public g(String str, int i, int i2, int i3, int i4, int i5, int[] iArr, int i6) {
        super(str, i);
        this.f185c = i2;
        this.f186d = i3;
        this.f187e = i4;
        this.f188f = i5;
        this.h = iArr;
        this.g = i6;
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
        int i2 = this.g;
        if (i != i2 || iArr[0] != this.f185c || iArr[1] != this.f186d || iArr[2] != this.f187e || iArr[3] != this.f188f) {
            return false;
        }
        switch (i) {
            case 8:
                if (iArr[7] != this.h[3]) {
                    return false;
                }
            case 7:
                if (iArr[6] != this.h[2]) {
                    return false;
                }
            case 6:
                if (iArr[5] != this.h[1]) {
                    return false;
                }
            case 5:
                if (iArr[4] != this.h[0]) {
                    return false;
                }
            case 4:
                return true;
            default:
                int i3 = i2 - 4;
                for (int i4 = 0; i4 < i3; i4++) {
                    if (iArr[i4 + 4] != this.h[i4]) {
                        return false;
                    }
                }
                return true;
        }
    }
}
