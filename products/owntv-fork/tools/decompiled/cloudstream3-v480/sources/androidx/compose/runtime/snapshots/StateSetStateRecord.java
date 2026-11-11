package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SnapshotStateSet.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B#\b\u0000\u0012\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0002H\u0016J\b\u0010\u0017\u001a\u00020\u0002H\u0016J\u0019\u0010\u0017\u001a\u00020\u00022\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005H\u0016¢\u0006\u0002\u0010\u0018R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/snapshots/StateSetStateRecord;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/runtime/snapshots/StateRecord;", "snapshotId", "", "Landroidx/compose/runtime/snapshots/SnapshotId;", "set", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentSet;", "<init>", "(JLandroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentSet;)V", "getSet$runtime", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentSet;", "setSet$runtime", "(Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentSet;)V", "modification", "", "getModification$runtime", "()I", "setModification$runtime", "(I)V", "assign", "", ES6Iterator.VALUE_PROPERTY, "create", "(J)Landroidx/compose/runtime/snapshots/StateRecord;", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class StateSetStateRecord<T> extends StateRecord {
    public static final int $stable = 8;
    private int modification;
    private PersistentSet<? extends T> set;

    public final PersistentSet<T> getSet$runtime() {
        return this.set;
    }

    public final void setSet$runtime(PersistentSet<? extends T> persistentSet) {
        this.set = persistentSet;
    }

    public StateSetStateRecord(long j, PersistentSet<? extends T> persistentSet) {
        super(j);
        this.set = persistentSet;
    }

    /* JADX INFO: renamed from: getModification$runtime, reason: from getter */
    public final int getModification() {
        return this.modification;
    }

    public final void setModification$runtime(int i) {
        this.modification = i;
    }

    @Override // androidx.compose.runtime.snapshots.StateRecord
    public void assign(StateRecord value) {
        synchronized (SnapshotStateSetKt.sync) {
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.StateSetStateRecord>");
            this.set = ((StateSetStateRecord) value).set;
            this.modification = ((StateSetStateRecord) value).modification;
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.snapshots.StateRecord
    public StateRecord create() {
        return new StateSetStateRecord(SnapshotKt.currentSnapshot().getSnapshotId(), this.set);
    }

    @Override // androidx.compose.runtime.snapshots.StateRecord
    public StateRecord create(long snapshotId) {
        return new StateSetStateRecord(snapshotId, this.set);
    }
}
