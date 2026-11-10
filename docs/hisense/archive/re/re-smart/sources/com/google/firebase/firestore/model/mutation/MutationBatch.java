package com.google.firebase.firestore.model.mutation;

import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class MutationBatch {
    public static final int UNKNOWN = -1;
    public final List<Mutation> baseMutations;
    public final int batchId;
    public final Timestamp localWriteTime;
    public final List<Mutation> mutations;

    public MutationBatch(int i, Timestamp timestamp, List<Mutation> list, List<Mutation> list2) {
        Assert.hardAssert(!list2.isEmpty(), "Cannot create an empty mutation batch", new Object[0]);
        this.batchId = i;
        this.localWriteTime = timestamp;
        this.baseMutations = list;
        this.mutations = list2;
    }

    public Map<DocumentKey, Mutation> applyToLocalDocumentSet(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap) {
        HashMap map = new HashMap();
        for (DocumentKey documentKey : getKeys()) {
            MutableDocument mutableDocument = (MutableDocument) immutableSortedMap.get(documentKey);
            map.put(documentKey, Mutation.calculateOverlayMutation(mutableDocument, applyToLocalView(mutableDocument)));
            if (!mutableDocument.isValidDocument()) {
                mutableDocument.convertToNoDocument(SnapshotVersion.NONE);
            }
        }
        return map;
    }

    public FieldMask applyToLocalView(MutableDocument mutableDocument) {
        return applyToLocalView(mutableDocument, new FieldMask(new HashSet()));
    }

    public void applyToRemoteDocument(MutableDocument mutableDocument, MutationBatchResult mutationBatchResult) {
        int size = this.mutations.size();
        List<MutationResult> mutationResults = mutationBatchResult.getMutationResults();
        Assert.hardAssert(mutationResults.size() == size, "Mismatch between mutations length (%d) and results length (%d)", Integer.valueOf(size), Integer.valueOf(mutationResults.size()));
        for (int i = 0; i < size; i++) {
            Mutation mutation = this.mutations.get(i);
            if (mutation.getKey().equals(mutableDocument.getKey())) {
                mutation.applyToRemoteDocument(mutableDocument, mutationResults.get(i));
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MutationBatch.class != obj.getClass()) {
            return false;
        }
        MutationBatch mutationBatch = (MutationBatch) obj;
        return this.batchId == mutationBatch.batchId && this.localWriteTime.equals(mutationBatch.localWriteTime) && this.baseMutations.equals(mutationBatch.baseMutations) && this.mutations.equals(mutationBatch.mutations);
    }

    public List<Mutation> getBaseMutations() {
        return this.baseMutations;
    }

    public int getBatchId() {
        return this.batchId;
    }

    public Set<DocumentKey> getKeys() {
        HashSet hashSet = new HashSet();
        Iterator<Mutation> it = this.mutations.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getKey());
        }
        return hashSet;
    }

    public Timestamp getLocalWriteTime() {
        return this.localWriteTime;
    }

    public List<Mutation> getMutations() {
        return this.mutations;
    }

    public int hashCode() {
        return this.mutations.hashCode() + ((this.baseMutations.hashCode() + ((this.localWriteTime.hashCode() + (this.batchId * 31)) * 31)) * 31);
    }

    public String toString() {
        StringBuilder sbA = a.a("MutationBatch(batchId=");
        sbA.append(this.batchId);
        sbA.append(", localWriteTime=");
        sbA.append(this.localWriteTime);
        sbA.append(", baseMutations=");
        sbA.append(this.baseMutations);
        sbA.append(", mutations=");
        return a.a(sbA, (Object) this.mutations, ')');
    }

    public FieldMask applyToLocalView(MutableDocument mutableDocument, @Nullable FieldMask fieldMask) {
        FieldMask fieldMaskApplyToLocalView = fieldMask;
        for (int i = 0; i < this.baseMutations.size(); i++) {
            Mutation mutation = this.baseMutations.get(i);
            if (mutation.getKey().equals(mutableDocument.getKey())) {
                fieldMaskApplyToLocalView = mutation.applyToLocalView(mutableDocument, fieldMaskApplyToLocalView, this.localWriteTime);
            }
        }
        for (int i2 = 0; i2 < this.mutations.size(); i2++) {
            Mutation mutation2 = this.mutations.get(i2);
            if (mutation2.getKey().equals(mutableDocument.getKey())) {
                fieldMaskApplyToLocalView = mutation2.applyToLocalView(mutableDocument, fieldMaskApplyToLocalView, this.localWriteTime);
            }
        }
        return fieldMaskApplyToLocalView;
    }
}
