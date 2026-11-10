package b.d.a.a.c;

/* JADX INFO: loaded from: classes.dex */
public class e extends b.d.a.a.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e f140c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f141d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public e f142e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f143f;
    public boolean g;

    public e(int i, e eVar, b bVar) {
        this.f220a = i;
        this.f140c = eVar;
        this.f141d = bVar;
        this.f221b = -1;
    }

    public e a(int i) {
        this.f220a = i;
        this.f221b = -1;
        this.f143f = null;
        this.g = false;
        b bVar = this.f141d;
        if (bVar != null) {
            bVar.f131b = null;
            bVar.f132c = null;
            bVar.f133d = null;
        }
        return this;
    }

    public e f() {
        e eVar = this.f142e;
        if (eVar != null) {
            eVar.a(1);
            return eVar;
        }
        b bVar = this.f141d;
        e eVar2 = new e(1, this, bVar == null ? null : bVar.a());
        this.f142e = eVar2;
        return eVar2;
    }

    public e g() {
        e eVar = this.f142e;
        if (eVar != null) {
            eVar.a(2);
            return eVar;
        }
        b bVar = this.f141d;
        e eVar2 = new e(2, this, bVar == null ? null : bVar.a());
        this.f142e = eVar2;
        return eVar2;
    }

    public int h() {
        int i = this.f220a;
        if (i == 2) {
            this.g = false;
            this.f221b++;
            return 2;
        }
        if (i != 1) {
            this.f221b++;
            return this.f221b == 0 ? 0 : 3;
        }
        int i2 = this.f221b;
        this.f221b = i2 + 1;
        return i2 < 0 ? 0 : 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        int i = this.f220a;
        if (i == 2) {
            sb.append('{');
            if (this.f143f != null) {
                sb.append('\"');
                sb.append(this.f143f);
                sb.append('\"');
            } else {
                sb.append('?');
            }
            sb.append('}');
        } else if (i == 1) {
            sb.append('[');
            sb.append(a());
            sb.append(']');
        } else {
            sb.append("/");
        }
        return sb.toString();
    }

    public int a(String str) throws b.d.a.a.c {
        if (this.g) {
            return 4;
        }
        this.g = true;
        this.f143f = str;
        b bVar = this.f141d;
        if (bVar == null || !bVar.a(str)) {
            return this.f221b < 0 ? 0 : 1;
        }
        throw new b.d.a.a.c(b.a.a.a.a.a("Duplicate field '", str, "'"));
    }
}
