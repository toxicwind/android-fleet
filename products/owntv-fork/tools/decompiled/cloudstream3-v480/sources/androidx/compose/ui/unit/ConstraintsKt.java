package androidx.compose.ui.unit;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.http.ContentDisposition;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Constraints.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0013\u001a\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0001H\u0000\u001a-\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u0001H\u0000¢\u0006\u0002\u0010 \u001a\u0010\u0010!\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0001H\u0000\u001a\u0011\u0010\"\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0001H\u0082\b\u001a5\u0010#\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00012\b\b\u0002\u0010\u001d\u001a\u00020\u00012\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010 \u001a\u0019\u0010$\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010%\u001a\u00020\u001b¢\u0006\u0004\b&\u0010'\u001a\u001b\u0010$\u001a\u00020(*\u00020\u001b2\u0006\u0010\u0019\u001a\u00020(H\u0007¢\u0006\u0004\b)\u0010'\u001a\u001b\u0010*\u001a\u00020\u0001*\u00020\u001b2\u0006\u0010+\u001a\u00020\u0001H\u0007¢\u0006\u0004\b,\u0010-\u001a\u001b\u0010.\u001a\u00020\u0001*\u00020\u001b2\u0006\u0010/\u001a\u00020\u0001H\u0007¢\u0006\u0004\b0\u0010-\u001a\u001b\u00101\u001a\u000202*\u00020\u001b2\u0006\u0010\u0019\u001a\u00020(H\u0007¢\u0006\u0004\b3\u00104\u001a'\u00105\u001a\u00020\u001b*\u00020\u001b2\b\b\u0002\u00106\u001a\u00020\u00012\b\b\u0002\u00107\u001a\u00020\u0001H\u0007¢\u0006\u0004\b8\u00109\u001a\u0019\u0010:\u001a\u00020\u00012\u0006\u0010;\u001a\u00020\u00012\u0006\u0010<\u001a\u00020\u0001H\u0082\b\u001a\u0011\u0010=\u001a\u00020\u00012\u0006\u0010>\u001a\u00020\u0001H\u0082\b\u001a\u0011\u0010?\u001a\u00020\u00012\u0006\u0010@\u001a\u00020\u0001H\u0082\b\u001a\u0011\u0010A\u001a\u00020\u00012\u0006\u0010B\u001a\u00020\u0001H\u0082\b\u001a\u0011\u0010C\u001a\u00020\u00012\u0006\u0010B\u001a\u00020\u0001H\u0082\b\u001a\u0011\u0010D\u001a\u00020\u00012\u0006\u0010B\u001a\u00020\u0001H\u0082\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0010\u001a\u00020\u00038\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0011\u0010\u0012¨\u0006E"}, d2 = {"Infinity", "", "FocusMask", "", "MinFocusBits", "MaxAllowedForMinFocusBits", "MinFocusMask", "MinNonFocusBits", "MaxAllowedForMinNonFocusBits", "MinNonFocusMask", "MaxFocusBits", "MaxAllowedForMaxFocusBits", "MaxFocusMask", "MaxNonFocusBits", "MaxAllowedForMaxNonFocusBits", "MaxNonFocusMask", "MaxDimensionsAndFocusMask", "getMaxDimensionsAndFocusMask$annotations", "()V", "throwInvalidConstraintException", "", "widthVal", "heightVal", "throwInvalidConstraintsSizeException", "", ContentDisposition.Parameters.Size, "createConstraints", "Landroidx/compose/ui/unit/Constraints;", "minWidth", "maxWidth", "minHeight", "maxHeight", "(IIII)J", "bitsNeedForSizeUnchecked", "maxAllowedForSize", androidx.constraintlayout.widget.Constraints.TAG, "constrain", "otherConstraints", "constrain-N9IONVI", "(JJ)J", "Landroidx/compose/ui/unit/IntSize;", "constrain-4WqzIAM", "constrainWidth", "width", "constrainWidth-K40F9xA", "(JI)I", "constrainHeight", "height", "constrainHeight-K40F9xA", "isSatisfiedBy", "", "isSatisfiedBy-4WqzIAM", "(JJ)Z", TypedValues.CycleType.S_WAVE_OFFSET, "horizontal", "vertical", "offset-NN6Ew-U", "(JII)J", "addMaxWithMinimum", "max", ES6Iterator.VALUE_PROPERTY, "indexToBitOffset", "index", "bitOffsetToIndex", "bits", "minHeightOffsets", "bitOffset", "widthMask", "heightMask", "ui-unit"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ConstraintsKt {
    private static final long FocusMask = 3;
    private static final int Infinity = Integer.MAX_VALUE;
    private static final int MaxAllowedForMaxFocusBits = 8190;
    private static final int MaxAllowedForMaxNonFocusBits = 262142;
    private static final int MaxAllowedForMinFocusBits = 32766;
    private static final int MaxAllowedForMinNonFocusBits = 65534;
    public static final long MaxDimensionsAndFocusMask = -8589934589L;
    private static final int MaxFocusBits = 18;
    private static final int MaxFocusMask = 262143;
    private static final int MaxNonFocusBits = 13;
    private static final int MaxNonFocusMask = 8191;
    private static final int MinFocusBits = 16;
    private static final int MinFocusMask = 65535;
    private static final int MinNonFocusBits = 15;
    private static final int MinNonFocusMask = 32767;

    private static final int addMaxWithMinimum(int i, int i2) {
        if (i == Integer.MAX_VALUE) {
            return i;
        }
        int i3 = i + i2;
        if (i3 < 0) {
            return 0;
        }
        return i3;
    }

    private static final int bitOffsetToIndex(int i) {
        return (i >> 1) + (i & 1);
    }

    public static final int bitsNeedForSizeUnchecked(int i) {
        if (i < MaxNonFocusMask) {
            return 13;
        }
        if (i < 32767) {
            return 15;
        }
        if (i < 65535) {
            return 16;
        }
        return i < MaxFocusMask ? 18 : 255;
    }

    public static /* synthetic */ void getMaxDimensionsAndFocusMask$annotations() {
    }

    private static final int heightMask(int i) {
        return (1 << (18 - i)) - 1;
    }

    private static final int indexToBitOffset(int i) {
        return ((i & 1) << 1) + (((i & 2) >> 1) * 3);
    }

    private static final int minHeightOffsets(int i) {
        return i + 15;
    }

    private static final int widthMask(int i) {
        return (1 << (i + 13)) - 1;
    }

    public static final void throwInvalidConstraintException(int i, int i2) {
        throw new IllegalArgumentException("Can't represent a width of " + i + " and height of " + i2 + " in Constraints");
    }

    public static final Void throwInvalidConstraintsSizeException(int i) {
        throw new IllegalArgumentException("Can't represent a size of " + i + " in Constraints");
    }

    public static final long createConstraints(int i, int i2, int i3, int i4) {
        int i5 = i4 == Integer.MAX_VALUE ? i3 : i4;
        int iBitsNeedForSizeUnchecked = bitsNeedForSizeUnchecked(i5);
        int i6 = i2 == Integer.MAX_VALUE ? i : i2;
        int iBitsNeedForSizeUnchecked2 = bitsNeedForSizeUnchecked(i6);
        if (iBitsNeedForSizeUnchecked + iBitsNeedForSizeUnchecked2 > 31) {
            throwInvalidConstraintException(i6, i5);
        }
        int i7 = i2 + 1;
        int i8 = i4 + 1;
        int i9 = iBitsNeedForSizeUnchecked2 - 13;
        return Constraints.m6821constructorimpl((((long) (i7 & (~(i7 >> 31)))) << 33) | ((long) ((i9 >> 1) + (i9 & 1))) | (((long) i) << 2) | (((long) i3) << (iBitsNeedForSizeUnchecked2 + 2)) | (((long) (i8 & (~(i8 >> 31)))) << (iBitsNeedForSizeUnchecked2 + 33)));
    }

    private static final int maxAllowedForSize(int i) {
        if (i < MaxNonFocusMask) {
            return MaxAllowedForMaxNonFocusBits;
        }
        if (i < 32767) {
            return 65534;
        }
        if (i < 65535) {
            return MaxAllowedForMinFocusBits;
        }
        if (i < MaxFocusMask) {
            return MaxAllowedForMaxFocusBits;
        }
        throwInvalidConstraintsSizeException(i);
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ long Constraints$default(int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = 0;
        }
        if ((i5 & 2) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        if ((i5 & 4) != 0) {
            i3 = 0;
        }
        if ((i5 & 8) != 0) {
            i4 = Integer.MAX_VALUE;
        }
        return Constraints(i, i2, i3, i4);
    }

    public static final long Constraints(int i, int i2, int i3, int i4) {
        if (!((i3 >= 0) & (i2 >= i) & (i4 >= i3) & (i >= 0))) {
            InlineClassHelperKt.throwIllegalArgumentException("maxWidth must be >= than minWidth,\nmaxHeight must be >= than minHeight,\nminWidth and minHeight must be >= 0");
        }
        return createConstraints(i, i2, i3, i4);
    }

    /* JADX INFO: renamed from: constrain-N9IONVI, reason: not valid java name */
    public static final long m6848constrainN9IONVI(long j, long j2) {
        int iM6835getMinWidthimpl = Constraints.m6835getMinWidthimpl(j);
        int iM6833getMaxWidthimpl = Constraints.m6833getMaxWidthimpl(j);
        int iM6834getMinHeightimpl = Constraints.m6834getMinHeightimpl(j);
        int iM6832getMaxHeightimpl = Constraints.m6832getMaxHeightimpl(j);
        int iM6835getMinWidthimpl2 = Constraints.m6835getMinWidthimpl(j2);
        if (iM6835getMinWidthimpl2 < iM6835getMinWidthimpl) {
            iM6835getMinWidthimpl2 = iM6835getMinWidthimpl;
        }
        if (iM6835getMinWidthimpl2 > iM6833getMaxWidthimpl) {
            iM6835getMinWidthimpl2 = iM6833getMaxWidthimpl;
        }
        int iM6833getMaxWidthimpl2 = Constraints.m6833getMaxWidthimpl(j2);
        if (iM6833getMaxWidthimpl2 >= iM6835getMinWidthimpl) {
            iM6835getMinWidthimpl = iM6833getMaxWidthimpl2;
        }
        if (iM6835getMinWidthimpl <= iM6833getMaxWidthimpl) {
            iM6833getMaxWidthimpl = iM6835getMinWidthimpl;
        }
        int iM6834getMinHeightimpl2 = Constraints.m6834getMinHeightimpl(j2);
        if (iM6834getMinHeightimpl2 < iM6834getMinHeightimpl) {
            iM6834getMinHeightimpl2 = iM6834getMinHeightimpl;
        }
        if (iM6834getMinHeightimpl2 > iM6832getMaxHeightimpl) {
            iM6834getMinHeightimpl2 = iM6832getMaxHeightimpl;
        }
        int iM6832getMaxHeightimpl2 = Constraints.m6832getMaxHeightimpl(j2);
        if (iM6832getMaxHeightimpl2 >= iM6834getMinHeightimpl) {
            iM6834getMinHeightimpl = iM6832getMaxHeightimpl2;
        }
        if (iM6834getMinHeightimpl <= iM6832getMaxHeightimpl) {
            iM6832getMaxHeightimpl = iM6834getMinHeightimpl;
        }
        return Constraints(iM6835getMinWidthimpl2, iM6833getMaxWidthimpl, iM6834getMinHeightimpl2, iM6832getMaxHeightimpl);
    }

    /* JADX INFO: renamed from: constrain-4WqzIAM, reason: not valid java name */
    public static final long m6847constrain4WqzIAM(long j, long j2) {
        int i = (int) (j2 >> 32);
        int iM6835getMinWidthimpl = Constraints.m6835getMinWidthimpl(j);
        int iM6833getMaxWidthimpl = Constraints.m6833getMaxWidthimpl(j);
        if (i < iM6835getMinWidthimpl) {
            i = iM6835getMinWidthimpl;
        }
        if (i <= iM6833getMaxWidthimpl) {
            iM6833getMaxWidthimpl = i;
        }
        int i2 = (int) (j2 & 4294967295L);
        int iM6834getMinHeightimpl = Constraints.m6834getMinHeightimpl(j);
        int iM6832getMaxHeightimpl = Constraints.m6832getMaxHeightimpl(j);
        if (i2 < iM6834getMinHeightimpl) {
            i2 = iM6834getMinHeightimpl;
        }
        if (i2 <= iM6832getMaxHeightimpl) {
            iM6832getMaxHeightimpl = i2;
        }
        return IntSize.m7060constructorimpl((((long) iM6833getMaxWidthimpl) << 32) | (4294967295L & ((long) iM6832getMaxHeightimpl)));
    }

    /* JADX INFO: renamed from: constrainWidth-K40F9xA, reason: not valid java name */
    public static final int m6850constrainWidthK40F9xA(long j, int i) {
        int iM6835getMinWidthimpl = Constraints.m6835getMinWidthimpl(j);
        int iM6833getMaxWidthimpl = Constraints.m6833getMaxWidthimpl(j);
        if (i < iM6835getMinWidthimpl) {
            i = iM6835getMinWidthimpl;
        }
        return i > iM6833getMaxWidthimpl ? iM6833getMaxWidthimpl : i;
    }

    /* JADX INFO: renamed from: constrainHeight-K40F9xA, reason: not valid java name */
    public static final int m6849constrainHeightK40F9xA(long j, int i) {
        int iM6834getMinHeightimpl = Constraints.m6834getMinHeightimpl(j);
        int iM6832getMaxHeightimpl = Constraints.m6832getMaxHeightimpl(j);
        if (i < iM6834getMinHeightimpl) {
            i = iM6834getMinHeightimpl;
        }
        return i > iM6832getMaxHeightimpl ? iM6832getMaxHeightimpl : i;
    }

    /* JADX INFO: renamed from: isSatisfiedBy-4WqzIAM, reason: not valid java name */
    public static final boolean m6851isSatisfiedBy4WqzIAM(long j, long j2) {
        int iM6835getMinWidthimpl = Constraints.m6835getMinWidthimpl(j);
        int iM6833getMaxWidthimpl = Constraints.m6833getMaxWidthimpl(j);
        int i = (int) (j2 >> 32);
        if (iM6835getMinWidthimpl > i || i > iM6833getMaxWidthimpl) {
            return false;
        }
        int i2 = (int) (j2 & 4294967295L);
        return Constraints.m6834getMinHeightimpl(j) <= i2 && i2 <= Constraints.m6832getMaxHeightimpl(j);
    }

    /* JADX INFO: renamed from: offset-NN6Ew-U$default, reason: not valid java name */
    public static /* synthetic */ long m6853offsetNN6EwU$default(long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        return m6852offsetNN6EwU(j, i, i2);
    }

    /* JADX INFO: renamed from: offset-NN6Ew-U, reason: not valid java name */
    public static final long m6852offsetNN6EwU(long j, int i, int i2) {
        int iM6835getMinWidthimpl = Constraints.m6835getMinWidthimpl(j) + i;
        if (iM6835getMinWidthimpl < 0) {
            iM6835getMinWidthimpl = 0;
        }
        int iM6833getMaxWidthimpl = Constraints.m6833getMaxWidthimpl(j);
        if (iM6833getMaxWidthimpl != Integer.MAX_VALUE && (iM6833getMaxWidthimpl = iM6833getMaxWidthimpl + i) < 0) {
            iM6833getMaxWidthimpl = 0;
        }
        int iM6834getMinHeightimpl = Constraints.m6834getMinHeightimpl(j) + i2;
        if (iM6834getMinHeightimpl < 0) {
            iM6834getMinHeightimpl = 0;
        }
        int iM6832getMaxHeightimpl = Constraints.m6832getMaxHeightimpl(j);
        return Constraints(iM6835getMinWidthimpl, iM6833getMaxWidthimpl, iM6834getMinHeightimpl, (iM6832getMaxHeightimpl == Integer.MAX_VALUE || (iM6832getMaxHeightimpl = iM6832getMaxHeightimpl + i2) >= 0) ? iM6832getMaxHeightimpl : 0);
    }
}
