package com.google.firebase.firestore.local;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Supplier;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class IndexBackfiller {
    public static final String LOG_TAG = "IndexBackfiller";
    public static final int MAX_DOCUMENTS_TO_PROCESS = 50;
    public IndexManager indexManager;
    public LocalDocumentsView localDocumentsView;
    public int maxDocumentsToProcess = 50;
    public final Persistence persistence;
    public final RemoteDocumentCache remoteDocumentCache;
    public final Scheduler scheduler;
    public static final long INITIAL_BACKFILL_DELAY_MS = TimeUnit.SECONDS.toMillis(15);
    public static final long REGULAR_BACKFILL_DELAY_MS = TimeUnit.MINUTES.toMillis(1);

    public class Scheduler implements com.google.firebase.firestore.local.Scheduler {
        public final AsyncQueue asyncQueue;

        @Nullable
        public AsyncQueue.DelayedTask backfillTask;
        public boolean hasRun = false;

        public Scheduler(AsyncQueue asyncQueue) {
            this.asyncQueue = asyncQueue;
        }

        private void scheduleBackfill() {
            this.backfillTask = this.asyncQueue.enqueueAfterDelay(AsyncQueue.TimerId.INDEX_BACKFILL, this.hasRun ? IndexBackfiller.REGULAR_BACKFILL_DELAY_MS : IndexBackfiller.INITIAL_BACKFILL_DELAY_MS, new Runnable() { // from class: b.e.b.d.d.d
                @Override // java.lang.Runnable
                public final void run() {
                    this.f395a.a();
                }
            });
        }

        public /* synthetic */ void a() {
            Logger.debug(IndexBackfiller.LOG_TAG, "Documents written: %s", Integer.valueOf(IndexBackfiller.this.backfill()));
            this.hasRun = true;
            scheduleBackfill();
        }

        @Override // com.google.firebase.firestore.local.Scheduler
        public void start() {
            Assert.hardAssert(Persistence.INDEXING_SUPPORT_ENABLED, "Indexing support not enabled", new Object[0]);
            scheduleBackfill();
        }

        @Override // com.google.firebase.firestore.local.Scheduler
        public void stop() {
            Assert.hardAssert(Persistence.INDEXING_SUPPORT_ENABLED, "Indexing support not enabled", new Object[0]);
            AsyncQueue.DelayedTask delayedTask = this.backfillTask;
            if (delayedTask != null) {
                delayedTask.cancel();
            }
        }
    }

    public IndexBackfiller(Persistence persistence, AsyncQueue asyncQueue) {
        this.persistence = persistence;
        this.scheduler = new Scheduler(asyncQueue);
        this.remoteDocumentCache = persistence.getRemoteDocumentCache();
    }

    private FieldIndex.IndexOffset getExistingOffset(Collection<FieldIndex> collection) {
        FieldIndex.IndexOffset offset = null;
        for (FieldIndex fieldIndex : collection) {
            if (offset == null || fieldIndex.getIndexState().getOffset().compareTo(offset) < 0) {
                offset = fieldIndex.getIndexState().getOffset();
            }
        }
        return offset == null ? FieldIndex.IndexOffset.NONE : offset;
    }

    private FieldIndex.IndexOffset getNewOffset(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap, FieldIndex.IndexOffset indexOffset) {
        if (immutableSortedMap.isEmpty()) {
            return FieldIndex.IndexOffset.create(this.remoteDocumentCache.getLatestReadTime());
        }
        Iterator<Map.Entry<DocumentKey, Document>> it = immutableSortedMap.iterator();
        while (it.hasNext()) {
            FieldIndex.IndexOffset indexOffsetFromDocument = FieldIndex.IndexOffset.fromDocument(it.next().getValue());
            if (indexOffsetFromDocument.compareTo(indexOffset) > 0) {
                indexOffset = indexOffsetFromDocument;
            }
        }
        return indexOffset;
    }

    private int writeEntriesForCollectionGroup(LocalDocumentsView localDocumentsView, String str, int i) {
        FieldIndex.IndexOffset existingOffset = getExistingOffset(this.indexManager.getFieldIndexes(str));
        ImmutableSortedMap<DocumentKey, Document> documents = localDocumentsView.getDocuments(str, existingOffset, i);
        this.indexManager.updateIndexEntries(documents);
        this.indexManager.updateCollectionGroup(str, getNewOffset(documents, existingOffset));
        return documents.size();
    }

    private int writeIndexEntries(LocalDocumentsView localDocumentsView) {
        HashSet hashSet = new HashSet();
        int iWriteEntriesForCollectionGroup = this.maxDocumentsToProcess;
        while (iWriteEntriesForCollectionGroup > 0) {
            String nextCollectionGroupToUpdate = this.indexManager.getNextCollectionGroupToUpdate();
            if (nextCollectionGroupToUpdate == null || hashSet.contains(nextCollectionGroupToUpdate)) {
                break;
            }
            Logger.debug(LOG_TAG, "Processing collection: %s", nextCollectionGroupToUpdate);
            iWriteEntriesForCollectionGroup -= writeEntriesForCollectionGroup(localDocumentsView, nextCollectionGroupToUpdate, iWriteEntriesForCollectionGroup);
            hashSet.add(nextCollectionGroupToUpdate);
        }
        return this.maxDocumentsToProcess - iWriteEntriesForCollectionGroup;
    }

    public /* synthetic */ Integer a() {
        return Integer.valueOf(writeIndexEntries(this.localDocumentsView));
    }

    public int backfill() {
        Assert.hardAssert(this.localDocumentsView != null, "setLocalDocumentsView() not called", new Object[0]);
        Assert.hardAssert(this.indexManager != null, "setIndexManager() not called", new Object[0]);
        return ((Integer) this.persistence.runTransaction("Backfill Indexes", new Supplier() { // from class: b.e.b.d.d.e
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f397a.a();
            }
        })).intValue();
    }

    public Scheduler getScheduler() {
        return this.scheduler;
    }

    public void setIndexManager(IndexManager indexManager) {
        this.indexManager = indexManager;
    }

    public void setLocalDocumentsView(LocalDocumentsView localDocumentsView) {
        this.localDocumentsView = localDocumentsView;
    }

    @VisibleForTesting
    public void setMaxDocumentsToProcess(int i) {
        this.maxDocumentsToProcess = i;
    }
}
