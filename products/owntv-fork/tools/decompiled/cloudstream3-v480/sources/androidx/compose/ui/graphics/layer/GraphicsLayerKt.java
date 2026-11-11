package androidx.compose.ui.graphics.layer;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: compiled from: GraphicsLayer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0007\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\b\u001a\u00020\t\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"drawLayer", "", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "graphicsLayer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "DefaultCameraDistance", "", "setOutline", "outline", "Landroidx/compose/ui/graphics/Outline;", "ui-graphics_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class GraphicsLayerKt {
    public static final float DefaultCameraDistance = 8.0f;

    public static final void setOutline(GraphicsLayer graphicsLayer, Outline outline) {
        if (outline instanceof Outline.Rectangle) {
            Outline.Rectangle rectangle = (Outline.Rectangle) outline;
            long jM3862constructorimpl = Offset.m3862constructorimpl((((long) Float.floatToRawIntBits(rectangle.getRect().getLeft())) << 32) | (((long) Float.floatToRawIntBits(rectangle.getRect().getTop())) & 4294967295L));
            Rect rect = rectangle.getRect();
            float right = rect.getRight() - rect.getLeft();
            Rect rect2 = rectangle.getRect();
            graphicsLayer.m4834setRectOutlinetz77jQw(jM3862constructorimpl, Size.m3930constructorimpl((4294967295L & ((long) Float.floatToRawIntBits(rect2.getBottom() - rect2.getTop()))) | (Float.floatToRawIntBits(right) << 32)));
            return;
        }
        if (outline instanceof Outline.Generic) {
            graphicsLayer.setPathOutline(((Outline.Generic) outline).getPath());
            return;
        }
        if (!(outline instanceof Outline.Rounded)) {
            throw new NoWhenBranchMatchedException();
        }
        Outline.Rounded rounded = (Outline.Rounded) outline;
        if (rounded.getRoundRectPath() != null) {
            graphicsLayer.setPathOutline(rounded.getRoundRectPath());
            return;
        }
        RoundRect roundRect = rounded.getRoundRect();
        long jM3862constructorimpl2 = Offset.m3862constructorimpl((((long) Float.floatToRawIntBits(roundRect.getLeft())) << 32) | (((long) Float.floatToRawIntBits(roundRect.getTop())) & 4294967295L));
        float width = roundRect.getWidth();
        graphicsLayer.m4835setRoundRectOutlineTNW_H78(jM3862constructorimpl2, Size.m3930constructorimpl((4294967295L & ((long) Float.floatToRawIntBits(roundRect.getHeight()))) | (Float.floatToRawIntBits(width) << 32)), Float.intBitsToFloat((int) (roundRect.m3918getBottomLeftCornerRadiuskKHJgLs() >> 32)));
    }

    public static final void drawLayer(DrawScope drawScope, GraphicsLayer graphicsLayer) {
        graphicsLayer.draw$ui_graphics_release(drawScope.getDrawContext().getCanvas(), drawScope.getDrawContext().getGraphicsLayer());
    }
}
