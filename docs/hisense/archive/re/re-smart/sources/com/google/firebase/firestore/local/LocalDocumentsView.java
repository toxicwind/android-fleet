package com.google.firebase.firestore.local;

import androidx.annotation.VisibleForTesting;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.util.Assert;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class LocalDocumentsView {
    public final DocumentOverlayCache documentOverlayCache;
    public final IndexManager indexManager;
    public final MutationQueue mutationQueue;
    public final RemoteDocumentCache remoteDocumentCache;

    public LocalDocumentsView(RemoteDocumentCache remoteDocumentCache, MutationQueue mutationQueue, DocumentOverlayCache documentOverlayCache, IndexManager indexManager) {
        this.remoteDocumentCache = remoteDocumentCache;
        this.mutationQueue = mutationQueue;
        this.documentOverlayCache = documentOverlayCache;
        this.indexManager = indexManager;
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingCollectionGroupQuery(Query query, FieldIndex.IndexOffset indexOffset) {
        Assert.hardAssert(query.getPath().isEmpty(), "Currently we only support collection group queries at the root.", new Object[0]);
        String collectionGroup = query.getCollectionGroup();
        ImmutableSortedMap immutableSortedMapInsert = DocumentCollections.EMPTY_DOCUMENT_MAP;
        Iterator<ResourcePath> it = this.indexManager.getCollectionParents(collectionGroup).iterator();
        while (it.hasNext()) {
            immutableSortedMapInsert = immutableSortedMapInsert;
            for (Map.Entry<DocumentKey, Document> entry : getDocumentsMatchingCollectionQuery(query.asCollectionQueryAtPath(it.next().append(collectionGroup)), indexOffset)) {
                immutableSortedMapInsert = immutableSortedMapInsert.insert(entry.getKey(), entry.getValue());
            }
        }
        return immutableSortedMapInsert;
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingCollectionQuery(Query query, FieldIndex.IndexOffset indexOffset) {
        Map<DocumentKey, MutableDocument> all = this.remoteDocumentCache.getAll(query.getPath(), indexOffset);
        Map<DocumentKey, Mutation> overlays = this.documentOverlayCache.getOverlays(query.getPath(), -1);
        for (Map.Entry<DocumentKey, Mutation> entry : overlays.entrySet()) {
            if (!all.containsKey(entry.getKey())) {
                all.put(entry.getKey(), MutableDocument.newInvalidDocument(entry.getKey()));
            }
        }
        ImmutableSortedMap immutableSortedMapInsert = DocumentCollections.EMPTY_DOCUMENT_MAP;
        for (Map.Entry<DocumentKey, MutableDocument> entry2 : all.entrySet()) {
            Mutation mutation = overlays.get(entry2.getKey());
            if (mutation != null) {
                mutation.applyToLocalView(entry2.getValue(), null, Timestamp.now());
            }
            if (query.matches(entry2.getValue())) {
                immutableSortedMapInsert = immutableSortedMapInsert.insert(entry2.getKey(), entry2.getValue());
            }
        }
        return immutableSortedMapInsert;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingDocumentQuery(ResourcePath resourcePath) {
        ImmutableSortedMap<DocumentKey, ?> immutableSortedMap = DocumentCollections.EMPTY_DOCUMENT_MAP;
        Document document = getDocument(new DocumentKey(resourcePath));
        return document.isFoundDocument() ? immutableSortedMap.insert(document.getKey(), document) : immutableSortedMap;
    }

    private void recalculateAndSaveOverlays(Map<DocumentKey, MutableDocument> map) {
        List<MutationBatch> allMutationBatchesAffectingDocumentKeys = this.mutationQueue.getAllMutationBatchesAffectingDocumentKeys(map.keySet());
        HashMap map2 = new HashMap();
        TreeMap treeMap = new TreeMap();
        for (MutationBatch mutationBatch : allMutationBatchesAffectingDocumentKeys) {
            for (DocumentKey documentKey : mutationBatch.getKeys()) {
                map2.put(documentKey, mutationBatch.applyToLocalView(map.get(documentKey), map2.containsKey(documentKey) ? (FieldMask) map2.get(documentKey) : FieldMask.EMPTY));
                int batchId = mutationBatch.getBatchId();
                if (!treeMap.containsKey(Integer.valueOf(batchId))) {
                    treeMap.put(Integer.valueOf(batchId), new HashSet());
                }
                ((Set) treeMap.get(Integer.valueOf(batchId))).add(documentKey);
            }
        }
        HashSet hashSet = new HashSet();
        for (Map.Entry entry : treeMap.descendingMap().entrySet()) {
            HashMap map3 = new HashMap();
            for (DocumentKey documentKey2 : (Set) entry.getValue()) {
                if (!hashSet.contains(documentKey2)) {
                    map3.put(documentKey2, Mutation.calculateOverlayMutation(map.get(documentKey2), (FieldMask) map2.get(documentKey2)));
                    hashSet.add(documentKey2);
                }
            }
            this.documentOverlayCache.saveOverlays(((Integer) entry.getKey()).intValue(), map3);
        }
    }

    public Document getDocument(DocumentKey documentKey) {
        Mutation overlay = this.documentOverlayCache.getOverlay(documentKey);
        MutableDocument mutableDocumentNewInvalidDocument = (overlay == null || (overlay instanceof PatchMutation)) ? this.remoteDocumentCache.get(documentKey) : MutableDocument.newInvalidDocument(documentKey);
        if (overlay != null) {
            overlay.applyToLocalView(mutableDocumentNewInvalidDocument, null, Timestamp.now());
        }
        return mutableDocumentNewInvalidDocument;
    }

    @VisibleForTesting
    public DocumentOverlayCache getDocumentOverlayCache() {
        return this.documentOverlayCache;
    }

    public ImmutableSortedMap<DocumentKey, Document> getDocuments(Iterable<DocumentKey> iterable) {
        return getLocalViewOfDocuments(this.remoteDocumentCache.getAll(iterable), new HashSet());
    }

    public ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset indexOffset) {
        ResourcePath path = query.getPath();
        if (query.isDocumentQuery()) {
            return getDocumentsMatchingDocumentQuery(path);
        }
        return query.isCollectionGroupQuery() ? getDocumentsMatchingCollectionGroupQuery(query, indexOffset) : getDocumentsMatchingCollectionQuery(query, indexOffset);
    }

    @VisibleForTesting
    public IndexManager getIndexManager() {
        return this.indexManager;
    }

    public ImmutableSortedMap<DocumentKey, Document> getLocalViewOfDocuments(Map<DocumentKey, MutableDocument> map, Set<DocumentKey> set) {
        ImmutableSortedMap<DocumentKey, ?> immutableSortedMap = DocumentCollections.EMPTY_DOCUMENT_MAP;
        HashMap map2 = new HashMap();
        for (Map.Entry<DocumentKey, MutableDocument> entry : map.entrySet()) {
            Mutation overlay = this.documentOverlayCache.getOverlay(entry.getKey());
            if (set.contains(entry.getKey()) && (overlay == null || (overlay instanceof PatchMutation))) {
                map2.put(entry.getKey(), map.get(entry.getKey()));
            } else if (overlay != null) {
                overlay.applyToLocalView(entry.getValue(), null, Timestamp.now());
            }
        }
        recalculateAndSaveOverlays(map2);
        ImmutableSortedMap immutableSortedMapInsert = immutableSortedMap;
        for (Map.Entry<DocumentKey, MutableDocument> entry2 : map.entrySet()) {
            immutableSortedMapInsert = immutableSortedMapInsert.insert(entry2.getKey(), entry2.getValue());
        }
        return immutableSortedMapInsert;
    }

    @VisibleForTesting
    public MutationQueue getMutationQueue() {
        return this.mutationQueue;
    }

    @VisibleForTesting
    public RemoteDocumentCache getRemoteDocumentCache() {
        return this.remoteDocumentCache;
    }

    public ImmutableSortedMap<DocumentKey, Document> getDocuments(String str, FieldIndex.IndexOffset indexOffset, int i) {
        return getLocalViewOfDocuments(this.remoteDocumentCache.getAll(str, indexOffset, i), new HashSet());
    }

    public void recalculateAndSaveOverlays(Set<DocumentKey> set) {
        recalculateAndSaveOverlays(this.remoteDocumentCache.getAll(set));
    }
}
