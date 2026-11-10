package androidx.work.impl.foreground;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.LifecycleService;
import androidx.work.Logger;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SystemForegroundService extends LifecycleService implements SystemForegroundDispatcher.Callback {
    public static final String TAG = Logger.tagWithPrefix("SystemFgService");

    @Nullable
    public static SystemForegroundService sForegroundService = null;
    public SystemForegroundDispatcher mDispatcher;
    public Handler mHandler;
    public boolean mIsShutdown;
    public NotificationManager mNotificationManager;

    @Nullable
    public static SystemForegroundService getInstance() {
        return sForegroundService;
    }

    @MainThread
    private void initializeDispatcher() {
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mNotificationManager = (NotificationManager) getApplicationContext().getSystemService("notification");
        this.mDispatcher = new SystemForegroundDispatcher(getApplicationContext());
        this.mDispatcher.setCallback(this);
    }

    @Override // androidx.work.impl.foreground.SystemForegroundDispatcher.Callback
    public void cancelNotification(final int notificationId) {
        this.mHandler.post(new Runnable() { // from class: androidx.work.impl.foreground.SystemForegroundService.3
            @Override // java.lang.Runnable
            public void run() {
                SystemForegroundService.this.mNotificationManager.cancel(notificationId);
            }
        });
    }

    @Override // androidx.work.impl.foreground.SystemForegroundDispatcher.Callback
    public void notify(final int notificationId, @NonNull final Notification notification) {
        this.mHandler.post(new Runnable() { // from class: androidx.work.impl.foreground.SystemForegroundService.2
            @Override // java.lang.Runnable
            public void run() {
                SystemForegroundService.this.mNotificationManager.notify(notificationId, notification);
            }
        });
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public void onCreate() {
        super.onCreate();
        sForegroundService = this;
        initializeDispatcher();
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.mDispatcher.onDestroy();
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        if (this.mIsShutdown) {
            Logger.get().info(TAG, "Re-initializing SystemForegroundService after a request to shut-down.", new Throwable[0]);
            this.mDispatcher.onDestroy();
            initializeDispatcher();
            this.mIsShutdown = false;
        }
        if (intent == null) {
            return 3;
        }
        this.mDispatcher.onStartCommand(intent);
        return 3;
    }

    @Override // androidx.work.impl.foreground.SystemForegroundDispatcher.Callback
    public void startForeground(final int notificationId, final int notificationType, @NonNull final Notification notification) {
        this.mHandler.post(new Runnable() { // from class: androidx.work.impl.foreground.SystemForegroundService.1
            @Override // java.lang.Runnable
            public void run() {
                if (Build.VERSION.SDK_INT >= 29) {
                    SystemForegroundService.this.startForeground(notificationId, notification, notificationType);
                } else {
                    SystemForegroundService.this.startForeground(notificationId, notification);
                }
            }
        });
    }

    @Override // androidx.work.impl.foreground.SystemForegroundDispatcher.Callback
    @MainThread
    public void stop() {
        this.mIsShutdown = true;
        Logger.get().debug(TAG, "All commands completed.", new Throwable[0]);
        int i = Build.VERSION.SDK_INT;
        stopForeground(true);
        sForegroundService = null;
        stopSelf();
    }
}
