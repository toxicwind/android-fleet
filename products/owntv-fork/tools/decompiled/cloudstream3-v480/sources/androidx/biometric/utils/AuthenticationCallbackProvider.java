package androidx.biometric.utils;

import android.hardware.biometrics.BiometricPrompt$AuthenticationCallback;
import android.os.Build;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.internal.FingerprintManagerCompat;

/* JADX INFO: loaded from: classes.dex */
public class AuthenticationCallbackProvider {
    private BiometricPrompt$AuthenticationCallback mBiometricCallback;
    private FingerprintManagerCompat.AuthenticationCallback mFingerprintCallback;
    final Listener mListener;

    public static class Listener {
        public void onError(int i, CharSequence charSequence) {
        }

        public void onFailure() {
        }

        public void onHelp(CharSequence charSequence) {
        }

        public void onSuccess(BiometricPrompt.AuthenticationResult authenticationResult) {
        }
    }

    public AuthenticationCallbackProvider(Listener listener) {
        this.mListener = listener;
    }

    public BiometricPrompt$AuthenticationCallback getBiometricCallback() {
        if (this.mBiometricCallback == null) {
            this.mBiometricCallback = Api28Impl.createCallback(this.mListener);
        }
        return this.mBiometricCallback;
    }

    public FingerprintManagerCompat.AuthenticationCallback getFingerprintCallback() {
        if (this.mFingerprintCallback == null) {
            this.mFingerprintCallback = new FingerprintManagerCompat.AuthenticationCallback() { // from class: androidx.biometric.utils.AuthenticationCallbackProvider.1
                @Override // androidx.biometric.internal.FingerprintManagerCompat.AuthenticationCallback
                public void onAuthenticationError(int i, CharSequence charSequence) {
                    AuthenticationCallbackProvider.this.mListener.onError(i, charSequence);
                }

                @Override // androidx.biometric.internal.FingerprintManagerCompat.AuthenticationCallback
                public void onAuthenticationHelp(int i, CharSequence charSequence) {
                    AuthenticationCallbackProvider.this.mListener.onHelp(charSequence);
                }

                @Override // androidx.biometric.internal.FingerprintManagerCompat.AuthenticationCallback
                public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult authenticationResult) {
                    AuthenticationCallbackProvider.this.mListener.onSuccess(new BiometricPrompt.AuthenticationResult(CryptoObjectUtils.unwrapFromFingerprintManager(authenticationResult.getCryptoObject()), 2));
                }

                @Override // androidx.biometric.internal.FingerprintManagerCompat.AuthenticationCallback
                public void onAuthenticationFailed() {
                    AuthenticationCallbackProvider.this.mListener.onFailure();
                }
            };
        }
        return this.mFingerprintCallback;
    }

    private static class Api30Impl {
        private Api30Impl() {
        }

        static int getAuthenticationType(android.hardware.biometrics.BiometricPrompt.AuthenticationResult authenticationResult) {
            return authenticationResult.getAuthenticationType();
        }
    }

    private static class Api28Impl {
        private Api28Impl() {
        }

        static BiometricPrompt$AuthenticationCallback createCallback(final Listener listener) {
            return new BiometricPrompt$AuthenticationCallback() { // from class: androidx.biometric.utils.AuthenticationCallbackProvider.Api28Impl.1
                public void onAuthenticationHelp(int i, CharSequence charSequence) {
                }

                public void onAuthenticationError(int i, CharSequence charSequence) {
                    listener.onError(i, charSequence);
                }

                public void onAuthenticationSucceeded(android.hardware.biometrics.BiometricPrompt.AuthenticationResult authenticationResult) {
                    BiometricPrompt.CryptoObject cryptoObjectUnwrapFromBiometricPrompt = authenticationResult != null ? CryptoObjectUtils.unwrapFromBiometricPrompt(Api28Impl.getCryptoObject(authenticationResult)) : null;
                    int authenticationType = -1;
                    if (Build.VERSION.SDK_INT >= 30) {
                        if (authenticationResult != null) {
                            authenticationType = Api30Impl.getAuthenticationType(authenticationResult);
                        }
                    } else if (Build.VERSION.SDK_INT != 29) {
                        authenticationType = 2;
                    }
                    listener.onSuccess(new BiometricPrompt.AuthenticationResult(cryptoObjectUnwrapFromBiometricPrompt, authenticationType));
                }

                public void onAuthenticationFailed() {
                    listener.onFailure();
                }
            };
        }

        static android.hardware.biometrics.BiometricPrompt.CryptoObject getCryptoObject(android.hardware.biometrics.BiometricPrompt.AuthenticationResult authenticationResult) {
            return authenticationResult.getCryptoObject();
        }
    }
}
