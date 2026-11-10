package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.app.NotificationCompat;
import androidx.work.Logger;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.firebase.analytics.FirebaseAnalytics;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class BatteryNotLowTracker extends BroadcastReceiverConstraintTracker<Boolean> {
    public static final float BATTERY_LOW_THRESHOLD = 0.15f;
    public static final String TAG = Logger.tagWithPrefix("BatteryNotLowTracker");

    public BatteryNotLowTracker(@NonNull Context context, @NonNull TaskExecutor taskExecutor) {
        super(context, taskExecutor);
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_OKAY");
        intentFilter.addAction("android.intent.action.BATTERY_LOW");
        return intentFilter;
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public void onBroadcastReceive(Context context, @NonNull Intent intent) {
        if (intent.getAction() == null) {
            return;
        }
        Logger.get().debug(TAG, String.format("Received %s", intent.getAction()), new Throwable[0]);
        String action = intent.getAction();
        byte b2 = -1;
        int iHashCode = action.hashCode();
        if (iHashCode != -1980154005) {
            if (iHashCode == 490310653 && action.equals("android.intent.action.BATTERY_LOW")) {
                b2 = 1;
            }
        } else if (action.equals("android.intent.action.BATTERY_OKAY")) {
            b2 = 0;
        }
        if (b2 == 0) {
            setState(true);
        } else {
            if (b2 != 1) {
                return;
            }
            setState(false);
        }
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public Boolean getInitialState() {
        Intent intentRegisterReceiver = this.mAppContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentRegisterReceiver == null) {
            Logger.get().error(TAG, "getInitialState - null intent received", new Throwable[0]);
            return null;
        }
        int intExtra = intentRegisterReceiver.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1);
        float intExtra2 = intentRegisterReceiver.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1) / intentRegisterReceiver.getIntExtra("scale", -1);
        boolean z = true;
        if (intExtra != 1 && intExtra2 <= 0.15f) {
            z = false;
        }
        return Boolean.valueOf(z);
    }
}
