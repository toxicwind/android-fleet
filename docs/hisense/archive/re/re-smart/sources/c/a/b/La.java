package c.a.b;

import c.a.C0219ea;

/* JADX INFO: loaded from: classes2.dex */
public class La implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0219ea f1064a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Na.a f1065b;

    public La(Na.a aVar, C0219ea c0219ea) {
        this.f1065b = aVar;
        this.f1064a = c0219ea;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1065b.f1092a.a(this.f1064a);
    }
}
