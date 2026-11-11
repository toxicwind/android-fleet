package androidx.biometric.internal;

import androidx.biometric.AuthenticationRequest;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: AuthenticationHandlerBiometricPrompt.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.biometric.internal.AuthenticationHandlerBiometricPrompt$uiStateObserver$1$createObserverJob$1", f = "AuthenticationHandlerBiometricPrompt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class AuthenticationHandlerBiometricPrompt$uiStateObserver$1$createObserverJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AuthenticationHandlerBiometricPrompt this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AuthenticationHandlerBiometricPrompt$uiStateObserver$1$createObserverJob$1(AuthenticationHandlerBiometricPrompt authenticationHandlerBiometricPrompt, Continuation<? super AuthenticationHandlerBiometricPrompt$uiStateObserver$1$createObserverJob$1> continuation) {
        super(2, continuation);
        this.this$0 = authenticationHandlerBiometricPrompt;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AuthenticationHandlerBiometricPrompt$uiStateObserver$1$createObserverJob$1 authenticationHandlerBiometricPrompt$uiStateObserver$1$createObserverJob$1 = new AuthenticationHandlerBiometricPrompt$uiStateObserver$1$createObserverJob$1(this.this$0, continuation);
        authenticationHandlerBiometricPrompt$uiStateObserver$1$createObserverJob$1.L$0 = obj;
        return authenticationHandlerBiometricPrompt$uiStateObserver$1$createObserverJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AuthenticationHandlerBiometricPrompt$uiStateObserver$1$createObserverJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.AuthenticationHandlerBiometricPrompt$uiStateObserver$1$createObserverJob$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: AuthenticationHandlerBiometricPrompt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.AuthenticationHandlerBiometricPrompt$uiStateObserver$1$createObserverJob$1$1", f = "AuthenticationHandlerBiometricPrompt.kt", i = {}, l = {106}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ AuthenticationHandlerBiometricPrompt this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(AuthenticationHandlerBiometricPrompt authenticationHandlerBiometricPrompt, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = authenticationHandlerBiometricPrompt;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<Unit> flowIsNegativeButtonPressPending = this.this$0.getViewModel().isNegativeButtonPressPending();
                final AuthenticationHandlerBiometricPrompt authenticationHandlerBiometricPrompt = this.this$0;
                this.label = 1;
                if (flowIsNegativeButtonPressPending.collect(new FlowCollector() { // from class: androidx.biometric.internal.AuthenticationHandlerBiometricPrompt.uiStateObserver.1.createObserverJob.1.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((Unit) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(Unit unit, Continuation<? super Unit> continuation) {
                        authenticationHandlerBiometricPrompt.authenticationManager.isNegativeButtonPressPendingObserver().invoke();
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

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.this$0, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.this$0, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.this$0, null), 3, null);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /* JADX INFO: renamed from: androidx.biometric.internal.AuthenticationHandlerBiometricPrompt$uiStateObserver$1$createObserverJob$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: AuthenticationHandlerBiometricPrompt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.AuthenticationHandlerBiometricPrompt$uiStateObserver$1$createObserverJob$1$2", f = "AuthenticationHandlerBiometricPrompt.kt", i = {}, l = {111}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ AuthenticationHandlerBiometricPrompt this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(AuthenticationHandlerBiometricPrompt authenticationHandlerBiometricPrompt, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = authenticationHandlerBiometricPrompt;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<AuthenticationRequest.Biometric.Fallback.CustomOption> flowIsFallbackOptionPressPending = this.this$0.getViewModel().isFallbackOptionPressPending();
                final AuthenticationHandlerBiometricPrompt authenticationHandlerBiometricPrompt = this.this$0;
                this.label = 1;
                if (flowIsFallbackOptionPressPending.collect(new FlowCollector() { // from class: androidx.biometric.internal.AuthenticationHandlerBiometricPrompt.uiStateObserver.1.createObserverJob.1.2.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((AuthenticationRequest.Biometric.Fallback.CustomOption) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(AuthenticationRequest.Biometric.Fallback.CustomOption customOption, Continuation<? super Unit> continuation) {
                        authenticationHandlerBiometricPrompt.onFallbackOptionPressed(customOption);
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

    /* JADX INFO: renamed from: androidx.biometric.internal.AuthenticationHandlerBiometricPrompt$uiStateObserver$1$createObserverJob$1$3, reason: invalid class name */
    /* JADX INFO: compiled from: AuthenticationHandlerBiometricPrompt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.AuthenticationHandlerBiometricPrompt$uiStateObserver$1$createObserverJob$1$3", f = "AuthenticationHandlerBiometricPrompt.kt", i = {}, l = {116}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ AuthenticationHandlerBiometricPrompt this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(AuthenticationHandlerBiometricPrompt authenticationHandlerBiometricPrompt, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.this$0 = authenticationHandlerBiometricPrompt;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<Unit> flowIsMoreOptionsButtonPressPending = this.this$0.getViewModel().isMoreOptionsButtonPressPending();
                final AuthenticationHandlerBiometricPrompt authenticationHandlerBiometricPrompt = this.this$0;
                this.label = 1;
                if (flowIsMoreOptionsButtonPressPending.collect(new FlowCollector() { // from class: androidx.biometric.internal.AuthenticationHandlerBiometricPrompt.uiStateObserver.1.createObserverJob.1.3.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((Unit) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(Unit unit, Continuation<? super Unit> continuation) {
                        authenticationHandlerBiometricPrompt.onMoreOptionsButtonPressed();
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
}
