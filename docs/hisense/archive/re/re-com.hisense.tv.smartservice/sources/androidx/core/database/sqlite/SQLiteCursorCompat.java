package androidx.core.database.sqlite;

import android.database.sqlite.SQLiteCursor;
import android.os.Build;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public final class SQLiteCursorCompat {
    public static void setFillWindowForwardOnly(@NonNull SQLiteCursor cursor, boolean fillWindowForwardOnly) {
        if (Build.VERSION.SDK_INT >= 28) {
            cursor.setFillWindowForwardOnly(fillWindowForwardOnly);
        }
    }
}
