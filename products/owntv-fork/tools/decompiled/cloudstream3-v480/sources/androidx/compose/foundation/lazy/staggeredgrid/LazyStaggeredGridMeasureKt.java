package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyStaggeredGridMeasure.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0082\b\u001a5\u0010\t\u001a\u0002H\n\"\u0004\b\u0000\u0010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u0002H\n0\u000e¢\u0006\u0002\b\u000fH\u0083\b¢\u0006\u0002\u0010\u0010\u001aR\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012*\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00050\u000e2!\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00010\u000e2\u0006\u0010\u001a\u001a\u00020\u0001H\u0083\b\u001a;\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012*\u00020\u00142\u0012\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0003H\u0002¢\u0006\u0002\u0010\"\u001a\u001d\u0010#\u001a\u00020\b*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u001e0\u001dH\u0002¢\u0006\u0002\u0010$\u001a\u001c\u0010%\u001a\u00020\u0005*\u00020\u00142\u0006\u0010&\u001a\u00020 2\u0006\u0010'\u001a\u00020\u0003H\u0002\u001a7\u0010(\u001a\u00020\u0005\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00122\b\b\u0002\u0010)\u001a\u00020\u00012\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00050\u000eH\u0082\b\u001a\u001c\u0010+\u001a\u00020\u0003*\u00020\u00142\u0006\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u0003H\u0002\u001a+\u0010.\u001a\u00020\u0005*\u00020/2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\u000eH\u0082\bø\u0001\u0000¢\u0006\u0004\b0\u00101\u001a\f\u00102\u001a\u00020\u0003*\u00020 H\u0002\u001a2\u00103\u001a\u00020\u0003\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u001d2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\u00030\u000eH\u0082\b¢\u0006\u0002\u00104\u001a\u0016\u00105\u001a\u00020\u0003*\u00020 2\b\b\u0002\u00106\u001a\u00020\u0003H\u0000\u001a\u001e\u00107\u001a\u00020\u0003*\u00020 2\u0006\u00108\u001a\u00020/H\u0002ø\u0001\u0000¢\u0006\u0004\b9\u0010:\u001a,\u0010;\u001a\u00020<*\u00020\u00142\u0006\u0010=\u001a\u00020\u00032\u0006\u0010>\u001a\u00020 2\u0006\u0010?\u001a\u00020 2\u0006\u0010@\u001a\u00020\u0001H\u0003\u001a\u008c\u0001\u0010A\u001a\u00020<*\u00020\f2\u0006\u0010B\u001a\u00020C2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00030\u00122\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020\u00012\u0006\u0010L\u001a\u00020\u00012\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020\u00032\u0006\u0010P\u001a\u00020\u00032\u0006\u0010Q\u001a\u00020\u00032\u0006\u0010R\u001a\u00020\u00032\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0001ø\u0001\u0000¢\u0006\u0004\bW\u0010X\u001a\u0014\u0010Y\u001a\u00020\u0005*\u00020 2\u0006\u0010Z\u001a\u00020\u0003H\u0002\u001a!\u0010[\u001a\u00020 *\u00020 2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000eH\u0082\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\\"}, d2 = {"DebugLoggingEnabled", "", "Unset", "", "debugLog", "", ContentType.Message.TYPE, "Lkotlin/Function0;", "", "withDebugLogging", ExifInterface.GPS_DIRECTION_TRUE, "scope", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "calculateExtraItems", "", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;", "position", "filter", "Lkotlin/ParameterName;", "name", "itemIndex", "beforeVisibleBounds", "calculateVisibleItems", "measuredItems", "", "Lkotlin/collections/ArrayDeque;", "itemScrollOffsets", "", "mainAxisLayoutSize", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;[Lkotlin/collections/ArrayDeque;[II)Ljava/util/List;", "debugRender", "([Lkotlin/collections/ArrayDeque;)Ljava/lang/String;", "ensureIndicesInRange", "indices", "itemCount", "fastForEach", "reverse", "action", "findPreviousItemIndex", "item", "lane", "forEach", "Landroidx/compose/foundation/lazy/staggeredgrid/SpanRange;", "forEach-nIS5qE8", "(JLkotlin/jvm/functions/Function1;)V", "indexOfMaxValue", "indexOfMinBy", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)I", "indexOfMinValue", "minBound", "maxInRange", "indexRange", "maxInRange-jy6DScQ", "([IJ)I", "measure", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "initialScrollDelta", "initialItemIndices", "initialItemOffsets", "canRestartMeasure", "measureStaggeredGrid", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "pinnedItems", "itemProvider", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;", "resolvedSlots", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "isVertical", "reverseLayout", "contentOffset", "Landroidx/compose/ui/unit/IntOffset;", "mainAxisAvailableSize", "mainAxisSpacing", "beforeContentPadding", "afterContentPadding", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "measureStaggeredGrid-XtK8cYQ", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Ljava/util/List;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;JZZJIIIILkotlinx/coroutines/CoroutineScope;Landroidx/compose/ui/graphics/GraphicsContext;)Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "offsetBy", "delta", "transform", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridMeasureKt {
    private static final boolean DebugLoggingEnabled = false;
    private static final int Unset = Integer.MIN_VALUE;

    private static final void debugLog(Function0<String> function0) {
    }

    private static final <T> T withDebugLogging(LazyLayoutMeasureScope lazyLayoutMeasureScope, Function1<? super LazyLayoutMeasureScope, ? extends T> function1) {
        return function1.invoke(lazyLayoutMeasureScope);
    }

    private static final String debugRender(ArrayDeque<LazyStaggeredGridMeasuredItem>[] arrayDequeArr) {
        return "";
    }

    /* JADX INFO: renamed from: measureStaggeredGrid-XtK8cYQ, reason: not valid java name */
    public static final LazyStaggeredGridMeasureResult m938measureStaggeredGridXtK8cYQ(LazyLayoutMeasureScope lazyLayoutMeasureScope, LazyStaggeredGridState lazyStaggeredGridState, List<Integer> list, LazyStaggeredGridItemProvider lazyStaggeredGridItemProvider, LazyStaggeredGridSlots lazyStaggeredGridSlots, long j, boolean z, boolean z2, long j2, int i, int i2, int i3, int i4, CoroutineScope coroutineScope, GraphicsContext graphicsContext) {
        int i5;
        int iM937maxInRangejy6DScQ;
        LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext = new LazyStaggeredGridMeasureContext(lazyStaggeredGridState, list, lazyStaggeredGridItemProvider, lazyStaggeredGridSlots, j, z, lazyLayoutMeasureScope, i, j2, i3, i4, z2, i2, coroutineScope, graphicsContext, null);
        int[] iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release = lazyStaggeredGridState.updateScrollPositionIfTheFirstItemWasMoved$foundation_release(lazyStaggeredGridItemProvider, lazyStaggeredGridState.getScrollPosition().getIndices());
        int[] scrollOffsets = lazyStaggeredGridState.getScrollPosition().getScrollOffsets();
        if (iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release.length != lazyStaggeredGridMeasureContext.getLaneCount()) {
            lazyStaggeredGridMeasureContext.getLaneInfo().reset();
            int laneCount = lazyStaggeredGridMeasureContext.getLaneCount();
            int[] iArr = new int[laneCount];
            int i6 = 0;
            while (i6 < laneCount) {
                if (i6 >= iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release.length || (iM937maxInRangejy6DScQ = iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release[i6]) == -1) {
                    iM937maxInRangejy6DScQ = i6 == 0 ? 0 : m937maxInRangejy6DScQ(iArr, SpanRange.m948constructorimpl(0, i6)) + 1;
                }
                iArr[i6] = iM937maxInRangejy6DScQ;
                lazyStaggeredGridMeasureContext.getLaneInfo().setLane(iArr[i6], i6);
                i6++;
            }
            iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release = iArr;
        }
        if (scrollOffsets.length != lazyStaggeredGridMeasureContext.getLaneCount()) {
            int laneCount2 = lazyStaggeredGridMeasureContext.getLaneCount();
            int[] iArr2 = new int[laneCount2];
            int i7 = 0;
            while (i7 < laneCount2) {
                if (i7 < scrollOffsets.length) {
                    i5 = scrollOffsets[i7];
                } else {
                    i5 = i7 == 0 ? 0 : iArr2[i7 - 1];
                }
                iArr2[i7] = i5;
                i7++;
            }
            scrollOffsets = iArr2;
        }
        return measure(lazyStaggeredGridMeasureContext, Math.round(lazyStaggeredGridState.getScrollToBeConsumed()), iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release, scrollOffsets, true);
    }

    /* JADX WARN: Code duplicated, block: B:309:0x0613  */
    /* JADX WARN: Code duplicated, block: B:311:0x0621  */
    /* JADX WARN: Code duplicated, block: B:313:0x0644  */
    /* JADX WARN: Code duplicated, block: B:358:0x0759  */
    private static final LazyStaggeredGridMeasureResult measure(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, int i, int[] iArr, int[] iArr2, boolean z) {
        int i2;
        int iIndexOf;
        int i3;
        int i4;
        int i5;
        int[] iArr3;
        int[] iArr4;
        int i6;
        int[] iArr5;
        int i7;
        int i8;
        int[] iArr6;
        int iM6850constrainWidthK40F9xA;
        int iM6832getMaxHeightimpl;
        int i9;
        List listEmptyList;
        int i10;
        int i11;
        boolean z2;
        boolean z3;
        boolean z4;
        List<Integer> list;
        boolean z5;
        boolean z6;
        int i12;
        int[] gaps;
        boolean z7;
        final LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext2 = lazyStaggeredGridMeasureContext;
        LazyLayoutMeasureScope measureScope = lazyStaggeredGridMeasureContext2.getMeasureScope();
        int itemCount = lazyStaggeredGridMeasureContext2.getItemProvider().getItemCount();
        if (itemCount <= 0 || lazyStaggeredGridMeasureContext2.getLaneCount() == 0) {
            int iM6835getMinWidthimpl = Constraints.m6835getMinWidthimpl(lazyStaggeredGridMeasureContext.getConstraints());
            int iM6834getMinHeightimpl = Constraints.m6834getMinHeightimpl(lazyStaggeredGridMeasureContext.getConstraints());
            lazyStaggeredGridMeasureContext.getState().getItemAnimator$foundation_release().onMeasured(0, iM6835getMinWidthimpl, iM6834getMinHeightimpl, new ArrayList(), lazyStaggeredGridMeasureContext.getMeasuredItemProvider().getKeyIndexMap(), lazyStaggeredGridMeasureContext.getMeasuredItemProvider(), lazyStaggeredGridMeasureContext.getIsVertical(), false, lazyStaggeredGridMeasureContext.getLaneCount(), false, 0, 0, lazyStaggeredGridMeasureContext.getCoroutineScope(), lazyStaggeredGridMeasureContext.getGraphicsContext());
            long jM884getMinSizeToFitDisappearingItemsYbymL2g = lazyStaggeredGridMeasureContext.getState().getItemAnimator$foundation_release().m884getMinSizeToFitDisappearingItemsYbymL2g();
            if (!IntSize.m7063equalsimpl0(jM884getMinSizeToFitDisappearingItemsYbymL2g, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                iM6835getMinWidthimpl = ConstraintsKt.m6850constrainWidthK40F9xA(lazyStaggeredGridMeasureContext.getConstraints(), IntSize.m7065getWidthimpl(jM884getMinSizeToFitDisappearingItemsYbymL2g));
                iM6834getMinHeightimpl = ConstraintsKt.m6849constrainHeightK40F9xA(lazyStaggeredGridMeasureContext.getConstraints(), IntSize.m7064getHeightimpl(jM884getMinSizeToFitDisappearingItemsYbymL2g));
            }
            return new LazyStaggeredGridMeasureResult(iArr, iArr2, 0.0f, MeasureScope.CC.layout$default(measureScope, iM6835getMinWidthimpl, iM6834getMinHeightimpl, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureKt$measure$1$1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope placementScope) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }
            }, 4, null), false, lazyStaggeredGridMeasureContext.getIsVertical(), false, lazyStaggeredGridMeasureContext.getResolvedSlots(), lazyStaggeredGridMeasureContext.getItemProvider().getSpanProvider(), measureScope, itemCount, CollectionsKt.emptyList(), IntSizeKt.IntSize(Constraints.m6835getMinWidthimpl(lazyStaggeredGridMeasureContext.getConstraints()), Constraints.m6834getMinHeightimpl(lazyStaggeredGridMeasureContext.getConstraints())), -lazyStaggeredGridMeasureContext.getBeforeContentPadding(), lazyStaggeredGridMeasureContext.getMainAxisAvailableSize() + lazyStaggeredGridMeasureContext.getAfterContentPadding(), lazyStaggeredGridMeasureContext.getBeforeContentPadding(), lazyStaggeredGridMeasureContext.getAfterContentPadding(), lazyStaggeredGridMeasureContext.getMainAxisSpacing(), lazyStaggeredGridMeasureContext.getCoroutineScope(), null);
        }
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
        String str = "copyOf(this, size)";
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, size)");
        int[] iArrCopyOf2 = Arrays.copyOf(iArr2, iArr2.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf2, "copyOf(this, size)");
        ensureIndicesInRange(lazyStaggeredGridMeasureContext2, iArrCopyOf, itemCount);
        offsetBy(iArrCopyOf2, -i);
        int laneCount = lazyStaggeredGridMeasureContext2.getLaneCount();
        ArrayDeque[] arrayDequeArr = new ArrayDeque[laneCount];
        for (int i13 = 0; i13 < laneCount; i13++) {
            arrayDequeArr[i13] = new ArrayDeque(16);
        }
        offsetBy(iArrCopyOf2, -lazyStaggeredGridMeasureContext2.getBeforeContentPadding());
        boolean z8 = false;
        while (true) {
            if (!measure$lambda$41$hasSpaceBeforeFirst(iArrCopyOf, iArrCopyOf2, lazyStaggeredGridMeasureContext2)) {
                i2 = 0;
                iIndexOf = -1;
                break;
            }
            iIndexOf = indexOfMaxValue(iArrCopyOf);
            int i14 = iArrCopyOf[iIndexOf];
            int length = iArrCopyOf2.length;
            i2 = 0;
            for (int i15 = 0; i15 < length; i15++) {
                if (iArrCopyOf[i15] != iArrCopyOf[iIndexOf]) {
                    int i16 = iArrCopyOf2[i15];
                    int i17 = iArrCopyOf2[iIndexOf];
                    if (i16 < i17) {
                        iArrCopyOf2[i15] = i17;
                    }
                }
            }
            int iFindPreviousItemIndex = findPreviousItemIndex(lazyStaggeredGridMeasureContext2, i14, iIndexOf);
            if (iFindPreviousItemIndex < 0) {
                break;
            }
            long jM933getSpanRangelOCCd4c = lazyStaggeredGridMeasureContext2.m933getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext2.getItemProvider(), iFindPreviousItemIndex, iIndexOf);
            int i18 = (int) (jM933getSpanRangelOCCd4c & 4294967295L);
            boolean z9 = z8;
            int i19 = (int) (jM933getSpanRangelOCCd4c >> 32);
            int i20 = i18 - i19;
            lazyStaggeredGridMeasureContext2.getLaneInfo().setLane(iFindPreviousItemIndex, i20 != 1 ? -2 : i19);
            LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ = lazyStaggeredGridMeasureContext2.getMeasuredItemProvider().m942getAndMeasurejy6DScQ(iFindPreviousItemIndex, jM933getSpanRangelOCCd4c);
            int iM937maxInRangejy6DScQ = m937maxInRangejy6DScQ(iArrCopyOf2, jM933getSpanRangelOCCd4c);
            int[] gaps2 = i20 != 1 ? lazyStaggeredGridMeasureContext2.getLaneInfo().getGaps(iFindPreviousItemIndex) : null;
            boolean z10 = z9;
            while (i19 < i18) {
                iArrCopyOf[i19] = iFindPreviousItemIndex;
                int mainAxisSizeWithSpacings = iM937maxInRangejy6DScQ + lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ.getMainAxisSizeWithSpacings() + (gaps2 == null ? 0 : gaps2[i19]);
                iArrCopyOf2[i19] = mainAxisSizeWithSpacings;
                if (lazyStaggeredGridMeasureContext2.getMainAxisAvailableSize() + mainAxisSizeWithSpacings <= 0) {
                    z10 = true;
                }
                i19++;
            }
            z8 = z10;
        }
        boolean z11 = z8;
        int i21 = -lazyStaggeredGridMeasureContext2.getBeforeContentPadding();
        int i22 = iArrCopyOf2[i2];
        if (i22 < i21) {
            i3 = i + i22;
            offsetBy(iArrCopyOf2, i21 - i22);
        } else {
            i3 = i;
        }
        offsetBy(iArrCopyOf2, lazyStaggeredGridMeasureContext2.getBeforeContentPadding());
        int i23 = -1;
        if (iIndexOf == -1) {
            iIndexOf = ArraysKt.indexOf(iArrCopyOf, i2);
        }
        if (iIndexOf != -1 && measure$lambda$41$misalignedStart(iArrCopyOf, lazyStaggeredGridMeasureContext2, iArrCopyOf2, iIndexOf) && z) {
            lazyStaggeredGridMeasureContext2.getLaneInfo().reset();
            int length2 = iArrCopyOf.length;
            int[] iArr7 = new int[length2];
            int i24 = 0;
            while (i24 < length2) {
                iArr7[i24] = i23;
                i24++;
                i23 = -1;
            }
            int length3 = iArrCopyOf2.length;
            int[] iArr8 = new int[length3];
            for (int i25 = 0; i25 < length3; i25++) {
                iArr8[i25] = iArrCopyOf2[iIndexOf];
            }
            return measure(lazyStaggeredGridMeasureContext2, i3, iArr7, iArr8, false);
        }
        int[] iArrCopyOf3 = Arrays.copyOf(iArrCopyOf, iArrCopyOf.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf3, "copyOf(this, size)");
        int length4 = iArrCopyOf2.length;
        int[] iArr9 = new int[length4];
        for (int i26 = 0; i26 < length4; i26++) {
            iArr9[i26] = -iArrCopyOf2[i26];
        }
        int mainAxisSpacing = i21 + lazyStaggeredGridMeasureContext2.getMainAxisSpacing();
        int iCoerceAtLeast = RangesKt.coerceAtLeast(lazyStaggeredGridMeasureContext2.getMainAxisAvailableSize() + lazyStaggeredGridMeasureContext2.getAfterContentPadding(), 0);
        LazyLayoutMeasureScope lazyLayoutMeasureScope = measureScope;
        boolean z12 = z11;
        int iIndexOfMinValue$default = indexOfMinValue$default(iArrCopyOf3, 0, 1, null);
        int laneCount2 = 0;
        for (int i27 = -1; iIndexOfMinValue$default != i27 && laneCount2 < lazyStaggeredGridMeasureContext2.getLaneCount(); i27 = -1) {
            int i28 = iArrCopyOf3[iIndexOfMinValue$default];
            iIndexOfMinValue$default = indexOfMinValue(iArrCopyOf3, i28);
            laneCount2++;
            if (i28 >= 0) {
                long jM933getSpanRangelOCCd4c2 = lazyStaggeredGridMeasureContext2.m933getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext2.getItemProvider(), i28, iIndexOfMinValue$default);
                ArrayDeque[] arrayDequeArr2 = arrayDequeArr;
                LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ2 = lazyStaggeredGridMeasureContext2.getMeasuredItemProvider().m942getAndMeasurejy6DScQ(i28, jM933getSpanRangelOCCd4c2);
                String str2 = str;
                int[] iArr10 = iArrCopyOf;
                int[] iArr11 = iArrCopyOf2;
                int i29 = (int) (jM933getSpanRangelOCCd4c2 & 4294967295L);
                int i30 = i3;
                int i31 = itemCount;
                int i32 = (int) (jM933getSpanRangelOCCd4c2 >> 32);
                int i33 = i29 - i32;
                lazyStaggeredGridMeasureContext2.getLaneInfo().setLane(i28, i33 != 1 ? -2 : i32);
                int iM937maxInRangejy6DScQ2 = m937maxInRangejy6DScQ(iArr9, jM933getSpanRangelOCCd4c2);
                for (int i34 = i32; i34 < i29; i34++) {
                    iArr9[i34] = lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ2.getMainAxisSizeWithSpacings() + iM937maxInRangejy6DScQ2;
                    iArrCopyOf3[i34] = i28;
                    arrayDequeArr2[i34].addLast(lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ2);
                }
                if (iM937maxInRangejy6DScQ2 >= mainAxisSpacing || iArr9[i32] > mainAxisSpacing) {
                    z7 = z12;
                } else {
                    lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ2.setVisible(false);
                    z7 = true;
                }
                laneCount2 = i33 != 1 ? lazyStaggeredGridMeasureContext2.getLaneCount() : laneCount2;
                iArrCopyOf2 = iArr11;
                z12 = z7;
                arrayDequeArr = arrayDequeArr2;
                str = str2;
                iArrCopyOf = iArr10;
                i3 = i30;
                itemCount = i31;
            }
        }
        String str3 = str;
        int[] iArr12 = iArrCopyOf;
        int[] iArr13 = iArrCopyOf2;
        ArrayDeque[] arrayDequeArr3 = arrayDequeArr;
        int i35 = i3;
        int i36 = itemCount;
        loop9: while (true) {
            int i37 = 0;
            while (true) {
                if (i37 >= length4) {
                    for (int i38 = 0; i38 < laneCount; i38++) {
                        if (!arrayDequeArr3[i38].isEmpty()) {
                            i4 = i36;
                            i5 = 1;
                            break loop9;
                        }
                    }
                    break;
                }
                int i39 = iArr9[i37];
                if (i39 < iCoerceAtLeast || i39 <= 0) {
                    break;
                }
                i37++;
            }
            i5 = 1;
            int iIndexOfMinValue$default2 = indexOfMinValue$default(iArr9, 0, 1, null);
            int iMaxOrThrow = ArraysKt.maxOrThrow(iArrCopyOf3) + 1;
            i4 = i36;
            if (iMaxOrThrow >= i4) {
                break;
            }
            boolean z13 = z12;
            int[] iArr14 = iArr13;
            int[] iArr15 = iArrCopyOf3;
            int i40 = length4;
            int[] iArr16 = iArr9;
            LazyLayoutMeasureScope lazyLayoutMeasureScope2 = lazyLayoutMeasureScope;
            String str4 = str3;
            int i41 = i35;
            int i42 = iCoerceAtLeast;
            int i43 = laneCount;
            long jM933getSpanRangelOCCd4c3 = lazyStaggeredGridMeasureContext2.m933getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext2.getItemProvider(), iMaxOrThrow, iIndexOfMinValue$default2);
            int i44 = (int) (jM933getSpanRangelOCCd4c3 & 4294967295L);
            int i45 = (int) (jM933getSpanRangelOCCd4c3 >> 32);
            int i46 = i44 - i45;
            lazyStaggeredGridMeasureContext2.getLaneInfo().setLane(iMaxOrThrow, i46 != 1 ? -2 : i45);
            LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ3 = lazyStaggeredGridMeasureContext.getMeasuredItemProvider().m942getAndMeasurejy6DScQ(iMaxOrThrow, jM933getSpanRangelOCCd4c3);
            int iM937maxInRangejy6DScQ3 = m937maxInRangejy6DScQ(iArr16, jM933getSpanRangelOCCd4c3);
            if (i46 != 1) {
                gaps = lazyStaggeredGridMeasureContext.getLaneInfo().getGaps(iMaxOrThrow);
                if (gaps == null) {
                    gaps = new int[lazyStaggeredGridMeasureContext.getLaneCount()];
                }
            } else {
                gaps = null;
            }
            for (int i47 = i45; i47 < i44; i47++) {
                if (gaps != null) {
                    gaps[i47] = iM937maxInRangejy6DScQ3 - iArr16[i47];
                }
                iArr15[i47] = iMaxOrThrow;
                iArr16[i47] = iM937maxInRangejy6DScQ3 + lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ3.getMainAxisSizeWithSpacings();
                arrayDequeArr3[i47].addLast(lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ3);
            }
            lazyStaggeredGridMeasureContext.getLaneInfo().setGaps(iMaxOrThrow, gaps);
            if (iM937maxInRangejy6DScQ3 < mainAxisSpacing && iArr16[i45] <= mainAxisSpacing) {
                lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ3.setVisible(false);
            }
            lazyStaggeredGridMeasureContext2 = lazyStaggeredGridMeasureContext;
            lazyLayoutMeasureScope = lazyLayoutMeasureScope2;
            i35 = i41;
            i36 = i4;
            iCoerceAtLeast = i42;
            z12 = z13;
            iArr13 = iArr14;
            laneCount = i43;
            iArr9 = iArr16;
            str3 = str4;
            length4 = i40;
            iArrCopyOf3 = iArr15;
        }
        int i48 = 0;
        while (i48 < laneCount) {
            ArrayDeque arrayDeque = arrayDequeArr3[i48];
            while (arrayDeque.size() > i5 && !((LazyStaggeredGridMeasuredItem) arrayDeque.first()).getIsVisible()) {
                LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem = (LazyStaggeredGridMeasuredItem) arrayDeque.removeFirst();
                int[] gaps3 = lazyStaggeredGridMeasuredItem.getSpan() != i5 ? lazyStaggeredGridMeasureContext2.getLaneInfo().getGaps(lazyStaggeredGridMeasuredItem.getIndex()) : null;
                iArr13[i48] = iArr13[i48] - (lazyStaggeredGridMeasuredItem.getMainAxisSizeWithSpacings() + (gaps3 == null ? 0 : gaps3[i48]));
                i5 = 1;
            }
            LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem2 = (LazyStaggeredGridMeasuredItem) arrayDeque.firstOrNull();
            iArr12[i48] = lazyStaggeredGridMeasuredItem2 != null ? lazyStaggeredGridMeasuredItem2.getIndex() : -1;
            i48++;
            i5 = 1;
        }
        for (int i49 : iArrCopyOf3) {
            if (i49 == i4 - 1) {
                offsetBy(iArr9, -lazyStaggeredGridMeasureContext2.getMainAxisSpacing());
                break;
            }
        }
        int i50 = 0;
        while (true) {
            if (i50 < length4) {
                if (iArr9[i50] >= lazyStaggeredGridMeasureContext2.getMainAxisAvailableSize()) {
                    iArr3 = iArr13;
                    i6 = iCoerceAtLeast;
                    iArr5 = iArrCopyOf3;
                    i7 = length4;
                    iArr6 = iArr9;
                    iArr4 = iArr12;
                    i8 = i35;
                    break;
                }
                i50++;
            } else {
                int mainAxisAvailableSize = lazyStaggeredGridMeasureContext2.getMainAxisAvailableSize() - iArr9[indexOfMaxValue(iArr9)];
                iArr3 = iArr13;
                offsetBy(iArr3, -mainAxisAvailableSize);
                offsetBy(iArr9, mainAxisAvailableSize);
                boolean z14 = false;
                loop26: while (true) {
                    int length5 = iArr3.length;
                    int i51 = 0;
                    while (true) {
                        if (i51 >= length5) {
                            iArr4 = iArr12;
                            break loop26;
                        }
                        if (iArr3[i51] < lazyStaggeredGridMeasureContext2.getBeforeContentPadding()) {
                            break;
                        }
                        i51++;
                        iArr12 = iArr12;
                    }
                    int iIndexOfMinValue$default3 = indexOfMinValue$default(iArr3, 0, 1, null);
                    int iIndexOfMaxValue = indexOfMaxValue(iArr12);
                    if (iIndexOfMinValue$default3 != iIndexOfMaxValue) {
                        if (iArr3[iIndexOfMinValue$default3] == iArr3[iIndexOfMaxValue]) {
                            iIndexOfMinValue$default3 = iIndexOfMaxValue;
                        } else {
                            z14 = true;
                        }
                    }
                    int i52 = iArr12[iIndexOfMinValue$default3];
                    if (i52 == -1) {
                        i52 = i4;
                    }
                    int iFindPreviousItemIndex2 = findPreviousItemIndex(lazyStaggeredGridMeasureContext2, i52, iIndexOfMinValue$default3);
                    if (iFindPreviousItemIndex2 < 0) {
                        iArr4 = iArr12;
                        if ((!z14 && !measure$lambda$41$misalignedStart(iArr4, lazyStaggeredGridMeasureContext2, iArr3, iIndexOfMinValue$default3)) || !z) {
                            break;
                            break;
                        }
                        lazyStaggeredGridMeasureContext2.getLaneInfo().reset();
                        int length6 = iArr4.length;
                        int[] iArr17 = new int[length6];
                        for (int i53 = 0; i53 < length6; i53++) {
                            iArr17[i53] = -1;
                        }
                        int length7 = iArr3.length;
                        int[] iArr18 = new int[length7];
                        for (int i54 = 0; i54 < length7; i54++) {
                            iArr18[i54] = iArr3[iIndexOfMinValue$default3];
                        }
                        return measure(lazyStaggeredGridMeasureContext2, i35, iArr17, iArr18, false);
                    }
                    int[] iArr19 = iArr12;
                    int i55 = i35;
                    boolean z15 = z14;
                    int i56 = iCoerceAtLeast;
                    long jM933getSpanRangelOCCd4c4 = lazyStaggeredGridMeasureContext2.m933getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext2.getItemProvider(), iFindPreviousItemIndex2, iIndexOfMinValue$default3);
                    int[] iArr20 = iArrCopyOf3;
                    int i57 = length4;
                    int i58 = (int) (jM933getSpanRangelOCCd4c4 & 4294967295L);
                    int[] iArr21 = iArr9;
                    int i59 = (int) (jM933getSpanRangelOCCd4c4 >> 32);
                    int i60 = i58 - i59;
                    lazyStaggeredGridMeasureContext2.getLaneInfo().setLane(iFindPreviousItemIndex2, i60 != 1 ? -2 : i59);
                    LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ4 = lazyStaggeredGridMeasureContext2.getMeasuredItemProvider().m942getAndMeasurejy6DScQ(iFindPreviousItemIndex2, jM933getSpanRangelOCCd4c4);
                    int iM937maxInRangejy6DScQ4 = m937maxInRangejy6DScQ(iArr3, jM933getSpanRangelOCCd4c4);
                    int[] gaps4 = i60 != 1 ? lazyStaggeredGridMeasureContext2.getLaneInfo().getGaps(iFindPreviousItemIndex2) : null;
                    boolean z16 = z15;
                    for (int i61 = i59; i61 < i58; i61++) {
                        if (iArr3[i61] != iM937maxInRangejy6DScQ4) {
                            z16 = true;
                        }
                        arrayDequeArr3[i61].addFirst(lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ4);
                        iArr19[i61] = iFindPreviousItemIndex2;
                        iArr3[i61] = iM937maxInRangejy6DScQ4 + lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ4.getMainAxisSizeWithSpacings() + (gaps4 == null ? 0 : gaps4[i61]);
                    }
                    iCoerceAtLeast = i56;
                    iArrCopyOf3 = iArr20;
                    i35 = i55;
                    z14 = z16;
                    length4 = i57;
                    iArr9 = iArr21;
                    iArr12 = iArr19;
                }
                int i62 = i35;
                i6 = iCoerceAtLeast;
                iArr5 = iArrCopyOf3;
                i7 = length4;
                int[] iArr22 = iArr9;
                if (z14 && z) {
                    lazyStaggeredGridMeasureContext2.getLaneInfo().reset();
                    return measure(lazyStaggeredGridMeasureContext2, i62, iArr4, iArr3, false);
                }
                i8 = i62 + mainAxisAvailableSize;
                int i63 = iArr3[indexOfMinValue$default(iArr3, 0, 1, null)];
                if (i63 >= 0) {
                    iArr6 = iArr22;
                    break;
                }
                i8 += i63;
                iArr6 = iArr22;
                offsetBy(iArr6, i63);
                offsetBy(iArr3, -i63);
                break;
            }
        }
        float scrollToBeConsumed = (MathKt.getSign(Math.round(lazyStaggeredGridMeasureContext2.getState().getScrollToBeConsumed())) != MathKt.getSign(i8) || Math.abs(Math.round(lazyStaggeredGridMeasureContext2.getState().getScrollToBeConsumed())) < Math.abs(i8)) ? lazyStaggeredGridMeasureContext2.getState().getScrollToBeConsumed() : i8;
        int[] iArrCopyOf4 = Arrays.copyOf(iArr3, iArr3.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf4, str3);
        int length8 = iArrCopyOf4.length;
        for (int i64 = 0; i64 < length8; i64++) {
            iArrCopyOf4[i64] = -iArrCopyOf4[i64];
        }
        if (lazyStaggeredGridMeasureContext2.getBeforeContentPadding() > lazyStaggeredGridMeasureContext2.getMainAxisSpacing()) {
            for (int i65 = 0; i65 < laneCount; i65++) {
                ArrayDeque arrayDeque2 = arrayDequeArr3[i65];
                int size = arrayDeque2.size();
                int i66 = 0;
                while (i66 < size) {
                    LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem3 = (LazyStaggeredGridMeasuredItem) arrayDeque2.get(i66);
                    int[] gaps5 = lazyStaggeredGridMeasureContext2.getLaneInfo().getGaps(lazyStaggeredGridMeasuredItem3.getIndex());
                    int mainAxisSizeWithSpacings2 = lazyStaggeredGridMeasuredItem3.getMainAxisSizeWithSpacings() + (gaps5 == null ? 0 : gaps5[i65]);
                    if (i66 == CollectionsKt.getLastIndex(arrayDeque2) || (i12 = iArr3[i65]) == 0 || i12 < mainAxisSizeWithSpacings2) {
                        break;
                    }
                    iArr3[i65] = i12 - mainAxisSizeWithSpacings2;
                    i66++;
                    iArr4[i65] = ((LazyStaggeredGridMeasuredItem) arrayDeque2.get(i66)).getIndex();
                }
            }
        }
        int beforeContentPadding = lazyStaggeredGridMeasureContext2.getBeforeContentPadding() + lazyStaggeredGridMeasureContext2.getAfterContentPadding();
        if (lazyStaggeredGridMeasureContext2.getIsVertical()) {
            iM6850constrainWidthK40F9xA = Constraints.m6833getMaxWidthimpl(lazyStaggeredGridMeasureContext2.getConstraints());
        } else {
            iM6850constrainWidthK40F9xA = ConstraintsKt.m6850constrainWidthK40F9xA(lazyStaggeredGridMeasureContext2.getConstraints(), ArraysKt.maxOrThrow(iArr6) + beforeContentPadding);
        }
        int i67 = iM6850constrainWidthK40F9xA;
        if (lazyStaggeredGridMeasureContext2.getIsVertical()) {
            iM6832getMaxHeightimpl = ConstraintsKt.m6849constrainHeightK40F9xA(lazyStaggeredGridMeasureContext2.getConstraints(), ArraysKt.maxOrThrow(iArr6) + beforeContentPadding);
        } else {
            iM6832getMaxHeightimpl = Constraints.m6832getMaxHeightimpl(lazyStaggeredGridMeasureContext2.getConstraints());
        }
        int i68 = iM6832getMaxHeightimpl;
        int iMin = (Math.min(lazyStaggeredGridMeasureContext2.getIsVertical() ? i68 : i67, lazyStaggeredGridMeasureContext2.getMainAxisAvailableSize()) - lazyStaggeredGridMeasureContext2.getBeforeContentPadding()) + lazyStaggeredGridMeasureContext2.getAfterContentPadding();
        int i69 = iArrCopyOf4[0];
        List<Integer> pinnedItems = lazyStaggeredGridMeasureContext2.getPinnedItems();
        int size2 = pinnedItems.size() - 1;
        if (size2 >= 0) {
            int i70 = size2;
            int mainAxisSizeWithSpacings3 = i69;
            listEmptyList = null;
            while (true) {
                int i71 = i70 - 1;
                int iIntValue = pinnedItems.get(i70).intValue();
                int lane = lazyStaggeredGridMeasureContext2.getLaneInfo().getLane(iIntValue);
                i9 = beforeContentPadding;
                if (lane == -2 || lane == -1) {
                    int i72 = 0;
                    while (true) {
                        if (i72 < laneCount) {
                            LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem4 = (LazyStaggeredGridMeasuredItem) arrayDequeArr3[i72].firstOrNull();
                            if ((lazyStaggeredGridMeasuredItem4 != null ? lazyStaggeredGridMeasuredItem4.getIndex() : -1) > iIntValue) {
                                i72++;
                            }
                        }
                    }
                } else {
                    LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem5 = (LazyStaggeredGridMeasuredItem) arrayDequeArr3[lane].firstOrNull();
                    z6 = (lazyStaggeredGridMeasuredItem5 != null ? lazyStaggeredGridMeasuredItem5.getIndex() : -1) > iIntValue;
                }
                if (z6) {
                    List list2 = listEmptyList;
                    long jM933getSpanRangelOCCd4c5 = lazyStaggeredGridMeasureContext2.m933getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext2.getItemProvider(), iIntValue, 0);
                    ArrayList arrayList = list2 == null ? new ArrayList() : list2;
                    LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ5 = lazyStaggeredGridMeasureContext2.getMeasuredItemProvider().m942getAndMeasurejy6DScQ(iIntValue, jM933getSpanRangelOCCd4c5);
                    mainAxisSizeWithSpacings3 -= lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ5.getMainAxisSizeWithSpacings();
                    lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ5.position(mainAxisSizeWithSpacings3, 0, iMin);
                    arrayList.add(lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ5);
                    listEmptyList = arrayList;
                }
                if (i71 < 0) {
                    break;
                }
                beforeContentPadding = i9;
                i70 = i71;
                laneCount = laneCount;
            }
        } else {
            i9 = beforeContentPadding;
            listEmptyList = null;
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List<LazyStaggeredGridMeasuredItem> listCalculateVisibleItems = calculateVisibleItems(lazyStaggeredGridMeasureContext2, arrayDequeArr3, iArrCopyOf4, iMin);
        int mainAxisSizeWithSpacings4 = iArrCopyOf4[0];
        List<Integer> pinnedItems2 = lazyStaggeredGridMeasureContext2.getPinnedItems();
        int size3 = pinnedItems2.size();
        int i73 = 0;
        ArrayList arrayListEmptyList = null;
        while (i73 < size3) {
            int iIntValue2 = pinnedItems2.get(i73).intValue();
            if (iIntValue2 >= i4) {
                list = pinnedItems2;
            } else {
                int lane2 = lazyStaggeredGridMeasureContext2.getLaneInfo().getLane(iIntValue2);
                list = pinnedItems2;
                if (lane2 != -2 && lane2 != -1) {
                    z5 = iArr5[lane2] < iIntValue2;
                } else {
                    int length9 = iArr5.length;
                    int i74 = 0;
                    while (true) {
                        if (i74 < length9) {
                            if (iArr5[i74] < iIntValue2) {
                                i74++;
                            }
                        }
                    }
                }
                if (z5) {
                    long jM933getSpanRangelOCCd4c6 = lazyStaggeredGridMeasureContext2.m933getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext2.getItemProvider(), iIntValue2, 0);
                    if (arrayListEmptyList == null) {
                        arrayListEmptyList = new ArrayList();
                    }
                    List list3 = arrayListEmptyList;
                    LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ6 = lazyStaggeredGridMeasureContext2.getMeasuredItemProvider().m942getAndMeasurejy6DScQ(iIntValue2, jM933getSpanRangelOCCd4c6);
                    lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ6.position(mainAxisSizeWithSpacings4, 0, iMin);
                    mainAxisSizeWithSpacings4 += lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ6.getMainAxisSizeWithSpacings();
                    list3.add(lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ6);
                    arrayListEmptyList = list3;
                }
                i73++;
                pinnedItems2 = list;
                listEmptyList = listEmptyList;
                size3 = size3;
                iArr4 = iArr4;
            }
            if (z5) {
                long jM933getSpanRangelOCCd4c7 = lazyStaggeredGridMeasureContext2.m933getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext2.getItemProvider(), iIntValue2, 0);
                if (arrayListEmptyList == null) {
                    arrayListEmptyList = new ArrayList();
                }
                List list4 = arrayListEmptyList;
                LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ7 = lazyStaggeredGridMeasureContext2.getMeasuredItemProvider().m942getAndMeasurejy6DScQ(iIntValue2, jM933getSpanRangelOCCd4c7);
                lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ7.position(mainAxisSizeWithSpacings4, 0, iMin);
                mainAxisSizeWithSpacings4 += lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ7.getMainAxisSizeWithSpacings();
                list4.add(lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ7);
                arrayListEmptyList = list4;
            }
            i73++;
            pinnedItems2 = list;
            listEmptyList = listEmptyList;
            size3 = size3;
            iArr4 = iArr4;
        }
        List list5 = listEmptyList;
        int[] iArr23 = iArr4;
        if (arrayListEmptyList == null) {
            arrayListEmptyList = CollectionsKt.emptyList();
        }
        final ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(list5);
        arrayList2.addAll(listCalculateVisibleItems);
        arrayList2.addAll(arrayListEmptyList);
        lazyStaggeredGridMeasureContext2.getState().getItemAnimator$foundation_release().onMeasured((int) scrollToBeConsumed, i67, i68, arrayList2, lazyStaggeredGridMeasureContext2.getMeasuredItemProvider().getKeyIndexMap(), lazyStaggeredGridMeasureContext2.getMeasuredItemProvider(), lazyStaggeredGridMeasureContext2.getIsVertical(), false, lazyStaggeredGridMeasureContext2.getLaneCount(), false, ArraysKt.minOrThrow(iArr3), ArraysKt.maxOrThrow(iArr6) + i9, lazyStaggeredGridMeasureContext2.getCoroutineScope(), lazyStaggeredGridMeasureContext2.getGraphicsContext());
        long jM884getMinSizeToFitDisappearingItemsYbymL2g2 = lazyStaggeredGridMeasureContext2.getState().getItemAnimator$foundation_release().m884getMinSizeToFitDisappearingItemsYbymL2g();
        if (IntSize.m7063equalsimpl0(jM884getMinSizeToFitDisappearingItemsYbymL2g2, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
            i10 = i68;
            i11 = i67;
        } else {
            int i75 = lazyStaggeredGridMeasureContext2.getIsVertical() ? i68 : i67;
            int iM6850constrainWidthK40F9xA2 = ConstraintsKt.m6850constrainWidthK40F9xA(lazyStaggeredGridMeasureContext2.getConstraints(), Math.max(i67, IntSize.m7065getWidthimpl(jM884getMinSizeToFitDisappearingItemsYbymL2g2)));
            int iM6849constrainHeightK40F9xA = ConstraintsKt.m6849constrainHeightK40F9xA(lazyStaggeredGridMeasureContext2.getConstraints(), Math.max(i68, IntSize.m7064getHeightimpl(jM884getMinSizeToFitDisappearingItemsYbymL2g2)));
            int i76 = lazyStaggeredGridMeasureContext2.getIsVertical() ? iM6849constrainHeightK40F9xA : iM6850constrainWidthK40F9xA2;
            if (i76 != i75) {
                int size4 = arrayList2.size();
                for (int i77 = 0; i77 < size4; i77++) {
                    ((LazyStaggeredGridMeasuredItem) arrayList2.get(i77)).updateMainAxisLayoutSize(i76);
                }
            }
            i11 = iM6850constrainWidthK40F9xA2;
            i10 = iM6849constrainHeightK40F9xA;
        }
        int i78 = i7;
        int i79 = 0;
        while (true) {
            if (i79 >= i78) {
                z2 = false;
                break;
            }
            if (iArr6[i79] > lazyStaggeredGridMeasureContext2.getMainAxisAvailableSize()) {
                z2 = true;
                break;
            }
            i79++;
        }
        if (z2) {
            z3 = true;
        } else {
            int length10 = iArr5.length;
            int i80 = 0;
            while (true) {
                if (i80 >= length10) {
                    z4 = true;
                    break;
                }
                if (!(iArr5[i80] < i4 + (-1))) {
                    z4 = false;
                    break;
                }
                i80++;
            }
            if (z4) {
                z3 = true;
            } else {
                z3 = false;
            }
        }
        return new LazyStaggeredGridMeasureResult(iArr23, iArr3, scrollToBeConsumed, MeasureScope.CC.layout$default(lazyLayoutMeasureScope, i11, i10, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureKt$measure$1$33
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope placementScope) {
                List<LazyStaggeredGridMeasuredItem> list6 = arrayList2;
                LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext3 = lazyStaggeredGridMeasureContext2;
                int size5 = list6.size();
                for (int i81 = 0; i81 < size5; i81++) {
                    list6.get(i81).place(placementScope, lazyStaggeredGridMeasureContext3);
                }
                ObservableScopeInvalidator.m901attachToScopeimpl(lazyStaggeredGridMeasureContext2.getState().m946getPlacementScopeInvalidatorzYiylxw$foundation_release());
            }
        }, 4, null), z3, lazyStaggeredGridMeasureContext2.getIsVertical(), z12, lazyStaggeredGridMeasureContext2.getResolvedSlots(), lazyStaggeredGridMeasureContext2.getItemProvider().getSpanProvider(), lazyLayoutMeasureScope, i4, listCalculateVisibleItems, IntSizeKt.IntSize(i11, i10), i21, i6, lazyStaggeredGridMeasureContext2.getBeforeContentPadding(), lazyStaggeredGridMeasureContext2.getAfterContentPadding(), lazyStaggeredGridMeasureContext2.getMainAxisSpacing(), lazyStaggeredGridMeasureContext2.getCoroutineScope(), null);
    }

    private static final boolean measure$lambda$41$hasSpaceBeforeFirst(int[] iArr, int[] iArr2, LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext) {
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            int i2 = iArr[i];
            if (iArr2[i] < Math.max(-lazyStaggeredGridMeasureContext.getMainAxisSpacing(), 0) && i2 > 0) {
                return true;
            }
        }
        return false;
    }

    private static final boolean measure$lambda$41$misalignedStart(int[] iArr, LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, int[] iArr2, int i) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (findPreviousItemIndex(lazyStaggeredGridMeasureContext, iArr[i2], i2) == -1 && iArr2[i2] != iArr2[i]) {
                return true;
            }
        }
        int length2 = iArr.length;
        for (int i3 = 0; i3 < length2; i3++) {
            if (findPreviousItemIndex(lazyStaggeredGridMeasureContext, iArr[i3], i3) != -1 && iArr2[i3] >= iArr2[i]) {
                return true;
            }
        }
        int lane = lazyStaggeredGridMeasureContext.getLaneInfo().getLane(0);
        return (lane == 0 || lane == -1 || lane == -2) ? false : true;
    }

    private static final List<LazyStaggeredGridMeasuredItem> calculateVisibleItems(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, ArrayDeque<LazyStaggeredGridMeasuredItem>[] arrayDequeArr, int[] iArr, int i) {
        int size = 0;
        for (ArrayDeque<LazyStaggeredGridMeasuredItem> arrayDeque : arrayDequeArr) {
            size += arrayDeque.size();
        }
        ArrayList arrayList = new ArrayList(size);
        while (true) {
            for (ArrayDeque<LazyStaggeredGridMeasuredItem> arrayDeque2 : arrayDequeArr) {
                if (!arrayDeque2.isEmpty()) {
                    int length = arrayDequeArr.length;
                    int i2 = -1;
                    int i3 = Integer.MAX_VALUE;
                    for (int i4 = 0; i4 < length; i4++) {
                        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemFirstOrNull = arrayDequeArr[i4].firstOrNull();
                        int index = lazyStaggeredGridMeasuredItemFirstOrNull != null ? lazyStaggeredGridMeasuredItemFirstOrNull.getIndex() : Integer.MAX_VALUE;
                        if (i3 > index) {
                            i2 = i4;
                            i3 = index;
                        }
                    }
                    LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemRemoveFirst = arrayDequeArr[i2].removeFirst();
                    if (lazyStaggeredGridMeasuredItemRemoveFirst.getLane() == i2) {
                        long jM948constructorimpl = SpanRange.m948constructorimpl(lazyStaggeredGridMeasuredItemRemoveFirst.getLane(), lazyStaggeredGridMeasuredItemRemoveFirst.getSpan());
                        int iM937maxInRangejy6DScQ = m937maxInRangejy6DScQ(iArr, jM948constructorimpl);
                        lazyStaggeredGridMeasuredItemRemoveFirst.position(iM937maxInRangejy6DScQ, lazyStaggeredGridMeasureContext.getResolvedSlots().getPositions()[i2], i);
                        arrayList.add(lazyStaggeredGridMeasuredItemRemoveFirst);
                        int i5 = (int) (jM948constructorimpl & 4294967295L);
                        for (int i6 = (int) (jM948constructorimpl >> 32); i6 < i5; i6++) {
                            iArr[i6] = lazyStaggeredGridMeasuredItemRemoveFirst.getMainAxisSizeWithSpacings() + iM937maxInRangejy6DScQ;
                        }
                    }
                }
            }
            return arrayList;
        }
    }

    private static final List<LazyStaggeredGridMeasuredItem> calculateExtraItems(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, Function1<? super LazyStaggeredGridMeasuredItem, Unit> function1, Function1<? super Integer, Boolean> function2, boolean z) {
        List<Integer> pinnedItems = lazyStaggeredGridMeasureContext.getPinnedItems();
        ArrayList arrayList = null;
        if (z) {
            int size = pinnedItems.size() - 1;
            if (size >= 0) {
                while (true) {
                    int i = size - 1;
                    int iIntValue = pinnedItems.get(size).intValue();
                    if (function2.invoke(Integer.valueOf(iIntValue)).booleanValue()) {
                        long jM933getSpanRangelOCCd4c = lazyStaggeredGridMeasureContext.m933getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext.getItemProvider(), iIntValue, 0);
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ = lazyStaggeredGridMeasureContext.getMeasuredItemProvider().m942getAndMeasurejy6DScQ(iIntValue, jM933getSpanRangelOCCd4c);
                        function1.invoke(lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ);
                        arrayList.add(lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ);
                    }
                    if (i < 0) {
                        break;
                    }
                    size = i;
                }
            }
        } else {
            int size2 = pinnedItems.size();
            for (int i2 = 0; i2 < size2; i2++) {
                int iIntValue2 = pinnedItems.get(i2).intValue();
                if (function2.invoke(Integer.valueOf(iIntValue2)).booleanValue()) {
                    long jM933getSpanRangelOCCd4c2 = lazyStaggeredGridMeasureContext.m933getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext.getItemProvider(), iIntValue2, 0);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ2 = lazyStaggeredGridMeasureContext.getMeasuredItemProvider().m942getAndMeasurejy6DScQ(iIntValue2, jM933getSpanRangelOCCd4c2);
                    function1.invoke(lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ2);
                    arrayList.add(lazyStaggeredGridMeasuredItemM942getAndMeasurejy6DScQ2);
                }
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    /* JADX INFO: renamed from: forEach-nIS5qE8, reason: not valid java name */
    private static final void m936forEachnIS5qE8(long j, Function1<? super Integer, Unit> function1) {
        int i = (int) (j & 4294967295L);
        for (int i2 = (int) (j >> 32); i2 < i; i2++) {
            function1.invoke(Integer.valueOf(i2));
        }
    }

    private static final void offsetBy(int[] iArr, int i) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = iArr[i2] + i;
        }
    }

    /* JADX INFO: renamed from: maxInRange-jy6DScQ, reason: not valid java name */
    private static final int m937maxInRangejy6DScQ(int[] iArr, long j) {
        int i = (int) (j & 4294967295L);
        int iMax = Integer.MIN_VALUE;
        for (int i2 = (int) (j >> 32); i2 < i; i2++) {
            iMax = Math.max(iMax, iArr[i2]);
        }
        return iMax;
    }

    public static /* synthetic */ int indexOfMinValue$default(int[] iArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Integer.MIN_VALUE;
        }
        return indexOfMinValue(iArr, i);
    }

    public static final int indexOfMinValue(int[] iArr, int i) {
        int length = iArr.length;
        int i2 = -1;
        int i3 = Integer.MAX_VALUE;
        for (int i4 = 0; i4 < length; i4++) {
            int i5 = i + 1;
            int i6 = iArr[i4];
            if (i5 <= i6 && i6 < i3) {
                i2 = i4;
                i3 = i6;
            }
        }
        return i2;
    }

    private static final <T> int indexOfMinBy(T[] tArr, Function1<? super T, Integer> function1) {
        int length = tArr.length;
        int i = -1;
        int i2 = Integer.MAX_VALUE;
        for (int i3 = 0; i3 < length; i3++) {
            int iIntValue = function1.invoke(tArr[i3]).intValue();
            if (i2 > iIntValue) {
                i = i3;
                i2 = iIntValue;
            }
        }
        return i;
    }

    private static final int indexOfMaxValue(int[] iArr) {
        int length = iArr.length;
        int i = -1;
        int i2 = Integer.MIN_VALUE;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = iArr[i3];
            if (i2 < i4) {
                i = i3;
                i2 = i4;
            }
        }
        return i;
    }

    private static final int[] transform(int[] iArr, Function1<? super Integer, Integer> function1) {
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            iArr[i] = function1.invoke(Integer.valueOf(iArr[i])).intValue();
        }
        return iArr;
    }

    private static final void ensureIndicesInRange(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, int[] iArr, int i) {
        int length = iArr.length - 1;
        if (length < 0) {
            return;
        }
        while (true) {
            int i2 = length - 1;
            while (true) {
                if (iArr[length] < i && lazyStaggeredGridMeasureContext.getLaneInfo().assignedToLane(iArr[length], length)) {
                    break;
                } else {
                    iArr[length] = findPreviousItemIndex(lazyStaggeredGridMeasureContext, iArr[length], length);
                }
            }
            if (iArr[length] >= 0 && !lazyStaggeredGridMeasureContext.isFullSpan(lazyStaggeredGridMeasureContext.getItemProvider(), iArr[length])) {
                lazyStaggeredGridMeasureContext.getLaneInfo().setLane(iArr[length], length);
            }
            if (i2 < 0) {
                return;
            } else {
                length = i2;
            }
        }
    }

    private static final int findPreviousItemIndex(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, int i, int i2) {
        return lazyStaggeredGridMeasureContext.getLaneInfo().findPreviousItemIndex(i, i2);
    }

    private static final <T> void fastForEach(List<? extends T> list, boolean z, Function1<? super T, Unit> function1) {
        if (z) {
            int size = list.size() - 1;
            if (size < 0) {
                return;
            }
            while (true) {
                int i = size - 1;
                function1.invoke(list.get(size));
                if (i < 0) {
                    return;
                } else {
                    size = i;
                }
            }
        } else {
            int size2 = list.size();
            for (int i2 = 0; i2 < size2; i2++) {
                function1.invoke(list.get(i2));
            }
        }
    }

    static /* synthetic */ void fastForEach$default(List list, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if (z) {
            int size = list.size() - 1;
            if (size < 0) {
                return;
            }
            while (true) {
                int i2 = size - 1;
                function1.invoke(list.get(size));
                if (i2 < 0) {
                    return;
                } else {
                    size = i2;
                }
            }
        } else {
            int size2 = list.size();
            for (int i3 = 0; i3 < size2; i3++) {
                function1.invoke(list.get(i3));
            }
        }
    }
}
