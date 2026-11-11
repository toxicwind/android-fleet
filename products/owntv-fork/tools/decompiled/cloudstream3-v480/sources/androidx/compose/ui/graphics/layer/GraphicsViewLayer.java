package androidx.compose.ui.graphics.layer;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.AndroidBlendMode_androidKt;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidColorFilter_androidKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.RenderEffect;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.layer.view.DrawChildContainer;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: GraphicsViewLayer.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Ö\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u009b\u00012\u00020\u0001:\u0002\u009b\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010?\u001a\u00020@2\u0006\u0010<\u001a\u00020;H\u0002¢\u0006\u0004\bA\u00103J\b\u0010B\u001a\u00020@H\u0002J\b\u0010C\u001a\u00020\u0018H\u0002J\b\u0010D\u001a\u00020$H\u0002J\b\u0010E\u001a\u00020$H\u0002J'\u0010}\u001a\u00020@2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!H\u0016¢\u0006\u0004\b~\u0010\u007fJ'\u0010\u0080\u0001\u001a\u00020@2\n\u0010\u0081\u0001\u001a\u0005\u0018\u00010\u0082\u00012\u0007\u0010\u0083\u0001\u001a\u00020!H\u0016¢\u0006\u0006\b\u0084\u0001\u0010\u0085\u0001JD\u0010\u0086\u0001\u001a\u00020@2\b\u0010\u0087\u0001\u001a\u00030\u0088\u00012\b\u0010\u0089\u0001\u001a\u00030\u008a\u00012\b\u0010\u008b\u0001\u001a\u00030\u008c\u00012\u001b\u0010\u008d\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u008f\u0001\u0012\u0004\u0012\u00020@0\u008e\u0001¢\u0006\u0003\b\u0090\u0001H\u0016J\t\u0010\u0093\u0001\u001a\u00020@H\u0002J\u0013\u0010\u0094\u0001\u001a\u00020@2\b\u0010\u0095\u0001\u001a\u00030\u0096\u0001H\u0016J\n\u0010\u0097\u0001\u001a\u00030\u0098\u0001H\u0016J\t\u0010\u0099\u0001\u001a\u00020@H\u0002J\t\u0010\u009a\u0001\u001a\u00020@H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n \u0014*\u0004\u0018\u00010\u00130\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u00020$X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u000e\u0010)\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010+\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\rR&\u0010/\u001a\u00020.2\u0006\u0010-\u001a\u00020.@VX\u0096\u000e¢\u0006\u0010\n\u0002\u00104\u001a\u0004\b0\u00101\"\u0004\b2\u00103R(\u00106\u001a\u0004\u0018\u0001052\b\u0010-\u001a\u0004\u0018\u000105@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R&\u0010<\u001a\u00020;2\u0006\u0010-\u001a\u00020;@VX\u0096\u000e¢\u0006\u0010\n\u0002\u00104\u001a\u0004\b=\u00101\"\u0004\b>\u00103R$\u0010G\u001a\u00020F2\u0006\u0010-\u001a\u00020F@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u000e\u0010L\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010N\u001a\u00020M2\u0006\u0010-\u001a\u00020M@VX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\bO\u0010\r\"\u0004\bP\u0010QR$\u0010R\u001a\u00020F2\u0006\u0010-\u001a\u00020F@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010I\"\u0004\bT\u0010KR$\u0010U\u001a\u00020F2\u0006\u0010-\u001a\u00020F@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010I\"\u0004\bW\u0010KR$\u0010X\u001a\u00020F2\u0006\u0010-\u001a\u00020F@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010I\"\u0004\bZ\u0010KR$\u0010[\u001a\u00020F2\u0006\u0010-\u001a\u00020F@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010I\"\u0004\b]\u0010KR$\u0010^\u001a\u00020F2\u0006\u0010-\u001a\u00020F@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010I\"\u0004\b`\u0010KR&\u0010b\u001a\u00020a2\u0006\u0010-\u001a\u00020a@VX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\bc\u0010\r\"\u0004\bd\u0010QR&\u0010e\u001a\u00020a2\u0006\u0010-\u001a\u00020a@VX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\bf\u0010\r\"\u0004\bg\u0010QR$\u0010h\u001a\u00020F2\u0006\u0010-\u001a\u00020F@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010I\"\u0004\bj\u0010KR$\u0010k\u001a\u00020F2\u0006\u0010-\u001a\u00020F@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010I\"\u0004\bm\u0010KR$\u0010n\u001a\u00020F2\u0006\u0010-\u001a\u00020F@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bo\u0010I\"\u0004\bp\u0010KR$\u0010q\u001a\u00020F2\u0006\u0010-\u001a\u00020F8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\br\u0010I\"\u0004\bs\u0010KR$\u0010t\u001a\u00020$2\u0006\u0010-\u001a\u00020$8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bu\u0010&\"\u0004\bv\u0010(R(\u0010x\u001a\u0004\u0018\u00010w2\b\u0010-\u001a\u0004\u0018\u00010w@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\by\u0010z\"\u0004\b{\u0010|R\u0016\u0010\u0091\u0001\u001a\u00020$X\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0092\u0001\u0010&¨\u0006\u009c\u0001"}, d2 = {"Landroidx/compose/ui/graphics/layer/GraphicsViewLayer;", "Landroidx/compose/ui/graphics/layer/GraphicsLayerImpl;", "layerContainer", "Landroidx/compose/ui/graphics/layer/view/DrawChildContainer;", "ownerId", "", "canvasHolder", "Landroidx/compose/ui/graphics/CanvasHolder;", "canvasDrawScope", "Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;", "<init>", "(Landroidx/compose/ui/graphics/layer/view/DrawChildContainer;JLandroidx/compose/ui/graphics/CanvasHolder;Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;)V", "getOwnerId", "()J", "getCanvasHolder", "()Landroidx/compose/ui/graphics/CanvasHolder;", "viewLayer", "Landroidx/compose/ui/graphics/layer/ViewLayer;", "resources", "Landroid/content/res/Resources;", "kotlin.jvm.PlatformType", "clipRect", "Landroid/graphics/Rect;", "layerPaint", "Landroid/graphics/Paint;", "picture", "Landroid/graphics/Picture;", "pictureDrawScope", "pictureCanvasHolder", "x", "", "y", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/unit/IntSize;", "J", "clipBoundsInvalidated", "", "isInvalidated", "()Z", "setInvalidated", "(Z)V", "outlineIsProvided", "clipToBounds", "layerId", "getLayerId", ES6Iterator.VALUE_PROPERTY, "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "getBlendMode-0nO6VwU", "()I", "setBlendMode-s9anfk8", "(I)V", "I", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "Landroidx/compose/ui/graphics/layer/CompositingStrategy;", "compositingStrategy", "getCompositingStrategy-ke2Ky5w", "setCompositingStrategy-Wpw9cng", "applyCompositingLayer", "", "applyCompositingLayer-Wpw9cng", "updateLayerProperties", "obtainLayerPaint", "requiresCompositingLayer", "requiresLayerPaint", "", "alpha", "getAlpha", "()F", "setAlpha", "(F)V", "shouldManuallySetCenterPivot", "Landroidx/compose/ui/geometry/Offset;", "pivotOffset", "getPivotOffset-F1C5BW0", "setPivotOffset-k-4lQ0M", "(J)V", "scaleX", "getScaleX", "setScaleX", "scaleY", "getScaleY", "setScaleY", "translationX", "getTranslationX", "setTranslationX", "translationY", "getTranslationY", "setTranslationY", "shadowElevation", "getShadowElevation", "setShadowElevation", "Landroidx/compose/ui/graphics/Color;", "ambientShadowColor", "getAmbientShadowColor-0d7_KjU", "setAmbientShadowColor-8_81llA", "spotShadowColor", "getSpotShadowColor-0d7_KjU", "setSpotShadowColor-8_81llA", "rotationX", "getRotationX", "setRotationX", "rotationY", "getRotationY", "setRotationY", "rotationZ", "getRotationZ", "setRotationZ", "cameraDistance", "getCameraDistance", "setCameraDistance", "clip", "getClip", "setClip", "Landroidx/compose/ui/graphics/RenderEffect;", "renderEffect", "getRenderEffect", "()Landroidx/compose/ui/graphics/RenderEffect;", "setRenderEffect", "(Landroidx/compose/ui/graphics/RenderEffect;)V", "setPosition", "setPosition-H0pRuoY", "(IIJ)V", "setOutline", "outline", "Landroid/graphics/Outline;", "outlineSize", "setOutline-O0kMr_c", "(Landroid/graphics/Outline;J)V", "record", "density", "Landroidx/compose/ui/unit/Density;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "layer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lkotlin/ExtensionFunctionType;", "supportsSoftwareRendering", "getSupportsSoftwareRendering", "recordDrawingOperations", "draw", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "calculateMatrix", "Landroid/graphics/Matrix;", "updateClipBounds", "discardDisplayList", "Companion", "ui-graphics_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class GraphicsViewLayer implements GraphicsLayerImpl {
    private float alpha;
    private long ambientShadowColor;
    private int blendMode;
    private final CanvasHolder canvasHolder;
    private boolean clipBoundsInvalidated;
    private final Rect clipRect;
    private boolean clipToBounds;
    private ColorFilter colorFilter;
    private int compositingStrategy;
    private boolean isInvalidated;
    private final DrawChildContainer layerContainer;
    private final long layerId;
    private Paint layerPaint;
    private boolean outlineIsProvided;
    private final long ownerId;
    private final Picture picture;
    private final CanvasHolder pictureCanvasHolder;
    private final CanvasDrawScope pictureDrawScope;
    private long pivotOffset;
    private RenderEffect renderEffect;
    private final Resources resources;
    private float rotationX;
    private float rotationY;
    private float rotationZ;
    private float scaleX;
    private float scaleY;
    private float shadowElevation;
    private boolean shouldManuallySetCenterPivot;
    private long size;
    private long spotShadowColor;
    private final boolean supportsSoftwareRendering;
    private float translationX;
    private float translationY;
    private final ViewLayer viewLayer;
    private int x;
    private int y;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final boolean mayRenderInSoftware = !SurfaceUtils.INSTANCE.isLockHardwareCanvasAvailable();
    private static final Canvas PlaceholderCanvas = new Canvas() { // from class: androidx.compose.ui.graphics.layer.GraphicsViewLayer$Companion$PlaceholderCanvas$1
        @Override // android.graphics.Canvas
        public boolean isHardwareAccelerated() {
            return true;
        }
    };

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public /* synthetic */ boolean getHasDisplayList() {
        return GraphicsLayerImpl.CC.$default$getHasDisplayList(this);
    }

    public GraphicsViewLayer(DrawChildContainer drawChildContainer, long j, CanvasHolder canvasHolder, CanvasDrawScope canvasDrawScope) {
        this.layerContainer = drawChildContainer;
        this.ownerId = j;
        this.canvasHolder = canvasHolder;
        ViewLayer viewLayer = new ViewLayer(drawChildContainer, canvasHolder, canvasDrawScope);
        this.viewLayer = viewLayer;
        this.resources = drawChildContainer.getResources();
        this.clipRect = new Rect();
        boolean z = mayRenderInSoftware;
        this.picture = z ? new Picture() : null;
        this.pictureDrawScope = z ? new CanvasDrawScope() : null;
        this.pictureCanvasHolder = z ? new CanvasHolder() : null;
        drawChildContainer.addView(viewLayer);
        viewLayer.setClipBounds(null);
        this.size = IntSize.INSTANCE.m7070getZeroYbymL2g();
        this.isInvalidated = true;
        this.layerId = View.generateViewId();
        this.blendMode = BlendMode.INSTANCE.m4093getSrcOver0nO6VwU();
        this.compositingStrategy = CompositingStrategy.INSTANCE.m4815getAutoke2Ky5w();
        this.alpha = 1.0f;
        this.pivotOffset = Offset.INSTANCE.m3886getZeroF1C5BW0();
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.ambientShadowColor = Color.INSTANCE.m4178getBlack0d7_KjU();
        this.spotShadowColor = Color.INSTANCE.m4178getBlack0d7_KjU();
        this.supportsSoftwareRendering = z;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public long getOwnerId() {
        return this.ownerId;
    }

    public /* synthetic */ GraphicsViewLayer(DrawChildContainer drawChildContainer, long j, CanvasHolder canvasHolder, CanvasDrawScope canvasDrawScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(drawChildContainer, j, (i & 4) != 0 ? new CanvasHolder() : canvasHolder, (i & 8) != 0 ? new CanvasDrawScope() : canvasDrawScope);
    }

    public final CanvasHolder getCanvasHolder() {
        return this.canvasHolder;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: isInvalidated, reason: from getter */
    public boolean getIsInvalidated() {
        return this.isInvalidated;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setInvalidated(boolean z) {
        this.isInvalidated = z;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public long getLayerId() {
        return this.layerId;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: getBlendMode-0nO6VwU, reason: from getter */
    public int getBlendMode() {
        return this.blendMode;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: setBlendMode-s9anfk8 */
    public void mo4844setBlendModes9anfk8(int i) {
        this.blendMode = i;
        obtainLayerPaint().setXfermode(new PorterDuffXfermode(AndroidBlendMode_androidKt.m3963toPorterDuffModes9anfk8(i)));
        updateLayerProperties();
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setColorFilter(ColorFilter colorFilter) {
        this.colorFilter = colorFilter;
        obtainLayerPaint().setColorFilter(colorFilter != null ? AndroidColorFilter_androidKt.asAndroidColorFilter(colorFilter) : null);
        updateLayerProperties();
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: getCompositingStrategy-ke2Ky5w, reason: from getter */
    public int getCompositingStrategy() {
        return this.compositingStrategy;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: setCompositingStrategy-Wpw9cng */
    public void mo4845setCompositingStrategyWpw9cng(int i) {
        this.compositingStrategy = i;
        updateLayerProperties();
    }

    /* JADX INFO: renamed from: applyCompositingLayer-Wpw9cng, reason: not valid java name */
    private final void m4852applyCompositingLayerWpw9cng(int compositingStrategy) {
        ViewLayer viewLayer = this.viewLayer;
        boolean z = true;
        if (CompositingStrategy.m4811equalsimpl0(compositingStrategy, CompositingStrategy.INSTANCE.m4817getOffscreenke2Ky5w())) {
            this.viewLayer.setLayerType(2, this.layerPaint);
        } else {
            boolean zM4811equalsimpl0 = CompositingStrategy.m4811equalsimpl0(compositingStrategy, CompositingStrategy.INSTANCE.m4816getModulateAlphake2Ky5w());
            ViewLayer viewLayer2 = this.viewLayer;
            if (zM4811equalsimpl0) {
                viewLayer2.setLayerType(0, this.layerPaint);
                z = false;
            } else {
                viewLayer2.setLayerType(0, this.layerPaint);
            }
        }
        viewLayer.setCanUseCompositingLayer$ui_graphics_release(z);
    }

    private final void updateLayerProperties() {
        if (requiresCompositingLayer()) {
            m4852applyCompositingLayerWpw9cng(CompositingStrategy.INSTANCE.m4817getOffscreenke2Ky5w());
        } else {
            m4852applyCompositingLayerWpw9cng(getCompositingStrategy());
        }
    }

    private final Paint obtainLayerPaint() {
        Paint paint = this.layerPaint;
        if (paint != null) {
            return paint;
        }
        Paint paint2 = new Paint();
        this.layerPaint = paint2;
        return paint2;
    }

    private final boolean requiresCompositingLayer() {
        return CompositingStrategy.m4811equalsimpl0(getCompositingStrategy(), CompositingStrategy.INSTANCE.m4817getOffscreenke2Ky5w()) || requiresLayerPaint();
    }

    private final boolean requiresLayerPaint() {
        return (BlendMode.m4062equalsimpl0(getBlendMode(), BlendMode.INSTANCE.m4093getSrcOver0nO6VwU()) && getColorFilter() == null) ? false : true;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getAlpha() {
        return this.alpha;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setAlpha(float f) {
        this.alpha = f;
        this.viewLayer.setAlpha(f);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: getPivotOffset-F1C5BW0, reason: from getter */
    public long getPivotOffset() {
        return this.pivotOffset;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: setPivotOffset-k-4lQ0M */
    public void mo4847setPivotOffsetk4lQ0M(long j) {
        this.pivotOffset = j;
        if ((9223372034707292159L & j) == InlineClassHelperKt.UnspecifiedPackedFloats) {
            if (Build.VERSION.SDK_INT >= 28) {
                ViewLayerVerificationHelper28.INSTANCE.resetPivot(this.viewLayer);
                return;
            }
            this.shouldManuallySetCenterPivot = true;
            this.viewLayer.setPivotX(((int) (this.size >> 32)) / 2.0f);
            this.viewLayer.setPivotY(((int) (4294967295L & this.size)) / 2.0f);
            return;
        }
        this.shouldManuallySetCenterPivot = false;
        this.viewLayer.setPivotX(Float.intBitsToFloat((int) (j >> 32)));
        this.viewLayer.setPivotY(Float.intBitsToFloat((int) (j & 4294967295L)));
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getScaleX() {
        return this.scaleX;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setScaleX(float f) {
        this.scaleX = f;
        this.viewLayer.setScaleX(f);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getScaleY() {
        return this.scaleY;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setScaleY(float f) {
        this.scaleY = f;
        this.viewLayer.setScaleY(f);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getTranslationX() {
        return this.translationX;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setTranslationX(float f) {
        this.translationX = f;
        this.viewLayer.setTranslationX(f);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getTranslationY() {
        return this.translationY;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setTranslationY(float f) {
        this.translationY = f;
        this.viewLayer.setTranslationY(f);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getShadowElevation() {
        return this.shadowElevation;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setShadowElevation(float f) {
        this.shadowElevation = f;
        this.viewLayer.setElevation(f);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: getAmbientShadowColor-0d7_KjU, reason: from getter */
    public long getAmbientShadowColor() {
        return this.ambientShadowColor;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: setAmbientShadowColor-8_81llA */
    public void mo4843setAmbientShadowColor8_81llA(long j) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.ambientShadowColor = j;
            ViewLayerVerificationHelper28.INSTANCE.setOutlineAmbientShadowColor(this.viewLayer, ColorKt.m4206toArgb8_81llA(j));
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: getSpotShadowColor-0d7_KjU, reason: from getter */
    public long getSpotShadowColor() {
        return this.spotShadowColor;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: setSpotShadowColor-8_81llA */
    public void mo4849setSpotShadowColor8_81llA(long j) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.spotShadowColor = j;
            ViewLayerVerificationHelper28.INSTANCE.setOutlineSpotShadowColor(this.viewLayer, ColorKt.m4206toArgb8_81llA(j));
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getRotationX() {
        return this.rotationX;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setRotationX(float f) {
        this.rotationX = f;
        this.viewLayer.setRotationX(f);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getRotationY() {
        return this.rotationY;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setRotationY(float f) {
        this.rotationY = f;
        this.viewLayer.setRotationY(f);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getRotationZ() {
        return this.rotationZ;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setRotationZ(float f) {
        this.rotationZ = f;
        this.viewLayer.setRotation(f);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getCameraDistance() {
        return this.viewLayer.getCameraDistance() / this.resources.getDisplayMetrics().densityDpi;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setCameraDistance(float f) {
        this.viewLayer.setCameraDistance(f * this.resources.getDisplayMetrics().densityDpi);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public boolean getClip() {
        return this.clipToBounds || this.viewLayer.getClipToOutline();
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setClip(boolean z) {
        boolean z2 = false;
        this.clipToBounds = z && !this.outlineIsProvided;
        this.clipBoundsInvalidated = true;
        ViewLayer viewLayer = this.viewLayer;
        if (z && this.outlineIsProvided) {
            z2 = true;
        }
        viewLayer.setClipToOutline(z2);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public RenderEffect getRenderEffect() {
        return this.renderEffect;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setRenderEffect(RenderEffect renderEffect) {
        this.renderEffect = renderEffect;
        if (Build.VERSION.SDK_INT >= 31) {
            ViewLayerVerificationHelper31.INSTANCE.setRenderEffect(this.viewLayer, renderEffect);
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: setPosition-H0pRuoY */
    public void mo4848setPositionH0pRuoY(int x, int y, long size) {
        if (!IntSize.m7063equalsimpl0(this.size, size)) {
            if (getClip()) {
                this.clipBoundsInvalidated = true;
            }
            int i = (int) (size >> 32);
            int i2 = (int) (4294967295L & size);
            this.viewLayer.layout(x, y, x + i, y + i2);
            this.size = size;
            if (this.shouldManuallySetCenterPivot) {
                this.viewLayer.setPivotX(i / 2.0f);
                this.viewLayer.setPivotY(i2 / 2.0f);
            }
        } else {
            int i3 = this.x;
            if (i3 != x) {
                this.viewLayer.offsetLeftAndRight(x - i3);
            }
            int i4 = this.y;
            if (i4 != y) {
                this.viewLayer.offsetTopAndBottom(y - i4);
            }
        }
        this.x = x;
        this.y = y;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: setOutline-O0kMr_c */
    public void mo4846setOutlineO0kMr_c(Outline outline, long outlineSize) {
        boolean layerOutline = this.viewLayer.setLayerOutline(outline);
        if (getClip() && outline != null) {
            this.viewLayer.setClipToOutline(true);
            if (this.clipToBounds) {
                this.clipToBounds = false;
                this.clipBoundsInvalidated = true;
            }
        }
        this.outlineIsProvided = outline != null;
        if (layerOutline) {
            return;
        }
        this.viewLayer.invalidate();
        recordDrawingOperations();
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void record(Density density, LayoutDirection layoutDirection, GraphicsLayer layer, Function1<? super DrawScope, Unit> block) throws Throwable {
        Picture picture;
        if (this.viewLayer.getParent() == null) {
            this.layerContainer.addView(this.viewLayer);
        }
        this.viewLayer.setDrawParams(density, layoutDirection, layer, block);
        if (!this.viewLayer.isAttachedToWindow()) {
            return;
        }
        this.viewLayer.setVisibility(4);
        this.viewLayer.setVisibility(0);
        recordDrawingOperations();
        Picture picture2 = this.picture;
        if (picture2 == null) {
            return;
        }
        long j = this.size;
        Canvas canvasBeginRecording = picture2.beginRecording((int) (j >> 32), (int) (j & 4294967295L));
        try {
            CanvasHolder canvasHolder = this.pictureCanvasHolder;
            if (canvasHolder != null) {
                Canvas internalCanvas = canvasHolder.getAndroidCanvas().getInternalCanvas();
                canvasHolder.getAndroidCanvas().setInternalCanvas(canvasBeginRecording);
                AndroidCanvas androidCanvas = canvasHolder.getAndroidCanvas();
                CanvasDrawScope canvasDrawScope = this.pictureDrawScope;
                if (canvasDrawScope != null) {
                    CanvasDrawScope canvasDrawScope2 = canvasDrawScope;
                    long jM7077toSizeozmzZPI = IntSizeKt.m7077toSizeozmzZPI(this.size);
                    Density density2 = canvasDrawScope2.getDrawContext().getDensity();
                    LayoutDirection layoutDirection2 = canvasDrawScope2.getDrawContext().getLayoutDirection();
                    androidx.compose.ui.graphics.Canvas canvas = canvasDrawScope2.getDrawContext().getCanvas();
                    long jMo4654getSizeNHjbRc = canvasDrawScope2.getDrawContext().mo4654getSizeNHjbRc();
                    picture = picture2;
                    try {
                        GraphicsLayer graphicsLayer = canvasDrawScope2.getDrawContext().getGraphicsLayer();
                        DrawContext drawContext = canvasDrawScope2.getDrawContext();
                        drawContext.setDensity(density);
                        drawContext.setLayoutDirection(layoutDirection);
                        drawContext.setCanvas(androidCanvas);
                        drawContext.mo4655setSizeuvyYCjk(jM7077toSizeozmzZPI);
                        drawContext.setGraphicsLayer(layer);
                        androidCanvas.save();
                        try {
                            block.invoke(canvasDrawScope2);
                            androidCanvas.restore();
                            DrawContext drawContext2 = canvasDrawScope2.getDrawContext();
                            drawContext2.setDensity(density2);
                            drawContext2.setLayoutDirection(layoutDirection2);
                            drawContext2.setCanvas(canvas);
                            drawContext2.mo4655setSizeuvyYCjk(jMo4654getSizeNHjbRc);
                            drawContext2.setGraphicsLayer(graphicsLayer);
                        } catch (Throwable th) {
                            androidCanvas.restore();
                            DrawContext drawContext3 = canvasDrawScope2.getDrawContext();
                            drawContext3.setDensity(density2);
                            drawContext3.setLayoutDirection(layoutDirection2);
                            drawContext3.setCanvas(canvas);
                            drawContext3.mo4655setSizeuvyYCjk(jMo4654getSizeNHjbRc);
                            drawContext3.setGraphicsLayer(graphicsLayer);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        picture.endRecording();
                        throw th;
                    }
                } else {
                    picture = picture2;
                }
                canvasHolder.getAndroidCanvas().setInternalCanvas(internalCanvas);
                Unit unit = Unit.INSTANCE;
            } else {
                picture = picture2;
            }
            picture.endRecording();
        } catch (Throwable th3) {
            th = th3;
            picture = picture2;
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public boolean getSupportsSoftwareRendering() {
        return this.supportsSoftwareRendering;
    }

    private final void recordDrawingOperations() {
        try {
            CanvasHolder canvasHolder = this.canvasHolder;
            Canvas canvas = PlaceholderCanvas;
            Canvas internalCanvas = canvasHolder.getAndroidCanvas().getInternalCanvas();
            canvasHolder.getAndroidCanvas().setInternalCanvas(canvas);
            AndroidCanvas androidCanvas = canvasHolder.getAndroidCanvas();
            DrawChildContainer drawChildContainer = this.layerContainer;
            ViewLayer viewLayer = this.viewLayer;
            drawChildContainer.drawChild$ui_graphics_release(androidCanvas, viewLayer, viewLayer.getDrawingTime());
            canvasHolder.getAndroidCanvas().setInternalCanvas(internalCanvas);
        } catch (ClassCastException unused) {
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void draw(androidx.compose.ui.graphics.Canvas canvas) {
        updateClipBounds();
        Canvas nativeCanvas = AndroidCanvas_androidKt.getNativeCanvas(canvas);
        if (nativeCanvas.isHardwareAccelerated()) {
            DrawChildContainer drawChildContainer = this.layerContainer;
            ViewLayer viewLayer = this.viewLayer;
            drawChildContainer.drawChild$ui_graphics_release(canvas, viewLayer, viewLayer.getDrawingTime());
        } else {
            Picture picture = this.picture;
            if (picture != null) {
                nativeCanvas.drawPicture(picture);
            }
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public Matrix calculateMatrix() {
        return this.viewLayer.getMatrix();
    }

    private final void updateClipBounds() {
        Rect rect;
        if (this.clipBoundsInvalidated) {
            ViewLayer viewLayer = this.viewLayer;
            if (!getClip() || this.outlineIsProvided) {
                rect = null;
            } else {
                rect = this.clipRect;
                rect.left = 0;
                rect.top = 0;
                rect.right = this.viewLayer.getWidth();
                rect.bottom = this.viewLayer.getHeight();
            }
            viewLayer.setClipBounds(rect);
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void discardDisplayList() {
        this.layerContainer.removeViewInLayout(this.viewLayer);
    }

    /* JADX INFO: compiled from: GraphicsViewLayer.android.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/ui/graphics/layer/GraphicsViewLayer$Companion;", "", "<init>", "()V", "mayRenderInSoftware", "", "getMayRenderInSoftware", "()Z", "PlaceholderCanvas", "Landroid/graphics/Canvas;", "getPlaceholderCanvas", "()Landroid/graphics/Canvas;", "ui-graphics_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean getMayRenderInSoftware() {
            return GraphicsViewLayer.mayRenderInSoftware;
        }

        public final Canvas getPlaceholderCanvas() {
            return GraphicsViewLayer.PlaceholderCanvas;
        }
    }
}
