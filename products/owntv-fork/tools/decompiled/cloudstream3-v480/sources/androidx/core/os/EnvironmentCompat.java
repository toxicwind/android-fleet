package androidx.core.os;

import android.os.Environment;
import java.io.File;

/* JADX INFO: loaded from: classes4.dex */
public final class EnvironmentCompat {

    @Deprecated
    public static final String MEDIA_UNKNOWN = "unknown";

    public static String getStorageState(File file) {
        return Environment.getExternalStorageState(file);
    }

    private EnvironmentCompat() {
    }
}
