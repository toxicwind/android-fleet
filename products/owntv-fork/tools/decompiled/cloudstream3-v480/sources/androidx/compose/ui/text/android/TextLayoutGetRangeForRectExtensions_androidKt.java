package androidx.compose.ui.text.android;

import android.graphics.RectF;
import android.text.Layout;
import androidx.compose.ui.text.android.selection.SegmentFinder;
import androidx.compose.ui.text.android.selection.SegmentFinder_androidKt;
import androidx.compose.ui.text.android.selection.WordSegmentFinder;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TextLayoutGetRangeForRectExtensions.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\b\u001aH\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\r0\fH\u0000\u001aV\u0010\u000e\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0012\u001a\u00020\rH\u0002\u001af\u0010\u0013\u001a\u00020\n*\u00020\u00142\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\u00112\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\r0\fH\u0002\u001af\u0010\u001d\u001a\u00020\n*\u00020\u00142\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\u00112\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\r0\fH\u0002\u001a \u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002\u001a \u0010 \u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002\u001a\u001c\u0010!\u001a\u00020\r*\u00020\b2\u0006\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020\u0019H\u0002¨\u0006$"}, d2 = {"getRangeForRect", "", "Landroidx/compose/ui/text/android/TextLayout;", TtmlNode.TAG_LAYOUT, "Landroid/text/Layout;", "layoutHelper", "Landroidx/compose/ui/text/android/LayoutHelper;", "rect", "Landroid/graphics/RectF;", "granularity", "", "inclusionStrategy", "Lkotlin/Function2;", "", "getStartOrEndOffsetForRectWithinLine", "lineIndex", "segmentFinder", "Landroidx/compose/ui/text/android/selection/SegmentFinder;", "getStart", "getStartOffsetForRectWithinRun", "Landroidx/compose/ui/text/android/LayoutHelper$BidiRun;", "lineStart", "lineTop", "lineBottom", "runLeft", "", "runRight", "horizontalBounds", "", "getEndOffsetForRectWithinRun", "getCharacterLeftBounds", TypedValues.CycleType.S_WAVE_OFFSET, "getCharacterRightBounds", "horizontalOverlap", TtmlNode.LEFT, TtmlNode.RIGHT, "ui-text"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextLayoutGetRangeForRectExtensions_androidKt {
    public static final int[] getRangeForRect(TextLayout textLayout, Layout layout, LayoutHelper layoutHelper, RectF rectF, int i, Function2<? super RectF, ? super RectF, Boolean> function2) {
        WordSegmentFinder wordSegmentFinderCreateGraphemeClusterSegmentFinder;
        int i2;
        if (i == 1) {
            wordSegmentFinderCreateGraphemeClusterSegmentFinder = new WordSegmentFinder(textLayout.getText(), textLayout.getWordIterator());
        } else {
            wordSegmentFinderCreateGraphemeClusterSegmentFinder = SegmentFinder_androidKt.createGraphemeClusterSegmentFinder(textLayout.getText(), textLayout.getTextPaint());
        }
        SegmentFinder segmentFinder = wordSegmentFinderCreateGraphemeClusterSegmentFinder;
        int lineForVertical = layout.getLineForVertical((int) rectF.top);
        if (rectF.top > textLayout.getLineBottom(lineForVertical) && (lineForVertical = lineForVertical + 1) >= textLayout.getLineCount()) {
            return null;
        }
        int i3 = lineForVertical;
        int lineForVertical2 = layout.getLineForVertical((int) rectF.bottom);
        if (lineForVertical2 == 0 && rectF.bottom < textLayout.getLineTop(0)) {
            return null;
        }
        int startOrEndOffsetForRectWithinLine = getStartOrEndOffsetForRectWithinLine(textLayout, layout, layoutHelper, i3, rectF, segmentFinder, function2, true);
        while (true) {
            i2 = i3;
            if (startOrEndOffsetForRectWithinLine != -1 || i2 >= lineForVertical2) {
                break;
            }
            i3 = i2 + 1;
            startOrEndOffsetForRectWithinLine = getStartOrEndOffsetForRectWithinLine(textLayout, layout, layoutHelper, i3, rectF, segmentFinder, function2, true);
        }
        if (startOrEndOffsetForRectWithinLine == -1) {
            return null;
        }
        int startOrEndOffsetForRectWithinLine2 = getStartOrEndOffsetForRectWithinLine(textLayout, layout, layoutHelper, lineForVertical2, rectF, segmentFinder, function2, false);
        while (startOrEndOffsetForRectWithinLine2 == -1 && i2 < lineForVertical2) {
            int i4 = lineForVertical2 - 1;
            startOrEndOffsetForRectWithinLine2 = getStartOrEndOffsetForRectWithinLine(textLayout, layout, layoutHelper, i4, rectF, segmentFinder, function2, false);
            lineForVertical2 = i4;
        }
        if (startOrEndOffsetForRectWithinLine2 == -1) {
            return null;
        }
        return new int[]{segmentFinder.previousStartBoundary(startOrEndOffsetForRectWithinLine + 1), segmentFinder.nextEndBoundary(startOrEndOffsetForRectWithinLine2 - 1)};
    }

    private static final int getStartOrEndOffsetForRectWithinLine(TextLayout textLayout, Layout layout, LayoutHelper layoutHelper, int i, RectF rectF, SegmentFinder segmentFinder, Function2<? super RectF, ? super RectF, Boolean> function2, boolean z) {
        IntRange intRangeDownTo;
        float characterLeftBounds;
        float characterRightBounds;
        int endOffsetForRectWithinRun;
        int lineTop = layout.getLineTop(i);
        int lineBottom = layout.getLineBottom(i);
        int lineStart = layout.getLineStart(i);
        int lineEnd = layout.getLineEnd(i);
        if (lineStart == lineEnd) {
            return -1;
        }
        float[] fArr = new float[(lineEnd - lineStart) * 2];
        textLayout.fillLineHorizontalBounds$ui_text(i, fArr);
        LayoutHelper.BidiRun[] lineBidiRuns$ui_text = layoutHelper.getLineBidiRuns$ui_text(i);
        if (z) {
            intRangeDownTo = ArraysKt.getIndices(lineBidiRuns$ui_text);
        } else {
            intRangeDownTo = RangesKt.downTo(ArraysKt.getLastIndex(lineBidiRuns$ui_text), 0);
        }
        int first = intRangeDownTo.getFirst();
        int last = intRangeDownTo.getLast();
        int step = intRangeDownTo.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            int i2 = first;
            while (true) {
                LayoutHelper.BidiRun bidiRun = lineBidiRuns$ui_text[i2];
                if (bidiRun.isRtl()) {
                    characterLeftBounds = getCharacterLeftBounds(bidiRun.getEnd() - 1, lineStart, fArr);
                } else {
                    characterLeftBounds = getCharacterLeftBounds(bidiRun.getStart(), lineStart, fArr);
                }
                float f = characterLeftBounds;
                if (bidiRun.isRtl()) {
                    characterRightBounds = getCharacterRightBounds(bidiRun.getStart(), lineStart, fArr);
                } else {
                    characterRightBounds = getCharacterRightBounds(bidiRun.getEnd() - 1, lineStart, fArr);
                }
                float f2 = characterRightBounds;
                if (z) {
                    endOffsetForRectWithinRun = getStartOffsetForRectWithinRun(bidiRun, rectF, lineStart, lineTop, lineBottom, f, f2, fArr, segmentFinder, function2);
                } else {
                    endOffsetForRectWithinRun = getEndOffsetForRectWithinRun(bidiRun, rectF, lineStart, lineTop, lineBottom, f, f2, fArr, segmentFinder, function2);
                }
                if (endOffsetForRectWithinRun >= 0) {
                    return endOffsetForRectWithinRun;
                }
                if (i2 != last) {
                    i2 += step;
                }
            }
        }
        return -1;
    }

    private static final int getStartOffsetForRectWithinRun(LayoutHelper.BidiRun bidiRun, RectF rectF, int i, int i2, int i3, float f, float f2, float[] fArr, SegmentFinder segmentFinder, Function2<? super RectF, ? super RectF, Boolean> function2) {
        int start;
        int iPreviousStartBoundary;
        float characterLeftBounds;
        float characterRightBounds;
        if (!horizontalOverlap(rectF, f, f2)) {
            return -1;
        }
        if ((!bidiRun.isRtl() && rectF.left <= f) || (bidiRun.isRtl() && rectF.right >= f2)) {
            start = bidiRun.getStart();
        } else {
            start = bidiRun.getStart();
            int end = bidiRun.getEnd();
            while (end - start > 1) {
                int i4 = (end + start) / 2;
                float characterLeftBounds2 = getCharacterLeftBounds(i4, i, fArr);
                if ((bidiRun.isRtl() || characterLeftBounds2 <= rectF.left) && (!bidiRun.isRtl() || characterLeftBounds2 >= rectF.right)) {
                    start = i4;
                } else {
                    end = i4;
                }
            }
            if (bidiRun.isRtl()) {
                start = end;
            }
        }
        int iNextEndBoundary = segmentFinder.nextEndBoundary(start);
        if (iNextEndBoundary == -1 || (iPreviousStartBoundary = segmentFinder.previousStartBoundary(iNextEndBoundary)) >= bidiRun.getEnd()) {
            return -1;
        }
        int iCoerceAtLeast = RangesKt.coerceAtLeast(iPreviousStartBoundary, bidiRun.getStart());
        int iCoerceAtMost = RangesKt.coerceAtMost(iNextEndBoundary, bidiRun.getEnd());
        RectF rectF2 = new RectF(0.0f, i2, 0.0f, i3);
        while (true) {
            if (bidiRun.isRtl()) {
                characterLeftBounds = getCharacterLeftBounds(iCoerceAtMost - 1, i, fArr);
            } else {
                characterLeftBounds = getCharacterLeftBounds(iCoerceAtLeast, i, fArr);
            }
            rectF2.left = characterLeftBounds;
            if (bidiRun.isRtl()) {
                characterRightBounds = getCharacterRightBounds(iCoerceAtLeast, i, fArr);
            } else {
                characterRightBounds = getCharacterRightBounds(iCoerceAtMost - 1, i, fArr);
            }
            rectF2.right = characterRightBounds;
            if (function2.invoke(rectF2, rectF).booleanValue()) {
                return iCoerceAtLeast;
            }
            iCoerceAtLeast = segmentFinder.nextStartBoundary(iCoerceAtLeast);
            if (iCoerceAtLeast == -1 || iCoerceAtLeast >= bidiRun.getEnd()) {
                return -1;
            }
            iCoerceAtMost = RangesKt.coerceAtMost(segmentFinder.nextEndBoundary(iCoerceAtLeast), bidiRun.getEnd());
        }
    }

    private static final int getEndOffsetForRectWithinRun(LayoutHelper.BidiRun bidiRun, RectF rectF, int i, int i2, int i3, float f, float f2, float[] fArr, SegmentFinder segmentFinder, Function2<? super RectF, ? super RectF, Boolean> function2) {
        int start;
        int iNextEndBoundary;
        float characterLeftBounds;
        float characterRightBounds;
        if (!horizontalOverlap(rectF, f, f2)) {
            return -1;
        }
        if ((!bidiRun.isRtl() && rectF.right >= f2) || (bidiRun.isRtl() && rectF.left <= f)) {
            start = bidiRun.getEnd() - 1;
        } else {
            start = bidiRun.getStart();
            int end = bidiRun.getEnd();
            while (end - start > 1) {
                int i4 = (end + start) / 2;
                float characterLeftBounds2 = getCharacterLeftBounds(i4, i, fArr);
                if ((bidiRun.isRtl() || characterLeftBounds2 <= rectF.right) && (!bidiRun.isRtl() || characterLeftBounds2 >= rectF.left)) {
                    start = i4;
                } else {
                    end = i4;
                }
            }
            if (bidiRun.isRtl()) {
                start = end;
            }
        }
        int iPreviousStartBoundary = segmentFinder.previousStartBoundary(start + 1);
        if (iPreviousStartBoundary == -1 || (iNextEndBoundary = segmentFinder.nextEndBoundary(iPreviousStartBoundary)) <= bidiRun.getStart()) {
            return -1;
        }
        int iCoerceAtLeast = RangesKt.coerceAtLeast(iPreviousStartBoundary, bidiRun.getStart());
        int iCoerceAtMost = RangesKt.coerceAtMost(iNextEndBoundary, bidiRun.getEnd());
        RectF rectF2 = new RectF(0.0f, i2, 0.0f, i3);
        while (true) {
            if (bidiRun.isRtl()) {
                characterLeftBounds = getCharacterLeftBounds(iCoerceAtMost - 1, i, fArr);
            } else {
                characterLeftBounds = getCharacterLeftBounds(iCoerceAtLeast, i, fArr);
            }
            rectF2.left = characterLeftBounds;
            if (bidiRun.isRtl()) {
                characterRightBounds = getCharacterRightBounds(iCoerceAtLeast, i, fArr);
            } else {
                characterRightBounds = getCharacterRightBounds(iCoerceAtMost - 1, i, fArr);
            }
            rectF2.right = characterRightBounds;
            if (function2.invoke(rectF2, rectF).booleanValue()) {
                return iCoerceAtMost;
            }
            iCoerceAtMost = segmentFinder.previousEndBoundary(iCoerceAtMost);
            if (iCoerceAtMost == -1 || iCoerceAtMost <= bidiRun.getStart()) {
                return -1;
            }
            iCoerceAtLeast = RangesKt.coerceAtLeast(segmentFinder.previousStartBoundary(iCoerceAtMost), bidiRun.getStart());
        }
    }

    private static final float getCharacterLeftBounds(int i, int i2, float[] fArr) {
        return fArr[(i - i2) * 2];
    }

    private static final float getCharacterRightBounds(int i, int i2, float[] fArr) {
        return fArr[((i - i2) * 2) + 1];
    }

    private static final boolean horizontalOverlap(RectF rectF, float f, float f2) {
        return f2 >= rectF.left && f <= rectF.right;
    }
}
