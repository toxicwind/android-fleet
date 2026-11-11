package androidx.biometric.internal;

import android.content.Context;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.internal.data.CanceledFrom;
import androidx.biometric.internal.viewmodel.AuthenticationViewModel;
import androidx.lifecycle.LifecycleOwner;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthenticationHandler.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bJ\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Landroidx/biometric/internal/AuthenticationHandler;", "", "authenticate", "", "info", "Landroidx/biometric/BiometricPrompt$PromptInfo;", "crypto", "Landroidx/biometric/BiometricPrompt$CryptoObject;", "cancelAuthentication", "canceledFrom", "Landroidx/biometric/internal/data/CanceledFrom;", "Companion", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface AuthenticationHandler {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: renamed from: androidx.biometric.internal.AuthenticationHandler$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: AuthenticationHandler.kt */
    public final /* synthetic */ class CC {
        static {
            Companion companion = AuthenticationHandler.INSTANCE;
        }

        @JvmStatic
        public static AuthenticationHandler create(Context context, LifecycleOwner lifecycleOwner, AuthenticationViewModel authenticationViewModel, Runnable runnable, Executor executor, BiometricPrompt.AuthenticationCallback authenticationCallback) {
            return AuthenticationHandler.INSTANCE.create(context, lifecycleOwner, authenticationViewModel, runnable, executor, authenticationCallback);
        }
    }

    void authenticate(BiometricPrompt.PromptInfo info, BiometricPrompt.CryptoObject crypto);

    void cancelAuthentication(CanceledFrom canceledFrom);

    /* JADX INFO: compiled from: AuthenticationHandler.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J<\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007¨\u0006\u0012"}, d2 = {"Landroidx/biometric/internal/AuthenticationHandler$Companion;", "", "<init>", "()V", "create", "Landroidx/biometric/internal/AuthenticationHandler;", "context", "Landroid/content/Context;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "viewModel", "Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;", "confirmCredentialActivityLauncher", "Ljava/lang/Runnable;", "clientExecutor", "Ljava/util/concurrent/Executor;", "authenticationCallback", "Landroidx/biometric/BiometricPrompt$AuthenticationCallback;", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        @JvmStatic
        public final AuthenticationHandler create(Context context, LifecycleOwner lifecycleOwner, AuthenticationViewModel viewModel, Runnable confirmCredentialActivityLauncher, Executor clientExecutor, BiometricPrompt.AuthenticationCallback authenticationCallback) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
            Intrinsics.checkNotNullParameter(viewModel, "viewModel");
            Intrinsics.checkNotNullParameter(confirmCredentialActivityLauncher, "confirmCredentialActivityLauncher");
            if (clientExecutor == null) {
                clientExecutor = new PromptExecutor();
            }
            Executor executor = clientExecutor;
            if (authenticationCallback == null) {
                authenticationCallback = new DefaultClientAuthenticationCallback();
            }
            return new DefaultAuthenticationHandler(context, lifecycleOwner, viewModel, confirmCredentialActivityLauncher, executor, authenticationCallback);
        }
    }
}
