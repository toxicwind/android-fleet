package com.fleeksoft.ksoup.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.fleeksoft.ksoup.ported.CharacterKt;
import com.fleeksoft.ksoup.ported.CodePoint;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: StringUtil.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001;B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\n\u001a\u00020\u00062\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\r\u001a\u00020\u0006J\u001a\u0010\n\u001a\u00020\u00062\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\r\u001a\u00020\u0006J\u0018\u0010\u0004\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010J\u0010\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u0015\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u0016\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0010J\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0010J\u000e\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0010J\u000e\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006J\"\u0010\u001c\u001a\u00020\u001d2\n\u0010\u001e\u001a\u00060\u001fj\u0002` 2\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u0013J'\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u00062\u0012\u0010$\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006¢\u0006\u0002\u0010%J#\u0010&\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u00062\u000e\u0010$\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010%J\u000e\u0010'\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0006J\u0016\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006J\u0010\u0010-\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u0006H\u0002J\n\u00103\u001a\u00060\u001fj\u0002` J\u0012\u00104\u001a\u00020\u00062\n\u00105\u001a\u00060\u001fj\u0002` J\u0012\u00106\u001a\u00020\u001d2\n\u00105\u001a\u00060\u001fj\u0002` J\u000e\u00107\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u000208J\u000e\u00109\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u000208J\u000e\u0010:\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u000208R\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u0018\u00101\u001a\f\u0012\b\u0012\u00060\u001fj\u0002` 02X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lcom/fleeksoft/ksoup/internal/StringUtil;", "", "<init>", "()V", "padding", "", "", "getPadding", "()[Ljava/lang/String;", "[Ljava/lang/String;", "join", "strings", "", "sep", "", "width", "", "maxPaddingWidth", "isBlank", "", TypedValues.Custom.S_STRING, "startsWithNewline", "isNumeric", "isWhitespace", "c", "isActuallyWhitespace", "isInvisibleChar", "normaliseWhitespace", "appendNormalisedWhitespace", "", "accum", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "stripLeading", "isIn", "needle", "haystack", "(Ljava/lang/String;[Ljava/lang/String;)Z", "inSorted", "isAscii", "resolve", "baseUrl", "relUrl", "controlChars", "Lkotlin/text/Regex;", "stripControlChars", TvContractCompat.PARAM_INPUT, "InitBuilderSize", "MaxBuilderSize", "BuilderPool", "Lcom/fleeksoft/ksoup/internal/SoftPool;", "borrowBuilder", "releaseBuilder", "sb", "releaseBuilderVoid", "isAsciiLetter", "", "isDigit", "isHexDigit", "StringJoiner", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class StringUtil {
    private static final int InitBuilderSize = 1024;
    private static final int MaxBuilderSize = 8192;
    public static final StringUtil INSTANCE = new StringUtil();
    private static final String[] padding = {"", Stream.ID_UNKNOWN, "  ", "   ", "    ", "     ", "      ", "       ", "        ", "         ", "          ", "           ", "            ", "             ", "              ", "               ", "                ", "                 ", "                  ", "                   ", "                    "};
    private static final Regex controlChars = new Regex("[\\x00-\\x1f]*");
    private static final SoftPool<StringBuilder> BuilderPool = new SoftPool<>(new Function0() { // from class: com.fleeksoft.ksoup.internal.StringUtil$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return StringUtil.BuilderPool$lambda$0();
        }
    });

    public final boolean isActuallyWhitespace(int c) {
        return c == 32 || c == 9 || c == 10 || c == 12 || c == 13 || c == 160;
    }

    public final boolean isInvisibleChar(int c) {
        return c == 8203 || c == 173;
    }

    public final boolean isWhitespace(int c) {
        return c == 32 || c == 9 || c == 10 || c == 12 || c == 13;
    }

    private StringUtil() {
    }

    public final String[] getPadding() {
        return padding;
    }

    public final String join(Collection<?> strings, String sep) {
        Intrinsics.checkNotNullParameter(strings, "strings");
        Intrinsics.checkNotNullParameter(sep, "sep");
        return join(strings.iterator(), sep);
    }

    public final String join(Iterator<?> strings, String sep) {
        Intrinsics.checkNotNullParameter(strings, "strings");
        Intrinsics.checkNotNullParameter(sep, "sep");
        if (!strings.hasNext()) {
            return "";
        }
        String strValueOf = String.valueOf(strings.next());
        if (!strings.hasNext()) {
            return strValueOf;
        }
        StringJoiner stringJoiner = new StringJoiner(sep);
        stringJoiner.add(strValueOf);
        while (strings.hasNext()) {
            stringJoiner.add(strings.next());
        }
        return stringJoiner.complete();
    }

    public static /* synthetic */ String padding$default(StringUtil stringUtil, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 30;
        }
        return stringUtil.padding(i, i2);
    }

    public final String padding(int width, int maxPaddingWidth) {
        if (width < 0) {
            throw new IllegalArgumentException("width must be >= 0".toString());
        }
        if (maxPaddingWidth < -1) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (maxPaddingWidth != -1) {
            width = Math.min(width, maxPaddingWidth);
        }
        String[] strArr = padding;
        if (width < strArr.length) {
            return strArr[width];
        }
        return StringsKt.repeat(Stream.ID_UNKNOWN, width);
    }

    public final boolean isBlank(String string) {
        String str = string;
        if (str != null && str.length() != 0) {
            int length = string.length();
            for (int i = 0; i < length; i++) {
                if (!isWhitespace(CharacterKt.codePointValueAt(str, i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public final boolean startsWithNewline(String string) {
        String str = string;
        return (str == null || str.length() == 0 || string.charAt(0) != '\n') ? false : true;
    }

    public final boolean isNumeric(String string) {
        String str = string;
        if (str == null || str.length() == 0) {
            return false;
        }
        int length = string.length();
        for (int i = 0; i < length; i++) {
            if (!isDigit(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public final String normaliseWhitespace(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        StringBuilder sbBorrowBuilder = borrowBuilder();
        appendNormalisedWhitespace(sbBorrowBuilder, string, false);
        return releaseBuilder(sbBorrowBuilder);
    }

    public final void appendNormalisedWhitespace(StringBuilder accum, String string, boolean stripLeading) {
        int charCount;
        Intrinsics.checkNotNullParameter(accum, "accum");
        Intrinsics.checkNotNullParameter(string, "string");
        int length = string.length();
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        while (i < length) {
            CodePoint codePointCodePointAt = CharacterKt.codePointAt(string, i);
            if (isActuallyWhitespace(codePointCodePointAt.getValue())) {
                if ((stripLeading && !z) || z2) {
                    charCount = codePointCodePointAt.getCharCount();
                } else {
                    accum.append(' ');
                    z2 = true;
                }
                i += charCount;
            } else if (!isInvisibleChar(codePointCodePointAt.getValue())) {
                accum.appendCodePoint(codePointCodePointAt.getValue());
                z2 = false;
                z = true;
            }
            charCount = codePointCodePointAt.getCharCount();
            i += charCount;
        }
    }

    public final boolean isIn(String needle, String... haystack) {
        Intrinsics.checkNotNullParameter(needle, "needle");
        Intrinsics.checkNotNullParameter(haystack, "haystack");
        for (String str : haystack) {
            if (Intrinsics.areEqual(str, needle)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x002c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:12:0x002d A[RETURN] */
    public final boolean inSorted(String needle, String[] haystack) {
        int i;
        Intrinsics.checkNotNullParameter(needle, "needle");
        Intrinsics.checkNotNullParameter(haystack, "haystack");
        String[] strArr = haystack;
        String str = needle;
        int length = strArr.length - 1;
        int i2 = 0;
        while (i2 <= length) {
            i = (i2 + length) >>> 1;
            int iCompareValues = ComparisonsKt.compareValues(strArr[i], str);
            if (iCompareValues < 0) {
                i2 = i + 1;
            } else {
                if (iCompareValues <= 0) {
                    if (i >= 0) {
                        return true;
                    }
                    return false;
                }
                length = i - 1;
            }
        }
        i = -(i2 + 1);
        if (i >= 0) {
            return true;
        }
        return false;
    }

    public final boolean isAscii(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        int length = string.length();
        for (int i = 0; i < length; i++) {
            if (string.charAt(i) > 127) {
                return false;
            }
        }
        return true;
    }

    public final String resolve(String baseUrl, String relUrl) {
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        Intrinsics.checkNotNullParameter(relUrl, "relUrl");
        return URLUtil.INSTANCE.resolve(stripControlChars(baseUrl), stripControlChars(relUrl));
    }

    private final String stripControlChars(String input) {
        return controlChars.replace(input, "");
    }

    static final StringBuilder BuilderPool$lambda$0() {
        return new StringBuilder(1024);
    }

    public final StringBuilder borrowBuilder() {
        return BuilderPool.borrow();
    }

    public final String releaseBuilder(StringBuilder sb) {
        Intrinsics.checkNotNullParameter(sb, "sb");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        releaseBuilderVoid(sb);
        return string;
    }

    public final void releaseBuilderVoid(StringBuilder sb) {
        Intrinsics.checkNotNullParameter(sb, "sb");
        if (sb.length() <= 8192) {
            Intrinsics.checkNotNullExpressionValue(sb.delete(0, sb.length()), "delete(...)");
            BuilderPool.release(sb);
        }
    }

    /* JADX INFO: compiled from: StringUtil.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u000b\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u0001J\u0010\u0010\r\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u0001J\u0006\u0010\u000e\u001a\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/fleeksoft/ksoup/internal/StringUtil$StringJoiner;", "", "separator", "", "<init>", "(Ljava/lang/String;)V", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "first", "", "add", "stringy", "append", "complete", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class StringJoiner {
        private boolean first;
        private StringBuilder sb;
        private final String separator;

        public StringJoiner(String separator) {
            Intrinsics.checkNotNullParameter(separator, "separator");
            this.separator = separator;
            this.sb = StringUtil.INSTANCE.borrowBuilder();
            this.first = true;
        }

        public final StringJoiner add(Object stringy) {
            if (!this.first) {
                StringBuilder sb = this.sb;
                Intrinsics.checkNotNull(sb);
                sb.append(this.separator);
            }
            StringBuilder sb2 = this.sb;
            Intrinsics.checkNotNull(sb2);
            sb2.append(stringy);
            this.first = false;
            return this;
        }

        public final StringJoiner append(Object stringy) {
            StringBuilder sb = this.sb;
            Intrinsics.checkNotNull(sb);
            sb.append(stringy);
            return this;
        }

        public final String complete() {
            StringBuilder sb = this.sb;
            String strReleaseBuilder = sb != null ? StringUtil.INSTANCE.releaseBuilder(sb) : null;
            this.sb = null;
            return strReleaseBuilder == null ? "" : strReleaseBuilder;
        }
    }

    public final boolean isAsciiLetter(char c) {
        if (Intrinsics.compare((int) c, 97) < 0 || Intrinsics.compare((int) c, 122) > 0) {
            return Intrinsics.compare((int) c, 65) >= 0 && Intrinsics.compare((int) c, 90) <= 0;
        }
        return true;
    }

    public final boolean isDigit(char c) {
        return Intrinsics.compare((int) c, 48) >= 0 && Intrinsics.compare((int) c, 57) <= 0;
    }

    public final boolean isHexDigit(char c) {
        if (isDigit(c)) {
            return true;
        }
        if (Intrinsics.compare((int) c, 97) < 0 || Intrinsics.compare((int) c, 102) > 0) {
            return Intrinsics.compare((int) c, 65) >= 0 && Intrinsics.compare((int) c, 70) <= 0;
        }
        return true;
    }
}
