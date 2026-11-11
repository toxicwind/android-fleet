package androidx.biometric.internal;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.biometrics.BiometricPrompt$AuthenticationCallback;
import android.hardware.biometrics.PromptContentView;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.R;
import androidx.biometric.utils.AuthenticatorUtils;
import androidx.biometric.utils.BiometricErrorData;
import androidx.biometric.utils.CryptoObjectUtils;
import androidx.biometric.utils.DeviceUtils;
import androidx.biometric.utils.ErrorUtils;
import androidx.biometric.utils.KeyguardUtils;
import androidx.biometric.utils.PackageUtils;
import androidx.biometric.utils.PromptContentViewUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: loaded from: classes.dex */
public class BiometricFragment extends Fragment {
    private static final String ARG_HAS_FACE = "has_face";
    private static final String ARG_HAS_FINGERPRINT = "has_fingerprint";
    private static final String ARG_HAS_IRIS = "has_iris";
    private static final String ARG_HOST_ACTIVITY = "host_activity";
    public static final int CANCELED_FROM_CLIENT = 3;
    public static final int CANCELED_FROM_INTERNAL = 0;
    public static final int CANCELED_FROM_MORE_OPTIONS_BUTTON = 4;
    public static final int CANCELED_FROM_NEGATIVE_BUTTON = 2;
    public static final int CANCELED_FROM_USER = 1;
    private static final int DISMISS_INSTANTLY_DELAY_MS = 500;
    private static final String FINGERPRINT_DIALOG_FRAGMENT_TAG = "androidx.biometric.internal.FingerprintDialogFragment";
    private static final int HIDE_DIALOG_DELAY_MS = 2000;
    private static final int REQUEST_CONFIRM_CREDENTIAL = 1;
    private static final int SHOW_PROMPT_DELAY_MS = 600;
    private static final String TAG = "BiometricFragment";
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private BiometricViewModel mViewModel;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CanceledFrom {
    }

    private static class PromptExecutor implements Executor {
        private final Handler mPromptHandler = new Handler(Looper.getMainLooper());

        PromptExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.mPromptHandler.post(runnable);
        }
    }

    private static class ShowPromptForAuthenticationRunnable implements Runnable {
        private final WeakReference<BiometricFragment> mFragmentRef;

        ShowPromptForAuthenticationRunnable(BiometricFragment biometricFragment) {
            this.mFragmentRef = new WeakReference<>(biometricFragment);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mFragmentRef.get() != null) {
                this.mFragmentRef.get().showPromptForAuthentication();
            }
        }
    }

    private static class StopDelayingPromptRunnable implements Runnable {
        private final WeakReference<BiometricViewModel> mViewModelRef;

        StopDelayingPromptRunnable(BiometricViewModel biometricViewModel) {
            this.mViewModelRef = new WeakReference<>(biometricViewModel);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mViewModelRef.get() != null) {
                this.mViewModelRef.get().setDelayingPrompt(false);
            }
        }
    }

    private static class StopIgnoringCancelRunnable implements Runnable {
        private final WeakReference<BiometricViewModel> mViewModelRef;

        StopIgnoringCancelRunnable(BiometricViewModel biometricViewModel) {
            this.mViewModelRef = new WeakReference<>(biometricViewModel);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mViewModelRef.get() != null) {
                this.mViewModelRef.get().setIgnoringCancel(false);
            }
        }
    }

    public static BiometricFragment newInstance(boolean z) {
        BiometricFragment biometricFragment = new BiometricFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(ARG_HOST_ACTIVITY, z);
        biometricFragment.setArguments(bundle);
        return biometricFragment;
    }

    static BiometricFragment newInstance(Handler handler, BiometricViewModel biometricViewModel, boolean z, boolean z2, boolean z3, boolean z4) {
        BiometricFragment biometricFragment = new BiometricFragment();
        Bundle bundle = new Bundle();
        biometricFragment.mHandler = handler;
        biometricFragment.mViewModel = biometricViewModel;
        bundle.putBoolean(ARG_HOST_ACTIVITY, z);
        bundle.putBoolean(ARG_HAS_FINGERPRINT, z2);
        bundle.putBoolean(ARG_HAS_FACE, z3);
        bundle.putBoolean(ARG_HAS_IRIS, z4);
        biometricFragment.setArguments(bundle);
        return biometricFragment;
    }

    private boolean isHostedInActivity() {
        return getArguments().getBoolean(ARG_HOST_ACTIVITY, true);
    }

    private boolean hasFingerprint() {
        return getArguments().getBoolean(ARG_HAS_FINGERPRINT, PackageUtils.hasSystemFeatureFingerprint(getContext()));
    }

    private boolean hasFace() {
        return getArguments().getBoolean(ARG_HAS_FACE, PackageUtils.hasSystemFeatureFace(getContext()));
    }

    private boolean hasIris() {
        return getArguments().getBoolean(ARG_HAS_IRIS, PackageUtils.hasSystemFeatureIris(getContext()));
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mViewModel == null) {
            this.mViewModel = BiometricPrompt.getViewModel(this, isHostedInActivity());
        }
        connectViewModel();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT == 29 && AuthenticatorUtils.isDeviceCredentialAllowed(this.mViewModel.getAllowedAuthenticators())) {
            this.mViewModel.setIgnoringCancel(true);
            this.mHandler.postDelayed(new StopIgnoringCancelRunnable(this.mViewModel), 250L);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (this.mViewModel.isPromptShowing() && !this.mViewModel.isConfirmingDeviceCredential() && isPermanentRemoving()) {
            cancelAuthentication(0);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            this.mViewModel.setConfirmingDeviceCredential(false);
            handleConfirmCredentialResult(i2);
        }
    }

    private void connectViewModel() {
        this.mViewModel.getAuthenticationResult().observe(this, new Observer() { // from class: androidx.biometric.internal.BiometricFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m28x8b5afa70((BiometricPrompt.AuthenticationResult) obj);
            }
        });
        this.mViewModel.getAuthenticationError().observe(this, new Observer() { // from class: androidx.biometric.internal.BiometricFragment$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m29xd520b1((BiometricErrorData) obj);
            }
        });
        this.mViewModel.getAuthenticationHelpMessage().observe(this, new Observer() { // from class: androidx.biometric.internal.BiometricFragment$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m30x764f46f2((CharSequence) obj);
            }
        });
        this.mViewModel.isAuthenticationFailurePending().observe(this, new Observer() { // from class: androidx.biometric.internal.BiometricFragment$$ExternalSyntheticLambda8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m31xebc96d33((Boolean) obj);
            }
        });
        this.mViewModel.isNegativeButtonPressPending().observe(this, new Observer() { // from class: androidx.biometric.internal.BiometricFragment$$ExternalSyntheticLambda9
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m32x61439374((Boolean) obj);
            }
        });
        this.mViewModel.isMoreOptionsButtonPressPending().observe(this, new Observer() { // from class: androidx.biometric.internal.BiometricFragment$$ExternalSyntheticLambda10
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m33xd6bdb9b5((Boolean) obj);
            }
        });
        this.mViewModel.isFingerprintDialogCancelPending().observe(this, new Observer() { // from class: androidx.biometric.internal.BiometricFragment$$ExternalSyntheticLambda11
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m34x4c37dff6((Boolean) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$connectViewModel$0$androidx-biometric-internal-BiometricFragment, reason: not valid java name */
    /* synthetic */ void m28x8b5afa70(BiometricPrompt.AuthenticationResult authenticationResult) {
        if (authenticationResult != null) {
            onAuthenticationSucceeded(authenticationResult);
            this.mViewModel.setAuthenticationResult(null);
        }
    }

    /* JADX INFO: renamed from: lambda$connectViewModel$1$androidx-biometric-internal-BiometricFragment, reason: not valid java name */
    /* synthetic */ void m29xd520b1(BiometricErrorData biometricErrorData) {
        if (biometricErrorData != null) {
            onAuthenticationError(biometricErrorData.getErrorCode(), biometricErrorData.getErrorMessage());
            this.mViewModel.setAuthenticationError(null);
        }
    }

    /* JADX INFO: renamed from: lambda$connectViewModel$2$androidx-biometric-internal-BiometricFragment, reason: not valid java name */
    /* synthetic */ void m30x764f46f2(CharSequence charSequence) {
        if (charSequence != null) {
            onAuthenticationHelp(charSequence);
            this.mViewModel.setAuthenticationError(null);
        }
    }

    /* JADX INFO: renamed from: lambda$connectViewModel$3$androidx-biometric-internal-BiometricFragment, reason: not valid java name */
    /* synthetic */ void m31xebc96d33(Boolean bool) {
        if (bool.booleanValue()) {
            onAuthenticationFailed();
            this.mViewModel.setAuthenticationFailurePending(false);
        }
    }

    /* JADX INFO: renamed from: lambda$connectViewModel$4$androidx-biometric-internal-BiometricFragment, reason: not valid java name */
    /* synthetic */ void m32x61439374(Boolean bool) {
        if (bool.booleanValue()) {
            if (isManagingDeviceCredentialButton()) {
                onDeviceCredentialButtonPressed();
            } else {
                onCancelButtonPressed();
            }
            this.mViewModel.setNegativeButtonPressPending(false);
        }
    }

    /* JADX INFO: renamed from: lambda$connectViewModel$5$androidx-biometric-internal-BiometricFragment, reason: not valid java name */
    /* synthetic */ void m33xd6bdb9b5(Boolean bool) {
        if (bool.booleanValue()) {
            onMoreOptionsButtonPressed();
            this.mViewModel.setMoreOptionsButtonPressPending(false);
        }
    }

    /* JADX INFO: renamed from: lambda$connectViewModel$6$androidx-biometric-internal-BiometricFragment, reason: not valid java name */
    /* synthetic */ void m34x4c37dff6(Boolean bool) {
        if (bool.booleanValue()) {
            cancelAuthentication(1);
            dismiss();
            this.mViewModel.setFingerprintDialogCancelPending(false);
        }
    }

    public void authenticate(BiometricPrompt.PromptInfo promptInfo, BiometricPrompt.CryptoObject cryptoObject) {
        this.mViewModel.setPromptInfo(promptInfo);
        this.mViewModel.setIsIdentityCheckAvailable(BiometricManager.from(requireContext()).isIdentityCheckAvailable());
        this.mViewModel.setCryptoObject(cryptoObject);
        boolean zIsManagingDeviceCredentialButton = isManagingDeviceCredentialButton();
        BiometricViewModel biometricViewModel = this.mViewModel;
        if (zIsManagingDeviceCredentialButton) {
            biometricViewModel.setNegativeButtonTextOverride(getString(R.string.confirm_device_credential_password));
        } else {
            biometricViewModel.setNegativeButtonTextOverride(null);
        }
        boolean zIsKeyguardManagerNeededForNoBiometric = isKeyguardManagerNeededForNoBiometric();
        BiometricViewModel biometricViewModel2 = this.mViewModel;
        if (zIsKeyguardManagerNeededForNoBiometric) {
            biometricViewModel2.setAwaitingResult(true);
            launchConfirmCredentialActivity();
        } else if (biometricViewModel2.isDelayingPrompt()) {
            this.mHandler.postDelayed(new ShowPromptForAuthenticationRunnable(this), 600L);
        } else {
            showPromptForAuthentication();
        }
    }

    void showPromptForAuthentication() {
        if (this.mViewModel.isPromptShowing()) {
            return;
        }
        if (getContext() == null) {
            Log.w(TAG, "Not showing biometric prompt. Context is null.");
            return;
        }
        this.mViewModel.setPromptShowing(true);
        this.mViewModel.setAwaitingResult(true);
        if (isKeyguardManagerNeededForBiometricAndCredential()) {
            launchConfirmCredentialActivity();
        } else if (isUsingFingerprintDialog()) {
            showFingerprintDialogForAuthentication();
        } else {
            showBiometricPromptForAuthentication();
        }
    }

    private void showFingerprintDialogForAuthentication() {
        Context applicationContext = requireContext().getApplicationContext();
        FingerprintManagerCompat fingerprintManagerCompatFrom = FingerprintManagerCompat.from(applicationContext);
        int iCheckForFingerprintPreAuthenticationErrors = checkForFingerprintPreAuthenticationErrors(fingerprintManagerCompatFrom);
        if (iCheckForFingerprintPreAuthenticationErrors != 0) {
            m35xc9ad2a75(iCheckForFingerprintPreAuthenticationErrors, ErrorUtils.getFingerprintErrorString(applicationContext, iCheckForFingerprintPreAuthenticationErrors));
            return;
        }
        if (isAdded()) {
            this.mViewModel.setFingerprintDialogDismissedInstantly(true);
            if (!DeviceUtils.shouldHideFingerprintDialog(applicationContext, Build.MODEL)) {
                this.mHandler.postDelayed(new Runnable() { // from class: androidx.biometric.internal.BiometricFragment$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m39x1e83a64f();
                    }
                }, 500L);
                FingerprintDialogFragment.newInstance(isHostedInActivity()).show(getParentFragmentManager(), FINGERPRINT_DIALOG_FRAGMENT_TAG);
            }
            this.mViewModel.setCanceledFrom(0);
            authenticateWithFingerprint(fingerprintManagerCompatFrom, applicationContext);
        }
    }

    /* JADX INFO: renamed from: lambda$showFingerprintDialogForAuthentication$7$androidx-biometric-internal-BiometricFragment, reason: not valid java name */
    /* synthetic */ void m39x1e83a64f() {
        this.mViewModel.setFingerprintDialogDismissedInstantly(false);
    }

    private void showBiometricPromptForAuthentication() {
        android.hardware.biometrics.BiometricPrompt.Builder builderCreatePromptBuilder = Api28Impl.createPromptBuilder(requireContext().getApplicationContext());
        CharSequence title = this.mViewModel.getTitle();
        CharSequence subtitle = this.mViewModel.getSubtitle();
        CharSequence description = this.mViewModel.getDescription();
        if (title != null) {
            Api28Impl.setTitle(builderCreatePromptBuilder, title);
        }
        if (subtitle != null) {
            Api28Impl.setSubtitle(builderCreatePromptBuilder, subtitle);
        }
        if (description != null) {
            Api28Impl.setDescription(builderCreatePromptBuilder, description);
        }
        CharSequence negativeButtonText = this.mViewModel.getNegativeButtonText();
        if (!TextUtils.isEmpty(negativeButtonText)) {
            Api28Impl.setNegativeButton(builderCreatePromptBuilder, negativeButtonText, this.mViewModel.getClientExecutor(), this.mViewModel.getNegativeButtonListener());
        }
        if (Build.VERSION.SDK_INT >= 29) {
            Api29Impl.setConfirmationRequired(builderCreatePromptBuilder, this.mViewModel.isConfirmationRequired());
        }
        int allowedAuthenticators = this.mViewModel.getAllowedAuthenticators();
        if (Build.VERSION.SDK_INT >= 30) {
            Api30Impl.setAllowedAuthenticators(builderCreatePromptBuilder, allowedAuthenticators);
        } else if (Build.VERSION.SDK_INT >= 29) {
            Api29Impl.setDeviceCredentialAllowed(builderCreatePromptBuilder, AuthenticatorUtils.isDeviceCredentialAllowed(allowedAuthenticators));
        }
        if (Build.VERSION.SDK_INT >= 35) {
            int logoRes = this.mViewModel.getLogoRes();
            Bitmap logoBitmap = this.mViewModel.getLogoBitmap();
            String logoDescription = this.mViewModel.getLogoDescription();
            PromptContentView promptContentViewWrapForBiometricPrompt = PromptContentViewUtils.wrapForBiometricPrompt(this.mViewModel.getContentView(), this.mViewModel.getClientExecutor(), this.mViewModel.getMoreOptionsButtonListener());
            if (logoRes != -1) {
                Api35Impl.setLogoRes(builderCreatePromptBuilder, logoRes);
            }
            if (logoBitmap != null) {
                Api35Impl.setLogoBitmap(builderCreatePromptBuilder, logoBitmap);
            }
            if (logoDescription != null && !logoDescription.isEmpty()) {
                Api35Impl.setLogoDescription(builderCreatePromptBuilder, logoDescription);
            }
            if (promptContentViewWrapForBiometricPrompt != null) {
                Api35Impl.setContentView(builderCreatePromptBuilder, promptContentViewWrapForBiometricPrompt);
            }
        }
        authenticateWithBiometricPrompt(Api28Impl.buildPrompt(builderCreatePromptBuilder), getContext());
    }

    void authenticateWithFingerprint(FingerprintManagerCompat fingerprintManagerCompat, Context context) {
        try {
            fingerprintManagerCompat.authenticate(CryptoObjectUtils.wrapForFingerprintManager(this.mViewModel.getCryptoObject()), 0, this.mViewModel.getCancellationSignalProvider().getFingerprintCancellationSignal(), this.mViewModel.getAuthenticationCallbackProvider().getFingerprintCallback(), (Handler) null);
        } catch (NullPointerException e) {
            Log.e(TAG, "Got NPE while authenticating with fingerprint.", e);
            m35xc9ad2a75(1, ErrorUtils.getFingerprintErrorString(context, 1));
        }
    }

    void authenticateWithBiometricPrompt(android.hardware.biometrics.BiometricPrompt biometricPrompt, Context context) {
        String string;
        android.hardware.biometrics.BiometricPrompt.CryptoObject cryptoObjectWrapForBiometricPrompt = CryptoObjectUtils.wrapForBiometricPrompt(this.mViewModel.getCryptoObject());
        CancellationSignal biometricCancellationSignal = this.mViewModel.getCancellationSignalProvider().getBiometricCancellationSignal();
        PromptExecutor promptExecutor = new PromptExecutor();
        BiometricPrompt$AuthenticationCallback biometricCallback = this.mViewModel.getAuthenticationCallbackProvider().getBiometricCallback();
        try {
            if (cryptoObjectWrapForBiometricPrompt == null) {
                Api28Impl.authenticate(biometricPrompt, biometricCancellationSignal, promptExecutor, biometricCallback);
            } else {
                Api28Impl.authenticate(biometricPrompt, cryptoObjectWrapForBiometricPrompt, biometricCancellationSignal, promptExecutor, biometricCallback);
            }
        } catch (NullPointerException e) {
            Log.e(TAG, "Got NPE while authenticating with biometric prompt.", e);
            if (context != null) {
                string = context.getString(R.string.default_error_msg);
            } else {
                string = "";
            }
            m35xc9ad2a75(1, string);
        }
    }

    public void cancelAuthentication(int i) {
        if (i == 3 || !this.mViewModel.isIgnoringCancel()) {
            if (isUsingFingerprintDialog()) {
                this.mViewModel.setCanceledFrom(i);
                if (i == 1) {
                    sendErrorToClient(10, ErrorUtils.getFingerprintErrorString(getContext(), 10));
                }
            }
            this.mViewModel.getCancellationSignalProvider().cancel();
        }
    }

    void dismiss() {
        dismissFingerprintDialog();
        this.mViewModel.setPromptShowing(false);
        if (!this.mViewModel.isConfirmingDeviceCredential() && isAdded()) {
            getParentFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
        }
        Context context = getContext();
        if (context == null || !DeviceUtils.shouldDelayShowingPrompt(context, Build.MODEL)) {
            return;
        }
        this.mViewModel.setDelayingPrompt(true);
        this.mHandler.postDelayed(new StopDelayingPromptRunnable(this.mViewModel), 600L);
    }

    private void dismissFingerprintDialog() {
        this.mViewModel.setPromptShowing(false);
        if (isAdded()) {
            FragmentManager parentFragmentManager = getParentFragmentManager();
            FingerprintDialogFragment fingerprintDialogFragment = (FingerprintDialogFragment) parentFragmentManager.findFragmentByTag(FINGERPRINT_DIALOG_FRAGMENT_TAG);
            if (fingerprintDialogFragment != null) {
                if (fingerprintDialogFragment.isAdded()) {
                    fingerprintDialogFragment.dismissAllowingStateLoss();
                } else {
                    parentFragmentManager.beginTransaction().remove(fingerprintDialogFragment).commitAllowingStateLoss();
                }
            }
        }
    }

    void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult authenticationResult) {
        sendSuccessAndDismiss(authenticationResult);
    }

    void onAuthenticationError(int i, final CharSequence charSequence) {
        final int knownErrorCodeForAuthenticate = ErrorUtils.toKnownErrorCodeForAuthenticate(i);
        Context context = getContext();
        if (Build.VERSION.SDK_INT < 29 && ErrorUtils.isLockoutError(knownErrorCodeForAuthenticate) && context != null && KeyguardUtils.isDeviceSecuredWithCredential(context) && AuthenticatorUtils.isDeviceCredentialAllowed(this.mViewModel.getAllowedAuthenticators())) {
            launchConfirmCredentialActivity();
            return;
        }
        if (isUsingFingerprintDialog()) {
            if (charSequence == null) {
                charSequence = ErrorUtils.getFingerprintErrorString(getContext(), knownErrorCodeForAuthenticate);
            }
            BiometricViewModel biometricViewModel = this.mViewModel;
            if (knownErrorCodeForAuthenticate == 5) {
                int canceledFrom = biometricViewModel.getCanceledFrom();
                if (canceledFrom == 0 || canceledFrom == 3) {
                    sendErrorToClient(knownErrorCodeForAuthenticate, charSequence);
                }
                dismiss();
                return;
            }
            if (biometricViewModel.isFingerprintDialogDismissedInstantly()) {
                m35xc9ad2a75(knownErrorCodeForAuthenticate, charSequence);
            } else {
                showFingerprintErrorMessage(charSequence);
                this.mHandler.postDelayed(new Runnable() { // from class: androidx.biometric.internal.BiometricFragment$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m35xc9ad2a75(knownErrorCodeForAuthenticate, charSequence);
                    }
                }, getDismissDialogDelay());
            }
            this.mViewModel.setFingerprintDialogDismissedInstantly(true);
            return;
        }
        if (charSequence == null) {
            charSequence = getString(R.string.default_error_msg) + Stream.ID_UNKNOWN + knownErrorCodeForAuthenticate;
        }
        m35xc9ad2a75(knownErrorCodeForAuthenticate, charSequence);
    }

    void onAuthenticationHelp(CharSequence charSequence) {
        if (isUsingFingerprintDialog()) {
            showFingerprintErrorMessage(charSequence);
        }
    }

    void onAuthenticationFailed() {
        if (isUsingFingerprintDialog()) {
            showFingerprintErrorMessage(getString(R.string.fingerprint_not_recognized));
        }
        sendFailureToClient();
    }

    void onDeviceCredentialButtonPressed() {
        launchConfirmCredentialActivity();
    }

    void onCancelButtonPressed() {
        CharSequence negativeButtonText = this.mViewModel.getNegativeButtonText();
        if (negativeButtonText == null) {
            negativeButtonText = getString(R.string.default_error_msg);
        }
        m35xc9ad2a75(13, negativeButtonText);
        cancelAuthentication(2);
    }

    void onMoreOptionsButtonPressed() {
        m35xc9ad2a75(22, getString(R.string.content_view_more_options_button_clicked));
        cancelAuthentication(4);
    }

    private void launchConfirmCredentialActivity() {
        Context context = getContext();
        KeyguardManager keyguardManager = context != null ? KeyguardUtils.getKeyguardManager(context) : null;
        if (keyguardManager == null) {
            m35xc9ad2a75(12, getString(R.string.generic_error_no_keyguard));
            return;
        }
        CharSequence title = this.mViewModel.getTitle();
        CharSequence subtitle = this.mViewModel.getSubtitle();
        CharSequence description = this.mViewModel.getDescription();
        if (subtitle == null) {
            subtitle = description;
        }
        Intent intentCreateConfirmDeviceCredentialIntent = keyguardManager.createConfirmDeviceCredentialIntent(title, subtitle);
        if (intentCreateConfirmDeviceCredentialIntent == null) {
            m35xc9ad2a75(14, getString(R.string.generic_error_no_device_credential));
            return;
        }
        this.mViewModel.setConfirmingDeviceCredential(true);
        if (isUsingFingerprintDialog()) {
            dismissFingerprintDialog();
        }
        intentCreateConfirmDeviceCredentialIntent.setFlags(134742016);
        startActivityForResult(intentCreateConfirmDeviceCredentialIntent, 1);
    }

    private void handleConfirmCredentialResult(int i) {
        int i2 = -1;
        if (i == -1) {
            if (this.mViewModel.isUsingKeyguardManagerForBiometricAndCredential()) {
                this.mViewModel.setUsingKeyguardManagerForBiometricAndCredential(false);
            } else {
                i2 = 1;
            }
            sendSuccessAndDismiss(new BiometricPrompt.AuthenticationResult(null, i2));
            return;
        }
        m35xc9ad2a75(10, getString(R.string.generic_error_user_canceled));
    }

    private void showFingerprintErrorMessage(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = getString(R.string.default_error_msg);
        }
        this.mViewModel.setFingerprintDialogState(2);
        this.mViewModel.setFingerprintDialogHelpMessage(charSequence);
    }

    private void sendSuccessAndDismiss(BiometricPrompt.AuthenticationResult authenticationResult) {
        sendSuccessToClient(authenticationResult);
        dismiss();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: sendErrorAndDismiss, reason: merged with bridge method [inline-methods] */
    public void m35xc9ad2a75(int i, CharSequence charSequence) {
        sendErrorToClient(i, charSequence);
        dismiss();
    }

    private void sendSuccessToClient(final BiometricPrompt.AuthenticationResult authenticationResult) {
        if (!this.mViewModel.isAwaitingResult()) {
            Log.w(TAG, "Success not sent to client. Client is not awaiting a result.");
        } else {
            this.mViewModel.setAwaitingResult(false);
            this.mViewModel.getClientExecutor().execute(new Runnable() { // from class: androidx.biometric.internal.BiometricFragment$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m38x2d6a06(authenticationResult);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$sendSuccessToClient$9$androidx-biometric-internal-BiometricFragment, reason: not valid java name */
    /* synthetic */ void m38x2d6a06(BiometricPrompt.AuthenticationResult authenticationResult) {
        this.mViewModel.getClientCallback().onAuthenticationSucceeded(authenticationResult);
    }

    private void sendErrorToClient(final int i, final CharSequence charSequence) {
        if (this.mViewModel.isConfirmingDeviceCredential()) {
            Log.v(TAG, "Error not sent to client. User is confirming their device credential.");
        } else if (!this.mViewModel.isAwaitingResult()) {
            Log.w(TAG, "Error not sent to client. Client is not awaiting a result.");
        } else {
            this.mViewModel.setAwaitingResult(false);
            this.mViewModel.getClientExecutor().execute(new Runnable() { // from class: androidx.biometric.internal.BiometricFragment$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m36xef614507(i, charSequence);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$sendErrorToClient$10$androidx-biometric-internal-BiometricFragment, reason: not valid java name */
    /* synthetic */ void m36xef614507(int i, CharSequence charSequence) {
        this.mViewModel.getClientCallback().onAuthenticationError(i, charSequence);
    }

    private void sendFailureToClient() {
        if (!this.mViewModel.isAwaitingResult()) {
            Log.w(TAG, "Failure not sent to client. Client is not awaiting a result.");
        } else {
            this.mViewModel.getClientExecutor().execute(new Runnable() { // from class: androidx.biometric.internal.BiometricFragment$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m37x5e2d54c6();
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$sendFailureToClient$11$androidx-biometric-internal-BiometricFragment, reason: not valid java name */
    /* synthetic */ void m37x5e2d54c6() {
        this.mViewModel.getClientCallback().onAuthenticationFailed();
    }

    private static int checkForFingerprintPreAuthenticationErrors(FingerprintManagerCompat fingerprintManagerCompat) {
        if (fingerprintManagerCompat.isHardwareDetected()) {
            return !fingerprintManagerCompat.hasEnrolledFingerprints() ? 11 : 0;
        }
        return 12;
    }

    boolean isManagingDeviceCredentialButton() {
        return Build.VERSION.SDK_INT <= 28 && AuthenticatorUtils.isDeviceCredentialAllowed(this.mViewModel.getAllowedAuthenticators());
    }

    private boolean isUsingFingerprintDialog() {
        return Build.VERSION.SDK_INT < 28 || isFingerprintDialogNeededForCrypto() || isFingerprintDialogNeededForErrorHandling();
    }

    private boolean isFingerprintDialogNeededForCrypto() {
        Context context = getContext();
        return (context == null || this.mViewModel.getCryptoObject() == null || !DeviceUtils.shouldUseFingerprintForCrypto(context, Build.MANUFACTURER, Build.MODEL)) ? false : true;
    }

    private boolean isFingerprintDialogNeededForErrorHandling() {
        return Build.VERSION.SDK_INT == 28 && !hasFingerprint();
    }

    private boolean isKeyguardManagerNeededForNoBiometric() {
        Context context = getContext();
        if (Build.VERSION.SDK_INT != 29 || hasFingerprint() || hasFace() || hasIris()) {
            return isManagingDeviceCredentialButton() && BiometricManager.from(context).canAuthenticate(255) != 0;
        }
        return true;
    }

    private boolean isKeyguardManagerNeededForBiometricAndCredential() {
        Context context = getContext();
        if (context == null) {
            return false;
        }
        if (DeviceUtils.isWearOS(context)) {
            return true;
        }
        if (Build.VERSION.SDK_INT == 29) {
            int allowedAuthenticators = this.mViewModel.getAllowedAuthenticators();
            if (AuthenticatorUtils.isWeakBiometricAllowed(allowedAuthenticators) && AuthenticatorUtils.isDeviceCredentialAllowed(allowedAuthenticators)) {
                this.mViewModel.setUsingKeyguardManagerForBiometricAndCredential(true);
                return true;
            }
        }
        return false;
    }

    private boolean isPermanentRemoving() {
        FragmentActivity activity = getActivity();
        if (isRemoving()) {
            return activity == null || !activity.isChangingConfigurations();
        }
        return false;
    }

    private int getDismissDialogDelay() {
        Context context = getContext();
        return (context == null || !DeviceUtils.shouldHideFingerprintDialog(context, Build.MODEL)) ? 2000 : 0;
    }

    private static class Api35Impl {
        private Api35Impl() {
        }

        static void setLogoRes(android.hardware.biometrics.BiometricPrompt.Builder builder, int i) {
            builder.setLogoRes(i);
        }

        static void setLogoBitmap(android.hardware.biometrics.BiometricPrompt.Builder builder, Bitmap bitmap) {
            builder.setLogoBitmap(bitmap);
        }

        static void setLogoDescription(android.hardware.biometrics.BiometricPrompt.Builder builder, String str) {
            builder.setLogoDescription(str);
        }

        static void setContentView(android.hardware.biometrics.BiometricPrompt.Builder builder, PromptContentView promptContentView) {
            builder.setContentView(promptContentView);
        }
    }

    private static class Api30Impl {
        private Api30Impl() {
        }

        static void setAllowedAuthenticators(android.hardware.biometrics.BiometricPrompt.Builder builder, int i) {
            builder.setAllowedAuthenticators(i);
        }
    }

    private static class Api29Impl {
        private Api29Impl() {
        }

        static void setConfirmationRequired(android.hardware.biometrics.BiometricPrompt.Builder builder, boolean z) {
            builder.setConfirmationRequired(z);
        }

        static void setDeviceCredentialAllowed(android.hardware.biometrics.BiometricPrompt.Builder builder, boolean z) {
            builder.setDeviceCredentialAllowed(z);
        }
    }

    private static class Api28Impl {
        private Api28Impl() {
        }

        static android.hardware.biometrics.BiometricPrompt.Builder createPromptBuilder(Context context) {
            return new android.hardware.biometrics.BiometricPrompt.Builder(context);
        }

        static void setTitle(android.hardware.biometrics.BiometricPrompt.Builder builder, CharSequence charSequence) {
            builder.setTitle(charSequence);
        }

        static void setSubtitle(android.hardware.biometrics.BiometricPrompt.Builder builder, CharSequence charSequence) {
            builder.setSubtitle(charSequence);
        }

        static void setDescription(android.hardware.biometrics.BiometricPrompt.Builder builder, CharSequence charSequence) {
            builder.setDescription(charSequence);
        }

        static void setNegativeButton(android.hardware.biometrics.BiometricPrompt.Builder builder, CharSequence charSequence, Executor executor, DialogInterface.OnClickListener onClickListener) {
            builder.setNegativeButton(charSequence, executor, onClickListener);
        }

        static android.hardware.biometrics.BiometricPrompt buildPrompt(android.hardware.biometrics.BiometricPrompt.Builder builder) {
            return builder.build();
        }

        static void authenticate(android.hardware.biometrics.BiometricPrompt biometricPrompt, CancellationSignal cancellationSignal, Executor executor, BiometricPrompt$AuthenticationCallback biometricPrompt$AuthenticationCallback) {
            biometricPrompt.authenticate(cancellationSignal, executor, biometricPrompt$AuthenticationCallback);
        }

        static void authenticate(android.hardware.biometrics.BiometricPrompt biometricPrompt, android.hardware.biometrics.BiometricPrompt.CryptoObject cryptoObject, CancellationSignal cancellationSignal, Executor executor, BiometricPrompt$AuthenticationCallback biometricPrompt$AuthenticationCallback) {
            biometricPrompt.authenticate(cryptoObject, cancellationSignal, executor, biometricPrompt$AuthenticationCallback);
        }
    }
}
