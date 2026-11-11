package qrcode.color;

import androidx.media3.exoplayer.offline.DownloadService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import qrcode.QRCode;
import qrcode.internals.QRCodeSquare;
import qrcode.render.QRCodeGraphics;

/* JADX INFO: compiled from: DefaultColorFunction.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J(\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J(\u0010\u000e\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lqrcode/color/DefaultColorFunction;", "Lqrcode/color/QRCodeColorFunction;", DownloadService.KEY_FOREGROUND, "", "background", "<init>", "(II)V", "fg", "row", "col", "qrCode", "Lqrcode/QRCode;", "qrCodeGraphics", "Lqrcode/render/QRCodeGraphics;", "bg", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class DefaultColorFunction implements QRCodeColorFunction {
    private final int background;
    private final int foreground;

    /* JADX WARN: Illegal instructions before constructor call */
    public DefaultColorFunction() {
        int i = 0;
        this(i, i, 3, null);
    }

    public DefaultColorFunction(int i, int i2) {
        this.foreground = i;
        this.background = i2;
    }

    public /* synthetic */ DefaultColorFunction(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? -16777216 : i, (i3 & 2) != 0 ? 0 : i2);
    }

    @Override // qrcode.color.QRCodeColorFunction
    public void beforeRender(QRCode qRCode, QRCodeGraphics qRCodeGraphics) {
        QRCodeColorFunction.DefaultImpls.beforeRender(this, qRCode, qRCodeGraphics);
    }

    @Override // qrcode.color.QRCodeColorFunction
    public int colorFn(QRCodeSquare qRCodeSquare, QRCode qRCode, QRCodeGraphics qRCodeGraphics) {
        return QRCodeColorFunction.DefaultImpls.colorFn(this, qRCodeSquare, qRCode, qRCodeGraphics);
    }

    @Override // qrcode.color.QRCodeColorFunction
    public int fg(int row, int col, QRCode qrCode, QRCodeGraphics qrCodeGraphics) {
        Intrinsics.checkNotNullParameter(qrCode, "qrCode");
        Intrinsics.checkNotNullParameter(qrCodeGraphics, "qrCodeGraphics");
        return this.foreground;
    }

    @Override // qrcode.color.QRCodeColorFunction
    public int bg(int row, int col, QRCode qrCode, QRCodeGraphics qrCodeGraphics) {
        Intrinsics.checkNotNullParameter(qrCode, "qrCode");
        Intrinsics.checkNotNullParameter(qrCodeGraphics, "qrCodeGraphics");
        return this.background;
    }
}
