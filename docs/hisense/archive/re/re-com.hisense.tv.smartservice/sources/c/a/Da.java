package c.a;

/* JADX INFO: loaded from: classes2.dex */
public class Da implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Ea.a f789a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Runnable f790b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Ea f791c;

    public Da(Ea ea, Ea.a aVar, Runnable runnable) {
        this.f791c = ea;
        this.f789a = aVar;
        this.f790b = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f791c.execute(this.f789a);
    }

    public String toString() {
        return this.f790b.toString() + "(scheduled in SynchronizationContext)";
    }
}
