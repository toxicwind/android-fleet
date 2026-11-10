package com.hisense.tv.ota.W;

import android.content.Context;
import android.os.PowerManager;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class n {
    private static volatile n c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f31a;
    private PowerManager.WakeLock b = null;

    private n(Context context) {
        this.f31a = context.getApplicationContext();
    }

    public static n b(Context context) {
        if (c == null) {
            synchronized (n.class) {
                if (c == null) {
                    c = new n(context);
                }
            }
        }
        return c;
    }

    public synchronized void a() {
        String str;
        String str2;
        try {
            if (this.b == null) {
                PowerManager powerManager = (PowerManager) this.f31a.getSystemService("power");
                Log.d("OTAWakelockManager", "start acquireWakeLock");
                PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(1, "com.hisense.tv.ota:WakelockManager");
                this.b = wakeLockNewWakeLock;
                if (wakeLockNewWakeLock != null) {
                    wakeLockNewWakeLock.acquire();
                    str = "OTAWakelockManager";
                    str2 = "acquireWakeLock, mWakeLock = " + this.b;
                    Log.d(str, str2);
                }
            } else if (this.b.isHeld()) {
                Log.i("OTAWakelockManager", "mWakeLock is held! " + this.b);
            } else {
                this.b.acquire();
                str = "OTAWakelockManager";
                str2 = "mWakeLock is not held, acquireWakeLock, mWakeLock = " + this.b;
                Log.d(str, str2);
            }
        } catch (Exception e) {
            Log.e("OTAWakelockManager", e.toString());
        }
    }

    public synchronized void c() {
        String str;
        String str2;
        if (this.b != null) {
            Log.d("OTAWakelockManager", "mWakeLock is not null,start release WakeLock");
            if (this.b.isHeld()) {
                this.b.release();
                Log.d("OTAWakelockManager", "released mWakeLock,mWakeLock=" + this.b);
            } else {
                str = "OTAWakelockManager";
                str2 = "mWakeLock is not held! " + this.b;
            }
        } else {
            str = "OTAWakelockManager";
            str2 = "mWakelock is null, no need release";
        }
        Log.i(str, str2);
    }
}
