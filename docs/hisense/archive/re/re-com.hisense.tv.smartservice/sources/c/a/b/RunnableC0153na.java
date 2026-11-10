package c.a.b;

import c.a.C0219ea;

/* JADX INFO: renamed from: c.a.b.na, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class RunnableC0153na implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c.a.za f1376a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0219ea f1377b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ C0138ka.b f1378c;

    public RunnableC0153na(C0138ka.b bVar, c.a.za zaVar, C0219ea c0219ea) {
        this.f1378c = bVar;
        this.f1376a = zaVar;
        this.f1377b = c0219ea;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f1378c.f1333a.onClose(this.f1376a, this.f1377b);
    }
}
