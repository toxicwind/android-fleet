package qrcode.shape;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import qrcode.render.QRCodeGraphics;

/* JADX INFO: compiled from: RoundSquaresShapeFunction.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B'\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J8\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J@\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lqrcode/shape/RoundSquaresShapeFunction;", "Lqrcode/shape/DefaultShapeFunction;", "squareSize", "", "radius", "innerSpace", "<init>", "(III)V", "fillRect", "", "x", "y", "width", "height", "color", "canvas", "Lqrcode/render/QRCodeGraphics;", "drawRect", "thickness", "", "Companion", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class RoundSquaresShapeFunction extends DefaultShapeFunction {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int radius;

    public RoundSquaresShapeFunction() {
        this(0, 0, 0, 7, null);
    }

    public RoundSquaresShapeFunction(int i) {
        this(i, 0, 0, 6, null);
    }

    public RoundSquaresShapeFunction(int i, int i2) {
        this(i, i2, 0, 4, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ RoundSquaresShapeFunction(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        i = (i4 & 1) != 0 ? 25 : i;
        this(i, (i4 & 2) != 0 ? INSTANCE.defaultRadius(i) : i2, (i4 & 4) != 0 ? INSTANCE.defaultInnerSpace(i) : i3);
    }

    /* JADX INFO: compiled from: RoundSquaresShapeFunction.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005¨\u0006\b"}, d2 = {"Lqrcode/shape/RoundSquaresShapeFunction$Companion;", "", "<init>", "()V", "defaultRadius", "", "squareSize", "defaultInnerSpace", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int defaultRadius(int squareSize) {
            return MathKt.roundToInt(((double) squareSize) / 1.75d);
        }

        public final int defaultInnerSpace(int squareSize) {
            return MathKt.roundToInt(((double) squareSize) * 0.05d);
        }
    }

    public RoundSquaresShapeFunction(int i, int i2, int i3) {
        super(i, i3);
        this.radius = i2;
    }

    @Override // qrcode.shape.DefaultShapeFunction
    public void fillRect(int x, int y, int width, int height, int color, QRCodeGraphics canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.fillRoundRect(x, y, width, height, this.radius, color);
    }

    @Override // qrcode.shape.DefaultShapeFunction
    public void drawRect(int x, int y, int width, int height, int color, double thickness, QRCodeGraphics canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.drawRoundRect(x, y, width, height, this.radius, color, thickness);
    }
}
