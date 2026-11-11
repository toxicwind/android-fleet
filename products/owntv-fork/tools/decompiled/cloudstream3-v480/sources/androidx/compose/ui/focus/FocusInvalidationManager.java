package androidx.compose.ui.focus;

import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.Owner;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: FocusInvalidationManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\nJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\fJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0012\u001a\u00020\u000eJ\b\u0010\u0013\u001a\u00020\u0010H\u0002J\b\u0010\u0014\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/focus/FocusInvalidationManager;", "", "focusOwner", "Landroidx/compose/ui/focus/FocusOwner;", "owner", "Landroidx/compose/ui/node/Owner;", "<init>", "(Landroidx/compose/ui/focus/FocusOwner;Landroidx/compose/ui/node/Owner;)V", "focusTargetNodes", "Landroidx/collection/MutableScatterSet;", "Landroidx/compose/ui/focus/FocusTargetNode;", "focusEventNodes", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "isInvalidationScheduled", "", "scheduleInvalidation", "", "node", "hasPendingInvalidation", "invalidateNodes", "invalidateOwnerFocusState", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class FocusInvalidationManager {
    public static final int $stable = 8;
    private final FocusOwner focusOwner;
    private boolean isInvalidationScheduled;
    private final Owner owner;
    private final MutableScatterSet<FocusTargetNode> focusTargetNodes = ScatterSetKt.mutableScatterSetOf();
    private final MutableScatterSet<FocusEventModifierNode> focusEventNodes = ScatterSetKt.mutableScatterSetOf();

    public FocusInvalidationManager(FocusOwner focusOwner, Owner owner) {
        this.focusOwner = focusOwner;
        this.owner = owner;
    }

    public final void scheduleInvalidation(FocusTargetNode node) {
        if (this.focusTargetNodes.add(node)) {
            scheduleInvalidation();
        }
    }

    public final void scheduleInvalidation(FocusEventModifierNode node) {
        if (this.focusEventNodes.add(node)) {
            scheduleInvalidation();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.focus.FocusInvalidationManager$scheduleInvalidation$1, reason: invalid class name */
    /* JADX INFO: compiled from: FocusInvalidationManager.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0<Unit> {
        AnonymousClass1(Object obj) {
            super(0, obj, FocusInvalidationManager.class, "invalidateNodes", "invalidateNodes()V", 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            ((FocusInvalidationManager) this.receiver).invalidateNodes();
        }
    }

    public final void scheduleInvalidation() {
        if (this.isInvalidationScheduled) {
            return;
        }
        this.owner.registerOnEndApplyChangesListener(new AnonymousClass1(this));
        this.isInvalidationScheduled = true;
    }

    /* JADX INFO: renamed from: hasPendingInvalidation, reason: from getter */
    public final boolean getIsInvalidationScheduled() {
        return this.isInvalidationScheduled;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:69:0x014e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:70:0x0150 A[LOOP:4: B:60:0x0120->B:70:0x0150, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:91:0x0153 A[EDGE_INSN: B:91:0x0153->B:71:0x0153 BREAK  A[LOOP:4: B:60:0x0120->B:70:0x0150], SYNTHETIC] */
    public final void invalidateNodes() {
        NodeChain nodes;
        long j;
        FocusTargetNode activeFocusTargetNode = this.focusOwner.getActiveFocusTargetNode();
        long j2 = 255;
        if (activeFocusTargetNode == null) {
            MutableScatterSet<FocusEventModifierNode> mutableScatterSet = this.focusEventNodes;
            Object[] objArr = mutableScatterSet.elements;
            long[] jArr = mutableScatterSet.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i = 0;
                while (true) {
                    long j3 = jArr[i];
                    if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i2 = 8 - ((~(i - length)) >>> 31);
                        int i3 = 0;
                        while (i3 < i2) {
                            if ((j3 & j2) < 128) {
                                ((FocusEventModifierNode) objArr[(i << 3) + i3]).onFocusEvent(FocusStateImpl.Inactive);
                            }
                            j3 >>= 8;
                            i3++;
                            j2 = j2;
                        }
                        j = j2;
                        if (i2 != 8) {
                            break;
                        }
                    } else {
                        j = j2;
                    }
                    if (i == length) {
                        break;
                    }
                    i++;
                    j2 = j;
                }
            }
        } else if (activeFocusTargetNode.getIsAttached()) {
            if (this.focusTargetNodes.contains(activeFocusTargetNode)) {
                activeFocusTargetNode.invalidateFocus$ui_release();
            }
            FocusStateImpl focusState = activeFocusTargetNode.getFocusState();
            FocusTargetNode focusTargetNode = activeFocusTargetNode;
            int iM5881constructorimpl = NodeKind.m5881constructorimpl(1024) | NodeKind.m5881constructorimpl(4096);
            if (!focusTargetNode.getNode().getIsAttached()) {
                InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node node = focusTargetNode.getNode();
            LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode);
            int i4 = 0;
            while (layoutNodeRequireLayoutNode != null) {
                if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM5881constructorimpl) != 0) {
                    while (node != null) {
                        if ((node.getKindSet() & iM5881constructorimpl) != 0) {
                            if ((NodeKind.m5881constructorimpl(1024) & node.getKindSet()) != 0) {
                                i4++;
                            }
                            if ((node instanceof FocusEventModifierNode) && this.focusEventNodes.contains(node)) {
                                if (i4 <= 1) {
                                    ((FocusEventModifierNode) node).onFocusEvent(focusState);
                                } else {
                                    ((FocusEventModifierNode) node).onFocusEvent(FocusStateImpl.ActiveParent);
                                }
                                this.focusEventNodes.remove(node);
                            }
                        }
                        node = node.getParent();
                    }
                }
                layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
                node = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
            }
            MutableScatterSet<FocusEventModifierNode> mutableScatterSet2 = this.focusEventNodes;
            Object[] objArr2 = mutableScatterSet2.elements;
            long[] jArr2 = mutableScatterSet2.metadata;
            int length2 = jArr2.length - 2;
            if (length2 >= 0) {
                int i5 = 0;
                while (true) {
                    long j4 = jArr2[i5];
                    if ((((~j4) << 7) & j4 & (-9187201950435737472L)) == -9187201950435737472L) {
                        if (i5 != length2) {
                            break;
                            break;
                        }
                        i5++;
                    } else {
                        int i6 = 8 - ((~(i5 - length2)) >>> 31);
                        for (int i7 = 0; i7 < i6; i7++) {
                            if ((j4 & 255) < 128) {
                                ((FocusEventModifierNode) objArr2[(i5 << 3) + i7]).onFocusEvent(FocusStateImpl.Inactive);
                            }
                            j4 >>= 8;
                        }
                        if (i6 != 8) {
                            break;
                        } else if (i5 != length2) {
                            break;
                        } else {
                            i5++;
                        }
                    }
                }
            }
        }
        invalidateOwnerFocusState();
        this.focusTargetNodes.clear();
        this.focusEventNodes.clear();
        this.isInvalidationScheduled = false;
    }

    private final void invalidateOwnerFocusState() {
        if (this.focusOwner.getActiveFocusTargetNode() == null || this.focusOwner.getRootState() == FocusStateImpl.Inactive) {
            this.focusOwner.clearOwnerFocus();
        }
    }
}
