package c.a.b;

/* JADX INFO: loaded from: classes2.dex */
public class wd implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AbstractC0171qd.j f1504a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AbstractC0171qd.i f1505b;

    public wd(AbstractC0171qd.i iVar, AbstractC0171qd.j jVar) {
        this.f1505b = iVar;
        this.f1504a = jVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        AbstractC0171qd.this.b(this.f1504a);
    }
}
