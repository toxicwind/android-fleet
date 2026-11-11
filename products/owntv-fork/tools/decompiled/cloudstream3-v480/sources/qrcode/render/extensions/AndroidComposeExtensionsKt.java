package qrcode.render.extensions;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import qrcode.QRCode;
import qrcode.render.QRCodeGraphics;
import qrcode.render.graphics.AndroidDrawingInterface;
import qrcode.render.graphics.DrawScopeGraphics;

/* JADX INFO: compiled from: AndroidComposeExtensions.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a-\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"drawQRCode", "", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "qrCode", "Lqrcode/QRCode;", "offsetTopLeft", "Landroidx/compose/ui/geometry/Offset;", "sizeToFitInto", "Landroidx/compose/ui/geometry/Size;", "drawQRCode-UxnYL8s", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Lqrcode/QRCode;JJ)V", "qrcode-kotlin_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AndroidComposeExtensionsKt {
    /* JADX INFO: renamed from: drawQRCode-UxnYL8s$default, reason: not valid java name */
    public static /* synthetic */ void m11026drawQRCodeUxnYL8s$default(DrawScope drawScope, QRCode qRCode, long j, long j2, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Offset.INSTANCE.m3886getZeroF1C5BW0();
        }
        long j3 = j;
        if ((i & 4) != 0) {
            j2 = drawScope.mo4647getSizeNHjbRc();
        }
        m11025drawQRCodeUxnYL8s(drawScope, qRCode, j3, j2);
    }

    /* JADX INFO: renamed from: drawQRCode-UxnYL8s, reason: not valid java name */
    public static final void m11025drawQRCodeUxnYL8s(DrawScope drawQRCode, QRCode qrCode, long j, long j2) {
        Intrinsics.checkNotNullParameter(drawQRCode, "$this$drawQRCode");
        Intrinsics.checkNotNullParameter(qrCode, "qrCode");
        qrCode.fitIntoArea((int) Float.intBitsToFloat((int) (j2 >> 32)), (int) Float.intBitsToFloat((int) (j2 & 4294967295L)));
        QRCodeGraphics graphics = qrCode.getGraphics();
        AndroidDrawingInterface drawingInterface = graphics.getDrawingInterface();
        graphics.setDrawingInterface(new DrawScopeGraphics(drawQRCode, graphics.getWidth(), graphics.getHeight()));
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j & 4294967295L));
        drawQRCode.getDrawContext().getTransform().translate(fIntBitsToFloat, fIntBitsToFloat2);
        try {
            QRCode.render$default(qrCode, null, 0, 0, 7, null);
            drawQRCode.getDrawContext().getTransform().translate(-fIntBitsToFloat, -fIntBitsToFloat2);
            graphics.setDrawingInterface(drawingInterface);
        } catch (Throwable th) {
            drawQRCode.getDrawContext().getTransform().translate(-fIntBitsToFloat, -fIntBitsToFloat2);
            throw th;
        }
    }
}
