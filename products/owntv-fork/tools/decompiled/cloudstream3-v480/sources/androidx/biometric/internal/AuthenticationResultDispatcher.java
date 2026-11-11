package androidx.biometric.internal;

import android.content.Context;
import android.util.Log;
import androidx.biometric.AuthenticationRequest;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.R;
import androidx.biometric.internal.viewmodel.AuthenticationViewModel;
import androidx.biometric.utils.ErrorUtils;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthenticationResultDispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\b \u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020\u000eH\u0016J\u000e\u0010#\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%J\b\u0010&\u001a\u00020\u000eH\u0016J\u0016\u0010'\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020!J\u0016\u0010)\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020!J\u0010\u0010*\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u0010+\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%H\u0002J\u0006\u0010,\u001a\u00020\u000eJ\u000e\u0010-\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020/J\u000e\u00100\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020/R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u00061"}, d2 = {"Landroidx/biometric/internal/AuthenticationResultDispatcher;", "", "context", "Landroid/content/Context;", "viewModel", "Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;", "clientExecutor", "Ljava/util/concurrent/Executor;", "clientAuthenticationCallback", "Landroidx/biometric/BiometricPrompt$AuthenticationCallback;", "confirmCredentialActivityLauncher", "Ljava/lang/Runnable;", "dismiss", "Lkotlin/Function0;", "", "<init>", "(Landroid/content/Context;Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;Ljava/util/concurrent/Executor;Landroidx/biometric/BiometricPrompt$AuthenticationCallback;Ljava/lang/Runnable;Lkotlin/jvm/functions/Function0;)V", "getContext", "()Landroid/content/Context;", "getViewModel", "()Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;", "getClientExecutor", "()Ljava/util/concurrent/Executor;", "getClientAuthenticationCallback", "()Landroidx/biometric/BiometricPrompt$AuthenticationCallback;", "getConfirmCredentialActivityLauncher", "()Ljava/lang/Runnable;", "getDismiss", "()Lkotlin/jvm/functions/Function0;", "onAuthenticationError", "errorCode", "", "errorMessage", "", "showKMAsFallback", "onAuthenticationSucceeded", "result", "Landroidx/biometric/BiometricPrompt$AuthenticationResult;", "onAuthenticationFailed", "sendErrorAndDismiss", "errorString", "sendErrorToClient", "sendSuccessAndDismiss", "sendSuccessToClient", "sendFailureToClient", "sendFallbackOptionAndDismiss", "fallback", "Landroidx/biometric/AuthenticationRequest$Biometric$Fallback$CustomOption;", "sendFallbackOption", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class AuthenticationResultDispatcher {
    private final BiometricPrompt.AuthenticationCallback clientAuthenticationCallback;
    private final Executor clientExecutor;
    private final Runnable confirmCredentialActivityLauncher;
    private final Context context;
    private final Function0<Unit> dismiss;
    private final AuthenticationViewModel viewModel;

    public AuthenticationResultDispatcher(Context context, AuthenticationViewModel viewModel, Executor clientExecutor, BiometricPrompt.AuthenticationCallback clientAuthenticationCallback, Runnable confirmCredentialActivityLauncher, Function0<Unit> dismiss) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(clientExecutor, "clientExecutor");
        Intrinsics.checkNotNullParameter(clientAuthenticationCallback, "clientAuthenticationCallback");
        Intrinsics.checkNotNullParameter(confirmCredentialActivityLauncher, "confirmCredentialActivityLauncher");
        Intrinsics.checkNotNullParameter(dismiss, "dismiss");
        this.context = context;
        this.viewModel = viewModel;
        this.clientExecutor = clientExecutor;
        this.clientAuthenticationCallback = clientAuthenticationCallback;
        this.confirmCredentialActivityLauncher = confirmCredentialActivityLauncher;
        this.dismiss = dismiss;
    }

    public final Context getContext() {
        return this.context;
    }

    public final AuthenticationViewModel getViewModel() {
        return this.viewModel;
    }

    public final Executor getClientExecutor() {
        return this.clientExecutor;
    }

    public final BiometricPrompt.AuthenticationCallback getClientAuthenticationCallback() {
        return this.clientAuthenticationCallback;
    }

    public final Runnable getConfirmCredentialActivityLauncher() {
        return this.confirmCredentialActivityLauncher;
    }

    public final Function0<Unit> getDismiss() {
        return this.dismiss;
    }

    public void onAuthenticationError(int errorCode, CharSequence errorMessage) {
        int knownErrorCodeForAuthenticate = ErrorUtils.toKnownErrorCodeForAuthenticate(errorCode);
        if (errorMessage == null) {
            String string = this.context.getString(R.string.default_error_msg);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            errorMessage = string;
        }
        sendErrorAndDismiss(knownErrorCodeForAuthenticate, errorMessage);
    }

    public void showKMAsFallback() {
        this.confirmCredentialActivityLauncher.run();
    }

    public final void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        sendSuccessAndDismiss(result);
    }

    public void onAuthenticationFailed() {
        sendFailureToClient();
    }

    public final void sendErrorAndDismiss(int errorCode, CharSequence errorString) {
        Intrinsics.checkNotNullParameter(errorString, "errorString");
        sendErrorToClient(errorCode, errorString);
        this.dismiss.invoke();
    }

    public final void sendErrorToClient(final int errorCode, final CharSequence errorString) {
        Intrinsics.checkNotNullParameter(errorString, "errorString");
        if (!this.viewModel.isAwaitingResult()) {
            Log.w("AuthResultDispatcher", "Error not sent to client. Client is not awaiting a result.");
        } else {
            this.viewModel.setAwaitingResult(false);
            this.clientExecutor.execute(new Runnable() { // from class: androidx.biometric.internal.AuthenticationResultDispatcher$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.clientAuthenticationCallback.onAuthenticationError(errorCode, errorString);
                }
            });
        }
    }

    private final void sendSuccessAndDismiss(BiometricPrompt.AuthenticationResult result) {
        sendSuccessToClient(result);
        this.dismiss.invoke();
    }

    private final void sendSuccessToClient(final BiometricPrompt.AuthenticationResult result) {
        if (!this.viewModel.isAwaitingResult()) {
            Log.w("AuthResultDispatcher", "Success not sent to client. Client is not awaiting a result.");
        } else {
            this.viewModel.setAwaitingResult(false);
            this.clientExecutor.execute(new Runnable() { // from class: androidx.biometric.internal.AuthenticationResultDispatcher$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.clientAuthenticationCallback.onAuthenticationSucceeded(result);
                }
            });
        }
    }

    public final void sendFailureToClient() {
        if (!this.viewModel.isAwaitingResult()) {
            Log.w("AuthResultDispatcher", "Failure not sent to client. Client is not awaiting a result.");
        } else {
            this.clientExecutor.execute(new Runnable() { // from class: androidx.biometric.internal.AuthenticationResultDispatcher$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.clientAuthenticationCallback.onAuthenticationFailed();
                }
            });
        }
    }

    public final void sendFallbackOptionAndDismiss(AuthenticationRequest.Biometric.Fallback.CustomOption fallback) {
        Intrinsics.checkNotNullParameter(fallback, "fallback");
        sendFallbackOption(fallback);
        this.dismiss.invoke();
    }

    public final void sendFallbackOption(final AuthenticationRequest.Biometric.Fallback.CustomOption fallback) {
        Intrinsics.checkNotNullParameter(fallback, "fallback");
        if (!this.viewModel.isAwaitingResult()) {
            Log.w("AuthResultDispatcher", "Error not sent to client. Client is not awaiting a result.");
        } else {
            this.viewModel.setAwaitingResult(false);
            this.clientExecutor.execute(new Runnable() { // from class: androidx.biometric.internal.AuthenticationResultDispatcher$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.clientAuthenticationCallback.onFallbackSelected(fallback);
                }
            });
        }
    }
}
