package c.a.b;

import java.util.Collection;
import java.util.concurrent.Future;

/* JADX INFO: renamed from: c.a.b.ad, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
class RunnableC0091ad implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Collection f1221a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AbstractC0171qd.j f1222b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Future f1223c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Future f1224d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ AbstractC0171qd f1225e;

    public RunnableC0091ad(AbstractC0171qd abstractC0171qd, Collection collection, AbstractC0171qd.j jVar, Future future, Future future2) {
        this.f1225e = abstractC0171qd;
        this.f1221a = collection;
        this.f1222b = jVar;
        this.f1223c = future;
        this.f1224d = future2;
    }

    @Override // java.lang.Runnable
    public void run() {
        for (AbstractC0171qd.j jVar : this.f1221a) {
            if (jVar != this.f1222b) {
                jVar.f1441a.a(AbstractC0171qd.f1417c);
            }
        }
        Future future = this.f1223c;
        if (future != null) {
            future.cancel(false);
        }
        Future future2 = this.f1224d;
        if (future2 != null) {
            future2.cancel(false);
        }
        C0125hc c0125hc = (C0125hc) this.f1225e;
        C0130ic.this.Q.b(c0125hc);
    }
}
