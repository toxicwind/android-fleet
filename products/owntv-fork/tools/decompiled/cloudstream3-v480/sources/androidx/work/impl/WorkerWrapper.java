package androidx.work.impl;

import android.content.Context;
import androidx.core.util.Consumer;
import androidx.work.Clock;
import androidx.work.Configuration;
import androidx.work.Data;
import androidx.work.ForegroundUpdater;
import androidx.work.InputMerger;
import androidx.work.ListenableFutureKt;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.WorkInfo;
import androidx.work.WorkerExceptionInfo;
import androidx.work.WorkerParameters;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.WorkForegroundUpdater;
import androidx.work.impl.utils.WorkProgressUpdater;
import androidx.work.impl.utils.WorkerExceptionUtilsKt;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;

/* JADX INFO: compiled from: WorkerWrapper.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001:\u0002@AB\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*J\u000e\u0010,\u001a\u00020-H\u0082@¢\u0006\u0002\u0010.J\u0010\u0010/\u001a\u00020+2\u0006\u00100\u001a\u000201H\u0002J\u0010\u00102\u001a\u00020+2\u0006\u00100\u001a\u000201H\u0002J\u0010\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0007J\u0010\u00107\u001a\u00020+2\u0006\u00105\u001a\u000206H\u0002J\u0012\u00108\u001a\u00020+2\b\u00100\u001a\u0004\u0018\u000101H\u0002J\b\u00109\u001a\u00020+H\u0002J\u0010\u0010:\u001a\u00020+2\u0006\u00100\u001a\u000201H\u0007J\u0010\u0010;\u001a\u0002042\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010<\u001a\u00020+2\u0006\u00105\u001a\u000206H\u0002J\b\u0010=\u001a\u00020+H\u0002J\u0010\u0010>\u001a\u00020+2\u0006\u00100\u001a\u000201H\u0002J\u0016\u0010?\u001a\u00020\r2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0!H\u0002R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010%\u001a\u00020&8F¢\u0006\u0006\u001a\u0004\b'\u0010(¨\u0006B"}, d2 = {"Landroidx/work/impl/WorkerWrapper;", "", "builder", "Landroidx/work/impl/WorkerWrapper$Builder;", "<init>", "(Landroidx/work/impl/WorkerWrapper$Builder;)V", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "getWorkSpec", "()Landroidx/work/impl/model/WorkSpec;", "appContext", "Landroid/content/Context;", "workSpecId", "", "runtimeExtras", "Landroidx/work/WorkerParameters$RuntimeExtras;", "builderWorker", "Landroidx/work/ListenableWorker;", "workTaskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "configuration", "Landroidx/work/Configuration;", "clock", "Landroidx/work/Clock;", "foregroundProcessor", "Landroidx/work/impl/foreground/ForegroundProcessor;", "workDatabase", "Landroidx/work/impl/WorkDatabase;", "workSpecDao", "Landroidx/work/impl/model/WorkSpecDao;", "dependencyDao", "Landroidx/work/impl/model/DependencyDao;", "tags", "", "workDescription", "workerJob", "Lkotlinx/coroutines/CompletableJob;", "workGenerationalId", "Landroidx/work/impl/model/WorkGenerationalId;", "getWorkGenerationalId", "()Landroidx/work/impl/model/WorkGenerationalId;", "launch", "Lcom/google/common/util/concurrent/ListenableFuture;", "", "runWorker", "Landroidx/work/impl/WorkerWrapper$Resolution;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onWorkFinished", "result", "Landroidx/work/ListenableWorker$Result;", "onWorkFailed", "interrupt", "", "stopReason", "", "resetWorkerStatus", "handleResult", "trySetRunning", "setFailed", "iterativelyFailWorkAndDependents", "reschedule", "resetPeriodic", "setSucceeded", "createWorkDescription", "Resolution", "Builder", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class WorkerWrapper {
    private final Context appContext;
    private final ListenableWorker builderWorker;
    private final Clock clock;
    private final Configuration configuration;
    private final DependencyDao dependencyDao;
    private final ForegroundProcessor foregroundProcessor;
    private final WorkerParameters.RuntimeExtras runtimeExtras;
    private final List<String> tags;
    private final WorkDatabase workDatabase;
    private final String workDescription;
    private final WorkSpec workSpec;
    private final WorkSpecDao workSpecDao;
    private final String workSpecId;
    private final TaskExecutor workTaskExecutor;
    private final CompletableJob workerJob;

    /* JADX INFO: renamed from: androidx.work.impl.WorkerWrapper$runWorker$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: WorkerWrapper.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.WorkerWrapper", f = "WorkerWrapper.kt", i = {0}, l = {296}, m = "runWorker", n = {"params"}, s = {"L$0"})
    static final class C06681 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C06681(Continuation<? super C06681> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WorkerWrapper.this.runWorker(this);
        }
    }

    public WorkerWrapper(Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        WorkSpec workSpec = builder.getWorkSpec();
        this.workSpec = workSpec;
        this.appContext = builder.getAppContext();
        this.workSpecId = workSpec.id;
        this.runtimeExtras = builder.getRuntimeExtras();
        this.builderWorker = builder.getWorker();
        this.workTaskExecutor = builder.getWorkTaskExecutor();
        Configuration configuration = builder.getConfiguration();
        this.configuration = configuration;
        this.clock = configuration.getClock();
        this.foregroundProcessor = builder.getForegroundProcessor();
        WorkDatabase workDatabase = builder.getWorkDatabase();
        this.workDatabase = workDatabase;
        this.workSpecDao = workDatabase.workSpecDao();
        this.dependencyDao = workDatabase.dependencyDao();
        List<String> tags = builder.getTags();
        this.tags = tags;
        this.workDescription = createWorkDescription(tags);
        this.workerJob = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
    }

    public final WorkSpec getWorkSpec() {
        return this.workSpec;
    }

    public final WorkGenerationalId getWorkGenerationalId() {
        return WorkSpecKt.generationalId(this.workSpec);
    }

    /* JADX INFO: renamed from: androidx.work.impl.WorkerWrapper$launch$1, reason: invalid class name */
    /* JADX INFO: compiled from: WorkerWrapper.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.WorkerWrapper$launch$1", f = "WorkerWrapper.kt", i = {}, l = {98}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WorkerWrapper.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final Resolution.Failed failed;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            int i2 = 1;
            ListenableWorker.Result result = null;
            Object[] objArr = 0;
            Object[] objArr2 = 0;
            Object[] objArr3 = 0;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    obj = BuildersKt.withContext(WorkerWrapper.this.workerJob, new WorkerWrapper$launch$1$resolution$1(WorkerWrapper.this, null), this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                failed = (Resolution) obj;
            } catch (WorkerStoppedException e) {
                failed = new Resolution.ResetWorkerStatus(e.getReason());
            } catch (CancellationException unused) {
                failed = new Resolution.Failed(result, i2, objArr3 == true ? 1 : 0);
            } catch (Throwable th) {
                Logger.get().error(WorkerWrapperKt.TAG, "Unexpected error in WorkerWrapper", th);
                failed = new Resolution.Failed(objArr2 == true ? 1 : 0, i2, objArr == true ? 1 : 0);
            }
            WorkDatabase workDatabase = WorkerWrapper.this.workDatabase;
            final WorkerWrapper workerWrapper = WorkerWrapper.this;
            Object objRunInTransaction = workDatabase.runInTransaction((Callable<Object>) new Callable() { // from class: androidx.work.impl.WorkerWrapper$launch$1$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return WorkerWrapper.AnonymousClass1.invokeSuspend$lambda$1(failed, workerWrapper);
                }
            });
            Intrinsics.checkNotNullExpressionValue(objRunInTransaction, "runInTransaction(...)");
            return objRunInTransaction;
        }

        static final Boolean invokeSuspend$lambda$1(Resolution resolution, WorkerWrapper workerWrapper) {
            boolean zResetWorkerStatus;
            if (resolution instanceof Resolution.Finished) {
                zResetWorkerStatus = workerWrapper.onWorkFinished(((Resolution.Finished) resolution).getResult());
            } else if (resolution instanceof Resolution.Failed) {
                zResetWorkerStatus = workerWrapper.onWorkFailed(((Resolution.Failed) resolution).getResult());
            } else {
                if (!(resolution instanceof Resolution.ResetWorkerStatus)) {
                    throw new NoWhenBranchMatchedException();
                }
                zResetWorkerStatus = workerWrapper.resetWorkerStatus(((Resolution.ResetWorkerStatus) resolution).getReason());
            }
            return Boolean.valueOf(zResetWorkerStatus);
        }
    }

    public final ListenableFuture<Boolean> launch() {
        return ListenableFutureKt.launchFuture$default(this.workTaskExecutor.getTaskCoroutineDispatcher().plus(JobKt__JobKt.Job$default((Job) null, 1, (Object) null)), null, new AnonymousClass1(null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: WorkerWrapper.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Resolution;", "", "<init>", "()V", "ResetWorkerStatus", "Failed", "Finished", "Landroidx/work/impl/WorkerWrapper$Resolution$Failed;", "Landroidx/work/impl/WorkerWrapper$Resolution$Finished;", "Landroidx/work/impl/WorkerWrapper$Resolution$ResetWorkerStatus;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    static abstract class Resolution {
        public /* synthetic */ Resolution(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: compiled from: WorkerWrapper.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Resolution$ResetWorkerStatus;", "Landroidx/work/impl/WorkerWrapper$Resolution;", "reason", "", "<init>", "(I)V", "getReason", "()I", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class ResetWorkerStatus extends Resolution {
            private final int reason;

            public ResetWorkerStatus() {
                this(0, 1, null);
            }

            public ResetWorkerStatus(int i) {
                super(null);
                this.reason = i;
            }

            public /* synthetic */ ResetWorkerStatus(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
                this((i2 & 1) != 0 ? -256 : i);
            }

            public final int getReason() {
                return this.reason;
            }
        }

        private Resolution() {
        }

        /* JADX INFO: compiled from: WorkerWrapper.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Resolution$Failed;", "Landroidx/work/impl/WorkerWrapper$Resolution;", "result", "Landroidx/work/ListenableWorker$Result;", "<init>", "(Landroidx/work/ListenableWorker$Result;)V", "getResult", "()Landroidx/work/ListenableWorker$Result;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Failed extends Resolution {
            private final ListenableWorker.Result result;

            /* JADX WARN: Multi-variable type inference failed */
            public Failed() {
                this(null, 1, 0 == true ? 1 : 0);
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Failed(ListenableWorker.Result result) {
                super(null);
                Intrinsics.checkNotNullParameter(result, "result");
                this.result = result;
            }

            public /* synthetic */ Failed(ListenableWorker.Result.Failure failure, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? new ListenableWorker.Result.Failure() : failure);
            }

            public final ListenableWorker.Result getResult() {
                return this.result;
            }
        }

        /* JADX INFO: compiled from: WorkerWrapper.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Resolution$Finished;", "Landroidx/work/impl/WorkerWrapper$Resolution;", "result", "Landroidx/work/ListenableWorker$Result;", "<init>", "(Landroidx/work/ListenableWorker$Result;)V", "getResult", "()Landroidx/work/ListenableWorker$Result;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Finished extends Resolution {
            private final ListenableWorker.Result result;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Finished(ListenableWorker.Result result) {
                super(null);
                Intrinsics.checkNotNullParameter(result, "result");
                this.result = result;
            }

            public final ListenableWorker.Result getResult() {
                return this.result;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:66:0x021d  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Multi-variable type inference failed */
    public final Object runWorker(Continuation<? super Resolution> continuation) {
        C06681 c06681;
        Data dataMerge;
        WorkerParameters workerParameters;
        Consumer<WorkerExceptionInfo> workerExecutionExceptionHandler;
        if (continuation instanceof C06681) {
            c06681 = (C06681) continuation;
            if ((c06681.label & Integer.MIN_VALUE) != 0) {
                c06681.label -= Integer.MIN_VALUE;
            } else {
                c06681 = new C06681(continuation);
            }
        } else {
            c06681 = new C06681(continuation);
        }
        Object objWithContext = c06681.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c06681.label;
        int i2 = 1;
        DefaultConstructorMarker defaultConstructorMarker = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(objWithContext);
                final boolean zIsEnabled = this.configuration.getTracer().isEnabled();
                final String traceTag = this.workSpec.getTraceTag();
                if (zIsEnabled && traceTag != null) {
                    this.configuration.getTracer().beginAsyncSection(traceTag, this.workSpec.hashCode());
                }
                int i3 = 0;
                if (((Boolean) this.workDatabase.runInTransaction(new Callable() { // from class: androidx.work.impl.WorkerWrapper$$ExternalSyntheticLambda0
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return WorkerWrapper.runWorker$lambda$1(this.f$0);
                    }
                })).booleanValue()) {
                    return new Resolution.ResetWorkerStatus(i3, i2, defaultConstructorMarker);
                }
                if (this.workSpec.isPeriodic()) {
                    dataMerge = this.workSpec.input;
                } else {
                    InputMerger inputMergerCreateInputMergerWithDefaultFallback = this.configuration.getInputMergerFactory().createInputMergerWithDefaultFallback(this.workSpec.inputMergerClassName);
                    if (inputMergerCreateInputMergerWithDefaultFallback == null) {
                        String str = WorkerWrapperKt.TAG;
                        Logger.get().error(str, "Could not create Input Merger " + this.workSpec.inputMergerClassName);
                        return new Resolution.Failed(objArr2 == true ? 1 : 0, i2, objArr == true ? 1 : 0);
                    }
                    dataMerge = inputMergerCreateInputMergerWithDefaultFallback.merge(CollectionsKt.plus((Collection) CollectionsKt.listOf(this.workSpec.input), (Iterable) this.workSpecDao.getInputsFromPrerequisites(this.workSpecId)));
                }
                WorkerParameters workerParameters2 = new WorkerParameters(UUID.fromString(this.workSpecId), dataMerge, this.tags, this.runtimeExtras, this.workSpec.runAttemptCount, this.workSpec.getGeneration(), this.configuration.getExecutor(), this.configuration.getWorkerCoroutineContext(), this.workTaskExecutor, this.configuration.getWorkerFactory(), new WorkProgressUpdater(this.workDatabase, this.workTaskExecutor), new WorkForegroundUpdater(this.workDatabase, this.foregroundProcessor, this.workTaskExecutor));
                final ListenableWorker listenableWorkerCreateWorkerWithDefaultFallback = this.builderWorker;
                if (listenableWorkerCreateWorkerWithDefaultFallback == null) {
                    try {
                        listenableWorkerCreateWorkerWithDefaultFallback = this.configuration.getWorkerFactory().createWorkerWithDefaultFallback(this.appContext, this.workSpec.workerClassName, workerParameters2);
                    } catch (Throwable th) {
                        String str2 = WorkerWrapperKt.TAG;
                        Logger.get().error(str2, "Could not create Worker " + this.workSpec.workerClassName);
                        Consumer<WorkerExceptionInfo> workerInitializationExceptionHandler = this.configuration.getWorkerInitializationExceptionHandler();
                        if (workerInitializationExceptionHandler != null) {
                            WorkerExceptionUtilsKt.safeAccept(workerInitializationExceptionHandler, new WorkerExceptionInfo(this.workSpec.workerClassName, workerParameters2, th), WorkerWrapperKt.TAG);
                        }
                        return new Resolution.Failed(null, 1, 0 == true ? 1 : 0);
                    }
                }
                listenableWorkerCreateWorkerWithDefaultFallback.setUsed();
                CoroutineContext.Element element = c06681.getContext().get(Job.INSTANCE);
                Intrinsics.checkNotNull(element);
                Job job = (Job) element;
                job.invokeOnCompletion(new Function1() { // from class: androidx.work.impl.WorkerWrapper$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return WorkerWrapper.runWorker$lambda$4(listenableWorkerCreateWorkerWithDefaultFallback, zIsEnabled, traceTag, this, (Throwable) obj);
                    }
                });
                if (!trySetRunning()) {
                    return new Resolution.ResetWorkerStatus(0, 1, null);
                }
                int i4 = 0;
                int i5 = 1;
                DefaultConstructorMarker defaultConstructorMarker2 = null;
                if (job.isCancelled()) {
                    return new Resolution.ResetWorkerStatus(i4, i5, defaultConstructorMarker2);
                }
                ForegroundUpdater foregroundUpdater = workerParameters2.getForegroundUpdater();
                Intrinsics.checkNotNullExpressionValue(foregroundUpdater, "getForegroundUpdater(...)");
                Executor mainThreadExecutor = this.workTaskExecutor.getMainThreadExecutor();
                Intrinsics.checkNotNullExpressionValue(mainThreadExecutor, "getMainThreadExecutor(...)");
                try {
                    CoroutineDispatcher coroutineDispatcherFrom = ExecutorsKt.from(mainThreadExecutor);
                    WorkerWrapper$runWorker$result$1 workerWrapper$runWorker$result$1 = new WorkerWrapper$runWorker$result$1(this, listenableWorkerCreateWorkerWithDefaultFallback, foregroundUpdater, null);
                    c06681.L$0 = workerParameters2;
                    c06681.label = 1;
                    objWithContext = BuildersKt.withContext(coroutineDispatcherFrom, workerWrapper$runWorker$result$1, c06681);
                    if (objWithContext == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    workerParameters = workerParameters2;
                } catch (Throwable th2) {
                    th = th2;
                    workerParameters = workerParameters2;
                    String str3 = WorkerWrapperKt.TAG;
                    Logger.get().error(str3, this.workDescription + " failed because it threw an exception/error", th);
                    workerExecutionExceptionHandler = this.configuration.getWorkerExecutionExceptionHandler();
                    if (workerExecutionExceptionHandler != null) {
                        WorkerExceptionUtilsKt.safeAccept(workerExecutionExceptionHandler, new WorkerExceptionInfo(this.workSpec.workerClassName, workerParameters, th), WorkerWrapperKt.TAG);
                    }
                    return new Resolution.Failed(null, 1, 0 == true ? 1 : 0);
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                workerParameters = (WorkerParameters) c06681.L$0;
                try {
                    ResultKt.throwOnFailure(objWithContext);
                } catch (Throwable th3) {
                    th = th3;
                    String str4 = WorkerWrapperKt.TAG;
                    Logger.get().error(str4, this.workDescription + " failed because it threw an exception/error", th);
                    workerExecutionExceptionHandler = this.configuration.getWorkerExecutionExceptionHandler();
                    if (workerExecutionExceptionHandler != null) {
                        WorkerExceptionUtilsKt.safeAccept(workerExecutionExceptionHandler, new WorkerExceptionInfo(this.workSpec.workerClassName, workerParameters, th), WorkerWrapperKt.TAG);
                    }
                    return new Resolution.Failed(null, 1, 0 == true ? 1 : 0);
                }
            }
            ListenableWorker.Result result = (ListenableWorker.Result) objWithContext;
            Intrinsics.checkNotNull(result);
            return new Resolution.Finished(result);
        } catch (CancellationException e) {
            Logger.get().info(WorkerWrapperKt.TAG, this.workDescription + " was cancelled", e);
            throw e;
        }
    }

    static final Boolean runWorker$lambda$1(WorkerWrapper workerWrapper) {
        if (workerWrapper.workSpec.state != WorkInfo.State.ENQUEUED) {
            String str = WorkerWrapperKt.TAG;
            Logger.get().debug(str, workerWrapper.workSpec.workerClassName + " is not in ENQUEUED state. Nothing more to do");
            return true;
        }
        if ((workerWrapper.workSpec.isPeriodic() || workerWrapper.workSpec.isBackedOff()) && workerWrapper.clock.currentTimeMillis() < workerWrapper.workSpec.calculateNextRunTime()) {
            Logger.get().debug(WorkerWrapperKt.TAG, "Delaying execution for " + workerWrapper.workSpec.workerClassName + " because it is being executed before schedule.");
            return true;
        }
        return false;
    }

    static final Unit runWorker$lambda$4(ListenableWorker listenableWorker, boolean z, String str, WorkerWrapper workerWrapper, Throwable th) {
        if (th instanceof WorkerStoppedException) {
            listenableWorker.stop(((WorkerStoppedException) th).getReason());
        }
        if (z && str != null) {
            workerWrapper.configuration.getTracer().endAsyncSection(str, workerWrapper.workSpec.hashCode());
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean onWorkFinished(ListenableWorker.Result result) {
        WorkInfo.State state = this.workSpecDao.getState(this.workSpecId);
        this.workDatabase.workProgressDao().delete(this.workSpecId);
        if (state == null) {
            return false;
        }
        if (state == WorkInfo.State.RUNNING) {
            return handleResult(result);
        }
        if (state.isFinished()) {
            return false;
        }
        return reschedule(WorkInfo.STOP_REASON_UNKNOWN);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean onWorkFailed(ListenableWorker.Result result) {
        String str = WorkerWrapperKt.TAG;
        Logger.get().info(str, "Worker result FAILURE for " + this.workDescription);
        if (this.workSpec.isPeriodic()) {
            resetPeriodic();
            return false;
        }
        setFailed(result);
        return false;
    }

    public final void interrupt(int stopReason) {
        this.workerJob.cancel((CancellationException) new WorkerStoppedException(stopReason));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean resetWorkerStatus(int stopReason) {
        if (Intrinsics.areEqual((Object) this.workSpec.getBackOffOnSystemInterruptions(), (Object) true)) {
            String str = WorkerWrapperKt.TAG;
            Logger.get().debug(str, "Worker " + this.workSpec.workerClassName + " was interrupted. Backing off.");
            reschedule(stopReason);
            return true;
        }
        WorkInfo.State state = this.workSpecDao.getState(this.workSpecId);
        if (state == null || state.isFinished()) {
            String str2 = WorkerWrapperKt.TAG;
            Logger.get().debug(str2, "Status for " + this.workSpecId + " is " + state + " ; not doing any work");
            return false;
        }
        String str3 = WorkerWrapperKt.TAG;
        Logger.get().debug(str3, "Status for " + this.workSpecId + " is " + state + "; not doing any work and rescheduling for later execution");
        this.workSpecDao.setState(WorkInfo.State.ENQUEUED, this.workSpecId);
        this.workSpecDao.setStopReason(this.workSpecId, stopReason);
        this.workSpecDao.markWorkSpecScheduled(this.workSpecId, -1L);
        return true;
    }

    private final boolean handleResult(ListenableWorker.Result result) {
        if (result instanceof ListenableWorker.Result.Success) {
            String str = WorkerWrapperKt.TAG;
            Logger.get().info(str, "Worker result SUCCESS for " + this.workDescription);
            if (this.workSpec.isPeriodic()) {
                return resetPeriodic();
            }
            return setSucceeded(result);
        }
        if (result instanceof ListenableWorker.Result.Retry) {
            String str2 = WorkerWrapperKt.TAG;
            Logger.get().info(str2, "Worker result RETRY for " + this.workDescription);
            return reschedule(-256);
        }
        String str3 = WorkerWrapperKt.TAG;
        Logger.get().info(str3, "Worker result FAILURE for " + this.workDescription);
        if (this.workSpec.isPeriodic()) {
            return resetPeriodic();
        }
        if (result == null) {
            result = new ListenableWorker.Result.Failure();
        }
        return setFailed(result);
    }

    private final boolean trySetRunning() {
        Object objRunInTransaction = this.workDatabase.runInTransaction((Callable<Object>) new Callable() { // from class: androidx.work.impl.WorkerWrapper$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return WorkerWrapper.trySetRunning$lambda$14(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(objRunInTransaction, "runInTransaction(...)");
        return ((Boolean) objRunInTransaction).booleanValue();
    }

    static final Boolean trySetRunning$lambda$14(WorkerWrapper workerWrapper) {
        boolean z;
        if (workerWrapper.workSpecDao.getState(workerWrapper.workSpecId) == WorkInfo.State.ENQUEUED) {
            workerWrapper.workSpecDao.setState(WorkInfo.State.RUNNING, workerWrapper.workSpecId);
            workerWrapper.workSpecDao.incrementWorkSpecRunAttemptCount(workerWrapper.workSpecId);
            workerWrapper.workSpecDao.setStopReason(workerWrapper.workSpecId, -256);
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public final boolean setFailed(ListenableWorker.Result result) {
        Intrinsics.checkNotNullParameter(result, "result");
        iterativelyFailWorkAndDependents(this.workSpecId);
        Data outputData = ((ListenableWorker.Result.Failure) result).getOutputData();
        Intrinsics.checkNotNullExpressionValue(outputData, "getOutputData(...)");
        this.workSpecDao.resetWorkSpecNextScheduleTimeOverride(this.workSpecId, this.workSpec.getNextScheduleTimeOverrideGeneration());
        this.workSpecDao.setOutput(this.workSpecId, outputData);
        return false;
    }

    private final void iterativelyFailWorkAndDependents(String workSpecId) {
        List listMutableListOf = CollectionsKt.mutableListOf(workSpecId);
        while (!listMutableListOf.isEmpty()) {
            String str = (String) CollectionsKt.removeLast(listMutableListOf);
            if (this.workSpecDao.getState(str) != WorkInfo.State.CANCELLED) {
                this.workSpecDao.setState(WorkInfo.State.FAILED, str);
            }
            listMutableListOf.addAll(this.dependencyDao.getDependentWorkIds(str));
        }
    }

    private final boolean reschedule(int stopReason) {
        this.workSpecDao.setState(WorkInfo.State.ENQUEUED, this.workSpecId);
        this.workSpecDao.setLastEnqueueTime(this.workSpecId, this.clock.currentTimeMillis());
        this.workSpecDao.resetWorkSpecNextScheduleTimeOverride(this.workSpecId, this.workSpec.getNextScheduleTimeOverrideGeneration());
        this.workSpecDao.markWorkSpecScheduled(this.workSpecId, -1L);
        this.workSpecDao.setStopReason(this.workSpecId, stopReason);
        return true;
    }

    private final boolean resetPeriodic() {
        this.workSpecDao.setLastEnqueueTime(this.workSpecId, this.clock.currentTimeMillis());
        this.workSpecDao.setState(WorkInfo.State.ENQUEUED, this.workSpecId);
        this.workSpecDao.resetWorkSpecRunAttemptCount(this.workSpecId);
        this.workSpecDao.resetWorkSpecNextScheduleTimeOverride(this.workSpecId, this.workSpec.getNextScheduleTimeOverrideGeneration());
        this.workSpecDao.incrementPeriodCount(this.workSpecId);
        this.workSpecDao.markWorkSpecScheduled(this.workSpecId, -1L);
        return false;
    }

    private final boolean setSucceeded(ListenableWorker.Result result) {
        this.workSpecDao.setState(WorkInfo.State.SUCCEEDED, this.workSpecId);
        Intrinsics.checkNotNull(result, "null cannot be cast to non-null type androidx.work.ListenableWorker.Result.Success");
        Data outputData = ((ListenableWorker.Result.Success) result).getOutputData();
        Intrinsics.checkNotNullExpressionValue(outputData, "getOutputData(...)");
        this.workSpecDao.setOutput(this.workSpecId, outputData);
        long jCurrentTimeMillis = this.clock.currentTimeMillis();
        for (String str : this.dependencyDao.getDependentWorkIds(this.workSpecId)) {
            if (this.workSpecDao.getState(str) == WorkInfo.State.BLOCKED && this.dependencyDao.hasCompletedAllPrerequisites(str)) {
                String str2 = WorkerWrapperKt.TAG;
                Logger.get().info(str2, "Setting status to enqueued for " + str);
                this.workSpecDao.setState(WorkInfo.State.ENQUEUED, str);
                this.workSpecDao.setLastEnqueueTime(str, jCurrentTimeMillis);
            }
        }
        return false;
    }

    private final String createWorkDescription(List<String> tags) {
        return "Work [ id=" + this.workSpecId + ", tags={ " + CollectionsKt.joinToString$default(tags, ",", null, null, 0, null, null, 62, null) + " } ]";
    }

    /* JADX INFO: compiled from: WorkerWrapper.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001BG\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010.\u001a\u00020\u00002\b\u0010(\u001a\u0004\u0018\u00010)J\u0010\u0010/\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020#H\u0007J\u0006\u00100\u001a\u000201R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001c\u0010\"\u001a\u0004\u0018\u00010#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-¨\u00062"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Builder;", "", "context", "Landroid/content/Context;", "configuration", "Landroidx/work/Configuration;", "workTaskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "foregroundProcessor", "Landroidx/work/impl/foreground/ForegroundProcessor;", "workDatabase", "Landroidx/work/impl/WorkDatabase;", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "tags", "", "", "<init>", "(Landroid/content/Context;Landroidx/work/Configuration;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;Landroidx/work/impl/foreground/ForegroundProcessor;Landroidx/work/impl/WorkDatabase;Landroidx/work/impl/model/WorkSpec;Ljava/util/List;)V", "getConfiguration", "()Landroidx/work/Configuration;", "getWorkTaskExecutor", "()Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "getForegroundProcessor", "()Landroidx/work/impl/foreground/ForegroundProcessor;", "getWorkDatabase", "()Landroidx/work/impl/WorkDatabase;", "getWorkSpec", "()Landroidx/work/impl/model/WorkSpec;", "getTags", "()Ljava/util/List;", "appContext", "getAppContext", "()Landroid/content/Context;", "worker", "Landroidx/work/ListenableWorker;", "getWorker", "()Landroidx/work/ListenableWorker;", "setWorker", "(Landroidx/work/ListenableWorker;)V", "runtimeExtras", "Landroidx/work/WorkerParameters$RuntimeExtras;", "getRuntimeExtras", "()Landroidx/work/WorkerParameters$RuntimeExtras;", "setRuntimeExtras", "(Landroidx/work/WorkerParameters$RuntimeExtras;)V", "withRuntimeExtras", "withWorker", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Landroidx/work/impl/WorkerWrapper;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Builder {
        private final Context appContext;
        private final Configuration configuration;
        private final ForegroundProcessor foregroundProcessor;
        private WorkerParameters.RuntimeExtras runtimeExtras;
        private final List<String> tags;
        private final WorkDatabase workDatabase;
        private final WorkSpec workSpec;
        private final TaskExecutor workTaskExecutor;
        private ListenableWorker worker;

        public Builder(Context context, Configuration configuration, TaskExecutor workTaskExecutor, ForegroundProcessor foregroundProcessor, WorkDatabase workDatabase, WorkSpec workSpec, List<String> tags) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(configuration, "configuration");
            Intrinsics.checkNotNullParameter(workTaskExecutor, "workTaskExecutor");
            Intrinsics.checkNotNullParameter(foregroundProcessor, "foregroundProcessor");
            Intrinsics.checkNotNullParameter(workDatabase, "workDatabase");
            Intrinsics.checkNotNullParameter(workSpec, "workSpec");
            Intrinsics.checkNotNullParameter(tags, "tags");
            this.configuration = configuration;
            this.workTaskExecutor = workTaskExecutor;
            this.foregroundProcessor = foregroundProcessor;
            this.workDatabase = workDatabase;
            this.workSpec = workSpec;
            this.tags = tags;
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            this.appContext = applicationContext;
            this.runtimeExtras = new WorkerParameters.RuntimeExtras();
        }

        public final Configuration getConfiguration() {
            return this.configuration;
        }

        public final TaskExecutor getWorkTaskExecutor() {
            return this.workTaskExecutor;
        }

        public final ForegroundProcessor getForegroundProcessor() {
            return this.foregroundProcessor;
        }

        public final WorkDatabase getWorkDatabase() {
            return this.workDatabase;
        }

        public final WorkSpec getWorkSpec() {
            return this.workSpec;
        }

        public final List<String> getTags() {
            return this.tags;
        }

        public final Context getAppContext() {
            return this.appContext;
        }

        public final ListenableWorker getWorker() {
            return this.worker;
        }

        public final void setWorker(ListenableWorker listenableWorker) {
            this.worker = listenableWorker;
        }

        public final WorkerParameters.RuntimeExtras getRuntimeExtras() {
            return this.runtimeExtras;
        }

        public final void setRuntimeExtras(WorkerParameters.RuntimeExtras runtimeExtras) {
            Intrinsics.checkNotNullParameter(runtimeExtras, "<set-?>");
            this.runtimeExtras = runtimeExtras;
        }

        public final Builder withRuntimeExtras(WorkerParameters.RuntimeExtras runtimeExtras) {
            if (runtimeExtras != null) {
                this.runtimeExtras = runtimeExtras;
            }
            return this;
        }

        public final Builder withWorker(ListenableWorker worker) {
            Intrinsics.checkNotNullParameter(worker, "worker");
            this.worker = worker;
            return this;
        }

        public final WorkerWrapper build() {
            return new WorkerWrapper(this);
        }
    }
}
