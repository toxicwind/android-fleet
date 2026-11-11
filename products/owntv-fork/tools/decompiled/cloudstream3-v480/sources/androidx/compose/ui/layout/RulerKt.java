package androidx.compose.ui.layout;

import kotlin.Metadata;

/* JADX INFO: compiled from: Ruler.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a1\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"mergeRulerValues", "", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "useGreater", "", "rulers", "", "Landroidx/compose/ui/layout/Ruler;", "defaultValue", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;Z[Landroidx/compose/ui/layout/Ruler;F)F", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class RulerKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:11:0x001d  */
    public static final float mergeRulerValues(Placeable.PlacementScope placementScope, boolean z, Ruler[] rulerArr, float f) {
        float f2 = Float.NaN;
        for (Ruler ruler : rulerArr) {
            float fCurrent = placementScope.current(ruler, Float.NaN);
            if (Float.isNaN(f2)) {
                f2 = fCurrent;
            } else if (z == (fCurrent > f2)) {
                f2 = fCurrent;
            }
        }
        return Float.isNaN(f2) ? f : f2;
    }
}
