package androidx.compose.ui.layout;

import androidx.collection.IntObjectMap;
import androidx.collection.MutableIntObjectMap;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.NodeKindKt;
import androidx.compose.ui.node.TraversableNode;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: WindowInsetsRulers.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\u0014\u0010\t\u001a\u00020\n*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000\u001a3\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u0018\u0010\u0019\"\u000e\u0010\r\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001e\"\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0 X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"findDisplayCutouts", "", "Landroidx/compose/ui/layout/RectRulers;", "placementScope", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "findInsetsAnimationProperties", "Landroidx/compose/ui/layout/WindowInsetsAnimation;", "windowInsetsRulers", "Landroidx/compose/ui/layout/WindowInsetsRulers;", "applyWindowInsetsRulers", "Landroidx/compose/ui/Modifier;", "insetsListener", "Landroidx/compose/ui/layout/InsetsListener;", "RulerKey", "", "provideInsetsValues", "", "Landroidx/compose/ui/layout/RulerScope;", "rulers", "insets", "Landroidx/compose/ui/layout/ValueInsets;", "width", "", "height", "provideInsetsValues-cytEWk0", "(Landroidx/compose/ui/layout/RulerScope;Landroidx/compose/ui/layout/RectRulers;JII)V", "WindowInsetsTypeMap", "Landroidx/collection/IntObjectMap;", "AnimatableInsetsRulers", "", "[Landroidx/compose/ui/layout/WindowInsetsRulers;", "AnimatableRulers", "Landroidx/collection/MutableIntObjectMap;", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class WindowInsetsRulers_androidKt {
    private static final WindowInsetsRulers[] AnimatableInsetsRulers;
    private static final MutableIntObjectMap<WindowInsetsRulers> AnimatableRulers;
    private static final String RulerKey = "androidx.compose.ui.layout.WindowInsetsRulers";
    private static final IntObjectMap<WindowInsetsRulers> WindowInsetsTypeMap;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v11 */
    public static final List<RectRulers> findDisplayCutouts(Placeable.PlacementScope placementScope) {
        LayoutCoordinates coordinates = placementScope.getCoordinates();
        LayoutCoordinates layoutCoordinatesFindRootCoordinates = coordinates != null ? LayoutCoordinatesKt.findRootCoordinates(coordinates) : null;
        for (NodeCoordinator wrapped = layoutCoordinatesFindRootCoordinates instanceof NodeCoordinator ? (NodeCoordinator) layoutCoordinatesFindRootCoordinates : null; wrapped != null; wrapped = wrapped.getWrapped()) {
            int iM5881constructorimpl = NodeKind.m5881constructorimpl(262144);
            boolean zM5890getIncludeSelfInTraversalH91voCI = NodeKindKt.m5890getIncludeSelfInTraversalH91voCI(iM5881constructorimpl);
            Modifier.Node tail = wrapped.getTail();
            if (zM5890getIncludeSelfInTraversalH91voCI || (tail = tail.getParent()) != null) {
                for (Modifier.Node nodeHeadNode = wrapped.headNode(zM5890getIncludeSelfInTraversalH91voCI); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & iM5881constructorimpl) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
                    if ((nodeHeadNode.getKindSet() & iM5881constructorimpl) != 0) {
                        MutableVector mutableVector = null;
                        Modifier.Node nodePop = nodeHeadNode;
                        while (nodePop != 0) {
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode traversableNode = (TraversableNode) nodePop;
                                if (traversableNode.getTraverseKey() == RulerKey) {
                                    return ((RulerProviderModifierNode) traversableNode).getCutoutRulers();
                                }
                            } else if ((nodePop.getKindSet() & iM5881constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate();
                                int i = 0;
                                nodePop = nodePop;
                                while (delegate$ui_release != null) {
                                    if ((delegate$ui_release.getKindSet() & iM5881constructorimpl) != 0) {
                                        i++;
                                        if (i == 1) {
                                            nodePop = delegate$ui_release;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != 0) {
                                                if (mutableVector != null) {
                                                    mutableVector.add(nodePop);
                                                }
                                                nodePop = 0;
                                            }
                                            if (mutableVector != null) {
                                                mutableVector.add(delegate$ui_release);
                                            }
                                        }
                                    }
                                    delegate$ui_release = delegate$ui_release.getChild();
                                    nodePop = nodePop;
                                }
                                if (i == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    if (nodeHeadNode == tail) {
                        break;
                    }
                }
            }
        }
        return CollectionsKt.emptyList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v11 */
    public static final WindowInsetsAnimation findInsetsAnimationProperties(Placeable.PlacementScope placementScope, WindowInsetsRulers windowInsetsRulers) {
        LayoutCoordinates coordinates = placementScope.getCoordinates();
        LayoutCoordinates layoutCoordinatesFindRootCoordinates = coordinates != null ? LayoutCoordinatesKt.findRootCoordinates(coordinates) : null;
        for (NodeCoordinator wrapped = layoutCoordinatesFindRootCoordinates instanceof NodeCoordinator ? (NodeCoordinator) layoutCoordinatesFindRootCoordinates : null; wrapped != null; wrapped = wrapped.getWrapped()) {
            int iM5881constructorimpl = NodeKind.m5881constructorimpl(262144);
            boolean zM5890getIncludeSelfInTraversalH91voCI = NodeKindKt.m5890getIncludeSelfInTraversalH91voCI(iM5881constructorimpl);
            Modifier.Node tail = wrapped.getTail();
            if (zM5890getIncludeSelfInTraversalH91voCI || (tail = tail.getParent()) != null) {
                for (Modifier.Node nodeHeadNode = wrapped.headNode(zM5890getIncludeSelfInTraversalH91voCI); nodeHeadNode != null && (nodeHeadNode.getAggregateChildKindSet() & iM5881constructorimpl) != 0; nodeHeadNode = nodeHeadNode.getChild()) {
                    if ((nodeHeadNode.getKindSet() & iM5881constructorimpl) != 0) {
                        MutableVector mutableVector = null;
                        Modifier.Node nodePop = nodeHeadNode;
                        while (nodePop != 0) {
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode traversableNode = (TraversableNode) nodePop;
                                if (traversableNode.getTraverseKey() == RulerKey) {
                                    WindowWindowInsetsAnimationValues windowWindowInsetsAnimationValues = ((RulerProviderModifierNode) traversableNode).getInsetsValues().get(windowInsetsRulers);
                                    return windowWindowInsetsAnimationValues != null ? windowWindowInsetsAnimationValues : NoWindowInsetsAnimation.INSTANCE;
                                }
                            } else if ((nodePop.getKindSet() & iM5881constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate();
                                int i = 0;
                                nodePop = nodePop;
                                while (delegate$ui_release != null) {
                                    if ((delegate$ui_release.getKindSet() & iM5881constructorimpl) != 0) {
                                        i++;
                                        if (i == 1) {
                                            nodePop = delegate$ui_release;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != 0) {
                                                if (mutableVector != null) {
                                                    mutableVector.add(nodePop);
                                                }
                                                nodePop = 0;
                                            }
                                            if (mutableVector != null) {
                                                mutableVector.add(delegate$ui_release);
                                            }
                                        }
                                    }
                                    delegate$ui_release = delegate$ui_release.getChild();
                                    nodePop = nodePop;
                                }
                                if (i == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                        }
                    }
                    if (nodeHeadNode == tail) {
                        break;
                    }
                }
            }
        }
        return NoWindowInsetsAnimation.INSTANCE;
    }

    public static final Modifier applyWindowInsetsRulers(Modifier modifier, InsetsListener insetsListener) {
        return modifier.then(new RulerProviderModifierElement(insetsListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: provideInsetsValues-cytEWk0, reason: not valid java name */
    public static final void m5708provideInsetsValuescytEWk0(RulerScope rulerScope, RectRulers rectRulers, long j, int i, int i2) {
        if (ValueInsets.m5699equalsimpl0(j, ValueInsets_androidKt.getUnsetValueInsets())) {
            return;
        }
        float f = (int) ((j >>> 48) & WebSocketProtocol.PAYLOAD_SHORT_MAX);
        float f2 = (int) ((j >>> 32) & WebSocketProtocol.PAYLOAD_SHORT_MAX);
        float f3 = i - ((int) ((j >>> 16) & WebSocketProtocol.PAYLOAD_SHORT_MAX));
        float f4 = i2 - ((int) (j & WebSocketProtocol.PAYLOAD_SHORT_MAX));
        rulerScope.provides(rectRulers.getLeft(), f);
        rulerScope.provides(rectRulers.getTop(), f2);
        rulerScope.provides(rectRulers.getRight(), f3);
        rulerScope.provides(rectRulers.getBottom(), f4);
    }

    static {
        MutableIntObjectMap mutableIntObjectMap = new MutableIntObjectMap(8);
        mutableIntObjectMap.set(WindowInsetsCompat.Type.statusBars(), WindowInsetsRulers.INSTANCE.getStatusBars());
        mutableIntObjectMap.set(WindowInsetsCompat.Type.navigationBars(), WindowInsetsRulers.INSTANCE.getNavigationBars());
        mutableIntObjectMap.set(WindowInsetsCompat.Type.captionBar(), WindowInsetsRulers.INSTANCE.getCaptionBar());
        mutableIntObjectMap.set(WindowInsetsCompat.Type.ime(), WindowInsetsRulers.INSTANCE.getIme());
        mutableIntObjectMap.set(WindowInsetsCompat.Type.systemGestures(), WindowInsetsRulers.INSTANCE.getSystemGestures());
        mutableIntObjectMap.set(WindowInsetsCompat.Type.mandatorySystemGestures(), WindowInsetsRulers.INSTANCE.getMandatorySystemGestures());
        mutableIntObjectMap.set(WindowInsetsCompat.Type.tappableElement(), WindowInsetsRulers.INSTANCE.getTappableElement());
        WindowInsetsTypeMap = mutableIntObjectMap;
        AnimatableInsetsRulers = new WindowInsetsRulers[]{WindowInsetsRulers.INSTANCE.getStatusBars(), WindowInsetsRulers.INSTANCE.getNavigationBars(), WindowInsetsRulers.INSTANCE.getCaptionBar(), WindowInsetsRulers.INSTANCE.getTappableElement(), WindowInsetsRulers.INSTANCE.getSystemGestures(), WindowInsetsRulers.INSTANCE.getMandatorySystemGestures(), WindowInsetsRulers.INSTANCE.getIme(), WindowInsetsRulers.INSTANCE.getWaterfall(), WindowInsetsRulers.INSTANCE.getDisplayCutout()};
        MutableIntObjectMap<WindowInsetsRulers> mutableIntObjectMap2 = new MutableIntObjectMap<>(7);
        mutableIntObjectMap2.set(WindowInsetsCompat.Type.statusBars(), WindowInsetsRulers.INSTANCE.getStatusBars());
        mutableIntObjectMap2.set(WindowInsetsCompat.Type.navigationBars(), WindowInsetsRulers.INSTANCE.getNavigationBars());
        mutableIntObjectMap2.set(WindowInsetsCompat.Type.captionBar(), WindowInsetsRulers.INSTANCE.getCaptionBar());
        mutableIntObjectMap2.set(WindowInsetsCompat.Type.systemGestures(), WindowInsetsRulers.INSTANCE.getSystemGestures());
        mutableIntObjectMap2.set(WindowInsetsCompat.Type.tappableElement(), WindowInsetsRulers.INSTANCE.getTappableElement());
        mutableIntObjectMap2.set(WindowInsetsCompat.Type.mandatorySystemGestures(), WindowInsetsRulers.INSTANCE.getMandatorySystemGestures());
        mutableIntObjectMap2.set(WindowInsetsCompat.Type.ime(), WindowInsetsRulers.INSTANCE.getIme());
        AnimatableRulers = mutableIntObjectMap2;
    }
}
