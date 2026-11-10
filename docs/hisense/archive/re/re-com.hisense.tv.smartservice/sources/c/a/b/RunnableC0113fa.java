package c.a.b;

/* JADX INFO: renamed from: c.a.b.fa, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0113fa implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Object f1257a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0138ka f1258b;

    public RunnableC0113fa(C0138ka c0138ka, Object obj) {
        this.f1258b = c0138ka;
        this.f1257a = obj;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1258b.h.sendMessage(this.f1257a);
    }
}
