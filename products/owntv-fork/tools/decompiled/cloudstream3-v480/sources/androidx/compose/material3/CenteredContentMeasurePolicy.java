package androidx.compose.material3;

import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ExpressiveNavigationBar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\r"}, d2 = {"Landroidx/compose/material3/CenteredContentMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "()V", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class CenteredContentMeasurePolicy implements MeasurePolicy {
    @Override // androidx.compose.ui.layout.MeasurePolicy
    public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo72measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
        final ArrayList arrayList;
        int iCoerceAtMost;
        int iM6833getMaxWidthimpl = Constraints.m6833getMaxWidthimpl(j);
        int iM6834getMinHeightimpl = Constraints.m6834getMinHeightimpl(j);
        int size = list.size();
        if (size < 1) {
            return MeasureScope.CC.layout$default(measureScope, iM6833getMaxWidthimpl, iM6834getMinHeightimpl, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.CenteredContentMeasurePolicy$measure$1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope placementScope) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }
            }, 4, null);
        }
        final Ref.IntRef intRef = new Ref.IntRef();
        int i = 0;
        if (Constraints.m6829getHasBoundedWidthimpl(j)) {
            int i2 = iM6833getMaxWidthimpl / size;
            intRef.element = ExpressiveNavigationBarKt.calculateCenteredContentHorizontalPadding(size, iM6833getMaxWidthimpl);
            int i3 = (iM6833getMaxWidthimpl - (intRef.element * 2)) / size;
            int size2 = list.size();
            for (int i4 = 0; i4 < size2; i4++) {
                int iMaxIntrinsicHeight = list.get(i4).maxIntrinsicHeight(i3);
                if (iM6834getMinHeightimpl < iMaxIntrinsicHeight) {
                    iM6834getMinHeightimpl = RangesKt.coerceAtMost(iMaxIntrinsicHeight, Constraints.m6832getMaxHeightimpl(j));
                }
            }
            ArrayList arrayList2 = new ArrayList(list.size());
            int size3 = list.size();
            while (i < size3) {
                ArrayList arrayList3 = arrayList2;
                Measurable measurable = list.get(i);
                int iMaxIntrinsicWidth = measurable.maxIntrinsicWidth(Constraints.m6834getMinHeightimpl(j));
                if (i3 < iMaxIntrinsicWidth) {
                    iCoerceAtMost = RangesKt.coerceAtMost(iMaxIntrinsicWidth, i2);
                    intRef.element -= (iCoerceAtMost - i3) / 2;
                } else {
                    iCoerceAtMost = i3;
                }
                arrayList3.add(measurable.mo5581measureBRTryo0(ConstraintsKt.m6848constrainN9IONVI(j, Constraints.INSTANCE.m6843fixedJhjzzOo(iCoerceAtMost, iM6834getMinHeightimpl))));
                i++;
            }
            arrayList = arrayList2;
        } else {
            ArrayList arrayList4 = new ArrayList(list.size());
            int size4 = list.size();
            while (i < size4) {
                arrayList4.add(list.get(i).mo5581measureBRTryo0(ConstraintsKt.m6848constrainN9IONVI(j, Constraints.INSTANCE.m6844fixedHeightOenEA2s(iM6834getMinHeightimpl))));
                i++;
            }
            arrayList = arrayList4;
        }
        return MeasureScope.CC.layout$default(measureScope, iM6833getMaxWidthimpl, iM6834getMinHeightimpl, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.CenteredContentMeasurePolicy$measure$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
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
                int i5 = intRef.element;
                List<Placeable> list2 = arrayList;
                int size5 = list2.size();
                int i6 = 0;
                int width = i5;
                while (i6 < size5) {
                    Placeable placeable = list2.get(i6);
                    Placeable.PlacementScope placementScope2 = placementScope;
                    Placeable.PlacementScope.placeRelative$default(placementScope2, placeable, width, 0, 0.0f, 4, null);
                    width += placeable.getWidth();
                    i6++;
                    placementScope = placementScope2;
                }
            }
        }, 4, null);
    }
}
