package androidx.compose.ui.graphics;

import android.graphics.BitmapShader;
import android.graphics.ComposeShader;
import android.graphics.Shader;
import android.os.Build;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: AndroidShader.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aI\u0010\u0002\u001a\u00060\u0001j\u0002`\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001aI\u0010\u0010\u001a\u00060\u0001j\u0002`\u00032\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u000b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a9\u0010\u0015\u001a\u00060\u0001j\u0002`\u00032\u0006\u0010\u0011\u001a\u00020\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bH\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a+\u0010\u0018\u001a\u00060\u0001j\u0002`\u00032\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\rH\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u0016\u0010\u001f\u001a\u00020 2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0001\u001a\u001e\u0010!\u001a\u00020\"2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010#\u001a\u00020 H\u0001\u001a0\u0010$\u001a\u0004\u0018\u00010%2\u000e\u0010&\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010#\u001a\u00020 H\u0001\u001a&\u0010'\u001a\u00020(2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bH\u0002\u001a3\u0010)\u001a\u00060\u0001j\u0002`\u00032\n\u0010*\u001a\u00060\u0001j\u0002`\u00032\n\u0010+\u001a\u00060\u0001j\u0002`\u00032\u0006\u0010,\u001a\u00020-H\u0000¢\u0006\u0004\b.\u0010/*\n\u0010\u0000\"\u00020\u00012\u00020\u0001¨\u00060"}, d2 = {"Shader", "Landroid/graphics/Shader;", "ActualLinearGradientShader", "Landroidx/compose/ui/graphics/Shader;", TypedValues.TransitionType.S_FROM, "Landroidx/compose/ui/geometry/Offset;", TypedValues.TransitionType.S_TO, "colors", "", "Landroidx/compose/ui/graphics/Color;", "colorStops", "", "tileMode", "Landroidx/compose/ui/graphics/TileMode;", "ActualLinearGradientShader-VjE6UOU", "(JJLjava/util/List;Ljava/util/List;I)Landroid/graphics/Shader;", "ActualRadialGradientShader", TtmlNode.CENTER, "radius", "ActualRadialGradientShader-8uybcMk", "(JFLjava/util/List;Ljava/util/List;I)Landroid/graphics/Shader;", "ActualSweepGradientShader", "ActualSweepGradientShader-9KIMszo", "(JLjava/util/List;Ljava/util/List;)Landroid/graphics/Shader;", "ActualImageShader", "image", "Landroidx/compose/ui/graphics/ImageBitmap;", "tileModeX", "tileModeY", "ActualImageShader-F49vj9s", "(Landroidx/compose/ui/graphics/ImageBitmap;II)Landroid/graphics/Shader;", "countTransparentColors", "", "makeTransparentColors", "", "numTransparentColors", "makeTransparentStops", "", "stops", "validateColorStops", "", "ActualCompositeShader", "dst", "src", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "ActualCompositeShader-7EN7VTw", "(Landroid/graphics/Shader;Landroid/graphics/Shader;I)Landroid/graphics/Shader;", "ui-graphics_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AndroidShader_androidKt {
    /* JADX INFO: renamed from: ActualLinearGradientShader-VjE6UOU, reason: not valid java name */
    public static final Shader m4015ActualLinearGradientShaderVjE6UOU(long j, long j2, List<Color> list, List<Float> list2, int i) {
        validateColorStops(list, list2);
        int iCountTransparentColors = countTransparentColors(list);
        return new android.graphics.LinearGradient(Float.intBitsToFloat((int) (j >> 32)), Float.intBitsToFloat((int) (j & 4294967295L)), Float.intBitsToFloat((int) (j2 >> 32)), Float.intBitsToFloat((int) (j2 & 4294967295L)), makeTransparentColors(list, iCountTransparentColors), makeTransparentStops(list2, list, iCountTransparentColors), AndroidTileMode_androidKt.m4019toAndroidTileMode0vamqd0(i));
    }

    /* JADX INFO: renamed from: ActualRadialGradientShader-8uybcMk, reason: not valid java name */
    public static final Shader m4016ActualRadialGradientShader8uybcMk(long j, float f, List<Color> list, List<Float> list2, int i) {
        validateColorStops(list, list2);
        int iCountTransparentColors = countTransparentColors(list);
        return new android.graphics.RadialGradient(Float.intBitsToFloat((int) (j >> 32)), Float.intBitsToFloat((int) (j & 4294967295L)), f, makeTransparentColors(list, iCountTransparentColors), makeTransparentStops(list2, list, iCountTransparentColors), AndroidTileMode_androidKt.m4019toAndroidTileMode0vamqd0(i));
    }

    /* JADX INFO: renamed from: ActualSweepGradientShader-9KIMszo, reason: not valid java name */
    public static final Shader m4017ActualSweepGradientShader9KIMszo(long j, List<Color> list, List<Float> list2) {
        validateColorStops(list, list2);
        int iCountTransparentColors = countTransparentColors(list);
        return new android.graphics.SweepGradient(Float.intBitsToFloat((int) (j >> 32)), Float.intBitsToFloat((int) (j & 4294967295L)), makeTransparentColors(list, iCountTransparentColors), makeTransparentStops(list2, list, iCountTransparentColors));
    }

    /* JADX INFO: renamed from: ActualImageShader-F49vj9s, reason: not valid java name */
    public static final Shader m4014ActualImageShaderF49vj9s(ImageBitmap imageBitmap, int i, int i2) {
        return new BitmapShader(AndroidImageBitmap_androidKt.asAndroidBitmap(imageBitmap), AndroidTileMode_androidKt.m4019toAndroidTileMode0vamqd0(i), AndroidTileMode_androidKt.m4019toAndroidTileMode0vamqd0(i2));
    }

    public static final int countTransparentColors(List<Color> list) {
        int i = 0;
        if (Build.VERSION.SDK_INT >= 26) {
            return 0;
        }
        int lastIndex = CollectionsKt.getLastIndex(list);
        for (int i2 = 1; i2 < lastIndex; i2++) {
            if (Color.m4154getAlphaimpl(list.get(i2).m4162unboximpl()) == 0.0f) {
                i++;
            }
        }
        return i;
    }

    public static final int[] makeTransparentColors(List<Color> list, int i) {
        int i2;
        int i3 = 0;
        if (Build.VERSION.SDK_INT >= 26) {
            int size = list.size();
            int[] iArr = new int[size];
            while (i3 < size) {
                iArr[i3] = ColorKt.m4206toArgb8_81llA(list.get(i3).m4162unboximpl());
                i3++;
            }
            return iArr;
        }
        int[] iArr2 = new int[list.size() + i];
        int lastIndex = CollectionsKt.getLastIndex(list);
        int size2 = list.size();
        int i4 = 0;
        while (i3 < size2) {
            long jM4162unboximpl = list.get(i3).m4162unboximpl();
            if (Color.m4154getAlphaimpl(jM4162unboximpl) == 0.0f) {
                if (i3 == 0) {
                    i2 = i4 + 1;
                    iArr2[i4] = ColorKt.m4206toArgb8_81llA(Color.m4151copywmQWz5c$default(list.get(1).m4162unboximpl(), 0.0f, 0.0f, 0.0f, 0.0f, 14, null));
                } else if (i3 == lastIndex) {
                    i2 = i4 + 1;
                    iArr2[i4] = ColorKt.m4206toArgb8_81llA(Color.m4151copywmQWz5c$default(list.get(i3 - 1).m4162unboximpl(), 0.0f, 0.0f, 0.0f, 0.0f, 14, null));
                } else {
                    int i5 = i4 + 1;
                    iArr2[i4] = ColorKt.m4206toArgb8_81llA(Color.m4151copywmQWz5c$default(list.get(i3 - 1).m4162unboximpl(), 0.0f, 0.0f, 0.0f, 0.0f, 14, null));
                    i4 += 2;
                    iArr2[i5] = ColorKt.m4206toArgb8_81llA(Color.m4151copywmQWz5c$default(list.get(i3 + 1).m4162unboximpl(), 0.0f, 0.0f, 0.0f, 0.0f, 14, null));
                }
                i4 = i2;
            } else {
                iArr2[i4] = ColorKt.m4206toArgb8_81llA(jM4162unboximpl);
                i4++;
            }
            i3++;
        }
        return iArr2;
    }

    public static final float[] makeTransparentStops(List<Float> list, List<Color> list2, int i) {
        if (i == 0) {
            if (list != null) {
                return CollectionsKt.toFloatArray(list);
            }
            return null;
        }
        float[] fArr = new float[list2.size() + i];
        fArr[0] = list != null ? list.get(0).floatValue() : 0.0f;
        int lastIndex = CollectionsKt.getLastIndex(list2);
        int i2 = 1;
        for (int i3 = 1; i3 < lastIndex; i3++) {
            long jM4162unboximpl = list2.get(i3).m4162unboximpl();
            float fFloatValue = list != null ? list.get(i3).floatValue() : i3 / CollectionsKt.getLastIndex(list2);
            int i4 = i2 + 1;
            fArr[i2] = fFloatValue;
            if (Color.m4154getAlphaimpl(jM4162unboximpl) == 0.0f) {
                i2 += 2;
                fArr[i4] = fFloatValue;
            } else {
                i2 = i4;
            }
        }
        fArr[i2] = list != null ? list.get(CollectionsKt.getLastIndex(list2)).floatValue() : 1.0f;
        return fArr;
    }

    private static final void validateColorStops(List<Color> list, List<Float> list2) {
        if (list2 == null) {
            if (list.size() < 2) {
                throw new IllegalArgumentException("colors must have length of at least 2 if colorStops is omitted.");
            }
        } else if (list.size() != list2.size()) {
            throw new IllegalArgumentException("colors and colorStops arguments must have equal length.");
        }
    }

    /* JADX INFO: renamed from: ActualCompositeShader-7EN7VTw, reason: not valid java name */
    public static final Shader m4013ActualCompositeShader7EN7VTw(Shader shader, Shader shader2, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            Api26Bitmap$$ExternalSyntheticApiModelOutline0.m4032m();
            return Api26Bitmap$$ExternalSyntheticApiModelOutline0.m(shader, shader2, AndroidBlendMode_androidKt.m3962toAndroidBlendModes9anfk8(i));
        }
        return new ComposeShader(shader, shader2, AndroidBlendMode_androidKt.m3963toPorterDuffModes9anfk8(i));
    }
}
