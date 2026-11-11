package com.lagradost.cloudstream3.ui.settings;

import android.content.Context;
import androidx.core.os.ConfigurationCompat;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;

/* JADX INFO: compiled from: SettingsGeneral.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0016\u0010\t\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u0006\"#\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"getCurrentLocale", "", "context", "Landroid/content/Context;", "appLanguages", "", "Lkotlin/Pair;", "getAppLanguages", "()Ljava/util/List;", "nameNextToFlagEmoji", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SettingsGeneralKt {
    private static final List<Pair<String, String>> appLanguages = CollectionsKt.sortedWith(CollectionsKt.arrayListOf(new Pair("Afrikaans", "af"), new Pair("Azərbaycan dili", "az"), new Pair("Bahasa Indonesia", "in"), new Pair("Bahasa Melayu", "ms"), new Pair("Deutsch", "de"), new Pair("English", "en"), new Pair("Español", "es"), new Pair("Esperanto", "eo"), new Pair("Français", "fr"), new Pair("Galego", "gl"), new Pair("hrvatski", "hr"), new Pair("Italiano", "it"), new Pair("Latviešu valoda", "lv"), new Pair("Lietuvių kalba", "lt"), new Pair("Magyar", "hu"), new Pair("Malti", "mt"), new Pair("mmmm... monke", "qt"), new Pair("Nederlands", "nl"), new Pair("Norsk bokmål", "no"), new Pair("Norsk nynorsk", "nn"), new Pair("Polski", "pl"), new Pair("Português", "pt"), new Pair("Português (Brasil)", "pt-BR"), new Pair("Română", "ro"), new Pair("Slovenčina", "sk"), new Pair("Soomaaliga", "so"), new Pair("Svenska", "sv"), new Pair("Tagalog", "tl"), new Pair("Tiếng Việt", "vi"), new Pair("Türkçe", "tr"), new Pair("Wikang Filipino", "fil"), new Pair("Čeština", "cs"), new Pair("Ελληνικά", "el"), new Pair("български", "bg"), new Pair("македонски", "mk"), new Pair("русский", "ru"), new Pair("українська", "uk"), new Pair("עברית", "iw"), new Pair("اردو", "ur"), new Pair("العربية", "ar"), new Pair("اللهجة النجدية", "ars"), new Pair("عربي شامي", "apc"), new Pair("فارسی", "fa"), new Pair("کوردیی ناوەندی", "ckb"), new Pair("नेपाली", "ne"), new Pair("हिन्दी", "hi"), new Pair("অসমীয়া", "as"), new Pair("বাংলা", "bn"), new Pair("ଓଡ଼ିଆ", "or"), new Pair("தமிழ்", "ta"), new Pair("ಕನ್ನಡ", "kn"), new Pair("മലയാളം", "ml"), new Pair("ဗမာစာ", "my"), new Pair("ትግርኛ", "ti"), new Pair("አማርኛ", "am"), new Pair("中文", "zh"), new Pair("日本語 (にほんご)", "ja"), new Pair("正體中文(臺灣)", "zh-TW"), new Pair("한국어", "ko")), new Comparator() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsGeneralKt$special$$inlined$sortedBy$1
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            String str = (String) ((Pair) t).getFirst();
            Locale ROOT = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
            String lowerCase = str.toLowerCase(ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            String str2 = (String) ((Pair) t2).getFirst();
            Locale ROOT2 = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue(ROOT2, "ROOT");
            String lowerCase2 = str2.toLowerCase(ROOT2);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            return ComparisonsKt.compareValues(lowerCase, lowerCase2);
        }
    });

    public static final String getCurrentLocale(Context context) {
        String languageTag;
        Intrinsics.checkNotNullParameter(context, "context");
        Locale locale = ConfigurationCompat.getLocales(context.getResources().getConfiguration()).get(0);
        return (locale == null || (languageTag = locale.toLanguageTag()) == null) ? "en" : languageTag;
    }

    public static final List<Pair<String, String>> getAppLanguages() {
        return appLanguages;
    }

    public static final String nameNextToFlagEmoji(Pair<String, String> pair) {
        Intrinsics.checkNotNullParameter(pair, "<this>");
        String flagFromIso = SubtitleHelper.INSTANCE.getFlagFromIso(pair.getSecond());
        if (flagFromIso == null) {
            flagFromIso = "🇦🇦";
        }
        return flagFromIso + Typography.nbsp + pair.getFirst();
    }
}
