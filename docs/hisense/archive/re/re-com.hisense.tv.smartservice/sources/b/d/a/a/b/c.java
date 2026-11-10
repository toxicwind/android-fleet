package b.d.a.a.b;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f88a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.d.a.a.a f89b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f90c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b.d.a.a.e.a f91d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f92e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte[] f93f = null;
    public char[] g = null;
    public char[] h = null;
    public char[] i = null;

    public c(b.d.a.a.e.a aVar, Object obj, boolean z) {
        this.f91d = aVar;
        this.f88a = obj;
        this.f90c = z;
    }

    public void a(b.d.a.a.a aVar) {
        this.f89b = aVar;
    }

    public b.d.a.a.e.f b() {
        return new b.d.a.a.e.f(this.f91d);
    }

    public b.d.a.a.a c() {
        return this.f89b;
    }

    public char[] a() {
        a((Object) this.h);
        char[] cArrA = this.f91d.a(1, 0);
        this.h = cArrA;
        return cArrA;
    }

    public void b(byte[] bArr) {
        if (bArr != null) {
            a(bArr, this.f93f);
            this.f93f = null;
            this.f91d.f196c[1] = bArr;
        }
    }

    public void c(char[] cArr) {
        if (cArr != null) {
            a(cArr, this.g);
            this.g = null;
            this.f91d.f197d[0] = cArr;
        }
    }

    public void a(byte[] bArr) {
        if (bArr != null) {
            a(bArr, this.f92e);
            this.f92e = null;
            this.f91d.f196c[0] = bArr;
        }
    }

    public void b(char[] cArr) {
        if (cArr != null) {
            a(cArr, this.i);
            this.i = null;
            this.f91d.f197d[3] = cArr;
        }
    }

    public void a(char[] cArr) {
        if (cArr != null) {
            a(cArr, this.h);
            this.h = null;
            this.f91d.f197d[1] = cArr;
        }
    }

    public final void a(Object obj) {
        if (obj != null) {
            throw new IllegalStateException("Trying to call same allocXxx() method second time");
        }
    }

    public final void a(byte[] bArr, byte[] bArr2) {
        if (bArr != bArr2 && bArr.length <= bArr2.length) {
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
    }

    public final void a(char[] cArr, char[] cArr2) {
        if (cArr != cArr2 && cArr.length <= cArr2.length) {
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
    }
}
