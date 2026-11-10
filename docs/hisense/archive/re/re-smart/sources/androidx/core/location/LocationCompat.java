package androidx.core.location;

import android.location.Location;
import android.os.Build;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class LocationCompat {
    public static final String EXTRA_IS_MOCK = "mockLocation";

    @Nullable
    public static Method sSetIsFromMockProviderMethod;

    @RequiresApi(17)
    private static class Api17Impl {
        @DoNotInline
        public static long getElapsedRealtimeNanos(Location location) {
            return location.getElapsedRealtimeNanos();
        }
    }

    @RequiresApi(18)
    private static class Api18Impl {
        @DoNotInline
        public static boolean isMock(Location location) {
            return location.isFromMockProvider();
        }
    }

    public static long getElapsedRealtimeMillis(@NonNull Location location) {
        int i = Build.VERSION.SDK_INT;
        return TimeUnit.NANOSECONDS.toMillis(location.getElapsedRealtimeNanos());
    }

    public static long getElapsedRealtimeNanos(@NonNull Location location) {
        int i = Build.VERSION.SDK_INT;
        return location.getElapsedRealtimeNanos();
    }

    public static Method getSetIsFromMockProviderMethod() {
        if (sSetIsFromMockProviderMethod == null) {
            sSetIsFromMockProviderMethod = Location.class.getDeclaredMethod("setIsFromMockProvider", Boolean.TYPE);
            sSetIsFromMockProviderMethod.setAccessible(true);
        }
        return sSetIsFromMockProviderMethod;
    }

    public static boolean isMock(@NonNull Location location) {
        int i = Build.VERSION.SDK_INT;
        return location.isFromMockProvider();
    }

    public static void setMock(@NonNull Location location, boolean mock) {
        int i = Build.VERSION.SDK_INT;
        try {
            getSetIsFromMockProviderMethod().invoke(location, Boolean.valueOf(mock));
        } catch (IllegalAccessException e2) {
            IllegalAccessError illegalAccessError = new IllegalAccessError();
            illegalAccessError.initCause(e2);
            throw illegalAccessError;
        } catch (NoSuchMethodException e3) {
            NoSuchMethodError noSuchMethodError = new NoSuchMethodError();
            noSuchMethodError.initCause(e3);
            throw noSuchMethodError;
        } catch (InvocationTargetException e4) {
            throw new RuntimeException(e4);
        }
    }
}
