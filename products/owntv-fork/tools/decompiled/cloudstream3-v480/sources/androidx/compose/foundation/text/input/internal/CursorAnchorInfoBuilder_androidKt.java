package androidx.compose.foundation.text.input.internal;

import android.graphics.Matrix;
import android.os.Build;
import android.view.inputmethod.CursorAnchorInfo;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.IntSize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: CursorAnchorInfoBuilder.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001ax\u0010\t\u001a\u00020\n*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u0014H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a$\u0010\u001a\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001c"}, d2 = {"addCharacterBounds", "Landroid/view/inputmethod/CursorAnchorInfo$Builder;", "startOffset", "", "endOffset", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "innerTextFieldBounds", "Landroidx/compose/ui/geometry/Rect;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Landroid/view/inputmethod/CursorAnchorInfo;", "text", "", "selection", "Landroidx/compose/ui/text/TextRange;", "composition", "matrix", "Landroid/graphics/Matrix;", "decorationBoxBounds", "includeInsertionMarker", "", "includeCharacterBounds", "includeEditorBounds", "includeLineBounds", "build-vxqZcH0", "(Landroid/view/inputmethod/CursorAnchorInfo$Builder;Ljava/lang/CharSequence;JLandroidx/compose/ui/text/TextRange;Landroidx/compose/ui/text/TextLayoutResult;Landroid/graphics/Matrix;Landroidx/compose/ui/geometry/Rect;Landroidx/compose/ui/geometry/Rect;ZZZZ)Landroid/view/inputmethod/CursorAnchorInfo;", "setInsertionMarker", "selectionStart", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CursorAnchorInfoBuilder_androidKt {
    /* JADX INFO: renamed from: build-vxqZcH0$default, reason: not valid java name */
    public static /* synthetic */ CursorAnchorInfo m1182buildvxqZcH0$default(CursorAnchorInfo.Builder builder, CharSequence charSequence, long j, TextRange textRange, TextLayoutResult textLayoutResult, Matrix matrix, Rect rect, Rect rect2, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        if ((i & 128) != 0) {
            z = true;
        }
        if ((i & 256) != 0) {
            z2 = true;
        }
        if ((i & 512) != 0) {
            z3 = true;
        }
        if ((i & 1024) != 0) {
            z4 = true;
        }
        return m1181buildvxqZcH0(builder, charSequence, j, textRange, textLayoutResult, matrix, rect, rect2, z, z2, z3, z4);
    }

    /* JADX INFO: renamed from: build-vxqZcH0, reason: not valid java name */
    public static final CursorAnchorInfo m1181buildvxqZcH0(CursorAnchorInfo.Builder builder, CharSequence charSequence, long j, TextRange textRange, TextLayoutResult textLayoutResult, Matrix matrix, Rect rect, Rect rect2, boolean z, boolean z2, boolean z3, boolean z4) {
        builder.reset();
        builder.setMatrix(matrix);
        int iM6342getMinimpl = TextRange.m6342getMinimpl(j);
        builder.setSelectionRange(iM6342getMinimpl, TextRange.m6341getMaximpl(j));
        if (z) {
            setInsertionMarker(builder, iM6342getMinimpl, textLayoutResult, rect);
        }
        if (z2) {
            int iM6342getMinimpl2 = textRange != null ? TextRange.m6342getMinimpl(textRange.getPackedValue()) : -1;
            int iM6341getMaximpl = textRange != null ? TextRange.m6341getMaximpl(textRange.getPackedValue()) : -1;
            if (iM6342getMinimpl2 >= 0 && iM6342getMinimpl2 < iM6341getMaximpl) {
                builder.setComposingText(iM6342getMinimpl2, charSequence.subSequence(iM6342getMinimpl2, iM6341getMaximpl));
                addCharacterBounds(builder, iM6342getMinimpl2, iM6341getMaximpl, textLayoutResult, rect);
            }
        }
        if (Build.VERSION.SDK_INT >= 33 && z3) {
            CursorAnchorInfoApi33Helper.setEditorBoundsInfo(builder, rect2);
        }
        if (Build.VERSION.SDK_INT >= 34 && z4) {
            CursorAnchorInfoApi34Helper.addVisibleLineBounds(builder, textLayoutResult, rect);
        }
        return builder.build();
    }

    private static final CursorAnchorInfo.Builder setInsertionMarker(CursorAnchorInfo.Builder builder, int i, TextLayoutResult textLayoutResult, Rect rect) {
        if (i < 0) {
            return builder;
        }
        Rect cursorRect = textLayoutResult.getCursorRect(i);
        float fCoerceIn = RangesKt.coerceIn(cursorRect.getLeft(), 0.0f, IntSize.m7065getWidthimpl(textLayoutResult.getSize()));
        boolean zContainsInclusive = LegacyCursorAnchorInfoBuilder_androidKt.containsInclusive(rect, fCoerceIn, cursorRect.getTop());
        boolean zContainsInclusive2 = LegacyCursorAnchorInfoBuilder_androidKt.containsInclusive(rect, fCoerceIn, cursorRect.getBottom());
        int i2 = 1;
        boolean z = textLayoutResult.getBidiRunDirection(i) == ResolvedTextDirection.Rtl;
        if (!zContainsInclusive && !zContainsInclusive2) {
            i2 = 0;
        }
        if (!zContainsInclusive || !zContainsInclusive2) {
            i2 |= 2;
        }
        if (z) {
            i2 |= 4;
        }
        builder.setInsertionMarkerLocation(fCoerceIn, cursorRect.getTop(), cursorRect.getBottom(), cursorRect.getBottom(), i2);
        return builder;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    private static final CursorAnchorInfo.Builder addCharacterBounds(CursorAnchorInfo.Builder builder, int i, int i2, TextLayoutResult textLayoutResult, Rect rect) {
        ?? r3;
        float[] fArr = new float[(i2 - i) * 4];
        textLayoutResult.getMultiParagraph().m6204fillBoundingBoxes8ffj60Q(TextRangeKt.TextRange(i, i2), fArr, 0);
        for (int i3 = i; i3 < i2; i3++) {
            int i4 = (i3 - i) * 4;
            Rect rect2 = new Rect(fArr[i4], fArr[i4 + 1], fArr[i4 + 2], fArr[i4 + 3]);
            boolean zOverlaps = rect.overlaps(rect2);
            if (!LegacyCursorAnchorInfoBuilder_androidKt.containsInclusive(rect, rect2.getLeft(), rect2.getTop()) || !LegacyCursorAnchorInfoBuilder_androidKt.containsInclusive(rect, rect2.getRight(), rect2.getBottom())) {
                r3 = zOverlaps;
                r3 = (zOverlaps ? 1 : 0) | 2;
            }
            r3 = zOverlaps;
            if (textLayoutResult.getBidiRunDirection(i3) == ResolvedTextDirection.Rtl) {
                r3 = (r3 == true ? 1 : 0) | 4;
            }
            builder.addCharacterBounds(i3, rect2.getLeft(), rect2.getTop(), rect2.getRight(), rect2.getBottom(), r3 == true ? 1 : 0);
        }
        return builder;
    }
}
