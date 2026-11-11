package androidx.biometric.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.biometrics.BiometricPrompt$AuthenticationCallback;
import android.hardware.biometrics.PromptContentView;
import android.os.Build;
import android.os.CancellationSignal;
import android.text.TextUtils;
import android.util.Log;
import androidx.biometric.AuthenticationRequest;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.R;
import androidx.biometric.internal.data.CanceledFrom;
import androidx.biometric.internal.viewmodel.AuthenticationViewModel;
import androidx.biometric.utils.AuthenticatorUtils;
import androidx.biometric.utils.CryptoObjectUtils;
import androidx.biometric.utils.ErrorUtils;
import androidx.biometric.utils.PromptContentViewUtils;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: AuthenticationHandlerBiometricPrompt.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0017H\u0003J\u0018\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0006\u001a\u00020\u0007H\u0003J\b\u0010#\u001a\u00020\u0017H\u0002J\u0010\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020&H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006'"}, d2 = {"Landroidx/biometric/internal/AuthenticationHandlerBiometricPrompt;", "Landroidx/biometric/internal/AuthenticationHandler;", "authenticationManager", "Landroidx/biometric/internal/AuthenticationManager;", "<init>", "(Landroidx/biometric/internal/AuthenticationManager;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "viewModel", "Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;", "getViewModel", "()Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "getLifecycleOwner", "()Landroidx/lifecycle/LifecycleOwner;", "clientExecutor", "Ljava/util/concurrent/Executor;", "getClientExecutor", "()Ljava/util/concurrent/Executor;", "authenticate", "", "info", "Landroidx/biometric/BiometricPrompt$PromptInfo;", "crypto", "Landroidx/biometric/BiometricPrompt$CryptoObject;", "cancelAuthentication", "canceledFrom", "Landroidx/biometric/internal/data/CanceledFrom;", "showAuthentication", "authenticateWithBiometricPrompt", "biometricPrompt", "Landroid/hardware/biometrics/BiometricPrompt;", "onMoreOptionsButtonPressed", "onFallbackOptionPressed", "fallback", "Landroidx/biometric/AuthenticationRequest$Biometric$Fallback$CustomOption;", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AuthenticationHandlerBiometricPrompt implements AuthenticationHandler {
    private final AuthenticationManager authenticationManager;

    public AuthenticationHandlerBiometricPrompt(AuthenticationManager authenticationManager) {
        Intrinsics.checkNotNullParameter(authenticationManager, "authenticationManager");
        this.authenticationManager = authenticationManager;
        final Context context = getContext();
        final AuthenticationViewModel viewModel = getViewModel();
        final Executor clientExecutor = getClientExecutor();
        final BiometricPrompt.AuthenticationCallback clientAuthenticationCallback = authenticationManager.getClientAuthenticationCallback();
        final Runnable confirmCredentialActivityLauncher = authenticationManager.getConfirmCredentialActivityLauncher();
        final Function0 function0 = new Function0() { // from class: androidx.biometric.internal.AuthenticationHandlerBiometricPrompt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AuthenticationHandlerBiometricPrompt._init_$lambda$0(this.f$0);
            }
        };
        authenticationManager.initialize(new AuthenticationResultDispatcher(context, viewModel, clientExecutor, clientAuthenticationCallback, confirmCredentialActivityLauncher, function0) { // from class: androidx.biometric.internal.AuthenticationHandlerBiometricPrompt$resultDispatcher$1
            @Override // androidx.biometric.internal.AuthenticationResultDispatcher
            public void onAuthenticationError(int errorCode, CharSequence errorMessage) {
                int knownErrorCodeForAuthenticate = ErrorUtils.toKnownErrorCodeForAuthenticate(errorCode);
                if (ErrorUtils.isLockoutError(knownErrorCodeForAuthenticate) && getViewModel().isOverriddenDeviceCredential()) {
                    showKMAsFallback();
                    return;
                }
                if (errorMessage == null) {
                    String string = getContext().getString(R.string.default_error_msg);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    errorMessage = string;
                }
                sendErrorAndDismiss(knownErrorCodeForAuthenticate, errorMessage);
            }
        }, new AuthenticationUiStateObserver() { // from class: androidx.biometric.internal.AuthenticationHandlerBiometricPrompt$uiStateObserver$1
            @Override // androidx.biometric.internal.AuthenticationUiStateObserver
            protected Job createObserverJob() {
                return BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0.getLifecycleOwner()), null, null, new AuthenticationHandlerBiometricPrompt$uiStateObserver$1$createObserverJob$1(this.this$0, null), 3, null);
            }
        });
    }

    public final Context getContext() {
        return this.authenticationManager.getContext();
    }

    public final AuthenticationViewModel getViewModel() {
        return this.authenticationManager.getViewModel();
    }

    public final LifecycleOwner getLifecycleOwner() {
        return this.authenticationManager.getLifecycleOwner();
    }

    public final Executor getClientExecutor() {
        return this.authenticationManager.getClientExecutor();
    }

    static final Unit _init_$lambda$0(AuthenticationHandlerBiometricPrompt authenticationHandlerBiometricPrompt) {
        authenticationHandlerBiometricPrompt.authenticationManager.dismiss();
        return Unit.INSTANCE;
    }

    static final Unit authenticate$lambda$0(AuthenticationHandlerBiometricPrompt authenticationHandlerBiometricPrompt) {
        authenticationHandlerBiometricPrompt.showAuthentication();
        return Unit.INSTANCE;
    }

    @Override // androidx.biometric.internal.AuthenticationHandler
    public void authenticate(BiometricPrompt.PromptInfo info, BiometricPrompt.CryptoObject crypto) {
        Intrinsics.checkNotNullParameter(info, "info");
        this.authenticationManager.authenticate(info, crypto, new Function0() { // from class: androidx.biometric.internal.AuthenticationHandlerBiometricPrompt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AuthenticationHandlerBiometricPrompt.authenticate$lambda$0(this.f$0);
            }
        });
    }

    @Override // androidx.biometric.internal.AuthenticationHandler
    public void cancelAuthentication(CanceledFrom canceledFrom) {
        Intrinsics.checkNotNullParameter(canceledFrom, "canceledFrom");
        this.authenticationManager.cancelAuthentication(canceledFrom);
    }

    private final void showAuthentication() {
        Api28Impl api28Impl = Api28Impl.INSTANCE;
        Context applicationContext = getContext().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        android.hardware.biometrics.BiometricPrompt.Builder builderCreatePromptBuilder = api28Impl.createPromptBuilder(applicationContext);
        CharSequence title = getViewModel().getTitle();
        CharSequence subtitle = getViewModel().getSubtitle();
        CharSequence description = getViewModel().getDescription();
        if (title != null) {
            Api28Impl.INSTANCE.setTitle(builderCreatePromptBuilder, title);
        }
        if (subtitle != null) {
            Api28Impl.INSTANCE.setSubtitle(builderCreatePromptBuilder, subtitle);
        }
        if (description != null) {
            Api28Impl.INSTANCE.setDescription(builderCreatePromptBuilder, description);
        }
        CharSequence singleFallbackOptionText = getViewModel().getSingleFallbackOptionText();
        if (singleFallbackOptionText != null && !TextUtils.isEmpty(singleFallbackOptionText)) {
            Api28Impl.INSTANCE.setNegativeButton(builderCreatePromptBuilder, singleFallbackOptionText, getClientExecutor(), getViewModel().getNegativeButtonListener());
        }
        List<AuthenticationRequest.Biometric.Fallback> multipleFallbackOptionList = getViewModel().getMultipleFallbackOptionList();
        if (multipleFallbackOptionList != null) {
            ArrayList<AuthenticationRequest.Biometric.Fallback.CustomOption> arrayList = new ArrayList();
            for (Object obj : multipleFallbackOptionList) {
                if (obj instanceof AuthenticationRequest.Biometric.Fallback.CustomOption) {
                    arrayList.add(obj);
                }
            }
            for (AuthenticationRequest.Biometric.Fallback.CustomOption customOption : arrayList) {
                Api36MinorImpl.INSTANCE.addFallbackOption(builderCreatePromptBuilder, customOption.getText(), customOption.getIconType(), getClientExecutor(), getViewModel().fallbackOptionListener(customOption));
            }
        }
        if (Build.VERSION.SDK_INT >= 29) {
            Api29Impl.INSTANCE.setConfirmationRequired(builderCreatePromptBuilder, getViewModel().isConfirmationRequired());
        }
        int allowedAuthenticators = getViewModel().getAllowedAuthenticators();
        if (Build.VERSION.SDK_INT >= 30) {
            Api30Impl.INSTANCE.setAllowedAuthenticators(builderCreatePromptBuilder, allowedAuthenticators);
        } else if (Build.VERSION.SDK_INT >= 29) {
            Api29Impl.INSTANCE.setDeviceCredentialAllowed(builderCreatePromptBuilder, AuthenticatorUtils.isDeviceCredentialAllowed(allowedAuthenticators));
        }
        if (Build.VERSION.SDK_INT >= 35) {
            int logoRes = getViewModel().getLogoRes();
            Bitmap logoBitmap = getViewModel().getLogoBitmap();
            String logoDescription = getViewModel().getLogoDescription();
            PromptContentView promptContentViewWrapForBiometricPrompt = PromptContentViewUtils.wrapForBiometricPrompt(getViewModel().getContentView(), getClientExecutor(), getViewModel().getMoreOptionsButtonListener());
            if (logoRes != -1) {
                Api35Impl.INSTANCE.setLogoRes(builderCreatePromptBuilder, logoRes);
            }
            if (logoBitmap != null) {
                Api35Impl.INSTANCE.setLogoBitmap(builderCreatePromptBuilder, logoBitmap);
            }
            String str = logoDescription;
            if (str != null && str.length() != 0) {
                Api35Impl.INSTANCE.setLogoDescription(builderCreatePromptBuilder, logoDescription);
            }
            if (promptContentViewWrapForBiometricPrompt != null) {
                Api35Impl.INSTANCE.setContentView(builderCreatePromptBuilder, promptContentViewWrapForBiometricPrompt);
            }
        }
        authenticateWithBiometricPrompt(Api28Impl.INSTANCE.buildPrompt(builderCreatePromptBuilder), getContext());
    }

    private final void authenticateWithBiometricPrompt(android.hardware.biometrics.BiometricPrompt biometricPrompt, Context context) {
        android.hardware.biometrics.BiometricPrompt.CryptoObject cryptoObjectWrapForBiometricPrompt = CryptoObjectUtils.wrapForBiometricPrompt(getViewModel().getCryptoObject());
        CancellationSignal biometricCancellationSignal = getViewModel().getCancellationSignalProvider().getBiometricCancellationSignal();
        Intrinsics.checkNotNullExpressionValue(biometricCancellationSignal, "getBiometricCancellationSignal(...)");
        PromptExecutor promptExecutor = new PromptExecutor();
        BiometricPrompt$AuthenticationCallback biometricCallback = getViewModel().getAuthenticationCallbackProvider().getBiometricCallback();
        Intrinsics.checkNotNullExpressionValue(biometricCallback, "getBiometricCallback(...)");
        try {
            if (cryptoObjectWrapForBiometricPrompt == null) {
                Api28Impl.INSTANCE.authenticate(biometricPrompt, biometricCancellationSignal, promptExecutor, biometricCallback);
            } else {
                Api28Impl.INSTANCE.authenticate(biometricPrompt, cryptoObjectWrapForBiometricPrompt, biometricCancellationSignal, promptExecutor, biometricCallback);
            }
        } catch (NullPointerException e) {
            Log.e("AuthHandlerBP", "Got NPE while authenticating with biometric prompt.", e);
            String string = context.getString(R.string.default_error_msg);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            this.authenticationManager.getResultDispatcher().sendErrorAndDismiss(1, string);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onMoreOptionsButtonPressed() {
        AuthenticationResultDispatcher resultDispatcher = this.authenticationManager.getResultDispatcher();
        String string = getContext().getString(R.string.content_view_more_options_button_clicked);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        resultDispatcher.sendErrorAndDismiss(22, string);
        cancelAuthentication(CanceledFrom.MORE_OPTIONS_BUTTON);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFallbackOptionPressed(AuthenticationRequest.Biometric.Fallback.CustomOption fallback) {
        this.authenticationManager.getResultDispatcher().sendFallbackOptionAndDismiss(fallback);
        cancelAuthentication(CanceledFrom.FALLBACK_OPTION);
    }
}
