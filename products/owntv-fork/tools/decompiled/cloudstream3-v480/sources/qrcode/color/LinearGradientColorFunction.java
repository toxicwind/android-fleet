package qrcode.color;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import qrcode.QRCode;
import qrcode.internals.QRCodeSquare;
import qrcode.render.QRCodeGraphics;

/* JADX INFO: compiled from: LinearGradientColorFunction.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B-\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ(\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J(\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lqrcode/color/LinearGradientColorFunction;", "Lqrcode/color/QRCodeColorFunction;", "startForegroundColor", "", "endForegroundColor", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "vertical", "", "<init>", "(IIIZ)V", "getStartForegroundColor", "()I", "getEndForegroundColor", "getBackgroundColor", "getVertical", "()Z", "setVertical", "(Z)V", "startComponents", "", "endComponents", "fg", "row", "col", "qrCode", "Lqrcode/QRCode;", "qrCodeGraphics", "Lqrcode/render/QRCodeGraphics;", "bg", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LinearGradientColorFunction implements QRCodeColorFunction {
    private final int backgroundColor;
    private final int[] endComponents;
    private final int endForegroundColor;
    private final int[] startComponents;
    private final int startForegroundColor;
    private boolean vertical;

    public LinearGradientColorFunction(int i, int i2) {
        this(i, i2, 0, false, 12, null);
    }

    public LinearGradientColorFunction(int i, int i2, int i3) {
        this(i, i2, i3, false, 8, null);
    }

    @Override // qrcode.color.QRCodeColorFunction
    public void beforeRender(QRCode qRCode, QRCodeGraphics qRCodeGraphics) {
        QRCodeColorFunction.DefaultImpls.beforeRender(this, qRCode, qRCodeGraphics);
    }

    @Override // qrcode.color.QRCodeColorFunction
    public int colorFn(QRCodeSquare qRCodeSquare, QRCode qRCode, QRCodeGraphics qRCodeGraphics) {
        return QRCodeColorFunction.DefaultImpls.colorFn(this, qRCodeSquare, qRCode, qRCodeGraphics);
    }

    public LinearGradientColorFunction(int i, int i2, int i3, boolean z) {
        this.startForegroundColor = i;
        this.endForegroundColor = i2;
        this.backgroundColor = i3;
        this.vertical = z;
        this.startComponents = Colors.getRGBA(i);
        this.endComponents = Colors.getRGBA(i2);
    }

    public /* synthetic */ LinearGradientColorFunction(int i, int i2, int i3, boolean z, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i4 & 4) != 0 ? -1 : i3, (i4 & 8) != 0 ? true : z);
    }

    public final int getStartForegroundColor() {
        return this.startForegroundColor;
    }

    public final int getEndForegroundColor() {
        return this.endForegroundColor;
    }

    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    public final boolean getVertical() {
        return this.vertical;
    }

    public final void setVertical(boolean z) {
        this.vertical = z;
    }

    @Override // qrcode.color.QRCodeColorFunction
    public int fg(int row, int col, QRCode qrCode, QRCodeGraphics qrCodeGraphics) {
        Intrinsics.checkNotNullParameter(qrCode, "qrCode");
        Intrinsics.checkNotNullParameter(qrCodeGraphics, "qrCodeGraphics");
        if (!this.vertical) {
            row = col;
        }
        double length = ((double) row) / ((double) qrCode.getRawData().length);
        int[] iArr = this.startComponents;
        double d = 1.0d - length;
        double d2 = ((double) iArr[0]) * d;
        int[] iArr2 = this.endComponents;
        return Colors.rgba(RangesKt.coerceIn(MathKt.roundToInt(d2 + (((double) iArr2[0]) * length)), (ClosedRange<Integer>) new IntRange(0, 255)), RangesKt.coerceIn(MathKt.roundToInt((((double) iArr[1]) * d) + (((double) iArr2[1]) * length)), (ClosedRange<Integer>) new IntRange(0, 255)), RangesKt.coerceIn(MathKt.roundToInt((((double) iArr[2]) * d) + (((double) iArr2[2]) * length)), (ClosedRange<Integer>) new IntRange(0, 255)), 255);
    }

    @Override // qrcode.color.QRCodeColorFunction
    public int bg(int row, int col, QRCode qrCode, QRCodeGraphics qrCodeGraphics) {
        Intrinsics.checkNotNullParameter(qrCode, "qrCode");
        Intrinsics.checkNotNullParameter(qrCodeGraphics, "qrCodeGraphics");
        return this.backgroundColor;
    }
}
