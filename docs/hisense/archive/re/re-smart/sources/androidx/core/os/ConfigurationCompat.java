package androidx.core.os;

import android.content.res.Configuration;
import android.os.Build;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public final class ConfigurationCompat {
    @NonNull
    public static LocaleListCompat getLocales(@NonNull Configuration configuration) {
        int i = Build.VERSION.SDK_INT;
        return LocaleListCompat.wrap(configuration.getLocales());
    }
}
