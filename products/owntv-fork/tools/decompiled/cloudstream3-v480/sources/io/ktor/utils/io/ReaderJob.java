package io.ktor.utils.io;

import androidx.tvprovider.media.tv.TvContractCompat;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: ByteReadChannelOperations.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bH\u0087@¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lio/ktor/utils/io/ReaderJob;", "Lio/ktor/utils/io/ChannelJob;", "Lio/ktor/utils/io/ByteWriteChannel;", TvContractCompat.PARAM_CHANNEL, "Lkotlinx/coroutines/Job;", "job", "<init>", "(Lio/ktor/utils/io/ByteWriteChannel;Lkotlinx/coroutines/Job;)V", "", "flushAndClose", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/ktor/utils/io/ByteWriteChannel;", "getChannel", "()Lio/ktor/utils/io/ByteWriteChannel;", "Lkotlinx/coroutines/Job;", "getJob", "()Lkotlinx/coroutines/Job;", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ReaderJob implements ChannelJob {
    private final ByteWriteChannel channel;
    private final Job job;

    /* JADX INFO: renamed from: io.ktor.utils.io.ReaderJob$flushAndClose$1, reason: invalid class name */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ReaderJob", f = "ByteReadChannelOperations.kt", i = {0, 0, 0, 0, 0}, l = {326, 328}, m = "flushAndClose", n = {"$this$forEach$iv", "element$iv", "it", "$i$f$forEach", "$i$a$-forEach-ReaderJob$flushAndClose$2"}, s = {"L$0", "L$2", "L$3", "I$0", "I$1"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ReaderJob.this.flushAndClose(this);
        }
    }

    public ReaderJob(ByteWriteChannel channel, Job job) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        Intrinsics.checkNotNullParameter(job, "job");
        this.channel = channel;
        this.job = job;
    }

    public final ByteWriteChannel getChannel() {
        return this.channel;
    }

    @Override // io.ktor.utils.io.ChannelJob
    public Job getJob() {
        return this.job;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a8, code lost:
    
        if (r11.flushAndClose(r0) == r1) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object flushAndClose(kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r11 = this;
            boolean r0 = r12 instanceof io.ktor.utils.io.ReaderJob.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r12
            io.ktor.utils.io.ReaderJob$flushAndClose$1 r0 = (io.ktor.utils.io.ReaderJob.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            io.ktor.utils.io.ReaderJob$flushAndClose$1 r0 = new io.ktor.utils.io.ReaderJob$flushAndClose$1
            r0.<init>(r12)
        L19:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L4e
            if (r2 == r5) goto L38
            if (r2 != r4) goto L30
            kotlin.ResultKt.throwOnFailure(r12)
            goto Lab
        L30:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L38:
            int r2 = r0.I$1
            int r2 = r0.I$0
            java.lang.Object r7 = r0.L$3
            kotlinx.coroutines.Job r7 = (kotlinx.coroutines.Job) r7
            java.lang.Object r7 = r0.L$2
            java.lang.Object r7 = r0.L$1
            java.util.Iterator r7 = (java.util.Iterator) r7
            java.lang.Object r8 = r0.L$0
            kotlin.sequences.Sequence r8 = (kotlin.sequences.Sequence) r8
            kotlin.ResultKt.throwOnFailure(r12)
            goto L67
        L4e:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlinx.coroutines.Job r12 = r11.getJob()
            kotlinx.coroutines.JobKt.cancelChildren$default(r12, r6, r5, r6)
            kotlinx.coroutines.Job r12 = r11.getJob()
            kotlin.sequences.Sequence r12 = r12.getChildren()
            java.util.Iterator r2 = r12.iterator()
            r8 = r12
            r7 = r2
            r2 = r3
        L67:
            boolean r12 = r7.hasNext()
            if (r12 == 0) goto L98
            java.lang.Object r12 = r7.next()
            r9 = r12
            kotlinx.coroutines.Job r9 = (kotlinx.coroutines.Job) r9
            kotlinx.coroutines.Job.CC.cancel$default(r9, r6, r5, r6)
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r0.L$0 = r10
            r0.L$1 = r7
            java.lang.Object r12 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r0.L$2 = r12
            java.lang.Object r12 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r0.L$3 = r12
            r0.I$0 = r2
            r0.I$1 = r3
            r0.label = r5
            java.lang.Object r12 = r9.join(r0)
            if (r12 != r1) goto L67
            goto Laa
        L98:
            io.ktor.utils.io.ByteWriteChannel r11 = r11.channel
            r0.L$0 = r6
            r0.L$1 = r6
            r0.L$2 = r6
            r0.L$3 = r6
            r0.label = r4
            java.lang.Object r11 = r11.flushAndClose(r0)
            if (r11 != r1) goto Lab
        Laa:
            return r1
        Lab:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ReaderJob.flushAndClose(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
