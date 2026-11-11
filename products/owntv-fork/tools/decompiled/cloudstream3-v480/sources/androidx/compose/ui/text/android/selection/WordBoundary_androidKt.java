package androidx.compose.ui.text.android.selection;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: WordBoundary.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0000¨\u0006\u0005"}, d2 = {"getWordStart", "", "Landroidx/compose/ui/text/android/selection/WordIterator;", TypedValues.CycleType.S_WAVE_OFFSET, "getWordEnd", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class WordBoundary_androidKt {
    public static final int getWordStart(WordIterator wordIterator, int i) {
        int prevWordBeginningOnTwoWordsBoundary;
        if (wordIterator.isOnPunctuation(wordIterator.prevBoundary(i))) {
            prevWordBeginningOnTwoWordsBoundary = wordIterator.getPunctuationBeginning(i);
        } else {
            prevWordBeginningOnTwoWordsBoundary = wordIterator.getPrevWordBeginningOnTwoWordsBoundary(i);
        }
        return prevWordBeginningOnTwoWordsBoundary == -1 ? i : prevWordBeginningOnTwoWordsBoundary;
    }

    public static final int getWordEnd(WordIterator wordIterator, int i) {
        int nextWordEndOnTwoWordBoundary;
        if (wordIterator.isAfterPunctuation(wordIterator.nextBoundary(i))) {
            nextWordEndOnTwoWordBoundary = wordIterator.getPunctuationEnd(i);
        } else {
            nextWordEndOnTwoWordBoundary = wordIterator.getNextWordEndOnTwoWordBoundary(i);
        }
        return nextWordEndOnTwoWordBoundary == -1 ? i : nextWordEndOnTwoWordBoundary;
    }
}
