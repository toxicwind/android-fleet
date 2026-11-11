package androidx.compose.ui.graphics.shadow;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.LayoutDirection;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: compiled from: ShadowRenderer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b \u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005JE\u0010\u0017\u001a\u00020\u0018*\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\r2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!¢\u0006\u0004\b\"\u0010#J-\u0010$\u001a\u00020\u0018*\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\b\u001a\u0004\u0018\u00010\tH$¢\u0006\u0004\b%\u0010&J\b\u0010'\u001a\u00020\u0018H\u0016JQ\u0010(\u001a\u00020\u0018*\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001f\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u000b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010 \u001a\u00020!H$¢\u0006\u0004\b)\u0010*J\u0017\u0010+\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\rH\u0002¢\u0006\u0004\b,\u0010-J\u0010\u0010.\u001a\u00020\u00182\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Landroidx/compose/ui/graphics/shadow/ShadowRenderer;", "", "outline", "Landroidx/compose/ui/graphics/Outline;", "<init>", "(Landroidx/compose/ui/graphics/Outline;)V", "getOutline", "()Landroidx/compose/ui/graphics/Outline;", "path", "Landroidx/compose/ui/graphics/Path;", "shadowTint", "Landroidx/compose/ui/graphics/ColorFilter;", "shadowTintColor", "Landroidx/compose/ui/graphics/Color;", "J", "cornerRadius", "Landroidx/compose/ui/geometry/CornerRadius;", "generatedSize", "Landroidx/compose/ui/geometry/Size;", "generatedLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "generatedDensity", "", "drawShadow", "", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "colorFilter", ContentDisposition.Parameters.Size, "color", "brush", "Landroidx/compose/ui/graphics/Brush;", "alpha", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "drawShadow-erFMhIw", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/graphics/ColorFilter;JJLandroidx/compose/ui/graphics/Brush;FI)V", "buildShadow", "buildShadow-_SMYjrA", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JJLandroidx/compose/ui/graphics/Path;)V", "invalidateShadow", "onDrawShadow", "onDrawShadow-MLmccfk", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JJLandroidx/compose/ui/graphics/Path;FLandroidx/compose/ui/graphics/ColorFilter;Landroidx/compose/ui/graphics/Brush;I)V", "obtainTint", "obtainTint-8_81llA", "(J)Landroidx/compose/ui/graphics/ColorFilter;", "updateParamsFromOutline", "ui-graphics_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class ShadowRenderer {
    private final Outline outline;
    private Path path;
    private ColorFilter shadowTint;
    private long shadowTintColor = Color.INSTANCE.m4188getUnspecified0d7_KjU();
    private long cornerRadius = CornerRadius.INSTANCE.m3842getZerokKHJgLs();
    private long generatedSize = Size.INSTANCE.m3947getUnspecifiedNHjbRc();
    private LayoutDirection generatedLayoutDirection = LayoutDirection.Ltr;
    private float generatedDensity = 1.0f;

    /* JADX INFO: renamed from: buildShadow-_SMYjrA */
    protected abstract void mo4873buildShadow_SMYjrA(DrawScope drawScope, long j, long j2, Path path);

    /* JADX INFO: renamed from: onDrawShadow-MLmccfk */
    protected abstract void mo4874onDrawShadowMLmccfk(DrawScope drawScope, long j, long j2, Path path, float f, ColorFilter colorFilter, Brush brush, int i);

    public ShadowRenderer(Outline outline) {
        this.outline = outline;
    }

    public final Outline getOutline() {
        return this.outline;
    }

    /* JADX INFO: renamed from: drawShadow-erFMhIw, reason: not valid java name */
    public final void m4884drawShadowerFMhIw(DrawScope drawScope, ColorFilter colorFilter, long j, long j2, Brush brush, float f, int i) {
        updateParamsFromOutline(this.outline);
        if (colorFilter == null) {
            colorFilter = (brush != null || j2 == 16) ? null : m4883obtainTint8_81llA(j2);
        }
        ColorFilter colorFilter2 = colorFilter;
        long j3 = this.generatedSize;
        if (j3 == InlineClassHelperKt.UnspecifiedPackedFloats || !Size.m3935equalsimpl0(j3, j) || this.generatedLayoutDirection != drawScope.getLayoutDirection() || this.generatedDensity != drawScope.getDensity()) {
            mo4873buildShadow_SMYjrA(drawScope, j, this.cornerRadius, this.path);
            this.generatedSize = j;
            this.generatedLayoutDirection = drawScope.getLayoutDirection();
            this.generatedDensity = drawScope.getDensity();
        }
        mo4874onDrawShadowMLmccfk(drawScope, j, this.cornerRadius, this.path, f, colorFilter2, brush, i);
    }

    public void invalidateShadow() {
        this.generatedSize = Size.INSTANCE.m3947getUnspecifiedNHjbRc();
        this.generatedLayoutDirection = LayoutDirection.Ltr;
        this.generatedDensity = 1.0f;
    }

    /* JADX INFO: renamed from: obtainTint-8_81llA, reason: not valid java name */
    private final ColorFilter m4883obtainTint8_81llA(long color) {
        ColorFilter colorFilter = this.shadowTint;
        if (colorFilter != null && Color.m4153equalsimpl0(this.shadowTintColor, color)) {
            return colorFilter;
        }
        ColorFilter colorFilterM4193tintxETnrds$default = ColorFilter.Companion.m4193tintxETnrds$default(ColorFilter.INSTANCE, color, 0, 2, null);
        this.shadowTintColor = color;
        this.shadowTint = colorFilterM4193tintxETnrds$default;
        return colorFilterM4193tintxETnrds$default;
    }

    private final void updateParamsFromOutline(Outline outline) {
        if (outline instanceof Outline.Generic) {
            this.path = ((Outline.Generic) outline).getPath();
            this.cornerRadius = CornerRadius.INSTANCE.m3842getZerokKHJgLs();
            return;
        }
        if (outline instanceof Outline.Rounded) {
            Outline.Rounded rounded = (Outline.Rounded) outline;
            if (RoundRectKt.isSimple(rounded.getRoundRect())) {
                this.path = null;
                this.cornerRadius = rounded.getRoundRect().m3920getTopLeftCornerRadiuskKHJgLs();
                return;
            } else {
                this.path = rounded.getRoundRectPath();
                this.cornerRadius = CornerRadius.INSTANCE.m3842getZerokKHJgLs();
                return;
            }
        }
        if (!(outline instanceof Outline.Rectangle)) {
            throw new NoWhenBranchMatchedException();
        }
        this.path = null;
        this.cornerRadius = CornerRadius.INSTANCE.m3842getZerokKHJgLs();
    }
}
