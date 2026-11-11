package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.external.kotlinx.collections.immutable.ExtensionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet;
import androidx.exifinterface.media.ExifInterface;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SnapshotStateSet.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aC\u0010\f\u001a\u0002H\r\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001d\u0010\u000e\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0007\u0012\u0004\u0012\u0002H\r0\u000f¢\u0006\u0002\b\u0010H\u0080\b¢\u0006\u0002\u0010\u0011\u001aC\u0010\u0012\u001a\u0002H\r\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001d\u0010\u000e\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0007\u0012\u0004\u0012\u0002H\r0\u000f¢\u0006\u0002\b\u0010H\u0080\b¢\u0006\u0002\u0010\u0011\u001a2\u0010\u0013\u001a\u00020\u0014\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u000e\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0015\u0012\u0004\u0012\u00020\u00140\u000fH\u0000\u001a>\u0010\u0016\u001a\u0002H\r\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u000e\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0015\u0012\u0004\u0012\u0002H\r0\u000fH\u0080\b¢\u0006\u0002\u0010\u0011\u001a9\u0010\u0017\u001a\u00020\u0014\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001e\u0010\u000e\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00180\u000fH\u0080\b\u001a\u0019\u0010\u0019\u001a\u00020\u001a\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0080\b\u001a.\u0010\u001b\u001a\u00020\u0014\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u00012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0018H\u0000\u001a&\u0010\u001e\u001a\u00020\u001f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u00020\u0018H\u0000\"$\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"0\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00038@X\u0080\u0004¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0014\u0010!\u001a\u00060\"j\u0002`#X\u0082\u0004¢\u0006\u0004\n\u0002\u0010$¨\u0006%"}, d2 = {"modification", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/runtime/snapshots/SnapshotStateSet;", "getModification", "(Landroidx/compose/runtime/snapshots/SnapshotStateSet;)I", "readable", "Landroidx/compose/runtime/snapshots/StateSetStateRecord;", "getReadable$annotations", "(Landroidx/compose/runtime/snapshots/SnapshotStateSet;)V", "getReadable", "(Landroidx/compose/runtime/snapshots/SnapshotStateSet;)Landroidx/compose/runtime/snapshots/StateSetStateRecord;", "writable", "R", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/runtime/snapshots/SnapshotStateSet;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "withCurrent", "mutateBoolean", "", "", "mutate", "conditionalUpdate", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentSet;", "clearImpl", "", "attemptUpdate", "currentModification", "newSet", "stateRecordWith", "Landroidx/compose/runtime/snapshots/StateRecord;", "set", "sync", "", "Landroidx/compose/runtime/platform/SynchronizedObject;", "Ljava/lang/Object;", "runtime"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SnapshotStateSetKt {
    private static final Object sync = new Object();

    public static /* synthetic */ void getReadable$annotations(SnapshotStateSet snapshotStateSet) {
    }

    public static final <T> StateSetStateRecord<T> getReadable(SnapshotStateSet<T> snapshotStateSet) {
        StateRecord firstStateRecord = snapshotStateSet.getFirstStateRecord();
        Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.<get-readable>>");
        return (StateSetStateRecord) SnapshotKt.readable((StateSetStateRecord) firstStateRecord, snapshotStateSet);
    }

    public static final <R, T> R writable(SnapshotStateSet<T> snapshotStateSet, Function1<? super StateSetStateRecord<T>, ? extends R> function1) {
        Snapshot current;
        R rInvoke;
        StateRecord firstStateRecord = snapshotStateSet.getFirstStateRecord();
        Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.writable>");
        StateSetStateRecord stateSetStateRecord = (StateSetStateRecord) firstStateRecord;
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            rInvoke = function1.invoke(SnapshotKt.writableRecord(stateSetStateRecord, snapshotStateSet, current));
        }
        SnapshotKt.notifyWrite(current, snapshotStateSet);
        return rInvoke;
    }

    public static final <R, T> R withCurrent(SnapshotStateSet<T> snapshotStateSet, Function1<? super StateSetStateRecord<T>, ? extends R> function1) {
        StateRecord firstStateRecord = snapshotStateSet.getFirstStateRecord();
        Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.withCurrent>");
        return function1.invoke(SnapshotKt.current((StateSetStateRecord) firstStateRecord));
    }

    public static final <R, T> R mutate(SnapshotStateSet<T> snapshotStateSet, Function1<? super Set<T>, ? extends R> function1) {
        int modification$runtime;
        PersistentSet<T> set$runtime;
        PersistentSet.Builder<T> builder;
        R rInvoke;
        Snapshot current;
        boolean zAttemptUpdate;
        do {
            synchronized (sync) {
                StateRecord firstStateRecord = snapshotStateSet.getFirstStateRecord();
                Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.withCurrent>");
                StateSetStateRecord stateSetStateRecord = (StateSetStateRecord) SnapshotKt.current((StateSetStateRecord) firstStateRecord);
                modification$runtime = stateSetStateRecord.getModification$runtime();
                set$runtime = stateSetStateRecord.getSet$runtime();
                Unit unit = Unit.INSTANCE;
            }
            if (set$runtime == null || (builder = set$runtime.builder()) == null) {
                throw new IllegalStateException("No set to mutate".toString());
            }
            rInvoke = function1.invoke(builder);
            PersistentSet<T> persistentSetBuild = builder.build();
            if (Intrinsics.areEqual(persistentSetBuild, set$runtime)) {
                break;
            }
            StateRecord firstStateRecord2 = snapshotStateSet.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.writable>");
            StateSetStateRecord stateSetStateRecord2 = (StateSetStateRecord) firstStateRecord2;
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                zAttemptUpdate = attemptUpdate((StateSetStateRecord) SnapshotKt.writableRecord(stateSetStateRecord2, snapshotStateSet, current), modification$runtime, persistentSetBuild);
            }
            SnapshotKt.notifyWrite(current, snapshotStateSet);
        } while (!zAttemptUpdate);
        return rInvoke;
    }

    public static final <T> boolean conditionalUpdate(SnapshotStateSet<T> snapshotStateSet, Function1<? super PersistentSet<? extends T>, ? extends PersistentSet<? extends T>> function1) {
        int modification$runtime;
        PersistentSet<T> set$runtime;
        Snapshot current;
        boolean zAttemptUpdate;
        do {
            synchronized (sync) {
                StateRecord firstStateRecord = snapshotStateSet.getFirstStateRecord();
                Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.withCurrent>");
                StateSetStateRecord stateSetStateRecord = (StateSetStateRecord) SnapshotKt.current((StateSetStateRecord) firstStateRecord);
                modification$runtime = stateSetStateRecord.getModification$runtime();
                set$runtime = stateSetStateRecord.getSet$runtime();
                Unit unit = Unit.INSTANCE;
            }
            Intrinsics.checkNotNull(set$runtime);
            PersistentSet<? extends T> persistentSetInvoke = function1.invoke(set$runtime);
            if (Intrinsics.areEqual(persistentSetInvoke, set$runtime)) {
                return false;
            }
            StateRecord firstStateRecord2 = snapshotStateSet.getFirstStateRecord();
            Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.writable>");
            StateSetStateRecord stateSetStateRecord2 = (StateSetStateRecord) firstStateRecord2;
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                zAttemptUpdate = attemptUpdate((StateSetStateRecord) SnapshotKt.writableRecord(stateSetStateRecord2, snapshotStateSet, current), modification$runtime, persistentSetInvoke);
            }
            SnapshotKt.notifyWrite(current, snapshotStateSet);
        } while (!zAttemptUpdate);
        return true;
    }

    public static final <T> boolean attemptUpdate(StateSetStateRecord<T> stateSetStateRecord, int i, PersistentSet<? extends T> persistentSet) {
        boolean z;
        synchronized (sync) {
            if (stateSetStateRecord.getModification$runtime() == i) {
                stateSetStateRecord.setSet$runtime(persistentSet);
                z = true;
                stateSetStateRecord.setModification$runtime(stateSetStateRecord.getModification$runtime() + 1);
            } else {
                z = false;
            }
        }
        return z;
    }

    public static final <T> StateRecord stateRecordWith(SnapshotStateSet<T> snapshotStateSet, PersistentSet<? extends T> persistentSet) {
        StateSetStateRecord stateSetStateRecord = new StateSetStateRecord(SnapshotKt.currentSnapshot().getSnapshotId(), persistentSet);
        if (Snapshot.INSTANCE.isInSnapshot()) {
            stateSetStateRecord.setNext$runtime(new StateSetStateRecord(SnapshotId_jvmKt.toSnapshotId(1), persistentSet));
        }
        return stateSetStateRecord;
    }

    public static final <T> int getModification(SnapshotStateSet<T> snapshotStateSet) {
        StateRecord firstStateRecord = snapshotStateSet.getFirstStateRecord();
        Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.withCurrent>");
        return ((StateSetStateRecord) SnapshotKt.current((StateSetStateRecord) firstStateRecord)).getModification$runtime();
    }

    public static final <T> boolean mutateBoolean(SnapshotStateSet<T> snapshotStateSet, Function1<? super Set<T>, Boolean> function1) {
        int modification$runtime;
        PersistentSet<T> set$runtime;
        PersistentSet.Builder<T> builder;
        Boolean boolInvoke;
        Snapshot current;
        boolean zAttemptUpdate;
        do {
            synchronized (sync) {
                StateRecord firstStateRecord = snapshotStateSet.getFirstStateRecord();
                Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.withCurrent>");
                StateSetStateRecord stateSetStateRecord = (StateSetStateRecord) SnapshotKt.current((StateSetStateRecord) firstStateRecord);
                modification$runtime = stateSetStateRecord.getModification$runtime();
                set$runtime = stateSetStateRecord.getSet$runtime();
                Unit unit = Unit.INSTANCE;
            }
            if (set$runtime != null && (builder = set$runtime.builder()) != null) {
                boolInvoke = function1.invoke(builder);
                PersistentSet<T> persistentSetBuild = builder.build();
                if (Intrinsics.areEqual(persistentSetBuild, set$runtime)) {
                    break;
                }
                StateRecord firstStateRecord2 = snapshotStateSet.getFirstStateRecord();
                Intrinsics.checkNotNull(firstStateRecord2, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.writable>");
                StateSetStateRecord stateSetStateRecord2 = (StateSetStateRecord) firstStateRecord2;
                synchronized (SnapshotKt.getLock()) {
                    current = Snapshot.INSTANCE.getCurrent();
                    zAttemptUpdate = attemptUpdate((StateSetStateRecord) SnapshotKt.writableRecord(stateSetStateRecord2, snapshotStateSet, current), modification$runtime, persistentSetBuild);
                }
                SnapshotKt.notifyWrite(current, snapshotStateSet);
            } else {
                throw new IllegalStateException("No set to mutate".toString());
            }
        } while (!zAttemptUpdate);
        return boolInvoke.booleanValue();
    }

    public static final <T> void clearImpl(SnapshotStateSet<T> snapshotStateSet) {
        Snapshot current;
        StateRecord firstStateRecord = snapshotStateSet.getFirstStateRecord();
        Intrinsics.checkNotNull(firstStateRecord, "null cannot be cast to non-null type androidx.compose.runtime.snapshots.StateSetStateRecord<T of androidx.compose.runtime.snapshots.SnapshotStateSetKt.writable>");
        StateSetStateRecord stateSetStateRecord = (StateSetStateRecord) firstStateRecord;
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            StateSetStateRecord stateSetStateRecord2 = (StateSetStateRecord) SnapshotKt.writableRecord(stateSetStateRecord, snapshotStateSet, current);
            synchronized (sync) {
                stateSetStateRecord2.setSet$runtime(ExtensionsKt.persistentSetOf());
                stateSetStateRecord2.setModification$runtime(stateSetStateRecord2.getModification$runtime() + 1);
            }
        }
        SnapshotKt.notifyWrite(current, snapshotStateSet);
    }
}
