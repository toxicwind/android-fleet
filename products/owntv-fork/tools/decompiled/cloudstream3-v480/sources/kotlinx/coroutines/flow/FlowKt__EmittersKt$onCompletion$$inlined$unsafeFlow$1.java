package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.internal.SafeCollector;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@¢\u0006\u0002\u0010\u0006¨\u0006\u0007¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1<T> implements Flow<T> {
    final /* synthetic */ Function3 $action$inlined;
    final /* synthetic */ Flow $this_onCompletion$inlined;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1", f = "Emitters.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {113, 120, 127}, m = "collect", n = {"collector", "$completion", "$this$onCompletion_u24lambda_u240", "$i$a$-unsafeFlow-FlowKt__EmittersKt$onCompletion$1", "collector", "$completion", "$this$onCompletion_u24lambda_u240", "e", "$i$a$-unsafeFlow-FlowKt__EmittersKt$onCompletion$1", "collector", "$completion", "$this$onCompletion_u24lambda_u240", "sc", "$i$a$-unsafeFlow-FlowKt__EmittersKt$onCompletion$1"}, s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
    public static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.this.collect(null, this);
        }
    }

    public FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1(Flow flow, Function3 function3) {
        this.$this_onCompletion$inlined = flow;
        this.$action$inlined = function3;
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:47:0x0107  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        int i;
        Continuation continuation2;
        int i2;
        FlowCollector<? super T> flowCollector2;
        FlowCollector<? super T> flowCollector3;
        ThrowingCollector throwingCollector;
        Function3 function3;
        SafeCollector safeCollector;
        SafeCollector safeCollector2;
        Function3 function4;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = anonymousClass1.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            AnonymousClass1 anonymousClass2 = anonymousClass1;
            i = 0;
            try {
                Flow flow = this.$this_onCompletion$inlined;
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(anonymousClass2);
                anonymousClass1.L$2 = flowCollector;
                anonymousClass1.I$0 = 0;
                anonymousClass1.label = 1;
                if (flow.collect(flowCollector, anonymousClass1) != coroutine_suspended) {
                    flowCollector3 = flowCollector;
                    continuation2 = anonymousClass2;
                    safeCollector = new SafeCollector(flowCollector, anonymousClass1.getContext());
                    function4 = this.$action$inlined;
                    anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector3);
                    anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(continuation2);
                    anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(flowCollector);
                    anonymousClass1.L$3 = safeCollector;
                    anonymousClass1.I$0 = i;
                    anonymousClass1.label = 3;
                    if (function4.invoke(safeCollector, null, anonymousClass1) != coroutine_suspended) {
                        safeCollector2 = safeCollector;
                        safeCollector2.releaseIntercepted();
                        return Unit.INSTANCE;
                    }
                }
            } catch (Throwable th) {
                th = th;
                continuation2 = anonymousClass2;
                i2 = 0;
                flowCollector2 = flowCollector;
                throwingCollector = new ThrowingCollector(th);
                function3 = this.$action$inlined;
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(continuation2);
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(flowCollector2);
                anonymousClass1.L$3 = th;
                anonymousClass1.I$0 = i2;
                anonymousClass1.label = 2;
                if (FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(throwingCollector, function3, th, anonymousClass1) != coroutine_suspended) {
                    throw th;
                }
            }
            return coroutine_suspended;
        }
        if (i3 != 1) {
            if (i3 == 2) {
                int i4 = anonymousClass1.I$0;
                Throwable th2 = (Throwable) anonymousClass1.L$3;
                ResultKt.throwOnFailure(obj);
                throw th2;
            }
            if (i3 != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i5 = anonymousClass1.I$0;
            safeCollector2 = (SafeCollector) anonymousClass1.L$3;
            try {
                ResultKt.throwOnFailure(obj);
                safeCollector2.releaseIntercepted();
                return Unit.INSTANCE;
            } catch (Throwable th3) {
                th = th3;
                safeCollector2.releaseIntercepted();
                throw th;
            }
        }
        int i6 = anonymousClass1.I$0;
        flowCollector2 = (FlowCollector) anonymousClass1.L$2;
        continuation2 = (Continuation) anonymousClass1.L$1;
        flowCollector3 = (FlowCollector) anonymousClass1.L$0;
        try {
            ResultKt.throwOnFailure(obj);
            i = i6;
            flowCollector = flowCollector2;
            safeCollector = new SafeCollector(flowCollector, anonymousClass1.getContext());
            try {
                function4 = this.$action$inlined;
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector3);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(continuation2);
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(flowCollector);
                anonymousClass1.L$3 = safeCollector;
                anonymousClass1.I$0 = i;
                anonymousClass1.label = 3;
                if (function4.invoke(safeCollector, null, anonymousClass1) != coroutine_suspended) {
                    safeCollector2 = safeCollector;
                    safeCollector2.releaseIntercepted();
                    return Unit.INSTANCE;
                }
            } catch (Throwable th4) {
                th = th4;
                safeCollector2 = safeCollector;
                safeCollector2.releaseIntercepted();
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            i2 = i6;
            flowCollector = flowCollector3;
            throwingCollector = new ThrowingCollector(th);
            function3 = this.$action$inlined;
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(continuation2);
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(flowCollector2);
            anonymousClass1.L$3 = th;
            anonymousClass1.I$0 = i2;
            anonymousClass1.label = 2;
            if (FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(throwingCollector, function3, th, anonymousClass1) != coroutine_suspended) {
                throw th;
            }
        }
        return coroutine_suspended;
    }
}
