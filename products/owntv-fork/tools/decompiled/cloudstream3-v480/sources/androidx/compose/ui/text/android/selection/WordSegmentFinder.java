package androidx.compose.ui.text.android.selection;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: SegmentFinder.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/text/android/selection/WordSegmentFinder;", "Landroidx/compose/ui/text/android/selection/SegmentFinder;", "text", "", "wordIterator", "Landroidx/compose/ui/text/android/selection/WordIterator;", "<init>", "(Ljava/lang/CharSequence;Landroidx/compose/ui/text/android/selection/WordIterator;)V", "previousStartBoundary", "", TypedValues.CycleType.S_WAVE_OFFSET, "previousEndBoundary", "nextStartBoundary", "nextEndBoundary", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class WordSegmentFinder implements SegmentFinder {
    public static final int $stable = 8;
    private final CharSequence text;
    private final WordIterator wordIterator;

    public WordSegmentFinder(CharSequence charSequence, WordIterator wordIterator) {
        this.text = charSequence;
        this.wordIterator = wordIterator;
    }

    @Override // androidx.compose.ui.text.android.selection.SegmentFinder
    public int previousStartBoundary(int offset) {
        do {
            offset = this.wordIterator.prevBoundary(offset);
            if (offset == -1) {
                return -1;
            }
        } while (Character.isWhitespace(this.text.charAt(offset)));
        return offset;
    }

    @Override // androidx.compose.ui.text.android.selection.SegmentFinder
    public int previousEndBoundary(int offset) {
        do {
            offset = this.wordIterator.prevBoundary(offset);
            if (offset == -1 || offset == 0) {
                return -1;
            }
        } while (Character.isWhitespace(this.text.charAt(offset - 1)));
        return offset;
    }

    @Override // androidx.compose.ui.text.android.selection.SegmentFinder
    public int nextStartBoundary(int offset) {
        do {
            offset = this.wordIterator.nextBoundary(offset);
            if (offset == -1 || offset == this.text.length()) {
                return -1;
            }
        } while (Character.isWhitespace(this.text.charAt(offset)));
        return offset;
    }

    @Override // androidx.compose.ui.text.android.selection.SegmentFinder
    public int nextEndBoundary(int offset) {
        do {
            offset = this.wordIterator.nextBoundary(offset);
            if (offset == -1) {
                return -1;
            }
        } while (Character.isWhitespace(this.text.charAt(offset - 1)));
        return offset;
    }
}
