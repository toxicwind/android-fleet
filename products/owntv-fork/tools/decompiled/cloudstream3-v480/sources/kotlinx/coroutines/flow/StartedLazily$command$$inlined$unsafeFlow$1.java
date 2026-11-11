package kotlinx.coroutines.flow;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@¢\u0006\u0002\u0010\u0006¨\u0006\u0007¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StartedLazily$command$$inlined$unsafeFlow$1 implements Flow<SharingCommand> {
    final /* synthetic */ StateFlow $subscriptionCount$inlined;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.StartedLazily$command$$inlined$unsafeFlow$1$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.StartedLazily$command$$inlined$unsafeFlow$1", f = "SharingStarted.kt", i = {0, 0, 0, 0, 0}, l = {113}, m = "collect", n = {"collector", "$completion", "$this$command_u24lambda_u240", "started", "$i$a$-unsafeFlow-StartedLazily$command$1"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
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
            return StartedLazily$command$$inlined$unsafeFlow$1.this.collect(null, this);
        }
    }

    public StartedLazily$command$$inlined$unsafeFlow$1(StateFlow stateFlow) {
        this.$subscriptionCount$inlined = stateFlow;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super SharingCommand> flowCollector, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
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
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            StateFlow stateFlow = this.$subscriptionCount$inlined;
            StartedLazily$command$1$1 startedLazily$command$1$1 = new StartedLazily$command$1$1(booleanRef, flowCollector);
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(anonymousClass1);
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(flowCollector);
            anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(booleanRef);
            anonymousClass1.I$0 = 0;
            anonymousClass1.label = 1;
            if (stateFlow.collect(startedLazily$command$1$1, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = anonymousClass1.I$0;
            ResultKt.throwOnFailure(obj);
        }
        throw new KotlinNothingValueException();
    }
}
