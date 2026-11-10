package androidx.core.os;

import android.os.Build;
import android.os.Environment;
import androidx.annotation.NonNull;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class EnvironmentCompat {
    public static final String MEDIA_UNKNOWN = "unknown";
    public static final String TAG = "EnvironmentCompat";

    @NonNull
    public static String getStorageState(@NonNull File path) {
        int i = Build.VERSION.SDK_INT;
        return Environment.getExternalStorageState(path);
    }
}
