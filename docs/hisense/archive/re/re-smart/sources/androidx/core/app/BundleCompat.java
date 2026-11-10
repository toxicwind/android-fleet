package androidx.core.app;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class BundleCompat {

    static class BundleCompatBaseImpl {
        public static final String TAG = "BundleCompatBaseImpl";
        public static Method sGetIBinderMethod;
        public static boolean sGetIBinderMethodFetched;
        public static Method sPutIBinderMethod;
        public static boolean sPutIBinderMethodFetched;

        public static IBinder getBinder(Bundle bundle, String key) {
            if (!sGetIBinderMethodFetched) {
                try {
                    sGetIBinderMethod = Bundle.class.getMethod("getIBinder", String.class);
                    sGetIBinderMethod.setAccessible(true);
                } catch (NoSuchMethodException e2) {
                    Log.i(TAG, "Failed to retrieve getIBinder method", e2);
                }
                sGetIBinderMethodFetched = true;
            }
            Method method = sGetIBinderMethod;
            if (method != null) {
                try {
                    return (IBinder) method.invoke(bundle, key);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e3) {
                    Log.i(TAG, "Failed to invoke getIBinder via reflection", e3);
                    sGetIBinderMethod = null;
                }
            }
            return null;
        }

        public static void putBinder(Bundle bundle, String key, IBinder binder) {
            if (!sPutIBinderMethodFetched) {
                try {
                    sPutIBinderMethod = Bundle.class.getMethod("putIBinder", String.class, IBinder.class);
                    sPutIBinderMethod.setAccessible(true);
                } catch (NoSuchMethodException e2) {
                    Log.i(TAG, "Failed to retrieve putIBinder method", e2);
                }
                sPutIBinderMethodFetched = true;
            }
            Method method = sPutIBinderMethod;
            if (method != null) {
                try {
                    method.invoke(bundle, key, binder);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e3) {
                    Log.i(TAG, "Failed to invoke putIBinder via reflection", e3);
                    sPutIBinderMethod = null;
                }
            }
        }
    }

    @Nullable
    public static IBinder getBinder(@NonNull Bundle bundle, @Nullable String key) {
        int i = Build.VERSION.SDK_INT;
        return bundle.getBinder(key);
    }

    public static void putBinder(@NonNull Bundle bundle, @Nullable String key, @Nullable IBinder binder) {
        int i = Build.VERSION.SDK_INT;
        bundle.putBinder(key, binder);
    }
}
