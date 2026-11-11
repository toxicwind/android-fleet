package io.ktor.http;

import coil3.util.UtilsKt;
import io.ktor.util.CharsetKt;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: URLParser.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\u001a\u0019\u0010\u0003\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001b\u0010\u0005\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0005\u0010\u0004\u001a3\u0010\u000b\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\f\u001a+\u0010\r\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\r\u0010\u000e\u001a+\u0010\u000f\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000f\u0010\u0010\u001a+\u0010\u0011\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0011\u0010\u000e\u001a+\u0010\u0012\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0012\u0010\u000e\u001a'\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a/\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001a#\u0010\u0019\u001a\u00020\u0006*\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0019\u0010\u0014\u001a\u0013\u0010\u001b\u001a\u00020\u001a*\u00020\u0015H\u0002¢\u0006\u0004\b\u001b\u0010\u001c\" \u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00010\u001d8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!¨\u0006\""}, d2 = {"Lio/ktor/http/URLBuilder;", "", "urlString", "takeFrom", "(Lio/ktor/http/URLBuilder;Ljava/lang/String;)Lio/ktor/http/URLBuilder;", "takeFromUnsafe", "", "startIndex", "endIndex", "slashCount", "", "parseFile", "(Lio/ktor/http/URLBuilder;Ljava/lang/String;III)V", "parseMailto", "(Lio/ktor/http/URLBuilder;Ljava/lang/String;II)V", "parseQuery", "(Lio/ktor/http/URLBuilder;Ljava/lang/String;II)I", "parseFragment", "fillHost", "findScheme", "(Ljava/lang/String;II)I", "", "char", PeertubeParsingHelper.COUNT_KEY, "(Ljava/lang/String;IIC)I", "indexOfColonInHostPort", "", "isLetter", "(C)Z", "", "ROOT_PATH", "Ljava/util/List;", "getROOT_PATH", "()Ljava/util/List;", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class URLParserKt {
    private static final List<String> ROOT_PATH = CollectionsKt.listOf("");

    public static final List<String> getROOT_PATH() {
        return ROOT_PATH;
    }

    public static final URLBuilder takeFrom(URLBuilder uRLBuilder, String urlString) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(urlString, "urlString");
        if (StringsKt.isBlank(urlString)) {
            return uRLBuilder;
        }
        try {
            return takeFromUnsafe(uRLBuilder, urlString);
        } catch (Throwable th) {
            throw new URLParserException(urlString, th);
        }
    }

    public static final URLBuilder takeFromUnsafe(URLBuilder uRLBuilder, String urlString) {
        List<String> listEmptyList;
        List<String> listEmptyList2;
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(urlString, "urlString");
        String str = urlString;
        int length = str.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            }
            if (!CharsKt.isWhitespace(str.charAt(i))) {
                break;
            }
            i++;
        }
        int length2 = str.length() - 1;
        if (length2 < 0) {
            length2 = -1;
            break;
        }
        while (true) {
            int i2 = length2 - 1;
            if (!CharsKt.isWhitespace(str.charAt(length2))) {
                break;
            }
            if (i2 < 0) {
                length2 = -1;
                break;
            }
            length2 = i2;
        }
        int i3 = length2 + 1;
        int iFindScheme = findScheme(urlString, i, i3);
        if (iFindScheme > 0) {
            String strSubstring = urlString.substring(i, i + iFindScheme);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            uRLBuilder.setProtocol(URLProtocol.INSTANCE.createOrDefault(strSubstring));
            i += iFindScheme + 1;
        }
        if (Intrinsics.areEqual(uRLBuilder.getProtocol().getName(), "data")) {
            String strSubstring2 = urlString.substring(i, i3);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
            uRLBuilder.setHost(strSubstring2);
            return uRLBuilder;
        }
        int iCount = count(urlString, i, i3, '/');
        int iIntValue = i + iCount;
        if (Intrinsics.areEqual(uRLBuilder.getProtocol().getName(), UtilsKt.SCHEME_FILE)) {
            parseFile(uRLBuilder, urlString, iIntValue, i3, iCount);
            return uRLBuilder;
        }
        if (Intrinsics.areEqual(uRLBuilder.getProtocol().getName(), "mailto")) {
            if (iCount != 0) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            parseMailto(uRLBuilder, urlString, iIntValue, i3);
            return uRLBuilder;
        }
        if (Intrinsics.areEqual(uRLBuilder.getProtocol().getName(), "about")) {
            if (iCount != 0) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            String strSubstring3 = urlString.substring(iIntValue, i3);
            Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
            uRLBuilder.setHost(strSubstring3);
            return uRLBuilder;
        }
        if (Intrinsics.areEqual(uRLBuilder.getProtocol().getName(), "tel")) {
            if (iCount != 0) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            String strSubstring4 = urlString.substring(iIntValue, i3);
            Intrinsics.checkNotNullExpressionValue(strSubstring4, "substring(...)");
            uRLBuilder.setHost(strSubstring4);
            return uRLBuilder;
        }
        if (iCount >= 2) {
            int i4 = iIntValue;
            while (true) {
                Integer numValueOf = Integer.valueOf(StringsKt.indexOfAny$default((CharSequence) str, CharsetKt.toCharArray("@/\\?#"), i4, false, 4, (Object) null));
                if (numValueOf.intValue() <= 0) {
                    numValueOf = null;
                }
                iIntValue = numValueOf != null ? numValueOf.intValue() : i3;
                if (iIntValue >= i3 || urlString.charAt(iIntValue) != '@') {
                    break;
                }
                int iIndexOfColonInHostPort = indexOfColonInHostPort(urlString, i4, iIntValue);
                if (iIndexOfColonInHostPort != -1) {
                    String strSubstring5 = urlString.substring(i4, iIndexOfColonInHostPort);
                    Intrinsics.checkNotNullExpressionValue(strSubstring5, "substring(...)");
                    uRLBuilder.setEncodedUser(strSubstring5);
                    String strSubstring6 = urlString.substring(iIndexOfColonInHostPort + 1, iIntValue);
                    Intrinsics.checkNotNullExpressionValue(strSubstring6, "substring(...)");
                    uRLBuilder.setEncodedPassword(strSubstring6);
                } else {
                    String strSubstring7 = urlString.substring(i4, iIntValue);
                    Intrinsics.checkNotNullExpressionValue(strSubstring7, "substring(...)");
                    uRLBuilder.setEncodedUser(strSubstring7);
                }
                i4 = iIntValue + 1;
            }
            fillHost(uRLBuilder, urlString, i4, iIntValue);
        }
        int query = iIntValue;
        if (query >= i3) {
            uRLBuilder.setEncodedPathSegments(urlString.charAt(length2) == '/' ? ROOT_PATH : CollectionsKt.emptyList());
            return uRLBuilder;
        }
        if (iCount == 0) {
            listEmptyList = CollectionsKt.dropLast(uRLBuilder.getEncodedPathSegments(), 1);
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        uRLBuilder.setEncodedPathSegments(listEmptyList);
        Integer numValueOf2 = Integer.valueOf(StringsKt.indexOfAny$default((CharSequence) str, CharsetKt.toCharArray("?#"), query, false, 4, (Object) null));
        Integer num = numValueOf2.intValue() > 0 ? numValueOf2 : null;
        int iIntValue2 = num != null ? num.intValue() : i3;
        if (iIntValue2 > query) {
            String strSubstring8 = urlString.substring(query, iIntValue2);
            Intrinsics.checkNotNullExpressionValue(strSubstring8, "substring(...)");
            List<String> listEmptyList3 = (uRLBuilder.getEncodedPathSegments().size() == 1 && ((CharSequence) CollectionsKt.first((List) uRLBuilder.getEncodedPathSegments())).length() == 0) ? CollectionsKt.emptyList() : uRLBuilder.getEncodedPathSegments();
            List<String> listSplit$default = Intrinsics.areEqual(strSubstring8, "/") ? ROOT_PATH : StringsKt.split$default((CharSequence) strSubstring8, new char[]{'/'}, false, 0, 6, (Object) null);
            if (iCount == 1) {
                listEmptyList2 = ROOT_PATH;
            } else {
                listEmptyList2 = CollectionsKt.emptyList();
            }
            uRLBuilder.setEncodedPathSegments(CollectionsKt.plus((Collection) listEmptyList3, (Iterable) CollectionsKt.plus((Collection) listEmptyList2, (Iterable) listSplit$default)));
            query = iIntValue2;
        }
        if (query < i3 && urlString.charAt(query) == '?') {
            query = parseQuery(uRLBuilder, urlString, query, i3);
        }
        parseFragment(uRLBuilder, urlString, query, i3);
        return uRLBuilder;
    }

    private static final void parseFile(URLBuilder uRLBuilder, String str, int i, int i2, int i3) {
        if (i3 == 1) {
            uRLBuilder.setHost("");
            String strSubstring = str.substring(i, i2);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            URLBuilderKt.setEncodedPath(uRLBuilder, strSubstring);
            return;
        }
        if (i3 != 2) {
            if (i3 == 3) {
                uRLBuilder.setHost("");
                StringBuilder sb = new StringBuilder("/");
                String strSubstring2 = str.substring(i, i2);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                sb.append(strSubstring2);
                URLBuilderKt.setEncodedPath(uRLBuilder, sb.toString());
                return;
            }
            throw new IllegalArgumentException("Invalid file url: " + str);
        }
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, '/', i, false, 4, (Object) null);
        if (iIndexOf$default == -1 || iIndexOf$default == i2) {
            String strSubstring3 = str.substring(i, i2);
            Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
            uRLBuilder.setHost(strSubstring3);
        } else {
            String strSubstring4 = str.substring(i, iIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(strSubstring4, "substring(...)");
            uRLBuilder.setHost(strSubstring4);
            String strSubstring5 = str.substring(iIndexOf$default, i2);
            Intrinsics.checkNotNullExpressionValue(strSubstring5, "substring(...)");
            URLBuilderKt.setEncodedPath(uRLBuilder, strSubstring5);
        }
    }

    private static final void parseMailto(URLBuilder uRLBuilder, String str, int i, int i2) {
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, "@", i, false, 4, (Object) null);
        if (iIndexOf$default == -1) {
            throw new IllegalArgumentException("Invalid mailto url: " + str + ", it should contain '@'.");
        }
        String strSubstring = str.substring(i, iIndexOf$default);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        uRLBuilder.setUser(CodecsKt.decodeURLPart$default(strSubstring, 0, 0, null, 7, null));
        String strSubstring2 = str.substring(iIndexOf$default + 1, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        uRLBuilder.setHost(strSubstring2);
    }

    private static final int parseQuery(final URLBuilder uRLBuilder, String str, int i, int i2) {
        int i3 = i + 1;
        if (i3 == i2) {
            uRLBuilder.setTrailingQuery(true);
            return i2;
        }
        Integer numValueOf = Integer.valueOf(StringsKt.indexOf$default((CharSequence) str, '#', i3, false, 4, (Object) null));
        if (numValueOf.intValue() <= 0) {
            numValueOf = null;
        }
        if (numValueOf != null) {
            i2 = numValueOf.intValue();
        }
        String strSubstring = str.substring(i3, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        QueryKt.parseQueryString$default(strSubstring, 0, 0, false, 6, null).forEach(new Function2() { // from class: io.ktor.http.URLParserKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return URLParserKt.parseQuery$lambda$1(uRLBuilder, (String) obj, (List) obj2);
            }
        });
        return i2;
    }

    static final Unit parseQuery$lambda$1(URLBuilder uRLBuilder, String key, List values) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(values, "values");
        uRLBuilder.getEncodedParameters().appendAll(key, values);
        return Unit.INSTANCE;
    }

    private static final void parseFragment(URLBuilder uRLBuilder, String str, int i, int i2) {
        if (i >= i2 || str.charAt(i) != '#') {
            return;
        }
        String strSubstring = str.substring(i + 1, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        uRLBuilder.setEncodedFragment(strSubstring);
    }

    private static final void fillHost(URLBuilder uRLBuilder, String str, int i, int i2) {
        int i3;
        Integer numValueOf = Integer.valueOf(indexOfColonInHostPort(str, i, i2));
        if (numValueOf.intValue() <= 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : i2;
        String strSubstring = str.substring(i, iIntValue);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        uRLBuilder.setHost(strSubstring);
        int i4 = iIntValue + 1;
        if (i4 < i2) {
            String strSubstring2 = str.substring(i4, i2);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
            i3 = Integer.parseInt(strSubstring2);
        } else {
            i3 = 0;
        }
        uRLBuilder.setPort(i3);
    }

    private static final int findScheme(String str, int i, int i2) {
        int i3;
        int i4;
        char cCharAt = str.charAt(i);
        if (('a' > cCharAt || cCharAt >= '{') && ('A' > cCharAt || cCharAt >= '[')) {
            i3 = i;
            i4 = i3;
        } else {
            i3 = i;
            i4 = -1;
        }
        while (i3 < i2) {
            char cCharAt2 = str.charAt(i3);
            if (cCharAt2 == ':') {
                if (i4 == -1) {
                    return i3 - i;
                }
                throw new IllegalArgumentException("Illegal character in scheme at position " + i4);
            }
            if (cCharAt2 == '#' || cCharAt2 == '/' || cCharAt2 == '?') {
                break;
            }
            if (i4 == -1 && (('a' > cCharAt2 || cCharAt2 >= '{') && (('A' > cCharAt2 || cCharAt2 >= '[') && (('0' > cCharAt2 || cCharAt2 >= ':') && cCharAt2 != '.' && cCharAt2 != '+' && cCharAt2 != '-')))) {
                i4 = i3;
            }
            i3++;
        }
        return -1;
    }

    private static final int count(String str, int i, int i2, char c) {
        int i3 = 0;
        while (true) {
            int i4 = i + i3;
            if (i4 >= i2 || str.charAt(i4) != c) {
                break;
            }
            i3++;
        }
        return i3;
    }

    private static final int indexOfColonInHostPort(String str, int i, int i2) {
        boolean z = false;
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (cCharAt != ':') {
                if (cCharAt == '[') {
                    z = true;
                } else if (cCharAt == ']') {
                    z = false;
                }
            } else if (!z) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private static final boolean isLetter(char c) {
        char lowerCase = Character.toLowerCase(c);
        return 'a' <= lowerCase && lowerCase < '{';
    }
}
