package com.google.firebase.firestore.local;

import androidx.annotation.NonNull;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class MemoryRemoteDocumentCache implements RemoteDocumentCache {
    public IndexManager indexManager;
    public ImmutableSortedMap<DocumentKey, Document> docs = DocumentCollections.EMPTY_DOCUMENT_MAP;
    public SnapshotVersion latestReadTime = SnapshotVersion.NONE;

    private class DocumentIterable implements Iterable<Document> {
        public DocumentIterable() {
        }

        @Override // java.lang.Iterable
        @NonNull
        public Iterator<Document> iterator() {
            final Iterator<Map.Entry<DocumentKey, Document>> it = MemoryRemoteDocumentCache.this.docs.iterator();
            return new Iterator<Document>() { // from class: com.google.firebase.firestore.local.MemoryRemoteDocumentCache.DocumentIterable.1
                @Override // java.util.Iterator
                public boolean hasNext() {
                    return it.hasNext();
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.Iterator
                public Document next() {
                    return (Document) ((Map.Entry) it.next()).getValue();
                }
            };
        }

        public /* synthetic */ DocumentIterable(AnonymousClass1 anonymousClass1) {
        }
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void add(MutableDocument mutableDocument, SnapshotVersion snapshotVersion) {
        Assert.hardAssert(this.indexManager != null, "setIndexManager() not called", new Object[0]);
        Assert.hardAssert(!snapshotVersion.equals(SnapshotVersion.NONE), "Cannot add document to the RemoteDocumentCache with a read time of zero", new Object[0]);
        this.docs = this.docs.insert(mutableDocument.getKey(), mutableDocument.mutableCopy().withReadTime(snapshotVersion));
        if (snapshotVersion.compareTo(this.latestReadTime) <= 0) {
            snapshotVersion = this.latestReadTime;
        }
        this.latestReadTime = snapshotVersion;
        this.indexManager.addToCollectionParentIndex(mutableDocument.getKey().getCollectionPath());
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public MutableDocument get(DocumentKey documentKey) {
        Document document = this.docs.get(documentKey);
        return document != null ? document.mutableCopy() : MutableDocument.newInvalidDocument(documentKey);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getAll(Iterable<DocumentKey> iterable) {
        HashMap map = new HashMap();
        for (DocumentKey documentKey : iterable) {
            map.put(documentKey, get(documentKey));
        }
        return map;
    }

    public long getByteSize(LocalSerializer localSerializer) {
        Iterator<Document> it = new DocumentIterable(null).iterator();
        long serializedSize = 0;
        while (it.hasNext()) {
            serializedSize += (long) localSerializer.encodeMaybeDocument(it.next()).getSerializedSize();
        }
        return serializedSize;
    }

    public Iterable<Document> getDocuments() {
        return new DocumentIterable(null);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public SnapshotVersion getLatestReadTime() {
        return this.latestReadTime;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void removeAll(Collection<DocumentKey> collection) {
        Assert.hardAssert(this.indexManager != null, "setIndexManager() not called", new Object[0]);
        ImmutableSortedMap<DocumentKey, ?> immutableSortedMapInsert = DocumentCollections.EMPTY_DOCUMENT_MAP;
        for (DocumentKey documentKey : collection) {
            this.docs = this.docs.remove(documentKey);
            immutableSortedMapInsert = immutableSortedMapInsert.insert(documentKey, MutableDocument.newNoDocument(documentKey, SnapshotVersion.NONE));
        }
        this.indexManager.updateIndexEntries(immutableSortedMapInsert);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void setIndexManager(IndexManager indexManager) {
        this.indexManager = indexManager;
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getAll(String str, FieldIndex.IndexOffset indexOffset, int i) {
        throw new UnsupportedOperationException("getAll(String, IndexOffset, int) is not supported.");
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getAll(ResourcePath resourcePath, FieldIndex.IndexOffset indexOffset) {
        HashMap map = new HashMap();
        Iterator<Map.Entry<DocumentKey, Document>> itIteratorFrom = this.docs.iteratorFrom(new DocumentKey(resourcePath.append("")));
        while (itIteratorFrom.hasNext()) {
            Map.Entry<DocumentKey, Document> next = itIteratorFrom.next();
            Document value = next.getValue();
            DocumentKey key = next.getKey();
            if (!resourcePath.isPrefixOf(key.getPath())) {
                break;
            }
            if (key.getPath().length() <= resourcePath.length() + 1 && FieldIndex.IndexOffset.fromDocument(value).compareTo(indexOffset) > 0) {
                map.put(value.getKey(), value.mutableCopy());
            }
        }
        return map;
    }
}
