package c.a;

/* JADX INFO: loaded from: classes2.dex */
public class Aa extends Exception {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final za f779a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0219ea f780b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f781c;

    public Aa(za zaVar) {
        super(za.a(zaVar), zaVar.r);
        this.f779a = zaVar;
        this.f780b = null;
        this.f781c = true;
        fillInStackTrace();
    }

    @Override // java.lang.Throwable
    public synchronized Throwable fillInStackTrace() {
        return this.f781c ? super.fillInStackTrace() : this;
    }
}
