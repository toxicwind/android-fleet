package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.app.NotificationCompat;
import androidx.work.Logger;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class BatteryChargingTracker extends BroadcastReceiverConstraintTracker<Boolean> {
    public static final String TAG = Logger.tagWithPrefix("BatteryChrgTracker");

    public BatteryChargingTracker(@NonNull Context context, @NonNull TaskExecutor taskExecutor) {
        super(context, taskExecutor);
    }

    private boolean isBatteryChangedIntentCharging(Intent intent) {
        int i = Build.VERSION.SDK_INT;
        int intExtra = intent.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1);
        return intExtra == 2 || intExtra == 5;
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        int i = Build.VERSION.SDK_INT;
        intentFilter.addAction("android.os.action.CHARGING");
        intentFilter.addAction("android.os.action.DISCHARGING");
        return intentFilter;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public void onBroadcastReceive(Context context, @NonNull Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        Logger.get().debug(TAG, String.format("Received %s", action), new Throwable[0]);
        byte b2 = -1;
        switch (action.hashCode()) {
            case -1886648615:
                if (action.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
                    b2 = 3;
                }
                break;
            case -54942926:
                if (action.equals("android.os.action.DISCHARGING")) {
                    b2 = 1;
                }
                break;
            case 948344062:
                if (action.equals("android.os.action.CHARGING")) {
                    b2 = 0;
                }
                break;
            case 1019184907:
                if (action.equals("android.intent.action.ACTION_POWER_CONNECTED")) {
                    b2 = 2;
                }
                break;
        }
        if (b2 == 0) {
            setState(true);
            return;
        }
        if (b2 == 1) {
            setState(false);
        } else if (b2 == 2) {
            setState(true);
        } else {
            if (b2 != 3) {
                return;
            }
            setState(false);
        }
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public Boolean getInitialState() {
        Intent intentRegisterReceiver = this.mAppContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentRegisterReceiver != null) {
            return Boolean.valueOf(isBatteryChangedIntentCharging(intentRegisterReceiver));
        }
        Logger.get().error(TAG, "getInitialState - null intent received", new Throwable[0]);
        return null;
    }
}
