package io.ktor.http.auth;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.CookieUtilsKt;
import io.ktor.http.parsing.ParseException;
import io.ktor.util.date.GMTDateParser;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: HttpAuthHeader.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\b\b\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0017\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0006\u0010\u0007\u001a-\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\nH\u0002¢\u0006\u0004\b\u0003\u0010\f\u001a7\u0010\u000f\u001a\u0004\u0018\u00010\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u000f\u0010\u0010\u001a3\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\b2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u0011H\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a3\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\b2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u0011H\u0002¢\u0006\u0004\b\u0015\u0010\u0014\u001a\u001f\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0013\u0010\u0018\u001a\u00020\u0000*\u00020\u0000H\u0002¢\u0006\u0004\b\u0018\u0010\u0019\u001a#\u0010\u001c\u001a\u00020\b*\u00020\u00002\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001b\u0010\u001e\u001a\u00020\b*\u00020\u00002\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u001e\u0010\u0017\u001a\u0013\u0010 \u001a\u00020\u001f*\u00020\u001aH\u0002¢\u0006\u0004\b \u0010!\u001a\u0013\u0010\"\u001a\u00020\u001f*\u00020\u001aH\u0002¢\u0006\u0004\b\"\u0010!\"\u001a\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001a0#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%\"\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001a0#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010%\"\u0014\u0010(\u001a\u00020'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)\"\u0014\u0010*\u001a\u00020'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010)¨\u0006+"}, d2 = {"", "headerValue", "Lio/ktor/http/auth/HttpAuthHeader;", "parseAuthorizationHeader", "(Ljava/lang/String;)Lio/ktor/http/auth/HttpAuthHeader;", "", "parseAuthorizationHeaders", "(Ljava/lang/String;)Ljava/util/List;", "", "startIndex", "", "headers", "(Ljava/lang/String;ILjava/util/List;)I", "header", "index", "nextChallengeIndex", "(Ljava/util/List;Lio/ktor/http/auth/HttpAuthHeader;ILjava/lang/String;)Ljava/lang/Integer;", "", "parameters", "matchParameters", "(Ljava/lang/String;ILjava/util/Map;)I", "matchParameter", "matchToken68", "(Ljava/lang/String;I)I", "unescaped", "(Ljava/lang/String;)Ljava/lang/String;", "", TtmlNode.RUBY_DELIMITER, "skipDelimiter", "(Ljava/lang/String;IC)I", "skipSpaces", "", "isToken68", "(C)Z", "isToken", "", "TOKEN_EXTRA", "Ljava/util/Set;", "TOKEN68_EXTRA", "Lkotlin/text/Regex;", "token68Pattern", "Lkotlin/text/Regex;", "escapeRegex", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class HttpAuthHeaderKt {
    private static final Set<Character> TOKEN_EXTRA = SetsKt.setOf((Object[]) new Character[]{'!', '#', Character.valueOf(Typography.dollar), '%', Character.valueOf(Typography.amp), '\'', Character.valueOf(GMTDateParser.ANY), '+', '-', '.', '^', '_', '`', '|', '~'});
    private static final Set<Character> TOKEN68_EXTRA = SetsKt.setOf((Object[]) new Character[]{'-', '.', '_', '~', '+', '/'});
    private static final Regex token68Pattern = new Regex("[a-zA-Z0-9\\-._~+/]+=*");
    private static final Regex escapeRegex = new Regex("\\\\.");

    public static final HttpAuthHeader parseAuthorizationHeader(String headerValue) {
        Intrinsics.checkNotNullParameter(headerValue, "headerValue");
        int iSkipSpaces = skipSpaces(headerValue, 0);
        int i = iSkipSpaces;
        while (i < headerValue.length() && isToken(headerValue.charAt(i))) {
            i++;
        }
        String strSubstring = StringsKt.substring(headerValue, RangesKt.until(iSkipSpaces, i));
        int iSkipSpaces2 = skipSpaces(headerValue, i);
        if (StringsKt.isBlank(strSubstring)) {
            return null;
        }
        if (headerValue.length() == iSkipSpaces2) {
            return new HttpAuthHeader.Parameterized(strSubstring, CollectionsKt.emptyList(), (HeaderValueEncoding) null, 4, (DefaultConstructorMarker) null);
        }
        int iMatchToken68 = matchToken68(headerValue, iSkipSpaces2);
        String string = StringsKt.trim((CharSequence) StringsKt.substring(headerValue, RangesKt.until(iSkipSpaces2, iMatchToken68))).toString();
        if (string.length() > 0 && iMatchToken68 == headerValue.length()) {
            return new HttpAuthHeader.Single(strSubstring, string);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (matchParameters(headerValue, iSkipSpaces2, linkedHashMap) == -1) {
            return new HttpAuthHeader.Parameterized(strSubstring, linkedHashMap, (HeaderValueEncoding) null, 4, (DefaultConstructorMarker) null);
        }
        throw new ParseException("Function parseAuthorizationHeader can parse only one header", null, 2, null);
    }

    public static final List<HttpAuthHeader> parseAuthorizationHeaders(String headerValue) {
        Intrinsics.checkNotNullParameter(headerValue, "headerValue");
        ArrayList arrayList = new ArrayList();
        int authorizationHeader = 0;
        while (authorizationHeader != -1) {
            authorizationHeader = parseAuthorizationHeader(headerValue, authorizationHeader, arrayList);
        }
        return arrayList;
    }

    private static final int parseAuthorizationHeader(String str, int i, List<HttpAuthHeader> list) {
        Integer numNextChallengeIndex;
        int iSkipSpaces = skipSpaces(str, i);
        int i2 = iSkipSpaces;
        while (i2 < str.length() && isToken(str.charAt(i2))) {
            i2++;
        }
        String strSubstring = StringsKt.substring(str, RangesKt.until(iSkipSpaces, i2));
        if (StringsKt.isBlank(strSubstring)) {
            throw new ParseException("Invalid authScheme value: it should be token, can't be blank", null, 2, null);
        }
        int iSkipSpaces2 = skipSpaces(str, i2);
        Integer numNextChallengeIndex2 = nextChallengeIndex(list, new HttpAuthHeader.Parameterized(strSubstring, CollectionsKt.emptyList(), (HeaderValueEncoding) null, 4, (DefaultConstructorMarker) null), iSkipSpaces2, str);
        if (numNextChallengeIndex2 != null) {
            return numNextChallengeIndex2.intValue();
        }
        int iMatchToken68 = matchToken68(str, iSkipSpaces2);
        String string = StringsKt.trim((CharSequence) StringsKt.substring(str, RangesKt.until(iSkipSpaces2, iMatchToken68))).toString();
        if (string.length() > 0 && (numNextChallengeIndex = nextChallengeIndex(list, new HttpAuthHeader.Single(strSubstring, string), iMatchToken68, str)) != null) {
            return numNextChallengeIndex.intValue();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int iMatchParameters = matchParameters(str, iSkipSpaces2, linkedHashMap);
        list.add(new HttpAuthHeader.Parameterized(strSubstring, linkedHashMap, (HeaderValueEncoding) null, 4, (DefaultConstructorMarker) null));
        return iMatchParameters;
    }

    private static final Integer nextChallengeIndex(List<HttpAuthHeader> list, HttpAuthHeader httpAuthHeader, int i, String str) {
        if (i != str.length() && str.charAt(i) != ',') {
            return null;
        }
        list.add(httpAuthHeader);
        if (i == str.length()) {
            return -1;
        }
        if (str.charAt(i) == ',') {
            return Integer.valueOf(i + 1);
        }
        throw new IllegalStateException("".toString());
    }

    private static final int matchParameters(String str, int i, Map<String, String> map) {
        while (i > 0 && i < str.length()) {
            int iMatchParameter = matchParameter(str, i, map);
            if (iMatchParameter == i) {
                break;
            }
            i = skipDelimiter(str, iMatchParameter, AbstractJsonLexerKt.COMMA);
        }
        return i;
    }

    private static final int matchParameter(String str, int i, Map<String, String> map) {
        int i2;
        int iSkipSpaces = skipSpaces(str, i);
        int i3 = iSkipSpaces;
        while (i3 < str.length() && isToken(str.charAt(i3))) {
            i3++;
        }
        String strSubstring = StringsKt.substring(str, RangesKt.until(iSkipSpaces, i3));
        int iSkipSpaces2 = skipSpaces(str, i3);
        if (iSkipSpaces2 == str.length() || str.charAt(iSkipSpaces2) != '=') {
            return i;
        }
        int iSkipSpaces3 = skipSpaces(str, iSkipSpaces2 + 1);
        boolean z = false;
        if (str.charAt(iSkipSpaces3) == '\"') {
            iSkipSpaces3++;
            i2 = iSkipSpaces3;
            boolean z2 = false;
            while (i2 < str.length() && (str.charAt(i2) != '\"' || z2)) {
                z2 = !z2 && str.charAt(i2) == '\\';
                i2++;
            }
            if (i2 == str.length()) {
                throw new ParseException("Expected closing quote'\"' in parameter", null, 2, null);
            }
            z = true;
        } else {
            i2 = iSkipSpaces3;
            while (i2 < str.length() && str.charAt(i2) != ' ' && str.charAt(i2) != ',') {
                i2++;
            }
        }
        String strSubstring2 = StringsKt.substring(str, RangesKt.until(iSkipSpaces3, i2));
        if (z) {
            strSubstring2 = unescaped(strSubstring2);
        }
        map.put(strSubstring, strSubstring2);
        return z ? i2 + 1 : i2;
    }

    private static final int matchToken68(String str, int i) {
        int iSkipSpaces = skipSpaces(str, i);
        while (iSkipSpaces < str.length() && isToken68(str.charAt(iSkipSpaces))) {
            iSkipSpaces++;
        }
        while (iSkipSpaces < str.length() && str.charAt(iSkipSpaces) == '=') {
            iSkipSpaces++;
        }
        return skipSpaces(str, iSkipSpaces);
    }

    private static final String unescaped(String str) {
        return escapeRegex.replace(str, new Function1() { // from class: io.ktor.http.auth.HttpAuthHeaderKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HttpAuthHeaderKt.unescaped$lambda$0((MatchResult) obj);
            }
        });
    }

    static final CharSequence unescaped$lambda$0(MatchResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return StringsKt.takeLast(it.getValue(), 1);
    }

    private static final int skipDelimiter(String str, int i, char c) {
        int iSkipSpaces = skipSpaces(str, i);
        if (iSkipSpaces == str.length()) {
            return -1;
        }
        if (str.charAt(iSkipSpaces) != c) {
            throw new ParseException("Expected delimiter " + c + " at position " + iSkipSpaces, null, 2, null);
        }
        return skipSpaces(str, iSkipSpaces + 1);
    }

    private static final int skipSpaces(String str, int i) {
        while (i < str.length() && str.charAt(i) == ' ') {
            i++;
        }
        return i;
    }

    private static final boolean isToken68(char c) {
        if ('a' > c || c >= '{') {
            return ('A' <= c && c < '[') || CookieUtilsKt.isDigit(c) || TOKEN68_EXTRA.contains(Character.valueOf(c));
        }
        return true;
    }

    private static final boolean isToken(char c) {
        if ('a' > c || c >= '{') {
            return ('A' <= c && c < '[') || CookieUtilsKt.isDigit(c) || TOKEN_EXTRA.contains(Character.valueOf(c));
        }
        return true;
    }
}
