package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.flow.internal.AbortFlowException;
import kotlinx.coroutines.flow.internal.FlowExceptions_commonKt;

/* JADX INFO: compiled from: Logic.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u001aB\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\"\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005H\u0086@¢\u0006\u0002\u0010\b\u001aB\u0010\t\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\"\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005H\u0086@¢\u0006\u0002\u0010\b\u001aB\u0010\n\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\"\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005H\u0086@¢\u0006\u0002\u0010\b¨\u0006\u000b"}, d2 = {"any", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/Flow;", "predicate", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "all", "none", "kotlinx-coroutines-core"}, k = 5, mv = {2, 2, 0}, xi = 48, xs = "kotlinx/coroutines/flow/FlowKt")
final /* synthetic */ class FlowKt__LogicKt {

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__LogicKt$all$1, reason: invalid class name */
    /* JADX INFO: compiled from: Logic.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__LogicKt", f = "Logic.kt", i = {0, 0, 0, 0, 0, 0}, l = {119}, m = "all", n = {"$this$all", "predicate", "foundCounterExample", "$this$collectWhile$iv", "collector$iv", "$i$f$collectWhile"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0"}, v = 1)
    static final class AnonymousClass1<T> extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.all(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__LogicKt$any$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Logic.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__LogicKt", f = "Logic.kt", i = {0, 0, 0, 0, 0, 0}, l = {119}, m = "any", n = {"$this$any", "predicate", "found", "$this$collectWhile$iv", "collector$iv", "$i$f$collectWhile"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0"}, v = 1)
    static final class C11261<T> extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C11261(Continuation<? super C11261> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.any(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__LogicKt$none$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Logic.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__LogicKt", f = "Logic.kt", i = {0, 0}, l = {107}, m = "none", n = {"$this$none", "predicate"}, s = {"L$0", "L$1"}, v = 1)
    static final class C11271<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C11271(Continuation<? super C11271> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.none(null, null, this);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <T> Object any(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super Boolean> continuation) {
        C11261 c11261;
        Ref.BooleanRef booleanRef;
        AbortFlowException e;
        FlowCollector<? super Object> flowCollector;
        if (continuation instanceof C11261) {
            c11261 = (C11261) continuation;
            if ((c11261.label & Integer.MIN_VALUE) != 0) {
                c11261.label -= Integer.MIN_VALUE;
            } else {
                c11261 = new C11261(continuation);
            }
        } else {
            c11261 = new C11261(continuation);
        }
        Object obj = c11261.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c11261.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
            FlowCollector<? super Object> flowKt__LogicKt$any$$inlined$collectWhile$1 = new FlowKt__LogicKt$any$$inlined$collectWhile$1<>(function2, booleanRef2);
            try {
                c11261.L$0 = SpillingKt.nullOutSpilledVariable(flow);
                c11261.L$1 = SpillingKt.nullOutSpilledVariable(function2);
                c11261.L$2 = booleanRef2;
                c11261.L$3 = SpillingKt.nullOutSpilledVariable(flow);
                c11261.L$4 = flowKt__LogicKt$any$$inlined$collectWhile$1;
                c11261.I$0 = 0;
                c11261.label = 1;
                if (flow.collect(flowKt__LogicKt$any$$inlined$collectWhile$1, c11261) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                booleanRef = booleanRef2;
            } catch (AbortFlowException e2) {
                booleanRef = booleanRef2;
                e = e2;
                flowCollector = flowKt__LogicKt$any$$inlined$collectWhile$1;
                FlowExceptions_commonKt.checkOwnership(e, flowCollector);
                JobKt.ensureActive(c11261.get$context());
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = c11261.I$0;
            flowCollector = (FlowKt__LogicKt$any$$inlined$collectWhile$1) c11261.L$4;
            booleanRef = (Ref.BooleanRef) c11261.L$2;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (AbortFlowException e3) {
                e = e3;
                FlowExceptions_commonKt.checkOwnership(e, flowCollector);
                JobKt.ensureActive(c11261.get$context());
            }
        }
        return Boxing.boxBoolean(booleanRef.element);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <T> Object all(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super Boolean> continuation) {
        AnonymousClass1 anonymousClass1;
        Ref.BooleanRef booleanRef;
        AbortFlowException e;
        FlowCollector<? super Object> flowCollector;
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
            Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
            FlowCollector<? super Object> flowKt__LogicKt$all$$inlined$collectWhile$1 = new FlowKt__LogicKt$all$$inlined$collectWhile$1<>(function2, booleanRef2);
            try {
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(flow);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(function2);
                anonymousClass1.L$2 = booleanRef2;
                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(flow);
                anonymousClass1.L$4 = flowKt__LogicKt$all$$inlined$collectWhile$1;
                anonymousClass1.I$0 = 0;
                anonymousClass1.label = 1;
                if (flow.collect(flowKt__LogicKt$all$$inlined$collectWhile$1, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                booleanRef = booleanRef2;
            } catch (AbortFlowException e2) {
                booleanRef = booleanRef2;
                e = e2;
                flowCollector = flowKt__LogicKt$all$$inlined$collectWhile$1;
                FlowExceptions_commonKt.checkOwnership(e, flowCollector);
                JobKt.ensureActive(anonymousClass1.get$context());
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = anonymousClass1.I$0;
            flowCollector = (FlowKt__LogicKt$all$$inlined$collectWhile$1) anonymousClass1.L$4;
            booleanRef = (Ref.BooleanRef) anonymousClass1.L$2;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (AbortFlowException e3) {
                e = e3;
                FlowExceptions_commonKt.checkOwnership(e, flowCollector);
                JobKt.ensureActive(anonymousClass1.get$context());
            }
        }
        return Boxing.boxBoolean(!booleanRef.element);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <T> Object none(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super Boolean> continuation) {
        C11271 c11271;
        if (continuation instanceof C11271) {
            c11271 = (C11271) continuation;
            if ((c11271.label & Integer.MIN_VALUE) != 0) {
                c11271.label -= Integer.MIN_VALUE;
            } else {
                c11271 = new C11271(continuation);
            }
        } else {
            c11271 = new C11271(continuation);
        }
        Object objAny = c11271.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c11271.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objAny);
            c11271.L$0 = SpillingKt.nullOutSpilledVariable(flow);
            c11271.L$1 = SpillingKt.nullOutSpilledVariable(function2);
            c11271.label = 1;
            objAny = FlowKt.any(flow, function2, c11271);
            if (objAny == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objAny);
        }
        return Boxing.boxBoolean(!((Boolean) objAny).booleanValue());
    }
}
