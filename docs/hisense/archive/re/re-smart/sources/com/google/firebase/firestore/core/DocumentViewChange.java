package com.google.firebase.firestore.core;

import b.a.a.a.a;
import com.google.firebase.firestore.model.Document;

/* JADX INFO: loaded from: classes.dex */
public class DocumentViewChange {
    public final Document document;
    public final Type type;

    public enum Type {
        REMOVED,
        ADDED,
        MODIFIED,
        METADATA
    }

    public DocumentViewChange(Type type, Document document) {
        this.type = type;
        this.document = document;
    }

    public static DocumentViewChange create(Type type, Document document) {
        return new DocumentViewChange(type, document);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DocumentViewChange)) {
            return false;
        }
        DocumentViewChange documentViewChange = (DocumentViewChange) obj;
        return this.type.equals(documentViewChange.type) && this.document.equals(documentViewChange.document);
    }

    public Document getDocument() {
        return this.document;
    }

    public Type getType() {
        return this.type;
    }

    public int hashCode() {
        return this.document.getData().hashCode() + ((this.document.getKey().hashCode() + ((this.type.hashCode() + 1891) * 31)) * 31);
    }

    public String toString() {
        StringBuilder sbA = a.a("DocumentViewChange(");
        sbA.append(this.document);
        sbA.append(",");
        return a.a(sbA, this.type, ")");
    }
}
