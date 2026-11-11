package androidx.compose.ui.layout;

import androidx.compose.ui.unit.ConstraintsKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: MultiContentMeasurePolicy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bç\u0080\u0001\u0018\u00002\u00020\u0001J/\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00062\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0004\b\n\u0010\u000bJ(\u0010\f\u001a\u00020\r*\u00020\u000e2\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00060\u00062\u0006\u0010\u0010\u001a\u00020\rH\u0016J(\u0010\u0011\u001a\u00020\r*\u00020\u000e2\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00060\u00062\u0006\u0010\u0012\u001a\u00020\rH\u0016J(\u0010\u0013\u001a\u00020\r*\u00020\u000e2\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00060\u00062\u0006\u0010\u0010\u001a\u00020\rH\u0016J(\u0010\u0014\u001a\u00020\r*\u00020\u000e2\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00060\u00062\u0006\u0010\u0012\u001a\u00020\rH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0015À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicWidth", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "minIntrinsicHeight", "width", "maxIntrinsicWidth", "maxIntrinsicHeight", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface MultiContentMeasurePolicy {
    int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i);

    int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i);

    /* JADX INFO: renamed from: measure-3p2s80s */
    MeasureResult mo668measure3p2s80s(MeasureScope measureScope, List<? extends List<? extends Measurable>> list, long j);

    int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i);

    int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i);

    /* JADX INFO: renamed from: androidx.compose.ui.layout.MultiContentMeasurePolicy$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: MultiContentMeasurePolicy.kt */
    public final /* synthetic */ class CC {
        public static int $default$minIntrinsicWidth(MultiContentMeasurePolicy _this, IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                ArrayList arrayList2 = arrayList;
                List list2 = (List) list.get(i2);
                ArrayList arrayList3 = new ArrayList(list2.size());
                int size2 = list2.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    arrayList3.add(new DefaultIntrinsicMeasurable((IntrinsicMeasurable) list2.get(i3), IntrinsicMinMax.Min, IntrinsicWidthHeight.Width));
                }
                arrayList2.add(arrayList3);
            }
            return _this.mo668measure3p2s80s(new IntrinsicsMeasureScope(intrinsicMeasureScope, intrinsicMeasureScope.getLayoutDirection()), arrayList, ConstraintsKt.Constraints$default(0, 0, 0, i, 7, null)).get$w();
        }

        public static int $default$minIntrinsicHeight(MultiContentMeasurePolicy _this, IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                ArrayList arrayList2 = arrayList;
                List list2 = (List) list.get(i2);
                ArrayList arrayList3 = new ArrayList(list2.size());
                int size2 = list2.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    arrayList3.add(new DefaultIntrinsicMeasurable((IntrinsicMeasurable) list2.get(i3), IntrinsicMinMax.Min, IntrinsicWidthHeight.Height));
                }
                arrayList2.add(arrayList3);
            }
            return _this.mo668measure3p2s80s(new IntrinsicsMeasureScope(intrinsicMeasureScope, intrinsicMeasureScope.getLayoutDirection()), arrayList, ConstraintsKt.Constraints$default(0, i, 0, 0, 13, null)).get$h();
        }

        public static int $default$maxIntrinsicWidth(MultiContentMeasurePolicy _this, IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                ArrayList arrayList2 = arrayList;
                List list2 = (List) list.get(i2);
                ArrayList arrayList3 = new ArrayList(list2.size());
                int size2 = list2.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    arrayList3.add(new DefaultIntrinsicMeasurable((IntrinsicMeasurable) list2.get(i3), IntrinsicMinMax.Max, IntrinsicWidthHeight.Width));
                }
                arrayList2.add(arrayList3);
            }
            return _this.mo668measure3p2s80s(new IntrinsicsMeasureScope(intrinsicMeasureScope, intrinsicMeasureScope.getLayoutDirection()), arrayList, ConstraintsKt.Constraints$default(0, 0, 0, i, 7, null)).get$w();
        }

        public static int $default$maxIntrinsicHeight(MultiContentMeasurePolicy _this, IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                ArrayList arrayList2 = arrayList;
                List list2 = (List) list.get(i2);
                ArrayList arrayList3 = new ArrayList(list2.size());
                int size2 = list2.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    arrayList3.add(new DefaultIntrinsicMeasurable((IntrinsicMeasurable) list2.get(i3), IntrinsicMinMax.Max, IntrinsicWidthHeight.Height));
                }
                arrayList2.add(arrayList3);
            }
            return _this.mo668measure3p2s80s(new IntrinsicsMeasureScope(intrinsicMeasureScope, intrinsicMeasureScope.getLayoutDirection()), arrayList, ConstraintsKt.Constraints$default(0, i, 0, 0, 13, null)).get$h();
        }
    }
}
