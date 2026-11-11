package qrcode.render;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import qrcode.render.graphics.AndroidDrawingInterface;
import qrcode.render.graphics.BitmapGraphics;

/* JADX INFO: compiled from: QRCodeGraphics.android.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 92\u00020\u0001:\u00019B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u000bH\u0002J\u0013\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0016H\u0016¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J$\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001f\u001a\u00020\u0003H\u0016J\u0013\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001b0\u0016H\u0016¢\u0006\u0002\u0010!J\b\u0010\"\u001a\u00020\u0001H\u0016J8\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u00032\u0006\u0010)\u001a\u00020*H\u0016J8\u0010+\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u00032\u0006\u0010)\u001a\u00020*H\u0016J0\u0010.\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u0003H\u0016J\u0010\u0010/\u001a\u00020\u00132\u0006\u0010(\u001a\u00020\u0003H\u0016J@\u00100\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u00101\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u00032\u0006\u0010)\u001a\u00020*H\u0016J8\u00102\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u00101\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u0003H\u0016J6\u00103\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u00032\u0006\u0010)\u001a\u00020*J.\u00104\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u0003J \u00105\u001a\u00020\u00132\b\u00106\u001a\u0004\u0018\u00010\u00192\u0006\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u0003J \u00105\u001a\u00020\u00132\u0006\u00107\u001a\u0002082\u0006\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lqrcode/render/QRCodeGraphics;", "", "width", "", "height", "<init>", "(II)V", "getWidth", "()I", "getHeight", "drawingInterface", "Lqrcode/render/graphics/AndroidDrawingInterface;", "getDrawingInterface", "()Lqrcode/render/graphics/AndroidDrawingInterface;", "setDrawingInterface", "(Lqrcode/render/graphics/AndroidDrawingInterface;)V", "changed", "", "reset", "", "useCanvas", "dimensions", "", "()[Ljava/lang/Integer;", "getBytes", "", "format", "", "writeImage", "destination", "Ljava/io/OutputStream;", "quality", "availableFormats", "()[Ljava/lang/String;", "nativeImage", "drawLine", "x1", "y1", "x2", "y2", "color", "thickness", "", "drawRect", "x", "y", "fillRect", "fill", "drawRoundRect", "borderRadius", "fillRoundRect", "drawEllipse", "fillEllipse", "drawImage", "rawData", "img", "Landroid/graphics/Bitmap;", "Companion", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class QRCodeGraphics {
    private static final String[] AVAILABLE_FORMATS;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private boolean changed;
    private AndroidDrawingInterface drawingInterface;
    private final int height;
    private final int width;

    /* JADX INFO: compiled from: QRCodeGraphics.android.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class EntriesMappings {
        public static final /* synthetic */ EnumEntries<Bitmap.CompressFormat> entries$0 = EnumEntriesKt.enumEntries(Bitmap.CompressFormat.values());
    }

    public QRCodeGraphics(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    /* JADX INFO: compiled from: QRCodeGraphics.android.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lqrcode/render/QRCodeGraphics$Companion;", "", "<init>", "()V", "AVAILABLE_FORMATS", "", "", "getAVAILABLE_FORMATS", "()[Ljava/lang/String;", "[Ljava/lang/String;", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String[] getAVAILABLE_FORMATS() {
            return QRCodeGraphics.AVAILABLE_FORMATS;
        }
    }

    static {
        EnumEntries<Bitmap.CompressFormat> enumEntries = EntriesMappings.entries$0;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(enumEntries, 10));
        Iterator<Bitmap.CompressFormat> it = enumEntries.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().name());
        }
        AVAILABLE_FORMATS = (String[]) arrayList.toArray(new String[0]);
    }

    public final AndroidDrawingInterface getDrawingInterface() {
        return this.drawingInterface;
    }

    public final void setDrawingInterface(AndroidDrawingInterface androidDrawingInterface) {
        this.drawingInterface = androidDrawingInterface;
    }

    /* JADX INFO: renamed from: changed, reason: from getter */
    public boolean getChanged() {
        return this.changed;
    }

    public final void reset() {
        if (this.changed) {
            this.changed = false;
        }
    }

    private final AndroidDrawingInterface useCanvas() {
        if (this.drawingInterface == null) {
            this.drawingInterface = new BitmapGraphics(this.width, this.height, null, 4, null);
        }
        AndroidDrawingInterface androidDrawingInterface = this.drawingInterface;
        Intrinsics.checkNotNull(androidDrawingInterface);
        return androidDrawingInterface;
    }

    public Integer[] dimensions() {
        return new Integer[]{Integer.valueOf(this.width), Integer.valueOf(this.height)};
    }

    public byte[] getBytes() {
        return getBytes("PNG");
    }

    public byte[] getBytes(String format) throws IOException {
        Intrinsics.checkNotNullParameter(format, "format");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        writeImage$default(this, byteArrayOutputStream, format, 0, 4, null);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "let(...)");
        return byteArray;
    }

    public static /* synthetic */ void writeImage$default(QRCodeGraphics qRCodeGraphics, OutputStream outputStream, String str, int i, int i2, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: writeImage");
        }
        if ((i2 & 2) != 0) {
            str = "PNG";
        }
        if ((i2 & 4) != 0) {
            i = 100;
        }
        qRCodeGraphics.writeImage(outputStream, str, i);
    }

    public void writeImage(OutputStream destination, String format, int quality) throws IOException {
        byte[] bytes;
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(format, "format");
        AndroidDrawingInterface androidDrawingInterface = this.drawingInterface;
        if (androidDrawingInterface == null || (bytes = androidDrawingInterface.getBytes(format, quality)) == null) {
            bytes = new byte[0];
        }
        destination.write(bytes);
    }

    public String[] availableFormats() {
        return AVAILABLE_FORMATS;
    }

    public Object nativeImage() {
        Object objNativeImage;
        AndroidDrawingInterface androidDrawingInterface = this.drawingInterface;
        if (androidDrawingInterface == null || (objNativeImage = androidDrawingInterface.nativeImage()) == null) {
            throw new NotImplementedError("Native image not supported");
        }
        return objNativeImage;
    }

    public void drawLine(int x1, int y1, int x2, int y2, int color, double thickness) {
        useCanvas().drawLine(x1, y1, x2, y2, color, thickness);
    }

    public void drawRect(int x, int y, int width, int height, int color, double thickness) {
        useCanvas().drawRect(x, y, width, height, color, thickness);
    }

    public void fillRect(int x, int y, int width, int height, int color) {
        useCanvas().fillRect(x, y, width, height, color);
    }

    public void fill(int color) {
        fillRect(0, 0, this.width, this.height, color);
    }

    public void drawRoundRect(int x, int y, int width, int height, int borderRadius, int color, double thickness) {
        useCanvas().drawRoundRect(x, y, width, height, borderRadius, color, thickness);
    }

    public void fillRoundRect(int x, int y, int width, int height, int borderRadius, int color) {
        useCanvas().fillRoundRect(x, y, width, height, borderRadius, color);
    }

    public final void drawEllipse(int x, int y, int width, int height, int color, double thickness) {
        useCanvas().drawEllipse(x, y, width, height, color, thickness);
    }

    public final void fillEllipse(int x, int y, int width, int height, int color) {
        useCanvas().fillEllipse(x, y, width, height, color);
    }

    public final void drawImage(byte[] rawData, int x, int y) {
        if (rawData != null) {
            if (rawData.length == 0) {
                return;
            }
            Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(rawData, 0, rawData.length);
            Intrinsics.checkNotNull(bitmapDecodeByteArray);
            drawImage(bitmapDecodeByteArray, x, y);
        }
    }

    public void drawImage(Bitmap img, int x, int y) {
        Intrinsics.checkNotNullParameter(img, "img");
        this.changed = true;
        useCanvas().drawBitmap(img, x, y);
    }
}
