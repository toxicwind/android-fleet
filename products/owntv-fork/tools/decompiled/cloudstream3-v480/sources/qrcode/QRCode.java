package qrcode;

import io.ktor.http.ContentDisposition;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;
import qrcode.color.DefaultColorFunction;
import qrcode.color.QRCodeColorFunction;
import qrcode.internals.QRCodeSquare;
import qrcode.internals.QRCodeSquareType;
import qrcode.raw.ErrorCorrectionLevel;
import qrcode.raw.MaskPattern;
import qrcode.raw.QRCodeProcessor;
import qrcode.render.QRCodeGraphics;
import qrcode.render.QRCodeGraphicsFactory;
import qrcode.shape.DefaultShapeFunction;
import qrcode.shape.QRCodeShapeFunction;

/* JADX INFO: compiled from: QRCode.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0012\n\u0002\b\u0005\u0018\u0000 U2\u00020\u0001:\u0001UBÏ\u0001\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012+\b\u0002\u0010\u0014\u001a%\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00170\u0015¢\u0006\u0002\b\u0018\u0012+\b\u0002\u0010\u0019\u001a%\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00170\u0015¢\u0006\u0002\b\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ=\u0010F\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0016\u00108\u001a\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020:0909j\u0002`;2\u0006\u0010G\u001a\u00020\u0016H\u0002¢\u0006\u0002\u0010HJ\u000e\u0010I\u001a\u00020\u00002\u0006\u0010J\u001a\u00020\u0005J\u0016\u0010K\u001a\u00020\u00002\u0006\u0010L\u001a\u00020\u00052\u0006\u0010M\u001a\u00020\u0005J&\u0010N\u001a\u00020\u00162\b\b\u0002\u0010O\u001a\u00020\u00162\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005H\u0007J0\u0010P\u001a\u00020Q2\b\b\u0002\u0010O\u001a\u00020\u00162\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010R\u001a\u00020\u0003H\u0007J\u0006\u0010S\u001a\u00020\u0017J\b\u0010T\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001fR\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R1\u0010\u0014\u001a%\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00170\u0015¢\u0006\u0002\b\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R1\u0010\u0019\u001a%\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00170\u0015¢\u0006\u0002\b\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001fR\u0011\u00100\u001a\u000201¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u001a\u00104\u001a\u00020\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\b5\u00106\u001a\u0004\b7\u0010\u001fR#\u00108\u001a\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020:0909j\u0002`;¢\u0006\n\n\u0002\u0010>\u001a\u0004\b<\u0010=R\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\u001fR\u001a\u0010@\u001a\u00020\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\bA\u00106\u001a\u0004\bB\u0010\u001fR\u001e\u0010C\u001a\u00020\u00162\u0006\u0010.\u001a\u00020\u0016@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bD\u0010E¨\u0006V"}, d2 = {"Lqrcode/QRCode;", "", "data", "", "squareSize", "", "canvasSize", "xOffset", "yOffset", "colorFn", "Lqrcode/color/QRCodeColorFunction;", "shapeFn", "Lqrcode/shape/QRCodeShapeFunction;", "graphicsFactory", "Lqrcode/render/QRCodeGraphicsFactory;", "errorCorrectionLevel", "Lqrcode/raw/ErrorCorrectionLevel;", "informationDensity", "maskPattern", "Lqrcode/raw/MaskPattern;", "doBefore", "Lkotlin/Function4;", "Lqrcode/render/QRCodeGraphics;", "", "Lkotlin/ExtensionFunctionType;", "doAfter", "<init>", "(Ljava/lang/String;IIIILqrcode/color/QRCodeColorFunction;Lqrcode/shape/QRCodeShapeFunction;Lqrcode/render/QRCodeGraphicsFactory;Lqrcode/raw/ErrorCorrectionLevel;ILqrcode/raw/MaskPattern;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function4;)V", "getData", "()Ljava/lang/String;", "getXOffset", "()I", "getYOffset", "getColorFn", "()Lqrcode/color/QRCodeColorFunction;", "getShapeFn", "()Lqrcode/shape/QRCodeShapeFunction;", "getGraphicsFactory", "()Lqrcode/render/QRCodeGraphicsFactory;", "setGraphicsFactory", "(Lqrcode/render/QRCodeGraphicsFactory;)V", "getErrorCorrectionLevel", "()Lqrcode/raw/ErrorCorrectionLevel;", "getInformationDensity", "getMaskPattern", "()Lqrcode/raw/MaskPattern;", ES6Iterator.VALUE_PROPERTY, "getSquareSize", "qrCodeProcessor", "Lqrcode/raw/QRCodeProcessor;", "getQrCodeProcessor", "()Lqrcode/raw/QRCodeProcessor;", "typeNum", "getTypeNum$annotations", "()V", "getTypeNum", "rawData", "", "Lqrcode/internals/QRCodeSquare;", "Lqrcode/raw/QRCodeRawData;", "getRawData", "()[[Lqrcode/internals/QRCodeSquare;", "[[Lqrcode/internals/QRCodeSquare;", "getCanvasSize", "computedSize", "getComputedSize$annotations", "getComputedSize", "graphics", "getGraphics", "()Lqrcode/render/QRCodeGraphics;", "draw", "canvas", "(II[[Lqrcode/internals/QRCodeSquare;Lqrcode/render/QRCodeGraphics;)Lqrcode/render/QRCodeGraphics;", "resize", ContentDisposition.Parameters.Size, "fitIntoArea", "width", "height", "render", "qrCodeGraphics", "renderToBytes", "", "format", "reset", "toString", "Companion", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class QRCode {
    public static final int DEFAULT_QRCODE_SIZE = 0;
    public static final int DEFAULT_SQUARE_SIZE = 25;
    public static final int DEFAULT_X_OFFSET = 0;
    public static final int DEFAULT_Y_OFFSET = 0;
    private int canvasSize;
    private final QRCodeColorFunction colorFn;
    private final String data;
    private final Function4<QRCode, QRCodeGraphics, Integer, Integer, Unit> doAfter;
    private final Function4<QRCode, QRCodeGraphics, Integer, Integer, Unit> doBefore;
    private final ErrorCorrectionLevel errorCorrectionLevel;
    private QRCodeGraphics graphics;
    private QRCodeGraphicsFactory graphicsFactory;
    private final int informationDensity;
    private final MaskPattern maskPattern;
    private final QRCodeProcessor qrCodeProcessor;
    private final QRCodeSquare[][] rawData;
    private final QRCodeShapeFunction shapeFn;
    private int squareSize;
    private final int xOffset;
    private final int yOffset;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Function4<QRCode, QRCodeGraphics, Integer, Integer, Unit> EMPTY_FN = new Function4() { // from class: qrcode.QRCode$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            return QRCode.EMPTY_FN$lambda$4((QRCode) obj, (QRCodeGraphics) obj2, ((Integer) obj3).intValue(), ((Integer) obj4).intValue());
        }
    };

    /* JADX INFO: compiled from: QRCode.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[QRCodeSquareType.values().length];
            try {
                iArr[QRCodeSquareType.POSITION_PROBE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[QRCodeSquareType.POSITION_ADJUST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QRCode(String data) {
        this(data, 0, 0, 0, 0, null, null, null, null, 0, null, null, null, 8190, null);
        Intrinsics.checkNotNullParameter(data, "data");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QRCode(String data, int i) {
        this(data, i, 0, 0, 0, null, null, null, null, 0, null, null, null, 8188, null);
        Intrinsics.checkNotNullParameter(data, "data");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QRCode(String data, int i, int i2) {
        this(data, i, i2, 0, 0, null, null, null, null, 0, null, null, null, 8184, null);
        Intrinsics.checkNotNullParameter(data, "data");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QRCode(String data, int i, int i2, int i3) {
        this(data, i, i2, i3, 0, null, null, null, null, 0, null, null, null, 8176, null);
        Intrinsics.checkNotNullParameter(data, "data");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QRCode(String data, int i, int i2, int i3, int i4) {
        this(data, i, i2, i3, i4, null, null, null, null, 0, null, null, null, 8160, null);
        Intrinsics.checkNotNullParameter(data, "data");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QRCode(String data, int i, int i2, int i3, int i4, QRCodeColorFunction colorFn) {
        this(data, i, i2, i3, i4, colorFn, null, null, null, 0, null, null, null, 8128, null);
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(colorFn, "colorFn");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QRCode(String data, int i, int i2, int i3, int i4, QRCodeColorFunction colorFn, QRCodeShapeFunction shapeFn) {
        this(data, i, i2, i3, i4, colorFn, shapeFn, null, null, 0, null, null, null, 8064, null);
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(colorFn, "colorFn");
        Intrinsics.checkNotNullParameter(shapeFn, "shapeFn");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QRCode(String data, int i, int i2, int i3, int i4, QRCodeColorFunction colorFn, QRCodeShapeFunction shapeFn, QRCodeGraphicsFactory graphicsFactory) {
        this(data, i, i2, i3, i4, colorFn, shapeFn, graphicsFactory, null, 0, null, null, null, 7936, null);
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(colorFn, "colorFn");
        Intrinsics.checkNotNullParameter(shapeFn, "shapeFn");
        Intrinsics.checkNotNullParameter(graphicsFactory, "graphicsFactory");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QRCode(String data, int i, int i2, int i3, int i4, QRCodeColorFunction colorFn, QRCodeShapeFunction shapeFn, QRCodeGraphicsFactory graphicsFactory, ErrorCorrectionLevel errorCorrectionLevel) {
        this(data, i, i2, i3, i4, colorFn, shapeFn, graphicsFactory, errorCorrectionLevel, 0, null, null, null, 7680, null);
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(colorFn, "colorFn");
        Intrinsics.checkNotNullParameter(shapeFn, "shapeFn");
        Intrinsics.checkNotNullParameter(graphicsFactory, "graphicsFactory");
        Intrinsics.checkNotNullParameter(errorCorrectionLevel, "errorCorrectionLevel");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QRCode(String data, int i, int i2, int i3, int i4, QRCodeColorFunction colorFn, QRCodeShapeFunction shapeFn, QRCodeGraphicsFactory graphicsFactory, ErrorCorrectionLevel errorCorrectionLevel, int i5) {
        this(data, i, i2, i3, i4, colorFn, shapeFn, graphicsFactory, errorCorrectionLevel, i5, null, null, null, 7168, null);
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(colorFn, "colorFn");
        Intrinsics.checkNotNullParameter(shapeFn, "shapeFn");
        Intrinsics.checkNotNullParameter(graphicsFactory, "graphicsFactory");
        Intrinsics.checkNotNullParameter(errorCorrectionLevel, "errorCorrectionLevel");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QRCode(String data, int i, int i2, int i3, int i4, QRCodeColorFunction colorFn, QRCodeShapeFunction shapeFn, QRCodeGraphicsFactory graphicsFactory, ErrorCorrectionLevel errorCorrectionLevel, int i5, MaskPattern maskPattern) {
        this(data, i, i2, i3, i4, colorFn, shapeFn, graphicsFactory, errorCorrectionLevel, i5, maskPattern, null, null, 6144, null);
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(colorFn, "colorFn");
        Intrinsics.checkNotNullParameter(shapeFn, "shapeFn");
        Intrinsics.checkNotNullParameter(graphicsFactory, "graphicsFactory");
        Intrinsics.checkNotNullParameter(errorCorrectionLevel, "errorCorrectionLevel");
        Intrinsics.checkNotNullParameter(maskPattern, "maskPattern");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QRCode(String data, int i, int i2, int i3, int i4, QRCodeColorFunction colorFn, QRCodeShapeFunction shapeFn, QRCodeGraphicsFactory graphicsFactory, ErrorCorrectionLevel errorCorrectionLevel, int i5, MaskPattern maskPattern, Function4<? super QRCode, ? super QRCodeGraphics, ? super Integer, ? super Integer, Unit> doBefore) {
        this(data, i, i2, i3, i4, colorFn, shapeFn, graphicsFactory, errorCorrectionLevel, i5, maskPattern, doBefore, null, 4096, null);
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(colorFn, "colorFn");
        Intrinsics.checkNotNullParameter(shapeFn, "shapeFn");
        Intrinsics.checkNotNullParameter(graphicsFactory, "graphicsFactory");
        Intrinsics.checkNotNullParameter(errorCorrectionLevel, "errorCorrectionLevel");
        Intrinsics.checkNotNullParameter(maskPattern, "maskPattern");
        Intrinsics.checkNotNullParameter(doBefore, "doBefore");
    }

    @Deprecated(message = "Please use canvasSize instead.")
    public static /* synthetic */ void getComputedSize$annotations() {
    }

    @Deprecated(message = "Please use informationDensity instead.")
    public static /* synthetic */ void getTypeNum$annotations() {
    }

    @JvmStatic
    public static final QRCodeBuilder ofCircles() {
        return INSTANCE.ofCircles();
    }

    @JvmStatic
    public static final QRCodeBuilder ofCustomShape(QRCodeShapeFunction qRCodeShapeFunction) {
        return INSTANCE.ofCustomShape(qRCodeShapeFunction);
    }

    @JvmStatic
    public static final QRCodeBuilder ofRoundedSquares() {
        return INSTANCE.ofRoundedSquares();
    }

    @JvmStatic
    public static final QRCodeBuilder ofSquares() {
        return INSTANCE.ofSquares();
    }

    public final QRCodeGraphics render() {
        return render$default(this, null, 0, 0, 7, null);
    }

    public final QRCodeGraphics render(QRCodeGraphics qrCodeGraphics) {
        Intrinsics.checkNotNullParameter(qrCodeGraphics, "qrCodeGraphics");
        return render$default(this, qrCodeGraphics, 0, 0, 6, null);
    }

    public final QRCodeGraphics render(QRCodeGraphics qrCodeGraphics, int i) {
        Intrinsics.checkNotNullParameter(qrCodeGraphics, "qrCodeGraphics");
        return render$default(this, qrCodeGraphics, i, 0, 4, null);
    }

    public final byte[] renderToBytes() {
        return renderToBytes$default(this, null, 0, 0, null, 15, null);
    }

    public final byte[] renderToBytes(QRCodeGraphics qrCodeGraphics) {
        Intrinsics.checkNotNullParameter(qrCodeGraphics, "qrCodeGraphics");
        return renderToBytes$default(this, qrCodeGraphics, 0, 0, null, 14, null);
    }

    public final byte[] renderToBytes(QRCodeGraphics qrCodeGraphics, int i) {
        Intrinsics.checkNotNullParameter(qrCodeGraphics, "qrCodeGraphics");
        return renderToBytes$default(this, qrCodeGraphics, i, 0, null, 12, null);
    }

    public final byte[] renderToBytes(QRCodeGraphics qrCodeGraphics, int i, int i2) {
        Intrinsics.checkNotNullParameter(qrCodeGraphics, "qrCodeGraphics");
        return renderToBytes$default(this, qrCodeGraphics, i, i2, null, 8, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public QRCode(String data, int i, int i2, int i3, int i4, QRCodeColorFunction colorFn, QRCodeShapeFunction shapeFn, QRCodeGraphicsFactory graphicsFactory, ErrorCorrectionLevel errorCorrectionLevel, int i5, MaskPattern maskPattern, Function4<? super QRCode, ? super QRCodeGraphics, ? super Integer, ? super Integer, Unit> doBefore, Function4<? super QRCode, ? super QRCodeGraphics, ? super Integer, ? super Integer, Unit> doAfter) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(colorFn, "colorFn");
        Intrinsics.checkNotNullParameter(shapeFn, "shapeFn");
        Intrinsics.checkNotNullParameter(graphicsFactory, "graphicsFactory");
        Intrinsics.checkNotNullParameter(errorCorrectionLevel, "errorCorrectionLevel");
        Intrinsics.checkNotNullParameter(maskPattern, "maskPattern");
        Intrinsics.checkNotNullParameter(doBefore, "doBefore");
        Intrinsics.checkNotNullParameter(doAfter, "doAfter");
        this.data = data;
        this.xOffset = i3;
        this.yOffset = i4;
        this.colorFn = colorFn;
        this.shapeFn = shapeFn;
        this.graphicsFactory = graphicsFactory;
        this.errorCorrectionLevel = errorCorrectionLevel;
        this.informationDensity = i5;
        this.maskPattern = maskPattern;
        this.doBefore = doBefore;
        this.doAfter = doAfter;
        this.squareSize = i;
        QRCodeProcessor qRCodeProcessor = new QRCodeProcessor(data, errorCorrectionLevel, null, this.graphicsFactory, 4, null);
        this.qrCodeProcessor = qRCodeProcessor;
        QRCodeSquare[][] qRCodeSquareArrEncode = qRCodeProcessor.encode(i5, maskPattern);
        this.rawData = qRCodeSquareArrEncode;
        i2 = i2 <= 0 ? qRCodeProcessor.computeImageSize(i, qRCodeSquareArrEncode) : i2;
        this.canvasSize = i2;
        this.graphics = this.graphicsFactory.newGraphicsSquare(i2);
    }

    public final String getData() {
        return this.data;
    }

    public final int getXOffset() {
        return this.xOffset;
    }

    public final int getYOffset() {
        return this.yOffset;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ QRCode(String str, int i, int i2, int i3, int i4, QRCodeColorFunction qRCodeColorFunction, QRCodeShapeFunction qRCodeShapeFunction, QRCodeGraphicsFactory qRCodeGraphicsFactory, ErrorCorrectionLevel errorCorrectionLevel, int i5, MaskPattern maskPattern, Function4 function4, Function4 function5, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        ErrorCorrectionLevel errorCorrectionLevel2;
        int iInfoDensityForDataAndECL$default;
        int i7 = (i6 & 2) != 0 ? 25 : i;
        int i8 = 0;
        int i9 = (i6 & 4) != 0 ? 0 : i2;
        int i10 = (i6 & 8) != 0 ? 0 : i3;
        int i11 = (i6 & 16) != 0 ? 0 : i4;
        DefaultColorFunction defaultColorFunction = (i6 & 32) != 0 ? new DefaultColorFunction(i8, i8, 3, null) : qRCodeColorFunction;
        DefaultShapeFunction defaultShapeFunction = (i6 & 64) != 0 ? new DefaultShapeFunction(i7, 0) : qRCodeShapeFunction;
        QRCodeGraphicsFactory qRCodeGraphicsFactory2 = (i6 & 128) != 0 ? new QRCodeGraphicsFactory() : qRCodeGraphicsFactory;
        ErrorCorrectionLevel errorCorrectionLevel3 = (i6 & 256) != 0 ? ErrorCorrectionLevel.LOW : errorCorrectionLevel;
        if ((i6 & 512) != 0) {
            ErrorCorrectionLevel errorCorrectionLevel4 = errorCorrectionLevel3;
            iInfoDensityForDataAndECL$default = QRCodeProcessor.Companion.infoDensityForDataAndECL$default(QRCodeProcessor.INSTANCE, str, errorCorrectionLevel4, null, 4, null);
            errorCorrectionLevel2 = errorCorrectionLevel4;
        } else {
            errorCorrectionLevel2 = errorCorrectionLevel3;
            iInfoDensityForDataAndECL$default = i5;
        }
        this(str, i7, i9, i10, i11, defaultColorFunction, defaultShapeFunction, qRCodeGraphicsFactory2, errorCorrectionLevel2, iInfoDensityForDataAndECL$default, (i6 & 1024) != 0 ? MaskPattern.PATTERN000 : maskPattern, (i6 & 2048) != 0 ? EMPTY_FN : function4, (i6 & 4096) != 0 ? EMPTY_FN : function5);
    }

    public final QRCodeColorFunction getColorFn() {
        return this.colorFn;
    }

    public final QRCodeShapeFunction getShapeFn() {
        return this.shapeFn;
    }

    public final QRCodeGraphicsFactory getGraphicsFactory() {
        return this.graphicsFactory;
    }

    public final void setGraphicsFactory(QRCodeGraphicsFactory qRCodeGraphicsFactory) {
        Intrinsics.checkNotNullParameter(qRCodeGraphicsFactory, "<set-?>");
        this.graphicsFactory = qRCodeGraphicsFactory;
    }

    public final ErrorCorrectionLevel getErrorCorrectionLevel() {
        return this.errorCorrectionLevel;
    }

    public final int getInformationDensity() {
        return this.informationDensity;
    }

    public final MaskPattern getMaskPattern() {
        return this.maskPattern;
    }

    /* JADX INFO: compiled from: QRCode.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0011\u001a\u00020\u0012H\u0007J\b\u0010\u0013\u001a\u00020\u0012H\u0007J\b\u0010\u0014\u001a\u00020\u0012H\u0007J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0007R7\u0010\u0004\u001a%\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0005¢\u0006\u0002\b\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lqrcode/QRCode$Companion;", "", "<init>", "()V", "EMPTY_FN", "Lkotlin/Function4;", "Lqrcode/QRCode;", "Lqrcode/render/QRCodeGraphics;", "", "", "Lkotlin/ExtensionFunctionType;", "getEMPTY_FN$qrcode_kotlin_release", "()Lkotlin/jvm/functions/Function4;", "DEFAULT_SQUARE_SIZE", "DEFAULT_QRCODE_SIZE", "DEFAULT_X_OFFSET", "DEFAULT_Y_OFFSET", "ofSquares", "Lqrcode/QRCodeBuilder;", "ofCircles", "ofRoundedSquares", "ofCustomShape", "customShapeFunction", "Lqrcode/shape/QRCodeShapeFunction;", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Function4<QRCode, QRCodeGraphics, Integer, Integer, Unit> getEMPTY_FN$qrcode_kotlin_release() {
            return QRCode.EMPTY_FN;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        public final QRCodeBuilder ofSquares() {
            return new QRCodeBuilder(QRCodeShapesEnum.SQUARE, null, 2, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        public final QRCodeBuilder ofCircles() {
            return new QRCodeBuilder(QRCodeShapesEnum.CIRCLE, null, 2, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        public final QRCodeBuilder ofRoundedSquares() {
            return new QRCodeBuilder(QRCodeShapesEnum.ROUNDED_SQUARE, null, 2, 0 == true ? 1 : 0);
        }

        @JvmStatic
        public final QRCodeBuilder ofCustomShape(QRCodeShapeFunction customShapeFunction) {
            Intrinsics.checkNotNullParameter(customShapeFunction, "customShapeFunction");
            return new QRCodeBuilder(QRCodeShapesEnum.CUSTOM, customShapeFunction);
        }
    }

    static final Unit EMPTY_FN$lambda$4(QRCode qRCode, QRCodeGraphics qRCodeGraphics, int i, int i2) {
        Intrinsics.checkNotNullParameter(qRCode, "<this>");
        Intrinsics.checkNotNullParameter(qRCodeGraphics, "<unused var>");
        return Unit.INSTANCE;
    }

    public final int getSquareSize() {
        return this.squareSize;
    }

    public final QRCodeProcessor getQrCodeProcessor() {
        return this.qrCodeProcessor;
    }

    public final int getTypeNum() {
        return this.informationDensity;
    }

    public final QRCodeSquare[][] getRawData() {
        return this.rawData;
    }

    public final int getCanvasSize() {
        return this.canvasSize;
    }

    public final int getComputedSize() {
        return this.canvasSize;
    }

    public final QRCodeGraphics getGraphics() {
        return this.graphics;
    }

    private final QRCodeGraphics draw(final int xOffset, final int yOffset, QRCodeSquare[][] rawData, final QRCodeGraphics canvas) {
        return this.qrCodeProcessor.renderShaded(this.squareSize, rawData, canvas, new Function4() { // from class: qrcode.QRCode$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return QRCode.draw$lambda$0(this.f$0, xOffset, yOffset, canvas, ((Integer) obj).intValue(), ((Integer) obj2).intValue(), (QRCodeSquare) obj3, (QRCodeGraphics) obj4);
            }
        });
    }

    static final Unit draw$lambda$0(QRCode qRCode, int i, int i2, QRCodeGraphics qRCodeGraphics, int i3, int i4, QRCodeSquare currentSquare, QRCodeGraphics qRCodeGraphics2) {
        QRCodeSquare qRCodeSquare;
        Intrinsics.checkNotNullParameter(currentSquare, "currentSquare");
        Intrinsics.checkNotNullParameter(qRCodeGraphics2, "<unused var>");
        QRCodeSquare parent = currentSquare.getParent();
        if (parent == null) {
            parent = currentSquare;
        }
        if (!parent.getRendered()) {
            int i5 = WhenMappings.$EnumSwitchMapping$0[currentSquare.getSquareInfo().getType().ordinal()];
            if (i5 == 1 || i5 == 2) {
                qRCodeSquare = parent;
                qRCode.shapeFn.renderControlSquare(i, i2, qRCode.colorFn, qRCodeSquare, qRCodeGraphics, qRCode);
            } else {
                qRCode.shapeFn.renderSquare(i + i3, i2 + i4, qRCode.colorFn, currentSquare, qRCodeGraphics, qRCode);
                qRCodeSquare = parent;
            }
            qRCodeSquare.setRendered(true);
        }
        return Unit.INSTANCE;
    }

    public final QRCode resize(int size) {
        this.canvasSize = size;
        this.graphics = this.graphicsFactory.newGraphicsSquare(size);
        return this;
    }

    public final QRCode fitIntoArea(int width, int height) {
        int iMin = Math.min(width, height);
        int iFloor = (int) Math.floor(((double) iMin) / ((double) this.rawData.length));
        this.squareSize = iFloor;
        this.shapeFn.resize(iFloor);
        this.canvasSize = iMin;
        this.graphics = this.graphicsFactory.newGraphicsSquare(iMin);
        return this;
    }

    public static /* synthetic */ QRCodeGraphics render$default(QRCode qRCode, QRCodeGraphics qRCodeGraphics, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            qRCodeGraphics = qRCode.graphics;
        }
        if ((i3 & 2) != 0) {
            i = qRCode.xOffset;
        }
        if ((i3 & 4) != 0) {
            i2 = qRCode.yOffset;
        }
        return qRCode.render(qRCodeGraphics, i, i2);
    }

    public final QRCodeGraphics render(QRCodeGraphics qrCodeGraphics, int xOffset, int yOffset) {
        Intrinsics.checkNotNullParameter(qrCodeGraphics, "qrCodeGraphics");
        this.colorFn.beforeRender(this, qrCodeGraphics);
        this.shapeFn.beforeRender(this, qrCodeGraphics);
        QRCodeGraphics qRCodeGraphics = this.graphics;
        qRCodeGraphics.fill(this.colorFn.bg(0, 0, this, qRCodeGraphics));
        this.doBefore.invoke(this, qrCodeGraphics, Integer.valueOf(xOffset), Integer.valueOf(yOffset));
        QRCodeGraphics qRCodeGraphicsDraw = draw(xOffset, yOffset, this.rawData, qrCodeGraphics);
        this.doAfter.invoke(this, qRCodeGraphicsDraw, Integer.valueOf(xOffset), Integer.valueOf(yOffset));
        return qRCodeGraphicsDraw;
    }

    public static /* synthetic */ byte[] renderToBytes$default(QRCode qRCode, QRCodeGraphics qRCodeGraphics, int i, int i2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            qRCodeGraphics = qRCode.graphics;
        }
        if ((i3 & 2) != 0) {
            i = qRCode.xOffset;
        }
        if ((i3 & 4) != 0) {
            i2 = qRCode.yOffset;
        }
        if ((i3 & 8) != 0) {
            str = "PNG";
        }
        return qRCode.renderToBytes(qRCodeGraphics, i, i2, str);
    }

    public final byte[] renderToBytes(QRCodeGraphics qrCodeGraphics, int xOffset, int yOffset, String format) {
        Intrinsics.checkNotNullParameter(qrCodeGraphics, "qrCodeGraphics");
        Intrinsics.checkNotNullParameter(format, "format");
        return render(qrCodeGraphics, xOffset, yOffset).getBytes(format);
    }

    public final void reset() {
        for (QRCodeSquare[] qRCodeSquareArr : this.rawData) {
            for (QRCodeSquare qRCodeSquare : qRCodeSquareArr) {
                qRCodeSquare.setRendered(false);
                QRCodeSquare parent = qRCodeSquare.getParent();
                if (parent != null) {
                    parent.setRendered(false);
                }
            }
        }
        this.graphics.reset();
    }

    public String toString() {
        return "QRCode(data=" + this.data + ", squareSize=" + this.squareSize + ", canvasSize=" + this.canvasSize + ", xOffset=" + this.xOffset + ", yOffset=" + this.yOffset + ", errorCorrectionLevel=" + this.errorCorrectionLevel + ", informationDensity=" + this.informationDensity + ", maskPattern=" + this.maskPattern + ')';
    }
}
