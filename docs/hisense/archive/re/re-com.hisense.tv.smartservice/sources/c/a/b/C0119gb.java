package c.a.b;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: renamed from: c.a.b.gb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0119gb implements Id.b<Executor> {
    @Override // c.a.b.Id.b
    public Executor a() {
        return Executors.newCachedThreadPool(C0149mb.a("grpc-default-executor-%d", true));
    }

    public String toString() {
        return "grpc-default-executor";
    }

    @Override // c.a.b.Id.b
    public void a(Executor executor) {
        ((ExecutorService) executor).shutdown();
    }
}
