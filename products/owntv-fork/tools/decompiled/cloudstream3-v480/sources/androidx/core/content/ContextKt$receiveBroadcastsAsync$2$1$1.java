package androidx.core.content;

import android.content.BroadcastReceiver;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: Context.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.core.content.ContextKt$receiveBroadcastsAsync$2$1$1", f = "Context.kt", i = {}, l = {247}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class ContextKt$receiveBroadcastsAsync$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Intent $intent;
    final /* synthetic */ Function3<BroadcastReceiver.PendingResult, Intent, Continuation<? super Unit>, Object> $onReceive;
    final /* synthetic */ BroadcastReceiver.PendingResult $pendingResult;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    ContextKt$receiveBroadcastsAsync$2$1$1(Function3<? super BroadcastReceiver.PendingResult, ? super Intent, ? super Continuation<? super Unit>, ? extends Object> function3, BroadcastReceiver.PendingResult pendingResult, Intent intent, Continuation<? super ContextKt$receiveBroadcastsAsync$2$1$1> continuation) {
        super(2, continuation);
        this.$onReceive = function3;
        this.$pendingResult = pendingResult;
        this.$intent = intent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ContextKt$receiveBroadcastsAsync$2$1$1 contextKt$receiveBroadcastsAsync$2$1$1 = new ContextKt$receiveBroadcastsAsync$2$1$1(this.$onReceive, this.$pendingResult, this.$intent, continuation);
        contextKt$receiveBroadcastsAsync$2$1$1.L$0 = obj;
        return contextKt$receiveBroadcastsAsync$2$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ContextKt$receiveBroadcastsAsync$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.lang.Object, kotlin.Unit] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScopeKt.ensureActive((CoroutineScope) this.L$0);
                Function3<BroadcastReceiver.PendingResult, Intent, Continuation<? super Unit>, Object> function3 = this.$onReceive;
                BroadcastReceiver.PendingResult pendingResult = this.$pendingResult;
                Intent intent = this.$intent;
                this.label = 1;
                if (function3.invoke(pendingResult, intent, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            this.$pendingResult.finish();
            this = Unit.INSTANCE;
            return this;
        } catch (Throwable th) {
            this.$pendingResult.finish();
            throw th;
        }
    }
}
