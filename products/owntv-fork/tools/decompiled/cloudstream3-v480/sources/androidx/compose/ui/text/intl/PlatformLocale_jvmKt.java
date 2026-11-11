package androidx.compose.ui.text.intl;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlatformLocale.jvm.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0010\u0010\u000b\u001a\u00020\u0003*\u00060\u0001j\u0002`\u0004H\u0000\"\u001c\u0010\u0002\u001a\u00020\u0003*\u00060\u0001j\u0002`\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u001c\u0010\u0007\u001a\u00020\u0003*\u00060\u0001j\u0002`\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006\"\u001c\u0010\t\u001a\u00020\u0003*\u00060\u0001j\u0002`\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006*\n\u0010\u0000\"\u00020\u00012\u00020\u0001¨\u0006\f"}, d2 = {"PlatformLocale", "Ljava/util/Locale;", "language", "", "Landroidx/compose/ui/text/intl/PlatformLocale;", "getLanguage", "(Ljava/util/Locale;)Ljava/lang/String;", "script", "getScript", TtmlNode.TAG_REGION, "getRegion", "getLanguageTag", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PlatformLocale_jvmKt {
    public static final String getLanguage(java.util.Locale locale) {
        return locale.getLanguage();
    }

    public static final String getScript(java.util.Locale locale) {
        return locale.getScript();
    }

    public static final String getRegion(java.util.Locale locale) {
        return locale.getCountry();
    }

    public static final String getLanguageTag(java.util.Locale locale) {
        return locale.toLanguageTag();
    }
}
