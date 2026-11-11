package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyGridMeasure.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¦\u0001\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0002\u001aA\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\nH\u0083\b\u001a\u008c\u0001\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002\u001a¸\u0002\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00020-2\u0006\u0010.\u001a\u00020\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u00012\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020423\u00105\u001a/\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(8\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020+090\u00010\n2/\u0010:\u001a+\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020=0\n¢\u0006\u0002\b>\u0012\u0004\u0012\u00020?0;H\u0000ø\u0001\u0000¢\u0006\u0004\b@\u0010A\u001a+\u0010B\u001a\u00020=\"\u0004\b\u0000\u0010C*\b\u0012\u0004\u0012\u0002HC0\r2\f\u0010D\u001a\b\u0012\u0004\u0012\u0002HC0EH\u0002¢\u0006\u0002\u0010F\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006G"}, d2 = {"calculateExtraItems", "", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "pinnedItems", "", "measuredItemProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;", "measuredLineProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;", "filter", "Lkotlin/Function1;", "", "calculateItemsOffsets", "", "lines", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLine;", "itemsBefore", "itemsAfter", "layoutWidth", "layoutHeight", "finalMainAxisOffset", "maxOffset", "firstLineScrollOffset", "isVertical", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "measureLazyGrid", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "itemsCount", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenLines", "firstVisibleLineIndex", "firstVisibleLineScrollOffset", "scrollToBeConsumed", "", "constraints", "Landroidx/compose/ui/unit/Constraints;", "itemAnimator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "slotsPerLine", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "placementScopeInvalidator", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "prefetchInfoRetriever", "Lkotlin/ParameterName;", "name", "line", "Lkotlin/Pair;", TtmlNode.TAG_LAYOUT, "Lkotlin/Function3;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "measureLazyGrid-OZKpZRA", "(ILandroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;IIIIIIFJZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;ILjava/util/List;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/graphics/GraphicsContext;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "addAllFromArray", ExifInterface.GPS_DIRECTION_TRUE, "arr", "", "(Ljava/util/List;[Ljava/lang/Object;)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyGridMeasureKt {
    private static final int calculateItemsOffsets$reverseAware(int i, boolean z, int i2) {
        return !z ? i : (i2 - i) - 1;
    }

    /* JADX INFO: renamed from: measureLazyGrid-OZKpZRA, reason: not valid java name */
    public static final LazyGridMeasureResult m860measureLazyGridOZKpZRA(int i, LazyGridMeasuredLineProvider lazyGridMeasuredLineProvider, LazyGridMeasuredItemProvider lazyGridMeasuredItemProvider, int i2, int i3, int i4, int i5, int i6, int i7, float f, long j, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density, LazyLayoutItemAnimator<LazyGridMeasuredItem> lazyLayoutItemAnimator, int i8, List<Integer> list, CoroutineScope coroutineScope, final MutableState<Unit> mutableState, GraphicsContext graphicsContext, Function1<? super Integer, ? extends List<Pair<Integer, Constraints>>> function1, Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> function3) {
        int i9;
        int iM6850constrainWidthK40F9xA;
        int iM6832getMaxHeightimpl;
        int iM6850constrainWidthK40F9xA2;
        List<LazyGridMeasuredItem> list2;
        LazyGridMeasuredItem[] items;
        LazyGridMeasuredItem lazyGridMeasuredItem;
        int i10;
        int i11;
        int i12 = i;
        List<Integer> list3 = list;
        if (i3 < 0) {
            throw new IllegalArgumentException("negative beforeContentPadding".toString());
        }
        if (i4 < 0) {
            throw new IllegalArgumentException("negative afterContentPadding".toString());
        }
        if (i12 <= 0) {
            int iM6835getMinWidthimpl = Constraints.m6835getMinWidthimpl(j);
            int iM6834getMinHeightimpl = Constraints.m6834getMinHeightimpl(j);
            lazyLayoutItemAnimator.onMeasured(0, iM6835getMinWidthimpl, iM6834getMinHeightimpl, new ArrayList(), lazyGridMeasuredItemProvider.getKeyIndexMap(), lazyGridMeasuredItemProvider, z, false, i8, false, 0, 0, coroutineScope, graphicsContext);
            long jM884getMinSizeToFitDisappearingItemsYbymL2g = lazyLayoutItemAnimator.m884getMinSizeToFitDisappearingItemsYbymL2g();
            if (!IntSize.m7063equalsimpl0(jM884getMinSizeToFitDisappearingItemsYbymL2g, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                iM6835getMinWidthimpl = ConstraintsKt.m6850constrainWidthK40F9xA(j, IntSize.m7065getWidthimpl(jM884getMinSizeToFitDisappearingItemsYbymL2g));
                iM6834getMinHeightimpl = ConstraintsKt.m6849constrainHeightK40F9xA(j, IntSize.m7064getHeightimpl(jM884getMinSizeToFitDisappearingItemsYbymL2g));
            }
            return new LazyGridMeasureResult(null, 0, false, 0.0f, function3.invoke(Integer.valueOf(iM6835getMinWidthimpl), Integer.valueOf(iM6834getMinHeightimpl), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$measureLazyGrid$3
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope placementScope) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }
            }), false, coroutineScope, density, i8, function1, CollectionsKt.emptyList(), -i3, i2 + i4, 0, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5);
        }
        int iRound = Math.round(f);
        int i13 = i7 - iRound;
        if (i6 == 0 && i13 < 0) {
            iRound += i13;
            i13 = 0;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        int i14 = -i3;
        int i15 = (i5 < 0 ? i5 : 0) + i14;
        int mainAxisSizeWithSpacings = i13 + i15;
        int i16 = i6;
        while (mainAxisSizeWithSpacings < 0 && i16 > 0) {
            i16--;
            LazyGridMeasuredLine andMeasure = lazyGridMeasuredLineProvider.getAndMeasure(i16);
            arrayDeque.add(0, andMeasure);
            mainAxisSizeWithSpacings += andMeasure.getMainAxisSizeWithSpacings();
        }
        if (mainAxisSizeWithSpacings < i15) {
            iRound += mainAxisSizeWithSpacings;
            mainAxisSizeWithSpacings = i15;
        }
        int mainAxisSizeWithSpacings2 = mainAxisSizeWithSpacings - i15;
        int i17 = i2 + i4;
        int i18 = iRound;
        int iCoerceAtLeast = RangesKt.coerceAtLeast(i17, 0);
        int i19 = i16;
        int i20 = i19;
        int mainAxisSizeWithSpacings3 = -mainAxisSizeWithSpacings2;
        int i21 = 0;
        boolean z3 = false;
        while (i21 < arrayDeque.size()) {
            if (mainAxisSizeWithSpacings3 >= iCoerceAtLeast) {
                arrayDeque.remove(i21);
                z3 = true;
            } else {
                i20++;
                mainAxisSizeWithSpacings3 += ((LazyGridMeasuredLine) arrayDeque.get(i21)).getMainAxisSizeWithSpacings();
                i21++;
            }
        }
        int i22 = i20;
        while (i22 < i12 && (mainAxisSizeWithSpacings3 < iCoerceAtLeast || mainAxisSizeWithSpacings3 <= 0 || arrayDeque.isEmpty())) {
            LazyGridMeasuredLine andMeasure2 = lazyGridMeasuredLineProvider.getAndMeasure(i22);
            if (andMeasure2.isEmpty()) {
                break;
            }
            int mainAxisSizeWithSpacings4 = mainAxisSizeWithSpacings3 + andMeasure2.getMainAxisSizeWithSpacings();
            if (mainAxisSizeWithSpacings4 <= i15) {
                i10 = iCoerceAtLeast;
                i11 = mainAxisSizeWithSpacings4;
                if (((LazyGridMeasuredItem) ArraysKt.last(andMeasure2.getItems())).getIndex() != i12 - 1) {
                    mainAxisSizeWithSpacings2 -= andMeasure2.getMainAxisSizeWithSpacings();
                    i19 = i22 + 1;
                    z3 = true;
                }
                i22++;
                mainAxisSizeWithSpacings3 = i11;
                iCoerceAtLeast = i10;
            } else {
                i10 = iCoerceAtLeast;
                i11 = mainAxisSizeWithSpacings4;
            }
            arrayDeque.add(andMeasure2);
            i22++;
            mainAxisSizeWithSpacings3 = i11;
            iCoerceAtLeast = i10;
        }
        if (mainAxisSizeWithSpacings3 < i2) {
            int i23 = i2 - mainAxisSizeWithSpacings3;
            mainAxisSizeWithSpacings2 -= i23;
            mainAxisSizeWithSpacings3 += i23;
            while (mainAxisSizeWithSpacings2 < i3 && i19 > 0) {
                int i24 = i19 - 1;
                LazyGridMeasuredLine andMeasure3 = lazyGridMeasuredLineProvider.getAndMeasure(i24);
                arrayDeque.add(0, andMeasure3);
                mainAxisSizeWithSpacings2 += andMeasure3.getMainAxisSizeWithSpacings();
                i19 = i24;
            }
            i9 = i18 + i23;
            if (mainAxisSizeWithSpacings2 < 0) {
                i9 += mainAxisSizeWithSpacings2;
                mainAxisSizeWithSpacings3 += mainAxisSizeWithSpacings2;
                mainAxisSizeWithSpacings2 = 0;
            }
        } else {
            i9 = i18;
        }
        float f2 = (MathKt.getSign(Math.round(f)) != MathKt.getSign(i9) || Math.abs(Math.round(f)) < Math.abs(i9)) ? f : i9;
        if (mainAxisSizeWithSpacings2 < 0) {
            throw new IllegalArgumentException("negative initial offset".toString());
        }
        int i25 = -mainAxisSizeWithSpacings2;
        LazyGridMeasuredLine lazyGridMeasuredLine = (LazyGridMeasuredLine) arrayDeque.first();
        LazyGridMeasuredItem lazyGridMeasuredItem2 = (LazyGridMeasuredItem) ArraysKt.firstOrNull(lazyGridMeasuredLine.getItems());
        int index = lazyGridMeasuredItem2 != null ? lazyGridMeasuredItem2.getIndex() : 0;
        LazyGridMeasuredLine lazyGridMeasuredLine2 = (LazyGridMeasuredLine) arrayDeque.lastOrNull();
        int index2 = (lazyGridMeasuredLine2 == null || (items = lazyGridMeasuredLine2.getItems()) == null || (lazyGridMeasuredItem = (LazyGridMeasuredItem) ArraysKt.lastOrNull(items)) == null) ? 0 : lazyGridMeasuredItem.getIndex();
        int size = list3.size();
        ArrayList arrayListEmptyList = null;
        List listEmptyList = null;
        int i26 = 0;
        while (i26 < size) {
            int i27 = size;
            int iIntValue = list3.get(i26).intValue();
            if (iIntValue >= 0 && iIntValue < index) {
                int iSpanOf = lazyGridMeasuredLineProvider.spanOf(iIntValue);
                LazyGridMeasuredItem lazyGridMeasuredItemMo833getAndMeasurehBUhpc = lazyGridMeasuredItemProvider.mo833getAndMeasurehBUhpc(iIntValue, 0, iSpanOf, lazyGridMeasuredLineProvider.m864childConstraintsJhjzzOo$foundation_release(0, iSpanOf));
                ArrayList arrayList = listEmptyList == null ? new ArrayList() : listEmptyList;
                arrayList.add(lazyGridMeasuredItemMo833getAndMeasurehBUhpc);
                listEmptyList = arrayList;
            }
            i26++;
            size = i27;
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List list4 = listEmptyList;
        int size2 = list3.size();
        int i28 = 0;
        while (i28 < size2) {
            int i29 = i28;
            int iIntValue2 = list3.get(i28).intValue();
            if (index2 + 1 <= iIntValue2 && iIntValue2 < i12) {
                int iSpanOf2 = lazyGridMeasuredLineProvider.spanOf(iIntValue2);
                LazyGridMeasuredItem lazyGridMeasuredItemMo833getAndMeasurehBUhpc2 = lazyGridMeasuredItemProvider.mo833getAndMeasurehBUhpc(iIntValue2, 0, iSpanOf2, lazyGridMeasuredLineProvider.m864childConstraintsJhjzzOo$foundation_release(0, iSpanOf2));
                if (arrayListEmptyList == null) {
                    arrayListEmptyList = new ArrayList();
                }
                List list5 = arrayListEmptyList;
                list5.add(lazyGridMeasuredItemMo833getAndMeasurehBUhpc2);
                arrayListEmptyList = list5;
            }
            i28 = i29 + 1;
            i12 = i;
            list3 = list;
        }
        if (arrayListEmptyList == null) {
            arrayListEmptyList = CollectionsKt.emptyList();
        }
        if (i3 > 0 || i5 < 0) {
            int size3 = arrayDeque.size();
            int i30 = 0;
            while (i30 < size3) {
                int mainAxisSizeWithSpacings5 = ((LazyGridMeasuredLine) arrayDeque.get(i30)).getMainAxisSizeWithSpacings();
                if (mainAxisSizeWithSpacings2 == 0 || mainAxisSizeWithSpacings5 > mainAxisSizeWithSpacings2 || i30 == CollectionsKt.getLastIndex(arrayDeque)) {
                    break;
                }
                mainAxisSizeWithSpacings2 -= mainAxisSizeWithSpacings5;
                i30++;
                lazyGridMeasuredLine = (LazyGridMeasuredLine) arrayDeque.get(i30);
            }
        }
        LazyGridMeasuredLine lazyGridMeasuredLine3 = lazyGridMeasuredLine;
        int i31 = mainAxisSizeWithSpacings2;
        if (z) {
            iM6850constrainWidthK40F9xA = Constraints.m6833getMaxWidthimpl(j);
        } else {
            iM6850constrainWidthK40F9xA = ConstraintsKt.m6850constrainWidthK40F9xA(j, mainAxisSizeWithSpacings3);
        }
        int i32 = iM6850constrainWidthK40F9xA;
        if (z) {
            iM6832getMaxHeightimpl = ConstraintsKt.m6849constrainHeightK40F9xA(j, mainAxisSizeWithSpacings3);
        } else {
            iM6832getMaxHeightimpl = Constraints.m6832getMaxHeightimpl(j);
        }
        int iM6849constrainHeightK40F9xA = iM6832getMaxHeightimpl;
        int i33 = mainAxisSizeWithSpacings3;
        int i34 = index;
        int i35 = index2;
        List list6 = arrayListEmptyList;
        final List<LazyGridMeasuredItem> listCalculateItemsOffsets = calculateItemsOffsets(arrayDeque, list4, list6, i32, iM6849constrainHeightK40F9xA, i33, i2, i25, z, vertical, horizontal, z2, density);
        lazyLayoutItemAnimator.onMeasured((int) f2, i32, iM6849constrainHeightK40F9xA, listCalculateItemsOffsets, lazyGridMeasuredItemProvider.getKeyIndexMap(), lazyGridMeasuredItemProvider, z, false, i8, false, i31, i33, coroutineScope, graphicsContext);
        long jM884getMinSizeToFitDisappearingItemsYbymL2g2 = lazyLayoutItemAnimator.m884getMinSizeToFitDisappearingItemsYbymL2g();
        if (IntSize.m7063equalsimpl0(jM884getMinSizeToFitDisappearingItemsYbymL2g2, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
            iM6850constrainWidthK40F9xA2 = i32;
        } else {
            int i36 = z ? iM6849constrainHeightK40F9xA : i32;
            iM6850constrainWidthK40F9xA2 = ConstraintsKt.m6850constrainWidthK40F9xA(j, Math.max(i32, IntSize.m7065getWidthimpl(jM884getMinSizeToFitDisappearingItemsYbymL2g2)));
            iM6849constrainHeightK40F9xA = ConstraintsKt.m6849constrainHeightK40F9xA(j, Math.max(iM6849constrainHeightK40F9xA, IntSize.m7064getHeightimpl(jM884getMinSizeToFitDisappearingItemsYbymL2g2)));
            int i37 = z ? iM6849constrainHeightK40F9xA : iM6850constrainWidthK40F9xA2;
            if (i37 != i36) {
                int size4 = listCalculateItemsOffsets.size();
                for (int i38 = 0; i38 < size4; i38++) {
                    listCalculateItemsOffsets.get(i38).updateMainAxisLayoutSize(i37);
                }
            }
        }
        boolean z4 = i35 != i + (-1) || i33 > i2;
        MeasureResult measureResultInvoke = function3.invoke(Integer.valueOf(iM6850constrainWidthK40F9xA2), Integer.valueOf(iM6849constrainHeightK40F9xA), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$measureLazyGrid$6
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
                List<LazyGridMeasuredItem> list7 = listCalculateItemsOffsets;
                int size5 = list7.size();
                for (int i39 = 0; i39 < size5; i39++) {
                    list7.get(i39).place(placementScope);
                }
                ObservableScopeInvalidator.m901attachToScopeimpl(mutableState);
            }
        });
        if (list4.isEmpty() && list6.isEmpty()) {
            list2 = listCalculateItemsOffsets;
        } else {
            ArrayList arrayList2 = new ArrayList(listCalculateItemsOffsets.size());
            int size5 = listCalculateItemsOffsets.size();
            int i39 = 0;
            while (i39 < size5) {
                LazyGridMeasuredItem lazyGridMeasuredItem3 = listCalculateItemsOffsets.get(i39);
                int index3 = lazyGridMeasuredItem3.getIndex();
                int i40 = i34;
                if (i40 <= index3 && index3 <= i35) {
                    arrayList2.add(lazyGridMeasuredItem3);
                }
                i39++;
                i34 = i40;
            }
            list2 = arrayList2;
        }
        return new LazyGridMeasureResult(lazyGridMeasuredLine3, i31, z4, f2, measureResultInvoke, z3, coroutineScope, density, i8, function1, list2, i14, i17, i, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5);
    }

    private static final List<LazyGridMeasuredItem> calculateItemsOffsets(List<LazyGridMeasuredLine> list, List<LazyGridMeasuredItem> list2, List<LazyGridMeasuredItem> list3, int i, int i2, int i3, int i4, int i5, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density) {
        int i6 = z ? i2 : i;
        boolean z3 = i3 < Math.min(i6, i4);
        if (z3 && i5 != 0) {
            throw new IllegalStateException("non-zero firstLineScrollOffset".toString());
        }
        int size = list.size();
        int length = 0;
        for (int i7 = 0; i7 < size; i7++) {
            length += list.get(i7).getItems().length;
        }
        ArrayList arrayList = new ArrayList(length);
        if (z3) {
            if (!list2.isEmpty() || !list3.isEmpty()) {
                throw new IllegalArgumentException("no items".toString());
            }
            int size2 = list.size();
            int[] iArr = new int[size2];
            for (int i8 = 0; i8 < size2; i8++) {
                iArr[i8] = list.get(calculateItemsOffsets$reverseAware(i8, z2, size2)).getMainAxisSize();
            }
            int[] iArr2 = new int[size2];
            for (int i9 = 0; i9 < size2; i9++) {
                iArr2[i9] = 0;
            }
            if (z) {
                if (vertical == null) {
                    throw new IllegalArgumentException("null verticalArrangement".toString());
                }
                vertical.arrange(density, i6, iArr, iArr2);
            } else {
                if (horizontal == null) {
                    throw new IllegalArgumentException("null horizontalArrangement".toString());
                }
                horizontal.arrange(density, i6, iArr, LayoutDirection.Ltr, iArr2);
            }
            IntRange indices = ArraysKt.getIndices(iArr2);
            if (z2) {
                indices = RangesKt.reversed(indices);
            }
            int first = indices.getFirst();
            int last = indices.getLast();
            int step = indices.getStep();
            if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
                while (true) {
                    int mainAxisSize = iArr2[first];
                    LazyGridMeasuredLine lazyGridMeasuredLine = list.get(calculateItemsOffsets$reverseAware(first, z2, size2));
                    if (z2) {
                        mainAxisSize = (i6 - mainAxisSize) - lazyGridMeasuredLine.getMainAxisSize();
                    }
                    addAllFromArray(arrayList, lazyGridMeasuredLine.position(mainAxisSize, i, i2));
                    if (first == last) {
                        break;
                    }
                    first += step;
                }
            }
        } else {
            int size3 = list2.size() - 1;
            if (size3 >= 0) {
                int mainAxisSizeWithSpacings = i5;
                while (true) {
                    int i10 = size3 - 1;
                    LazyGridMeasuredItem lazyGridMeasuredItem = list2.get(size3);
                    mainAxisSizeWithSpacings -= lazyGridMeasuredItem.getMainAxisSizeWithSpacings();
                    lazyGridMeasuredItem.position(mainAxisSizeWithSpacings, 0, i, i2);
                    arrayList.add(lazyGridMeasuredItem);
                    if (i10 < 0) {
                        break;
                    }
                    size3 = i10;
                }
            }
            int size4 = list.size();
            int mainAxisSizeWithSpacings2 = i5;
            for (int i11 = 0; i11 < size4; i11++) {
                LazyGridMeasuredLine lazyGridMeasuredLine2 = list.get(i11);
                addAllFromArray(arrayList, lazyGridMeasuredLine2.position(mainAxisSizeWithSpacings2, i, i2));
                mainAxisSizeWithSpacings2 += lazyGridMeasuredLine2.getMainAxisSizeWithSpacings();
            }
            int size5 = list3.size();
            for (int i12 = 0; i12 < size5; i12++) {
                LazyGridMeasuredItem lazyGridMeasuredItem2 = list3.get(i12);
                lazyGridMeasuredItem2.position(mainAxisSizeWithSpacings2, 0, i, i2);
                arrayList.add(lazyGridMeasuredItem2);
                mainAxisSizeWithSpacings2 += lazyGridMeasuredItem2.getMainAxisSizeWithSpacings();
            }
        }
        return arrayList;
    }

    private static final <T> void addAllFromArray(List<T> list, T[] tArr) {
        for (T t : tArr) {
            list.add(t);
        }
    }

    private static final List<LazyGridMeasuredItem> calculateExtraItems(List<Integer> list, LazyGridMeasuredItemProvider lazyGridMeasuredItemProvider, LazyGridMeasuredLineProvider lazyGridMeasuredLineProvider, Function1<? super Integer, Boolean> function1) {
        LazyGridMeasuredItemProvider lazyGridMeasuredItemProvider2;
        int size = list.size();
        ArrayList arrayList = null;
        int i = 0;
        while (i < size) {
            int iIntValue = list.get(i).intValue();
            if (function1.invoke(Integer.valueOf(iIntValue)).booleanValue()) {
                int iSpanOf = lazyGridMeasuredLineProvider.spanOf(iIntValue);
                lazyGridMeasuredItemProvider2 = lazyGridMeasuredItemProvider;
                LazyGridMeasuredItem lazyGridMeasuredItemMo833getAndMeasurehBUhpc = lazyGridMeasuredItemProvider2.mo833getAndMeasurehBUhpc(iIntValue, 0, iSpanOf, lazyGridMeasuredLineProvider.m864childConstraintsJhjzzOo$foundation_release(0, iSpanOf));
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(lazyGridMeasuredItemMo833getAndMeasurehBUhpc);
            } else {
                lazyGridMeasuredItemProvider2 = lazyGridMeasuredItemProvider;
            }
            i++;
            lazyGridMeasuredItemProvider = lazyGridMeasuredItemProvider2;
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }
}
