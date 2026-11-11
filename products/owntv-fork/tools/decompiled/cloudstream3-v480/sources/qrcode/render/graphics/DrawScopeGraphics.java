package qrcode.render.graphics;

import android.graphics.Bitmap;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import qrcode.color.Colors;

/* JADX INFO: compiled from: DrawScopeGraphics.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ4\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J8\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010 \u001a\u00020!H\u0016J8\u0010(\u001a\u00020#2\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010 \u001a\u00020!H\u0016J0\u0010)\u001a\u00020#2\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005H\u0016J\u0010\u0010*\u001a\u00020#2\u0006\u0010\u0012\u001a\u00020\u0005H\u0016J@\u0010+\u001a\u00020#2\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010 \u001a\u00020!H\u0016J8\u0010-\u001a\u00020#2\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005H\u0016J8\u0010.\u001a\u00020#2\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010 \u001a\u00020!H\u0016J0\u0010/\u001a\u00020#2\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005H\u0016J\"\u00100\u001a\u00020#2\b\u00101\u001a\u0004\u0018\u0001022\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005H\u0016J \u00103\u001a\u00020#2\u0006\u00104\u001a\u0002052\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005H\u0016J\b\u00106\u001a\u000207H\u0016J\u0018\u00108\u001a\u0002022\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\u0005H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lqrcode/render/graphics/DrawScopeGraphics;", "Lqrcode/render/graphics/AndroidDrawingInterface;", "drawScope", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "width", "", "height", "<init>", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;II)V", "getDrawScope", "()Landroidx/compose/ui/graphics/drawscope/DrawScope;", "getWidth", "()I", "getHeight", "paintCache", "", "Landroidx/compose/ui/graphics/Color;", "composeColor", "color", "composeColor-vNxB06k", "(I)J", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/ui/geometry/Offset;", "x", "y", "offset-dBAh8RU", "(II)J", "offsetSize", "Lkotlin/Pair;", "Landroidx/compose/ui/geometry/Size;", "stroke", "Landroidx/compose/ui/graphics/drawscope/Stroke;", "thickness", "", "drawLine", "", "x1", "y1", "x2", "y2", "drawRect", "fillRect", "fill", "drawRoundRect", "borderRadius", "fillRoundRect", "drawEllipse", "fillEllipse", "drawImage", "rawData", "", "drawBitmap", "img", "Landroid/graphics/Bitmap;", "nativeImage", "", "getBytes", "format", "", "quality", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DrawScopeGraphics implements AndroidDrawingInterface {
    private final DrawScope drawScope;
    private final int height;
    private final Map<Integer, Color> paintCache;
    private final int width;

    public DrawScopeGraphics(DrawScope drawScope, int i, int i2) {
        Intrinsics.checkNotNullParameter(drawScope, "drawScope");
        this.drawScope = drawScope;
        this.width = i;
        this.height = i2;
        this.paintCache = new LinkedHashMap();
    }

    public final DrawScope getDrawScope() {
        return this.drawScope;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getWidth() {
        return this.width;
    }

    /* JADX INFO: renamed from: composeColor-vNxB06k, reason: not valid java name */
    private final long m11027composeColorvNxB06k(int color) {
        Map<Integer, Color> map = this.paintCache;
        Integer numValueOf = Integer.valueOf(color);
        Color colorM4142boximpl = map.get(numValueOf);
        if (colorM4142boximpl == null) {
            int[] rgba = Colors.getRGBA(color);
            colorM4142boximpl = Color.m4142boximpl(ColorKt.Color(rgba[0], rgba[1], rgba[2], rgba[3]));
            map.put(numValueOf, colorM4142boximpl);
        }
        return colorM4142boximpl.m4162unboximpl();
    }

    private final Pair<Offset, Size> offsetSize(int x, int y, int width, int height) {
        return new Pair<>(Offset.m3859boximpl(m11028offsetdBAh8RU(x, y)), Size.m3927boximpl(Size.m3930constructorimpl((((long) Float.floatToRawIntBits(height)) & 4294967295L) | (Float.floatToRawIntBits(width) << 32))));
    }

    private final Stroke stroke(double thickness) {
        return new Stroke((float) thickness, 0.0f, 0, 0, null, 30, null);
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void drawLine(int x1, int y1, int x2, int y2, int color, double thickness) {
        DrawScope.drawLine-NGM6Ib0$default(this.drawScope, m11027composeColorvNxB06k(color), m11028offsetdBAh8RU(x1, y1), m11028offsetdBAh8RU(x2, y2), (float) thickness, 0, null, 0.0f, null, 0, 496, null);
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void drawRect(int x, int y, int width, int height, int color, double thickness) {
        int i = (int) (thickness / 2.0d);
        int i2 = x + i;
        int i3 = y + i;
        int i4 = i * 2;
        Pair<Offset, Size> pairOffsetSize = offsetSize(i2, i3, width - i4, height - i4);
        DrawScope.drawRect-n-J9OG0$default(this.drawScope, m11027composeColorvNxB06k(color), pairOffsetSize.component1().m3880unboximpl(), pairOffsetSize.component2().m3944unboximpl(), 0.0f, stroke(thickness), null, 0, 104, null);
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void fillRect(int x, int y, int width, int height, int color) {
        Pair<Offset, Size> pairOffsetSize = offsetSize(x, y, width, height);
        DrawScope.drawRect-n-J9OG0$default(this.drawScope, m11027composeColorvNxB06k(color), pairOffsetSize.component1().m3880unboximpl(), pairOffsetSize.component2().m3944unboximpl(), 0.0f, Fill.INSTANCE, null, 0, 104, null);
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void fill(int color) {
        fillRect(0, 0, this.width, this.height, color);
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void drawRoundRect(int x, int y, int width, int height, int borderRadius, int color, double thickness) {
        int i = (int) (thickness / 2.0d);
        int i2 = x + i;
        int i3 = y + i;
        int i4 = i * 2;
        Pair<Offset, Size> pairOffsetSize = offsetSize(i2, i3, width - i4, height - i4);
        long jM3880unboximpl = pairOffsetSize.component1().m3880unboximpl();
        long jM3944unboximpl = pairOffsetSize.component2().m3944unboximpl();
        float f = borderRadius;
        DrawScope.drawRoundRect-u-Aw5IA$default(this.drawScope, m11027composeColorvNxB06k(color), jM3880unboximpl, jM3944unboximpl, CornerRadius.m3824constructorimpl((((long) Float.floatToRawIntBits(f)) & 4294967295L) | (Float.floatToRawIntBits(f) << 32)), stroke(thickness), 0.0f, null, 0, 224, null);
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void fillRoundRect(int x, int y, int width, int height, int borderRadius, int color) {
        Pair<Offset, Size> pairOffsetSize = offsetSize(x, y, width, height);
        long jM3880unboximpl = pairOffsetSize.component1().m3880unboximpl();
        long jM3944unboximpl = pairOffsetSize.component2().m3944unboximpl();
        float f = borderRadius;
        DrawScope.drawRoundRect-u-Aw5IA$default(this.drawScope, m11027composeColorvNxB06k(color), jM3880unboximpl, jM3944unboximpl, CornerRadius.m3824constructorimpl((((long) Float.floatToRawIntBits(f)) << 32) | (((long) Float.floatToRawIntBits(f)) & 4294967295L)), Fill.INSTANCE, 0.0f, null, 0, 224, null);
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void drawEllipse(int x, int y, int width, int height, int color, double thickness) {
        int i = (int) (thickness / 2.0d);
        int i2 = x + i;
        int i3 = y + i;
        int i4 = i * 2;
        Pair<Offset, Size> pairOffsetSize = offsetSize(i2, i3, width - i4, height - i4);
        DrawScope.drawOval-n-J9OG0$default(this.drawScope, m11027composeColorvNxB06k(color), pairOffsetSize.component1().m3880unboximpl(), pairOffsetSize.component2().m3944unboximpl(), 0.0f, stroke(thickness), null, 0, 104, null);
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void fillEllipse(int x, int y, int width, int height, int color) {
        Pair<Offset, Size> pairOffsetSize = offsetSize(x, y, width, height);
        DrawScope.drawOval-n-J9OG0$default(this.drawScope, m11027composeColorvNxB06k(color), pairOffsetSize.component1().m3880unboximpl(), pairOffsetSize.component2().m3944unboximpl(), 0.0f, Fill.INSTANCE, null, 0, 104, null);
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void drawImage(byte[] rawData, int x, int y) {
        throw new NotImplementedError("An operation is not implemented: Unsupported operation");
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public void drawBitmap(Bitmap img, int x, int y) {
        Intrinsics.checkNotNullParameter(img, "img");
        throw new NotImplementedError("An operation is not implemented: Unsupported operation");
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public Object nativeImage() {
        return this.drawScope;
    }

    @Override // qrcode.render.graphics.AndroidDrawingInterface
    public byte[] getBytes(String format, int quality) {
        Intrinsics.checkNotNullParameter(format, "format");
        throw new NotImplementedError("An operation is not implemented: Unsupported operation");
    }

    /* JADX INFO: renamed from: offset-dBAh8RU, reason: not valid java name */
    private final long m11028offsetdBAh8RU(int x, int y) {
        return Offset.m3862constructorimpl((((long) Float.floatToRawIntBits(y)) & 4294967295L) | (Float.floatToRawIntBits(x) << 32));
    }
}
