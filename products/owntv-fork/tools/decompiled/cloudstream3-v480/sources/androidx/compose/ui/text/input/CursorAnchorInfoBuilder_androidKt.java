package androidx.compose.ui.text.input;

import android.graphics.Matrix;
import android.os.Build;
import android.view.inputmethod.CursorAnchorInfo;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: CursorAnchorInfoBuilder.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\u001ad\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000fH\u0001\u001a,\u0010\u0013\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a4\u0010\u0016\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a\u001c\u0010\u0019\u001a\u00020\u000f*\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0002¨\u0006\u001d"}, d2 = {JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Landroid/view/inputmethod/CursorAnchorInfo;", "Landroid/view/inputmethod/CursorAnchorInfo$Builder;", "textFieldValue", "Landroidx/compose/ui/text/input/TextFieldValue;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "matrix", "Landroid/graphics/Matrix;", "innerTextFieldBounds", "Landroidx/compose/ui/geometry/Rect;", "decorationBoxBounds", "includeInsertionMarker", "", "includeCharacterBounds", "includeEditorBounds", "includeLineBounds", "setInsertionMarker", "selectionStart", "", "addCharacterBounds", "startOffset", "endOffset", "containsInclusive", "x", "", "y", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class CursorAnchorInfoBuilder_androidKt {
    public static /* synthetic */ CursorAnchorInfo build$default(CursorAnchorInfo.Builder builder, TextFieldValue textFieldValue, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, Matrix matrix, Rect rect, Rect rect2, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        if ((i & 64) != 0) {
            z = true;
        }
        if ((i & 128) != 0) {
            z2 = true;
        }
        if ((i & 256) != 0) {
            z3 = true;
        }
        if ((i & 512) != 0) {
            z4 = true;
        }
        return build(builder, textFieldValue, offsetMapping, textLayoutResult, matrix, rect, rect2, z, z2, z3, z4);
    }

    @Deprecated(message = "Only exists to support the legacy TextInputService APIs. It is not used by any Compose code. A copy of this function in foundation is used by the legacy BasicTextField.")
    public static final CursorAnchorInfo build(CursorAnchorInfo.Builder builder, TextFieldValue textFieldValue, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, Matrix matrix, Rect rect, Rect rect2, boolean z, boolean z2, boolean z3, boolean z4) {
        builder.reset();
        builder.setMatrix(matrix);
        int iM6342getMinimpl = TextRange.m6342getMinimpl(textFieldValue.getSelection());
        builder.setSelectionRange(iM6342getMinimpl, TextRange.m6341getMaximpl(textFieldValue.getSelection()));
        if (z) {
            setInsertionMarker(builder, iM6342getMinimpl, offsetMapping, textLayoutResult, rect);
        }
        if (z2) {
            TextRange composition = textFieldValue.getComposition();
            int iM6342getMinimpl2 = composition != null ? TextRange.m6342getMinimpl(composition.getPackedValue()) : -1;
            TextRange composition2 = textFieldValue.getComposition();
            int iM6341getMaximpl = composition2 != null ? TextRange.m6341getMaximpl(composition2.getPackedValue()) : -1;
            if (iM6342getMinimpl2 >= 0 && iM6342getMinimpl2 < iM6341getMaximpl) {
                builder.setComposingText(iM6342getMinimpl2, textFieldValue.getText().subSequence(iM6342getMinimpl2, iM6341getMaximpl));
                addCharacterBounds(builder, iM6342getMinimpl2, iM6341getMaximpl, offsetMapping, textLayoutResult, rect);
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

    private static final CursorAnchorInfo.Builder setInsertionMarker(CursorAnchorInfo.Builder builder, int i, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, Rect rect) {
        if (i < 0) {
            return builder;
        }
        int iOriginalToTransformed = offsetMapping.originalToTransformed(i);
        Rect cursorRect = textLayoutResult.getCursorRect(iOriginalToTransformed);
        float fCoerceIn = RangesKt.coerceIn(cursorRect.getLeft(), 0.0f, (int) (textLayoutResult.getSize() >> 32));
        boolean zContainsInclusive = containsInclusive(rect, fCoerceIn, cursorRect.getTop());
        boolean zContainsInclusive2 = containsInclusive(rect, fCoerceIn, cursorRect.getBottom());
        int i2 = 1;
        boolean z = textLayoutResult.getBidiRunDirection(iOriginalToTransformed) == ResolvedTextDirection.Rtl;
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
    /* JADX WARN: Type inference failed for: r16v0 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    private static final CursorAnchorInfo.Builder addCharacterBounds(CursorAnchorInfo.Builder builder, int i, int i2, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, Rect rect) {
        ?? r5;
        int iOriginalToTransformed = offsetMapping.originalToTransformed(i);
        int iOriginalToTransformed2 = offsetMapping.originalToTransformed(i2);
        float[] fArr = new float[(iOriginalToTransformed2 - iOriginalToTransformed) * 4];
        textLayoutResult.getMultiParagraph().m6204fillBoundingBoxes8ffj60Q(TextRangeKt.TextRange(iOriginalToTransformed, iOriginalToTransformed2), fArr, 0);
        for (int i3 = i; i3 < i2; i3++) {
            int iOriginalToTransformed3 = offsetMapping.originalToTransformed(i3);
            int i4 = (iOriginalToTransformed3 - iOriginalToTransformed) * 4;
            Rect rect2 = new Rect(fArr[i4], fArr[i4 + 1], fArr[i4 + 2], fArr[i4 + 3]);
            boolean zOverlaps = rect.overlaps(rect2);
            if (!containsInclusive(rect, rect2.getLeft(), rect2.getTop()) || !containsInclusive(rect, rect2.getRight(), rect2.getBottom())) {
                r5 = zOverlaps;
                r5 = (zOverlaps ? 1 : 0) | 2;
            }
            r5 = zOverlaps;
            if (textLayoutResult.getBidiRunDirection(iOriginalToTransformed3) == ResolvedTextDirection.Rtl) {
                r5 = (r5 == true ? 1 : 0) | 4;
            }
            builder.addCharacterBounds(i3, rect2.getLeft(), rect2.getTop(), rect2.getRight(), rect2.getBottom(), r5 == true ? 1 : 0);
        }
        return builder;
    }

    private static final boolean containsInclusive(Rect rect, float f, float f2) {
        float left = rect.getLeft();
        if (f > rect.getRight() || left > f) {
            return false;
        }
        return f2 <= rect.getBottom() && rect.getTop() <= f2;
    }
}
