package androidx.biometric.internal;

import android.content.Context;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.internal.data.CanceledFrom;
import androidx.biometric.internal.viewmodel.AuthenticationViewModel;
import androidx.biometric.utils.DeviceUtils;
import androidx.lifecycle.LifecycleOwner;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultAuthenticationHandler.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0010\u0010!\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020\u0001H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R&\u0010\u0014\u001a\u0004\u0018\u00010\u00018\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006%"}, d2 = {"Landroidx/biometric/internal/DefaultAuthenticationHandler;", "Landroidx/biometric/internal/AuthenticationHandler;", "context", "Landroid/content/Context;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "viewModel", "Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;", "confirmCredentialActivityLauncher", "Ljava/lang/Runnable;", "clientExecutor", "Ljava/util/concurrent/Executor;", "clientAuthenticationCallback", "Landroidx/biometric/BiometricPrompt$AuthenticationCallback;", "<init>", "(Landroid/content/Context;Landroidx/lifecycle/LifecycleOwner;Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;Ljava/lang/Runnable;Ljava/util/concurrent/Executor;Landroidx/biometric/BiometricPrompt$AuthenticationCallback;)V", "key", "", "getKey", "()I", "internalHandler", "getInternalHandler$annotations", "()V", "getInternalHandler", "()Landroidx/biometric/internal/AuthenticationHandler;", "setInternalHandler", "(Landroidx/biometric/internal/AuthenticationHandler;)V", "authenticate", "", "info", "Landroidx/biometric/BiometricPrompt$PromptInfo;", "crypto", "Landroidx/biometric/BiometricPrompt$CryptoObject;", "cancelAuthentication", "canceledFrom", "Landroidx/biometric/internal/data/CanceledFrom;", "createHandler", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DefaultAuthenticationHandler implements AuthenticationHandler {
    private final BiometricPrompt.AuthenticationCallback clientAuthenticationCallback;
    private final Executor clientExecutor;
    private final Runnable confirmCredentialActivityLauncher;
    private final Context context;
    private AuthenticationHandler internalHandler;
    private final int key;
    private final LifecycleOwner lifecycleOwner;
    private final AuthenticationViewModel viewModel;

    public static /* synthetic */ void getInternalHandler$annotations() {
    }

    public DefaultAuthenticationHandler(Context context, LifecycleOwner lifecycleOwner, AuthenticationViewModel viewModel, Runnable confirmCredentialActivityLauncher, Executor clientExecutor, BiometricPrompt.AuthenticationCallback clientAuthenticationCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(confirmCredentialActivityLauncher, "confirmCredentialActivityLauncher");
        Intrinsics.checkNotNullParameter(clientExecutor, "clientExecutor");
        Intrinsics.checkNotNullParameter(clientAuthenticationCallback, "clientAuthenticationCallback");
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
        this.viewModel = viewModel;
        this.confirmCredentialActivityLauncher = confirmCredentialActivityLauncher;
        this.clientExecutor = clientExecutor;
        this.clientAuthenticationCallback = clientAuthenticationCallback;
        int iGenerateNextHandlerKey = viewModel.generateNextHandlerKey();
        this.key = iGenerateNextHandlerKey;
        if (viewModel.isPromptShowing() && iGenerateNextHandlerKey == viewModel.getCurrentAuthenticationKey()) {
            this.internalHandler = createHandler();
        }
    }

    public final int getKey() {
        return this.key;
    }

    public final AuthenticationHandler getInternalHandler() {
        return this.internalHandler;
    }

    public final void setInternalHandler(AuthenticationHandler authenticationHandler) {
        this.internalHandler = authenticationHandler;
    }

    @Override // androidx.biometric.internal.AuthenticationHandler
    public void authenticate(BiometricPrompt.PromptInfo info, BiometricPrompt.CryptoObject crypto) {
        Intrinsics.checkNotNullParameter(info, "info");
        this.viewModel.setCurrentAuthenticationKey(this.key);
        this.viewModel.setPromptShowing(true);
        this.viewModel.setAwaitingResult(true);
        this.viewModel.setPromptInfo(info);
        this.viewModel.setIdentityCheckAvailable(BiometricManager.from(this.context).isIdentityCheckAvailable());
        this.viewModel.setCryptoObject(crypto);
        AuthenticationHandler authenticationHandlerCreateHandler = createHandler();
        this.internalHandler = authenticationHandlerCreateHandler;
        if (authenticationHandlerCreateHandler != null) {
            authenticationHandlerCreateHandler.authenticate(info, crypto);
        }
    }

    @Override // androidx.biometric.internal.AuthenticationHandler
    public void cancelAuthentication(CanceledFrom canceledFrom) {
        Intrinsics.checkNotNullParameter(canceledFrom, "canceledFrom");
        AuthenticationHandler authenticationHandler = this.internalHandler;
        if (authenticationHandler != null) {
            authenticationHandler.cancelAuthentication(canceledFrom);
        }
        this.internalHandler = null;
    }

    private final AuthenticationHandler createHandler() {
        int allowedAuthenticators = this.viewModel.getAllowedAuthenticators();
        this.viewModel.setUsingKeyguardManagerForBiometricAndCredential(DefaultAuthenticationHandlerKt.isKeyguardManagerNeededForBiometricAndCredential(allowedAuthenticators));
        boolean z = false;
        boolean z2 = DeviceUtils.isWearOS(this.context) || this.viewModel.isUsingKeyguardManagerForBiometricAndCredential() || DefaultAuthenticationHandlerKt.isKeyguardManagerNeededForNoBiometric(this.context, allowedAuthenticators);
        if (!z2 && DefaultAuthenticationHandlerKt.isUsingFingerprintDialog(this.context, this.viewModel.getCryptoObject())) {
            z = true;
        }
        AuthenticationManager authenticationManager = new AuthenticationManager(this.context, this.lifecycleOwner, this.viewModel, this.confirmCredentialActivityLauncher, this.clientExecutor, this.clientAuthenticationCallback, new Function0() { // from class: androidx.biometric.internal.DefaultAuthenticationHandler$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DefaultAuthenticationHandler.createHandler$lambda$0(this.f$0);
            }
        });
        if (z2) {
            return new AuthenticationHandlerKeyguardManager(authenticationManager);
        }
        if (z) {
            return new AuthenticationHandlerFingerprintManager(authenticationManager);
        }
        return new AuthenticationHandlerBiometricPrompt(authenticationManager);
    }

    static final Unit createHandler$lambda$0(DefaultAuthenticationHandler defaultAuthenticationHandler) {
        defaultAuthenticationHandler.internalHandler = null;
        defaultAuthenticationHandler.viewModel.setCurrentAuthenticationKey(0);
        defaultAuthenticationHandler.viewModel.setPromptShowing(false);
        defaultAuthenticationHandler.viewModel.setConfirmingDeviceCredential(false);
        return Unit.INSTANCE;
    }
}
