package androidx.compose.runtime.snapshots;

import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Snapshot.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&J\"\u0010\t\u001a\u0004\u0018\u00010\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003H\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0003"}, d2 = {"Landroidx/compose/runtime/snapshots/StateObject;", "", "firstStateRecord", "Landroidx/compose/runtime/snapshots/StateRecord;", "getFirstStateRecord", "()Landroidx/compose/runtime/snapshots/StateRecord;", "prependStateRecord", "", ES6Iterator.VALUE_PROPERTY, "mergeRecords", "previous", "current", "applied", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface StateObject {
    StateRecord getFirstStateRecord();

    StateRecord mergeRecords(StateRecord previous, StateRecord current, StateRecord applied);

    void prependStateRecord(StateRecord value);

    /* JADX INFO: renamed from: androidx.compose.runtime.snapshots.StateObject$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Snapshot.kt */
    public final /* synthetic */ class CC {
        public static StateRecord $default$mergeRecords(StateObject _this, StateRecord stateRecord, StateRecord stateRecord2, StateRecord stateRecord3) {
            return null;
        }
    }

    /* JADX INFO: compiled from: Snapshot.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static StateRecord mergeRecords(StateObject stateObject, StateRecord stateRecord, StateRecord stateRecord2, StateRecord stateRecord3) {
            return CC.$default$mergeRecords(stateObject, stateRecord, stateRecord2, stateRecord3);
        }
    }
}
