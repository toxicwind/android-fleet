package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.external.kotlinx.collections.immutable.ExtensionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SnapshotStateList.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\u001aC\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001d\u0010\u0004\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0004\u0012\u0002H\u00010\u0005¢\u0006\u0002\b\u0007H\u0080\b¢\u0006\u0002\u0010\b\u001aC\u0010\t\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001d\u0010\u0004\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0004\u0012\u0002H\u00010\u0005¢\u0006\u0002\b\u0007H\u0080\b¢\u0006\u0002\u0010\b\u001a2\u0010\n\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\f\u0012\u0004\u0012\u00020\u000b0\u0005H\u0000\u001a>\u0010\r\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\f\u0012\u0004\u0012\u0002H\u00010\u0005H\u0080\b¢\u0006\u0002\u0010\b\u001aC\u0010\u000e\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u000b2\u001e\u0010\u0004\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00110\u0005H\u0080\b\u001a\u0019\u0010\u0012\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0080\b\u001aC\u0010\u0013\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u000b2\u001e\u0010\u0004\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00110\u0005H\u0080\b\u001a6\u0010\u0014\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000bH\u0000\u001a&\u0010\u0018\u001a\u00020\u0019\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0011H\u0000\u001a=\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u00022\u0006\u0010$\u001a\u00020\u00162!\u0010%\u001a\u001d\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0004\u0012\u0002H\u00020\u0005\u001a\b\u0010-\u001a\u00020.H\u0002\u001a\u0018\u0010/\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\u0016H\u0002\u001a\b\u00100\u001a\u00020.H\u0002\"$\u0010\u001b\u001a\u00020\u0016\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d\"0\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00038@X\u0080\u0004¢\u0006\f\u0012\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0014\u0010)\u001a\u00060*j\u0002`+X\u0082\u0004¢\u0006\u0004\n\u0002\u0010,¨\u00061"}, d2 = {"writable", "R", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "block", "Lkotlin/Function1;", "Landroidx/compose/runtime/snapshots/StateListStateRecord;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/runtime/snapshots/SnapshotStateList;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "withCurrent", "mutateBoolean", "", "", "mutate", "update", "", "structural", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "clearImpl", "conditionalUpdate", "attemptUpdate", "currentModification", "", "newList", "stateRecordWith", "Landroidx/compose/runtime/snapshots/StateRecord;", "list", "structure", "getStructure", "(Landroidx/compose/runtime/snapshots/SnapshotStateList;)I", "readable", "getReadable$annotations", "(Landroidx/compose/runtime/snapshots/SnapshotStateList;)V", "getReadable", "(Landroidx/compose/runtime/snapshots/SnapshotStateList;)Landroidx/compose/runtime/snapshots/StateListStateRecord;", "SnapshotStateList", ContentDisposition.Parameters.Size, "init", "Lkotlin/ParameterName;", "name", "index", "sync", "", "Landroidx/compose/runtime/platform/SynchronizedObject;", "Ljava/lang/Object;", "modificationError", "", "validateRange", "invalidIteratorSet", "runtime"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SnapshotStateListKt {
    private static final Object sync = new Object();

    public static /* synthetic */ void getReadable$annotations(SnapshotStateList snapshotStateList) {
    }

    public static final <R, T> R writable(SnapshotStateList<T> snapshotStateList, Function1<? super StateListStateRecord<T>, ? extends R> function1) {
        Snapshot current;
        R rInvoke;
        StateRecord firstStateRecord = snapshotStateList.getFirstStateRecord();
        Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>");
        StateListStateRecord stateListStateRecord = (StateListStateRecord) firstStateRecord;
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            rInvoke = function1.invoke(SnapshotKt.writableRecord(stateListStateRecord, snapshotStateList, current));
        }
        SnapshotKt.notifyWrite(current, snapshotStateList);
        return rInvoke;
    }

    public static final <R, T> R withCurrent(SnapshotStateList<T> snapshotStateList, Function1<? super StateListStateRecord<T>, ? extends R> function1) {
        StateRecord firstStateRecord = snapshotStateList.getFirstStateRecord();
        Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>");
        return function1.invoke(SnapshotKt.current((StateListStateRecord) firstStateRecord));
    }

    public static final <R, T> R mutate(SnapshotStateList<T> snapshotStateList, Function1<? super List<T>, ? extends R> function1) {
        int modification$runtime;
        PersistentList<T> list$runtime;
        R rInvoke;
        Snapshot current;
        boolean zAttemptUpdate;
        do {
            synchronized (sync) {
                StateRecord firstStateRecord = snapshotStateList.getFirstStateRecord();
                Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>");
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) firstStateRecord);
                modification$runtime = stateListStateRecord.getModification$runtime();
                list$runtime = stateListStateRecord.getList$runtime();
                Unit unit = Unit.INSTANCE;
            }
            Intrinsics.checkNotNull(list$runtime);
            PersistentList.Builder<T> builder = list$runtime.builder();
            rInvoke = function1.invoke(builder);
            PersistentList<T> persistentListBuild = builder.build();
            if (Intrinsics.areEqual(persistentListBuild, list$runtime)) {
                break;
            }
            StateRecord firstStateRecord2 = snapshotStateList.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>");
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) firstStateRecord2;
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                zAttemptUpdate = attemptUpdate((StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, snapshotStateList, current), modification$runtime, persistentListBuild, true);
            }
            SnapshotKt.notifyWrite(current, snapshotStateList);
        } while (!zAttemptUpdate);
        return rInvoke;
    }

    public static final <T> boolean conditionalUpdate(SnapshotStateList<T> snapshotStateList, boolean z, Function1<? super PersistentList<? extends T>, ? extends PersistentList<? extends T>> function1) {
        int modification$runtime;
        PersistentList<T> list$runtime;
        Snapshot current;
        boolean zAttemptUpdate;
        do {
            synchronized (sync) {
                StateRecord firstStateRecord = snapshotStateList.getFirstStateRecord();
                Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>");
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) firstStateRecord);
                modification$runtime = stateListStateRecord.getModification$runtime();
                list$runtime = stateListStateRecord.getList$runtime();
                Unit unit = Unit.INSTANCE;
            }
            Intrinsics.checkNotNull(list$runtime);
            PersistentList<? extends T> persistentListInvoke = function1.invoke(list$runtime);
            if (Intrinsics.areEqual(persistentListInvoke, list$runtime)) {
                return false;
            }
            StateRecord firstStateRecord2 = snapshotStateList.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>");
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) firstStateRecord2;
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                zAttemptUpdate = attemptUpdate((StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, snapshotStateList, current), modification$runtime, persistentListInvoke, z);
            }
            SnapshotKt.notifyWrite(current, snapshotStateList);
        } while (!zAttemptUpdate);
        return true;
    }

    public static /* synthetic */ boolean conditionalUpdate$default(SnapshotStateList snapshotStateList, boolean z, Function1 function1, int i, Object obj) {
        int modification$runtime;
        PersistentList list$runtime;
        Snapshot current;
        boolean zAttemptUpdate;
        if ((i & 1) != 0) {
            z = true;
        }
        do {
            synchronized (sync) {
                StateRecord firstStateRecord = snapshotStateList.getFirstStateRecord();
                Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>");
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) firstStateRecord);
                modification$runtime = stateListStateRecord.getModification$runtime();
                list$runtime = stateListStateRecord.getList$runtime();
                Unit unit = Unit.INSTANCE;
            }
            Intrinsics.checkNotNull(list$runtime);
            PersistentList persistentList = (PersistentList) function1.invoke(list$runtime);
            if (Intrinsics.areEqual(persistentList, list$runtime)) {
                return false;
            }
            StateRecord firstStateRecord2 = snapshotStateList.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>");
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) firstStateRecord2;
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                zAttemptUpdate = attemptUpdate((StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, snapshotStateList, current), modification$runtime, persistentList, z);
            }
            SnapshotKt.notifyWrite(current, snapshotStateList);
        } while (!zAttemptUpdate);
        return true;
    }

    public static final <T> boolean attemptUpdate(StateListStateRecord<T> stateListStateRecord, int i, PersistentList<? extends T> persistentList, boolean z) {
        boolean z2;
        synchronized (sync) {
            if (stateListStateRecord.getModification$runtime() == i) {
                stateListStateRecord.setList$runtime(persistentList);
                z2 = true;
                if (z) {
                    stateListStateRecord.setStructuralChange$runtime(stateListStateRecord.getStructuralChange$runtime() + 1);
                }
                stateListStateRecord.setModification$runtime(stateListStateRecord.getModification$runtime() + 1);
            } else {
                z2 = false;
            }
        }
        return z2;
    }

    public static final <T> StateRecord stateRecordWith(SnapshotStateList<T> snapshotStateList, PersistentList<? extends T> persistentList) {
        Snapshot snapshotCurrentSnapshot = SnapshotKt.currentSnapshot();
        StateListStateRecord stateListStateRecord = new StateListStateRecord(snapshotCurrentSnapshot.getSnapshotId(), persistentList);
        if (!(snapshotCurrentSnapshot instanceof GlobalSnapshot)) {
            stateListStateRecord.setNext$runtime(new StateListStateRecord(SnapshotId_jvmKt.toSnapshotId(1), persistentList));
        }
        return stateListStateRecord;
    }

    public static final <T> StateListStateRecord<T> getReadable(SnapshotStateList<T> snapshotStateList) {
        StateRecord firstStateRecord = snapshotStateList.getFirstStateRecord();
        Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.<get-readable>>");
        return (StateListStateRecord) SnapshotKt.readable((StateListStateRecord) firstStateRecord, snapshotStateList);
    }

    public static final <T> SnapshotStateList<T> SnapshotStateList(int i, Function1<? super Integer, ? extends T> function1) {
        if (i == 0) {
            return new SnapshotStateList<>();
        }
        PersistentList.Builder builder = ExtensionsKt.persistentListOf().builder();
        for (int i2 = 0; i2 < i; i2++) {
            builder.add(function1.invoke(Integer.valueOf(i2)));
        }
        return new SnapshotStateList<>(builder.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void modificationError() {
        throw new IllegalStateException("Cannot modify a state list through an iterator".toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void validateRange(int i, int i2) {
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException("index (" + i + ") is out of bound of [0, " + i2 + ')');
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void invalidIteratorSet() {
        throw new IllegalStateException("Cannot call set before the first call to next() or previous() or immediately after a call to add() or remove()".toString());
    }

    public static final <T> boolean mutateBoolean(SnapshotStateList<T> snapshotStateList, Function1<? super List<T>, Boolean> function1) {
        int modification$runtime;
        PersistentList<T> list$runtime;
        Boolean boolInvoke;
        Snapshot current;
        boolean zAttemptUpdate;
        do {
            synchronized (sync) {
                StateRecord firstStateRecord = snapshotStateList.getFirstStateRecord();
                Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>");
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) firstStateRecord);
                modification$runtime = stateListStateRecord.getModification$runtime();
                list$runtime = stateListStateRecord.getList$runtime();
                Unit unit = Unit.INSTANCE;
            }
            Intrinsics.checkNotNull(list$runtime);
            PersistentList.Builder<T> builder = list$runtime.builder();
            boolInvoke = function1.invoke(builder);
            PersistentList<T> persistentListBuild = builder.build();
            if (Intrinsics.areEqual(persistentListBuild, list$runtime)) {
                break;
            }
            StateRecord firstStateRecord2 = snapshotStateList.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>");
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) firstStateRecord2;
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                zAttemptUpdate = attemptUpdate((StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, snapshotStateList, current), modification$runtime, persistentListBuild, true);
            }
            SnapshotKt.notifyWrite(current, snapshotStateList);
        } while (!zAttemptUpdate);
        return boolInvoke.booleanValue();
    }

    public static final <T> void update(SnapshotStateList<T> snapshotStateList, boolean z, Function1<? super PersistentList<? extends T>, ? extends PersistentList<? extends T>> function1) {
        int modification$runtime;
        PersistentList<T> list$runtime;
        Snapshot current;
        boolean zAttemptUpdate;
        do {
            synchronized (sync) {
                StateRecord firstStateRecord = snapshotStateList.getFirstStateRecord();
                Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>");
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) firstStateRecord);
                modification$runtime = stateListStateRecord.getModification$runtime();
                list$runtime = stateListStateRecord.getList$runtime();
                Unit unit = Unit.INSTANCE;
            }
            Intrinsics.checkNotNull(list$runtime);
            PersistentList<? extends T> persistentListInvoke = function1.invoke(list$runtime);
            if (Intrinsics.areEqual(persistentListInvoke, list$runtime)) {
                return;
            }
            StateRecord firstStateRecord2 = snapshotStateList.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>");
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) firstStateRecord2;
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                zAttemptUpdate = attemptUpdate((StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, snapshotStateList, current), modification$runtime, persistentListInvoke, z);
            }
            SnapshotKt.notifyWrite(current, snapshotStateList);
        } while (!zAttemptUpdate);
    }

    public static /* synthetic */ void update$default(SnapshotStateList snapshotStateList, boolean z, Function1 function1, int i, Object obj) {
        int modification$runtime;
        PersistentList list$runtime;
        Snapshot current;
        boolean zAttemptUpdate;
        if ((i & 1) != 0) {
            z = true;
        }
        do {
            synchronized (sync) {
                StateRecord firstStateRecord = snapshotStateList.getFirstStateRecord();
                Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>");
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current((StateListStateRecord) firstStateRecord);
                modification$runtime = stateListStateRecord.getModification$runtime();
                list$runtime = stateListStateRecord.getList$runtime();
                Unit unit = Unit.INSTANCE;
            }
            Intrinsics.checkNotNull(list$runtime);
            PersistentList persistentList = (PersistentList) function1.invoke(list$runtime);
            if (Intrinsics.areEqual(persistentList, list$runtime)) {
                return;
            }
            StateRecord firstStateRecord2 = snapshotStateList.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>");
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) firstStateRecord2;
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                zAttemptUpdate = attemptUpdate((StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, snapshotStateList, current), modification$runtime, persistentList, z);
            }
            SnapshotKt.notifyWrite(current, snapshotStateList);
        } while (!zAttemptUpdate);
    }

    public static final <T> void clearImpl(SnapshotStateList<T> snapshotStateList) {
        Snapshot current;
        StateRecord firstStateRecord = snapshotStateList.getFirstStateRecord();
        Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.writable>");
        StateListStateRecord stateListStateRecord = (StateListStateRecord) firstStateRecord;
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord, snapshotStateList, current);
            synchronized (sync) {
                stateListStateRecord2.setList$runtime(ExtensionsKt.persistentListOf());
                stateListStateRecord2.setModification$runtime(stateListStateRecord2.getModification$runtime() + 1);
                stateListStateRecord2.setStructuralChange$runtime(stateListStateRecord2.getStructuralChange$runtime() + 1);
            }
        }
        SnapshotKt.notifyWrite(current, snapshotStateList);
    }

    public static final <T> int getStructure(SnapshotStateList<T> snapshotStateList) {
        StateRecord firstStateRecord = snapshotStateList.getFirstStateRecord();
        Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateListStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateListKt.withCurrent>");
        return ((StateListStateRecord) SnapshotKt.current((StateListStateRecord) firstStateRecord)).getStructuralChange$runtime();
    }
}
