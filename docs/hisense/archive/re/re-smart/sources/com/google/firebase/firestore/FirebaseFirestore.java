package com.google.firebase.firestore;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import b.e.b.d.j;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.emulators.EmulatedServiceSettings;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.FirebaseAppCheckTokenProvider;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.ActivityScope;
import com.google.firebase.firestore.core.AsyncEventListener;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.core.FirestoreClient;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.remote.FirestoreChannel;
import com.google.firebase.firestore.remote.GrpcMetadataProvider;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.ByteBufferInputStream;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.inject.Deferred;
import com.google.firebase.installations.Utils;
import e.b.a;
import e.b.b;
import e.b.d;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseFirestore {
    public static final String TAG = "FirebaseFirestore";
    public final CredentialsProvider<String> appCheckProvider;
    public final AsyncQueue asyncQueue;
    public final CredentialsProvider<User> authProvider;
    public volatile FirestoreClient client;
    public final Context context;
    public final DatabaseId databaseId;

    @Nullable
    public EmulatedServiceSettings emulatorSettings;
    public final FirebaseApp firebaseApp;
    public final InstanceRegistry instanceRegistry;
    public final GrpcMetadataProvider metadataProvider;
    public final String persistenceKey;
    public FirebaseFirestoreSettings settings;
    public final UserDataReader userDataReader;

    public interface InstanceRegistry {
        void remove(@NonNull String str);
    }

    @VisibleForTesting
    public FirebaseFirestore(Context context, DatabaseId databaseId, String str, CredentialsProvider<User> credentialsProvider, CredentialsProvider<String> credentialsProvider2, AsyncQueue asyncQueue, @Nullable FirebaseApp firebaseApp, InstanceRegistry instanceRegistry, @Nullable GrpcMetadataProvider grpcMetadataProvider) {
        if (context == null) {
            throw new NullPointerException();
        }
        this.context = context;
        if (databaseId == null) {
            throw new NullPointerException();
        }
        this.databaseId = databaseId;
        this.userDataReader = new UserDataReader(databaseId);
        if (str == null) {
            throw new NullPointerException();
        }
        this.persistenceKey = str;
        if (credentialsProvider == null) {
            throw new NullPointerException();
        }
        this.authProvider = credentialsProvider;
        if (credentialsProvider2 == null) {
            throw new NullPointerException();
        }
        this.appCheckProvider = credentialsProvider2;
        if (asyncQueue == null) {
            throw new NullPointerException();
        }
        this.asyncQueue = asyncQueue;
        this.firebaseApp = firebaseApp;
        this.instanceRegistry = instanceRegistry;
        this.metadataProvider = grpcMetadataProvider;
        this.settings = new FirebaseFirestoreSettings.Builder().build();
    }

    private void ensureClientConfigured() {
        if (this.client != null) {
            return;
        }
        synchronized (this.databaseId) {
            if (this.client != null) {
                return;
            }
            this.client = new FirestoreClient(this.context, new DatabaseInfo(this.databaseId, this.persistenceKey, this.settings.getHost(), this.settings.isSslEnabled()), this.settings, this.authProvider, this.appCheckProvider, this.asyncQueue, this.metadataProvider);
        }
    }

    @NonNull
    public static FirebaseFirestore getInstance() {
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        if (firebaseApp != null) {
            return getInstance(firebaseApp, DatabaseId.DEFAULT_DATABASE_ID);
        }
        throw new IllegalStateException("You must call FirebaseApp.initializeApp first.");
    }

    private FirebaseFirestoreSettings mergeEmulatorSettings(@NonNull FirebaseFirestoreSettings firebaseFirestoreSettings, @Nullable EmulatedServiceSettings emulatedServiceSettings) {
        if (emulatedServiceSettings == null) {
            return firebaseFirestoreSettings;
        }
        if (!FirebaseFirestoreSettings.DEFAULT_HOST.equals(firebaseFirestoreSettings.getHost())) {
            Logger.warn(TAG, "Host has been set in FirebaseFirestoreSettings and useEmulator, emulator host will be used.", new Object[0]);
        }
        return new FirebaseFirestoreSettings.Builder(firebaseFirestoreSettings).setHost(emulatedServiceSettings.getHost() + Utils.APP_ID_IDENTIFICATION_SUBSTRING + emulatedServiceSettings.getPort()).setSslEnabled(false).build();
    }

    @NonNull
    public static FirebaseFirestore newInstance(@NonNull Context context, @NonNull FirebaseApp firebaseApp, @NonNull Deferred<InternalAuthProvider> deferred, @NonNull Deferred<InternalAppCheckTokenProvider> deferred2, @NonNull String str, @NonNull InstanceRegistry instanceRegistry, @Nullable GrpcMetadataProvider grpcMetadataProvider) {
        String projectId = firebaseApp.getOptions().getProjectId();
        if (projectId == null) {
            throw new IllegalArgumentException("FirebaseOptions.getProjectId() cannot be null");
        }
        DatabaseId databaseId = new DatabaseId(projectId, str);
        AsyncQueue asyncQueue = new AsyncQueue();
        return new FirebaseFirestore(context, databaseId, firebaseApp.getName(), new FirebaseAuthCredentialsProvider(deferred), new FirebaseAppCheckTokenProvider(deferred2), asyncQueue, firebaseApp, instanceRegistry, grpcMetadataProvider);
    }

    private <ResultT> Task<ResultT> runTransaction(Transaction.Function<ResultT> function, Executor executor) {
        ensureClientConfigured();
        return this.client.transaction(new j(this, executor, function));
    }

    @Keep
    public static void setClientLanguage(@NonNull String str) {
        FirestoreChannel.clientLanguage = str;
    }

    public static void setLoggingEnabled(boolean z) {
        if (z) {
            Logger.logLevel = Logger.Level.DEBUG;
        } else {
            Logger.logLevel = Logger.Level.WARN;
        }
    }

    public /* synthetic */ Task a(Executor executor, final Transaction.Function function, final com.google.firebase.firestore.core.Transaction transaction) {
        return Tasks.call(executor, new Callable() { // from class: b.e.b.d.f
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f483a.a(function, transaction);
            }
        });
    }

    @NonNull
    public ListenerRegistration addSnapshotsInSyncListener(@NonNull Runnable runnable) {
        return addSnapshotsInSyncListener(Executors.DEFAULT_CALLBACK_EXECUTOR, runnable);
    }

    @NonNull
    public WriteBatch batch() {
        ensureClientConfigured();
        return new WriteBatch(this);
    }

    @NonNull
    public Task<Void> clearPersistence() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.asyncQueue.enqueueAndForgetEvenAfterShutdown(new Runnable() { // from class: b.e.b.d.i
            @Override // java.lang.Runnable
            public final void run() {
                this.f565a.a(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    @NonNull
    public CollectionReference collection(@NonNull String str) {
        Preconditions.checkNotNull(str, "Provided collection path must not be null.");
        ensureClientConfigured();
        return new CollectionReference(ResourcePath.fromString(str), this);
    }

    @NonNull
    public Query collectionGroup(@NonNull String str) {
        Preconditions.checkNotNull(str, "Provided collection ID must not be null.");
        if (str.contains("/")) {
            throw new IllegalArgumentException(String.format("Invalid collectionId '%s'. Collection IDs must not contain '/'.", str));
        }
        ensureClientConfigured();
        return new Query(new com.google.firebase.firestore.core.Query(ResourcePath.EMPTY, str), this);
    }

    @NonNull
    public Task<Void> disableNetwork() {
        ensureClientConfigured();
        return this.client.disableNetwork();
    }

    @NonNull
    public DocumentReference document(@NonNull String str) {
        Preconditions.checkNotNull(str, "Provided document path must not be null.");
        ensureClientConfigured();
        return DocumentReference.forPath(ResourcePath.fromString(str), this);
    }

    @NonNull
    public Task<Void> enableNetwork() {
        ensureClientConfigured();
        return this.client.enableNetwork();
    }

    @NonNull
    public FirebaseApp getApp() {
        return this.firebaseApp;
    }

    @VisibleForTesting
    public AsyncQueue getAsyncQueue() {
        return this.asyncQueue;
    }

    public FirestoreClient getClient() {
        return this.client;
    }

    public DatabaseId getDatabaseId() {
        return this.databaseId;
    }

    @NonNull
    public FirebaseFirestoreSettings getFirestoreSettings() {
        return this.settings;
    }

    @NonNull
    public Task<Query> getNamedQuery(@NonNull String str) {
        ensureClientConfigured();
        return this.client.getNamedQuery(str).continueWith(new Continuation() { // from class: b.e.b.d.h
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f564a.a(task);
            }
        });
    }

    public UserDataReader getUserDataReader() {
        return this.userDataReader;
    }

    @NonNull
    public LoadBundleTask loadBundle(@NonNull InputStream inputStream) {
        ensureClientConfigured();
        LoadBundleTask loadBundleTask = new LoadBundleTask();
        this.client.loadBundle(inputStream, loadBundleTask);
        return loadBundleTask;
    }

    @NonNull
    public Task<Void> runBatch(@NonNull WriteBatch.Function function) {
        WriteBatch writeBatchBatch = batch();
        function.apply(writeBatchBatch);
        return writeBatchBatch.commit();
    }

    public void setFirestoreSettings(@NonNull FirebaseFirestoreSettings firebaseFirestoreSettings) {
        FirebaseFirestoreSettings firebaseFirestoreSettingsMergeEmulatorSettings = mergeEmulatorSettings(firebaseFirestoreSettings, this.emulatorSettings);
        synchronized (this.databaseId) {
            Preconditions.checkNotNull(firebaseFirestoreSettingsMergeEmulatorSettings, "Provided settings must not be null.");
            if (this.client != null && !this.settings.equals(firebaseFirestoreSettingsMergeEmulatorSettings)) {
                throw new IllegalStateException("FirebaseFirestore has already been started and its settings can no longer be changed. You can only call setFirestoreSettings() before calling any other methods on a FirebaseFirestore object.");
            }
            this.settings = firebaseFirestoreSettingsMergeEmulatorSettings;
        }
    }

    @VisibleForTesting
    public Task<Void> setIndexConfiguration(String str) {
        ensureClientConfigured();
        ArrayList arrayList = new ArrayList();
        try {
            d dVar = new d(str);
            if (dVar.f2163b.containsKey("indexes")) {
                a aVarE = dVar.e("indexes");
                for (int i = 0; i < aVarE.a(); i++) {
                    d dVarA = aVarE.a(i);
                    String strH = dVarA.h("collectionGroup");
                    ArrayList arrayList2 = new ArrayList();
                    a aVarK = dVarA.k("fields");
                    for (int i2 = 0; aVarK != null && i2 < aVarK.a(); i2++) {
                        d dVarA2 = aVarK.a(i2);
                        com.google.firebase.firestore.model.FieldPath fieldPathFromServerFormat = com.google.firebase.firestore.model.FieldPath.fromServerFormat(dVarA2.h("fieldPath"));
                        if ("CONTAINS".equals(dVarA2.a("arrayConfig", ""))) {
                            arrayList2.add(FieldIndex.Segment.create(fieldPathFromServerFormat, FieldIndex.Segment.Kind.CONTAINS));
                        } else if ("ASCENDING".equals(dVarA2.a("order", ""))) {
                            arrayList2.add(FieldIndex.Segment.create(fieldPathFromServerFormat, FieldIndex.Segment.Kind.ASCENDING));
                        } else {
                            arrayList2.add(FieldIndex.Segment.create(fieldPathFromServerFormat, FieldIndex.Segment.Kind.DESCENDING));
                        }
                    }
                    arrayList.add(FieldIndex.create(-1, strH, arrayList2, FieldIndex.INITIAL_STATE));
                }
            }
            return this.client.configureFieldIndexes(arrayList);
        } catch (b e2) {
            throw new IllegalArgumentException("Failed to parse index configuration", e2);
        }
    }

    @NonNull
    public Task<Void> terminate() {
        this.instanceRegistry.remove(getDatabaseId().getDatabaseId());
        ensureClientConfigured();
        return this.client.terminate();
    }

    public void useEmulator(@NonNull String str, int i) {
        if (this.client != null) {
            throw new IllegalStateException("Cannot call useEmulator() after instance has already been initialized.");
        }
        this.emulatorSettings = new EmulatedServiceSettings(str, i);
        this.settings = mergeEmulatorSettings(this.settings, this.emulatorSettings);
    }

    public void validateReference(DocumentReference documentReference) {
        Preconditions.checkNotNull(documentReference, "Provided DocumentReference must not be null.");
        if (documentReference.getFirestore() != this) {
            throw new IllegalArgumentException("Provided document reference is from a different Cloud Firestore instance.");
        }
    }

    @NonNull
    public Task<Void> waitForPendingWrites() {
        return this.client.waitForPendingWrites();
    }

    public /* synthetic */ Object a(Transaction.Function function, com.google.firebase.firestore.core.Transaction transaction) {
        return function.apply(new Transaction(transaction, this));
    }

    @NonNull
    public ListenerRegistration addSnapshotsInSyncListener(@NonNull Activity activity, @NonNull Runnable runnable) {
        return addSnapshotsInSyncListener(Executors.DEFAULT_CALLBACK_EXECUTOR, activity, runnable);
    }

    public /* synthetic */ void a(TaskCompletionSource taskCompletionSource) {
        try {
            if (this.client != null && !this.client.isTerminated()) {
                throw new FirebaseFirestoreException("Persistence cannot be cleared while the firestore instance is running.", FirebaseFirestoreException.Code.FAILED_PRECONDITION);
            }
            SQLitePersistence.clearPersistence(this.context, this.databaseId, this.persistenceKey);
            taskCompletionSource.setResult(null);
        } catch (FirebaseFirestoreException e2) {
            taskCompletionSource.setException(e2);
        }
    }

    @NonNull
    public ListenerRegistration addSnapshotsInSyncListener(@NonNull Executor executor, @NonNull Runnable runnable) {
        return addSnapshotsInSyncListener(executor, null, runnable);
    }

    private ListenerRegistration addSnapshotsInSyncListener(Executor executor, @Nullable Activity activity, @NonNull final Runnable runnable) {
        ensureClientConfigured();
        final AsyncEventListener asyncEventListener = new AsyncEventListener(executor, new EventListener() { // from class: b.e.b.d.g
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                FirebaseFirestore.a(runnable, (Void) obj, firebaseFirestoreException);
            }
        });
        this.client.addSnapshotsInSyncListener(asyncEventListener);
        ListenerRegistration listenerRegistration = new ListenerRegistration() { // from class: b.e.b.d.e
            @Override // com.google.firebase.firestore.ListenerRegistration
            public final void remove() {
                this.f476a.a(asyncEventListener);
            }
        };
        ActivityScope.bind(activity, listenerRegistration);
        return listenerRegistration;
    }

    @NonNull
    public static FirebaseFirestore getInstance(@NonNull FirebaseApp firebaseApp) {
        return getInstance(firebaseApp, DatabaseId.DEFAULT_DATABASE_ID);
    }

    @NonNull
    public LoadBundleTask loadBundle(@NonNull byte[] bArr) {
        return loadBundle(new ByteArrayInputStream(bArr));
    }

    @NonNull
    public <TResult> Task<TResult> runTransaction(@NonNull Transaction.Function<TResult> function) {
        Preconditions.checkNotNull(function, "Provided transaction update function must not be null.");
        Executor executor = com.google.firebase.firestore.core.Transaction.defaultExecutor;
        ensureClientConfigured();
        return this.client.transaction(new j(this, executor, function));
    }

    @NonNull
    public static FirebaseFirestore getInstance(@NonNull FirebaseApp firebaseApp, @NonNull String str) {
        Preconditions.checkNotNull(firebaseApp, "Provided FirebaseApp must not be null.");
        FirestoreMultiDbComponent firestoreMultiDbComponent = (FirestoreMultiDbComponent) firebaseApp.get(FirestoreMultiDbComponent.class);
        Preconditions.checkNotNull(firestoreMultiDbComponent, "Firestore component is not present.");
        return firestoreMultiDbComponent.get(str);
    }

    @NonNull
    public LoadBundleTask loadBundle(@NonNull ByteBuffer byteBuffer) {
        return loadBundle(new ByteBufferInputStream(byteBuffer));
    }

    public /* synthetic */ Query a(Task task) {
        com.google.firebase.firestore.core.Query query = (com.google.firebase.firestore.core.Query) task.getResult();
        if (query != null) {
            return new Query(query, this);
        }
        return null;
    }

    public static /* synthetic */ void a(Runnable runnable, Void r2, FirebaseFirestoreException firebaseFirestoreException) {
        Assert.hardAssert(firebaseFirestoreException == null, "snapshots-in-sync listeners should never get errors.", new Object[0]);
        runnable.run();
    }

    public /* synthetic */ void a(AsyncEventListener asyncEventListener) {
        asyncEventListener.mute();
        this.client.removeSnapshotsInSyncListener(asyncEventListener);
    }
}
