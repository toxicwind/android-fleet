package androidx.compose.ui.input.pointer;

import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: PointerIcon.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0003H&J\u0012\u0010\b\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerIconService;", "", "getIcon", "Landroidx/compose/ui/input/pointer/PointerIcon;", "setIcon", "", ES6Iterator.VALUE_PROPERTY, "getStylusHoverIcon", "setStylusHoverIcon", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface PointerIconService {
    PointerIcon getIcon();

    PointerIcon getStylusHoverIcon();

    void setIcon(PointerIcon value);

    void setStylusHoverIcon(PointerIcon value);
}
