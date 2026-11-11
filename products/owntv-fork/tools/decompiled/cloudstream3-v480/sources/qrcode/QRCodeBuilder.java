package qrcode;

import androidx.tvprovider.media.tv.TvContractCompat;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import qrcode.color.DefaultColorFunction;
import qrcode.color.LinearGradientColorFunction;
import qrcode.color.QRCodeColorFunction;
import qrcode.internals.QRCodeSquare;
import qrcode.internals.QRMath;
import qrcode.raw.ErrorCorrectionLevel;
import qrcode.raw.MaskPattern;
import qrcode.raw.QRCodeProcessor;
import qrcode.render.QRCodeGraphics;
import qrcode.render.QRCodeGraphicsFactory;
import qrcode.shape.CircleShapeFunction;
import qrcode.shape.DefaultShapeFunction;
import qrcode.shape.QRCodeShapeFunction;
import qrcode.shape.RoundSquaresShapeFunction;

/* JADX INFO: compiled from: QRCodeBuilder.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0012\u001a\u00020\u000bH\u0002J\u000e\u0010(\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u000bJ\u000e\u0010+\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000bJ\u000e\u0010,\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\u000bJ)\u0010.\u001a\u00020\u00002\u0006\u0010/\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007¢\u0006\u0002\u00100J\u000e\u00101\u001a\u00020\u00002\u0006\u00102\u001a\u00020\u000bJ\u0019\u00103\u001a\u00020\u00002\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u000bH\u0007¢\u0006\u0002\u00105J,\u00106\u001a\u00020\u00002\b\u00107\u001a\u0004\u0018\u0001082\u0006\u00109\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020\u000b2\b\b\u0002\u0010;\u001a\u00020\u0010H\u0007J%\u0010<\u001a\u00020\u00002\u001d\u0010=\u001a\u0019\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180>¢\u0006\u0002\b\u0019J%\u0010?\u001a\u00020\u00002\u001d\u0010=\u001a\u0019\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180>¢\u0006\u0002\b\u0019J\u000e\u0010@\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\u001eJ\u0010\u0010B\u001a\u00020\u00002\b\u0010C\u001a\u0004\u0018\u00010\tJ\u0010\u0010D\u001a\u00020\u00002\b\u0010E\u001a\u0004\u0018\u00010\u0005J\u000e\u0010F\u001a\u00020\u00002\u0006\u0010G\u001a\u00020 J\u000e\u0010H\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u000bJ\u000e\u0010I\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020#J\u000e\u0010J\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u000bJ\u000e\u0010K\u001a\u00020\u00002\u0006\u0010%\u001a\u00020\u000bJ\u000e\u0010L\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u000bJ\u000e\u0010M\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\u000bJ\u000e\u0010V\u001a\u00020\u00162\u0006\u0010W\u001a\u00020XR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R1\u0010\u0014\u001a%\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00180\u0015¢\u0006\u0002\b\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R1\u0010\u001a\u001a%\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00180\u0015¢\u0006\u0002\b\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R1\u0010\u001b\u001a%\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00180\u0015¢\u0006\u0002\b\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R1\u0010\u001c\u001a%\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00180\u0015¢\u0006\u0002\b\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R7\u0010N\u001a%\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00180\u0015¢\u0006\u0002\b\u00198BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bO\u0010PR7\u0010Q\u001a%\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00180\u0015¢\u0006\u0002\b\u00198BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bR\u0010PR\u0014\u0010S\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bT\u0010U¨\u0006Y"}, d2 = {"Lqrcode/QRCodeBuilder;", "", "shape", "Lqrcode/QRCodeShapesEnum;", "customShapeFunction", "Lqrcode/shape/QRCodeShapeFunction;", "<init>", "(Lqrcode/QRCodeShapesEnum;Lqrcode/shape/QRCodeShapeFunction;)V", "customColorFunction", "Lqrcode/color/QRCodeColorFunction;", "squareSize", "", "color", "endColor", "Ljava/lang/Integer;", "vertical", "", "background", "innerSpace", "radiusInPixels", "drawLogoAction", "Lkotlin/Function4;", "Lqrcode/QRCode;", "Lqrcode/render/QRCodeGraphics;", "", "Lkotlin/ExtensionFunctionType;", "drawLogoBeforeAction", "userDoAfter", "userDoBefore", "graphicsFactory", "Lqrcode/render/QRCodeGraphicsFactory;", "errorCorrectionLevel", "Lqrcode/raw/ErrorCorrectionLevel;", "informationDensity", "maskPattern", "Lqrcode/raw/MaskPattern;", "canvasSize", "xOffset", "yOffset", "margin", "withShape", "withSize", ContentDisposition.Parameters.Size, "withColor", "withBackgroundColor", "bgColor", "withGradientColor", "startColor", "(ILjava/lang/Integer;Z)Lqrcode/QRCodeBuilder;", "withRadius", "radius", "withInnerSpacing", "innerSpacing", "(Ljava/lang/Integer;)Lqrcode/QRCodeBuilder;", "withLogo", TvContractCompat.Channels.Logo.CONTENT_DIRECTORY, "", "width", "height", "clearLogoArea", "withAfterRenderAction", "action", "Lkotlin/Function2;", "withBeforeRenderAction", "withGraphicsFactory", "factory", "withCustomColorFunction", "colorFn", "withCustomShapeFunction", "shapeFn", "withErrorCorrectionLevel", "ecl", "withInformationDensity", "withMaskPattern", "withCanvasSize", "withXOffset", "withYOffset", "withMargin", "beforeFn", "getBeforeFn", "()Lkotlin/jvm/functions/Function4;", "afterFn", "getAfterFn", "colorFunction", "getColorFunction", "()Lqrcode/color/QRCodeColorFunction;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "data", "", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class QRCodeBuilder {
    private int background;
    private int canvasSize;
    private int color;
    private QRCodeColorFunction customColorFunction;
    private QRCodeShapeFunction customShapeFunction;
    private Function4<? super QRCode, ? super QRCodeGraphics, ? super Integer, ? super Integer, Unit> drawLogoAction;
    private Function4<? super QRCode, ? super QRCodeGraphics, ? super Integer, ? super Integer, Unit> drawLogoBeforeAction;
    private Integer endColor;
    private ErrorCorrectionLevel errorCorrectionLevel;
    private QRCodeGraphicsFactory graphicsFactory;
    private int informationDensity;
    private int innerSpace;
    private int margin;
    private MaskPattern maskPattern;
    private int radiusInPixels;
    private QRCodeShapesEnum shape;
    private int squareSize;
    private Function4<? super QRCode, ? super QRCodeGraphics, ? super Integer, ? super Integer, Unit> userDoAfter;
    private Function4<? super QRCode, ? super QRCodeGraphics, ? super Integer, ? super Integer, Unit> userDoBefore;
    private boolean vertical;
    private int xOffset;
    private int yOffset;

    /* JADX INFO: compiled from: QRCodeBuilder.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[QRCodeShapesEnum.values().length];
            try {
                iArr[QRCodeShapesEnum.SQUARE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[QRCodeShapesEnum.CIRCLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[QRCodeShapesEnum.ROUNDED_SQUARE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[QRCodeShapesEnum.CUSTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public QRCodeBuilder(QRCodeShapesEnum shape) {
        this(shape, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(shape, "shape");
    }

    public final QRCodeBuilder withGradientColor(int i, Integer num) {
        return withGradientColor$default(this, i, num, false, 4, null);
    }

    public final QRCodeBuilder withInnerSpacing() {
        return withInnerSpacing$default(this, null, 1, null);
    }

    public final QRCodeBuilder withLogo(byte[] bArr, int i, int i2) {
        return withLogo$default(this, bArr, i, i2, false, 8, null);
    }

    public QRCodeBuilder(QRCodeShapesEnum shape, QRCodeShapeFunction qRCodeShapeFunction) {
        Intrinsics.checkNotNullParameter(shape, "shape");
        this.shape = shape;
        this.customShapeFunction = qRCodeShapeFunction;
        this.squareSize = 25;
        this.color = -16777216;
        this.vertical = true;
        this.innerSpace = innerSpace();
        this.radiusInPixels = RoundSquaresShapeFunction.INSTANCE.defaultRadius(this.squareSize);
        this.drawLogoAction = QRCode.INSTANCE.getEMPTY_FN$qrcode_kotlin_release();
        this.drawLogoBeforeAction = QRCode.INSTANCE.getEMPTY_FN$qrcode_kotlin_release();
        this.userDoAfter = QRCode.INSTANCE.getEMPTY_FN$qrcode_kotlin_release();
        this.userDoBefore = QRCode.INSTANCE.getEMPTY_FN$qrcode_kotlin_release();
        this.graphicsFactory = new QRCodeGraphicsFactory();
        this.errorCorrectionLevel = ErrorCorrectionLevel.LOW;
        this.maskPattern = MaskPattern.PATTERN000;
    }

    public /* synthetic */ QRCodeBuilder(QRCodeShapesEnum qRCodeShapesEnum, QRCodeShapeFunction qRCodeShapeFunction, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(qRCodeShapesEnum, (i & 2) != 0 ? null : qRCodeShapeFunction);
    }

    private final int innerSpace() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.shape.ordinal()];
        int iDefaultInnerSpace = 1;
        if (i != 1) {
            if (i == 2) {
                iDefaultInnerSpace = CircleShapeFunction.INSTANCE.defaultInnerSpace(this.squareSize);
            } else if (i == 3) {
                iDefaultInnerSpace = RoundSquaresShapeFunction.INSTANCE.defaultInnerSpace(this.squareSize);
            } else {
                if (i != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                iDefaultInnerSpace = 0;
            }
        }
        Integer numValueOf = Integer.valueOf(iDefaultInnerSpace);
        if (numValueOf.intValue() >= this.squareSize) {
            numValueOf = null;
        }
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        return 0;
    }

    public final QRCodeBuilder withShape(QRCodeShapesEnum shape) {
        Intrinsics.checkNotNullParameter(shape, "shape");
        this.shape = shape;
        return withInnerSpacing(Integer.valueOf(innerSpace()));
    }

    public final QRCodeBuilder withSize(int size) {
        this.squareSize = RangesKt.coerceAtLeast(size, 1);
        return withInnerSpacing(Integer.valueOf(innerSpace()));
    }

    public final QRCodeBuilder withColor(int color) {
        this.color = color;
        return this;
    }

    public final QRCodeBuilder withBackgroundColor(int bgColor) {
        this.background = bgColor;
        return this;
    }

    public static /* synthetic */ QRCodeBuilder withGradientColor$default(QRCodeBuilder qRCodeBuilder, int i, Integer num, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        return qRCodeBuilder.withGradientColor(i, num, z);
    }

    public final QRCodeBuilder withGradientColor(int startColor, Integer endColor, boolean vertical) {
        this.color = startColor;
        this.endColor = endColor;
        this.vertical = vertical;
        return this;
    }

    public final QRCodeBuilder withRadius(int radius) {
        Integer numValueOf = Integer.valueOf(radius);
        if (numValueOf.intValue() < 0) {
            numValueOf = null;
        }
        this.radiusInPixels = numValueOf != null ? numValueOf.intValue() : RoundSquaresShapeFunction.INSTANCE.defaultRadius(this.squareSize);
        return this;
    }

    public static /* synthetic */ QRCodeBuilder withInnerSpacing$default(QRCodeBuilder qRCodeBuilder, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        return qRCodeBuilder.withInnerSpacing(num);
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0014  */
    public final QRCodeBuilder withInnerSpacing(Integer innerSpacing) {
        int iInnerSpace;
        if (innerSpacing == null) {
            iInnerSpace = innerSpace();
        } else {
            if (innerSpacing.intValue() < 0) {
                innerSpacing = null;
            }
            if (innerSpacing != null) {
                iInnerSpace = innerSpacing.intValue();
            } else {
                iInnerSpace = innerSpace();
            }
        }
        this.innerSpace = iInnerSpace;
        return this;
    }

    public static /* synthetic */ QRCodeBuilder withLogo$default(QRCodeBuilder qRCodeBuilder, byte[] bArr, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            z = true;
        }
        return qRCodeBuilder.withLogo(bArr, i, i2, z);
    }

    public final QRCodeBuilder withLogo(final byte[] logo, final int width, final int height, boolean clearLogoArea) {
        if (logo != null) {
            if (clearLogoArea) {
                this.drawLogoBeforeAction = new Function4() { // from class: qrcode.QRCodeBuilder$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function4
                    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                        return QRCodeBuilder.withLogo$lambda$5(width, height, (QRCode) obj, (QRCodeGraphics) obj2, ((Integer) obj3).intValue(), ((Integer) obj4).intValue());
                    }
                };
            } else {
                this.drawLogoBeforeAction = QRCode.INSTANCE.getEMPTY_FN$qrcode_kotlin_release();
            }
            this.drawLogoAction = new Function4() { // from class: qrcode.QRCodeBuilder$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function4
                public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                    return QRCodeBuilder.withLogo$lambda$6(width, height, logo, (QRCode) obj, (QRCodeGraphics) obj2, ((Integer) obj3).intValue(), ((Integer) obj4).intValue());
                }
            };
        }
        return this;
    }

    static final Unit withLogo$lambda$5(int i, int i2, QRCode qRCode, QRCodeGraphics qRCodeGraphics, int i3, int i4) {
        Intrinsics.checkNotNullParameter(qRCode, "<this>");
        Intrinsics.checkNotNullParameter(qRCodeGraphics, "<unused var>");
        int canvasSize = (qRCode.getCanvasSize() - i) / 2;
        int canvasSize2 = (qRCode.getCanvasSize() - i2) / 2;
        for (QRCodeSquare[] qRCodeSquareArr : qRCode.getRawData()) {
            for (QRCodeSquare qRCodeSquare : qRCodeSquareArr) {
                qRCodeSquare.setRendered(!QRMath.INSTANCE.rectsIntersect(canvasSize, canvasSize2, i, i2, qRCodeSquare.absoluteX(qRCode.getSquareSize()), qRCodeSquare.absoluteY(qRCode.getSquareSize()), qRCode.getSquareSize(), qRCode.getSquareSize()));
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit withLogo$lambda$6(int i, int i2, byte[] bArr, QRCode qRCode, QRCodeGraphics canvas, int i3, int i4) {
        Intrinsics.checkNotNullParameter(qRCode, "<this>");
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.drawImage(bArr, i3 + ((qRCode.getCanvasSize() - i) / 2), i4 + ((qRCode.getCanvasSize() - i2) / 2));
        return Unit.INSTANCE;
    }

    static final Unit withAfterRenderAction$lambda$7(Function2 function2, QRCode qRCode, QRCodeGraphics it, int i, int i2) {
        Intrinsics.checkNotNullParameter(qRCode, "<this>");
        Intrinsics.checkNotNullParameter(it, "it");
        function2.invoke(qRCode, it);
        return Unit.INSTANCE;
    }

    public final QRCodeBuilder withAfterRenderAction(final Function2<? super QRCode, ? super QRCodeGraphics, Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        this.userDoAfter = new Function4() { // from class: qrcode.QRCodeBuilder$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return QRCodeBuilder.withAfterRenderAction$lambda$7(action, (QRCode) obj, (QRCodeGraphics) obj2, ((Integer) obj3).intValue(), ((Integer) obj4).intValue());
            }
        };
        return this;
    }

    static final Unit withBeforeRenderAction$lambda$8(Function2 function2, QRCode qRCode, QRCodeGraphics it, int i, int i2) {
        Intrinsics.checkNotNullParameter(qRCode, "<this>");
        Intrinsics.checkNotNullParameter(it, "it");
        function2.invoke(qRCode, it);
        return Unit.INSTANCE;
    }

    public final QRCodeBuilder withBeforeRenderAction(final Function2<? super QRCode, ? super QRCodeGraphics, Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        this.userDoBefore = new Function4() { // from class: qrcode.QRCodeBuilder$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return QRCodeBuilder.withBeforeRenderAction$lambda$8(action, (QRCode) obj, (QRCodeGraphics) obj2, ((Integer) obj3).intValue(), ((Integer) obj4).intValue());
            }
        };
        return this;
    }

    public final QRCodeBuilder withGraphicsFactory(QRCodeGraphicsFactory factory) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        this.graphicsFactory = factory;
        return this;
    }

    public final QRCodeBuilder withCustomColorFunction(QRCodeColorFunction colorFn) {
        this.customColorFunction = colorFn;
        return this;
    }

    public final QRCodeBuilder withCustomShapeFunction(QRCodeShapeFunction shapeFn) {
        this.customShapeFunction = shapeFn;
        return this;
    }

    public final QRCodeBuilder withErrorCorrectionLevel(ErrorCorrectionLevel ecl) {
        Intrinsics.checkNotNullParameter(ecl, "ecl");
        this.errorCorrectionLevel = ecl;
        return this;
    }

    public final QRCodeBuilder withInformationDensity(int informationDensity) {
        this.informationDensity = RangesKt.coerceIn(informationDensity, (ClosedRange<Integer>) new IntRange(0, 40));
        return this;
    }

    public final QRCodeBuilder withMaskPattern(MaskPattern maskPattern) {
        Intrinsics.checkNotNullParameter(maskPattern, "maskPattern");
        this.maskPattern = maskPattern;
        return this;
    }

    public final QRCodeBuilder withCanvasSize(int size) {
        this.canvasSize = size;
        return this;
    }

    public final QRCodeBuilder withXOffset(int xOffset) {
        this.xOffset = xOffset;
        return this;
    }

    public final QRCodeBuilder withYOffset(int yOffset) {
        this.yOffset = yOffset;
        return this;
    }

    public final QRCodeBuilder withMargin(int margin) {
        this.margin = margin;
        return this;
    }

    private final Function4<QRCode, QRCodeGraphics, Integer, Integer, Unit> getBeforeFn() {
        return new Function4() { // from class: qrcode.QRCodeBuilder$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return QRCodeBuilder._get_beforeFn_$lambda$9(this.f$0, (QRCode) obj, (QRCodeGraphics) obj2, ((Integer) obj3).intValue(), ((Integer) obj4).intValue());
            }
        };
    }

    static final Unit _get_beforeFn_$lambda$9(QRCodeBuilder qRCodeBuilder, QRCode qRCode, QRCodeGraphics canvas, int i, int i2) {
        Intrinsics.checkNotNullParameter(qRCode, "<this>");
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        qRCodeBuilder.drawLogoBeforeAction.invoke(qRCode, canvas, Integer.valueOf(i), Integer.valueOf(i2));
        qRCodeBuilder.userDoBefore.invoke(qRCode, canvas, Integer.valueOf(i), Integer.valueOf(i2));
        return Unit.INSTANCE;
    }

    private final Function4<QRCode, QRCodeGraphics, Integer, Integer, Unit> getAfterFn() {
        return new Function4() { // from class: qrcode.QRCodeBuilder$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return QRCodeBuilder._get_afterFn_$lambda$10(this.f$0, (QRCode) obj, (QRCodeGraphics) obj2, ((Integer) obj3).intValue(), ((Integer) obj4).intValue());
            }
        };
    }

    static final Unit _get_afterFn_$lambda$10(QRCodeBuilder qRCodeBuilder, QRCode qRCode, QRCodeGraphics canvas, int i, int i2) {
        Intrinsics.checkNotNullParameter(qRCode, "<this>");
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        qRCodeBuilder.drawLogoAction.invoke(qRCode, canvas, Integer.valueOf(i), Integer.valueOf(i2));
        qRCodeBuilder.userDoAfter.invoke(qRCode, canvas, Integer.valueOf(i), Integer.valueOf(i2));
        return Unit.INSTANCE;
    }

    private final QRCodeColorFunction getColorFunction() {
        Integer num = this.endColor;
        QRCodeColorFunction qRCodeColorFunction = this.customColorFunction;
        if (num == null) {
            return qRCodeColorFunction == null ? new DefaultColorFunction(this.color, this.background) : qRCodeColorFunction;
        }
        if (qRCodeColorFunction != null) {
            return qRCodeColorFunction;
        }
        int i = this.color;
        Integer num2 = this.endColor;
        Intrinsics.checkNotNull(num2);
        return new LinearGradientColorFunction(i, num2.intValue(), this.background, false, 8, null);
    }

    public final QRCode build(String data) {
        CircleShapeFunction defaultShapeFunction;
        Intrinsics.checkNotNullParameter(data, "data");
        int i = this.squareSize;
        int i2 = this.canvasSize;
        int i3 = this.xOffset;
        int i4 = this.margin;
        int i5 = i3 + i4;
        int i6 = this.yOffset + i4;
        QRCodeColorFunction colorFunction = getColorFunction();
        DefaultShapeFunction defaultShapeFunction2 = this.customShapeFunction;
        if (defaultShapeFunction2 == null) {
            int i7 = WhenMappings.$EnumSwitchMapping$0[this.shape.ordinal()];
            if (i7 == 1) {
                defaultShapeFunction = new DefaultShapeFunction(this.squareSize, this.innerSpace);
            } else if (i7 == 2) {
                defaultShapeFunction = new CircleShapeFunction(this.squareSize, this.innerSpace);
            } else if (i7 != 3) {
                if (i7 != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                defaultShapeFunction = new DefaultShapeFunction(this.squareSize, this.innerSpace);
            } else {
                defaultShapeFunction = new RoundSquaresShapeFunction(this.squareSize, this.radiusInPixels, this.innerSpace);
            }
            defaultShapeFunction2 = defaultShapeFunction;
        }
        QRCodeShapeFunction qRCodeShapeFunction = defaultShapeFunction2;
        QRCodeGraphicsFactory qRCodeGraphicsFactory = this.graphicsFactory;
        ErrorCorrectionLevel errorCorrectionLevel = this.errorCorrectionLevel;
        int iInfoDensityForDataAndECL$default = this.informationDensity;
        if (iInfoDensityForDataAndECL$default == 0) {
            iInfoDensityForDataAndECL$default = QRCodeProcessor.Companion.infoDensityForDataAndECL$default(QRCodeProcessor.INSTANCE, data, this.errorCorrectionLevel, null, 4, null);
        }
        QRCode qRCode = new QRCode(data, i, i2, i5, i6, colorFunction, qRCodeShapeFunction, qRCodeGraphicsFactory, errorCorrectionLevel, iInfoDensityForDataAndECL$default, this.maskPattern, getBeforeFn(), getAfterFn());
        if (this.margin > 0) {
            qRCode.resize(qRCode.getCanvasSize() + (this.margin * 2));
        }
        return qRCode;
    }
}
