package c.a.b;

import java.util.LinkedHashSet;

/* JADX INFO: renamed from: c.a.b.tc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0184tc implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0130ic.j.a f1472a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0130ic.j f1473b;

    public RunnableC0184tc(C0130ic.j jVar, C0130ic.j.a aVar) {
        this.f1473b = jVar;
        this.f1472a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f1473b.f1297a.get() != C0130ic.g) {
            C0130ic.j.a aVar = this.f1472a;
            C0130ic.this.a(aVar.n).execute(new RunnableC0189uc(aVar));
            return;
        }
        if (C0130ic.this.M == null) {
            C0130ic.this.M = new LinkedHashSet();
            C0130ic c0130ic = C0130ic.this;
            c0130ic.ma.a(c0130ic.N, true);
        }
        C0130ic.this.M.add(this.f1472a);
    }
}
