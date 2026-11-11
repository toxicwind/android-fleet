package androidx.compose.runtime.changelist;

import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SlotWriter;
import androidx.compose.runtime.tooling.ComposeStackTraceBuilderKt;
import androidx.compose.runtime.tooling.ComposeStackTraceFrame;
import androidx.compose.runtime.tooling.ComposeStackTraceKt;
import io.ktor.http.LinkHeader;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: Operation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u001a(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010\t\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\n\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a(\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0002\u001a3\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014H\u0082\b\u001a(\u0010\u0015\u001a\u00020\u0016*\u00020\u00162\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002\u001a\u0014\u0010\u000e\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u0005H\u0002*\f\b\u0000\u0010\u0000\"\u00020\u00012\u00020\u0001¨\u0006\u0017"}, d2 = {"IntParameter", "", "positionToParentOf", "", "slots", "Landroidx/compose/runtime/SlotWriter;", "applier", "Landroidx/compose/runtime/Applier;", "", "index", "currentNodeIndex", "positionToInsert", LinkHeader.Parameters.Anchor, "Landroidx/compose/runtime/Anchor;", "withCurrentStackTrace", "errorContext", "Landroidx/compose/runtime/changelist/OperationErrorContext;", "writer", "location", "block", "Lkotlin/Function0;", "attachComposeStackTrace", "", "runtime"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class OperationKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void positionToParentOf(SlotWriter slotWriter, Applier<Object> applier, int i) {
        while (!slotWriter.indexInParent(i)) {
            slotWriter.skipToGroupEnd();
            if (slotWriter.isNode(slotWriter.getParent())) {
                applier.up();
            }
            slotWriter.endGroup();
        }
    }

    private static final int currentNodeIndex(SlotWriter slotWriter) {
        int currentGroup = slotWriter.getCurrentGroup();
        int parent = slotWriter.getParent();
        while (parent >= 0 && !slotWriter.isNode(parent)) {
            parent = slotWriter.parent(parent);
        }
        int iGroupSize = parent + 1;
        int iNodeCount = 0;
        while (iGroupSize < currentGroup) {
            if (slotWriter.indexInGroup(currentGroup, iGroupSize)) {
                if (slotWriter.isNode(iGroupSize)) {
                    iNodeCount = 0;
                }
                iGroupSize++;
            } else {
                iNodeCount += slotWriter.isNode(iGroupSize) ? 1 : slotWriter.nodeCount(iGroupSize);
                iGroupSize += slotWriter.groupSize(iGroupSize);
            }
        }
        return iNodeCount;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int positionToInsert(SlotWriter slotWriter, Anchor anchor, Applier<Object> applier) {
        int iAnchorIndex = slotWriter.anchorIndex(anchor);
        if (!(slotWriter.getCurrentGroup() < iAnchorIndex)) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        positionToParentOf(slotWriter, applier, iAnchorIndex);
        int iCurrentNodeIndex = currentNodeIndex(slotWriter);
        while (slotWriter.getCurrentGroup() < iAnchorIndex) {
            if (slotWriter.indexInCurrentGroup(iAnchorIndex)) {
                if (slotWriter.isNode()) {
                    applier.down(slotWriter.node(slotWriter.getCurrentGroup()));
                    iCurrentNodeIndex = 0;
                }
                slotWriter.startGroup();
            } else {
                iCurrentNodeIndex += slotWriter.skipGroup();
            }
        }
        if (!(slotWriter.getCurrentGroup() == iAnchorIndex)) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        return iCurrentNodeIndex;
    }

    private static final void withCurrentStackTrace(OperationErrorContext operationErrorContext, SlotWriter slotWriter, Anchor anchor, Function0<Unit> function0) throws Throwable {
        try {
            function0.invoke();
        } catch (Throwable th) {
            throw attachComposeStackTrace(th, operationErrorContext, slotWriter, anchor);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Throwable attachComposeStackTrace(Throwable th, final OperationErrorContext operationErrorContext, final SlotWriter slotWriter, final Anchor anchor) {
        return operationErrorContext == null ? th : ComposeStackTraceKt.attachComposeStackTrace(th, new Function0() { // from class: androidx.compose.runtime.changelist.OperationKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return OperationKt.attachComposeStackTrace$lambda$1(anchor, slotWriter, operationErrorContext);
            }
        });
    }

    static final List attachComposeStackTrace$lambda$1(Anchor anchor, SlotWriter slotWriter, OperationErrorContext operationErrorContext) {
        if (anchor != null) {
            slotWriter.seek(anchor);
        }
        List listBuildTrace$default = ComposeStackTraceBuilderKt.buildTrace$default(slotWriter, null, 0, null, 7, null);
        ComposeStackTraceFrame composeStackTraceFrame = (ComposeStackTraceFrame) CollectionsKt.lastOrNull(listBuildTrace$default);
        Integer groupOffset = composeStackTraceFrame != null ? composeStackTraceFrame.getGroupOffset() : null;
        List<ComposeStackTraceFrame> listBuildStackTrace = operationErrorContext.buildStackTrace(groupOffset);
        if (groupOffset != null && !listBuildStackTrace.isEmpty()) {
            listBuildStackTrace = CollectionsKt.plus((Collection) CollectionsKt.listOf(ComposeStackTraceFrame.copy$default((ComposeStackTraceFrame) CollectionsKt.first((List) listBuildStackTrace), null, groupOffset, 1, null)), (Iterable) CollectionsKt.drop(listBuildStackTrace, 1));
        }
        return CollectionsKt.plus((Collection) listBuildTrace$default, (Iterable) listBuildStackTrace);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final OperationErrorContext withCurrentStackTrace(final OperationErrorContext operationErrorContext, final SlotWriter slotWriter) {
        return new OperationErrorContext() { // from class: androidx.compose.runtime.changelist.OperationKt.withCurrentStackTrace.1
            @Override // androidx.compose.runtime.changelist.OperationErrorContext
            public List<ComposeStackTraceFrame> buildStackTrace(Integer currentOffset) {
                List<ComposeStackTraceFrame> listBuildStackTrace = operationErrorContext.buildStackTrace(null);
                int parent = slotWriter.getParent();
                if (parent < 0) {
                    return listBuildStackTrace;
                }
                SlotWriter slotWriter2 = slotWriter;
                return CollectionsKt.plus((Collection) ComposeStackTraceBuilderKt.buildTrace(slotWriter2, currentOffset, parent, Integer.valueOf(slotWriter2.parent(parent))), (Iterable) listBuildStackTrace);
            }
        };
    }
}
