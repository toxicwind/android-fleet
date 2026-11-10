package com.android.settingslib.inputmethod;

import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import androidx.preference.Preference;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.R$string;
import com.android.settingslib.RestrictedSwitchPreference;

/* JADX INFO: loaded from: classes.dex */
public class InputMethodPreference extends RestrictedSwitchPreference implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    private static final String TAG = InputMethodPreference.class.getSimpleName();
    private AlertDialog mDialog;
    private final InputMethodInfo mImi;
    private final OnSavePreferenceListener mOnSaveListener;

    public interface OnSavePreferenceListener {
        void onSaveInputMethodPreference(InputMethodPreference inputMethodPreference);
    }

    @VisibleForTesting
    InputMethodPreference(Context context, InputMethodInfo inputMethodInfo, CharSequence charSequence, boolean z, OnSavePreferenceListener onSavePreferenceListener) {
        super(context);
        this.mDialog = null;
        setPersistent(false);
        this.mImi = inputMethodInfo;
        this.mOnSaveListener = onSavePreferenceListener;
        setSwitchTextOn("");
        setSwitchTextOff("");
        setKey(inputMethodInfo.getId());
        setTitle(charSequence);
        String settingsActivity = inputMethodInfo.getSettingsActivity();
        if (TextUtils.isEmpty(settingsActivity)) {
            setIntent(null);
        } else {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setClassName(inputMethodInfo.getPackageName(), settingsActivity);
            setIntent(intent);
        }
        InputMethodSettingValuesWrapper.getInstance(context);
        if (inputMethodInfo.isSystem()) {
            InputMethodAndSubtypeUtil.isValidNonAuxAsciiCapableIme(inputMethodInfo);
        }
        setOnPreferenceClickListener(this);
        setOnPreferenceChangeListener(this);
    }

    private boolean isImeEnabler() {
        return getWidgetLayoutResource() != 0;
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (!isImeEnabler()) {
            return false;
        }
        if (isChecked()) {
            setCheckedInternal(false);
            return false;
        }
        if (this.mImi.isSystem()) {
            if (this.mImi.getServiceInfo().directBootAware || isTv()) {
                setCheckedInternal(true);
            } else if (!isTv()) {
                showDirectBootWarnDialog();
            }
        } else {
            showSecurityWarnDialog();
        }
        return false;
    }

    private void setCheckedInternal(boolean z) {
        super.setChecked(z);
        this.mOnSaveListener.onSaveInputMethodPreference(this);
        notifyChanged();
    }

    private void showSecurityWarnDialog() {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.mDialog.dismiss();
        }
        Context context = getContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(R.string.dialog_alert_title);
        builder.setMessage(context.getString(R$string.ime_security_warning, this.mImi.getServiceInfo().applicationInfo.loadLabel(context.getPackageManager())));
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.settingslib.inputmethod.-$$Lambda$InputMethodPreference$pHt4-6FWRQ9Ts6PuJy_AB14MhJc
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$showSecurityWarnDialog$0$InputMethodPreference(dialogInterface, i);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.settingslib.inputmethod.-$$Lambda$InputMethodPreference$HH5dtwzFZv06UNDXJAO6Cyx4kxo
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$showSecurityWarnDialog$1$InputMethodPreference(dialogInterface, i);
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.settingslib.inputmethod.-$$Lambda$InputMethodPreference$hpUUW_Jm1ATEk1-GeQASyreqYZI
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f$0.lambda$showSecurityWarnDialog$2$InputMethodPreference(dialogInterface);
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        this.mDialog = alertDialogCreate;
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$showSecurityWarnDialog$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$showSecurityWarnDialog$0$InputMethodPreference(DialogInterface dialogInterface, int i) {
        if (this.mImi.getServiceInfo().directBootAware || isTv()) {
            setCheckedInternal(true);
        } else {
            showDirectBootWarnDialog();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$showSecurityWarnDialog$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$showSecurityWarnDialog$1$InputMethodPreference(DialogInterface dialogInterface, int i) {
        setCheckedInternal(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$showSecurityWarnDialog$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$showSecurityWarnDialog$2$InputMethodPreference(DialogInterface dialogInterface) {
        setCheckedInternal(false);
    }

    private boolean isTv() {
        return (getContext().getResources().getConfiguration().uiMode & 15) == 4;
    }

    private void showDirectBootWarnDialog() {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.mDialog.dismiss();
        }
        Context context = getContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setMessage(context.getText(R$string.direct_boot_unaware_dialog_message));
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.settingslib.inputmethod.-$$Lambda$InputMethodPreference$_R1WCgG1LabBNKieYWiJs9NnYv4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$showDirectBootWarnDialog$3$InputMethodPreference(dialogInterface, i);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.settingslib.inputmethod.-$$Lambda$InputMethodPreference$8Yu3IA81uQ9mforg_QOtWUG_Sj4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$showDirectBootWarnDialog$4$InputMethodPreference(dialogInterface, i);
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        this.mDialog = alertDialogCreate;
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$showDirectBootWarnDialog$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$showDirectBootWarnDialog$3$InputMethodPreference(DialogInterface dialogInterface, int i) {
        setCheckedInternal(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$showDirectBootWarnDialog$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$showDirectBootWarnDialog$4$InputMethodPreference(DialogInterface dialogInterface, int i) {
        setCheckedInternal(false);
    }
}
