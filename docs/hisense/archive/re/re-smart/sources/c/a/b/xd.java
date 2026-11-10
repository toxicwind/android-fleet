package c.a.b;

import c.a.C0219ea;

/* JADX INFO: loaded from: classes2.dex */
public class xd implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c.a.za f1510a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ N.a f1511b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ C0219ea f1512c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ AbstractC0171qd.i f1513d;

    public xd(AbstractC0171qd.i iVar, c.a.za zaVar, N.a aVar, C0219ea c0219ea) {
        this.f1513d = iVar;
        this.f1510a = zaVar;
        this.f1511b = aVar;
        this.f1512c = c0219ea;
    }

    @Override // java.lang.Runnable
    public void run() {
        AbstractC0171qd.this.A = true;
        AbstractC0171qd.this.v.a(this.f1510a, this.f1511b, this.f1512c);
    }
}
