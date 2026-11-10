package com.google.firebase.firestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.core.UserData;
import com.google.firebase.firestore.model.mutation.DeleteMutation;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class WriteBatch {
    public final FirebaseFirestore firestore;
    public final ArrayList<Mutation> mutations = new ArrayList<>();
    public boolean committed = false;

    public interface Function {
        void apply(@NonNull WriteBatch writeBatch);
    }

    public WriteBatch(FirebaseFirestore firebaseFirestore) {
        if (firebaseFirestore == null) {
            throw new NullPointerException();
        }
        this.firestore = firebaseFirestore;
    }

    private void verifyNotCommitted() {
        if (this.committed) {
            throw new IllegalStateException("A write batch can no longer be used after commit() has been called.");
        }
    }

    @NonNull
    public Task<Void> commit() {
        verifyNotCommitted();
        this.committed = true;
        return this.mutations.size() > 0 ? this.firestore.getClient().write(this.mutations) : Tasks.forResult(null);
    }

    @NonNull
    public WriteBatch delete(@NonNull DocumentReference documentReference) {
        this.firestore.validateReference(documentReference);
        verifyNotCommitted();
        this.mutations.add(new DeleteMutation(documentReference.getKey(), Precondition.NONE));
        return this;
    }

    @NonNull
    public WriteBatch set(@NonNull DocumentReference documentReference, @NonNull Object obj) {
        return set(documentReference, obj, SetOptions.OVERWRITE);
    }

    @NonNull
    public WriteBatch update(@NonNull DocumentReference documentReference, @NonNull Map<String, Object> map) {
        update(documentReference, this.firestore.getUserDataReader().parseUpdateData(map));
        return this;
    }

    @NonNull
    public WriteBatch set(@NonNull DocumentReference documentReference, @NonNull Object obj, @NonNull SetOptions setOptions) {
        this.firestore.validateReference(documentReference);
        Preconditions.checkNotNull(obj, "Provided data must not be null.");
        Preconditions.checkNotNull(setOptions, "Provided options must not be null.");
        verifyNotCommitted();
        this.mutations.add((setOptions.isMerge() ? this.firestore.getUserDataReader().parseMergeData(obj, setOptions.getFieldMask()) : this.firestore.getUserDataReader().parseSetData(obj)).toMutation(documentReference.getKey(), Precondition.NONE));
        return this;
    }

    @NonNull
    public WriteBatch update(@NonNull DocumentReference documentReference, @NonNull String str, @Nullable Object obj, Object... objArr) {
        update(documentReference, this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, str, obj, objArr)));
        return this;
    }

    @NonNull
    public WriteBatch update(@NonNull DocumentReference documentReference, @NonNull FieldPath fieldPath, @Nullable Object obj, Object... objArr) {
        update(documentReference, this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, fieldPath, obj, objArr)));
        return this;
    }

    private WriteBatch update(@NonNull DocumentReference documentReference, @NonNull UserData.ParsedUpdateData parsedUpdateData) {
        this.firestore.validateReference(documentReference);
        verifyNotCommitted();
        this.mutations.add(parsedUpdateData.toMutation(documentReference.getKey(), Precondition.exists(true)));
        return this;
    }
}
