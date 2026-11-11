package org.schabi.newpipe.extractor.utils;

import j$.net.URLDecoder;
import j$.net.URLEncoder;
import j$.util.DesugarArrays;
import j$.util.Objects;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import j$.util.stream.Collectors;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.schabi.newpipe.extractor.exceptions.ParsingException;

/* JADX INFO: loaded from: classes8.dex */
public final class Utils {
    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";
    private static final Pattern M_PATTERN = Pattern.compile("(https?)?://m\\.");
    private static final Pattern WWW_PATTERN = Pattern.compile("(https?)?://www\\.");

    private Utils() {
    }

    public static String encodeUrlUtf8(String str) {
        return URLEncoder.encode(str, StandardCharsets.UTF_8);
    }

    public static String decodeUrlUtf8(String str) {
        return URLDecoder.decode(str, StandardCharsets.UTF_8);
    }

    @Nonnull
    public static String removeNonDigitCharacters(@Nonnull String str) {
        return str.replaceAll("\\D+", "");
    }

    public static long mixedNumberWordToLong(String str) throws NumberFormatException, ParsingException {
        String strMatchGroup;
        double d;
        try {
            strMatchGroup = Parser.matchGroup("[\\d]+([\\.,][\\d]+)?([KMBkmb])+", str, 2);
        } catch (ParsingException unused) {
            strMatchGroup = "";
        }
        double d2 = Double.parseDouble(Parser.matchGroup1("([\\d]+([\\.,][\\d]+)?)", str).replace(",", "."));
        String upperCase = strMatchGroup.toUpperCase();
        upperCase.hashCode();
        switch (upperCase) {
            case "B":
                d = 1.0E9d;
                break;
            case "K":
                d = 1000.0d;
                break;
            case "M":
                d = 1000000.0d;
                break;
            default:
                return (long) d2;
        }
        return (long) (d2 * d);
    }

    public static void checkUrl(String str, String str2) throws ParsingException {
        checkUrl(Pattern.compile(str), str2);
    }

    public static void checkUrl(Pattern pattern, String str) throws ParsingException {
        if (isNullOrEmpty(str)) {
            throw new IllegalArgumentException("Url can't be null or empty");
        }
        if (!Parser.isMatch(pattern, str.toLowerCase())) {
            throw new ParsingException("Url doesn't match the pattern");
        }
    }

    public static String replaceHttpWithHttps(String str) {
        if (str == null) {
            return null;
        }
        if (!str.startsWith(HTTP)) {
            return str;
        }
        return HTTPS + str.substring(7);
    }

    @Nullable
    public static String getQueryValue(@Nonnull URL url, String str) {
        String query = url.getQuery();
        if (query == null) {
            return null;
        }
        for (String str2 : query.split("&")) {
            String[] strArrSplit = str2.split("=", 2);
            if (decodeUrlUtf8(strArrSplit[0]).equals(str)) {
                return decodeUrlUtf8(strArrSplit[1]);
            }
        }
        return null;
    }

    @Nonnull
    public static URL stringToURL(String str) throws MalformedURLException {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            if (e.getMessage().equals("no protocol: " + str)) {
                return new URL(HTTPS + str);
            }
            throw e;
        }
    }

    public static boolean isHTTP(@Nonnull URL url) {
        String protocol = url.getProtocol();
        if (protocol.equals("http") || protocol.equals("https")) {
            return url.getPort() == -1 || (url.getPort() == url.getDefaultPort());
        }
        return false;
    }

    public static String removeMAndWWWFromUrl(String str) {
        if (M_PATTERN.matcher(str).find()) {
            return str.replace("m.", "");
        }
        return WWW_PATTERN.matcher(str).find() ? str.replace("www.", "") : str;
    }

    @Nonnull
    public static String removeUTF8BOM(@Nonnull String str) {
        if (str.startsWith("\ufeff")) {
            str = str.substring(1);
        }
        return str.endsWith("\ufeff") ? str.substring(0, str.length() - 1) : str;
    }

    @Nonnull
    public static String getBaseUrl(String str) throws ParsingException {
        try {
            URL urlStringToURL = stringToURL(str);
            return urlStringToURL.getProtocol() + "://" + urlStringToURL.getAuthority();
        } catch (MalformedURLException e) {
            String message = e.getMessage();
            if (message.startsWith("unknown protocol: ")) {
                return message.substring(18);
            }
            throw new ParsingException("Malformed url: " + str, e);
        }
    }

    public static String followGoogleRedirectIfNeeded(String str) {
        try {
            URL urlStringToURL = stringToURL(str);
            return (urlStringToURL.getHost().contains("google") && urlStringToURL.getPath().equals("/url")) ? decodeUrlUtf8(Parser.matchGroup1("&url=([^&]+)(?:&|$)", str)) : str;
        } catch (Exception unused) {
            return str;
        }
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <K, V> boolean isNullOrEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isBlank(String str) {
        return str == null || UByte$$ExternalSyntheticBackport0.m9015m(str);
    }

    @Nonnull
    public static String join(String str, final String str2, @Nonnull Map<? extends CharSequence, ? extends CharSequence> map) {
        return (String) j$.util.Collection.EL.stream(map.entrySet()).map(new Function() { // from class: org.schabi.newpipe.extractor.utils.Utils$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Utils.lambda$join$0(str2, (Map.Entry) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).collect(Collectors.joining(str));
    }

    static /* synthetic */ String lambda$join$0(String str, Map.Entry entry) {
        return entry.getKey() + str + entry.getValue();
    }

    @Nonnull
    public static String nonEmptyAndNullJoin(CharSequence charSequence, String... strArr) {
        return (String) DesugarArrays.stream(strArr).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.utils.Utils$$ExternalSyntheticLambda5
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Utils.lambda$nonEmptyAndNullJoin$1((String) obj);
            }
        }).collect(Collectors.joining(charSequence));
    }

    static /* synthetic */ boolean lambda$nonEmptyAndNullJoin$1(String str) {
        return (isNullOrEmpty(str) || str.equals(AbstractJsonLexerKt.NULL)) ? false : true;
    }

    @Nonnull
    public static String getStringResultFromRegexArray(@Nonnull String str, @Nonnull String[] strArr) throws Parser.RegexException {
        return getStringResultFromRegexArray(str, strArr, 0);
    }

    @Nonnull
    public static String getStringResultFromRegexArray(@Nonnull String str, @Nonnull Pattern[] patternArr) throws Parser.RegexException {
        return getStringResultFromRegexArray(str, patternArr, 0);
    }

    @Nonnull
    public static String getStringResultFromRegexArray(@Nonnull String str, @Nonnull String[] strArr, int i) throws Parser.RegexException {
        return getStringResultFromRegexArray(str, (Pattern[]) DesugarArrays.stream(strArr).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.utils.Utils$$ExternalSyntheticLambda2
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Objects.nonNull((String) obj);
            }
        }).map(new Function() { // from class: org.schabi.newpipe.extractor.utils.Utils$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Pattern.compile((String) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).toArray(new IntFunction() { // from class: org.schabi.newpipe.extractor.utils.Utils$$ExternalSyntheticLambda4
            @Override // java.util.function.IntFunction
            public final Object apply(int i2) {
                return Utils.lambda$getStringResultFromRegexArray$2(i2);
            }
        }), i);
    }

    static /* synthetic */ Pattern[] lambda$getStringResultFromRegexArray$2(int i) {
        return new Pattern[i];
    }

    @Nonnull
    public static String getStringResultFromRegexArray(@Nonnull String str, @Nonnull Pattern[] patternArr, int i) throws Parser.RegexException {
        for (Pattern pattern : patternArr) {
            try {
                String strMatchGroup = Parser.matchGroup(pattern, str, i);
                if (strMatchGroup != null) {
                    return strMatchGroup;
                }
            } catch (Parser.RegexException unused) {
            }
        }
        throw new Parser.RegexException("No regex matched the input on group " + i);
    }
}
