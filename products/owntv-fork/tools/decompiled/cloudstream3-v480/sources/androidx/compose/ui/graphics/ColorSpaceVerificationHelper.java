package androidx.compose.ui.graphics;

import android.graphics.ColorSpace;
import android.os.Build;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.graphics.colorspace.DoubleFunction;
import androidx.compose.ui.graphics.colorspace.Rgb;
import androidx.compose.ui.graphics.colorspace.TransferParameters;
import androidx.compose.ui.graphics.colorspace.WhitePoint;
import j$.util.function.DoubleUnaryOperator$CC;
import java.util.function.DoubleUnaryOperator;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidColorSpace.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0007J\f\u0010\u0007\u001a\u00020\u0006*\u00020\u0005H\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/graphics/ColorSpaceVerificationHelper;", "", "<init>", "()V", "androidColorSpace", "Landroid/graphics/ColorSpace;", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "composeColorSpace", "ui-graphics_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class ColorSpaceVerificationHelper {
    public static final ColorSpaceVerificationHelper INSTANCE = new ColorSpaceVerificationHelper();

    private ColorSpaceVerificationHelper() {
    }

    @JvmStatic
    public static final ColorSpace androidColorSpace(androidx.compose.ui.graphics.colorspace.ColorSpace colorSpace) {
        ColorSpace.Rgb.TransferParameters transferParametersM;
        ColorSpace.Rgb rgbM;
        ColorSpace colorSpaceObtainAndroidColorSpace;
        if (Intrinsics.areEqual(colorSpace, ColorSpaces.INSTANCE.getSrgb())) {
            return ColorSpace.get(ColorSpace.Named.SRGB);
        }
        if (Intrinsics.areEqual(colorSpace, ColorSpaces.INSTANCE.getAces())) {
            return ColorSpace.get(ColorSpace.Named.ACES);
        }
        if (Intrinsics.areEqual(colorSpace, ColorSpaces.INSTANCE.getAcescg())) {
            return ColorSpace.get(ColorSpace.Named.ACESCG);
        }
        if (Intrinsics.areEqual(colorSpace, ColorSpaces.INSTANCE.getAdobeRgb())) {
            return ColorSpace.get(ColorSpace.Named.ADOBE_RGB);
        }
        if (Intrinsics.areEqual(colorSpace, ColorSpaces.INSTANCE.getBt2020())) {
            return ColorSpace.get(ColorSpace.Named.BT2020);
        }
        if (Intrinsics.areEqual(colorSpace, ColorSpaces.INSTANCE.getBt709())) {
            return ColorSpace.get(ColorSpace.Named.BT709);
        }
        if (Intrinsics.areEqual(colorSpace, ColorSpaces.INSTANCE.getCieLab())) {
            return ColorSpace.get(ColorSpace.Named.CIE_LAB);
        }
        if (Intrinsics.areEqual(colorSpace, ColorSpaces.INSTANCE.getCieXyz())) {
            return ColorSpace.get(ColorSpace.Named.CIE_XYZ);
        }
        if (Intrinsics.areEqual(colorSpace, ColorSpaces.INSTANCE.getDciP3())) {
            return ColorSpace.get(ColorSpace.Named.DCI_P3);
        }
        if (Intrinsics.areEqual(colorSpace, ColorSpaces.INSTANCE.getDisplayP3())) {
            return ColorSpace.get(ColorSpace.Named.DISPLAY_P3);
        }
        if (Intrinsics.areEqual(colorSpace, ColorSpaces.INSTANCE.getExtendedSrgb())) {
            return ColorSpace.get(ColorSpace.Named.EXTENDED_SRGB);
        }
        if (Intrinsics.areEqual(colorSpace, ColorSpaces.INSTANCE.getLinearExtendedSrgb())) {
            return ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB);
        }
        if (Intrinsics.areEqual(colorSpace, ColorSpaces.INSTANCE.getLinearSrgb())) {
            return ColorSpace.get(ColorSpace.Named.LINEAR_SRGB);
        }
        if (Intrinsics.areEqual(colorSpace, ColorSpaces.INSTANCE.getNtsc1953())) {
            return ColorSpace.get(ColorSpace.Named.NTSC_1953);
        }
        if (Intrinsics.areEqual(colorSpace, ColorSpaces.INSTANCE.getProPhotoRgb())) {
            return ColorSpace.get(ColorSpace.Named.PRO_PHOTO_RGB);
        }
        if (Intrinsics.areEqual(colorSpace, ColorSpaces.INSTANCE.getSmpteC())) {
            return ColorSpace.get(ColorSpace.Named.SMPTE_C);
        }
        if (Build.VERSION.SDK_INT >= 34 && (colorSpaceObtainAndroidColorSpace = ColorSpaceVerificationHelperV34.obtainAndroidColorSpace(colorSpace)) != null) {
            return colorSpaceObtainAndroidColorSpace;
        }
        if (colorSpace instanceof Rgb) {
            Rgb rgb = (Rgb) colorSpace;
            float[] xyz$ui_graphics_release = rgb.getWhitePoint().toXyz$ui_graphics_release();
            TransferParameters transferParameters = rgb.getTransferParameters();
            if (transferParameters != null) {
                Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4050m$2();
                transferParametersM = Api26Bitmap$$ExternalSyntheticApiModelOutline0.m(transferParameters.getA(), transferParameters.getB(), transferParameters.getC(), transferParameters.getD(), transferParameters.getE(), transferParameters.getF(), transferParameters.getGamma());
            } else {
                transferParametersM = null;
            }
            if (transferParametersM != null) {
                Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4052m$3();
                rgbM = Api26Bitmap$$ExternalSyntheticApiModelOutline0.m(colorSpace.getName(), rgb.getPrimaries(), xyz$ui_graphics_release, transferParametersM);
            } else {
                Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4052m$3();
                String name = colorSpace.getName();
                float[] primaries = rgb.getPrimaries();
                final Function1<Double, Double> oetf = rgb.getOetf();
                DoubleUnaryOperator doubleUnaryOperator = new DoubleUnaryOperator() { // from class: androidx.compose.ui.graphics.ColorSpaceVerificationHelper$$ExternalSyntheticLambda44
                    public /* synthetic */ DoubleUnaryOperator andThen(DoubleUnaryOperator doubleUnaryOperator2) {
                        return DoubleUnaryOperator$CC.$default$andThen(this, doubleUnaryOperator2);
                    }

                    @Override // java.util.function.DoubleUnaryOperator
                    public final double applyAsDouble(double d) {
                        return ((Number) oetf.invoke(Double.valueOf(d))).doubleValue();
                    }

                    public /* synthetic */ DoubleUnaryOperator compose(DoubleUnaryOperator doubleUnaryOperator2) {
                        return DoubleUnaryOperator$CC.$default$compose(this, doubleUnaryOperator2);
                    }
                };
                final Function1<Double, Double> eotf = rgb.getEotf();
                rgbM = Api26Bitmap$$ExternalSyntheticApiModelOutline0.m(name, primaries, xyz$ui_graphics_release, doubleUnaryOperator, new DoubleUnaryOperator() { // from class: androidx.compose.ui.graphics.ColorSpaceVerificationHelper$$ExternalSyntheticLambda45
                    public /* synthetic */ DoubleUnaryOperator andThen(DoubleUnaryOperator doubleUnaryOperator2) {
                        return DoubleUnaryOperator$CC.$default$andThen(this, doubleUnaryOperator2);
                    }

                    @Override // java.util.function.DoubleUnaryOperator
                    public final double applyAsDouble(double d) {
                        return ((Number) eotf.invoke(Double.valueOf(d))).doubleValue();
                    }

                    public /* synthetic */ DoubleUnaryOperator compose(DoubleUnaryOperator doubleUnaryOperator2) {
                        return DoubleUnaryOperator$CC.$default$compose(this, doubleUnaryOperator2);
                    }
                }, rgb.getMinValue(0), rgb.getMaxValue(0));
            }
            return Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4028m((Object) rgbM);
        }
        return ColorSpace.get(ColorSpace.Named.SRGB);
    }

    @JvmStatic
    public static final androidx.compose.ui.graphics.colorspace.ColorSpace composeColorSpace(final ColorSpace colorSpace) {
        Rgb srgb;
        WhitePoint whitePoint;
        int id = colorSpace.getId();
        if (id == ColorSpace.Named.SRGB.ordinal()) {
            return ColorSpaces.INSTANCE.getSrgb();
        }
        if (id == ColorSpace.Named.ACES.ordinal()) {
            return ColorSpaces.INSTANCE.getAces();
        }
        if (id == ColorSpace.Named.ACESCG.ordinal()) {
            return ColorSpaces.INSTANCE.getAcescg();
        }
        if (id == ColorSpace.Named.ADOBE_RGB.ordinal()) {
            return ColorSpaces.INSTANCE.getAdobeRgb();
        }
        if (id == ColorSpace.Named.BT2020.ordinal()) {
            return ColorSpaces.INSTANCE.getBt2020();
        }
        if (id == ColorSpace.Named.BT709.ordinal()) {
            return ColorSpaces.INSTANCE.getBt709();
        }
        if (id == ColorSpace.Named.CIE_LAB.ordinal()) {
            return ColorSpaces.INSTANCE.getCieLab();
        }
        if (id == ColorSpace.Named.CIE_XYZ.ordinal()) {
            return ColorSpaces.INSTANCE.getCieXyz();
        }
        if (id == ColorSpace.Named.DCI_P3.ordinal()) {
            return ColorSpaces.INSTANCE.getDciP3();
        }
        if (id == ColorSpace.Named.DISPLAY_P3.ordinal()) {
            return ColorSpaces.INSTANCE.getDisplayP3();
        }
        if (id == ColorSpace.Named.EXTENDED_SRGB.ordinal()) {
            return ColorSpaces.INSTANCE.getExtendedSrgb();
        }
        if (id == ColorSpace.Named.LINEAR_EXTENDED_SRGB.ordinal()) {
            return ColorSpaces.INSTANCE.getLinearExtendedSrgb();
        }
        if (id == ColorSpace.Named.LINEAR_SRGB.ordinal()) {
            return ColorSpaces.INSTANCE.getLinearSrgb();
        }
        if (id == ColorSpace.Named.NTSC_1953.ordinal()) {
            return ColorSpaces.INSTANCE.getNtsc1953();
        }
        if (id == ColorSpace.Named.PRO_PHOTO_RGB.ordinal()) {
            return ColorSpaces.INSTANCE.getProPhotoRgb();
        }
        if (id == ColorSpace.Named.SMPTE_C.ordinal()) {
            return ColorSpaces.INSTANCE.getSmpteC();
        }
        if (Build.VERSION.SDK_INT >= 34) {
            androidx.compose.ui.graphics.colorspace.ColorSpace colorSpaceObtainComposeColorSpaceFromId = ColorSpaceVerificationHelperV34.obtainComposeColorSpaceFromId(colorSpace.getId());
            if (!Intrinsics.areEqual(colorSpaceObtainComposeColorSpaceFromId, ColorSpaces.INSTANCE.getUnspecified$ui_graphics_release())) {
                return colorSpaceObtainComposeColorSpaceFromId;
            }
        }
        if (Api26Bitmap$$ExternalSyntheticApiModelOutline0.m$1(colorSpace)) {
            ColorSpace.Rgb.TransferParameters transferParameters = Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4026m((Object) colorSpace).getTransferParameters();
            if (Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4026m((Object) colorSpace).getWhitePoint().length == 3) {
                whitePoint = new WhitePoint(Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4026m((Object) colorSpace).getWhitePoint()[0], Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4026m((Object) colorSpace).getWhitePoint()[1], Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4026m((Object) colorSpace).getWhitePoint()[2]);
            } else {
                whitePoint = new WhitePoint(Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4026m((Object) colorSpace).getWhitePoint()[0], Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4026m((Object) colorSpace).getWhitePoint()[1]);
            }
            srgb = new Rgb(Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4026m((Object) colorSpace).getName(), Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4026m((Object) colorSpace).getPrimaries(), whitePoint, Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4026m((Object) colorSpace).getTransform(), new DoubleFunction() { // from class: androidx.compose.ui.graphics.ColorSpaceVerificationHelper$$ExternalSyntheticLambda46
                @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                public final double invoke(double d) {
                    return Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4026m((Object) colorSpace).getOetf().applyAsDouble(d);
                }
            }, new DoubleFunction() { // from class: androidx.compose.ui.graphics.ColorSpaceVerificationHelper$$ExternalSyntheticLambda47
                @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                public final double invoke(double d) {
                    return Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4026m((Object) colorSpace).getEotf().applyAsDouble(d);
                }
            }, Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4026m((Object) colorSpace).getMinValue(0), Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4026m((Object) colorSpace).getMaxValue(0), transferParameters != null ? new TransferParameters(transferParameters.g, transferParameters.a, transferParameters.b, transferParameters.c, transferParameters.d, transferParameters.e, transferParameters.f) : null, Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4026m((Object) colorSpace).getId());
        } else {
            srgb = ColorSpaces.INSTANCE.getSrgb();
        }
        return srgb;
    }
}
