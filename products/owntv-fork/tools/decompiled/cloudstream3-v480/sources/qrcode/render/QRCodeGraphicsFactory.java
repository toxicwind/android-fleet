package qrcode.render;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: compiled from: QRCodeGraphicsFactory.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H\u0016¨\u0006\u000b"}, d2 = {"Lqrcode/render/QRCodeGraphicsFactory;", "", "<init>", "()V", "newGraphicsSquare", "Lqrcode/render/QRCodeGraphics;", ContentDisposition.Parameters.Size, "", "newGraphics", "width", "height", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class QRCodeGraphicsFactory {
    public QRCodeGraphics newGraphicsSquare(int size) {
        return newGraphics(size, size);
    }

    public QRCodeGraphics newGraphics(int width, int height) {
        return new QRCodeGraphics(width, height);
    }
}
