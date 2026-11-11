package androidx.compose.ui.graphics.colorspace;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ColorSpaces.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010A\u001a\u0004\u0018\u0001042\b\b\u0001\u0010B\u001a\u00020\u00052\u0006\u0010C\u001a\u00020\rJ\u0016\u0010D\u001a\u0002042\u0006\u0010E\u001a\u00020FH\u0080\b¢\u0006\u0002\bGJ\u001d\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020\r2\u0006\u0010P\u001a\u00020NH\u0000¢\u0006\u0002\bQJ\u001d\u0010R\u001a\u00020N2\u0006\u0010O\u001a\u00020\r2\u0006\u0010P\u001a\u00020NH\u0000¢\u0006\u0002\bSJ\u001d\u0010T\u001a\u00020N2\u0006\u0010O\u001a\u00020\r2\u0006\u0010P\u001a\u00020NH\u0000¢\u0006\u0002\bUJ\u001d\u0010V\u001a\u00020N2\u0006\u0010W\u001a\u00020\r2\u0006\u0010P\u001a\u00020NH\u0000¢\u0006\u0002\bXR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0014\u0010\f\u001a\u00020\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0014\u0010\u0013\u001a\u00020\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\u001b\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\u001d\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0011\u0010\u001f\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0011\u0010!\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u0011\u0010#\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0011\u0010%\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0018R\u0011\u0010'\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0018R\u0011\u0010)\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0018R\u0011\u0010+\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0018R\u0011\u0010-\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0018R\u0011\u0010/\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0018R\u0011\u00101\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0018R\u0011\u00103\u001a\u000204¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0011\u00107\u001a\u000204¢\u0006\b\n\u0000\u001a\u0004\b8\u00106R\u0014\u00109\u001a\u00020\u0016X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0018R\u0011\u0010;\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0018R\u0011\u0010=\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0018R\u0011\u0010?\u001a\u000204¢\u0006\b\n\u0000\u001a\u0004\b@\u00106R\u001c\u0010H\u001a\b\u0012\u0004\u0012\u0002040IX\u0080\u0004¢\u0006\n\n\u0002\u0010L\u001a\u0004\bJ\u0010K¨\u0006Y"}, d2 = {"Landroidx/compose/ui/graphics/colorspace/ColorSpaces;", "", "<init>", "()V", "SrgbPrimaries", "", "getSrgbPrimaries$ui_graphics_release", "()[F", "Ntsc1953Primaries", "getNtsc1953Primaries$ui_graphics_release", "Bt2020Primaries", "getBt2020Primaries$ui_graphics_release", "SrgbTransferParameters", "Landroidx/compose/ui/graphics/colorspace/TransferParameters;", "getSrgbTransferParameters$ui_graphics_release", "()Landroidx/compose/ui/graphics/colorspace/TransferParameters;", "NoneTransferParameters", "Bt2020HlgTransferParameters", "getBt2020HlgTransferParameters$ui_graphics_release", "Bt2020PqTransferParameters", "getBt2020PqTransferParameters$ui_graphics_release", "Srgb", "Landroidx/compose/ui/graphics/colorspace/Rgb;", "getSrgb", "()Landroidx/compose/ui/graphics/colorspace/Rgb;", "LinearSrgb", "getLinearSrgb", "ExtendedSrgb", "getExtendedSrgb", "LinearExtendedSrgb", "getLinearExtendedSrgb", "Bt709", "getBt709", "Bt2020", "getBt2020", "DciP3", "getDciP3", "DisplayP3", "getDisplayP3", "Ntsc1953", "getNtsc1953", "SmpteC", "getSmpteC", "AdobeRgb", "getAdobeRgb", "ProPhotoRgb", "getProPhotoRgb", "Aces", "getAces", "Acescg", "getAcescg", "CieXyz", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "getCieXyz", "()Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "CieLab", "getCieLab", "Unspecified", "getUnspecified$ui_graphics_release", "Bt2020Hlg", "getBt2020Hlg", "Bt2020Pq", "getBt2020Pq", "Oklab", "getOklab", "match", "toXYZD50", "function", "getColorSpace", "id", "", "getColorSpace$ui_graphics_release", "ColorSpacesArray", "", "getColorSpacesArray$ui_graphics_release", "()[Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "[Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "transferHlgOetf", "", "params", "x", "transferHlgOetf$ui_graphics_release", "transferHlgEotf", "transferHlgEotf$ui_graphics_release", "transferSt2048Oetf", "transferSt2048Oetf$ui_graphics_release", "transferSt2048Eotf", "pq", "transferSt2048Eotf$ui_graphics_release", "ui-graphics_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ColorSpaces {
    private static final Rgb Aces;
    private static final Rgb Acescg;
    private static final Rgb AdobeRgb;
    private static final Rgb Bt2020;
    private static final Rgb Bt2020Hlg;
    private static final TransferParameters Bt2020HlgTransferParameters;
    private static final Rgb Bt2020Pq;
    private static final TransferParameters Bt2020PqTransferParameters;
    private static final float[] Bt2020Primaries;
    private static final Rgb Bt709;
    private static final ColorSpace CieLab;
    private static final ColorSpace CieXyz;
    private static final ColorSpace[] ColorSpacesArray;
    private static final Rgb DciP3;
    private static final Rgb DisplayP3;
    private static final Rgb ExtendedSrgb;
    public static final ColorSpaces INSTANCE = new ColorSpaces();
    private static final Rgb LinearExtendedSrgb;
    private static final Rgb LinearSrgb;
    private static final TransferParameters NoneTransferParameters;
    private static final Rgb Ntsc1953;
    private static final float[] Ntsc1953Primaries;
    private static final ColorSpace Oklab;
    private static final Rgb ProPhotoRgb;
    private static final Rgb SmpteC;
    private static final Rgb Srgb;
    private static final float[] SrgbPrimaries;
    private static final TransferParameters SrgbTransferParameters;
    private static final Rgb Unspecified;

    private ColorSpaces() {
    }

    static {
        float[] fArr = {0.64f, 0.33f, 0.3f, 0.6f, 0.15f, 0.06f};
        SrgbPrimaries = fArr;
        float[] fArr2 = {0.67f, 0.33f, 0.21f, 0.71f, 0.14f, 0.08f};
        Ntsc1953Primaries = fArr2;
        float[] fArr3 = {0.708f, 0.292f, 0.17f, 0.797f, 0.131f, 0.046f};
        Bt2020Primaries = fArr3;
        TransferParameters transferParameters = new TransferParameters(2.4d, 0.9478672985781991d, 0.05213270142180095d, 0.07739938080495357d, 0.04045d, 0.0d, 0.0d, 96, null);
        SrgbTransferParameters = transferParameters;
        TransferParameters transferParameters2 = new TransferParameters(2.2d, 0.9478672985781991d, 0.05213270142180095d, 0.07739938080495357d, 0.04045d, 0.0d, 0.0d, 96, null);
        NoneTransferParameters = transferParameters2;
        TransferParameters transferParameters3 = new TransferParameters(-3.0d, 2.0d, 2.0d, 5.591816309728916d, 0.28466892d, 0.55991073d, -0.685490157d);
        Bt2020HlgTransferParameters = transferParameters3;
        TransferParameters transferParameters4 = new TransferParameters(-2.0d, -1.555223d, 1.860454d, 0.012683313515655966d, 18.8515625d, -18.6875d, 6.277394636015326d);
        Bt2020PqTransferParameters = transferParameters4;
        Rgb rgb = new Rgb("sRGB IEC61966-2.1", fArr, Illuminant.INSTANCE.getD65(), transferParameters, 0);
        Srgb = rgb;
        Rgb rgb2 = new Rgb("sRGB IEC61966-2.1 (Linear)", fArr, Illuminant.INSTANCE.getD65(), 1.0d, 0.0f, 1.0f, 1);
        LinearSrgb = rgb2;
        Rgb rgb3 = new Rgb("scRGB-nl IEC 61966-2-2:2003", fArr, Illuminant.INSTANCE.getD65(), null, new DoubleFunction() { // from class: androidx.compose.ui.graphics.colorspace.ColorSpaces$$ExternalSyntheticLambda0
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d) {
                return ColorSpaceKt.absRcpResponse(d, 0.9478672985781991d, 0.05213270142180095d, 0.07739938080495357d, 0.04045d, 2.4d);
            }
        }, new DoubleFunction() { // from class: androidx.compose.ui.graphics.colorspace.ColorSpaces$$ExternalSyntheticLambda1
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d) {
                return ColorSpaceKt.absResponse(d, 0.9478672985781991d, 0.05213270142180095d, 0.07739938080495357d, 0.04045d, 2.4d);
            }
        }, -0.799f, 2.399f, transferParameters, 2);
        ExtendedSrgb = rgb3;
        Rgb rgb4 = new Rgb("scRGB IEC 61966-2-2:2003", fArr, Illuminant.INSTANCE.getD65(), 1.0d, -0.5f, 7.499f, 3);
        LinearExtendedSrgb = rgb4;
        Rgb rgb5 = new Rgb("Rec. ITU-R BT.709-5", new float[]{0.64f, 0.33f, 0.3f, 0.6f, 0.15f, 0.06f}, Illuminant.INSTANCE.getD65(), new TransferParameters(2.2222222222222223d, 0.9099181073703367d, 0.09008189262966333d, 0.2222222222222222d, 0.081d, 0.0d, 0.0d, 96, null), 4);
        Bt709 = rgb5;
        Rgb rgb6 = new Rgb("Rec. ITU-R BT.2020-1", new float[]{0.708f, 0.292f, 0.17f, 0.797f, 0.131f, 0.046f}, Illuminant.INSTANCE.getD65(), new TransferParameters(2.2222222222222223d, 0.9096697898662786d, 0.09033021013372146d, 0.2222222222222222d, 0.08145d, 0.0d, 0.0d, 96, null), 5);
        Bt2020 = rgb6;
        Rgb rgb7 = new Rgb("SMPTE RP 431-2-2007 DCI (P3)", new float[]{0.68f, 0.32f, 0.265f, 0.69f, 0.15f, 0.06f}, new WhitePoint(0.314f, 0.351f), 2.6d, 0.0f, 1.0f, 6);
        DciP3 = rgb7;
        Rgb rgb8 = new Rgb("Display P3", new float[]{0.68f, 0.32f, 0.265f, 0.69f, 0.15f, 0.06f}, Illuminant.INSTANCE.getD65(), transferParameters, 7);
        DisplayP3 = rgb8;
        Rgb rgb9 = new Rgb("NTSC (1953)", fArr2, Illuminant.INSTANCE.getC(), new TransferParameters(2.2222222222222223d, 0.9099181073703367d, 0.09008189262966333d, 0.2222222222222222d, 0.081d, 0.0d, 0.0d, 96, null), 8);
        Ntsc1953 = rgb9;
        Rgb rgb10 = new Rgb("SMPTE-C RGB", new float[]{0.63f, 0.34f, 0.31f, 0.595f, 0.155f, 0.07f}, Illuminant.INSTANCE.getD65(), new TransferParameters(2.2222222222222223d, 0.9099181073703367d, 0.09008189262966333d, 0.2222222222222222d, 0.081d, 0.0d, 0.0d, 96, null), 9);
        SmpteC = rgb10;
        Rgb rgb11 = new Rgb("Adobe RGB (1998)", new float[]{0.64f, 0.33f, 0.21f, 0.71f, 0.15f, 0.06f}, Illuminant.INSTANCE.getD65(), 2.2d, 0.0f, 1.0f, 10);
        AdobeRgb = rgb11;
        Rgb rgb12 = new Rgb("ROMM RGB ISO 22028-2:2013", new float[]{0.7347f, 0.2653f, 0.1596f, 0.8404f, 0.0366f, 1.0E-4f}, Illuminant.INSTANCE.getD50(), new TransferParameters(1.8d, 1.0d, 0.0d, 0.0625d, 0.031248d, 0.0d, 0.0d, 96, null), 11);
        ProPhotoRgb = rgb12;
        Rgb rgb13 = new Rgb("SMPTE ST 2065-1:2012 ACES", new float[]{0.7347f, 0.2653f, 0.0f, 1.0f, 1.0E-4f, -0.077f}, Illuminant.INSTANCE.getD60(), 1.0d, -65504.0f, 65504.0f, 12);
        Aces = rgb13;
        Rgb rgb14 = new Rgb("Academy S-2014-004 ACEScg", new float[]{0.713f, 0.293f, 0.165f, 0.83f, 0.128f, 0.044f}, Illuminant.INSTANCE.getD60(), 1.0d, -65504.0f, 65504.0f, 13);
        Acescg = rgb14;
        Xyz xyz = new Xyz("Generic XYZ", 14);
        CieXyz = xyz;
        Lab lab = new Lab("Generic L*a*b*", 15);
        CieLab = lab;
        Rgb rgb15 = new Rgb("None", fArr, Illuminant.INSTANCE.getD65(), transferParameters2, 16);
        Unspecified = rgb15;
        Rgb rgb16 = new Rgb("Hybrid Log Gamma encoding", fArr3, Illuminant.INSTANCE.getD65(), null, new DoubleFunction() { // from class: androidx.compose.ui.graphics.colorspace.ColorSpaces$$ExternalSyntheticLambda2
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d) {
                return ColorSpaces.INSTANCE.transferHlgOetf$ui_graphics_release(ColorSpaces.Bt2020HlgTransferParameters, d);
            }
        }, new DoubleFunction() { // from class: androidx.compose.ui.graphics.colorspace.ColorSpaces$$ExternalSyntheticLambda3
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d) {
                return ColorSpaces.INSTANCE.transferHlgEotf$ui_graphics_release(ColorSpaces.Bt2020HlgTransferParameters, d);
            }
        }, 0.0f, 1.0f, transferParameters3, 17);
        Bt2020Hlg = rgb16;
        Rgb rgb17 = new Rgb("Perceptual Quantizer encoding", fArr3, Illuminant.INSTANCE.getD65(), null, new DoubleFunction() { // from class: androidx.compose.ui.graphics.colorspace.ColorSpaces$$ExternalSyntheticLambda4
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d) {
                return ColorSpaces.INSTANCE.transferSt2048Oetf$ui_graphics_release(ColorSpaces.Bt2020PqTransferParameters, d);
            }
        }, new DoubleFunction() { // from class: androidx.compose.ui.graphics.colorspace.ColorSpaces$$ExternalSyntheticLambda5
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d) {
                return ColorSpaces.INSTANCE.transferSt2048Eotf$ui_graphics_release(ColorSpaces.Bt2020PqTransferParameters, d);
            }
        }, 0.0f, 1.0f, transferParameters4, 18);
        Bt2020Pq = rgb17;
        Oklab oklab = new Oklab("Oklab", 19);
        Oklab = oklab;
        ColorSpacesArray = new ColorSpace[]{rgb, rgb2, rgb3, rgb4, rgb5, rgb6, rgb7, rgb8, rgb9, rgb10, rgb11, rgb12, rgb13, rgb14, xyz, lab, rgb15, rgb16, rgb17, oklab};
    }

    public final float[] getSrgbPrimaries$ui_graphics_release() {
        return SrgbPrimaries;
    }

    public final float[] getNtsc1953Primaries$ui_graphics_release() {
        return Ntsc1953Primaries;
    }

    public final float[] getBt2020Primaries$ui_graphics_release() {
        return Bt2020Primaries;
    }

    public final TransferParameters getSrgbTransferParameters$ui_graphics_release() {
        return SrgbTransferParameters;
    }

    public final TransferParameters getBt2020HlgTransferParameters$ui_graphics_release() {
        return Bt2020HlgTransferParameters;
    }

    public final TransferParameters getBt2020PqTransferParameters$ui_graphics_release() {
        return Bt2020PqTransferParameters;
    }

    public final Rgb getSrgb() {
        return Srgb;
    }

    public final Rgb getLinearSrgb() {
        return LinearSrgb;
    }

    public final Rgb getExtendedSrgb() {
        return ExtendedSrgb;
    }

    public final Rgb getLinearExtendedSrgb() {
        return LinearExtendedSrgb;
    }

    public final Rgb getBt709() {
        return Bt709;
    }

    public final Rgb getBt2020() {
        return Bt2020;
    }

    public final Rgb getDciP3() {
        return DciP3;
    }

    public final Rgb getDisplayP3() {
        return DisplayP3;
    }

    public final Rgb getNtsc1953() {
        return Ntsc1953;
    }

    public final Rgb getSmpteC() {
        return SmpteC;
    }

    public final Rgb getAdobeRgb() {
        return AdobeRgb;
    }

    public final Rgb getProPhotoRgb() {
        return ProPhotoRgb;
    }

    public final Rgb getAces() {
        return Aces;
    }

    public final Rgb getAcescg() {
        return Acescg;
    }

    public final ColorSpace getCieXyz() {
        return CieXyz;
    }

    public final ColorSpace getCieLab() {
        return CieLab;
    }

    public final Rgb getUnspecified$ui_graphics_release() {
        return Unspecified;
    }

    public final Rgb getBt2020Hlg() {
        return Bt2020Hlg;
    }

    public final Rgb getBt2020Pq() {
        return Bt2020Pq;
    }

    public final ColorSpace getOklab() {
        return Oklab;
    }

    public final ColorSpace match(float[] toXYZD50, TransferParameters function) {
        for (ColorSpace colorSpace : ColorSpacesArray) {
            if (ColorModel.m4586equalsimpl0(colorSpace.getModel(), ColorModel.INSTANCE.m4593getRgbxdoWZVw())) {
                ColorSpace colorSpaceAdapt$default = ColorSpaceKt.adapt$default(colorSpace, Illuminant.INSTANCE.getD50(), null, 2, null);
                Intrinsics.checkNotNull(colorSpaceAdapt$default, "null cannot be cast to non-null type androidx.compose.ui.graphics.colorspace.Rgb");
                Rgb rgb = (Rgb) colorSpaceAdapt$default;
                if (ColorSpaceKt.compare(toXYZD50, rgb.getTransform()) && ColorSpaceKt.compare(function, rgb.getTransferParameters())) {
                    return colorSpace;
                }
            }
        }
        return null;
    }

    public final ColorSpace getColorSpace$ui_graphics_release(int id) {
        return getColorSpacesArray$ui_graphics_release()[id];
    }

    public final ColorSpace[] getColorSpacesArray$ui_graphics_release() {
        return ColorSpacesArray;
    }

    public final double transferHlgOetf$ui_graphics_release(TransferParameters params, double x) {
        double dLog;
        double d = x < 0.0d ? -1.0d : 1.0d;
        double a = 1.0d / params.getA();
        double b = 1.0d / params.getB();
        double c = 1.0d / params.getC();
        double d2 = params.getD();
        double e = params.getE();
        double f = (x * d) / (params.getF() + 1.0d);
        if (f <= 1.0d) {
            dLog = a * Math.pow(f, b);
        } else {
            dLog = (c * Math.log(f - d2)) + e;
        }
        return d * dLog;
    }

    public final double transferHlgEotf$ui_graphics_release(TransferParameters params, double x) {
        double dExp;
        double d = x < 0.0d ? -1.0d : 1.0d;
        double d2 = x * d;
        double a = params.getA();
        double b = params.getB();
        double c = params.getC();
        double d3 = params.getD();
        double e = params.getE();
        double f = params.getF() + 1.0d;
        double d4 = a * d2;
        if (d4 <= 1.0d) {
            dExp = Math.pow(d4, b);
        } else {
            dExp = Math.exp((d2 - e) * c) + d3;
        }
        return f * d * dExp;
    }

    public final double transferSt2048Oetf$ui_graphics_release(TransferParameters params, double x) {
        double d = x < 0.0d ? -1.0d : 1.0d;
        double d2 = x * d;
        double d3 = -params.getA();
        double d4 = params.getD();
        double f = 1.0d / params.getF();
        return d * Math.pow(Math.max(d3 + (d4 * Math.pow(d2, f)), 0.0d) / (params.getB() + ((-params.getE()) * Math.pow(d2, f))), 1.0d / params.getC());
    }

    public final double transferSt2048Eotf$ui_graphics_release(TransferParameters pq, double x) {
        double d = x < 0.0d ? -1.0d : 1.0d;
        double d2 = x * d;
        return d * Math.pow(RangesKt.coerceAtLeast(pq.getA() + (pq.getB() * Math.pow(d2, pq.getC())), 0.0d) / (pq.getD() + (pq.getE() * Math.pow(d2, pq.getC()))), pq.getF());
    }
}
