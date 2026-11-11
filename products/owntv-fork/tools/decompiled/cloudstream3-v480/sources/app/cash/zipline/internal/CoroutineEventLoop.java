package app.cash.zipline.internal;

import app.cash.zipline.internal.bridge.ThrowablesKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: CoroutineEventLoop.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0013B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\fJ\u000e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f\u0012\b\u0012\u00060\rR\u00020\u00000\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lapp/cash/zipline/internal/CoroutineEventLoop;", "", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "guestService", "Lapp/cash/zipline/internal/GuestService;", "<init>", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineScope;Lapp/cash/zipline/internal/GuestService;)V", "jobs", "", "", "Lapp/cash/zipline/internal/CoroutineEventLoop$DelayedJob;", "setTimeout", "", "timeoutId", "delayMillis", "clearTimeout", "DelayedJob", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CoroutineEventLoop {
    private final CoroutineDispatcher dispatcher;
    private final GuestService guestService;
    private final Map<Integer, DelayedJob> jobs;
    private final CoroutineScope scope;

    public CoroutineEventLoop(CoroutineDispatcher dispatcher, CoroutineScope scope, GuestService guestService) {
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(guestService, "guestService");
        this.dispatcher = dispatcher;
        this.scope = scope;
        this.guestService = guestService;
        this.jobs = new LinkedHashMap();
    }

    public final void setTimeout(int timeoutId, int delayMillis) {
        DelayedJob delayedJob = new DelayedJob(timeoutId, delayMillis);
        this.jobs.put(Integer.valueOf(timeoutId), delayedJob);
        this.dispatcher.mo10542dispatch(this.scope.getCoroutineContext(), delayedJob);
    }

    public final void clearTimeout(int timeoutId) {
        DelayedJob delayedJobRemove = this.jobs.remove(Integer.valueOf(timeoutId));
        if (delayedJobRemove != null) {
            delayedJobRemove.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: CoroutineEventLoop.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0006\u0010\u0019\u001a\u00020\u0018R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u001a"}, d2 = {"Lapp/cash/zipline/internal/CoroutineEventLoop$DelayedJob;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "timeoutId", "", "delayMillis", "<init>", "(Lapp/cash/zipline/internal/CoroutineEventLoop;II)V", "getTimeoutId", "()I", "getDelayMillis", "canceled", "", "getCanceled", "()Z", "setCanceled", "(Z)V", "job", "Lkotlinx/coroutines/Job;", "getJob", "()Lkotlinx/coroutines/Job;", "setJob", "(Lkotlinx/coroutines/Job;)V", "run", "", "cancel", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    final class DelayedJob implements Runnable {
        private boolean canceled;
        private final int delayMillis;
        private Job job;
        private final int timeoutId;

        public DelayedJob(int i, int i2) {
            this.timeoutId = i;
            this.delayMillis = i2;
        }

        public final int getTimeoutId() {
            return this.timeoutId;
        }

        public final int getDelayMillis() {
            return this.delayMillis;
        }

        public final boolean getCanceled() {
            return this.canceled;
        }

        public final void setCanceled(boolean z) {
            this.canceled = z;
        }

        public final Job getJob() {
            return this.job;
        }

        public final void setJob(Job job) {
            this.job = job;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.canceled) {
                return;
            }
            this.job = BuildersKt__Builders_commonKt.launch$default(CoroutineEventLoop.this.scope, null, CoroutineStart.UNDISPATCHED, new CoroutineEventLoop$DelayedJob$run$1(this, CoroutineEventLoop.this, null), 1, null);
        }

        public final void cancel() {
            this.canceled = true;
            Job job = this.job;
            if (job != null) {
                job.cancel(ThrowablesKt.getTheOnlyCancellationException());
            }
        }
    }
}
