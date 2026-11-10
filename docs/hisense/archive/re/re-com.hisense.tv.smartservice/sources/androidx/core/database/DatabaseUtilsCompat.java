package androidx.core.database;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class DatabaseUtilsCompat {
    @Deprecated
    public static String[] appendSelectionArgs(String[] originalValues, String[] newValues) {
        if (originalValues == null || originalValues.length == 0) {
            return newValues;
        }
        String[] strArr = new String[originalValues.length + newValues.length];
        System.arraycopy(originalValues, 0, strArr, 0, originalValues.length);
        System.arraycopy(newValues, 0, strArr, originalValues.length, newValues.length);
        return strArr;
    }

    @Deprecated
    public static String concatenateWhere(String a2, String b2) {
        if (TextUtils.isEmpty(a2)) {
            return b2;
        }
        if (TextUtils.isEmpty(b2)) {
            return a2;
        }
        return "(" + a2 + ") AND (" + b2 + ")";
    }
}
