package com.google.firebase.firestore.local;

import android.util.SparseArray;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Logger;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class LruGarbageCollector {
    public static final long INITIAL_GC_DELAY_MS = TimeUnit.MINUTES.toMillis(1);
    public static final long REGULAR_GC_DELAY_MS = TimeUnit.MINUTES.toMillis(5);
    public final LruDelegate delegate;
    public final Params params;

    public class GCScheduler implements Scheduler {
        public final AsyncQueue asyncQueue;

        @Nullable
        public AsyncQueue.DelayedTask gcTask;
        public boolean hasRun = false;
        public final LocalStore localStore;

        public GCScheduler(AsyncQueue asyncQueue, LocalStore localStore) {
            this.asyncQueue = asyncQueue;
            this.localStore = localStore;
        }

        private void scheduleGC() {
            this.gcTask = this.asyncQueue.enqueueAfterDelay(AsyncQueue.TimerId.GARBAGE_COLLECTION, this.hasRun ? LruGarbageCollector.REGULAR_GC_DELAY_MS : LruGarbageCollector.INITIAL_GC_DELAY_MS, new Runnable() { // from class: b.e.b.d.d.x
                @Override // java.lang.Runnable
                public final void run() {
                    this.f467a.a();
                }
            });
        }

        public /* synthetic */ void a() {
            this.localStore.collectGarbage(LruGarbageCollector.this);
            this.hasRun = true;
            scheduleGC();
        }

        @Override // com.google.firebase.firestore.local.Scheduler
        public void start() {
            if (LruGarbageCollector.this.params.minBytesThreshold != -1) {
                scheduleGC();
            }
        }

        @Override // com.google.firebase.firestore.local.Scheduler
        public void stop() {
            AsyncQueue.DelayedTask delayedTask = this.gcTask;
            if (delayedTask != null) {
                delayedTask.cancel();
            }
        }
    }

    public static class Params {
        public static final long COLLECTION_DISABLED = -1;
        public static final long DEFAULT_CACHE_SIZE_BYTES = 104857600;
        public static final int DEFAULT_COLLECTION_PERCENTILE = 10;
        public static final int DEFAULT_MAX_SEQUENCE_NUMBERS_TO_COLLECT = 1000;
        public final int maximumSequenceNumbersToCollect;
        public final long minBytesThreshold;
        public final int percentileToCollect;

        public Params(long j, int i, int i2) {
            this.minBytesThreshold = j;
            this.percentileToCollect = i;
            this.maximumSequenceNumbersToCollect = i2;
        }

        public static Params Default() {
            return new Params(104857600L, 10, 1000);
        }

        public static Params Disabled() {
            return new Params(-1L, 0, 0);
        }

        public static Params WithCacheSizeBytes(long j) {
            return new Params(j, 10, 1000);
        }
    }

    public static class Results {
        public final int documentsRemoved;
        public final boolean hasRun;
        public final int sequenceNumbersCollected;
        public final int targetsRemoved;

        public Results(boolean z, int i, int i2, int i3) {
            this.hasRun = z;
            this.sequenceNumbersCollected = i;
            this.targetsRemoved = i2;
            this.documentsRemoved = i3;
        }

        public static Results DidNotRun() {
            return new Results(false, 0, 0, 0);
        }

        public int getDocumentsRemoved() {
            return this.documentsRemoved;
        }

        public int getSequenceNumbersCollected() {
            return this.sequenceNumbersCollected;
        }

        public int getTargetsRemoved() {
            return this.targetsRemoved;
        }

        public boolean hasRun() {
            return this.hasRun;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static class RollingSequenceNumberBuffer {
        public static final Comparator<Long> COMPARATOR = new Comparator() { // from class: b.e.b.d.d.y
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((Long) obj2).compareTo((Long) obj);
            }
        };
        public final int maxElements;
        public final PriorityQueue<Long> queue;

        public RollingSequenceNumberBuffer(int i) {
            this.maxElements = i;
            this.queue = new PriorityQueue<>(i, COMPARATOR);
        }

        public void addElement(Long l) {
            if (this.queue.size() < this.maxElements) {
                this.queue.add(l);
                return;
            }
            if (l.longValue() < this.queue.peek().longValue()) {
                this.queue.poll();
                this.queue.add(l);
            }
        }

        public long getMaxValue() {
            return this.queue.peek().longValue();
        }
    }

    public LruGarbageCollector(LruDelegate lruDelegate, Params params) {
        this.delegate = lruDelegate;
        this.params = params;
    }

    private Results runGarbageCollection(SparseArray<?> sparseArray) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int iCalculateQueryCount = calculateQueryCount(this.params.percentileToCollect);
        if (iCalculateQueryCount > this.params.maximumSequenceNumbersToCollect) {
            StringBuilder sbA = a.a("Capping sequence numbers to collect down to the maximum of ");
            sbA.append(this.params.maximumSequenceNumbersToCollect);
            sbA.append(" from ");
            sbA.append(iCalculateQueryCount);
            Logger.debug("LruGarbageCollector", sbA.toString(), new Object[0]);
            iCalculateQueryCount = this.params.maximumSequenceNumbersToCollect;
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        long nthSequenceNumber = getNthSequenceNumber(iCalculateQueryCount);
        long jCurrentTimeMillis3 = System.currentTimeMillis();
        int iRemoveTargets = removeTargets(nthSequenceNumber, sparseArray);
        long jCurrentTimeMillis4 = System.currentTimeMillis();
        int iRemoveOrphanedDocuments = removeOrphanedDocuments(nthSequenceNumber);
        long jCurrentTimeMillis5 = System.currentTimeMillis();
        if (Logger.isDebugEnabled()) {
            StringBuilder sbB = a.b("LRU Garbage Collection:\n", "\tCounted targets in ");
            sbB.append(jCurrentTimeMillis2 - jCurrentTimeMillis);
            sbB.append("ms\n");
            StringBuilder sbA2 = a.a(sbB.toString());
            sbA2.append(String.format(Locale.ROOT, "\tDetermined least recently used %d sequence numbers in %dms\n", Integer.valueOf(iCalculateQueryCount), Long.valueOf(jCurrentTimeMillis3 - jCurrentTimeMillis2)));
            StringBuilder sbA3 = a.a(sbA2.toString());
            sbA3.append(String.format(Locale.ROOT, "\tRemoved %d targets in %dms\n", Integer.valueOf(iRemoveTargets), Long.valueOf(jCurrentTimeMillis4 - jCurrentTimeMillis3)));
            StringBuilder sbA4 = a.a(sbA3.toString());
            sbA4.append(String.format(Locale.ROOT, "\tRemoved %d documents in %dms\n", Integer.valueOf(iRemoveOrphanedDocuments), Long.valueOf(jCurrentTimeMillis5 - jCurrentTimeMillis4)));
            StringBuilder sbA5 = a.a(sbA4.toString());
            sbA5.append(String.format(Locale.ROOT, "Total Duration: %dms", Long.valueOf(jCurrentTimeMillis5 - jCurrentTimeMillis)));
            Logger.debug("LruGarbageCollector", sbA5.toString(), new Object[0]);
        }
        return new Results(true, iCalculateQueryCount, iRemoveTargets, iRemoveOrphanedDocuments);
    }

    public int calculateQueryCount(int i) {
        return (int) ((i / 100.0f) * this.delegate.getSequenceNumberCount());
    }

    public Results collect(SparseArray<?> sparseArray) {
        if (this.params.minBytesThreshold == -1) {
            Logger.debug("LruGarbageCollector", "Garbage collection skipped; disabled", new Object[0]);
            return Results.DidNotRun();
        }
        long byteSize = getByteSize();
        if (byteSize >= this.params.minBytesThreshold) {
            return runGarbageCollection(sparseArray);
        }
        Logger.debug("LruGarbageCollector", "Garbage collection skipped; Cache size " + byteSize + " is lower than threshold " + this.params.minBytesThreshold, new Object[0]);
        return Results.DidNotRun();
    }

    public long getByteSize() {
        return this.delegate.getByteSize();
    }

    public long getNthSequenceNumber(int i) {
        if (i == 0) {
            return -1L;
        }
        final RollingSequenceNumberBuffer rollingSequenceNumberBuffer = new RollingSequenceNumberBuffer(i);
        this.delegate.forEachTarget(new Consumer() { // from class: b.e.b.d.d.z
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                rollingSequenceNumberBuffer.addElement(Long.valueOf(((TargetData) obj).getSequenceNumber()));
            }
        });
        LruDelegate lruDelegate = this.delegate;
        Objects.requireNonNull(rollingSequenceNumberBuffer);
        lruDelegate.forEachOrphanedDocumentSequenceNumber(new Consumer() { // from class: b.e.b.d.d.a
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                rollingSequenceNumberBuffer.addElement((Long) obj);
            }
        });
        return rollingSequenceNumberBuffer.getMaxValue();
    }

    public GCScheduler newScheduler(AsyncQueue asyncQueue, LocalStore localStore) {
        return new GCScheduler(asyncQueue, localStore);
    }

    public int removeOrphanedDocuments(long j) {
        return this.delegate.removeOrphanedDocuments(j);
    }

    public int removeTargets(long j, SparseArray<?> sparseArray) {
        return this.delegate.removeTargets(j, sparseArray);
    }
}
