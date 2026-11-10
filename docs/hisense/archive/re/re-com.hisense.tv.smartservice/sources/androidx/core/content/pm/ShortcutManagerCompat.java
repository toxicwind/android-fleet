package androidx.core.content.pm;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.graphics.drawable.IconCompat;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ShortcutManagerCompat {

    @VisibleForTesting
    public static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    public static final int DEFAULT_MAX_ICON_DIMENSION_DP = 96;
    public static final int DEFAULT_MAX_ICON_DIMENSION_LOWRAM_DP = 48;
    public static final String EXTRA_SHORTCUT_ID = "android.intent.extra.shortcut.ID";
    public static final int FLAG_MATCH_CACHED = 8;
    public static final int FLAG_MATCH_DYNAMIC = 2;
    public static final int FLAG_MATCH_MANIFEST = 1;
    public static final int FLAG_MATCH_PINNED = 4;

    @VisibleForTesting
    public static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";
    public static final String SHORTCUT_LISTENER_INTENT_FILTER_ACTION = "androidx.core.content.pm.SHORTCUT_LISTENER";
    public static final String SHORTCUT_LISTENER_META_DATA_KEY = "androidx.core.content.pm.shortcut_listener_impl";
    public static volatile List<ShortcutInfoChangeListener> sShortcutInfoChangeListeners;
    public static volatile ShortcutInfoCompatSaver<?> sShortcutInfoCompatSaver;

    /* JADX INFO: renamed from: androidx.core.content.pm.ShortcutManagerCompat$1, reason: invalid class name */
    public class AnonymousClass1 extends BroadcastReceiver {
        public final /* synthetic */ IntentSender val$callback;

        public AnonymousClass1(final IntentSender val$callback) {
            this.val$callback = val$callback;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                this.val$callback.sendIntent(context, 0, null, null, null);
            } catch (IntentSender.SendIntentException unused) {
            }
        }
    }

    @RequiresApi(25)
    private static class Api25Impl {
        public static String getShortcutInfoWithLowestRank(@NonNull final List<ShortcutInfo> shortcuts) {
            int rank = -1;
            String id = null;
            for (ShortcutInfo shortcutInfo : shortcuts) {
                if (shortcutInfo.getRank() > rank) {
                    id = shortcutInfo.getId();
                    rank = shortcutInfo.getRank();
                }
            }
            return id;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface ShortcutMatchFlags {
    }

    public static boolean addDynamicShortcuts(@NonNull Context context, @NonNull List<ShortcutInfoCompat> shortcutInfoList) {
        if (Build.VERSION.SDK_INT <= 29) {
            convertUriIconsToBitmapIcons(context, shortcutInfoList);
        }
        int i = Build.VERSION.SDK_INT;
        ArrayList arrayList = new ArrayList();
        Iterator<ShortcutInfoCompat> it = shortcutInfoList.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toShortcutInfo());
        }
        if (!((ShortcutManager) context.getSystemService(ShortcutManager.class)).addDynamicShortcuts(arrayList)) {
            return false;
        }
        getShortcutInfoSaverInstance(context).addShortcuts(shortcutInfoList);
        Iterator<ShortcutInfoChangeListener> it2 = getShortcutInfoListeners(context).iterator();
        while (it2.hasNext()) {
            it2.next().onShortcutAdded(shortcutInfoList);
        }
        return true;
    }

    @VisibleForTesting
    public static boolean convertUriIconToBitmapIcon(@NonNull final Context context, @NonNull final ShortcutInfoCompat info) {
        Bitmap bitmapDecodeStream;
        IconCompat iconCompat = info.mIcon;
        if (iconCompat == null) {
            return false;
        }
        int i = iconCompat.mType;
        if (i != 6 && i != 4) {
            return true;
        }
        InputStream uriInputStream = info.mIcon.getUriInputStream(context);
        if (uriInputStream == null || (bitmapDecodeStream = BitmapFactory.decodeStream(uriInputStream)) == null) {
            return false;
        }
        info.mIcon = i == 6 ? IconCompat.createWithAdaptiveBitmap(bitmapDecodeStream) : IconCompat.createWithBitmap(bitmapDecodeStream);
        return true;
    }

    @VisibleForTesting
    public static void convertUriIconsToBitmapIcons(@NonNull final Context context, @NonNull final List<ShortcutInfoCompat> shortcutInfoList) {
        for (ShortcutInfoCompat shortcutInfoCompat : new ArrayList(shortcutInfoList)) {
            if (!convertUriIconToBitmapIcon(context, shortcutInfoCompat)) {
                shortcutInfoList.remove(shortcutInfoCompat);
            }
        }
    }

    @NonNull
    public static Intent createShortcutResultIntent(@NonNull Context context, @NonNull ShortcutInfoCompat shortcut) {
        int i = Build.VERSION.SDK_INT;
        Intent intentCreateShortcutResultIntent = ((ShortcutManager) context.getSystemService(ShortcutManager.class)).createShortcutResultIntent(shortcut.toShortcutInfo());
        if (intentCreateShortcutResultIntent == null) {
            intentCreateShortcutResultIntent = new Intent();
        }
        return shortcut.addToIntent(intentCreateShortcutResultIntent);
    }

    public static void disableShortcuts(@NonNull final Context context, @NonNull final List<String> shortcutIds, @Nullable final CharSequence disabledMessage) {
        int i = Build.VERSION.SDK_INT;
        ((ShortcutManager) context.getSystemService(ShortcutManager.class)).disableShortcuts(shortcutIds, disabledMessage);
        getShortcutInfoSaverInstance(context).removeShortcuts(shortcutIds);
        Iterator<ShortcutInfoChangeListener> it = getShortcutInfoListeners(context).iterator();
        while (it.hasNext()) {
            it.next().onShortcutRemoved(shortcutIds);
        }
    }

    public static void enableShortcuts(@NonNull final Context context, @NonNull final List<ShortcutInfoCompat> shortcutInfoList) {
        int i = Build.VERSION.SDK_INT;
        ArrayList arrayList = new ArrayList(shortcutInfoList.size());
        Iterator<ShortcutInfoCompat> it = shortcutInfoList.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().mId);
        }
        ((ShortcutManager) context.getSystemService(ShortcutManager.class)).enableShortcuts(arrayList);
        getShortcutInfoSaverInstance(context).addShortcuts(shortcutInfoList);
        Iterator<ShortcutInfoChangeListener> it2 = getShortcutInfoListeners(context).iterator();
        while (it2.hasNext()) {
            it2.next().onShortcutAdded(shortcutInfoList);
        }
    }

    @NonNull
    public static List<ShortcutInfoCompat> getDynamicShortcuts(@NonNull Context context) {
        int i = Build.VERSION.SDK_INT;
        List<ShortcutInfo> dynamicShortcuts = ((ShortcutManager) context.getSystemService(ShortcutManager.class)).getDynamicShortcuts();
        ArrayList arrayList = new ArrayList(dynamicShortcuts.size());
        Iterator<ShortcutInfo> it = dynamicShortcuts.iterator();
        while (it.hasNext()) {
            arrayList.add(new ShortcutInfoCompat.Builder(context, it.next()).build());
        }
        return arrayList;
    }

    public static int getIconDimensionInternal(@NonNull final Context context, final boolean isHorizontal) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        int i = Build.VERSION.SDK_INT;
        int iMax = Math.max(1, activityManager == null || activityManager.isLowRamDevice() ? 48 : 96);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (iMax * ((isHorizontal ? displayMetrics.xdpi : displayMetrics.ydpi) / 160.0f));
    }

    public static int getIconMaxHeight(@NonNull final Context context) {
        if (context == null) {
            throw new NullPointerException();
        }
        int i = Build.VERSION.SDK_INT;
        return ((ShortcutManager) context.getSystemService(ShortcutManager.class)).getIconMaxHeight();
    }

    public static int getIconMaxWidth(@NonNull final Context context) {
        if (context == null) {
            throw new NullPointerException();
        }
        int i = Build.VERSION.SDK_INT;
        return ((ShortcutManager) context.getSystemService(ShortcutManager.class)).getIconMaxWidth();
    }

    public static int getMaxShortcutCountPerActivity(@NonNull Context context) {
        if (context == null) {
            throw new NullPointerException();
        }
        int i = Build.VERSION.SDK_INT;
        return ((ShortcutManager) context.getSystemService(ShortcutManager.class)).getMaxShortcutCountPerActivity();
    }

    @VisibleForTesting
    public static List<ShortcutInfoChangeListener> getShortcutInfoChangeListeners() {
        return sShortcutInfoChangeListeners;
    }

    public static String getShortcutInfoCompatWithLowestRank(@NonNull final List<ShortcutInfoCompat> shortcuts) {
        int rank = -1;
        String id = null;
        for (ShortcutInfoCompat shortcutInfoCompat : shortcuts) {
            if (shortcutInfoCompat.getRank() > rank) {
                id = shortcutInfoCompat.getId();
                rank = shortcutInfoCompat.getRank();
            }
        }
        return id;
    }

    public static List<ShortcutInfoChangeListener> getShortcutInfoListeners(Context context) {
        Bundle bundle;
        String string;
        if (sShortcutInfoChangeListeners == null) {
            ArrayList arrayList = new ArrayList();
            int i = Build.VERSION.SDK_INT;
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent(SHORTCUT_LISTENER_INTENT_FILTER_ACTION);
            intent.setPackage(context.getPackageName());
            Iterator<ResolveInfo> it = packageManager.queryIntentActivities(intent, 128).iterator();
            while (it.hasNext()) {
                ActivityInfo activityInfo = it.next().activityInfo;
                if (activityInfo != null && (bundle = activityInfo.metaData) != null && (string = bundle.getString(SHORTCUT_LISTENER_META_DATA_KEY)) != null) {
                    try {
                        arrayList.add((ShortcutInfoChangeListener) Class.forName(string, false, ShortcutManagerCompat.class.getClassLoader()).getMethod("getInstance", Context.class).invoke(null, context));
                    } catch (Exception unused) {
                    }
                }
            }
            if (sShortcutInfoChangeListeners == null) {
                sShortcutInfoChangeListeners = arrayList;
            }
        }
        return sShortcutInfoChangeListeners;
    }

    public static ShortcutInfoCompatSaver<?> getShortcutInfoSaverInstance(Context context) {
        if (sShortcutInfoCompatSaver == null) {
            int i = Build.VERSION.SDK_INT;
            try {
                sShortcutInfoCompatSaver = (ShortcutInfoCompatSaver) Class.forName("androidx.sharetarget.ShortcutInfoCompatSaverImpl", false, ShortcutManagerCompat.class.getClassLoader()).getMethod("getInstance", Context.class).invoke(null, context);
            } catch (Exception unused) {
            }
            if (sShortcutInfoCompatSaver == null) {
                sShortcutInfoCompatSaver = new ShortcutInfoCompatSaver.NoopImpl();
            }
        }
        return sShortcutInfoCompatSaver;
    }

    @NonNull
    public static List<ShortcutInfoCompat> getShortcuts(@NonNull final Context context, int matchFlags) {
        if (Build.VERSION.SDK_INT >= 30) {
            return ShortcutInfoCompat.fromShortcuts(context, ((ShortcutManager) context.getSystemService(ShortcutManager.class)).getShortcuts(matchFlags));
        }
        ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(ShortcutManager.class);
        ArrayList arrayList = new ArrayList();
        if ((matchFlags & 1) != 0) {
            arrayList.addAll(shortcutManager.getManifestShortcuts());
        }
        if ((matchFlags & 2) != 0) {
            arrayList.addAll(shortcutManager.getDynamicShortcuts());
        }
        if ((matchFlags & 4) != 0) {
            arrayList.addAll(shortcutManager.getPinnedShortcuts());
        }
        return ShortcutInfoCompat.fromShortcuts(context, arrayList);
    }

    public static boolean isRateLimitingActive(@NonNull final Context context) {
        if (context == null) {
            throw new NullPointerException();
        }
        int i = Build.VERSION.SDK_INT;
        return ((ShortcutManager) context.getSystemService(ShortcutManager.class)).isRateLimitingActive();
    }

    public static boolean isRequestPinShortcutSupported(@NonNull Context context) {
        int i = Build.VERSION.SDK_INT;
        return ((ShortcutManager) context.getSystemService(ShortcutManager.class)).isRequestPinShortcutSupported();
    }

    public static boolean pushDynamicShortcut(@NonNull final Context context, @NonNull final ShortcutInfoCompat shortcut) {
        if (context == null) {
            throw new NullPointerException();
        }
        if (shortcut == null) {
            throw new NullPointerException();
        }
        int maxShortcutCountPerActivity = getMaxShortcutCountPerActivity(context);
        if (maxShortcutCountPerActivity == 0) {
            return false;
        }
        if (Build.VERSION.SDK_INT <= 29) {
            convertUriIconToBitmapIcon(context, shortcut);
        }
        if (Build.VERSION.SDK_INT >= 30) {
            ((ShortcutManager) context.getSystemService(ShortcutManager.class)).pushDynamicShortcut(shortcut.toShortcutInfo());
        } else {
            ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(ShortcutManager.class);
            if (shortcutManager.isRateLimitingActive()) {
                return false;
            }
            List<ShortcutInfo> dynamicShortcuts = shortcutManager.getDynamicShortcuts();
            if (dynamicShortcuts.size() >= maxShortcutCountPerActivity) {
                shortcutManager.removeDynamicShortcuts(Arrays.asList(Api25Impl.getShortcutInfoWithLowestRank(dynamicShortcuts)));
            }
            shortcutManager.addDynamicShortcuts(Arrays.asList(shortcut.toShortcutInfo()));
        }
        ShortcutInfoCompatSaver<?> shortcutInfoSaverInstance = getShortcutInfoSaverInstance(context);
        try {
            List<ShortcutInfoCompat> shortcuts = shortcutInfoSaverInstance.getShortcuts();
            if (shortcuts.size() >= maxShortcutCountPerActivity) {
                shortcutInfoSaverInstance.removeShortcuts(Arrays.asList(getShortcutInfoCompatWithLowestRank(shortcuts)));
            }
            shortcutInfoSaverInstance.addShortcuts(Arrays.asList(shortcut));
            return true;
        } catch (Exception unused) {
            return false;
        } finally {
            Iterator<ShortcutInfoChangeListener> it = getShortcutInfoListeners(context).iterator();
            while (it.hasNext()) {
                it.next().onShortcutAdded(Collections.singletonList(shortcut));
            }
            reportShortcutUsed(context, shortcut.getId());
        }
    }

    public static void removeAllDynamicShortcuts(@NonNull Context context) {
        int i = Build.VERSION.SDK_INT;
        ((ShortcutManager) context.getSystemService(ShortcutManager.class)).removeAllDynamicShortcuts();
        getShortcutInfoSaverInstance(context).removeAllShortcuts();
        Iterator<ShortcutInfoChangeListener> it = getShortcutInfoListeners(context).iterator();
        while (it.hasNext()) {
            it.next().onAllShortcutsRemoved();
        }
    }

    public static void removeDynamicShortcuts(@NonNull Context context, @NonNull List<String> shortcutIds) {
        int i = Build.VERSION.SDK_INT;
        ((ShortcutManager) context.getSystemService(ShortcutManager.class)).removeDynamicShortcuts(shortcutIds);
        getShortcutInfoSaverInstance(context).removeShortcuts(shortcutIds);
        Iterator<ShortcutInfoChangeListener> it = getShortcutInfoListeners(context).iterator();
        while (it.hasNext()) {
            it.next().onShortcutRemoved(shortcutIds);
        }
    }

    public static void removeLongLivedShortcuts(@NonNull final Context context, @NonNull final List<String> shortcutIds) {
        if (Build.VERSION.SDK_INT < 30) {
            removeDynamicShortcuts(context, shortcutIds);
            return;
        }
        ((ShortcutManager) context.getSystemService(ShortcutManager.class)).removeLongLivedShortcuts(shortcutIds);
        getShortcutInfoSaverInstance(context).removeShortcuts(shortcutIds);
        Iterator<ShortcutInfoChangeListener> it = getShortcutInfoListeners(context).iterator();
        while (it.hasNext()) {
            it.next().onShortcutRemoved(shortcutIds);
        }
    }

    public static void reportShortcutUsed(@NonNull final Context context, @NonNull final String shortcutId) {
        if (context == null) {
            throw new NullPointerException();
        }
        if (shortcutId == null) {
            throw new NullPointerException();
        }
        int i = Build.VERSION.SDK_INT;
        ((ShortcutManager) context.getSystemService(ShortcutManager.class)).reportShortcutUsed(shortcutId);
        Iterator<ShortcutInfoChangeListener> it = getShortcutInfoListeners(context).iterator();
        while (it.hasNext()) {
            it.next().onShortcutUsageReported(Collections.singletonList(shortcutId));
        }
    }

    public static boolean requestPinShortcut(@NonNull final Context context, @NonNull ShortcutInfoCompat shortcut, @Nullable final IntentSender callback) {
        int i = Build.VERSION.SDK_INT;
        return ((ShortcutManager) context.getSystemService(ShortcutManager.class)).requestPinShortcut(shortcut.toShortcutInfo(), callback);
    }

    public static boolean setDynamicShortcuts(@NonNull final Context context, @NonNull final List<ShortcutInfoCompat> shortcutInfoList) {
        if (context == null) {
            throw new NullPointerException();
        }
        if (shortcutInfoList == null) {
            throw new NullPointerException();
        }
        int i = Build.VERSION.SDK_INT;
        ArrayList arrayList = new ArrayList(shortcutInfoList.size());
        Iterator<ShortcutInfoCompat> it = shortcutInfoList.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toShortcutInfo());
        }
        if (!((ShortcutManager) context.getSystemService(ShortcutManager.class)).setDynamicShortcuts(arrayList)) {
            return false;
        }
        getShortcutInfoSaverInstance(context).removeAllShortcuts();
        getShortcutInfoSaverInstance(context).addShortcuts(shortcutInfoList);
        for (ShortcutInfoChangeListener shortcutInfoChangeListener : getShortcutInfoListeners(context)) {
            shortcutInfoChangeListener.onAllShortcutsRemoved();
            shortcutInfoChangeListener.onShortcutAdded(shortcutInfoList);
        }
        return true;
    }

    @VisibleForTesting
    public static void setShortcutInfoChangeListeners(final List<ShortcutInfoChangeListener> listeners) {
        sShortcutInfoChangeListeners = listeners;
    }

    @VisibleForTesting
    public static void setShortcutInfoCompatSaver(final ShortcutInfoCompatSaver<Void> saver) {
        sShortcutInfoCompatSaver = saver;
    }

    public static boolean updateShortcuts(@NonNull Context context, @NonNull List<ShortcutInfoCompat> shortcutInfoList) {
        if (Build.VERSION.SDK_INT <= 29) {
            convertUriIconsToBitmapIcons(context, shortcutInfoList);
        }
        int i = Build.VERSION.SDK_INT;
        ArrayList arrayList = new ArrayList();
        Iterator<ShortcutInfoCompat> it = shortcutInfoList.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toShortcutInfo());
        }
        if (!((ShortcutManager) context.getSystemService(ShortcutManager.class)).updateShortcuts(arrayList)) {
            return false;
        }
        getShortcutInfoSaverInstance(context).addShortcuts(shortcutInfoList);
        Iterator<ShortcutInfoChangeListener> it2 = getShortcutInfoListeners(context).iterator();
        while (it2.hasNext()) {
            it2.next().onShortcutUpdated(shortcutInfoList);
        }
        return true;
    }
}
