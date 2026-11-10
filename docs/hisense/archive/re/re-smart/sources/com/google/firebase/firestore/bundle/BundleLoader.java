package com.google.firebase.firestore.bundle;

import androidx.annotation.Nullable;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.LoadBundleTaskProgress;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.util.Preconditions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BundleLoader {
    public final BundleCallback bundleCallback;
    public final BundleMetadata bundleMetadata;
    public long bytesLoaded;

    @Nullable
    public DocumentKey currentDocument;
    public final List<NamedQuery> queries = new ArrayList();
    public ImmutableSortedMap<DocumentKey, MutableDocument> documents = DocumentCollections.EMPTY_DOCUMENT_MAP;
    public final Map<DocumentKey, BundledDocumentMetadata> documentsMetadata = new HashMap();

    public BundleLoader(BundleCallback bundleCallback, BundleMetadata bundleMetadata) {
        this.bundleCallback = bundleCallback;
        this.bundleMetadata = bundleMetadata;
    }

    private Map<String, ImmutableSortedSet<DocumentKey>> getQueryDocumentMapping() {
        HashMap map = new HashMap();
        Iterator<NamedQuery> it = this.queries.iterator();
        while (it.hasNext()) {
            map.put(it.next().getName(), DocumentKey.EMPTY_KEY_SET);
        }
        for (BundledDocumentMetadata bundledDocumentMetadata : this.documentsMetadata.values()) {
            for (String str : bundledDocumentMetadata.getQueries()) {
                map.put(str, ((ImmutableSortedSet) map.get(str)).insert(bundledDocumentMetadata.getKey()));
            }
        }
        return map;
    }

    @Nullable
    public LoadBundleTaskProgress addElement(BundleElement bundleElement, long j) {
        Preconditions.checkArgument(!(bundleElement instanceof BundleMetadata), "Unexpected bundle metadata element.", new Object[0]);
        int size = this.documents.size();
        if (bundleElement instanceof NamedQuery) {
            this.queries.add((NamedQuery) bundleElement);
        } else if (bundleElement instanceof BundledDocumentMetadata) {
            BundledDocumentMetadata bundledDocumentMetadata = (BundledDocumentMetadata) bundleElement;
            this.documentsMetadata.put(bundledDocumentMetadata.getKey(), bundledDocumentMetadata);
            this.currentDocument = bundledDocumentMetadata.getKey();
            if (!bundledDocumentMetadata.exists()) {
                this.documents = this.documents.insert(bundledDocumentMetadata.getKey(), MutableDocument.newNoDocument(bundledDocumentMetadata.getKey(), bundledDocumentMetadata.getReadTime()));
                this.currentDocument = null;
            }
        } else if (bundleElement instanceof BundleDocument) {
            BundleDocument bundleDocument = (BundleDocument) bundleElement;
            if (!bundleDocument.getKey().equals(this.currentDocument)) {
                throw new IllegalArgumentException("The document being added does not match the stored metadata.");
            }
            this.documents = this.documents.insert(bundleDocument.getKey(), bundleDocument.getDocument());
            this.currentDocument = null;
        }
        this.bytesLoaded += j;
        if (size != this.documents.size()) {
            return new LoadBundleTaskProgress(this.documents.size(), this.bundleMetadata.getTotalDocuments(), this.bytesLoaded, this.bundleMetadata.getTotalBytes(), null, LoadBundleTaskProgress.TaskState.RUNNING);
        }
        return null;
    }

    public ImmutableSortedMap<DocumentKey, Document> applyChanges() {
        Preconditions.checkArgument(this.currentDocument == null, "Bundled documents end with a document metadata element instead of a document.", new Object[0]);
        Preconditions.checkArgument(this.bundleMetadata.getBundleId() != null, "Bundle ID must be set", new Object[0]);
        Preconditions.checkArgument(this.documents.size() == this.bundleMetadata.getTotalDocuments(), "Expected %s documents, but loaded %s.", Integer.valueOf(this.bundleMetadata.getTotalDocuments()), Integer.valueOf(this.documents.size()));
        ImmutableSortedMap<DocumentKey, Document> immutableSortedMapApplyBundledDocuments = this.bundleCallback.applyBundledDocuments(this.documents, this.bundleMetadata.getBundleId());
        Map<String, ImmutableSortedSet<DocumentKey>> queryDocumentMapping = getQueryDocumentMapping();
        for (NamedQuery namedQuery : this.queries) {
            this.bundleCallback.saveNamedQuery(namedQuery, queryDocumentMapping.get(namedQuery.getName()));
        }
        this.bundleCallback.saveBundle(this.bundleMetadata);
        return immutableSortedMapApplyBundledDocuments;
    }
}
