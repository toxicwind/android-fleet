package com.google.firebase.firestore.local;

import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Supplier;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class MemoryPersistence extends Persistence {
    public ReferenceDelegate referenceDelegate;
    public boolean started;
    public final Map<User, MemoryMutationQueue> mutationQueues = new HashMap();
    public final MemoryIndexManager indexManager = new MemoryIndexManager();
    public final MemoryTargetCache targetCache = new MemoryTargetCache(this);
    public final MemoryBundleCache bundleCache = new MemoryBundleCache();
    public final MemoryRemoteDocumentCache remoteDocumentCache = new MemoryRemoteDocumentCache();
    public final Map<User, MemoryDocumentOverlayCache> overlays = new HashMap();

    public static MemoryPersistence createEagerGcMemoryPersistence() {
        MemoryPersistence memoryPersistence = new MemoryPersistence();
        memoryPersistence.referenceDelegate = new MemoryEagerReferenceDelegate(memoryPersistence);
        return memoryPersistence;
    }

    public static MemoryPersistence createLruGcMemoryPersistence(LruGarbageCollector.Params params, LocalSerializer localSerializer) {
        MemoryPersistence memoryPersistence = new MemoryPersistence();
        memoryPersistence.referenceDelegate = new MemoryLruReferenceDelegate(memoryPersistence, params, localSerializer);
        return memoryPersistence;
    }

    private void setReferenceDelegate(ReferenceDelegate referenceDelegate) {
        this.referenceDelegate = referenceDelegate;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public BundleCache getBundleCache() {
        return this.bundleCache;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public DocumentOverlayCache getDocumentOverlay(User user) {
        MemoryDocumentOverlayCache memoryDocumentOverlayCache = this.overlays.get(user);
        if (memoryDocumentOverlayCache != null) {
            return memoryDocumentOverlayCache;
        }
        MemoryDocumentOverlayCache memoryDocumentOverlayCache2 = new MemoryDocumentOverlayCache();
        this.overlays.put(user, memoryDocumentOverlayCache2);
        return memoryDocumentOverlayCache2;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public MutationQueue getMutationQueue(User user, IndexManager indexManager) {
        MemoryMutationQueue memoryMutationQueue = this.mutationQueues.get(user);
        if (memoryMutationQueue != null) {
            return memoryMutationQueue;
        }
        MemoryMutationQueue memoryMutationQueue2 = new MemoryMutationQueue(this, user);
        this.mutationQueues.put(user, memoryMutationQueue2);
        return memoryMutationQueue2;
    }

    public Iterable<MemoryMutationQueue> getMutationQueues() {
        return this.mutationQueues.values();
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public OverlayMigrationManager getOverlayMigrationManager() {
        return new MemoryOverlayMigrationManager();
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public ReferenceDelegate getReferenceDelegate() {
        return this.referenceDelegate;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public boolean isStarted() {
        return this.started;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public void runTransaction(String str, Runnable runnable) {
        this.referenceDelegate.onTransactionStarted();
        try {
            runnable.run();
        } finally {
            this.referenceDelegate.onTransactionCommitted();
        }
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public void shutdown() {
        Assert.hardAssert(this.started, "MemoryPersistence shutdown without start", new Object[0]);
        this.started = false;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public void start() {
        Assert.hardAssert(!this.started, "MemoryPersistence double-started!", new Object[0]);
        this.started = true;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public MemoryIndexManager getIndexManager(User user) {
        return this.indexManager;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public MemoryRemoteDocumentCache getRemoteDocumentCache() {
        return this.remoteDocumentCache;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public MemoryTargetCache getTargetCache() {
        return this.targetCache;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public <T> T runTransaction(String str, Supplier<T> supplier) {
        this.referenceDelegate.onTransactionStarted();
        try {
            return supplier.get();
        } finally {
            this.referenceDelegate.onTransactionCommitted();
        }
    }
}
