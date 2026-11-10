package com.google.firebase.firestore.local;

import com.google.firebase.firestore.local.DocumentReference;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Util;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public class DocumentReference {
    public static final Comparator<DocumentReference> BY_KEY = new Comparator() { // from class: b.e.b.d.d.b
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return DocumentReference.a((DocumentReference) obj, (DocumentReference) obj2);
        }
    };
    public static final Comparator<DocumentReference> BY_TARGET = new Comparator() { // from class: b.e.b.d.d.c
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return DocumentReference.b((DocumentReference) obj, (DocumentReference) obj2);
        }
    };
    public final DocumentKey key;
    public final int targetOrBatchId;

    public DocumentReference(DocumentKey documentKey, int i) {
        this.key = documentKey;
        this.targetOrBatchId = i;
    }

    public static /* synthetic */ int a(DocumentReference documentReference, DocumentReference documentReference2) {
        int iCompareTo = documentReference.key.compareTo(documentReference2.key);
        return iCompareTo != 0 ? iCompareTo : Util.compareIntegers(documentReference.targetOrBatchId, documentReference2.targetOrBatchId);
    }

    public static /* synthetic */ int b(DocumentReference documentReference, DocumentReference documentReference2) {
        int iCompareIntegers = Util.compareIntegers(documentReference.targetOrBatchId, documentReference2.targetOrBatchId);
        return iCompareIntegers != 0 ? iCompareIntegers : documentReference.key.compareTo(documentReference2.key);
    }

    public int getId() {
        return this.targetOrBatchId;
    }

    public DocumentKey getKey() {
        return this.key;
    }
}
