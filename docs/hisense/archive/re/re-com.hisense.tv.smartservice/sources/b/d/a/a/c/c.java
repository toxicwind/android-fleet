package b.d.a.a.c;

import b.d.a.a.m;

/* JADX INFO: loaded from: classes.dex */
public abstract class c extends b.d.a.a.a.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f134e = b.d.a.a.b.a.h;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final b.d.a.a.b.c f135f;
    public int[] g;
    public int h;
    public b.d.a.a.b.b i;
    public m j;

    public c(b.d.a.a.b.c cVar, int i) {
        super(i);
        this.g = f134e;
        this.j = b.d.a.a.e.c.f203a;
        this.f135f = cVar;
        if (a(b.d.a.a.d.a.ESCAPE_NON_ASCII)) {
            this.h = 127;
        }
    }

    public b.d.a.a.d a(m mVar) {
        this.j = mVar;
        return this;
    }
}
