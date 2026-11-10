package androidx.core.os;

import android.os.Build;
import android.os.LocaleList;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.Size;
import b.a.a.a.a;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class LocaleListCompat {
    public static final LocaleListCompat sEmptyLocaleList = create(new Locale[0]);
    public LocaleListInterface mImpl;

    public LocaleListCompat(LocaleListInterface impl) {
        this.mImpl = impl;
    }

    @NonNull
    public static LocaleListCompat create(@NonNull Locale... localeList) {
        int i = Build.VERSION.SDK_INT;
        return wrap(new LocaleList(localeList));
    }

    public static Locale forLanguageTagCompat(String str) {
        if (str.contains(MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING)) {
            String[] strArrSplit = str.split(MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING, -1);
            if (strArrSplit.length > 2) {
                return new Locale(strArrSplit[0], strArrSplit[1], strArrSplit[2]);
            }
            if (strArrSplit.length > 1) {
                return new Locale(strArrSplit[0], strArrSplit[1]);
            }
            if (strArrSplit.length == 1) {
                return new Locale(strArrSplit[0]);
            }
        } else {
            if (!str.contains("_")) {
                return new Locale(str);
            }
            String[] strArrSplit2 = str.split("_", -1);
            if (strArrSplit2.length > 2) {
                return new Locale(strArrSplit2[0], strArrSplit2[1], strArrSplit2[2]);
            }
            if (strArrSplit2.length > 1) {
                return new Locale(strArrSplit2[0], strArrSplit2[1]);
            }
            if (strArrSplit2.length == 1) {
                return new Locale(strArrSplit2[0]);
            }
        }
        throw new IllegalArgumentException(a.a("Can not parse language tag: [", str, "]"));
    }

    @NonNull
    public static LocaleListCompat forLanguageTags(@Nullable String list) {
        if (list == null || list.isEmpty()) {
            return sEmptyLocaleList;
        }
        String[] strArrSplit = list.split(",", -1);
        Locale[] localeArr = new Locale[strArrSplit.length];
        for (int i = 0; i < localeArr.length; i++) {
            int i2 = Build.VERSION.SDK_INT;
            localeArr[i] = Locale.forLanguageTag(strArrSplit[i]);
        }
        return create(localeArr);
    }

    @NonNull
    @Size(min = 1)
    public static LocaleListCompat getAdjustedDefault() {
        int i = Build.VERSION.SDK_INT;
        return wrap(LocaleList.getAdjustedDefault());
    }

    @NonNull
    @Size(min = 1)
    public static LocaleListCompat getDefault() {
        int i = Build.VERSION.SDK_INT;
        return wrap(LocaleList.getDefault());
    }

    @NonNull
    public static LocaleListCompat getEmptyLocaleList() {
        return sEmptyLocaleList;
    }

    @RequiresApi(24)
    @Deprecated
    public static LocaleListCompat wrap(Object localeList) {
        return wrap((LocaleList) localeList);
    }

    public boolean equals(Object other) {
        return (other instanceof LocaleListCompat) && this.mImpl.equals(((LocaleListCompat) other).mImpl);
    }

    public Locale get(int index) {
        return this.mImpl.get(index);
    }

    @Nullable
    public Locale getFirstMatch(@NonNull String[] supportedLocales) {
        return this.mImpl.getFirstMatch(supportedLocales);
    }

    public int hashCode() {
        return this.mImpl.hashCode();
    }

    @IntRange(from = -1)
    public int indexOf(Locale locale) {
        return this.mImpl.indexOf(locale);
    }

    public boolean isEmpty() {
        return this.mImpl.isEmpty();
    }

    @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG)
    public int size() {
        return this.mImpl.size();
    }

    @NonNull
    public String toLanguageTags() {
        return this.mImpl.toLanguageTags();
    }

    public String toString() {
        return this.mImpl.toString();
    }

    @Nullable
    public Object unwrap() {
        return this.mImpl.getLocaleList();
    }

    @NonNull
    @RequiresApi(24)
    public static LocaleListCompat wrap(@NonNull LocaleList localeList) {
        return new LocaleListCompat(new LocaleListPlatformWrapper(localeList));
    }
}
