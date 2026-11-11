package androidx.biometric;

import android.content.Context;
import androidx.activity.ComponentActivity;
import androidx.biometric.internal.AuthenticationResultRegistry;
import androidx.biometric.internal.ui.ConfirmCredentialUiLauncherKt;
import androidx.fragment.app.Fragment;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthenticationExtensions.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\b"}, d2 = {"registerForAuthenticationResult", "Landroidx/biometric/AuthenticationResultLauncher;", "Landroidx/activity/ComponentActivity;", "callbackExecutor", "Ljava/util/concurrent/Executor;", "resultCallback", "Landroidx/biometric/AuthenticationResultCallback;", "Landroidx/fragment/app/Fragment;", "biometric"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AuthenticationUtils {
    public static final AuthenticationResultLauncher registerForAuthenticationResult(ComponentActivity componentActivity, Executor callbackExecutor, AuthenticationResultCallback resultCallback) {
        Intrinsics.checkNotNullParameter(componentActivity, "<this>");
        Intrinsics.checkNotNullParameter(callbackExecutor, "callbackExecutor");
        Intrinsics.checkNotNullParameter(resultCallback, "resultCallback");
        return new AuthenticationResultRegistry().register(componentActivity, componentActivity, componentActivity, ConfirmCredentialUiLauncherKt.getConfirmCredentialActivityLauncher(componentActivity), resultCallback, callbackExecutor);
    }

    public static final AuthenticationResultLauncher registerForAuthenticationResult(ComponentActivity componentActivity, AuthenticationResultCallback resultCallback) {
        Intrinsics.checkNotNullParameter(componentActivity, "<this>");
        Intrinsics.checkNotNullParameter(resultCallback, "resultCallback");
        return new AuthenticationResultRegistry().register(componentActivity, componentActivity, componentActivity, ConfirmCredentialUiLauncherKt.getConfirmCredentialActivityLauncher(componentActivity), resultCallback, null);
    }

    public static final AuthenticationResultLauncher registerForAuthenticationResult(Fragment fragment, Executor callbackExecutor, AuthenticationResultCallback resultCallback) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(callbackExecutor, "callbackExecutor");
        Intrinsics.checkNotNullParameter(resultCallback, "resultCallback");
        AuthenticationResultRegistry authenticationResultRegistry = new AuthenticationResultRegistry();
        Context contextRequireContext = fragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        return authenticationResultRegistry.register(contextRequireContext, fragment, fragment, ConfirmCredentialUiLauncherKt.getConfirmCredentialActivityLauncher(fragment), resultCallback, callbackExecutor);
    }

    public static final AuthenticationResultLauncher registerForAuthenticationResult(Fragment fragment, AuthenticationResultCallback resultCallback) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(resultCallback, "resultCallback");
        AuthenticationResultRegistry authenticationResultRegistry = new AuthenticationResultRegistry();
        Context contextRequireContext = fragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        return authenticationResultRegistry.register(contextRequireContext, fragment, fragment, ConfirmCredentialUiLauncherKt.getConfirmCredentialActivityLauncher(fragment), resultCallback, null);
    }
}
