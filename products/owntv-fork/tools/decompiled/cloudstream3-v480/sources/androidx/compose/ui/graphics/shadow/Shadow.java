package androidx.compose.ui.graphics.shadow;

import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpOffset;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Shadow.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001BE\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0001\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010BA\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0003\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0011BC\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0003\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0012J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020'H\u0016J\r\u0010(\u001a\u00020\u0000H\u0000¢\u0006\u0002\b)R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0016\u0010\u0014R\u0013\u0010\u0005\u001a\u00020\u0006¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\r\u001a\u00020\u000e¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0007\u001a\u00020\b¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001d\u0010\u0018R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0014¨\u0006*"}, d2 = {"Landroidx/compose/ui/graphics/shadow/Shadow;", "", "radius", "Landroidx/compose/ui/unit/Dp;", "spread", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/ui/unit/DpOffset;", "color", "Landroidx/compose/ui/graphics/Color;", "brush", "Landroidx/compose/ui/graphics/Brush;", "alpha", "", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "<init>", "(FFJJLandroidx/compose/ui/graphics/Brush;FI)V", "(FLandroidx/compose/ui/graphics/Brush;FJFILkotlin/jvm/internal/DefaultConstructorMarker;)V", "(FJFJFILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getRadius-D9Ej5fM", "()F", "F", "getSpread-D9Ej5fM", "getOffset-RKDOV3M", "()J", "J", "getBlendMode-0nO6VwU", "()I", "I", "getColor-0d7_KjU", "getBrush", "()Landroidx/compose/ui/graphics/Brush;", "getAlpha", "equals", "", "other", "hashCode", "", "toString", "", "copyWithoutOffset", "copyWithoutOffset$ui_graphics_release", "ui-graphics_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class Shadow {
    private final float alpha;
    private final int blendMode;
    private final Brush brush;
    private final long color;
    private final long offset;
    private final float radius;
    private final float spread;

    public /* synthetic */ Shadow(float f, long j, float f2, long j2, float f3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, j, f2, j2, f3, i);
    }

    public /* synthetic */ Shadow(float f, Brush brush, float f2, long j, float f3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, brush, f2, j, f3, i);
    }

    private Shadow(float f, float f2, long j, long j2, Brush brush, float f3, int i) {
        this.radius = f;
        this.spread = f2;
        this.offset = j;
        this.blendMode = i;
        if (brush instanceof SolidColor) {
            this.color = ((SolidColor) brush).getValue();
            this.brush = null;
        } else {
            this.color = j2;
            this.brush = brush;
        }
        this.alpha = RangesKt.coerceIn(f3, 0.0f, 1.0f);
    }

    /* JADX INFO: renamed from: getRadius-D9Ej5fM, reason: not valid java name and from getter */
    public final float getRadius() {
        return this.radius;
    }

    /* JADX INFO: renamed from: getSpread-D9Ej5fM, reason: not valid java name and from getter */
    public final float getSpread() {
        return this.spread;
    }

    /* JADX INFO: renamed from: getOffset-RKDOV3M, reason: not valid java name and from getter */
    public final long getOffset() {
        return this.offset;
    }

    /* JADX INFO: renamed from: getBlendMode-0nO6VwU, reason: not valid java name and from getter */
    public final int getBlendMode() {
        return this.blendMode;
    }

    /* JADX INFO: renamed from: getColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getColor() {
        return this.color;
    }

    public final Brush getBrush() {
        return this.brush;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    private Shadow(float f, Brush brush, float f2, long j, float f3, int i) {
        this(f, f2, j, Color.INSTANCE.m4178getBlack0d7_KjU(), brush, f3, i);
    }

    public /* synthetic */ Shadow(float f, long j, float f2, long j2, float f3, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, (i2 & 2) != 0 ? Color.INSTANCE.m4178getBlack0d7_KjU() : j, (i2 & 4) != 0 ? Dp.m6890constructorimpl(0.0f) : f2, (i2 & 8) != 0 ? DpOffset.INSTANCE.m6961getZeroRKDOV3M() : j2, (i2 & 16) != 0 ? 1.0f : f3, (i2 & 32) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i, (DefaultConstructorMarker) null);
    }

    private Shadow(float f, long j, float f2, long j2, float f3, int i) {
        this(f, f2, j2, j == 16 ? Color.INSTANCE.m4178getBlack0d7_KjU() : j, (Brush) null, f3, i);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Shadow)) {
            return false;
        }
        Shadow shadow = (Shadow) other;
        return Dp.m6895equalsimpl0(this.radius, shadow.radius) && Dp.m6895equalsimpl0(this.spread, shadow.spread) && DpOffset.m6950equalsimpl0(this.offset, shadow.offset) && this.alpha == shadow.alpha && BlendMode.m4062equalsimpl0(this.blendMode, shadow.blendMode) && Color.m4153equalsimpl0(this.color, shadow.color) && Intrinsics.areEqual(this.brush, shadow.brush);
    }

    public int hashCode() {
        int iM6896hashCodeimpl = ((((((((((Dp.m6896hashCodeimpl(this.radius) * 31) + Dp.m6896hashCodeimpl(this.spread)) * 31) + DpOffset.m6955hashCodeimpl(this.offset)) * 31) + Float.floatToIntBits(this.alpha)) * 31) + BlendMode.m4063hashCodeimpl(this.blendMode)) * 31) + Color.m4159hashCodeimpl(this.color)) * 31;
        Brush brush = this.brush;
        return iM6896hashCodeimpl + (brush != null ? brush.hashCode() : 0);
    }

    public String toString() {
        return "ShadowParams(radius=" + ((Object) Dp.m6901toStringimpl(this.radius)) + ", spread=" + ((Object) Dp.m6901toStringimpl(this.spread)) + ", offset=" + ((Object) DpOffset.m6958toStringimpl(this.offset)) + ", alpha=" + this.alpha + ", blendMode=" + ((Object) BlendMode.m4064toStringimpl(this.blendMode)) + ", color=" + ((Object) Color.m4160toStringimpl(this.color)) + ", brush=" + this.brush + ')';
    }

    public final Shadow copyWithoutOffset$ui_graphics_release() {
        return new Shadow(this.radius, this.spread, DpOffset.INSTANCE.m6961getZeroRKDOV3M(), this.color, this.brush, this.alpha, this.blendMode);
    }

    public /* synthetic */ Shadow(float f, Brush brush, float f2, long j, float f3, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, brush, (i2 & 4) != 0 ? Dp.m6890constructorimpl(0.0f) : f2, (i2 & 8) != 0 ? DpOffset.INSTANCE.m6961getZeroRKDOV3M() : j, (i2 & 16) != 0 ? 1.0f : f3, (i2 & 32) != 0 ? DrawScope.INSTANCE.m4736getDefaultBlendMode0nO6VwU() : i, (DefaultConstructorMarker) null);
    }
}
