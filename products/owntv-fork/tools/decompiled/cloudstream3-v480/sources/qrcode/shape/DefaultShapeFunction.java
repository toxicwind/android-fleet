package qrcode.shape;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.mozilla.javascript.ES6Iterator;
import qrcode.QRCode;
import qrcode.color.QRCodeColorFunction;
import qrcode.internals.QRCodeSquare;
import qrcode.internals.QRCodeSquareType;
import qrcode.render.QRCodeGraphics;

/* JADX INFO: compiled from: DefaultShapeFunction.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0006\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J8\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J8\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J8\u0010\u001d\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J8\u0010!\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J@\u0010$\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u00032\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\b¨\u0006'"}, d2 = {"Lqrcode/shape/DefaultShapeFunction;", "Lqrcode/shape/QRCodeShapeFunction;", "squareSize", "", "innerSpace", "<init>", "(II)V", "getInnerSpace", "()I", "innerSpacing", ES6Iterator.VALUE_PROPERTY, "getSquareSize", "resize", "", "newSquareSize", "renderSquare", "x", "y", "colorFn", "Lqrcode/color/QRCodeColorFunction;", "square", "Lqrcode/internals/QRCodeSquare;", "canvas", "Lqrcode/render/QRCodeGraphics;", "qrCode", "Lqrcode/QRCode;", "renderControlSquare", "xOffset", "yOffset", "drawSquaresLine", "amount", "skip", "color", "fillRect", "width", "height", "drawRect", "thickness", "", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class DefaultShapeFunction implements QRCodeShapeFunction {
    private final int innerSpace;
    private int innerSpacing;
    private int squareSize;

    /* JADX INFO: compiled from: DefaultShapeFunction.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[QRCodeSquareType.values().length];
            try {
                iArr[QRCodeSquareType.POSITION_PROBE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public DefaultShapeFunction() {
        int i = 0;
        this(i, i, 3, null);
    }

    @Override // qrcode.shape.QRCodeShapeFunction
    public void beforeRender(QRCode qRCode, QRCodeGraphics qRCodeGraphics) {
        QRCodeShapeFunction.DefaultImpls.beforeRender(this, qRCode, qRCodeGraphics);
    }

    public DefaultShapeFunction(int i, int i2) {
        this.innerSpace = i2;
        this.innerSpacing = RangesKt.coerceIn(i2, (ClosedRange<Integer>) new IntRange(0, i / 2));
        this.squareSize = i;
    }

    public /* synthetic */ DefaultShapeFunction(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 25 : i, (i3 & 2) != 0 ? 1 : i2);
    }

    public final int getInnerSpace() {
        return this.innerSpace;
    }

    public final int getSquareSize() {
        return this.squareSize;
    }

    @Override // qrcode.shape.QRCodeShapeFunction
    public void resize(int newSquareSize) {
        double d = ((double) newSquareSize) / ((double) this.squareSize);
        this.squareSize = newSquareSize;
        this.innerSpacing = RangesKt.coerceIn((int) (((double) this.innerSpace) * d), (ClosedRange<Integer>) new IntRange(0, newSquareSize / 2));
    }

    @Override // qrcode.shape.QRCodeShapeFunction
    public void renderSquare(int x, int y, QRCodeColorFunction colorFn, QRCodeSquare square, QRCodeGraphics canvas, QRCode qrCode) {
        Intrinsics.checkNotNullParameter(colorFn, "colorFn");
        Intrinsics.checkNotNullParameter(square, "square");
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(qrCode, "qrCode");
        int iFg = square.getDark() ? colorFn.fg(square.getRow(), square.getCol(), qrCode, canvas) : colorFn.bg(square.getRow(), square.getCol(), qrCode, canvas);
        int i = this.innerSpacing;
        int i2 = this.squareSize;
        fillRect(x + i, y + i, i2 - (i * 2), i2 - (i * 2), iFg, canvas);
    }

    @Override // qrcode.shape.QRCodeShapeFunction
    public void renderControlSquare(int xOffset, int yOffset, QRCodeColorFunction colorFn, QRCodeSquare square, QRCodeGraphics canvas, QRCode qrCode) {
        Intrinsics.checkNotNullParameter(colorFn, "colorFn");
        Intrinsics.checkNotNullParameter(square, "square");
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(qrCode, "qrCode");
        QRCodeSquare parent = square.getParent();
        if (parent == null) {
            parent = square;
        }
        int iBg = colorFn.bg(parent.getRow(), parent.getCol(), qrCode, canvas);
        int iFg = colorFn.fg(parent.getRow(), parent.getCol(), qrCode, canvas);
        int rowSize = this.squareSize * parent.getRowSize();
        int iAbsoluteX = xOffset + parent.absoluteX(this.squareSize);
        int iAbsoluteY = parent.absoluteY(this.squareSize) + yOffset;
        if (WhenMappings.$EnumSwitchMapping$0[parent.getSquareInfo().getType().ordinal()] == 1) {
            int i = this.squareSize;
            canvas.fillRect(iAbsoluteX, iAbsoluteY, rowSize + (i * 2), rowSize + (i * 2), iBg);
            int i2 = this.innerSpacing;
            drawRect(iAbsoluteX + i2, iAbsoluteY + i2, rowSize - (i2 * 2), rowSize - (i2 * 2), iFg, this.squareSize, canvas);
            int i3 = this.squareSize;
            fillRect(iAbsoluteX + (i3 * 2), (i3 * 2) + iAbsoluteY, rowSize - (i3 * 4), rowSize - (i3 * 4), iFg, canvas);
            return;
        }
        canvas.fillRect(iAbsoluteX, iAbsoluteY, rowSize, rowSize, iBg);
        drawSquaresLine(iAbsoluteX, iAbsoluteY, 5, 1, iFg, canvas);
        drawSquaresLine(iAbsoluteX, iAbsoluteY + this.squareSize, 5, 4, iFg, canvas);
        drawSquaresLine(iAbsoluteX, iAbsoluteY + (this.squareSize * 2), 5, 2, iFg, canvas);
        drawSquaresLine(iAbsoluteX, iAbsoluteY + (this.squareSize * 3), 5, 4, iFg, canvas);
        drawSquaresLine(iAbsoluteX, iAbsoluteY + (this.squareSize * 4), 5, 1, iFg, canvas);
    }

    private final void drawSquaresLine(int x, int y, int amount, int skip, int color, QRCodeGraphics canvas) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, amount), skip);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step <= 0 || first > last) && (step >= 0 || last > first)) {
            return;
        }
        int i = first;
        while (true) {
            int i2 = this.squareSize;
            int i3 = this.innerSpacing;
            fillRect((i2 * i) + x + i3, y + i3, i2 - (i3 * 2), i2 - (i3 * 2), color, canvas);
            if (i == last) {
                return;
            } else {
                i += step;
            }
        }
    }

    public void fillRect(int x, int y, int width, int height, int color, QRCodeGraphics canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.fillRect(x, y, width, height, color);
    }

    public void drawRect(int x, int y, int width, int height, int color, double thickness, QRCodeGraphics canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.drawRect(x, y, width, height, color, thickness);
    }
}
