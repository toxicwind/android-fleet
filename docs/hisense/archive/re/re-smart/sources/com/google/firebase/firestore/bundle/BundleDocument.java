package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;

/* JADX INFO: loaded from: classes.dex */
public class BundleDocument implements BundleElement {
    public MutableDocument document;

    public BundleDocument(MutableDocument mutableDocument) {
        this.document = mutableDocument;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || BundleDocument.class != obj.getClass()) {
            return false;
        }
        return this.document.equals(((BundleDocument) obj).document);
    }

    public MutableDocument getDocument() {
        return this.document;
    }

    public DocumentKey getKey() {
        return this.document.getKey();
    }

    public int hashCode() {
        return this.document.hashCode();
    }
}
