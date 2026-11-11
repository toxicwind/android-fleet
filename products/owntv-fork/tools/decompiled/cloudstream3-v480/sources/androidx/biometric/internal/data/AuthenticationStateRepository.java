package androidx.biometric.internal.data;

import androidx.biometric.AuthenticationRequest;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.utils.BiometricErrorData;
import androidx.biometric.utils.CancellationSignalProvider;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: AuthenticationStateRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0010\b`\u0018\u0000 62\u00020\u0001:\u00016J\u001e\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020'H¦@¢\u0006\u0002\u0010(J\u0018\u0010)\u001a\u00020\u001f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0015H¦@¢\u0006\u0002\u0010*J\u0018\u0010+\u001a\u00020\u001f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H¦@¢\u0006\u0002\u0010,J\u0018\u0010-\u001a\u00020\u001f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH¦@¢\u0006\u0002\u0010.J\u000e\u0010/\u001a\u00020\u001fH¦@¢\u0006\u0002\u00100J\u000e\u00101\u001a\u00020\u001fH¦@¢\u0006\u0002\u00100J\u0016\u00102\u001a\u00020\u001f2\u0006\u00103\u001a\u00020\"H¦@¢\u0006\u0002\u00104J\u000e\u00105\u001a\u00020\u001fH¦@¢\u0006\u0002\u00100R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\tX¦\u000e¢\u0006\f\u001a\u0004\b\b\u0010\n\"\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\u00020\tX¦\u000e¢\u0006\f\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0017R\u001a\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0017R\u0018\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0017R\u0018\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0017R\u0018\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0017R\u0018\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0017ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u00067À\u0006\u0001"}, d2 = {"Landroidx/biometric/internal/data/AuthenticationStateRepository;", "", "canceledFrom", "Landroidx/biometric/internal/data/CanceledFrom;", "getCanceledFrom", "()Landroidx/biometric/internal/data/CanceledFrom;", "setCanceledFrom", "(Landroidx/biometric/internal/data/CanceledFrom;)V", "isAwaitingResult", "", "()Z", "setAwaitingResult", "(Z)V", "isIgnoringCancel", "setIgnoringCancel", "cancellationSignalProvider", "Landroidx/biometric/utils/CancellationSignalProvider;", "getCancellationSignalProvider", "()Landroidx/biometric/utils/CancellationSignalProvider;", "authenticationResult", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/biometric/BiometricPrompt$AuthenticationResult;", "getAuthenticationResult", "()Lkotlinx/coroutines/flow/Flow;", "authenticationError", "Landroidx/biometric/utils/BiometricErrorData;", "getAuthenticationError", "authenticationHelpMessage", "", "getAuthenticationHelpMessage", "isAuthenticationFailurePending", "", "isNegativeButtonPressPending", "isFallbackOptionPressPending", "Landroidx/biometric/AuthenticationRequest$Biometric$Fallback$CustomOption;", "isMoreOptionsButtonPressPending", "setDelayedIgnoringCancel", "ignoringCancel", "delayedTime", "", "(ZJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setAuthenticationResult", "(Landroidx/biometric/BiometricPrompt$AuthenticationResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setAuthenticationError", "(Landroidx/biometric/utils/BiometricErrorData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setAuthenticationHelpMessage", "(Ljava/lang/CharSequence;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setAuthenticationFailurePending", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setNegativeButtonPressPending", "setFallbackOptionPressPending", "fallbackOption", "(Landroidx/biometric/AuthenticationRequest$Biometric$Fallback$CustomOption;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setMoreOptionsButtonPressPending", "Companion", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface AuthenticationStateRepository {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    Flow<BiometricErrorData> getAuthenticationError();

    Flow<CharSequence> getAuthenticationHelpMessage();

    Flow<BiometricPrompt.AuthenticationResult> getAuthenticationResult();

    CanceledFrom getCanceledFrom();

    CancellationSignalProvider getCancellationSignalProvider();

    Flow<Unit> isAuthenticationFailurePending();

    /* JADX INFO: renamed from: isAwaitingResult */
    boolean getIsAwaitingResult();

    Flow<AuthenticationRequest.Biometric.Fallback.CustomOption> isFallbackOptionPressPending();

    /* JADX INFO: renamed from: isIgnoringCancel */
    boolean getIsIgnoringCancel();

    Flow<Unit> isMoreOptionsButtonPressPending();

    Flow<Unit> isNegativeButtonPressPending();

    Object setAuthenticationError(BiometricErrorData biometricErrorData, Continuation<? super Unit> continuation);

    Object setAuthenticationFailurePending(Continuation<? super Unit> continuation);

    Object setAuthenticationHelpMessage(CharSequence charSequence, Continuation<? super Unit> continuation);

    Object setAuthenticationResult(BiometricPrompt.AuthenticationResult authenticationResult, Continuation<? super Unit> continuation);

    void setAwaitingResult(boolean z);

    void setCanceledFrom(CanceledFrom canceledFrom);

    Object setDelayedIgnoringCancel(boolean z, long j, Continuation<? super Unit> continuation);

    Object setFallbackOptionPressPending(AuthenticationRequest.Biometric.Fallback.CustomOption customOption, Continuation<? super Unit> continuation);

    void setIgnoringCancel(boolean z);

    Object setMoreOptionsButtonPressPending(Continuation<? super Unit> continuation);

    Object setNegativeButtonPressPending(Continuation<? super Unit> continuation);

    /* JADX INFO: compiled from: AuthenticationStateRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/biometric/internal/data/AuthenticationStateRepository$Companion;", "", "<init>", "()V", "instance", "Landroidx/biometric/internal/data/AuthenticationStateRepository;", "getInstance", "()Landroidx/biometric/internal/data/AuthenticationStateRepository;", "instance$delegate", "Lkotlin/Lazy;", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        /* JADX INFO: renamed from: instance$delegate, reason: from kotlin metadata */
        private static final Lazy<AuthenticationStateRepositoryImpl> instance = LazyKt.lazy(new Function0() { // from class: androidx.biometric.internal.data.AuthenticationStateRepository$Companion$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AuthenticationStateRepository.Companion.instance_delegate$lambda$0();
            }
        });

        private Companion() {
        }

        static final AuthenticationStateRepositoryImpl instance_delegate$lambda$0() {
            return new AuthenticationStateRepositoryImpl();
        }

        public final AuthenticationStateRepository getInstance() {
            return instance.getValue();
        }
    }
}
