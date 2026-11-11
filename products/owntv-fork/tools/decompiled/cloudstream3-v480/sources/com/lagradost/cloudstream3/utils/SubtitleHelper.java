package com.lagradost.cloudstream3.utils;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.text.util.LocalePreferences;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.google.android.gms.cast.HlsSegmentFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

/* JADX INFO: compiled from: SubtitleHelper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010$\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0011\bÆ\u0002\u0018\u00002\u00020\u0001:\u000289B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0002\u0010\nJ\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\tH\u0007J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0007J#\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007H\u0002J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0007J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0007J\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0007J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\u001a\u001a\u00020\t2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0007J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u00072\b\u0010\u001d\u001a\u0004\u0018\u00010\u0007J\u001e\u0010\u001e\u001a\u0004\u0018\u00010\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0007J\u0014\u0010\u001f\u001a\u0004\u0018\u00010\u00072\b\u0010 \u001a\u0004\u0018\u00010\u0007H\u0002R\u001a\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00050$¢\u0006\u000e\n\u0000\u0012\u0004\b%\u0010\u0003\u001a\u0004\b&\u0010'R\u001d\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020)0\"¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u001d\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020)0\"¢\u0006\b\n\u0000\u001a\u0004\b-\u0010+R\u001d\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020)0\"¢\u0006\b\n\u0000\u001a\u0004\b/\u0010+R\u001d\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020)0\"¢\u0006\b\n\u0000\u001a\u0004\b1\u0010+R\u001d\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020)0\"¢\u0006\b\n\u0000\u001a\u0004\b3\u0010+R\u001d\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020)0\"¢\u0006\b\n\u0000\u001a\u0004\b5\u0010+R\u001d\u00106\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020)0\"¢\u0006\b\n\u0000\u001a\u0004\b7\u0010+¨\u0006:"}, d2 = {"Lcom/lagradost/cloudstream3/utils/SubtitleHelper;", "", "<init>", "()V", "getLanguageDataFromName", "Lcom/lagradost/cloudstream3/utils/SubtitleHelper$LanguageMetadata;", "languageName", "", "halfMatch", "", "(Ljava/lang/String;Ljava/lang/Boolean;)Lcom/lagradost/cloudstream3/utils/SubtitleHelper$LanguageMetadata;", "fromLanguageToTwoLetters", TvContractCompat.PARAM_INPUT, "looseCheck", "fromLanguageToThreeLetters", "fromLanguageToTagIETF", "(Ljava/lang/String;Ljava/lang/Boolean;)Ljava/lang/String;", "getLanguageDataFromCode", "languageCode", "fromTwoLettersToLanguage", "fromThreeLettersToLanguage", "fromTagToLanguageName", "localizedTo", "fromTagToEnglishLanguageName", "fromCodeToOpenSubtitlesTag", "fromCodeToLangTagIETF", "isWellFormedTagIETF", "langTagIETF", "getFlagFromIso", "inp", "getNameNextToFlagEmoji", "getFlagFromCountry2Letters", "countryLetters", "lang2country", "", "languages", "", "getLanguages$annotations", "getLanguages", "()Ljava/util/List;", "indexMapLanguageName", "", "getIndexMapLanguageName", "()Ljava/util/Map;", "indexMapNativeName", "getIndexMapNativeName", "indexMapIETF_tag", "getIndexMapIETF_tag", "indexMapISO_639_1", "getIndexMapISO_639_1", "indexMapISO_639_2_B", "getIndexMapISO_639_2_B", "indexMapISO_639_3", "getIndexMapISO_639_3", "indexMapOpenSubtitles", "getIndexMapOpenSubtitles", "Language639", "LanguageMetadata", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SubtitleHelper {
    private static final Map<String, Integer> indexMapIETF_tag;
    private static final Map<String, Integer> indexMapISO_639_1;
    private static final Map<String, Integer> indexMapISO_639_2_B;
    private static final Map<String, Integer> indexMapISO_639_3;
    private static final Map<String, Integer> indexMapLanguageName;
    private static final Map<String, Integer> indexMapNativeName;
    private static final Map<String, Integer> indexMapOpenSubtitles;
    private static final List<LanguageMetadata> languages;
    public static final SubtitleHelper INSTANCE = new SubtitleHelper();
    private static final Map<String, String> lang2country = MapsKt.mapOf(TuplesKt.to("419", "ES"), TuplesKt.to("aa", "ET"), TuplesKt.to("af", "ZA"), TuplesKt.to("agq", "CM"), TuplesKt.to("ajp", "SY"), TuplesKt.to("ak", "GH"), TuplesKt.to("am", "ET"), TuplesKt.to("an", "ES"), TuplesKt.to("apc", "SY"), TuplesKt.to("ar", "AE"), TuplesKt.to("arg", "ES"), TuplesKt.to("ars", "SA"), TuplesKt.to("as", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), TuplesKt.to("asa", "TZ"), TuplesKt.to(CmcdData.OBJECT_TYPE_MUXED_AUDIO_AND_VIDEO, "RU"), TuplesKt.to("ay", "BO"), TuplesKt.to("azb", "AZ"), TuplesKt.to("bas", "CM"), TuplesKt.to("be", "BY"), TuplesKt.to("bem", "ZM"), TuplesKt.to("bez", "IT"), TuplesKt.to("bm", "ML"), TuplesKt.to("bn", "BD"), TuplesKt.to("bo", "CN"), TuplesKt.to("br", "FR"), TuplesKt.to("brx", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), TuplesKt.to(CmcdConfiguration.KEY_BUFFER_STARVATION, "BA"), TuplesKt.to("ca", "ES"), TuplesKt.to("cgg", "UG"), TuplesKt.to("chr", "US"), TuplesKt.to("ckb", "IQ"), TuplesKt.to("cnr", "ME"), TuplesKt.to("cs", "CZ"), TuplesKt.to("cy", "GB"), TuplesKt.to("da", "DK"), TuplesKt.to("dav", "KE"), TuplesKt.to("dje", "NE"), TuplesKt.to("dua", "CM"), TuplesKt.to("dyo", "SN"), TuplesKt.to("dz", "BT"), TuplesKt.to("ea", "ES"), TuplesKt.to("ebu", "KE"), TuplesKt.to("ee", "GH"), TuplesKt.to("el", "GR"), TuplesKt.to("en", "GB"), TuplesKt.to("et", "EE"), TuplesKt.to("eu", "ES"), TuplesKt.to("ewo", "CM"), TuplesKt.to("ex", "ES"), TuplesKt.to("ext", "ES"), TuplesKt.to("fa", "IR"), TuplesKt.to("ff", "CN"), TuplesKt.to("fil", "PH"), TuplesKt.to("fj", "FJ"), TuplesKt.to("ga", "IE"), TuplesKt.to("gd", "GB"), TuplesKt.to("gl", "ES"), TuplesKt.to("gn", "PY"), TuplesKt.to("gsw", "CH"), TuplesKt.to("gu", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), TuplesKt.to("guz", "KE"), TuplesKt.to("gv", "GB"), TuplesKt.to("ha", "NG"), TuplesKt.to("haw", "US"), TuplesKt.to("he", "IL"), TuplesKt.to("hi", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), TuplesKt.to("hy", "AM"), TuplesKt.to("ig", "NG"), TuplesKt.to("ii", "CN"), TuplesKt.to("in", "ID"), TuplesKt.to("ita", "IT"), TuplesKt.to("iw", "IL"), TuplesKt.to("ja", "JP"), TuplesKt.to("jmc", "TZ"), TuplesKt.to("jv", "ID"), TuplesKt.to("jvn", "ID"), TuplesKt.to("ka", "GE"), TuplesKt.to("kab", "DZ"), TuplesKt.to("kam", "KE"), TuplesKt.to("kde", "TZ"), TuplesKt.to("kea", "CV"), TuplesKt.to("kg", "CD"), TuplesKt.to("khq", "ML"), TuplesKt.to("ki", "KE"), TuplesKt.to("kk", "KZ"), TuplesKt.to("kl", "GL"), TuplesKt.to("kln", "KE"), TuplesKt.to("km", "KH"), TuplesKt.to("kn", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), TuplesKt.to("ko", "KR"), TuplesKt.to("kok", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), TuplesKt.to("kr", "TD"), TuplesKt.to("ks", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), TuplesKt.to("ksb", "TZ"), TuplesKt.to("ksf", "CM"), TuplesKt.to("ku", "IQ"), TuplesKt.to("kw", "GB"), TuplesKt.to("ky", "KG"), TuplesKt.to("la", "IT"), TuplesKt.to("lag", "TZ"), TuplesKt.to("lat", "IT"), TuplesKt.to("lat", "LV"), TuplesKt.to("lb", "LU"), TuplesKt.to("lg", "UG"), TuplesKt.to("ln", "CG"), TuplesKt.to("lo", "LA"), TuplesKt.to("ltz", "LU"), TuplesKt.to("lu", "CD"), TuplesKt.to("luo", "KE"), TuplesKt.to("luy", "KE"), TuplesKt.to("ma", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), TuplesKt.to("mas", "TZ"), TuplesKt.to("mer", "KE"), TuplesKt.to("mfe", "MU"), TuplesKt.to("mgh", "MZ"), TuplesKt.to("ml", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), TuplesKt.to("mni", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), TuplesKt.to("mr", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), TuplesKt.to("ms", "MY"), TuplesKt.to("mua", "CM"), TuplesKt.to("my", "MM"), TuplesKt.to("naq", "NA"), TuplesKt.to("nb", "NO"), TuplesKt.to("nd", "ZW"), TuplesKt.to("ne", "NP"), TuplesKt.to("nmg", "CM"), TuplesKt.to("nn", "NO"), TuplesKt.to("nr", "ZA"), TuplesKt.to("nus", "SD"), TuplesKt.to("nv", "US"), TuplesKt.to("ny", "MW"), TuplesKt.to("nyn", "UG"), TuplesKt.to("oc", "ES"), TuplesKt.to("oci", "ES"), TuplesKt.to("om", "ET"), TuplesKt.to("or", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), TuplesKt.to("pa", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), TuplesKt.to("pa", "PK"), TuplesKt.to("pan", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), TuplesKt.to("pb", "BR"), TuplesKt.to("pm", "MZ"), TuplesKt.to("por", "PT"), TuplesKt.to(CmcdConfiguration.KEY_PLAYBACK_RATE, "AF"), TuplesKt.to("prs", "AF"), TuplesKt.to("ps", "AF"), TuplesKt.to("qu", "PE"), TuplesKt.to("que", "PE"), TuplesKt.to("rm", "CH"), TuplesKt.to("rn", "BI"), TuplesKt.to("rof", "TZ"), TuplesKt.to("rwk", "TZ"), TuplesKt.to("sa", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), TuplesKt.to("san", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), TuplesKt.to("saq", "KE"), TuplesKt.to(LocalePreferences.FirstDayOfWeek.SATURDAY, HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), TuplesKt.to("sbp", "TZ"), TuplesKt.to("sd", "PK"), TuplesKt.to("sdn", "PK"), TuplesKt.to("se", "NO"), TuplesKt.to("seh", "MZ"), TuplesKt.to("ses", "ML"), TuplesKt.to("sg", "CF"), TuplesKt.to("shi", "MA"), TuplesKt.to("si", "LK"), TuplesKt.to("sl", "SI"), TuplesKt.to("sm", "WS"), TuplesKt.to("smo", "WS"), TuplesKt.to("sn", "ZW"), TuplesKt.to("sp", "ES"), TuplesKt.to("sq", "AL"), TuplesKt.to("sr", "RS"), TuplesKt.to(CmcdConfiguration.KEY_STREAM_TYPE, "LS"), TuplesKt.to(CmcdConfiguration.KEY_STARTUP, "ID"), TuplesKt.to("sv", "SE"), TuplesKt.to("sw", "TZ"), TuplesKt.to("swc", "CD"), TuplesKt.to("ta", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), TuplesKt.to("tat", "RU"), TuplesKt.to("tdt", "TL"), TuplesKt.to("te", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), TuplesKt.to("teo", "UG"), TuplesKt.to("tg", "TJ"), TuplesKt.to("tgk", "TJ"), TuplesKt.to("ti", "ET"), TuplesKt.to("tk", "TM"), TuplesKt.to("tl", "PH"), TuplesKt.to("tm-td", "TL"), TuplesKt.to("tn", "ZA"), TuplesKt.to(HlsSegmentFormat.TS, "ZA"), TuplesKt.to("tso", "ZA"), TuplesKt.to(TtmlNode.TAG_TT, "RU"), TuplesKt.to("tuk", "TM"), TuplesKt.to("twq", "NE"), TuplesKt.to("tzm", "MA"), TuplesKt.to("uk", "UA"), TuplesKt.to("ur", "PK"), TuplesKt.to("vai", "LR"), TuplesKt.to("vi", "VN"), TuplesKt.to("vun", "TZ"), TuplesKt.to("wo", "SN"), TuplesKt.to("xh", "ZA"), TuplesKt.to("xho", "ZA"), TuplesKt.to("xog", "UG"), TuplesKt.to("yav", "CM"), TuplesKt.to("yo", "NG"), TuplesKt.to("yue", "CN"), TuplesKt.to("za", "CN"), TuplesKt.to("zh-hans", "CN"), TuplesKt.to("zh-hant", "TW"), TuplesKt.to("zh", "CN"), TuplesKt.to("zha", "CN"), TuplesKt.to("zu", "ZA"));

    public static /* synthetic */ void getLanguages$annotations() {
    }

    private SubtitleHelper() {
    }

    /* JADX INFO: compiled from: SubtitleHelper.kt */
    @Deprecated(level = DeprecationLevel.WARNING, message = "Default language code changed to IETF BCP 47 tag", replaceWith = @ReplaceWith(expression = "LanguageMetadata(languageName, nativeName, ISO_639_1.ifBlank { ISO_639_2_B }), ISO_639_1, ISO_639_2_B, ISO_639_3, ISO_639_1", imports = {}))
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003JO\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/utils/SubtitleHelper$Language639;", "", "languageName", "", "nativeName", "ISO_639_1", "ISO_639_2_T", "ISO_639_2_B", "ISO_639_3", "ISO_639_6", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getLanguageName", "()Ljava/lang/String;", "getNativeName", "getISO_639_1", "getISO_639_2_T", "getISO_639_2_B", "getISO_639_3", "getISO_639_6", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Language639 {
        private final String ISO_639_1;
        private final String ISO_639_2_B;
        private final String ISO_639_2_T;
        private final String ISO_639_3;
        private final String ISO_639_6;
        private final String languageName;
        private final String nativeName;

        public static /* synthetic */ Language639 copy$default(Language639 language639, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, Object obj) {
            if ((i & 1) != 0) {
                str = language639.languageName;
            }
            if ((i & 2) != 0) {
                str2 = language639.nativeName;
            }
            if ((i & 4) != 0) {
                str3 = language639.ISO_639_1;
            }
            if ((i & 8) != 0) {
                str4 = language639.ISO_639_2_T;
            }
            if ((i & 16) != 0) {
                str5 = language639.ISO_639_2_B;
            }
            if ((i & 32) != 0) {
                str6 = language639.ISO_639_3;
            }
            if ((i & 64) != 0) {
                str7 = language639.ISO_639_6;
            }
            String str8 = str6;
            String str9 = str7;
            String str10 = str5;
            String str11 = str3;
            return language639.copy(str, str2, str11, str4, str10, str8, str9);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLanguageName() {
            return this.languageName;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getNativeName() {
            return this.nativeName;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getISO_639_1() {
            return this.ISO_639_1;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getISO_639_2_T() {
            return this.ISO_639_2_T;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getISO_639_2_B() {
            return this.ISO_639_2_B;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getISO_639_3() {
            return this.ISO_639_3;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getISO_639_6() {
            return this.ISO_639_6;
        }

        public final Language639 copy(String languageName, String nativeName, String ISO_639_1, String ISO_639_2_T, String ISO_639_2_B, String ISO_639_3, String ISO_639_6) {
            Intrinsics.checkNotNullParameter(languageName, "languageName");
            Intrinsics.checkNotNullParameter(nativeName, "nativeName");
            Intrinsics.checkNotNullParameter(ISO_639_1, "ISO_639_1");
            Intrinsics.checkNotNullParameter(ISO_639_2_T, "ISO_639_2_T");
            Intrinsics.checkNotNullParameter(ISO_639_2_B, "ISO_639_2_B");
            Intrinsics.checkNotNullParameter(ISO_639_3, "ISO_639_3");
            Intrinsics.checkNotNullParameter(ISO_639_6, "ISO_639_6");
            return new Language639(languageName, nativeName, ISO_639_1, ISO_639_2_T, ISO_639_2_B, ISO_639_3, ISO_639_6);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Language639)) {
                return false;
            }
            Language639 language639 = (Language639) other;
            return Intrinsics.areEqual(this.languageName, language639.languageName) && Intrinsics.areEqual(this.nativeName, language639.nativeName) && Intrinsics.areEqual(this.ISO_639_1, language639.ISO_639_1) && Intrinsics.areEqual(this.ISO_639_2_T, language639.ISO_639_2_T) && Intrinsics.areEqual(this.ISO_639_2_B, language639.ISO_639_2_B) && Intrinsics.areEqual(this.ISO_639_3, language639.ISO_639_3) && Intrinsics.areEqual(this.ISO_639_6, language639.ISO_639_6);
        }

        public int hashCode() {
            return (((((((((((this.languageName.hashCode() * 31) + this.nativeName.hashCode()) * 31) + this.ISO_639_1.hashCode()) * 31) + this.ISO_639_2_T.hashCode()) * 31) + this.ISO_639_2_B.hashCode()) * 31) + this.ISO_639_3.hashCode()) * 31) + this.ISO_639_6.hashCode();
        }

        public String toString() {
            return "Language639(languageName=" + this.languageName + ", nativeName=" + this.nativeName + ", ISO_639_1=" + this.ISO_639_1 + ", ISO_639_2_T=" + this.ISO_639_2_T + ", ISO_639_2_B=" + this.ISO_639_2_B + ", ISO_639_3=" + this.ISO_639_3 + ", ISO_639_6=" + this.ISO_639_6 + ')';
        }

        public Language639(String languageName, String nativeName, String ISO_639_1, String ISO_639_2_T, String ISO_639_2_B, String ISO_639_3, String ISO_639_6) {
            Intrinsics.checkNotNullParameter(languageName, "languageName");
            Intrinsics.checkNotNullParameter(nativeName, "nativeName");
            Intrinsics.checkNotNullParameter(ISO_639_1, "ISO_639_1");
            Intrinsics.checkNotNullParameter(ISO_639_2_T, "ISO_639_2_T");
            Intrinsics.checkNotNullParameter(ISO_639_2_B, "ISO_639_2_B");
            Intrinsics.checkNotNullParameter(ISO_639_3, "ISO_639_3");
            Intrinsics.checkNotNullParameter(ISO_639_6, "ISO_639_6");
            this.languageName = languageName;
            this.nativeName = nativeName;
            this.ISO_639_1 = ISO_639_1;
            this.ISO_639_2_T = ISO_639_2_T;
            this.ISO_639_2_B = ISO_639_2_B;
            this.ISO_639_3 = ISO_639_3;
            this.ISO_639_6 = ISO_639_6;
        }

        public final String getLanguageName() {
            return this.languageName;
        }

        public final String getNativeName() {
            return this.nativeName;
        }

        public final String getISO_639_1() {
            return this.ISO_639_1;
        }

        public final String getISO_639_2_T() {
            return this.ISO_639_2_T;
        }

        public final String getISO_639_2_B() {
            return this.ISO_639_2_B;
        }

        public final String getISO_639_3() {
            return this.ISO_639_3;
        }

        public final String getISO_639_6() {
            return this.ISO_639_6;
        }
    }

    /* JADX INFO: compiled from: SubtitleHelper.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\u0014\u001a\u00020\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003J\u0012\u0010\u0016\u001a\u00020\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003JO\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004J\n\u0010$\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006%"}, d2 = {"Lcom/lagradost/cloudstream3/utils/SubtitleHelper$LanguageMetadata;", "", "languageName", "", "nativeName", "IETF_tag", "ISO_639_1", "ISO_639_2_B", "ISO_639_3", "openSubtitles", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getLanguageName", "()Ljava/lang/String;", "getNativeName", "getIETF_tag", "getISO_639_1", "getISO_639_2_B", "getISO_639_3", "getOpenSubtitles", "localizedName", "localizedTo", "nameNextToFlagEmoji", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class LanguageMetadata {
        private final String IETF_tag;
        private final String ISO_639_1;
        private final String ISO_639_2_B;
        private final String ISO_639_3;
        private final String languageName;
        private final String nativeName;
        private final String openSubtitles;

        public static /* synthetic */ LanguageMetadata copy$default(LanguageMetadata languageMetadata, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, Object obj) {
            if ((i & 1) != 0) {
                str = languageMetadata.languageName;
            }
            if ((i & 2) != 0) {
                str2 = languageMetadata.nativeName;
            }
            if ((i & 4) != 0) {
                str3 = languageMetadata.IETF_tag;
            }
            if ((i & 8) != 0) {
                str4 = languageMetadata.ISO_639_1;
            }
            if ((i & 16) != 0) {
                str5 = languageMetadata.ISO_639_2_B;
            }
            if ((i & 32) != 0) {
                str6 = languageMetadata.ISO_639_3;
            }
            if ((i & 64) != 0) {
                str7 = languageMetadata.openSubtitles;
            }
            String str8 = str6;
            String str9 = str7;
            String str10 = str5;
            String str11 = str3;
            return languageMetadata.copy(str, str2, str11, str4, str10, str8, str9);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLanguageName() {
            return this.languageName;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getNativeName() {
            return this.nativeName;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getIETF_tag() {
            return this.IETF_tag;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getISO_639_1() {
            return this.ISO_639_1;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getISO_639_2_B() {
            return this.ISO_639_2_B;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getISO_639_3() {
            return this.ISO_639_3;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getOpenSubtitles() {
            return this.openSubtitles;
        }

        public final LanguageMetadata copy(String languageName, String nativeName, String IETF_tag, String ISO_639_1, String ISO_639_2_B, String ISO_639_3, String openSubtitles) {
            Intrinsics.checkNotNullParameter(languageName, "languageName");
            Intrinsics.checkNotNullParameter(nativeName, "nativeName");
            Intrinsics.checkNotNullParameter(IETF_tag, "IETF_tag");
            Intrinsics.checkNotNullParameter(ISO_639_1, "ISO_639_1");
            Intrinsics.checkNotNullParameter(ISO_639_2_B, "ISO_639_2_B");
            Intrinsics.checkNotNullParameter(ISO_639_3, "ISO_639_3");
            Intrinsics.checkNotNullParameter(openSubtitles, "openSubtitles");
            return new LanguageMetadata(languageName, nativeName, IETF_tag, ISO_639_1, ISO_639_2_B, ISO_639_3, openSubtitles);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LanguageMetadata)) {
                return false;
            }
            LanguageMetadata languageMetadata = (LanguageMetadata) other;
            return Intrinsics.areEqual(this.languageName, languageMetadata.languageName) && Intrinsics.areEqual(this.nativeName, languageMetadata.nativeName) && Intrinsics.areEqual(this.IETF_tag, languageMetadata.IETF_tag) && Intrinsics.areEqual(this.ISO_639_1, languageMetadata.ISO_639_1) && Intrinsics.areEqual(this.ISO_639_2_B, languageMetadata.ISO_639_2_B) && Intrinsics.areEqual(this.ISO_639_3, languageMetadata.ISO_639_3) && Intrinsics.areEqual(this.openSubtitles, languageMetadata.openSubtitles);
        }

        public int hashCode() {
            return (((((((((((this.languageName.hashCode() * 31) + this.nativeName.hashCode()) * 31) + this.IETF_tag.hashCode()) * 31) + this.ISO_639_1.hashCode()) * 31) + this.ISO_639_2_B.hashCode()) * 31) + this.ISO_639_3.hashCode()) * 31) + this.openSubtitles.hashCode();
        }

        public String toString() {
            return "LanguageMetadata(languageName=" + this.languageName + ", nativeName=" + this.nativeName + ", IETF_tag=" + this.IETF_tag + ", ISO_639_1=" + this.ISO_639_1 + ", ISO_639_2_B=" + this.ISO_639_2_B + ", ISO_639_3=" + this.ISO_639_3 + ", openSubtitles=" + this.openSubtitles + ')';
        }

        public LanguageMetadata(String languageName, String nativeName, String IETF_tag, String ISO_639_1, String ISO_639_2_B, String ISO_639_3, String openSubtitles) {
            Intrinsics.checkNotNullParameter(languageName, "languageName");
            Intrinsics.checkNotNullParameter(nativeName, "nativeName");
            Intrinsics.checkNotNullParameter(IETF_tag, "IETF_tag");
            Intrinsics.checkNotNullParameter(ISO_639_1, "ISO_639_1");
            Intrinsics.checkNotNullParameter(ISO_639_2_B, "ISO_639_2_B");
            Intrinsics.checkNotNullParameter(ISO_639_3, "ISO_639_3");
            Intrinsics.checkNotNullParameter(openSubtitles, "openSubtitles");
            this.languageName = languageName;
            this.nativeName = nativeName;
            this.IETF_tag = IETF_tag;
            this.ISO_639_1 = ISO_639_1;
            this.ISO_639_2_B = ISO_639_2_B;
            this.ISO_639_3 = ISO_639_3;
            this.openSubtitles = openSubtitles;
        }

        public final String getLanguageName() {
            return this.languageName;
        }

        public final String getNativeName() {
            return this.nativeName;
        }

        public final String getIETF_tag() {
            return this.IETF_tag;
        }

        public final String getISO_639_1() {
            return this.ISO_639_1;
        }

        public final String getISO_639_2_B() {
            return this.ISO_639_2_B;
        }

        public final String getISO_639_3() {
            return this.ISO_639_3;
        }

        public final String getOpenSubtitles() {
            return this.openSubtitles;
        }

        public static /* synthetic */ String localizedName$default(LanguageMetadata languageMetadata, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = null;
            }
            return languageMetadata.localizedName(str);
        }

        public final String localizedName(String localizedTo) {
            Locale localeForLanguageTag = Locale.forLanguageTag(this.IETF_tag);
            if (localizedTo == null) {
                localizedTo = SubtitleHelperKt.getCurrentLocale();
            }
            String displayName = localeForLanguageTag.getDisplayName(Locale.forLanguageTag(localizedTo));
            String str = localeForLanguageTag.getLanguage() + " (";
            if (!StringsKt.equals(displayName, this.IETF_tag, true)) {
                Intrinsics.checkNotNull(displayName);
                if (!StringsKt.contains((CharSequence) displayName, (CharSequence) str, true)) {
                    Intrinsics.checkNotNull(displayName);
                    return displayName;
                }
            }
            return this.nativeName;
        }

        public static /* synthetic */ String nameNextToFlagEmoji$default(LanguageMetadata languageMetadata, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = null;
            }
            return languageMetadata.nameNextToFlagEmoji(str);
        }

        public final String nameNextToFlagEmoji(String localizedTo) {
            String flagFromIso = SubtitleHelper.INSTANCE.getFlagFromIso(this.IETF_tag);
            if (flagFromIso == null) {
                flagFromIso = "🇦🇦";
            }
            return flagFromIso + Typography.nbsp + localizedName(localizedTo);
        }
    }

    static /* synthetic */ LanguageMetadata getLanguageDataFromName$default(SubtitleHelper subtitleHelper, String str, Boolean bool, int i, Object obj) {
        if ((i & 2) != 0) {
            bool = false;
        }
        return subtitleHelper.getLanguageDataFromName(str, bool);
    }

    private final LanguageMetadata getLanguageDataFromName(String languageName, Boolean halfMatch) {
        String str = languageName;
        if (str != null && !StringsKt.isBlank(str) && languageName.length() >= 2) {
            Regex regex = new Regex("\\([^)]*(?:dub|sub|original|audio|code)[^)]*\\)|[\\u064B-\\u065B]|\\d|[^\\p{L}\\p{Mn}\\p{Mc}\\p{Me} ()-]");
            String lowerCase = languageName.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            String string = StringsKt.trim((CharSequence) regex.replace(lowerCase, "")).toString();
            Integer num = indexMapLanguageName.get(string);
            int iIntValue = (num == null && (num = indexMapNativeName.get(string)) == null && (num = indexMapIETF_tag.get(string)) == null) ? -1 : num.intValue();
            List<LanguageMetadata> list = languages;
            LanguageMetadata languageMetadata = (LanguageMetadata) CollectionsKt.getOrNull(list, iIntValue);
            if (languageMetadata != null) {
                return languageMetadata;
            }
            if (Intrinsics.areEqual((Object) halfMatch, (Object) true)) {
                Pair pair = TuplesKt.to(null, 0);
                for (LanguageMetadata languageMetadata2 : list) {
                    Levenshtein levenshtein = Levenshtein.INSTANCE;
                    String lowerCase2 = languageMetadata2.getLanguageName().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                    int iRatio$default = Levenshtein.ratio$default(levenshtein, string, lowerCase2, null, 4, null);
                    Levenshtein levenshtein2 = Levenshtein.INSTANCE;
                    String lowerCase3 = languageMetadata2.getNativeName().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
                    int iMax = Math.max(iRatio$default, Levenshtein.ratio$default(levenshtein2, string, lowerCase3, null, 4, null));
                    String str2 = string;
                    if (StringsKt.contains((CharSequence) str2, (CharSequence) languageMetadata2.getLanguageName(), true) || StringsKt.contains((CharSequence) str2, (CharSequence) languageMetadata2.getNativeName(), true) || iMax > 80) {
                        if (iMax > ((Number) pair.getSecond()).intValue()) {
                            pair = TuplesKt.to(languageMetadata2, Integer.valueOf(iMax));
                        }
                    }
                }
                return (LanguageMetadata) pair.getFirst();
            }
        }
        return null;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Default language code changed to IETF BCP 47 tag", replaceWith = @ReplaceWith(expression = "fromLanguageToTagIETF(input, looseCheck)", imports = {}))
    public final String fromLanguageToTwoLetters(String input, boolean looseCheck) {
        Intrinsics.checkNotNullParameter(input, "input");
        LanguageMetadata languageDataFromName = getLanguageDataFromName(input, Boolean.valueOf(looseCheck));
        if (languageDataFromName != null) {
            return languageDataFromName.getISO_639_1();
        }
        return null;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Default language code changed to IETF BCP 47 tag", replaceWith = @ReplaceWith(expression = "fromLanguageToTagIETF(input)", imports = {}))
    public final String fromLanguageToThreeLetters(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        LanguageMetadata languageDataFromName$default = getLanguageDataFromName$default(this, input, null, 2, null);
        if (languageDataFromName$default != null) {
            return languageDataFromName$default.getISO_639_3();
        }
        return null;
    }

    public static /* synthetic */ String fromLanguageToTagIETF$default(SubtitleHelper subtitleHelper, String str, Boolean bool, int i, Object obj) {
        if ((i & 2) != 0) {
            bool = false;
        }
        return subtitleHelper.fromLanguageToTagIETF(str, bool);
    }

    public final String fromLanguageToTagIETF(String languageName, Boolean halfMatch) {
        LanguageMetadata languageDataFromName = getLanguageDataFromName(languageName, halfMatch);
        if (languageDataFromName != null) {
            return languageDataFromName.getIETF_tag();
        }
        return null;
    }

    private final LanguageMetadata getLanguageDataFromCode(String languageCode) {
        int iIntValue;
        String str = languageCode;
        if (str == null || StringsKt.isBlank(str) || languageCode.length() < 2) {
            return null;
        }
        String lowerCase = languageCode.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String string = StringsKt.trim((CharSequence) lowerCase).toString();
        Integer num = indexMapIETF_tag.get(string);
        if (num == null && (num = indexMapISO_639_1.get(string)) == null && (num = indexMapISO_639_3.get(string)) == null && (num = indexMapISO_639_2_B.get(string)) == null) {
            Integer num2 = indexMapOpenSubtitles.get(string);
            iIntValue = num2 != null ? num2.intValue() : -1;
        } else {
            iIntValue = num.intValue();
        }
        return (LanguageMetadata) CollectionsKt.getOrNull(languages, iIntValue);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Default language code changed to IETF BCP 47 tag", replaceWith = @ReplaceWith(expression = "fromTagToEnglishLanguageName(input)", imports = {}))
    public final String fromTwoLettersToLanguage(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        LanguageMetadata languageDataFromCode = getLanguageDataFromCode(input);
        if (languageDataFromCode != null) {
            return languageDataFromCode.getLanguageName();
        }
        return null;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Default language code changed to IETF BCP 47 tag", replaceWith = @ReplaceWith(expression = "fromTagToEnglishLanguageName(input)", imports = {}))
    public final String fromThreeLettersToLanguage(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        LanguageMetadata languageDataFromCode = getLanguageDataFromCode(input);
        if (languageDataFromCode != null) {
            return languageDataFromCode.getLanguageName();
        }
        return null;
    }

    public static /* synthetic */ String fromTagToLanguageName$default(SubtitleHelper subtitleHelper, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return subtitleHelper.fromTagToLanguageName(str, str2);
    }

    public final String fromTagToLanguageName(String languageCode, String localizedTo) {
        LanguageMetadata languageDataFromCode = getLanguageDataFromCode(languageCode);
        if (languageDataFromCode != null) {
            return languageDataFromCode.localizedName(localizedTo);
        }
        return null;
    }

    public final String fromTagToEnglishLanguageName(String languageCode) {
        LanguageMetadata languageDataFromCode = getLanguageDataFromCode(languageCode);
        if (languageDataFromCode != null) {
            return languageDataFromCode.getLanguageName();
        }
        return null;
    }

    public final String fromCodeToOpenSubtitlesTag(String languageCode) {
        LanguageMetadata languageDataFromCode = getLanguageDataFromCode(languageCode);
        if (languageDataFromCode != null) {
            return languageDataFromCode.getOpenSubtitles();
        }
        return null;
    }

    public final String fromCodeToLangTagIETF(String languageCode) {
        LanguageMetadata languageDataFromCode = getLanguageDataFromCode(languageCode);
        if (languageDataFromCode != null) {
            return languageDataFromCode.getIETF_tag();
        }
        return null;
    }

    public final boolean isWellFormedTagIETF(String langTagIETF) {
        String str = langTagIETF;
        if (str == null || StringsKt.isBlank(str) || langTagIETF.length() < 2) {
            return false;
        }
        return new Regex("(^[xX](\\x2d\\p{Alnum}{1,8})*$)\n|(((^\\p{Alpha}{2,8}(?=\\x2d|$)){1}\n((\\x2d\\p{Alpha}{3})(?=\\x2d|$)){0,3}\n(\\x2d\\p{Alpha}{4}(?=\\x2d|$))?\n(\\x2d(\\p{Alpha}{2}|\\d{3})(?=\\x2d|$))?\n(\\x2d(\\d\\p{Alnum}{3}|\\p{Alnum}{5,8})(?=\\x2d|$))*)\n((\\x2d([a-wyzA-WYZ](?=\\x2d))(\\x2d(\\p{Alnum}{2,8})+)*))*\n(\\x2d[xX](\\x2d\\p{Alnum}{1,8})*)?)$").matches(str);
    }

    public final String getFlagFromIso(String inp) {
        String upperCase;
        String lowerCase;
        List<String> groupValues;
        String str = inp;
        if (str != null && !StringsKt.isBlank(str) && inp.length() >= 2) {
            Regex regex = new Regex("[🇦-🇿]{2}");
            MatchResult matchResultFind$default = Regex.find$default(new Regex("[-_](\\p{Alnum}{2,3})$", RegexOption.IGNORE_CASE), str, 0, 2, null);
            String str2 = (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null) ? null : groupValues.get(1);
            Map<String, String> map = lang2country;
            String lowerCase2 = inp.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            String flagFromCountry2Letters = getFlagFromCountry2Letters(map.get(lowerCase2));
            if (flagFromCountry2Letters == null) {
                if (str2 != null) {
                    upperCase = str2.toUpperCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                } else {
                    upperCase = null;
                }
                String flagFromCountry2Letters2 = getFlagFromCountry2Letters(upperCase);
                if (flagFromCountry2Letters2 == null) {
                    String upperCase2 = inp.toUpperCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
                    flagFromCountry2Letters = getFlagFromCountry2Letters(upperCase2);
                    if (flagFromCountry2Letters == null) {
                        if (str2 != null) {
                            lowerCase = str2.toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                        } else {
                            lowerCase = null;
                        }
                        flagFromCountry2Letters = getFlagFromCountry2Letters(map.get(lowerCase));
                        if (flagFromCountry2Letters == null) {
                            flagFromCountry2Letters = "";
                        }
                    }
                } else {
                    flagFromCountry2Letters = flagFromCountry2Letters2;
                }
            }
            if (StringsKt.equals(inp, "qt", true)) {
                return "🦍";
            }
            if (regex.matches(flagFromCountry2Letters)) {
                return flagFromCountry2Letters;
            }
        }
        return null;
    }

    public static /* synthetic */ String getNameNextToFlagEmoji$default(SubtitleHelper subtitleHelper, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return subtitleHelper.getNameNextToFlagEmoji(str, str2);
    }

    public final String getNameNextToFlagEmoji(String languageCode, String localizedTo) {
        LanguageMetadata languageDataFromCode = getLanguageDataFromCode(languageCode);
        if (languageDataFromCode != null) {
            return languageDataFromCode.nameNextToFlagEmoji(localizedTo);
        }
        return null;
    }

    private final String getFlagFromCountry2Letters(String countryLetters) {
        String str = countryLetters;
        if (str == null || StringsKt.isBlank(str) || countryLetters.length() != 2) {
            return null;
        }
        String upperCase = countryLetters.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        return getFlagFromCountry2Letters$toSurrogatePair(upperCase.charAt(0) + 61861) + getFlagFromCountry2Letters$toSurrogatePair(upperCase.charAt(1) + 61861);
    }

    private static final String getFlagFromCountry2Letters$toSurrogatePair(int i) {
        int i2 = i - 65536;
        int i3 = (i2 >> 10) + GeneratorBase.SURR1_FIRST;
        int i4 = (i2 & AnalyticsListener.EVENT_DRM_KEYS_LOADED) + 56320;
        StringBuilder sb = new StringBuilder();
        sb.append((char) i3);
        sb.append((char) i4);
        return sb.toString();
    }

    static {
        List<LanguageMetadata> listListOf = CollectionsKt.listOf((Object[]) new LanguageMetadata[]{new LanguageMetadata("Afar", "Afaraf", "aa", "aa", "aar", "aar", ""), new LanguageMetadata("Afrikaans", "Afrikaans", "af", "af", "afr", "afr", "af"), new LanguageMetadata("Akan", "Akan", "ak", "ak", "aka", "aka", ""), new LanguageMetadata("Albanian", "Shqip", "sq", "sq", "", "sqi", "sq"), new LanguageMetadata("Amharic", "አማርኛ", "am", "am", "amh", "amh", "am"), new LanguageMetadata("Arabic", "العربية", "ar", "ar", "ara", "ara", "ar"), new LanguageMetadata("Arabic (Levantine)", "عربي شامي", "apc", "", "ajp", "apc", "ar"), new LanguageMetadata("Arabic (Najdi)", "عربي شامي", "ars", "", "", "ars", "ar"), new LanguageMetadata("Aragonese", "aragonés", "an", "an", "arg", "arg", "an"), new LanguageMetadata("Armenian", "Հայերեն", "hy", "hy", "", "hye", "hy"), new LanguageMetadata("Assamese", "অসমীয়া", "as", "as", "asm", "asm", "as"), new LanguageMetadata("Avaric", "авар мацӀ, магӀарул мацӀ", CmcdData.OBJECT_TYPE_MUXED_AUDIO_AND_VIDEO, CmcdData.OBJECT_TYPE_MUXED_AUDIO_AND_VIDEO, "ava", "ava", ""), new LanguageMetadata("Aymara", "aymar aru", "ay", "ay", "aym", "aym", ""), new LanguageMetadata("Azerbaijani", "Azərbaycan", "az", "az", "aze", "aze", "az-az"), new LanguageMetadata("Azerbaijani (South)", "Azərbaycan (Cənubi)", "azb", "", "", "azb", "az-zb"), new LanguageMetadata("Bambara", "bamanankan", "bm", "bm", "bam", "bam", ""), new LanguageMetadata("Basque", "euskara, euskera", "eu", "eu", "", "eus", "eu"), new LanguageMetadata("Belarusian", "беларуская мова", "be", "be", "bel", "bel", "be"), new LanguageMetadata("Bengali", "বাংলা", "bn", "bn", "ben", "ben", "bn"), new LanguageMetadata("Bosnian", "bosanski jezik", CmcdConfiguration.KEY_BUFFER_STARVATION, CmcdConfiguration.KEY_BUFFER_STARVATION, "bos", "bos", CmcdConfiguration.KEY_BUFFER_STARVATION), new LanguageMetadata("Breton", "brezhoneg", "br", "br", "bre", "bre", "br"), new LanguageMetadata("Bulgarian", "български език", "bg", "bg", "bul", "bul", "bg"), new LanguageMetadata("Burmese", "ဗမာစာ", "my", "my", "", "mya", "my"), new LanguageMetadata("Catalan", "català", "ca", "ca", "cat", "cat", "ca"), new LanguageMetadata("Chichewa", "chiCheŵa, chinyanja", "ny", "ny", "nya", "nya", ""), new LanguageMetadata("Chinese", "中文, 汉语, 漢語", "zh", "zh", "chi", "zho", "ze"), new LanguageMetadata("Chinese (Cantonese)", "廣東話, 广东话", "yue", "", "", "yue", "zh-ca"), new LanguageMetadata("Chinese (simplified)", "汉语", "zh-hans", "", "", "", "zh-cn"), new LanguageMetadata("Chinese (Taiwan)", "正體中文(臺灣)", "zh-hant-tw", "", "", "", "zh-tw"), new LanguageMetadata("Chinese (traditional)", "漢語", "zh-hant", "", "", "", "zh-tw"), new LanguageMetadata("Croatian", "hrvatski jezik", "hr", "hr", "hrv", "hrv", "hr"), new LanguageMetadata("Czech", "čeština, český jazyk", "cs", "cs", "", "ces", "cs"), new LanguageMetadata("Danish", "dansk", "da", "da", "dan", "dan", "da"), new LanguageMetadata("Dari", "دری", "prs", "", "", "prs", CmcdConfiguration.KEY_PLAYBACK_RATE), new LanguageMetadata("Dutch", "Nederlands, Vlaams", "nl", "nl", "", "nld", "nl"), new LanguageMetadata("Dzongkha", "རྫོང་ཁ", "dz", "dz", "dzo", "dzo", ""), new LanguageMetadata("English", "English", "en", "en", "eng", "eng", "en"), new LanguageMetadata("Esperanto", "Esperanto", "eo", "eo", "epo", "epo", "eo"), new LanguageMetadata("Estonian", "eesti, eesti keel", "et", "et", "est", "est", "et"), new LanguageMetadata("Ewe", "Eʋegbe", "ee", "ee", "ewe", "ewe", ""), new LanguageMetadata("Extremaduran", "Estremeñu", "ext", "", "", "ext", "ex"), new LanguageMetadata("Faroese", "føroyskt", "fo", "fo", "fao", "fao", ""), new LanguageMetadata("Fijian", "vosa Vakaviti", "fj", "fj", "fij", "fij", ""), new LanguageMetadata("Filipino", "Wikang Filipino", "fil", "", "fil", "fil", ""), new LanguageMetadata("Finnish", "suomi, suomen kieli", "fi", "fi", "fin", "fin", "fi"), new LanguageMetadata("French", "Français", "fr", "fr", "", "fra", "fr"), new LanguageMetadata("Fula", "Fulfulde, Pulaar, Pular", "ff", "ff", "ful", "ful", ""), new LanguageMetadata("Galician", "Galego", "gl", "gl", "glg", "glg", "gl"), new LanguageMetadata("Ganda", "Luganda", "lg", "lg", "lug", "lug", ""), new LanguageMetadata("Georgian", "ქართული", "ka", "ka", "", "kat", "ka"), new LanguageMetadata("German", "Deutsch", "de", "de", "", "deu", "de"), new LanguageMetadata("Greek", "ελληνικά", "el", "el", "", "ell", "el"), new LanguageMetadata("Guarani", "Avañe'ẽ", "gn", "gn", "grn", "gug", ""), new LanguageMetadata("Gujarati", "ગુજરાતી", "gu", "gu", "guj", "guj", ""), new LanguageMetadata("Haitian", "Kreyòl ayisyen", "ht", "ht", "hat", "hat", ""), new LanguageMetadata("Hausa", "(Hausa) هَوُسَ", "ha", "ha", "hau", "hau", ""), new LanguageMetadata("Hebrew", "עברית", "he", "iw", "heb", "heb", "he"), new LanguageMetadata("Hindi", "हिन्दी, हिंदी", "hi", "hi", "hin", "hin", "hi"), new LanguageMetadata("Hungarian", "Magyar", "hu", "hu", "hun", "hun", "hu"), new LanguageMetadata("Icelandic", "Íslenska", "is", "is", "", "isl", "is"), new LanguageMetadata("Ido", "Ido", "io", "io", "ido", "ido", ""), new LanguageMetadata("Igbo", "Asụsụ Igbo", "ig", "ig", "ibo", "ibo", "ig"), new LanguageMetadata("Indonesian", "Bahasa Indonesia", "id", "in", "ind", "ind", "id"), new LanguageMetadata("Interlingua", "Interlingua", "ia", "ia", "ina", "ina", "ia"), new LanguageMetadata("Interlingue", "Interlingue (originally Occidental)", "ie", "ie", "ile", "ile", ""), new LanguageMetadata("Irish", "Gaeilge", "ga", "ga", "gle", "gle", "ga"), new LanguageMetadata("Italian", "italiano", "it", "it", "ita", "ita", "it"), new LanguageMetadata("Japanese", "日本語 (にほんご)", "ja", "ja", "jpn", "jpn", "ja"), new LanguageMetadata("Javanese", "ꦧꦱꦗꦮ", "jv", "jv", "jav", "jvn", ""), new LanguageMetadata("Kalaallisut", "kalaallisut, kalaallit oqaasii", "kl", "kl", "kal", "kal", ""), new LanguageMetadata("Kannada", "ಕನ್ನಡ", "kn", "kn", "kan", "kan", "kn"), new LanguageMetadata("Kanuri", "Kanuri", "kr", "kr", "kau", "kau", ""), new LanguageMetadata("Kashmiri", "कश्मीरी, كشميري\u200e", "ks", "ks", "kas", "kas", ""), new LanguageMetadata("Kazakh", "қазақ тілі", "kk", "kk", "kaz", "kaz", "kk"), new LanguageMetadata("Khmer", "ខ្មែរ, ខេមរភាសា, ភាសាខ្មែរ", "km", "km", "khm", "khm", "km"), new LanguageMetadata("Kikuyu", "Gĩkũyũ", "ki", "ki", "kik", "kik", ""), new LanguageMetadata("Kinyarwanda", "Ikinyarwanda", "rw", "rw", "kin", "kin", ""), new LanguageMetadata("Kirundi", "Ikirundi", "rn", "rn", "run", "run", ""), new LanguageMetadata("Kongo", "Kikongo", "kg", "kg", "kon", "kon", ""), new LanguageMetadata("Korean", "한국어, 조선어", "ko", "ko", "kor", "kor", "ko"), new LanguageMetadata("Kurdish", "Kurdî, كوردی\u200e", "ku", "ku", "kur", "kur", "ku"), new LanguageMetadata("Kyrgyz", "Кыргызча, Кыргыз тили", "ky", "ky", "kir", "kir", ""), new LanguageMetadata("Lao", "ພາສາລາວ", "lo", "lo", "lao", "lao", ""), new LanguageMetadata("Latin", "Latine", "la", "la", "lat", "lat", ""), new LanguageMetadata("Latvian", "latviešu valoda", "lv", "lv", "lav", "lav", "lv"), new LanguageMetadata("Lingala", "Lingála", "ln", "ln", "lin", "lin", ""), new LanguageMetadata("Lithuanian", "lietuvių kalba", "lt", "lt", "lit", "lit", "lt"), new LanguageMetadata("Luba-Katanga", "Tshiluba", "lu", "lu", "lub", "lub", ""), new LanguageMetadata("Luxembourgish", "Lëtzebuergesch", "lb", "lb", "ltz", "ltz", "lb"), new LanguageMetadata("Macedonian", "македонски", "mk", "mk", "", "mkd", "mk"), new LanguageMetadata("Malagasy", "fiteny malagasy", "mg", "mg", "mlg", "mlg", ""), new LanguageMetadata("Malay", "Bahasa Melayu, بهاس ملايو\u200e", "ms", "ms", "", "msa", "ms"), new LanguageMetadata("Malayalam", "മലയാളം", "ml", "ml", "mal", "mal", "ml"), new LanguageMetadata("Maltese", "Malti", "mt", "mt", "mlt", "mlt", ""), new LanguageMetadata("Manx", "Gaelg, Gailck", "gv", "gv", "glv", "glv", ""), new LanguageMetadata("Marathi", "मराठी", "mr", "mr", "mar", "mar", "mr"), new LanguageMetadata("Marshallese", "Kajin M̧ajeļ", "mh", "mh", "mah", "mah", ""), new LanguageMetadata("Meitei", "ꯃꯅꯤꯄꯨꯔꯤ, মণিপুরী", "mni", "", "mni", "mni", "ma"), new LanguageMetadata("Mexican Spanish", "Español mexicano", "es-MX", "mx", "", "", ""), new LanguageMetadata("Mongolian", "Монгол хэл", "mn", "mn", LocalePreferences.FirstDayOfWeek.MONDAY, LocalePreferences.FirstDayOfWeek.MONDAY, "mn"), new LanguageMetadata("Montenegrin", "crnogorski, црногорски", "cnr", "", "cnr", "cnr", "me"), new LanguageMetadata("Navajo", "Diné bizaad", "nv", "nv", "nav", "nav", "nv"), new LanguageMetadata("Nepali", "नेपाली", "ne", "ne", "nep", "nep", "ne"), new LanguageMetadata("Northern Ndebele", "isiNdebele", "nd", "nd", "nde", "nde", ""), new LanguageMetadata("Northern Sami", "Davvisámegiella", "se", "se", "sme", "sme", "se"), new LanguageMetadata("Norwegian", "Norsk", "no", "no", CmcdConfiguration.KEY_NEXT_OBJECT_REQUEST, CmcdConfiguration.KEY_NEXT_OBJECT_REQUEST, "no"), new LanguageMetadata("Norwegian Bokmål", "Norsk bokmål", "nb", "nb", "nob", "nob", ""), new LanguageMetadata("Norwegian Nynorsk", "Norsk nynorsk", "nn", "nn", "nno", "nno", ""), new LanguageMetadata("Nuosu", "ꆈꌠ꒿ Nuosuhxop", "ii", "ii", "iii", "iii", ""), new LanguageMetadata("Occitan", "occitan, lenga d'òc", "oc", "oc", "oci", "oci", "oc"), new LanguageMetadata("Oriya", "ଓଡ଼ିଆ", "or", "or", "ori", "ori", "or"), new LanguageMetadata("Oromo", "Afaan Oromoo", "om", "om", "orm", "orm", ""), new LanguageMetadata("Panjabi", "ਪੰਜਾਬੀ, پنجابی\u200e", "pa", "pa", "pan", "pan", ""), new LanguageMetadata("Pashto", "پښتو", "ps", "ps", "pus", "pus", "ps"), new LanguageMetadata("Persian (Farsi)", "فارسی", "fa", "fa", "", "fas", "fa"), new LanguageMetadata("Polish", "Polski, polszczyzna", "pl", "pl", "pol", "pol", "pl"), new LanguageMetadata("Portuguese", "Português", "pt", "pt", "por", "por", "pt-pt"), new LanguageMetadata("Portuguese (Brazil)", "Português (Brasil)", "pt-br", "", "", "", "pt-br"), new LanguageMetadata("Portuguese (Mozambique)", "Português (Moçambique)", "pt-mz", "", "", "", "pm"), new LanguageMetadata("Quechua", "Runa Simi, Kichwa", "qu", "qu", "que", "que", ""), new LanguageMetadata("Romanian", "Română", "ro", "ro", "", "ron", "ro"), new LanguageMetadata("Romansh", "rumantsch grischun", "rm", "rm", "roh", "roh", ""), new LanguageMetadata("Russian", "Русский", "ru", "ru", "rus", "rus", "ru"), new LanguageMetadata("Samoan", "gagana fa'a Samoa", "sm", "sm", "smo", "smo", ""), new LanguageMetadata("Sango", "yângâ tî sängö", "sg", "sg", "sag", "sag", ""), new LanguageMetadata("Sanskrit", "संस्कृतम्", "sa", "sa", "san", "san", ""), new LanguageMetadata("Santali", "ᱥᱟᱱᱛᱟᱲᱤ", LocalePreferences.FirstDayOfWeek.SATURDAY, "", "", LocalePreferences.FirstDayOfWeek.SATURDAY, "sx"), new LanguageMetadata("Scottish Gaelic", "Gàidhlig", "gd", "gd", "gla", "gla", "gd"), new LanguageMetadata("Serbian", "српски језик", "sr", "sr", "srp", "srp", "sr"), new LanguageMetadata("Shona", "chiShona", "sn", "sn", "sna", "sna", ""), new LanguageMetadata("Sindhi", "सिन्धी, سنڌي، سندھی\u200e", "sd", "sd", "snd", "snd", "sd"), new LanguageMetadata("Sinhala", "සිංහල", "si", "si", "sin", "sin", "si"), new LanguageMetadata("Slovak", "slovenčina, slovenský jazyk", "sk", "sk", "", "slk", "sk"), new LanguageMetadata("Slovenian", "slovenski jezik, slovenščina", "sl", "sl", "slv", "slv", "sl"), new LanguageMetadata("Somali", "Soomaaliga, af Soomaali", "so", "so", "som", "som", "so"), new LanguageMetadata("Sotho", "Sesotho", CmcdConfiguration.KEY_STREAM_TYPE, CmcdConfiguration.KEY_STREAM_TYPE, "sot", "sot", ""), new LanguageMetadata("Southern Ndebele", "isiNdebele", "nr", "nr", "nbl", "nbl", ""), new LanguageMetadata("Spanish", "Español", "es", "es", "spa", "spa", "es"), new LanguageMetadata("Spanish (Europe)", "Español (Europa)", "es-es", "", "", "", "sp"), new LanguageMetadata("Spanish (Latin America)", "Español (Latinoamérica)", "es-419", "", "", "", "ea"), new LanguageMetadata("Sundanese", "Basa Sunda", CmcdConfiguration.KEY_STARTUP, CmcdConfiguration.KEY_STARTUP, LocalePreferences.FirstDayOfWeek.SUNDAY, LocalePreferences.FirstDayOfWeek.SUNDAY, ""), new LanguageMetadata("Swahili", "Kiswahili", "sw", "sw", "swa", "swa", "sw"), new LanguageMetadata("Swedish", "Svenska", "sv", "sv", "swe", "swe", "sv"), new LanguageMetadata("Tagalog", "Wikang Tagalog, ᜆᜄᜎᜓᜄ᜔", "tl", "tl", "", "tlg", "tl"), new LanguageMetadata("Tajik", "тоҷикӣ, toçikī, تاجیکی\u200e", "tg", "tg", "tgk", "tgk", ""), new LanguageMetadata("Tamil", "தமிழ்", "ta", "ta", "tam", "tam", "ta"), new LanguageMetadata("Tatar", "татар теле, tatar tele", TtmlNode.TAG_TT, TtmlNode.TAG_TT, "tat", "tat", TtmlNode.TAG_TT), new LanguageMetadata("Telugu", "తెలుగు", "te", "te", "tel", "tel", "te"), new LanguageMetadata("Tetum", "Tetun", "tdt", "", "", "tdt", "tm-td"), new LanguageMetadata("Thai", "ไทย", "th", "th", "tha", "tha", "th"), new LanguageMetadata("Tibetan Standard", "བོད་ཡིག", "bo", "bo", "", "bod", ""), new LanguageMetadata("Tigrinya", "ትግርኛ", "ti", "ti", "tir", "tir", ""), new LanguageMetadata("Toki Pona", "toki pona", "tok", "", "", "tok", "tp"), new LanguageMetadata("Tonga", "faka Tonga", TypedValues.TransitionType.S_TO, TypedValues.TransitionType.S_TO, "ton", "ton", ""), new LanguageMetadata("Tsonga", "Xitsonga", HlsSegmentFormat.TS, HlsSegmentFormat.TS, "tso", "tso", ""), new LanguageMetadata("Tswana", "Setswana", "tn", "tn", "tsn", "tsn", ""), new LanguageMetadata("Turkish", "Türkçe", "tr", "tr", "tur", "tur", "tr"), new LanguageMetadata("Turkmen", "Türkmen, Түркмен", "tk", "tk", "tuk", "tuk", "tk"), new LanguageMetadata("Ukrainian", "Українська", "uk", "uk", "ukr", "ukr", "uk"), new LanguageMetadata("Urdu", "اردو", "ur", "ur", "urd", "urd", "ur"), new LanguageMetadata("Uzbek", "Oʻzbek, Ўзбек, أۇزبېك\u200e", "uz", "uz", "uzb", "uzb", "uz"), new LanguageMetadata("Vietnamese", "Tiếng Việt", "vi", "vi", "vie", "vie", "vi"), new LanguageMetadata("Welsh", "Cymraeg", "cy", "cy", "", "cym", "cy"), new LanguageMetadata("Wolof", "Wollof", "wo", "wo", "wol", "wol", ""), new LanguageMetadata("Xhosa", "isiXhosa", "xh", "xh", "xho", "xho", ""), new LanguageMetadata("Yoruba", "Yorùbá", "yo", "yo", "yor", "yor", ""), new LanguageMetadata("Zhuang", "Saɯ cueŋƅ, Saw cuengh", "za", "za", "zha", "zha", ""), new LanguageMetadata("Zulu", "isiZulu", "zu", "zu", "zul", "zul", "")});
        languages = listListOf;
        Iterable<IndexedValue> iterableWithIndex = CollectionsKt.withIndex(listListOf);
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterableWithIndex, 10)), 16));
        for (IndexedValue indexedValue : iterableWithIndex) {
            int index = indexedValue.getIndex();
            String lowerCase = ((LanguageMetadata) indexedValue.component2()).getLanguageName().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            Pair pair = TuplesKt.to(lowerCase, Integer.valueOf(index));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        indexMapLanguageName = linkedHashMap;
        Iterable<IndexedValue> iterableWithIndex2 = CollectionsKt.withIndex(languages);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterableWithIndex2, 10)), 16));
        for (IndexedValue indexedValue2 : iterableWithIndex2) {
            int index2 = indexedValue2.getIndex();
            String lowerCase2 = ((LanguageMetadata) indexedValue2.component2()).getNativeName().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            Pair pair2 = TuplesKt.to(lowerCase2, Integer.valueOf(index2));
            linkedHashMap2.put(pair2.getFirst(), pair2.getSecond());
        }
        indexMapNativeName = linkedHashMap2;
        Iterable<IndexedValue> iterableWithIndex3 = CollectionsKt.withIndex(languages);
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterableWithIndex3, 10)), 16));
        for (IndexedValue indexedValue3 : iterableWithIndex3) {
            int index3 = indexedValue3.getIndex();
            String lowerCase3 = ((LanguageMetadata) indexedValue3.component2()).getIETF_tag().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
            Pair pair3 = TuplesKt.to(lowerCase3, Integer.valueOf(index3));
            linkedHashMap3.put(pair3.getFirst(), pair3.getSecond());
        }
        indexMapIETF_tag = linkedHashMap3;
        Iterable<IndexedValue> iterableWithIndex4 = CollectionsKt.withIndex(languages);
        LinkedHashMap linkedHashMap4 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterableWithIndex4, 10)), 16));
        for (IndexedValue indexedValue4 : iterableWithIndex4) {
            int index4 = indexedValue4.getIndex();
            String lowerCase4 = ((LanguageMetadata) indexedValue4.component2()).getISO_639_1().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase4, "toLowerCase(...)");
            Pair pair4 = TuplesKt.to(lowerCase4, Integer.valueOf(index4));
            linkedHashMap4.put(pair4.getFirst(), pair4.getSecond());
        }
        indexMapISO_639_1 = linkedHashMap4;
        Iterable<IndexedValue> iterableWithIndex5 = CollectionsKt.withIndex(languages);
        LinkedHashMap linkedHashMap5 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterableWithIndex5, 10)), 16));
        for (IndexedValue indexedValue5 : iterableWithIndex5) {
            int index5 = indexedValue5.getIndex();
            String lowerCase5 = ((LanguageMetadata) indexedValue5.component2()).getISO_639_2_B().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase5, "toLowerCase(...)");
            Pair pair5 = TuplesKt.to(lowerCase5, Integer.valueOf(index5));
            linkedHashMap5.put(pair5.getFirst(), pair5.getSecond());
        }
        indexMapISO_639_2_B = linkedHashMap5;
        Iterable<IndexedValue> iterableWithIndex6 = CollectionsKt.withIndex(languages);
        LinkedHashMap linkedHashMap6 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterableWithIndex6, 10)), 16));
        for (IndexedValue indexedValue6 : iterableWithIndex6) {
            int index6 = indexedValue6.getIndex();
            String lowerCase6 = ((LanguageMetadata) indexedValue6.component2()).getISO_639_3().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase6, "toLowerCase(...)");
            Pair pair6 = TuplesKt.to(lowerCase6, Integer.valueOf(index6));
            linkedHashMap6.put(pair6.getFirst(), pair6.getSecond());
        }
        indexMapISO_639_3 = linkedHashMap6;
        Iterable<IndexedValue> iterableWithIndex7 = CollectionsKt.withIndex(languages);
        LinkedHashMap linkedHashMap7 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterableWithIndex7, 10)), 16));
        for (IndexedValue indexedValue7 : iterableWithIndex7) {
            int index7 = indexedValue7.getIndex();
            String lowerCase7 = ((LanguageMetadata) indexedValue7.component2()).getOpenSubtitles().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase7, "toLowerCase(...)");
            Pair pair7 = TuplesKt.to(lowerCase7, Integer.valueOf(index7));
            linkedHashMap7.put(pair7.getFirst(), pair7.getSecond());
        }
        indexMapOpenSubtitles = linkedHashMap7;
    }

    public final List<LanguageMetadata> getLanguages() {
        return languages;
    }

    public final Map<String, Integer> getIndexMapLanguageName() {
        return indexMapLanguageName;
    }

    public final Map<String, Integer> getIndexMapNativeName() {
        return indexMapNativeName;
    }

    public final Map<String, Integer> getIndexMapIETF_tag() {
        return indexMapIETF_tag;
    }

    public final Map<String, Integer> getIndexMapISO_639_1() {
        return indexMapISO_639_1;
    }

    public final Map<String, Integer> getIndexMapISO_639_2_B() {
        return indexMapISO_639_2_B;
    }

    public final Map<String, Integer> getIndexMapISO_639_3() {
        return indexMapISO_639_3;
    }

    public final Map<String, Integer> getIndexMapOpenSubtitles() {
        return indexMapOpenSubtitles;
    }
}
