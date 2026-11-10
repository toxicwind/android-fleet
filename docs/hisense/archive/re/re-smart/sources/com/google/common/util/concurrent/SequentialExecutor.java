package com.google.common.util.concurrent;

import b.a.a.a.a;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.firebase.installations.local.IidStore;
import com.google.j2objc.annotations.RetainedWith;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public final class SequentialExecutor implements Executor {
    public static final Logger log = Logger.getLogger(SequentialExecutor.class.getName());
    public final Executor executor;

    @GuardedBy("queue")
    public final Deque<Runnable> queue = new ArrayDeque();

    @GuardedBy("queue")
    public WorkerRunningState workerRunningState = WorkerRunningState.IDLE;

    @GuardedBy("queue")
    public long workerRunCount = 0;

    @RetainedWith
    public final QueueWorker worker = new QueueWorker(null);

    private final class QueueWorker implements Runnable {
        public Runnable task;

        public QueueWorker() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x0047, code lost:
        
            if (r1 == false) goto L49;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0049, code lost:
        
            java.lang.Thread.currentThread().interrupt();
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0050, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x0056, code lost:
        
            r1 = r1 | java.lang.Thread.interrupted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0058, code lost:
        
            r9.task.run();
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x0060, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x0062, code lost:
        
            r3 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x0063, code lost:
        
            r4 = com.google.common.util.concurrent.SequentialExecutor.log;
            r5 = java.util.logging.Level.SEVERE;
            r6 = java.lang.String.valueOf(r9.task);
            r8 = new java.lang.StringBuilder(r6.length() + 35);
            r8.append("Exception while executing runnable ");
            r8.append(r6);
            r4.log(r5, r8.toString(), (java.lang.Throwable) r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0088, code lost:
        
            r9.task = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x008a, code lost:
        
            throw r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:?, code lost:
        
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void workOnQueue() {
            /*
                r9 = this;
                r0 = 0
                r1 = r0
            L2:
                com.google.common.util.concurrent.SequentialExecutor r2 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L8e
                java.util.Deque r2 = com.google.common.util.concurrent.SequentialExecutor.access$100(r2)     // Catch: java.lang.Throwable -> L8e
                monitor-enter(r2)     // Catch: java.lang.Throwable -> L8e
                if (r0 != 0) goto L2d
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L8b
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r0 = com.google.common.util.concurrent.SequentialExecutor.access$200(r0)     // Catch: java.lang.Throwable -> L8b
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r3 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.RUNNING     // Catch: java.lang.Throwable -> L8b
                if (r0 != r3) goto L20
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L8b
                if (r1 == 0) goto L1f
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L1f:
                return
            L20:
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L8b
                com.google.common.util.concurrent.SequentialExecutor.access$308(r0)     // Catch: java.lang.Throwable -> L8b
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L8b
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r3 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.RUNNING     // Catch: java.lang.Throwable -> L8b
                com.google.common.util.concurrent.SequentialExecutor.access$202(r0, r3)     // Catch: java.lang.Throwable -> L8b
                r0 = 1
            L2d:
                com.google.common.util.concurrent.SequentialExecutor r3 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L8b
                java.util.Deque r3 = com.google.common.util.concurrent.SequentialExecutor.access$100(r3)     // Catch: java.lang.Throwable -> L8b
                java.lang.Object r3 = r3.poll()     // Catch: java.lang.Throwable -> L8b
                java.lang.Runnable r3 = (java.lang.Runnable) r3     // Catch: java.lang.Throwable -> L8b
                r9.task = r3     // Catch: java.lang.Throwable -> L8b
                java.lang.Runnable r3 = r9.task     // Catch: java.lang.Throwable -> L8b
                if (r3 != 0) goto L51
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L8b
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r3 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.IDLE     // Catch: java.lang.Throwable -> L8b
                com.google.common.util.concurrent.SequentialExecutor.access$202(r0, r3)     // Catch: java.lang.Throwable -> L8b
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L8b
                if (r1 == 0) goto L50
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L50:
                return
            L51:
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L8b
                boolean r2 = java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L8e
                r1 = r1 | r2
                r2 = 0
                java.lang.Runnable r3 = r9.task     // Catch: java.lang.Throwable -> L60 java.lang.RuntimeException -> L62
                r3.run()     // Catch: java.lang.Throwable -> L60 java.lang.RuntimeException -> L62
            L5d:
                r9.task = r2     // Catch: java.lang.Throwable -> L8e
                goto L2
            L60:
                r0 = move-exception
                goto L88
            L62:
                r3 = move-exception
                java.util.logging.Logger r4 = com.google.common.util.concurrent.SequentialExecutor.log     // Catch: java.lang.Throwable -> L60
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch: java.lang.Throwable -> L60
                java.lang.Runnable r6 = r9.task     // Catch: java.lang.Throwable -> L60
                java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L60
                int r7 = r6.length()     // Catch: java.lang.Throwable -> L60
                int r7 = r7 + 35
                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L60
                r8.<init>(r7)     // Catch: java.lang.Throwable -> L60
                java.lang.String r7 = "Exception while executing runnable "
                r8.append(r7)     // Catch: java.lang.Throwable -> L60
                r8.append(r6)     // Catch: java.lang.Throwable -> L60
                java.lang.String r6 = r8.toString()     // Catch: java.lang.Throwable -> L60
                r4.log(r5, r6, r3)     // Catch: java.lang.Throwable -> L60
                goto L5d
            L88:
                r9.task = r2     // Catch: java.lang.Throwable -> L8e
                throw r0     // Catch: java.lang.Throwable -> L8e
            L8b:
                r0 = move-exception
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L8b
                throw r0     // Catch: java.lang.Throwable -> L8e
            L8e:
                r0 = move-exception
                if (r1 == 0) goto L98
                java.lang.Thread r1 = java.lang.Thread.currentThread()
                r1.interrupt()
            L98:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.SequentialExecutor.QueueWorker.workOnQueue():void");
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                workOnQueue();
            } catch (Error e2) {
                synchronized (SequentialExecutor.this.queue) {
                    SequentialExecutor.this.workerRunningState = WorkerRunningState.IDLE;
                    throw e2;
                }
            }
        }

        public String toString() {
            Runnable runnable = this.task;
            if (runnable != null) {
                String strValueOf = String.valueOf(runnable);
                return a.a(strValueOf.length() + 34, "SequentialExecutorWorker{running=", strValueOf, "}");
            }
            String strValueOf2 = String.valueOf(SequentialExecutor.this.workerRunningState);
            return a.a(strValueOf2.length() + 32, "SequentialExecutorWorker{state=", strValueOf2, "}");
        }

        public /* synthetic */ QueueWorker(AnonymousClass1 anonymousClass1) {
        }
    }

    enum WorkerRunningState {
        IDLE,
        QUEUING,
        QUEUED,
        RUNNING
    }

    public SequentialExecutor(Executor executor) {
        if (executor == null) {
            throw new NullPointerException();
        }
        this.executor = executor;
    }

    public static /* synthetic */ long access$308(SequentialExecutor sequentialExecutor) {
        long j = sequentialExecutor.workerRunCount;
        sequentialExecutor.workerRunCount = 1 + j;
        return j;
    }

    @Override // java.util.concurrent.Executor
    public void execute(final Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException();
        }
        synchronized (this.queue) {
            if (this.workerRunningState != WorkerRunningState.RUNNING && this.workerRunningState != WorkerRunningState.QUEUED) {
                long j = this.workerRunCount;
                Runnable runnable2 = new Runnable(this) { // from class: com.google.common.util.concurrent.SequentialExecutor.1
                    @Override // java.lang.Runnable
                    public void run() {
                        runnable.run();
                    }

                    public String toString() {
                        return runnable.toString();
                    }
                };
                this.queue.add(runnable2);
                this.workerRunningState = WorkerRunningState.QUEUING;
                boolean z = true;
                try {
                    this.executor.execute(this.worker);
                    if (this.workerRunningState != WorkerRunningState.QUEUING) {
                        return;
                    }
                    synchronized (this.queue) {
                        if (this.workerRunCount == j && this.workerRunningState == WorkerRunningState.QUEUING) {
                            this.workerRunningState = WorkerRunningState.QUEUED;
                        }
                    }
                    return;
                } catch (Error | RuntimeException e2) {
                    synchronized (this.queue) {
                        if ((this.workerRunningState != WorkerRunningState.IDLE && this.workerRunningState != WorkerRunningState.QUEUING) || !this.queue.removeLastOccurrence(runnable2)) {
                            z = false;
                        }
                        if (!(e2 instanceof RejectedExecutionException) || z) {
                            throw e2;
                        }
                    }
                    return;
                }
            }
            this.queue.add(runnable);
        }
    }

    public String toString() {
        int iIdentityHashCode = System.identityHashCode(this);
        String strValueOf = String.valueOf(this.executor);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 32);
        sb.append("SequentialExecutor@");
        sb.append(iIdentityHashCode);
        sb.append(IidStore.JSON_ENCODED_PREFIX);
        sb.append(strValueOf);
        sb.append("}");
        return sb.toString();
    }
}
