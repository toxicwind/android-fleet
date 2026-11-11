package androidx.biometric.internal.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.biometric.internal.viewmodel.AuthenticationViewModel;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConfirmCredentialCallerFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00010\b0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/biometric/internal/ui/ConfirmCredentialCallerFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "viewModel", "Landroidx/biometric/internal/viewmodel/AuthenticationViewModel;", "launcher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "isHostedInActivity", "", "getCredentialIntent", "Companion", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ConfirmCredentialCallerFragment extends Fragment {
    private static final String ARG_CONFIRM_CREDENTIAL_INTENT = "confirm_credential_intent";
    private static final String ARG_HOST_ACTIVITY = "host_activity";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ActivityResultLauncher<Intent> launcher;
    private AuthenticationViewModel viewModel;

    public ConfirmCredentialCallerFragment() {
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: androidx.biometric.internal.ui.ConfirmCredentialCallerFragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ConfirmCredentialCallerFragment.launcher$lambda$0(this.f$0, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResult(...)");
        this.launcher = activityResultLauncherRegisterForActivityResult;
    }

    /* JADX INFO: compiled from: ConfirmCredentialCallerFragment.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/biometric/internal/ui/ConfirmCredentialCallerFragment$Companion;", "", "<init>", "()V", "ARG_HOST_ACTIVITY", "", "ARG_CONFIRM_CREDENTIAL_INTENT", "newInstance", "Landroidx/biometric/internal/ui/ConfirmCredentialCallerFragment;", "hostedInActivity", "", "credentialIntent", "Landroid/content/Intent;", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ConfirmCredentialCallerFragment newInstance(boolean hostedInActivity, Intent credentialIntent) {
            Intrinsics.checkNotNullParameter(credentialIntent, "credentialIntent");
            ConfirmCredentialCallerFragment confirmCredentialCallerFragment = new ConfirmCredentialCallerFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean(ConfirmCredentialCallerFragment.ARG_HOST_ACTIVITY, hostedInActivity);
            bundle.putParcelable(ConfirmCredentialCallerFragment.ARG_CONFIRM_CREDENTIAL_INTENT, credentialIntent);
            confirmCredentialCallerFragment.setArguments(bundle);
            return confirmCredentialCallerFragment;
        }
    }

    static final void launcher$lambda$0(ConfirmCredentialCallerFragment confirmCredentialCallerFragment, ActivityResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        Context contextRequireContext = confirmCredentialCallerFragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        AuthenticationViewModel authenticationViewModel = confirmCredentialCallerFragment.viewModel;
        if (authenticationViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            authenticationViewModel = null;
        }
        ConfirmCredentialUiLauncherKt.handleConfirmCredentialResult(contextRequireContext, authenticationViewModel, result.getResultCode());
        confirmCredentialCallerFragment.getParentFragmentManager().beginTransaction().remove(confirmCredentialCallerFragment).commitAllowingStateLoss();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = ConfirmCredentialUiLauncherKt.getCallerViewModelForFragment(this, isHostedInActivity());
        if (savedInstanceState != null) {
            return;
        }
        getCredentialIntent().setFlags(134742016);
        this.launcher.launch(getCredentialIntent());
    }

    private final boolean isHostedInActivity() {
        return requireArguments().getBoolean(ARG_HOST_ACTIVITY, true);
    }

    private final Intent getCredentialIntent() {
        Intent intent = (Intent) requireArguments().getParcelable(ARG_CONFIRM_CREDENTIAL_INTENT);
        if (intent != null) {
            return intent;
        }
        throw new IllegalStateException("Confirm credential intent cannot be null");
    }
}
