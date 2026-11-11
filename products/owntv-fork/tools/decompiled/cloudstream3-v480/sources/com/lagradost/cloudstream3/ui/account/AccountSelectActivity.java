package com.lagradost.cloudstream3.ui.account;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import androidx.biometric.BiometricPrompt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.ActivityAccountSelectBinding;
import com.lagradost.cloudstream3.mvvm.LifecycleKt;
import com.lagradost.cloudstream3.ui.AutofitRecyclerView;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.BiometricAuthenticator;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: AccountSelectActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00122\u00020\u00012\u00020\u0002:\u0001\u0012B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0015J\b\u0010\u000f\u001a\u00020\fH\u0003J\b\u0010\u0010\u001a\u00020\fH\u0016J\b\u0010\u0011\u001a\u00020\fH\u0016R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/ui/account/AccountSelectActivity;", "Landroidx/fragment/app/FragmentActivity;", "Lcom/lagradost/cloudstream3/utils/BiometricAuthenticator$BiometricCallback;", "<init>", "()V", "accountViewModel", "Lcom/lagradost/cloudstream3/ui/account/AccountViewModel;", "getAccountViewModel", "()Lcom/lagradost/cloudstream3/ui/account/AccountViewModel;", "accountViewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "navigateToMainActivity", "onAuthenticationSuccess", "onAuthenticationError", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AccountSelectActivity extends FragmentActivity implements BiometricAuthenticator.BiometricCallback {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static boolean hasLoggedIn;

    /* JADX INFO: renamed from: accountViewModel$delegate, reason: from kotlin metadata */
    private final Lazy accountViewModel;

    public AccountSelectActivity() {
        final AccountSelectActivity accountSelectActivity = this;
        final Function0 function0 = null;
        this.accountViewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(AccountViewModel.class), new Function0<ViewModelStore>() { // from class: com.lagradost.cloudstream3.ui.account.AccountSelectActivity$special$$inlined$viewModels$default$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return accountSelectActivity.getViewModelStore();
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.lagradost.cloudstream3.ui.account.AccountSelectActivity$special$$inlined$viewModels$default$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return accountSelectActivity.getDefaultViewModelProviderFactory();
            }
        }, new Function0<CreationExtras>() { // from class: com.lagradost.cloudstream3.ui.account.AccountSelectActivity$special$$inlined$viewModels$default$3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function1 = function0;
                return (function1 == null || (creationExtras = (CreationExtras) function1.invoke()) == null) ? accountSelectActivity.getDefaultViewModelCreationExtras() : creationExtras;
            }
        });
    }

    /* JADX INFO: compiled from: AccountSelectActivity.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/lagradost/cloudstream3/ui/account/AccountSelectActivity$Companion;", "", "<init>", "()V", "hasLoggedIn", "", "getHasLoggedIn", "()Z", "setHasLoggedIn", "(Z)V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean getHasLoggedIn() {
            return AccountSelectActivity.hasLoggedIn;
        }

        public final void setHasLoggedIn(boolean z) {
            AccountSelectActivity.hasLoggedIn = z;
        }
    }

    public final AccountViewModel getAccountViewModel() {
        return (AccountViewModel) this.accountViewModel.getValue();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        DataStoreHelper.Account account;
        super.onCreate(savedInstanceState);
        final boolean booleanExtra = getIntent().getBooleanExtra("isEditingFromMainActivity", false);
        if (hasLoggedIn && !booleanExtra) {
            navigateToMainActivity();
            return;
        }
        AccountSelectActivity accountSelectActivity = this;
        CommonActivity.INSTANCE.loadThemes(accountSelectActivity);
        UIHelper.INSTANCE.enableEdgeToEdgeCompat(accountSelectActivity);
        UIHelper.INSTANCE.setNavigationBarColorCompat(accountSelectActivity, R.attr.primaryBlackBackground);
        AccountSelectActivity accountSelectActivity2 = this;
        boolean z = PreferenceManager.getDefaultSharedPreferences(accountSelectActivity2).getBoolean(getString(R.string.skip_startup_account_select_key), false) || DataStoreHelper.INSTANCE.getAccounts().length <= 1;
        AccountSelectActivity accountSelectActivity3 = this;
        LifecycleKt.observe(accountSelectActivity3, getAccountViewModel().isAllowedLogin(), new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountSelectActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountSelectActivity.onCreate$lambda$1(this.f$0, ((Boolean) obj).booleanValue());
            }
        });
        if (!booleanExtra && z) {
            DataStoreHelper.Account[] accounts = DataStoreHelper.INSTANCE.getAccounts();
            int length = accounts.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    account = null;
                    break;
                }
                account = accounts[i];
                if (account.getKeyIndex() == DataStoreHelper.INSTANCE.getSelectedKeyIndex()) {
                    break;
                } else {
                    i++;
                }
            }
            if ((account != null ? account.getLockPin() : null) != null) {
                CommonActivity.INSTANCE.init(accountSelectActivity);
                AccountViewModel.handleAccountSelect$default(getAccountViewModel(), account, accountSelectActivity2, true, false, 8, null);
                return;
            } else {
                if (DataStoreHelper.INSTANCE.getAccounts().length > 1) {
                    CommonActivity.showToast$default(CommonActivity.INSTANCE, accountSelectActivity, getString(R.string.logged_account, new Object[]{account != null ? account.getName() : null}), (Integer) null, 4, (Object) null);
                }
                navigateToMainActivity();
                return;
            }
        }
        CommonActivity.INSTANCE.init(accountSelectActivity);
        final ActivityAccountSelectBinding activityAccountSelectBindingInflate = ActivityAccountSelectBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityAccountSelectBindingInflate, "inflate(...)");
        setContentView(activityAccountSelectBindingInflate.getRoot());
        UIHelper uIHelper = UIHelper.INSTANCE;
        FrameLayout root = activityAccountSelectBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        UIHelper.fixSystemBarsPadding$default(uIHelper, root, null, null, false, false, false, false, false, false, TypedValues.PositionType.TYPE_DRAWPATH, null);
        final AutofitRecyclerView accountRecyclerView = activityAccountSelectBindingInflate.accountRecyclerView;
        Intrinsics.checkNotNullExpressionValue(accountRecyclerView, "accountRecyclerView");
        LifecycleKt.observe(accountSelectActivity3, getAccountViewModel().getAccounts(), new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountSelectActivity$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountSelectActivity.onCreate$lambda$3(accountRecyclerView, activityAccountSelectBindingInflate, this, booleanExtra, (List) obj);
            }
        });
        onCreate$askBiometricAuth(this);
    }

    private static final void onCreate$askBiometricAuth(AccountSelectActivity accountSelectActivity) {
        BiometricPrompt biometricPrompt;
        if (Globals.INSTANCE.isLayout(1)) {
            AccountSelectActivity accountSelectActivity2 = accountSelectActivity;
            if (BiometricAuthenticator.INSTANCE.isAuthEnabled(accountSelectActivity2) && BiometricAuthenticator.INSTANCE.deviceHasPasswordPinLock(accountSelectActivity2)) {
                BiometricAuthenticator.INSTANCE.startBiometricAuthentication(accountSelectActivity, R.string.biometric_authentication_title, false);
                BiometricPrompt.PromptInfo promptInfo = BiometricAuthenticator.INSTANCE.getPromptInfo();
                if (promptInfo == null || (biometricPrompt = BiometricAuthenticator.INSTANCE.getBiometricPrompt()) == null) {
                    return;
                }
                biometricPrompt.authenticate(promptInfo);
            }
        }
    }

    static final Unit onCreate$lambda$1(AccountSelectActivity accountSelectActivity, boolean z) {
        if (z) {
            accountSelectActivity.navigateToMainActivity();
        }
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$3(final AutofitRecyclerView autofitRecyclerView, final ActivityAccountSelectBinding activityAccountSelectBinding, final AccountSelectActivity accountSelectActivity, final boolean z, List liveAccounts) {
        Intrinsics.checkNotNullParameter(liveAccounts, "liveAccounts");
        final AccountAdapter accountAdapter = new AccountAdapter(new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountSelectActivity$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountSelectActivity.onCreate$lambda$3$0(this.f$0, (DataStoreHelper.Account) obj);
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountSelectActivity$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountSelectActivity.onCreate$lambda$3$1(this.f$0, (DataStoreHelper.Account) obj);
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountSelectActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountSelectActivity.onCreate$lambda$3$2(this.f$0, z, (DataStoreHelper.Account) obj);
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountSelectActivity$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountSelectActivity.onCreate$lambda$3$3(this.f$0, (DataStoreHelper.Account) obj);
            }
        });
        List list = liveAccounts;
        BaseAdapter.submitList$default(accountAdapter, list, null, 2, null);
        autofitRecyclerView.setAdapter(accountAdapter);
        if (Globals.INSTANCE.isLayout(6)) {
            activityAccountSelectBinding.editAccountButton.setBackgroundResource(R.drawable.player_button_tv_attr_no_bg);
        }
        AccountSelectActivity accountSelectActivity2 = accountSelectActivity;
        LifecycleKt.observe(accountSelectActivity2, accountSelectActivity.getAccountViewModel().getSelectedKeyIndex(), new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountSelectActivity$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountSelectActivity.onCreate$lambda$3$5(autofitRecyclerView, ((Integer) obj).intValue());
            }
        });
        LifecycleKt.observe(accountSelectActivity2, accountSelectActivity.getAccountViewModel().isEditing(), new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountSelectActivity$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountSelectActivity.onCreate$lambda$3$6(activityAccountSelectBinding, accountAdapter, ((Boolean) obj).booleanValue());
            }
        });
        if (z) {
            accountSelectActivity.getAccountViewModel().setIsEditing(true);
        }
        activityAccountSelectBinding.editAccountButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountSelectActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountSelectActivity.onCreate$lambda$3$7(z, accountSelectActivity, view);
            }
        });
        if (Globals.INSTANCE.isLayout(6)) {
            autofitRecyclerView.setSpanCount(list.size() + 1 <= 6 ? list.size() + 1 : 6);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$3$0(AccountSelectActivity accountSelectActivity, DataStoreHelper.Account it) {
        Intrinsics.checkNotNullParameter(it, "it");
        AccountViewModel.handleAccountSelect$default(accountSelectActivity.getAccountViewModel(), it, accountSelectActivity, false, false, 12, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$3$1(AccountSelectActivity accountSelectActivity, DataStoreHelper.Account it) {
        Intrinsics.checkNotNullParameter(it, "it");
        accountSelectActivity.getAccountViewModel().handleAccountUpdate(it, accountSelectActivity);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$3$2(AccountSelectActivity accountSelectActivity, boolean z, DataStoreHelper.Account it) {
        Intrinsics.checkNotNullParameter(it, "it");
        accountSelectActivity.getAccountViewModel().handleAccountUpdate(it, accountSelectActivity);
        if (z) {
            DataStoreHelper.INSTANCE.setAccount(it);
            accountSelectActivity.navigateToMainActivity();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$3$3(AccountSelectActivity accountSelectActivity, DataStoreHelper.Account it) {
        Intrinsics.checkNotNullParameter(it, "it");
        accountSelectActivity.getAccountViewModel().handleAccountDelete(it, accountSelectActivity);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$3$5(AutofitRecyclerView autofitRecyclerView, int i) {
        RecyclerView.LayoutManager layoutManager = autofitRecyclerView.getLayoutManager();
        Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.GridLayoutManager");
        ((GridLayoutManager) layoutManager).scrollToPositionWithOffset(i, 0);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$3$6(ActivityAccountSelectBinding activityAccountSelectBinding, AccountAdapter accountAdapter, boolean z) {
        if (z) {
            activityAccountSelectBinding.editAccountButton.setImageResource(R.drawable.ic_baseline_close_24);
            activityAccountSelectBinding.title.setText(R.string.manage_accounts);
            accountAdapter.setViewType(2);
        } else {
            activityAccountSelectBinding.editAccountButton.setImageResource(R.drawable.ic_baseline_edit_24);
            activityAccountSelectBinding.title.setText(R.string.select_an_account);
            accountAdapter.setViewType(0);
        }
        accountAdapter.notifyDataSetChanged();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3$7(boolean z, AccountSelectActivity accountSelectActivity, View view) {
        if (z) {
            accountSelectActivity.navigateToMainActivity();
        } else {
            accountSelectActivity.getAccountViewModel().toggleIsEditing();
        }
    }

    private final void navigateToMainActivity() {
        hasLoggedIn = true;
        UIHelper.openActivity$default(UIHelper.INSTANCE, this, MainActivity.class, null, getIntent(), 2, null);
        finish();
    }

    @Override // com.lagradost.cloudstream3.utils.BiometricAuthenticator.BiometricCallback
    public void onAuthenticationSuccess() {
        Log.i(BiometricAuthenticator.TAG, "Authentication successful in AccountSelectActivity");
    }

    @Override // com.lagradost.cloudstream3.utils.BiometricAuthenticator.BiometricCallback
    public void onAuthenticationError() {
        finish();
    }
}
