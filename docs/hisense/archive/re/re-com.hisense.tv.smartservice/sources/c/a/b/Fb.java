package c.a.b;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class Fb implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c.a.za f965a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Ib f966b;

    public Fb(Ib ib, c.a.za zaVar) {
        this.f966b = ib;
        this.f965a = zaVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Iterator it = new ArrayList(this.f966b.s).iterator();
        while (it.hasNext()) {
            ((Cc) it.next()).a(this.f965a);
        }
    }
}
