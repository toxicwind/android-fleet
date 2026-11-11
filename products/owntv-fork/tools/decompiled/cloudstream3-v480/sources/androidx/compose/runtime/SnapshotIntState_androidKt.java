package androidx.compose.runtime;

import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SnapshotIntState.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, d2 = {"createSnapshotMutableIntState", "Landroidx/compose/runtime/MutableIntState;", ES6Iterator.VALUE_PROPERTY, "", "runtime"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SnapshotIntState_androidKt {
    public static final MutableIntState createSnapshotMutableIntState(int i) {
        return new ParcelableSnapshotMutableIntState(i);
    }
}
