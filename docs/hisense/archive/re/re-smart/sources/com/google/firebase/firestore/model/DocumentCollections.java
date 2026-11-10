package com.google.firebase.firestore.model;

import com.google.firebase.database.collection.ImmutableSortedMap;

/* JADX INFO: loaded from: classes.dex */
public class DocumentCollections {
    public static final ImmutableSortedMap<DocumentKey, ?> EMPTY_DOCUMENT_MAP = ImmutableSortedMap.Builder.emptyMap(DocumentKey.COMPARATOR);

    public static ImmutableSortedMap<DocumentKey, Document> emptyDocumentMap() {
        return EMPTY_DOCUMENT_MAP;
    }

    public static ImmutableSortedMap<DocumentKey, MutableDocument> emptyMutableDocumentMap() {
        return EMPTY_DOCUMENT_MAP;
    }

    public static ImmutableSortedMap<DocumentKey, SnapshotVersion> emptyVersionMap() {
        return EMPTY_DOCUMENT_MAP;
    }
}
