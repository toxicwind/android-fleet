package com.lagradost.cloudstream3.ui.settings;

import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import androidx.preference.PreferenceManager;
import com.lagradost.cloudstream3.R;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Globals.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u000f\u001a\u00020\u0005*\u00020\u0010H\u0002J\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0010H\u0002J\f\u0010\u0013\u001a\u00020\u0005*\u00020\u0010H\u0002J\n\u0010\u0014\u001a\u00020\u0015*\u00020\u0010J\u0006\u0010\u0016\u001a\u00020\u0012J\u000e\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0005R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/Globals;", "", "<init>", "()V", "beneneCount", "", "getBeneneCount", "()I", "setBeneneCount", "(I)V", "PHONE", "TV", "EMULATOR", "INVALID", "layoutId", "getLayoutInt", "Landroid/content/Context;", "isAutoTv", "", "layoutIntCorrected", "updateTv", "", "isLandscape", "isLayout", "flags", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Globals {
    public static final int EMULATOR = 4;
    private static final int INVALID = -1;
    public static final int PHONE = 1;
    public static final int TV = 2;
    private static int beneneCount;
    public static final Globals INSTANCE = new Globals();
    private static int layoutId = -1;

    private Globals() {
    }

    public final int getBeneneCount() {
        return beneneCount;
    }

    public final void setBeneneCount(int i) {
        beneneCount = i;
    }

    private final int getLayoutInt(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getString(R.string.app_layout_key), -1);
    }

    private final boolean isAutoTv(Context context) {
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        String MODEL = Build.MODEL;
        Intrinsics.checkNotNullExpressionValue(MODEL, "MODEL");
        String lowerCase = MODEL.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        if (uiModeManager != null && uiModeManager.getCurrentModeType() == 4) {
            return true;
        }
        String MODEL2 = Build.MODEL;
        Intrinsics.checkNotNullExpressionValue(MODEL2, "MODEL");
        if (StringsKt.contains$default((CharSequence) MODEL2, (CharSequence) "AFT", false, 2, (Object) null)) {
            return true;
        }
        String str = lowerCase;
        return StringsKt.contains$default((CharSequence) str, (CharSequence) "firestick", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "fire tv", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "chromecast", false, 2, (Object) null);
    }

    private final int layoutIntCorrected(Context context) {
        int layoutInt = getLayoutInt(context);
        if (layoutInt == -1) {
            return isAutoTv(context) ? 2 : 1;
        }
        if (layoutInt == 0) {
            return 1;
        }
        if (layoutInt != 1) {
            return layoutInt != 2 ? 1 : 4;
        }
        return 2;
    }

    public final void updateTv(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        layoutId = layoutIntCorrected(context);
    }

    public final boolean isLandscape() {
        return isLayout(6) || Resources.getSystem().getConfiguration().orientation == 2;
    }

    public final boolean isLayout(int flags) {
        return (layoutId & flags) != 0;
    }
}
