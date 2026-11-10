package c.a;

/* JADX INFO: loaded from: classes2.dex */
public class Ba extends RuntimeException {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final za f786a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0219ea f787b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f788c;

    public Ba(za zaVar, C0219ea c0219ea) {
        super(za.a(zaVar), zaVar.r);
        this.f786a = zaVar;
        this.f787b = c0219ea;
        this.f788c = true;
        fillInStackTrace();
    }

    public final C0219ea a() {
        return this.f787b;
    }

    @Override // java.lang.Throwable
    public synchronized Throwable fillInStackTrace() {
        return this.f788c ? super.fillInStackTrace() : this;
    }
}
