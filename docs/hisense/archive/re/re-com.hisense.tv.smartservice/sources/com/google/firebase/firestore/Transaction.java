package com.google.firebase.firestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.core.UserData;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes.dex */
public class Transaction {
    public final FirebaseFirestore firestore;
    public final com.google.firebase.firestore.core.Transaction transaction;

    public interface Function<TResult> {
        @Nullable
        TResult apply(@NonNull Transaction transaction);
    }

    public Transaction(com.google.firebase.firestore.core.Transaction transaction, FirebaseFirestore firebaseFirestore) {
        if (transaction == null) {
            throw new NullPointerException();
        }
        this.transaction = transaction;
        if (firebaseFirestore == null) {
            throw new NullPointerException();
        }
        this.firestore = firebaseFirestore;
    }

    private Task<DocumentSnapshot> getAsync(DocumentReference documentReference) {
        return this.transaction.lookup(Collections.singletonList(documentReference.getKey())).continueWith(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: b.e.b.d.q
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f581a.a(task);
            }
        });
    }

    public /* synthetic */ DocumentSnapshot a(Task task) throws Exception {
        if (!task.isSuccessful()) {
            throw task.getException();
        }
        List list = (List) task.getResult();
        if (list.size() != 1) {
            Assert.fail("Mismatch in docs returned from document lookup.", new Object[0]);
            throw null;
        }
        MutableDocument mutableDocument = (MutableDocument) list.get(0);
        if (mutableDocument.isFoundDocument()) {
            return DocumentSnapshot.fromDocument(this.firestore, mutableDocument, false, false);
        }
        if (mutableDocument.isNoDocument()) {
            return DocumentSnapshot.fromNoDocument(this.firestore, mutableDocument.getKey(), false);
        }
        StringBuilder sbA = a.a("BatchGetDocumentsRequest returned unexpected document type: ");
        sbA.append(MutableDocument.class.getCanonicalName());
        Assert.fail(sbA.toString(), new Object[0]);
        throw null;
    }

    @NonNull
    public Transaction delete(@NonNull DocumentReference documentReference) {
        this.firestore.validateReference(documentReference);
        this.transaction.delete(documentReference.getKey());
        return this;
    }

    @NonNull
    public DocumentSnapshot get(@NonNull DocumentReference documentReference) throws FirebaseFirestoreException {
        this.firestore.validateReference(documentReference);
        try {
            return (DocumentSnapshot) Tasks.await(getAsync(documentReference));
        } catch (InterruptedException e2) {
            throw new RuntimeException(e2);
        } catch (ExecutionException e3) {
            if (e3.getCause() instanceof FirebaseFirestoreException) {
                throw ((FirebaseFirestoreException) e3.getCause());
            }
            throw new RuntimeException(e3.getCause());
        }
    }

    @NonNull
    public Transaction set(@NonNull DocumentReference documentReference, @NonNull Object obj) {
        return set(documentReference, obj, SetOptions.OVERWRITE);
    }

    @NonNull
    public Transaction update(@NonNull DocumentReference documentReference, @NonNull Map<String, Object> map) {
        UserData.ParsedUpdateData updateData = this.firestore.getUserDataReader().parseUpdateData(map);
        this.firestore.validateReference(documentReference);
        this.transaction.update(documentReference.getKey(), updateData);
        return this;
    }

    @NonNull
    public Transaction set(@NonNull DocumentReference documentReference, @NonNull Object obj, @NonNull SetOptions setOptions) {
        this.firestore.validateReference(documentReference);
        Preconditions.checkNotNull(obj, "Provided data must not be null.");
        Preconditions.checkNotNull(setOptions, "Provided options must not be null.");
        this.transaction.set(documentReference.getKey(), setOptions.isMerge() ? this.firestore.getUserDataReader().parseMergeData(obj, setOptions.getFieldMask()) : this.firestore.getUserDataReader().parseSetData(obj));
        return this;
    }

    @NonNull
    public Transaction update(@NonNull DocumentReference documentReference, @NonNull String str, @Nullable Object obj, Object... objArr) {
        UserData.ParsedUpdateData updateData = this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, str, obj, objArr));
        this.firestore.validateReference(documentReference);
        this.transaction.update(documentReference.getKey(), updateData);
        return this;
    }

    @NonNull
    public Transaction update(@NonNull DocumentReference documentReference, @NonNull FieldPath fieldPath, @Nullable Object obj, Object... objArr) {
        UserData.ParsedUpdateData updateData = this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, fieldPath, obj, objArr));
        this.firestore.validateReference(documentReference);
        this.transaction.update(documentReference.getKey(), updateData);
        return this;
    }

    private Transaction update(@NonNull DocumentReference documentReference, @NonNull UserData.ParsedUpdateData parsedUpdateData) {
        this.firestore.validateReference(documentReference);
        this.transaction.update(documentReference.getKey(), parsedUpdateData);
        return this;
    }
}
