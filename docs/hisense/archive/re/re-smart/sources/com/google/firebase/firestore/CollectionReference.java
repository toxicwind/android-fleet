package com.google.firebase.firestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;

/* JADX INFO: loaded from: classes.dex */
public class CollectionReference extends Query {
    public CollectionReference(ResourcePath resourcePath, FirebaseFirestore firebaseFirestore) {
        super(com.google.firebase.firestore.core.Query.atPath(resourcePath), firebaseFirestore);
        if (resourcePath.length() % 2 == 1) {
            return;
        }
        StringBuilder sbA = a.a("Invalid collection reference. Collection references must have an odd number of segments, but ");
        sbA.append(resourcePath.canonicalString());
        sbA.append(" has ");
        sbA.append(resourcePath.length());
        throw new IllegalArgumentException(sbA.toString());
    }

    @NonNull
    public Task<DocumentReference> add(@NonNull Object obj) {
        Preconditions.checkNotNull(obj, "Provided data must not be null.");
        final DocumentReference documentReferenceDocument = document();
        return documentReferenceDocument.set(obj).continueWith(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: b.e.b.d.a
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                DocumentReference documentReference = documentReferenceDocument;
                task.getResult();
                return documentReference;
            }
        });
    }

    @NonNull
    public DocumentReference document() {
        return document(Util.autoId());
    }

    @NonNull
    public String getId() {
        return this.query.getPath().getLastSegment();
    }

    @Nullable
    public DocumentReference getParent() {
        ResourcePath resourcePathPopLast = this.query.getPath().popLast();
        if (resourcePathPopLast.isEmpty()) {
            return null;
        }
        return new DocumentReference(new DocumentKey(resourcePathPopLast), this.firestore);
    }

    @NonNull
    public String getPath() {
        return this.query.getPath().canonicalString();
    }

    @NonNull
    public DocumentReference document(@NonNull String str) {
        Preconditions.checkNotNull(str, "Provided document path must not be null.");
        return DocumentReference.forPath(this.query.getPath().append(ResourcePath.fromString(str)), this.firestore);
    }
}
