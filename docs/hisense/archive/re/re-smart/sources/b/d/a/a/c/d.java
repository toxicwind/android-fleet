package b.d.a.a.c;

/* JADX INFO: loaded from: classes.dex */
public final class d extends b.d.a.a.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f136c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f137d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public d f138e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f139f;
    public int g;
    public int h;

    public d(d dVar, b bVar, int i, int i2, int i3) {
        this.f136c = dVar;
        this.f137d = bVar;
        this.f220a = i;
        this.g = i2;
        this.h = i3;
        this.f221b = -1;
    }

    public void a(int i, int i2, int i3) {
        this.f220a = i;
        this.f221b = -1;
        this.g = i2;
        this.h = i3;
        this.f139f = null;
        b bVar = this.f137d;
        if (bVar != null) {
            bVar.f131b = null;
            bVar.f132c = null;
            bVar.f133d = null;
        }
    }

    public d b(int i, int i2) {
        d dVar = this.f138e;
        if (dVar != null) {
            dVar.a(2, i, i2);
            return dVar;
        }
        b bVar = this.f137d;
        d dVar2 = new d(this, bVar == null ? null : bVar.a(), 2, i, i2);
        this.f138e = dVar2;
        return dVar2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        int i = this.f220a;
        if (i != 0) {
            if (i == 1) {
                sb.append('[');
                int i2 = this.f221b;
                if (i2 < 0) {
                    i2 = 0;
                }
                sb.append(i2);
                sb.append(']');
            } else if (i == 2) {
                sb.append('{');
                if (this.f139f != null) {
                    sb.append('\"');
                    String str = this.f139f;
                    int[] iArr = b.d.a.a.b.a.h;
                    int length = iArr.length;
                    int length2 = str.length();
                    for (int i3 = 0; i3 < length2; i3++) {
                        char cCharAt = str.charAt(i3);
                        if (cCharAt >= length || iArr[cCharAt] == 0) {
                            sb.append(cCharAt);
                        } else {
                            sb.append('\\');
                            int i4 = iArr[cCharAt];
                            if (i4 < 0) {
                                sb.append('u');
                                sb.append('0');
                                sb.append('0');
                                sb.append(b.d.a.a.b.a.f82a[cCharAt >> 4]);
                                sb.append(b.d.a.a.b.a.f82a[cCharAt & 15]);
                            } else {
                                sb.append((char) i4);
                            }
                        }
                    }
                    sb.append('\"');
                } else {
                    sb.append('?');
                }
                sb.append('}');
            }
        } else {
            sb.append("/");
        }
        return sb.toString();
    }

    public d a(int i, int i2) {
        d dVar = this.f138e;
        if (dVar == null) {
            b bVar = this.f137d;
            dVar = new d(this, bVar == null ? null : bVar.a(), 1, i, i2);
            this.f138e = dVar;
        } else {
            dVar.a(1, i, i2);
        }
        return dVar;
    }

    public b.d.a.a.e a(Object obj) {
        return new b.d.a.a.e(obj, -1L, this.g, this.h);
    }

    public void a(String str) {
        this.f139f = str;
        b bVar = this.f137d;
        if (bVar == null || !bVar.a(str)) {
            return;
        }
        String strA = b.a.a.a.a.a("Duplicate field '", str, "'");
        Object obj = bVar.f130a;
        throw new b.d.a.a.f(strA, obj instanceof b.d.a.a.g ? ((b.d.a.a.g) obj).p() : null);
    }
}
