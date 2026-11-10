package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.SnapshotVersion;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BundledDocumentMetadata implements BundleElement {
    public final boolean exists;
    public final DocumentKey key;
    public final List<String> queries;
    public final SnapshotVersion readTime;

    public BundledDocumentMetadata(DocumentKey documentKey, SnapshotVersion snapshotVersion, boolean z, List<String> list) {
        this.key = documentKey;
        this.readTime = snapshotVersion;
        this.exists = z;
        this.queries = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || BundledDocumentMetadata.class != obj.getClass()) {
            return false;
        }
        BundledDocumentMetadata bundledDocumentMetadata = (BundledDocumentMetadata) obj;
        if (this.exists == bundledDocumentMetadata.exists && this.key.equals(bundledDocumentMetadata.key) && this.readTime.equals(bundledDocumentMetadata.readTime)) {
            return this.queries.equals(bundledDocumentMetadata.queries);
        }
        return false;
    }

    public boolean exists() {
        return this.exists;
    }

    public DocumentKey getKey() {
        return this.key;
    }

    public List<String> getQueries() {
        return this.queries;
    }

    public SnapshotVersion getReadTime() {
        return this.readTime;
    }

    public int hashCode() {
        return this.queries.hashCode() + ((((this.readTime.hashCode() + (this.key.hashCode() * 31)) * 31) + (this.exists ? 1 : 0)) * 31);
    }
}
