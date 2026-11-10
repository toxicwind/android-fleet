package androidx.room;

import android.annotation.SuppressLint;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.LiveData;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class RoomTrackingLiveData<T> extends LiveData<T> {
    public final Callable<T> mComputeFunction;
    public final InvalidationLiveDataContainer mContainer;
    public final RoomDatabase mDatabase;
    public final boolean mInTransaction;
    public final InvalidationTracker.Observer mObserver;
    public final AtomicBoolean mInvalid = new AtomicBoolean(true);
    public final AtomicBoolean mComputing = new AtomicBoolean(false);
    public final AtomicBoolean mRegisteredObserver = new AtomicBoolean(false);
    public final Runnable mRefreshRunnable = new Runnable() { // from class: androidx.room.RoomTrackingLiveData.1
        /* JADX WARN: Bottom block not found for handler: all -> 0x0054 */
        @Override // java.lang.Runnable
        @androidx.annotation.WorkerThread
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r5 = this;
                androidx.room.RoomTrackingLiveData r0 = androidx.room.RoomTrackingLiveData.this
                java.util.concurrent.atomic.AtomicBoolean r0 = r0.mRegisteredObserver
                r1 = 1
                r2 = 0
                boolean r0 = r0.compareAndSet(r2, r1)
                if (r0 == 0) goto L1b
                androidx.room.RoomTrackingLiveData r0 = androidx.room.RoomTrackingLiveData.this
                androidx.room.RoomDatabase r0 = r0.mDatabase
                androidx.room.InvalidationTracker r0 = r0.getInvalidationTracker()
                androidx.room.RoomTrackingLiveData r3 = androidx.room.RoomTrackingLiveData.this
                androidx.room.InvalidationTracker$Observer r3 = r3.mObserver
                r0.addWeakObserver(r3)
            L1b:
                androidx.room.RoomTrackingLiveData r0 = androidx.room.RoomTrackingLiveData.this
                java.util.concurrent.atomic.AtomicBoolean r0 = r0.mComputing
                boolean r0 = r0.compareAndSet(r2, r1)
                if (r0 == 0) goto L5d
                r0 = 0
                r3 = r0
                r0 = r2
            L28:
                androidx.room.RoomTrackingLiveData r4 = androidx.room.RoomTrackingLiveData.this     // Catch: java.lang.Throwable -> L54
                java.util.concurrent.atomic.AtomicBoolean r4 = r4.mInvalid     // Catch: java.lang.Throwable -> L54
                boolean r4 = r4.compareAndSet(r1, r2)     // Catch: java.lang.Throwable -> L54
                if (r4 == 0) goto L45
                androidx.room.RoomTrackingLiveData r0 = androidx.room.RoomTrackingLiveData.this     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L54
                java.util.concurrent.Callable<T> r0 = r0.mComputeFunction     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L54
                java.lang.Object r3 = r0.call()     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L54
                r0 = r1
                goto L28
            L3c:
                r0 = move-exception
                java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch: java.lang.Throwable -> L54
                java.lang.String r3 = "Exception while computing database live data."
                r1.<init>(r3, r0)     // Catch: java.lang.Throwable -> L54
                throw r1     // Catch: java.lang.Throwable -> L54
            L45:
                if (r0 == 0) goto L4c
                androidx.room.RoomTrackingLiveData r4 = androidx.room.RoomTrackingLiveData.this     // Catch: java.lang.Throwable -> L54
                androidx.room.RoomTrackingLiveData.access$000(r4, r3)     // Catch: java.lang.Throwable -> L54
            L4c:
                androidx.room.RoomTrackingLiveData r3 = androidx.room.RoomTrackingLiveData.this
                java.util.concurrent.atomic.AtomicBoolean r3 = r3.mComputing
                r3.set(r2)
                goto L5e
            L54:
                r0 = move-exception
                androidx.room.RoomTrackingLiveData r1 = androidx.room.RoomTrackingLiveData.this
                java.util.concurrent.atomic.AtomicBoolean r1 = r1.mComputing
                r1.set(r2)
                throw r0
            L5d:
                r0 = r2
            L5e:
                if (r0 == 0) goto L6a
                androidx.room.RoomTrackingLiveData r0 = androidx.room.RoomTrackingLiveData.this
                java.util.concurrent.atomic.AtomicBoolean r0 = r0.mInvalid
                boolean r0 = r0.get()
                if (r0 != 0) goto L1b
            L6a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomTrackingLiveData.AnonymousClass1.run():void");
        }
    };
    public final Runnable mInvalidationRunnable = new Runnable() { // from class: androidx.room.RoomTrackingLiveData.2
        @Override // java.lang.Runnable
        @MainThread
        public void run() {
            boolean zHasActiveObservers = RoomTrackingLiveData.this.hasActiveObservers();
            if (RoomTrackingLiveData.this.mInvalid.compareAndSet(false, true) && zHasActiveObservers) {
                RoomTrackingLiveData.this.getQueryExecutor().execute(RoomTrackingLiveData.this.mRefreshRunnable);
            }
        }
    };

    @SuppressLint({"RestrictedApi"})
    public RoomTrackingLiveData(RoomDatabase roomDatabase, InvalidationLiveDataContainer invalidationLiveDataContainer, boolean z, Callable<T> callable, String[] strArr) {
        this.mDatabase = roomDatabase;
        this.mInTransaction = z;
        this.mComputeFunction = callable;
        this.mContainer = invalidationLiveDataContainer;
        this.mObserver = new InvalidationTracker.Observer(strArr) { // from class: androidx.room.RoomTrackingLiveData.3
            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                ArchTaskExecutor.getInstance().executeOnMainThread(RoomTrackingLiveData.this.mInvalidationRunnable);
            }
        };
    }

    public Executor getQueryExecutor() {
        return this.mInTransaction ? this.mDatabase.getTransactionExecutor() : this.mDatabase.getQueryExecutor();
    }

    @Override // androidx.lifecycle.LiveData
    public void onActive() {
        this.mContainer.onActive(this);
        getQueryExecutor().execute(this.mRefreshRunnable);
    }

    @Override // androidx.lifecycle.LiveData
    public void onInactive() {
        this.mContainer.onInactive(this);
    }
}
