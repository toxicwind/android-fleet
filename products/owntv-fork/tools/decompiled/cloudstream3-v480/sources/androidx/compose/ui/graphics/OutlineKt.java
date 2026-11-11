package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Outline.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000~\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001aK\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012\u001aK\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0003\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0011\u0010\u0017\u001a\u00020\u0018*\u00020\u0019H\u0002¢\u0006\u0002\u0010\u001a\u001a\u0011\u0010\u001b\u001a\u00020\u001c*\u00020\u0019H\u0002¢\u0006\u0002\u0010\u001a\u001a\u0011\u0010\u0017\u001a\u00020\u0018*\u00020\u001dH\u0002¢\u0006\u0002\u0010\u001e\u001a\u0011\u0010\u001b\u001a\u00020\u001c*\u00020\u001dH\u0002¢\u0006\u0002\u0010\u001e\u001a\u009f\u0001\u0010\u001f\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042,\u0010 \u001a(\u0012\u0004\u0012\u00020\u0006\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00010!¢\u0006\u0002\b%2,\u0010&\u001a(\u0012\u0004\u0012\u00020\u0006\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\u00010!¢\u0006\u0002\b%2,\u0010(\u001a(\u0012\u0004\u0012\u00020\u0006\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\u00010!¢\u0006\u0002\b%H\u0082\b\u001a\u001a\u0010\u0005\u001a\u00020\u0001*\u00020*2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010+\u001a\u00020,\u001a\f\u0010-\u001a\u00020.*\u00020\u001dH\u0002¨\u0006/"}, d2 = {"addOutline", "", "Landroidx/compose/ui/graphics/Path;", "outline", "Landroidx/compose/ui/graphics/Outline;", "drawOutline", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "color", "Landroidx/compose/ui/graphics/Color;", "alpha", "", "style", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "drawOutline-wDX37Ww", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/graphics/Outline;JFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "brush", "Landroidx/compose/ui/graphics/Brush;", "drawOutline-hn5TExg", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/graphics/Outline;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "topLeft", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/ui/geometry/Rect;", "(Landroidx/compose/ui/geometry/Rect;)J", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/geometry/Size;", "Landroidx/compose/ui/geometry/RoundRect;", "(Landroidx/compose/ui/geometry/RoundRect;)J", "drawOutlineHelper", "drawRectBlock", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "rect", "Lkotlin/ExtensionFunctionType;", "drawRoundedRectBlock", "rrect", "drawPathBlock", "path", "Landroidx/compose/ui/graphics/Canvas;", "paint", "Landroidx/compose/ui/graphics/Paint;", "hasSameCornerRadius", "", "ui-graphics_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class OutlineKt {
    public static final void addOutline(Path path, Outline outline) {
        if (outline instanceof Outline.Rectangle) {
            Path.CC.addRect$default(path, ((Outline.Rectangle) outline).getRect(), null, 2, null);
        } else if (outline instanceof Outline.Rounded) {
            Path.CC.addRoundRect$default(path, ((Outline.Rounded) outline).getRoundRect(), null, 2, null);
        } else {
            if (!(outline instanceof Outline.Generic)) {
                throw new NoWhenBranchMatchedException();
            }
            Path.CC.m4443addPathUv8p0NA$default(path, ((Outline.Generic) outline).getPath(), 0L, 2, null);
        }
    }

    /* JADX INFO: renamed from: drawOutline-wDX37Ww$default, reason: not valid java name */
    public static /* synthetic */ void m4431drawOutlinewDX37Ww$default(DrawScope drawScope, Outline outline, long j, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            f = 1.0f;
        }
        float f2 = f;
        if ((i2 & 8) != 0) {
            drawStyle = Fill.INSTANCE;
        }
        DrawStyle drawStyle2 = drawStyle;
        if ((i2 & 16) != 0) {
            colorFilter = null;
        }
        m4430drawOutlinewDX37Ww(drawScope, outline, j, f2, drawStyle2, colorFilter, (i2 & 32) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i);
    }

    /* JADX INFO: renamed from: drawOutline-hn5TExg$default, reason: not valid java name */
    public static /* synthetic */ void m4429drawOutlinehn5TExg$default(DrawScope drawScope, Outline outline, Brush brush, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            f = 1.0f;
        }
        float f2 = f;
        if ((i2 & 8) != 0) {
            drawStyle = Fill.INSTANCE;
        }
        DrawStyle drawStyle2 = drawStyle;
        if ((i2 & 16) != 0) {
            colorFilter = null;
        }
        ColorFilter colorFilter2 = colorFilter;
        if ((i2 & 32) != 0) {
            i = DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU();
        }
        m4428drawOutlinehn5TExg(drawScope, outline, brush, f2, drawStyle2, colorFilter2, i);
    }

    private static final long topLeft(Rect rect) {
        float left = rect.getLeft();
        float top = rect.getTop();
        return Offset.m3862constructorimpl((((long) Float.floatToRawIntBits(left)) << 32) | (((long) Float.floatToRawIntBits(top)) & 4294967295L));
    }

    private static final long topLeft(RoundRect roundRect) {
        float left = roundRect.getLeft();
        float top = roundRect.getTop();
        return Offset.m3862constructorimpl((((long) Float.floatToRawIntBits(left)) << 32) | (((long) Float.floatToRawIntBits(top)) & 4294967295L));
    }

    private static final long size(RoundRect roundRect) {
        float width = roundRect.getWidth();
        float height = roundRect.getHeight();
        return Size.m3930constructorimpl((((long) Float.floatToRawIntBits(width)) << 32) | (((long) Float.floatToRawIntBits(height)) & 4294967295L));
    }

    private static final void drawOutlineHelper(DrawScope drawScope, Outline outline, Function2<? super DrawScope, ? super Rect, Unit> function2, Function2<? super DrawScope, ? super RoundRect, Unit> function3, Function2<? super DrawScope, ? super Path, Unit> function4) {
        if (outline instanceof Outline.Rectangle) {
            function2.invoke(drawScope, ((Outline.Rectangle) outline).getRect());
            return;
        }
        if (outline instanceof Outline.Rounded) {
            Outline.Rounded rounded = (Outline.Rounded) outline;
            Path roundRectPath = rounded.getRoundRectPath();
            if (roundRectPath != null) {
                function4.invoke(drawScope, roundRectPath);
                return;
            } else {
                function3.invoke(drawScope, rounded.getRoundRect());
                return;
            }
        }
        if (!(outline instanceof Outline.Generic)) {
            throw new NoWhenBranchMatchedException();
        }
        function4.invoke(drawScope, ((Outline.Generic) outline).getPath());
    }

    public static final void drawOutline(Canvas canvas, Outline outline, Paint paint) {
        if (outline instanceof Outline.Rectangle) {
            canvas.drawRect(((Outline.Rectangle) outline).getRect(), paint);
            return;
        }
        if (outline instanceof Outline.Rounded) {
            Outline.Rounded rounded = (Outline.Rounded) outline;
            Path roundRectPath = rounded.getRoundRectPath();
            if (roundRectPath != null) {
                canvas.drawPath(roundRectPath, paint);
                return;
            } else {
                canvas.drawRoundRect(rounded.getRoundRect().getLeft(), rounded.getRoundRect().getTop(), rounded.getRoundRect().getRight(), rounded.getRoundRect().getBottom(), Float.intBitsToFloat((int) (rounded.getRoundRect().m3918getBottomLeftCornerRadiuskKHJgLs() >> 32)), Float.intBitsToFloat((int) (rounded.getRoundRect().m3918getBottomLeftCornerRadiuskKHJgLs() & 4294967295L)), paint);
                return;
            }
        }
        if (!(outline instanceof Outline.Generic)) {
            throw new NoWhenBranchMatchedException();
        }
        canvas.drawPath(((Outline.Generic) outline).getPath(), paint);
    }

    private static final boolean hasSameCornerRadius(RoundRect roundRect) {
        return ((Float.intBitsToFloat((int) (roundRect.m3918getBottomLeftCornerRadiuskKHJgLs() >> 32)) > Float.intBitsToFloat((int) (roundRect.m3919getBottomRightCornerRadiuskKHJgLs() >> 32)) ? 1 : (Float.intBitsToFloat((int) (roundRect.m3918getBottomLeftCornerRadiuskKHJgLs() >> 32)) == Float.intBitsToFloat((int) (roundRect.m3919getBottomRightCornerRadiuskKHJgLs() >> 32)) ? 0 : -1)) == 0 && (Float.intBitsToFloat((int) (roundRect.m3919getBottomRightCornerRadiuskKHJgLs() >> 32)) > Float.intBitsToFloat((int) (roundRect.m3921getTopRightCornerRadiuskKHJgLs() >> 32)) ? 1 : (Float.intBitsToFloat((int) (roundRect.m3919getBottomRightCornerRadiuskKHJgLs() >> 32)) == Float.intBitsToFloat((int) (roundRect.m3921getTopRightCornerRadiuskKHJgLs() >> 32)) ? 0 : -1)) == 0 && (Float.intBitsToFloat((int) (roundRect.m3921getTopRightCornerRadiuskKHJgLs() >> 32)) > Float.intBitsToFloat((int) (roundRect.m3920getTopLeftCornerRadiuskKHJgLs() >> 32)) ? 1 : (Float.intBitsToFloat((int) (roundRect.m3921getTopRightCornerRadiuskKHJgLs() >> 32)) == Float.intBitsToFloat((int) (roundRect.m3920getTopLeftCornerRadiuskKHJgLs() >> 32)) ? 0 : -1)) == 0) && ((Float.intBitsToFloat((int) (roundRect.m3918getBottomLeftCornerRadiuskKHJgLs() & 4294967295L)) > Float.intBitsToFloat((int) (roundRect.m3919getBottomRightCornerRadiuskKHJgLs() & 4294967295L)) ? 1 : (Float.intBitsToFloat((int) (roundRect.m3918getBottomLeftCornerRadiuskKHJgLs() & 4294967295L)) == Float.intBitsToFloat((int) (roundRect.m3919getBottomRightCornerRadiuskKHJgLs() & 4294967295L)) ? 0 : -1)) == 0 && (Float.intBitsToFloat((int) (roundRect.m3919getBottomRightCornerRadiuskKHJgLs() & 4294967295L)) > Float.intBitsToFloat((int) (roundRect.m3921getTopRightCornerRadiuskKHJgLs() & 4294967295L)) ? 1 : (Float.intBitsToFloat((int) (roundRect.m3919getBottomRightCornerRadiuskKHJgLs() & 4294967295L)) == Float.intBitsToFloat((int) (roundRect.m3921getTopRightCornerRadiuskKHJgLs() & 4294967295L)) ? 0 : -1)) == 0 && (Float.intBitsToFloat((int) (roundRect.m3921getTopRightCornerRadiuskKHJgLs() & 4294967295L)) > Float.intBitsToFloat((int) (roundRect.m3920getTopLeftCornerRadiuskKHJgLs() & 4294967295L)) ? 1 : (Float.intBitsToFloat((int) (roundRect.m3921getTopRightCornerRadiuskKHJgLs() & 4294967295L)) == Float.intBitsToFloat((int) (roundRect.m3920getTopLeftCornerRadiuskKHJgLs() & 4294967295L)) ? 0 : -1)) == 0);
    }

    /* JADX INFO: renamed from: drawOutline-wDX37Ww, reason: not valid java name */
    public static final void m4430drawOutlinewDX37Ww(DrawScope drawScope, Outline outline, long j, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i) {
        if (outline instanceof Outline.Rectangle) {
            Rect rect = ((Outline.Rectangle) outline).getRect();
            drawScope.mo4643drawRectnJ9OG0(j, topLeft(rect), size(rect), f, drawStyle, colorFilter, i);
            return;
        }
        if (outline instanceof Outline.Rounded) {
            Outline.Rounded rounded = (Outline.Rounded) outline;
            Path roundRectPath = rounded.getRoundRectPath();
            if (roundRectPath != null) {
                drawScope.mo4639drawPathLG529CI(roundRectPath, j, f, drawStyle, colorFilter, i);
                return;
            }
            RoundRect roundRect = rounded.getRoundRect();
            float fIntBitsToFloat = Float.intBitsToFloat((int) (roundRect.m3918getBottomLeftCornerRadiuskKHJgLs() >> 32));
            drawScope.mo4645drawRoundRectuAw5IA(j, topLeft(roundRect), size(roundRect), CornerRadius.m3824constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) & 4294967295L) | (Float.floatToRawIntBits(fIntBitsToFloat) << 32)), drawStyle, f, colorFilter, i);
            return;
        }
        if (outline instanceof Outline.Generic) {
            drawScope.mo4639drawPathLG529CI(((Outline.Generic) outline).getPath(), j, f, drawStyle, colorFilter, i);
            return;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: renamed from: drawOutline-hn5TExg, reason: not valid java name */
    public static final void m4428drawOutlinehn5TExg(DrawScope drawScope, Outline outline, Brush brush, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i) {
        if (outline instanceof Outline.Rectangle) {
            Rect rect = ((Outline.Rectangle) outline).getRect();
            drawScope.mo4642drawRectAsUm42w(brush, topLeft(rect), size(rect), f, drawStyle, colorFilter, i);
            return;
        }
        if (outline instanceof Outline.Rounded) {
            Outline.Rounded rounded = (Outline.Rounded) outline;
            Path roundRectPath = rounded.getRoundRectPath();
            if (roundRectPath != null) {
                drawScope.mo4638drawPathGBMwjPU(roundRectPath, brush, f, drawStyle, colorFilter, i);
                return;
            }
            RoundRect roundRect = rounded.getRoundRect();
            float fIntBitsToFloat = Float.intBitsToFloat((int) (roundRect.m3918getBottomLeftCornerRadiuskKHJgLs() >> 32));
            drawScope.mo4644drawRoundRectZuiqVtQ(brush, topLeft(roundRect), size(roundRect), CornerRadius.m3824constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) & 4294967295L) | (Float.floatToRawIntBits(fIntBitsToFloat) << 32)), f, drawStyle, colorFilter, i);
            return;
        }
        if (outline instanceof Outline.Generic) {
            drawScope.mo4638drawPathGBMwjPU(((Outline.Generic) outline).getPath(), brush, f, drawStyle, colorFilter, i);
            return;
        }
        throw new NoWhenBranchMatchedException();
    }

    private static final long size(Rect rect) {
        float right = rect.getRight() - rect.getLeft();
        return Size.m3930constructorimpl((((long) Float.floatToRawIntBits(rect.getBottom() - rect.getTop())) & 4294967295L) | (Float.floatToRawIntBits(right) << 32));
    }
}
