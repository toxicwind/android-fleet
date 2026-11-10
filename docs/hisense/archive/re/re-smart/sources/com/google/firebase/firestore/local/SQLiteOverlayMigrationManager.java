package com.google.firebase.firestore.local;

import android.database.Cursor;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.local.SQLiteOverlayMigrationManager;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class SQLiteOverlayMigrationManager implements OverlayMigrationManager {
    public final SQLitePersistence db;

    public SQLiteOverlayMigrationManager(SQLitePersistence sQLitePersistence) {
        this.db = sQLitePersistence;
    }

    private void buildOverlays() {
        this.db.runTransaction("build overlays", new Runnable() { // from class: b.e.b.d.d.ga
            @Override // java.lang.Runnable
            public final void run() {
                this.f403a.a();
            }
        });
    }

    private Set<String> getAllUserIds() {
        final HashSet hashSet = new HashSet();
        this.db.query("SELECT DISTINCT uid FROM mutation_queues").forEach(new Consumer() { // from class: b.e.b.d.d.ea
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                hashSet.add(((Cursor) obj).getString(0));
            }
        });
        return hashSet;
    }

    private void removePendingOverlayMigrations() {
        this.db.execute("DELETE FROM data_migrations WHERE migration_name = ?", Persistence.DATA_MIGRATION_BUILD_OVERLAYS);
    }

    public /* synthetic */ void a() {
        Set<String> allUserIds = getAllUserIds();
        RemoteDocumentCache remoteDocumentCache = this.db.getRemoteDocumentCache();
        Iterator<String> it = allUserIds.iterator();
        while (it.hasNext()) {
            User user = new User(it.next());
            SQLitePersistence sQLitePersistence = this.db;
            MutationQueue mutationQueue = sQLitePersistence.getMutationQueue(user, sQLitePersistence.getIndexManager(user));
            HashSet hashSet = new HashSet();
            Iterator<MutationBatch> it2 = mutationQueue.getAllMutationBatches().iterator();
            while (it2.hasNext()) {
                hashSet.addAll(it2.next().getKeys());
            }
            new LocalDocumentsView(remoteDocumentCache, mutationQueue, this.db.getDocumentOverlay(user), this.db.getIndexManager(user)).recalculateAndSaveOverlays(hashSet);
        }
        removePendingOverlayMigrations();
    }

    @VisibleForTesting
    public boolean hasPendingOverlayMigration() {
        final Boolean[] boolArr = {false};
        this.db.query("SELECT migration_name FROM data_migrations").forEach(new Consumer() { // from class: b.e.b.d.d.fa
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteOverlayMigrationManager.a(boolArr, (Cursor) obj);
            }
        });
        return boolArr[0].booleanValue();
    }

    @Override // com.google.firebase.firestore.local.OverlayMigrationManager
    public void run() {
        buildOverlays();
    }

    public static /* synthetic */ void a(Boolean[] boolArr, Cursor cursor) {
        try {
            if (Persistence.DATA_MIGRATION_BUILD_OVERLAYS.equals(cursor.getString(0))) {
                boolArr[0] = true;
            }
        } catch (IllegalArgumentException e2) {
            Assert.fail("SQLitePersistence.DataMigration failed to parse: %s", e2);
            throw null;
        }
    }
}
