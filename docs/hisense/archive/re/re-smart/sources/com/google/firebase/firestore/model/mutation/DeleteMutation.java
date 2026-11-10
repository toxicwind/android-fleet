package com.google.firebase.firestore.model.mutation;

import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.util.Assert;

/* JADX INFO: loaded from: classes.dex */
public final class DeleteMutation extends Mutation {
    public DeleteMutation(DocumentKey documentKey, Precondition precondition) {
        super(documentKey, precondition);
    }

    @Override // com.google.firebase.firestore.model.mutation.Mutation
    @Nullable
    public FieldMask applyToLocalView(MutableDocument mutableDocument, @Nullable FieldMask fieldMask, Timestamp timestamp) {
        verifyKeyMatches(mutableDocument);
        if (!getPrecondition().isValidFor(mutableDocument)) {
            return fieldMask;
        }
        mutableDocument.convertToNoDocument(mutableDocument.getVersion()).setHasLocalMutations();
        return null;
    }

    @Override // com.google.firebase.firestore.model.mutation.Mutation
    public void applyToRemoteDocument(MutableDocument mutableDocument, MutationResult mutationResult) {
        verifyKeyMatches(mutableDocument);
        Assert.hardAssert(mutationResult.getTransformResults().isEmpty(), "Transform results received by DeleteMutation.", new Object[0]);
        mutableDocument.convertToNoDocument(mutationResult.getVersion()).setHasCommittedMutations();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DeleteMutation.class != obj.getClass()) {
            return false;
        }
        return hasSameKeyAndPrecondition((DeleteMutation) obj);
    }

    public int hashCode() {
        return keyAndPreconditionHashCode();
    }

    public String toString() {
        StringBuilder sbA = a.a("DeleteMutation{");
        sbA.append(keyAndPreconditionToString());
        sbA.append("}");
        return sbA.toString();
    }
}
