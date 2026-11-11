package androidx.biometric.internal;

import android.hardware.biometrics.BiometricPrompt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthenticationHandlerBiometricPrompt.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\tH\u0007¨\u0006\f"}, d2 = {"Landroidx/biometric/internal/Api29Impl;", "", "<init>", "()V", "setConfirmationRequired", "", "builder", "Landroid/hardware/biometrics/BiometricPrompt$Builder;", "confirmationRequired", "", "setDeviceCredentialAllowed", "deviceCredentialAllowed", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class Api29Impl {
    public static final Api29Impl INSTANCE = new Api29Impl();

    private Api29Impl() {
    }

    public final void setConfirmationRequired(BiometricPrompt.Builder builder, boolean confirmationRequired) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        builder.setConfirmationRequired(confirmationRequired);
    }

    public final void setDeviceCredentialAllowed(BiometricPrompt.Builder builder, boolean deviceCredentialAllowed) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        builder.setDeviceCredentialAllowed(deviceCredentialAllowed);
    }
}
