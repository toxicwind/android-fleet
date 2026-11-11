package com.lagradost.cloudstream3.ui.account;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import coil3.ImageLoadersKt;
import coil3.request.ErrorResult;
import coil3.request.ImageRequest;
import coil3.request.ImageRequests_androidKt;
import coil3.request.SuccessResult;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.AccountEditDialogBinding;
import com.lagradost.cloudstream3.databinding.AccountSelectLinearBinding;
import com.lagradost.cloudstream3.databinding.BottomInputDialogBinding;
import com.lagradost.cloudstream3.databinding.LockPinDialogBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.LifecycleKt;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.result.LinearListLayoutKt;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.ImageLoader;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: AccountHelper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JF\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\rJL\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\b\b\u0002\u0010\u0013\u001a\u00020\u000b2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00112\u0014\u0010\u0015\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0004\u0012\u00020\u00050\rJ\f\u0010\u0016\u001a\u00020\u0005*\u0004\u0018\u00010\u0017¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/ui/account/AccountHelper;", "", "<init>", "()V", "showAccountEditDialog", "", "context", "Landroid/content/Context;", "account", "Lcom/lagradost/cloudstream3/utils/DataStoreHelper$Account;", "isNewAccount", "", "accountEditCallback", "Lkotlin/Function1;", "accountDeleteCallback", "showPinInputDialog", "currentPin", "", "editAccount", "forStartup", "errorText", "callback", "showAccountSelectLinear", "Landroid/app/Activity;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AccountHelper {
    public static final AccountHelper INSTANCE = new AccountHelper();

    private AccountHelper() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v25, types: [T, com.lagradost.cloudstream3.utils.DataStoreHelper$Account] */
    public final void showAccountEditDialog(final Context context, final DataStoreHelper.Account account, boolean isNewAccount, final Function1<? super DataStoreHelper.Account, Unit> accountEditCallback, final Function1<? super DataStoreHelper.Account, Unit> accountDeleteCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(accountEditCallback, "accountEditCallback");
        Intrinsics.checkNotNullParameter(accountDeleteCallback, "accountDeleteCallback");
        final AccountEditDialogBinding accountEditDialogBindingInflate = AccountEditDialogBinding.inflate(LayoutInflater.from(context), null, false);
        Intrinsics.checkNotNullExpressionValue(accountEditDialogBindingInflate, "inflate(...)");
        AlertDialog.Builder view = new AlertDialog.Builder(context, R.style.AlertDialogCustom).setView(accountEditDialogBindingInflate.getRoot());
        Editable editableNewEditable = null;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = account;
        final AlertDialog alertDialogShow = view.show();
        if (!isNewAccount) {
            accountEditDialogBindingInflate.title.setText(R.string.edit_account);
        }
        EditText editText = accountEditDialogBindingInflate.accountName;
        Editable.Factory factory = Editable.Factory.getInstance();
        if (factory != null) {
            editableNewEditable = factory.newEditable(account.getName());
        }
        editText.setText(editableNewEditable);
        EditText accountName = accountEditDialogBindingInflate.accountName;
        Intrinsics.checkNotNullExpressionValue(accountName, "accountName");
        accountName.addTextChangedListener(new TextWatcher() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$showAccountEditDialog$$inlined$doOnTextChanged$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r9v3, types: [T, com.lagradost.cloudstream3.utils.DataStoreHelper$Account] */
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                String string;
                Ref.ObjectRef objectRef2 = objectRef;
                DataStoreHelper.Account account2 = (DataStoreHelper.Account) objectRef2.element;
                if (text == null || (string = text.toString()) == null) {
                    string = "";
                }
                objectRef2.element = DataStoreHelper.Account.copy$default(account2, 0, string, null, 0, null, 29, null);
            }
        });
        MaterialButton deleteBtt = accountEditDialogBindingInflate.deleteBtt;
        Intrinsics.checkNotNullExpressionValue(deleteBtt, "deleteBtt");
        deleteBtt.setVisibility(isNewAccount ? 8 : 0);
        accountEditDialogBindingInflate.deleteBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda22
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AccountHelper.showAccountEditDialog$lambda$1(context, objectRef, accountDeleteCallback, account, alertDialogShow, view2);
            }
        });
        accountEditDialogBindingInflate.cancelBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda23
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AccountHelper.showAccountEditDialog$lambda$2(alertDialogShow, view2);
            }
        });
        ImageLoader imageLoader = ImageLoader.INSTANCE;
        ImageView accountImage = accountEditDialogBindingInflate.accountImage;
        Intrinsics.checkNotNullExpressionValue(accountImage, "accountImage");
        ImageLoader.loadImage$default(imageLoader, accountImage, account.getImage(), (Function1) null, 2, (Object) null);
        accountEditDialogBindingInflate.accountImage.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda24
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AccountHelper.showAccountEditDialog$lambda$3(objectRef, accountEditDialogBindingInflate, view2);
            }
        });
        accountEditDialogBindingInflate.applyBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda25
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AccountHelper.showAccountEditDialog$lambda$4(objectRef, context, accountEditCallback, alertDialogShow, view2);
            }
        });
        if (((DataStoreHelper.Account) objectRef.element).getKeyIndex() == DataStoreHelper.INSTANCE.getDefaultAccount(context).getKeyIndex()) {
            CheckBox lockProfileCheckbox = accountEditDialogBindingInflate.lockProfileCheckbox;
            Intrinsics.checkNotNullExpressionValue(lockProfileCheckbox, "lockProfileCheckbox");
            lockProfileCheckbox.setVisibility(8);
            if (((DataStoreHelper.Account) objectRef.element).getLockPin() != null) {
                objectRef.element = DataStoreHelper.Account.copy$default((DataStoreHelper.Account) objectRef.element, 0, null, null, 0, null, 15, null);
            }
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        accountEditDialogBindingInflate.lockProfileCheckbox.setChecked(((DataStoreHelper.Account) objectRef.element).getLockPin() != null);
        accountEditDialogBindingInflate.lockProfileCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda26
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                AccountHelper.showAccountEditDialog$lambda$5(booleanRef, context, objectRef, accountEditDialogBindingInflate, compoundButton, z);
            }
        });
        booleanRef.element = true;
        accountEditDialogBindingInflate.editProfilePhotoButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AccountHelper.showAccountEditDialog$lambda$6(context, objectRef, accountEditDialogBindingInflate, view2);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final void showAccountEditDialog$lambda$1(Context context, Ref.ObjectRef objectRef, final Function1 function1, final DataStoreHelper.Account account, final AlertDialog alertDialog, View view) {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AccountHelper.showAccountEditDialog$lambda$1$0(function1, account, alertDialog, dialogInterface, i);
            }
        };
        try {
            AppContextUtils appContextUtils = AppContextUtils.INSTANCE;
            AlertDialog.Builder title = new AlertDialog.Builder(context).setTitle(R.string.delete);
            String string = context.getString(R.string.delete_message);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String str = String.format(string, Arrays.copyOf(new Object[]{((DataStoreHelper.Account) objectRef.element).getName()}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            AlertDialog alertDialogShow = title.setMessage(str).setPositiveButton(R.string.delete, onClickListener).setNegativeButton(R.string.cancel, onClickListener).show();
            Intrinsics.checkNotNullExpressionValue(alertDialogShow, "show(...)");
            AppContextUtils.setDefaultFocus$default(appContextUtils, alertDialogShow, 0, 1, null);
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAccountEditDialog$lambda$1$0(Function1 function1, DataStoreHelper.Account account, AlertDialog alertDialog, DialogInterface dialogInterface, int i) {
        if (i == -2) {
            if (alertDialog != null) {
                UIHelper.INSTANCE.dismissSafe(alertDialog);
            }
        } else {
            if (i != -1) {
                return;
            }
            function1.invoke(account);
            if (alertDialog != null) {
                UIHelper.INSTANCE.dismissSafe(alertDialog);
            }
        }
    }

    static final void showAccountEditDialog$lambda$2(AlertDialog alertDialog, View view) {
        if (alertDialog != null) {
            UIHelper.INSTANCE.dismissSafe(alertDialog);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v2, types: [T, com.lagradost.cloudstream3.utils.DataStoreHelper$Account] */
    /* JADX WARN: Type inference failed for: r10v8, types: [T, com.lagradost.cloudstream3.utils.DataStoreHelper$Account] */
    static final void showAccountEditDialog$lambda$3(Ref.ObjectRef objectRef, AccountEditDialogBinding accountEditDialogBinding, View view) {
        objectRef.element = DataStoreHelper.Account.copy$default((DataStoreHelper.Account) objectRef.element, 0, null, null, 0, null, 27, null);
        objectRef.element = DataStoreHelper.Account.copy$default((DataStoreHelper.Account) objectRef.element, 0, null, null, (((DataStoreHelper.Account) objectRef.element).getDefaultImageIndex() + 1) % DataStoreHelper.INSTANCE.getProfileImages().length, null, 23, null);
        ImageLoader imageLoader = ImageLoader.INSTANCE;
        ImageView accountImage = accountEditDialogBinding.accountImage;
        Intrinsics.checkNotNullExpressionValue(accountImage, "accountImage");
        ImageLoader.loadImage$default(imageLoader, accountImage, ((DataStoreHelper.Account) objectRef.element).getImage(), (Function1) null, 2, (Object) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final void showAccountEditDialog$lambda$4(final Ref.ObjectRef objectRef, Context context, final Function1 function1, final AlertDialog alertDialog, View view) {
        if (((DataStoreHelper.Account) objectRef.element).getLockPin() != null) {
            showPinInputDialog$default(INSTANCE, context, ((DataStoreHelper.Account) objectRef.element).getLockPin(), false, false, null, new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AccountHelper.showAccountEditDialog$lambda$4$0(function1, objectRef, alertDialog, (String) obj);
                }
            }, 24, null);
        } else {
            function1.invoke(objectRef.element);
            UIHelper.INSTANCE.dismissSafe(alertDialog);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showAccountEditDialog$lambda$4$0(Function1 function1, Ref.ObjectRef objectRef, AlertDialog alertDialog, String str) {
        if (str == null) {
            return Unit.INSTANCE;
        }
        function1.invoke(objectRef.element);
        UIHelper.INSTANCE.dismissSafe(alertDialog);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final void showAccountEditDialog$lambda$5(final Ref.BooleanRef booleanRef, Context context, final Ref.ObjectRef objectRef, final AccountEditDialogBinding accountEditDialogBinding, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(compoundButton, "<unused var>");
        if (z) {
            if (booleanRef.element) {
                showPinInputDialog$default(INSTANCE, context, null, true, false, null, new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AccountHelper.showAccountEditDialog$lambda$5$0(accountEditDialogBinding, objectRef, (String) obj);
                    }
                }, 24, null);
            }
        } else if (((DataStoreHelper.Account) objectRef.element).getLockPin() != null) {
            showPinInputDialog$default(INSTANCE, context, ((DataStoreHelper.Account) objectRef.element).getLockPin(), true, false, null, new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AccountHelper.showAccountEditDialog$lambda$5$1(objectRef, booleanRef, accountEditDialogBinding, (String) obj);
                }
            }, 24, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v2, types: [T, com.lagradost.cloudstream3.utils.DataStoreHelper$Account] */
    public static final Unit showAccountEditDialog$lambda$5$0(AccountEditDialogBinding accountEditDialogBinding, Ref.ObjectRef objectRef, String str) {
        if (str == null) {
            accountEditDialogBinding.lockProfileCheckbox.setChecked(false);
            return Unit.INSTANCE;
        }
        objectRef.element = DataStoreHelper.Account.copy$default((DataStoreHelper.Account) objectRef.element, 0, null, null, 0, str, 15, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v3, types: [T, com.lagradost.cloudstream3.utils.DataStoreHelper$Account] */
    public static final Unit showAccountEditDialog$lambda$5$1(Ref.ObjectRef objectRef, Ref.BooleanRef booleanRef, AccountEditDialogBinding accountEditDialogBinding, String str) {
        if (str == null || !Intrinsics.areEqual(str, ((DataStoreHelper.Account) objectRef.element).getLockPin())) {
            booleanRef.element = false;
            accountEditDialogBinding.lockProfileCheckbox.setChecked(true);
        } else {
            objectRef.element = DataStoreHelper.Account.copy$default((DataStoreHelper.Account) objectRef.element, 0, null, null, 0, null, 15, null);
        }
        return Unit.INSTANCE;
    }

    static final void showAccountEditDialog$lambda$6(final Context context, final Ref.ObjectRef objectRef, final AccountEditDialogBinding accountEditDialogBinding, View view) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        final BottomInputDialogBinding bottomInputDialogBindingInflate = BottomInputDialogBinding.inflate(LayoutInflater.from(context));
        Intrinsics.checkNotNullExpressionValue(bottomInputDialogBindingInflate, "inflate(...)");
        bottomSheetDialog.setContentView(bottomInputDialogBindingInflate.getRoot());
        bottomSheetDialog.show();
        bottomInputDialogBindingInflate.text1.setText(context.getString(R.string.edit_profile_image_title));
        bottomInputDialogBindingInflate.nginxTextInput.setHint(context.getString(R.string.edit_profile_image_hint));
        bottomInputDialogBindingInflate.applyBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AccountHelper.showAccountEditDialog$lambda$6$0$0(bottomInputDialogBindingInflate, bottomInputDialogBindingInflate, context, objectRef, accountEditDialogBinding, bottomSheetDialog, view2);
            }
        });
        bottomInputDialogBindingInflate.cancelBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AccountHelper.showAccountEditDialog$lambda$6$0$1(bottomSheetDialog, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAccountEditDialog$lambda$6$0$0(BottomInputDialogBinding bottomInputDialogBinding, final BottomInputDialogBinding bottomInputDialogBinding2, Context context, final Ref.ObjectRef objectRef, final AccountEditDialogBinding accountEditDialogBinding, final BottomSheetDialog bottomSheetDialog, View view) {
        final String string = bottomInputDialogBinding.nginxTextInput.getText().toString();
        if (string.length() == 0) {
            CommonActivity.INSTANCE.showToast(R.string.edit_profile_image_error_empty, (Integer) 0);
            return;
        }
        UIHelper uIHelper = UIHelper.INSTANCE;
        MaterialButton applyBtt = bottomInputDialogBinding2.applyBtt;
        Intrinsics.checkNotNullExpressionValue(applyBtt, "applyBtt");
        UIHelper.showProgress$default(uIHelper, applyBtt, 0, 1, null);
        ImageLoadersKt.ImageLoader(context).enqueue(ImageRequests_androidKt.allowHardware(new ImageRequest.Builder(context).data(string), false).listener(new ImageRequest.Listener() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$showAccountEditDialog$lambda$6$0$0$$inlined$listener$default$1
            @Override // coil3.request.ImageRequest.Listener
            public void onStart(ImageRequest request) {
            }

            @Override // coil3.request.ImageRequest.Listener
            public void onCancel(ImageRequest request) {
                UIHelper uIHelper2 = UIHelper.INSTANCE;
                MaterialButton applyBtt2 = bottomInputDialogBinding2.applyBtt;
                Intrinsics.checkNotNullExpressionValue(applyBtt2, "applyBtt");
                uIHelper2.hideProgress(applyBtt2);
            }

            @Override // coil3.request.ImageRequest.Listener
            public void onError(ImageRequest request, ErrorResult result) {
                CommonActivity.INSTANCE.showToast(R.string.edit_profile_image_error_invalid, (Integer) 0);
                UIHelper uIHelper2 = UIHelper.INSTANCE;
                MaterialButton applyBtt2 = bottomInputDialogBinding2.applyBtt;
                Intrinsics.checkNotNullExpressionValue(applyBtt2, "applyBtt");
                uIHelper2.hideProgress(applyBtt2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r10v2, types: [T, com.lagradost.cloudstream3.utils.DataStoreHelper$Account] */
            @Override // coil3.request.ImageRequest.Listener
            public void onSuccess(ImageRequest request, SuccessResult result) {
                Ref.ObjectRef objectRef2 = objectRef;
                objectRef2.element = DataStoreHelper.Account.copy$default((DataStoreHelper.Account) objectRef2.element, 0, null, string, 0, null, 27, null);
                ImageLoader imageLoader = ImageLoader.INSTANCE;
                ImageView accountImage = accountEditDialogBinding.accountImage;
                Intrinsics.checkNotNullExpressionValue(accountImage, "accountImage");
                ImageLoader.loadImage$default(imageLoader, accountImage, string, (Map) null, (Function1) null, 6, (Object) null);
                CommonActivity.INSTANCE.showToast(R.string.edit_profile_image_success, (Integer) 0);
                UIHelper.INSTANCE.dismissSafe(bottomSheetDialog);
            }
        }).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAccountEditDialog$lambda$6$0$1(BottomSheetDialog bottomSheetDialog, View view) {
        UIHelper.INSTANCE.dismissSafe(bottomSheetDialog);
    }

    public static /* synthetic */ void showPinInputDialog$default(AccountHelper accountHelper, Context context, String str, boolean z, boolean z2, String str2, Function1 function1, int i, Object obj) {
        if ((i & 8) != 0) {
            z2 = false;
        }
        boolean z3 = z2;
        if ((i & 16) != 0) {
            str2 = null;
        }
        accountHelper.showPinInputDialog(context, str, z, z3, str2, function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPinInputDialog$visibleWithText(TextView textView, int i) {
        textView.setVisibility(0);
        textView.setText(i);
    }

    private static final void showPinInputDialog$visibleWithText$0(TextView textView, String str) {
        textView.setVisibility(0);
        textView.setText(str);
    }

    public final void showPinInputDialog(final Context context, final String currentPin, boolean editAccount, boolean forStartup, String errorText, final Function1<? super String, Unit> callback) {
        DataStoreHelper.Account account;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final LockPinDialogBinding lockPinDialogBindingInflate = LockPinDialogBinding.inflate(LayoutInflater.from(context));
        Intrinsics.checkNotNullExpressionValue(lockPinDialogBindingInflate, "inflate(...)");
        boolean z = currentPin != null;
        final boolean z2 = editAccount && !z;
        int i = (editAccount && z) ? R.string.enter_current_pin : R.string.enter_pin;
        final boolean z3 = z;
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        AlertDialog.Builder onDismissListener = new AlertDialog.Builder(context, R.style.AlertDialogCustom).setView(lockPinDialogBindingInflate.getRoot()).setTitle(i).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda10
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                callback.invoke(null);
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda12
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                callback.invoke(null);
            }
        }).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda13
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                AccountHelper.showPinInputDialog$lambda$3(booleanRef, callback, dialogInterface);
            }
        });
        if (forStartup) {
            DataStoreHelper.Account[] accounts = DataStoreHelper.INSTANCE.getAccounts();
            int length = accounts.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    account = null;
                    break;
                }
                account = accounts[i2];
                if (account.getKeyIndex() == DataStoreHelper.INSTANCE.getSelectedKeyIndex()) {
                    break;
                } else {
                    i2++;
                }
            }
            onDismissListener.setTitle(context.getString(R.string.enter_pin_with_name, account != null ? account.getName() : null));
            onDismissListener.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda14
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    AccountHelper.showPinInputDialog$lambda$5(booleanRef, context, dialogInterface);
                }
            });
            onDismissListener.setNeutralButton(R.string.use_default_account, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda15
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i3) {
                    AccountHelper.showPinInputDialog$lambda$6(context, booleanRef, dialogInterface, i3);
                }
            });
        }
        if (z2) {
            if (errorText != null) {
                TextView pinEditTextError = lockPinDialogBindingInflate.pinEditTextError;
                Intrinsics.checkNotNullExpressionValue(pinEditTextError, "pinEditTextError");
                showPinInputDialog$visibleWithText$0(pinEditTextError, errorText);
            }
            onDismissListener.setPositiveButton(R.string.setup_done, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda16
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i3) {
                    AccountHelper.showPinInputDialog$lambda$7(booleanRef, lockPinDialogBindingInflate, callback, dialogInterface, i3);
                }
            });
        }
        final AlertDialog alertDialogCreate = onDismissListener.create();
        Intrinsics.checkNotNullExpressionValue(alertDialogCreate, "create(...)");
        EditText pinEditText = lockPinDialogBindingInflate.pinEditText;
        Intrinsics.checkNotNullExpressionValue(pinEditText, "pinEditText");
        pinEditText.addTextChangedListener(new TextWatcher() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$showPinInputDialog$$inlined$doOnTextChanged$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                String strValueOf = String.valueOf(text);
                if (strValueOf.length() == 4) {
                    if (z3) {
                        boolean zAreEqual = Intrinsics.areEqual(strValueOf, currentPin);
                        LockPinDialogBinding lockPinDialogBinding = lockPinDialogBindingInflate;
                        if (!zAreEqual) {
                            TextView pinEditTextError2 = lockPinDialogBinding.pinEditTextError;
                            Intrinsics.checkNotNullExpressionValue(pinEditTextError2, "pinEditTextError");
                            AccountHelper.showPinInputDialog$visibleWithText(pinEditTextError2, R.string.pin_error_incorrect);
                            lockPinDialogBindingInflate.pinEditText.setText((CharSequence) null);
                            booleanRef.element = false;
                            return;
                        }
                        TextView pinEditTextError3 = lockPinDialogBinding.pinEditTextError;
                        Intrinsics.checkNotNullExpressionValue(pinEditTextError3, "pinEditTextError");
                        pinEditTextError3.setVisibility(8);
                        booleanRef.element = true;
                        callback.invoke(strValueOf);
                        UIHelper.INSTANCE.dismissSafe(alertDialogCreate);
                        return;
                    }
                    TextView pinEditTextError4 = lockPinDialogBindingInflate.pinEditTextError;
                    Intrinsics.checkNotNullExpressionValue(pinEditTextError4, "pinEditTextError");
                    pinEditTextError4.setVisibility(8);
                    booleanRef.element = true;
                    return;
                }
                if (z2) {
                    TextView pinEditTextError5 = lockPinDialogBindingInflate.pinEditTextError;
                    Intrinsics.checkNotNullExpressionValue(pinEditTextError5, "pinEditTextError");
                    AccountHelper.showPinInputDialog$visibleWithText(pinEditTextError5, R.string.pin_error_length);
                    booleanRef.element = false;
                }
            }
        });
        lockPinDialogBindingInflate.pinEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda17
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i3, KeyEvent keyEvent) {
                return AccountHelper.showPinInputDialog$lambda$9(booleanRef, lockPinDialogBindingInflate, callback, alertDialogCreate, textView, i3, keyEvent);
            }
        });
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.show();
        lockPinDialogBindingInflate.pinEditText.requestFocus();
        lockPinDialogBindingInflate.pinEditText.postDelayed(new Runnable() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                UIHelper.INSTANCE.showInputMethod(lockPinDialogBindingInflate.pinEditText);
            }
        }, 200L);
    }

    static final void showPinInputDialog$lambda$3(Ref.BooleanRef booleanRef, Function1 function1, DialogInterface dialogInterface) {
        if (booleanRef.element) {
            return;
        }
        function1.invoke(null);
    }

    static final void showPinInputDialog$lambda$5(Ref.BooleanRef booleanRef, Context context, DialogInterface dialogInterface) {
        Activity activity;
        if (booleanRef.element || (activity = CloudStreamApp.INSTANCE.getActivity(context)) == null) {
            return;
        }
        activity.finish();
    }

    static final void showPinInputDialog$lambda$6(Context context, Ref.BooleanRef booleanRef, DialogInterface dialogInterface, int i) {
        Activity activity = CloudStreamApp.INSTANCE.getActivity(context);
        if (activity instanceof AccountSelectActivity) {
            booleanRef.element = true;
            AccountViewModel.handleAccountSelect$default(((AccountSelectActivity) activity).getAccountViewModel(), DataStoreHelper.INSTANCE.getDefaultAccount(context), activity, false, false, 12, null);
        }
    }

    static final void showPinInputDialog$lambda$7(Ref.BooleanRef booleanRef, LockPinDialogBinding lockPinDialogBinding, Function1 function1, DialogInterface dialogInterface, int i) {
        if (!booleanRef.element) {
            AccountHelper accountHelper = INSTANCE;
            Context context = lockPinDialogBinding.getRoot().getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            showPinInputDialog$default(accountHelper, context, null, true, false, lockPinDialogBinding.pinEditTextError.getText().toString(), function1, 8, null);
            return;
        }
        function1.invoke(lockPinDialogBinding.pinEditText.getText().toString());
    }

    static final boolean showPinInputDialog$lambda$9(Ref.BooleanRef booleanRef, LockPinDialogBinding lockPinDialogBinding, Function1 function1, AlertDialog alertDialog, TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6 || !booleanRef.element) {
            return true;
        }
        function1.invoke(lockPinDialogBinding.pinEditText.getText().toString());
        UIHelper.INSTANCE.dismissSafe(alertDialog);
        return true;
    }

    public final void showAccountSelectLinear(Activity activity) {
        DataStoreHelper.Account defaultAccount = null;
        final MainActivity mainActivity = activity instanceof MainActivity ? (MainActivity) activity : null;
        if (mainActivity == null) {
            return;
        }
        final AccountViewModel accountViewModel = (AccountViewModel) new ViewModelProvider(mainActivity).get(AccountViewModel.class);
        MainActivity mainActivity2 = mainActivity;
        AccountSelectLinearBinding accountSelectLinearBindingInflate = AccountSelectLinearBinding.inflate(LayoutInflater.from(mainActivity2));
        Intrinsics.checkNotNullExpressionValue(accountSelectLinearBindingInflate, "inflate(...)");
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mainActivity2);
        bottomSheetDialog.setContentView(accountSelectLinearBindingInflate.getRoot());
        bottomSheetDialog.show();
        accountSelectLinearBindingInflate.manageAccountsButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountHelper.showAccountSelectLinear$lambda$0(mainActivity, bottomSheetDialog, view);
            }
        });
        final RecyclerView accountRecyclerView = accountSelectLinearBindingInflate.accountRecyclerView;
        Intrinsics.checkNotNullExpressionValue(accountRecyclerView, "accountRecyclerView");
        accountRecyclerView.addItemDecoration(new AccountSelectLinearItemDecoration(accountRecyclerView.getResources().getDimensionPixelSize(R.dimen.account_select_linear_item_size)));
        LinearListLayoutKt.setLinearListLayout$default(accountRecyclerView, true, 0, 0, 0, 0, 30, null);
        for (DataStoreHelper.Account account : DataStoreHelper.INSTANCE.getAccounts()) {
            if (account.getKeyIndex() == DataStoreHelper.INSTANCE.getSelectedKeyIndex()) {
                defaultAccount = account;
                break;
            }
        }
        if (defaultAccount == null) {
            defaultAccount = DataStoreHelper.INSTANCE.getDefaultAccount(mainActivity2);
        }
        AccountViewModel.handleAccountSelect$default(accountViewModel, defaultAccount, mainActivity2, false, true, 4, null);
        LifecycleKt.observe(mainActivity, accountViewModel.getAccounts(), new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountHelper.showAccountSelectLinear$lambda$2(accountRecyclerView, mainActivity, accountViewModel, bottomSheetDialog, (List) obj);
            }
        });
    }

    static final void showAccountSelectLinear$lambda$0(MainActivity mainActivity, BottomSheetDialog bottomSheetDialog, View view) {
        UIHelper uIHelper = UIHelper.INSTANCE;
        MainActivity mainActivity2 = mainActivity;
        int i = R.id.accountSelectActivity;
        Bundle bundle = new Bundle();
        bundle.putBoolean("isEditingFromMainActivity", true);
        Unit unit = Unit.INSTANCE;
        UIHelper.navigate$default(uIHelper, mainActivity2, i, bundle, null, 4, null);
        UIHelper.INSTANCE.dismissSafe(bottomSheetDialog);
    }

    static final Unit showAccountSelectLinear$lambda$2(final RecyclerView recyclerView, final MainActivity mainActivity, final AccountViewModel accountViewModel, final BottomSheetDialog bottomSheetDialog, List liveAccounts) {
        Intrinsics.checkNotNullParameter(liveAccounts, "liveAccounts");
        AccountAdapter accountAdapter = new AccountAdapter(new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountHelper.showAccountSelectLinear$lambda$2$0(accountViewModel, mainActivity, bottomSheetDialog, (DataStoreHelper.Account) obj);
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountHelper.showAccountSelectLinear$lambda$2$1(accountViewModel, mainActivity, (DataStoreHelper.Account) obj);
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountHelper.showAccountSelectLinear$lambda$2$2(accountViewModel, mainActivity, (DataStoreHelper.Account) obj);
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountHelper.showAccountSelectLinear$lambda$2$3(accountViewModel, mainActivity, (DataStoreHelper.Account) obj);
            }
        });
        BaseAdapter.submitList$default(accountAdapter, liveAccounts, null, 2, null);
        recyclerView.setAdapter(accountAdapter);
        LifecycleKt.observe(mainActivity, accountViewModel.getSelectedKeyIndex(), new Function1() { // from class: com.lagradost.cloudstream3.ui.account.AccountHelper$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountHelper.showAccountSelectLinear$lambda$2$5(recyclerView, ((Integer) obj).intValue());
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showAccountSelectLinear$lambda$2$0(AccountViewModel accountViewModel, MainActivity mainActivity, BottomSheetDialog bottomSheetDialog, DataStoreHelper.Account account) {
        Intrinsics.checkNotNullParameter(account, "account");
        AccountViewModel.handleAccountSelect$default(accountViewModel, account, mainActivity, false, false, 12, null);
        UIHelper.INSTANCE.dismissSafe(bottomSheetDialog);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showAccountSelectLinear$lambda$2$1(AccountViewModel accountViewModel, MainActivity mainActivity, DataStoreHelper.Account it) {
        Intrinsics.checkNotNullParameter(it, "it");
        accountViewModel.handleAccountUpdate(it, mainActivity);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showAccountSelectLinear$lambda$2$2(AccountViewModel accountViewModel, MainActivity mainActivity, DataStoreHelper.Account it) {
        Intrinsics.checkNotNullParameter(it, "it");
        accountViewModel.handleAccountUpdate(it, mainActivity);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showAccountSelectLinear$lambda$2$3(AccountViewModel accountViewModel, MainActivity mainActivity, DataStoreHelper.Account it) {
        Intrinsics.checkNotNullParameter(it, "it");
        accountViewModel.handleAccountDelete(it, mainActivity);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showAccountSelectLinear$lambda$2$5(RecyclerView recyclerView, int i) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(i, 0);
        return Unit.INSTANCE;
    }
}
