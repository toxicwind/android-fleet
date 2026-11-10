package c.a.b;

/* JADX INFO: loaded from: classes2.dex */
public class zd implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AbstractC0171qd.i f1547a;

    public zd(AbstractC0171qd.i iVar) {
        this.f1547a = iVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (AbstractC0171qd.this.A) {
            return;
        }
        AbstractC0171qd.this.v.a();
    }
}
