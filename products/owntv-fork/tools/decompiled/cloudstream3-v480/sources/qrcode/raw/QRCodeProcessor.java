package qrcode.raw;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import qrcode.exception.InsufficientInformationDensityException;
import qrcode.internals.BitBuffer;
import qrcode.internals.Polynomial;
import qrcode.internals.QR8BitByte;
import qrcode.internals.QRAlphaNum;
import qrcode.internals.QRCodeSetup;
import qrcode.internals.QRCodeSquare;
import qrcode.internals.QRData;
import qrcode.internals.QRNumber;
import qrcode.internals.QRUtil;
import qrcode.internals.RSBlock;
import qrcode.render.QRCodeGraphics;
import qrcode.render.QRCodeGraphicsFactory;

/* JADX INFO: compiled from: QRCodeProcessor.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 22\u00020\u0001:\u00012B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ1\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\u0018\b\u0002\u0010\u0013\u001a\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0014j\u0002`\u0016H\u0007¢\u0006\u0002\u0010\u0017J\u0018\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0011J$\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u001b\u001a\u00020\u00112\b\b\u0002\u0010\u001c\u001a\u00020\u0011JO\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u0012\u001a\u00020\u00112\u0018\b\u0002\u0010\u0013\u001a\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0014j\u0002`\u00162\b\b\u0002\u0010\u001d\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00112\b\b\u0002\u0010\u001c\u001a\u00020\u0011H\u0007¢\u0006\u0002\u0010\u001eJa\u0010\u001f\u001a\u00020\u001a2\b\b\u0002\u0010\u0012\u001a\u00020\u00112\u0018\b\u0002\u0010\u0013\u001a\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0014j\u0002`\u00162\b\b\u0002\u0010\u001d\u001a\u00020\u001a2$\u0010 \u001a \u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\"0!H\u0007¢\u0006\u0002\u0010#J1\u0010$\u001a\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0014j\u0002`\u00162\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010&\u001a\u00020'H\u0007¢\u0006\u0002\u0010(J\u0010\u0010)\u001a\u00020*2\u0006\u0010%\u001a\u00020\u0011H\u0002J#\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020-2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u0014H\u0002¢\u0006\u0002\u00100J\b\u00101\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lqrcode/raw/QRCodeProcessor;", "", "data", "", "errorCorrectionLevel", "Lqrcode/raw/ErrorCorrectionLevel;", "dataType", "Lqrcode/raw/QRCodeDataType;", "graphicsFactory", "Lqrcode/render/QRCodeGraphicsFactory;", "<init>", "(Ljava/lang/String;Lqrcode/raw/ErrorCorrectionLevel;Lqrcode/raw/QRCodeDataType;Lqrcode/render/QRCodeGraphicsFactory;)V", "getGraphicsFactory", "()Lqrcode/render/QRCodeGraphicsFactory;", "qrCodeData", "Lqrcode/internals/QRData;", "computeImageSize", "", "cellSize", "rawData", "", "Lqrcode/internals/QRCodeSquare;", "Lqrcode/raw/QRCodeRawData;", "(I[[Lqrcode/internals/QRCodeSquare;)I", ContentDisposition.Parameters.Size, "render", "Lqrcode/render/QRCodeGraphics;", "brightColor", "darkColor", "qrCodeGraphics", "(I[[Lqrcode/internals/QRCodeSquare;Lqrcode/render/QRCodeGraphics;II)Lqrcode/render/QRCodeGraphics;", "renderShaded", "renderer", "Lkotlin/Function4;", "", "(I[[Lqrcode/internals/QRCodeSquare;Lqrcode/render/QRCodeGraphics;Lkotlin/jvm/functions/Function4;)Lqrcode/render/QRCodeGraphics;", "encode", "type", "maskPattern", "Lqrcode/raw/MaskPattern;", "(ILqrcode/raw/MaskPattern;)[[Lqrcode/internals/QRCodeSquare;", "createData", "", "createBytes", "buffer", "Lqrcode/internals/BitBuffer;", "rsBlocks", "Lqrcode/internals/RSBlock;", "(Lqrcode/internals/BitBuffer;[Lqrcode/internals/RSBlock;)[I", "toString", "Companion", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class QRCodeProcessor {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int DEFAULT_CELL_SIZE = 25;
    public static final int MAXIMUM_INFO_DENSITY = 40;
    private static final int PAD0 = 236;
    private static final int PAD1 = 17;
    private final String data;
    private final QRCodeDataType dataType;
    private final ErrorCorrectionLevel errorCorrectionLevel;
    private final QRCodeGraphicsFactory graphicsFactory;
    private final QRData qrCodeData;

    /* JADX INFO: compiled from: QRCodeProcessor.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[QRCodeDataType.values().length];
            try {
                iArr[QRCodeDataType.NUMBERS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[QRCodeDataType.UPPER_ALPHA_NUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[QRCodeDataType.DEFAULT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QRCodeProcessor(String data) {
        this(data, null, null, null, 14, null);
        Intrinsics.checkNotNullParameter(data, "data");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QRCodeProcessor(String data, ErrorCorrectionLevel errorCorrectionLevel) {
        this(data, errorCorrectionLevel, null, null, 12, null);
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(errorCorrectionLevel, "errorCorrectionLevel");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QRCodeProcessor(String data, ErrorCorrectionLevel errorCorrectionLevel, QRCodeDataType dataType) {
        this(data, errorCorrectionLevel, dataType, null, 8, null);
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(errorCorrectionLevel, "errorCorrectionLevel");
        Intrinsics.checkNotNullParameter(dataType, "dataType");
    }

    @JvmStatic
    public static final int infoDensityForDataAndECL(String str, ErrorCorrectionLevel errorCorrectionLevel) {
        return INSTANCE.infoDensityForDataAndECL(str, errorCorrectionLevel);
    }

    @JvmStatic
    public static final int infoDensityForDataAndECL(String str, ErrorCorrectionLevel errorCorrectionLevel, QRCodeDataType qRCodeDataType) {
        return INSTANCE.infoDensityForDataAndECL(str, errorCorrectionLevel, qRCodeDataType);
    }

    public final int computeImageSize(int cellSize, int size) {
        return size * cellSize;
    }

    public final QRCodeSquare[][] encode() {
        return encode$default(this, 0, null, 3, null);
    }

    public final QRCodeSquare[][] encode(int i) {
        return encode$default(this, i, null, 2, null);
    }

    public final QRCodeGraphics render() {
        return render$default(this, 0, null, null, 0, 0, 31, null);
    }

    public final QRCodeGraphics render(int i) {
        return render$default(this, i, null, null, 0, 0, 30, null);
    }

    public final QRCodeGraphics render(int i, QRCodeSquare[][] rawData) {
        Intrinsics.checkNotNullParameter(rawData, "rawData");
        return render$default(this, i, rawData, null, 0, 0, 28, null);
    }

    public final QRCodeGraphics render(int i, QRCodeSquare[][] rawData, QRCodeGraphics qrCodeGraphics) {
        Intrinsics.checkNotNullParameter(rawData, "rawData");
        Intrinsics.checkNotNullParameter(qrCodeGraphics, "qrCodeGraphics");
        return render$default(this, i, rawData, qrCodeGraphics, 0, 0, 24, null);
    }

    public final QRCodeGraphics render(int i, QRCodeSquare[][] rawData, QRCodeGraphics qrCodeGraphics, int i2) {
        Intrinsics.checkNotNullParameter(rawData, "rawData");
        Intrinsics.checkNotNullParameter(qrCodeGraphics, "qrCodeGraphics");
        return render$default(this, i, rawData, qrCodeGraphics, i2, 0, 16, null);
    }

    public final QRCodeGraphics renderShaded(int i, Function4<? super Integer, ? super Integer, ? super QRCodeSquare, ? super QRCodeGraphics, Unit> renderer) {
        Intrinsics.checkNotNullParameter(renderer, "renderer");
        return renderShaded$default(this, i, null, null, renderer, 6, null);
    }

    public final QRCodeGraphics renderShaded(int i, QRCodeSquare[][] rawData, Function4<? super Integer, ? super Integer, ? super QRCodeSquare, ? super QRCodeGraphics, Unit> renderer) {
        Intrinsics.checkNotNullParameter(rawData, "rawData");
        Intrinsics.checkNotNullParameter(renderer, "renderer");
        return renderShaded$default(this, i, rawData, null, renderer, 4, null);
    }

    public final QRCodeGraphics renderShaded(Function4<? super Integer, ? super Integer, ? super QRCodeSquare, ? super QRCodeGraphics, Unit> renderer) {
        Intrinsics.checkNotNullParameter(renderer, "renderer");
        return renderShaded$default(this, 0, null, null, renderer, 7, null);
    }

    public QRCodeProcessor(String data, ErrorCorrectionLevel errorCorrectionLevel, QRCodeDataType dataType, QRCodeGraphicsFactory graphicsFactory) {
        QRNumber qRNumber;
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(errorCorrectionLevel, "errorCorrectionLevel");
        Intrinsics.checkNotNullParameter(dataType, "dataType");
        Intrinsics.checkNotNullParameter(graphicsFactory, "graphicsFactory");
        this.data = data;
        this.errorCorrectionLevel = errorCorrectionLevel;
        this.dataType = dataType;
        this.graphicsFactory = graphicsFactory;
        int i = WhenMappings.$EnumSwitchMapping$0[dataType.ordinal()];
        if (i == 1) {
            qRNumber = new QRNumber(data);
        } else if (i == 2) {
            qRNumber = new QRAlphaNum(data);
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            qRNumber = new QR8BitByte(data);
        }
        this.qrCodeData = qRNumber;
    }

    public /* synthetic */ QRCodeProcessor(String str, ErrorCorrectionLevel errorCorrectionLevel, QRCodeDataType qRCodeDataType, QRCodeGraphicsFactory qRCodeGraphicsFactory, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? ErrorCorrectionLevel.MEDIUM : errorCorrectionLevel, (i & 4) != 0 ? QRUtil.INSTANCE.getDataType(str) : qRCodeDataType, (i & 8) != 0 ? new QRCodeGraphicsFactory() : qRCodeGraphicsFactory);
    }

    public final QRCodeGraphicsFactory getGraphicsFactory() {
        return this.graphicsFactory;
    }

    /* JADX INFO: compiled from: QRCodeProcessor.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lqrcode/raw/QRCodeProcessor$Companion;", "", "<init>", "()V", "DEFAULT_CELL_SIZE", "", "PAD0", "PAD1", "MAXIMUM_INFO_DENSITY", "infoDensityForDataAndECL", "data", "", "errorCorrectionLevel", "Lqrcode/raw/ErrorCorrectionLevel;", "dataType", "Lqrcode/raw/QRCodeDataType;", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: QRCodeProcessor.kt */
        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[QRCodeDataType.values().length];
                try {
                    iArr[QRCodeDataType.NUMBERS.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[QRCodeDataType.UPPER_ALPHA_NUM.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[QRCodeDataType.DEFAULT.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final int infoDensityForDataAndECL(String data, ErrorCorrectionLevel errorCorrectionLevel) {
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(errorCorrectionLevel, "errorCorrectionLevel");
            return infoDensityForDataAndECL$default(this, data, errorCorrectionLevel, null, 4, null);
        }

        private Companion() {
        }

        public static /* synthetic */ int infoDensityForDataAndECL$default(Companion companion, String str, ErrorCorrectionLevel errorCorrectionLevel, QRCodeDataType qRCodeDataType, int i, Object obj) {
            if ((i & 4) != 0) {
                qRCodeDataType = QRUtil.INSTANCE.getDataType(str);
            }
            return companion.infoDensityForDataAndECL(str, errorCorrectionLevel, qRCodeDataType);
        }

        @JvmStatic
        public final int infoDensityForDataAndECL(String data, ErrorCorrectionLevel errorCorrectionLevel, QRCodeDataType dataType) {
            QRNumber qRNumber;
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(errorCorrectionLevel, "errorCorrectionLevel");
            Intrinsics.checkNotNullParameter(dataType, "dataType");
            int i = WhenMappings.$EnumSwitchMapping$0[dataType.ordinal()];
            if (i == 1) {
                qRNumber = new QRNumber(data);
            } else if (i == 2) {
                qRNumber = new QRAlphaNum(data);
            } else {
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                qRNumber = new QR8BitByte(data);
            }
            int length = qRNumber.length();
            int maxTypeNum = errorCorrectionLevel.getMaxTypeNum();
            for (int i2 = 1; i2 < maxTypeNum; i2++) {
                if (length <= QRUtil.INSTANCE.getMaxLength(i2, dataType, errorCorrectionLevel)) {
                    return i2;
                }
            }
            return 40;
        }
    }

    public static /* synthetic */ int computeImageSize$default(QRCodeProcessor qRCodeProcessor, int i, QRCodeSquare[][] qRCodeSquareArr, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 25;
        }
        if ((i2 & 2) != 0) {
            qRCodeSquareArr = encode$default(qRCodeProcessor, 0, null, 3, null);
        }
        return qRCodeProcessor.computeImageSize(i, qRCodeSquareArr);
    }

    public final int computeImageSize(int cellSize, QRCodeSquare[][] rawData) {
        Intrinsics.checkNotNullParameter(rawData, "rawData");
        return computeImageSize(cellSize, rawData.length);
    }

    public static /* synthetic */ int computeImageSize$default(QRCodeProcessor qRCodeProcessor, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 25;
        }
        return qRCodeProcessor.computeImageSize(i, i2);
    }

    public static /* synthetic */ QRCodeGraphics render$default(QRCodeProcessor qRCodeProcessor, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = 25;
        }
        if ((i4 & 2) != 0) {
            i2 = -1;
        }
        if ((i4 & 4) != 0) {
            i3 = -16777216;
        }
        return qRCodeProcessor.render(i, i2, i3);
    }

    public final QRCodeGraphics render(int cellSize, int brightColor, int darkColor) {
        return render$default(this, cellSize, encode$default(this, 0, null, 3, null), null, brightColor, darkColor, 4, null);
    }

    public static /* synthetic */ QRCodeGraphics render$default(QRCodeProcessor qRCodeProcessor, int i, QRCodeSquare[][] qRCodeSquareArr, QRCodeGraphics qRCodeGraphics, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = 25;
        }
        if ((i4 & 2) != 0) {
            qRCodeSquareArr = encode$default(qRCodeProcessor, 0, null, 3, null);
        }
        if ((i4 & 4) != 0) {
            qRCodeGraphics = qRCodeProcessor.graphicsFactory.newGraphicsSquare(qRCodeProcessor.computeImageSize(i, qRCodeSquareArr));
        }
        if ((i4 & 8) != 0) {
            i2 = -1;
        }
        if ((i4 & 16) != 0) {
            i3 = -16777216;
        }
        int i5 = i3;
        QRCodeGraphics qRCodeGraphics2 = qRCodeGraphics;
        return qRCodeProcessor.render(i, qRCodeSquareArr, qRCodeGraphics2, i2, i5);
    }

    public final QRCodeGraphics render(final int cellSize, QRCodeSquare[][] rawData, QRCodeGraphics qrCodeGraphics, final int brightColor, final int darkColor) {
        Intrinsics.checkNotNullParameter(rawData, "rawData");
        Intrinsics.checkNotNullParameter(qrCodeGraphics, "qrCodeGraphics");
        return renderShaded(cellSize, rawData, qrCodeGraphics, new Function4() { // from class: qrcode.raw.QRCodeProcessor$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return QRCodeProcessor.render$lambda$0(cellSize, darkColor, brightColor, ((Integer) obj).intValue(), ((Integer) obj2).intValue(), (QRCodeSquare) obj3, (QRCodeGraphics) obj4);
            }
        });
    }

    static final Unit render$lambda$0(int i, int i2, int i3, int i4, int i5, QRCodeSquare cellData, QRCodeGraphics graphics) {
        Intrinsics.checkNotNullParameter(cellData, "cellData");
        Intrinsics.checkNotNullParameter(graphics, "graphics");
        if (cellData.getDark()) {
            graphics.fillRect(i4, i5, i, i, i2);
        } else {
            graphics.fillRect(i4, i5, i, i, i3);
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ QRCodeGraphics renderShaded$default(QRCodeProcessor qRCodeProcessor, int i, QRCodeSquare[][] qRCodeSquareArr, QRCodeGraphics qRCodeGraphics, Function4 function4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 25;
        }
        if ((i2 & 2) != 0) {
            qRCodeSquareArr = encode$default(qRCodeProcessor, 0, null, 3, null);
        }
        if ((i2 & 4) != 0) {
            qRCodeGraphics = qRCodeProcessor.graphicsFactory.newGraphicsSquare(qRCodeProcessor.computeImageSize(i, qRCodeSquareArr));
        }
        return qRCodeProcessor.renderShaded(i, qRCodeSquareArr, qRCodeGraphics, function4);
    }

    public final QRCodeGraphics renderShaded(int cellSize, QRCodeSquare[][] rawData, QRCodeGraphics qrCodeGraphics, Function4<? super Integer, ? super Integer, ? super QRCodeSquare, ? super QRCodeGraphics, Unit> renderer) {
        Intrinsics.checkNotNullParameter(rawData, "rawData");
        Intrinsics.checkNotNullParameter(qrCodeGraphics, "qrCodeGraphics");
        Intrinsics.checkNotNullParameter(renderer, "renderer");
        for (QRCodeSquare[] qRCodeSquareArr : rawData) {
            for (QRCodeSquare qRCodeSquare : qRCodeSquareArr) {
                if (!qRCodeSquare.getRendered()) {
                    renderer.invoke(Integer.valueOf(qRCodeSquare.absoluteX(cellSize)), Integer.valueOf(qRCodeSquare.absoluteY(cellSize)), qRCodeSquare, qrCodeGraphics);
                    qRCodeSquare.setRendered(true);
                }
            }
        }
        return qrCodeGraphics;
    }

    public static /* synthetic */ QRCodeSquare[][] encode$default(QRCodeProcessor qRCodeProcessor, int i, MaskPattern maskPattern, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Companion.infoDensityForDataAndECL$default(INSTANCE, qRCodeProcessor.data, qRCodeProcessor.errorCorrectionLevel, null, 4, null);
        }
        if ((i2 & 2) != 0) {
            maskPattern = MaskPattern.PATTERN000;
        }
        return qRCodeProcessor.encode(i, maskPattern);
    }

    public final QRCodeSquare[][] encode(int type, MaskPattern maskPattern) {
        Intrinsics.checkNotNullParameter(maskPattern, "maskPattern");
        int i = (type * 4) + 17;
        QRCodeSquare[][] qRCodeSquareArr = new QRCodeSquare[i][];
        for (int i2 = 0; i2 < i; i2++) {
            QRCodeSquare[] qRCodeSquareArr2 = new QRCodeSquare[i];
            for (int i3 = 0; i3 < i; i3++) {
                qRCodeSquareArr2[i3] = null;
            }
            qRCodeSquareArr[i2] = qRCodeSquareArr2;
        }
        QRCodeSetup.setupTopLeftPositionProbePattern$default(QRCodeSetup.INSTANCE, qRCodeSquareArr, 0, 2, null);
        QRCodeSetup.setupTopRightPositionProbePattern$default(QRCodeSetup.INSTANCE, qRCodeSquareArr, 0, 2, null);
        QRCodeSetup.setupBottomLeftPositionProbePattern$default(QRCodeSetup.INSTANCE, qRCodeSquareArr, 0, 2, null);
        QRCodeSetup.INSTANCE.setupPositionAdjustPattern(type, qRCodeSquareArr);
        QRCodeSetup.INSTANCE.setupTimingPattern(i, qRCodeSquareArr);
        QRCodeSetup.INSTANCE.setupTypeInfo(this.errorCorrectionLevel, maskPattern, i, qRCodeSquareArr);
        if (type >= 7) {
            QRCodeSetup.INSTANCE.setupTypeNumber(type, i, qRCodeSquareArr);
        }
        QRCodeSetup.INSTANCE.applyMaskPattern(createData(type), maskPattern, i, qRCodeSquareArr);
        QRCodeSquare[][] qRCodeSquareArr3 = new QRCodeSquare[i][];
        for (int i4 = 0; i4 < i; i4++) {
            QRCodeSquare[] qRCodeSquareArr4 = new QRCodeSquare[i];
            for (int i5 = 0; i5 < i; i5++) {
                QRCodeSquare qRCodeSquare = qRCodeSquareArr[i4][i5];
                if (qRCodeSquare == null) {
                    qRCodeSquare = new QRCodeSquare(false, i4, i5, i, null, 0, 0, null, 240, null);
                }
                qRCodeSquareArr4[i5] = qRCodeSquare;
            }
            qRCodeSquareArr3[i4] = qRCodeSquareArr4;
        }
        return qRCodeSquareArr3;
    }

    private final int[] createData(int type) {
        RSBlock[] rSBlocks = RSBlock.INSTANCE.getRSBlocks(type, this.errorCorrectionLevel);
        BitBuffer bitBuffer = new BitBuffer();
        bitBuffer.put(this.qrCodeData.getDataType().getValue(), 4);
        bitBuffer.put(this.qrCodeData.length(), this.qrCodeData.getLengthInBits(type));
        this.qrCodeData.write(bitBuffer);
        int dataCount = 0;
        for (RSBlock rSBlock : rSBlocks) {
            dataCount += rSBlock.getDataCount();
        }
        int i = dataCount * 8;
        if (bitBuffer.getLengthInBits() > i) {
            throw new InsufficientInformationDensityException("Insufficient Information Density Parameter: " + type + " [neededBits=" + bitBuffer.getLengthInBits() + ", maximumBitsForDensityLevel=" + i + "] - Try increasing the Information Density parameter value or use 0 (zero) to automatically compute the least amount needed to fit the QRCode data being encoded.", null, 2, null);
        }
        if (bitBuffer.getLengthInBits() + 4 <= i) {
            bitBuffer.put(0, 4);
        }
        while (bitBuffer.getLengthInBits() % 8 != 0) {
            bitBuffer.put(false);
        }
        while (bitBuffer.getLengthInBits() < i) {
            bitBuffer.put(PAD0, 8);
            if (bitBuffer.getLengthInBits() >= i) {
                break;
            }
            bitBuffer.put(17, 8);
        }
        return createBytes(bitBuffer, rSBlocks);
    }

    private final int[] createBytes(BitBuffer buffer, RSBlock[] rsBlocks) {
        int length = rsBlocks.length;
        int[][] iArr = new int[length][];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = new int[0];
        }
        int length2 = rsBlocks.length;
        int[][] iArr2 = new int[length2][];
        for (int i3 = 0; i3 < length2; i3++) {
            iArr2[i3] = new int[0];
        }
        int length3 = rsBlocks.length;
        int i4 = 0;
        int totalCount = 0;
        int iCoerceAtLeast = 0;
        int i5 = 0;
        int iCoerceAtLeast2 = 0;
        int i6 = 0;
        while (i4 < length3) {
            RSBlock rSBlock = rsBlocks[i4];
            int i7 = i5 + 1;
            int dataCount = rSBlock.getDataCount();
            int totalCount2 = rSBlock.getTotalCount() - dataCount;
            totalCount += rSBlock.getTotalCount();
            iCoerceAtLeast = RangesKt.coerceAtLeast(iCoerceAtLeast, dataCount);
            iCoerceAtLeast2 = RangesKt.coerceAtLeast(iCoerceAtLeast2, totalCount2);
            int[] iArr3 = new int[dataCount];
            for (int i8 = i; i8 < dataCount; i8++) {
                iArr3[i8] = buffer.getBuffer()[i8 + i6] & 255;
            }
            iArr[i5] = iArr3;
            i6 += dataCount;
            Polynomial errorCorrectPolynomial = QRUtil.INSTANCE.getErrorCorrectPolynomial(totalCount2);
            Polynomial polynomialMod = new Polynomial(iArr[i5], errorCorrectPolynomial.len() - 1).mod(errorCorrectPolynomial);
            int iLen = errorCorrectPolynomial.len() - 1;
            int[] iArr4 = new int[iLen];
            for (int i9 = 0; i9 < iLen; i9++) {
                int iLen2 = (polynomialMod.len() + i9) - iLen;
                iArr4[i9] = iLen2 >= 0 ? polynomialMod.get(iLen2) : 0;
            }
            iArr2[i5] = iArr4;
            i4++;
            i5 = i7;
            i = 0;
        }
        int[] iArr5 = new int[totalCount];
        int i10 = 0;
        for (int i11 = 0; i11 < iCoerceAtLeast; i11++) {
            int length4 = rsBlocks.length;
            for (int i12 = 0; i12 < length4; i12++) {
                int[] iArr6 = iArr[i12];
                if (i11 < iArr6.length) {
                    iArr5[i10] = iArr6[i11];
                    i10++;
                }
            }
        }
        for (int i13 = 0; i13 < iCoerceAtLeast2; i13++) {
            int length5 = rsBlocks.length;
            for (int i14 = 0; i14 < length5; i14++) {
                int[] iArr7 = iArr2[i14];
                if (i13 < iArr7.length) {
                    iArr5[i10] = iArr7[i13];
                    i10++;
                }
            }
        }
        return iArr5;
    }

    public String toString() {
        return "QRCode(data=" + this.data + ", errorCorrectionLevel=" + this.errorCorrectionLevel + ", dataType=" + this.dataType + ", qrCodeData=" + Reflection.getOrCreateKotlinClass(this.qrCodeData.getClass()).getSimpleName() + ')';
    }
}
