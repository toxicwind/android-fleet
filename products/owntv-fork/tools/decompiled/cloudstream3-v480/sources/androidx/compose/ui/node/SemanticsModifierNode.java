package androidx.compose.ui.node;

import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Metadata;

/* JADX INFO: compiled from: SemanticsModifierNode.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\f\u0010\t\u001a\u00020\n*\u00020\u000bH&R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0014\u0010\b\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/ui/node/DelegatableNode;", "shouldClearDescendantSemantics", "", "getShouldClearDescendantSemantics", "()Z", "shouldMergeDescendantSemantics", "getShouldMergeDescendantSemantics", "isImportantForBounds", "applySemantics", "", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface SemanticsModifierNode extends DelegatableNode {

    /* JADX INFO: renamed from: androidx.compose.ui.node.SemanticsModifierNode$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: SemanticsModifierNode.kt */
    public final /* synthetic */ class CC {
        public static boolean $default$getShouldClearDescendantSemantics(SemanticsModifierNode _this) {
            return false;
        }

        public static boolean $default$getShouldMergeDescendantSemantics(SemanticsModifierNode _this) {
            return false;
        }

        public static boolean $default$isImportantForBounds(SemanticsModifierNode _this) {
            return true;
        }
    }

    void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver);

    boolean getShouldClearDescendantSemantics();

    boolean getShouldMergeDescendantSemantics();

    boolean isImportantForBounds();
}
