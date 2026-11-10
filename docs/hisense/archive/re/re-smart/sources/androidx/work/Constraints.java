package androidx.work;

import android.net.Uri;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.room.ColumnInfo;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class Constraints {
    public static final Constraints NONE = new Builder().build();

    @ColumnInfo(name = "content_uri_triggers")
    public ContentUriTriggers mContentUriTriggers;

    @ColumnInfo(name = "required_network_type")
    public NetworkType mRequiredNetworkType;

    @ColumnInfo(name = "requires_battery_not_low")
    public boolean mRequiresBatteryNotLow;

    @ColumnInfo(name = "requires_charging")
    public boolean mRequiresCharging;

    @ColumnInfo(name = "requires_device_idle")
    public boolean mRequiresDeviceIdle;

    @ColumnInfo(name = "requires_storage_not_low")
    public boolean mRequiresStorageNotLow;

    @ColumnInfo(name = "trigger_content_update_delay")
    public long mTriggerContentUpdateDelay;

    @ColumnInfo(name = "trigger_max_content_delay")
    public long mTriggerMaxContentDelay;

    public static final class Builder {
        public ContentUriTriggers mContentUriTriggers;
        public NetworkType mRequiredNetworkType;
        public boolean mRequiresBatteryNotLow;
        public boolean mRequiresCharging;
        public boolean mRequiresDeviceIdle;
        public boolean mRequiresStorageNotLow;
        public long mTriggerContentMaxDelay;
        public long mTriggerContentUpdateDelay;

        public Builder() {
            this.mRequiresCharging = false;
            this.mRequiresDeviceIdle = false;
            this.mRequiredNetworkType = NetworkType.NOT_REQUIRED;
            this.mRequiresBatteryNotLow = false;
            this.mRequiresStorageNotLow = false;
            this.mTriggerContentUpdateDelay = -1L;
            this.mTriggerContentMaxDelay = -1L;
            this.mContentUriTriggers = new ContentUriTriggers();
        }

        @NonNull
        @RequiresApi(24)
        public Builder addContentUriTrigger(@NonNull Uri uri, boolean triggerForDescendants) {
            this.mContentUriTriggers.add(uri, triggerForDescendants);
            return this;
        }

        @NonNull
        public Constraints build() {
            return new Constraints(this);
        }

        @NonNull
        public Builder setRequiredNetworkType(@NonNull NetworkType networkType) {
            this.mRequiredNetworkType = networkType;
            return this;
        }

        @NonNull
        public Builder setRequiresBatteryNotLow(boolean requiresBatteryNotLow) {
            this.mRequiresBatteryNotLow = requiresBatteryNotLow;
            return this;
        }

        @NonNull
        public Builder setRequiresCharging(boolean requiresCharging) {
            this.mRequiresCharging = requiresCharging;
            return this;
        }

        @NonNull
        @RequiresApi(23)
        public Builder setRequiresDeviceIdle(boolean requiresDeviceIdle) {
            this.mRequiresDeviceIdle = requiresDeviceIdle;
            return this;
        }

        @NonNull
        public Builder setRequiresStorageNotLow(boolean requiresStorageNotLow) {
            this.mRequiresStorageNotLow = requiresStorageNotLow;
            return this;
        }

        @NonNull
        @RequiresApi(24)
        public Builder setTriggerContentMaxDelay(long duration, @NonNull TimeUnit timeUnit) {
            this.mTriggerContentMaxDelay = timeUnit.toMillis(duration);
            return this;
        }

        @NonNull
        @RequiresApi(24)
        public Builder setTriggerContentUpdateDelay(long duration, @NonNull TimeUnit timeUnit) {
            this.mTriggerContentUpdateDelay = timeUnit.toMillis(duration);
            return this;
        }

        @NonNull
        @RequiresApi(26)
        public Builder setTriggerContentMaxDelay(Duration duration) {
            this.mTriggerContentMaxDelay = duration.toMillis();
            return this;
        }

        @NonNull
        @RequiresApi(26)
        public Builder setTriggerContentUpdateDelay(Duration duration) {
            this.mTriggerContentUpdateDelay = duration.toMillis();
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder(@NonNull Constraints constraints) {
            this.mRequiresCharging = false;
            this.mRequiresDeviceIdle = false;
            this.mRequiredNetworkType = NetworkType.NOT_REQUIRED;
            this.mRequiresBatteryNotLow = false;
            this.mRequiresStorageNotLow = false;
            this.mTriggerContentUpdateDelay = -1L;
            this.mTriggerContentMaxDelay = -1L;
            this.mContentUriTriggers = new ContentUriTriggers();
            this.mRequiresCharging = constraints.requiresCharging();
            int i = Build.VERSION.SDK_INT;
            this.mRequiresDeviceIdle = constraints.requiresDeviceIdle();
            this.mRequiredNetworkType = constraints.getRequiredNetworkType();
            this.mRequiresBatteryNotLow = constraints.requiresBatteryNotLow();
            this.mRequiresStorageNotLow = constraints.requiresStorageNotLow();
            int i2 = Build.VERSION.SDK_INT;
            this.mTriggerContentUpdateDelay = constraints.getTriggerContentUpdateDelay();
            this.mTriggerContentMaxDelay = constraints.getTriggerMaxContentDelay();
            this.mContentUriTriggers = constraints.getContentUriTriggers();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Constraints() {
        this.mRequiredNetworkType = NetworkType.NOT_REQUIRED;
        this.mTriggerContentUpdateDelay = -1L;
        this.mTriggerMaxContentDelay = -1L;
        this.mContentUriTriggers = new ContentUriTriggers();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Constraints.class != o.getClass()) {
            return false;
        }
        Constraints constraints = (Constraints) o;
        if (this.mRequiresCharging == constraints.mRequiresCharging && this.mRequiresDeviceIdle == constraints.mRequiresDeviceIdle && this.mRequiresBatteryNotLow == constraints.mRequiresBatteryNotLow && this.mRequiresStorageNotLow == constraints.mRequiresStorageNotLow && this.mTriggerContentUpdateDelay == constraints.mTriggerContentUpdateDelay && this.mTriggerMaxContentDelay == constraints.mTriggerMaxContentDelay && this.mRequiredNetworkType == constraints.mRequiredNetworkType) {
            return this.mContentUriTriggers.equals(constraints.mContentUriTriggers);
        }
        return false;
    }

    @NonNull
    @RequiresApi(24)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ContentUriTriggers getContentUriTriggers() {
        return this.mContentUriTriggers;
    }

    @NonNull
    public NetworkType getRequiredNetworkType() {
        return this.mRequiredNetworkType;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public long getTriggerContentUpdateDelay() {
        return this.mTriggerContentUpdateDelay;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public long getTriggerMaxContentDelay() {
        return this.mTriggerMaxContentDelay;
    }

    @RequiresApi(24)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean hasContentUriTriggers() {
        return this.mContentUriTriggers.size() > 0;
    }

    public int hashCode() {
        int iHashCode = ((((((((this.mRequiredNetworkType.hashCode() * 31) + (this.mRequiresCharging ? 1 : 0)) * 31) + (this.mRequiresDeviceIdle ? 1 : 0)) * 31) + (this.mRequiresBatteryNotLow ? 1 : 0)) * 31) + (this.mRequiresStorageNotLow ? 1 : 0)) * 31;
        long j = this.mTriggerContentUpdateDelay;
        int i = (iHashCode + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.mTriggerMaxContentDelay;
        return this.mContentUriTriggers.hashCode() + ((i + ((int) (j2 ^ (j2 >>> 32)))) * 31);
    }

    public boolean requiresBatteryNotLow() {
        return this.mRequiresBatteryNotLow;
    }

    public boolean requiresCharging() {
        return this.mRequiresCharging;
    }

    @RequiresApi(23)
    public boolean requiresDeviceIdle() {
        return this.mRequiresDeviceIdle;
    }

    public boolean requiresStorageNotLow() {
        return this.mRequiresStorageNotLow;
    }

    @RequiresApi(24)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setContentUriTriggers(@Nullable ContentUriTriggers mContentUriTriggers) {
        this.mContentUriTriggers = mContentUriTriggers;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setRequiredNetworkType(@NonNull NetworkType requiredNetworkType) {
        this.mRequiredNetworkType = requiredNetworkType;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setRequiresBatteryNotLow(boolean requiresBatteryNotLow) {
        this.mRequiresBatteryNotLow = requiresBatteryNotLow;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setRequiresCharging(boolean requiresCharging) {
        this.mRequiresCharging = requiresCharging;
    }

    @RequiresApi(23)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setRequiresDeviceIdle(boolean requiresDeviceIdle) {
        this.mRequiresDeviceIdle = requiresDeviceIdle;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setRequiresStorageNotLow(boolean requiresStorageNotLow) {
        this.mRequiresStorageNotLow = requiresStorageNotLow;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setTriggerContentUpdateDelay(long triggerContentUpdateDelay) {
        this.mTriggerContentUpdateDelay = triggerContentUpdateDelay;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setTriggerMaxContentDelay(long triggerMaxContentDelay) {
        this.mTriggerMaxContentDelay = triggerMaxContentDelay;
    }

    public Constraints(Builder builder) {
        this.mRequiredNetworkType = NetworkType.NOT_REQUIRED;
        this.mTriggerContentUpdateDelay = -1L;
        this.mTriggerMaxContentDelay = -1L;
        this.mContentUriTriggers = new ContentUriTriggers();
        this.mRequiresCharging = builder.mRequiresCharging;
        int i = Build.VERSION.SDK_INT;
        this.mRequiresDeviceIdle = builder.mRequiresDeviceIdle;
        this.mRequiredNetworkType = builder.mRequiredNetworkType;
        this.mRequiresBatteryNotLow = builder.mRequiresBatteryNotLow;
        this.mRequiresStorageNotLow = builder.mRequiresStorageNotLow;
        int i2 = Build.VERSION.SDK_INT;
        this.mContentUriTriggers = builder.mContentUriTriggers;
        this.mTriggerContentUpdateDelay = builder.mTriggerContentUpdateDelay;
        this.mTriggerMaxContentDelay = builder.mTriggerContentMaxDelay;
    }

    public Constraints(@NonNull Constraints other) {
        this.mRequiredNetworkType = NetworkType.NOT_REQUIRED;
        this.mTriggerContentUpdateDelay = -1L;
        this.mTriggerMaxContentDelay = -1L;
        this.mContentUriTriggers = new ContentUriTriggers();
        this.mRequiresCharging = other.mRequiresCharging;
        this.mRequiresDeviceIdle = other.mRequiresDeviceIdle;
        this.mRequiredNetworkType = other.mRequiredNetworkType;
        this.mRequiresBatteryNotLow = other.mRequiresBatteryNotLow;
        this.mRequiresStorageNotLow = other.mRequiresStorageNotLow;
        this.mContentUriTriggers = other.mContentUriTriggers;
    }
}
