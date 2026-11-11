package androidx.compose.ui.text.android.selection;

import androidx.compose.ui.text.android.CharSequenceCharacterIterator;
import androidx.compose.ui.text.internal.InlineClassHelperKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.emoji2.text.EmojiCompat;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.text.BreakIterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: WordIterator.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 #2\u00020\u0001:\u0001#B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005J\u000e\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005J\u000e\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005J\u000e\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005J\u000e\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005J\u000e\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u0005J\u000e\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u0005J\u0018\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0017H\u0002J\u0018\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0017H\u0002J\u0010\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u0005H\u0002J\u0010\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u0005H\u0002J\u0010\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u0005H\u0002J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u0005H\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u0010\u001a\u00020\u0005H\u0002J\u0010\u0010!\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u0005H\u0002J\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u0005H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Landroidx/compose/ui/text/android/selection/WordIterator;", "", "charSequence", "", "start", "", TtmlNode.END, "locale", "Ljava/util/Locale;", "<init>", "(Ljava/lang/CharSequence;IILjava/util/Locale;)V", "getCharSequence", "()Ljava/lang/CharSequence;", "iterator", "Ljava/text/BreakIterator;", "nextBoundary", TypedValues.CycleType.S_WAVE_OFFSET, "prevBoundary", "getPrevWordBeginningOnTwoWordsBoundary", "getNextWordEndOnTwoWordBoundary", "getPunctuationBeginning", "getPunctuationEnd", "isAfterPunctuation", "", "isOnPunctuation", "getBeginning", "getEnd", "isPunctuationStartBoundary", "isPunctuationEndBoundary", "isAfterLetterOrDigitOrEmoji", "isOnLetterOrDigitOrEmoji", "checkOffsetIsValid", "", "isBoundary", "isHiraganaKatakanaBoundary", "Companion", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class WordIterator {
    private static final int WINDOW_WIDTH = 50;
    private final CharSequence charSequence;
    private final int end;
    private final BreakIterator iterator;
    private final int start;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public WordIterator(CharSequence charSequence, int i, int i2, Locale locale) {
        this.charSequence = charSequence;
        if (!(i >= 0 && i <= charSequence.length())) {
            InlineClassHelperKt.throwIllegalArgumentException("input start index is outside the CharSequence");
        }
        if (!(i2 >= 0 && i2 <= charSequence.length())) {
            InlineClassHelperKt.throwIllegalArgumentException("input end index is outside the CharSequence");
        }
        BreakIterator wordInstance = BreakIterator.getWordInstance(locale);
        this.iterator = wordInstance;
        this.start = Math.max(0, i - 50);
        this.end = Math.min(charSequence.length(), i2 + 50);
        wordInstance.setText(new CharSequenceCharacterIterator(charSequence, i, i2));
    }

    public final CharSequence getCharSequence() {
        return this.charSequence;
    }

    public final int nextBoundary(int offset) {
        checkOffsetIsValid(offset);
        int iFollowing = this.iterator.following(offset);
        return (isOnLetterOrDigitOrEmoji(iFollowing + (-1)) && isOnLetterOrDigitOrEmoji(iFollowing) && !isHiraganaKatakanaBoundary(iFollowing)) ? nextBoundary(iFollowing) : iFollowing;
    }

    public final int prevBoundary(int offset) {
        checkOffsetIsValid(offset);
        int iPreceding = this.iterator.preceding(offset);
        return (isOnLetterOrDigitOrEmoji(iPreceding) && isAfterLetterOrDigitOrEmoji(iPreceding) && !isHiraganaKatakanaBoundary(iPreceding)) ? prevBoundary(iPreceding) : iPreceding;
    }

    public final int getPrevWordBeginningOnTwoWordsBoundary(int offset) {
        return getBeginning(offset, true);
    }

    public final int getNextWordEndOnTwoWordBoundary(int offset) {
        return getEnd(offset, true);
    }

    public final int getPunctuationBeginning(int offset) {
        checkOffsetIsValid(offset);
        while (offset != -1 && !isPunctuationStartBoundary(offset)) {
            offset = prevBoundary(offset);
        }
        return offset;
    }

    public final int getPunctuationEnd(int offset) {
        checkOffsetIsValid(offset);
        while (offset != -1 && !isPunctuationEndBoundary(offset)) {
            offset = nextBoundary(offset);
        }
        return offset;
    }

    public final boolean isAfterPunctuation(int offset) {
        int i = this.start + 1;
        if (offset > this.end || i > offset) {
            return false;
        }
        return INSTANCE.isPunctuation$ui_text(Character.codePointBefore(this.charSequence, offset));
    }

    public final boolean isOnPunctuation(int offset) {
        int i = this.start;
        if (offset >= this.end || i > offset) {
            return false;
        }
        return INSTANCE.isPunctuation$ui_text(Character.codePointAt(this.charSequence, offset));
    }

    private final int getBeginning(int offset, boolean getPrevWordBeginningOnTwoWordsBoundary) {
        checkOffsetIsValid(offset);
        if (isOnLetterOrDigitOrEmoji(offset)) {
            return (!isBoundary(offset) || (isAfterLetterOrDigitOrEmoji(offset) && getPrevWordBeginningOnTwoWordsBoundary)) ? prevBoundary(offset) : offset;
        }
        if (isAfterLetterOrDigitOrEmoji(offset)) {
            return prevBoundary(offset);
        }
        return -1;
    }

    private final int getEnd(int offset, boolean getNextWordEndOnTwoWordBoundary) {
        checkOffsetIsValid(offset);
        if (isAfterLetterOrDigitOrEmoji(offset)) {
            return (!isBoundary(offset) || (isOnLetterOrDigitOrEmoji(offset) && getNextWordEndOnTwoWordBoundary)) ? nextBoundary(offset) : offset;
        }
        if (isOnLetterOrDigitOrEmoji(offset)) {
            return nextBoundary(offset);
        }
        return -1;
    }

    private final boolean isPunctuationStartBoundary(int offset) {
        return isOnPunctuation(offset) && !isAfterPunctuation(offset);
    }

    private final boolean isPunctuationEndBoundary(int offset) {
        return !isOnPunctuation(offset) && isAfterPunctuation(offset);
    }

    private final boolean isAfterLetterOrDigitOrEmoji(int offset) {
        int i = this.start + 1;
        if (offset > this.end || i > offset) {
            return false;
        }
        if (Character.isLetterOrDigit(Character.codePointBefore(this.charSequence, offset))) {
            return true;
        }
        int i2 = offset - 1;
        if (Character.isSurrogate(this.charSequence.charAt(i2))) {
            return true;
        }
        if (!EmojiCompat.isConfigured()) {
            return false;
        }
        EmojiCompat emojiCompat = EmojiCompat.get();
        return emojiCompat.getLoadState() == 1 && emojiCompat.getEmojiStart(this.charSequence, i2) != -1;
    }

    private final boolean isOnLetterOrDigitOrEmoji(int offset) {
        int i = this.start;
        if (offset >= this.end || i > offset) {
            return false;
        }
        if (Character.isLetterOrDigit(Character.codePointAt(this.charSequence, offset)) || Character.isSurrogate(this.charSequence.charAt(offset))) {
            return true;
        }
        if (!EmojiCompat.isConfigured()) {
            return false;
        }
        EmojiCompat emojiCompat = EmojiCompat.get();
        return emojiCompat.getLoadState() == 1 && emojiCompat.getEmojiStart(this.charSequence, offset) != -1;
    }

    private final void checkOffsetIsValid(int offset) {
        int i = this.start;
        boolean z = false;
        if (offset <= this.end && i <= offset) {
            z = true;
        }
        if (z) {
            return;
        }
        InlineClassHelperKt.throwIllegalArgumentException("Invalid offset: " + offset + ". Valid range is [" + this.start + " , " + this.end + AbstractJsonLexerKt.END_LIST);
    }

    private final boolean isBoundary(int offset) {
        checkOffsetIsValid(offset);
        if (!this.iterator.isBoundary(offset)) {
            return false;
        }
        if (isOnLetterOrDigitOrEmoji(offset) && isOnLetterOrDigitOrEmoji(offset - 1) && isOnLetterOrDigitOrEmoji(offset + 1)) {
            return false;
        }
        return offset <= 0 || offset >= this.charSequence.length() - 1 || !(isHiraganaKatakanaBoundary(offset) || isHiraganaKatakanaBoundary(offset + 1));
    }

    private final boolean isHiraganaKatakanaBoundary(int offset) {
        int i = offset - 1;
        if (Intrinsics.areEqual(Character.UnicodeBlock.of(this.charSequence.charAt(i)), Character.UnicodeBlock.HIRAGANA) && Intrinsics.areEqual(Character.UnicodeBlock.of(this.charSequence.charAt(offset)), Character.UnicodeBlock.KATAKANA)) {
            return true;
        }
        return Intrinsics.areEqual(Character.UnicodeBlock.of(this.charSequence.charAt(offset)), Character.UnicodeBlock.HIRAGANA) && Intrinsics.areEqual(Character.UnicodeBlock.of(this.charSequence.charAt(i)), Character.UnicodeBlock.KATAKANA);
    }

    /* JADX INFO: compiled from: WordIterator.android.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/compose/ui/text/android/selection/WordIterator$Companion;", "", "<init>", "()V", "WINDOW_WIDTH", "", "isPunctuation", "", "cp", "isPunctuation$ui_text", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isPunctuation$ui_text(int cp) {
            int type = Character.getType(cp);
            return type == 23 || type == 20 || type == 22 || type == 30 || type == 29 || type == 24 || type == 21;
        }
    }
}
