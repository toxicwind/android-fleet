package b.d.a.a.a;

import b.d.a.a.c.e;
import b.d.a.a.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f63b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f64c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public e f65d;

    static {
        int i = d.a.WRITE_NUMBERS_AS_STRINGS.m;
        int i2 = d.a.ESCAPE_NON_ASCII.m;
        int i3 = d.a.STRICT_DUPLICATE_DETECTION.m;
    }

    public a(int i) {
        this.f63b = i;
        this.f65d = new e(0, null, (d.a.STRICT_DUPLICATE_DETECTION.m & i) != 0 ? new b.d.a.a.c.b(this) : null);
        this.f64c = (i & d.a.WRITE_NUMBERS_AS_STRINGS.m) != 0;
    }

    public final boolean a(d.a aVar) {
        return (aVar.m & this.f63b) != 0;
    }
}
