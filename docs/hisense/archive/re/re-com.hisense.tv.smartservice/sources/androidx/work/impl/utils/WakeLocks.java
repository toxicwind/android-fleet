package androidx.work.impl.utils;

import android.content.Context;
import android.os.PowerManager;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.Logger;
import b.a.a.a.a;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class WakeLocks {
    public static final String TAG = Logger.tagWithPrefix("WakeLocks");
    public static final WeakHashMap<PowerManager.WakeLock, String> sWakeLocks = new WeakHashMap<>();

    public static void checkWakeLocks() {
        HashMap map = new HashMap();
        synchronized (sWakeLocks) {
            map.putAll(sWakeLocks);
        }
        for (PowerManager.WakeLock wakeLock : map.keySet()) {
            if (wakeLock != null && wakeLock.isHeld()) {
                Logger.get().warning(TAG, String.format("WakeLock held for %s", map.get(wakeLock)), new Throwable[0]);
            }
        }
    }

    public static PowerManager.WakeLock newWakeLock(@NonNull Context context, @NonNull String tag) {
        PowerManager powerManager = (PowerManager) context.getApplicationContext().getSystemService("power");
        String strA = a.a("WorkManager: ", tag);
        PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(1, strA);
        synchronized (sWakeLocks) {
            sWakeLocks.put(wakeLockNewWakeLock, strA);
        }
        return wakeLockNewWakeLock;
    }
}
