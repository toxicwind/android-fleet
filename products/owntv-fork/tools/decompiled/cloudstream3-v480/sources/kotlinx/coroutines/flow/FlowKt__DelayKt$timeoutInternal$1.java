package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: Delay.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\n"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;", "downStream", "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$timeoutInternal$1", f = "Delay.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {413}, m = "invokeSuspend", n = {"$this$scopedFlow", "downStream", "values", "$this$select_u24lambda_u240$iv$iv", "$i$f$whileSelect", "$i$f$select", "$i$a$-run-SelectKt$select$2$iv$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "I$1", "I$2"}, v = 1)
final class FlowKt__DelayKt$timeoutInternal$1<T> extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {

    /* JADX INFO: renamed from: $$v$c$kotlin-time-Duration$-timeout$0, reason: not valid java name */
    final /* synthetic */ long f80$$v$c$kotlintimeDuration$timeout$0;
    final /* synthetic */ Flow<T> $this_timeoutInternal;
    int I$0;
    int I$1;
    int I$2;
    long J$0;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    FlowKt__DelayKt$timeoutInternal$1(long j, Flow<? extends T> flow, Continuation<? super FlowKt__DelayKt$timeoutInternal$1> continuation) {
        super(3, continuation);
        this.f80$$v$c$kotlintimeDuration$timeout$0 = j;
        this.$this_timeoutInternal = flow;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        FlowKt__DelayKt$timeoutInternal$1 flowKt__DelayKt$timeoutInternal$1 = new FlowKt__DelayKt$timeoutInternal$1(this.f80$$v$c$kotlintimeDuration$timeout$0, this.$this_timeoutInternal, continuation);
        flowKt__DelayKt$timeoutInternal$1.L$0 = coroutineScope;
        flowKt__DelayKt$timeoutInternal$1.L$1 = flowCollector;
        return flowKt__DelayKt$timeoutInternal$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0091 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:16:0x009a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x008f -> B:14:0x0092). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:0:?
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = r13.L$0
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            java.lang.Object r1 = r13.L$1
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r13.label
            r4 = 1
            r5 = 0
            r6 = 0
            if (r3 == 0) goto L2d
            if (r3 != r4) goto L25
            int r3 = r13.I$0
            long r7 = r13.J$0
            java.lang.Object r9 = r13.L$3
            kotlinx.coroutines.selects.SelectImplementation r9 = (kotlinx.coroutines.selects.SelectImplementation) r9
            java.lang.Object r9 = r13.L$2
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            kotlin.ResultKt.throwOnFailure(r14)
            goto L92
        L25:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L2d:
            kotlin.ResultKt.throwOnFailure(r14)
            long r7 = r13.f80$$v$c$kotlintimeDuration$timeout$0
            kotlin.time.Duration$Companion r14 = kotlin.time.Duration.INSTANCE
            long r9 = r14.m10413getZEROUwyO8pc()
            int r14 = kotlin.time.Duration.m10317compareToLRDsOJo(r7, r9)
            if (r14 <= 0) goto L9d
            kotlinx.coroutines.flow.Flow<T> r14 = r13.$this_timeoutInternal
            r3 = 2
            kotlinx.coroutines.flow.Flow r14 = kotlinx.coroutines.flow.FlowKt.buffer$default(r14, r6, r5, r3, r5)
            kotlinx.coroutines.channels.ReceiveChannel r14 = kotlinx.coroutines.flow.FlowKt.produceIn(r14, r0)
            long r7 = r13.f80$$v$c$kotlintimeDuration$timeout$0
            r9 = r14
            r3 = r6
        L4d:
            kotlinx.coroutines.selects.SelectImplementation r14 = new kotlinx.coroutines.selects.SelectImplementation
            kotlin.coroutines.CoroutineContext r10 = r13.get$context()
            r14.<init>(r10)
            r10 = r14
            kotlinx.coroutines.selects.SelectBuilder r10 = (kotlinx.coroutines.selects.SelectBuilder) r10
            kotlinx.coroutines.selects.SelectClause1 r11 = r9.getOnReceiveCatching()
            kotlinx.coroutines.flow.FlowKt__DelayKt$timeoutInternal$1$1$1 r12 = new kotlinx.coroutines.flow.FlowKt__DelayKt$timeoutInternal$1$1$1
            r12.<init>(r1, r5)
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            r10.invoke(r11, r12)
            kotlinx.coroutines.flow.FlowKt__DelayKt$timeoutInternal$1$1$2 r11 = new kotlinx.coroutines.flow.FlowKt__DelayKt$timeoutInternal$1$1$2
            r11.<init>(r7, r5)
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            kotlinx.coroutines.selects.OnTimeoutKt.m10554onTimeout8Mi8wO0(r10, r7, r11)
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r13.L$0 = r10
            r13.L$1 = r1
            r13.L$2 = r9
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)
            r13.L$3 = r10
            r13.J$0 = r7
            r13.I$0 = r3
            r13.I$1 = r6
            r13.I$2 = r6
            r13.label = r4
            java.lang.Object r14 = r14.doSelect(r13)
            if (r14 != r2) goto L92
            return r2
        L92:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 != 0) goto L4d
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        L9d:
            kotlinx.coroutines.TimeoutCancellationException r13 = new kotlinx.coroutines.TimeoutCancellationException
            java.lang.String r14 = "Timed out immediately"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt$timeoutInternal$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
