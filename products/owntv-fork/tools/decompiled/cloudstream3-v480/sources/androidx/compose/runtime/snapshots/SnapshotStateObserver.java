package androidx.compose.runtime.snapshots;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ObjectIntMap;
import androidx.collection.ScatterSet;
import androidx.compose.animation.core.MutatorMutex$$ExternalSyntheticBackportWithForwarding0;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DerivedState;
import androidx.compose.runtime.DerivedStateObserver;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.collection.ScatterSetWrapper;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.internal.Thread_jvmKt;
import androidx.exifinterface.media.ExifInterface;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SnapshotStateObserver.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0012\b\u0007\u0018\u00002\u00020\u0001:\u0001<B0\u0012'\u0010\u0002\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0015\u001a\u00020\u0010H\u0002J\b\u0010\u0016\u001a\u00020\u0005H\u0002J\u0016\u0010\u0017\u001a\u00020\u00052\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u0013H\u0002J\u0010\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u001d\u0010#\u001a\u00020\u00052\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00050\u0003H\u0082\bJ\u001d\u0010%\u001a\u00020\u00052\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00100\u0003H\u0082\bJ?\u0010,\u001a\u00020\u0005\"\b\b\u0000\u0010-*\u00020\u00012\u0006\u0010.\u001a\u0002H-2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u00020\u00050\u00032\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u00100J\u0016\u00101\u001a\u00020\u00052\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007J\u000e\u00102\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u0001J)\u00103\u001a\u00020\u00052!\u00104\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00100\u0003J\u0006\u00105\u001a\u00020\u0005J\u0006\u00106\u001a\u00020\u0005J\u001e\u00107\u001a\u00020\u00052\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00010\u00132\u0006\u00109\u001a\u00020\u0014H\u0007J\u0006\u00102\u001a\u00020\u0005J&\u0010:\u001a\u00020\u001f\"\b\b\u0000\u0010-*\u00020\u00012\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u00020\u00050\u0003H\u0002R/\u0010\u0002\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fj\n\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0011\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00050\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u00060\u0001j\u0002`!X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\"R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "", "onChangedExecutor", "Lkotlin/Function1;", "Lkotlin/Function0;", "", "Lkotlin/ParameterName;", "name", "callback", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "pendingChanges", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/runtime/internal/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicReference;", "sendingNotifications", "", "applyObserver", "Lkotlin/Function2;", "", "Landroidx/compose/runtime/snapshots/Snapshot;", "drainChanges", "sendNotifications", "addChanges", "set", "removeChanges", "report", "", "readObserver", "observedScopeMaps", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver$ObservedScopeMap;", "observedScopeMapsLock", "Landroidx/compose/runtime/platform/SynchronizedObject;", "Ljava/lang/Object;", "forEachScopeMap", "block", "removeScopeMapIf", "applyUnsubscribe", "Landroidx/compose/runtime/snapshots/ObserverHandle;", "isPaused", "currentMap", "currentMapThreadId", "", "observeReads", ExifInterface.GPS_DIRECTION_TRUE, "scope", "onValueChangedForScope", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "withNoObservations", "clear", "clearIf", "predicate", "start", "stop", "notifyChanges", "changes", "snapshot", "ensureMap", "onChanged", "ObservedScopeMap", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SnapshotStateObserver {
    public static final int $stable = 8;
    private ObserverHandle applyUnsubscribe;
    private ObservedScopeMap currentMap;
    private boolean isPaused;
    private final Function1<Function0<Unit>, Unit> onChangedExecutor;
    private boolean sendingNotifications;
    private final AtomicReference<Object> pendingChanges = new AtomicReference<>(null);
    private final Function2<Set<? extends Object>, Snapshot, Unit> applyObserver = new Function2() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return SnapshotStateObserver.applyObserver$lambda$0(this.f$0, (Set) obj, (Snapshot) obj2);
        }
    };
    private final Function1<Object, Unit> readObserver = new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SnapshotStateObserver.readObserver$lambda$7(this.f$0, obj);
        }
    };
    private final MutableVector<ObservedScopeMap> observedScopeMaps = new MutableVector<>(new ObservedScopeMap[16], 0);
    private final Object observedScopeMapsLock = new Object();
    private long currentMapThreadId = -1;

    /* JADX WARN: Multi-variable type inference failed */
    public SnapshotStateObserver(Function1<? super Function0<Unit>, Unit> function1) {
        this.onChangedExecutor = function1;
    }

    static final Unit applyObserver$lambda$0(SnapshotStateObserver snapshotStateObserver, Set set, Snapshot snapshot) {
        snapshotStateObserver.addChanges(set);
        if (snapshotStateObserver.drainChanges()) {
            snapshotStateObserver.sendNotifications();
        }
        return Unit.INSTANCE;
    }

    private final boolean drainChanges() {
        boolean z;
        synchronized (this.observedScopeMapsLock) {
            z = this.sendingNotifications;
        }
        if (z) {
            return false;
        }
        boolean z2 = false;
        while (true) {
            Set<? extends Object> setRemoveChanges = removeChanges();
            if (setRemoveChanges == null) {
                return z2;
            }
            synchronized (this.observedScopeMapsLock) {
                MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
                ObservedScopeMap[] observedScopeMapArr = mutableVector.content;
                int size = mutableVector.getSize();
                for (int i = 0; i < size; i++) {
                    z2 = observedScopeMapArr[i].recordInvalidation(setRemoveChanges) || z2;
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    private final void sendNotifications() {
        this.onChangedExecutor.invoke(new Function0() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SnapshotStateObserver.sendNotifications$lambda$5(this.f$0);
            }
        });
    }

    static final Unit sendNotifications$lambda$5(SnapshotStateObserver snapshotStateObserver) {
        do {
            synchronized (snapshotStateObserver.observedScopeMapsLock) {
                if (!snapshotStateObserver.sendingNotifications) {
                    snapshotStateObserver.sendingNotifications = true;
                    try {
                        MutableVector<ObservedScopeMap> mutableVector = snapshotStateObserver.observedScopeMaps;
                        ObservedScopeMap[] observedScopeMapArr = mutableVector.content;
                        int size = mutableVector.getSize();
                        for (int i = 0; i < size; i++) {
                            observedScopeMapArr[i].notifyInvalidatedScopes();
                        }
                        snapshotStateObserver.sendingNotifications = false;
                    } catch (Throwable th) {
                        snapshotStateObserver.sendingNotifications = false;
                        throw th;
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
        } while (snapshotStateObserver.drainChanges());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void addChanges(Set<? extends Object> set) {
        Object obj;
        List listPlus;
        do {
            obj = this.pendingChanges.get();
            if (obj == null) {
                listPlus = set;
            } else if (obj instanceof Set) {
                listPlus = CollectionsKt.listOf((Object[]) new Set[]{obj, set});
            } else {
                if (!(obj instanceof List)) {
                    report();
                    throw new KotlinNothingValueException();
                }
                listPlus = CollectionsKt.plus((Collection) obj, (Iterable) CollectionsKt.listOf(set));
            }
        } while (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.pendingChanges, obj, listPlus));
    }

    private final Set<Object> removeChanges() {
        Object obj;
        Object objSubList;
        Set<Object> set;
        do {
            obj = this.pendingChanges.get();
            objSubList = null;
            if (obj == null) {
                return null;
            }
            if (obj instanceof Set) {
                set = (Set) obj;
            } else if (obj instanceof List) {
                List list = (List) obj;
                Set<Object> set2 = (Set) list.get(0);
                if (list.size() == 2) {
                    objSubList = list.get(1);
                } else if (list.size() > 2) {
                    objSubList = list.subList(1, list.size());
                }
                set = set2;
            } else {
                report();
                throw new KotlinNothingValueException();
            }
        } while (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.pendingChanges, obj, objSubList));
        return set;
    }

    private final Void report() {
        ComposerKt.composeRuntimeError("Unexpected notification");
        throw new KotlinNothingValueException();
    }

    static final Unit readObserver$lambda$7(SnapshotStateObserver snapshotStateObserver, Object obj) {
        if (!snapshotStateObserver.isPaused) {
            synchronized (snapshotStateObserver.observedScopeMapsLock) {
                ObservedScopeMap observedScopeMap = snapshotStateObserver.currentMap;
                Intrinsics.checkNotNull(observedScopeMap);
                observedScopeMap.recordRead(obj);
                Unit unit = Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }

    private final void forEachScopeMap(Function1<? super ObservedScopeMap, Unit> block) {
        synchronized (this.observedScopeMapsLock) {
            MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
            ObservedScopeMap[] observedScopeMapArr = mutableVector.content;
            int size = mutableVector.getSize();
            for (int i = 0; i < size; i++) {
                block.invoke(observedScopeMapArr[i]);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void removeScopeMapIf(Function1<? super ObservedScopeMap, Boolean> block) {
        synchronized (this.observedScopeMapsLock) {
            MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
            int size = mutableVector.getSize();
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                if (block.invoke(mutableVector.content[i2]).booleanValue()) {
                    i++;
                } else if (i > 0) {
                    mutableVector.content[i2 - i] = mutableVector.content[i2];
                }
            }
            int i3 = size - i;
            ArraysKt.fill(mutableVector.content, (Object) null, i3, size);
            mutableVector.setSize(i3);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final <T> void observeReads(T scope, Function1<? super T, Unit> onValueChangedForScope, Function0<Unit> block) {
        ObservedScopeMap observedScopeMapEnsureMap;
        synchronized (this.observedScopeMapsLock) {
            observedScopeMapEnsureMap = ensureMap(onValueChangedForScope);
        }
        boolean z = this.isPaused;
        ObservedScopeMap observedScopeMap = this.currentMap;
        long j = this.currentMapThreadId;
        if (j != -1) {
            if (!(j == Thread_jvmKt.currentThreadId())) {
                PreconditionsKt.throwIllegalArgumentException("Detected multithreaded access to SnapshotStateObserver: previousThreadId=" + j + "), currentThread={id=" + Thread_jvmKt.currentThreadId() + ", name=" + Thread_jvmKt.currentThreadName() + "}. Note that observation on multiple threads in layout/draw is not supported. Make sure your measure/layout/draw for each Owner (AndroidComposeView) is executed on the same thread.");
            }
        }
        try {
            this.isPaused = false;
            this.currentMap = observedScopeMapEnsureMap;
            this.currentMapThreadId = Thread_jvmKt.currentThreadId();
            observedScopeMapEnsureMap.observe(scope, this.readObserver, block);
        } finally {
            this.currentMap = observedScopeMap;
            this.isPaused = z;
            this.currentMapThreadId = j;
        }
    }

    @Deprecated(message = "Replace with Snapshot.withoutReadObservation()", replaceWith = @ReplaceWith(expression = "Snapshot.withoutReadObservation(block)", imports = {"androidx.compose.runtime.snapshots.Snapshot"}))
    public final void withNoObservations(Function0<Unit> block) {
        boolean z = this.isPaused;
        this.isPaused = true;
        try {
            block.invoke();
        } finally {
            this.isPaused = z;
        }
    }

    public final void start() {
        this.applyUnsubscribe = Snapshot.INSTANCE.registerApplyObserver(this.applyObserver);
    }

    public final void stop() {
        ObserverHandle observerHandle = this.applyUnsubscribe;
        if (observerHandle != null) {
            observerHandle.dispose();
        }
    }

    public final void notifyChanges(Set<? extends Object> changes, Snapshot snapshot) {
        this.applyObserver.invoke(changes, snapshot);
    }

    private final <T> ObservedScopeMap ensureMap(Function1<? super T, Unit> onChanged) {
        ObservedScopeMap observedScopeMap;
        MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
        ObservedScopeMap[] observedScopeMapArr = mutableVector.content;
        int size = mutableVector.getSize();
        int i = 0;
        while (true) {
            if (i >= size) {
                observedScopeMap = null;
                break;
            }
            observedScopeMap = observedScopeMapArr[i];
            if (observedScopeMap.getOnChanged() == onChanged) {
                break;
            }
            i++;
        }
        ObservedScopeMap observedScopeMap2 = observedScopeMap;
        if (observedScopeMap2 != null) {
            return observedScopeMap2;
        }
        Intrinsics.checkNotNull(onChanged, "null cannot be cast to non-null type kotlin.Function1<kotlin.Any, kotlin.Unit>");
        ObservedScopeMap observedScopeMap3 = new ObservedScopeMap((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(onChanged, 1));
        this.observedScopeMaps.add(observedScopeMap3);
        return observedScopeMap3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SnapshotStateObserver.kt */
    @Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0001J.\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00012\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0002J0\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00012\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00040(J\u0010\u0010)\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u0001H\u0002J\u000e\u0010*\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u0001J)\u0010+\u001a\u00020\u00042!\u0010,\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020/0\u0003J\u0006\u00100\u001a\u00020/J\u0018\u00101\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u0001H\u0002J\u0006\u00102\u001a\u00020\u0004J\u0014\u00103\u001a\u00020/2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000105J\u0012\u00106\u001a\u00020\u00042\n\u00107\u001a\u0006\u0012\u0002\b\u00030\u0017J\u0006\u00108\u001a\u00020\u0004R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R \u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000b0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0015\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u0001\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R6\u0010\u001e\u001a*\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001fj\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u0001` X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateObserver$ObservedScopeMap;", "", "onChanged", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "getOnChanged", "()Lkotlin/jvm/functions/Function1;", "currentScope", "currentScopeReads", "Landroidx/collection/MutableObjectIntMap;", "currentToken", "", "valueToScopes", "Landroidx/compose/runtime/collection/ScopeMap;", "Landroidx/collection/MutableScatterMap;", "scopeToValues", "Landroidx/collection/MutableScatterMap;", "invalidated", "Landroidx/collection/MutableScatterSet;", "statesToReread", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/runtime/DerivedState;", "derivedStateObserver", "Landroidx/compose/runtime/DerivedStateObserver;", "getDerivedStateObserver", "()Landroidx/compose/runtime/DerivedStateObserver;", "deriveStateScopeCount", "dependencyToDerivedStates", "recordedDerivedStateValues", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "recordRead", ES6Iterator.VALUE_PROPERTY, "recordedValues", "observe", "scope", "readObserver", "block", "Lkotlin/Function0;", "clearObsoleteStateReads", "clearScopeObservations", "removeScopeIf", "predicate", "Lkotlin/ParameterName;", "name", "", "hasScopeObservations", "removeObservation", "clear", "recordInvalidation", "changes", "", "rereadDerivedState", "derivedState", "notifyInvalidatedScopes", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    static final class ObservedScopeMap {
        private Object currentScope;
        private MutableObjectIntMap<Object> currentScopeReads;
        private int deriveStateScopeCount;
        private final MutableScatterSet<Object> invalidated;
        private final Function1<Object, Unit> onChanged;
        private final MutableScatterMap<Object, MutableObjectIntMap<Object>> scopeToValues;
        private int currentToken = -1;
        private final MutableScatterMap<Object, Object> valueToScopes = ScopeMap.m3554constructorimpl$default(null, 1, null);
        private final MutableVector<DerivedState<?>> statesToReread = new MutableVector<>(new DerivedState[16], 0);
        private final DerivedStateObserver derivedStateObserver = new DerivedStateObserver() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$ObservedScopeMap$derivedStateObserver$1
            @Override // androidx.compose.runtime.DerivedStateObserver
            public void start(DerivedState<?> derivedState) {
                this.this$0.deriveStateScopeCount++;
            }

            @Override // androidx.compose.runtime.DerivedStateObserver
            public void done(DerivedState<?> derivedState) {
                this.this$0.deriveStateScopeCount--;
            }
        };
        private final MutableScatterMap<Object, Object> dependencyToDerivedStates = ScopeMap.m3554constructorimpl$default(null, 1, null);
        private final HashMap<DerivedState<?>, Object> recordedDerivedStateValues = new HashMap<>();

        public ObservedScopeMap(Function1<Object, Unit> function1) {
            this.onChanged = function1;
            DefaultConstructorMarker defaultConstructorMarker = null;
            int i = 1;
            int i2 = 0;
            this.scopeToValues = new MutableScatterMap<>(i2, i, defaultConstructorMarker);
            this.invalidated = new MutableScatterSet<>(i2, i, defaultConstructorMarker);
        }

        public final Function1<Object, Unit> getOnChanged() {
            return this.onChanged;
        }

        public final DerivedStateObserver getDerivedStateObserver() {
            return this.derivedStateObserver;
        }

        public final void recordRead(Object value) {
            Object obj = this.currentScope;
            Intrinsics.checkNotNull(obj);
            int i = this.currentToken;
            MutableObjectIntMap<Object> mutableObjectIntMap = this.currentScopeReads;
            if (mutableObjectIntMap == null) {
                mutableObjectIntMap = new MutableObjectIntMap<>(0, 1, null);
                this.currentScopeReads = mutableObjectIntMap;
                this.scopeToValues.set(obj, mutableObjectIntMap);
                Unit unit = Unit.INSTANCE;
            }
            recordRead(value, i, obj, mutableObjectIntMap);
        }

        private final void recordRead(Object value, int currentToken, Object currentScope, MutableObjectIntMap<Object> recordedValues) {
            int i;
            int i2;
            int i3;
            if (this.deriveStateScopeCount > 0) {
                return;
            }
            int iPut = recordedValues.put(value, currentToken, -1);
            int i4 = 2;
            if (!(value instanceof DerivedState) || iPut == currentToken) {
                i = 2;
                i2 = -1;
            } else {
                DerivedState.Record currentRecord = ((DerivedState) value).getCurrentRecord();
                this.recordedDerivedStateValues.put(value, currentRecord.getCurrentValue());
                ObjectIntMap<StateObject> dependencies = currentRecord.getDependencies();
                MutableScatterMap<Object, Object> mutableScatterMap = this.dependencyToDerivedStates;
                ScopeMap.m3563removeScopeimpl(mutableScatterMap, value);
                Object[] objArr = dependencies.keys;
                long[] jArr = dependencies.metadata;
                int length = jArr.length - 2;
                if (length >= 0) {
                    int i5 = 0;
                    while (true) {
                        long j = jArr[i5];
                        if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i6 = 8 - ((~(i5 - length)) >>> 31);
                            int i7 = 0;
                            while (i7 < i6) {
                                if ((j & 255) < 128) {
                                    i3 = i4;
                                    StateObject stateObject = (StateObject) objArr[(i5 << 3) + i7];
                                    if (stateObject instanceof StateObjectImpl) {
                                        ReaderKind.Companion companion = ReaderKind.INSTANCE;
                                        ((StateObjectImpl) stateObject).m3592recordReadInh_f27i8$runtime(ReaderKind.m3579constructorimpl(i3));
                                    }
                                    ScopeMap.m3548addimpl(mutableScatterMap, stateObject, value);
                                } else {
                                    i3 = i4;
                                }
                                j >>= 8;
                                i7++;
                                i4 = i3;
                            }
                            i = i4;
                            if (i6 != 8) {
                                break;
                            }
                        } else {
                            i = i4;
                        }
                        if (i5 == length) {
                            break;
                        }
                        i5++;
                        i4 = i;
                    }
                } else {
                    i = 2;
                }
                i2 = -1;
            }
            if (iPut == i2) {
                if (value instanceof StateObjectImpl) {
                    ReaderKind.Companion companion2 = ReaderKind.INSTANCE;
                    ((StateObjectImpl) value).m3592recordReadInh_f27i8$runtime(ReaderKind.m3579constructorimpl(i));
                }
                ScopeMap.m3548addimpl(this.valueToScopes, value, currentScope);
            }
        }

        public final void observe(Object scope, Function1<Object, Unit> readObserver, Function0<Unit> block) {
            Object obj = this.currentScope;
            MutableObjectIntMap<Object> mutableObjectIntMap = this.currentScopeReads;
            int i = this.currentToken;
            this.currentScope = scope;
            this.currentScopeReads = this.scopeToValues.get(scope);
            if (this.currentToken == -1) {
                this.currentToken = UByte$$ExternalSyntheticBackport0.m(SnapshotKt.currentSnapshot().getSnapshotId());
            }
            DerivedStateObserver derivedStateObserver = this.derivedStateObserver;
            MutableVector<DerivedStateObserver> mutableVectorDerivedStateObservers = SnapshotStateKt.derivedStateObservers();
            try {
                mutableVectorDerivedStateObservers.add(derivedStateObserver);
                Snapshot.INSTANCE.observe(readObserver, null, block);
                mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                Object obj2 = this.currentScope;
                Intrinsics.checkNotNull(obj2);
                clearObsoleteStateReads(obj2);
                this.currentScope = obj;
                this.currentScopeReads = mutableObjectIntMap;
                this.currentToken = i;
            } catch (Throwable th) {
                mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                throw th;
            }
        }

        private final void clearObsoleteStateReads(Object scope) {
            int i = this.currentToken;
            MutableObjectIntMap<Object> mutableObjectIntMap = this.currentScopeReads;
            if (mutableObjectIntMap == null) {
                return;
            }
            long[] jArr = mutableObjectIntMap.metadata;
            int length = jArr.length - 2;
            if (length < 0) {
                return;
            }
            int i2 = 0;
            while (true) {
                long j = jArr[i2];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j) < 128) {
                            int i5 = (i2 << 3) + i4;
                            Object obj = mutableObjectIntMap.keys[i5];
                            boolean z = mutableObjectIntMap.values[i5] != i;
                            if (z) {
                                removeObservation(scope, obj);
                            }
                            if (z) {
                                mutableObjectIntMap.removeValueAt(i5);
                            }
                        }
                        j >>= 8;
                    }
                    if (i3 != 8) {
                        return;
                    }
                }
                if (i2 == length) {
                    return;
                } else {
                    i2++;
                }
            }
        }

        public final void clearScopeObservations(Object scope) {
            MutableObjectIntMap<Object> mutableObjectIntMapRemove = this.scopeToValues.remove(scope);
            if (mutableObjectIntMapRemove == null) {
                return;
            }
            MutableObjectIntMap<Object> mutableObjectIntMap = mutableObjectIntMapRemove;
            Object[] objArr = mutableObjectIntMap.keys;
            int[] iArr = mutableObjectIntMap.values;
            long[] jArr = mutableObjectIntMap.metadata;
            int length = jArr.length - 2;
            if (length < 0) {
                return;
            }
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            int i4 = (i << 3) + i3;
                            Object obj = objArr[i4];
                            int i5 = iArr[i4];
                            removeObservation(scope, obj);
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        return;
                    }
                }
                if (i == length) {
                    return;
                } else {
                    i++;
                }
            }
        }

        /* JADX WARN: Code duplicated, block: B:27:0x00a2 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:28:0x00a4 A[LOOP:2: B:16:0x006b->B:28:0x00a4, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:29:0x00ad  */
        /* JADX WARN: Code duplicated, block: B:49:0x00b1 A[EDGE_INSN: B:49:0x00b1->B:30:0x00b1 BREAK  A[LOOP:2: B:16:0x006b->B:28:0x00a4], SYNTHETIC] */
        public final void removeScopeIf(Function1<Object, Boolean> predicate) {
            long[] jArr;
            long[] jArr2;
            long j;
            char c;
            long j2;
            int i;
            MutableScatterMap<Object, MutableObjectIntMap<Object>> mutableScatterMap = this.scopeToValues;
            long[] jArr3 = mutableScatterMap.metadata;
            int length = jArr3.length - 2;
            if (length < 0) {
                return;
            }
            int i2 = 0;
            while (true) {
                long j3 = jArr3[i2];
                char c2 = 7;
                long j4 = -9187201950435737472L;
                if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8;
                    int i4 = 8 - ((~(i2 - length)) >>> 31);
                    int i5 = 0;
                    while (i5 < i4) {
                        if ((j3 & 255) < 128) {
                            int i6 = (i2 << 3) + i5;
                            c = c2;
                            Object obj = mutableScatterMap.keys[i6];
                            j2 = j4;
                            MutableObjectIntMap mutableObjectIntMap = (MutableObjectIntMap) mutableScatterMap.values[i6];
                            Boolean boolInvoke = predicate.invoke(obj);
                            if (boolInvoke.booleanValue()) {
                                MutableObjectIntMap mutableObjectIntMap2 = mutableObjectIntMap;
                                Object[] objArr = mutableObjectIntMap2.keys;
                                int[] iArr = mutableObjectIntMap2.values;
                                long[] jArr4 = mutableObjectIntMap2.metadata;
                                int i7 = i3;
                                int length2 = jArr4.length - 2;
                                if (length2 >= 0) {
                                    jArr2 = jArr3;
                                    j = j3;
                                    int i8 = 0;
                                    while (true) {
                                        long j5 = jArr4[i8];
                                        long[] jArr5 = jArr4;
                                        if ((((~j5) << c) & j5 & j2) != j2) {
                                            int i9 = 8 - ((~(i8 - length2)) >>> 31);
                                            for (int i10 = 0; i10 < i9; i10++) {
                                                if ((j5 & 255) < 128) {
                                                    int i11 = (i8 << 3) + i10;
                                                    Object obj2 = objArr[i11];
                                                    int i12 = iArr[i11];
                                                    removeObservation(obj, obj2);
                                                }
                                                j5 >>= i7;
                                            }
                                            if (i9 != i7) {
                                                break;
                                            }
                                            if (i8 != length2) {
                                                break;
                                            }
                                            i8++;
                                            jArr4 = jArr5;
                                            i7 = 8;
                                        } else if (i8 != length2) {
                                            break;
                                            break;
                                        } else {
                                            i8++;
                                            jArr4 = jArr5;
                                            i7 = 8;
                                        }
                                    }
                                } else {
                                    jArr2 = jArr3;
                                    j = j3;
                                }
                            } else {
                                jArr2 = jArr3;
                                j = j3;
                            }
                            if (boolInvoke.booleanValue()) {
                                mutableScatterMap.removeValueAt(i6);
                            }
                            i = 8;
                        } else {
                            jArr2 = jArr3;
                            j = j3;
                            c = c2;
                            j2 = j4;
                            i = i3;
                        }
                        i5++;
                        i3 = i;
                        j3 = j >> i;
                        c2 = c;
                        j4 = j2;
                        jArr3 = jArr2;
                    }
                    jArr = jArr3;
                    if (i4 != i3) {
                        return;
                    }
                } else {
                    jArr = jArr3;
                }
                if (i2 == length) {
                    return;
                }
                i2++;
                jArr3 = jArr;
            }
        }

        public final boolean hasScopeObservations() {
            return this.scopeToValues.isNotEmpty();
        }

        private final void removeObservation(Object scope, Object value) {
            ScopeMap.m3561removeimpl(this.valueToScopes, value, scope);
            if (!(value instanceof DerivedState) || ScopeMap.m3555containsimpl(this.valueToScopes, value)) {
                return;
            }
            ScopeMap.m3563removeScopeimpl(this.dependencyToDerivedStates, value);
            this.recordedDerivedStateValues.remove(value);
        }

        public final void clear() {
            ScopeMap.m3552clearimpl(this.valueToScopes);
            this.scopeToValues.clear();
            ScopeMap.m3552clearimpl(this.dependencyToDerivedStates);
            this.recordedDerivedStateValues.clear();
        }

        /* JADX WARN: Code duplicated, block: B:100:0x0252 A[DONT_INVERT, PHI: r11
          0x0252: PHI (r11v57 boolean) = (r11v56 boolean), (r11v58 boolean) binds: [B:91:0x022a, B:99:0x0250] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:101:0x0254 A[LOOP:8: B:90:0x0220->B:101:0x0254, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:125:0x02cd A[DONT_INVERT, PHI: r11
          0x02cd: PHI (r11v46 boolean) = (r11v45 boolean), (r11v47 boolean) binds: [B:116:0x02a5, B:124:0x02cb] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:126:0x02cf A[LOOP:6: B:115:0x029b->B:126:0x02cf, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:146:0x0360 A[PHI: r11 r25 r44
          0x0360: PHI (r11v35 boolean) = (r11v2 boolean), (r11v2 boolean), (r11v1 boolean) binds: [B:230:0x051a, B:234:0x052b, B:145:0x035c] A[DONT_GENERATE, DONT_INLINE]
          0x0360: PHI (r25v14 androidx.collection.MutableScatterMap<java.lang.Object, java.lang.Object>) = 
          (r25v1 androidx.collection.MutableScatterMap<java.lang.Object, java.lang.Object>)
          (r25v1 androidx.collection.MutableScatterMap<java.lang.Object, java.lang.Object>)
          (r25v15 androidx.collection.MutableScatterMap<java.lang.Object, java.lang.Object>)
         binds: [B:230:0x051a, B:234:0x052b, B:145:0x035c] A[DONT_GENERATE, DONT_INLINE]
          0x0360: PHI (r44v11 java.util.Iterator) = (r44v2 java.util.Iterator), (r44v2 java.util.Iterator), (r44v12 java.util.Iterator) binds: [B:230:0x051a, B:234:0x052b, B:145:0x035c] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:147:0x0364  */
        /* JADX WARN: Code duplicated, block: B:223:0x04fc A[DONT_INVERT, PHI: r11
          0x04fc: PHI (r11v15 boolean) = (r11v14 boolean), (r11v16 boolean) binds: [B:214:0x04d4, B:222:0x04fa] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:224:0x04fe A[LOOP:18: B:213:0x04ca->B:224:0x04fe, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:228:0x0512  */
        /* JADX WARN: Code duplicated, block: B:231:0x051c  */
        /* JADX WARN: Code duplicated, block: B:233:0x0520  */
        /* JADX WARN: Code duplicated, block: B:235:0x052d  */
        /* JADX WARN: Code duplicated, block: B:238:0x053b  */
        /* JADX WARN: Code duplicated, block: B:240:0x0548  */
        /* JADX WARN: Code duplicated, block: B:242:0x054e  */
        /* JADX WARN: Code duplicated, block: B:247:0x0562  */
        /* JADX WARN: Code duplicated, block: B:249:0x0566 A[LOOP:16: B:236:0x052f->B:249:0x0566, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:250:0x0569  */
        /* JADX WARN: Code duplicated, block: B:276:0x02d6 A[EDGE_INSN: B:276:0x02d6->B:128:0x02d6 BREAK  A[LOOP:6: B:115:0x029b->B:126:0x02cf], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:281:0x025b A[EDGE_INSN: B:281:0x025b->B:103:0x025b BREAK  A[LOOP:8: B:90:0x0220->B:101:0x0254], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:289:0x056f A[EDGE_INSN: B:289:0x056f->B:251:0x056f BREAK  A[LOOP:16: B:236:0x052f->B:249:0x0566], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:290:0x056f A[EDGE_INSN: B:290:0x056f->B:251:0x056f BREAK  A[LOOP:16: B:236:0x052f->B:249:0x0566], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:304:0x0557 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:306:0x0505 A[EDGE_INSN: B:306:0x0505->B:226:0x0505 BREAK  A[LOOP:18: B:213:0x04ca->B:224:0x04fe], SYNTHETIC] */
        public final boolean recordInvalidation(Set<? extends Object> changes) {
            boolean z;
            Iterator it;
            MutableScatterMap<Object, Object> mutableScatterMap;
            Object obj;
            Object[] objArr;
            long[] jArr;
            int length;
            int i;
            long j;
            int i2;
            int i3;
            Object obj2;
            long[] jArr2;
            Object[] objArr2;
            Iterator it2;
            MutableScatterMap<Object, Object> mutableScatterMap2;
            long[] jArr3;
            Object[] objArr3;
            boolean z2;
            long[] jArr4;
            Object[] objArr4;
            String str;
            int i4;
            Object[] objArr5;
            String str2;
            int i5;
            int i6;
            long j2;
            int i7;
            int i8;
            Object obj3;
            Object obj4;
            boolean z3;
            String str3;
            Object obj5;
            int i9;
            long j3;
            int i10;
            MutableScatterMap<Object, Object> mutableScatterMap3 = this.dependencyToDerivedStates;
            HashMap<DerivedState<?>, Object> map = this.recordedDerivedStateValues;
            MutableScatterMap<Object, Object> mutableScatterMap4 = this.valueToScopes;
            MutableScatterSet<Object> mutableScatterSet = this.invalidated;
            String str4 = "null cannot be cast to non-null type androidx.compose.runtime.DerivedState<kotlin.Any?>";
            int i11 = 8;
            if (changes instanceof ScatterSetWrapper) {
                ScatterSet set$runtime = ((ScatterSetWrapper) changes).getSet$runtime();
                Object[] objArr6 = set$runtime.elements;
                long[] jArr5 = set$runtime.metadata;
                int length2 = jArr5.length - 2;
                if (length2 >= 0) {
                    int i12 = 0;
                    z = false;
                    while (true) {
                        long j4 = jArr5[i12];
                        if ((((~j4) << 7) & j4 & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i13 = 8 - ((~(i12 - length2)) >>> 31);
                            int i14 = 0;
                            while (i14 < i13) {
                                if ((j4 & 255) < 128) {
                                    int i15 = i11;
                                    Object obj6 = objArr6[(i12 << 3) + i14];
                                    if (obj6 instanceof StateObjectImpl) {
                                        ReaderKind.Companion companion = ReaderKind.INSTANCE;
                                        objArr5 = objArr6;
                                        if (!((StateObjectImpl) obj6).m3591isReadInh_f27i8$runtime(ReaderKind.m3579constructorimpl(2))) {
                                            str2 = str4;
                                            i5 = length2;
                                            i6 = i12;
                                            j2 = j4;
                                            i7 = i13;
                                        }
                                        i8 = 8;
                                    } else {
                                        objArr5 = objArr6;
                                    }
                                    if (ScopeMap.m3555containsimpl(mutableScatterMap3, obj6)) {
                                        Object obj7 = mutableScatterMap3.get(obj6);
                                        if (obj7 == null) {
                                            str2 = str4;
                                            obj4 = obj6;
                                            i5 = length2;
                                            i6 = i12;
                                            j2 = j4;
                                            i7 = i13;
                                        } else if (obj7 instanceof MutableScatterSet) {
                                            MutableScatterSet mutableScatterSet2 = (MutableScatterSet) obj7;
                                            Object[] objArr7 = mutableScatterSet2.elements;
                                            long[] jArr6 = mutableScatterSet2.metadata;
                                            int length3 = jArr6.length - 2;
                                            if (length3 >= 0) {
                                                j2 = j4;
                                                int i16 = 0;
                                                z3 = z;
                                                while (true) {
                                                    long j5 = jArr6[i16];
                                                    i5 = length2;
                                                    i6 = i12;
                                                    if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                        int i17 = 8 - ((~(i16 - length3)) >>> 31);
                                                        int i18 = 0;
                                                        while (i18 < i17) {
                                                            if ((j5 & 255) < 128) {
                                                                i9 = i18;
                                                                DerivedState<?> derivedState = (DerivedState) objArr7[(i16 << 3) + i18];
                                                                Intrinsics.checkNotNull(derivedState, str4);
                                                                j3 = j5;
                                                                Object obj8 = map.get(derivedState);
                                                                SnapshotMutationPolicy<?> policy = derivedState.getPolicy();
                                                                if (policy == null) {
                                                                    policy = SnapshotStateKt.structuralEqualityPolicy();
                                                                }
                                                                boolean z4 = z3;
                                                                if (policy.equivalent(derivedState.getCurrentRecord().getCurrentValue(), obj8)) {
                                                                    str3 = str4;
                                                                    obj5 = obj6;
                                                                    i10 = i13;
                                                                    Boolean.valueOf(this.statesToReread.add(derivedState));
                                                                    z3 = z4;
                                                                } else {
                                                                    Object obj9 = mutableScatterMap4.get(derivedState);
                                                                    if (obj9 != null) {
                                                                        if (obj9 instanceof MutableScatterSet) {
                                                                            MutableScatterSet mutableScatterSet3 = (MutableScatterSet) obj9;
                                                                            Object[] objArr8 = mutableScatterSet3.elements;
                                                                            long[] jArr7 = mutableScatterSet3.metadata;
                                                                            int length4 = jArr7.length - 2;
                                                                            if (length4 >= 0) {
                                                                                i10 = i13;
                                                                                int i19 = 0;
                                                                                while (true) {
                                                                                    long j6 = jArr7[i19];
                                                                                    str3 = str4;
                                                                                    obj5 = obj6;
                                                                                    if ((((~j6) << 7) & j6 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                                                        int i20 = 8 - ((~(i19 - length4)) >>> 31);
                                                                                        for (int i21 = 0; i21 < i20; i21++) {
                                                                                            if ((j6 & 255) < 128) {
                                                                                                mutableScatterSet.add(objArr8[(i19 << 3) + i21]);
                                                                                                z4 = true;
                                                                                            }
                                                                                            j6 >>= i15;
                                                                                        }
                                                                                        if (i20 != i15) {
                                                                                            break;
                                                                                        }
                                                                                    }
                                                                                    if (i19 != length4) {
                                                                                        i19++;
                                                                                        str4 = str3;
                                                                                        obj6 = obj5;
                                                                                        i15 = 8;
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                str3 = str4;
                                                                                obj5 = obj6;
                                                                                i10 = i13;
                                                                            }
                                                                            z4 = z4;
                                                                            break;
                                                                        } else {
                                                                            str3 = str4;
                                                                            obj5 = obj6;
                                                                            i10 = i13;
                                                                            mutableScatterSet.add(obj9);
                                                                            z3 = true;
                                                                        }
                                                                        Unit unit = Unit.INSTANCE;
                                                                    } else {
                                                                        str3 = str4;
                                                                        obj5 = obj6;
                                                                        i10 = i13;
                                                                    }
                                                                    z3 = z4;
                                                                    Unit unit2 = Unit.INSTANCE;
                                                                }
                                                            } else {
                                                                str3 = str4;
                                                                obj5 = obj6;
                                                                i9 = i18;
                                                                j3 = j5;
                                                                i10 = i13;
                                                            }
                                                            j5 = j3 >> 8;
                                                            i18 = i9 + 1;
                                                            i15 = 8;
                                                            i13 = i10;
                                                            str4 = str3;
                                                            obj6 = obj5;
                                                        }
                                                        str2 = str4;
                                                        obj4 = obj6;
                                                        i7 = i13;
                                                        if (i17 != i15) {
                                                            break;
                                                        }
                                                    } else {
                                                        str2 = str4;
                                                        obj4 = obj6;
                                                        i7 = i13;
                                                    }
                                                    if (i16 != length3) {
                                                        i16++;
                                                        length2 = i5;
                                                        i12 = i6;
                                                        i13 = i7;
                                                        str4 = str2;
                                                        obj6 = obj4;
                                                        i15 = 8;
                                                    } else {
                                                        z = z3;
                                                    }
                                                }
                                                z = z3;
                                            } else {
                                                str2 = str4;
                                                obj4 = obj6;
                                                i5 = length2;
                                                i6 = i12;
                                                j2 = j4;
                                                i7 = i13;
                                            }
                                            z3 = z;
                                            z = z3;
                                        } else {
                                            str2 = str4;
                                            obj4 = obj6;
                                            i5 = length2;
                                            i6 = i12;
                                            j2 = j4;
                                            i7 = i13;
                                            DerivedState<?> derivedState2 = (DerivedState) obj7;
                                            Object obj10 = map.get(derivedState2);
                                            SnapshotMutationPolicy<?> policy2 = derivedState2.getPolicy();
                                            if (policy2 == null) {
                                                policy2 = SnapshotStateKt.structuralEqualityPolicy();
                                            }
                                            if (policy2.equivalent(derivedState2.getCurrentRecord().getCurrentValue(), obj10)) {
                                                Boolean.valueOf(this.statesToReread.add(derivedState2));
                                            } else {
                                                Object obj11 = mutableScatterMap4.get(derivedState2);
                                                if (obj11 != null) {
                                                    if (obj11 instanceof MutableScatterSet) {
                                                        MutableScatterSet mutableScatterSet4 = (MutableScatterSet) obj11;
                                                        Object[] objArr9 = mutableScatterSet4.elements;
                                                        long[] jArr8 = mutableScatterSet4.metadata;
                                                        int length5 = jArr8.length - 2;
                                                        if (length5 >= 0) {
                                                            int i22 = 0;
                                                            while (true) {
                                                                long j7 = jArr8[i22];
                                                                if ((((~j7) << 7) & j7 & (-9187201950435737472L)) == -9187201950435737472L) {
                                                                    if (i22 != length5) {
                                                                        break;
                                                                        break;
                                                                    }
                                                                    i22++;
                                                                } else {
                                                                    int i23 = 8 - ((~(i22 - length5)) >>> 31);
                                                                    for (int i24 = 0; i24 < i23; i24++) {
                                                                        if ((j7 & 255) < 128) {
                                                                            mutableScatterSet.add(objArr9[(i22 << 3) + i24]);
                                                                            z = true;
                                                                        }
                                                                        j7 >>= 8;
                                                                    }
                                                                    if (i23 != 8) {
                                                                        break;
                                                                    }
                                                                    if (i22 != length5) {
                                                                        break;
                                                                    }
                                                                    i22++;
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        mutableScatterSet.add(obj11);
                                                        z = true;
                                                    }
                                                }
                                                Unit unit3 = Unit.INSTANCE;
                                            }
                                        }
                                        obj3 = obj4;
                                    } else {
                                        str2 = str4;
                                        i5 = length2;
                                        i6 = i12;
                                        j2 = j4;
                                        i7 = i13;
                                        obj3 = obj6;
                                    }
                                    Object obj12 = mutableScatterMap4.get(obj3);
                                    if (obj12 != null) {
                                        if (obj12 instanceof MutableScatterSet) {
                                            MutableScatterSet mutableScatterSet5 = (MutableScatterSet) obj12;
                                            Object[] objArr10 = mutableScatterSet5.elements;
                                            long[] jArr9 = mutableScatterSet5.metadata;
                                            int length6 = jArr9.length - 2;
                                            if (length6 >= 0) {
                                                int i25 = 0;
                                                while (true) {
                                                    long j8 = jArr9[i25];
                                                    if ((((~j8) << 7) & j8 & (-9187201950435737472L)) == -9187201950435737472L) {
                                                        if (i25 != length6) {
                                                            break;
                                                            break;
                                                        }
                                                        i25++;
                                                    } else {
                                                        int i26 = 8 - ((~(i25 - length6)) >>> 31);
                                                        for (int i27 = 0; i27 < i26; i27++) {
                                                            if ((j8 & 255) < 128) {
                                                                mutableScatterSet.add(objArr10[(i25 << 3) + i27]);
                                                                z = true;
                                                            }
                                                            j8 >>= 8;
                                                        }
                                                        if (i26 != 8) {
                                                            break;
                                                        }
                                                        if (i25 != length6) {
                                                            break;
                                                        }
                                                        i25++;
                                                    }
                                                }
                                            }
                                        } else {
                                            mutableScatterSet.add(obj12);
                                            z = true;
                                        }
                                    }
                                    i8 = 8;
                                } else {
                                    objArr5 = objArr6;
                                    str2 = str4;
                                    i5 = length2;
                                    i6 = i12;
                                    j2 = j4;
                                    i7 = i13;
                                    i8 = i11;
                                }
                                j4 = j2 >> i8;
                                i14++;
                                i11 = i8;
                                jArr5 = jArr5;
                                objArr6 = objArr5;
                                length2 = i5;
                                i12 = i6;
                                i13 = i7;
                                str4 = str2;
                            }
                            jArr4 = jArr5;
                            objArr4 = objArr6;
                            str = str4;
                            int i28 = length2;
                            int i29 = i12;
                            if (i13 != i11) {
                                break;
                            }
                            length2 = i28;
                            i4 = i29;
                        } else {
                            jArr4 = jArr5;
                            objArr4 = objArr6;
                            str = str4;
                            i4 = i12;
                        }
                        if (i4 == length2) {
                            break;
                        }
                        i12 = i4 + 1;
                        jArr5 = jArr4;
                        objArr6 = objArr4;
                        str4 = str;
                        i11 = 8;
                    }
                } else {
                    z = false;
                }
            } else {
                Iterator it3 = changes.iterator();
                z = false;
                while (it3.hasNext()) {
                    Object next = it3.next();
                    if (next instanceof StateObjectImpl) {
                        ReaderKind.Companion companion2 = ReaderKind.INSTANCE;
                        if (((StateObjectImpl) next).m3591isReadInh_f27i8$runtime(ReaderKind.m3579constructorimpl(2))) {
                            if (ScopeMap.m3555containsimpl(mutableScatterMap3, next) || (obj2 = mutableScatterMap3.get(next)) == null) {
                                it = it3;
                                mutableScatterMap = mutableScatterMap3;
                            } else if (obj2 instanceof MutableScatterSet) {
                                MutableScatterSet mutableScatterSet6 = (MutableScatterSet) obj2;
                                Object[] objArr11 = mutableScatterSet6.elements;
                                long[] jArr10 = mutableScatterSet6.metadata;
                                int length7 = jArr10.length - 2;
                                if (length7 >= 0) {
                                    int i30 = 0;
                                    while (true) {
                                        long j9 = jArr10[i30];
                                        if ((((~j9) << 7) & j9 & (-9187201950435737472L)) != -9187201950435737472L) {
                                            int i31 = 8 - ((~(i30 - length7)) >>> 31);
                                            int i32 = 0;
                                            while (i32 < i31) {
                                                if ((j9 & 255) < 128) {
                                                    it2 = it3;
                                                    DerivedState<?> derivedState3 = (DerivedState) objArr11[(i30 << 3) + i32];
                                                    mutableScatterMap2 = mutableScatterMap3;
                                                    Intrinsics.checkNotNull(derivedState3, "null cannot be cast to non-null type androidx.compose.runtime.DerivedState<kotlin.Any?>");
                                                    Object obj13 = map.get(derivedState3);
                                                    SnapshotMutationPolicy<?> policy3 = derivedState3.getPolicy();
                                                    if (policy3 == null) {
                                                        policy3 = SnapshotStateKt.structuralEqualityPolicy();
                                                    }
                                                    jArr3 = jArr10;
                                                    objArr3 = objArr11;
                                                    if (policy3.equivalent(derivedState3.getCurrentRecord().getCurrentValue(), obj13)) {
                                                        j9 = j9;
                                                        Boolean.valueOf(this.statesToReread.add(derivedState3));
                                                    } else {
                                                        Object obj14 = mutableScatterMap4.get(derivedState3);
                                                        if (obj14 != null) {
                                                            if (obj14 instanceof MutableScatterSet) {
                                                                MutableScatterSet mutableScatterSet7 = (MutableScatterSet) obj14;
                                                                Object[] objArr12 = mutableScatterSet7.elements;
                                                                long[] jArr11 = mutableScatterSet7.metadata;
                                                                int length8 = jArr11.length - 2;
                                                                if (length8 < 0) {
                                                                    z2 = z;
                                                                    break;
                                                                }
                                                                int i33 = 0;
                                                                z2 = z;
                                                                while (true) {
                                                                    long j10 = jArr11[i33];
                                                                    long[] jArr12 = jArr11;
                                                                    Object[] objArr13 = objArr12;
                                                                    if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                                        int i34 = 8 - ((~(i33 - length8)) >>> 31);
                                                                        for (int i35 = 0; i35 < i34; i35++) {
                                                                            if ((j10 & 255) < 128) {
                                                                                mutableScatterSet.add(objArr13[(i33 << 3) + i35]);
                                                                                z2 = true;
                                                                            }
                                                                            j10 >>= 8;
                                                                        }
                                                                        if (i34 != 8) {
                                                                            break;
                                                                        }
                                                                    }
                                                                    if (i33 == length8) {
                                                                        z = z2;
                                                                        z2 = z;
                                                                        break;
                                                                    }
                                                                    i33++;
                                                                    objArr12 = objArr13;
                                                                    jArr11 = jArr12;
                                                                }
                                                                z = z2;
                                                            } else {
                                                                mutableScatterSet.add(obj14);
                                                                z = true;
                                                            }
                                                        }
                                                        Unit unit4 = Unit.INSTANCE;
                                                    }
                                                } else {
                                                    it2 = it3;
                                                    mutableScatterMap2 = mutableScatterMap3;
                                                    jArr3 = jArr10;
                                                    objArr3 = objArr11;
                                                    j9 = j9;
                                                }
                                                j9 >>= 8;
                                                i32++;
                                                it3 = it2;
                                                mutableScatterMap3 = mutableScatterMap2;
                                                jArr10 = jArr3;
                                                objArr11 = objArr3;
                                            }
                                            it = it3;
                                            mutableScatterMap = mutableScatterMap3;
                                            jArr2 = jArr10;
                                            objArr2 = objArr11;
                                            if (i31 != 8) {
                                                break;
                                            }
                                        } else {
                                            it = it3;
                                            mutableScatterMap = mutableScatterMap3;
                                            jArr2 = jArr10;
                                            objArr2 = objArr11;
                                        }
                                        if (i30 == length7) {
                                            break;
                                        }
                                        i30++;
                                        it3 = it;
                                        mutableScatterMap3 = mutableScatterMap;
                                        jArr10 = jArr2;
                                        objArr11 = objArr2;
                                    }
                                } else {
                                    it = it3;
                                    mutableScatterMap = mutableScatterMap3;
                                }
                            } else {
                                it = it3;
                                mutableScatterMap = mutableScatterMap3;
                                DerivedState<?> derivedState4 = (DerivedState) obj2;
                                Object obj15 = map.get(derivedState4);
                                SnapshotMutationPolicy<?> policy4 = derivedState4.getPolicy();
                                if (policy4 == null) {
                                    policy4 = SnapshotStateKt.structuralEqualityPolicy();
                                }
                                if (policy4.equivalent(derivedState4.getCurrentRecord().getCurrentValue(), obj15)) {
                                    Boolean.valueOf(this.statesToReread.add(derivedState4));
                                } else {
                                    Object obj16 = mutableScatterMap4.get(derivedState4);
                                    if (obj16 != null) {
                                        if (obj16 instanceof MutableScatterSet) {
                                            MutableScatterSet mutableScatterSet8 = (MutableScatterSet) obj16;
                                            Object[] objArr14 = mutableScatterSet8.elements;
                                            long[] jArr13 = mutableScatterSet8.metadata;
                                            int length9 = jArr13.length - 2;
                                            if (length9 >= 0) {
                                                int i36 = 0;
                                                while (true) {
                                                    long j11 = jArr13[i36];
                                                    if ((((~j11) << 7) & j11 & (-9187201950435737472L)) == -9187201950435737472L) {
                                                        if (i36 != length9) {
                                                            break;
                                                            break;
                                                        }
                                                        i36++;
                                                    } else {
                                                        int i37 = 8 - ((~(i36 - length9)) >>> 31);
                                                        for (int i38 = 0; i38 < i37; i38++) {
                                                            if ((j11 & 255) < 128) {
                                                                mutableScatterSet.add(objArr14[(i36 << 3) + i38]);
                                                                z = true;
                                                            }
                                                            j11 >>= 8;
                                                        }
                                                        if (i37 != 8) {
                                                            break;
                                                        }
                                                        if (i36 != length9) {
                                                            break;
                                                        }
                                                        i36++;
                                                    }
                                                }
                                            }
                                        } else {
                                            mutableScatterSet.add(obj16);
                                            z = true;
                                        }
                                    }
                                    Unit unit5 = Unit.INSTANCE;
                                }
                            }
                            obj = mutableScatterMap4.get(next);
                            if (obj == null) {
                                if (obj instanceof MutableScatterSet) {
                                    MutableScatterSet mutableScatterSet9 = (MutableScatterSet) obj;
                                    objArr = mutableScatterSet9.elements;
                                    jArr = mutableScatterSet9.metadata;
                                    length = jArr.length - 2;
                                    if (length >= 0) {
                                        i = 0;
                                        while (true) {
                                            j = jArr[i];
                                            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                                                i2 = 8 - ((~(i - length)) >>> 31);
                                                for (i3 = 0; i3 < i2; i3++) {
                                                    if ((j & 255) < 128) {
                                                        mutableScatterSet.add(objArr[(i << 3) + i3]);
                                                        z = true;
                                                    }
                                                    j >>= 8;
                                                }
                                                if (i2 == 8) {
                                                    break;
                                                }
                                            }
                                            if (i != length) {
                                                break;
                                            }
                                            i++;
                                        }
                                    }
                                } else {
                                    mutableScatterSet.add(obj);
                                    z = true;
                                }
                            }
                        } else {
                            it = it3;
                            mutableScatterMap = mutableScatterMap3;
                        }
                    } else {
                        if (ScopeMap.m3555containsimpl(mutableScatterMap3, next)) {
                            it = it3;
                            mutableScatterMap = mutableScatterMap3;
                        } else {
                            it = it3;
                            mutableScatterMap = mutableScatterMap3;
                        }
                        obj = mutableScatterMap4.get(next);
                        if (obj == null) {
                            if (obj instanceof MutableScatterSet) {
                                MutableScatterSet mutableScatterSet10 = (MutableScatterSet) obj;
                                objArr = mutableScatterSet10.elements;
                                jArr = mutableScatterSet10.metadata;
                                length = jArr.length - 2;
                                if (length >= 0) {
                                    i = 0;
                                    while (true) {
                                        j = jArr[i];
                                        if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                                            i2 = 8 - ((~(i - length)) >>> 31);
                                            while (i3 < i2) {
                                                if ((j & 255) < 128) {
                                                    mutableScatterSet.add(objArr[(i << 3) + i3]);
                                                    z = true;
                                                }
                                                j >>= 8;
                                            }
                                            if (i2 == 8) {
                                                break;
                                                break;
                                            }
                                        }
                                        if (i != length) {
                                            break;
                                            break;
                                        }
                                        i++;
                                    }
                                }
                            } else {
                                mutableScatterSet.add(obj);
                                z = true;
                            }
                        }
                    }
                    it3 = it;
                    mutableScatterMap3 = mutableScatterMap;
                }
            }
            if (this.statesToReread.getSize() != 0) {
                MutableVector<DerivedState<?>> mutableVector = this.statesToReread;
                DerivedState<?>[] derivedStateArr = mutableVector.content;
                int size = mutableVector.getSize();
                for (int i39 = 0; i39 < size; i39++) {
                    rereadDerivedState(derivedStateArr[i39]);
                }
                this.statesToReread.clear();
            }
            return z;
        }

        public final void rereadDerivedState(DerivedState<?> derivedState) {
            long[] jArr;
            MutableObjectIntMap<Object> mutableObjectIntMap;
            MutableScatterMap<Object, MutableObjectIntMap<Object>> mutableScatterMap = this.scopeToValues;
            int iM = UByte$$ExternalSyntheticBackport0.m(SnapshotKt.currentSnapshot().getSnapshotId());
            Object obj = this.valueToScopes.get(derivedState);
            if (obj == null) {
                return;
            }
            DefaultConstructorMarker defaultConstructorMarker = null;
            int i = 1;
            int i2 = 0;
            if (!(obj instanceof MutableScatterSet)) {
                MutableObjectIntMap<Object> mutableObjectIntMap2 = mutableScatterMap.get(obj);
                if (mutableObjectIntMap2 == null) {
                    mutableObjectIntMap2 = new MutableObjectIntMap<>(i2, i, defaultConstructorMarker);
                    mutableScatterMap.set(obj, mutableObjectIntMap2);
                    Unit unit = Unit.INSTANCE;
                }
                recordRead(derivedState, iM, obj, mutableObjectIntMap2);
                return;
            }
            MutableScatterSet mutableScatterSet = (MutableScatterSet) obj;
            Object[] objArr = mutableScatterSet.elements;
            long[] jArr2 = mutableScatterSet.metadata;
            int length = jArr2.length - 2;
            if (length < 0) {
                return;
            }
            int i3 = 0;
            while (true) {
                long j = jArr2[i3];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i4 = 8;
                    int i5 = 8 - ((~(i3 - length)) >>> 31);
                    int i6 = 0;
                    while (i6 < i5) {
                        if ((j & 255) < 128) {
                            Object obj2 = objArr[(i3 << 3) + i6];
                            MutableObjectIntMap<Object> mutableObjectIntMap3 = mutableScatterMap.get(obj2);
                            if (mutableObjectIntMap3 == null) {
                                mutableObjectIntMap = new MutableObjectIntMap<>(i2, i, defaultConstructorMarker);
                                mutableScatterMap.set(obj2, mutableObjectIntMap);
                                Unit unit2 = Unit.INSTANCE;
                            } else {
                                mutableObjectIntMap = mutableObjectIntMap3;
                            }
                            recordRead(derivedState, iM, obj2, mutableObjectIntMap);
                        }
                        j >>= i4;
                        i6++;
                        i4 = i4;
                        jArr2 = jArr2;
                    }
                    jArr = jArr2;
                    if (i5 != i4) {
                        return;
                    }
                } else {
                    jArr = jArr2;
                }
                if (i3 == length) {
                    return;
                }
                i3++;
                jArr2 = jArr;
            }
        }

        /* JADX WARN: Code duplicated, block: B:14:0x0045 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:15:0x0047 A[LOOP:0: B:5:0x0012->B:15:0x0047, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:19:0x004a A[EDGE_INSN: B:19:0x004a->B:16:0x004a BREAK  A[LOOP:0: B:5:0x0012->B:15:0x0047], SYNTHETIC] */
        public final void notifyInvalidatedScopes() {
            MutableScatterSet<Object> mutableScatterSet = this.invalidated;
            MutableScatterSet<Object> mutableScatterSet2 = mutableScatterSet;
            Function1<Object, Unit> function1 = this.onChanged;
            Object[] objArr = mutableScatterSet2.elements;
            long[] jArr = mutableScatterSet2.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i = 0;
                while (true) {
                    long j = jArr[i];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) == -9187201950435737472L) {
                        if (i != length) {
                            break;
                            break;
                        }
                        i++;
                    } else {
                        int i2 = 8 - ((~(i - length)) >>> 31);
                        for (int i3 = 0; i3 < i2; i3++) {
                            if ((255 & j) < 128) {
                                function1.invoke(objArr[(i << 3) + i3]);
                            }
                            j >>= 8;
                        }
                        if (i2 != 8) {
                            break;
                        } else if (i != length) {
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            }
            mutableScatterSet.clear();
        }
    }

    public final void clear(Object scope) {
        synchronized (this.observedScopeMapsLock) {
            MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
            int size = mutableVector.getSize();
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                ObservedScopeMap observedScopeMap = mutableVector.content[i2];
                observedScopeMap.clearScopeObservations(scope);
                if (!observedScopeMap.hasScopeObservations()) {
                    i++;
                } else if (i > 0) {
                    mutableVector.content[i2 - i] = mutableVector.content[i2];
                }
            }
            int i3 = size - i;
            ArraysKt.fill(mutableVector.content, (Object) null, i3, size);
            mutableVector.setSize(i3);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void clearIf(Function1<Object, Boolean> predicate) {
        synchronized (this.observedScopeMapsLock) {
            MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
            int size = mutableVector.getSize();
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                ObservedScopeMap observedScopeMap = mutableVector.content[i2];
                observedScopeMap.removeScopeIf(predicate);
                if (!observedScopeMap.hasScopeObservations()) {
                    i++;
                } else if (i > 0) {
                    mutableVector.content[i2 - i] = mutableVector.content[i2];
                }
            }
            int i3 = size - i;
            ArraysKt.fill(mutableVector.content, (Object) null, i3, size);
            mutableVector.setSize(i3);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void clear() {
        synchronized (this.observedScopeMapsLock) {
            MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
            ObservedScopeMap[] observedScopeMapArr = mutableVector.content;
            int size = mutableVector.getSize();
            for (int i = 0; i < size; i++) {
                observedScopeMapArr[i].clear();
            }
            Unit unit = Unit.INSTANCE;
        }
    }
}
