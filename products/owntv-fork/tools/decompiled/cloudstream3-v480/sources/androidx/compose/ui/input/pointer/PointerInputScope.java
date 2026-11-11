package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.FontScaling;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: SuspendingPointerInputFilter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J=\u0010\u0016\u001a\u0002H\u0017\"\u0004\b\u0000\u0010\u00172'\u0010\u0018\u001a#\b\u0001\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00170\u001b\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u0019¢\u0006\u0002\b\u001dH¦@¢\u0006\u0002\u0010\u001eR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0005R\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR*\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8V@VX\u0097\u000e¢\u0006\u0012\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001fÀ\u0006\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Landroidx/compose/ui/unit/Density;", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "()J", "extendedTouchPadding", "Landroidx/compose/ui/geometry/Size;", "getExtendedTouchPadding-NH-jbRc", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "_", "", "interceptOutOfBoundsChildEvents", "getInterceptOutOfBoundsChildEvents$annotations", "()V", "getInterceptOutOfBoundsChildEvents", "()Z", "setInterceptOutOfBoundsChildEvents", "(Z)V", "awaitPointerEventScope", "R", "block", "Lkotlin/Function2;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface PointerInputScope extends Density {
    <R> Object awaitPointerEventScope(Function2<? super AwaitPointerEventScope, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super R> continuation);

    /* JADX INFO: renamed from: getExtendedTouchPadding-NH-jbRc */
    long mo397getExtendedTouchPaddingNHjbRc();

    boolean getInterceptOutOfBoundsChildEvents();

    /* JADX INFO: renamed from: getSize-YbymL2g */
    long mo398getSizeYbymL2g();

    ViewConfiguration getViewConfiguration();

    void setInterceptOutOfBoundsChildEvents(boolean z);

    /* JADX INFO: renamed from: androidx.compose.ui.input.pointer.PointerInputScope$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: SuspendingPointerInputFilter.kt */
    public final /* synthetic */ class CC {
        public static boolean $default$getInterceptOutOfBoundsChildEvents(PointerInputScope _this) {
            return false;
        }

        public static void $default$setInterceptOutOfBoundsChildEvents(PointerInputScope _this, boolean z) {
        }

        /* JADX INFO: renamed from: $default$getExtendedTouchPadding-NH-jbRc, reason: not valid java name */
        public static long m5475$default$getExtendedTouchPaddingNHjbRc(PointerInputScope _this) {
            return Size.INSTANCE.m3948getZeroNHjbRc();
        }
    }

    /* JADX INFO: compiled from: SuspendingPointerInputFilter.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void getInterceptOutOfBoundsChildEvents$annotations() {
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m5490roundToPxR2X_6o(PointerInputScope pointerInputScope, long j) {
            return Density.CC.m6854$default$roundToPxR2X_6o(pointerInputScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m5491roundToPx0680j_4(PointerInputScope pointerInputScope, float f) {
            return Density.CC.m6855$default$roundToPx0680j_4(pointerInputScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m5492toDpGaN1DYA(PointerInputScope pointerInputScope, long j) {
            return FontScaling.CC.m6999$default$toDpGaN1DYA(pointerInputScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m5493toDpu2uoSUM(PointerInputScope pointerInputScope, float f) {
            return Density.CC.m6856$default$toDpu2uoSUM(pointerInputScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m5494toDpu2uoSUM(PointerInputScope pointerInputScope, int i) {
            return Density.CC.m6857$default$toDpu2uoSUM((Density) pointerInputScope, i);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m5495toDpSizekrfVVM(PointerInputScope pointerInputScope, long j) {
            return Density.CC.m6858$default$toDpSizekrfVVM(pointerInputScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m5496toPxR2X_6o(PointerInputScope pointerInputScope, long j) {
            return Density.CC.m6859$default$toPxR2X_6o(pointerInputScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m5497toPx0680j_4(PointerInputScope pointerInputScope, float f) {
            return Density.CC.m6860$default$toPx0680j_4(pointerInputScope, f);
        }

        @Deprecated
        public static Rect toRect(PointerInputScope pointerInputScope, DpRect dpRect) {
            return Density.CC.$default$toRect(pointerInputScope, dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m5498toSizeXkaWNTQ(PointerInputScope pointerInputScope, long j) {
            return Density.CC.m6861$default$toSizeXkaWNTQ(pointerInputScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m5499toSp0xMU5do(PointerInputScope pointerInputScope, float f) {
            return FontScaling.CC.m7000$default$toSp0xMU5do(pointerInputScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m5500toSpkPz2Gy4(PointerInputScope pointerInputScope, float f) {
            return Density.CC.m6862$default$toSpkPz2Gy4(pointerInputScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m5501toSpkPz2Gy4(PointerInputScope pointerInputScope, int i) {
            return Density.CC.m6863$default$toSpkPz2Gy4((Density) pointerInputScope, i);
        }

        @Deprecated
        /* JADX INFO: renamed from: getExtendedTouchPadding-NH-jbRc, reason: not valid java name */
        public static long m5489getExtendedTouchPaddingNHjbRc(PointerInputScope pointerInputScope) {
            return CC.m5475$default$getExtendedTouchPaddingNHjbRc(pointerInputScope);
        }

        @Deprecated
        public static boolean getInterceptOutOfBoundsChildEvents(PointerInputScope pointerInputScope) {
            return CC.$default$getInterceptOutOfBoundsChildEvents(pointerInputScope);
        }

        @Deprecated
        public static void setInterceptOutOfBoundsChildEvents(PointerInputScope pointerInputScope, boolean z) {
            CC.$default$setInterceptOutOfBoundsChildEvents(pointerInputScope, z);
        }
    }
}
