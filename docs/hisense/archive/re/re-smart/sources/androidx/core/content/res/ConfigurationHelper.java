package androidx.core.content.res;

import android.content.res.Resources;
import android.os.Build;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public final class ConfigurationHelper {
    public static int getDensityDpi(@NonNull Resources resources) {
        int i = Build.VERSION.SDK_INT;
        return resources.getConfiguration().densityDpi;
    }
}
