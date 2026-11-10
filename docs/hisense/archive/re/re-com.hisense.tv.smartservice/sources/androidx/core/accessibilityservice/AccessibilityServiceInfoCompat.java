package androidx.core.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class AccessibilityServiceInfoCompat {
    public static final int CAPABILITY_CAN_FILTER_KEY_EVENTS = 8;
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
    public static final int FEEDBACK_ALL_MASK = -1;
    public static final int FEEDBACK_BRAILLE = 32;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
    public static final int FLAG_REPORT_VIEW_IDS = 16;
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;

    @NonNull
    public static String capabilityToString(int capability) {
        if (capability == 1) {
            return "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
        }
        if (capability == 2) {
            return "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION";
        }
        if (capability != 4) {
            return capability != 8 ? "UNKNOWN" : "CAPABILITY_CAN_FILTER_KEY_EVENTS";
        }
        return "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
    }

    @NonNull
    public static String feedbackTypeToString(int feedbackType) {
        StringBuilder sbA = a.a("[");
        while (feedbackType > 0) {
            int iNumberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(feedbackType);
            feedbackType &= ~iNumberOfTrailingZeros;
            if (sbA.length() > 1) {
                sbA.append(", ");
            }
            if (iNumberOfTrailingZeros == 1) {
                sbA.append("FEEDBACK_SPOKEN");
            } else if (iNumberOfTrailingZeros == 2) {
                sbA.append("FEEDBACK_HAPTIC");
            } else if (iNumberOfTrailingZeros == 4) {
                sbA.append("FEEDBACK_AUDIBLE");
            } else if (iNumberOfTrailingZeros == 8) {
                sbA.append("FEEDBACK_VISUAL");
            } else if (iNumberOfTrailingZeros == 16) {
                sbA.append("FEEDBACK_GENERIC");
            }
        }
        sbA.append("]");
        return sbA.toString();
    }

    @Nullable
    public static String flagToString(int flag) {
        if (flag == 1) {
            return "DEFAULT";
        }
        if (flag == 2) {
            return "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";
        }
        if (flag == 4) {
            return "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";
        }
        if (flag == 8) {
            return "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
        }
        if (flag == 16) {
            return "FLAG_REPORT_VIEW_IDS";
        }
        if (flag != 32) {
            return null;
        }
        return "FLAG_REQUEST_FILTER_KEY_EVENTS";
    }

    public static int getCapabilities(@NonNull AccessibilityServiceInfo info) {
        int i = Build.VERSION.SDK_INT;
        return info.getCapabilities();
    }

    @Nullable
    public static String loadDescription(@NonNull AccessibilityServiceInfo info, @NonNull PackageManager packageManager) {
        int i = Build.VERSION.SDK_INT;
        return info.loadDescription(packageManager);
    }
}
