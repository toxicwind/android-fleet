package com.lagradost.cloudstream3.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.PowerManager;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.PreferenceManager;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.settings.Globals;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PowerManagerAPI.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007J\n\u0010\n\u001a\u00020\t*\u00020\u0007J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\f\u0010\f\u001a\u00020\t*\u00020\u0007H\u0002¨\u0006\r"}, d2 = {"Lcom/lagradost/cloudstream3/utils/BatteryOptimizationChecker;", "", "<init>", "()V", "isAppRestricted", "", "context", "Landroid/content/Context;", "openBatteryOptimizationSettings", "", "showBatteryOptimizationDialog", "shouldShowBatteryOptimizationDialog", "showRequestIgnoreBatteryOptDialog", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class BatteryOptimizationChecker {
    public static final BatteryOptimizationChecker INSTANCE = new BatteryOptimizationChecker();

    private BatteryOptimizationChecker() {
    }

    public final boolean isAppRestricted(Context context) {
        if (context == null) {
            return false;
        }
        Object systemService = context.getSystemService("power");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        return !((PowerManager) systemService).isIgnoringBatteryOptimizations(context.getPackageName());
    }

    public final void openBatteryOptimizationSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (shouldShowBatteryOptimizationDialog(context)) {
            showBatteryOptimizationDialog(context);
        }
    }

    public final void showBatteryOptimizationDialog(final Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            new AlertDialog.Builder(context).setTitle(R.string.battery_dialog_title).setIcon(R.drawable.ic_battery).setMessage(R.string.battery_dialog_message).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.utils.BatteryOptimizationChecker$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    BatteryOptimizationChecker.INSTANCE.showRequestIgnoreBatteryOptDialog(context);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.utils.BatteryOptimizationChecker$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    BatteryOptimizationChecker.showBatteryOptimizationDialog$lambda$1(defaultSharedPreferences, context, dialogInterface, i);
                }
            }).show();
        } catch (Throwable th) {
            Log.e("PowerManagerAPI", "Error showing battery optimization dialog", th);
        }
    }

    static final void showBatteryOptimizationDialog$lambda$1(SharedPreferences sharedPreferences, Context context, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putBoolean(context.getString(R.string.battery_optimisation_key), false);
        editorEdit.apply();
    }

    private final boolean shouldShowBatteryOptimizationDialog(Context context) {
        return isAppRestricted(context) && PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.battery_optimisation_key), true) && Globals.INSTANCE.isLayout(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showRequestIgnoreBatteryOptDialog(Context context) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
            intent.setData(Uri.parse("package:com.lagradost.cloudstream3"));
            context.startActivity(intent);
        } catch (Throwable th) {
            Log.e("PowerManagerAPI", "Unable to invoke APP_DETAILS intent", th);
            if (th instanceof ActivityNotFoundException) {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, "Exception: Activity Not Found", (Integer) null, 2, (Object) null);
            } else {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.app_info_intent_error, (Integer) null, 2, (Object) null);
            }
        }
    }
}
