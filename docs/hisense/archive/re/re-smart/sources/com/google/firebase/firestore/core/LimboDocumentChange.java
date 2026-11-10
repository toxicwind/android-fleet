package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.DocumentKey;

/* JADX INFO: loaded from: classes.dex */
public class LimboDocumentChange {
    public final DocumentKey key;
    public final Type type;

    public enum Type {
        ADDED,
        REMOVED
    }

    public LimboDocumentChange(Type type, DocumentKey documentKey) {
        this.type = type;
        this.key = documentKey;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LimboDocumentChange)) {
            return false;
        }
        LimboDocumentChange limboDocumentChange = (LimboDocumentChange) obj;
        return this.type.equals(limboDocumentChange.getType()) && this.key.equals(limboDocumentChange.getKey());
    }

    public DocumentKey getKey() {
        return this.key;
    }

    public Type getType() {
        return this.type;
    }

    public int hashCode() {
        return this.key.hashCode() + ((this.type.hashCode() + 2077) * 31);
    }
}
