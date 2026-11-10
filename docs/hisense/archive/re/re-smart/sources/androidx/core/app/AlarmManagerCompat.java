package androidx.core.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Build;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public final class AlarmManagerCompat {
    public static void setAlarmClock(@NonNull AlarmManager alarmManager, long triggerTime, @NonNull PendingIntent showIntent, @NonNull PendingIntent operation) {
        int i = Build.VERSION.SDK_INT;
        alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(triggerTime, showIntent), operation);
    }

    public static void setAndAllowWhileIdle(@NonNull AlarmManager alarmManager, int type, long triggerAtMillis, @NonNull PendingIntent operation) {
        int i = Build.VERSION.SDK_INT;
        alarmManager.setAndAllowWhileIdle(type, triggerAtMillis, operation);
    }

    public static void setExact(@NonNull AlarmManager alarmManager, int type, long triggerAtMillis, @NonNull PendingIntent operation) {
        int i = Build.VERSION.SDK_INT;
        alarmManager.setExact(type, triggerAtMillis, operation);
    }

    public static void setExactAndAllowWhileIdle(@NonNull AlarmManager alarmManager, int type, long triggerAtMillis, @NonNull PendingIntent operation) {
        int i = Build.VERSION.SDK_INT;
        alarmManager.setExactAndAllowWhileIdle(type, triggerAtMillis, operation);
    }
}
