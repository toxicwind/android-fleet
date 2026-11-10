package c.a.b;

/* JADX INFO: loaded from: classes2.dex */
public class Ha implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c.a.za f990a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Na f991b;

    public Ha(Na na, c.a.za zaVar) {
        this.f991b = na;
        this.f990a = zaVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f991b.f1088c.a(this.f990a);
    }
}
