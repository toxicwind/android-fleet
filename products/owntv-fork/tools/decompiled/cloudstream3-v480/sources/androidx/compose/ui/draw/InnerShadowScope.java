package androidx.compose.ui.draw;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.FontScaling;
import kotlin.Metadata;

/* JADX INFO: compiled from: Shadow.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0002À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/draw/InnerShadowScope;", "Landroidx/compose/ui/draw/ShadowScope;", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface InnerShadowScope extends ShadowScope {

    /* JADX INFO: renamed from: androidx.compose.ui.draw.InnerShadowScope$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Shadow.kt */
    public final /* synthetic */ class CC {
    }

    /* JADX INFO: compiled from: Shadow.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m3692roundToPxR2X_6o(InnerShadowScope innerShadowScope, long j) {
            return Density.CC.m6854$default$roundToPxR2X_6o(innerShadowScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m3693roundToPx0680j_4(InnerShadowScope innerShadowScope, float f) {
            return Density.CC.m6855$default$roundToPx0680j_4(innerShadowScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m3694toDpGaN1DYA(InnerShadowScope innerShadowScope, long j) {
            return FontScaling.CC.m6999$default$toDpGaN1DYA(innerShadowScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m3695toDpu2uoSUM(InnerShadowScope innerShadowScope, float f) {
            return Density.CC.m6856$default$toDpu2uoSUM(innerShadowScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m3696toDpu2uoSUM(InnerShadowScope innerShadowScope, int i) {
            return Density.CC.m6857$default$toDpu2uoSUM((Density) innerShadowScope, i);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m3697toDpSizekrfVVM(InnerShadowScope innerShadowScope, long j) {
            return Density.CC.m6858$default$toDpSizekrfVVM(innerShadowScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m3698toPxR2X_6o(InnerShadowScope innerShadowScope, long j) {
            return Density.CC.m6859$default$toPxR2X_6o(innerShadowScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m3699toPx0680j_4(InnerShadowScope innerShadowScope, float f) {
            return Density.CC.m6860$default$toPx0680j_4(innerShadowScope, f);
        }

        @Deprecated
        public static Rect toRect(InnerShadowScope innerShadowScope, DpRect dpRect) {
            return Density.CC.$default$toRect(innerShadowScope, dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m3700toSizeXkaWNTQ(InnerShadowScope innerShadowScope, long j) {
            return Density.CC.m6861$default$toSizeXkaWNTQ(innerShadowScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m3701toSp0xMU5do(InnerShadowScope innerShadowScope, float f) {
            return FontScaling.CC.m7000$default$toSp0xMU5do(innerShadowScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m3702toSpkPz2Gy4(InnerShadowScope innerShadowScope, float f) {
            return Density.CC.m6862$default$toSpkPz2Gy4(innerShadowScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m3703toSpkPz2Gy4(InnerShadowScope innerShadowScope, int i) {
            return Density.CC.m6863$default$toSpkPz2Gy4((Density) innerShadowScope, i);
        }
    }
}
