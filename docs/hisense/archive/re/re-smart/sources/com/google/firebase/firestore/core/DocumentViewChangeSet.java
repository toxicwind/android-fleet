package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class DocumentViewChangeSet {
    public final TreeMap<DocumentKey, DocumentViewChange> changes = new TreeMap<>();

    public void addChange(DocumentViewChange documentViewChange) {
        DocumentViewChange.Type type;
        DocumentKey key = documentViewChange.getDocument().getKey();
        DocumentViewChange documentViewChange2 = this.changes.get(key);
        if (documentViewChange2 == null) {
            this.changes.put(key, documentViewChange);
            return;
        }
        DocumentViewChange.Type type2 = documentViewChange2.getType();
        DocumentViewChange.Type type3 = documentViewChange.getType();
        if (type3 != DocumentViewChange.Type.ADDED && type2 == DocumentViewChange.Type.METADATA) {
            this.changes.put(key, documentViewChange);
            return;
        }
        if (type3 == DocumentViewChange.Type.METADATA && type2 != DocumentViewChange.Type.REMOVED) {
            this.changes.put(key, new DocumentViewChange(type2, documentViewChange.getDocument()));
            return;
        }
        DocumentViewChange.Type type4 = DocumentViewChange.Type.MODIFIED;
        if (type3 == type4 && type2 == type4) {
            this.changes.put(key, new DocumentViewChange(type4, documentViewChange.getDocument()));
            return;
        }
        if (type3 == DocumentViewChange.Type.MODIFIED && type2 == (type = DocumentViewChange.Type.ADDED)) {
            this.changes.put(key, new DocumentViewChange(type, documentViewChange.getDocument()));
            return;
        }
        if (type3 == DocumentViewChange.Type.REMOVED && type2 == DocumentViewChange.Type.ADDED) {
            this.changes.remove(key);
            return;
        }
        if (type3 == DocumentViewChange.Type.REMOVED && type2 == DocumentViewChange.Type.MODIFIED) {
            this.changes.put(key, new DocumentViewChange(DocumentViewChange.Type.REMOVED, documentViewChange2.getDocument()));
        } else if (type3 == DocumentViewChange.Type.ADDED && type2 == DocumentViewChange.Type.REMOVED) {
            this.changes.put(key, new DocumentViewChange(DocumentViewChange.Type.MODIFIED, documentViewChange.getDocument()));
        } else {
            Assert.fail("Unsupported combination of changes %s after %s", type3, type2);
            throw null;
        }
    }

    public List<DocumentViewChange> getChanges() {
        return new ArrayList(this.changes.values());
    }
}
