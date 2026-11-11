package androidx.compose.ui.semantics;

import android.os.Trace;
import androidx.collection.IntObjectMap;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutInfo;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: SemanticsOwner.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u001a\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a,\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00020\b*\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0000\u001a\f\u0010\u000b\u001a\u00020\u0005*\u00020\u0002H\u0000\u001a\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\tH\u0000\"\u001e\u0010\f\u001a\u00020\u0005*\u00020\u00028@X\u0080\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\f\u0010\u000f\"\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"getAllSemanticsNodes", "", "Landroidx/compose/ui/semantics/SemanticsNode;", "Landroidx/compose/ui/semantics/SemanticsOwner;", "mergingEnabled", "", "skipDeactivatedNodes", "getAllSemanticsNodesToMap", "", "", "useUnmergedTree", "isImportantForAccessibility", "isHidden", "isHidden$annotations", "(Landroidx/compose/ui/semantics/SemanticsNode;)V", "(Landroidx/compose/ui/semantics/SemanticsNode;)Z", "DefaultFakeNodeBounds", "Landroidx/compose/ui/geometry/Rect;", "getAllUncoveredSemanticsNodesToIntObjectMap", "Landroidx/collection/IntObjectMap;", "Landroidx/compose/ui/semantics/SemanticsNodeWithAdjustedBounds;", "customRootNodeId", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SemanticsOwnerKt {
    private static final Rect DefaultFakeNodeBounds = new Rect(0.0f, 0.0f, 10.0f, 10.0f);

    public static /* synthetic */ void isHidden$annotations(SemanticsNode semanticsNode) {
    }

    public static /* synthetic */ List getAllSemanticsNodes$default(SemanticsOwner semanticsOwner, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = true;
        }
        return getAllSemanticsNodes(semanticsOwner, z, z2);
    }

    public static final List<SemanticsNode> getAllSemanticsNodes(SemanticsOwner semanticsOwner, boolean z, boolean z2) {
        return CollectionsKt.toList(getAllSemanticsNodesToMap(semanticsOwner, !z, z2).values());
    }

    public static /* synthetic */ Map getAllSemanticsNodesToMap$default(SemanticsOwner semanticsOwner, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return getAllSemanticsNodesToMap(semanticsOwner, z, z2);
    }

    public static final Map<Integer, SemanticsNode> getAllSemanticsNodesToMap(SemanticsOwner semanticsOwner, boolean z, boolean z2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        SemanticsNode unmergedRootSemanticsNode = z ? semanticsOwner.getUnmergedRootSemanticsNode() : semanticsOwner.getRootSemanticsNode();
        if (z2 && unmergedRootSemanticsNode.getLayoutNode().getIsDeactivated()) {
            return linkedHashMap;
        }
        getAllSemanticsNodesToMap$findAllSemanticNodesRecursive(linkedHashMap, z2, unmergedRootSemanticsNode);
        return linkedHashMap;
    }

    private static final void getAllSemanticsNodesToMap$findAllSemanticNodesRecursive(Map<Integer, SemanticsNode> map, boolean z, SemanticsNode semanticsNode) {
        map.put(Integer.valueOf(semanticsNode.getId()), semanticsNode);
        List children$ui_release$default = SemanticsNode.getChildren$ui_release$default(semanticsNode, false, false, !z, 3, null);
        int size = children$ui_release$default.size();
        for (int i = 0; i < size; i++) {
            getAllSemanticsNodesToMap$findAllSemanticNodesRecursive(map, z, (SemanticsNode) children$ui_release$default.get(i));
        }
    }

    public static final boolean isImportantForAccessibility(SemanticsNode semanticsNode) {
        if (isHidden(semanticsNode)) {
            return false;
        }
        return semanticsNode.getUnmergedConfig().getIsMergingSemanticsOfDescendants() || semanticsNode.getUnmergedConfig().containsImportantForAccessibility$ui_release();
    }

    public static final boolean isHidden(SemanticsNode semanticsNode) {
        return semanticsNode.isTransparent$ui_release() || semanticsNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getHideFromAccessibility()) || semanticsNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getInvisibleToUser());
    }

    public static final IntObjectMap<SemanticsNodeWithAdjustedBounds> getAllUncoveredSemanticsNodesToIntObjectMap(SemanticsOwner semanticsOwner, int i) {
        Trace.beginSection("getAllUncoveredSemanticsNodesToIntObjectMap");
        try {
            SemanticsNode unmergedRootSemanticsNode = semanticsOwner.getUnmergedRootSemanticsNode();
            if (unmergedRootSemanticsNode.getLayoutNode().isPlaced() && unmergedRootSemanticsNode.getLayoutNode().isAttached()) {
                MutableIntObjectMap mutableIntObjectMap = new MutableIntObjectMap(48);
                SemanticsRegion SemanticsRegion = SemanticsRegion_androidKt.SemanticsRegion();
                SemanticsRegion.set(IntRectKt.roundToIntRect(unmergedRootSemanticsNode.getBoundsInRoot()));
                getAllUncoveredSemanticsNodesToIntObjectMap$lambda$2$findAllSemanticNodesRecursive$1(SemanticsRegion, unmergedRootSemanticsNode, i, mutableIntObjectMap, unmergedRootSemanticsNode, SemanticsRegion_androidKt.SemanticsRegion());
                return mutableIntObjectMap;
            }
            return IntObjectMapKt.emptyIntObjectMap();
        } finally {
            Trace.endSection();
        }
    }

    private static final void getAllUncoveredSemanticsNodesToIntObjectMap$lambda$2$findAllSemanticNodesRecursive$1(SemanticsRegion semanticsRegion, SemanticsNode semanticsNode, int i, MutableIntObjectMap<SemanticsNodeWithAdjustedBounds> mutableIntObjectMap, SemanticsNode semanticsNode2, SemanticsRegion semanticsRegion2) {
        Rect boundsInRoot;
        LayoutInfo layoutInfo;
        SemanticsRegion semanticsRegion3;
        SemanticsNode semanticsNode3;
        int i2;
        MutableIntObjectMap<SemanticsNodeWithAdjustedBounds> mutableIntObjectMap2;
        SemanticsRegion semanticsRegion4;
        boolean z = (semanticsNode2.getLayoutNode().isPlaced() && semanticsNode2.getLayoutNode().isAttached()) ? false : true;
        if (!semanticsRegion.isEmpty() || semanticsNode2.getId() == semanticsNode.getId()) {
            if (!z || semanticsNode2.getIsFake()) {
                IntRect intRectRoundToIntRect = IntRectKt.roundToIntRect(semanticsNode2.getTouchBoundsInRoot());
                semanticsRegion2.set(intRectRoundToIntRect);
                int id = semanticsNode2.getId() == semanticsNode.getId() ? i : semanticsNode2.getId();
                if (semanticsRegion2.intersect(semanticsRegion)) {
                    mutableIntObjectMap.set(id, new SemanticsNodeWithAdjustedBounds(semanticsNode2, semanticsRegion2.getBounds()));
                    List<SemanticsNode> replacedChildren$ui_release = semanticsNode2.getReplacedChildren$ui_release();
                    int size = replacedChildren$ui_release.size() - 1;
                    while (-1 < size) {
                        if (replacedChildren$ui_release.get(size).getConfig().contains(SemanticsProperties.INSTANCE.getLinkTestMarker())) {
                            semanticsRegion3 = semanticsRegion;
                            semanticsNode3 = semanticsNode;
                            i2 = i;
                            mutableIntObjectMap2 = mutableIntObjectMap;
                            semanticsRegion4 = semanticsRegion2;
                        } else {
                            semanticsRegion3 = semanticsRegion;
                            semanticsNode3 = semanticsNode;
                            i2 = i;
                            mutableIntObjectMap2 = mutableIntObjectMap;
                            semanticsRegion4 = semanticsRegion2;
                            getAllUncoveredSemanticsNodesToIntObjectMap$lambda$2$findAllSemanticNodesRecursive$1(semanticsRegion3, semanticsNode3, i2, mutableIntObjectMap2, replacedChildren$ui_release.get(size), semanticsRegion4);
                        }
                        size--;
                        semanticsRegion = semanticsRegion3;
                        semanticsNode = semanticsNode3;
                        i = i2;
                        mutableIntObjectMap = mutableIntObjectMap2;
                        semanticsRegion2 = semanticsRegion4;
                    }
                    SemanticsRegion semanticsRegion5 = semanticsRegion;
                    if (isImportantForAccessibility(semanticsNode2)) {
                        semanticsRegion5.difference(intRectRoundToIntRect);
                        return;
                    }
                    return;
                }
                if (!semanticsNode2.getIsFake()) {
                    if (id == i) {
                        mutableIntObjectMap.set(id, new SemanticsNodeWithAdjustedBounds(semanticsNode2, semanticsRegion2.getBounds()));
                        return;
                    }
                    return;
                }
                SemanticsNode parent = semanticsNode2.getParent();
                if (parent != null && (layoutInfo = parent.getLayoutInfo()) != null && layoutInfo.isPlaced()) {
                    boundsInRoot = parent.getBoundsInRoot();
                } else {
                    boundsInRoot = DefaultFakeNodeBounds;
                }
                mutableIntObjectMap.set(id, new SemanticsNodeWithAdjustedBounds(semanticsNode2, IntRectKt.roundToIntRect(boundsInRoot)));
            }
        }
    }
}
