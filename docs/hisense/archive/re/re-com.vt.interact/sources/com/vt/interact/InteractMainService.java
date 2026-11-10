package com.vt.interact;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;
import com.vt.interact.manager.InteractManager;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class InteractMainService extends Service {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String ATSC_PATH = "atsc3";
    public static final int NETWORK_CONNECTED = 1;
    public static final int NETWORK_DISCONNECTED = 0;
    public static final int NETWORK_STATUS = 0;
    private static final String TAG = "Interact_Service";
    private Context mContext = null;
    SystemBroadcastReceiver mBroadcaseReceiver = null;

    @Override // android.app.Service
    public void onCreate() {
        Log.d(TAG, "[interact] enter onCreate");
        super.onCreate();
        if (InteractManager.createAtscDir(getApplicationContext().getFilesDir().getAbsolutePath() + File.separator + ATSC_PATH) == 0) {
            InteractManager.getInstance().init(getApplicationContext());
        }
        this.mBroadcaseReceiver = new SystemBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        getApplicationContext().registerReceiver(this.mBroadcaseReceiver, intentFilter);
        Log.d(TAG, "[interact] exit onCreate");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Log.d(TAG, "onStartCommand(). start interact service");
        NotificationChannel notificationChannel = new NotificationChannel(BuildConfig.APPLICATION_ID, "HATSC3InteractService", 0);
        notificationChannel.setLightColor(-16776961);
        notificationChannel.setLockscreenVisibility(0);
        ((NotificationManager) getSystemService("notification")).createNotificationChannel(notificationChannel);
        startForeground(1, new Notification.Builder(getApplicationContext(), BuildConfig.APPLICATION_ID).setContentText("HATSC3InteractService").setSmallIcon(R.mipmap.ic_launcher).setWhen(System.currentTimeMillis()).build());
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.d(TAG, "[interact] onDestroy");
        super.onDestroy();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "[AntPlayer] onBind(). return new BnInteractServer()");
        return InteractManager.getInstance().onBind(intent);
    }

    public class SystemBroadcastReceiver extends BroadcastReceiver {
        private final String powerOff = "android.intent.action.SCREEN_OFF";
        private final String powerOn = "android.intent.action.SCREEN_ON";

        public SystemBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(InteractMainService.TAG, "action = " + action);
            if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                if (InteractMainService.isNetworkConnected(context)) {
                    InteractManager.getInstance().notifyNetworkState(1);
                } else {
                    InteractManager.getInstance().notifyNetworkState(0);
                }
            }
        }
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }
}
