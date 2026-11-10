package com.google.firebase.firestore.model;

import androidx.annotation.Nullable;
import com.google.firebase.firestore.model.Document;
import com.google.firestore.v1.Value;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public interface Document {
    public static final Comparator<Document> KEY_COMPARATOR = new Comparator() { // from class: b.e.b.d.e.a
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ((Document) obj).getKey().compareTo(((Document) obj2).getKey());
        }
    };

    ObjectValue getData();

    @Nullable
    Value getField(FieldPath fieldPath);

    DocumentKey getKey();

    SnapshotVersion getReadTime();

    SnapshotVersion getVersion();

    boolean hasCommittedMutations();

    boolean hasLocalMutations();

    boolean hasPendingWrites();

    boolean isFoundDocument();

    boolean isNoDocument();

    boolean isUnknownDocument();

    boolean isValidDocument();

    MutableDocument mutableCopy();
}
