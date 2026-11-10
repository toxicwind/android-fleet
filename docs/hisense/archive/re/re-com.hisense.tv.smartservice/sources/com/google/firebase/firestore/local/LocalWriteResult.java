package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;

/* JADX INFO: loaded from: classes.dex */
public final class LocalWriteResult {
    public final int batchId;
    public final ImmutableSortedMap<DocumentKey, Document> changes;

    public LocalWriteResult(int i, ImmutableSortedMap<DocumentKey, Document> immutableSortedMap) {
        this.batchId = i;
        this.changes = immutableSortedMap;
    }

    public int getBatchId() {
        return this.batchId;
    }

    public ImmutableSortedMap<DocumentKey, Document> getChanges() {
        return this.changes;
    }
}
