package c.a.b;

/* JADX INFO: loaded from: classes2.dex */
public class Aa implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f886a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Na f887b;

    public Aa(Na na, int i) {
        this.f887b = na;
        this.f886a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f887b.f1088c.c(this.f886a);
    }
}
