package androidx.biometric.internal;

import android.content.Context;
import android.os.Build;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.utils.AuthenticatorUtils;
import androidx.biometric.utils.DeviceUtils;
import androidx.biometric.utils.KeyguardUtils;
import androidx.biometric.utils.PackageUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultAuthenticationHandler.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0010\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0016\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0001\u001a\u0016\u0010\n\u001a\u00020\u0001*\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002\u001a\f\u0010\u000b\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\u000e\u0010\f\u001a\u00020\u0001*\u0004\u0018\u00010\u0002H\u0000\u001a\u000e\u0010\r\u001a\u00020\u0001*\u0004\u0018\u00010\u0002H\u0002\u001a\u000e\u0010\u000e\u001a\u00020\u0001*\u0004\u0018\u00010\u0002H\u0002¨\u0006\u000f"}, d2 = {"isManagingDeviceCredentialButton", "", "Landroid/content/Context;", "allowedAuthenticators", "", "isKeyguardManagerNeededForBiometricAndCredential", "isKeyguardManagerNeededForNoBiometric", "isUsingFingerprintDialog", "crypto", "Landroidx/biometric/BiometricPrompt$CryptoObject;", "isFingerprintDialogNeededForCrypto", "isFingerprintDialogNeededForErrorHandling", "hasFingerprint", "hasFace", "hasIris", "biometric"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class DefaultAuthenticationHandlerKt {
    public static final boolean isManagingDeviceCredentialButton(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return Build.VERSION.SDK_INT <= 28 && KeyguardUtils.isDeviceSecuredWithCredential(context) && AuthenticatorUtils.isDeviceCredentialAllowed(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isKeyguardManagerNeededForBiometricAndCredential(int i) {
        return Build.VERSION.SDK_INT == 29 && AuthenticatorUtils.isWeakBiometricAllowed(i) && AuthenticatorUtils.isDeviceCredentialAllowed(i);
    }

    public static final boolean isKeyguardManagerNeededForNoBiometric(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        if (Build.VERSION.SDK_INT != 29 || hasFingerprint(context) || hasFace(context) || hasIris(context)) {
            return isManagingDeviceCredentialButton(context, i) && BiometricManager.from(context).canAuthenticate(255) != 0;
        }
        return true;
    }

    public static final boolean isUsingFingerprintDialog(Context context, BiometricPrompt.CryptoObject cryptoObject) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return Build.VERSION.SDK_INT < 28 || isFingerprintDialogNeededForCrypto(context, cryptoObject) || isFingerprintDialogNeededForErrorHandling(context);
    }

    private static final boolean isFingerprintDialogNeededForCrypto(Context context, BiometricPrompt.CryptoObject cryptoObject) {
        return cryptoObject != null && DeviceUtils.shouldUseFingerprintForCrypto(context, Build.MANUFACTURER, Build.MODEL);
    }

    private static final boolean isFingerprintDialogNeededForErrorHandling(Context context) {
        return Build.VERSION.SDK_INT == 28 && !hasFingerprint(context);
    }

    public static final boolean hasFingerprint(Context context) {
        return PackageUtils.hasSystemFeatureFingerprint(context);
    }

    private static final boolean hasFace(Context context) {
        return PackageUtils.hasSystemFeatureFace(context);
    }

    private static final boolean hasIris(Context context) {
        return PackageUtils.hasSystemFeatureIris(context);
    }
}
