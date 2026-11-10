package androidx.core.content;

import android.content.ContentProvider;
import android.content.Context;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public final class ContentProviderCompat {
    @NonNull
    public static Context requireContext(@NonNull final ContentProvider provider) {
        Context context = provider.getContext();
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("Cannot find context from the provider.");
    }
}
