package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.FontScaling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: TapGestureDetector.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H¦@¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u0006H¦@¢\u0006\u0002\u0010\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/PressGestureScope;", "Landroidx/compose/ui/unit/Density;", "awaitRelease", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryAwaitRelease", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface PressGestureScope extends Density {
    Object awaitRelease(Continuation<? super Unit> continuation);

    Object tryAwaitRelease(Continuation<? super Boolean> continuation);

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.PressGestureScope$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: TapGestureDetector.kt */
    public final /* synthetic */ class CC {
    }

    /* JADX INFO: compiled from: TapGestureDetector.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m501roundToPxR2X_6o(PressGestureScope pressGestureScope, long j) {
            return Density.CC.m6854$default$roundToPxR2X_6o(pressGestureScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m502roundToPx0680j_4(PressGestureScope pressGestureScope, float f) {
            return Density.CC.m6855$default$roundToPx0680j_4(pressGestureScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m503toDpGaN1DYA(PressGestureScope pressGestureScope, long j) {
            return FontScaling.CC.m6999$default$toDpGaN1DYA(pressGestureScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m504toDpu2uoSUM(PressGestureScope pressGestureScope, float f) {
            return Density.CC.m6856$default$toDpu2uoSUM(pressGestureScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m505toDpu2uoSUM(PressGestureScope pressGestureScope, int i) {
            return Density.CC.m6857$default$toDpu2uoSUM((Density) pressGestureScope, i);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m506toDpSizekrfVVM(PressGestureScope pressGestureScope, long j) {
            return Density.CC.m6858$default$toDpSizekrfVVM(pressGestureScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m507toPxR2X_6o(PressGestureScope pressGestureScope, long j) {
            return Density.CC.m6859$default$toPxR2X_6o(pressGestureScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m508toPx0680j_4(PressGestureScope pressGestureScope, float f) {
            return Density.CC.m6860$default$toPx0680j_4(pressGestureScope, f);
        }

        @Deprecated
        public static Rect toRect(PressGestureScope pressGestureScope, DpRect dpRect) {
            return Density.CC.$default$toRect(pressGestureScope, dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m509toSizeXkaWNTQ(PressGestureScope pressGestureScope, long j) {
            return Density.CC.m6861$default$toSizeXkaWNTQ(pressGestureScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m510toSp0xMU5do(PressGestureScope pressGestureScope, float f) {
            return FontScaling.CC.m7000$default$toSp0xMU5do(pressGestureScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m511toSpkPz2Gy4(PressGestureScope pressGestureScope, float f) {
            return Density.CC.m6862$default$toSpkPz2Gy4(pressGestureScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m512toSpkPz2Gy4(PressGestureScope pressGestureScope, int i) {
            return Density.CC.m6863$default$toSpkPz2Gy4((Density) pressGestureScope, i);
        }
    }
}
