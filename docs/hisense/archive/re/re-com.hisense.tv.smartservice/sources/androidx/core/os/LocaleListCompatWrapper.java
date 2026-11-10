package androidx.core.os;

import android.os.Build;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import b.a.a.a.a;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.hisense.support.tv.HisenseCountryLanInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class LocaleListCompatWrapper implements LocaleListInterface {
    public final Locale[] mList;

    @NonNull
    public final String mStringRepresentation;
    public static final Locale[] sEmptyList = new Locale[0];
    public static final Locale LOCALE_EN_XA = new Locale(HisenseCountryLanInfo.LOCALE_ENG_CODE, "XA");
    public static final Locale LOCALE_AR_XB = new Locale(HisenseCountryLanInfo.LOCALE_ARA_CODE, "XB");
    public static final Locale EN_LATN = LocaleListCompat.forLanguageTagCompat("en-Latn");

    public LocaleListCompatWrapper(@NonNull Locale... list) {
        if (list.length == 0) {
            this.mList = sEmptyList;
            this.mStringRepresentation = "";
            return;
        }
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            Locale locale = list[i];
            if (locale == null) {
                throw new NullPointerException(a.g("list[", i, "] is null"));
            }
            if (!hashSet.contains(locale)) {
                Locale locale2 = (Locale) locale.clone();
                arrayList.add(locale2);
                toLanguageTag(sb, locale2);
                if (i < list.length - 1) {
                    sb.append(',');
                }
                hashSet.add(locale2);
            }
        }
        this.mList = (Locale[]) arrayList.toArray(new Locale[arrayList.size()]);
        this.mStringRepresentation = sb.toString();
    }

    private Locale computeFirstMatch(Collection<String> supportedLocales, boolean assumeEnglishIsSupported) {
        int iComputeFirstMatchIndex = computeFirstMatchIndex(supportedLocales, assumeEnglishIsSupported);
        if (iComputeFirstMatchIndex == -1) {
            return null;
        }
        return this.mList[iComputeFirstMatchIndex];
    }

    /* JADX WARN: Code duplicated, block: B:16:0x001e  */
    private int computeFirstMatchIndex(Collection<String> supportedLocales, boolean assumeEnglishIsSupported) {
        int iFindFirstMatchIndex;
        Locale[] localeArr = this.mList;
        if (localeArr.length == 1) {
            return 0;
        }
        if (localeArr.length == 0) {
            return -1;
        }
        if (assumeEnglishIsSupported) {
            iFindFirstMatchIndex = findFirstMatchIndex(EN_LATN);
            if (iFindFirstMatchIndex == 0) {
                return 0;
            }
            if (iFindFirstMatchIndex >= Integer.MAX_VALUE) {
                iFindFirstMatchIndex = Integer.MAX_VALUE;
            }
        } else {
            iFindFirstMatchIndex = Integer.MAX_VALUE;
        }
        Iterator<String> it = supportedLocales.iterator();
        while (it.hasNext()) {
            int iFindFirstMatchIndex2 = findFirstMatchIndex(LocaleListCompat.forLanguageTagCompat(it.next()));
            if (iFindFirstMatchIndex2 == 0) {
                return 0;
            }
            if (iFindFirstMatchIndex2 < iFindFirstMatchIndex) {
                iFindFirstMatchIndex = iFindFirstMatchIndex2;
            }
        }
        if (iFindFirstMatchIndex == Integer.MAX_VALUE) {
            return 0;
        }
        return iFindFirstMatchIndex;
    }

    private int findFirstMatchIndex(Locale supportedLocale) {
        int i = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i >= localeArr.length) {
                return Integer.MAX_VALUE;
            }
            if (matchScore(supportedLocale, localeArr[i]) > 0) {
                return i;
            }
            i++;
        }
    }

    public static String getLikelyScript(Locale locale) {
        int i = Build.VERSION.SDK_INT;
        String script = locale.getScript();
        return !script.isEmpty() ? script : "";
    }

    public static boolean isPseudoLocale(Locale locale) {
        return LOCALE_EN_XA.equals(locale) || LOCALE_AR_XB.equals(locale);
    }

    @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG, to = 1)
    public static int matchScore(Locale locale, Locale locale2) {
        if (locale.equals(locale2)) {
            return 1;
        }
        if (!locale.getLanguage().equals(locale2.getLanguage()) || isPseudoLocale(locale) || isPseudoLocale(locale2)) {
            return 0;
        }
        String likelyScript = getLikelyScript(locale);
        if (!likelyScript.isEmpty()) {
            return likelyScript.equals(getLikelyScript(locale2)) ? 1 : 0;
        }
        String country = locale.getCountry();
        return (country.isEmpty() || country.equals(locale2.getCountry())) ? 1 : 0;
    }

    @VisibleForTesting
    public static void toLanguageTag(StringBuilder builder, Locale locale) {
        builder.append(locale.getLanguage());
        String country = locale.getCountry();
        if (country == null || country.isEmpty()) {
            return;
        }
        builder.append('-');
        builder.append(locale.getCountry());
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof LocaleListCompatWrapper)) {
            return false;
        }
        Locale[] localeArr = ((LocaleListCompatWrapper) other).mList;
        if (this.mList.length != localeArr.length) {
            return false;
        }
        int i = 0;
        while (true) {
            Locale[] localeArr2 = this.mList;
            if (i >= localeArr2.length) {
                return true;
            }
            if (!localeArr2[i].equals(localeArr[i])) {
                return false;
            }
            i++;
        }
    }

    @Override // androidx.core.os.LocaleListInterface
    public Locale get(int index) {
        if (index >= 0) {
            Locale[] localeArr = this.mList;
            if (index < localeArr.length) {
                return localeArr[index];
            }
        }
        return null;
    }

    @Override // androidx.core.os.LocaleListInterface
    public Locale getFirstMatch(@NonNull String[] supportedLocales) {
        return computeFirstMatch(Arrays.asList(supportedLocales), false);
    }

    @Override // androidx.core.os.LocaleListInterface
    @Nullable
    public Object getLocaleList() {
        return null;
    }

    public int hashCode() {
        int iHashCode = 1;
        int i = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i >= localeArr.length) {
                return iHashCode;
            }
            iHashCode = (iHashCode * 31) + localeArr[i].hashCode();
            i++;
        }
    }

    @Override // androidx.core.os.LocaleListInterface
    public int indexOf(Locale locale) {
        int i = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i >= localeArr.length) {
                return -1;
            }
            if (localeArr[i].equals(locale)) {
                return i;
            }
            i++;
        }
    }

    @Override // androidx.core.os.LocaleListInterface
    public boolean isEmpty() {
        return this.mList.length == 0;
    }

    @Override // androidx.core.os.LocaleListInterface
    public int size() {
        return this.mList.length;
    }

    @Override // androidx.core.os.LocaleListInterface
    public String toLanguageTags() {
        return this.mStringRepresentation;
    }

    public String toString() {
        StringBuilder sbA = a.a("[");
        int i = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i >= localeArr.length) {
                sbA.append("]");
                return sbA.toString();
            }
            sbA.append(localeArr[i]);
            if (i < this.mList.length - 1) {
                sbA.append(',');
            }
            i++;
        }
    }
}
