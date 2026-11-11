package com.lagradost.cloudstream3.utils;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceManager;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.R;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BiometricAuthenticator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001-B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J \u0010!\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020%H\u0002J\u0010\u0010'\u001a\u00020%2\b\u0010(\u001a\u0004\u0018\u00010)J\u001e\u0010*\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020%J\u000e\u0010+\u001a\u00020%2\u0006\u0010,\u001a\u00020)R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006."}, d2 = {"Lcom/lagradost/cloudstream3/utils/BiometricAuthenticator;", "", "<init>", "()V", "TAG", "", "MAX_FAILED_ATTEMPTS", "", "failedAttempts", "biometricManager", "Landroidx/biometric/BiometricManager;", "biometricPrompt", "Landroidx/biometric/BiometricPrompt;", "getBiometricPrompt", "()Landroidx/biometric/BiometricPrompt;", "setBiometricPrompt", "(Landroidx/biometric/BiometricPrompt;)V", "promptInfo", "Landroidx/biometric/BiometricPrompt$PromptInfo;", "getPromptInfo", "()Landroidx/biometric/BiometricPrompt$PromptInfo;", "setPromptInfo", "(Landroidx/biometric/BiometricPrompt$PromptInfo;)V", "authCallback", "Lcom/lagradost/cloudstream3/utils/BiometricAuthenticator$BiometricCallback;", "getAuthCallback", "()Lcom/lagradost/cloudstream3/utils/BiometricAuthenticator$BiometricCallback;", "setAuthCallback", "(Lcom/lagradost/cloudstream3/utils/BiometricAuthenticator$BiometricCallback;)V", "initializeBiometrics", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "authenticationDialog", "Landroid/app/Activity;", "title", "setDeviceCred", "", "isBiometricHardWareAvailable", "deviceHasPasswordPinLock", "context", "Landroid/content/Context;", "startBiometricAuthentication", "isAuthEnabled", "ctx", "BiometricCallback", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class BiometricAuthenticator {
    public static final BiometricAuthenticator INSTANCE = new BiometricAuthenticator();
    private static final int MAX_FAILED_ATTEMPTS = 3;
    public static final String TAG = "cs3Auth";
    private static BiometricCallback authCallback;
    private static BiometricManager biometricManager;
    private static BiometricPrompt biometricPrompt;
    private static int failedAttempts;
    private static BiometricPrompt.PromptInfo promptInfo;

    /* JADX INFO: compiled from: BiometricAuthenticator.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005À\u0006\u0003"}, d2 = {"Lcom/lagradost/cloudstream3/utils/BiometricAuthenticator$BiometricCallback;", "", "onAuthenticationSuccess", "", "onAuthenticationError", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface BiometricCallback {
        void onAuthenticationError();

        void onAuthenticationSuccess();
    }

    private BiometricAuthenticator() {
    }

    public final BiometricPrompt getBiometricPrompt() {
        return biometricPrompt;
    }

    public final void setBiometricPrompt(BiometricPrompt biometricPrompt2) {
        biometricPrompt = biometricPrompt2;
    }

    public final BiometricPrompt.PromptInfo getPromptInfo() {
        return promptInfo;
    }

    public final void setPromptInfo(BiometricPrompt.PromptInfo promptInfo2) {
        promptInfo = promptInfo2;
    }

    public final BiometricCallback getAuthCallback() {
        return authCallback;
    }

    public final void setAuthCallback(BiometricCallback biometricCallback) {
        authCallback = biometricCallback;
    }

    private final void initializeBiometrics(final FragmentActivity activity) {
        FragmentActivity fragmentActivity = activity;
        Executor mainExecutor = ContextCompat.getMainExecutor(fragmentActivity);
        Intrinsics.checkNotNullExpressionValue(mainExecutor, "getMainExecutor(...)");
        biometricManager = BiometricManager.from(fragmentActivity);
        biometricPrompt = new BiometricPrompt(activity, mainExecutor, new BiometricPrompt.AuthenticationCallback() { // from class: com.lagradost.cloudstream3.utils.BiometricAuthenticator.initializeBiometrics.1
            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                Intrinsics.checkNotNullParameter(errString, "errString");
                super.onAuthenticationError(errorCode, errString);
                CommonActivity.showToast$default(CommonActivity.INSTANCE, String.valueOf(errString), (Integer) null, 2, (Object) null);
                Log.e(BiometricAuthenticator.TAG, String.valueOf(errorCode));
                BiometricCallback authCallback2 = BiometricAuthenticator.INSTANCE.getAuthCallback();
                if (authCallback2 != null) {
                    authCallback2.onAuthenticationError();
                }
            }

            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                super.onAuthenticationSucceeded(result);
                BiometricAuthenticator biometricAuthenticator = BiometricAuthenticator.INSTANCE;
                BiometricAuthenticator.failedAttempts = 0;
                BiometricCallback authCallback2 = BiometricAuthenticator.INSTANCE.getAuthCallback();
                if (authCallback2 != null) {
                    authCallback2.onAuthenticationSuccess();
                }
            }

            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                int i = BiometricAuthenticator.failedAttempts;
                BiometricAuthenticator biometricAuthenticator = BiometricAuthenticator.INSTANCE;
                BiometricAuthenticator.failedAttempts = i + 1;
                if (BiometricAuthenticator.failedAttempts >= 3) {
                    BiometricAuthenticator biometricAuthenticator2 = BiometricAuthenticator.INSTANCE;
                    BiometricAuthenticator.failedAttempts = 0;
                    activity.finish();
                }
            }
        });
    }

    private final void authenticationDialog(Activity activity, int title, boolean setDeviceCred) {
        String string = activity.getString(R.string.biometric_prompt_description);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        if (setDeviceCred) {
            if (Build.VERSION.SDK_INT >= 30) {
                promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle(activity.getString(title)).setDescription(string).setAllowedAuthenticators(33023).build();
                return;
            } else {
                promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle(activity.getString(title)).setDescription(string).setDeviceCredentialAllowed(true).build();
                return;
            }
        }
        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle(activity.getString(title)).setDescription(string).setDeviceCredentialAllowed(true).build();
    }

    private final boolean isBiometricHardWareAvailable() {
        Integer numValueOf;
        if (Build.VERSION.SDK_INT >= 36) {
            BiometricManager biometricManager2 = biometricManager;
            numValueOf = biometricManager2 != null ? Integer.valueOf(biometricManager2.canAuthenticate(33023)) : null;
            if (numValueOf != null && numValueOf.intValue() == 0) {
                return true;
            }
            if (numValueOf != null && numValueOf.intValue() == 12) {
                return false;
            }
            if (numValueOf != null && numValueOf.intValue() == 1) {
                return false;
            }
            if (numValueOf != null && numValueOf.intValue() == 11) {
                return false;
            }
            if (numValueOf != null && numValueOf.intValue() == 21) {
                return false;
            }
            if (numValueOf != null && numValueOf.intValue() == 15) {
                return true;
            }
            if (numValueOf != null && numValueOf.intValue() == -2) {
                return true;
            }
            if (numValueOf != null) {
                numValueOf.intValue();
                return false;
            }
        } else if (Build.VERSION.SDK_INT >= 30) {
            BiometricManager biometricManager3 = biometricManager;
            numValueOf = biometricManager3 != null ? Integer.valueOf(biometricManager3.canAuthenticate(33023)) : null;
            if (numValueOf != null && numValueOf.intValue() == 0) {
                return true;
            }
            if (numValueOf != null && numValueOf.intValue() == 12) {
                return false;
            }
            if (numValueOf != null && numValueOf.intValue() == 1) {
                return false;
            }
            if (numValueOf != null && numValueOf.intValue() == 11) {
                return false;
            }
            if (numValueOf != null && numValueOf.intValue() == 15) {
                return true;
            }
            if (numValueOf != null && numValueOf.intValue() == -2) {
                return true;
            }
            if (numValueOf != null) {
                numValueOf.intValue();
                return false;
            }
        } else {
            BiometricManager biometricManager4 = biometricManager;
            numValueOf = biometricManager4 != null ? Integer.valueOf(biometricManager4.canAuthenticate()) : null;
            if (numValueOf != null && numValueOf.intValue() == 0) {
                return true;
            }
            if (numValueOf != null && numValueOf.intValue() == 12) {
                return false;
            }
            if (numValueOf != null && numValueOf.intValue() == 1) {
                return false;
            }
            if (numValueOf != null && numValueOf.intValue() == 11) {
                return false;
            }
            if (numValueOf != null && numValueOf.intValue() == 15) {
                return true;
            }
            if (numValueOf != null && numValueOf.intValue() == -2) {
                return true;
            }
            if (numValueOf != null) {
                numValueOf.intValue();
                return false;
            }
        }
        return false;
    }

    public final boolean deviceHasPasswordPinLock(Context context) {
        Object systemService = context != null ? context.getSystemService("keyguard") : null;
        KeyguardManager keyguardManager = systemService instanceof KeyguardManager ? (KeyguardManager) systemService : null;
        if (keyguardManager != null) {
            return keyguardManager.isKeyguardSecure();
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void startBiometricAuthentication(FragmentActivity activity, int title, boolean setDeviceCred) {
        BiometricPrompt biometricPrompt2;
        BiometricPrompt biometricPrompt3;
        Intrinsics.checkNotNullParameter(activity, "activity");
        initializeBiometrics(activity);
        boolean z = activity instanceof BiometricCallback;
        authCallback = z ? (BiometricCallback) activity : null;
        if (isBiometricHardWareAvailable()) {
            authCallback = z ? (BiometricCallback) activity : null;
            authenticationDialog(activity, title, setDeviceCred);
            BiometricPrompt.PromptInfo promptInfo2 = promptInfo;
            if (promptInfo2 == null || (biometricPrompt3 = biometricPrompt) == null) {
                return;
            }
            biometricPrompt3.authenticate(promptInfo2);
            return;
        }
        if (deviceHasPasswordPinLock(activity)) {
            authCallback = z ? (BiometricCallback) activity : null;
            authenticationDialog(activity, R.string.password_pin_authentication_title, true);
            BiometricPrompt.PromptInfo promptInfo3 = promptInfo;
            if (promptInfo3 == null || (biometricPrompt2 = biometricPrompt) == null) {
                return;
            }
            biometricPrompt2.authenticate(promptInfo3);
            return;
        }
        CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.biometric_unsupported, (Integer) null, 2, (Object) null);
    }

    public final boolean isAuthEnabled(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        return PreferenceManager.getDefaultSharedPreferences(ctx).getBoolean(ContextCompat.getString(ctx, R.string.biometric_key), false);
    }
}
