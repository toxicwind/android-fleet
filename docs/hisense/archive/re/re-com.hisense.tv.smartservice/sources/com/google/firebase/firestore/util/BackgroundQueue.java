package com.google.firebase.firestore.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: classes.dex */
public class BackgroundQueue implements Executor {
    public Semaphore completedTasks = new Semaphore(0);
    public int pendingTaskCount = 0;

    public /* synthetic */ void a(Runnable runnable) {
        runnable.run();
        this.completedTasks.release();
    }

    public void drain() throws InterruptedException {
        this.completedTasks.acquire(this.pendingTaskCount);
        this.pendingTaskCount = 0;
    }

    @Override // java.util.concurrent.Executor
    public void execute(final Runnable runnable) {
        this.pendingTaskCount++;
        Executors.BACKGROUND_EXECUTOR.execute(new Runnable() { // from class: b.e.b.d.g.l
            @Override // java.lang.Runnable
            public final void run() {
                this.f552a.a(runnable);
            }
        });
    }
}
