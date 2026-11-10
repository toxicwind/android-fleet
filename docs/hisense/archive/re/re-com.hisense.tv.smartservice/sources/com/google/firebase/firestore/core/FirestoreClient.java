package com.google.firebase.firestore.core;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.LoadBundleTask;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.bundle.BundleReader;
import com.google.firebase.firestore.bundle.BundleSerializer;
import com.google.firebase.firestore.bundle.NamedQuery;
import com.google.firebase.firestore.core.FirestoreClient;
import com.google.firebase.firestore.local.IndexBackfiller;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.Persistence;
import com.google.firebase.firestore.local.QueryResult;
import com.google.firebase.firestore.local.Scheduler;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.remote.Datastore;
import com.google.firebase.firestore.remote.GrpcMetadataProvider;
import com.google.firebase.firestore.remote.RemoteSerializer;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Listener;
import com.google.firebase.firestore.util.Logger;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class FirestoreClient {
    public static final String LOG_TAG = "FirestoreClient";
    public static final int MAX_CONCURRENT_LIMBO_RESOLUTIONS = 100;
    public final CredentialsProvider<String> appCheckProvider;
    public final AsyncQueue asyncQueue;
    public final CredentialsProvider<User> authProvider;
    public final BundleSerializer bundleSerializer;
    public final DatabaseInfo databaseInfo;
    public EventManager eventManager;

    @Nullable
    public Scheduler gcScheduler;
    public IndexBackfiller indexBackfiller;
    public LocalStore localStore;
    public final GrpcMetadataProvider metadataProvider;
    public Persistence persistence;
    public RemoteStore remoteStore;
    public SyncEngine syncEngine;

    public FirestoreClient(final Context context, DatabaseInfo databaseInfo, final FirebaseFirestoreSettings firebaseFirestoreSettings, CredentialsProvider<User> credentialsProvider, CredentialsProvider<String> credentialsProvider2, final AsyncQueue asyncQueue, @Nullable GrpcMetadataProvider grpcMetadataProvider) {
        this.databaseInfo = databaseInfo;
        this.authProvider = credentialsProvider;
        this.appCheckProvider = credentialsProvider2;
        this.asyncQueue = asyncQueue;
        this.metadataProvider = grpcMetadataProvider;
        this.bundleSerializer = new BundleSerializer(new RemoteSerializer(databaseInfo.getDatabaseId()));
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        asyncQueue.enqueueAndForget(new Runnable() { // from class: b.e.b.d.b.s
            @Override // java.lang.Runnable
            public final void run() {
                this.f306a.a(taskCompletionSource, context, firebaseFirestoreSettings);
            }
        });
        credentialsProvider.setChangeListener(new Listener() { // from class: b.e.b.d.b.v
            @Override // com.google.firebase.firestore.util.Listener
            public final void onValue(Object obj) {
                this.f314a.a(atomicBoolean, taskCompletionSource, asyncQueue, (User) obj);
            }
        });
        credentialsProvider2.setChangeListener(new Listener() { // from class: b.e.b.d.b.m
            @Override // com.google.firebase.firestore.util.Listener
            public final void onValue(Object obj) {
                FirestoreClient.a((String) obj);
            }
        });
    }

    public static /* synthetic */ void a(String str) {
    }

    private void initialize(Context context, User user, FirebaseFirestoreSettings firebaseFirestoreSettings) {
        Logger.debug(LOG_TAG, "Initializing. user=%s", user.getUid());
        ComponentProvider.Configuration configuration = new ComponentProvider.Configuration(context, this.asyncQueue, this.databaseInfo, new Datastore(this.databaseInfo, this.asyncQueue, this.authProvider, this.appCheckProvider, context, this.metadataProvider), user, 100, firebaseFirestoreSettings);
        ComponentProvider sQLiteComponentProvider = firebaseFirestoreSettings.isPersistenceEnabled() ? new SQLiteComponentProvider() : new MemoryComponentProvider();
        sQLiteComponentProvider.initialize(configuration);
        this.persistence = sQLiteComponentProvider.getPersistence();
        this.gcScheduler = sQLiteComponentProvider.getGarbageCollectionScheduler();
        this.localStore = sQLiteComponentProvider.getLocalStore();
        this.remoteStore = sQLiteComponentProvider.getRemoteStore();
        this.syncEngine = sQLiteComponentProvider.getSyncEngine();
        this.eventManager = sQLiteComponentProvider.getEventManager();
        this.indexBackfiller = sQLiteComponentProvider.getIndexBackfiller();
        Scheduler scheduler = this.gcScheduler;
        if (scheduler != null) {
            scheduler.start();
        }
        if (Persistence.INDEXING_SUPPORT_ENABLED && firebaseFirestoreSettings.isPersistenceEnabled()) {
            this.indexBackfiller.getScheduler().start();
        }
    }

    private void verifyNotTerminated() {
        if (isTerminated()) {
            throw new IllegalStateException("The client has already been terminated");
        }
    }

    public /* synthetic */ void a(TaskCompletionSource taskCompletionSource, Context context, FirebaseFirestoreSettings firebaseFirestoreSettings) {
        try {
            initialize(context, (User) Tasks.await(taskCompletionSource.getTask()), firebaseFirestoreSettings);
        } catch (InterruptedException | ExecutionException e2) {
            throw new RuntimeException(e2);
        }
    }

    public void addSnapshotsInSyncListener(final EventListener<Void> eventListener) {
        verifyNotTerminated();
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: b.e.b.d.b.o
            @Override // java.lang.Runnable
            public final void run() {
                this.f297a.a(eventListener);
            }
        });
    }

    public /* synthetic */ void b() {
        this.remoteStore.enableNetwork();
    }

    public /* synthetic */ void c() {
        this.remoteStore.shutdown();
        this.persistence.shutdown();
        Scheduler scheduler = this.gcScheduler;
        if (scheduler != null) {
            scheduler.stop();
        }
        if (Persistence.INDEXING_SUPPORT_ENABLED) {
            this.indexBackfiller.getScheduler().stop();
        }
    }

    public Task<Void> configureFieldIndexes(final List<FieldIndex> list) {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(new Runnable() { // from class: b.e.b.d.b.u
            @Override // java.lang.Runnable
            public final void run() {
                this.f312a.a(list);
            }
        });
    }

    public Task<Void> disableNetwork() {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(new Runnable() { // from class: b.e.b.d.b.w
            @Override // java.lang.Runnable
            public final void run() {
                this.f318a.a();
            }
        });
    }

    public Task<Void> enableNetwork() {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(new Runnable() { // from class: b.e.b.d.b.l
            @Override // java.lang.Runnable
            public final void run() {
                this.f293a.b();
            }
        });
    }

    public Task<Document> getDocumentFromLocalCache(final DocumentKey documentKey) {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(new Callable() { // from class: b.e.b.d.b.t
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f310a.a(documentKey);
            }
        }).continueWith(new Continuation() { // from class: b.e.b.d.b.f
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FirestoreClient.a(task);
            }
        });
    }

    public Task<ViewSnapshot> getDocumentsFromLocalCache(final Query query) {
        verifyNotTerminated();
        return this.asyncQueue.enqueue(new Callable() { // from class: b.e.b.d.b.r
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f304a.a(query);
            }
        });
    }

    public Task<Query> getNamedQuery(final String str) {
        verifyNotTerminated();
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: b.e.b.d.b.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f277a.a(str, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    public boolean isTerminated() {
        return this.asyncQueue.isShuttingDown();
    }

    public QueryListener listen(Query query, EventManager.ListenOptions listenOptions, EventListener<ViewSnapshot> eventListener) {
        verifyNotTerminated();
        final QueryListener queryListener = new QueryListener(query, listenOptions, eventListener);
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: b.e.b.d.b.n
            @Override // java.lang.Runnable
            public final void run() {
                this.f295a.a(queryListener);
            }
        });
        return queryListener;
    }

    public void loadBundle(InputStream inputStream, final LoadBundleTask loadBundleTask) {
        verifyNotTerminated();
        final BundleReader bundleReader = new BundleReader(this.bundleSerializer, inputStream);
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: b.e.b.d.b.k
            @Override // java.lang.Runnable
            public final void run() {
                this.f290a.a(bundleReader, loadBundleTask);
            }
        });
    }

    public void removeSnapshotsInSyncListener(final EventListener<Void> eventListener) {
        if (isTerminated()) {
            return;
        }
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: b.e.b.d.b.q
            @Override // java.lang.Runnable
            public final void run() {
                this.f302a.b(eventListener);
            }
        });
    }

    public void stopListening(final QueryListener queryListener) {
        if (isTerminated()) {
            return;
        }
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: b.e.b.d.b.j
            @Override // java.lang.Runnable
            public final void run() {
                this.f288a.b(queryListener);
            }
        });
    }

    public Task<Void> terminate() {
        this.authProvider.removeChangeListener();
        this.appCheckProvider.removeChangeListener();
        return this.asyncQueue.enqueueAndInitiateShutdown(new Runnable() { // from class: b.e.b.d.b.h
            @Override // java.lang.Runnable
            public final void run() {
                this.f285a.c();
            }
        });
    }

    public <TResult> Task<TResult> transaction(final Function<Transaction, Task<TResult>> function) {
        verifyNotTerminated();
        return AsyncQueue.callTask(this.asyncQueue.getExecutor(), new Callable() { // from class: b.e.b.d.b.i
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f286a.a(function);
            }
        });
    }

    public Task<Void> waitForPendingWrites() {
        verifyNotTerminated();
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: b.e.b.d.b.e
            @Override // java.lang.Runnable
            public final void run() {
                this.f280a.a(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    public Task<Void> write(final List<Mutation> list) {
        verifyNotTerminated();
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: b.e.b.d.b.p
            @Override // java.lang.Runnable
            public final void run() {
                this.f299a.a(list, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    public /* synthetic */ void b(QueryListener queryListener) {
        this.eventManager.removeQueryListener(queryListener);
    }

    public /* synthetic */ void b(EventListener eventListener) {
        this.eventManager.removeSnapshotsInSyncListener(eventListener);
    }

    public /* synthetic */ void a(AtomicBoolean atomicBoolean, TaskCompletionSource taskCompletionSource, AsyncQueue asyncQueue, final User user) {
        if (atomicBoolean.compareAndSet(false, true)) {
            Assert.hardAssert(!taskCompletionSource.getTask().isComplete(), "Already fulfilled first user task", new Object[0]);
            taskCompletionSource.setResult(user);
        } else {
            asyncQueue.enqueueAndForget(new Runnable() { // from class: b.e.b.d.b.g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f283a.a(user);
                }
            });
        }
    }

    public /* synthetic */ void a(User user) {
        Assert.hardAssert(this.syncEngine != null, "SyncEngine not yet initialized", new Object[0]);
        Logger.debug(LOG_TAG, "Credential changed. Current user: %s", user.getUid());
        this.syncEngine.handleCredentialChange(user);
    }

    public /* synthetic */ void a() {
        this.remoteStore.disableNetwork();
    }

    public /* synthetic */ void a(QueryListener queryListener) {
        this.eventManager.addQueryListener(queryListener);
    }

    public /* synthetic */ Document a(DocumentKey documentKey) {
        return this.localStore.readDocument(documentKey);
    }

    public static /* synthetic */ Document a(Task task) throws FirebaseFirestoreException {
        Document document = (Document) task.getResult();
        if (document.isFoundDocument()) {
            return document;
        }
        if (document.isNoDocument()) {
            return null;
        }
        throw new FirebaseFirestoreException("Failed to get document from cache. (However, this document may exist on the server. Run again without setting source to CACHE to attempt to retrieve the document from the server.)", FirebaseFirestoreException.Code.UNAVAILABLE);
    }

    public /* synthetic */ ViewSnapshot a(Query query) {
        QueryResult queryResultExecuteQuery = this.localStore.executeQuery(query, true);
        View view = new View(query, queryResultExecuteQuery.getRemoteKeys());
        return view.applyChanges(view.computeDocChanges(queryResultExecuteQuery.getDocuments())).getSnapshot();
    }

    public /* synthetic */ void a(List list, TaskCompletionSource taskCompletionSource) {
        this.syncEngine.writeMutations(list, taskCompletionSource);
    }

    public /* synthetic */ Task a(Function function) {
        return this.syncEngine.transaction(this.asyncQueue, function);
    }

    public /* synthetic */ void a(TaskCompletionSource taskCompletionSource) {
        this.syncEngine.registerPendingWritesTask(taskCompletionSource);
    }

    public /* synthetic */ void a(EventListener eventListener) {
        this.eventManager.addSnapshotsInSyncListener(eventListener);
    }

    public /* synthetic */ void a(BundleReader bundleReader, LoadBundleTask loadBundleTask) {
        this.syncEngine.loadBundle(bundleReader, loadBundleTask);
    }

    public /* synthetic */ void a(String str, TaskCompletionSource taskCompletionSource) {
        NamedQuery namedQuery = this.localStore.getNamedQuery(str);
        if (namedQuery != null) {
            Target target = namedQuery.getBundledQuery().getTarget();
            taskCompletionSource.setResult(new Query(target.getPath(), target.getCollectionGroup(), target.getFilters(), target.getOrderBy(), target.getLimit(), namedQuery.getBundledQuery().getLimitType(), target.getStartAt(), target.getEndAt()));
        } else {
            taskCompletionSource.setResult(null);
        }
    }

    public /* synthetic */ void a(List list) {
        this.localStore.configureFieldIndexes(list);
    }
}
