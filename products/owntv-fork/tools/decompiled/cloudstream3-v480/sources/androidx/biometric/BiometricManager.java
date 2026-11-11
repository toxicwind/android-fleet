package androidx.biometric;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import androidx.biometric.internal.FingerprintManagerCompat;
import androidx.biometric.utils.AuthenticatorUtils;
import androidx.biometric.utils.CryptoObjectUtils;
import androidx.biometric.utils.DeviceUtils;
import androidx.biometric.utils.ErrorUtils;
import androidx.biometric.utils.KeyguardUtils;
import androidx.biometric.utils.PackageUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class BiometricManager {
    private static final int AUTH_MODALITY_CREDENTIAL = 1;
    private static final int AUTH_MODALITY_FACE = 8;
    private static final int AUTH_MODALITY_FINGERPRINT = 4;
    private static final int AUTH_MODALITY_NONE = 0;
    private static final int AUTH_MODALITY_UNKNOWN_BIOMETRIC = 2;
    public static final int BIOMETRIC_ERROR_HW_UNAVAILABLE = 1;
    public static final int BIOMETRIC_ERROR_IDENTITY_CHECK_NOT_ACTIVE = 20;
    public static final int BIOMETRIC_ERROR_LOCKOUT = 7;
    public static final int BIOMETRIC_ERROR_NONE_ENROLLED = 11;
    public static final int BIOMETRIC_ERROR_NOT_ENABLED_FOR_APPS = 21;
    public static final int BIOMETRIC_ERROR_NO_HARDWARE = 12;
    public static final int BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED = 15;
    public static final int BIOMETRIC_ERROR_UNSUPPORTED = -2;
    public static final int BIOMETRIC_STATUS_UNKNOWN = -1;
    public static final int BIOMETRIC_SUCCESS = 0;
    private static final String TAG = "BiometricManager";
    private final android.hardware.biometrics.BiometricManager mBiometricManager;
    private final FingerprintManagerCompat mFingerprintManager;
    private final Injector mInjector;
    private Boolean mIsIdentityCheckAvailable = null;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AuthenticationStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AuthenticatorTypes {
    }

    public interface Authenticators {
        public static final int BIOMETRIC_MAX_STRENGTH = 1;
        public static final int BIOMETRIC_MIN_STRENGTH = 32767;
        public static final int BIOMETRIC_STRONG = 15;
        public static final int BIOMETRIC_WEAK = 255;
        public static final int DEVICE_CREDENTIAL = 32768;
        public static final int IDENTITY_CHECK = 65536;
    }

    interface Injector {
        android.hardware.biometrics.BiometricManager getBiometricManager();

        FingerprintManagerCompat getFingerprintManager();

        Resources getResources();

        boolean isDeviceSecurable();

        boolean isDeviceSecuredWithCredential();

        boolean isFaceHardwarePresent();

        boolean isFingerprintHardwarePresent();

        boolean isIrisHardwarePresent();

        boolean isStrongBiometricGuaranteed();
    }

    public static class Strings {
        private final android.hardware.biometrics.BiometricManager.Strings mStrings;
        private final StringsCompat mStringsCompat;

        Strings(android.hardware.biometrics.BiometricManager.Strings strings) {
            this.mStrings = strings;
            this.mStringsCompat = null;
        }

        Strings(StringsCompat stringsCompat) {
            this.mStrings = null;
            this.mStringsCompat = stringsCompat;
        }

        public CharSequence getButtonLabel() {
            android.hardware.biometrics.BiometricManager.Strings strings;
            if (Build.VERSION.SDK_INT >= 31 && (strings = this.mStrings) != null) {
                return Api31Impl.getButtonLabel(strings);
            }
            StringsCompat stringsCompat = this.mStringsCompat;
            if (stringsCompat != null) {
                return stringsCompat.getButtonLabel();
            }
            Log.e(BiometricManager.TAG, "Failure in Strings.getButtonLabel(). No available string provider.");
            return null;
        }

        public CharSequence getPromptMessage() {
            android.hardware.biometrics.BiometricManager.Strings strings;
            if (Build.VERSION.SDK_INT >= 31 && (strings = this.mStrings) != null) {
                return Api31Impl.getPromptMessage(strings);
            }
            StringsCompat stringsCompat = this.mStringsCompat;
            if (stringsCompat != null) {
                return stringsCompat.getPromptMessage();
            }
            Log.e(BiometricManager.TAG, "Failure in Strings.getPromptMessage(). No available string provider.");
            return null;
        }

        public CharSequence getSettingName() {
            android.hardware.biometrics.BiometricManager.Strings strings;
            if (Build.VERSION.SDK_INT >= 31 && (strings = this.mStrings) != null) {
                return Api31Impl.getSettingName(strings);
            }
            StringsCompat stringsCompat = this.mStringsCompat;
            if (stringsCompat != null) {
                return stringsCompat.getSettingName();
            }
            Log.e(BiometricManager.TAG, "Failure in Strings.getSettingName(). No available string provider.");
            return null;
        }
    }

    private class StringsCompat {
        private final int mAuthenticators;
        private final int mPossibleModalities;
        private final Resources mResources;

        StringsCompat(Resources resources, int i, boolean z, boolean z2, boolean z3, boolean z4) {
            this.mResources = resources;
            this.mAuthenticators = i;
            int i2 = (z4 && AuthenticatorUtils.isDeviceCredentialAllowed(i)) ? 1 : 0;
            if (AuthenticatorUtils.isSomeBiometricAllowed(i)) {
                i2 = z ? i2 | 4 : i2;
                i2 = z2 ? i2 | 8 : i2;
                if (z3) {
                    i2 |= 2;
                }
            }
            this.mPossibleModalities = i2;
        }

        CharSequence getButtonLabel() {
            int iCanAuthenticate = BiometricManager.this.canAuthenticate(AuthenticatorUtils.getBiometricAuthenticators(this.mAuthenticators));
            int i = this.mPossibleModalities;
            if (iCanAuthenticate != 0) {
                if ((i & 1) != 0) {
                    return this.mResources.getString(R.string.use_screen_lock_label);
                }
                return null;
            }
            int i2 = i & (-2);
            if (i2 == 4) {
                return this.mResources.getString(R.string.use_fingerprint_label);
            }
            Resources resources = this.mResources;
            if (i2 == 8) {
                return resources.getString(R.string.use_face_label);
            }
            return resources.getString(R.string.use_biometric_label);
        }

        CharSequence getPromptMessage() {
            int i;
            int iCanAuthenticate = BiometricManager.this.canAuthenticate(AuthenticatorUtils.getBiometricAuthenticators(this.mAuthenticators));
            int i2 = this.mPossibleModalities;
            if (iCanAuthenticate != 0) {
                if ((i2 & 1) != 0) {
                    return this.mResources.getString(R.string.screen_lock_prompt_message);
                }
                return null;
            }
            int i3 = i2 & (-2);
            if (i3 == 4) {
                if (AuthenticatorUtils.isDeviceCredentialAllowed(this.mAuthenticators)) {
                    i = R.string.fingerprint_or_screen_lock_prompt_message;
                } else {
                    i = R.string.fingerprint_prompt_message;
                }
            } else {
                int i4 = this.mAuthenticators;
                if (i3 == 8) {
                    if (AuthenticatorUtils.isDeviceCredentialAllowed(i4)) {
                        i = R.string.face_or_screen_lock_prompt_message;
                    } else {
                        i = R.string.face_prompt_message;
                    }
                } else if (AuthenticatorUtils.isDeviceCredentialAllowed(i4)) {
                    i = R.string.biometric_or_screen_lock_prompt_message;
                } else {
                    i = R.string.biometric_prompt_message;
                }
            }
            return this.mResources.getString(i);
        }

        CharSequence getSettingName() {
            int i = this.mPossibleModalities;
            if (i == 0) {
                return null;
            }
            if (i == 1) {
                return this.mResources.getString(R.string.use_screen_lock_label);
            }
            if (i == 2) {
                return this.mResources.getString(R.string.use_biometric_label);
            }
            if (i == 4) {
                return this.mResources.getString(R.string.use_fingerprint_label);
            }
            if (i == 8) {
                return this.mResources.getString(R.string.use_face_label);
            }
            if ((i & 1) == 0) {
                return this.mResources.getString(R.string.use_biometric_label);
            }
            int i2 = i & (-2);
            if (i2 == 4) {
                return this.mResources.getString(R.string.use_fingerprint_or_screen_lock_label);
            }
            Resources resources = this.mResources;
            if (i2 == 8) {
                return resources.getString(R.string.use_face_or_screen_lock_label);
            }
            return resources.getString(R.string.use_biometric_or_screen_lock_label);
        }
    }

    private static class DefaultInjector implements Injector {
        private final Context mContext;

        DefaultInjector(Context context) {
            this.mContext = context.getApplicationContext();
        }

        @Override // androidx.biometric.BiometricManager.Injector
        public Resources getResources() {
            return this.mContext.getResources();
        }

        @Override // androidx.biometric.BiometricManager.Injector
        public android.hardware.biometrics.BiometricManager getBiometricManager() {
            return Api29Impl.create(this.mContext);
        }

        @Override // androidx.biometric.BiometricManager.Injector
        public FingerprintManagerCompat getFingerprintManager() {
            return FingerprintManagerCompat.from(this.mContext);
        }

        @Override // androidx.biometric.BiometricManager.Injector
        public boolean isDeviceSecurable() {
            return KeyguardUtils.getKeyguardManager(this.mContext) != null;
        }

        @Override // androidx.biometric.BiometricManager.Injector
        public boolean isDeviceSecuredWithCredential() {
            return KeyguardUtils.isDeviceSecuredWithCredential(this.mContext);
        }

        @Override // androidx.biometric.BiometricManager.Injector
        public boolean isFingerprintHardwarePresent() {
            return PackageUtils.hasSystemFeatureFingerprint(this.mContext);
        }

        @Override // androidx.biometric.BiometricManager.Injector
        public boolean isFaceHardwarePresent() {
            return PackageUtils.hasSystemFeatureFace(this.mContext);
        }

        @Override // androidx.biometric.BiometricManager.Injector
        public boolean isIrisHardwarePresent() {
            return PackageUtils.hasSystemFeatureIris(this.mContext);
        }

        @Override // androidx.biometric.BiometricManager.Injector
        public boolean isStrongBiometricGuaranteed() {
            return DeviceUtils.canAssumeStrongBiometrics(this.mContext, Build.MODEL);
        }
    }

    public static BiometricManager from(Context context) {
        return new BiometricManager(new DefaultInjector(context));
    }

    BiometricManager(Injector injector) {
        this.mInjector = injector;
        this.mBiometricManager = Build.VERSION.SDK_INT >= 29 ? injector.getBiometricManager() : null;
        this.mFingerprintManager = Build.VERSION.SDK_INT <= 29 ? injector.getFingerprintManager() : null;
    }

    @Deprecated
    public int canAuthenticate() {
        return canAuthenticate(255);
    }

    public int canAuthenticate(int i) {
        if (!isIdentityCheckAvailable()) {
            if (i == 65536) {
                return 20;
            }
            if ((i & 65536) == 65536) {
                i &= -65537;
            }
        }
        if (Build.VERSION.SDK_INT >= 30) {
            android.hardware.biometrics.BiometricManager biometricManager = this.mBiometricManager;
            if (biometricManager == null) {
                Log.e(TAG, "Failure in canAuthenticate(). BiometricManager was null.");
                return 1;
            }
            return ErrorUtils.toKnownStatusCodeForCanAuthenticate(Api30Impl.canAuthenticate(biometricManager, i));
        }
        return canAuthenticateCompat(i);
    }

    public boolean isIdentityCheckAvailable() {
        android.hardware.biometrics.BiometricManager biometricManager;
        Boolean bool = this.mIsIdentityCheckAvailable;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (Build.VERSION.SDK_INT < 35 || (biometricManager = this.mBiometricManager) == null) {
            this.mIsIdentityCheckAvailable = false;
        } else {
            try {
                Api30Impl.canAuthenticate(biometricManager, 65536);
                this.mIsIdentityCheckAvailable = true;
            } catch (SecurityException unused) {
                this.mIsIdentityCheckAvailable = false;
            }
        }
        return this.mIsIdentityCheckAvailable.booleanValue();
    }

    private int canAuthenticateCompat(int i) {
        if (!AuthenticatorUtils.isSupportedCombination(i)) {
            return -2;
        }
        if (i == 0 || !this.mInjector.isDeviceSecurable()) {
            return 12;
        }
        if (AuthenticatorUtils.isDeviceCredentialAllowed(i)) {
            return this.mInjector.isDeviceSecuredWithCredential() ? 0 : 11;
        }
        if (Build.VERSION.SDK_INT == 29) {
            if (AuthenticatorUtils.isWeakBiometricAllowed(i)) {
                return canAuthenticateWithWeakBiometricOnApi29();
            }
            return canAuthenticateWithStrongBiometricOnApi29();
        }
        if (Build.VERSION.SDK_INT == 28) {
            if (this.mInjector.isFingerprintHardwarePresent()) {
                return canAuthenticateWithFingerprintOrUnknownBiometric();
            }
            return 12;
        }
        return canAuthenticateWithFingerprint();
    }

    private int canAuthenticateWithStrongBiometricOnApi29() {
        android.hardware.biometrics.BiometricPrompt.CryptoObject cryptoObjectWrapForBiometricPrompt;
        Method canAuthenticateWithCryptoMethod = Api29Impl.getCanAuthenticateWithCryptoMethod();
        if (canAuthenticateWithCryptoMethod != null && (cryptoObjectWrapForBiometricPrompt = CryptoObjectUtils.wrapForBiometricPrompt(CryptoObjectUtils.createFakeCryptoObject())) != null) {
            try {
                Object objInvoke = Build.VERSION.SDK_INT == 29 ? canAuthenticateWithCryptoMethod.invoke(this.mBiometricManager, cryptoObjectWrapForBiometricPrompt) : null;
                if (!(objInvoke instanceof Integer)) {
                    Log.w(TAG, "Invalid return type for canAuthenticate(CryptoObject).");
                } else {
                    return ((Integer) objInvoke).intValue();
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                Log.w(TAG, "Failed to invoke canAuthenticate(CryptoObject).", e);
            }
        }
        int iCanAuthenticateWithWeakBiometricOnApi29 = canAuthenticateWithWeakBiometricOnApi29();
        return (this.mInjector.isStrongBiometricGuaranteed() || iCanAuthenticateWithWeakBiometricOnApi29 != 0) ? iCanAuthenticateWithWeakBiometricOnApi29 : canAuthenticateWithFingerprintOrUnknownBiometric();
    }

    private int canAuthenticateWithWeakBiometricOnApi29() {
        android.hardware.biometrics.BiometricManager biometricManager = this.mBiometricManager;
        if (biometricManager == null) {
            Log.e(TAG, "Failure in canAuthenticate(). BiometricManager was null.");
            return 1;
        }
        return Api29Impl.canAuthenticate(biometricManager);
    }

    private int canAuthenticateWithFingerprintOrUnknownBiometric() {
        if (this.mInjector.isDeviceSecuredWithCredential()) {
            return canAuthenticateWithFingerprint() == 0 ? 0 : -1;
        }
        return canAuthenticateWithFingerprint();
    }

    private int canAuthenticateWithFingerprint() {
        FingerprintManagerCompat fingerprintManagerCompat = this.mFingerprintManager;
        if (fingerprintManagerCompat == null) {
            Log.e(TAG, "Failure in canAuthenticate(). FingerprintManager was null.");
            return 1;
        }
        if (fingerprintManagerCompat.isHardwareDetected()) {
            return !this.mFingerprintManager.hasEnrolledFingerprints() ? 11 : 0;
        }
        return 12;
    }

    public Strings getStrings(int i) {
        if (Build.VERSION.SDK_INT >= 31) {
            if (this.mBiometricManager == null) {
                Log.e(TAG, "Failure in getStrings(). BiometricManager was null.");
                return null;
            }
            return new Strings(Api31Impl.getStrings(this.mBiometricManager, i));
        }
        return new Strings(new StringsCompat(this.mInjector.getResources(), i, this.mInjector.isFingerprintHardwarePresent(), this.mInjector.isFaceHardwarePresent(), this.mInjector.isIrisHardwarePresent(), this.mInjector.isDeviceSecuredWithCredential()));
    }

    private static class Api31Impl {
        private Api31Impl() {
        }

        static android.hardware.biometrics.BiometricManager.Strings getStrings(android.hardware.biometrics.BiometricManager biometricManager, int i) {
            return biometricManager.getStrings(i);
        }

        static CharSequence getButtonLabel(android.hardware.biometrics.BiometricManager.Strings strings) {
            return strings.getButtonLabel();
        }

        static CharSequence getPromptMessage(android.hardware.biometrics.BiometricManager.Strings strings) {
            return strings.getPromptMessage();
        }

        static CharSequence getSettingName(android.hardware.biometrics.BiometricManager.Strings strings) {
            return strings.getSettingName();
        }
    }

    private static class Api30Impl {
        private Api30Impl() {
        }

        static int canAuthenticate(android.hardware.biometrics.BiometricManager biometricManager, int i) {
            return biometricManager.canAuthenticate(i);
        }
    }

    private static class Api29Impl {
        private Api29Impl() {
        }

        static android.hardware.biometrics.BiometricManager create(Context context) {
            return (android.hardware.biometrics.BiometricManager) context.getSystemService(android.hardware.biometrics.BiometricManager.class);
        }

        static int canAuthenticate(android.hardware.biometrics.BiometricManager biometricManager) {
            return biometricManager.canAuthenticate();
        }

        static Method getCanAuthenticateWithCryptoMethod() {
            try {
                return android.hardware.biometrics.BiometricManager.class.getMethod("canAuthenticate", android.hardware.biometrics.BiometricPrompt.CryptoObject.class);
            } catch (NoSuchMethodException unused) {
                return null;
            }
        }
    }
}
