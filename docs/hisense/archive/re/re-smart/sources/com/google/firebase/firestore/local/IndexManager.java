package com.google.firebase.firestore.local;

import androidx.annotation.Nullable;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.ResourcePath;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public interface IndexManager {
    void addFieldIndex(FieldIndex fieldIndex);

    void addToCollectionParentIndex(ResourcePath resourcePath);

    void deleteFieldIndex(FieldIndex fieldIndex);

    List<ResourcePath> getCollectionParents(String str);

    Set<DocumentKey> getDocumentsMatchingTarget(FieldIndex fieldIndex, Target target);

    @Nullable
    FieldIndex getFieldIndex(Target target);

    Collection<FieldIndex> getFieldIndexes();

    Collection<FieldIndex> getFieldIndexes(String str);

    @Nullable
    String getNextCollectionGroupToUpdate();

    void start();

    void updateCollectionGroup(String str, FieldIndex.IndexOffset indexOffset);

    void updateIndexEntries(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap);
}
