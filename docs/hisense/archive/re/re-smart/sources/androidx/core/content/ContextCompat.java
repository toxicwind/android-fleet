package androidx.core.content;

import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.app.WallpaperManager;
import android.app.admin.DevicePolicyManager;
import android.app.job.JobScheduler;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.RestrictionsManager;
import android.content.pm.LauncherApps;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.tv.TvInputManager;
import android.net.ConnectivityManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Process;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.print.PrintManager;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textservice.TextServicesManager;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DoNotInline;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.os.ExecutorCompat;
import com.google.common.net.MediaType;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class ContextCompat {
    public static final String TAG = "ContextCompat";
    public static final Object sLock = new Object();
    public static final Object sSync = new Object();
    public static TypedValue sTempValue;

    @RequiresApi(16)
    static class Api16Impl {
        @DoNotInline
        public static void startActivities(Context obj, Intent[] intents, Bundle options) {
            obj.startActivities(intents, options);
        }

        @DoNotInline
        public static void startActivity(Context obj, Intent intent, Bundle options) {
            obj.startActivity(intent, options);
        }
    }

    @RequiresApi(19)
    static class Api19Impl {
        @DoNotInline
        public static File[] getExternalCacheDirs(Context obj) {
            return obj.getExternalCacheDirs();
        }

        @DoNotInline
        public static File[] getExternalFilesDirs(Context obj, String type) {
            return obj.getExternalFilesDirs(type);
        }

        @DoNotInline
        public static File[] getObbDirs(Context obj) {
            return obj.getObbDirs();
        }
    }

    @RequiresApi(21)
    static class Api21Impl {
        @DoNotInline
        public static File getCodeCacheDir(Context obj) {
            return obj.getCodeCacheDir();
        }

        @DoNotInline
        public static Drawable getDrawable(Context obj, int id) {
            return obj.getDrawable(id);
        }

        @DoNotInline
        public static File getNoBackupFilesDir(Context obj) {
            return obj.getNoBackupFilesDir();
        }
    }

    @RequiresApi(23)
    static class Api23Impl {
        @DoNotInline
        public static int getColor(Context obj, int id) {
            return obj.getColor(id);
        }

        @DoNotInline
        public static ColorStateList getColorStateList(Context obj, int id) {
            return obj.getColorStateList(id);
        }

        @DoNotInline
        public static <T> T getSystemService(Context context, Class<T> cls) {
            return (T) context.getSystemService(cls);
        }

        @DoNotInline
        public static String getSystemServiceName(Context obj, Class<?> serviceClass) {
            return obj.getSystemServiceName(serviceClass);
        }
    }

    @RequiresApi(24)
    static class Api24Impl {
        @DoNotInline
        public static Context createDeviceProtectedStorageContext(Context obj) {
            return obj.createDeviceProtectedStorageContext();
        }

        @DoNotInline
        public static File getDataDir(Context obj) {
            return obj.getDataDir();
        }

        @DoNotInline
        public static boolean isDeviceProtectedStorage(Context obj) {
            return obj.isDeviceProtectedStorage();
        }
    }

    @RequiresApi(26)
    static class Api26Impl {
        @DoNotInline
        public static ComponentName startForegroundService(Context obj, Intent service) {
            return obj.startForegroundService(service);
        }
    }

    @RequiresApi(28)
    static class Api28Impl {
        @DoNotInline
        public static Executor getMainExecutor(Context obj) {
            return obj.getMainExecutor();
        }
    }

    @RequiresApi(30)
    static class Api30Impl {
        @DoNotInline
        public static String getAttributionTag(Context obj) {
            return obj.getAttributionTag();
        }
    }

    private static final class LegacyServiceMapHolder {
        public static final HashMap<Class<?>, String> SERVICES = new HashMap<>();

        static {
            int i = Build.VERSION.SDK_INT;
            SERVICES.put(SubscriptionManager.class, "telephony_subscription_service");
            SERVICES.put(UsageStatsManager.class, "usagestats");
            int i2 = Build.VERSION.SDK_INT;
            SERVICES.put(AppWidgetManager.class, "appwidget");
            SERVICES.put(BatteryManager.class, "batterymanager");
            SERVICES.put(CameraManager.class, "camera");
            SERVICES.put(JobScheduler.class, "jobscheduler");
            SERVICES.put(LauncherApps.class, "launcherapps");
            SERVICES.put(MediaProjectionManager.class, "media_projection");
            SERVICES.put(MediaSessionManager.class, "media_session");
            SERVICES.put(RestrictionsManager.class, "restrictions");
            SERVICES.put(TelecomManager.class, "telecom");
            SERVICES.put(TvInputManager.class, "tv_input");
            int i3 = Build.VERSION.SDK_INT;
            SERVICES.put(AppOpsManager.class, "appops");
            SERVICES.put(CaptioningManager.class, "captioning");
            SERVICES.put(ConsumerIrManager.class, "consumer_ir");
            SERVICES.put(PrintManager.class, "print");
            int i4 = Build.VERSION.SDK_INT;
            SERVICES.put(BluetoothManager.class, "bluetooth");
            int i5 = Build.VERSION.SDK_INT;
            SERVICES.put(DisplayManager.class, "display");
            SERVICES.put(UserManager.class, "user");
            int i6 = Build.VERSION.SDK_INT;
            SERVICES.put(InputManager.class, "input");
            SERVICES.put(MediaRouter.class, "media_router");
            SERVICES.put(NsdManager.class, "servicediscovery");
            SERVICES.put(AccessibilityManager.class, "accessibility");
            SERVICES.put(AccountManager.class, "account");
            SERVICES.put(ActivityManager.class, "activity");
            SERVICES.put(AlarmManager.class, NotificationCompat.CATEGORY_ALARM);
            SERVICES.put(AudioManager.class, MediaType.AUDIO_TYPE);
            SERVICES.put(ClipboardManager.class, "clipboard");
            SERVICES.put(ConnectivityManager.class, "connectivity");
            SERVICES.put(DevicePolicyManager.class, "device_policy");
            SERVICES.put(DownloadManager.class, "download");
            SERVICES.put(DropBoxManager.class, "dropbox");
            SERVICES.put(InputMethodManager.class, "input_method");
            SERVICES.put(KeyguardManager.class, "keyguard");
            SERVICES.put(LayoutInflater.class, "layout_inflater");
            SERVICES.put(LocationManager.class, FirebaseAnalytics.Param.LOCATION);
            SERVICES.put(NfcManager.class, "nfc");
            SERVICES.put(NotificationManager.class, "notification");
            SERVICES.put(PowerManager.class, "power");
            SERVICES.put(SearchManager.class, FirebaseAnalytics.Event.SEARCH);
            SERVICES.put(SensorManager.class, "sensor");
            SERVICES.put(StorageManager.class, "storage");
            SERVICES.put(TelephonyManager.class, "phone");
            SERVICES.put(TextServicesManager.class, "textservices");
            SERVICES.put(UiModeManager.class, "uimode");
            SERVICES.put(UsbManager.class, "usb");
            SERVICES.put(Vibrator.class, "vibrator");
            SERVICES.put(WallpaperManager.class, "wallpaper");
            SERVICES.put(WifiP2pManager.class, "wifip2p");
            SERVICES.put(WifiManager.class, "wifi");
            SERVICES.put(WindowManager.class, "window");
        }
    }

    public static int checkSelfPermission(@NonNull Context context, @NonNull String permission) {
        if (permission != null) {
            return context.checkPermission(permission, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }

    @Nullable
    public static Context createDeviceProtectedStorageContext(@NonNull Context context) {
        int i = Build.VERSION.SDK_INT;
        return context.createDeviceProtectedStorageContext();
    }

    public static File createFilesDir(File file) {
        synchronized (sSync) {
            if (!file.exists()) {
                if (file.mkdirs()) {
                    return file;
                }
                Log.w(TAG, "Unable to create files subdir " + file.getPath());
            }
            return file;
        }
    }

    @Nullable
    public static String getAttributionTag(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 30) {
            return context.getAttributionTag();
        }
        return null;
    }

    public static File getCodeCacheDir(@NonNull Context context) {
        int i = Build.VERSION.SDK_INT;
        return context.getCodeCacheDir();
    }

    @ColorInt
    public static int getColor(@NonNull Context context, @ColorRes int id) {
        int i = Build.VERSION.SDK_INT;
        return context.getColor(id);
    }

    @Nullable
    public static ColorStateList getColorStateList(@NonNull Context context, @ColorRes int id) {
        return ResourcesCompat.getColorStateList(context.getResources(), id, context.getTheme());
    }

    @Nullable
    public static File getDataDir(@NonNull Context context) {
        int i = Build.VERSION.SDK_INT;
        return context.getDataDir();
    }

    @Nullable
    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int id) {
        int i = Build.VERSION.SDK_INT;
        return context.getDrawable(id);
    }

    @NonNull
    public static File[] getExternalCacheDirs(@NonNull Context context) {
        int i = Build.VERSION.SDK_INT;
        return context.getExternalCacheDirs();
    }

    @NonNull
    public static File[] getExternalFilesDirs(@NonNull Context context, @Nullable String type) {
        int i = Build.VERSION.SDK_INT;
        return context.getExternalFilesDirs(type);
    }

    public static Executor getMainExecutor(Context context) {
        return Build.VERSION.SDK_INT >= 28 ? context.getMainExecutor() : new ExecutorCompat.HandlerExecutor(new Handler(context.getMainLooper()));
    }

    @Nullable
    public static File getNoBackupFilesDir(@NonNull Context context) {
        int i = Build.VERSION.SDK_INT;
        return context.getNoBackupFilesDir();
    }

    @NonNull
    public static File[] getObbDirs(@NonNull Context context) {
        int i = Build.VERSION.SDK_INT;
        return context.getObbDirs();
    }

    @Nullable
    public static <T> T getSystemService(@NonNull Context context, @NonNull Class<T> cls) {
        int i = Build.VERSION.SDK_INT;
        return (T) context.getSystemService(cls);
    }

    @Nullable
    public static String getSystemServiceName(@NonNull Context context, @NonNull Class<?> serviceClass) {
        int i = Build.VERSION.SDK_INT;
        return context.getSystemServiceName(serviceClass);
    }

    public static boolean isDeviceProtectedStorage(@NonNull Context context) {
        int i = Build.VERSION.SDK_INT;
        return context.isDeviceProtectedStorage();
    }

    public static boolean startActivities(@NonNull Context context, @NonNull Intent[] intents) {
        startActivities(context, intents, null);
        return true;
    }

    public static void startActivity(@NonNull Context context, @NonNull Intent intent, @Nullable Bundle options) {
        int i = Build.VERSION.SDK_INT;
        context.startActivity(intent, options);
    }

    public static void startForegroundService(@NonNull Context context, @NonNull Intent intent) {
        int i = Build.VERSION.SDK_INT;
        context.startForegroundService(intent);
    }

    public static boolean startActivities(@NonNull Context context, @NonNull Intent[] intents, @Nullable Bundle options) {
        int i = Build.VERSION.SDK_INT;
        context.startActivities(intents, options);
        return true;
    }
}
