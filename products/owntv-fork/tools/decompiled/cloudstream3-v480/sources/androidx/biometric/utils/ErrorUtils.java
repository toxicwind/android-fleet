package androidx.biometric.utils;

import android.content.Context;
import android.util.Log;
import androidx.biometric.R;

/* JADX INFO: loaded from: classes.dex */
public class ErrorUtils {
    public static boolean isLockoutError(int i) {
        return i == 7 || i == 9;
    }

    public static int toKnownErrorCodeForAuthenticate(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 20:
            case 22:
                return i;
            case 6:
            case 16:
            case 17:
            case 19:
            default:
                return 8;
            case 18:
            case 21:
                return 1;
        }
    }

    public static int toKnownStatusCodeForCanAuthenticate(int i) {
        if (i == 7) {
            return 0;
        }
        if (i != 21) {
            return i;
        }
        return 1;
    }

    private ErrorUtils() {
    }

    public static String getFingerprintErrorString(Context context, int i) {
        if (context == null) {
            return "";
        }
        if (i == 1) {
            return context.getString(R.string.fingerprint_error_hw_not_available);
        }
        if (i != 7) {
            switch (i) {
                case 9:
                    break;
                case 10:
                    return context.getString(R.string.fingerprint_error_user_canceled);
                case 11:
                    return context.getString(R.string.fingerprint_error_no_fingerprints);
                case 12:
                    return context.getString(R.string.fingerprint_error_hw_not_present);
                default:
                    Log.e("BiometricUtils", "Unknown error code: " + i);
                    return context.getString(R.string.default_error_msg);
            }
        }
        return context.getString(R.string.fingerprint_error_lockout);
    }
}
