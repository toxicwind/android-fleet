package androidx.core.content;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public final class IntentCompat {

    @SuppressLint({"ActionValue"})
    public static final String ACTION_CREATE_REMINDER = "android.intent.action.CREATE_REMINDER";
    public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";
    public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
    public static final String EXTRA_START_PLAYBACK = "android.intent.extra.START_PLAYBACK";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_TIME = "android.intent.extra.TIME";

    @NonNull
    public static Intent makeMainSelectorActivity(@NonNull String selectorAction, @NonNull String selectorCategory) {
        int i = Build.VERSION.SDK_INT;
        return Intent.makeMainSelectorActivity(selectorAction, selectorCategory);
    }
}
