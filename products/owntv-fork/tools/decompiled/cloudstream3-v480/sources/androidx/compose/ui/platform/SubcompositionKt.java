package androidx.compose.ui.platform;

import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionKt;
import androidx.compose.runtime.PausableComposition;
import androidx.compose.runtime.PausableCompositionKt;
import androidx.compose.runtime.ReusableComposition;
import androidx.compose.ui.node.LayoutNode;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: compiled from: Subcomposition.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\b"}, d2 = {"createSubcomposition", "Landroidx/compose/runtime/ReusableComposition;", TtmlNode.RUBY_CONTAINER, "Landroidx/compose/ui/node/LayoutNode;", "parent", "Landroidx/compose/runtime/CompositionContext;", "createPausableSubcomposition", "Landroidx/compose/runtime/PausableComposition;", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SubcompositionKt {
    public static final ReusableComposition createSubcomposition(LayoutNode layoutNode, CompositionContext compositionContext) {
        return CompositionKt.ReusableComposition(Wrapper_androidKt.createApplier(layoutNode), compositionContext);
    }

    public static final PausableComposition createPausableSubcomposition(LayoutNode layoutNode, CompositionContext compositionContext) {
        return PausableCompositionKt.PausableComposition(Wrapper_androidKt.createApplier(layoutNode), compositionContext);
    }
}
