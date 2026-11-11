package androidx.compose.ui.input.pointer;

import androidx.compose.ui.node.HitTestResult;
import androidx.compose.ui.node.LayoutNode;
import kotlin.Metadata;

/* JADX INFO: compiled from: PointerInputEventProcessor.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J'\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u000f¢\u0006\u0004\b\u0017\u0010\u0018J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputEventProcessor;", "", "root", "Landroidx/compose/ui/node/LayoutNode;", "<init>", "(Landroidx/compose/ui/node/LayoutNode;)V", "getRoot", "()Landroidx/compose/ui/node/LayoutNode;", "hitPathTracker", "Landroidx/compose/ui/input/pointer/HitPathTracker;", "pointerInputChangeEventProducer", "Landroidx/compose/ui/input/pointer/PointerInputChangeEventProducer;", "hitResult", "Landroidx/compose/ui/node/HitTestResult;", "isProcessing", "", "process", "Landroidx/compose/ui/input/pointer/ProcessResult;", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerInputEvent;", "positionCalculator", "Landroidx/compose/ui/input/pointer/PositionCalculator;", "isInBounds", "process-BIzXfog", "(Landroidx/compose/ui/input/pointer/PointerInputEvent;Landroidx/compose/ui/input/pointer/PositionCalculator;Z)I", "processCancel", "", "clearPreviouslyHitModifierNodes", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PointerInputEventProcessor {
    public static final int $stable = 8;
    private final HitPathTracker hitPathTracker;
    private boolean isProcessing;
    private final LayoutNode root;
    private final PointerInputChangeEventProducer pointerInputChangeEventProducer = new PointerInputChangeEventProducer();
    private final HitTestResult hitResult = new HitTestResult();

    public PointerInputEventProcessor(LayoutNode layoutNode) {
        this.root = layoutNode;
        this.hitPathTracker = new HitPathTracker(layoutNode.getCoordinates());
    }

    public final LayoutNode getRoot() {
        return this.root;
    }

    /* JADX INFO: renamed from: process-BIzXfog$default, reason: not valid java name */
    public static /* synthetic */ int m5471processBIzXfog$default(PointerInputEventProcessor pointerInputEventProcessor, PointerInputEvent pointerInputEvent, PositionCalculator positionCalculator, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return pointerInputEventProcessor.m5472processBIzXfog(pointerInputEvent, positionCalculator, z);
    }

    /* JADX INFO: renamed from: process-BIzXfog, reason: not valid java name */
    public final int m5472processBIzXfog(PointerInputEvent pointerEvent, PositionCalculator positionCalculator, boolean isInBounds) {
        int i;
        boolean z;
        boolean z2;
        if (this.isProcessing) {
            return PointerInputEventProcessorKt.ProcessResult(false, false, false);
        }
        boolean z3 = true;
        try {
            this.isProcessing = true;
            InternalPointerEvent internalPointerEventProduce = this.pointerInputChangeEventProducer.produce(pointerEvent, positionCalculator);
            int size = internalPointerEventProduce.getChanges().size();
            while (true) {
                if (i >= size) {
                    z = true;
                    break;
                }
                PointerInputChange pointerInputChangeValueAt = internalPointerEventProduce.getChanges().valueAt(i);
                i = (pointerInputChangeValueAt.getPressed() || pointerInputChangeValueAt.getPreviousPressed()) ? 0 : i + 1;
                z = false;
                break;
            }
            int size2 = internalPointerEventProduce.getChanges().size();
            for (int i2 = 0; i2 < size2; i2++) {
                PointerInputChange pointerInputChangeValueAt2 = internalPointerEventProduce.getChanges().valueAt(i2);
                if (z || PointerEventKt.changedToDownIgnoreConsumed(pointerInputChangeValueAt2)) {
                    LayoutNode.m5783hitTest6fMxITs$ui_release$default(this.root, pointerInputChangeValueAt2.getPosition(), this.hitResult, pointerInputChangeValueAt2.getType(), false, 8, null);
                    if (!this.hitResult.isEmpty()) {
                        this.hitPathTracker.m5377addHitPathQJqDSyo(pointerInputChangeValueAt2.getId(), this.hitResult, PointerEventKt.changedToDownIgnoreConsumed(pointerInputChangeValueAt2));
                        this.hitResult.clear();
                    }
                }
            }
            boolean zDispatchChanges = this.hitPathTracker.dispatchChanges(internalPointerEventProduce, isInBounds);
            if (internalPointerEventProduce.getSuppressMovementConsumption()) {
                z2 = false;
                break;
            }
            int size3 = internalPointerEventProduce.getChanges().size();
            int i3 = 0;
            while (true) {
                if (i3 >= size3) {
                    z2 = false;
                    break;
                }
                PointerInputChange pointerInputChangeValueAt3 = internalPointerEventProduce.getChanges().valueAt(i3);
                if (PointerEventKt.positionChangedIgnoreConsumed(pointerInputChangeValueAt3) && pointerInputChangeValueAt3.isConsumed()) {
                    z2 = true;
                    break;
                }
                i3++;
            }
            int size4 = internalPointerEventProduce.getChanges().size();
            int i4 = 0;
            while (true) {
                if (i4 >= size4) {
                    z3 = false;
                    break;
                }
                if (internalPointerEventProduce.getChanges().valueAt(i4).isConsumed()) {
                    break;
                }
                i4++;
            }
            return PointerInputEventProcessorKt.ProcessResult(zDispatchChanges, z2, z3);
        } finally {
            this.isProcessing = false;
        }
    }

    public final void processCancel() {
        if (this.isProcessing) {
            return;
        }
        this.pointerInputChangeEventProducer.clear();
        this.hitPathTracker.processCancel();
    }

    public final void clearPreviouslyHitModifierNodes() {
        this.hitPathTracker.clearPreviouslyHitModifierNodeCache();
    }
}
