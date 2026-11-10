package com.google.firebase.firestore.core;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.local.IndexBackfiller;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.Persistence;
import com.google.firebase.firestore.local.Scheduler;
import com.google.firebase.firestore.remote.ConnectivityMonitor;
import com.google.firebase.firestore.remote.Datastore;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.util.AsyncQueue;

/* JADX INFO: loaded from: classes.dex */
public abstract class ComponentProvider {
    public ConnectivityMonitor connectivityMonitor;
    public EventManager eventManager;

    @Nullable
    public Scheduler garbageCollectionScheduler;
    public IndexBackfiller indexBackfiller;
    public LocalStore localStore;
    public Persistence persistence;
    public RemoteStore remoteStore;
    public SyncEngine syncEngine;

    public static class Configuration {
        public final AsyncQueue asyncQueue;
        public final Context context;
        public final DatabaseInfo databaseInfo;
        public final Datastore datastore;
        public final User initialUser;
        public final int maxConcurrentLimboResolutions;
        public final FirebaseFirestoreSettings settings;

        public Configuration(Context context, AsyncQueue asyncQueue, DatabaseInfo databaseInfo, Datastore datastore, User user, int i, FirebaseFirestoreSettings firebaseFirestoreSettings) {
            this.context = context;
            this.asyncQueue = asyncQueue;
            this.databaseInfo = databaseInfo;
            this.datastore = datastore;
            this.initialUser = user;
            this.maxConcurrentLimboResolutions = i;
            this.settings = firebaseFirestoreSettings;
        }

        public AsyncQueue getAsyncQueue() {
            return this.asyncQueue;
        }

        public Context getContext() {
            return this.context;
        }

        public DatabaseInfo getDatabaseInfo() {
            return this.databaseInfo;
        }

        public Datastore getDatastore() {
            return this.datastore;
        }

        public User getInitialUser() {
            return this.initialUser;
        }

        public int getMaxConcurrentLimboResolutions() {
            return this.maxConcurrentLimboResolutions;
        }

        public FirebaseFirestoreSettings getSettings() {
            return this.settings;
        }
    }

    public abstract ConnectivityMonitor createConnectivityMonitor(Configuration configuration);

    public abstract EventManager createEventManager(Configuration configuration);

    public abstract Scheduler createGarbageCollectionScheduler(Configuration configuration);

    public abstract IndexBackfiller createIndexBackfiller(Configuration configuration);

    public abstract LocalStore createLocalStore(Configuration configuration);

    public abstract Persistence createPersistence(Configuration configuration);

    public abstract RemoteStore createRemoteStore(Configuration configuration);

    public abstract SyncEngine createSyncEngine(Configuration configuration);

    public ConnectivityMonitor getConnectivityMonitor() {
        return this.connectivityMonitor;
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }

    @Nullable
    public Scheduler getGarbageCollectionScheduler() {
        return this.garbageCollectionScheduler;
    }

    public IndexBackfiller getIndexBackfiller() {
        return this.indexBackfiller;
    }

    public LocalStore getLocalStore() {
        return this.localStore;
    }

    public Persistence getPersistence() {
        return this.persistence;
    }

    public RemoteStore getRemoteStore() {
        return this.remoteStore;
    }

    public SyncEngine getSyncEngine() {
        return this.syncEngine;
    }

    public void initialize(Configuration configuration) {
        this.persistence = createPersistence(configuration);
        this.persistence.start();
        this.indexBackfiller = createIndexBackfiller(configuration);
        this.localStore = createLocalStore(configuration);
        this.connectivityMonitor = createConnectivityMonitor(configuration);
        this.remoteStore = createRemoteStore(configuration);
        this.syncEngine = createSyncEngine(configuration);
        this.eventManager = createEventManager(configuration);
        this.localStore.start();
        this.remoteStore.start();
        this.garbageCollectionScheduler = createGarbageCollectionScheduler(configuration);
    }
}
