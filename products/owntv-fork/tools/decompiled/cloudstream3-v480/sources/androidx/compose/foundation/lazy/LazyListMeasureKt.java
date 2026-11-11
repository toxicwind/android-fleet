package androidx.compose.foundation.lazy;

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
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyListMeasure.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u008c\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002\u001a\\\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u000e2\b\u0010 \u001a\u0004\u0018\u00010!H\u0002\u001a4\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010#\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u0004H\u0002\u001a£\u0002\u0010$\u001a\u00020%2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00020\b2\u0006\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\b2\u0006\u0010+\u001a\u00020\b2\u0006\u0010,\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020.2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u0002012\u0006\u0010\u001b\u001a\u00020\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\u0006\u00102\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000e2\b\u00103\u001a\u0004\u0018\u00010!2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u0002092/\u0010:\u001a+\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u00020>0<¢\u0006\u0002\b?\u0012\u0004\u0012\u00020@0;H\u0000ø\u0001\u0000¢\u0006\u0004\bA\u0010B\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006C"}, d2 = {"calculateItemsOffsets", "", "Landroidx/compose/foundation/lazy/LazyListMeasuredItem;", "items", "", "extraItemsBefore", "extraItemsAfter", "layoutWidth", "", "layoutHeight", "finalMainAxisOffset", "maxOffset", "itemsScrollOffset", "isVertical", "", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "createItemsAfterList", "visibleItems", "measuredItemProvider", "Landroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;", "itemsCount", "beyondBoundsItemCount", "pinnedItems", "consumedScroll", "", "isLookingAhead", "lastPostLookaheadLayoutInfo", "Landroidx/compose/foundation/lazy/LazyListLayoutInfo;", "createItemsBeforeList", "currentFirstItemIndex", "measureLazyList", "Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenItems", "firstVisibleItemIndex", "firstVisibleItemScrollOffset", "scrollToBeConsumed", "constraints", "Landroidx/compose/ui/unit/Constraints;", "headerIndexes", "itemAnimator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "hasLookaheadPassOccurred", "postLookaheadLayoutInfo", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "placementScopeInvalidator", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", TtmlNode.TAG_LAYOUT, "Lkotlin/Function3;", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "measureLazyList-x0Ok8Vo", "(ILandroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;IIIIIIFJZLjava/util/List;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;ILjava/util/List;ZZLandroidx/compose/foundation/lazy/LazyListLayoutInfo;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/graphics/GraphicsContext;Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyListMeasureKt {
    private static final int calculateItemsOffsets$reverseAware(int i, boolean z, int i2) {
        return !z ? i : (i2 - i) - 1;
    }

    /* JADX WARN: Code duplicated, block: B:133:0x0392  */
    /* JADX INFO: renamed from: measureLazyList-x0Ok8Vo, reason: not valid java name */
    public static final LazyListMeasureResult m826measureLazyListx0Ok8Vo(int i, LazyListMeasuredItemProvider lazyListMeasuredItemProvider, int i2, int i3, int i4, int i5, int i6, int i7, float f, long j, boolean z, List<Integer> list, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density, LazyLayoutItemAnimator<LazyListMeasuredItem> lazyLayoutItemAnimator, int i8, List<Integer> list2, boolean z3, final boolean z4, LazyListLayoutInfo lazyListLayoutInfo, CoroutineScope coroutineScope, final MutableState<Unit> mutableState, GraphicsContext graphicsContext, Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> function3) {
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        LazyListMeasuredItemProvider lazyListMeasuredItemProvider2;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        LazyListMeasuredItem lazyListMeasuredItem;
        int i19;
        int i20;
        int i21 = i3;
        if (i21 < 0) {
            throw new IllegalArgumentException("invalid beforeContentPadding".toString());
        }
        if (i4 < 0) {
            throw new IllegalArgumentException("invalid afterContentPadding".toString());
        }
        if (i <= 0) {
            int iM6835getMinWidthimpl = Constraints.m6835getMinWidthimpl(j);
            int iM6834getMinHeightimpl = Constraints.m6834getMinHeightimpl(j);
            lazyLayoutItemAnimator.onMeasured(0, iM6835getMinWidthimpl, iM6834getMinHeightimpl, new ArrayList(), lazyListMeasuredItemProvider.getKeyIndexMap(), lazyListMeasuredItemProvider, z, z4, 1, z3, 0, 0, coroutineScope, graphicsContext);
            if (!z4) {
                long jM884getMinSizeToFitDisappearingItemsYbymL2g = lazyLayoutItemAnimator.m884getMinSizeToFitDisappearingItemsYbymL2g();
                if (!IntSize.m7063equalsimpl0(jM884getMinSizeToFitDisappearingItemsYbymL2g, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                    iM6835getMinWidthimpl = ConstraintsKt.m6850constrainWidthK40F9xA(j, IntSize.m7065getWidthimpl(jM884getMinSizeToFitDisappearingItemsYbymL2g));
                    iM6834getMinHeightimpl = ConstraintsKt.m6849constrainHeightK40F9xA(j, IntSize.m7064getHeightimpl(jM884getMinSizeToFitDisappearingItemsYbymL2g));
                }
            }
            return new LazyListMeasureResult(null, 0, false, 0.0f, function3.invoke(Integer.valueOf(iM6835getMinWidthimpl), Integer.valueOf(iM6834getMinHeightimpl), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListMeasureKt$measureLazyList$3
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope placementScope) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }
            }), 0.0f, false, coroutineScope, density, lazyListMeasuredItemProvider.getChildConstraints(), CollectionsKt.emptyList(), -i21, i2 + i4, 0, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5, null);
        }
        int i22 = i6;
        if (i22 >= i) {
            i22 = i - 1;
            i9 = 0;
        } else {
            i9 = i7;
        }
        int iRound = Math.round(f);
        int i23 = i9 - iRound;
        if (i22 == 0 && i23 < 0) {
            iRound += i23;
            i23 = 0;
        }
        int i24 = iRound;
        ArrayDeque arrayDeque = new ArrayDeque();
        int i25 = -i21;
        int i26 = (i5 < 0 ? i5 : 0) + i25;
        int mainAxisSizeWithSpacings = i23 + i26;
        int iMax = 0;
        while (mainAxisSizeWithSpacings < 0 && i22 > 0) {
            int i27 = i22 - 1;
            int i28 = i26;
            LazyListMeasuredItem lazyListMeasuredItemM832getAndMeasure0kLqBqw$default = LazyListMeasuredItemProvider.m832getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, i27, 0L, 2, null);
            arrayDeque.add(0, lazyListMeasuredItemM832getAndMeasure0kLqBqw$default);
            iMax = Math.max(iMax, lazyListMeasuredItemM832getAndMeasure0kLqBqw$default.getCrossAxisSize());
            mainAxisSizeWithSpacings = lazyListMeasuredItemM832getAndMeasure0kLqBqw$default.getMainAxisSizeWithSpacings() + mainAxisSizeWithSpacings;
            i22 = i27;
            i26 = i28;
            i25 = i25;
            i24 = i24;
        }
        int i29 = mainAxisSizeWithSpacings;
        int i30 = i24;
        int i31 = i25;
        int i32 = i26;
        int i33 = iMax;
        if (i29 < i32) {
            i11 = i30 + i29;
            i10 = i32;
        } else {
            i10 = i29;
            i11 = i30;
        }
        int i34 = i10 - i32;
        int i35 = i2 + i4;
        int iCoerceAtLeast = RangesKt.coerceAtLeast(i35, 0);
        int mainAxisSizeWithSpacings2 = -i34;
        int i36 = i22;
        int i37 = 0;
        boolean z5 = false;
        while (i37 < arrayDeque.size()) {
            if (mainAxisSizeWithSpacings2 >= iCoerceAtLeast) {
                arrayDeque.remove(i37);
                z5 = true;
            } else {
                i36++;
                mainAxisSizeWithSpacings2 += ((LazyListMeasuredItem) arrayDeque.get(i37)).getMainAxisSizeWithSpacings();
                i37++;
            }
        }
        int i38 = i22;
        int i39 = i33;
        int mainAxisSizeWithSpacings3 = i34;
        int i40 = mainAxisSizeWithSpacings2;
        boolean z6 = z5;
        int i41 = i35;
        int i42 = i36;
        while (i42 < i && (i40 < iCoerceAtLeast || i40 <= 0 || arrayDeque.isEmpty())) {
            int i43 = i41;
            int i44 = i40;
            int i45 = iCoerceAtLeast;
            int i46 = i38;
            int i47 = mainAxisSizeWithSpacings3;
            int i48 = i39;
            LazyListMeasuredItem lazyListMeasuredItemM832getAndMeasure0kLqBqw$default2 = LazyListMeasuredItemProvider.m832getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, i42, 0L, 2, null);
            int i49 = i42;
            int mainAxisSizeWithSpacings4 = lazyListMeasuredItemM832getAndMeasure0kLqBqw$default2.getMainAxisSizeWithSpacings() + i44;
            if (mainAxisSizeWithSpacings4 <= i32 && i49 != i - 1) {
                i20 = i49 + 1;
                mainAxisSizeWithSpacings3 = i47 - lazyListMeasuredItemM832getAndMeasure0kLqBqw$default2.getMainAxisSizeWithSpacings();
                z6 = true;
            } else {
                int iMax2 = Math.max(i48, lazyListMeasuredItemM832getAndMeasure0kLqBqw$default2.getCrossAxisSize());
                arrayDeque.add(lazyListMeasuredItemM832getAndMeasure0kLqBqw$default2);
                mainAxisSizeWithSpacings3 = i47;
                i48 = iMax2;
                i20 = i46;
            }
            i40 = mainAxisSizeWithSpacings4;
            i41 = i43;
            i42 = i49 + 1;
            i39 = i48;
            i38 = i20;
            iCoerceAtLeast = i45;
        }
        int i50 = i41;
        int i51 = i38;
        int i52 = mainAxisSizeWithSpacings3;
        int iMax3 = i39;
        int i53 = i42;
        int i54 = i40;
        if (i54 < i2) {
            int i55 = i2 - i54;
            int i56 = i54 + i55;
            int mainAxisSizeWithSpacings5 = i52 - i55;
            i14 = i51;
            while (mainAxisSizeWithSpacings5 < i21 && i14 > 0) {
                i14--;
                int i57 = mainAxisSizeWithSpacings5;
                LazyListMeasuredItem lazyListMeasuredItemM832getAndMeasure0kLqBqw$default3 = LazyListMeasuredItemProvider.m832getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, i14, 0L, 2, null);
                arrayDeque.add(0, lazyListMeasuredItemM832getAndMeasure0kLqBqw$default3);
                iMax3 = Math.max(iMax3, lazyListMeasuredItemM832getAndMeasure0kLqBqw$default3.getCrossAxisSize());
                mainAxisSizeWithSpacings5 = i57 + lazyListMeasuredItemM832getAndMeasure0kLqBqw$default3.getMainAxisSizeWithSpacings();
                i53 = i53;
                i21 = i3;
            }
            i12 = i53;
            int i58 = mainAxisSizeWithSpacings5;
            i13 = 0;
            lazyListMeasuredItemProvider2 = lazyListMeasuredItemProvider;
            i15 = i55 + i11;
            if (i58 < 0) {
                i15 += i58;
                i16 = i56 + i58;
                i17 = 0;
            } else {
                i16 = i56;
                i17 = i58;
            }
        } else {
            i12 = i53;
            i13 = 0;
            lazyListMeasuredItemProvider2 = lazyListMeasuredItemProvider;
            i14 = i51;
            i15 = i11;
            i16 = i54;
            i17 = i52;
        }
        float f2 = (MathKt.getSign(Math.round(f)) != MathKt.getSign(i15) || Math.abs(Math.round(f)) < Math.abs(i15)) ? f : i15;
        float f3 = f - f2;
        float f4 = 0.0f;
        if (z4 && i15 > i11 && f3 <= 0.0f) {
            f4 = (i15 - i11) + f3;
        }
        float f5 = f4;
        if (i17 < 0) {
            throw new IllegalArgumentException("negative currentFirstItemScrollOffset".toString());
        }
        int i59 = -i17;
        LazyListMeasuredItem lazyListMeasuredItem2 = (LazyListMeasuredItem) arrayDeque.first();
        if (i3 > 0 || i5 < 0) {
            int size = arrayDeque.size();
            int i60 = i13;
            while (true) {
                if (i60 < size) {
                    int mainAxisSizeWithSpacings6 = ((LazyListMeasuredItem) arrayDeque.get(i60)).getMainAxisSizeWithSpacings();
                    if (i17 != 0 && mainAxisSizeWithSpacings6 <= i17) {
                        if (i60 == CollectionsKt.getLastIndex(arrayDeque)) {
                            break;
                        }
                        i17 -= mainAxisSizeWithSpacings6;
                        i60++;
                        lazyListMeasuredItem2 = (LazyListMeasuredItem) arrayDeque.get(i60);
                    }
                }
                break;
            }
            i18 = i17;
            lazyListMeasuredItem = lazyListMeasuredItem2;
        } else {
            i18 = i17;
            lazyListMeasuredItem = lazyListMeasuredItem2;
        }
        List<LazyListMeasuredItem> listCreateItemsBeforeList = createItemsBeforeList(i14, lazyListMeasuredItemProvider2, i8, list2);
        int size2 = listCreateItemsBeforeList.size();
        int iMax4 = iMax3;
        for (int i61 = 0; i61 < size2; i61++) {
            iMax4 = Math.max(iMax4, listCreateItemsBeforeList.get(i61).getCrossAxisSize());
        }
        ArrayDeque arrayDeque2 = arrayDeque;
        List<LazyListMeasuredItem> listCreateItemsAfterList = createItemsAfterList(arrayDeque2, lazyListMeasuredItemProvider, i, i8, list2, f2, z4, lazyListLayoutInfo);
        int size3 = listCreateItemsAfterList.size();
        int iMax5 = iMax4;
        for (int i62 = 0; i62 < size3; i62++) {
            iMax5 = Math.max(iMax5, listCreateItemsAfterList.get(i62).getCrossAxisSize());
        }
        boolean z7 = Intrinsics.areEqual(lazyListMeasuredItem, arrayDeque.first()) && listCreateItemsBeforeList.isEmpty() && listCreateItemsAfterList.isEmpty();
        int iM6850constrainWidthK40F9xA = ConstraintsKt.m6850constrainWidthK40F9xA(j, z ? iMax5 : i16);
        if (z) {
            iMax5 = i16;
        }
        int iM6849constrainHeightK40F9xA = ConstraintsKt.m6849constrainHeightK40F9xA(j, iMax5);
        float f6 = f2;
        final ArrayList arrayListCalculateItemsOffsets = calculateItemsOffsets(arrayDeque2, listCreateItemsBeforeList, listCreateItemsAfterList, iM6850constrainWidthK40F9xA, iM6849constrainHeightK40F9xA, i16, i2, i59, z, vertical, horizontal, z2, density);
        LazyListMeasuredItem lazyListMeasuredItem3 = lazyListMeasuredItem;
        lazyLayoutItemAnimator.onMeasured((int) f6, iM6850constrainWidthK40F9xA, iM6849constrainHeightK40F9xA, arrayListCalculateItemsOffsets, lazyListMeasuredItemProvider.getKeyIndexMap(), lazyListMeasuredItemProvider, z, z4, 1, z3, i18, i16, coroutineScope, graphicsContext);
        int iM6850constrainWidthK40F9xA2 = iM6850constrainWidthK40F9xA;
        int i63 = i16;
        if (z4) {
            i19 = iM6849constrainHeightK40F9xA;
        } else {
            long jM884getMinSizeToFitDisappearingItemsYbymL2g2 = lazyLayoutItemAnimator.m884getMinSizeToFitDisappearingItemsYbymL2g();
            if (IntSize.m7063equalsimpl0(jM884getMinSizeToFitDisappearingItemsYbymL2g2, IntSize.INSTANCE.m7070getZeroYbymL2g())) {
                i19 = iM6849constrainHeightK40F9xA;
            } else {
                int i64 = z ? iM6849constrainHeightK40F9xA : iM6850constrainWidthK40F9xA2;
                iM6850constrainWidthK40F9xA2 = ConstraintsKt.m6850constrainWidthK40F9xA(j, Math.max(iM6850constrainWidthK40F9xA2, IntSize.m7065getWidthimpl(jM884getMinSizeToFitDisappearingItemsYbymL2g2)));
                int iM6849constrainHeightK40F9xA2 = ConstraintsKt.m6849constrainHeightK40F9xA(j, Math.max(iM6849constrainHeightK40F9xA, IntSize.m7064getHeightimpl(jM884getMinSizeToFitDisappearingItemsYbymL2g2)));
                int i65 = z ? iM6849constrainHeightK40F9xA2 : iM6850constrainWidthK40F9xA2;
                if (i65 != i64) {
                    int size4 = arrayListCalculateItemsOffsets.size();
                    for (int i66 = 0; i66 < size4; i66++) {
                        arrayListCalculateItemsOffsets.get(i66).updateMainAxisLayoutSize(i65);
                    }
                }
                i19 = iM6849constrainHeightK40F9xA2;
            }
        }
        int i67 = iM6850constrainWidthK40F9xA2;
        final LazyListMeasuredItem lazyListMeasuredItemFindOrComposeLazyListHeader = !list.isEmpty() ? LazyListHeadersKt.findOrComposeLazyListHeader(arrayListCalculateItemsOffsets, lazyListMeasuredItemProvider, list, i3, i67, i19) : null;
        boolean z8 = i12 < i || i63 > i2;
        MeasureResult measureResultInvoke = function3.invoke(Integer.valueOf(i67), Integer.valueOf(i19), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListMeasureKt$measureLazyList$8
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
                List<LazyListMeasuredItem> list3 = arrayListCalculateItemsOffsets;
                LazyListMeasuredItem lazyListMeasuredItem4 = lazyListMeasuredItemFindOrComposeLazyListHeader;
                boolean z9 = z4;
                int size5 = list3.size();
                for (int i68 = 0; i68 < size5; i68++) {
                    LazyListMeasuredItem lazyListMeasuredItem5 = list3.get(i68);
                    if (lazyListMeasuredItem5 != lazyListMeasuredItem4) {
                        lazyListMeasuredItem5.place(placementScope, z9);
                    }
                }
                LazyListMeasuredItem lazyListMeasuredItem6 = lazyListMeasuredItemFindOrComposeLazyListHeader;
                if (lazyListMeasuredItem6 != null) {
                    lazyListMeasuredItem6.place(placementScope, z4);
                }
                ObservableScopeInvalidator.m901attachToScopeimpl(mutableState);
            }
        });
        if (!z7) {
            ArrayList arrayList = new ArrayList(arrayListCalculateItemsOffsets.size());
            int size5 = arrayListCalculateItemsOffsets.size();
            for (int i68 = 0; i68 < size5; i68++) {
                LazyListMeasuredItem lazyListMeasuredItem4 = arrayListCalculateItemsOffsets.get(i68);
                LazyListMeasuredItem lazyListMeasuredItem5 = lazyListMeasuredItem4;
                if ((lazyListMeasuredItem5.getIndex() >= ((LazyListMeasuredItem) arrayDeque.first()).getIndex() && lazyListMeasuredItem5.getIndex() <= ((LazyListMeasuredItem) arrayDeque.last()).getIndex()) || lazyListMeasuredItem5 == lazyListMeasuredItemFindOrComposeLazyListHeader) {
                    arrayList.add(lazyListMeasuredItem4);
                }
            }
            arrayListCalculateItemsOffsets = arrayList;
        }
        return new LazyListMeasureResult(lazyListMeasuredItem3, i18, z8, f6, measureResultInvoke, f5, z6, coroutineScope, density, lazyListMeasuredItemProvider.getChildConstraints(), arrayListCalculateItemsOffsets, i31, i50, i, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5, null);
    }

    private static final List<LazyListMeasuredItem> createItemsAfterList(List<LazyListMeasuredItem> list, LazyListMeasuredItemProvider lazyListMeasuredItemProvider, int i, int i2, List<Integer> list2, float f, boolean z, LazyListLayoutInfo lazyListLayoutInfo) {
        ArrayList arrayList;
        LazyListItemInfo lazyListItemInfo;
        LazyListMeasuredItem lazyListMeasuredItem;
        LazyListMeasuredItem lazyListMeasuredItem2;
        int mainAxisSizeWithSpacings;
        LazyListMeasuredItem lazyListMeasuredItem3;
        int index;
        int iMin;
        LazyListMeasuredItem lazyListMeasuredItem4;
        LazyListMeasuredItem lazyListMeasuredItem5;
        int i3 = i - 1;
        int iMin2 = Math.min(((LazyListMeasuredItem) CollectionsKt.last((List) list)).getIndex() + i2, i3);
        int index2 = ((LazyListMeasuredItem) CollectionsKt.last((List) list)).getIndex() + 1;
        if (index2 <= iMin2) {
            int i4 = index2;
            arrayList = null;
            while (true) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(LazyListMeasuredItemProvider.m832getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, i4, 0L, 2, null));
                if (i4 == iMin2) {
                    break;
                }
                i4++;
            }
        } else {
            arrayList = null;
        }
        if (z && lazyListLayoutInfo != null && !lazyListLayoutInfo.getVisibleItemsInfo().isEmpty()) {
            List<LazyListItemInfo> visibleItemsInfo = lazyListLayoutInfo.getVisibleItemsInfo();
            int size = visibleItemsInfo.size();
            while (true) {
                size--;
                if (-1 >= size) {
                    lazyListItemInfo = null;
                    break;
                }
                if (visibleItemsInfo.get(size).getIndex() > iMin2 && (size == 0 || visibleItemsInfo.get(size - 1).getIndex() <= iMin2)) {
                    lazyListItemInfo = visibleItemsInfo.get(size);
                    break;
                }
            }
            LazyListItemInfo lazyListItemInfo2 = (LazyListItemInfo) CollectionsKt.last((List) lazyListLayoutInfo.getVisibleItemsInfo());
            if (lazyListItemInfo != null && (index = lazyListItemInfo.getIndex()) <= (iMin = Math.min(lazyListItemInfo2.getIndex(), i3))) {
                int i5 = index;
                while (true) {
                    if (arrayList != null) {
                        int size2 = arrayList.size();
                        int i6 = 0;
                        while (true) {
                            if (i6 >= size2) {
                                lazyListMeasuredItem5 = null;
                                break;
                            }
                            lazyListMeasuredItem5 = arrayList.get(i6);
                            if (lazyListMeasuredItem5.getIndex() == i5) {
                                break;
                            }
                            i6++;
                        }
                        lazyListMeasuredItem4 = lazyListMeasuredItem5;
                    } else {
                        lazyListMeasuredItem4 = null;
                    }
                    if (lazyListMeasuredItem4 == null) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(LazyListMeasuredItemProvider.m832getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, i5, 0L, 2, null));
                    }
                    if (i5 == iMin) {
                        break;
                    }
                    i5++;
                }
            }
            float viewportEndOffset = ((lazyListLayoutInfo.getViewportEndOffset() - lazyListItemInfo2.getOffset()) - lazyListItemInfo2.getSize()) - f;
            if (viewportEndOffset > 0.0f) {
                int index3 = lazyListItemInfo2.getIndex() + 1;
                int i7 = 0;
                while (index3 < i && i7 < viewportEndOffset) {
                    if (index3 <= iMin2) {
                        int size3 = list.size();
                        int i8 = 0;
                        while (true) {
                            if (i8 >= size3) {
                                lazyListMeasuredItem3 = null;
                                break;
                            }
                            lazyListMeasuredItem3 = list.get(i8);
                            if (lazyListMeasuredItem3.getIndex() == index3) {
                                break;
                            }
                            i8++;
                        }
                        lazyListMeasuredItem = lazyListMeasuredItem3;
                    } else if (arrayList != null) {
                        int size4 = arrayList.size();
                        int i9 = 0;
                        while (true) {
                            if (i9 >= size4) {
                                lazyListMeasuredItem2 = null;
                                break;
                            }
                            lazyListMeasuredItem2 = arrayList.get(i9);
                            if (lazyListMeasuredItem2.getIndex() == index3) {
                                break;
                            }
                            i9++;
                        }
                        lazyListMeasuredItem = lazyListMeasuredItem2;
                    } else {
                        lazyListMeasuredItem = null;
                    }
                    if (lazyListMeasuredItem != null) {
                        index3++;
                        mainAxisSizeWithSpacings = lazyListMeasuredItem.getMainAxisSizeWithSpacings();
                    } else {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(LazyListMeasuredItemProvider.m832getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, index3, 0L, 2, null));
                        index3++;
                        mainAxisSizeWithSpacings = ((LazyListMeasuredItem) CollectionsKt.last((List) arrayList)).getMainAxisSizeWithSpacings();
                    }
                    i7 += mainAxisSizeWithSpacings;
                }
            }
        }
        if (arrayList != null && ((LazyListMeasuredItem) CollectionsKt.last((List) arrayList)).getIndex() > iMin2) {
            iMin2 = ((LazyListMeasuredItem) CollectionsKt.last((List) arrayList)).getIndex();
        }
        int size5 = list2.size();
        for (int i10 = 0; i10 < size5; i10++) {
            int iIntValue = list2.get(i10).intValue();
            if (iIntValue > iMin2) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(LazyListMeasuredItemProvider.m832getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, iIntValue, 0L, 2, null));
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<LazyListMeasuredItem> createItemsBeforeList(int i, LazyListMeasuredItemProvider lazyListMeasuredItemProvider, int i2, List<Integer> list) {
        LazyListMeasuredItemProvider lazyListMeasuredItemProvider2;
        int iMax = Math.max(0, i - i2);
        int i3 = i - 1;
        ArrayList arrayList = null;
        if (iMax <= i3) {
            int i4 = i3;
            while (true) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                lazyListMeasuredItemProvider2 = lazyListMeasuredItemProvider;
                arrayList.add(LazyListMeasuredItemProvider.m832getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider2, i4, 0L, 2, null));
                if (i4 == iMax) {
                    break;
                }
                i4--;
                lazyListMeasuredItemProvider = lazyListMeasuredItemProvider2;
            }
        } else {
            lazyListMeasuredItemProvider2 = lazyListMeasuredItemProvider;
        }
        int size = list.size() - 1;
        if (size >= 0) {
            while (true) {
                int i5 = size - 1;
                int iIntValue = list.get(size).intValue();
                if (iIntValue < iMax) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(LazyListMeasuredItemProvider.m832getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider2, iIntValue, 0L, 2, null));
                }
                if (i5 < 0) {
                    break;
                }
                size = i5;
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<LazyListMeasuredItem> calculateItemsOffsets(List<LazyListMeasuredItem> list, List<LazyListMeasuredItem> list2, List<LazyListMeasuredItem> list3, int i, int i2, int i3, int i4, int i5, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density) {
        int i6 = z ? i2 : i;
        boolean z3 = i3 < Math.min(i6, i4);
        if (z3 && i5 != 0) {
            throw new IllegalStateException("non-zero itemsScrollOffset".toString());
        }
        ArrayList arrayList = new ArrayList(list.size() + list2.size() + list3.size());
        if (z3) {
            if (!list2.isEmpty() || !list3.isEmpty()) {
                throw new IllegalArgumentException("no extra items".toString());
            }
            int size = list.size();
            int[] iArr = new int[size];
            for (int i7 = 0; i7 < size; i7++) {
                iArr[i7] = list.get(calculateItemsOffsets$reverseAware(i7, z2, size)).getSize();
            }
            int[] iArr2 = new int[size];
            for (int i8 = 0; i8 < size; i8++) {
                iArr2[i8] = 0;
            }
            if (z) {
                if (vertical == null) {
                    throw new IllegalArgumentException("null verticalArrangement when isVertical == true".toString());
                }
                vertical.arrange(density, i6, iArr, iArr2);
            } else {
                if (horizontal == null) {
                    throw new IllegalArgumentException("null horizontalArrangement when isVertical == false".toString());
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
                    int size2 = iArr2[first];
                    LazyListMeasuredItem lazyListMeasuredItem = list.get(calculateItemsOffsets$reverseAware(first, z2, size));
                    if (z2) {
                        size2 = (i6 - size2) - lazyListMeasuredItem.getSize();
                    }
                    lazyListMeasuredItem.position(size2, i, i2);
                    arrayList.add(lazyListMeasuredItem);
                    if (first == last) {
                        break;
                    }
                    first += step;
                }
            }
        } else {
            int size3 = list2.size();
            int mainAxisSizeWithSpacings = i5;
            for (int i9 = 0; i9 < size3; i9++) {
                LazyListMeasuredItem lazyListMeasuredItem2 = list2.get(i9);
                mainAxisSizeWithSpacings -= lazyListMeasuredItem2.getMainAxisSizeWithSpacings();
                lazyListMeasuredItem2.position(mainAxisSizeWithSpacings, i, i2);
                arrayList.add(lazyListMeasuredItem2);
            }
            int size4 = list.size();
            int mainAxisSizeWithSpacings2 = i5;
            for (int i10 = 0; i10 < size4; i10++) {
                LazyListMeasuredItem lazyListMeasuredItem3 = list.get(i10);
                lazyListMeasuredItem3.position(mainAxisSizeWithSpacings2, i, i2);
                arrayList.add(lazyListMeasuredItem3);
                mainAxisSizeWithSpacings2 += lazyListMeasuredItem3.getMainAxisSizeWithSpacings();
            }
            int size5 = list3.size();
            for (int i11 = 0; i11 < size5; i11++) {
                LazyListMeasuredItem lazyListMeasuredItem4 = list3.get(i11);
                lazyListMeasuredItem4.position(mainAxisSizeWithSpacings2, i, i2);
                arrayList.add(lazyListMeasuredItem4);
                mainAxisSizeWithSpacings2 += lazyListMeasuredItem4.getMainAxisSizeWithSpacings();
            }
        }
        return arrayList;
    }
}
