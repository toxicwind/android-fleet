package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.FontScaling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: ContentDrawScope.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "drawContent", "", "ui-graphics_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface ContentDrawScope extends DrawScope {
    void drawContent();

    /* JADX INFO: renamed from: androidx.compose.ui.graphics.drawscope.ContentDrawScope$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: ContentDrawScope.kt */
    public final /* synthetic */ class CC {
    }

    /* JADX INFO: compiled from: ContentDrawScope.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* JADX INFO: renamed from: drawImage-AZ2fEMs, reason: not valid java name */
        public static void m4679drawImageAZ2fEMs(ContentDrawScope contentDrawScope, ImageBitmap imageBitmap, long j, long j2, long j3, long j4, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2) {
            DrawScope.CC.m4721drawImageAZ2fEMs$default(contentDrawScope, imageBitmap, j, j2, j3, j4, f, drawStyle, colorFilter, i, 0, 512, null);
        }

        @Deprecated
        /* JADX INFO: renamed from: getCenter-F1C5BW0, reason: not valid java name */
        public static long m4680getCenterF1C5BW0(ContentDrawScope contentDrawScope) {
            return DrawScope.CC.m4696$default$getCenterF1C5BW0(contentDrawScope);
        }

        @Deprecated
        /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
        public static long m4681getSizeNHjbRc(ContentDrawScope contentDrawScope) {
            return DrawScope.CC.m4697$default$getSizeNHjbRc(contentDrawScope);
        }

        @Deprecated
        /* JADX INFO: renamed from: record-JVtK1S4, reason: not valid java name */
        public static void m4682recordJVtK1S4(ContentDrawScope contentDrawScope, GraphicsLayer graphicsLayer, long j, Function1<? super DrawScope, Unit> function1) {
            DrawScope.CC.m4698$default$recordJVtK1S4(contentDrawScope, graphicsLayer, j, function1);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m4683roundToPxR2X_6o(ContentDrawScope contentDrawScope, long j) {
            return Density.CC.m6854$default$roundToPxR2X_6o(contentDrawScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m4684roundToPx0680j_4(ContentDrawScope contentDrawScope, float f) {
            return Density.CC.m6855$default$roundToPx0680j_4(contentDrawScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m4685toDpGaN1DYA(ContentDrawScope contentDrawScope, long j) {
            return FontScaling.CC.m6999$default$toDpGaN1DYA(contentDrawScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4686toDpu2uoSUM(ContentDrawScope contentDrawScope, float f) {
            return Density.CC.m6856$default$toDpu2uoSUM(contentDrawScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4687toDpu2uoSUM(ContentDrawScope contentDrawScope, int i) {
            return Density.CC.m6857$default$toDpu2uoSUM((Density) contentDrawScope, i);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m4688toDpSizekrfVVM(ContentDrawScope contentDrawScope, long j) {
            return Density.CC.m6858$default$toDpSizekrfVVM(contentDrawScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m4689toPxR2X_6o(ContentDrawScope contentDrawScope, long j) {
            return Density.CC.m6859$default$toPxR2X_6o(contentDrawScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m4690toPx0680j_4(ContentDrawScope contentDrawScope, float f) {
            return Density.CC.m6860$default$toPx0680j_4(contentDrawScope, f);
        }

        @Deprecated
        public static Rect toRect(ContentDrawScope contentDrawScope, DpRect dpRect) {
            return Density.CC.$default$toRect(contentDrawScope, dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m4691toSizeXkaWNTQ(ContentDrawScope contentDrawScope, long j) {
            return Density.CC.m6861$default$toSizeXkaWNTQ(contentDrawScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m4692toSp0xMU5do(ContentDrawScope contentDrawScope, float f) {
            return FontScaling.CC.m7000$default$toSp0xMU5do(contentDrawScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4693toSpkPz2Gy4(ContentDrawScope contentDrawScope, float f) {
            return Density.CC.m6862$default$toSpkPz2Gy4(contentDrawScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4694toSpkPz2Gy4(ContentDrawScope contentDrawScope, int i) {
            return Density.CC.m6863$default$toSpkPz2Gy4((Density) contentDrawScope, i);
        }
    }
}
