package qrcode.render.graphics;

import android.graphics.Bitmap;
import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidDrawingInterface.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J8\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH&J8\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH&J0\u0010\u0011\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H&J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0005H&J@\u0010\u0013\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH&J8\u0010\u0015\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H&J8\u0010\u0016\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH&J0\u0010\u0017\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H&J\"\u0010\u0018\u001a\u00020\u00032\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H&J \u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H&J\b\u0010\u001e\u001a\u00020\u0001H&J\u001c\u0010\u001f\u001a\u00020\u001a2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020\u0005H&¨\u0006#"}, d2 = {"Lqrcode/render/graphics/AndroidDrawingInterface;", "", "drawLine", "", "x1", "", "y1", "x2", "y2", "color", "thickness", "", "drawRect", "x", "y", "width", "height", "fillRect", "fill", "drawRoundRect", "borderRadius", "fillRoundRect", "drawEllipse", "fillEllipse", "drawImage", "rawData", "", "drawBitmap", "img", "Landroid/graphics/Bitmap;", "nativeImage", "getBytes", "format", "", "quality", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface AndroidDrawingInterface {
    void drawBitmap(Bitmap img, int x, int y);

    void drawEllipse(int x, int y, int width, int height, int color, double thickness);

    void drawImage(byte[] rawData, int x, int y);

    void drawLine(int x1, int y1, int x2, int y2, int color, double thickness);

    void drawRect(int x, int y, int width, int height, int color, double thickness);

    void drawRoundRect(int x, int y, int width, int height, int borderRadius, int color, double thickness);

    void fill(int color);

    void fillEllipse(int x, int y, int width, int height, int color);

    void fillRect(int x, int y, int width, int height, int color);

    void fillRoundRect(int x, int y, int width, int height, int borderRadius, int color);

    byte[] getBytes(String format, int quality);

    Object nativeImage();

    /* JADX INFO: compiled from: AndroidDrawingInterface.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ byte[] getBytes$default(AndroidDrawingInterface androidDrawingInterface, String str, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getBytes");
            }
            if ((i2 & 1) != 0) {
                str = "PNG";
            }
            if ((i2 & 2) != 0) {
                i = 100;
            }
            return androidDrawingInterface.getBytes(str, i);
        }
    }
}
