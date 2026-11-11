package androidx.compose.ui.spatial;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: ThrottledCallbacks.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aK\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"rectInfoFor", "Landroidx/compose/ui/spatial/RelativeLayoutBounds;", "node", "Landroidx/compose/ui/node/DelegatableNode;", "topLeft", "", "bottomRight", "windowOffset", "Landroidx/compose/ui/unit/IntOffset;", "screenOffset", "windowSize", "viewToWindowMatrix", "Landroidx/compose/ui/graphics/Matrix;", "rectInfoFor-Dg36KO4", "(Landroidx/compose/ui/node/DelegatableNode;JJJJJ[F)Landroidx/compose/ui/spatial/RelativeLayoutBounds;", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ThrottledCallbacksKt {
    /* JADX INFO: renamed from: rectInfoFor-Dg36KO4, reason: not valid java name */
    public static final RelativeLayoutBounds m6157rectInfoForDg36KO4(DelegatableNode delegatableNode, long j, long j2, long j3, long j4, long j5, float[] fArr) {
        NodeCoordinator nodeCoordinatorM5734requireCoordinator64DMado = DelegatableNodeKt.m5734requireCoordinator64DMado(delegatableNode, NodeKind.m5881constructorimpl(2));
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(delegatableNode);
        if (!layoutNodeRequireLayoutNode.isPlaced()) {
            return null;
        }
        if (layoutNodeRequireLayoutNode.getOuterCoordinator$ui_release() != nodeCoordinatorM5734requireCoordinator64DMado) {
            long jM7016constructorimpl = IntOffset.m7016constructorimpl(j);
            long jM3862constructorimpl = Offset.m3862constructorimpl((((long) Float.floatToRawIntBits(IntOffset.m7022getXimpl(jM7016constructorimpl))) << 32) | (((long) Float.floatToRawIntBits(IntOffset.m7023getYimpl(jM7016constructorimpl))) & 4294967295L));
            long jMo5583getSizeYbymL2g = nodeCoordinatorM5734requireCoordinator64DMado.getCoordinates().mo5583getSizeYbymL2g();
            long jM7039roundk4lQ0M = IntOffsetKt.m7039roundk4lQ0M(layoutNodeRequireLayoutNode.getOuterCoordinator$ui_release().getCoordinates().mo5584localPositionOfR5De75A(nodeCoordinatorM5734requireCoordinator64DMado, jM3862constructorimpl));
            return new RelativeLayoutBounds(jM7039roundk4lQ0M, IntOffset.m7016constructorimpl((((long) (IntOffset.m7022getXimpl(jM7039roundk4lQ0M) + ((int) (jMo5583getSizeYbymL2g >> 32)))) << 32) | (4294967295L & ((long) (IntOffset.m7023getYimpl(jM7039roundk4lQ0M) + ((int) (jMo5583getSizeYbymL2g & 4294967295L)))))), j3, j4, j5, fArr, delegatableNode, null);
        }
        return new RelativeLayoutBounds(j, j2, j3, j4, j5, fArr, delegatableNode, null);
    }
}
