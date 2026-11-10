package c.a.b;

/* JADX INFO: loaded from: classes2.dex */
public class Ea implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f935a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Na f936b;

    public Ea(Na na, String str) {
        this.f936b = na;
        this.f935a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f936b.f1088c.a(this.f935a);
    }
}
