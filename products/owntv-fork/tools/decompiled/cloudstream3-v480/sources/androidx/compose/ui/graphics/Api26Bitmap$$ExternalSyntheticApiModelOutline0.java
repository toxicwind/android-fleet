package androidx.compose.ui.graphics;

import android.graphics.ColorSpace;
import android.graphics.ComposeShader;
import android.graphics.Shader;
import java.util.function.DoubleUnaryOperator;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Api26Bitmap$$ExternalSyntheticApiModelOutline0 {
    public static /* synthetic */ android.graphics.BlendModeColorFilter m(int i, android.graphics.BlendMode blendMode) {
        return new android.graphics.BlendModeColorFilter(i, blendMode);
    }

    public static /* bridge */ /* synthetic */ android.graphics.BlendModeColorFilter m(Object obj) {
        return (android.graphics.BlendModeColorFilter) obj;
    }

    public static /* synthetic */ ColorSpace.Rgb.TransferParameters m(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
        return new ColorSpace.Rgb.TransferParameters(d, d2, d3, d4, d5, d6, d7);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ColorSpace.Rgb m4026m(Object obj) {
        return (ColorSpace.Rgb) obj;
    }

    public static /* synthetic */ ColorSpace.Rgb m(String str, float[] fArr, float[] fArr2, ColorSpace.Rgb.TransferParameters transferParameters) {
        return new ColorSpace.Rgb(str, fArr, fArr2, transferParameters);
    }

    public static /* synthetic */ ColorSpace.Rgb m(String str, float[] fArr, float[] fArr2, DoubleUnaryOperator doubleUnaryOperator, DoubleUnaryOperator doubleUnaryOperator2, float f, float f2) {
        return new ColorSpace.Rgb(str, fArr, fArr2, doubleUnaryOperator, doubleUnaryOperator2, f, f2);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ColorSpace m4028m(Object obj) {
        return (ColorSpace) obj;
    }

    public static /* synthetic */ ComposeShader m(Shader shader, Shader shader2, android.graphics.BlendMode blendMode) {
        return new ComposeShader(shader, shader2, blendMode);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m4032m() {
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m4035m(Object obj) {
        return obj instanceof android.graphics.BlendModeColorFilter;
    }

    /* JADX INFO: renamed from: m$1, reason: collision with other method in class */
    public static /* synthetic */ void m4040m$1() {
    }

    public static /* bridge */ /* synthetic */ boolean m$1(Object obj) {
        return obj instanceof ColorSpace.Rgb;
    }

    /* JADX INFO: renamed from: m$2, reason: collision with other method in class */
    public static /* synthetic */ void m4050m$2() {
    }

    /* JADX INFO: renamed from: m$3, reason: collision with other method in class */
    public static /* synthetic */ void m4052m$3() {
    }
}
