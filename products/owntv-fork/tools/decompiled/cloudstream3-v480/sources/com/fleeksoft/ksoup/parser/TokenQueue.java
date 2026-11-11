package com.fleeksoft.ksoup.parser;

import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.fleeksoft.ksoup.exception.UncheckedIOException;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.internal.StringUtil;
import com.fleeksoft.ksoup.ported.Character;
import io.ktor.util.date.GMTDateParser;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TokenQueue.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0019\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 *2\u00060\u0001j\u0002`\u0002:\u0001*B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\fJ\u000e\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0004J\u000e\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\fJ\u0012\u0010\u0013\u001a\u00020\n2\n\u0010\u0011\u001a\u00020\u0014\"\u00020\fJ\u000e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0004J\u000e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\fJ\u0006\u0010\u0016\u001a\u00020\nJ\u0006\u0010\u0017\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0004J\u000e\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004J\u001f\u0010\u0019\u001a\u00020\u00042\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u001a\"\u00020\u0004¢\u0006\u0002\u0010\u001bJ\u0016\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\fJ\u0006\u0010\u001f\u001a\u00020\nJ\u0006\u0010 \u001a\u00020\u0004J\u0006\u0010!\u001a\u00020\u0004J\u0014\u0010\"\u001a\u00020\u000e2\n\u0010#\u001a\u00060$j\u0002`%H\u0002J\u0014\u0010&\u001a\u00020\u00042\n\u0010\u0010\u001a\u00020\u0014\"\u00020\fH\u0002J\u0014\u0010'\u001a\u00020\n2\n\u0010\u0010\u001a\u00020\u0014\"\u00020\fH\u0002J\u0006\u0010(\u001a\u00020\u0004J\n\u0010)\u001a\u00020\u0004H\u0096\u0080\u0004J\b\u0010\u001e\u001a\u00020\u000eH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/fleeksoft/ksoup/parser/TokenQueue;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "data", "", "<init>", "(Ljava/lang/String;)V", "reader", "Lcom/fleeksoft/ksoup/parser/CharacterReader;", "isEmpty", "", "consume", "", "advance", "", "current", "matches", "seq", "c", "matchesAny", "", "matchChomp", "matchesWhitespace", "matchesWord", "consumeTo", "consumeToAny", "", "([Ljava/lang/String;)Ljava/lang/String;", "chompBalanced", TtmlNode.TEXT_EMPHASIS_MARK_OPEN, "close", "consumeWhitespace", "consumeElementSelector", "consumeCssIdentifier", "consumeCssEscapeSequenceInto", "out", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "consumeEscapedCssIdentifier", "matchesCssIdentifier", "remainder", "toString", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TokenQueue implements AutoCloseable {
    private static final char ESC = '\\';
    private static final char REPLACEMENT = 65533;
    private static final char UNICODE_NULL = 0;
    private final CharacterReader reader;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final char HYPHEN_MINUS = '-';
    private static final char[] ELEMENT_SELECTOR_CHARS = {GMTDateParser.ANY, '|', '_', HYPHEN_MINUS};

    @JvmStatic
    public static final String escapeCssIdentifier(String str) {
        return INSTANCE.escapeCssIdentifier(str);
    }

    @JvmStatic
    public static final String unescape(String str) {
        return INSTANCE.unescape(str);
    }

    public TokenQueue(String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.reader = new CharacterReader(data);
    }

    public final boolean isEmpty() {
        return this.reader.isEmpty();
    }

    public final char consume() {
        return this.reader.consume();
    }

    public final void advance() {
        if (isEmpty()) {
            return;
        }
        this.reader.advance();
    }

    public final char current() {
        return this.reader.current();
    }

    public final boolean matches(String seq) {
        Intrinsics.checkNotNullParameter(seq, "seq");
        return this.reader.matchesIgnoreCase(seq);
    }

    public final boolean matches(char c) {
        return this.reader.matches(c);
    }

    public final boolean matchesAny(char... seq) {
        Intrinsics.checkNotNullParameter(seq, "seq");
        return this.reader.matchesAny(Arrays.copyOf(seq, seq.length));
    }

    public final boolean matchChomp(String seq) {
        Intrinsics.checkNotNullParameter(seq, "seq");
        return this.reader.matchConsumeIgnoreCase(seq);
    }

    public final boolean matchChomp(char c) {
        if (!this.reader.matches(c)) {
            return false;
        }
        consume();
        return true;
    }

    public final boolean matchesWhitespace() {
        return CharsKt.isWhitespace(this.reader.current());
    }

    public final boolean matchesWord() {
        return Character.isLetterOrDigit(this.reader.current());
    }

    public final void consume(String seq) {
        Intrinsics.checkNotNullParameter(seq, "seq");
        if (!this.reader.matchConsumeIgnoreCase(seq)) {
            throw new IllegalStateException("Queue did not match expected sequence");
        }
    }

    public final String consumeTo(String seq) {
        Intrinsics.checkNotNullParameter(seq, "seq");
        return this.reader.consumeTo(seq);
    }

    public final String consumeToAny(String... seq) {
        Intrinsics.checkNotNullParameter(seq, "seq");
        StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
        loop0: while (!isEmpty()) {
            for (String str : seq) {
                if (this.reader.matchesIgnoreCase(str)) {
                    break loop0;
                }
            }
            sbBorrowBuilder.append(consume());
        }
        return StringUtil.INSTANCE.releaseBuilder(sbBorrowBuilder);
    }

    public final String chompBalanced(char open, char close) throws UncheckedIOException {
        StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
        this.reader.mark();
        char c = 0;
        boolean z = false;
        boolean z2 = false;
        int i = 0;
        boolean z3 = false;
        while (!isEmpty()) {
            char cConsume = consume();
            if (c == '\\') {
                if (cConsume == 'E') {
                    z3 = false;
                } else if (cConsume == 'Q') {
                    z3 = true;
                }
                sbBorrowBuilder.append(cConsume);
            } else {
                if (cConsume == '\'' && cConsume != open && !z) {
                    z2 = !z2;
                } else if (cConsume == '\"' && cConsume != open && !z2) {
                    z = !z;
                }
                if (z2 || z || z3) {
                    sbBorrowBuilder.append(cConsume);
                } else if (cConsume == open) {
                    i++;
                    if (i > 1) {
                        sbBorrowBuilder.append(cConsume);
                    }
                    Unit unit = Unit.INSTANCE;
                } else if (cConsume == close) {
                    i--;
                    if (i > 0) {
                        sbBorrowBuilder.append(cConsume);
                    }
                    Unit unit2 = Unit.INSTANCE;
                } else {
                    sbBorrowBuilder.append(cConsume);
                }
            }
            if (i <= 0) {
                break;
            }
            c = cConsume;
        }
        String strReleaseBuilder = StringUtil.INSTANCE.releaseBuilder(sbBorrowBuilder);
        if (i > 0) {
            this.reader.rewindToMark();
            Validate.INSTANCE.fail("Did not find balanced marker at '" + strReleaseBuilder + "'");
        }
        return strReleaseBuilder;
    }

    public final boolean consumeWhitespace() {
        boolean z = false;
        while (matchesWhitespace()) {
            advance();
            z = true;
        }
        return z;
    }

    public final String consumeElementSelector() {
        char[] cArr = ELEMENT_SELECTOR_CHARS;
        return consumeEscapedCssIdentifier(Arrays.copyOf(cArr, cArr.length));
    }

    public final String consumeCssIdentifier() throws UncheckedIOException {
        if (isEmpty()) {
            throw new IllegalArgumentException("CSS identifier expected, but end of input found");
        }
        String strConsumeMatching = this.reader.consumeMatching(new TokenQueue$consumeCssIdentifier$identifier$1(INSTANCE));
        char cCurrent = current();
        if (cCurrent != '\\' && cCurrent != 0) {
            return strConsumeMatching;
        }
        StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
        if (strConsumeMatching.length() > 0) {
            sbBorrowBuilder.append(strConsumeMatching);
        }
        while (!isEmpty()) {
            char cCurrent2 = current();
            Companion companion = INSTANCE;
            if (!companion.isIdent(cCurrent2)) {
                if (cCurrent2 != 0) {
                    if (cCurrent2 != '\\') {
                        break;
                    }
                    advance();
                    if (!isEmpty() && companion.isNewline(current())) {
                        this.reader.unconsume();
                        break;
                    }
                    consumeCssEscapeSequenceInto(sbBorrowBuilder);
                    Unit unit = Unit.INSTANCE;
                } else {
                    advance();
                    sbBorrowBuilder.append((char) 65533);
                }
            } else {
                sbBorrowBuilder.append(consume());
            }
        }
        return StringUtil.INSTANCE.releaseBuilder(sbBorrowBuilder);
    }

    private final void consumeCssEscapeSequenceInto(StringBuilder out) throws UncheckedIOException {
        if (isEmpty()) {
            out.append((char) 65533);
            return;
        }
        char cConsume = consume();
        if (!StringUtil.INSTANCE.isHexDigit(cConsume)) {
            out.append(cConsume);
            return;
        }
        this.reader.unconsume();
        String strConsumeMatching = this.reader.consumeMatching(new Function1() { // from class: com.fleeksoft.ksoup.parser.TokenQueue$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(StringUtil.INSTANCE.isHexDigit(((Character) obj).charValue()));
            }
        }, 6);
        Integer intOrNull = StringsKt.toIntOrNull(strConsumeMatching, 16);
        if (intOrNull == null) {
            throw new IllegalArgumentException("Invalid escape sequence: " + strConsumeMatching);
        }
        int iIntValue = intOrNull.intValue();
        Companion companion = INSTANCE;
        if (companion.isValidCodePoint(iIntValue)) {
            out.appendCodePoint(iIntValue);
        } else {
            out.append((char) 65533);
        }
        if (isEmpty()) {
            return;
        }
        char cCurrent = current();
        if (cCurrent == '\r') {
            advance();
            if (isEmpty() || current() != '\n') {
                return;
            }
            advance();
            return;
        }
        if (cCurrent == ' ' || cCurrent == '\t' || companion.isNewline(cCurrent)) {
            advance();
        }
    }

    private final String consumeEscapedCssIdentifier(char... matches) {
        StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
        while (!isEmpty()) {
            char cCurrent = current();
            if (cCurrent == '\\') {
                advance();
                if (isEmpty()) {
                    break;
                }
                sbBorrowBuilder.append(consume());
            } else {
                if (!matchesCssIdentifier(Arrays.copyOf(matches, matches.length))) {
                    break;
                }
                sbBorrowBuilder.append(cCurrent);
                advance();
            }
        }
        return StringUtil.INSTANCE.releaseBuilder(sbBorrowBuilder);
    }

    private final boolean matchesCssIdentifier(char... matches) {
        return matchesWord() || this.reader.matchesAny(Arrays.copyOf(matches, matches.length));
    }

    public final String remainder() {
        return this.reader.consumeToEnd();
    }

    public String toString() {
        return this.reader.toString();
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.reader.close();
    }

    /* JADX INFO: compiled from: TokenQueue.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0007J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0007J\u001c\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u00060\u0012j\u0002`\u00132\u0006\u0010\u0014\u001a\u00020\u0005H\u0002J\u001c\u0010\u0015\u001a\u00020\u00102\n\u0010\u0011\u001a\u00060\u0012j\u0002`\u00132\u0006\u0010\u0014\u001a\u00020\u0005H\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0005H\u0002J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0005H\u0002J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0005H\u0002J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0005H\u0002J\u0010\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/fleeksoft/ksoup/parser/TokenQueue$Companion;", "", "<init>", "()V", "ESC", "", "HYPHEN_MINUS", "UNICODE_NULL", "REPLACEMENT", "ELEMENT_SELECTOR_CHARS", "", "unescape", "", TvContractCompat.PARAM_INPUT, "escapeCssIdentifier", "appendEscaped", "", "out", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "c", "appendEscapedCodepoint", "isNonAscii", "", "isIdentStart", "isIdent", "isNewline", "isValidCodePoint", "codePoint", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isNewline(char c) {
            return c == '\n' || c == '\r' || c == '\f';
        }

        private Companion() {
        }

        @JvmStatic
        public final String unescape(String input) {
            Intrinsics.checkNotNullParameter(input, "input");
            if (!StringsKt.contains$default((CharSequence) input, '\\', false, 2, (Object) null)) {
                return input;
            }
            StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
            int length = input.length();
            char c = 0;
            for (int i = 0; i < length; i++) {
                char cCharAt = input.charAt(i);
                if (cCharAt == '\\') {
                    if (c == '\\') {
                        sbBorrowBuilder.append(cCharAt);
                        c = 0;
                    }
                } else {
                    sbBorrowBuilder.append(cCharAt);
                }
                c = cCharAt;
            }
            return StringUtil.INSTANCE.releaseBuilder(sbBorrowBuilder);
        }

        @JvmStatic
        public final String escapeCssIdentifier(String input) {
            Intrinsics.checkNotNullParameter(input, "input");
            if (input.length() == 0) {
                return input;
            }
            StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
            TokenQueue tokenQueue = new TokenQueue(input);
            char cCurrent = tokenQueue.current();
            if (cCurrent == '-') {
                tokenQueue.advance();
                if (tokenQueue.isEmpty()) {
                    appendEscaped(sbBorrowBuilder, TokenQueue.HYPHEN_MINUS);
                } else {
                    sbBorrowBuilder.append(TokenQueue.HYPHEN_MINUS);
                    if (StringUtil.INSTANCE.isDigit(tokenQueue.current())) {
                        appendEscapedCodepoint(sbBorrowBuilder, tokenQueue.consume());
                    }
                }
            } else if (StringUtil.INSTANCE.isDigit(cCurrent)) {
                appendEscapedCodepoint(sbBorrowBuilder, tokenQueue.consume());
            }
            while (!tokenQueue.isEmpty()) {
                char cConsume = tokenQueue.consume();
                if (cConsume == 0) {
                    sbBorrowBuilder.append((char) 65533);
                } else if (Intrinsics.compare((int) cConsume, 31) <= 0 || cConsume == 127) {
                    appendEscapedCodepoint(sbBorrowBuilder, cConsume);
                } else if (isIdent(cConsume)) {
                    sbBorrowBuilder.append(cConsume);
                } else {
                    appendEscaped(sbBorrowBuilder, cConsume);
                }
            }
            tokenQueue.close();
            return StringUtil.INSTANCE.releaseBuilder(sbBorrowBuilder);
        }

        private final void appendEscaped(StringBuilder out, char c) {
            out.append('\\');
            out.append(c);
        }

        private final void appendEscapedCodepoint(StringBuilder out, char c) {
            out.append('\\');
            String string = Integer.toString(c, CharsKt.checkRadix(16));
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            out.append(string);
            out.append(' ');
        }

        private final boolean isNonAscii(char c) {
            return Intrinsics.compare((int) c, 128) >= 0;
        }

        private final boolean isIdentStart(char c) {
            return c == '_' || StringUtil.INSTANCE.isAsciiLetter(c) || isNonAscii(c);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isIdent(char c) {
            return c == '-' || StringUtil.INSTANCE.isDigit(c) || isIdentStart(c);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isValidCodePoint(int codePoint) {
            return (codePoint == 0 || !Character.INSTANCE.isValidCodePoint(codePoint) || CharsKt.isSurrogate((char) codePoint)) ? false : true;
        }
    }
}
