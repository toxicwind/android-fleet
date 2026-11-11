package kotlinx.coroutines.flow.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: ChannelFlow.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0000\u001a&\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001aX\u0010\b\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\t2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u0002H\t2\b\b\u0002\u0010\f\u001a\u00020\r2\"\u0010\u000e\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\r0\u000fH\u0080@¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"asChannelFlow", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/Flow;", "withUndispatchedContextCollector", "Lkotlinx/coroutines/flow/FlowCollector;", "emitContext", "Lkotlin/coroutines/CoroutineContext;", "withContextUndispatched", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "newContext", ES6Iterator.VALUE_PROPERTY, "countOrElement", "", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class ChannelFlowKt {

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.ChannelFlowKt$withContextUndispatched$1, reason: invalid class name */
    /* JADX INFO: compiled from: ChannelFlow.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.ChannelFlowKt", f = "ChannelFlow.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {221}, m = "withContextUndispatched", n = {"newContext", ES6Iterator.VALUE_PROPERTY, "countOrElement", "block", "context$iv", "countOrElement$iv", "oldValue$iv", "$i$f$withCoroutineContext", "$i$a$-withCoroutineContext-ChannelFlowKt$withContextUndispatched$2"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "I$1"}, v = 1)
    static final class AnonymousClass1<T, V> extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelFlowKt.withContextUndispatched(null, null, null, null, this);
        }
    }

    public static final <T> ChannelFlow<T> asChannelFlow(Flow<? extends T> flow) {
        ChannelFlow<T> channelFlow = flow instanceof ChannelFlow ? (ChannelFlow) flow : null;
        if (channelFlow == null) {
            return new ChannelFlowOperatorImpl(flow, null, 0, null, 14, null);
        }
        return channelFlow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> FlowCollector<T> withUndispatchedContextCollector(FlowCollector<? super T> flowCollector, CoroutineContext coroutineContext) {
        return ((flowCollector instanceof SendingCollector) || (flowCollector instanceof NopCollector)) ? flowCollector : new UndispatchedContextCollector(flowCollector, coroutineContext);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <T, V> Object withContextUndispatched(CoroutineContext coroutineContext, V v, Object obj, Function2<? super V, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        Object objUpdateThreadContext;
        Object objWrapWithContinuationImpl;
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
        Object obj2 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = anonymousClass1.I$1;
            int i3 = anonymousClass1.I$0;
            Object obj3 = anonymousClass1.L$6;
            Object obj4 = anonymousClass1.L$5;
            CoroutineContext coroutineContext2 = (CoroutineContext) anonymousClass1.L$4;
            Object obj5 = anonymousClass1.L$2;
            Object obj6 = anonymousClass1.L$1;
            try {
                ResultKt.throwOnFailure(obj2);
                objUpdateThreadContext = obj3;
                coroutineContext = coroutineContext2;
                objWrapWithContinuationImpl = obj2;
                ThreadContextKt.restoreThreadContext(coroutineContext, objUpdateThreadContext);
                return objWrapWithContinuationImpl;
            } catch (Throwable th) {
                th = th;
                objUpdateThreadContext = obj3;
                coroutineContext = coroutineContext2;
                ThreadContextKt.restoreThreadContext(coroutineContext, objUpdateThreadContext);
                throw th;
            }
        }
        ResultKt.throwOnFailure(obj2);
        objUpdateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext, obj);
        try {
            anonymousClass1.L$0 = coroutineContext;
            anonymousClass1.L$1 = v;
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(obj);
            anonymousClass1.L$3 = function2;
            anonymousClass1.L$4 = coroutineContext;
            anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(obj);
            anonymousClass1.L$6 = objUpdateThreadContext;
            anonymousClass1.L$7 = anonymousClass1;
            anonymousClass1.I$0 = 0;
            anonymousClass1.I$1 = 0;
            anonymousClass1.label = 1;
            StackFrameContinuation stackFrameContinuation = new StackFrameContinuation(anonymousClass1, coroutineContext);
            objWrapWithContinuationImpl = !(function2 instanceof BaseContinuationImpl) ? IntrinsicsKt.wrapWithContinuationImpl(function2, v, stackFrameContinuation) : ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(v, stackFrameContinuation);
            if (objWrapWithContinuationImpl == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(anonymousClass1);
            }
            if (objWrapWithContinuationImpl == coroutine_suspended) {
                return coroutine_suspended;
            }
            ThreadContextKt.restoreThreadContext(coroutineContext, objUpdateThreadContext);
            return objWrapWithContinuationImpl;
        } catch (Throwable th2) {
            th = th2;
            ThreadContextKt.restoreThreadContext(coroutineContext, objUpdateThreadContext);
            throw th;
        }
    }

    public static /* synthetic */ Object withContextUndispatched$default(CoroutineContext coroutineContext, Object obj, Object obj2, Function2 function2, Continuation continuation, int i, Object obj3) {
        if ((i & 4) != 0) {
            obj2 = ThreadContextKt.threadContextElements(coroutineContext);
        }
        return withContextUndispatched(coroutineContext, obj, obj2, function2, continuation);
    }
}
