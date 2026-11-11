package androidx.biometric.utils;

import android.os.Build;
import android.security.identity.IdentityCredential;
import android.security.identity.PresentationSession;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Log;
import androidx.activity.BackEventCompat$$ExternalSyntheticApiModelOutline0;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.internal.AuthenticationResultRegistryKt$$ExternalSyntheticBackportWithForwarding1;
import androidx.biometric.internal.FingerprintManagerCompat;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/* JADX INFO: loaded from: classes.dex */
public class CryptoObjectUtils {
    private static final String FAKE_KEY_NAME = "androidxBiometric";
    private static final String KEYSTORE_INSTANCE = "AndroidKeyStore";
    private static final String TAG = "CryptoObjectUtils";

    private CryptoObjectUtils() {
    }

    static BiometricPrompt.CryptoObject unwrapFromBiometricPrompt(android.hardware.biometrics.BiometricPrompt.CryptoObject cryptoObject) {
        KeyAgreement keyAgreement;
        PresentationSession presentationSession;
        IdentityCredential identityCredential;
        if (cryptoObject == null) {
            return null;
        }
        Cipher cipher = Api28Impl.getCipher(cryptoObject);
        if (cipher != null) {
            return new BiometricPrompt.CryptoObject(cipher);
        }
        Signature signature = Api28Impl.getSignature(cryptoObject);
        if (signature != null) {
            return new BiometricPrompt.CryptoObject(signature);
        }
        Mac mac = Api28Impl.getMac(cryptoObject);
        if (mac != null) {
            return new BiometricPrompt.CryptoObject(mac);
        }
        if (Build.VERSION.SDK_INT >= 30 && (identityCredential = Api30Impl.getIdentityCredential(cryptoObject)) != null) {
            return new BiometricPrompt.CryptoObject(identityCredential);
        }
        if (Build.VERSION.SDK_INT >= 33 && (presentationSession = Api33Impl.getPresentationSession(cryptoObject)) != null) {
            return new BiometricPrompt.CryptoObject(presentationSession);
        }
        if (Build.VERSION.SDK_INT >= 36 && AuthenticationResultRegistryKt$$ExternalSyntheticBackportWithForwarding1.m() >= 3600001 && (keyAgreement = Api36MinorImpl.getKeyAgreement(cryptoObject)) != null) {
            return new BiometricPrompt.CryptoObject(keyAgreement);
        }
        if (Build.VERSION.SDK_INT >= 35) {
            long operationHandle = Api35Impl.getOperationHandle(cryptoObject);
            if (operationHandle != 0) {
                return new BiometricPrompt.CryptoObject(operationHandle);
            }
        }
        return null;
    }

    public static android.hardware.biometrics.BiometricPrompt.CryptoObject wrapForBiometricPrompt(BiometricPrompt.CryptoObject cryptoObject) {
        KeyAgreement keyAgreement;
        PresentationSession presentationSession;
        IdentityCredential identityCredential;
        if (cryptoObject == null) {
            return null;
        }
        Cipher cipher = cryptoObject.getCipher();
        if (cipher != null) {
            return Api28Impl.create(cipher);
        }
        Signature signature = cryptoObject.getSignature();
        if (signature != null) {
            return Api28Impl.create(signature);
        }
        Mac mac = cryptoObject.getMac();
        if (mac != null) {
            return Api28Impl.create(mac);
        }
        if (Build.VERSION.SDK_INT >= 30 && (identityCredential = cryptoObject.getIdentityCredential()) != null) {
            return Api30Impl.create(identityCredential);
        }
        if (Build.VERSION.SDK_INT >= 33 && (presentationSession = cryptoObject.getPresentationSession()) != null) {
            return Api33Impl.create(presentationSession);
        }
        if (Build.VERSION.SDK_INT >= 36 && AuthenticationResultRegistryKt$$ExternalSyntheticBackportWithForwarding1.m() >= 3600001 && (keyAgreement = cryptoObject.getKeyAgreement()) != null) {
            return Api36MinorImpl.create(keyAgreement);
        }
        if (Build.VERSION.SDK_INT >= 35) {
            long operationHandleCryptoObject = cryptoObject.getOperationHandleCryptoObject();
            if (operationHandleCryptoObject != 0) {
                return Api35Impl.create(operationHandleCryptoObject);
            }
        }
        return null;
    }

    public static long getOperationHandle(BiometricPrompt.CryptoObject cryptoObject) {
        android.hardware.biometrics.BiometricPrompt.CryptoObject cryptoObjectWrapForBiometricPrompt = wrapForBiometricPrompt(cryptoObject);
        if (cryptoObjectWrapForBiometricPrompt != null) {
            return Api35Impl.getOperationHandle(cryptoObjectWrapForBiometricPrompt);
        }
        return 0L;
    }

    static BiometricPrompt.CryptoObject unwrapFromFingerprintManager(FingerprintManagerCompat.CryptoObject cryptoObject) {
        if (cryptoObject == null) {
            return null;
        }
        Cipher cipher = cryptoObject.getCipher();
        if (cipher != null) {
            return new BiometricPrompt.CryptoObject(cipher);
        }
        Signature signature = cryptoObject.getSignature();
        if (signature != null) {
            return new BiometricPrompt.CryptoObject(signature);
        }
        Mac mac = cryptoObject.getMac();
        if (mac != null) {
            return new BiometricPrompt.CryptoObject(mac);
        }
        return null;
    }

    public static FingerprintManagerCompat.CryptoObject wrapForFingerprintManager(BiometricPrompt.CryptoObject cryptoObject) {
        if (cryptoObject == null) {
            return null;
        }
        Cipher cipher = cryptoObject.getCipher();
        if (cipher != null) {
            return new FingerprintManagerCompat.CryptoObject(cipher);
        }
        Signature signature = cryptoObject.getSignature();
        if (signature != null) {
            return new FingerprintManagerCompat.CryptoObject(signature);
        }
        Mac mac = cryptoObject.getMac();
        if (mac != null) {
            return new FingerprintManagerCompat.CryptoObject(mac);
        }
        if (Build.VERSION.SDK_INT >= 30 && cryptoObject.getIdentityCredential() != null) {
            Log.e(TAG, "Identity credential is not supported by FingerprintManager.");
            return null;
        }
        if (Build.VERSION.SDK_INT >= 33 && cryptoObject.getPresentationSession() != null) {
            Log.e(TAG, "Presentation session is not supported by FingerprintManager.");
            return null;
        }
        if (Build.VERSION.SDK_INT >= 36 && AuthenticationResultRegistryKt$$ExternalSyntheticBackportWithForwarding1.m() >= 3600001 && cryptoObject.getKeyAgreement() != null) {
            Log.e(TAG, "Key agreement is not supported by FingerprintManager.");
            return null;
        }
        if (Build.VERSION.SDK_INT >= 35) {
            Log.e(TAG, "Operation handle is not supported by FingerprintManager.");
        }
        return null;
    }

    public static BiometricPrompt.CryptoObject createFakeCryptoObject() {
        try {
            KeyStore keyStore = KeyStore.getInstance(KEYSTORE_INSTANCE);
            keyStore.load(null);
            KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(FAKE_KEY_NAME, 3);
            builder.setBlockModes("CBC");
            builder.setEncryptionPaddings("PKCS7Padding");
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", KEYSTORE_INSTANCE);
            keyGenerator.init(builder.build());
            keyGenerator.generateKey();
            SecretKey secretKey = (SecretKey) keyStore.getKey(FAKE_KEY_NAME, null);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(1, secretKey);
            return new BiometricPrompt.CryptoObject(cipher);
        } catch (IOException | InvalidAlgorithmParameterException | InvalidKeyException | KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException | UnrecoverableKeyException | CertificateException | NoSuchPaddingException e) {
            Log.w(TAG, "Failed to create fake crypto object.", e);
            return null;
        }
    }

    private static class Api36MinorImpl {
        private Api36MinorImpl() {
        }

        static android.hardware.biometrics.BiometricPrompt.CryptoObject create(KeyAgreement keyAgreement) {
            return BackEventCompat$$ExternalSyntheticApiModelOutline0.m(keyAgreement);
        }

        static KeyAgreement getKeyAgreement(android.hardware.biometrics.BiometricPrompt.CryptoObject cryptoObject) {
            return cryptoObject.getKeyAgreement();
        }
    }

    private static class Api35Impl {
        private Api35Impl() {
        }

        static android.hardware.biometrics.BiometricPrompt.CryptoObject create(long j) {
            return new android.hardware.biometrics.BiometricPrompt.CryptoObject(j);
        }

        static long getOperationHandle(android.hardware.biometrics.BiometricPrompt.CryptoObject cryptoObject) {
            return cryptoObject.getOperationHandle();
        }
    }

    private static class Api33Impl {
        private Api33Impl() {
        }

        static android.hardware.biometrics.BiometricPrompt.CryptoObject create(PresentationSession presentationSession) {
            return new android.hardware.biometrics.BiometricPrompt.CryptoObject(presentationSession);
        }

        static PresentationSession getPresentationSession(android.hardware.biometrics.BiometricPrompt.CryptoObject cryptoObject) {
            return cryptoObject.getPresentationSession();
        }
    }

    private static class Api30Impl {
        private Api30Impl() {
        }

        static android.hardware.biometrics.BiometricPrompt.CryptoObject create(IdentityCredential identityCredential) {
            return new android.hardware.biometrics.BiometricPrompt.CryptoObject(identityCredential);
        }

        static IdentityCredential getIdentityCredential(android.hardware.biometrics.BiometricPrompt.CryptoObject cryptoObject) {
            return cryptoObject.getIdentityCredential();
        }
    }

    private static class Api28Impl {
        private Api28Impl() {
        }

        static android.hardware.biometrics.BiometricPrompt.CryptoObject create(Cipher cipher) {
            return new android.hardware.biometrics.BiometricPrompt.CryptoObject(cipher);
        }

        static android.hardware.biometrics.BiometricPrompt.CryptoObject create(Signature signature) {
            return new android.hardware.biometrics.BiometricPrompt.CryptoObject(signature);
        }

        static android.hardware.biometrics.BiometricPrompt.CryptoObject create(Mac mac) {
            return new android.hardware.biometrics.BiometricPrompt.CryptoObject(mac);
        }

        static Cipher getCipher(android.hardware.biometrics.BiometricPrompt.CryptoObject cryptoObject) {
            return cryptoObject.getCipher();
        }

        static Signature getSignature(android.hardware.biometrics.BiometricPrompt.CryptoObject cryptoObject) {
            return cryptoObject.getSignature();
        }

        static Mac getMac(android.hardware.biometrics.BiometricPrompt.CryptoObject cryptoObject) {
            return cryptoObject.getMac();
        }
    }
}
