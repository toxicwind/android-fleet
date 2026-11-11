package androidx.compose.ui.node;

import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import kotlin.Metadata;

/* JADX INFO: compiled from: DrawModifierNode.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0003H\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/node/DelegatableNode;", "draw", "", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "onMeasureResultChanged", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface DrawModifierNode extends DelegatableNode {

    /* JADX INFO: renamed from: androidx.compose.ui.node.DrawModifierNode$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: DrawModifierNode.kt */
    public final /* synthetic */ class CC {
        public static void $default$onMeasureResultChanged(DrawModifierNode _this) {
        }
    }

    void draw(ContentDrawScope contentDrawScope);

    void onMeasureResultChanged();
}
