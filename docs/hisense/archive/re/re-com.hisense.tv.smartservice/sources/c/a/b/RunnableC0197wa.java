package c.a.b;

import c.a.InterfaceC0233q;

/* JADX INFO: renamed from: c.a.b.wa, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0197wa implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0233q f1500a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Na f1501b;

    public RunnableC0197wa(Na na, InterfaceC0233q interfaceC0233q) {
        this.f1501b = na;
        this.f1500a = interfaceC0233q;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1501b.f1088c.a(this.f1500a);
    }
}
