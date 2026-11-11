package androidx.compose.ui.node;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.ObjectIntMapKt;
import androidx.compose.ui.internal.InlineClassHelperKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DepthSortedSet.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\bJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\bJ\u000e\u0010\u000f\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\bJ\u0006\u0010\u0010\u001a\u00020\bJ\u001f\u0010\u0011\u001a\u00020\u000e2\u0014\b\u0004\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000e0\u0013H\u0086\bJ\u0006\u0010\u0014\u001a\u00020\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u0086\bJ\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/compose/ui/node/DepthSortedSet;", "", "extraAssertions", "", "<init>", "(Z)V", "mapOfOriginalDepth", "Landroidx/collection/MutableObjectIntMap;", "Landroidx/compose/ui/node/LayoutNode;", "set", "Landroidx/compose/ui/node/SortedSet;", "contains", "node", "add", "", "remove", "pop", "popEach", "block", "Lkotlin/Function1;", "isEmpty", "isNotEmpty", "safeMapOfOriginalDepth", "toString", "", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DepthSortedSet {
    public static final int $stable = 8;
    private final boolean extraAssertions;
    private MutableObjectIntMap<LayoutNode> mapOfOriginalDepth;
    private final SortedSet<LayoutNode> set = new SortedSet<>(DepthSortedSetKt.DepthComparator);

    public DepthSortedSet(boolean z) {
        this.extraAssertions = z;
    }

    public final boolean contains(LayoutNode node) {
        boolean zContains = this.set.contains(node);
        if (this.extraAssertions) {
            if (!(zContains == safeMapOfOriginalDepth().containsKey(node))) {
                InlineClassHelperKt.throwIllegalStateException("inconsistency in TreeSet");
            }
        }
        return zContains;
    }

    public final void add(LayoutNode node) {
        if (!node.isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("DepthSortedSet.add called on an unattached node");
        }
        if (this.extraAssertions) {
            MutableObjectIntMap<LayoutNode> mutableObjectIntMapSafeMapOfOriginalDepth = safeMapOfOriginalDepth();
            int orDefault = mutableObjectIntMapSafeMapOfOriginalDepth.getOrDefault(node, Integer.MAX_VALUE);
            if (orDefault == Integer.MAX_VALUE) {
                mutableObjectIntMapSafeMapOfOriginalDepth.set(node, node.getDepth());
            } else {
                if (!(orDefault == node.getDepth())) {
                    InlineClassHelperKt.throwIllegalStateException("invalid node depth");
                }
            }
        }
        this.set.add(node);
    }

    public final boolean remove(LayoutNode node) {
        if (!node.isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("DepthSortedSet.remove called on an unattached node");
        }
        boolean zRemove = this.set.remove(node);
        if (this.extraAssertions) {
            MutableObjectIntMap<LayoutNode> mutableObjectIntMapSafeMapOfOriginalDepth = safeMapOfOriginalDepth();
            if (mutableObjectIntMapSafeMapOfOriginalDepth.containsKey(node)) {
                int i = mutableObjectIntMapSafeMapOfOriginalDepth.get(node);
                mutableObjectIntMapSafeMapOfOriginalDepth.remove(node);
                if (!(i == (zRemove ? node.getDepth() : Integer.MAX_VALUE))) {
                    InlineClassHelperKt.throwIllegalStateException("invalid node depth");
                }
            }
        }
        return zRemove;
    }

    public final LayoutNode pop() {
        LayoutNode layoutNodeFirst = this.set.first();
        remove(layoutNodeFirst);
        return layoutNodeFirst;
    }

    public final boolean isEmpty() {
        return this.set.isEmpty();
    }

    public final boolean isNotEmpty() {
        return !isEmpty();
    }

    private final MutableObjectIntMap<LayoutNode> safeMapOfOriginalDepth() {
        if (this.mapOfOriginalDepth == null) {
            this.mapOfOriginalDepth = ObjectIntMapKt.mutableObjectIntMapOf();
        }
        MutableObjectIntMap<LayoutNode> mutableObjectIntMap = this.mapOfOriginalDepth;
        Intrinsics.checkNotNull(mutableObjectIntMap);
        return mutableObjectIntMap;
    }

    public String toString() {
        return this.set.toString();
    }

    public final void popEach(Function1<? super LayoutNode, Unit> block) {
        while (!isEmpty()) {
            block.invoke(pop());
        }
    }
}
