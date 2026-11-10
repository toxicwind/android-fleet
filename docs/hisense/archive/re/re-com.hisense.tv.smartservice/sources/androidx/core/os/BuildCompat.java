package androidx.core.os;

import android.os.Build;
import androidx.annotation.ChecksSdkIntAtLeast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresOptIn;
import androidx.annotation.RestrictTo;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;

/* JADX INFO: loaded from: classes.dex */
public class BuildCompat {

    @RequiresOptIn
    public @interface PrereleaseSdkCheck {
    }

    @ChecksSdkIntAtLeast(api = 24)
    @Deprecated
    public static boolean isAtLeastN() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @ChecksSdkIntAtLeast(api = 25)
    @Deprecated
    public static boolean isAtLeastNMR1() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @ChecksSdkIntAtLeast(api = 26)
    @Deprecated
    public static boolean isAtLeastO() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }

    @ChecksSdkIntAtLeast(api = 27)
    @Deprecated
    public static boolean isAtLeastOMR1() {
        return Build.VERSION.SDK_INT >= 27;
    }

    @ChecksSdkIntAtLeast(api = 28)
    @Deprecated
    public static boolean isAtLeastP() {
        return Build.VERSION.SDK_INT >= 28;
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    public static boolean isAtLeastPreReleaseCodename(@NonNull String codename, @NonNull String buildCodename) {
        return !"REL".equals(buildCodename) && buildCodename.compareTo(codename) >= 0;
    }

    @ChecksSdkIntAtLeast(api = 29)
    @Deprecated
    public static boolean isAtLeastQ() {
        return Build.VERSION.SDK_INT >= 29;
    }

    @ChecksSdkIntAtLeast(api = 30)
    @Deprecated
    public static boolean isAtLeastR() {
        return Build.VERSION.SDK_INT >= 30;
    }

    @ChecksSdkIntAtLeast(api = 31, codename = MtkTvRatingConvert2Goo.SUB_RATING_STR_S)
    public static boolean isAtLeastS() {
        return Build.VERSION.SDK_INT >= 31 || isAtLeastPreReleaseCodename(MtkTvRatingConvert2Goo.SUB_RATING_STR_S, Build.VERSION.CODENAME);
    }

    @ChecksSdkIntAtLeast(codename = "T")
    @PrereleaseSdkCheck
    public static boolean isAtLeastT() {
        return isAtLeastPreReleaseCodename("T", Build.VERSION.CODENAME);
    }
}
