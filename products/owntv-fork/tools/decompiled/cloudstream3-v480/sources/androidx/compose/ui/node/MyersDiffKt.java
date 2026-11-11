package androidx.compose.ui.node;

import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;

/* JADX INFO: compiled from: MyersDiff.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0012\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0006H\u0002\u001a \u0010\u000b\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0006H\u0000\u001aO\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001aW\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u001a\u0010\u001b\u001aW\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u001c\u0010\u001b\u001a\r\u0010\u001d\u001a\u00020\u0003*\u00020\rH\u0082\b\u001a8\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\u0016H\u0000\u001a\u001c\u0010%\u001a\u00020\b*\u00020\u00162\u0006\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\u0003H\u0002¨\u0006("}, d2 = {"calculateDiff", "Landroidx/compose/ui/node/IntStack;", "oldSize", "", "newSize", "cb", "Landroidx/compose/ui/node/DiffCallback;", "applyDiff", "", "diagonals", "callback", "executeDiff", "midPoint", "", "oldStart", "oldEnd", "newStart", "newEnd", "forward", "Landroidx/compose/ui/node/CenteredArray;", "backward", "snake", "", "midPoint-q5eDKzI", "(IIIILandroidx/compose/ui/node/DiffCallback;[I[I[I)Z", "d", "forward-4l5_RBY", "(IIIILandroidx/compose/ui/node/DiffCallback;[I[II[I)Z", "backward-4l5_RBY", "toInt", "fillSnake", "startX", "startY", "endX", "endY", "reverse", "data", "swap", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "j", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class MyersDiffKt {
    private static final int toInt(boolean z) {
        return z ? 1 : 0;
    }

    private static final IntStack calculateDiff(int i, int i2, DiffCallback diffCallback) {
        char c = 1;
        int i3 = ((i + i2) + 1) / 2;
        IntStack intStack = new IntStack(i3 * 3);
        IntStack intStack2 = new IntStack(i3 * 4);
        intStack2.pushRange(0, i, 0, i2);
        int i4 = (i3 * 2) + 1;
        int[] iArrM5720constructorimpl = CenteredArray.m5720constructorimpl(new int[i4]);
        int[] iArrM5720constructorimpl2 = CenteredArray.m5720constructorimpl(new int[i4]);
        int[] iArrM5950constructorimpl = Snake.m5950constructorimpl(new int[5]);
        while (intStack2.isNotEmpty()) {
            int iPop = intStack2.pop();
            int iPop2 = intStack2.pop();
            int iPop3 = intStack2.pop();
            int iPop4 = intStack2.pop();
            iArrM5950constructorimpl = iArrM5950constructorimpl;
            if (m5840midPointq5eDKzI(iPop4, iPop3, iPop2, iPop, diffCallback, iArrM5720constructorimpl, iArrM5720constructorimpl2, iArrM5950constructorimpl)) {
                char c2 = c;
                if (Math.min(iArrM5950constructorimpl[2] - iArrM5950constructorimpl[0], iArrM5950constructorimpl[3] - iArrM5950constructorimpl[c]) > 0) {
                    Snake.m5948addDiagonalToStackimpl(iArrM5950constructorimpl, intStack);
                }
                intStack2.pushRange(iPop4, iArrM5950constructorimpl[0], iPop2, iArrM5950constructorimpl[c2]);
                intStack2.pushRange(iArrM5950constructorimpl[2], iPop3, iArrM5950constructorimpl[3], iPop);
                c = c2;
            }
        }
        intStack.sortDiagonals();
        intStack.pushDiagonal(i, i2, 0);
        return intStack;
    }

    private static final void applyDiff(IntStack intStack, DiffCallback diffCallback) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < intStack.getLastIndex()) {
            int i4 = i + 2;
            int i5 = intStack.get(i) - intStack.get(i4);
            int i6 = intStack.get(i + 1) - intStack.get(i4);
            int i7 = intStack.get(i4);
            i += 3;
            while (i2 < i5) {
                diffCallback.remove(i3, i2);
                i2++;
            }
            while (i3 < i6) {
                diffCallback.insert(i3);
                i3++;
            }
            while (true) {
                int i8 = i7 - 1;
                if (i7 > 0) {
                    diffCallback.same(i2, i3);
                    i2++;
                    i3++;
                    i7 = i8;
                }
            }
        }
    }

    public static final void executeDiff(int i, int i2, DiffCallback diffCallback) {
        applyDiff(calculateDiff(i, i2, diffCallback), diffCallback);
    }

    /* JADX INFO: renamed from: midPoint-q5eDKzI, reason: not valid java name */
    private static final boolean m5840midPointq5eDKzI(int i, int i2, int i3, int i4, DiffCallback diffCallback, int[] iArr, int[] iArr2, int[] iArr3) {
        int i5 = i2 - i;
        int i6 = i4 - i3;
        if (i5 >= 1 && i6 >= 1) {
            int i7 = ((i5 + i6) + 1) / 2;
            int[] iArr4 = iArr;
            CenteredArray.m5726setimpl(iArr4, 1, i);
            int[] iArr5 = iArr2;
            CenteredArray.m5726setimpl(iArr5, 1, i2);
            int i8 = 0;
            while (i8 < i7) {
                if (m5839forward4l5_RBY(i, i2, i3, i4, diffCallback, iArr4, iArr5, i8, iArr3) || m5838backward4l5_RBY(i, i2, i3, i4, diffCallback, iArr, iArr2, i8, iArr3)) {
                    return true;
                }
                i8++;
                iArr4 = iArr;
                iArr5 = iArr2;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: forward-4l5_RBY, reason: not valid java name */
    private static final boolean m5839forward4l5_RBY(int i, int i2, int i3, int i4, DiffCallback diffCallback, int[] iArr, int[] iArr2, int i5, int[] iArr3) {
        int iM5723getimpl;
        int i6;
        boolean z;
        int i7 = (i2 - i) - (i4 - i3);
        boolean z2 = true;
        boolean z3 = (Math.abs(i7) & 1) == 1;
        int i8 = -i5;
        int i9 = i8;
        while (i9 <= i5) {
            if (i9 == i8 || (i9 != i5 && CenteredArray.m5723getimpl(iArr, i9 + 1) > CenteredArray.m5723getimpl(iArr, i9 - 1))) {
                iM5723getimpl = CenteredArray.m5723getimpl(iArr, i9 + 1);
                i6 = iM5723getimpl;
            } else {
                iM5723getimpl = CenteredArray.m5723getimpl(iArr, i9 - 1);
                i6 = iM5723getimpl + 1;
            }
            int i10 = (i3 + (i6 - i)) - i9;
            int i11 = i10 - ((i5 != 0 ? z2 : 0) & (i6 == iM5723getimpl ? z2 : 0));
            while (true) {
                if (i6 < i2 && i10 < i4) {
                    if (!diffCallback.areItemsTheSame(i6, i10)) {
                        break;
                    }
                    i6++;
                    i10++;
                } else {
                    break;
                }
            }
            CenteredArray.m5726setimpl(iArr, i9, i6);
            if (z3) {
                int i12 = i7 - i9;
                z = z2;
                if (i12 >= i8 + 1 && i12 <= i5 - 1) {
                    if (CenteredArray.m5723getimpl(iArr2, i12) <= i6) {
                        fillSnake(iM5723getimpl, i11, i6, i10, false, iArr3);
                        return z;
                    }
                }
                i9 += 2;
                z2 = z;
            } else {
                z = z2;
            }
            i9 += 2;
            z2 = z;
        }
        return false;
    }

    /* JADX INFO: renamed from: backward-4l5_RBY, reason: not valid java name */
    private static final boolean m5838backward4l5_RBY(int i, int i2, int i3, int i4, DiffCallback diffCallback, int[] iArr, int[] iArr2, int i5, int[] iArr3) {
        int iM5723getimpl;
        int i6;
        int i7;
        int i8 = (i2 - i) - (i4 - i3);
        boolean z = (i8 & 1) == 0;
        int i9 = -i5;
        for (int i10 = i9; i10 <= i5; i10 += 2) {
            if (i10 == i9 || (i10 != i5 && CenteredArray.m5723getimpl(iArr2, i10 + 1) < CenteredArray.m5723getimpl(iArr2, i10 - 1))) {
                iM5723getimpl = CenteredArray.m5723getimpl(iArr2, i10 + 1);
                i6 = iM5723getimpl;
            } else {
                iM5723getimpl = CenteredArray.m5723getimpl(iArr2, i10 - 1);
                i6 = iM5723getimpl - 1;
            }
            int i11 = i4 - ((i2 - i6) - i10);
            int i12 = ((i5 != 0 ? 1 : 0) & (i6 == iM5723getimpl ? 1 : 0)) + i11;
            while (true) {
                if (i6 <= i || i11 <= i3) {
                    break;
                }
                if (!diffCallback.areItemsTheSame(i6 - 1, i11 - 1)) {
                    break;
                }
                i6--;
                i11--;
            }
            CenteredArray.m5726setimpl(iArr2, i10, i6);
            if (z && (i7 = i8 - i10) >= i9 && i7 <= i5) {
                if (CenteredArray.m5723getimpl(iArr, i7) >= i6) {
                    fillSnake(i6, i11, iM5723getimpl, i12, true, iArr3);
                    return true;
                }
            }
        }
        return false;
    }

    public static final void fillSnake(int i, int i2, int i3, int i4, boolean z, int[] iArr) {
        if (iArr.length < 5) {
            return;
        }
        iArr[0] = i;
        iArr[1] = i2;
        iArr[2] = i3;
        iArr[3] = i4;
        iArr[4] = z ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void swap(int[] iArr, int i, int i2) {
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
    }
}
