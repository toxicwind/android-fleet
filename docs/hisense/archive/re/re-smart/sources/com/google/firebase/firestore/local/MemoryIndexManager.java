package com.google.firebase.firestore.local;

import androidx.annotation.Nullable;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class MemoryIndexManager implements IndexManager {
    public final MemoryCollectionParentIndex collectionParentsIndex = new MemoryCollectionParentIndex();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static class MemoryCollectionParentIndex {
        public final HashMap<String, HashSet<ResourcePath>> index = new HashMap<>();

        public boolean add(ResourcePath resourcePath) {
            Assert.hardAssert(resourcePath.length() % 2 == 1, "Expected a collection path.", new Object[0]);
            String lastSegment = resourcePath.getLastSegment();
            ResourcePath resourcePathPopLast = resourcePath.popLast();
            HashSet<ResourcePath> hashSet = this.index.get(lastSegment);
            if (hashSet == null) {
                hashSet = new HashSet<>();
                this.index.put(lastSegment, hashSet);
            }
            return hashSet.add(resourcePathPopLast);
        }

        public List<ResourcePath> getEntries(String str) {
            HashSet<ResourcePath> hashSet = this.index.get(str);
            return hashSet != null ? new ArrayList(hashSet) : Collections.emptyList();
        }
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void addFieldIndex(FieldIndex fieldIndex) {
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void addToCollectionParentIndex(ResourcePath resourcePath) {
        this.collectionParentsIndex.add(resourcePath);
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void deleteFieldIndex(FieldIndex fieldIndex) {
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public List<ResourcePath> getCollectionParents(String str) {
        return this.collectionParentsIndex.getEntries(str);
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    @Nullable
    public Set<DocumentKey> getDocumentsMatchingTarget(FieldIndex fieldIndex, Target target) {
        return Collections.emptySet();
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    @Nullable
    public FieldIndex getFieldIndex(Target target) {
        return null;
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public Collection<FieldIndex> getFieldIndexes(String str) {
        return Collections.emptyList();
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    @Nullable
    public String getNextCollectionGroupToUpdate() {
        return null;
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void start() {
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void updateCollectionGroup(String str, FieldIndex.IndexOffset indexOffset) {
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void updateIndexEntries(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap) {
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public Collection<FieldIndex> getFieldIndexes() {
        return Collections.emptyList();
    }
}
