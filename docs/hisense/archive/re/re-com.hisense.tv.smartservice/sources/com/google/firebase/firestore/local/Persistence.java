package com.google.firebase.firestore.local;

import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.util.Supplier;

/* JADX INFO: loaded from: classes.dex */
public abstract class Persistence {
    public static String DATA_MIGRATION_BUILD_OVERLAYS = "BUILD_OVERLAYS";
    public static boolean INDEXING_SUPPORT_ENABLED = false;
    public static final String TAG = "Persistence";

    public abstract BundleCache getBundleCache();

    public abstract DocumentOverlayCache getDocumentOverlay(User user);

    public abstract IndexManager getIndexManager(User user);

    public abstract MutationQueue getMutationQueue(User user, IndexManager indexManager);

    public abstract OverlayMigrationManager getOverlayMigrationManager();

    public abstract ReferenceDelegate getReferenceDelegate();

    public abstract RemoteDocumentCache getRemoteDocumentCache();

    public abstract TargetCache getTargetCache();

    public abstract boolean isStarted();

    public abstract <T> T runTransaction(String str, Supplier<T> supplier);

    public abstract void runTransaction(String str, Runnable runnable);

    public abstract void shutdown();

    public abstract void start();
}
