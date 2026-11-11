package androidx.biometric.internal;

import android.content.Context;
import androidx.biometric.AuthenticationRequest;
import androidx.biometric.AuthenticationResultCallback;
import androidx.biometric.AuthenticationResultLauncher;
import androidx.biometric.BiometricPrompt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: AuthenticationResultRegistry.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J:\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/biometric/internal/AuthenticationResultRegistry;", "", "<init>", "()V", "register", "Landroidx/biometric/AuthenticationResultLauncher;", "context", "Landroid/content/Context;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "viewModelStoreOwner", "Landroidx/lifecycle/ViewModelStoreOwner;", "confirmCredentialActivityLauncher", "Ljava/lang/Runnable;", "resultCallback", "Landroidx/biometric/AuthenticationResultCallback;", "callbackExecutor", "Ljava/util/concurrent/Executor;", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AuthenticationResultRegistry {
    public static /* synthetic */ AuthenticationResultLauncher register$default(AuthenticationResultRegistry authenticationResultRegistry, Context context, LifecycleOwner lifecycleOwner, ViewModelStoreOwner viewModelStoreOwner, Runnable runnable, AuthenticationResultCallback authenticationResultCallback, Executor executor, int i, Object obj) {
        if ((i & 32) != 0) {
            executor = null;
        }
        return authenticationResultRegistry.register(context, lifecycleOwner, viewModelStoreOwner, runnable, authenticationResultCallback, executor);
    }

    public final AuthenticationResultLauncher register(final Context context, final LifecycleOwner lifecycleOwner, final ViewModelStoreOwner viewModelStoreOwner, final Runnable confirmCredentialActivityLauncher, AuthenticationResultCallback resultCallback, final Executor callbackExecutor) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(viewModelStoreOwner, "viewModelStoreOwner");
        Intrinsics.checkNotNullParameter(confirmCredentialActivityLauncher, "confirmCredentialActivityLauncher");
        Intrinsics.checkNotNullParameter(resultCallback, "resultCallback");
        final BiometricPrompt.AuthenticationCallback authenticationCallbackCreateAuthenticationCallback = AuthenticationResultRegistryKt.createAuthenticationCallback(resultCallback);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final BiometricPrompt.LifecycleContainer lifecycleContainer = new BiometricPrompt.LifecycleContainer(lifecycleOwner.getLifecycleRegistry());
        final Function0 function0 = new Function0() { // from class: androidx.biometric.internal.AuthenticationResultRegistry$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AuthenticationResultRegistry.register$lambda$0(objectRef, context, lifecycleOwner, viewModelStoreOwner, confirmCredentialActivityLauncher, callbackExecutor, authenticationCallbackCreateAuthenticationCallback);
            }
        };
        lifecycleContainer.addObserver(new LifecycleEventObserver() { // from class: androidx.biometric.internal.AuthenticationResultRegistry$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                AuthenticationResultRegistry.register$lambda$1(function0, lifecycleContainer, lifecycleOwner2, event);
            }
        });
        return new AuthenticationResultLauncher() { // from class: androidx.biometric.internal.AuthenticationResultRegistry.register.1
            @Override // androidx.biometric.AuthenticationResultLauncher
            public void launch(AuthenticationRequest input) {
                Intrinsics.checkNotNullParameter(input, "input");
                BiometricPrompt biometricPrompt = objectRef.element;
                if (biometricPrompt != null) {
                    AuthenticationResultRegistryKt.onLaunch(biometricPrompt, context, input);
                }
            }

            @Override // androidx.biometric.AuthenticationResultLauncher
            public void cancel() {
                BiometricPrompt biometricPrompt = objectRef.element;
                if (biometricPrompt != null) {
                    biometricPrompt.cancelAuthentication();
                }
            }
        };
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, androidx.biometric.BiometricPrompt] */
    static final Unit register$lambda$0(Ref.ObjectRef objectRef, Context context, LifecycleOwner lifecycleOwner, ViewModelStoreOwner viewModelStoreOwner, Runnable runnable, Executor executor, BiometricPrompt.AuthenticationCallback authenticationCallback) {
        if (objectRef.element == 0) {
            objectRef.element = new BiometricPrompt(context, lifecycleOwner, viewModelStoreOwner, runnable, executor, authenticationCallback);
        }
        return Unit.INSTANCE;
    }

    static final void register$lambda$1(Function0 function0, BiometricPrompt.LifecycleContainer lifecycleContainer, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<unused var>");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event == Lifecycle.Event.ON_START) {
            function0.invoke();
            lifecycleContainer.clearObservers();
        }
    }
}
