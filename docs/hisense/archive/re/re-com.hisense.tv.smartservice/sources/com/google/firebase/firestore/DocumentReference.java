package com.google.firebase.firestore;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.core.ActivityScope;
import com.google.firebase.firestore.core.AsyncEventListener;
import com.google.firebase.firestore.core.EventManager;
import com.google.firebase.firestore.core.ListenerRegistrationImpl;
import com.google.firebase.firestore.core.UserData;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.DeleteMutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class DocumentReference {
    public final FirebaseFirestore firestore;
    public final DocumentKey key;

    public DocumentReference(DocumentKey documentKey, FirebaseFirestore firebaseFirestore) {
        if (documentKey == null) {
            throw new NullPointerException();
        }
        this.key = documentKey;
        this.firestore = firebaseFirestore;
    }

    private ListenerRegistration addSnapshotListenerInternal(Executor executor, EventManager.ListenOptions listenOptions, @Nullable Activity activity, final EventListener<DocumentSnapshot> eventListener) {
        AsyncEventListener asyncEventListener = new AsyncEventListener(executor, new EventListener() { // from class: b.e.b.d.c
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                this.f322a.a(eventListener, (ViewSnapshot) obj, firebaseFirestoreException);
            }
        });
        ListenerRegistrationImpl listenerRegistrationImpl = new ListenerRegistrationImpl(this.firestore.getClient(), this.firestore.getClient().listen(asQuery(), listenOptions, asyncEventListener), asyncEventListener);
        ActivityScope.bind(activity, listenerRegistrationImpl);
        return listenerRegistrationImpl;
    }

    private com.google.firebase.firestore.core.Query asQuery() {
        return com.google.firebase.firestore.core.Query.atPath(this.key.getPath());
    }

    public static DocumentReference forPath(ResourcePath resourcePath, FirebaseFirestore firebaseFirestore) {
        if (resourcePath.length() % 2 == 0) {
            return new DocumentReference(new DocumentKey(resourcePath), firebaseFirestore);
        }
        StringBuilder sbA = a.a("Invalid document reference. Document references must have an even number of segments, but ");
        sbA.append(resourcePath.canonicalString());
        sbA.append(" has ");
        sbA.append(resourcePath.length());
        throw new IllegalArgumentException(sbA.toString());
    }

    @NonNull
    private Task<DocumentSnapshot> getViaSnapshotListener(final Source source) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
        EventManager.ListenOptions listenOptions = new EventManager.ListenOptions();
        listenOptions.includeDocumentMetadataChanges = true;
        listenOptions.includeQueryMetadataChanges = true;
        listenOptions.waitForSyncWhenOnline = true;
        taskCompletionSource2.setResult(addSnapshotListenerInternal(Executors.DIRECT_EXECUTOR, listenOptions, null, new EventListener() { // from class: b.e.b.d.d
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                DocumentReference.a(taskCompletionSource, taskCompletionSource2, source, (DocumentSnapshot) obj, firebaseFirestoreException);
            }
        }));
        return taskCompletionSource.getTask();
    }

    public static EventManager.ListenOptions internalOptions(MetadataChanges metadataChanges) {
        EventManager.ListenOptions listenOptions = new EventManager.ListenOptions();
        listenOptions.includeDocumentMetadataChanges = metadataChanges == MetadataChanges.INCLUDE;
        listenOptions.includeQueryMetadataChanges = metadataChanges == MetadataChanges.INCLUDE;
        listenOptions.waitForSyncWhenOnline = false;
        return listenOptions;
    }

    public /* synthetic */ DocumentSnapshot a(Task task) {
        Document document = (Document) task.getResult();
        return new DocumentSnapshot(this.firestore, this.key, document, true, document != null && document.hasLocalMutations());
    }

    @NonNull
    public ListenerRegistration addSnapshotListener(@NonNull EventListener<DocumentSnapshot> eventListener) {
        return addSnapshotListener(MetadataChanges.EXCLUDE, eventListener);
    }

    @NonNull
    public CollectionReference collection(@NonNull String str) {
        Preconditions.checkNotNull(str, "Provided collection path must not be null.");
        return new CollectionReference(this.key.getPath().append(ResourcePath.fromString(str)), this.firestore);
    }

    @NonNull
    public Task<Void> delete() {
        return this.firestore.getClient().write(Collections.singletonList(new DeleteMutation(this.key, Precondition.NONE))).continueWith(Executors.DIRECT_EXECUTOR, Util.VOID_ERROR_TRANSFORMER);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DocumentReference)) {
            return false;
        }
        DocumentReference documentReference = (DocumentReference) obj;
        return this.key.equals(documentReference.key) && this.firestore.equals(documentReference.firestore);
    }

    @NonNull
    public Task<DocumentSnapshot> get() {
        return get(Source.DEFAULT);
    }

    @NonNull
    public FirebaseFirestore getFirestore() {
        return this.firestore;
    }

    @NonNull
    public String getId() {
        return this.key.getDocumentId();
    }

    public DocumentKey getKey() {
        return this.key;
    }

    @NonNull
    public CollectionReference getParent() {
        return new CollectionReference(this.key.getCollectionPath(), this.firestore);
    }

    @NonNull
    public String getPath() {
        return this.key.getPath().canonicalString();
    }

    public int hashCode() {
        return this.firestore.hashCode() + (this.key.hashCode() * 31);
    }

    @NonNull
    public Task<Void> set(@NonNull Object obj) {
        return set(obj, SetOptions.OVERWRITE);
    }

    @NonNull
    public Task<Void> update(@NonNull Map<String, Object> map) {
        return update(this.firestore.getUserDataReader().parseUpdateData(map));
    }

    @NonNull
    public ListenerRegistration addSnapshotListener(@NonNull Executor executor, @NonNull EventListener<DocumentSnapshot> eventListener) {
        return addSnapshotListener(executor, MetadataChanges.EXCLUDE, eventListener);
    }

    @NonNull
    public Task<DocumentSnapshot> get(@NonNull Source source) {
        return source == Source.CACHE ? this.firestore.getClient().getDocumentFromLocalCache(this.key).continueWith(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: b.e.b.d.b
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f263a.a(task);
            }
        }) : getViaSnapshotListener(source);
    }

    @NonNull
    public Task<Void> set(@NonNull Object obj, @NonNull SetOptions setOptions) {
        Preconditions.checkNotNull(obj, "Provided data must not be null.");
        Preconditions.checkNotNull(setOptions, "Provided options must not be null.");
        return this.firestore.getClient().write(Collections.singletonList((setOptions.isMerge() ? this.firestore.getUserDataReader().parseMergeData(obj, setOptions.getFieldMask()) : this.firestore.getUserDataReader().parseSetData(obj)).toMutation(this.key, Precondition.NONE))).continueWith(Executors.DIRECT_EXECUTOR, Util.VOID_ERROR_TRANSFORMER);
    }

    @NonNull
    public ListenerRegistration addSnapshotListener(@NonNull Activity activity, @NonNull EventListener<DocumentSnapshot> eventListener) {
        return addSnapshotListener(activity, MetadataChanges.EXCLUDE, eventListener);
    }

    @NonNull
    public Task<Void> update(@NonNull String str, @Nullable Object obj, Object... objArr) {
        return update(this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, str, obj, objArr)));
    }

    public static /* synthetic */ void a(TaskCompletionSource taskCompletionSource, TaskCompletionSource taskCompletionSource2, Source source, DocumentSnapshot documentSnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (firebaseFirestoreException != null) {
            taskCompletionSource.setException(firebaseFirestoreException);
            return;
        }
        try {
            ((ListenerRegistration) Tasks.await(taskCompletionSource2.getTask())).remove();
            if (!documentSnapshot.exists() && documentSnapshot.getMetadata().isFromCache()) {
                taskCompletionSource.setException(new FirebaseFirestoreException("Failed to get document because the client is offline.", FirebaseFirestoreException.Code.UNAVAILABLE));
            } else if (documentSnapshot.exists() && documentSnapshot.getMetadata().isFromCache() && source == Source.SERVER) {
                taskCompletionSource.setException(new FirebaseFirestoreException("Failed to get document from server. (However, this document does exist in the local cache. Run again without setting source to SERVER to retrieve the cached document.)", FirebaseFirestoreException.Code.UNAVAILABLE));
            } else {
                taskCompletionSource.setResult(documentSnapshot);
            }
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            Assert.fail(e2, "Failed to register a listener for a single document", new Object[0]);
            throw null;
        } catch (ExecutionException e3) {
            Assert.fail(e3, "Failed to register a listener for a single document", new Object[0]);
            throw null;
        }
    }

    @NonNull
    public ListenerRegistration addSnapshotListener(@NonNull MetadataChanges metadataChanges, @NonNull EventListener<DocumentSnapshot> eventListener) {
        return addSnapshotListener(Executors.DEFAULT_CALLBACK_EXECUTOR, metadataChanges, eventListener);
    }

    @NonNull
    public ListenerRegistration addSnapshotListener(@NonNull Executor executor, @NonNull MetadataChanges metadataChanges, @NonNull EventListener<DocumentSnapshot> eventListener) {
        Preconditions.checkNotNull(executor, "Provided executor must not be null.");
        Preconditions.checkNotNull(metadataChanges, "Provided MetadataChanges value must not be null.");
        Preconditions.checkNotNull(eventListener, "Provided EventListener must not be null.");
        return addSnapshotListenerInternal(executor, internalOptions(metadataChanges), null, eventListener);
    }

    @NonNull
    public Task<Void> update(@NonNull FieldPath fieldPath, @Nullable Object obj, Object... objArr) {
        return update(this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, fieldPath, obj, objArr)));
    }

    @NonNull
    public ListenerRegistration addSnapshotListener(@NonNull Activity activity, @NonNull MetadataChanges metadataChanges, @NonNull EventListener<DocumentSnapshot> eventListener) {
        Preconditions.checkNotNull(activity, "Provided activity must not be null.");
        Preconditions.checkNotNull(metadataChanges, "Provided MetadataChanges value must not be null.");
        Preconditions.checkNotNull(eventListener, "Provided EventListener must not be null.");
        return addSnapshotListenerInternal(Executors.DEFAULT_CALLBACK_EXECUTOR, internalOptions(metadataChanges), activity, eventListener);
    }

    private Task<Void> update(@NonNull UserData.ParsedUpdateData parsedUpdateData) {
        return this.firestore.getClient().write(Collections.singletonList(parsedUpdateData.toMutation(this.key, Precondition.exists(true)))).continueWith(Executors.DIRECT_EXECUTOR, Util.VOID_ERROR_TRANSFORMER);
    }

    public /* synthetic */ void a(EventListener eventListener, ViewSnapshot viewSnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        DocumentSnapshot documentSnapshotFromNoDocument;
        if (firebaseFirestoreException != null) {
            eventListener.onEvent(null, firebaseFirestoreException);
            return;
        }
        Assert.hardAssert(viewSnapshot != null, "Got event without value or error set", new Object[0]);
        Assert.hardAssert(viewSnapshot.getDocuments().size() <= 1, "Too many documents returned on a document query", new Object[0]);
        Document document = viewSnapshot.getDocuments().getDocument(this.key);
        if (document != null) {
            documentSnapshotFromNoDocument = DocumentSnapshot.fromDocument(this.firestore, document, viewSnapshot.isFromCache(), viewSnapshot.getMutatedKeys().contains(document.getKey()));
        } else {
            documentSnapshotFromNoDocument = DocumentSnapshot.fromNoDocument(this.firestore, this.key, viewSnapshot.isFromCache());
        }
        eventListener.onEvent(documentSnapshotFromNoDocument, null);
    }
}
