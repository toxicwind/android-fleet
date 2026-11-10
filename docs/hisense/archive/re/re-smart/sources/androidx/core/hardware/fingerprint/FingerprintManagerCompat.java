package androidx.core.hardware.fingerprint;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.core.os.CancellationSignal;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class FingerprintManagerCompat {
    public final Context mContext;

    /* JADX INFO: renamed from: androidx.core.hardware.fingerprint.FingerprintManagerCompat$1, reason: invalid class name */
    public class AnonymousClass1 extends FingerprintManager.AuthenticationCallback {
        public final /* synthetic */ AuthenticationCallback val$callback;

        public AnonymousClass1(final AuthenticationCallback val$callback) {
            this.val$callback = val$callback;
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            this.val$callback.onAuthenticationError(errMsgId, errString);
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationFailed() {
            this.val$callback.onAuthenticationFailed();
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            this.val$callback.onAuthenticationHelp(helpMsgId, helpString);
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            this.val$callback.onAuthenticationSucceeded(new AuthenticationResult(FingerprintManagerCompat.unwrapCryptoObject(result.getCryptoObject())));
        }
    }

    public static abstract class AuthenticationCallback {
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
        }

        public void onAuthenticationFailed() {
        }

        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        }

        public void onAuthenticationSucceeded(AuthenticationResult result) {
        }
    }

    public static final class AuthenticationResult {
        public final CryptoObject mCryptoObject;

        public AuthenticationResult(CryptoObject crypto) {
            this.mCryptoObject = crypto;
        }

        public CryptoObject getCryptoObject() {
            return this.mCryptoObject;
        }
    }

    public FingerprintManagerCompat(Context context) {
        this.mContext = context;
    }

    @NonNull
    public static FingerprintManagerCompat from(@NonNull Context context) {
        return new FingerprintManagerCompat(context);
    }

    @Nullable
    @RequiresApi(23)
    public static FingerprintManager getFingerprintManagerOrNull(@NonNull Context context) {
        int i = Build.VERSION.SDK_INT;
        if (context.getPackageManager().hasSystemFeature("android.hardware.fingerprint")) {
            return (FingerprintManager) context.getSystemService(FingerprintManager.class);
        }
        return null;
    }

    @RequiresApi(23)
    public static CryptoObject unwrapCryptoObject(FingerprintManager.CryptoObject cryptoObject) {
        if (cryptoObject == null) {
            return null;
        }
        if (cryptoObject.getCipher() != null) {
            return new CryptoObject(cryptoObject.getCipher());
        }
        if (cryptoObject.getSignature() != null) {
            return new CryptoObject(cryptoObject.getSignature());
        }
        if (cryptoObject.getMac() != null) {
            return new CryptoObject(cryptoObject.getMac());
        }
        return null;
    }

    @RequiresApi(23)
    public static FingerprintManager.AuthenticationCallback wrapCallback(final AuthenticationCallback callback) {
        return new AnonymousClass1(callback);
    }

    @RequiresApi(23)
    public static FingerprintManager.CryptoObject wrapCryptoObject(CryptoObject cryptoObject) {
        if (cryptoObject == null) {
            return null;
        }
        if (cryptoObject.getCipher() != null) {
            return new FingerprintManager.CryptoObject(cryptoObject.getCipher());
        }
        if (cryptoObject.getSignature() != null) {
            return new FingerprintManager.CryptoObject(cryptoObject.getSignature());
        }
        if (cryptoObject.getMac() != null) {
            return new FingerprintManager.CryptoObject(cryptoObject.getMac());
        }
        return null;
    }

    @RequiresPermission("android.permission.USE_FINGERPRINT")
    public void authenticate(@Nullable CryptoObject crypto, int flags, @Nullable CancellationSignal cancel, @NonNull AuthenticationCallback callback, @Nullable Handler handler) {
        int i = Build.VERSION.SDK_INT;
        FingerprintManager fingerprintManagerOrNull = getFingerprintManagerOrNull(this.mContext);
        if (fingerprintManagerOrNull != null) {
            fingerprintManagerOrNull.authenticate(wrapCryptoObject(crypto), cancel != null ? (android.os.CancellationSignal) cancel.getCancellationSignalObject() : null, flags, new AnonymousClass1(callback), handler);
        }
    }

    @RequiresPermission("android.permission.USE_FINGERPRINT")
    public boolean hasEnrolledFingerprints() {
        int i = Build.VERSION.SDK_INT;
        FingerprintManager fingerprintManagerOrNull = getFingerprintManagerOrNull(this.mContext);
        return fingerprintManagerOrNull != null && fingerprintManagerOrNull.hasEnrolledFingerprints();
    }

    @RequiresPermission("android.permission.USE_FINGERPRINT")
    public boolean isHardwareDetected() {
        int i = Build.VERSION.SDK_INT;
        FingerprintManager fingerprintManagerOrNull = getFingerprintManagerOrNull(this.mContext);
        return fingerprintManagerOrNull != null && fingerprintManagerOrNull.isHardwareDetected();
    }

    public static class CryptoObject {
        public final Cipher mCipher;
        public final Mac mMac;
        public final Signature mSignature;

        public CryptoObject(@NonNull Signature signature) {
            this.mSignature = signature;
            this.mCipher = null;
            this.mMac = null;
        }

        @Nullable
        public Cipher getCipher() {
            return this.mCipher;
        }

        @Nullable
        public Mac getMac() {
            return this.mMac;
        }

        @Nullable
        public Signature getSignature() {
            return this.mSignature;
        }

        public CryptoObject(@NonNull Cipher cipher) {
            this.mCipher = cipher;
            this.mSignature = null;
            this.mMac = null;
        }

        public CryptoObject(@NonNull Mac mac) {
            this.mMac = mac;
            this.mCipher = null;
            this.mSignature = null;
        }
    }
}
