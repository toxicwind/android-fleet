package androidx.core.app;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.INotificationSideChannel;

/* JADX INFO: loaded from: classes.dex */
public abstract class NotificationCompatSideChannelService extends Service {

    private class NotificationSideChannelStub extends INotificationSideChannel.Stub {
        public NotificationSideChannelStub() {
        }

        @Override // android.support.v4.app.INotificationSideChannel
        public void cancel(String packageName, int id, String tag) {
            NotificationCompatSideChannelService.this.checkPermission(Binder.getCallingUid(), packageName);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                NotificationCompatSideChannelService.this.cancel(packageName, id, tag);
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        @Override // android.support.v4.app.INotificationSideChannel
        public void cancelAll(String packageName) {
            NotificationCompatSideChannelService.this.checkPermission(Binder.getCallingUid(), packageName);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                NotificationCompatSideChannelService.this.cancelAll(packageName);
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        @Override // android.support.v4.app.INotificationSideChannel
        public void notify(String packageName, int id, String tag, Notification notification) {
            NotificationCompatSideChannelService.this.checkPermission(Binder.getCallingUid(), packageName);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                NotificationCompatSideChannelService.this.notify(packageName, id, tag, notification);
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public abstract void cancel(String packageName, int id, String tag);

    public abstract void cancelAll(String packageName);

    public void checkPermission(int callingUid, String packageName) {
        for (String str : getPackageManager().getPackagesForUid(callingUid)) {
            if (str.equals(packageName)) {
                return;
            }
        }
        throw new SecurityException("NotificationSideChannelService: Uid " + callingUid + " is not authorized for package " + packageName);
    }

    public abstract void notify(String packageName, int id, String tag, Notification notification);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (intent.getAction().equals(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL)) {
            int i = Build.VERSION.SDK_INT;
        }
        return null;
    }
}
