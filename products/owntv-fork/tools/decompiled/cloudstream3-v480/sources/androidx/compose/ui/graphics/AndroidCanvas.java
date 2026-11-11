package androidx.compose.ui.graphics;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Region;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.IntOffset;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidCanvas.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\u0018\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0016J\u0018\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0019H\u0016J\u0010\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u0019H\u0016J\u0018\u0010 \u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0019H\u0016J\u0017\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020#H\u0016¢\u0006\u0004\b$\u0010%J7\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\u00192\u0006\u0010+\u001a\u00020,H\u0016¢\u0006\u0004\b-\u0010.J\u001f\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u0002012\u0006\u0010+\u001a\u00020,H\u0016¢\u0006\u0004\b2\u00103J\u0011\u00104\u001a\u000205*\u00020,¢\u0006\u0004\b6\u00107J'\u00108\u001a\u00020\u00102\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020:2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¢\u0006\u0004\b<\u0010=J0\u0010>\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J@\u0010?\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\u00192\u0006\u0010@\u001a\u00020\u00192\u0006\u0010A\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J0\u0010B\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J'\u0010C\u001a\u00020\u00102\u0006\u0010D\u001a\u00020:2\u0006\u0010E\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¢\u0006\u0004\bF\u0010GJH\u0010H\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\u00192\u0006\u0010I\u001a\u00020\u00192\u0006\u0010J\u001a\u00020\u00192\u0006\u0010K\u001a\u00020L2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010M\u001a\u00020\u00102\u0006\u00100\u001a\u0002012\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J'\u0010N\u001a\u00020\u00102\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020:2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¢\u0006\u0004\bR\u0010SJ?\u0010T\u001a\u00020\u00102\u0006\u0010O\u001a\u00020P2\u0006\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020X2\u0006\u0010Y\u001a\u00020V2\u0006\u0010Z\u001a\u00020X2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¢\u0006\u0004\b[\u0010\\J-\u0010]\u001a\u00020\u00102\u0006\u0010^\u001a\u00020_2\f\u0010`\u001a\b\u0012\u0004\u0012\u00020:0a2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¢\u0006\u0004\bb\u0010cJ\b\u0010d\u001a\u00020\u0010H\u0016J\b\u0010e\u001a\u00020\u0010H\u0016J\u001e\u0010]\u001a\u00020\u00102\f\u0010`\u001a\b\u0012\u0004\u0012\u00020:0a2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J&\u0010f\u001a\u00020\u00102\f\u0010`\u001a\b\u0012\u0004\u0012\u00020:0a2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010g\u001a\u00020hH\u0002J'\u0010i\u001a\u00020\u00102\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020j2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¢\u0006\u0004\bk\u0010lJ \u0010i\u001a\u00020\u00102\u0006\u0010`\u001a\u00020j2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010g\u001a\u00020hH\u0002J \u0010m\u001a\u00020\u00102\u0006\u0010`\u001a\u00020j2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010g\u001a\u00020hH\u0002J'\u0010n\u001a\u00020\u00102\u0006\u0010o\u001a\u00020p2\u0006\u0010q\u001a\u00020r2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¢\u0006\u0004\bs\u0010tR(\u0010\u0004\u001a\u00060\u0005j\u0002`\u00068\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0007\u0010\u0003\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006u"}, d2 = {"Landroidx/compose/ui/graphics/AndroidCanvas;", "Landroidx/compose/ui/graphics/Canvas;", "<init>", "()V", "internalCanvas", "Landroid/graphics/Canvas;", "Landroidx/compose/ui/graphics/NativeCanvas;", "getInternalCanvas$annotations", "getInternalCanvas", "()Landroid/graphics/Canvas;", "setInternalCanvas", "(Landroid/graphics/Canvas;)V", "srcRect", "Landroid/graphics/Rect;", "dstRect", "save", "", "restore", "saveLayer", "bounds", "Landroidx/compose/ui/geometry/Rect;", "paint", "Landroidx/compose/ui/graphics/Paint;", "translate", "dx", "", "dy", "scale", "sx", "sy", "rotate", "degrees", "skew", "concat", "matrix", "Landroidx/compose/ui/graphics/Matrix;", "concat-58bKbWc", "([F)V", "clipRect", TtmlNode.LEFT, "top", TtmlNode.RIGHT, "bottom", "clipOp", "Landroidx/compose/ui/graphics/ClipOp;", "clipRect-N_I0leg", "(FFFFI)V", "clipPath", "path", "Landroidx/compose/ui/graphics/Path;", "clipPath-mtrdD-E", "(Landroidx/compose/ui/graphics/Path;I)V", "toRegionOp", "Landroid/graphics/Region$Op;", "toRegionOp--7u2Bmg", "(I)Landroid/graphics/Region$Op;", "drawLine", "p1", "Landroidx/compose/ui/geometry/Offset;", "p2", "drawLine-Wko1d7g", "(JJLandroidx/compose/ui/graphics/Paint;)V", "drawRect", "drawRoundRect", "radiusX", "radiusY", "drawOval", "drawCircle", TtmlNode.CENTER, "radius", "drawCircle-9KIMszo", "(JFLandroidx/compose/ui/graphics/Paint;)V", "drawArc", "startAngle", "sweepAngle", "useCenter", "", "drawPath", "drawImage", "image", "Landroidx/compose/ui/graphics/ImageBitmap;", "topLeftOffset", "drawImage-d-4ec7I", "(Landroidx/compose/ui/graphics/ImageBitmap;JLandroidx/compose/ui/graphics/Paint;)V", "drawImageRect", "srcOffset", "Landroidx/compose/ui/unit/IntOffset;", "srcSize", "Landroidx/compose/ui/unit/IntSize;", "dstOffset", "dstSize", "drawImageRect-HPBpro0", "(Landroidx/compose/ui/graphics/ImageBitmap;JJJJLandroidx/compose/ui/graphics/Paint;)V", "drawPoints", "pointMode", "Landroidx/compose/ui/graphics/PointMode;", "points", "", "drawPoints-O7TthRY", "(ILjava/util/List;Landroidx/compose/ui/graphics/Paint;)V", "enableZ", "disableZ", "drawLines", "stepBy", "", "drawRawPoints", "", "drawRawPoints-O7TthRY", "(I[FLandroidx/compose/ui/graphics/Paint;)V", "drawRawLines", "drawVertices", "vertices", "Landroidx/compose/ui/graphics/Vertices;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "drawVertices-TPEHhCM", "(Landroidx/compose/ui/graphics/Vertices;ILandroidx/compose/ui/graphics/Paint;)V", "ui-graphics_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AndroidCanvas implements Canvas {
    private Rect dstRect;
    private android.graphics.Canvas internalCanvas = AndroidCanvas_androidKt.EmptyCanvas;
    private Rect srcRect;

    public static /* synthetic */ void getInternalCanvas$annotations() {
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: clipRect-mtrdD-E, reason: not valid java name */
    public /* synthetic */ void mo3966clipRectmtrdDE(androidx.compose.ui.geometry.Rect rect, int i) {
        Canvas.CC.m4122$default$clipRectmtrdDE(this, rect, i);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public /* synthetic */ void drawArc(androidx.compose.ui.geometry.Rect rect, float f, float f2, boolean z, Paint paint) {
        Canvas.CC.$default$drawArc(this, rect, f, f2, z, paint);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public /* synthetic */ void drawArcRad(androidx.compose.ui.geometry.Rect rect, float f, float f2, boolean z, Paint paint) {
        Canvas.CC.$default$drawArcRad(this, rect, f, f2, z, paint);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public /* synthetic */ void drawOval(androidx.compose.ui.geometry.Rect rect, Paint paint) {
        Canvas.CC.$default$drawOval(this, rect, paint);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public /* synthetic */ void drawRect(androidx.compose.ui.geometry.Rect rect, Paint paint) {
        Canvas.CC.$default$drawRect(this, rect, paint);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public /* synthetic */ void skewRad(float f, float f2) {
        Canvas.CC.$default$skewRad(this, f, f2);
    }

    public final android.graphics.Canvas getInternalCanvas() {
        return this.internalCanvas;
    }

    public final void setInternalCanvas(android.graphics.Canvas canvas) {
        this.internalCanvas = canvas;
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void save() {
        this.internalCanvas.save();
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void restore() {
        this.internalCanvas.restore();
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void saveLayer(androidx.compose.ui.geometry.Rect bounds, Paint paint) {
        this.internalCanvas.saveLayer(bounds.getLeft(), bounds.getTop(), bounds.getRight(), bounds.getBottom(), paint.getInternalPaint(), 31);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void translate(float dx, float dy) {
        this.internalCanvas.translate(dx, dy);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void scale(float sx, float sy) {
        this.internalCanvas.scale(sx, sy);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void rotate(float degrees) {
        this.internalCanvas.rotate(degrees);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void skew(float sx, float sy) {
        this.internalCanvas.skew(sx, sy);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: concat-58bKbWc, reason: not valid java name */
    public void mo3967concat58bKbWc(float[] matrix) {
        if (MatrixKt.m4427isIdentity58bKbWc(matrix)) {
            return;
        }
        android.graphics.Matrix matrix2 = new android.graphics.Matrix();
        AndroidMatrixConversions_androidKt.m3983setFromEL8BTi8(matrix2, matrix);
        this.internalCanvas.concat(matrix2);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: clipRect-N_I0leg, reason: not valid java name */
    public void mo3965clipRectN_I0leg(float left, float top, float right, float bottom, int clipOp) {
        this.internalCanvas.clipRect(left, top, right, bottom, m3975toRegionOp7u2Bmg(clipOp));
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: clipPath-mtrdD-E, reason: not valid java name */
    public void mo3964clipPathmtrdDE(Path path, int clipOp) {
        android.graphics.Canvas canvas = this.internalCanvas;
        if (path instanceof AndroidPath) {
            canvas.clipPath(((AndroidPath) path).getInternalPath(), m3975toRegionOp7u2Bmg(clipOp));
            return;
        }
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
    }

    /* JADX INFO: renamed from: toRegionOp--7u2Bmg, reason: not valid java name */
    public final Region.Op m3975toRegionOp7u2Bmg(int i) {
        return ClipOp.m4136equalsimpl0(i, ClipOp.INSTANCE.m4140getDifferencertfAjoo()) ? Region.Op.DIFFERENCE : Region.Op.INTERSECT;
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: drawLine-Wko1d7g, reason: not valid java name */
    public void mo3971drawLineWko1d7g(long p1, long p2, Paint paint) {
        this.internalCanvas.drawLine(Float.intBitsToFloat((int) (p1 >> 32)), Float.intBitsToFloat((int) (p1 & 4294967295L)), Float.intBitsToFloat((int) (p2 >> 32)), Float.intBitsToFloat((int) (p2 & 4294967295L)), paint.getInternalPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void drawRect(float left, float top, float right, float bottom, Paint paint) {
        this.internalCanvas.drawRect(left, top, right, bottom, paint.getInternalPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void drawRoundRect(float left, float top, float right, float bottom, float radiusX, float radiusY, Paint paint) {
        this.internalCanvas.drawRoundRect(left, top, right, bottom, radiusX, radiusY, paint.getInternalPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void drawOval(float left, float top, float right, float bottom, Paint paint) {
        this.internalCanvas.drawOval(left, top, right, bottom, paint.getInternalPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: drawCircle-9KIMszo, reason: not valid java name */
    public void mo3968drawCircle9KIMszo(long center, float radius, Paint paint) {
        this.internalCanvas.drawCircle(Float.intBitsToFloat((int) (center >> 32)), Float.intBitsToFloat((int) (center & 4294967295L)), radius, paint.getInternalPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint) {
        this.internalCanvas.drawArc(left, top, right, bottom, startAngle, sweepAngle, useCenter, paint.getInternalPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void drawPath(Path path, Paint paint) {
        android.graphics.Canvas canvas = this.internalCanvas;
        if (path instanceof AndroidPath) {
            canvas.drawPath(((AndroidPath) path).getInternalPath(), paint.getInternalPaint());
            return;
        }
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: drawImage-d-4ec7I, reason: not valid java name */
    public void mo3969drawImaged4ec7I(ImageBitmap image, long topLeftOffset, Paint paint) {
        this.internalCanvas.drawBitmap(AndroidImageBitmap_androidKt.asAndroidBitmap(image), Float.intBitsToFloat((int) (topLeftOffset >> 32)), Float.intBitsToFloat((int) (topLeftOffset & 4294967295L)), paint.getInternalPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: drawImageRect-HPBpro0, reason: not valid java name */
    public void mo3970drawImageRectHPBpro0(ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, Paint paint) {
        if (this.srcRect == null) {
            this.srcRect = new Rect();
            this.dstRect = new Rect();
        }
        android.graphics.Canvas canvas = this.internalCanvas;
        Bitmap bitmapAsAndroidBitmap = AndroidImageBitmap_androidKt.asAndroidBitmap(image);
        Rect rect = this.srcRect;
        Intrinsics.checkNotNull(rect);
        rect.left = IntOffset.m7022getXimpl(srcOffset);
        rect.top = IntOffset.m7023getYimpl(srcOffset);
        rect.right = IntOffset.m7022getXimpl(srcOffset) + ((int) (srcSize >> 32));
        rect.bottom = IntOffset.m7023getYimpl(srcOffset) + ((int) (srcSize & 4294967295L));
        Unit unit = Unit.INSTANCE;
        Rect rect2 = this.dstRect;
        Intrinsics.checkNotNull(rect2);
        rect2.left = IntOffset.m7022getXimpl(dstOffset);
        rect2.top = IntOffset.m7023getYimpl(dstOffset);
        rect2.right = IntOffset.m7022getXimpl(dstOffset) + ((int) (dstSize >> 32));
        rect2.bottom = IntOffset.m7023getYimpl(dstOffset) + ((int) (dstSize & 4294967295L));
        Unit unit2 = Unit.INSTANCE;
        canvas.drawBitmap(bitmapAsAndroidBitmap, rect, rect2, paint.getInternalPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: drawPoints-O7TthRY, reason: not valid java name */
    public void mo3972drawPointsO7TthRY(int pointMode, List<Offset> points, Paint paint) {
        if (PointMode.m4474equalsimpl0(pointMode, PointMode.INSTANCE.m4478getLinesr_lszbg())) {
            drawLines(points, paint, 2);
        } else if (PointMode.m4474equalsimpl0(pointMode, PointMode.INSTANCE.m4480getPolygonr_lszbg())) {
            drawLines(points, paint, 1);
        } else if (PointMode.m4474equalsimpl0(pointMode, PointMode.INSTANCE.m4479getPointsr_lszbg())) {
            drawPoints(points, paint);
        }
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void enableZ() {
        CanvasUtils.INSTANCE.enableZ(this.internalCanvas, true);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public void disableZ() {
        CanvasUtils.INSTANCE.enableZ(this.internalCanvas, false);
    }

    private final void drawLines(List<Offset> points, Paint paint, int stepBy) {
        if (points.size() >= 2) {
            android.graphics.Paint internalPaint = paint.getInternalPaint();
            int i = 0;
            while (i < points.size() - 1) {
                long jM3880unboximpl = points.get(i).m3880unboximpl();
                long jM3880unboximpl2 = points.get(i + 1).m3880unboximpl();
                this.internalCanvas.drawLine(Float.intBitsToFloat((int) (jM3880unboximpl >> 32)), Float.intBitsToFloat((int) (jM3880unboximpl & 4294967295L)), Float.intBitsToFloat((int) (jM3880unboximpl2 >> 32)), Float.intBitsToFloat((int) (jM3880unboximpl2 & 4294967295L)), internalPaint);
                i += stepBy;
            }
        }
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: drawRawPoints-O7TthRY, reason: not valid java name */
    public void mo3973drawRawPointsO7TthRY(int pointMode, float[] points, Paint paint) {
        if (points.length % 2 != 0) {
            throw new IllegalArgumentException("points must have an even number of values");
        }
        if (PointMode.m4474equalsimpl0(pointMode, PointMode.INSTANCE.m4478getLinesr_lszbg())) {
            drawRawLines(points, paint, 2);
        } else if (PointMode.m4474equalsimpl0(pointMode, PointMode.INSTANCE.m4480getPolygonr_lszbg())) {
            drawRawLines(points, paint, 1);
        } else if (PointMode.m4474equalsimpl0(pointMode, PointMode.INSTANCE.m4479getPointsr_lszbg())) {
            drawRawPoints(points, paint, 2);
        }
    }

    private final void drawRawPoints(float[] points, Paint paint, int stepBy) {
        if (points.length % 2 == 0) {
            android.graphics.Paint internalPaint = paint.getInternalPaint();
            int i = 0;
            while (i < points.length - 1) {
                this.internalCanvas.drawPoint(points[i], points[i + 1], internalPaint);
                i += stepBy;
            }
        }
    }

    private final void drawRawLines(float[] points, Paint paint, int stepBy) {
        if (points.length < 4 || points.length % 2 != 0) {
            return;
        }
        android.graphics.Paint internalPaint = paint.getInternalPaint();
        int i = 0;
        while (i < points.length - 3) {
            this.internalCanvas.drawLine(points[i], points[i + 1], points[i + 2], points[i + 3], internalPaint);
            i += stepBy * 2;
        }
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* JADX INFO: renamed from: drawVertices-TPEHhCM, reason: not valid java name */
    public void mo3974drawVerticesTPEHhCM(Vertices vertices, int blendMode, Paint paint) {
        this.internalCanvas.drawVertices(AndroidVertexMode_androidKt.m4020toAndroidVertexModeJOOmi9M(vertices.getVertexMode()), vertices.getPositions().length, vertices.getPositions(), 0, vertices.getTextureCoordinates(), 0, vertices.getColors(), 0, vertices.getIndices(), 0, vertices.getIndices().length, paint.getInternalPaint());
    }

    private final void drawPoints(List<Offset> points, Paint paint) {
        int size = points.size();
        for (int i = 0; i < size; i++) {
            long jM3880unboximpl = points.get(i).m3880unboximpl();
            this.internalCanvas.drawPoint(Float.intBitsToFloat((int) (jM3880unboximpl >> 32)), Float.intBitsToFloat((int) (jM3880unboximpl & 4294967295L)), paint.getInternalPaint());
        }
    }
}
