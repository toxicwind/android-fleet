package androidx.biometric.internal;

import android.util.Log;
import androidx.biometric.BiometricPrompt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthenticationHandler.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u0005H\u0002¨\u0006\u000f"}, d2 = {"Landroidx/biometric/internal/DefaultClientAuthenticationCallback;", "Landroidx/biometric/BiometricPrompt$AuthenticationCallback;", "<init>", "()V", "onAuthenticationFailed", "", "onAuthenticationSucceeded", "result", "Landroidx/biometric/BiometricPrompt$AuthenticationResult;", "onAuthenticationError", "errorCode", "", "errString", "", "logClientCallbackNullError", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class DefaultClientAuthenticationCallback extends BiometricPrompt.AuthenticationCallback {
    @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
    public void onAuthenticationFailed() {
        logClientCallbackNullError();
    }

    @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        logClientCallbackNullError();
    }

    @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        Intrinsics.checkNotNullParameter(errString, "errString");
        logClientCallbackNullError();
    }

    private final void logClientCallbackNullError() {
        Log.e("AuthenticationHandler", "Callbacks are not re-registered when the caller's activity/fragment is recreated!");
    }
}
