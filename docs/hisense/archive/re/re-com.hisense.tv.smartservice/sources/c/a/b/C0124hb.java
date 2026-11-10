package c.a.b;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: renamed from: c.a.b.hb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0124hb implements Id.b<ScheduledExecutorService> {
    @Override // c.a.b.Id.b
    public void a(ScheduledExecutorService scheduledExecutorService) {
        scheduledExecutorService.shutdown();
    }

    @Override // c.a.b.Id.b
    public ScheduledExecutorService a() {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, C0149mb.a("grpc-timer-%d", true));
        try {
            scheduledExecutorServiceNewScheduledThreadPool.getClass().getMethod("setRemoveOnCancelPolicy", Boolean.TYPE).invoke(scheduledExecutorServiceNewScheduledThreadPool, true);
        } catch (NoSuchMethodException unused) {
        } catch (RuntimeException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new RuntimeException(e3);
        }
        return Executors.unconfigurableScheduledExecutorService(scheduledExecutorServiceNewScheduledThreadPool);
    }
}
