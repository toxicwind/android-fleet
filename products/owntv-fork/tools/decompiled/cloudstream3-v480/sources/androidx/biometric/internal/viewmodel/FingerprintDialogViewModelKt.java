package androidx.biometric.internal.viewmodel;

import androidx.biometric.internal.FingerprintManagerCompat;
import kotlin.Metadata;

/* JADX INFO: compiled from: FingerprintDialogViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¨\u0006\u0004"}, d2 = {"checkForFingerprintPreAuthenticationErrors", "", "fingerprintManager", "Landroidx/biometric/internal/FingerprintManagerCompat;", "biometric"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class FingerprintDialogViewModelKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final int checkForFingerprintPreAuthenticationErrors(FingerprintManagerCompat fingerprintManagerCompat) {
        if (fingerprintManagerCompat.isHardwareDetected()) {
            return !fingerprintManagerCompat.hasEnrolledFingerprints() ? 11 : 0;
        }
        return 12;
    }
}
