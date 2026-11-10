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
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class SetMutation extends Mutation {
    public final ObjectValue value;

    public SetMutation(DocumentKey documentKey, ObjectValue objectValue, Precondition precondition) {
        super(documentKey, precondition, new ArrayList());
        this.value = objectValue;
    }

    @Override // com.google.firebase.firestore.model.mutation.Mutation
    public FieldMask applyToLocalView(MutableDocument mutableDocument, @Nullable FieldMask fieldMask, Timestamp timestamp) {
        verifyKeyMatches(mutableDocument);
        if (!getPrecondition().isValidFor(mutableDocument)) {
            return fieldMask;
        }
        Map<FieldPath, Value> mapLocalTransformResults = localTransformResults(timestamp, mutableDocument);
        ObjectValue objectValueM4clone = this.value.m4clone();
        objectValueM4clone.setAll(mapLocalTransformResults);
        mutableDocument.convertToFoundDocument(mutableDocument.getVersion(), objectValueM4clone).setHasLocalMutations();
        return null;
    }

    @Override // com.google.firebase.firestore.model.mutation.Mutation
    public void applyToRemoteDocument(MutableDocument mutableDocument, MutationResult mutationResult) {
        verifyKeyMatches(mutableDocument);
        ObjectValue objectValueM4clone = this.value.m4clone();
        objectValueM4clone.setAll(serverTransformResults(mutableDocument, mutationResult.getTransformResults()));
        mutableDocument.convertToFoundDocument(mutationResult.getVersion(), objectValueM4clone).setHasCommittedMutations();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SetMutation.class != obj.getClass()) {
            return false;
        }
        SetMutation setMutation = (SetMutation) obj;
        return hasSameKeyAndPrecondition(setMutation) && this.value.equals(setMutation.value) && getFieldTransforms().equals(setMutation.getFieldTransforms());
    }

    public ObjectValue getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode() + (keyAndPreconditionHashCode() * 31);
    }

    public String toString() {
        StringBuilder sbA = a.a("SetMutation{");
        sbA.append(keyAndPreconditionToString());
        sbA.append(", value=");
        return a.a(sbA, this.value, "}");
    }

    public SetMutation(DocumentKey documentKey, ObjectValue objectValue, Precondition precondition, List<FieldTransform> list) {
        super(documentKey, precondition, list);
        this.value = objectValue;
    }
}
