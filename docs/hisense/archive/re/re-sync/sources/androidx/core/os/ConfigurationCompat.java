package androidx.core.os;

import android.content.res.Configuration;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public final class ConfigurationCompat {
    @NonNull
    public static LocaleListCompat getLocales(@NonNull Configuration configuration) {
        return LocaleListCompat.wrap(configuration.getLocales());
    }
}
