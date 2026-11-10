package c.a.b;

/* JADX INFO: renamed from: c.a.b.od, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0161od implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AbstractC0171qd f1400a;

    public RunnableC0161od(AbstractC0171qd abstractC0171qd) {
        this.f1400a = abstractC0171qd;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f1400a.A) {
            return;
        }
        this.f1400a.v.a();
    }
}
