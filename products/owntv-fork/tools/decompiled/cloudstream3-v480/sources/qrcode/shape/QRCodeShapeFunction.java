package qrcode.shape;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import qrcode.QRCode;
import qrcode.color.QRCodeColorFunction;
import qrcode.internals.QRCodeSquare;
import qrcode.render.QRCodeGraphics;

/* JADX INFO: compiled from: QRCodeShapeFunction.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J8\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH&J8\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\u0016"}, d2 = {"Lqrcode/shape/QRCodeShapeFunction;", "", "resize", "", "newSquareSize", "", "beforeRender", "qrCode", "Lqrcode/QRCode;", "qrCodeGraphics", "Lqrcode/render/QRCodeGraphics;", "renderSquare", "x", "y", "colorFn", "Lqrcode/color/QRCodeColorFunction;", "square", "Lqrcode/internals/QRCodeSquare;", "canvas", "renderControlSquare", "xOffset", "yOffset", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface QRCodeShapeFunction {

    /* JADX INFO: compiled from: QRCodeShapeFunction.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void beforeRender(QRCodeShapeFunction qRCodeShapeFunction, QRCode qrCode, QRCodeGraphics qrCodeGraphics) {
            Intrinsics.checkNotNullParameter(qrCode, "qrCode");
            Intrinsics.checkNotNullParameter(qrCodeGraphics, "qrCodeGraphics");
        }
    }

    void beforeRender(QRCode qrCode, QRCodeGraphics qrCodeGraphics);

    void renderControlSquare(int xOffset, int yOffset, QRCodeColorFunction colorFn, QRCodeSquare square, QRCodeGraphics canvas, QRCode qrCode);

    void renderSquare(int x, int y, QRCodeColorFunction colorFn, QRCodeSquare square, QRCodeGraphics canvas, QRCode qrCode);

    void resize(int newSquareSize);
}
