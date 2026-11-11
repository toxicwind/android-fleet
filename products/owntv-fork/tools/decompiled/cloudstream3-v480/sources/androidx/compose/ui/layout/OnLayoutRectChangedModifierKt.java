package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeKt;
import androidx.compose.ui.spatial.RelativeLayoutBounds;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: OnLayoutRectChangedModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a4\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0007\u001a.\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¨\u0006\f"}, d2 = {"onLayoutRectChanged", "Landroidx/compose/ui/Modifier;", "throttleMillis", "", "debounceMillis", "callback", "Lkotlin/Function1;", "Landroidx/compose/ui/spatial/RelativeLayoutBounds;", "", "registerOnLayoutRectChanged", "Landroidx/compose/ui/node/DelegatableNode$RegistrationHandle;", "Landroidx/compose/ui/node/DelegatableNode;", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class OnLayoutRectChangedModifierKt {
    public static /* synthetic */ Modifier onLayoutRectChanged$default(Modifier modifier, long j, long j2, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 0;
        }
        long j3 = j;
        if ((i & 2) != 0) {
            j2 = 64;
        }
        return onLayoutRectChanged(modifier, j3, j2, function1);
    }

    public static final Modifier onLayoutRectChanged(Modifier modifier, long j, long j2, Function1<? super RelativeLayoutBounds, Unit> function1) {
        return modifier.then(new OnLayoutRectChangedElement(j, j2, function1));
    }

    public static final DelegatableNode.RegistrationHandle registerOnLayoutRectChanged(DelegatableNode delegatableNode, long j, long j2, Function1<? super RelativeLayoutBounds, Unit> function1) {
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(delegatableNode);
        return LayoutNodeKt.requireOwner(layoutNodeRequireLayoutNode).getRectManager().registerOnRectChangedCallback(layoutNodeRequireLayoutNode.getSemanticsId(), j, j2, delegatableNode, function1);
    }
}
