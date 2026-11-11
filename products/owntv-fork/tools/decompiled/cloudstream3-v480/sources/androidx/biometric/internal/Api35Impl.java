package androidx.biometric.internal;

import android.graphics.Bitmap;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.biometrics.PromptContentView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthenticationHandlerBiometricPrompt.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u001a\u0010\r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007J\u0018\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012H\u0007¨\u0006\u0013"}, d2 = {"Landroidx/biometric/internal/Api35Impl;", "", "<init>", "()V", "setLogoRes", "", "builder", "Landroid/hardware/biometrics/BiometricPrompt$Builder;", "logoRes", "", "setLogoBitmap", "logoBitmap", "Landroid/graphics/Bitmap;", "setLogoDescription", "logoDescription", "", "setContentView", "contentView", "Landroid/hardware/biometrics/PromptContentView;", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class Api35Impl {
    public static final Api35Impl INSTANCE = new Api35Impl();

    private Api35Impl() {
    }

    public final void setLogoRes(BiometricPrompt.Builder builder, int logoRes) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        builder.setLogoRes(logoRes);
    }

    public final void setLogoBitmap(BiometricPrompt.Builder builder, Bitmap logoBitmap) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNullParameter(logoBitmap, "logoBitmap");
        builder.setLogoBitmap(logoBitmap);
    }

    public final void setLogoDescription(BiometricPrompt.Builder builder, String logoDescription) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNull(logoDescription);
        builder.setLogoDescription(logoDescription);
    }

    public final void setContentView(BiometricPrompt.Builder builder, PromptContentView contentView) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNullParameter(contentView, "contentView");
        builder.setContentView(contentView);
    }
}
