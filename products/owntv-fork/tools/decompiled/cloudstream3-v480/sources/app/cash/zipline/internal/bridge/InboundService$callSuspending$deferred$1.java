package app.cash.zipline.internal.bridge;

import app.cash.zipline.ZiplineService;
import java.util.List;
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
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: InboundService.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lkotlin/Result;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "app.cash.zipline.internal.bridge.InboundService$callSuspending$deferred$1", f = "InboundService.kt", i = {0, 0, 0, 0}, l = {78}, m = "invokeSuspend", n = {"$this$async", "args", "$this$invokeSuspend_u24lambda_u240", "$i$a$-runCatching-InboundService$callSuspending$deferred$1$1"}, nl = {77}, s = {"L$0", "L$1", "L$2", "I$0"}, v = 2)
final class InboundService$callSuspending$deferred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends Object>>, Object> {
    final /* synthetic */ SuspendingZiplineFunction<ZiplineService> $function;
    final /* synthetic */ InternalCall $internalCall;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ InboundService<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    InboundService$callSuspending$deferred$1(InternalCall internalCall, SuspendingZiplineFunction<ZiplineService> suspendingZiplineFunction, InboundService<T> inboundService, Continuation<? super InboundService$callSuspending$deferred$1> continuation) {
        super(2, continuation);
        this.$internalCall = internalCall;
        this.$function = suspendingZiplineFunction;
        this.this$0 = inboundService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        InboundService$callSuspending$deferred$1 inboundService$callSuspending$deferred$1 = new InboundService$callSuspending$deferred$1(this.$internalCall, this.$function, this.this$0, continuation);
        inboundService$callSuspending$deferred$1.L$0 = obj;
        return inboundService$callSuspending$deferred$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends Object>> continuation) {
        return ((InboundService$callSuspending$deferred$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object objM8936constructorimpl;
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                List<?> args = this.$internalCall.getArgs();
                SuspendingZiplineFunction<ZiplineService> suspendingZiplineFunction = this.$function;
                InboundService<T> inboundService = this.this$0;
                Result.Companion companion = Result.INSTANCE;
                ZiplineService service$zipline_release = inboundService.getService$zipline_release();
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable(args);
                this.L$2 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.I$0 = 0;
                this.label = 1;
                obj = suspendingZiplineFunction.callSuspending(service$zipline_release, args, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            objM8936constructorimpl = Result.m8936constructorimpl(obj);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        return Result.m8935boximpl(objM8936constructorimpl);
    }
}
