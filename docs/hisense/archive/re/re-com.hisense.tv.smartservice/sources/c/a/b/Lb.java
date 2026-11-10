package c.a.b;

import c.a.EnumC0234s;
import com.google.common.base.Preconditions;
import java.util.Queue;

/* JADX INFO: loaded from: classes2.dex */
public class Lb implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Ib.d f1066a;

    public Lb(Ib.d dVar) {
        this.f1066a = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Ib.d dVar = this.f1066a;
        Ib.this.s.remove(dVar.f1019a);
        if (Ib.this.w.f1906a == EnumC0234s.SHUTDOWN && Ib.this.s.isEmpty()) {
            Ib ib = Ib.this;
            c.a.Ea ea = ib.k;
            Db db = new Db(ib);
            Queue<Runnable> queue = ea.f796b;
            Preconditions.checkNotNull(db, "runnable is null");
            queue.add(db);
            ea.a();
        }
    }
}
