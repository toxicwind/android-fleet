package androidx.work;

import android.app.Notification;
import androidx.annotation.NonNull;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class ForegroundInfo {
    public final int mForegroundServiceType;
    public final Notification mNotification;
    public final int mNotificationId;

    public ForegroundInfo(int notificationId, @NonNull Notification notification) {
        this.mNotificationId = notificationId;
        this.mNotification = notification;
        this.mForegroundServiceType = 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || ForegroundInfo.class != o.getClass()) {
            return false;
        }
        ForegroundInfo foregroundInfo = (ForegroundInfo) o;
        if (this.mNotificationId == foregroundInfo.mNotificationId && this.mForegroundServiceType == foregroundInfo.mForegroundServiceType) {
            return this.mNotification.equals(foregroundInfo.mNotification);
        }
        return false;
    }

    public int getForegroundServiceType() {
        return this.mForegroundServiceType;
    }

    @NonNull
    public Notification getNotification() {
        return this.mNotification;
    }

    public int getNotificationId() {
        return this.mNotificationId;
    }

    public int hashCode() {
        return this.mNotification.hashCode() + (((this.mNotificationId * 31) + this.mForegroundServiceType) * 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ForegroundInfo{");
        sb.append("mNotificationId=");
        sb.append(this.mNotificationId);
        sb.append(", mForegroundServiceType=");
        sb.append(this.mForegroundServiceType);
        sb.append(", mNotification=");
        return a.a(sb, (Object) this.mNotification, '}');
    }

    public ForegroundInfo(int notificationId, @NonNull Notification notification, int foregroundServiceType) {
        this.mNotificationId = notificationId;
        this.mNotification = notification;
        this.mForegroundServiceType = foregroundServiceType;
    }
}
