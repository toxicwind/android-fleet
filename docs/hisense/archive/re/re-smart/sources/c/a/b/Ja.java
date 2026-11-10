package c.a.b;

/* JADX INFO: loaded from: classes2.dex */
public class Ja implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Nd.a f1035a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Na.a f1036b;

    public Ja(Na.a aVar, Nd.a aVar2) {
        this.f1036b = aVar;
        this.f1035a = aVar2;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1036b.f1092a.a(this.f1035a);
    }
}
