package androidx.biometric;

import kotlin.Metadata;

/* JADX INFO: compiled from: AuthenticationResultCallback.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Landroidx/biometric/AuthenticationResultCallback;", "", "onAuthResult", "", "result", "Landroidx/biometric/AuthenticationResult;", "onAuthAttemptFailed", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface AuthenticationResultCallback {

    /* JADX INFO: renamed from: androidx.biometric.AuthenticationResultCallback$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: AuthenticationResultCallback.kt */
    public final /* synthetic */ class CC {
        public static void $default$onAuthAttemptFailed(AuthenticationResultCallback _this) {
        }
    }

    void onAuthAttemptFailed();

    void onAuthResult(AuthenticationResult result);
}
