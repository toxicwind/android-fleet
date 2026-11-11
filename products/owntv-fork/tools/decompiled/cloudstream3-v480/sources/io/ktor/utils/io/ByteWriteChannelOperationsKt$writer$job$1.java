package io.ktor.utils.io;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.JobKt;
import org.mozilla.classfile.ByteCode;

/* JADX INFO: compiled from: ByteWriteChannelOperations.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.ByteWriteChannelOperationsKt$writer$job$1", f = "ByteWriteChannelOperations.kt", i = {0, 0, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 5, 5, 6, 6, 6, 6}, l = {185, ByteCode.MONITOREXIT, 196, ByteCode.MONITOREXIT, 196, ByteCode.MONITOREXIT, 196}, m = "invokeSuspend", n = {"$this$launch", "nested", "$this$launch", "nested", "$this$launch", "nested", "$this$invokeSuspend_u24lambda_u240", "$i$a$-runCatching-ByteWriteChannelOperationsKt$writer$job$1$1", "$this$launch", "nested", "$this$launch", "nested", "$this$invokeSuspend_u24lambda_u240", "$i$a$-runCatching-ByteWriteChannelOperationsKt$writer$job$1$1", "$this$launch", "nested", "$this$launch", "nested", "$this$invokeSuspend_u24lambda_u240", "$i$a$-runCatching-ByteWriteChannelOperationsKt$writer$job$1$1"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$0", "L$1", "L$3", "I$0"}, v = 1)
final class ByteWriteChannelOperationsKt$writer$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<WriterScope, Continuation<? super Unit>, Object> $block;
    final /* synthetic */ ByteChannel $channel;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    ByteWriteChannelOperationsKt$writer$job$1(Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object> function2, ByteChannel byteChannel, Continuation<? super ByteWriteChannelOperationsKt$writer$job$1> continuation) {
        super(2, continuation);
        this.$block = function2;
        this.$channel = byteChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ByteWriteChannelOperationsKt$writer$job$1 byteWriteChannelOperationsKt$writer$job$1 = new ByteWriteChannelOperationsKt$writer$job$1(this.$block, this.$channel, continuation);
        byteWriteChannelOperationsKt$writer$job$1.L$0 = obj;
        return byteWriteChannelOperationsKt$writer$job$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ByteWriteChannelOperationsKt$writer$job$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:32:0x00b6 A[Catch: all -> 0x006c, TRY_LEAVE, TryCatch #5 {all -> 0x006c, blocks: (B:21:0x0068, B:30:0x00a5, B:32:0x00b6), top: B:85:0x0068 }] */
    /* JADX WARN: Code duplicated, block: B:36:0x00df A[PHI: r2
      0x00df: PHI (r2v18 kotlinx.coroutines.CompletableJob) = (r2v15 kotlinx.coroutines.CompletableJob), (r2v21 kotlinx.coroutines.CompletableJob) binds: [B:34:0x00db, B:19:0x005b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:39:0x0100  */
    /* JADX WARN: Code duplicated, block: B:51:0x0138  */
    /* JADX WARN: Code duplicated, block: B:55:0x015a  */
    /* JADX WARN: Code duplicated, block: B:68:0x019d  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        CompletableJob completableJob;
        Throwable th;
        CompletableJob completableJob2;
        CompletableJob completableJob3;
        ByteChannel byteChannel;
        Unit unit;
        ByteChannel byteChannel2;
        ByteChannel byteChannel3;
        Throwable th2;
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    CompletableJob completableJobJob = JobKt.Job(JobKt.getJob(coroutineScope.getCoroutineContext()));
                    try {
                        Function2<WriterScope, Continuation<? super Unit>, Object> function2 = this.$block;
                        WriterScope writerScope = new WriterScope(this.$channel, coroutineScope.getCoroutineContext().plus(completableJobJob));
                        this.L$0 = coroutineScope;
                        this.L$1 = completableJobJob;
                        this.label = 1;
                        if (function2.invoke(writerScope, this) != coroutine_suspended) {
                            completableJob2 = completableJobJob;
                            completableJob2.complete();
                            if (JobKt.getJob(coroutineScope.getCoroutineContext()).isCancelled()) {
                                this.$channel.cancel(JobKt.getJob(coroutineScope.getCoroutineContext()).getCancellationException());
                            }
                            this.L$0 = coroutineScope;
                            this.L$1 = SpillingKt.nullOutSpilledVariable(completableJob2);
                            this.label = 2;
                            if (completableJob2.join(this) != coroutine_suspended) {
                                byteChannel = this.$channel;
                                Result.Companion companion = Result.INSTANCE;
                                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                this.L$1 = SpillingKt.nullOutSpilledVariable(completableJob2);
                                this.L$2 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                this.I$0 = 0;
                                this.label = 3;
                                if (byteChannel.flushAndClose(this) == coroutine_suspended) {
                                }
                                unit = Unit.INSTANCE;
                                Result.m8936constructorimpl(unit);
                                return Unit.INSTANCE;
                            }
                        }
                        break;
                    } catch (Throwable th3) {
                        completableJob = completableJobJob;
                        th = th3;
                        try {
                            JobKt.cancel(completableJob, "Exception thrown while writing to channel", th);
                            this.$channel.cancel(th);
                            this.L$0 = coroutineScope;
                            this.L$1 = SpillingKt.nullOutSpilledVariable(completableJob);
                            this.label = 4;
                            if (completableJob.join(this) != coroutine_suspended) {
                                completableJob3 = completableJob;
                                byteChannel2 = this.$channel;
                                Result.Companion companion2 = Result.INSTANCE;
                                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                this.L$1 = SpillingKt.nullOutSpilledVariable(completableJob3);
                                this.L$2 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                this.I$0 = 0;
                                this.label = 5;
                                if (byteChannel2.flushAndClose(this) == coroutine_suspended) {
                                }
                                unit = Unit.INSTANCE;
                                Result.m8936constructorimpl(unit);
                                return Unit.INSTANCE;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            this.L$0 = coroutineScope;
                            this.L$1 = SpillingKt.nullOutSpilledVariable(completableJob);
                            this.L$2 = th;
                            this.label = 6;
                            if (completableJob.join(this) != coroutine_suspended) {
                                byteChannel3 = this.$channel;
                                try {
                                    Result.Companion companion3 = Result.INSTANCE;
                                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                    this.L$1 = SpillingKt.nullOutSpilledVariable(completableJob);
                                    this.L$2 = th;
                                    this.L$3 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                    this.I$0 = 0;
                                    this.label = 7;
                                    if (byteChannel3.flushAndClose(this) != coroutine_suspended) {
                                        th2 = th;
                                        Result.m8936constructorimpl(Unit.INSTANCE);
                                        throw th2;
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                    th2 = th;
                                    Result.Companion companion4 = Result.INSTANCE;
                                    Result.m8936constructorimpl(ResultKt.createFailure(th));
                                    throw th2;
                                }
                            }
                        }
                    }
                    return coroutine_suspended;
                case 1:
                    completableJob2 = (CompletableJob) this.L$1;
                    try {
                        ResultKt.throwOnFailure(obj);
                        completableJob2.complete();
                        if (JobKt.getJob(coroutineScope.getCoroutineContext()).isCancelled()) {
                            this.$channel.cancel(JobKt.getJob(coroutineScope.getCoroutineContext()).getCancellationException());
                        }
                        this.L$0 = coroutineScope;
                        this.L$1 = SpillingKt.nullOutSpilledVariable(completableJob2);
                        this.label = 2;
                        if (completableJob2.join(this) != coroutine_suspended) {
                            byteChannel = this.$channel;
                            Result.Companion companion5 = Result.INSTANCE;
                            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                            this.L$1 = SpillingKt.nullOutSpilledVariable(completableJob2);
                            this.L$2 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                            this.I$0 = 0;
                            this.label = 3;
                            if (byteChannel.flushAndClose(this) == coroutine_suspended) {
                            }
                            unit = Unit.INSTANCE;
                            Result.m8936constructorimpl(unit);
                            return Unit.INSTANCE;
                        }
                        break;
                    } catch (Throwable th6) {
                        th = th6;
                        completableJob = completableJob2;
                        JobKt.cancel(completableJob, "Exception thrown while writing to channel", th);
                        this.$channel.cancel(th);
                        this.L$0 = coroutineScope;
                        this.L$1 = SpillingKt.nullOutSpilledVariable(completableJob);
                        this.label = 4;
                        if (completableJob.join(this) != coroutine_suspended) {
                            completableJob3 = completableJob;
                            byteChannel2 = this.$channel;
                            Result.Companion companion6 = Result.INSTANCE;
                            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                            this.L$1 = SpillingKt.nullOutSpilledVariable(completableJob3);
                            this.L$2 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                            this.I$0 = 0;
                            this.label = 5;
                            if (byteChannel2.flushAndClose(this) == coroutine_suspended) {
                            }
                            unit = Unit.INSTANCE;
                            Result.m8936constructorimpl(unit);
                            return Unit.INSTANCE;
                        }
                    }
                    return coroutine_suspended;
                case 2:
                    completableJob2 = (CompletableJob) this.L$1;
                    ResultKt.throwOnFailure(obj);
                    byteChannel = this.$channel;
                    Result.Companion companion7 = Result.INSTANCE;
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(completableJob2);
                    this.L$2 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.I$0 = 0;
                    this.label = 3;
                    if (byteChannel.flushAndClose(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    unit = Unit.INSTANCE;
                    Result.m8936constructorimpl(unit);
                    return Unit.INSTANCE;
                case 3:
                    ResultKt.throwOnFailure(obj);
                    unit = Unit.INSTANCE;
                    Result.m8936constructorimpl(unit);
                    return Unit.INSTANCE;
                case 4:
                    completableJob3 = (CompletableJob) this.L$1;
                    ResultKt.throwOnFailure(obj);
                    byteChannel2 = this.$channel;
                    Result.Companion companion8 = Result.INSTANCE;
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(completableJob3);
                    this.L$2 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.I$0 = 0;
                    this.label = 5;
                    if (byteChannel2.flushAndClose(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    unit = Unit.INSTANCE;
                    Result.m8936constructorimpl(unit);
                    return Unit.INSTANCE;
                case 5:
                    ResultKt.throwOnFailure(obj);
                    unit = Unit.INSTANCE;
                    Result.m8936constructorimpl(unit);
                    return Unit.INSTANCE;
                case 6:
                    Throwable th7 = (Throwable) this.L$2;
                    completableJob = (CompletableJob) this.L$1;
                    ResultKt.throwOnFailure(obj);
                    th = th7;
                    byteChannel3 = this.$channel;
                    Result.Companion companion9 = Result.INSTANCE;
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(completableJob);
                    this.L$2 = th;
                    this.L$3 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.I$0 = 0;
                    this.label = 7;
                    if (byteChannel3.flushAndClose(this) != coroutine_suspended) {
                        th2 = th;
                        Result.m8936constructorimpl(Unit.INSTANCE);
                        throw th2;
                    }
                    return coroutine_suspended;
                case 7:
                    th2 = (Throwable) this.L$2;
                    try {
                        ResultKt.throwOnFailure(obj);
                        Result.m8936constructorimpl(Unit.INSTANCE);
                        throw th2;
                    } catch (Throwable th8) {
                        th = th8;
                        Result.Companion companion10 = Result.INSTANCE;
                        Result.m8936constructorimpl(ResultKt.createFailure(th));
                        throw th2;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th9) {
            Result.Companion companion11 = Result.INSTANCE;
            Result.m8936constructorimpl(ResultKt.createFailure(th9));
        }
    }
}
