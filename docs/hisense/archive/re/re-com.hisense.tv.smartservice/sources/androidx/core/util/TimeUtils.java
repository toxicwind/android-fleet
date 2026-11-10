package androidx.core.util;

import androidx.annotation.RestrictTo;
import com.google.common.base.Ascii;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public final class TimeUtils {

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static final int HUNDRED_DAY_FIELD_LEN = 19;
    public static final int SECONDS_PER_DAY = 86400;
    public static final int SECONDS_PER_HOUR = 3600;
    public static final int SECONDS_PER_MINUTE = 60;
    public static final Object sFormatSync = new Object();
    public static char[] sFormatStr = new char[24];

    public static int accumField(int amt, int suffix, boolean always, int zeropad) {
        if (amt > 99 || (always && zeropad >= 3)) {
            return suffix + 3;
        }
        if (amt > 9 || (always && zeropad >= 2)) {
            return suffix + 2;
        }
        if (always || amt > 0) {
            return suffix + 1;
        }
        return 0;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static void formatDuration(long duration, StringBuilder builder) {
        synchronized (sFormatSync) {
            builder.append(sFormatStr, 0, formatDurationLocked(duration, 0));
        }
    }

    public static int formatDurationLocked(long duration, int fieldLen) {
        char c2;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        long j = duration;
        if (sFormatStr.length < fieldLen) {
            sFormatStr = new char[fieldLen];
        }
        char[] cArr = sFormatStr;
        if (j == 0) {
            int i6 = fieldLen - 1;
            while (i6 > 0) {
                cArr[0] = Ascii.CASE_MASK;
            }
            cArr[0] = '0';
            return 1;
        }
        if (j > 0) {
            c2 = '+';
        } else {
            c2 = '-';
            j = -j;
        }
        int i7 = (int) (j % 1000);
        int iFloor = (int) Math.floor(j / 1000);
        if (iFloor > 86400) {
            i = iFloor / SECONDS_PER_DAY;
            iFloor -= SECONDS_PER_DAY * i;
        } else {
            i = 0;
        }
        if (iFloor > 3600) {
            i2 = iFloor / 3600;
            iFloor -= i2 * 3600;
        } else {
            i2 = 0;
        }
        if (iFloor > 60) {
            int i8 = iFloor / 60;
            i3 = iFloor - (i8 * 60);
            i4 = i8;
        } else {
            i3 = iFloor;
            i4 = 0;
        }
        if (fieldLen != 0) {
            int iAccumField = accumField(i, 1, false, 0);
            int iAccumField2 = accumField(i2, 1, iAccumField > 0, 2) + iAccumField;
            int iAccumField3 = accumField(i4, 1, iAccumField2 > 0, 2) + iAccumField2;
            int iAccumField4 = accumField(i3, 1, iAccumField3 > 0, 2) + iAccumField3;
            i5 = 0;
            for (int iAccumField5 = accumField(i7, 2, true, iAccumField4 > 0 ? 3 : 0) + 1 + iAccumField4; iAccumField5 < fieldLen; iAccumField5++) {
                cArr[i5] = Ascii.CASE_MASK;
                i5++;
            }
        } else {
            i5 = 0;
        }
        cArr[i5] = c2;
        int i9 = i5 + 1;
        boolean z = fieldLen != 0;
        int iPrintField = printField(cArr, i, 'd', i9, false, 0);
        int iPrintField2 = printField(cArr, i2, 'h', iPrintField, iPrintField != i9, z ? 2 : 0);
        int iPrintField3 = printField(cArr, i4, 'm', iPrintField2, iPrintField2 != i9, z ? 2 : 0);
        int iPrintField4 = printField(cArr, i3, 's', iPrintField3, iPrintField3 != i9, z ? 2 : 0);
        int iPrintField5 = printField(cArr, i7, 'm', iPrintField4, true, (!z || iPrintField4 == i9) ? 0 : 3);
        cArr[iPrintField5] = 's';
        return iPrintField5 + 1;
    }

    public static int printField(char[] formatStr, int amt, char suffix, int pos, boolean always, int zeropad) {
        int i;
        if (!always && amt <= 0) {
            return pos;
        }
        if ((!always || zeropad < 3) && amt <= 99) {
            i = pos;
        } else {
            int i2 = amt / 100;
            formatStr[pos] = (char) (i2 + 48);
            i = pos + 1;
            amt -= i2 * 100;
        }
        if ((always && zeropad >= 2) || amt > 9 || pos != i) {
            int i3 = amt / 10;
            formatStr[i] = (char) (i3 + 48);
            i++;
            amt -= i3 * 10;
        }
        formatStr[i] = (char) (amt + 48);
        int i4 = i + 1;
        formatStr[i4] = suffix;
        return i4 + 1;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static void formatDuration(long duration, PrintWriter pw, int fieldLen) {
        synchronized (sFormatSync) {
            pw.print(new String(sFormatStr, 0, formatDurationLocked(duration, fieldLen)));
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static void formatDuration(long duration, PrintWriter pw) {
        formatDuration(duration, pw, 0);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static void formatDuration(long time, long now, PrintWriter pw) {
        if (time == 0) {
            pw.print("--");
        } else {
            formatDuration(time - now, pw, 0);
        }
    }
}
