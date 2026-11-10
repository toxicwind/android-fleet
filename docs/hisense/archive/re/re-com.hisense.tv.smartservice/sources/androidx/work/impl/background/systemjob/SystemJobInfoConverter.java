package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Context;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.PersistableBundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.os.BuildCompat;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.ContentUriTriggers;
import androidx.work.Logger;
import androidx.work.NetworkType;
import androidx.work.impl.model.WorkSpec;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 23)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SystemJobInfoConverter {
    public static final String EXTRA_IS_PERIODIC = "EXTRA_IS_PERIODIC";
    public static final String EXTRA_WORK_SPEC_ID = "EXTRA_WORK_SPEC_ID";
    public static final String TAG = Logger.tagWithPrefix("SystemJobInfoConverter");
    public final ComponentName mWorkServiceComponent;

    /* JADX INFO: renamed from: androidx.work.impl.background.systemjob.SystemJobInfoConverter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$androidx$work$NetworkType = new int[NetworkType.values().length];

        static {
            try {
                $SwitchMap$androidx$work$NetworkType[NetworkType.NOT_REQUIRED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$work$NetworkType[NetworkType.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$work$NetworkType[NetworkType.UNMETERED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$work$NetworkType[NetworkType.NOT_ROAMING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$androidx$work$NetworkType[NetworkType.METERED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @VisibleForTesting(otherwise = 3)
    public SystemJobInfoConverter(@NonNull Context context) {
        this.mWorkServiceComponent = new ComponentName(context.getApplicationContext(), (Class<?>) SystemJobService.class);
    }

    @RequiresApi(24)
    public static JobInfo.TriggerContentUri convertContentUriTrigger(ContentUriTriggers.Trigger trigger) {
        return new JobInfo.TriggerContentUri(trigger.getUri(), trigger.shouldTriggerForDescendants() ? 1 : 0);
    }

    public static int convertNetworkType(NetworkType networkType) {
        int iOrdinal = networkType.ordinal();
        if (iOrdinal == 0) {
            return 0;
        }
        if (iOrdinal == 1) {
            return 1;
        }
        int i = 2;
        if (iOrdinal != 2) {
            i = 3;
            if (iOrdinal != 3) {
                if (iOrdinal != 4) {
                    Logger.get().debug(TAG, String.format("API version too low. Cannot convert network type value %s", networkType), new Throwable[0]);
                    return 1;
                }
                int i2 = Build.VERSION.SDK_INT;
                return 4;
            }
            int i3 = Build.VERSION.SDK_INT;
        }
        return i;
    }

    public static void setRequiredNetwork(@NonNull JobInfo.Builder builder, @NonNull NetworkType networkType) {
        if (Build.VERSION.SDK_INT < 30 || networkType != NetworkType.TEMPORARILY_UNMETERED) {
            builder.setRequiredNetworkType(convertNetworkType(networkType));
        } else {
            builder.setRequiredNetwork(new NetworkRequest.Builder().addCapability(25).build());
        }
    }

    public JobInfo convert(WorkSpec workSpec, int jobId) {
        Constraints constraints = workSpec.constraints;
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString(EXTRA_WORK_SPEC_ID, workSpec.id);
        persistableBundle.putBoolean(EXTRA_IS_PERIODIC, workSpec.isPeriodic());
        JobInfo.Builder extras = new JobInfo.Builder(jobId, this.mWorkServiceComponent).setRequiresCharging(constraints.requiresCharging()).setRequiresDeviceIdle(constraints.requiresDeviceIdle()).setExtras(persistableBundle);
        setRequiredNetwork(extras, constraints.getRequiredNetworkType());
        if (!constraints.requiresDeviceIdle()) {
            extras.setBackoffCriteria(workSpec.backoffDelayDuration, workSpec.backoffPolicy == BackoffPolicy.LINEAR ? 0 : 1);
        }
        long jMax = Math.max(workSpec.calculateNextRunTime() - System.currentTimeMillis(), 0L);
        if (Build.VERSION.SDK_INT <= 28 || jMax > 0) {
            extras.setMinimumLatency(jMax);
        } else if (!workSpec.expedited) {
            extras.setImportantWhileForeground(true);
        }
        int i = Build.VERSION.SDK_INT;
        if (constraints.hasContentUriTriggers()) {
            Iterator<ContentUriTriggers.Trigger> it = constraints.getContentUriTriggers().getTriggers().iterator();
            while (it.hasNext()) {
                extras.addTriggerContentUri(convertContentUriTrigger(it.next()));
            }
            extras.setTriggerContentUpdateDelay(constraints.getTriggerContentUpdateDelay());
            extras.setTriggerContentMaxDelay(constraints.getTriggerMaxContentDelay());
        }
        extras.setPersisted(false);
        int i2 = Build.VERSION.SDK_INT;
        extras.setRequiresBatteryNotLow(constraints.requiresBatteryNotLow());
        extras.setRequiresStorageNotLow(constraints.requiresStorageNotLow());
        boolean z = workSpec.runAttemptCount > 0;
        boolean z2 = jMax > 0;
        if (BuildCompat.isAtLeastS() && workSpec.expedited && !z && !z2) {
            extras.setExpedited(true);
        }
        return extras.build();
    }
}
