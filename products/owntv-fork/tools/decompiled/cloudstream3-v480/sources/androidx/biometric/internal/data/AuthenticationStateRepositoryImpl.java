package androidx.biometric.internal.data;

import androidx.biometric.AuthenticationRequest;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.utils.BiometricErrorData;
import androidx.biometric.utils.CancellationSignalProvider;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* JADX INFO: compiled from: AuthenticationStateRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u000f\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u00102\u001a\u00020)2\u0006\u00103\u001a\u00020\u000b2\u0006\u00104\u001a\u000205H\u0096@¢\u0006\u0002\u00106J\u0018\u00107\u001a\u00020)2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0096@¢\u0006\u0002\u00108J\u0018\u00109\u001a\u00020)2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0096@¢\u0006\u0002\u0010:J\u0018\u0010;\u001a\u00020)2\b\u0010$\u001a\u0004\u0018\u00010#H\u0096@¢\u0006\u0002\u0010<J\u000e\u0010=\u001a\u00020)H\u0096@¢\u0006\u0002\u0010>J\u000e\u0010?\u001a\u00020)H\u0096@¢\u0006\u0002\u0010>J\u0016\u0010@\u001a\u00020)2\u0006\u0010A\u001a\u00020.H\u0096@¢\u0006\u0002\u0010BJ\u000e\u0010C\u001a\u00020)H\u0096@¢\u0006\u0002\u0010>R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001b\u0010\u0011\u001a\u00020\u00128VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u001bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001dR\u0016\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#0%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010*\u001a\b\u0012\u0004\u0012\u00020)0%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010'R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020)0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010,\u001a\b\u0012\u0004\u0012\u00020)0%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010'R\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010/\u001a\b\u0012\u0004\u0012\u00020.0%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010'R\u0014\u00100\u001a\b\u0012\u0004\u0012\u00020)0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00101\u001a\b\u0012\u0004\u0012\u00020)0%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u0010'¨\u0006D"}, d2 = {"Landroidx/biometric/internal/data/AuthenticationStateRepositoryImpl;", "Landroidx/biometric/internal/data/AuthenticationStateRepository;", "<init>", "()V", "canceledFrom", "Landroidx/biometric/internal/data/CanceledFrom;", "getCanceledFrom", "()Landroidx/biometric/internal/data/CanceledFrom;", "setCanceledFrom", "(Landroidx/biometric/internal/data/CanceledFrom;)V", "isAwaitingResult", "", "()Z", "setAwaitingResult", "(Z)V", "isIgnoringCancel", "setIgnoringCancel", "cancellationSignalProvider", "Landroidx/biometric/utils/CancellationSignalProvider;", "getCancellationSignalProvider", "()Landroidx/biometric/utils/CancellationSignalProvider;", "cancellationSignalProvider$delegate", "Lkotlin/Lazy;", "_authenticationResult", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Landroidx/biometric/BiometricPrompt$AuthenticationResult;", "authenticationResult", "Lkotlinx/coroutines/flow/Flow;", "getAuthenticationResult", "()Lkotlinx/coroutines/flow/Flow;", "_authenticationError", "Landroidx/biometric/utils/BiometricErrorData;", "authenticationError", "getAuthenticationError", "_authenticationHelpMessage", "", "authenticationHelpMessage", "Lkotlinx/coroutines/flow/SharedFlow;", "getAuthenticationHelpMessage", "()Lkotlinx/coroutines/flow/SharedFlow;", "_isAuthenticationFailurePending", "", "isAuthenticationFailurePending", "_isNegativeButtonPressPending", "isNegativeButtonPressPending", "_isFallbackOptionPressPending", "Landroidx/biometric/AuthenticationRequest$Biometric$Fallback$CustomOption;", "isFallbackOptionPressPending", "_isMoreOptionsButtonPressPending", "isMoreOptionsButtonPressPending", "setDelayedIgnoringCancel", "ignoringCancel", "delayedTime", "", "(ZJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setAuthenticationResult", "(Landroidx/biometric/BiometricPrompt$AuthenticationResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setAuthenticationError", "(Landroidx/biometric/utils/BiometricErrorData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setAuthenticationHelpMessage", "(Ljava/lang/CharSequence;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setAuthenticationFailurePending", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setNegativeButtonPressPending", "setFallbackOptionPressPending", "fallbackOption", "(Landroidx/biometric/AuthenticationRequest$Biometric$Fallback$CustomOption;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setMoreOptionsButtonPressPending", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AuthenticationStateRepositoryImpl implements AuthenticationStateRepository {
    private final MutableSharedFlow<BiometricErrorData> _authenticationError;
    private final MutableSharedFlow<CharSequence> _authenticationHelpMessage;
    private final MutableSharedFlow<BiometricPrompt.AuthenticationResult> _authenticationResult;
    private final MutableSharedFlow<Unit> _isAuthenticationFailurePending;
    private final MutableSharedFlow<AuthenticationRequest.Biometric.Fallback.CustomOption> _isFallbackOptionPressPending;
    private final MutableSharedFlow<Unit> _isMoreOptionsButtonPressPending;
    private final MutableSharedFlow<Unit> _isNegativeButtonPressPending;
    private final Flow<BiometricErrorData> authenticationError;
    private final SharedFlow<CharSequence> authenticationHelpMessage;
    private final Flow<BiometricPrompt.AuthenticationResult> authenticationResult;
    private CanceledFrom canceledFrom = CanceledFrom.INTERNAL;

    /* JADX INFO: renamed from: cancellationSignalProvider$delegate, reason: from kotlin metadata */
    private final Lazy cancellationSignalProvider = LazyKt.lazy(new Function0() { // from class: androidx.biometric.internal.data.AuthenticationStateRepositoryImpl$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AuthenticationStateRepositoryImpl.cancellationSignalProvider_delegate$lambda$0();
        }
    });
    private final SharedFlow<Unit> isAuthenticationFailurePending;
    private boolean isAwaitingResult;
    private final SharedFlow<AuthenticationRequest.Biometric.Fallback.CustomOption> isFallbackOptionPressPending;
    private boolean isIgnoringCancel;
    private final SharedFlow<Unit> isMoreOptionsButtonPressPending;
    private final SharedFlow<Unit> isNegativeButtonPressPending;

    /* JADX INFO: renamed from: androidx.biometric.internal.data.AuthenticationStateRepositoryImpl$setDelayedIgnoringCancel$1, reason: invalid class name */
    /* JADX INFO: compiled from: AuthenticationStateRepository.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.biometric.internal.data.AuthenticationStateRepositoryImpl", f = "AuthenticationStateRepository.kt", i = {0}, l = {153}, m = "setDelayedIgnoringCancel", n = {"ignoringCancel"}, s = {"Z$0"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AuthenticationStateRepositoryImpl.this.setDelayedIgnoringCancel(false, 0L, this);
        }
    }

    public AuthenticationStateRepositoryImpl() {
        MutableSharedFlow<BiometricPrompt.AuthenticationResult> mutableSharedFlowMutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        this._authenticationResult = mutableSharedFlowMutableSharedFlow$default;
        this.authenticationResult = FlowKt.filterNotNull(FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default));
        MutableSharedFlow<BiometricErrorData> mutableSharedFlowMutableSharedFlow$default2 = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        this._authenticationError = mutableSharedFlowMutableSharedFlow$default2;
        this.authenticationError = FlowKt.filterNotNull(FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default2));
        MutableSharedFlow<CharSequence> mutableSharedFlowMutableSharedFlow$default3 = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        this._authenticationHelpMessage = mutableSharedFlowMutableSharedFlow$default3;
        this.authenticationHelpMessage = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default3);
        MutableSharedFlow<Unit> mutableSharedFlowMutableSharedFlow$default4 = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        this._isAuthenticationFailurePending = mutableSharedFlowMutableSharedFlow$default4;
        this.isAuthenticationFailurePending = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default4);
        MutableSharedFlow<Unit> mutableSharedFlowMutableSharedFlow$default5 = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        this._isNegativeButtonPressPending = mutableSharedFlowMutableSharedFlow$default5;
        this.isNegativeButtonPressPending = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default5);
        MutableSharedFlow<AuthenticationRequest.Biometric.Fallback.CustomOption> mutableSharedFlowMutableSharedFlow$default6 = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        this._isFallbackOptionPressPending = mutableSharedFlowMutableSharedFlow$default6;
        this.isFallbackOptionPressPending = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default6);
        MutableSharedFlow<Unit> mutableSharedFlowMutableSharedFlow$default7 = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        this._isMoreOptionsButtonPressPending = mutableSharedFlowMutableSharedFlow$default7;
        this.isMoreOptionsButtonPressPending = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default7);
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public CanceledFrom getCanceledFrom() {
        return this.canceledFrom;
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public void setCanceledFrom(CanceledFrom canceledFrom) {
        Intrinsics.checkNotNullParameter(canceledFrom, "<set-?>");
        this.canceledFrom = canceledFrom;
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    /* JADX INFO: renamed from: isAwaitingResult, reason: from getter */
    public boolean getIsAwaitingResult() {
        return this.isAwaitingResult;
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public void setAwaitingResult(boolean z) {
        this.isAwaitingResult = z;
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    /* JADX INFO: renamed from: isIgnoringCancel, reason: from getter */
    public boolean getIsIgnoringCancel() {
        return this.isIgnoringCancel;
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public void setIgnoringCancel(boolean z) {
        this.isIgnoringCancel = z;
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public CancellationSignalProvider getCancellationSignalProvider() {
        return (CancellationSignalProvider) this.cancellationSignalProvider.getValue();
    }

    static final CancellationSignalProvider cancellationSignalProvider_delegate$lambda$0() {
        return new CancellationSignalProvider();
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public Flow<BiometricPrompt.AuthenticationResult> getAuthenticationResult() {
        return this.authenticationResult;
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public Flow<BiometricErrorData> getAuthenticationError() {
        return this.authenticationError;
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public SharedFlow<CharSequence> getAuthenticationHelpMessage() {
        return this.authenticationHelpMessage;
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public SharedFlow<Unit> isAuthenticationFailurePending() {
        return this.isAuthenticationFailurePending;
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public SharedFlow<Unit> isNegativeButtonPressPending() {
        return this.isNegativeButtonPressPending;
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public SharedFlow<AuthenticationRequest.Biometric.Fallback.CustomOption> isFallbackOptionPressPending() {
        return this.isFallbackOptionPressPending;
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public SharedFlow<Unit> isMoreOptionsButtonPressPending() {
        return this.isMoreOptionsButtonPressPending;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public Object setDelayedIgnoringCancel(boolean z, long j, Continuation<? super Unit> continuation) {
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
            anonymousClass1.Z$0 = z;
            anonymousClass1.label = 1;
            if (DelayKt.delay(j, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            z = anonymousClass1.Z$0;
            ResultKt.throwOnFailure(obj);
        }
        setIgnoringCancel(z);
        return Unit.INSTANCE;
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public Object setAuthenticationResult(BiometricPrompt.AuthenticationResult authenticationResult, Continuation<? super Unit> continuation) {
        Object objEmit = this._authenticationResult.emit(authenticationResult, continuation);
        return objEmit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public Object setAuthenticationError(BiometricErrorData biometricErrorData, Continuation<? super Unit> continuation) {
        Object objEmit = this._authenticationError.emit(biometricErrorData, continuation);
        return objEmit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public Object setAuthenticationHelpMessage(CharSequence charSequence, Continuation<? super Unit> continuation) {
        Object objEmit = this._authenticationHelpMessage.emit(charSequence, continuation);
        return objEmit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public Object setAuthenticationFailurePending(Continuation<? super Unit> continuation) {
        Object objEmit = this._isAuthenticationFailurePending.emit(Unit.INSTANCE, continuation);
        return objEmit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public Object setNegativeButtonPressPending(Continuation<? super Unit> continuation) {
        Object objEmit = this._isNegativeButtonPressPending.emit(Unit.INSTANCE, continuation);
        return objEmit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public Object setFallbackOptionPressPending(AuthenticationRequest.Biometric.Fallback.CustomOption customOption, Continuation<? super Unit> continuation) {
        Object objEmit = this._isFallbackOptionPressPending.emit(customOption, continuation);
        return objEmit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    @Override // androidx.biometric.internal.data.AuthenticationStateRepository
    public Object setMoreOptionsButtonPressPending(Continuation<? super Unit> continuation) {
        Object objEmit = this._isMoreOptionsButtonPressPending.emit(Unit.INSTANCE, continuation);
        return objEmit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }
}
