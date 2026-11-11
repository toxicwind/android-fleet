package androidx.biometric.utils;

import android.os.Build;
import androidx.biometric.BiometricPrompt;

/* JADX INFO: loaded from: classes.dex */
public class AuthenticatorUtils {
    private static final int BIOMETRIC_CLASS_MASK = 32767;

    public static int getBiometricAuthenticators(int i) {
        return i & 32767;
    }

    public static boolean isAtLeastStrength(int i, int i2) {
        int i3 = i & 32767;
        if (((~i2) & i3) != 0) {
            return false;
        }
        for (int i4 = 1; i4 <= i2; i4 = (i4 << 1) | 1) {
            if (i4 == i3) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDeviceCredentialAllowed(int i) {
        return (i & 32768) != 0;
    }

    public static boolean isWeakBiometricAllowed(int i) {
        return (i & 255) == 255;
    }

    private AuthenticatorUtils() {
    }

    public static String convertToString(int i) {
        String strValueOf;
        int i2 = (-65537) & i;
        if (i2 == 15) {
            strValueOf = "BIOMETRIC_STRONG";
        } else if (i2 == 255) {
            strValueOf = "BIOMETRIC_WEAK";
        } else if (i2 == 32768) {
            strValueOf = "DEVICE_CREDENTIAL";
        } else if (i2 == 32783) {
            strValueOf = "BIOMETRIC_STRONG | DEVICE_CREDENTIAL";
        } else if (i2 == 33023) {
            strValueOf = "BIOMETRIC_WEAK | DEVICE_CREDENTIAL";
        } else {
            strValueOf = String.valueOf(i);
        }
        if ((i & 65536) != 65536) {
            return strValueOf;
        }
        if (i != 65536) {
            strValueOf = strValueOf + " | ";
        }
        return strValueOf + "IDENTITY_CHECK";
    }

    public static int getConsolidatedAuthenticators(BiometricPrompt.PromptInfo promptInfo, BiometricPrompt.CryptoObject cryptoObject, boolean z) {
        if (promptInfo == null) {
            return 0;
        }
        int allowedAuthenticators = promptInfo.getAllowedAuthenticators();
        if ((allowedAuthenticators & 65536) == 65536 && !z) {
            allowedAuthenticators &= -65537;
        }
        if (allowedAuthenticators != 0) {
            return allowedAuthenticators;
        }
        int i = cryptoObject != null ? 15 : 255;
        return promptInfo.isDeviceCredentialAllowed() ? 32768 | i : i;
    }

    public static boolean isSupportedCombination(int i) {
        int i2 = i & (-65537);
        if (i2 != 15 && i2 != 255) {
            if (i2 == 32768) {
                return Build.VERSION.SDK_INT >= 30;
            }
            if (i2 != 32783) {
                return i2 == 33023 || i2 == 0;
            }
            return Build.VERSION.SDK_INT < 28 || Build.VERSION.SDK_INT > 29;
        }
        return true;
    }

    public static boolean isSomeBiometricAllowed(int i) {
        return getBiometricAuthenticators(i) != 0;
    }
}
