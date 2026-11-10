package androidx.core.os;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public interface LocaleListInterface {
    Locale get(int index);

    @Nullable
    Locale getFirstMatch(@NonNull String[] supportedLocales);

    Object getLocaleList();

    @IntRange(from = -1)
    int indexOf(Locale locale);

    boolean isEmpty();

    @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG)
    int size();

    String toLanguageTags();
}
