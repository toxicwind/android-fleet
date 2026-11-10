package com.google.firebase.firestore.model;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class AutoValue_FieldIndex_IndexOffset extends FieldIndex.IndexOffset {
    public final DocumentKey documentKey;
    public final SnapshotVersion readTime;

    public AutoValue_FieldIndex_IndexOffset(SnapshotVersion snapshotVersion, DocumentKey documentKey) {
        if (snapshotVersion == null) {
            throw new NullPointerException("Null readTime");
        }
        this.readTime = snapshotVersion;
        if (documentKey == null) {
            throw new NullPointerException("Null documentKey");
        }
        this.documentKey = documentKey;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldIndex.IndexOffset)) {
            return false;
        }
        FieldIndex.IndexOffset indexOffset = (FieldIndex.IndexOffset) obj;
        return this.readTime.equals(indexOffset.getReadTime()) && this.documentKey.equals(indexOffset.getDocumentKey());
    }

    @Override // com.google.firebase.firestore.model.FieldIndex.IndexOffset
    public DocumentKey getDocumentKey() {
        return this.documentKey;
    }

    @Override // com.google.firebase.firestore.model.FieldIndex.IndexOffset
    public SnapshotVersion getReadTime() {
        return this.readTime;
    }

    public int hashCode() {
        return ((this.readTime.hashCode() ^ 1000003) * 1000003) ^ this.documentKey.hashCode();
    }

    public String toString() {
        StringBuilder sbA = a.a("IndexOffset{readTime=");
        sbA.append(this.readTime);
        sbA.append(", documentKey=");
        return a.a(sbA, this.documentKey, "}");
    }
}
