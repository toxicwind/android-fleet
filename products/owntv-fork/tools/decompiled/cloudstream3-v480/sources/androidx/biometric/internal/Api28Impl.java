package androidx.biometric.internal;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.biometrics.BiometricPrompt$AuthenticationCallback;
import android.os.CancellationSignal;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthenticationHandlerBiometricPrompt.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0018\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\fH\u0007J\u0018\u0010\u000f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\fH\u0007J(\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u0005H\u0007J(\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001eH\u0007J0\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001eH\u0007¨\u0006!"}, d2 = {"Landroidx/biometric/internal/Api28Impl;", "", "<init>", "()V", "createPromptBuilder", "Landroid/hardware/biometrics/BiometricPrompt$Builder;", "context", "Landroid/content/Context;", "setTitle", "", "builder", "title", "", "setSubtitle", MediaTrack.ROLE_SUBTITLE, "setDescription", "description", "setNegativeButton", "text", "executor", "Ljava/util/concurrent/Executor;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/content/DialogInterface$OnClickListener;", "buildPrompt", "Landroid/hardware/biometrics/BiometricPrompt;", "authenticate", "biometricPrompt", "cancellationSignal", "Landroid/os/CancellationSignal;", "callback", "Landroid/hardware/biometrics/BiometricPrompt$AuthenticationCallback;", "crypto", "Landroid/hardware/biometrics/BiometricPrompt$CryptoObject;", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class Api28Impl {
    public static final Api28Impl INSTANCE = new Api28Impl();

    private Api28Impl() {
    }

    public final BiometricPrompt.Builder createPromptBuilder(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new BiometricPrompt.Builder(context);
    }

    public final void setTitle(BiometricPrompt.Builder builder, CharSequence title) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNullParameter(title, "title");
        builder.setTitle(title);
    }

    public final void setSubtitle(BiometricPrompt.Builder builder, CharSequence subtitle) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        builder.setSubtitle(subtitle);
    }

    public final void setDescription(BiometricPrompt.Builder builder, CharSequence description) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNullParameter(description, "description");
        builder.setDescription(description);
    }

    public final void setNegativeButton(BiometricPrompt.Builder builder, CharSequence text, Executor executor, DialogInterface.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(listener, "listener");
        builder.setNegativeButton(text, executor, listener);
    }

    public final BiometricPrompt buildPrompt(BiometricPrompt.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        BiometricPrompt biometricPromptBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(biometricPromptBuild, "build(...)");
        return biometricPromptBuild;
    }

    public final void authenticate(BiometricPrompt biometricPrompt, CancellationSignal cancellationSignal, Executor executor, BiometricPrompt$AuthenticationCallback callback) {
        Intrinsics.checkNotNullParameter(biometricPrompt, "biometricPrompt");
        Intrinsics.checkNotNullParameter(cancellationSignal, "cancellationSignal");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(callback, "callback");
        biometricPrompt.authenticate(cancellationSignal, executor, callback);
    }

    public final void authenticate(BiometricPrompt biometricPrompt, BiometricPrompt.CryptoObject crypto, CancellationSignal cancellationSignal, Executor executor, BiometricPrompt$AuthenticationCallback callback) {
        Intrinsics.checkNotNullParameter(biometricPrompt, "biometricPrompt");
        Intrinsics.checkNotNullParameter(crypto, "crypto");
        Intrinsics.checkNotNullParameter(cancellationSignal, "cancellationSignal");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(callback, "callback");
        biometricPrompt.authenticate(crypto, cancellationSignal, executor, callback);
    }
}
