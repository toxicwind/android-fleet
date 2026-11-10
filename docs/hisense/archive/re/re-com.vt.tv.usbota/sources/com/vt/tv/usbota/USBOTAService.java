package com.vt.tv.usbota;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class USBOTAService extends Service {
    private l a;

    private void a() {
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(new NotificationChannel("HisUSBOTA", "USBOTA_Service", 0));
            startForeground(1, new Notification.Builder(this, "HisUSBOTA").setContentText("USBOTA").setContentTitle("HisUSBOTAservice").setSmallIcon(R.drawable.ic_launcher).build());
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.i("USBOTAService", "onBind");
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        Log.i("USBOTAService", "onCreate");
        super.onCreate();
        this.a = l.k(this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.i("USBOTAService", "onDestroy");
        stopForeground(true);
        super.onDestroy();
        Log.i("USBOTAService", "system exit");
        System.exit(0);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Log.i("USBOTAService", "on start command");
        if (intent == null) {
            return 2;
        }
        boolean booleanExtra = intent.getBooleanExtra("ota_from_usb", false);
        if (Build.VERSION.SDK_INT >= 26) {
            a();
        }
        this.a.s(booleanExtra);
        return 2;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Log.i("USBOTAService", "onUnbind");
        return super.onUnbind(intent);
    }
}
