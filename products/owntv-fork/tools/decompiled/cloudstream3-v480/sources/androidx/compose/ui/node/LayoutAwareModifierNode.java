package androidx.compose.ui.node;

import androidx.compose.ui.layout.LayoutCoordinates;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: compiled from: LayoutAwareModifierNode.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0017\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/node/LayoutAwareModifierNode;", "Landroidx/compose/ui/node/DelegatableNode;", "onPlaced", "", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "onRemeasured", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/unit/IntSize;", "onRemeasured-ozmzZPI", "(J)V", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface LayoutAwareModifierNode extends DelegatableNode {

    /* JADX INFO: renamed from: androidx.compose.ui.node.LayoutAwareModifierNode$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: LayoutAwareModifierNode.kt */
    public final /* synthetic */ class CC {
        public static void $default$onPlaced(LayoutAwareModifierNode _this, LayoutCoordinates layoutCoordinates) {
        }

        /* JADX INFO: renamed from: $default$onRemeasured-ozmzZPI, reason: not valid java name */
        public static void m5779$default$onRemeasuredozmzZPI(LayoutAwareModifierNode _this, long j) {
        }
    }

    void onPlaced(LayoutCoordinates coordinates);

    /* JADX INFO: renamed from: onRemeasured-ozmzZPI */
    void mo396onRemeasuredozmzZPI(long size);
}
