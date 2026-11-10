package com.google.firebase.firestore.core;

import androidx.annotation.Nullable;
import c.a.za;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.local.IndexBackfiller;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.MemoryPersistence;
import com.google.firebase.firestore.local.Persistence;
import com.google.firebase.firestore.local.QueryEngine;
import com.google.firebase.firestore.local.Scheduler;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.mutation.MutationBatchResult;
import com.google.firebase.firestore.remote.AndroidConnectivityMonitor;
import com.google.firebase.firestore.remote.RemoteEvent;
import com.google.firebase.firestore.remote.RemoteStore;

/* JADX INFO: loaded from: classes.dex */
public class MemoryComponentProvider extends ComponentProvider {

    private class RemoteStoreCallback implements RemoteStore.RemoteStoreCallback {
        public RemoteStoreCallback() {
        }

        @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
        public ImmutableSortedSet<DocumentKey> getRemoteKeysForTarget(int i) {
            return MemoryComponentProvider.this.getSyncEngine().getRemoteKeysForTarget(i);
        }

        @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
        public void handleOnlineStateChange(OnlineState onlineState) {
            MemoryComponentProvider.this.getSyncEngine().handleOnlineStateChange(onlineState);
        }

        @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
        public void handleRejectedListen(int i, za zaVar) {
            MemoryComponentProvider.this.getSyncEngine().handleRejectedListen(i, zaVar);
        }

        @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
        public void handleRejectedWrite(int i, za zaVar) {
            MemoryComponentProvider.this.getSyncEngine().handleRejectedWrite(i, zaVar);
        }

        @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
        public void handleRemoteEvent(RemoteEvent remoteEvent) {
            MemoryComponentProvider.this.getSyncEngine().handleRemoteEvent(remoteEvent);
        }

        @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
        public void handleSuccessfulWrite(MutationBatchResult mutationBatchResult) {
            MemoryComponentProvider.this.getSyncEngine().handleSuccessfulWrite(mutationBatchResult);
        }

        public /* synthetic */ RemoteStoreCallback(AnonymousClass1 anonymousClass1) {
        }
    }

    @Override // com.google.firebase.firestore.core.ComponentProvider
    public EventManager createEventManager(ComponentProvider.Configuration configuration) {
        return new EventManager(getSyncEngine());
    }

    @Override // com.google.firebase.firestore.core.ComponentProvider
    @Nullable
    public Scheduler createGarbageCollectionScheduler(ComponentProvider.Configuration configuration) {
        return null;
    }

    @Override // com.google.firebase.firestore.core.ComponentProvider
    public IndexBackfiller createIndexBackfiller(ComponentProvider.Configuration configuration) {
        return new IndexBackfiller(getPersistence(), configuration.getAsyncQueue());
    }

    @Override // com.google.firebase.firestore.core.ComponentProvider
    public LocalStore createLocalStore(ComponentProvider.Configuration configuration) {
        return new LocalStore(getPersistence(), getIndexBackfiller(), new QueryEngine(), configuration.getInitialUser());
    }

    @Override // com.google.firebase.firestore.core.ComponentProvider
    public Persistence createPersistence(ComponentProvider.Configuration configuration) {
        return MemoryPersistence.createEagerGcMemoryPersistence();
    }

    @Override // com.google.firebase.firestore.core.ComponentProvider
    public RemoteStore createRemoteStore(ComponentProvider.Configuration configuration) {
        return new RemoteStore(new RemoteStoreCallback(null), getLocalStore(), configuration.getDatastore(), configuration.getAsyncQueue(), getConnectivityMonitor());
    }

    @Override // com.google.firebase.firestore.core.ComponentProvider
    public SyncEngine createSyncEngine(ComponentProvider.Configuration configuration) {
        return new SyncEngine(getLocalStore(), getRemoteStore(), configuration.getInitialUser(), configuration.getMaxConcurrentLimboResolutions());
    }

    @Override // com.google.firebase.firestore.core.ComponentProvider
    public AndroidConnectivityMonitor createConnectivityMonitor(ComponentProvider.Configuration configuration) {
        return new AndroidConnectivityMonitor(configuration.getContext());
    }
}
