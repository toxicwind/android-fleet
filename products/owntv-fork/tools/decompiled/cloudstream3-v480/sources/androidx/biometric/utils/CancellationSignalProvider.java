package androidx.biometric.utils;

import android.os.CancellationSignal;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class CancellationSignalProvider {
    private static final String TAG = "CancelSignalProvider";
    private CancellationSignal mBiometricCancellationSignal;
    private androidx.core.os.CancellationSignal mFingerprintCancellationSignal;
    private final Injector mInjector;

    interface Injector {
        CancellationSignal getBiometricCancellationSignal();

        androidx.core.os.CancellationSignal getFingerprintCancellationSignal();
    }

    public CancellationSignalProvider() {
        this.mInjector = new Injector() { // from class: androidx.biometric.utils.CancellationSignalProvider.1
            @Override // androidx.biometric.utils.CancellationSignalProvider.Injector
            public CancellationSignal getBiometricCancellationSignal() {
                return new CancellationSignal();
            }

            @Override // androidx.biometric.utils.CancellationSignalProvider.Injector
            public androidx.core.os.CancellationSignal getFingerprintCancellationSignal() {
                return new androidx.core.os.CancellationSignal();
            }
        };
    }

    public CancellationSignalProvider(Injector injector) {
        this.mInjector = injector;
    }

    public CancellationSignal getBiometricCancellationSignal() {
        if (this.mBiometricCancellationSignal == null) {
            this.mBiometricCancellationSignal = this.mInjector.getBiometricCancellationSignal();
        }
        return this.mBiometricCancellationSignal;
    }

    public androidx.core.os.CancellationSignal getFingerprintCancellationSignal() {
        if (this.mFingerprintCancellationSignal == null) {
            this.mFingerprintCancellationSignal = this.mInjector.getFingerprintCancellationSignal();
        }
        return this.mFingerprintCancellationSignal;
    }

    public void cancel() {
        CancellationSignal cancellationSignal = this.mBiometricCancellationSignal;
        if (cancellationSignal != null) {
            try {
                cancellationSignal.cancel();
            } catch (NullPointerException e) {
                Log.e(TAG, "Got NPE while canceling biometric authentication.", e);
            }
            this.mBiometricCancellationSignal = null;
        }
        androidx.core.os.CancellationSignal cancellationSignal2 = this.mFingerprintCancellationSignal;
        if (cancellationSignal2 != null) {
            try {
                cancellationSignal2.cancel();
            } catch (NullPointerException e2) {
                Log.e(TAG, "Got NPE while canceling fingerprint authentication.", e2);
            }
            this.mFingerprintCancellationSignal = null;
        }
    }
}
