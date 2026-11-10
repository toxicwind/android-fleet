package c.a.c;

import c.a.b.C0149mb;
import c.a.b.Id;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes2.dex */
public class g implements Id.b<Executor> {
    @Override // c.a.b.Id.b
    public Executor a() {
        return Executors.newCachedThreadPool(C0149mb.a("grpc-okhttp-%d", true));
    }

    @Override // c.a.b.Id.b
    public void a(Executor executor) {
        ((ExecutorService) executor).shutdown();
    }
}
