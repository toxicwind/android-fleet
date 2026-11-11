package org.schabi.newpipe.extractor.utils;

import j$.util.DesugarArrays;
import j$.util.function.BiFunction$CC;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import j$.util.stream.Collectors;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.exceptions.ParsingException;

/* JADX INFO: loaded from: classes8.dex */
public final class Parser {
    static /* synthetic */ String lambda$compatParseMap$4(String str, String str2) {
        return str2;
    }

    private Parser() {
    }

    public static class RegexException extends ParsingException {
        public RegexException(String str) {
            super(str);
        }
    }

    @Nonnull
    public static Matcher matchOrThrow(@Nonnull Pattern pattern, String str) throws RegexException {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher;
        }
        String str2 = "Failed to find pattern \"" + pattern.pattern() + "\"";
        if (str.length() <= 1024) {
            str2 = str2 + " inside of \"" + str + "\"";
        }
        throw new RegexException(str2);
    }

    @Nonnull
    public static String matchGroup1(String str, String str2) throws RegexException {
        return matchGroup(str, str2, 1);
    }

    @Nonnull
    public static String matchGroup1(Pattern pattern, String str) throws RegexException {
        return matchGroup(pattern, str, 1);
    }

    @Nonnull
    public static String matchGroup(String str, String str2, int i) throws RegexException {
        return matchGroup(Pattern.compile(str), str2, i);
    }

    @Nonnull
    public static String matchGroup(@Nonnull Pattern pattern, String str, int i) throws RegexException {
        return matchOrThrow(pattern, str).group(i);
    }

    public static String matchGroup1MultiplePatterns(Pattern[] patternArr, String str) throws RegexException {
        return matchMultiplePatterns(patternArr, str).group(1);
    }

    public static Matcher matchMultiplePatterns(Pattern[] patternArr, String str) throws RegexException {
        RegexException regexException = null;
        for (Pattern pattern : patternArr) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                return matcher;
            }
            if (regexException == null) {
                regexException = new RegexException("Failed to find pattern \"" + pattern.pattern() + "\"" + (str.length() <= 1000 ? "inside of \"" + str + "\"" : ""));
            }
        }
        if (regexException != null) {
            throw regexException;
        }
        throw new RegexException("Empty patterns array passed to matchMultiplePatterns");
    }

    public static boolean isMatch(String str, String str2) {
        return isMatch(Pattern.compile(str), str2);
    }

    public static boolean isMatch(@Nonnull Pattern pattern, String str) {
        return pattern.matcher(str).find();
    }

    @Nonnull
    public static Map<String, String> compatParseMap(@Nonnull String str) {
        return (Map) DesugarArrays.stream(str.split("&")).map(new Function() { // from class: org.schabi.newpipe.extractor.utils.Parser$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((String) obj).split("=");
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.utils.Parser$$ExternalSyntheticLambda1
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
                return Parser.lambda$compatParseMap$1((String[]) obj);
            }
        }).collect(Collectors.toMap(new Function() { // from class: org.schabi.newpipe.extractor.utils.Parser$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Parser.lambda$compatParseMap$2((String[]) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }, new Function() { // from class: org.schabi.newpipe.extractor.utils.Parser$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Utils.decodeUrlUtf8(((String[]) obj)[1]);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }, new BinaryOperator() { // from class: org.schabi.newpipe.extractor.utils.Parser$$ExternalSyntheticLambda4
            public /* synthetic */ BiFunction andThen(Function function) {
                return BiFunction$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return Parser.lambda$compatParseMap$4((String) obj, (String) obj2);
            }
        }));
    }

    static /* synthetic */ boolean lambda$compatParseMap$1(String[] strArr) {
        return strArr.length > 1;
    }

    static /* synthetic */ String lambda$compatParseMap$2(String[] strArr) {
        return strArr[0];
    }
}
