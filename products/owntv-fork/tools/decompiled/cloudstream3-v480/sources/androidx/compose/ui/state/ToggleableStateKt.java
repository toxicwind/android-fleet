package androidx.compose.ui.state;

import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: ToggleableState.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"ToggleableState", "Landroidx/compose/ui/state/ToggleableState;", ES6Iterator.VALUE_PROPERTY, "", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ToggleableStateKt {
    public static final ToggleableState ToggleableState(boolean z) {
        return z ? ToggleableState.On : ToggleableState.Off;
    }
}
