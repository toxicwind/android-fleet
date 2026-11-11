package androidx.compose.ui.node;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: DepthSortedSet.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003J\u000e\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fJX\u0010\u0013\u001a\u00020\u000f2M\b\u0004\u0010\u0014\u001aG\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u000f0\u0015H\u0086\bJ\u0006\u0010\u0019\u001a\u00020\u0003J\u0006\u0010\u001d\u001a\u00020\u0003R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001a\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001e"}, d2 = {"Landroidx/compose/ui/node/DepthSortedSetsForDifferentPasses;", "", "extraAssertions", "", "<init>", "(Z)V", "lookaheadAndAncestorMeasureSet", "Landroidx/compose/ui/node/DepthSortedSet;", "lookaheadAndAncestorPlaceSet", "approachSet", "contains", "node", "Landroidx/compose/ui/node/LayoutNode;", "affectsLookahead", "add", "", "invalidation", "Landroidx/compose/ui/node/Invalidation;", "remove", "popEach", "block", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "relayoutNeeded", "isEmpty", "affectsLookaheadMeasure", "getAffectsLookaheadMeasure", "()Z", "isNotEmpty", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DepthSortedSetsForDifferentPasses {
    public static final int $stable = 8;
    private final DepthSortedSet approachSet;
    private final DepthSortedSet lookaheadAndAncestorMeasureSet;
    private final DepthSortedSet lookaheadAndAncestorPlaceSet;

    /* JADX INFO: compiled from: DepthSortedSet.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Invalidation.values().length];
            try {
                iArr[Invalidation.LookaheadMeasurement.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Invalidation.LookaheadPlacement.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Invalidation.Measurement.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Invalidation.Placement.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public DepthSortedSetsForDifferentPasses(boolean z) {
        this.lookaheadAndAncestorMeasureSet = new DepthSortedSet(z);
        this.lookaheadAndAncestorPlaceSet = new DepthSortedSet(z);
        this.approachSet = new DepthSortedSet(z);
    }

    public final boolean contains(LayoutNode node, boolean affectsLookahead) {
        boolean z = node.getLookaheadRoot() == null;
        boolean z2 = this.lookaheadAndAncestorMeasureSet.contains(node) || this.lookaheadAndAncestorPlaceSet.contains(node);
        if (affectsLookahead) {
            return !z && z2;
        }
        return (z && z2) || this.approachSet.contains(node);
    }

    public final boolean contains(LayoutNode node) {
        return this.lookaheadAndAncestorMeasureSet.contains(node) || this.lookaheadAndAncestorPlaceSet.contains(node) || this.approachSet.contains(node);
    }

    public final void add(LayoutNode node, Invalidation invalidation) {
        int i = WhenMappings.$EnumSwitchMapping$0[invalidation.ordinal()];
        if (i == 1) {
            this.lookaheadAndAncestorMeasureSet.add(node);
            this.approachSet.add(node);
            return;
        }
        if (i == 2) {
            this.lookaheadAndAncestorPlaceSet.add(node);
            this.approachSet.add(node);
            return;
        }
        if (i == 3) {
            if (node.getLookaheadRoot() != null) {
                this.approachSet.add(node);
                return;
            } else {
                this.lookaheadAndAncestorMeasureSet.add(node);
                return;
            }
        }
        if (i != 4) {
            throw new NoWhenBranchMatchedException();
        }
        if (node.getLookaheadRoot() != null) {
            this.approachSet.add(node);
        } else {
            this.lookaheadAndAncestorPlaceSet.add(node);
        }
    }

    public final boolean remove(LayoutNode node) {
        return this.approachSet.remove(node) || this.lookaheadAndAncestorMeasureSet.remove(node) || this.lookaheadAndAncestorPlaceSet.remove(node);
    }

    public final void popEach(Function3<? super LayoutNode, ? super Boolean, ? super Boolean, Unit> block) {
        LayoutNode layoutNodePop;
        while (true) {
            boolean z = true;
            boolean z2 = false;
            if (!this.lookaheadAndAncestorMeasureSet.isEmpty()) {
                layoutNodePop = this.lookaheadAndAncestorMeasureSet.pop();
                z2 = layoutNodePop.getLookaheadRoot() != null;
                z = false;
            } else if (!this.lookaheadAndAncestorPlaceSet.isEmpty()) {
                layoutNodePop = this.lookaheadAndAncestorPlaceSet.pop();
                if (layoutNodePop.getLookaheadRoot() != null) {
                    z2 = true;
                }
            } else if (this.approachSet.isEmpty()) {
                return;
            } else {
                layoutNodePop = this.approachSet.pop();
            }
            block.invoke(layoutNodePop, Boolean.valueOf(z2), Boolean.valueOf(z));
        }
    }

    public final boolean isEmpty() {
        return this.lookaheadAndAncestorMeasureSet.isEmpty() && this.approachSet.isEmpty() && this.lookaheadAndAncestorPlaceSet.isEmpty();
    }

    public final boolean getAffectsLookaheadMeasure() {
        return (this.approachSet.isEmpty() || this.lookaheadAndAncestorMeasureSet.isEmpty()) ? false : true;
    }

    public final boolean isNotEmpty() {
        return !isEmpty();
    }
}
