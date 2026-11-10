package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class KeyFieldNotInFilter extends FieldFilter {
    public final List<DocumentKey> keys;

    public KeyFieldNotInFilter(FieldPath fieldPath, Value value) {
        super(fieldPath, FieldFilter.Operator.NOT_IN, value);
        this.keys = new ArrayList();
        this.keys.addAll(KeyFieldInFilter.extractDocumentKeysFromArrayValue(FieldFilter.Operator.NOT_IN, value));
    }

    @Override // com.google.firebase.firestore.core.FieldFilter, com.google.firebase.firestore.core.Filter
    public boolean matches(Document document) {
        return !this.keys.contains(document.getKey());
    }
}
