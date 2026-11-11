package qrcode.color;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import qrcode.QRCode;
import qrcode.internals.QRCodeSquare;
import qrcode.render.QRCodeGraphics;

/* JADX INFO: compiled from: QRCodeColorFunction.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J(\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J(\u0010\u000f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\u0010"}, d2 = {"Lqrcode/color/QRCodeColorFunction;", "", "colorFn", "", "square", "Lqrcode/internals/QRCodeSquare;", "qrCode", "Lqrcode/QRCode;", "qrCodeGraphics", "Lqrcode/render/QRCodeGraphics;", "beforeRender", "", "fg", "row", "col", "bg", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface QRCodeColorFunction {
    void beforeRender(QRCode qrCode, QRCodeGraphics qrCodeGraphics);

    int bg(int row, int col, QRCode qrCode, QRCodeGraphics qrCodeGraphics);

    int colorFn(QRCodeSquare square, QRCode qrCode, QRCodeGraphics qrCodeGraphics);

    int fg(int row, int col, QRCode qrCode, QRCodeGraphics qrCodeGraphics);

    /* JADX INFO: compiled from: QRCodeColorFunction.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void beforeRender(QRCodeColorFunction qRCodeColorFunction, QRCode qrCode, QRCodeGraphics qrCodeGraphics) {
            Intrinsics.checkNotNullParameter(qrCode, "qrCode");
            Intrinsics.checkNotNullParameter(qrCodeGraphics, "qrCodeGraphics");
        }

        public static int colorFn(QRCodeColorFunction qRCodeColorFunction, QRCodeSquare square, QRCode qrCode, QRCodeGraphics qrCodeGraphics) {
            Intrinsics.checkNotNullParameter(square, "square");
            Intrinsics.checkNotNullParameter(qrCode, "qrCode");
            Intrinsics.checkNotNullParameter(qrCodeGraphics, "qrCodeGraphics");
            if (square.getDark()) {
                return qRCodeColorFunction.fg(square.getRow(), square.getCol(), qrCode, qrCodeGraphics);
            }
            return qRCodeColorFunction.bg(square.getRow(), square.getCol(), qrCode, qrCodeGraphics);
        }
    }
}
