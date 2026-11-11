package androidx.compose.foundation;

import androidx.compose.ui.draw.CacheDrawModifierNode;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.ImageBitmapConfig;
import androidx.compose.ui.graphics.ImageBitmapKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathOperation;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.universalchardet.prober.HebrewProber;

/* JADX INFO: compiled from: Border.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ,\u0010\u001e\u001a\u00020\u001f*\u00020 2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002JF\u0010'\u001a\u00020\u001f*\u00020 2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010!\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002ø\u0001\u0000¢\u0006\u0004\b-\u0010.R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R,\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003@FX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006/"}, d2 = {"Landroidx/compose/foundation/BorderModifierNode;", "Landroidx/compose/ui/node/DelegatingNode;", "widthParameter", "Landroidx/compose/ui/unit/Dp;", "brushParameter", "Landroidx/compose/ui/graphics/Brush;", "shapeParameter", "Landroidx/compose/ui/graphics/Shape;", "(FLandroidx/compose/ui/graphics/Brush;Landroidx/compose/ui/graphics/Shape;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "borderCache", "Landroidx/compose/foundation/BorderCache;", ES6Iterator.VALUE_PROPERTY, "brush", "getBrush", "()Landroidx/compose/ui/graphics/Brush;", "setBrush", "(Landroidx/compose/ui/graphics/Brush;)V", "drawWithCacheModifierNode", "Landroidx/compose/ui/draw/CacheDrawModifierNode;", "shape", "getShape", "()Landroidx/compose/ui/graphics/Shape;", "setShape", "(Landroidx/compose/ui/graphics/Shape;)V", "width", "getWidth-D9Ej5fM", "()F", "setWidth-0680j_4", "(F)V", "F", "drawGenericBorder", "Landroidx/compose/ui/draw/DrawResult;", "Landroidx/compose/ui/draw/CacheDrawScope;", "outline", "Landroidx/compose/ui/graphics/Outline$Generic;", "fillArea", "", "strokeWidth", "", "drawRoundRectBorder", "Landroidx/compose/ui/graphics/Outline$Rounded;", "topLeft", "Landroidx/compose/ui/geometry/Offset;", "borderSize", "Landroidx/compose/ui/geometry/Size;", "drawRoundRectBorder-JqoCqck", "(Landroidx/compose/ui/draw/CacheDrawScope;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/ui/graphics/Outline$Rounded;JJZF)Landroidx/compose/ui/draw/DrawResult;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BorderModifierNode extends DelegatingNode {
    public static final int $stable = 8;
    private BorderCache borderCache;
    private Brush brush;
    private final CacheDrawModifierNode drawWithCacheModifierNode;
    private Shape shape;
    private float width;

    public /* synthetic */ BorderModifierNode(float f, Brush brush, Shape shape, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, brush, shape);
    }

    private BorderModifierNode(float f, Brush brush, Shape shape) {
        this.width = f;
        this.brush = brush;
        this.shape = shape;
        this.drawWithCacheModifierNode = (CacheDrawModifierNode) delegate(DrawModifierKt.CacheDrawModifierNode(new Function1<CacheDrawScope, DrawResult>() { // from class: androidx.compose.foundation.BorderModifierNode$drawWithCacheModifierNode$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final DrawResult invoke(CacheDrawScope cacheDrawScope) {
                if (cacheDrawScope.mo406toPx0680j_4(this.this$0.getWidth()) < 0.0f || Size.m3938getMinDimensionimpl(cacheDrawScope.m3654getSizeNHjbRc()) <= 0.0f) {
                    return BorderKt.drawContentWithoutBorder(cacheDrawScope);
                }
                float fMin = Math.min(Dp.m6895equalsimpl0(this.this$0.getWidth(), Dp.INSTANCE.m6908getHairlineD9Ej5fM()) ? 1.0f : (float) Math.ceil(cacheDrawScope.mo406toPx0680j_4(this.this$0.getWidth())), (float) Math.ceil(Size.m3938getMinDimensionimpl(cacheDrawScope.m3654getSizeNHjbRc()) / 2.0f));
                float f2 = fMin / 2.0f;
                long jOffset = OffsetKt.Offset(f2, f2);
                long jSize = SizeKt.Size(Size.m3939getWidthimpl(cacheDrawScope.m3654getSizeNHjbRc()) - fMin, Size.m3936getHeightimpl(cacheDrawScope.m3654getSizeNHjbRc()) - fMin);
                boolean z = 2.0f * fMin > Size.m3938getMinDimensionimpl(cacheDrawScope.m3654getSizeNHjbRc());
                Outline outlineMo317createOutlinePq9zytI = this.this$0.getShape().mo317createOutlinePq9zytI(cacheDrawScope.m3654getSizeNHjbRc(), cacheDrawScope.getLayoutDirection(), cacheDrawScope);
                if (outlineMo317createOutlinePq9zytI instanceof Outline.Generic) {
                    BorderModifierNode borderModifierNode = this.this$0;
                    return borderModifierNode.drawGenericBorder(cacheDrawScope, borderModifierNode.getBrush(), (Outline.Generic) outlineMo317createOutlinePq9zytI, z, fMin);
                }
                if (outlineMo317createOutlinePq9zytI instanceof Outline.Rounded) {
                    BorderModifierNode borderModifierNode2 = this.this$0;
                    return borderModifierNode2.m286drawRoundRectBorderJqoCqck(cacheDrawScope, borderModifierNode2.getBrush(), (Outline.Rounded) outlineMo317createOutlinePq9zytI, jOffset, jSize, z, fMin);
                }
                if (outlineMo317createOutlinePq9zytI instanceof Outline.Rectangle) {
                    return BorderKt.m283drawRectBorderNsqcLGU(cacheDrawScope, this.this$0.getBrush(), jOffset, jSize, z, fMin);
                }
                throw new NoWhenBranchMatchedException();
            }
        }));
    }

    /* JADX INFO: renamed from: getWidth-D9Ej5fM, reason: not valid java name and from getter */
    public final float getWidth() {
        return this.width;
    }

    /* JADX INFO: renamed from: setWidth-0680j_4, reason: not valid java name */
    public final void m288setWidth0680j_4(float f) {
        if (Dp.m6895equalsimpl0(this.width, f)) {
            return;
        }
        this.width = f;
        this.drawWithCacheModifierNode.invalidateDrawCache();
    }

    public final Brush getBrush() {
        return this.brush;
    }

    public final void setBrush(Brush brush) {
        if (Intrinsics.areEqual(this.brush, brush)) {
            return;
        }
        this.brush = brush;
        this.drawWithCacheModifierNode.invalidateDrawCache();
    }

    public final Shape getShape() {
        return this.shape;
    }

    public final void setShape(Shape shape) {
        if (Intrinsics.areEqual(this.shape, shape)) {
            return;
        }
        this.shape = shape;
        this.drawWithCacheModifierNode.invalidateDrawCache();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:26:0x00d6  */
    /* JADX WARN: Type inference failed for: r11v4, types: [T, androidx.compose.ui.graphics.ImageBitmap] */
    public final DrawResult drawGenericBorder(CacheDrawScope cacheDrawScope, final Brush brush, final Outline.Generic generic, boolean z, float f) throws Throwable {
        int iM4392getArgb8888_sVssgQ;
        ColorFilter colorFilterM4193tintxETnrds$default;
        boolean z2;
        float f2;
        float f3;
        DrawContext drawContext;
        long j;
        if (z) {
            return cacheDrawScope.onDrawWithContent(new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.foundation.BorderModifierNode.drawGenericBorder.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                    invoke2(contentDrawScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ContentDrawScope contentDrawScope) {
                    contentDrawScope.drawContent();
                    DrawScope.CC.m4727drawPathGBMwjPU$default(contentDrawScope, generic.getPath(), brush, 0.0f, null, null, 0, 60, null);
                }
            });
        }
        if (brush instanceof SolidColor) {
            iM4392getArgb8888_sVssgQ = ImageBitmapConfig.INSTANCE.m4391getAlpha8_sVssgQ();
            colorFilterM4193tintxETnrds$default = ColorFilter.Companion.m4193tintxETnrds$default(ColorFilter.INSTANCE, ((SolidColor) brush).getValue(), 0, 2, null);
        } else {
            iM4392getArgb8888_sVssgQ = ImageBitmapConfig.INSTANCE.m4392getArgb8888_sVssgQ();
            colorFilterM4193tintxETnrds$default = null;
        }
        int i = iM4392getArgb8888_sVssgQ;
        final Rect bounds = generic.getPath().getBounds();
        if (this.borderCache == null) {
            this.borderCache = new BorderCache(null, null, null, null, 15, null);
        }
        BorderCache borderCache = this.borderCache;
        Intrinsics.checkNotNull(borderCache);
        Path pathObtainPath = borderCache.obtainPath();
        pathObtainPath.reset();
        Path.CC.addRect$default(pathObtainPath, bounds, null, 2, null);
        pathObtainPath.mo4005opN5in7k0(pathObtainPath, generic.getPath(), PathOperation.INSTANCE.m4465getDifferenceb3I0S0c());
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final long jIntSize = IntSizeKt.IntSize((int) Math.ceil(bounds.getWidth()), (int) Math.ceil(bounds.getHeight()));
        BorderCache borderCache2 = this.borderCache;
        Intrinsics.checkNotNull(borderCache2);
        ImageBitmap imageBitmapM4397ImageBitmapx__hDU$default = borderCache2.imageBitmap;
        Canvas Canvas = borderCache2.canvas;
        ImageBitmapConfig imageBitmapConfigM4384boximpl = imageBitmapM4397ImageBitmapx__hDU$default != null ? ImageBitmapConfig.m4384boximpl(imageBitmapM4397ImageBitmapx__hDU$default.mo3980getConfig_sVssgQ()) : null;
        if (!(imageBitmapConfigM4384boximpl == null ? false : ImageBitmapConfig.m4387equalsimpl0(imageBitmapConfigM4384boximpl.m4390unboximpl(), ImageBitmapConfig.INSTANCE.m4392getArgb8888_sVssgQ()))) {
            z2 = ImageBitmapConfig.m4386equalsimpl(i, imageBitmapM4397ImageBitmapx__hDU$default != null ? ImageBitmapConfig.m4384boximpl(imageBitmapM4397ImageBitmapx__hDU$default.mo3980getConfig_sVssgQ()) : null);
        }
        if (imageBitmapM4397ImageBitmapx__hDU$default == null || Canvas == null || Size.m3939getWidthimpl(cacheDrawScope.m3654getSizeNHjbRc()) > imageBitmapM4397ImageBitmapx__hDU$default.getWidth() || Size.m3936getHeightimpl(cacheDrawScope.m3654getSizeNHjbRc()) > imageBitmapM4397ImageBitmapx__hDU$default.getHeight() || !z2) {
            imageBitmapM4397ImageBitmapx__hDU$default = ImageBitmapKt.m4397ImageBitmapx__hDU$default(IntSize.m7065getWidthimpl(jIntSize), IntSize.m7064getHeightimpl(jIntSize), i, false, null, 24, null);
            borderCache2.imageBitmap = imageBitmapM4397ImageBitmapx__hDU$default;
            Canvas = androidx.compose.ui.graphics.CanvasKt.Canvas(imageBitmapM4397ImageBitmapx__hDU$default);
            borderCache2.canvas = Canvas;
        }
        ?? r11 = imageBitmapM4397ImageBitmapx__hDU$default;
        Canvas canvas = Canvas;
        CanvasDrawScope canvasDrawScope = borderCache2.canvasDrawScope;
        if (canvasDrawScope == null) {
            canvasDrawScope = new CanvasDrawScope();
            borderCache2.canvasDrawScope = canvasDrawScope;
        }
        CanvasDrawScope canvasDrawScope2 = canvasDrawScope;
        long jM7077toSizeozmzZPI = IntSizeKt.m7077toSizeozmzZPI(jIntSize);
        LayoutDirection layoutDirection = cacheDrawScope.getLayoutDirection();
        CanvasDrawScope.DrawParams drawParams = canvasDrawScope2.getDrawParams();
        Density density = drawParams.getDensity();
        LayoutDirection layoutDirection2 = drawParams.getLayoutDirection();
        Canvas canvas2 = drawParams.getCanvas();
        long size = drawParams.getSize();
        CanvasDrawScope.DrawParams drawParams2 = canvasDrawScope2.getDrawParams();
        drawParams2.setDensity(cacheDrawScope);
        drawParams2.setLayoutDirection(layoutDirection);
        drawParams2.setCanvas(canvas);
        drawParams2.m4653setSizeuvyYCjk(jM7077toSizeozmzZPI);
        canvas.save();
        CanvasDrawScope canvasDrawScope3 = canvasDrawScope2;
        DrawScope.CC.m4732drawRectnJ9OG0$default(canvasDrawScope3, Color.INSTANCE.m4178getBlack0d7_KjU(), 0L, jM7077toSizeozmzZPI, 0.0f, null, null, BlendMode.INSTANCE.m4066getClear0nO6VwU(), 58, null);
        float f4 = -bounds.getLeft();
        float f5 = -bounds.getTop();
        canvasDrawScope3.getDrawContext().getTransform().translate(f4, f5);
        try {
            f2 = f4;
            f3 = f5;
            final ColorFilter colorFilter = colorFilterM4193tintxETnrds$default;
            try {
                DrawScope.CC.m4727drawPathGBMwjPU$default(canvasDrawScope3, generic.getPath(), brush, 0.0f, new Stroke(f * 2.0f, 0.0f, 0, 0, null, 30, null), null, 0, 52, null);
                float fM3939getWidthimpl = (Size.m3939getWidthimpl(canvasDrawScope3.mo4647getSizeNHjbRc()) + 1.0f) / Size.m3939getWidthimpl(canvasDrawScope3.mo4647getSizeNHjbRc());
                float fM3936getHeightimpl = (Size.m3936getHeightimpl(canvasDrawScope3.mo4647getSizeNHjbRc()) + 1.0f) / Size.m3936getHeightimpl(canvasDrawScope3.mo4647getSizeNHjbRc());
                long jMo4646getCenterF1C5BW0 = canvasDrawScope3.mo4646getCenterF1C5BW0();
                DrawContext drawContext2 = canvasDrawScope3.getDrawContext();
                long jMo4654getSizeNHjbRc = drawContext2.mo4654getSizeNHjbRc();
                drawContext2.getCanvas().save();
                try {
                    drawContext2.getTransform().mo4661scale0AR0LA0(fM3939getWidthimpl, fM3936getHeightimpl, jMo4646getCenterF1C5BW0);
                    j = jMo4654getSizeNHjbRc;
                    try {
                        DrawScope.CC.m4727drawPathGBMwjPU$default(canvasDrawScope3, pathObtainPath, brush, 0.0f, null, null, BlendMode.INSTANCE.m4066getClear0nO6VwU(), 28, null);
                        drawContext2.getCanvas().restore();
                        drawContext2.mo4655setSizeuvyYCjk(j);
                        canvasDrawScope3.getDrawContext().getTransform().translate(-f2, -f3);
                        canvas.restore();
                        CanvasDrawScope.DrawParams drawParams3 = canvasDrawScope2.getDrawParams();
                        drawParams3.setDensity(density);
                        drawParams3.setLayoutDirection(layoutDirection2);
                        drawParams3.setCanvas(canvas2);
                        drawParams3.m4653setSizeuvyYCjk(size);
                        r11.prepareToDraw();
                        objectRef.element = r11;
                        return cacheDrawScope.onDrawWithContent(new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.foundation.BorderModifierNode.drawGenericBorder.3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) throws Throwable {
                                invoke2(contentDrawScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(ContentDrawScope contentDrawScope) throws Throwable {
                                float f6;
                                contentDrawScope.drawContent();
                                ContentDrawScope contentDrawScope2 = contentDrawScope;
                                float left = bounds.getLeft();
                                float top = bounds.getTop();
                                Ref.ObjectRef<ImageBitmap> objectRef2 = objectRef;
                                long j2 = jIntSize;
                                ColorFilter colorFilter2 = colorFilter;
                                contentDrawScope2.getDrawContext().getTransform().translate(left, top);
                                try {
                                    try {
                                        DrawScope.CC.m4721drawImageAZ2fEMs$default(contentDrawScope2, objectRef2.element, 0L, j2, 0L, 0L, 0.0f, null, colorFilter2, 0, 0, 890, null);
                                        contentDrawScope2.getDrawContext().getTransform().translate(-left, -top);
                                    } catch (Throwable th) {
                                        th = th;
                                        left = left;
                                        f6 = top;
                                        contentDrawScope2.getDrawContext().getTransform().translate(-left, -f6);
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    f6 = top;
                                }
                            }
                        });
                    } catch (Throwable th) {
                        th = th;
                        drawContext = drawContext2;
                        drawContext.getCanvas().restore();
                        drawContext.mo4655setSizeuvyYCjk(j);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    drawContext = drawContext2;
                    j = jMo4654getSizeNHjbRc;
                }
            } catch (Throwable th3) {
                th = th3;
                canvasDrawScope3.getDrawContext().getTransform().translate(-f2, -f3);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            f2 = f4;
            f3 = f5;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawRoundRectBorder-JqoCqck, reason: not valid java name */
    public final DrawResult m286drawRoundRectBorderJqoCqck(CacheDrawScope cacheDrawScope, final Brush brush, Outline.Rounded rounded, final long j, final long j2, final boolean z, final float f) {
        if (RoundRectKt.isSimple(rounded.getRoundRect())) {
            final long jM3920getTopLeftCornerRadiuskKHJgLs = rounded.getRoundRect().m3920getTopLeftCornerRadiuskKHJgLs();
            final float f2 = f / 2.0f;
            final Stroke stroke = new Stroke(f, 0.0f, 0, 0, null, 30, null);
            return cacheDrawScope.onDrawWithContent(new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.foundation.BorderModifierNode$drawRoundRectBorder$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) throws Throwable {
                    invoke2(contentDrawScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ContentDrawScope contentDrawScope) throws Throwable {
                    long j3;
                    contentDrawScope.drawContent();
                    if (z) {
                        DrawScope.CC.m4733drawRoundRectZuiqVtQ$default(contentDrawScope, brush, 0L, 0L, jM3920getTopLeftCornerRadiuskKHJgLs, 0.0f, null, null, 0, HebrewProber.NORMAL_TSADI, null);
                        return;
                    }
                    float fM3830getXimpl = CornerRadius.m3830getXimpl(jM3920getTopLeftCornerRadiuskKHJgLs);
                    float f3 = f2;
                    if (fM3830getXimpl < f3) {
                        ContentDrawScope contentDrawScope2 = contentDrawScope;
                        float f4 = f;
                        float fM3939getWidthimpl = Size.m3939getWidthimpl(contentDrawScope.mo4647getSizeNHjbRc()) - f;
                        float fM3936getHeightimpl = Size.m3936getHeightimpl(contentDrawScope.mo4647getSizeNHjbRc()) - f;
                        int iM4140getDifferencertfAjoo = ClipOp.INSTANCE.m4140getDifferencertfAjoo();
                        Brush brush2 = brush;
                        long j4 = jM3920getTopLeftCornerRadiuskKHJgLs;
                        DrawContext drawContext = contentDrawScope2.getDrawContext();
                        long jMo4654getSizeNHjbRc = drawContext.mo4654getSizeNHjbRc();
                        drawContext.getCanvas().save();
                        try {
                            drawContext.getTransform().mo4657clipRectN_I0leg(f4, f4, fM3939getWidthimpl, fM3936getHeightimpl, iM4140getDifferencertfAjoo);
                            try {
                                DrawScope.CC.m4733drawRoundRectZuiqVtQ$default(contentDrawScope2, brush2, 0L, 0L, j4, 0.0f, null, null, 0, HebrewProber.NORMAL_TSADI, null);
                                drawContext.getCanvas().restore();
                                drawContext.mo4655setSizeuvyYCjk(jMo4654getSizeNHjbRc);
                            } catch (Throwable th) {
                                th = th;
                                j3 = jMo4654getSizeNHjbRc;
                                drawContext.getCanvas().restore();
                                drawContext.mo4655setSizeuvyYCjk(j3);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            j3 = jMo4654getSizeNHjbRc;
                        }
                    } else {
                        DrawScope.CC.m4733drawRoundRectZuiqVtQ$default(contentDrawScope, brush, j, j2, BorderKt.m284shrinkKibmq7A(jM3920getTopLeftCornerRadiuskKHJgLs, f3), 0.0f, stroke, null, 0, 208, null);
                    }
                }
            });
        }
        if (this.borderCache == null) {
            this.borderCache = new BorderCache(null, null, null, null, 15, null);
        }
        BorderCache borderCache = this.borderCache;
        Intrinsics.checkNotNull(borderCache);
        final Path pathCreateRoundRectPath = BorderKt.createRoundRectPath(borderCache.obtainPath(), rounded.getRoundRect(), f, z);
        return cacheDrawScope.onDrawWithContent(new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.foundation.BorderModifierNode$drawRoundRectBorder$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                invoke2(contentDrawScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContentDrawScope contentDrawScope) {
                contentDrawScope.drawContent();
                DrawScope.CC.m4727drawPathGBMwjPU$default(contentDrawScope, pathCreateRoundRectPath, brush, 0.0f, null, null, 0, 60, null);
            }
        });
    }
}
