package c.a.b;

/* JADX INFO: renamed from: c.a.b.ma, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0148ma implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Object f1356a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0138ka.b f1357b;

    public RunnableC0148ma(C0138ka.b bVar, Object obj) {
        this.f1357b = bVar;
        this.f1356a = obj;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // java.lang.Runnable
    public void run() {
        this.f1357b.f1333a.onMessage((RespT) this.f1356a);
    }
}
