package androidx.biometric.internal;

import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthenticationHandlerBiometricPrompt.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007¨\u0006\u0010"}, d2 = {"Landroidx/biometric/internal/Api36MinorImpl;", "", "<init>", "()V", "addFallbackOption", "", "builder", "Landroid/hardware/biometrics/BiometricPrompt$Builder;", "text", "", "iconType", "", "executor", "Ljava/util/concurrent/Executor;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/content/DialogInterface$OnClickListener;", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class Api36MinorImpl {
    public static final Api36MinorImpl INSTANCE = new Api36MinorImpl();

    private Api36MinorImpl() {
    }

    public final void addFallbackOption(BiometricPrompt.Builder builder, CharSequence text, int iconType, Executor executor, DialogInterface.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(listener, "listener");
        builder.addFallbackOption(text, iconType, executor, listener);
    }
}
