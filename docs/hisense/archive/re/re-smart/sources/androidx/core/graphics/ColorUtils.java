package androidx.core.graphics;

import android.graphics.Color;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.ViewCompat;
import b.a.a.a.a;
import com.google.common.collect.LinkedHashMultimap;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class ColorUtils {
    public static final int MIN_ALPHA_SEARCH_MAX_ITERATIONS = 10;
    public static final int MIN_ALPHA_SEARCH_PRECISION = 1;
    public static final ThreadLocal<double[]> TEMP_ARRAY = new ThreadLocal<>();
    public static final double XYZ_EPSILON = 0.008856d;
    public static final double XYZ_KAPPA = 903.3d;
    public static final double XYZ_WHITE_REFERENCE_X = 95.047d;
    public static final double XYZ_WHITE_REFERENCE_Y = 100.0d;
    public static final double XYZ_WHITE_REFERENCE_Z = 108.883d;

    @ColorInt
    public static int HSLToColor(@NonNull float[] hsl) {
        int iRound;
        int iRound2;
        int iRound3;
        float f2 = hsl[0];
        float f3 = hsl[1];
        float f4 = hsl[2];
        float fAbs = (1.0f - Math.abs((f4 * 2.0f) - 1.0f)) * f3;
        float f5 = f4 - (0.5f * fAbs);
        float fAbs2 = (1.0f - Math.abs(((f2 / 60.0f) % 2.0f) - 1.0f)) * fAbs;
        switch (((int) f2) / 60) {
            case 0:
                iRound = Math.round((fAbs + f5) * 255.0f);
                iRound2 = Math.round((fAbs2 + f5) * 255.0f);
                iRound3 = Math.round(f5 * 255.0f);
                break;
            case 1:
                iRound = Math.round((fAbs2 + f5) * 255.0f);
                iRound2 = Math.round((fAbs + f5) * 255.0f);
                iRound3 = Math.round(f5 * 255.0f);
                break;
            case 2:
                iRound = Math.round(f5 * 255.0f);
                iRound2 = Math.round((fAbs + f5) * 255.0f);
                iRound3 = Math.round((fAbs2 + f5) * 255.0f);
                break;
            case 3:
                iRound = Math.round(f5 * 255.0f);
                iRound2 = Math.round((fAbs2 + f5) * 255.0f);
                iRound3 = Math.round((fAbs + f5) * 255.0f);
                break;
            case 4:
                iRound = Math.round((fAbs2 + f5) * 255.0f);
                iRound2 = Math.round(f5 * 255.0f);
                iRound3 = Math.round((fAbs + f5) * 255.0f);
                break;
            case 5:
            case 6:
                iRound = Math.round((fAbs + f5) * 255.0f);
                iRound2 = Math.round(f5 * 255.0f);
                iRound3 = Math.round((fAbs2 + f5) * 255.0f);
                break;
            default:
                iRound3 = 0;
                iRound = 0;
                iRound2 = 0;
                break;
        }
        return Color.rgb(constrain(iRound, 0, 255), constrain(iRound2, 0, 255), constrain(iRound3, 0, 255));
    }

    @ColorInt
    public static int LABToColor(@FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = XYZ_WHITE_REFERENCE_Y) final double l, @FloatRange(from = -128.0d, to = 127.0d) final double a2, @FloatRange(from = -128.0d, to = 127.0d) final double b2) {
        double[] tempDouble3Array = getTempDouble3Array();
        LABToXYZ(l, a2, b2, tempDouble3Array);
        return XYZToColor(tempDouble3Array[0], tempDouble3Array[1], tempDouble3Array[2]);
    }

    public static void LABToXYZ(@FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = XYZ_WHITE_REFERENCE_Y) final double l, @FloatRange(from = -128.0d, to = 127.0d) final double a2, @FloatRange(from = -128.0d, to = 127.0d) final double b2, @NonNull double[] outXyz) {
        double d2 = (l + 16.0d) / 116.0d;
        double d3 = (a2 / 500.0d) + d2;
        double d4 = d2 - (b2 / 200.0d);
        double dPow = Math.pow(d3, 3.0d);
        if (dPow <= 0.008856d) {
            dPow = ((d3 * 116.0d) - 16.0d) / 903.3d;
        }
        double dPow2 = l > 7.9996247999999985d ? Math.pow(d2, 3.0d) : l / 903.3d;
        double dPow3 = Math.pow(d4, 3.0d);
        if (dPow3 <= 0.008856d) {
            dPow3 = ((d4 * 116.0d) - 16.0d) / 903.3d;
        }
        outXyz[0] = dPow * 95.047d;
        outXyz[1] = dPow2 * 100.0d;
        outXyz[2] = dPow3 * 108.883d;
    }

    public static void RGBToHSL(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG, to = 255) int r, @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG, to = 255) int g, @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG, to = 255) int b2, @NonNull float[] outHsl) {
        float f2;
        float fAbs;
        float f3 = r / 255.0f;
        float f4 = g / 255.0f;
        float f5 = b2 / 255.0f;
        float fMax = Math.max(f3, Math.max(f4, f5));
        float fMin = Math.min(f3, Math.min(f4, f5));
        float f6 = fMax - fMin;
        float f7 = (fMax + fMin) / 2.0f;
        if (fMax == fMin) {
            f2 = 0.0f;
            fAbs = 0.0f;
        } else {
            if (fMax == f3) {
                f2 = ((f4 - f5) / f6) % 6.0f;
            } else {
                f2 = fMax == f4 ? ((f5 - f3) / f6) + 2.0f : ((f3 - f4) / f6) + 4.0f;
            }
            fAbs = f6 / (1.0f - Math.abs((2.0f * f7) - 1.0f));
        }
        float f8 = (f2 * 60.0f) % 360.0f;
        if (f8 < 0.0f) {
            f8 += 360.0f;
        }
        outHsl[0] = constrain(f8, 0.0f, 360.0f);
        outHsl[1] = constrain(fAbs, 0.0f, 1.0f);
        outHsl[2] = constrain(f7, 0.0f, 1.0f);
    }

    public static void RGBToLAB(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG, to = 255) int r, @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG, to = 255) int g, @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG, to = 255) int b2, @NonNull double[] outLab) {
        RGBToXYZ(r, g, b2, outLab);
        XYZToLAB(outLab[0], outLab[1], outLab[2], outLab);
    }

    public static void RGBToXYZ(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG, to = 255) int r, @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG, to = 255) int g, @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG, to = 255) int b2, @NonNull double[] outXyz) {
        if (outXyz.length != 3) {
            throw new IllegalArgumentException("outXyz must have a length of 3.");
        }
        double d2 = ((double) r) / 255.0d;
        double dPow = d2 < 0.04045d ? d2 / 12.92d : Math.pow((d2 + 0.055d) / 1.055d, 2.4d);
        double d3 = ((double) g) / 255.0d;
        double dPow2 = d3 < 0.04045d ? d3 / 12.92d : Math.pow((d3 + 0.055d) / 1.055d, 2.4d);
        double d4 = ((double) b2) / 255.0d;
        double dPow3 = d4 < 0.04045d ? d4 / 12.92d : Math.pow((d4 + 0.055d) / 1.055d, 2.4d);
        outXyz[0] = ((0.1805d * dPow3) + (0.3576d * dPow2) + (0.4124d * dPow)) * 100.0d;
        outXyz[1] = ((0.0722d * dPow3) + (0.7152d * dPow2) + (0.2126d * dPow)) * 100.0d;
        outXyz[2] = ((dPow3 * 0.9505d) + (dPow2 * 0.1192d) + (dPow * 0.0193d)) * 100.0d;
    }

    @ColorInt
    public static int XYZToColor(@FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = XYZ_WHITE_REFERENCE_X) double x, @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = XYZ_WHITE_REFERENCE_Y) double y, @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = XYZ_WHITE_REFERENCE_Z) double z) {
        double d2 = (((-0.4986d) * z) + (((-1.5372d) * y) + (3.2406d * x))) / 100.0d;
        double d3 = ((0.0415d * z) + ((1.8758d * y) + ((-0.9689d) * x))) / 100.0d;
        double d4 = ((1.057d * z) + (((-0.204d) * y) + (0.0557d * x))) / 100.0d;
        return Color.rgb(constrain((int) Math.round((d2 > 0.0031308d ? (Math.pow(d2, 0.4166666666666667d) * 1.055d) - 0.055d : d2 * 12.92d) * 255.0d), 0, 255), constrain((int) Math.round((d3 > 0.0031308d ? (Math.pow(d3, 0.4166666666666667d) * 1.055d) - 0.055d : d3 * 12.92d) * 255.0d), 0, 255), constrain((int) Math.round((d4 > 0.0031308d ? (Math.pow(d4, 0.4166666666666667d) * 1.055d) - 0.055d : d4 * 12.92d) * 255.0d), 0, 255));
    }

    public static void XYZToLAB(@FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = XYZ_WHITE_REFERENCE_X) double x, @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = XYZ_WHITE_REFERENCE_Y) double y, @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = XYZ_WHITE_REFERENCE_Z) double z, @NonNull double[] outLab) {
        if (outLab.length != 3) {
            throw new IllegalArgumentException("outLab must have a length of 3.");
        }
        double dPivotXyzComponent = pivotXyzComponent(x / 95.047d);
        double dPivotXyzComponent2 = pivotXyzComponent(y / 100.0d);
        double dPivotXyzComponent3 = pivotXyzComponent(z / 108.883d);
        outLab[0] = Math.max(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, (116.0d * dPivotXyzComponent2) - 16.0d);
        outLab[1] = (dPivotXyzComponent - dPivotXyzComponent2) * 500.0d;
        outLab[2] = (dPivotXyzComponent2 - dPivotXyzComponent3) * 200.0d;
    }

    @ColorInt
    public static int blendARGB(@ColorInt int color1, @ColorInt int color2, @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = LinkedHashMultimap.VALUE_SET_LOAD_FACTOR) float ratio) {
        float f2 = 1.0f - ratio;
        return Color.argb((int) ((Color.alpha(color2) * ratio) + (Color.alpha(color1) * f2)), (int) ((Color.red(color2) * ratio) + (Color.red(color1) * f2)), (int) ((Color.green(color2) * ratio) + (Color.green(color1) * f2)), (int) ((Color.blue(color2) * ratio) + (Color.blue(color1) * f2)));
    }

    public static void blendHSL(@NonNull float[] hsl1, @NonNull float[] hsl2, @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = LinkedHashMultimap.VALUE_SET_LOAD_FACTOR) float ratio, @NonNull float[] outResult) {
        if (outResult.length != 3) {
            throw new IllegalArgumentException("result must have a length of 3.");
        }
        float f2 = 1.0f - ratio;
        outResult[0] = circularInterpolate(hsl1[0], hsl2[0], ratio);
        outResult[1] = (hsl2[1] * ratio) + (hsl1[1] * f2);
        outResult[2] = (hsl2[2] * ratio) + (hsl1[2] * f2);
    }

    public static void blendLAB(@NonNull double[] lab1, @NonNull double[] lab2, @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = LinkedHashMultimap.VALUE_SET_LOAD_FACTOR) double ratio, @NonNull double[] outResult) {
        if (outResult.length != 3) {
            throw new IllegalArgumentException("outResult must have a length of 3.");
        }
        double d2 = 1.0d - ratio;
        outResult[0] = (lab2[0] * ratio) + (lab1[0] * d2);
        outResult[1] = (lab2[1] * ratio) + (lab1[1] * d2);
        outResult[2] = (lab2[2] * ratio) + (lab1[2] * d2);
    }

    public static double calculateContrast(@ColorInt int foreground, @ColorInt int background) {
        if (Color.alpha(background) != 255) {
            StringBuilder sbA = a.a("background can not be translucent: #");
            sbA.append(Integer.toHexString(background));
            throw new IllegalArgumentException(sbA.toString());
        }
        if (Color.alpha(foreground) < 255) {
            foreground = compositeColors(foreground, background);
        }
        double dCalculateLuminance = calculateLuminance(foreground) + 0.05d;
        double dCalculateLuminance2 = calculateLuminance(background) + 0.05d;
        return Math.max(dCalculateLuminance, dCalculateLuminance2) / Math.min(dCalculateLuminance, dCalculateLuminance2);
    }

    @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = LinkedHashMultimap.VALUE_SET_LOAD_FACTOR)
    public static double calculateLuminance(@ColorInt int color) {
        double[] tempDouble3Array = getTempDouble3Array();
        colorToXYZ(color, tempDouble3Array);
        return tempDouble3Array[1] / 100.0d;
    }

    public static int calculateMinimumAlpha(@ColorInt int foreground, @ColorInt int background, float minContrastRatio) {
        int i = 255;
        if (Color.alpha(background) != 255) {
            StringBuilder sbA = a.a("background can not be translucent: #");
            sbA.append(Integer.toHexString(background));
            throw new IllegalArgumentException(sbA.toString());
        }
        double d2 = minContrastRatio;
        if (calculateContrast(setAlphaComponent(foreground, 255), background) < d2) {
            return -1;
        }
        int i2 = 0;
        for (int i3 = 0; i3 <= 10 && i - i2 > 1; i3++) {
            int i4 = (i2 + i) / 2;
            if (calculateContrast(setAlphaComponent(foreground, i4), background) < d2) {
                i2 = i4;
            } else {
                i = i4;
            }
        }
        return i;
    }

    @VisibleForTesting
    public static float circularInterpolate(float a2, float b2, float f2) {
        if (Math.abs(b2 - a2) > 180.0f) {
            if (b2 > a2) {
                a2 += 360.0f;
            } else {
                b2 += 360.0f;
            }
        }
        return (((b2 - a2) * f2) + a2) % 360.0f;
    }

    public static void colorToHSL(@ColorInt int color, @NonNull float[] outHsl) {
        RGBToHSL(Color.red(color), Color.green(color), Color.blue(color), outHsl);
    }

    public static void colorToLAB(@ColorInt int color, @NonNull double[] outLab) {
        RGBToLAB(Color.red(color), Color.green(color), Color.blue(color), outLab);
    }

    public static void colorToXYZ(@ColorInt int color, @NonNull double[] outXyz) {
        RGBToXYZ(Color.red(color), Color.green(color), Color.blue(color), outXyz);
    }

    public static int compositeAlpha(int foregroundAlpha, int backgroundAlpha) {
        return 255 - (((255 - foregroundAlpha) * (255 - backgroundAlpha)) / 255);
    }

    public static int compositeColors(@ColorInt int foreground, @ColorInt int background) {
        int iAlpha = Color.alpha(background);
        int iAlpha2 = Color.alpha(foreground);
        int iCompositeAlpha = compositeAlpha(iAlpha2, iAlpha);
        return Color.argb(iCompositeAlpha, compositeComponent(Color.red(foreground), iAlpha2, Color.red(background), iAlpha, iCompositeAlpha), compositeComponent(Color.green(foreground), iAlpha2, Color.green(background), iAlpha, iCompositeAlpha), compositeComponent(Color.blue(foreground), iAlpha2, Color.blue(background), iAlpha, iCompositeAlpha));
    }

    public static int compositeComponent(int fgC, int fgA, int bgC, int bgA, int a2) {
        if (a2 == 0) {
            return 0;
        }
        return (((255 - fgA) * (bgC * bgA)) + ((fgC * 255) * fgA)) / (a2 * 255);
    }

    public static float constrain(float amount, float low, float high) {
        if (amount < low) {
            return low;
        }
        return amount > high ? high : amount;
    }

    public static int constrain(int amount, int low, int high) {
        if (amount < low) {
            return low;
        }
        return amount > high ? high : amount;
    }

    public static double distanceEuclidean(@NonNull double[] labX, @NonNull double[] labY) {
        return Math.sqrt(Math.pow(labX[2] - labY[2], 2.0d) + Math.pow(labX[1] - labY[1], 2.0d) + Math.pow(labX[0] - labY[0], 2.0d));
    }

    public static double[] getTempDouble3Array() {
        double[] dArr = TEMP_ARRAY.get();
        if (dArr != null) {
            return dArr;
        }
        double[] dArr2 = new double[3];
        TEMP_ARRAY.set(dArr2);
        return dArr2;
    }

    public static double pivotXyzComponent(double component) {
        return component > 0.008856d ? Math.pow(component, 0.3333333333333333d) : ((component * 903.3d) + 16.0d) / 116.0d;
    }

    @ColorInt
    public static int setAlphaComponent(@ColorInt int color, @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG, to = 255) int alpha) {
        if (alpha < 0 || alpha > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (color & ViewCompat.MEASURED_SIZE_MASK) | (alpha << 24);
    }

    @NonNull
    @RequiresApi(26)
    public static Color compositeColors(@NonNull Color foreground, @NonNull Color background) {
        if (Objects.equals(foreground.getModel(), background.getModel())) {
            if (!Objects.equals(background.getColorSpace(), foreground.getColorSpace())) {
                foreground = foreground.convert(background.getColorSpace());
            }
            float[] components = foreground.getComponents();
            float[] components2 = background.getComponents();
            float fAlpha = foreground.alpha();
            float fAlpha2 = (1.0f - fAlpha) * background.alpha();
            int componentCount = background.getComponentCount() - 1;
            components2[componentCount] = fAlpha + fAlpha2;
            if (components2[componentCount] > 0.0f) {
                fAlpha /= components2[componentCount];
                fAlpha2 /= components2[componentCount];
            }
            for (int i = 0; i < componentCount; i++) {
                components2[i] = (components2[i] * fAlpha2) + (components[i] * fAlpha);
            }
            return Color.valueOf(components2, background.getColorSpace());
        }
        StringBuilder sbA = a.a("Color models must match (");
        sbA.append(foreground.getModel());
        sbA.append(" vs. ");
        sbA.append(background.getModel());
        sbA.append(")");
        throw new IllegalArgumentException(sbA.toString());
    }
}
