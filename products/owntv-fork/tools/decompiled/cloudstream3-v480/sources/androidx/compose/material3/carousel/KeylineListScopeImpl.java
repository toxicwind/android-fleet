package androidx.compose.material3.carousel;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: KeylineList.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016JT\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0012H\u0002J(\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u001bø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ&\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0006J\b\u0010\u001f\u001a\u00020\u0004H\u0002J\u0018\u0010 \u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u0006H\u0002J \u0010\"\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006$"}, d2 = {"Landroidx/compose/material3/carousel/KeylineListScopeImpl;", "Landroidx/compose/material3/carousel/KeylineListScope;", "()V", "firstFocalIndex", "", "focalItemSize", "", "pivotIndex", "pivotOffset", "tmpKeylines", "", "Landroidx/compose/material3/carousel/KeylineListScopeImpl$TmpKeyline;", "add", "", ContentDisposition.Parameters.Size, "isAnchor", "", "createKeylinesWithPivot", "", "Landroidx/compose/material3/carousel/Keyline;", "lastFocalIndex", "itemMainAxisSize", "carouselMainAxisSize", "itemSpacing", "createWithAlignment", "Landroidx/compose/material3/carousel/KeylineList;", "carouselAlignment", "Landroidx/compose/material3/carousel/CarouselAlignment;", "createWithAlignment-waks0t8", "(FFI)Landroidx/compose/material3/carousel/KeylineList;", "createWithPivot", "findLastFocalIndex", "isCutoffLeft", TypedValues.CycleType.S_WAVE_OFFSET, "isCutoffRight", "TmpKeyline", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class KeylineListScopeImpl implements KeylineListScope {
    private float focalItemSize;
    private float pivotOffset;
    private int firstFocalIndex = -1;
    private int pivotIndex = -1;
    private final List<TmpKeyline> tmpKeylines = new ArrayList();

    private final boolean isCutoffLeft(float size, float offset) {
        float f = size / 2.0f;
        return offset - f < 0.0f && offset + f > 0.0f;
    }

    private final boolean isCutoffRight(float size, float offset, float carouselMainAxisSize) {
        float f = size / 2.0f;
        return offset - f < carouselMainAxisSize && offset + f > carouselMainAxisSize;
    }

    /* JADX INFO: compiled from: KeylineList.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Landroidx/compose/material3/carousel/KeylineListScopeImpl$TmpKeyline;", "", ContentDisposition.Parameters.Size, "", "isAnchor", "", "(FZ)V", "()Z", "getSize", "()F", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final /* data */ class TmpKeyline {
        private final boolean isAnchor;
        private final float size;

        public static /* synthetic */ TmpKeyline copy$default(TmpKeyline tmpKeyline, float f, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                f = tmpKeyline.size;
            }
            if ((i & 2) != 0) {
                z = tmpKeyline.isAnchor;
            }
            return tmpKeyline.copy(f, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final float getSize() {
            return this.size;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsAnchor() {
            return this.isAnchor;
        }

        public final TmpKeyline copy(float size, boolean isAnchor) {
            return new TmpKeyline(size, isAnchor);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TmpKeyline)) {
                return false;
            }
            TmpKeyline tmpKeyline = (TmpKeyline) other;
            return Float.compare(this.size, tmpKeyline.size) == 0 && this.isAnchor == tmpKeyline.isAnchor;
        }

        public int hashCode() {
            return (Float.floatToIntBits(this.size) * 31) + UByte$$ExternalSyntheticBackport0.m(this.isAnchor);
        }

        public String toString() {
            return "TmpKeyline(size=" + this.size + ", isAnchor=" + this.isAnchor + ')';
        }

        public TmpKeyline(float f, boolean z) {
            this.size = f;
            this.isAnchor = z;
        }

        public final float getSize() {
            return this.size;
        }

        public final boolean isAnchor() {
            return this.isAnchor;
        }
    }

    @Override // androidx.compose.material3.carousel.KeylineListScope
    public void add(float size, boolean isAnchor) {
        this.tmpKeylines.add(new TmpKeyline(size, isAnchor));
        if (size > this.focalItemSize) {
            this.firstFocalIndex = CollectionsKt.getLastIndex(this.tmpKeylines);
            this.focalItemSize = size;
        }
    }

    public final KeylineList createWithPivot(float carouselMainAxisSize, float itemSpacing, int pivotIndex, float pivotOffset) {
        return new KeylineList(createKeylinesWithPivot(pivotIndex, pivotOffset, this.firstFocalIndex, findLastFocalIndex(), this.focalItemSize, carouselMainAxisSize, itemSpacing, this.tmpKeylines));
    }

    /* JADX INFO: renamed from: createWithAlignment-waks0t8, reason: not valid java name */
    public final KeylineList m2606createWithAlignmentwaks0t8(float carouselMainAxisSize, float itemSpacing, int carouselAlignment) {
        float f;
        int iFindLastFocalIndex = findLastFocalIndex();
        int i = this.firstFocalIndex;
        int i2 = iFindLastFocalIndex - i;
        this.pivotIndex = i;
        if (CarouselAlignment.m2590equalsimpl0(carouselAlignment, CarouselAlignment.INSTANCE.m2594getCenterNUL3oTo())) {
            float f2 = 0.0f;
            if (itemSpacing != 0.0f) {
                int i3 = i2 % 2;
                if (i3 + ((((i3 ^ 2) & ((-i3) | i3)) >> 31) & 2) != 0) {
                    f2 = itemSpacing / 2.0f;
                }
            }
            f = ((carouselMainAxisSize / 2.0f) - ((this.focalItemSize / 2.0f) * i2)) - f2;
        } else {
            boolean zM2590equalsimpl0 = CarouselAlignment.m2590equalsimpl0(carouselAlignment, CarouselAlignment.INSTANCE.m2595getEndNUL3oTo());
            float f3 = this.focalItemSize;
            f = zM2590equalsimpl0 ? carouselMainAxisSize - (f3 / 2.0f) : f3 / 2.0f;
        }
        float f4 = f;
        this.pivotOffset = f4;
        return new KeylineList(createKeylinesWithPivot(this.pivotIndex, f4, this.firstFocalIndex, iFindLastFocalIndex, this.focalItemSize, carouselMainAxisSize, itemSpacing, this.tmpKeylines));
    }

    private final int findLastFocalIndex() {
        int i = this.firstFocalIndex;
        while (i < CollectionsKt.getLastIndex(this.tmpKeylines)) {
            int i2 = i + 1;
            if (this.tmpKeylines.get(i2).getSize() != this.focalItemSize) {
                break;
            }
            i = i2;
        }
        return i;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x005a  */
    /* JADX WARN: Code duplicated, block: B:18:0x0086  */
    /* JADX WARN: Code duplicated, block: B:20:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:21:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:26:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:31:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:33:0x011d  */
    /* JADX WARN: Code duplicated, block: B:34:0x0128  */
    /* JADX WARN: Code duplicated, block: B:39:0x0137  */
    private final List<Keyline> createKeylinesWithPivot(int pivotIndex, float pivotOffset, int firstFocalIndex, int lastFocalIndex, float itemMainAxisSize, float carouselMainAxisSize, float itemSpacing, List<TmpKeyline> tmpKeylines) {
        float f;
        float size;
        float f2;
        float size2;
        Iterator<Integer> it;
        float f3;
        float size3;
        Iterator<Integer> it2;
        float f4;
        int iNextInt;
        TmpKeyline tmpKeyline;
        float size4;
        float size5;
        boolean z;
        int iNextInt2;
        TmpKeyline tmpKeyline2;
        float size6;
        float fAbs;
        boolean z2;
        TmpKeyline tmpKeyline3 = tmpKeylines.get(pivotIndex);
        ArrayList arrayList = new ArrayList();
        if (!isCutoffLeft(tmpKeyline3.getSize(), pivotOffset)) {
            if (isCutoffRight(tmpKeyline3.getSize(), pivotOffset, carouselMainAxisSize)) {
                size = ((tmpKeyline3.getSize() / 2.0f) + pivotOffset) - carouselMainAxisSize;
            } else {
                f = 0.0f;
            }
            float size7 = tmpKeyline3.getSize();
            boolean z3 = false;
            if (firstFocalIndex > pivotIndex && pivotIndex <= lastFocalIndex) {
                z3 = true;
            }
            arrayList.add(new Keyline(size7, pivotOffset, pivotOffset, z3, tmpKeyline3.isAnchor(), true, f));
            f2 = itemMainAxisSize / 2.0f;
            size2 = (pivotOffset - f2) - itemSpacing;
            it = RangesKt.downTo(pivotIndex - 1, 0).iterator();
            f3 = size2;
            while (it.hasNext()) {
                iNextInt2 = ((IntIterator) it).nextInt();
                tmpKeyline2 = tmpKeylines.get(iNextInt2);
                size6 = size2 - (tmpKeyline2.getSize() / 2.0f);
                float f5 = f3 - f2;
                if (isCutoffLeft(tmpKeyline2.getSize(), size6)) {
                    fAbs = Math.abs(size6 - (tmpKeyline2.getSize() / 2.0f));
                } else {
                    fAbs = 0.0f;
                }
                float size8 = tmpKeyline2.getSize();
                if (firstFocalIndex <= iNextInt2 || iNextInt2 > lastFocalIndex) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                arrayList.add(0, new Keyline(size8, size6, f5, z2, tmpKeyline2.isAnchor(), false, fAbs));
                size2 -= tmpKeyline2.getSize() + itemSpacing;
                f3 -= itemMainAxisSize + itemSpacing;
            }
            size3 = pivotOffset + f2 + itemSpacing;
            it2 = RangesKt.until(pivotIndex + 1, tmpKeylines.size()).iterator();
            f4 = size3;
            while (it2.hasNext()) {
                iNextInt = ((IntIterator) it2).nextInt();
                tmpKeyline = tmpKeylines.get(iNextInt);
                size4 = (tmpKeyline.getSize() / 2.0f) + size3;
                float f6 = f4 + f2;
                if (isCutoffRight(tmpKeyline.getSize(), size4, carouselMainAxisSize)) {
                    size5 = ((tmpKeyline.getSize() / 2.0f) + size4) - carouselMainAxisSize;
                } else {
                    size5 = 0.0f;
                }
                float size9 = tmpKeyline.getSize();
                if (firstFocalIndex <= iNextInt || iNextInt > lastFocalIndex) {
                    z = false;
                } else {
                    z = true;
                }
                arrayList.add(new Keyline(size9, size4, f6, z, tmpKeyline.isAnchor(), false, size5));
                size3 += tmpKeyline.getSize() + itemSpacing;
                f4 += itemMainAxisSize + itemSpacing;
            }
            return arrayList;
        }
        size = pivotOffset - (tmpKeyline3.getSize() / 2.0f);
        f = size;
        float size10 = tmpKeyline3.getSize();
        boolean z4 = false;
        if (firstFocalIndex > pivotIndex) {
        }
        arrayList.add(new Keyline(size10, pivotOffset, pivotOffset, z4, tmpKeyline3.isAnchor(), true, f));
        f2 = itemMainAxisSize / 2.0f;
        size2 = (pivotOffset - f2) - itemSpacing;
        it = RangesKt.downTo(pivotIndex - 1, 0).iterator();
        f3 = size2;
        while (it.hasNext()) {
            iNextInt2 = ((IntIterator) it).nextInt();
            tmpKeyline2 = tmpKeylines.get(iNextInt2);
            size6 = size2 - (tmpKeyline2.getSize() / 2.0f);
            float f7 = f3 - f2;
            if (isCutoffLeft(tmpKeyline2.getSize(), size6)) {
                fAbs = Math.abs(size6 - (tmpKeyline2.getSize() / 2.0f));
            } else {
                fAbs = 0.0f;
            }
            float size11 = tmpKeyline2.getSize();
            if (firstFocalIndex <= iNextInt2) {
                z2 = false;
            } else {
                z2 = false;
            }
            arrayList.add(0, new Keyline(size11, size6, f7, z2, tmpKeyline2.isAnchor(), false, fAbs));
            size2 -= tmpKeyline2.getSize() + itemSpacing;
            f3 -= itemMainAxisSize + itemSpacing;
        }
        size3 = pivotOffset + f2 + itemSpacing;
        it2 = RangesKt.until(pivotIndex + 1, tmpKeylines.size()).iterator();
        f4 = size3;
        while (it2.hasNext()) {
            iNextInt = ((IntIterator) it2).nextInt();
            tmpKeyline = tmpKeylines.get(iNextInt);
            size4 = (tmpKeyline.getSize() / 2.0f) + size3;
            float f8 = f4 + f2;
            if (isCutoffRight(tmpKeyline.getSize(), size4, carouselMainAxisSize)) {
                size5 = ((tmpKeyline.getSize() / 2.0f) + size4) - carouselMainAxisSize;
            } else {
                size5 = 0.0f;
            }
            float size12 = tmpKeyline.getSize();
            if (firstFocalIndex <= iNextInt) {
                z = false;
            } else {
                z = false;
            }
            arrayList.add(new Keyline(size12, size4, f8, z, tmpKeyline.isAnchor(), false, size5));
            size3 += tmpKeyline.getSize() + itemSpacing;
            f4 += itemMainAxisSize + itemSpacing;
        }
        return arrayList;
    }
}
