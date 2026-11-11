package androidx.compose.ui.focus;

import androidx.compose.ui.node.DelegatableNode;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: compiled from: FocusTargetModifierNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H'J\u0019\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH&¢\u0006\u0004\b\n\u0010\u000bR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\f\u001a\u00020\rX¦\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011\u0082\u0001\u0001\u0012ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0013À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/focus/FocusTargetModifierNode;", "Landroidx/compose/ui/node/DelegatableNode;", "focusState", "Landroidx/compose/ui/focus/FocusState;", "getFocusState", "()Landroidx/compose/ui/focus/FocusState;", "requestFocus", "", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "requestFocus-3ESFkO8", "(I)Z", "focusability", "Landroidx/compose/ui/focus/Focusability;", "getFocusability-LCbbffg", "()I", "setFocusability-josRg5g", "(I)V", "Landroidx/compose/ui/focus/FocusTargetNode;", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface FocusTargetModifierNode extends DelegatableNode {
    FocusState getFocusState();

    /* JADX INFO: renamed from: getFocusability-LCbbffg, reason: not valid java name */
    int mo3779getFocusabilityLCbbffg();

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the version accepting FocusDirection", replaceWith = @ReplaceWith(expression = "this.requestFocus()", imports = {}))
    /* synthetic */ boolean requestFocus();

    /* JADX INFO: renamed from: requestFocus-3ESFkO8, reason: not valid java name */
    boolean mo3780requestFocus3ESFkO8(int focusDirection);

    /* JADX INFO: renamed from: setFocusability-josRg5g, reason: not valid java name */
    void mo3781setFocusabilityjosRg5g(int i);

    /* JADX INFO: renamed from: androidx.compose.ui.focus.FocusTargetModifierNode$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: FocusTargetModifierNode.kt */
    public final /* synthetic */ class CC {
        /* JADX INFO: renamed from: requestFocus-3ESFkO8$default, reason: not valid java name */
        public static /* synthetic */ boolean m3782requestFocus3ESFkO8$default(FocusTargetModifierNode focusTargetModifierNode, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: requestFocus-3ESFkO8");
            }
            if ((i2 & 1) != 0) {
                i = FocusDirection.INSTANCE.m3754getEnterdhqQ8s();
            }
            return focusTargetModifierNode.mo3780requestFocus3ESFkO8(i);
        }
    }
}
