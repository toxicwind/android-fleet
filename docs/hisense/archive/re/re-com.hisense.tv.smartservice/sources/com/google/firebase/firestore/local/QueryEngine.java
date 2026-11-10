package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Util;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class QueryEngine {
    public static final String LOG_TAG = "QueryEngine";
    public IndexManager indexManager;
    public boolean initialized;
    public LocalDocumentsView localDocumentsView;

    private ImmutableSortedMap<DocumentKey, Document> appendRemainingResults(Iterable<Document> iterable, Query query, FieldIndex.IndexOffset indexOffset) {
        ImmutableSortedMap<DocumentKey, Document> documentsMatchingQuery = this.localDocumentsView.getDocumentsMatchingQuery(query, indexOffset);
        for (Document document : iterable) {
            documentsMatchingQuery = documentsMatchingQuery.insert(document.getKey(), document);
        }
        return documentsMatchingQuery;
    }

    private ImmutableSortedSet<Document> applyQuery(Query query, ImmutableSortedMap<DocumentKey, Document> immutableSortedMap) {
        ImmutableSortedSet<Document> immutableSortedSet = new ImmutableSortedSet<>(Collections.emptyList(), query.comparator());
        Iterator<Map.Entry<DocumentKey, Document>> it = immutableSortedMap.iterator();
        while (it.hasNext()) {
            Document value = it.next().getValue();
            if (query.matches(value)) {
                immutableSortedSet = immutableSortedSet.insert(value);
            }
        }
        return immutableSortedSet;
    }

    private ImmutableSortedMap<DocumentKey, Document> executeFullCollectionScan(Query query) {
        if (Logger.isDebugEnabled()) {
            Logger.debug(LOG_TAG, "Using full collection scan to execute query: %s", query.toString());
        }
        return this.localDocumentsView.getDocumentsMatchingQuery(query, FieldIndex.IndexOffset.NONE);
    }

    private boolean needsRefill(Query.LimitType limitType, ImmutableSortedSet<Document> immutableSortedSet, ImmutableSortedSet<DocumentKey> immutableSortedSet2, SnapshotVersion snapshotVersion) {
        if (immutableSortedSet2.size() != immutableSortedSet.size()) {
            return true;
        }
        Document maxEntry = limitType == Query.LimitType.LIMIT_TO_FIRST ? immutableSortedSet.getMaxEntry() : immutableSortedSet.getMinEntry();
        if (maxEntry == null) {
            return false;
        }
        return maxEntry.hasPendingWrites() || maxEntry.getVersion().compareTo(snapshotVersion) > 0;
    }

    private ImmutableSortedMap<DocumentKey, Document> performQueryUsingIndex(Query query, Target target) {
        FieldIndex fieldIndex;
        if (query.matchesAllDocuments() || (fieldIndex = this.indexManager.getFieldIndex(query.toTarget())) == null) {
            return null;
        }
        return appendRemainingResults(Util.values(this.localDocumentsView.getDocuments(this.indexManager.getDocumentsMatchingTarget(fieldIndex, target))), query, fieldIndex.getIndexState().getOffset());
    }

    private ImmutableSortedMap<DocumentKey, Document> performQueryUsingRemoteKeys(Query query, ImmutableSortedSet<DocumentKey> immutableSortedSet, SnapshotVersion snapshotVersion) {
        if (query.matchesAllDocuments() || snapshotVersion.equals(SnapshotVersion.NONE)) {
            return null;
        }
        ImmutableSortedSet<Document> immutableSortedSetApplyQuery = applyQuery(query, this.localDocumentsView.getDocuments(immutableSortedSet));
        if ((query.hasLimitToFirst() || query.hasLimitToLast()) && needsRefill(query.getLimitType(), immutableSortedSetApplyQuery, immutableSortedSet, snapshotVersion)) {
            return null;
        }
        if (Logger.isDebugEnabled()) {
            Logger.debug(LOG_TAG, "Re-using previous result from %s to execute query: %s", snapshotVersion.toString(), query.toString());
        }
        return appendRemainingResults(immutableSortedSetApplyQuery, query, FieldIndex.IndexOffset.create(snapshotVersion));
    }

    public ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingQuery(Query query, SnapshotVersion snapshotVersion, ImmutableSortedSet<DocumentKey> immutableSortedSet) {
        Assert.hardAssert(this.initialized, "initialize() not called", new Object[0]);
        ImmutableSortedMap<DocumentKey, Document> immutableSortedMapPerformQueryUsingIndex = performQueryUsingIndex(query, query.toTarget());
        if (immutableSortedMapPerformQueryUsingIndex != null) {
            return immutableSortedMapPerformQueryUsingIndex;
        }
        ImmutableSortedMap<DocumentKey, Document> immutableSortedMapPerformQueryUsingRemoteKeys = performQueryUsingRemoteKeys(query, immutableSortedSet, snapshotVersion);
        return immutableSortedMapPerformQueryUsingRemoteKeys != null ? immutableSortedMapPerformQueryUsingRemoteKeys : executeFullCollectionScan(query);
    }

    public void initialize(LocalDocumentsView localDocumentsView, IndexManager indexManager) {
        this.localDocumentsView = localDocumentsView;
        this.indexManager = indexManager;
        this.initialized = true;
    }
}
