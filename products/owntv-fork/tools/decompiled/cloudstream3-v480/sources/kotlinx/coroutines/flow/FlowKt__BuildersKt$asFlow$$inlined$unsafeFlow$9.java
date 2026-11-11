package kotlinx.coroutines.flow;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.ranges.IntRange;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@¢\u0006\u0002\u0010\u0006¨\u0006\u0007¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9 implements Flow<Integer> {
    final /* synthetic */ IntRange $this_asFlow$inlined;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9", f = "Builders.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {114}, m = "collect", n = {"collector", "$completion", "$this$asFlow_u24lambda_u248", "$this$forEach$iv", "element$iv", "$i$a$-unsafeFlow-FlowKt__BuildersKt$asFlow$9", "$i$f$forEach", ES6Iterator.VALUE_PROPERTY, "$i$a$-forEach-FlowKt__BuildersKt$asFlow$9$1"}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "I$0", "I$1", "I$2", "I$3"}, v = 1)
    public static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9.this.collect(null, this);
        }
    }

    public FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$9(IntRange intRange) {
        this.$this_asFlow$inlined = intRange;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super Integer> flowCollector, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        Iterable iterable;
        Continuation continuation2;
        Iterator<Integer> it;
        int i;
        FlowCollector<? super Integer> flowCollector2;
        AnonymousClass1 anonymousClass2;
        int i2;
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
            IntRange intRange = this.$this_asFlow$inlined;
            iterable = intRange;
            continuation2 = anonymousClass1;
            it = intRange.iterator();
            i = 0;
            flowCollector2 = flowCollector;
            anonymousClass2 = anonymousClass1;
            i2 = 0;
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i4 = anonymousClass1.I$3;
            int i5 = anonymousClass1.I$2;
            i = anonymousClass1.I$1;
            int i6 = anonymousClass1.I$0;
            Object obj2 = anonymousClass1.L$5;
            Iterator<Integer> it2 = (Iterator) anonymousClass1.L$4;
            Iterable iterable2 = (Iterable) anonymousClass1.L$3;
            FlowCollector<? super Integer> flowCollector3 = (FlowCollector) anonymousClass1.L$2;
            continuation2 = (Continuation) anonymousClass1.L$1;
            FlowCollector<? super Integer> flowCollector4 = (FlowCollector) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
            AnonymousClass1 anonymousClass3 = anonymousClass1;
            i2 = i6;
            flowCollector = flowCollector3;
            iterable = iterable2;
            it = it2;
            anonymousClass2 = anonymousClass3;
            flowCollector2 = flowCollector4;
        }
        while (it.hasNext()) {
            Integer next = it.next();
            int iIntValue = next.intValue();
            Integer numBoxInt = Boxing.boxInt(iIntValue);
            anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector2);
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(continuation2);
            anonymousClass2.L$2 = flowCollector;
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(iterable);
            anonymousClass2.L$4 = it;
            anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(next);
            anonymousClass2.I$0 = i2;
            anonymousClass2.I$1 = i;
            anonymousClass2.I$2 = iIntValue;
            anonymousClass2.I$3 = 0;
            anonymousClass2.label = 1;
            if (flowCollector.emit(numBoxInt, anonymousClass2) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
