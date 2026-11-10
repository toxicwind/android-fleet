package androidx.core.database;

import android.database.CursorWindow;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class CursorWindowCompat {
    @NonNull
    public static CursorWindow create(@Nullable String name, long windowSizeBytes) {
        return Build.VERSION.SDK_INT >= 28 ? new CursorWindow(name, windowSizeBytes) : new CursorWindow(name);
    }
}
