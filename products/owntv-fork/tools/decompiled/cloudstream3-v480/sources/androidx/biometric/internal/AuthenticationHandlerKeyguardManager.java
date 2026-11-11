package androidx.biometric.internal;

import androidx.biometric.BiometricPrompt;
import androidx.biometric.internal.data.CanceledFrom;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthenticationHandlerKeyguardManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Landroidx/biometric/internal/AuthenticationHandlerKeyguardManager;", "Landroidx/biometric/internal/AuthenticationHandler;", "authenticationManager", "Landroidx/biometric/internal/AuthenticationManager;", "<init>", "(Landroidx/biometric/internal/AuthenticationManager;)V", "confirmCredentialActivityLauncher", "Ljava/lang/Runnable;", "getConfirmCredentialActivityLauncher", "()Ljava/lang/Runnable;", "authenticate", "", "info", "Landroidx/biometric/BiometricPrompt$PromptInfo;", "crypto", "Landroidx/biometric/BiometricPrompt$CryptoObject;", "cancelAuthentication", "canceledFrom", "Landroidx/biometric/internal/data/CanceledFrom;", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AuthenticationHandlerKeyguardManager implements AuthenticationHandler {
    private final AuthenticationManager authenticationManager;

    public AuthenticationHandlerKeyguardManager(AuthenticationManager authenticationManager) {
        Intrinsics.checkNotNullParameter(authenticationManager, "authenticationManager");
        this.authenticationManager = authenticationManager;
        AuthenticationManager.initialize$default(authenticationManager, null, null, 3, null);
    }

    public final Runnable getConfirmCredentialActivityLauncher() {
        return this.authenticationManager.getConfirmCredentialActivityLauncher();
    }

    static final Unit authenticate$lambda$0(AuthenticationHandlerKeyguardManager authenticationHandlerKeyguardManager) {
        authenticationHandlerKeyguardManager.getConfirmCredentialActivityLauncher().run();
        return Unit.INSTANCE;
    }

    @Override // androidx.biometric.internal.AuthenticationHandler
    public void authenticate(BiometricPrompt.PromptInfo info, BiometricPrompt.CryptoObject crypto) {
        Intrinsics.checkNotNullParameter(info, "info");
        this.authenticationManager.authenticate(info, crypto, new Function0() { // from class: androidx.biometric.internal.AuthenticationHandlerKeyguardManager$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AuthenticationHandlerKeyguardManager.authenticate$lambda$0(this.f$0);
            }
        });
    }

    @Override // androidx.biometric.internal.AuthenticationHandler
    public void cancelAuthentication(CanceledFrom canceledFrom) {
        Intrinsics.checkNotNullParameter(canceledFrom, "canceledFrom");
        this.authenticationManager.cancelAuthentication(canceledFrom);
    }
}
