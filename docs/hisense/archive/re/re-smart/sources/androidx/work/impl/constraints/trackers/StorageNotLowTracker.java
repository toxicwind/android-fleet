package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.Logger;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class StorageNotLowTracker extends BroadcastReceiverConstraintTracker<Boolean> {
    public static final String TAG = Logger.tagWithPrefix("StorageNotLowTracker");

    public StorageNotLowTracker(@NonNull Context context, @NonNull TaskExecutor taskExecutor) {
        super(context, taskExecutor);
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.DEVICE_STORAGE_OK");
        intentFilter.addAction("android.intent.action.DEVICE_STORAGE_LOW");
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
        if (iHashCode != -1181163412) {
            if (iHashCode == -730838620 && action.equals("android.intent.action.DEVICE_STORAGE_OK")) {
                b2 = 0;
            }
        } else if (action.equals("android.intent.action.DEVICE_STORAGE_LOW")) {
            b2 = 1;
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
        Intent intentRegisterReceiver = this.mAppContext.registerReceiver(null, getIntentFilter());
        if (intentRegisterReceiver != null && intentRegisterReceiver.getAction() != null) {
            String action = intentRegisterReceiver.getAction();
            byte b2 = -1;
            int iHashCode = action.hashCode();
            if (iHashCode != -1181163412) {
                if (iHashCode == -730838620 && action.equals("android.intent.action.DEVICE_STORAGE_OK")) {
                    b2 = 0;
                }
            } else if (action.equals("android.intent.action.DEVICE_STORAGE_LOW")) {
                b2 = 1;
            }
            if (b2 != 0) {
                return b2 != 1 ? null : false;
            }
        }
        return true;
    }
}
