package androidx.compose.ui.viewinterop;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewParent;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusModifierKt;
import androidx.compose.ui.focus.FocusOwner;
import androidx.compose.ui.node.DelegatableNodeKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: FocusGroupNode.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0000\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H\u0002\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u0002\u001a\"\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003H\u0002¨\u0006\u000e"}, d2 = {"focusInteropModifier", "Landroidx/compose/ui/Modifier;", "getEmbeddedView", "Landroid/view/View;", "Landroidx/compose/ui/Modifier$Node;", "containsDescendant", "", "other", "getCurrentlyFocusedRect", "Landroid/graphics/Rect;", "focusOwner", "Landroidx/compose/ui/focus/FocusOwner;", "hostView", "embeddedView", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class FocusGroupNode_androidKt {
    public static final Modifier focusInteropModifier(Modifier modifier) {
        return FocusModifierKt.focusTarget(FocusModifierKt.focusTarget(modifier.then(FocusGroupPropertiesElement.INSTANCE)).then(FocusTargetPropertiesElement.INSTANCE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final View getEmbeddedView(Modifier.Node node) {
        View interopView = DelegatableNodeKt.requireLayoutNode(node.getNode()).getInteropView();
        if (interopView != null) {
            return interopView;
        }
        throw new IllegalStateException("Could not fetch interop view".toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean containsDescendant(View view, View view2) {
        for (ViewParent parent = view2.getParent(); parent != null; parent = parent.getParent()) {
            if (parent == view.getParent()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Rect getCurrentlyFocusedRect(FocusOwner focusOwner, View view, View view2) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int[] iArr2 = new int[2];
        view2.getLocationOnScreen(iArr2);
        androidx.compose.ui.geometry.Rect focusRect = focusOwner.getFocusRect();
        if (focusRect == null) {
            return null;
        }
        return new Rect((((int) focusRect.getLeft()) + iArr[0]) - iArr2[0], (((int) focusRect.getTop()) + iArr[1]) - iArr2[1], (((int) focusRect.getRight()) + iArr[0]) - iArr2[0], (((int) focusRect.getBottom()) + iArr[1]) - iArr2[1]);
    }
}
