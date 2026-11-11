package androidx.compose.ui.draganddrop;

import androidx.compose.ui.Modifier;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: DragAndDropManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\u001f\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH&¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013H&J\u0010\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0015À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/draganddrop/DragAndDropManager;", "", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "isRequestDragAndDropTransferRequired", "", "()Z", "requestDragAndDropTransfer", "", "node", "Landroidx/compose/ui/draganddrop/DragAndDropNode;", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/ui/geometry/Offset;", "requestDragAndDropTransfer-Uv8p0NA", "(Landroidx/compose/ui/draganddrop/DragAndDropNode;J)V", "registerTargetInterest", TypedValues.AttributesType.S_TARGET, "Landroidx/compose/ui/draganddrop/DragAndDropTarget;", "isInterestedTarget", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface DragAndDropManager {
    Modifier getModifier();

    boolean isInterestedTarget(DragAndDropTarget target);

    boolean isRequestDragAndDropTransferRequired();

    void registerTargetInterest(DragAndDropTarget target);

    /* JADX INFO: renamed from: requestDragAndDropTransfer-Uv8p0NA */
    void mo3623requestDragAndDropTransferUv8p0NA(DragAndDropNode node, long offset);
}
