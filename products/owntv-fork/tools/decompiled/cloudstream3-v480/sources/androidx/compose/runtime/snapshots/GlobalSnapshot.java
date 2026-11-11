package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.snapshots.tooling.SnapshotInstanceObservers;
import androidx.compose.runtime.snapshots.tooling.SnapshotObserver;
import androidx.compose.runtime.snapshots.tooling.SnapshotObserverKt;
import java.util.List;
import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: Snapshot.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0001\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u001b\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001e\u0010\t\u001a\u00020\n2\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\fH\u0016J4\u0010\u000f\u001a\u00020\u00012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\f2\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\fH\u0016J\r\u0010\u0011\u001a\u00020\u000eH\u0010¢\u0006\u0002\b\u0012J\u0015\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nH\u0010¢\u0006\u0002\b\u0016J\u0015\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nH\u0010¢\u0006\u0002\b\u0018J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u000eH\u0016¨\u0006\u001c"}, d2 = {"Landroidx/compose/runtime/snapshots/GlobalSnapshot;", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "snapshotId", "", "Landroidx/compose/runtime/snapshots/SnapshotId;", "invalid", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "<init>", "(JLandroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "takeNestedSnapshot", "Landroidx/compose/runtime/snapshots/Snapshot;", "readObserver", "Lkotlin/Function1;", "", "", "takeNestedMutableSnapshot", "writeObserver", "notifyObjectsInitialized", "notifyObjectsInitialized$runtime", "nestedDeactivated", "", "snapshot", "nestedDeactivated$runtime", "nestedActivated", "nestedActivated$runtime", "apply", "Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "dispose", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class GlobalSnapshot extends MutableSnapshot {
    public static final int $stable = 8;

    public GlobalSnapshot(long j, SnapshotIdSet snapshotIdSet) {
        super(j, snapshotIdSet, null, new Function1() { // from class: androidx.compose.runtime.snapshots.GlobalSnapshot$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return GlobalSnapshot._init_$lambda$2(obj);
            }
        });
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public void notifyObjectsInitialized$runtime() {
        SnapshotKt.advanceGlobalSnapshot();
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedDeactivated$runtime, reason: merged with bridge method [inline-methods] */
    public Void mo3576nestedDeactivated$runtime(Snapshot snapshot) {
        SnapshotStateMapKt.unsupported();
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedActivated$runtime, reason: merged with bridge method [inline-methods] */
    public Void mo3575nestedActivated$runtime(Snapshot snapshot) {
        SnapshotStateMapKt.unsupported();
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot
    public SnapshotApplyResult apply() {
        throw new IllegalStateException("Cannot apply the global snapshot directly. Call Snapshot.advanceGlobalSnapshot".toString());
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public Snapshot takeNestedSnapshot(final Function1<Object, Unit> readObserver) {
        Map<SnapshotObserver, SnapshotInstanceObservers> second;
        PersistentList persistentList = SnapshotObserverKt.observers;
        if (persistentList != null) {
            Pair<SnapshotInstanceObservers, Map<SnapshotObserver, SnapshotInstanceObservers>> pairMergeObservers = SnapshotObserverKt.mergeObservers(persistentList, null, true, readObserver, null);
            SnapshotInstanceObservers first = pairMergeObservers.getFirst();
            Function1<Object, Unit> readObserver2 = first.getReadObserver();
            first.getWriteObserver();
            second = pairMergeObservers.getSecond();
            readObserver = readObserver2;
        } else {
            second = null;
        }
        ReadonlySnapshot readonlySnapshot = (ReadonlySnapshot) SnapshotKt.takeNewSnapshot(new Function1<SnapshotIdSet, ReadonlySnapshot>() { // from class: androidx.compose.runtime.snapshots.GlobalSnapshot$takeNestedSnapshot$1$1
            @Override // kotlin.jvm.functions.Function1
            public final ReadonlySnapshot invoke(SnapshotIdSet snapshotIdSet) {
                long j;
                synchronized (SnapshotKt.getLock()) {
                    j = SnapshotKt.nextSnapshotId;
                    SnapshotKt.nextSnapshotId++;
                }
                return new ReadonlySnapshot(j, snapshotIdSet, readObserver);
            }
        });
        if (persistentList != null) {
            SnapshotObserverKt.dispatchCreatedObservers(persistentList, null, readonlySnapshot, second);
        }
        return readonlySnapshot;
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot
    public MutableSnapshot takeNestedMutableSnapshot(final Function1<Object, Unit> readObserver, Function1<Object, Unit> writeObserver) {
        final Function1<Object, Unit> function1;
        Map<SnapshotObserver, SnapshotInstanceObservers> second;
        PersistentList persistentList = SnapshotObserverKt.observers;
        if (persistentList != null) {
            Pair<SnapshotInstanceObservers, Map<SnapshotObserver, SnapshotInstanceObservers>> pairMergeObservers = SnapshotObserverKt.mergeObservers(persistentList, null, false, readObserver, writeObserver);
            SnapshotInstanceObservers first = pairMergeObservers.getFirst();
            Function1<Object, Unit> readObserver2 = first.getReadObserver();
            Function1<Object, Unit> writeObserver2 = first.getWriteObserver();
            second = pairMergeObservers.getSecond();
            readObserver = readObserver2;
            function1 = writeObserver2;
        } else {
            function1 = writeObserver;
            second = null;
        }
        MutableSnapshot mutableSnapshot = (MutableSnapshot) SnapshotKt.takeNewSnapshot(new Function1<SnapshotIdSet, MutableSnapshot>() { // from class: androidx.compose.runtime.snapshots.GlobalSnapshot$takeNestedMutableSnapshot$1$1
            @Override // kotlin.jvm.functions.Function1
            public final MutableSnapshot invoke(SnapshotIdSet snapshotIdSet) {
                long j;
                synchronized (SnapshotKt.getLock()) {
                    j = SnapshotKt.nextSnapshotId;
                    SnapshotKt.nextSnapshotId++;
                }
                return new MutableSnapshot(j, snapshotIdSet, readObserver, function1);
            }
        });
        if (persistentList != null) {
            SnapshotObserverKt.dispatchCreatedObservers(persistentList, null, mutableSnapshot, second);
        }
        return mutableSnapshot;
    }

    @Override // androidx.compose.runtime.snapshots.MutableSnapshot, androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        synchronized (SnapshotKt.getLock()) {
            releasePinnedSnapshotLocked$runtime();
            Unit unit = Unit.INSTANCE;
        }
    }

    static final Unit _init_$lambda$2(Object obj) {
        synchronized (SnapshotKt.getLock()) {
            List list = SnapshotKt.globalWriteObservers;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ((Function1) list.get(i)).invoke(obj);
            }
            Unit unit = Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
