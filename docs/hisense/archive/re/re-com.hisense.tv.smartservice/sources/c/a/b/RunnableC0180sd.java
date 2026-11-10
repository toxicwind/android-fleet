package c.a.b;

import c.a.C0219ea;

/* JADX INFO: renamed from: c.a.b.sd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0180sd implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0219ea f1461a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AbstractC0171qd.i f1462b;

    public RunnableC0180sd(AbstractC0171qd.i iVar, C0219ea c0219ea) {
        this.f1462b = iVar;
        this.f1461a = c0219ea;
    }

    @Override // java.lang.Runnable
    public void run() {
        AbstractC0171qd.this.v.a(this.f1461a);
    }
}
