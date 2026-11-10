package com.google.firebase.firestore.model.mutation;

import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class Mutation {
    public final List<FieldTransform> fieldTransforms;
    public final DocumentKey key;
    public final Precondition precondition;

    public Mutation(DocumentKey documentKey, Precondition precondition) {
        ArrayList arrayList = new ArrayList();
        this.key = documentKey;
        this.precondition = precondition;
        this.fieldTransforms = arrayList;
    }

    public static Mutation calculateOverlayMutation(MutableDocument mutableDocument, @Nullable FieldMask fieldMask) {
        if (!mutableDocument.hasLocalMutations()) {
            return null;
        }
        if (fieldMask != null && fieldMask.getMask().isEmpty()) {
            return null;
        }
        if (fieldMask == null) {
            return mutableDocument.isNoDocument() ? new DeleteMutation(mutableDocument.getKey(), Precondition.NONE) : new SetMutation(mutableDocument.getKey(), mutableDocument.getData(), Precondition.NONE);
        }
        ObjectValue data = mutableDocument.getData();
        ObjectValue objectValue = new ObjectValue();
        HashSet hashSet = new HashSet();
        for (FieldPath fieldPathPopLast : fieldMask.getMask()) {
            if (!hashSet.contains(fieldPathPopLast)) {
                if (data.get(fieldPathPopLast) == null && fieldPathPopLast.length() > 1) {
                    fieldPathPopLast = fieldPathPopLast.popLast();
                }
                objectValue.set(fieldPathPopLast, data.get(fieldPathPopLast));
                hashSet.add(fieldPathPopLast);
            }
        }
        return new PatchMutation(mutableDocument.getKey(), objectValue, new FieldMask(hashSet), Precondition.NONE);
    }

    @Nullable
    public abstract FieldMask applyToLocalView(MutableDocument mutableDocument, @Nullable FieldMask fieldMask, Timestamp timestamp);

    public abstract void applyToRemoteDocument(MutableDocument mutableDocument, MutationResult mutationResult);

    public ObjectValue extractTransformBaseValue(Document document) {
        ObjectValue objectValue = null;
        for (FieldTransform fieldTransform : this.fieldTransforms) {
            Value valueComputeBaseValue = fieldTransform.getOperation().computeBaseValue(document.getField(fieldTransform.getFieldPath()));
            if (valueComputeBaseValue != null) {
                if (objectValue == null) {
                    objectValue = new ObjectValue();
                }
                objectValue.set(fieldTransform.getFieldPath(), valueComputeBaseValue);
            }
        }
        return objectValue;
    }

    public List<FieldTransform> getFieldTransforms() {
        return this.fieldTransforms;
    }

    public DocumentKey getKey() {
        return this.key;
    }

    public Precondition getPrecondition() {
        return this.precondition;
    }

    public boolean hasSameKeyAndPrecondition(Mutation mutation) {
        return this.key.equals(mutation.key) && this.precondition.equals(mutation.precondition);
    }

    public int keyAndPreconditionHashCode() {
        return this.precondition.hashCode() + (getKey().hashCode() * 31);
    }

    public String keyAndPreconditionToString() {
        StringBuilder sbA = a.a("key=");
        sbA.append(this.key);
        sbA.append(", precondition=");
        sbA.append(this.precondition);
        return sbA.toString();
    }

    public Map<FieldPath, Value> localTransformResults(Timestamp timestamp, MutableDocument mutableDocument) {
        HashMap map = new HashMap(this.fieldTransforms.size());
        for (FieldTransform fieldTransform : this.fieldTransforms) {
            map.put(fieldTransform.getFieldPath(), fieldTransform.getOperation().applyToLocalView(mutableDocument.getField(fieldTransform.getFieldPath()), timestamp));
        }
        return map;
    }

    public Map<FieldPath, Value> serverTransformResults(MutableDocument mutableDocument, List<Value> list) {
        HashMap map = new HashMap(this.fieldTransforms.size());
        Assert.hardAssert(this.fieldTransforms.size() == list.size(), "server transform count (%d) should match field transform count (%d)", Integer.valueOf(list.size()), Integer.valueOf(this.fieldTransforms.size()));
        for (int i = 0; i < list.size(); i++) {
            FieldTransform fieldTransform = this.fieldTransforms.get(i);
            map.put(fieldTransform.getFieldPath(), fieldTransform.getOperation().applyToRemoteDocument(mutableDocument.getField(fieldTransform.getFieldPath()), list.get(i)));
        }
        return map;
    }

    public void verifyKeyMatches(MutableDocument mutableDocument) {
        Assert.hardAssert(mutableDocument.getKey().equals(getKey()), "Can only apply a mutation to a document with the same key", new Object[0]);
    }

    public Mutation(DocumentKey documentKey, Precondition precondition, List<FieldTransform> list) {
        this.key = documentKey;
        this.precondition = precondition;
        this.fieldTransforms = list;
    }
}
