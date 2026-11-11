package org.schabi.newpipe.extractor.services.youtube;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.exceptions.ParsingException;
import org.schabi.newpipe.extractor.utils.JavaScript;
import org.schabi.newpipe.extractor.utils.Pair;
import org.schabi.newpipe.extractor.utils.Parser;
import org.schabi.newpipe.extractor.utils.jsextractor.JavaScriptExtractor;

/* JADX INFO: loaded from: classes8.dex */
final class YoutubeSignatureUtils {
    static final String DEOBFUSCATION_FUNCTION_NAME = "deobfuscate";
    private static final String DEOBF_FUNC_REGEX_END = "=function\\([a-zA-Z0-9_]+\\)\\{.+?\\})";
    private static final String DEOBF_FUNC_REGEX_START = "(";
    private static final Pattern[] FUNCTION_REGEXES = {Pattern.compile("\\b(?:[a-zA-Z0-9_$]+)&&\\((?:[a-zA-Z0-9_$]+)=([a-zA-Z0-9_$]{2,})\\((\\d+,)decodeURIComponent\\((?:[a-zA-Z0-9_$]+)\\)\\)"), Pattern.compile("\\b(?:[a-zA-Z0-9_$]+)&&\\((?:[a-zA-Z0-9_$]+)=([a-zA-Z0-9_$]{2,})\\(decodeURIComponent\\((?:[a-zA-Z0-9_$]+)\\)\\)"), Pattern.compile("\\bm=([a-zA-Z0-9$]{2,})\\(decodeURIComponent\\(h\\.s\\)\\)"), Pattern.compile("\\bc&&\\(c=([a-zA-Z0-9$]{2,})\\(decodeURIComponent\\(c\\)\\)"), Pattern.compile("(?:\\b|[^a-zA-Z0-9$])([a-zA-Z0-9$]{2,})\\s*=\\s*function\\(\\s*a\\s*\\)\\s*\\{\\s*a\\s*=\\s*a\\.split\\(\\s*\"\"\\s*\\)"), Pattern.compile("([\\w$]+)\\s*=\\s*function\\((\\w+)\\)\\{\\s*\\2=\\s*\\2\\.split\\(\"\"\\)\\s*;")};
    private static final Pattern SIG_DEOBF_GLOBAL_ARRAY_REGEX = Pattern.compile("(var [A-z]=['\"].*['\"].split\\(\"[;{]\"\\))");
    private static final Pattern SIG_DEOBF_HELPER_OBJ_NAME_REGEX = Pattern.compile("[;,]([A-Za-z0-9_$]{2,})\\[..");
    private static final String SIG_DEOBF_HELPER_OBJ_REGEX_END = "=\\{(?>.|\\n)+?\\}\\};)";
    private static final String SIG_DEOBF_HELPER_OBJ_REGEX_START = "(var ";
    private static final String STS_REGEX = "signatureTimestamp[=:](\\d+)";

    private YoutubeSignatureUtils() {
    }

    @Nonnull
    static String getSignatureTimestamp(@Nonnull String str) throws ParsingException {
        try {
            return Parser.matchGroup1(STS_REGEX, str);
        } catch (ParsingException e) {
            throw new ParsingException("Could not extract signature timestamp from JavaScript code", e);
        }
    }

    @Nonnull
    static String getDeobfuscationCode(@Nonnull String str) throws ParsingException {
        String deobfuscateFunctionWithRegex;
        try {
            Pair<String, String> deobfuscationFunctionNameAndParams = getDeobfuscationFunctionNameAndParams(str);
            String str2 = (String) deobfuscationFunctionNameAndParams.getFirst();
            String str3 = (String) deobfuscationFunctionNameAndParams.getSecond();
            try {
                deobfuscateFunctionWithRegex = getDeobfuscateFunctionWithLexer(str, str2);
            } catch (Exception unused) {
                deobfuscateFunctionWithRegex = getDeobfuscateFunctionWithRegex(str, str2);
            }
            JavaScript.compileOrThrow(deobfuscateFunctionWithRegex);
            return Parser.matchGroup1(SIG_DEOBF_GLOBAL_ARRAY_REGEX, str) + ";" + getHelperObject(str, Parser.matchGroup1(SIG_DEOBF_HELPER_OBJ_NAME_REGEX, deobfuscateFunctionWithRegex)) + deobfuscateFunctionWithRegex + ";" + ("function deobfuscate(a){return " + str2 + DEOBF_FUNC_REGEX_START + str3 + "a);}");
        } catch (Exception e) {
            throw new ParsingException("Could not parse deobfuscation function", e);
        }
    }

    @Nonnull
    private static Pair<String, String> getDeobfuscationFunctionNameAndParams(@Nonnull String str) throws ParsingException {
        String strGroup;
        try {
            Matcher matcherMatchMultiplePatterns = Parser.matchMultiplePatterns(FUNCTION_REGEXES, str);
            String strGroup2 = matcherMatchMultiplePatterns.group(1);
            if (matcherMatchMultiplePatterns.groupCount() > 1) {
                strGroup = matcherMatchMultiplePatterns.group(2);
            } else {
                strGroup = "";
            }
            return new Pair<>(strGroup2, strGroup);
        } catch (Parser.RegexException e) {
            throw new ParsingException("Could not find deobfuscation function with any of the known patterns", e);
        }
    }

    @Nonnull
    private static String getDeobfuscateFunctionWithLexer(@Nonnull String str, @Nonnull String str2) throws ParsingException {
        String str3 = str2 + "=function";
        return str3 + JavaScriptExtractor.matchToClosingBrace(str, str3);
    }

    @Nonnull
    private static String getDeobfuscateFunctionWithRegex(@Nonnull String str, @Nonnull String str2) throws ParsingException {
        return "var " + Parser.matchGroup1(DEOBF_FUNC_REGEX_START + Pattern.quote(str2) + DEOBF_FUNC_REGEX_END, str);
    }

    @Nonnull
    private static String getHelperObject(@Nonnull String str, @Nonnull String str2) throws ParsingException {
        return Parser.matchGroup1(SIG_DEOBF_HELPER_OBJ_REGEX_START + Pattern.quote(str2) + SIG_DEOBF_HELPER_OBJ_REGEX_END, str).replace("\n", "");
    }
}
