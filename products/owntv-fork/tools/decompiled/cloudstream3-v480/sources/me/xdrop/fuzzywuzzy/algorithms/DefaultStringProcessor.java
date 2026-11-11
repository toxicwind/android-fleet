package me.xdrop.fuzzywuzzy.algorithms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.xdrop.fuzzywuzzy.StringProcessor;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: loaded from: classes5.dex */
@Deprecated
public class DefaultStringProcessor extends StringProcessor {
    private static final String pattern = "[^\\p{Alnum}]";
    private static final Pattern r = compilePattern();

    public static String subNonAlphaNumeric(String str, String str2) {
        Matcher matcher = r.matcher(str);
        return matcher.find() ? matcher.replaceAll(str2) : str;
    }

    @Override // me.xdrop.fuzzywuzzy.StringProcessor
    public String process(String str) {
        return subNonAlphaNumeric(str, Stream.ID_UNKNOWN).toLowerCase().trim();
    }

    private static Pattern compilePattern() {
        try {
            return Pattern.compile(pattern, 256);
        } catch (IllegalArgumentException unused) {
            return Pattern.compile(pattern);
        }
    }
}
