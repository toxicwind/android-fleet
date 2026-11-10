package c.a.b;

import c.a.AbstractC0226j;
import c.a.C0236u;

/* JADX INFO: renamed from: c.a.b.uc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0189uc implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0130ic.j.a f1483a;

    public RunnableC0189uc(C0130ic.j.a aVar) {
        this.f1483a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0236u c0236uA = this.f1483a.l.a();
        try {
            AbstractC0226j abstractC0226jB = C0130ic.j.this.b(this.f1483a.m, this.f1483a.n);
            this.f1483a.l.a(c0236uA);
            this.f1483a.a(abstractC0226jB);
            C0130ic.j.a aVar = this.f1483a;
            c.a.Ea ea = C0130ic.this.w;
            C0130ic.j.a.RunnableC0009a runnableC0009a = new C0130ic.j.a.RunnableC0009a();
            b.a.a.a.a.a(runnableC0009a, "runnable is null", ea.f796b, runnableC0009a, ea);
        } catch (Throwable th) {
            this.f1483a.l.a(c0236uA);
            throw th;
        }
    }
}
