package c.a.b;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes2.dex */
public class Gd implements Id.c {
    public ScheduledExecutorService a() {
        return Executors.newSingleThreadScheduledExecutor(C0149mb.a("grpc-shared-destroyer-%d", true));
    }
}
