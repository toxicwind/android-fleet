package com.lagradost.cloudstream3.ui.settings;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.biometric.BiometricPrompt;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.ErrorLoadingException;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.AccountManagmentBinding;
import com.lagradost.cloudstream3.databinding.AccountSwitchBinding;
import com.lagradost.cloudstream3.databinding.AddAccountInputBinding;
import com.lagradost.cloudstream3.databinding.DeviceAuthBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.syncproviders.AccountManager;
import com.lagradost.cloudstream3.syncproviders.AuthData;
import com.lagradost.cloudstream3.syncproviders.AuthLoginRequirement;
import com.lagradost.cloudstream3.syncproviders.AuthLoginResponse;
import com.lagradost.cloudstream3.syncproviders.AuthRepo;
import com.lagradost.cloudstream3.syncproviders.AuthUser;
import com.lagradost.cloudstream3.syncproviders.PlainAuthRepo;
import com.lagradost.cloudstream3.syncproviders.SubtitleRepo;
import com.lagradost.cloudstream3.syncproviders.SyncRepo;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BasePreferenceFragmentCompat;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.BackupUtils;
import com.lagradost.cloudstream3.utils.BiometricAuthenticator;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.ImageLoader;
import com.lagradost.cloudstream3.utils.SingleSelectionHelper;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SettingsAccount.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u00012\u00020\u0002:\u0001\u0013B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\u0006H\u0016J\b\u0010\n\u001a\u00020\u0006H\u0016J\u001a\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001c\u0010\u0010\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016¨\u0006\u0014"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/SettingsAccount;", "Lcom/lagradost/cloudstream3/ui/BasePreferenceFragmentCompat;", "Lcom/lagradost/cloudstream3/utils/BiometricAuthenticator$BiometricCallback;", "<init>", "()V", "updateAuthPreference", "", "enabled", "", "onAuthenticationError", "onAuthenticationSuccess", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "onCreatePreferences", "rootKey", "", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SettingsAccount extends BasePreferenceFragmentCompat implements BiometricAuthenticator.BiometricCallback {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: SettingsAccount.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0018\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\u0016\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0018\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/SettingsAccount$Companion;", "", "<init>", "()V", "showLoginInfo", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "api", "Lcom/lagradost/cloudstream3/syncproviders/AuthRepo;", "info", "Lcom/lagradost/cloudstream3/syncproviders/AuthUser;", "index", "", "showAccountSwitch", "showPin", "showAppLogin", "addAccount", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void showPin$lambda$0$0(DialogInterface dialogInterface, int i) {
        }

        private Companion() {
        }

        public final void showLoginInfo(final FragmentActivity activity, final AuthRepo api, final AuthUser info, int index) {
            String string;
            Intrinsics.checkNotNullParameter(api, "api");
            if (activity == null) {
                return;
            }
            AccountManagmentBinding accountManagmentBindingInflate = AccountManagmentBinding.inflate(activity.getLayoutInflater(), null, false);
            Intrinsics.checkNotNullExpressionValue(accountManagmentBindingInflate, "inflate(...)");
            final AlertDialog alertDialogShow = new AlertDialog.Builder(activity, R.style.AlertDialogCustom).setView(accountManagmentBindingInflate.getRoot()).show();
            CardView accountMainProfilePictureHolder = accountManagmentBindingInflate.accountMainProfilePictureHolder;
            Intrinsics.checkNotNullExpressionValue(accountMainProfilePictureHolder, "accountMainProfilePictureHolder");
            CardView cardView = accountMainProfilePictureHolder;
            String profilePicture = info != null ? info.getProfilePicture() : null;
            cardView.setVisibility(!(profilePicture == null || profilePicture.length() == 0) ? 0 : 8);
            ImageLoader imageLoader = ImageLoader.INSTANCE;
            ImageView accountMainProfilePicture = accountManagmentBindingInflate.accountMainProfilePicture;
            Intrinsics.checkNotNullExpressionValue(accountMainProfilePicture, "accountMainProfilePicture");
            ImageLoader.loadImage$default(imageLoader, accountMainProfilePicture, info != null ? info.getProfilePicture() : null, (Map) null, (Function1) null, 6, (Object) null);
            TextView accountLogout = accountManagmentBindingInflate.accountLogout;
            Intrinsics.checkNotNullExpressionValue(accountLogout, "accountLogout");
            accountLogout.setVisibility(info != null ? 0 : 8);
            accountManagmentBindingInflate.accountLogout.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsAccount$Companion$$ExternalSyntheticLambda10
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SettingsAccount.Companion.showLoginInfo$lambda$0(info, alertDialogShow, activity, api, view);
                }
            });
            TextView textView = (TextView) alertDialogShow.findViewById(R.id.account_name);
            if (textView != null) {
                if (info != null) {
                    String name = info.getName();
                    if (name == null) {
                        name = String.format("%s %d", Arrays.copyOf(new Object[]{activity.getString(R.string.account), Integer.valueOf(index + 1)}, 2));
                        Intrinsics.checkNotNullExpressionValue(name, "format(...)");
                    }
                    string = name;
                } else {
                    string = activity.getString(R.string.no_account);
                }
                textView.setText(string);
            }
            accountManagmentBindingInflate.accountSite.setText(api.getName());
            accountManagmentBindingInflate.accountSwitchAccount.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsAccount$Companion$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SettingsAccount.Companion.showLoginInfo$lambda$1(alertDialogShow, activity, api, view);
                }
            });
            if (Globals.INSTANCE.isLayout(6)) {
                accountManagmentBindingInflate.accountSwitchAccount.requestFocus();
            }
        }

        static final void showLoginInfo$lambda$0(AuthUser authUser, AlertDialog alertDialog, FragmentActivity fragmentActivity, AuthRepo authRepo, View view) {
            if (authUser != null) {
                Coroutines.INSTANCE.ioSafe(SettingsAccount.INSTANCE, new SettingsAccount$Companion$showLoginInfo$1$1(authRepo, authUser, null));
            }
            UIHelper.INSTANCE.dismissSafe(alertDialog, fragmentActivity);
        }

        static final void showLoginInfo$lambda$1(AlertDialog alertDialog, FragmentActivity fragmentActivity, AuthRepo authRepo, View view) {
            UIHelper.INSTANCE.dismissSafe(alertDialog, fragmentActivity);
            SettingsAccount.INSTANCE.showAccountSwitch(fragmentActivity, authRepo);
        }

        private final void showAccountSwitch(final FragmentActivity activity, final AuthRepo api) {
            AuthData[] accounts = api.getAccounts();
            AccountSwitchBinding accountSwitchBindingInflate = AccountSwitchBinding.inflate(activity.getLayoutInflater(), null, false);
            Intrinsics.checkNotNullExpressionValue(accountSwitchBindingInflate, "inflate(...)");
            final AlertDialog alertDialogShow = new AlertDialog.Builder(activity, R.style.AlertDialogCustom).setView(accountSwitchBindingInflate.getRoot()).show();
            accountSwitchBindingInflate.accountAdd.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsAccount$Companion$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SettingsAccount.Companion.showAccountSwitch$lambda$0(activity, api, alertDialogShow, view);
                }
            });
            accountSwitchBindingInflate.accountNone.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsAccount$Companion$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SettingsAccount.Companion.showAccountSwitch$lambda$1(api, alertDialogShow, activity, view);
                }
            });
            AccountAdapter accountAdapter = new AccountAdapter(new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsAccount$Companion$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SettingsAccount.Companion.showAccountSwitch$lambda$2(alertDialogShow, activity, api, (AccountClickCallback) obj);
                }
            });
            BaseAdapter.submitList$default(accountAdapter, ArraysKt.toList(accounts), null, 2, null);
            RecyclerView recyclerView = (RecyclerView) alertDialogShow.findViewById(R.id.account_list);
            if (recyclerView != null) {
                recyclerView.setAdapter(accountAdapter);
            }
        }

        static final void showAccountSwitch$lambda$0(FragmentActivity fragmentActivity, AuthRepo authRepo, AlertDialog alertDialog, View view) {
            SettingsAccount.INSTANCE.addAccount(fragmentActivity, authRepo);
            if (alertDialog != null) {
                UIHelper.INSTANCE.dismissSafe(alertDialog, fragmentActivity);
            }
        }

        static final void showAccountSwitch$lambda$1(AuthRepo authRepo, AlertDialog alertDialog, FragmentActivity fragmentActivity, View view) {
            authRepo.setAccountId(-1);
            if (alertDialog != null) {
                UIHelper.INSTANCE.dismissSafe(alertDialog, fragmentActivity);
            }
        }

        static final Unit showAccountSwitch$lambda$2(AlertDialog alertDialog, FragmentActivity fragmentActivity, AuthRepo authRepo, AccountClickCallback it) {
            Intrinsics.checkNotNullParameter(it, "it");
            if (alertDialog != null) {
                UIHelper.INSTANCE.dismissSafe(alertDialog, fragmentActivity);
            }
            authRepo.setAccountId(it.getCard().getUser().getId());
            return Unit.INSTANCE;
        }

        public final void showPin(FragmentActivity activity, final AuthRepo api) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(api, "api");
            DeviceAuthBinding deviceAuthBindingInflate = DeviceAuthBinding.inflate(activity.getLayoutInflater(), null, false);
            Intrinsics.checkNotNullExpressionValue(deviceAuthBindingInflate, "inflate(...)");
            AlertDialog.Builder view = new AlertDialog.Builder(activity).setView(deviceAuthBindingInflate.getRoot());
            view.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsAccount$Companion$$ExternalSyntheticLambda8
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    SettingsAccount.Companion.showPin$lambda$0$0(dialogInterface, i);
                }
            });
            if (api.getHasOAuth2()) {
                view.setPositiveButton(R.string.auth_locally, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsAccount$Companion$$ExternalSyntheticLambda9
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        api.openOAuth2PageWithToast();
                    }
                });
            }
            AlertDialog alertDialogCreate = view.create();
            Intrinsics.checkNotNullExpressionValue(alertDialogCreate, "create(...)");
            Coroutines.INSTANCE.ioSafe(this, new SettingsAccount$Companion$showPin$2(api, activity, alertDialogCreate, deviceAuthBindingInflate, null));
        }

        public final void showAppLogin(final FragmentActivity activity, final AuthRepo api) throws ErrorLoadingException {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(api, "api");
            final AddAccountInputBinding addAccountInputBindingInflate = AddAccountInputBinding.inflate(activity.getLayoutInflater(), null, false);
            Intrinsics.checkNotNullExpressionValue(addAccountInputBindingInflate, "inflate(...)");
            final AlertDialog alertDialogShow = new AlertDialog.Builder(activity, R.style.AlertDialogCustom).setView(addAccountInputBindingInflate.getRoot()).show();
            final AuthLoginRequirement inAppLoginRequirement = api.getInAppLoginRequirement();
            if (inAppLoginRequirement == null) {
                throw new ErrorLoadingException("Missing LoginRequirement");
            }
            List<Pair> listListOf = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(addAccountInputBindingInflate.loginEmailInput, Boolean.valueOf(inAppLoginRequirement.getEmail())), TuplesKt.to(addAccountInputBindingInflate.loginPasswordInput, Boolean.valueOf(inAppLoginRequirement.getPassword())), TuplesKt.to(addAccountInputBindingInflate.loginServerInput, Boolean.valueOf(inAppLoginRequirement.getServer())), TuplesKt.to(addAccountInputBindingInflate.loginUsernameInput, Boolean.valueOf(inAppLoginRequirement.getUsername()))});
            if (Globals.INSTANCE.isLayout(6)) {
                for (Pair pair : listListOf) {
                    Object objComponent1 = pair.component1();
                    Intrinsics.checkNotNullExpressionValue(objComponent1, "component1(...)");
                    EditText editText = (EditText) objComponent1;
                    editText.setVisibility(((Boolean) pair.component2()).booleanValue() ? 0 : 8);
                    editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsAccount$Companion$$ExternalSyntheticLambda0
                        @Override // android.widget.TextView.OnEditorActionListener
                        public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                            return SettingsAccount.Companion.showAppLogin$lambda$0$0(textView, i, keyEvent);
                        }
                    });
                }
            } else {
                for (Pair pair2 : listListOf) {
                    Object objComponent2 = pair2.component1();
                    Intrinsics.checkNotNullExpressionValue(objComponent2, "component1(...)");
                    ((EditText) objComponent2).setVisibility(((Boolean) pair2.component2()).booleanValue() ? 0 : 8);
                }
            }
            MaterialButton createAccount = addAccountInputBindingInflate.createAccount;
            Intrinsics.checkNotNullExpressionValue(createAccount, "createAccount");
            MaterialButton materialButton = createAccount;
            String createAccountUrl = api.getCreateAccountUrl();
            materialButton.setVisibility(createAccountUrl == null || StringsKt.isBlank(createAccountUrl) ? 8 : 0);
            addAccountInputBindingInflate.createAccount.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsAccount$Companion$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SettingsAccount.Companion.showAppLogin$lambda$2(api, activity, alertDialogShow, view);
                }
            });
            List listListOf2 = CollectionsKt.listOf((Object[]) new EditText[]{addAccountInputBindingInflate.loginUsernameInput, addAccountInputBindingInflate.loginEmailInput, addAccountInputBindingInflate.loginServerInput, addAccountInputBindingInflate.loginPasswordInput});
            ArrayList arrayList = new ArrayList();
            for (Object obj : listListOf2) {
                EditText editText2 = (EditText) obj;
                Intrinsics.checkNotNull(editText2);
                if (editText2.getVisibility() == 0) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = arrayList;
            Object objFirstOrNull = CollectionsKt.firstOrNull((List<? extends Object>) arrayList2);
            if (!arrayList2.isEmpty()) {
                ListIterator listIterator = arrayList2.listIterator(arrayList2.size());
                while (listIterator.hasPrevious()) {
                    EditText editText3 = (EditText) objFirstOrNull;
                    EditText editText4 = (EditText) listIterator.previous();
                    int id = editText4.getId();
                    if (editText3 != null) {
                        editText3.setNextFocusDownId(id);
                    }
                    if (editText3 != null) {
                        editText4.setNextFocusUpId(editText3.getId());
                    }
                    objFirstOrNull = editText4;
                }
            }
            EditText editText5 = (EditText) CollectionsKt.firstOrNull((List) arrayList2);
            if (editText5 != null) {
                addAccountInputBindingInflate.createAccount.setNextFocusDownId(editText5.getId());
                editText5.setNextFocusUpId(addAccountInputBindingInflate.createAccount.getId());
            }
            int id2 = addAccountInputBindingInflate.applyBtt.getId();
            EditText editText6 = (EditText) CollectionsKt.lastOrNull((List) arrayList2);
            if (editText6 != null) {
                editText6.setNextFocusDownId(id2);
            }
            addAccountInputBindingInflate.text1.setText(api.getName());
            addAccountInputBindingInflate.applyBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsAccount$Companion$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SettingsAccount.Companion.showAppLogin$lambda$7(inAppLoginRequirement, addAccountInputBindingInflate, api, alertDialogShow, activity, view);
                }
            });
            addAccountInputBindingInflate.cancelBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsAccount$Companion$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    UIHelper.INSTANCE.dismissSafe(alertDialogShow, activity);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean showAppLogin$lambda$0$0(TextView textView, int i, KeyEvent keyEvent) {
            if (i != 5) {
                return true;
            }
            View viewFocusSearch = textView.focusSearch(130);
            return viewFocusSearch != null && viewFocusSearch.requestFocus(130);
        }

        static final void showAppLogin$lambda$2(AuthRepo authRepo, FragmentActivity fragmentActivity, AlertDialog alertDialog, View view) {
            CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
            String createAccountUrl = authRepo.getCreateAccountUrl();
            if (createAccountUrl == null) {
                return;
            }
            companion.openBrowser(createAccountUrl, fragmentActivity);
            UIHelper.INSTANCE.dismissSafe(alertDialog);
        }

        static final void showAppLogin$lambda$7(AuthLoginRequirement authLoginRequirement, AddAccountInputBinding addAccountInputBinding, AuthRepo authRepo, AlertDialog alertDialog, FragmentActivity fragmentActivity, View view) {
            Editable text;
            Editable text2;
            Editable text3;
            Editable text4;
            AuthLoginResponse authLoginResponse = new AuthLoginResponse((!authLoginRequirement.getPassword() || (text3 = addAccountInputBinding.loginPasswordInput.getText()) == null) ? null : text3.toString(), (!authLoginRequirement.getUsername() || (text4 = addAccountInputBinding.loginUsernameInput.getText()) == null) ? null : text4.toString(), (!authLoginRequirement.getEmail() || (text2 = addAccountInputBinding.loginEmailInput.getText()) == null) ? null : text2.toString(), (!authLoginRequirement.getServer() || (text = addAccountInputBinding.loginServerInput.getText()) == null) ? null : text.toString());
            UIHelper uIHelper = UIHelper.INSTANCE;
            MaterialButton applyBtt = addAccountInputBinding.applyBtt;
            Intrinsics.checkNotNullExpressionValue(applyBtt, "applyBtt");
            UIHelper.showProgress$default(uIHelper, applyBtt, 0, 1, null);
            Coroutines.INSTANCE.ioSafe(SettingsAccount.INSTANCE, new SettingsAccount$Companion$showAppLogin$7$1(authRepo, authLoginResponse, alertDialog, fragmentActivity, addAccountInputBinding, null));
        }

        public final void addAccount(FragmentActivity activity, AuthRepo api) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(api, "api");
            try {
                if (api.getHasPin() && !Globals.INSTANCE.isLayout(1)) {
                    showPin(activity, api);
                    return;
                }
                if (api.getHasOAuth2()) {
                    api.openOAuth2PageWithToast();
                } else {
                    if (api.getHasInApp()) {
                        showAppLogin(activity, api);
                        return;
                    }
                    throw new NotImplementedError("The api " + api.getName() + " has no login");
                }
            } catch (Throwable th) {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, TextUtilKt.txt(R.string.authenticated_user_fail, api.getName()), (Integer) null, 2, (Object) null);
                ArchComponentExtKt.logError(th);
            }
        }
    }

    private final void updateAuthPreference(boolean enabled) {
        String string = getString(R.string.biometric_key);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        Context context = getContext();
        if (context == null) {
            return;
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Intrinsics.checkNotNullExpressionValue(defaultSharedPreferences, "getDefaultSharedPreferences(...)");
        SharedPreferences.Editor editorEdit = defaultSharedPreferences.edit();
        editorEdit.putBoolean(string, enabled);
        editorEdit.apply();
        SwitchPreference switchPreference = (SwitchPreference) findPreference(string);
        if (switchPreference != null) {
            switchPreference.setChecked(enabled);
        }
    }

    @Override // com.lagradost.cloudstream3.utils.BiometricAuthenticator.BiometricCallback
    public void onAuthenticationError() {
        BiometricAuthenticator biometricAuthenticator = BiometricAuthenticator.INSTANCE;
        Context context = getContext();
        if (context == null) {
            return;
        }
        updateAuthPreference(!biometricAuthenticator.isAuthEnabled(context));
    }

    @Override // com.lagradost.cloudstream3.utils.BiometricAuthenticator.BiometricCallback
    public void onAuthenticationSuccess() {
        BiometricAuthenticator biometricAuthenticator = BiometricAuthenticator.INSTANCE;
        Context context = getContext();
        if (context == null) {
            return;
        }
        if (biometricAuthenticator.isAuthEnabled(context)) {
            updateAuthPreference(true);
            BackupUtils.INSTANCE.backup(getActivity());
            FragmentActivity activity = getActivity();
            if (activity != null) {
                String string = getString(R.string.biometric_setting);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                SingleSelectionHelper.INSTANCE.showBottomDialogText(activity, string, AppContextUtils.INSTANCE.html(getString(R.string.biometric_warning)), new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsAccount$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SettingsAccount.onAuthenticationSuccess$lambda$0();
                    }
                });
                return;
            }
            return;
        }
        updateAuthPreference(false);
    }

    static final Unit onAuthenticationSuccess$lambda$0() {
        CommonActivity.INSTANCE.getOnDialogDismissedEvent();
        return Unit.INSTANCE;
    }

    @Override // com.lagradost.cloudstream3.ui.BasePreferenceFragmentCompat, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        SettingsFragment.INSTANCE.setUpToolbar(this, R.string.category_account);
        SettingsAccount settingsAccount = this;
        SettingsFragment.INSTANCE.setPaddingBottom(settingsAccount);
        SettingsFragment.INSTANCE.setToolBarScrollFlags((PreferenceFragmentCompat) settingsAccount);
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        Preference preferenceHideOn;
        UIHelper.INSTANCE.hideKeyboard(this);
        setPreferencesFromResource(R.xml.settings_account, rootKey);
        SettingsAccount settingsAccount = this;
        Preference pref = SettingsFragment.INSTANCE.getPref(settingsAccount, R.string.pref_category_security_key);
        if (pref != null) {
            SettingsFragment.INSTANCE.hideOn(pref, 6);
        }
        Preference pref2 = SettingsFragment.INSTANCE.getPref(settingsAccount, R.string.biometric_key);
        if (pref2 != null && (preferenceHideOn = SettingsFragment.INSTANCE.hideOn(pref2, 6)) != null) {
            preferenceHideOn.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsAccount$$ExternalSyntheticLambda0
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return SettingsAccount.onCreatePreferences$lambda$0(this.f$0, preference);
                }
            });
        }
        for (Pair pair : CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(Integer.valueOf(R.string.mal_key), new SyncRepo(AccountManager.INSTANCE.getMalApi())), TuplesKt.to(Integer.valueOf(R.string.kitsu_key), new SyncRepo(AccountManager.INSTANCE.getKitsuApi())), TuplesKt.to(Integer.valueOf(R.string.anilist_key), new SyncRepo(AccountManager.INSTANCE.getAniListApi())), TuplesKt.to(Integer.valueOf(R.string.simkl_key), new SyncRepo(AccountManager.INSTANCE.getSimklApi())), TuplesKt.to(Integer.valueOf(R.string.opensubtitles_key), new SubtitleRepo(AccountManager.INSTANCE.getOpenSubtitlesApi())), TuplesKt.to(Integer.valueOf(R.string.subdl_key), new SubtitleRepo(AccountManager.INSTANCE.getSubDlApi())), TuplesKt.to(Integer.valueOf(R.string.animeskip_key), new PlainAuthRepo(AccountManager.INSTANCE.getAnimeSkipApi()))})) {
            int iIntValue = ((Number) pair.component1()).intValue();
            final AuthRepo authRepo = (AuthRepo) pair.component2();
            Preference pref3 = SettingsFragment.INSTANCE.getPref(settingsAccount, iIntValue);
            if (pref3 != null) {
                pref3.setTitle(authRepo.getName());
                pref3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsAccount$$ExternalSyntheticLambda1
                    @Override // androidx.preference.Preference.OnPreferenceClickListener
                    public final boolean onPreferenceClick(Preference preference) {
                        return SettingsAccount.onCreatePreferences$lambda$1$0(this.f$0, authRepo, preference);
                    }
                });
            }
        }
    }

    static final boolean onCreatePreferences$lambda$0(SettingsAccount settingsAccount, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Context context = settingsAccount.getContext();
        if (context != null && BiometricAuthenticator.INSTANCE.deviceHasPasswordPinLock(context)) {
            BiometricAuthenticator biometricAuthenticator = BiometricAuthenticator.INSTANCE;
            FragmentActivity activity = settingsAccount.getActivity();
            if (activity == null) {
                return false;
            }
            biometricAuthenticator.startBiometricAuthentication(activity, R.string.biometric_authentication_title, false);
            BiometricPrompt.PromptInfo promptInfo = BiometricAuthenticator.INSTANCE.getPromptInfo();
            if (promptInfo != null) {
                BiometricAuthenticator.INSTANCE.setAuthCallback(settingsAccount);
                BiometricPrompt biometricPrompt = BiometricAuthenticator.INSTANCE.getBiometricPrompt();
                if (biometricPrompt != null) {
                    biometricPrompt.authenticate(promptInfo);
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreatePreferences$lambda$1$0(SettingsAccount settingsAccount, AuthRepo authRepo, Preference it) {
        Intrinsics.checkNotNullParameter(it, "it");
        FragmentActivity activity = settingsAccount.getActivity();
        if (activity == null) {
            return false;
        }
        AuthUser authUser = authRepo.authUser();
        AuthData[] accounts = authRepo.getAccounts();
        int length = accounts.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            }
            AuthData authData = accounts[i];
            if (authUser != null && authData.getUser().getId() == authUser.getId()) {
                break;
            }
            i++;
        }
        if (!(authRepo.getAccounts().length == 0)) {
            INSTANCE.showLoginInfo(activity, authRepo, authUser, i);
        } else {
            INSTANCE.addAccount(activity, authRepo);
        }
        return true;
    }
}
