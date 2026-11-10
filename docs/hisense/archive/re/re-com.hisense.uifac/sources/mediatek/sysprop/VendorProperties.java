package mediatek.sysprop;

import android.os.SystemProperties;
import java.util.Locale;
import java.util.Optional;

/* JADX INFO: loaded from: classes.dex */
public final class VendorProperties {
    /* JADX WARN: Code duplicated, block: B:23:0x0048  */
    private static Boolean tryParseBoolean(String str) {
        byte b;
        String lowerCase = str.toLowerCase(Locale.US);
        int iHashCode = lowerCase.hashCode();
        if (iHashCode != 48) {
            if (iHashCode != 49) {
                if (iHashCode != 3569038) {
                    if (iHashCode == 97196323 && lowerCase.equals("false")) {
                        b = 3;
                    } else {
                        b = -1;
                    }
                } else if (lowerCase.equals("true")) {
                    b = 1;
                } else {
                    b = -1;
                }
            } else if (lowerCase.equals("1")) {
                b = 0;
            } else {
                b = -1;
            }
        } else if (lowerCase.equals("0")) {
            b = 2;
        } else {
            b = -1;
        }
        if (b == 0 || b == 1) {
            return Boolean.TRUE;
        }
        if (b == 2 || b == 3) {
            return Boolean.FALSE;
        }
        return null;
    }

    private static Integer tryParseInteger(String str) {
        try {
            return Integer.valueOf(str);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static Optional<Boolean> mtk_inside() {
        return Optional.ofNullable(tryParseBoolean(SystemProperties.get("vendor.mtk.inside")));
    }

    public static Optional<Integer> mtk_system_timesync_existed() {
        return Optional.ofNullable(tryParseInteger(SystemProperties.get("ro.vendor.mtk.system.timesync.existed")));
    }
}
