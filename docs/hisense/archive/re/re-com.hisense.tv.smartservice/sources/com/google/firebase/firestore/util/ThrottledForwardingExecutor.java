package com.google.firebase.firestore.util;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: classes.dex */
public class ThrottledForwardingExecutor implements Executor {
    public final Semaphore availableSlots;
    public final Executor executor;

    public ThrottledForwardingExecutor(int i, Executor executor) {
        this.availableSlots = new Semaphore(i);
        this.executor = executor;
    }

    public /* synthetic */ void a(Runnable runnable) {
        runnable.run();
        this.availableSlots.release();
    }

    @Override // java.util.concurrent.Executor
    public void execute(final Runnable runnable) {
        if (!this.availableSlots.tryAcquire()) {
            runnable.run();
            return;
        }
        try {
            this.executor.execute(new Runnable() { // from class: b.e.b.d.g.n
                @Override // java.lang.Runnable
                public final void run() {
                    this.f556a.a(runnable);
                }
            });
        } catch (RejectedExecutionException unused) {
            runnable.run();
        }
    }
}
