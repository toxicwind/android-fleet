package androidx.compose.ui.graphics;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: compiled from: ColorMatrix.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0082\b¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {TtmlNode.TEXT_EMPHASIS_MARK_DOT, "", "m1", "Landroidx/compose/ui/graphics/ColorMatrix;", "row", "", "m2", "column", "dot-Me4OoYI", "([FI[FI)F", "ui-graphics_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ColorMatrixKt {
    /* JADX INFO: renamed from: dot-Me4OoYI, reason: not valid java name */
    private static final float m4232dotMe4OoYI(float[] fArr, int i, float[] fArr2, int i2) {
        int i3 = i * 5;
        return (fArr[i3] * fArr2[i2]) + (fArr[i3 + 1] * fArr2[5 + i2]) + (fArr[i3 + 2] * fArr2[10 + i2]) + (fArr[i3 + 3] * fArr2[15 + i2]);
    }
}
