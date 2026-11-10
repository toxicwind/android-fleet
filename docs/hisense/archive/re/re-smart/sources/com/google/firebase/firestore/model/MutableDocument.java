package com.google.firebase.firestore.model;

import androidx.annotation.NonNull;
import b.a.a.a.a;
import com.google.firestore.v1.Value;

/* JADX INFO: loaded from: classes.dex */
public final class MutableDocument implements Document {
    public DocumentState documentState;
    public DocumentType documentType;
    public final DocumentKey key;
    public SnapshotVersion readTime;
    public ObjectValue value;
    public SnapshotVersion version;

    private enum DocumentState {
        HAS_LOCAL_MUTATIONS,
        HAS_COMMITTED_MUTATIONS,
        SYNCED
    }

    private enum DocumentType {
        INVALID,
        FOUND_DOCUMENT,
        NO_DOCUMENT,
        UNKNOWN_DOCUMENT
    }

    public MutableDocument(DocumentKey documentKey) {
        this.key = documentKey;
        this.readTime = SnapshotVersion.NONE;
    }

    public static MutableDocument newFoundDocument(DocumentKey documentKey, SnapshotVersion snapshotVersion, ObjectValue objectValue) {
        return new MutableDocument(documentKey).convertToFoundDocument(snapshotVersion, objectValue);
    }

    public static MutableDocument newInvalidDocument(DocumentKey documentKey) {
        DocumentType documentType = DocumentType.INVALID;
        SnapshotVersion snapshotVersion = SnapshotVersion.NONE;
        return new MutableDocument(documentKey, documentType, snapshotVersion, snapshotVersion, new ObjectValue(), DocumentState.SYNCED);
    }

    public static MutableDocument newNoDocument(DocumentKey documentKey, SnapshotVersion snapshotVersion) {
        return new MutableDocument(documentKey).convertToNoDocument(snapshotVersion);
    }

    public static MutableDocument newUnknownDocument(DocumentKey documentKey, SnapshotVersion snapshotVersion) {
        return new MutableDocument(documentKey).convertToUnknownDocument(snapshotVersion);
    }

    public MutableDocument convertToFoundDocument(SnapshotVersion snapshotVersion, ObjectValue objectValue) {
        this.version = snapshotVersion;
        this.documentType = DocumentType.FOUND_DOCUMENT;
        this.value = objectValue;
        this.documentState = DocumentState.SYNCED;
        return this;
    }

    public MutableDocument convertToNoDocument(SnapshotVersion snapshotVersion) {
        this.version = snapshotVersion;
        this.documentType = DocumentType.NO_DOCUMENT;
        this.value = new ObjectValue();
        this.documentState = DocumentState.SYNCED;
        return this;
    }

    public MutableDocument convertToUnknownDocument(SnapshotVersion snapshotVersion) {
        this.version = snapshotVersion;
        this.documentType = DocumentType.UNKNOWN_DOCUMENT;
        this.value = new ObjectValue();
        this.documentState = DocumentState.HAS_COMMITTED_MUTATIONS;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MutableDocument.class != obj.getClass()) {
            return false;
        }
        MutableDocument mutableDocument = (MutableDocument) obj;
        if (this.key.equals(mutableDocument.key) && this.version.equals(mutableDocument.version) && this.documentType.equals(mutableDocument.documentType) && this.documentState.equals(mutableDocument.documentState)) {
            return this.value.equals(mutableDocument.value);
        }
        return false;
    }

    @Override // com.google.firebase.firestore.model.Document
    public ObjectValue getData() {
        return this.value;
    }

    @Override // com.google.firebase.firestore.model.Document
    public Value getField(FieldPath fieldPath) {
        return getData().get(fieldPath);
    }

    @Override // com.google.firebase.firestore.model.Document
    public DocumentKey getKey() {
        return this.key;
    }

    @Override // com.google.firebase.firestore.model.Document
    public SnapshotVersion getReadTime() {
        return this.readTime;
    }

    @Override // com.google.firebase.firestore.model.Document
    public SnapshotVersion getVersion() {
        return this.version;
    }

    @Override // com.google.firebase.firestore.model.Document
    public boolean hasCommittedMutations() {
        return this.documentState.equals(DocumentState.HAS_COMMITTED_MUTATIONS);
    }

    @Override // com.google.firebase.firestore.model.Document
    public boolean hasLocalMutations() {
        return this.documentState.equals(DocumentState.HAS_LOCAL_MUTATIONS);
    }

    @Override // com.google.firebase.firestore.model.Document
    public boolean hasPendingWrites() {
        return hasLocalMutations() || hasCommittedMutations();
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    @Override // com.google.firebase.firestore.model.Document
    public boolean isFoundDocument() {
        return this.documentType.equals(DocumentType.FOUND_DOCUMENT);
    }

    @Override // com.google.firebase.firestore.model.Document
    public boolean isNoDocument() {
        return this.documentType.equals(DocumentType.NO_DOCUMENT);
    }

    @Override // com.google.firebase.firestore.model.Document
    public boolean isUnknownDocument() {
        return this.documentType.equals(DocumentType.UNKNOWN_DOCUMENT);
    }

    @Override // com.google.firebase.firestore.model.Document
    public boolean isValidDocument() {
        return !this.documentType.equals(DocumentType.INVALID);
    }

    @Override // com.google.firebase.firestore.model.Document
    @NonNull
    public MutableDocument mutableCopy() {
        return new MutableDocument(this.key, this.documentType, this.version, this.readTime, this.value.m4clone(), this.documentState);
    }

    public MutableDocument setHasCommittedMutations() {
        this.documentState = DocumentState.HAS_COMMITTED_MUTATIONS;
        return this;
    }

    public MutableDocument setHasLocalMutations() {
        this.documentState = DocumentState.HAS_LOCAL_MUTATIONS;
        this.version = SnapshotVersion.NONE;
        return this;
    }

    public String toString() {
        StringBuilder sbA = a.a("Document{key=");
        sbA.append(this.key);
        sbA.append(", version=");
        sbA.append(this.version);
        sbA.append(", readTime=");
        sbA.append(this.readTime);
        sbA.append(", type=");
        sbA.append(this.documentType);
        sbA.append(", documentState=");
        sbA.append(this.documentState);
        sbA.append(", value=");
        return a.a(sbA, (Object) this.value, '}');
    }

    public MutableDocument withReadTime(SnapshotVersion snapshotVersion) {
        this.readTime = snapshotVersion;
        return this;
    }

    public MutableDocument(DocumentKey documentKey, DocumentType documentType, SnapshotVersion snapshotVersion, SnapshotVersion snapshotVersion2, ObjectValue objectValue, DocumentState documentState) {
        this.key = documentKey;
        this.version = snapshotVersion;
        this.readTime = snapshotVersion2;
        this.documentType = documentType;
        this.documentState = documentState;
        this.value = objectValue;
    }
}
