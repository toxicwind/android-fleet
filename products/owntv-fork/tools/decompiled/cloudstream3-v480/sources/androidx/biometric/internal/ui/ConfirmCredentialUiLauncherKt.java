package androidx.biometric.internal.ui;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.R;
import androidx.biometric.internal.viewmodel.AuthenticationViewModel;
import androidx.biometric.internal.viewmodel.AuthenticationViewModelFactory;
import androidx.biometric.utils.BiometricErrorData;
import androidx.biometric.utils.KeyguardUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConfirmCredentialUiLauncher.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0010\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004*\u00020\u0005H\u0000\u001a\u0010\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004*\u00020\u0006H\u0000\u001a\u0010\u0010\u0007\u001a\u00060\u0003j\u0002`\u0004*\u00020\u0005H\u0000\u001a\u0010\u0010\u0007\u001a\u00060\u0003j\u0002`\u0004*\u00020\bH\u0000\u001a\"\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0007\u001a \u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0007\u001a \u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u0014H\u0000\u001a9\u0010\u0017\u001a\u0004\u0018\u0001H\u0018\"\b\b\u0000\u0010\u0018*\u00020\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u00012\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001dH\u0002¢\u0006\u0002\u0010\u001e\u001a\u0014\u0010\u001f\u001a\u00020\u0016*\u00020\u00052\u0006\u0010 \u001a\u00020!H\u0000\u001a\u0010\u0010\"\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\rH\u0002\u001a\u0016\u0010#\u001a\u0004\u0018\u00010\u0010*\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"TAG", "", "getConfirmCredentialActivityLauncher", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "Landroidx/fragment/app/Fragment;", "Landroidx/activity/ComponentActivity;", "getConfirmCredentialFragmentLauncher", "Landroidx/fragment/app/FragmentActivity;", "launchConfirmCredentialActivity", "", "Landroid/content/Context;", "viewModelStoreOwner", "Landroidx/lifecycle/ViewModelStoreOwner;", "confirmCredentialActivityLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "handleConfirmCredentialResult", "context", "resultCode", "", "callerViewModel", "Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;", "findOrAddCallerFragment", "F", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "fragmentTag", "fragmentFactory", "Lkotlin/Function0;", "(Landroidx/fragment/app/FragmentManager;Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Landroidx/fragment/app/Fragment;", "getCallerViewModelForFragment", "hostedInActivity", "", "getViewModel", "getConfirmCredentialIntent", "biometric"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ConfirmCredentialUiLauncherKt {
    private static final String TAG = "ConfirmCredentialUiLauncher";

    public static final Runnable getConfirmCredentialActivityLauncher(final Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        final ActivityResultLauncher activityResultLauncherRegisterForActivityResult = fragment.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: androidx.biometric.internal.ui.ConfirmCredentialUiLauncherKt$$ExternalSyntheticLambda3
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ConfirmCredentialUiLauncherKt.getConfirmCredentialActivityLauncher$lambda$0(fragment, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResult(...)");
        return new Runnable() { // from class: androidx.biometric.internal.ui.ConfirmCredentialUiLauncherKt$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                ConfirmCredentialUiLauncherKt.getConfirmCredentialActivityLauncher$lambda$1(fragment, activityResultLauncherRegisterForActivityResult);
            }
        };
    }

    static final void getConfirmCredentialActivityLauncher$lambda$0(Fragment fragment, ActivityResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        Context contextRequireContext = fragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        handleConfirmCredentialResult(contextRequireContext, fragment, result.getResultCode());
    }

    static final void getConfirmCredentialActivityLauncher$lambda$1(Fragment fragment, ActivityResultLauncher activityResultLauncher) {
        if (fragment.getLifecycle().getState().isAtLeast(Lifecycle.State.STARTED)) {
            Context contextRequireContext = fragment.requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
            launchConfirmCredentialActivity(contextRequireContext, fragment, activityResultLauncher);
            return;
        }
        Log.w(TAG, "Attempted to launch ConfirmCredentialActivity when Fragment is not STARTED.");
    }

    public static final Runnable getConfirmCredentialActivityLauncher(final ComponentActivity componentActivity) {
        Intrinsics.checkNotNullParameter(componentActivity, "<this>");
        final ActivityResultLauncher activityResultLauncherRegisterForActivityResult = componentActivity.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: androidx.biometric.internal.ui.ConfirmCredentialUiLauncherKt$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ConfirmCredentialUiLauncherKt.getConfirmCredentialActivityLauncher$lambda$2(componentActivity, (ActivityResult) obj);
            }
        });
        return new Runnable() { // from class: androidx.biometric.internal.ui.ConfirmCredentialUiLauncherKt$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ConfirmCredentialUiLauncherKt.getConfirmCredentialActivityLauncher$lambda$3(componentActivity, activityResultLauncherRegisterForActivityResult);
            }
        };
    }

    static final void getConfirmCredentialActivityLauncher$lambda$2(ComponentActivity componentActivity, ActivityResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        handleConfirmCredentialResult(componentActivity, componentActivity, result.getResultCode());
    }

    static final void getConfirmCredentialActivityLauncher$lambda$3(ComponentActivity componentActivity, ActivityResultLauncher activityResultLauncher) {
        if (componentActivity.getLifecycle().getState().isAtLeast(Lifecycle.State.STARTED)) {
            launchConfirmCredentialActivity(componentActivity, componentActivity, activityResultLauncher);
        } else {
            Log.w(TAG, "Attempted to launch ConfirmCredentialActivity when Fragment is not STARTED.");
        }
    }

    public static final Runnable getConfirmCredentialFragmentLauncher(final Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        return new Runnable() { // from class: androidx.biometric.internal.ui.ConfirmCredentialUiLauncherKt$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                ConfirmCredentialUiLauncherKt.getConfirmCredentialFragmentLauncher$lambda$0(fragment);
            }
        };
    }

    static final void getConfirmCredentialFragmentLauncher$lambda$0(Fragment fragment) {
        Context contextRequireContext = fragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        final Intent confirmCredentialIntent = getConfirmCredentialIntent(contextRequireContext, fragment);
        if (confirmCredentialIntent != null) {
            FragmentManager childFragmentManager = fragment.getChildFragmentManager();
            String name = ConfirmCredentialCallerFragment.class.getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            findOrAddCallerFragment(childFragmentManager, name, new Function0() { // from class: androidx.biometric.internal.ui.ConfirmCredentialUiLauncherKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ConfirmCredentialUiLauncherKt.getConfirmCredentialFragmentLauncher$lambda$0$0(confirmCredentialIntent);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConfirmCredentialCallerFragment getConfirmCredentialFragmentLauncher$lambda$0$0(Intent intent) {
        return ConfirmCredentialCallerFragment.INSTANCE.newInstance(false, intent);
    }

    public static final Runnable getConfirmCredentialFragmentLauncher(final FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        return new Runnable() { // from class: androidx.biometric.internal.ui.ConfirmCredentialUiLauncherKt$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                ConfirmCredentialUiLauncherKt.getConfirmCredentialFragmentLauncher$lambda$1(fragmentActivity);
            }
        };
    }

    static final void getConfirmCredentialFragmentLauncher$lambda$1(FragmentActivity fragmentActivity) {
        final Intent confirmCredentialIntent = getConfirmCredentialIntent(fragmentActivity, fragmentActivity);
        if (confirmCredentialIntent != null) {
            FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
            String name = ConfirmCredentialCallerFragment.class.getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            findOrAddCallerFragment(supportFragmentManager, name, new Function0() { // from class: androidx.biometric.internal.ui.ConfirmCredentialUiLauncherKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ConfirmCredentialUiLauncherKt.getConfirmCredentialFragmentLauncher$lambda$1$0(confirmCredentialIntent);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConfirmCredentialCallerFragment getConfirmCredentialFragmentLauncher$lambda$1$0(Intent intent) {
        return ConfirmCredentialCallerFragment.INSTANCE.newInstance(true, intent);
    }

    public static final void launchConfirmCredentialActivity(Context context, ViewModelStoreOwner viewModelStoreOwner, ActivityResultLauncher<Intent> confirmCredentialActivityLauncher) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(viewModelStoreOwner, "viewModelStoreOwner");
        Intrinsics.checkNotNullParameter(confirmCredentialActivityLauncher, "confirmCredentialActivityLauncher");
        Intent confirmCredentialIntent = getConfirmCredentialIntent(context, viewModelStoreOwner);
        if (confirmCredentialIntent != null) {
            confirmCredentialIntent.setFlags(134742016);
            confirmCredentialActivityLauncher.launch(confirmCredentialIntent);
        }
    }

    public static final void handleConfirmCredentialResult(Context context, ViewModelStoreOwner viewModelStoreOwner, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(viewModelStoreOwner, "viewModelStoreOwner");
        handleConfirmCredentialResult(context, getViewModel(viewModelStoreOwner), i);
    }

    public static final void handleConfirmCredentialResult(Context context, AuthenticationViewModel callerViewModel, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callerViewModel, "callerViewModel");
        int i2 = -1;
        if (i == -1) {
            if (callerViewModel.isUsingKeyguardManagerForBiometricAndCredential()) {
                callerViewModel.setUsingKeyguardManagerForBiometricAndCredential(false);
            } else {
                i2 = 1;
            }
            callerViewModel.setAuthenticationResult(new BiometricPrompt.AuthenticationResult(null, i2));
        } else {
            callerViewModel.setAuthenticationError(new BiometricErrorData(10, context.getString(R.string.generic_error_user_canceled)));
        }
        callerViewModel.setPromptShowing(false);
    }

    private static final <F extends Fragment> F findOrAddCallerFragment(FragmentManager fragmentManager, String str, Function0<? extends F> function0) {
        if (fragmentManager == null) {
            Log.e(TAG, str + ": Unable to start authentication . Client fragment manager was null");
            return null;
        }
        if (fragmentManager.isStateSaved()) {
            Log.e(TAG, str + ": Unable to start authentication. Called after onSaveInstanceState().");
            return null;
        }
        F f = (F) fragmentManager.findFragmentByTag(str);
        if (f != null) {
            return f;
        }
        F fInvoke = function0.invoke();
        fragmentManager.beginTransaction().add(fInvoke, str).commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
        return fInvoke;
    }

    public static final AuthenticationViewModel getCallerViewModelForFragment(Fragment fragment, boolean z) {
        Fragment fragment2;
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        if (z) {
            FragmentActivity fragmentActivityRequireActivity = fragment.requireActivity();
            Intrinsics.checkNotNull(fragmentActivityRequireActivity);
            fragment2 = fragmentActivityRequireActivity;
        } else {
            Fragment fragmentRequireParentFragment = fragment.requireParentFragment();
            Intrinsics.checkNotNull(fragmentRequireParentFragment);
            fragment2 = fragmentRequireParentFragment;
        }
        return getViewModel(fragment2);
    }

    private static final AuthenticationViewModel getViewModel(ViewModelStoreOwner viewModelStoreOwner) {
        return (AuthenticationViewModel) new ViewModelProvider(viewModelStoreOwner, new AuthenticationViewModelFactory()).get(AuthenticationViewModel.class);
    }

    public static final Intent getConfirmCredentialIntent(Context context, ViewModelStoreOwner viewModelStoreOwner) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(viewModelStoreOwner, "viewModelStoreOwner");
        AuthenticationViewModel viewModel = getViewModel(viewModelStoreOwner);
        KeyguardManager keyguardManager = KeyguardUtils.getKeyguardManager(context);
        if (keyguardManager == null) {
            viewModel.setAuthenticationError(new BiometricErrorData(12, context.getString(R.string.generic_error_no_keyguard)));
            return null;
        }
        CharSequence title = viewModel.getTitle();
        CharSequence subtitle = viewModel.getSubtitle();
        CharSequence description = viewModel.getDescription();
        if (subtitle == null) {
            subtitle = description;
        }
        Intent intentCreateConfirmDeviceCredentialIntent = keyguardManager.createConfirmDeviceCredentialIntent(title, subtitle);
        if (intentCreateConfirmDeviceCredentialIntent == null) {
            viewModel.setAuthenticationError(new BiometricErrorData(14, context.getString(R.string.generic_error_no_device_credential)));
            return null;
        }
        viewModel.setConfirmingDeviceCredential(true);
        return intentCreateConfirmDeviceCredentialIntent;
    }
}
