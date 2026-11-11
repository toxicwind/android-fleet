package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function4;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@¢\u0006\u0002\u0010\u0006¨\u0006\u0007¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1<T> implements Flow<T> {
    final /* synthetic */ Function4 $predicate$inlined;
    final /* synthetic */ Flow $this_retryWhen$inlined;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1", f = "Errors.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, l = {116, 118}, m = "collect", n = {"collector", "$completion", "$this$retryWhen_u24lambda_u240", "$i$a$-unsafeFlow-FlowKt__ErrorsKt$retryWhen$1", "attempt", "shallRetry", "collector", "$completion", "$this$retryWhen_u24lambda_u240", "cause", "$i$a$-unsafeFlow-FlowKt__ErrorsKt$retryWhen$1", "attempt", "shallRetry"}, s = {"L$0", "L$1", "L$2", "I$0", "J$0", "I$1", "L$0", "L$1", "L$2", "L$3", "I$0", "J$0", "I$1"}, v = 1)
    public static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        long J$0;
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
            return FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1.this.collect(null, this);
        }
    }

    public FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1(Flow flow, Function4 function4) {
        this.$this_retryWhen$inlined = flow;
        this.$predicate$inlined = function4;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x009e  */
    /* JADX WARN: Code duplicated, block: B:23:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:26:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:31:0x00e1 A[PHI: r2 r4 r8 r9 r11 r12 r13
      0x00e1: PHI (r2v7 kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1) = 
      (r2v4 kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1)
      (r2v9 kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1)
     binds: [B:22:0x00a8, B:29:0x00da] A[DONT_GENERATE, DONT_INLINE]
      0x00e1: PHI (r4v5 int) = (r4v2 int), (r4v7 int) binds: [B:22:0x00a8, B:29:0x00da] A[DONT_GENERATE, DONT_INLINE]
      0x00e1: PHI (r8v6 int) = (r8v2 int), (r8v10 int) binds: [B:22:0x00a8, B:29:0x00da] A[DONT_GENERATE, DONT_INLINE]
      0x00e1: PHI (r9v3 long) = (r9v1 long), (r9v4 long) binds: [B:22:0x00a8, B:29:0x00da] A[DONT_GENERATE, DONT_INLINE]
      0x00e1: PHI (r11v4 kotlinx.coroutines.flow.FlowCollector<? super T>) = (r11v1 kotlinx.coroutines.flow.FlowCollector<? super T>), (r11v6 kotlinx.coroutines.flow.FlowCollector<? super T>) binds: [B:22:0x00a8, B:29:0x00da] A[DONT_GENERATE, DONT_INLINE]
      0x00e1: PHI (r12v4 kotlin.coroutines.Continuation) = (r12v0 kotlin.coroutines.Continuation), (r12v5 kotlin.coroutines.Continuation) binds: [B:22:0x00a8, B:29:0x00da] A[DONT_GENERATE, DONT_INLINE]
      0x00e1: PHI (r13v6 kotlinx.coroutines.flow.FlowCollector<? super T>) = (r13v0 kotlinx.coroutines.flow.FlowCollector<? super T>), (r13v7 kotlinx.coroutines.flow.FlowCollector<? super T>) binds: [B:22:0x00a8, B:29:0x00da] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:33:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:35:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x00a8 -> B:31:0x00e1). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00cf -> B:27:0x00d2). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // kotlinx.coroutines.flow.Flow
    public java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector<? super T> r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
        /*
            Method dump skipped, instruction units count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
