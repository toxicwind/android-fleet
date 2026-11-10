package com.google.firebase.firestore.model.mutation;

import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class PatchMutation extends Mutation {
    public final FieldMask mask;
    public final ObjectValue value;

    public PatchMutation(DocumentKey documentKey, ObjectValue objectValue, FieldMask fieldMask, Precondition precondition) {
        super(documentKey, precondition, new ArrayList());
        this.value = objectValue;
        this.mask = fieldMask;
    }

    private List<FieldPath> getFieldTransformPaths() {
        ArrayList arrayList = new ArrayList();
        Iterator<FieldTransform> it = getFieldTransforms().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getFieldPath());
        }
        return arrayList;
    }

    private Map<FieldPath, Value> getPatch() {
        HashMap map = new HashMap();
        for (FieldPath fieldPath : this.mask.getMask()) {
            if (!fieldPath.isEmpty()) {
                map.put(fieldPath, this.value.get(fieldPath));
            }
        }
        return map;
    }

    @Override // com.google.firebase.firestore.model.mutation.Mutation
    @Nullable
    public FieldMask applyToLocalView(MutableDocument mutableDocument, @Nullable FieldMask fieldMask, Timestamp timestamp) {
        verifyKeyMatches(mutableDocument);
        if (!getPrecondition().isValidFor(mutableDocument)) {
            return fieldMask;
        }
        Map<FieldPath, Value> mapLocalTransformResults = localTransformResults(timestamp, mutableDocument);
        Map<FieldPath, Value> patch = getPatch();
        ObjectValue data = mutableDocument.getData();
        data.setAll(patch);
        data.setAll(mapLocalTransformResults);
        mutableDocument.convertToFoundDocument(mutableDocument.getVersion(), mutableDocument.getData()).setHasLocalMutations();
        if (fieldMask == null) {
            return null;
        }
        HashSet hashSet = new HashSet(fieldMask.getMask());
        hashSet.addAll(this.mask.getMask());
        hashSet.addAll(getFieldTransformPaths());
        return new FieldMask(hashSet);
    }

    @Override // com.google.firebase.firestore.model.mutation.Mutation
    public void applyToRemoteDocument(MutableDocument mutableDocument, MutationResult mutationResult) {
        verifyKeyMatches(mutableDocument);
        if (!getPrecondition().isValidFor(mutableDocument)) {
            mutableDocument.convertToUnknownDocument(mutationResult.getVersion());
            return;
        }
        Map<FieldPath, Value> mapServerTransformResults = serverTransformResults(mutableDocument, mutationResult.getTransformResults());
        ObjectValue data = mutableDocument.getData();
        data.setAll(getPatch());
        data.setAll(mapServerTransformResults);
        mutableDocument.convertToFoundDocument(mutationResult.getVersion(), mutableDocument.getData()).setHasCommittedMutations();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PatchMutation.class != obj.getClass()) {
            return false;
        }
        PatchMutation patchMutation = (PatchMutation) obj;
        return hasSameKeyAndPrecondition(patchMutation) && this.value.equals(patchMutation.value) && getFieldTransforms().equals(patchMutation.getFieldTransforms());
    }

    public FieldMask getMask() {
        return this.mask;
    }

    public ObjectValue getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode() + (keyAndPreconditionHashCode() * 31);
    }

    public String toString() {
        StringBuilder sbA = a.a("PatchMutation{");
        sbA.append(keyAndPreconditionToString());
        sbA.append(", mask=");
        sbA.append(this.mask);
        sbA.append(", value=");
        return a.a(sbA, this.value, "}");
    }

    public PatchMutation(DocumentKey documentKey, ObjectValue objectValue, FieldMask fieldMask, Precondition precondition, List<FieldTransform> list) {
        super(documentKey, precondition, list);
        this.value = objectValue;
        this.mask = fieldMask;
    }
}
