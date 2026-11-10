package c.a.b;

/* JADX INFO: loaded from: classes2.dex */
public class yd implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Nd.a f1523a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AbstractC0171qd.i f1524b;

    public yd(AbstractC0171qd.i iVar, Nd.a aVar) {
        this.f1524b = iVar;
        this.f1523a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        AbstractC0171qd.this.v.a(this.f1523a);
    }
}
