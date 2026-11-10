package com.hisense.tv.ota;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class SysUpgradeNotifyService extends Service {
    public void a() {
        Log.i("SysUpgradeNotifyService", "NotificationService ShowNotification!!!");
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService("notification");
        if (notificationManager.getNotificationChannel("OTANotificationService") == null) {
            notificationManager.createNotificationChannel(new NotificationChannel("OTANotificationService", "OTANotificationService", 4));
        }
        Log.i("SysUpgradeNotifyService", "NotificationService createNotificationChannel!!!");
        Intent intent = new Intent(this, (Class<?>) SysUpgradeActivity.class);
        intent.setFlags(268468224);
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 134217728);
        Log.i("SysUpgradeNotifyService", "Notification.Builder in ");
        Notification notificationBuild = new Notification.Builder(getApplicationContext(), "OTANotificationService").setSmallIcon(R.drawable.information).setWhen(System.currentTimeMillis()).setOnlyAlertOnce(true).setPriority(2).setAutoCancel(true).setDefaults(0).setContentTitle(getString(R.string.sys_upgrade)).setContentText(getString(R.string.new_version_detected)).setContentIntent(activity).setVisibility(1).extend(new Notification.TvExtender().setChannelId("OTANotificationService")).build();
        Log.i("SysUpgradeNotifyService", "Notification.Builder done");
        notificationManager.notify((int) (SystemClock.uptimeMillis() / 1000), notificationBuild);
        Log.i("SysUpgradeNotifyService", "notificationManager.notify done id " + ((int) (SystemClock.uptimeMillis() / 1000)) + " NOTIFICATION_CHANNEL_ID OTANotificationService");
    }

    public void b() {
        new Thread(new RunnableC0017r(this)).start();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.i("SysUpgradeNotifyService", "NotificationService onCreate!!!");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        b();
        return super.onStartCommand(intent, i, i2);
    }
}
