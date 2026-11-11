package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: Canvas.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0018\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH&J\u001a\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\fH&J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\fH&J\u0018\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH&J\u0018\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\fH\u0016J\u0017\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0019H&¢\u0006\u0004\b\u001a\u0010\u001bJ!\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0016¢\u0006\u0004\b \u0010!J9\u0010\u001c\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020\f2\b\b\u0002\u0010\u001e\u001a\u00020\u001fH&¢\u0006\u0004\b&\u0010'J!\u0010(\u001a\u00020\u00032\u0006\u0010)\u001a\u00020*2\b\b\u0002\u0010\u001e\u001a\u00020\u001fH&¢\u0006\u0004\b+\u0010,J'\u0010-\u001a\u00020\u00032\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0004\b1\u00102J\u0018\u00103\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J0\u00103\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tH&J@\u00104\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020\f2\u0006\u00105\u001a\u00020\f2\u0006\u00106\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tH&J\u0018\u00107\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J0\u00107\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tH&J'\u00108\u001a\u00020\u00032\u0006\u00109\u001a\u00020/2\u0006\u0010:\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0004\b;\u0010<J0\u0010=\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010>\u001a\u00020\f2\u0006\u0010?\u001a\u00020\f2\u0006\u0010@\u001a\u00020A2\u0006\u0010\b\u001a\u00020\tH\u0016JH\u0010=\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020\f2\u0006\u0010>\u001a\u00020\f2\u0006\u0010?\u001a\u00020\f2\u0006\u0010@\u001a\u00020A2\u0006\u0010\b\u001a\u00020\tH&J0\u0010B\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010C\u001a\u00020\f2\u0006\u0010D\u001a\u00020\f2\u0006\u0010@\u001a\u00020A2\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010E\u001a\u00020\u00032\u0006\u0010)\u001a\u00020*2\u0006\u0010\b\u001a\u00020\tH&J'\u0010F\u001a\u00020\u00032\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020/2\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0004\bJ\u0010KJG\u0010L\u001a\u00020\u00032\u0006\u0010G\u001a\u00020H2\b\b\u0002\u0010M\u001a\u00020N2\b\b\u0002\u0010O\u001a\u00020P2\b\b\u0002\u0010Q\u001a\u00020N2\b\b\u0002\u0010R\u001a\u00020P2\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0004\bS\u0010TJ-\u0010U\u001a\u00020\u00032\u0006\u0010V\u001a\u00020W2\f\u0010X\u001a\b\u0012\u0004\u0012\u00020/0Y2\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0004\bZ\u0010[J'\u0010\\\u001a\u00020\u00032\u0006\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020]2\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0004\b^\u0010_J'\u0010`\u001a\u00020\u00032\u0006\u0010a\u001a\u00020b2\u0006\u0010c\u001a\u00020d2\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0004\be\u0010fJ\b\u0010g\u001a\u00020\u0003H&J\b\u0010h\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006iÀ\u0006\u0003"}, d2 = {"Landroidx/compose/ui/graphics/Canvas;", "", "save", "", "restore", "saveLayer", "bounds", "Landroidx/compose/ui/geometry/Rect;", "paint", "Landroidx/compose/ui/graphics/Paint;", "translate", "dx", "", "dy", "scale", "sx", "sy", "rotate", "degrees", "skew", "skewRad", "sxRad", "syRad", "concat", "matrix", "Landroidx/compose/ui/graphics/Matrix;", "concat-58bKbWc", "([F)V", "clipRect", "rect", "clipOp", "Landroidx/compose/ui/graphics/ClipOp;", "clipRect-mtrdD-E", "(Landroidx/compose/ui/geometry/Rect;I)V", TtmlNode.LEFT, "top", TtmlNode.RIGHT, "bottom", "clipRect-N_I0leg", "(FFFFI)V", "clipPath", "path", "Landroidx/compose/ui/graphics/Path;", "clipPath-mtrdD-E", "(Landroidx/compose/ui/graphics/Path;I)V", "drawLine", "p1", "Landroidx/compose/ui/geometry/Offset;", "p2", "drawLine-Wko1d7g", "(JJLandroidx/compose/ui/graphics/Paint;)V", "drawRect", "drawRoundRect", "radiusX", "radiusY", "drawOval", "drawCircle", TtmlNode.CENTER, "radius", "drawCircle-9KIMszo", "(JFLandroidx/compose/ui/graphics/Paint;)V", "drawArc", "startAngle", "sweepAngle", "useCenter", "", "drawArcRad", "startAngleRad", "sweepAngleRad", "drawPath", "drawImage", "image", "Landroidx/compose/ui/graphics/ImageBitmap;", "topLeftOffset", "drawImage-d-4ec7I", "(Landroidx/compose/ui/graphics/ImageBitmap;JLandroidx/compose/ui/graphics/Paint;)V", "drawImageRect", "srcOffset", "Landroidx/compose/ui/unit/IntOffset;", "srcSize", "Landroidx/compose/ui/unit/IntSize;", "dstOffset", "dstSize", "drawImageRect-HPBpro0", "(Landroidx/compose/ui/graphics/ImageBitmap;JJJJLandroidx/compose/ui/graphics/Paint;)V", "drawPoints", "pointMode", "Landroidx/compose/ui/graphics/PointMode;", "points", "", "drawPoints-O7TthRY", "(ILjava/util/List;Landroidx/compose/ui/graphics/Paint;)V", "drawRawPoints", "", "drawRawPoints-O7TthRY", "(I[FLandroidx/compose/ui/graphics/Paint;)V", "drawVertices", "vertices", "Landroidx/compose/ui/graphics/Vertices;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "drawVertices-TPEHhCM", "(Landroidx/compose/ui/graphics/Vertices;ILandroidx/compose/ui/graphics/Paint;)V", "enableZ", "disableZ", "ui-graphics_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface Canvas {
    /* JADX INFO: renamed from: clipPath-mtrdD-E */
    void mo3964clipPathmtrdDE(Path path, int clipOp);

    /* JADX INFO: renamed from: clipRect-N_I0leg */
    void mo3965clipRectN_I0leg(float left, float top, float right, float bottom, int clipOp);

    /* JADX INFO: renamed from: clipRect-mtrdD-E */
    void mo3966clipRectmtrdDE(Rect rect, int clipOp);

    /* JADX INFO: renamed from: concat-58bKbWc */
    void mo3967concat58bKbWc(float[] matrix);

    void disableZ();

    void drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint);

    void drawArc(Rect rect, float startAngle, float sweepAngle, boolean useCenter, Paint paint);

    void drawArcRad(Rect rect, float startAngleRad, float sweepAngleRad, boolean useCenter, Paint paint);

    /* JADX INFO: renamed from: drawCircle-9KIMszo */
    void mo3968drawCircle9KIMszo(long center, float radius, Paint paint);

    /* JADX INFO: renamed from: drawImage-d-4ec7I */
    void mo3969drawImaged4ec7I(ImageBitmap image, long topLeftOffset, Paint paint);

    /* JADX INFO: renamed from: drawImageRect-HPBpro0 */
    void mo3970drawImageRectHPBpro0(ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, Paint paint);

    /* JADX INFO: renamed from: drawLine-Wko1d7g */
    void mo3971drawLineWko1d7g(long p1, long p2, Paint paint);

    void drawOval(float left, float top, float right, float bottom, Paint paint);

    void drawOval(Rect rect, Paint paint);

    void drawPath(Path path, Paint paint);

    /* JADX INFO: renamed from: drawPoints-O7TthRY */
    void mo3972drawPointsO7TthRY(int pointMode, List<Offset> points, Paint paint);

    /* JADX INFO: renamed from: drawRawPoints-O7TthRY */
    void mo3973drawRawPointsO7TthRY(int pointMode, float[] points, Paint paint);

    void drawRect(float left, float top, float right, float bottom, Paint paint);

    void drawRect(Rect rect, Paint paint);

    void drawRoundRect(float left, float top, float right, float bottom, float radiusX, float radiusY, Paint paint);

    /* JADX INFO: renamed from: drawVertices-TPEHhCM */
    void mo3974drawVerticesTPEHhCM(Vertices vertices, int blendMode, Paint paint);

    void enableZ();

    void restore();

    void rotate(float degrees);

    void save();

    void saveLayer(Rect bounds, Paint paint);

    void scale(float sx, float sy);

    void skew(float sx, float sy);

    void skewRad(float sxRad, float syRad);

    void translate(float dx, float dy);

    /* JADX INFO: renamed from: androidx.compose.ui.graphics.Canvas$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Canvas.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ void scale$default(Canvas canvas, float f, float f2, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: scale");
            }
            if ((i & 2) != 0) {
                f2 = f;
            }
            canvas.scale(f, f2);
        }

        public static void $default$skewRad(Canvas _this, float f, float f2) {
            _this.skew(DegreesKt.degrees(f), DegreesKt.degrees(f2));
        }

        /* JADX INFO: renamed from: clipRect-mtrdD-E$default, reason: not valid java name */
        public static /* synthetic */ void m4126clipRectmtrdDE$default(Canvas canvas, Rect rect, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: clipRect-mtrdD-E");
            }
            if ((i2 & 2) != 0) {
                i = ClipOp.INSTANCE.m4141getIntersectrtfAjoo();
            }
            canvas.mo3966clipRectmtrdDE(rect, i);
        }

        /* JADX INFO: renamed from: $default$clipRect-mtrdD-E, reason: not valid java name */
        public static void m4122$default$clipRectmtrdDE(Canvas _this, Rect rect, int i) {
            _this.mo3965clipRectN_I0leg(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), i);
        }

        /* JADX INFO: renamed from: clipRect-N_I0leg$default, reason: not valid java name */
        public static /* synthetic */ void m4125clipRectN_I0leg$default(Canvas canvas, float f, float f2, float f3, float f4, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: clipRect-N_I0leg");
            }
            if ((i2 & 16) != 0) {
                i = ClipOp.INSTANCE.m4141getIntersectrtfAjoo();
            }
            canvas.mo3965clipRectN_I0leg(f, f2, f3, f4, i);
        }

        /* JADX INFO: renamed from: clipPath-mtrdD-E$default, reason: not valid java name */
        public static /* synthetic */ void m4124clipPathmtrdDE$default(Canvas canvas, Path path, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: clipPath-mtrdD-E");
            }
            if ((i2 & 2) != 0) {
                i = ClipOp.INSTANCE.m4141getIntersectrtfAjoo();
            }
            canvas.mo3964clipPathmtrdDE(path, i);
        }

        public static void $default$drawRect(Canvas _this, Rect rect, Paint paint) {
            _this.drawRect(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), paint);
        }

        public static void $default$drawOval(Canvas _this, Rect rect, Paint paint) {
            _this.drawOval(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), paint);
        }

        public static void $default$drawArc(Canvas _this, Rect rect, float f, float f2, boolean z, Paint paint) {
            _this.drawArc(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), f, f2, z, paint);
        }

        public static void $default$drawArcRad(Canvas _this, Rect rect, float f, float f2, boolean z, Paint paint) {
            _this.drawArc(rect, DegreesKt.degrees(f), DegreesKt.degrees(f2), z, paint);
        }

        /* JADX INFO: renamed from: drawImageRect-HPBpro0$default, reason: not valid java name */
        public static /* synthetic */ void m4127drawImageRectHPBpro0$default(Canvas canvas, ImageBitmap imageBitmap, long j, long j2, long j3, long j4, Paint paint, int i, Object obj) {
            long jM7060constructorimpl;
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawImageRect-HPBpro0");
            }
            long jM7033getZeronOccac = (i & 2) != 0 ? IntOffset.INSTANCE.m7033getZeronOccac() : j;
            if ((i & 4) != 0) {
                jM7060constructorimpl = IntSize.m7060constructorimpl((((long) imageBitmap.getHeight()) & 4294967295L) | (((long) imageBitmap.getWidth()) << 32));
            } else {
                jM7060constructorimpl = j2;
            }
            canvas.mo3970drawImageRectHPBpro0(imageBitmap, jM7033getZeronOccac, jM7060constructorimpl, (i & 8) != 0 ? IntOffset.INSTANCE.m7033getZeronOccac() : j3, (i & 16) != 0 ? jM7060constructorimpl : j4, paint);
        }
    }

    /* JADX INFO: compiled from: Canvas.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void skewRad(Canvas canvas, float f, float f2) {
            CC.$default$skewRad(canvas, f, f2);
        }

        @Deprecated
        /* JADX INFO: renamed from: clipRect-mtrdD-E, reason: not valid java name */
        public static void m4130clipRectmtrdDE(Canvas canvas, Rect rect, int i) {
            CC.m4122$default$clipRectmtrdDE(canvas, rect, i);
        }

        @Deprecated
        public static void drawRect(Canvas canvas, Rect rect, Paint paint) {
            CC.$default$drawRect(canvas, rect, paint);
        }

        @Deprecated
        public static void drawOval(Canvas canvas, Rect rect, Paint paint) {
            CC.$default$drawOval(canvas, rect, paint);
        }

        @Deprecated
        public static void drawArc(Canvas canvas, Rect rect, float f, float f2, boolean z, Paint paint) {
            CC.$default$drawArc(canvas, rect, f, f2, z, paint);
        }

        @Deprecated
        public static void drawArcRad(Canvas canvas, Rect rect, float f, float f2, boolean z, Paint paint) {
            CC.$default$drawArcRad(canvas, rect, f, f2, z, paint);
        }
    }
}
