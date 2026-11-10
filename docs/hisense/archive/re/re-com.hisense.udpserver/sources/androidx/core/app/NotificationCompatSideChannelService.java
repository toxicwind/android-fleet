package androidx.core.app;

import a.a.a.a.a;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/* JADX INFO: loaded from: classes.dex */
public abstract class NotificationCompatSideChannelService extends Service {

    public class NotificationSideChannelStub extends a.AbstractBinderC0000a {
        public NotificationSideChannelStub() {
        }

        @Override // a.a.a.a.a
        public void cancel(String str, int i, String str2) {
            NotificationCompatSideChannelService.this.checkPermission(Binder.getCallingUid(), str);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                NotificationCompatSideChannelService.this.cancel(str, i, str2);
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        @Override // a.a.a.a.a
        public void cancelAll(String str) {
            NotificationCompatSideChannelService.this.checkPermission(Binder.getCallingUid(), str);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                NotificationCompatSideChannelService.this.cancelAll(str);
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        @Override // a.a.a.a.a
        public void notify(String str, int i, String str2, Notification notification) {
            NotificationCompatSideChannelService.this.checkPermission(Binder.getCallingUid(), str);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                NotificationCompatSideChannelService.this.notify(str, i, str2, notification);
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public abstract void cancel(String str, int i, String str2);

    public abstract void cancelAll(String str);

    public void checkPermission(int i, String str) {
        for (String str2 : getPackageManager().getPackagesForUid(i)) {
            if (str2.equals(str)) {
                return;
            }
        }
        throw new SecurityException("NotificationSideChannelService: Uid " + i + " is not authorized for package " + str);
    }

    public abstract void notify(String str, int i, String str2, Notification notification);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (intent.getAction().equals(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL)) {
        }
        return null;
    }
}
