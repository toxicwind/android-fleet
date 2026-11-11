package androidx.biometric.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class PackageUtils {
    private PackageUtils() {
    }

    public static boolean hasSystemFeatureFingerprint(Context context) {
        return (context == null || context.getPackageManager() == null || !context.getPackageManager().hasSystemFeature("android.hardware.fingerprint")) ? false : true;
    }

    public static boolean hasSystemFeatureFace(Context context) {
        return Build.VERSION.SDK_INT >= 29 && context != null && context.getPackageManager() != null && Api29Impl.hasSystemFeatureFace(context.getPackageManager());
    }

    public static boolean hasSystemFeatureIris(Context context) {
        return Build.VERSION.SDK_INT >= 29 && context != null && context.getPackageManager() != null && Api29Impl.hasSystemFeatureIris(context.getPackageManager());
    }

    private static class Api29Impl {
        private Api29Impl() {
        }

        static boolean hasSystemFeatureFace(PackageManager packageManager) {
            return packageManager.hasSystemFeature("android.hardware.biometrics.face");
        }

        static boolean hasSystemFeatureIris(PackageManager packageManager) {
            return packageManager.hasSystemFeature("android.hardware.biometrics.iris");
        }
    }
}
