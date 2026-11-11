package io.ktor.http;

import androidx.collection.SieveCacheKt;
import io.ktor.util.Base64Kt;
import io.ktor.util.TextKt;
import io.ktor.util.date.GMTDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.io.encoding.Base64;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;
import kotlin.text.MatchGroup;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Cookie.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a+\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u00072\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\t\u001a\u0015\u0010\u000b\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\u000b\u0010\f\u001a\u0015\u0010\r\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\f\u001a\u008d\u0001\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0011\u001a\u00020\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00002\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u00052\u0016\b\u0002\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u00072\b\b\u0002\u0010\u001b\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\u001c\u001a\u001d\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u001d\u0010 \u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b \u0010\u001e\u001a\u0013\u0010!\u001a\u00020\u0000*\u00020\u0000H\u0002¢\u0006\u0004\b!\u0010\"\u001a\u0013\u0010$\u001a\u00020\u0005*\u00020#H\u0002¢\u0006\u0004\b$\u0010%\u001a*\u0010'\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010&2\u0006\u0010\u0011\u001a\u00020\u0010H\u0082\b¢\u0006\u0004\b'\u0010(\u001a\"\u0010)\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010&H\u0082\b¢\u0006\u0004\b)\u0010*\u001a \u0010+\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0005H\u0082\b¢\u0006\u0004\b+\u0010,\u001a\"\u0010-\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\u0000H\u0082\b¢\u0006\u0004\b-\u0010.\u001a\u0013\u0010/\u001a\u00020\u0012*\u00020\u0000H\u0002¢\u0006\u0004\b/\u00100\"\u001a\u00102\u001a\b\u0012\u0004\u0012\u00020\u0000018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103\"\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106\"\u001a\u00107\u001a\b\u0012\u0004\u0012\u00020#018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00103¨\u00068"}, d2 = {"", "cookiesHeader", "Lio/ktor/http/Cookie;", "parseServerSetCookieHeader", "(Ljava/lang/String;)Lio/ktor/http/Cookie;", "", "skipEscaped", "", "parseClientCookiesHeader", "(Ljava/lang/String;Z)Ljava/util/Map;", "cookie", "renderSetCookieHeader", "(Lio/ktor/http/Cookie;)Ljava/lang/String;", "renderCookieHeader", "name", ES6Iterator.VALUE_PROPERTY, "Lio/ktor/http/CookieEncoding;", "encoding", "", "maxAge", "Lio/ktor/util/date/GMTDate;", "expires", "domain", "path", "secure", "httpOnly", "extensions", "includeEncoding", "(Ljava/lang/String;Ljava/lang/String;Lio/ktor/http/CookieEncoding;Ljava/lang/Integer;Lio/ktor/util/date/GMTDate;Ljava/lang/String;Ljava/lang/String;ZZLjava/util/Map;Z)Ljava/lang/String;", "encodeCookieValue", "(Ljava/lang/String;Lio/ktor/http/CookieEncoding;)Ljava/lang/String;", "encodedValue", "decodeCookieValue", "assertCookieName", "(Ljava/lang/String;)Ljava/lang/String;", "", "shouldEscapeInCookies", "(C)Z", "", "cookiePart", "(Ljava/lang/String;Ljava/lang/Object;Lio/ktor/http/CookieEncoding;)Ljava/lang/String;", "cookiePartUnencoded", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;", "cookiePartFlag", "(Ljava/lang/String;Z)Ljava/lang/String;", "cookiePartExt", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "toIntClamping", "(Ljava/lang/String;)I", "", "loweredPartNames", "Ljava/util/Set;", "Lkotlin/text/Regex;", "clientCookieHeaderPattern", "Lkotlin/text/Regex;", "cookieCharsShouldBeEscaped", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CookieKt {
    private static final Set<String> loweredPartNames = SetsKt.setOf((Object[]) new String[]{"max-age", "expires", "domain", "path", "secure", "httponly", "$x-enc"});
    private static final Regex clientCookieHeaderPattern = new Regex("(^|;)\\s*([^;=\\{\\}\\s]+)\\s*(=\\s*(\"[^\"]*\"|[^;]*))?");
    private static final Set<Character> cookieCharsShouldBeEscaped = SetsKt.setOf((Object[]) new Character[]{';', Character.valueOf(AbstractJsonLexerKt.COMMA), '\"'});

    /* JADX INFO: compiled from: Cookie.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CookieEncoding.values().length];
            try {
                iArr[CookieEncoding.RAW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CookieEncoding.DQUOTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CookieEncoding.BASE64_ENCODING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CookieEncoding.URI_ENCODING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final Cookie parseServerSetCookieHeader(String cookiesHeader) {
        CookieEncoding cookieEncodingValueOf;
        Object objM8936constructorimpl;
        Intrinsics.checkNotNullParameter(cookiesHeader, "cookiesHeader");
        Map<String, String> clientCookiesHeader = parseClientCookiesHeader(cookiesHeader, false);
        Iterator<T> it = clientCookiesHeader.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!StringsKt.startsWith$default((String) entry.getKey(), "$", false, 2, (Object) null)) {
                String str = clientCookiesHeader.get("$x-enc");
                if (str == null || (cookieEncodingValueOf = CookieEncoding.valueOf(str)) == null) {
                    cookieEncodingValueOf = CookieEncoding.RAW;
                }
                CookieEncoding cookieEncoding = cookieEncodingValueOf;
                LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(clientCookiesHeader.size()));
                Iterator<T> it2 = clientCookiesHeader.entrySet().iterator();
                while (it2.hasNext()) {
                    Map.Entry entry2 = (Map.Entry) it2.next();
                    linkedHashMap.put(TextKt.toLowerCasePreservingASCIIRules((String) entry2.getKey()), entry2.getValue());
                }
                String str2 = (String) entry.getKey();
                String strDecodeCookieValue = decodeCookieValue((String) entry.getValue(), cookieEncoding);
                String str3 = (String) linkedHashMap.get("max-age");
                Integer numValueOf = str3 != null ? Integer.valueOf(toIntClamping(str3)) : null;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    String str4 = (String) linkedHashMap.get("expires");
                    objM8936constructorimpl = Result.m8936constructorimpl(str4 != null ? DateUtilsKt.fromCookieToGmtDate(str4) : null);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                }
                GMTDate gMTDate = (GMTDate) (Result.m8942isFailureimpl(objM8936constructorimpl) ? null : objM8936constructorimpl);
                String str5 = (String) linkedHashMap.get("domain");
                String str6 = (String) linkedHashMap.get("path");
                boolean zContainsKey = linkedHashMap.containsKey("secure");
                boolean zContainsKey2 = linkedHashMap.containsKey("httponly");
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                for (Map.Entry<String, String> entry3 : clientCookiesHeader.entrySet()) {
                    String key = entry3.getKey();
                    if (!loweredPartNames.contains(TextKt.toLowerCasePreservingASCIIRules(key)) && !Intrinsics.areEqual(key, entry.getKey())) {
                        linkedHashMap2.put(entry3.getKey(), entry3.getValue());
                    }
                }
                return new Cookie(str2, strDecodeCookieValue, cookieEncoding, numValueOf, gMTDate, str5, str6, zContainsKey, zContainsKey2, linkedHashMap2);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public static /* synthetic */ Map parseClientCookiesHeader$default(String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return parseClientCookiesHeader(str, z);
    }

    public static final Map<String, String> parseClientCookiesHeader(String cookiesHeader, final boolean z) {
        Intrinsics.checkNotNullParameter(cookiesHeader, "cookiesHeader");
        return MapsKt.toMap(SequencesKt.map(SequencesKt.filter(SequencesKt.map(Regex.findAll$default(clientCookieHeaderPattern, cookiesHeader, 0, 2, null), new Function1() { // from class: io.ktor.http.CookieKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CookieKt.parseClientCookiesHeader$lambda$0((MatchResult) obj);
            }
        }), new Function1() { // from class: io.ktor.http.CookieKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CookieKt.parseClientCookiesHeader$lambda$1(z, (Pair) obj));
            }
        }), new Function1() { // from class: io.ktor.http.CookieKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CookieKt.parseClientCookiesHeader$lambda$2((Pair) obj);
            }
        }));
    }

    static final Pair parseClientCookiesHeader$lambda$0(MatchResult it) {
        String value;
        String value2;
        Intrinsics.checkNotNullParameter(it, "it");
        MatchGroup matchGroup = it.getGroups().get(2);
        String str = "";
        if (matchGroup == null || (value = matchGroup.getValue()) == null) {
            value = "";
        }
        MatchGroup matchGroup2 = it.getGroups().get(4);
        if (matchGroup2 != null && (value2 = matchGroup2.getValue()) != null) {
            str = value2;
        }
        return TuplesKt.to(value, str);
    }

    static final boolean parseClientCookiesHeader$lambda$1(boolean z, Pair it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (z && StringsKt.startsWith$default((String) it.getFirst(), "$", false, 2, (Object) null)) ? false : true;
    }

    static final Pair parseClientCookiesHeader$lambda$2(Pair cookie) {
        Intrinsics.checkNotNullParameter(cookie, "cookie");
        return (StringsKt.startsWith$default((String) cookie.getSecond(), "\"", false, 2, (Object) null) && StringsKt.endsWith$default((String) cookie.getSecond(), "\"", false, 2, (Object) null)) ? Pair.copy$default(cookie, null, StringsKt.removeSurrounding((String) cookie.getSecond(), (CharSequence) "\""), 1, null) : cookie;
    }

    public static final String renderSetCookieHeader(Cookie cookie) {
        Intrinsics.checkNotNullParameter(cookie, "cookie");
        return renderSetCookieHeader$default(cookie.getName(), cookie.getValue(), cookie.getEncoding(), cookie.getMaxAgeInt(), cookie.getExpires(), cookie.getDomain(), cookie.getPath(), cookie.getSecure(), cookie.getHttpOnly(), cookie.getExtensions(), false, 1024, null);
    }

    public static final String renderCookieHeader(Cookie cookie) {
        Intrinsics.checkNotNullParameter(cookie, "cookie");
        return cookie.getName() + '=' + encodeCookieValue(cookie.getValue(), cookie.getEncoding());
    }

    public static /* synthetic */ String renderSetCookieHeader$default(String str, String str2, CookieEncoding cookieEncoding, Integer num, GMTDate gMTDate, String str3, String str4, boolean z, boolean z2, Map map, boolean z3, int i, Object obj) {
        return renderSetCookieHeader(str, str2, (i & 4) != 0 ? CookieEncoding.URI_ENCODING : cookieEncoding, (i & 8) != 0 ? null : num, (i & 16) != 0 ? null : gMTDate, (i & 32) != 0 ? null : str3, (i & 64) == 0 ? str4 : null, (i & 128) != 0 ? false : z, (i & 256) == 0 ? z2 : false, (i & 512) != 0 ? MapsKt.emptyMap() : map, (i & 1024) != 0 ? true : z3);
    }

    public static final String renderSetCookieHeader(String name, String value, CookieEncoding encoding, Integer num, GMTDate gMTDate, String str, String str2, boolean z, boolean z2, Map<String, String> extensions, boolean z3) {
        String str3;
        String str4;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(encoding, "encoding");
        Intrinsics.checkNotNullParameter(extensions, "extensions");
        String str5 = assertCookieName(name) + '=' + encodeCookieValue(value.toString(), encoding);
        String str6 = "";
        String str7 = num != null ? "Max-Age=" + num : "";
        String httpDate = gMTDate != null ? DateUtilsKt.toHttpDate(gMTDate) : null;
        String str8 = httpDate == null ? "" : "Expires=" + ((Object) httpDate);
        String str9 = str == null ? "" : "Domain=" + encodeCookieValue(str.toString(), CookieEncoding.RAW);
        String str10 = str2 == null ? "" : "Path=" + encodeCookieValue(str2.toString(), CookieEncoding.RAW);
        if (!z) {
            str3 = "";
        } else {
            str3 = "Secure";
        }
        if (!z2) {
            str4 = "";
        } else {
            str4 = "HttpOnly";
        }
        List listListOf = CollectionsKt.listOf((Object[]) new String[]{str5, str7, str8, str9, str10, str3, str4});
        ArrayList arrayList = new ArrayList(extensions.size());
        for (Map.Entry<String, String> entry : extensions.entrySet()) {
            String strAssertCookieName = assertCookieName(entry.getKey());
            String value2 = entry.getValue();
            if (value2 != null) {
                strAssertCookieName = strAssertCookieName + '=' + encodeCookieValue(value2.toString(), CookieEncoding.RAW);
            }
            arrayList.add(strAssertCookieName);
        }
        List listPlus = CollectionsKt.plus((Collection) listListOf, (Iterable) arrayList);
        if (z3) {
            String strName = encoding.name();
            str6 = strName != null ? "$x-enc=" + encodeCookieValue(strName.toString(), CookieEncoding.RAW) : "$x-enc";
        }
        List listPlus2 = CollectionsKt.plus((Collection<? extends String>) listPlus, str6);
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : listPlus2) {
            if (((String) obj).length() > 0) {
                arrayList2.add(obj);
            }
        }
        return CollectionsKt.joinToString$default(arrayList2, "; ", null, null, 0, null, null, 62, null);
    }

    public static final String encodeCookieValue(String value, CookieEncoding encoding) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(encoding, "encoding");
        int i = WhenMappings.$EnumSwitchMapping$0[encoding.ordinal()];
        if (i == 1) {
            return value;
        }
        if (i != 2) {
            if (i == 3) {
                return Base64Kt.encodeBase64(value);
            }
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            }
            return CodecsKt.encodeURLParameter(value, true);
        }
        String str = value;
        if (StringsKt.contains$default((CharSequence) str, '\"', false, 2, (Object) null)) {
            throw new IllegalArgumentException("The cookie value contains characters that cannot be encoded in DQUOTES format. Consider URL_ENCODING mode");
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (shouldEscapeInCookies(str.charAt(i2))) {
                return "\"" + value + '\"';
            }
        }
        return value;
    }

    public static final String decodeCookieValue(String encodedValue, CookieEncoding encoding) {
        Intrinsics.checkNotNullParameter(encodedValue, "encodedValue");
        Intrinsics.checkNotNullParameter(encoding, "encoding");
        int i = WhenMappings.$EnumSwitchMapping$0[encoding.ordinal()];
        if (i == 1 || i == 2) {
            String str = encodedValue;
            return (StringsKt.startsWith$default(StringsKt.trimStart((CharSequence) str).toString(), "\"", false, 2, (Object) null) && StringsKt.endsWith$default(StringsKt.trimEnd((CharSequence) str).toString(), "\"", false, 2, (Object) null)) ? StringsKt.removeSurrounding(StringsKt.trim((CharSequence) str).toString(), (CharSequence) "\"") : encodedValue;
        }
        if (i == 3) {
            return StringsKt.decodeToString(Base64.decode$default(Base64.INSTANCE, encodedValue, 0, 0, 6, (Object) null));
        }
        if (i != 4) {
            throw new NoWhenBranchMatchedException();
        }
        return CodecsKt.decodeURLQueryComponent$default(encodedValue, 0, 0, true, null, 11, null);
    }

    private static final String assertCookieName(String str) {
        String str2 = str;
        for (int i = 0; i < str2.length(); i++) {
            if (shouldEscapeInCookies(str2.charAt(i))) {
                throw new IllegalArgumentException("Cookie name is not valid: " + str);
            }
        }
        return str;
    }

    private static final boolean shouldEscapeInCookies(char c) {
        return CharsKt.isWhitespace(c) || Intrinsics.compare((int) c, 32) < 0 || cookieCharsShouldBeEscaped.contains(Character.valueOf(c));
    }

    private static final String cookiePart(String str, Object obj, CookieEncoding cookieEncoding) {
        if (obj == null) {
            return "";
        }
        return str + '=' + encodeCookieValue(obj.toString(), cookieEncoding);
    }

    private static final String cookiePartUnencoded(String str, Object obj) {
        if (obj == null) {
            return "";
        }
        return str + '=' + obj;
    }

    private static final String cookiePartFlag(String str, boolean z) {
        return z ? str : "";
    }

    private static final String cookiePartExt(String str, String str2) {
        if (str2 == null) {
            return str;
        }
        return str + '=' + encodeCookieValue(str2.toString(), CookieEncoding.RAW);
    }

    private static final int toIntClamping(String str) {
        return (int) kotlin.ranges.RangesKt.coerceIn(Long.parseLong(str), 0L, SieveCacheKt.NodeLinkMask);
    }
}
