package c.a.b;

import c.a.C0219ea;

/* JADX INFO: loaded from: classes2.dex */
public class Ma implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c.a.za f1071a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ N.a f1072b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ C0219ea f1073c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Na.a f1074d;

    public Ma(Na.a aVar, c.a.za zaVar, N.a aVar2, C0219ea c0219ea) {
        this.f1074d = aVar;
        this.f1071a = zaVar;
        this.f1072b = aVar2;
        this.f1073c = c0219ea;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1074d.f1092a.a(this.f1071a, this.f1072b, this.f1073c);
    }
}
