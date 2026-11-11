package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import kotlin.Metadata;

/* JADX INFO: compiled from: Density.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0013\u0010\b\u001a\u00020\u0003*\u00020\tH\u0017¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r*\u00020\tH\u0017¢\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\b\u001a\u00020\u0003*\u00020\u0010H\u0017¢\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\f\u001a\u00020\r*\u00020\u0010H\u0017¢\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\t*\u00020\rH\u0017¢\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u0010*\u00020\rH\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u0013\u0010\u0015\u001a\u00020\t*\u00020\u0003H\u0017¢\u0006\u0004\b\u0016\u0010\u000bJ\u0013\u0010\u0018\u001a\u00020\u0010*\u00020\u0003H\u0017¢\u0006\u0004\b\u0019\u0010\u001bJ\f\u0010\u001c\u001a\u00020\u001d*\u00020\u001eH\u0017J\u0013\u0010\u001f\u001a\u00020 *\u00020!H\u0017¢\u0006\u0004\b\"\u0010#J\u0013\u0010$\u001a\u00020!*\u00020 H\u0017¢\u0006\u0004\b%\u0010#R\u001a\u0010\u0002\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006&À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/unit/FontScaling;", "density", "", "getDensity$annotations", "()V", "getDensity", "()F", "toPx", "Landroidx/compose/ui/unit/Dp;", "toPx-0680j_4", "(F)F", "roundToPx", "", "roundToPx-0680j_4", "(F)I", "Landroidx/compose/ui/unit/TextUnit;", "toPx--R2X_6o", "(J)F", "roundToPx--R2X_6o", "(J)I", "toDp", "toDp-u2uoSUM", "(I)F", "toSp", "toSp-kPz2Gy4", "(I)J", "(F)J", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "toSize", "Landroidx/compose/ui/geometry/Size;", "Landroidx/compose/ui/unit/DpSize;", "toSize-XkaWNTQ", "(J)J", "toDpSize", "toDpSize-k-rfVVM", "ui-unit"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface Density extends FontScaling {
    float getDensity();

    /* JADX INFO: renamed from: roundToPx--R2X_6o */
    int mo399roundToPxR2X_6o(long j);

    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    int mo400roundToPx0680j_4(float f);

    /* JADX INFO: renamed from: toDp-u2uoSUM */
    float mo402toDpu2uoSUM(float f);

    /* JADX INFO: renamed from: toDp-u2uoSUM */
    float mo403toDpu2uoSUM(int i);

    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    long mo404toDpSizekrfVVM(long j);

    /* JADX INFO: renamed from: toPx--R2X_6o */
    float mo405toPxR2X_6o(long j);

    /* JADX INFO: renamed from: toPx-0680j_4 */
    float mo406toPx0680j_4(float f);

    Rect toRect(DpRect dpRect);

    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    long mo407toSizeXkaWNTQ(long j);

    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    long mo409toSpkPz2Gy4(float f);

    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    long mo410toSpkPz2Gy4(int i);

    /* JADX INFO: renamed from: androidx.compose.ui.unit.Density$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Density.kt */
    public final /* synthetic */ class CC {
        /* JADX INFO: renamed from: $default$toPx-0680j_4, reason: not valid java name */
        public static float m6860$default$toPx0680j_4(Density _this, float f) {
            return f * _this.getDensity();
        }

        /* JADX INFO: renamed from: $default$roundToPx-0680j_4, reason: not valid java name */
        public static int m6855$default$roundToPx0680j_4(Density _this, float f) {
            float fMo406toPx0680j_4 = _this.mo406toPx0680j_4(f);
            if (Float.isInfinite(fMo406toPx0680j_4)) {
                return Integer.MAX_VALUE;
            }
            return Math.round(fMo406toPx0680j_4);
        }

        /* JADX INFO: renamed from: $default$toPx--R2X_6o, reason: not valid java name */
        public static float m6859$default$toPxR2X_6o(Density _this, long j) {
            if (!TextUnitType.m7116equalsimpl0(TextUnit.m7087getTypeUIouoOA(j), TextUnitType.INSTANCE.m7121getSpUIouoOA())) {
                InlineClassHelperKt.throwIllegalStateException("Only Sp can convert to Px");
            }
            return _this.mo406toPx0680j_4(_this.mo401toDpGaN1DYA(j));
        }

        /* JADX INFO: renamed from: $default$roundToPx--R2X_6o, reason: not valid java name */
        public static int m6854$default$roundToPxR2X_6o(Density _this, long j) {
            return Math.round(_this.mo405toPxR2X_6o(j));
        }

        /* JADX INFO: renamed from: $default$toDp-u2uoSUM, reason: not valid java name */
        public static float m6857$default$toDpu2uoSUM(Density _this, int i) {
            return Dp.m6890constructorimpl(i / _this.getDensity());
        }

        /* JADX INFO: renamed from: $default$toSp-kPz2Gy4, reason: not valid java name */
        public static long m6863$default$toSpkPz2Gy4(Density _this, int i) {
            return _this.mo408toSp0xMU5do(_this.mo403toDpu2uoSUM(i));
        }

        /* JADX INFO: renamed from: $default$toDp-u2uoSUM, reason: not valid java name */
        public static float m6856$default$toDpu2uoSUM(Density _this, float f) {
            return Dp.m6890constructorimpl(f / _this.getDensity());
        }

        /* JADX INFO: renamed from: $default$toSp-kPz2Gy4, reason: not valid java name */
        public static long m6862$default$toSpkPz2Gy4(Density _this, float f) {
            return _this.mo408toSp0xMU5do(_this.mo402toDpu2uoSUM(f));
        }

        public static Rect $default$toRect(Density _this, DpRect dpRect) {
            return new Rect(_this.mo406toPx0680j_4(dpRect.m6973getLeftD9Ej5fM()), _this.mo406toPx0680j_4(dpRect.m6975getTopD9Ej5fM()), _this.mo406toPx0680j_4(dpRect.m6974getRightD9Ej5fM()), _this.mo406toPx0680j_4(dpRect.m6972getBottomD9Ej5fM()));
        }

        /* JADX INFO: renamed from: $default$toSize-XkaWNTQ, reason: not valid java name */
        public static long m6861$default$toSizeXkaWNTQ(Density _this, long j) {
            if (j == androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats) {
                return Size.INSTANCE.m3947getUnspecifiedNHjbRc();
            }
            float fMo406toPx0680j_4 = _this.mo406toPx0680j_4(DpSize.m6988getWidthD9Ej5fM(j));
            float fMo406toPx0680j_5 = _this.mo406toPx0680j_4(DpSize.m6986getHeightD9Ej5fM(j));
            return Size.m3930constructorimpl((((long) Float.floatToRawIntBits(fMo406toPx0680j_4)) << 32) | (((long) Float.floatToRawIntBits(fMo406toPx0680j_5)) & 4294967295L));
        }

        /* JADX INFO: renamed from: $default$toDpSize-k-rfVVM, reason: not valid java name */
        public static long m6858$default$toDpSizekrfVVM(Density _this, long j) {
            return j != androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats ? DpKt.m6912DpSizeYgX7TsA(_this.mo402toDpu2uoSUM(Float.intBitsToFloat((int) (j >> 32))), _this.mo402toDpu2uoSUM(Float.intBitsToFloat((int) (j & 4294967295L)))) : DpSize.INSTANCE.m6997getUnspecifiedMYxV2XQ();
        }
    }

    /* JADX INFO: compiled from: Density.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void getDensity$annotations() {
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m6878toDpGaN1DYA(Density density, long j) {
            return FontScaling.CC.m6999$default$toDpGaN1DYA(density, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m6885toSp0xMU5do(Density density, float f) {
            return FontScaling.CC.m7000$default$toSp0xMU5do(density, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m6883toPx0680j_4(Density density, float f) {
            return CC.m6860$default$toPx0680j_4(density, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m6877roundToPx0680j_4(Density density, float f) {
            return CC.m6855$default$roundToPx0680j_4(density, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m6882toPxR2X_6o(Density density, long j) {
            return CC.m6859$default$toPxR2X_6o(density, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m6876roundToPxR2X_6o(Density density, long j) {
            return CC.m6854$default$roundToPxR2X_6o(density, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m6880toDpu2uoSUM(Density density, int i) {
            return CC.m6857$default$toDpu2uoSUM(density, i);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m6887toSpkPz2Gy4(Density density, int i) {
            return CC.m6863$default$toSpkPz2Gy4(density, i);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m6879toDpu2uoSUM(Density density, float f) {
            return CC.m6856$default$toDpu2uoSUM(density, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m6886toSpkPz2Gy4(Density density, float f) {
            return CC.m6862$default$toSpkPz2Gy4(density, f);
        }

        @Deprecated
        public static Rect toRect(Density density, DpRect dpRect) {
            return CC.$default$toRect(density, dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m6884toSizeXkaWNTQ(Density density, long j) {
            return CC.m6861$default$toSizeXkaWNTQ(density, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m6881toDpSizekrfVVM(Density density, long j) {
            return CC.m6858$default$toDpSizekrfVVM(density, j);
        }
    }
}
