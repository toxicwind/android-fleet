package androidx.compose.ui.scrollcapture;

import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.unit.IntRect;
import kotlin.Metadata;

/* JADX INFO: compiled from: ScrollCapture.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Landroidx/compose/ui/scrollcapture/ScrollCaptureCandidate;", "", "node", "Landroidx/compose/ui/semantics/SemanticsNode;", "depth", "", "viewportBoundsInWindow", "Landroidx/compose/ui/unit/IntRect;", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "<init>", "(Landroidx/compose/ui/semantics/SemanticsNode;ILandroidx/compose/ui/unit/IntRect;Landroidx/compose/ui/layout/LayoutCoordinates;)V", "getNode", "()Landroidx/compose/ui/semantics/SemanticsNode;", "getDepth", "()I", "getViewportBoundsInWindow", "()Landroidx/compose/ui/unit/IntRect;", "getCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "toString", "", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class ScrollCaptureCandidate {
    private final LayoutCoordinates coordinates;
    private final int depth;
    private final SemanticsNode node;
    private final IntRect viewportBoundsInWindow;

    public ScrollCaptureCandidate(SemanticsNode semanticsNode, int i, IntRect intRect, LayoutCoordinates layoutCoordinates) {
        this.node = semanticsNode;
        this.depth = i;
        this.viewportBoundsInWindow = intRect;
        this.coordinates = layoutCoordinates;
    }

    public final SemanticsNode getNode() {
        return this.node;
    }

    public final int getDepth() {
        return this.depth;
    }

    public final IntRect getViewportBoundsInWindow() {
        return this.viewportBoundsInWindow;
    }

    public final LayoutCoordinates getCoordinates() {
        return this.coordinates;
    }

    public String toString() {
        return "ScrollCaptureCandidate(node=" + this.node + ", depth=" + this.depth + ", viewportBoundsInWindow=" + this.viewportBoundsInWindow + ", coordinates=" + this.coordinates + ')';
    }
}
