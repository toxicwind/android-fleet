package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;
import kotlin.Metadata;

/* JADX INFO: compiled from: LayoutBoundsHolder.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"layoutBounds", "Landroidx/compose/ui/Modifier;", "holder", "Landroidx/compose/ui/layout/LayoutBoundsHolder;", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LayoutBoundsHolderKt {
    public static final Modifier layoutBounds(Modifier modifier, LayoutBoundsHolder layoutBoundsHolder) {
        return modifier.then(new LayoutBoundsElement(layoutBoundsHolder));
    }
}
