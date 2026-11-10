package com.google.firebase.firestore.remote;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.protobuf.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class TargetChange {
    public final ImmutableSortedSet<DocumentKey> addedDocuments;
    public final boolean current;
    public final ImmutableSortedSet<DocumentKey> modifiedDocuments;
    public final ImmutableSortedSet<DocumentKey> removedDocuments;
    public final ByteString resumeToken;

    public TargetChange(ByteString byteString, boolean z, ImmutableSortedSet<DocumentKey> immutableSortedSet, ImmutableSortedSet<DocumentKey> immutableSortedSet2, ImmutableSortedSet<DocumentKey> immutableSortedSet3) {
        this.resumeToken = byteString;
        this.current = z;
        this.addedDocuments = immutableSortedSet;
        this.modifiedDocuments = immutableSortedSet2;
        this.removedDocuments = immutableSortedSet3;
    }

    public static TargetChange createSynthesizedTargetChangeForCurrentChange(boolean z) {
        ByteString byteString = ByteString.EMPTY;
        ImmutableSortedSet<DocumentKey> immutableSortedSet = DocumentKey.EMPTY_KEY_SET;
        return new TargetChange(byteString, z, immutableSortedSet, immutableSortedSet, immutableSortedSet);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TargetChange.class != obj.getClass()) {
            return false;
        }
        TargetChange targetChange = (TargetChange) obj;
        if (this.current == targetChange.current && this.resumeToken.equals(targetChange.resumeToken) && this.addedDocuments.equals(targetChange.addedDocuments) && this.modifiedDocuments.equals(targetChange.modifiedDocuments)) {
            return this.removedDocuments.equals(targetChange.removedDocuments);
        }
        return false;
    }

    public ImmutableSortedSet<DocumentKey> getAddedDocuments() {
        return this.addedDocuments;
    }

    public ImmutableSortedSet<DocumentKey> getModifiedDocuments() {
        return this.modifiedDocuments;
    }

    public ImmutableSortedSet<DocumentKey> getRemovedDocuments() {
        return this.removedDocuments;
    }

    public ByteString getResumeToken() {
        return this.resumeToken;
    }

    public int hashCode() {
        return this.removedDocuments.hashCode() + ((this.modifiedDocuments.hashCode() + ((this.addedDocuments.hashCode() + (((this.resumeToken.hashCode() * 31) + (this.current ? 1 : 0)) * 31)) * 31)) * 31);
    }

    public boolean isCurrent() {
        return this.current;
    }
}
