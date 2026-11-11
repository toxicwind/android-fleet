package androidx.biometric.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: AuthenticationHandlerFingerprintManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.biometric.internal.AuthenticationHandlerFingerprintManager$uiStateObserver$1$createObserverJob$1", f = "AuthenticationHandlerFingerprintManager.kt", i = {}, l = {76}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class AuthenticationHandlerFingerprintManager$uiStateObserver$1$createObserverJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ AuthenticationHandlerFingerprintManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AuthenticationHandlerFingerprintManager$uiStateObserver$1$createObserverJob$1(AuthenticationHandlerFingerprintManager authenticationHandlerFingerprintManager, Continuation<? super AuthenticationHandlerFingerprintManager$uiStateObserver$1$createObserverJob$1> continuation) {
        super(2, continuation);
        this.this$0 = authenticationHandlerFingerprintManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AuthenticationHandlerFingerprintManager$uiStateObserver$1$createObserverJob$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AuthenticationHandlerFingerprintManager$uiStateObserver$1$createObserverJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow<Unit> flowIsNegativeButtonPressPending = this.this$0.getViewModel().isNegativeButtonPressPending();
            final AuthenticationHandlerFingerprintManager authenticationHandlerFingerprintManager = this.this$0;
            this.label = 1;
            if (flowIsNegativeButtonPressPending.collect(new FlowCollector() { // from class: androidx.biometric.internal.AuthenticationHandlerFingerprintManager$uiStateObserver$1$createObserverJob$1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((Unit) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(Unit unit, Continuation<? super Unit> continuation) {
                    authenticationHandlerFingerprintManager.authenticationManager.isNegativeButtonPressPendingObserver().invoke();
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
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
