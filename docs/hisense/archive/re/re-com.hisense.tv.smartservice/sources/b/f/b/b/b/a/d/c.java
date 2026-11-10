package b.f.b.b.b.a.d;

import android.content.Context;
import android.os.PowerManager;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile c f700a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f701b = "c";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f702c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PowerManager.WakeLock f703d = null;

    public c(Context context) {
        this.f702c = context.getApplicationContext();
    }

    public static c a(Context context) {
        if (f700a == null) {
            synchronized (c.class) {
                if (f700a == null) {
                    f700a = new c(context);
                }
            }
        }
        return f700a;
    }

    public synchronized void a(long j) {
        try {
            if (this.f703d == null) {
                PowerManager powerManager = (PowerManager) this.f702c.getSystemService("power");
                Log.d(f701b, "start acquireWakeLock");
                this.f703d = powerManager.newWakeLock(1, "com.hisense.tv.smartsense:module.emmc.utils.WakelockManager");
                if (this.f703d != null) {
                    this.f703d.acquire(j);
                    Log.d(f701b, "acquireWakeLock, mWakeLock = " + this.f703d);
                }
            } else if (!this.f703d.isHeld()) {
                this.f703d.acquire(j);
                Log.d(f701b, "mWakeLock is not held, acquireWakeLock, mWakeLock = " + this.f703d);
            } else {
                Log.i(f701b, "mWakeLock is held! " + this.f703d);
            }
        } catch (Exception e2) {
            Log.e(f701b, e2.toString());
        }
    }
}
