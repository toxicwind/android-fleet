package com.hisense.tv.ota;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class SysUpgradeService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private C0016q f22a;
    BroadcastReceiver b = new C0018s(this);

    private void b() {
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(new NotificationChannel("VTOTA", "OTA_Service", 0));
            startForeground(1, new Notification.Builder(this, "VTOTA").setContentText("OTA").setContentTitle("OTAservice").setSmallIcon(R.drawable.ic_launcher).build());
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.i("SysUpgradeService", "onBind");
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        Log.i("SysUpgradeService", "onCreate");
        super.onCreate();
        this.f22a = C0016q.t(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("eula.ota.message");
        intentFilter.addAction("com.hisense.fte.BringMsg");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        registerReceiver(this.b, intentFilter);
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.i("SysUpgradeService", "onDestroy");
        stopForeground(true);
        super.onDestroy();
        unregisterReceiver(this.b);
        System.exit(0);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Log.i("SysUpgradeService", "on start command");
        if (intent == null) {
            return 2;
        }
        boolean booleanExtra = intent.getBooleanExtra("auto_check", false);
        boolean booleanExtra2 = intent.getBooleanExtra("is_str_boot", false);
        boolean booleanExtra3 = intent.getBooleanExtra("is_screen_off", false);
        boolean booleanExtra4 = intent.getBooleanExtra("is_agent_bring", false);
        boolean booleanExtra5 = intent.getBooleanExtra("is_fte_bring", false);
        if (Build.VERSION.SDK_INT >= 26) {
            b();
        }
        this.f22a.H(booleanExtra, booleanExtra2, booleanExtra3, booleanExtra4, booleanExtra5);
        return 2;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Log.i("SysUpgradeService", "onUnbind");
        return super.onUnbind(intent);
    }
}
