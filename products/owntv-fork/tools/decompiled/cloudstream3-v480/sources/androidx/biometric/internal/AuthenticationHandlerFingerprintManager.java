package androidx.biometric.internal;

import android.content.Context;
import android.content.Intent;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.internal.data.CanceledFrom;
import androidx.biometric.internal.ui.FingerprintDialogActivity;
import androidx.biometric.internal.viewmodel.AuthenticationViewModel;
import androidx.biometric.utils.ErrorUtils;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: AuthenticationHandlerFingerprintManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0000*\u0002\u0017\u001a\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010\"\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\u001dH\u0002J\b\u0010&\u001a\u00020\u001dH\u0002J\b\u0010'\u001a\u00020\u001dH\u0002J\u001a\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u0010\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001b¨\u0006-"}, d2 = {"Landroidx/biometric/internal/AuthenticationHandlerFingerprintManager;", "Landroidx/biometric/internal/AuthenticationHandler;", "authenticationManager", "Landroidx/biometric/internal/AuthenticationManager;", "<init>", "(Landroidx/biometric/internal/AuthenticationManager;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "getLifecycleOwner", "()Landroidx/lifecycle/LifecycleOwner;", "viewModel", "Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;", "getViewModel", "()Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;", "confirmCredentialActivityLauncher", "Ljava/lang/Runnable;", "getConfirmCredentialActivityLauncher", "()Ljava/lang/Runnable;", "resultDispatcher", "androidx/biometric/internal/AuthenticationHandlerFingerprintManager$resultDispatcher$1", "Landroidx/biometric/internal/AuthenticationHandlerFingerprintManager$resultDispatcher$1;", "uiStateObserver", "androidx/biometric/internal/AuthenticationHandlerFingerprintManager$uiStateObserver$1", "Landroidx/biometric/internal/AuthenticationHandlerFingerprintManager$uiStateObserver$1;", "authenticate", "", "info", "Landroidx/biometric/BiometricPrompt$PromptInfo;", "crypto", "Landroidx/biometric/BiometricPrompt$CryptoObject;", "cancelAuthentication", "canceledFrom", "Landroidx/biometric/internal/data/CanceledFrom;", "showAuthentication", "dismiss", "showKMAsFallback", "onAuthenticationError", "errorCode", "", "errorMessage", "", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AuthenticationHandlerFingerprintManager implements AuthenticationHandler {
    private final AuthenticationManager authenticationManager;
    private final AuthenticationHandlerFingerprintManager$resultDispatcher$1 resultDispatcher;
    private final AuthenticationHandlerFingerprintManager$uiStateObserver$1 uiStateObserver;

    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.biometric.internal.AuthenticationHandlerFingerprintManager$resultDispatcher$1] */
    /* JADX WARN: Type inference failed for: r9v1, types: [androidx.biometric.internal.AuthenticationHandlerFingerprintManager$uiStateObserver$1] */
    public AuthenticationHandlerFingerprintManager(AuthenticationManager authenticationManager) {
        Intrinsics.checkNotNullParameter(authenticationManager, "authenticationManager");
        this.authenticationManager = authenticationManager;
        final Context context = getContext();
        final AuthenticationViewModel viewModel = getViewModel();
        final Executor clientExecutor = authenticationManager.getClientExecutor();
        final BiometricPrompt.AuthenticationCallback clientAuthenticationCallback = authenticationManager.getClientAuthenticationCallback();
        final Runnable confirmCredentialActivityLauncher = getConfirmCredentialActivityLauncher();
        final Function0 function0 = new Function0() { // from class: androidx.biometric.internal.AuthenticationHandlerFingerprintManager$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AuthenticationHandlerFingerprintManager.resultDispatcher$lambda$0(this.f$0);
            }
        };
        ?? r1 = new AuthenticationResultDispatcher(context, viewModel, clientExecutor, clientAuthenticationCallback, confirmCredentialActivityLauncher, function0) { // from class: androidx.biometric.internal.AuthenticationHandlerFingerprintManager$resultDispatcher$1
            @Override // androidx.biometric.internal.AuthenticationResultDispatcher
            public void onAuthenticationError(int errorCode, CharSequence errorMessage) {
                this.this$0.onAuthenticationError(errorCode, errorMessage);
            }

            @Override // androidx.biometric.internal.AuthenticationResultDispatcher
            public void showKMAsFallback() {
                this.this$0.showKMAsFallback();
            }
        };
        this.resultDispatcher = r1;
        ?? r9 = new AuthenticationUiStateObserver() { // from class: androidx.biometric.internal.AuthenticationHandlerFingerprintManager$uiStateObserver$1
            @Override // androidx.biometric.internal.AuthenticationUiStateObserver
            protected Job createObserverJob() {
                return BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0.getLifecycleOwner()), null, null, new AuthenticationHandlerFingerprintManager$uiStateObserver$1$createObserverJob$1(this.this$0, null), 3, null);
            }
        };
        this.uiStateObserver = r9;
        authenticationManager.initialize((AuthenticationResultDispatcher) r1, (AuthenticationUiStateObserver) r9);
    }

    public final Context getContext() {
        return this.authenticationManager.getContext();
    }

    public final LifecycleOwner getLifecycleOwner() {
        return this.authenticationManager.getLifecycleOwner();
    }

    public final AuthenticationViewModel getViewModel() {
        return this.authenticationManager.getViewModel();
    }

    public final Runnable getConfirmCredentialActivityLauncher() {
        return this.authenticationManager.getConfirmCredentialActivityLauncher();
    }

    static final Unit resultDispatcher$lambda$0(AuthenticationHandlerFingerprintManager authenticationHandlerFingerprintManager) {
        authenticationHandlerFingerprintManager.dismiss();
        return Unit.INSTANCE;
    }

    static final Unit authenticate$lambda$0(AuthenticationHandlerFingerprintManager authenticationHandlerFingerprintManager) {
        authenticationHandlerFingerprintManager.showAuthentication();
        return Unit.INSTANCE;
    }

    @Override // androidx.biometric.internal.AuthenticationHandler
    public void authenticate(BiometricPrompt.PromptInfo info, BiometricPrompt.CryptoObject crypto) {
        Intrinsics.checkNotNullParameter(info, "info");
        this.authenticationManager.authenticate(info, crypto, new Function0() { // from class: androidx.biometric.internal.AuthenticationHandlerFingerprintManager$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AuthenticationHandlerFingerprintManager.authenticate$lambda$0(this.f$0);
            }
        });
    }

    @Override // androidx.biometric.internal.AuthenticationHandler
    public void cancelAuthentication(CanceledFrom canceledFrom) {
        Intrinsics.checkNotNullParameter(canceledFrom, "canceledFrom");
        this.authenticationManager.cancelAuthentication(canceledFrom);
    }

    private final void showAuthentication() {
        Intent intent = new Intent(getContext(), (Class<?>) FingerprintDialogActivity.class);
        intent.addFlags(268435456);
        getContext().startActivity(intent);
    }

    private final void dismiss() {
        this.authenticationManager.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showKMAsFallback() {
        getConfirmCredentialActivityLauncher().run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onAuthenticationError(int errorCode, CharSequence errorMessage) {
        int knownErrorCodeForAuthenticate = ErrorUtils.toKnownErrorCodeForAuthenticate(errorCode);
        if (ErrorUtils.isLockoutError(knownErrorCodeForAuthenticate) && getViewModel().isOverriddenDeviceCredential()) {
            showKMAsFallback();
            return;
        }
        if (errorMessage == null) {
            String fingerprintErrorString = ErrorUtils.getFingerprintErrorString(getContext(), knownErrorCodeForAuthenticate);
            Intrinsics.checkNotNullExpressionValue(fingerprintErrorString, "getFingerprintErrorString(...)");
            errorMessage = fingerprintErrorString;
        }
        if (knownErrorCodeForAuthenticate == 5) {
            if (getViewModel().getCanceledFrom().isNotUserInitiated()) {
                sendErrorToClient(knownErrorCodeForAuthenticate, errorMessage);
            }
            dismiss();
            return;
        }
        sendErrorAndDismiss(knownErrorCodeForAuthenticate, errorMessage);
    }
}
