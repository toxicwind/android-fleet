package androidx.work;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class PeriodicWorkRequest extends WorkRequest {

    @SuppressLint({"MinMaxConstant"})
    public static final long MIN_PERIODIC_FLEX_MILLIS = 300000;

    @SuppressLint({"MinMaxConstant"})
    public static final long MIN_PERIODIC_INTERVAL_MILLIS = 900000;

    public static final class Builder extends WorkRequest.Builder<Builder, PeriodicWorkRequest> {
        public Builder(@NonNull Class<? extends ListenableWorker> workerClass, long repeatInterval, @NonNull TimeUnit repeatIntervalTimeUnit) {
            super(workerClass);
            this.mWorkSpec.setPeriodic(repeatIntervalTimeUnit.toMillis(repeatInterval));
        }

        @Override // androidx.work.WorkRequest.Builder
        @NonNull
        public Builder getThis() {
            return this;
        }

        @Override // androidx.work.WorkRequest.Builder
        @NonNull
        public PeriodicWorkRequest buildInternal() {
            if (this.mBackoffCriteriaSet) {
                int i = Build.VERSION.SDK_INT;
                if (this.mWorkSpec.constraints.requiresDeviceIdle()) {
                    throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
                }
            }
            if (this.mWorkSpec.expedited) {
                throw new IllegalArgumentException("PeriodicWorkRequests cannot be expedited");
            }
            return new PeriodicWorkRequest(this);
        }

        @RequiresApi(26)
        public Builder(@NonNull Class<? extends ListenableWorker> workerClass, @NonNull Duration repeatInterval) {
            super(workerClass);
            this.mWorkSpec.setPeriodic(repeatInterval.toMillis());
        }

        public Builder(@NonNull Class<? extends ListenableWorker> workerClass, long repeatInterval, @NonNull TimeUnit repeatIntervalTimeUnit, long flexInterval, @NonNull TimeUnit flexIntervalTimeUnit) {
            super(workerClass);
            this.mWorkSpec.setPeriodic(repeatIntervalTimeUnit.toMillis(repeatInterval), flexIntervalTimeUnit.toMillis(flexInterval));
        }

        @RequiresApi(26)
        public Builder(@NonNull Class<? extends ListenableWorker> workerClass, @NonNull Duration repeatInterval, @NonNull Duration flexInterval) {
            super(workerClass);
            this.mWorkSpec.setPeriodic(repeatInterval.toMillis(), flexInterval.toMillis());
        }
    }

    public PeriodicWorkRequest(Builder builder) {
        super(builder.mId, builder.mWorkSpec, builder.mTags);
    }
}
