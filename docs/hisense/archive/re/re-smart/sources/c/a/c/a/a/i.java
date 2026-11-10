package c.a.c.a.a;

/* JADX INFO: loaded from: classes2.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1619a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1620b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1621c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int[] f1622d = new int[10];

    public i a(int i, int i2, int i3) {
        if (i >= this.f1622d.length) {
            return this;
        }
        int i4 = 1 << i;
        this.f1619a |= i4;
        if ((i2 & 1) != 0) {
            this.f1620b |= i4;
        } else {
            this.f1620b &= ~i4;
        }
        if ((i2 & 2) != 0) {
            this.f1621c |= i4;
        } else {
            this.f1621c &= ~i4;
        }
        this.f1622d[i] = i3;
        return this;
    }

    public boolean a(int i) {
        return ((1 << i) & this.f1619a) != 0;
    }
}
