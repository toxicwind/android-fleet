package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: Emitters.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n¨\u0006\u0004"}, d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$transform$1"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ShareKt$asFlow$$inlined$transform$1", f = "Share.kt", i = {0}, l = {36}, m = "invokeSuspend", n = {"$this$flow"}, s = {"L$0"}, v = 1)
public final class FlowKt__ShareKt$asFlow$$inlined$transform$1<T> extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow $this_transform;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ShareKt$asFlow$$inlined$transform$1(Flow flow, Continuation continuation) {
        super(2, continuation);
        this.$this_transform = flow;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FlowKt__ShareKt$asFlow$$inlined$transform$1 flowKt__ShareKt$asFlow$$inlined$transform$1 = new FlowKt__ShareKt$asFlow$$inlined$transform$1(this.$this_transform, continuation);
        flowKt__ShareKt$asFlow$$inlined$transform$1.L$0 = obj;
        return flowKt__ShareKt$asFlow$$inlined$transform$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        return ((FlowKt__ShareKt$asFlow$$inlined$transform$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ShareKt$asFlow$$inlined$transform$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: Emitters.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class AnonymousClass1<T> implements FlowCollector {
        final /* synthetic */ FlowCollector<T> $$this$flow;

        /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ShareKt$asFlow$$inlined$transform$1$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ShareKt$asFlow$$inlined$transform$1$1", f = "Share.kt", i = {0, 0, 0, 0, 0}, l = {39}, m = "emit", n = {ES6Iterator.VALUE_PROPERTY, "$completion", ES6Iterator.VALUE_PROPERTY, "$this$asFlow_u24lambda_u240", "$i$a$-transform-FlowKt__ShareKt$asFlow$1"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
        public static final class C01911 extends ContinuationImpl {
            int I$0;
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int label;
            /* synthetic */ Object result;

            public C01911(Continuation continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass1.this.emit(null, this);
            }
        }

        public AnonymousClass1(FlowCollector flowCollector) {
            this.$$this$flow = flowCollector;
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(T t, Continuation<? super Unit> continuation) {
            C01911 c01911;
            if (continuation instanceof C01911) {
                c01911 = (C01911) continuation;
                if ((c01911.label & Integer.MIN_VALUE) != 0) {
                    c01911.label -= Integer.MIN_VALUE;
                } else {
                    c01911 = new C01911(continuation);
                }
            } else {
                c01911 = new C01911(continuation);
            }
            Object obj = c01911.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = c01911.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector<T> flowCollector = this.$$this$flow;
                c01911.L$0 = SpillingKt.nullOutSpilledVariable(t);
                c01911.L$1 = SpillingKt.nullOutSpilledVariable(c01911);
                c01911.L$2 = SpillingKt.nullOutSpilledVariable(t);
                c01911.L$3 = SpillingKt.nullOutSpilledVariable(flowCollector);
                c01911.I$0 = 0;
                c01911.label = 1;
                if (flowCollector.emit(t, c01911) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i2 = c01911.I$0;
                Object obj2 = c01911.L$2;
                Object obj3 = c01911.L$0;
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        FlowCollector flowCollector = (FlowCollector) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
            this.label = 1;
            if (this.$this_transform.collect(new AnonymousClass1(flowCollector), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
