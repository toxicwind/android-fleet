package androidx.compose.ui.semantics;

import androidx.compose.ui.unit.IntRect;
import kotlin.Metadata;

/* JADX INFO: compiled from: SemanticsOwner.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsNodeWithAdjustedBounds;", "", "semanticsNode", "Landroidx/compose/ui/semantics/SemanticsNode;", "adjustedBounds", "Landroidx/compose/ui/unit/IntRect;", "<init>", "(Landroidx/compose/ui/semantics/SemanticsNode;Landroidx/compose/ui/unit/IntRect;)V", "getSemanticsNode", "()Landroidx/compose/ui/semantics/SemanticsNode;", "getAdjustedBounds", "()Landroidx/compose/ui/unit/IntRect;", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SemanticsNodeWithAdjustedBounds {
    public static final int $stable = 8;
    private final IntRect adjustedBounds;
    private final SemanticsNode semanticsNode;

    public SemanticsNodeWithAdjustedBounds(SemanticsNode semanticsNode, IntRect intRect) {
        this.semanticsNode = semanticsNode;
        this.adjustedBounds = intRect;
    }

    public final SemanticsNode getSemanticsNode() {
        return this.semanticsNode;
    }

    public final IntRect getAdjustedBounds() {
        return this.adjustedBounds;
    }
}
