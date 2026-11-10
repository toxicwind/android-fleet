package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.local.EncodedPath;
import com.google.firebase.firestore.local.SQLiteTargetCache;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.proto.Target;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.protobuf.InvalidProtocolBufferException;

/* JADX INFO: loaded from: classes.dex */
public final class SQLiteTargetCache implements TargetCache {
    public final SQLitePersistence db;
    public int highestTargetId;
    public long lastListenSequenceNumber;
    public SnapshotVersion lastRemoteSnapshotVersion = SnapshotVersion.NONE;
    public final LocalSerializer localSerializer;
    public long targetCount;

    /* JADX INFO: Access modifiers changed from: private */
    public static class DocumentKeysHolder {
        public ImmutableSortedSet<DocumentKey> keys;

        public DocumentKeysHolder() {
            this.keys = DocumentKey.EMPTY_KEY_SET;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static class TargetDataHolder {
        public TargetData targetData;

        public TargetDataHolder() {
        }

        public /* synthetic */ TargetDataHolder(AnonymousClass1 anonymousClass1) {
        }
    }

    public SQLiteTargetCache(SQLitePersistence sQLitePersistence, LocalSerializer localSerializer) {
        this.db = sQLitePersistence;
        this.localSerializer = localSerializer;
    }

    private TargetData decodeTargetData(byte[] bArr) {
        try {
            return this.localSerializer.decodeTargetData(Target.parseFrom(bArr));
        } catch (InvalidProtocolBufferException e2) {
            Assert.fail("TargetData failed to parse: %s", e2);
            throw null;
        }
    }

    private void removeTarget(int i) {
        removeMatchingKeysForTargetId(i);
        this.db.execute("DELETE FROM targets WHERE target_id = ?", Integer.valueOf(i));
        this.targetCount--;
    }

    private void saveTargetData(TargetData targetData) {
        int targetId = targetData.getTargetId();
        String canonicalId = targetData.getTarget().getCanonicalId();
        Timestamp timestamp = targetData.getSnapshotVersion().getTimestamp();
        this.db.execute("INSERT OR REPLACE INTO targets (target_id, canonical_id, snapshot_version_seconds, snapshot_version_nanos, resume_token, last_listen_sequence_number, target_proto) VALUES (?, ?, ?, ?, ?, ?, ?)", Integer.valueOf(targetId), canonicalId, Long.valueOf(timestamp.getSeconds()), Integer.valueOf(timestamp.getNanoseconds()), targetData.getResumeToken().toByteArray(), Long.valueOf(targetData.getSequenceNumber()), this.localSerializer.encodeTargetData(targetData).toByteArray());
    }

    private boolean updateMetadata(TargetData targetData) {
        boolean z;
        if (targetData.getTargetId() > this.highestTargetId) {
            this.highestTargetId = targetData.getTargetId();
            z = true;
        } else {
            z = false;
        }
        if (targetData.getSequenceNumber() <= this.lastListenSequenceNumber) {
            return z;
        }
        this.lastListenSequenceNumber = targetData.getSequenceNumber();
        return true;
    }

    private void writeMetadata() {
        this.db.execute("UPDATE target_globals SET highest_target_id = ?, highest_listen_sequence_number = ?, last_remote_snapshot_version_seconds = ?, last_remote_snapshot_version_nanos = ?, target_count = ?", Integer.valueOf(this.highestTargetId), Long.valueOf(this.lastListenSequenceNumber), Long.valueOf(this.lastRemoteSnapshotVersion.getTimestamp().getSeconds()), Integer.valueOf(this.lastRemoteSnapshotVersion.getTimestamp().getNanoseconds()), Long.valueOf(this.targetCount));
    }

    public /* synthetic */ void a(Cursor cursor) {
        this.highestTargetId = cursor.getInt(0);
        this.lastListenSequenceNumber = cursor.getInt(1);
        this.lastRemoteSnapshotVersion = new SnapshotVersion(new Timestamp(cursor.getLong(2), cursor.getInt(3)));
        this.targetCount = cursor.getLong(4);
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void addMatchingKeys(ImmutableSortedSet<DocumentKey> immutableSortedSet, int i) {
        SQLiteStatement sQLiteStatementPrepare = this.db.prepare("INSERT OR IGNORE INTO target_documents (target_id, path) VALUES (?, ?)");
        SQLiteLruReferenceDelegate referenceDelegate = this.db.getReferenceDelegate();
        for (DocumentKey documentKey : immutableSortedSet) {
            this.db.execute(sQLiteStatementPrepare, Integer.valueOf(i), EncodedPath.encode(documentKey.getPath()));
            referenceDelegate.addReference(documentKey);
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void addTargetData(TargetData targetData) {
        saveTargetData(targetData);
        updateMetadata(targetData);
        this.targetCount++;
        writeMetadata();
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public boolean containsKey(DocumentKey documentKey) {
        return !this.db.query("SELECT target_id FROM target_documents WHERE path = ? AND target_id != 0 LIMIT 1").binding(EncodedPath.encode(documentKey.getPath())).isEmpty();
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void forEachTarget(final Consumer<TargetData> consumer) {
        this.db.query("SELECT target_proto FROM targets").forEach(new Consumer() { // from class: b.e.b.d.d.Ja
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f356a.a(consumer, (Cursor) obj);
            }
        });
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public long getHighestListenSequenceNumber() {
        return this.lastListenSequenceNumber;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public int getHighestTargetId() {
        return this.highestTargetId;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public SnapshotVersion getLastRemoteSnapshotVersion() {
        return this.lastRemoteSnapshotVersion;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public ImmutableSortedSet<DocumentKey> getMatchingKeysForTargetId(int i) {
        final DocumentKeysHolder documentKeysHolder = new DocumentKeysHolder();
        this.db.query("SELECT path FROM target_documents WHERE target_id = ?").binding(Integer.valueOf(i)).forEach(new Consumer() { // from class: b.e.b.d.d.Ka
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteTargetCache.DocumentKeysHolder documentKeysHolder2 = documentKeysHolder;
                documentKeysHolder2.keys = documentKeysHolder2.keys.insert(new DocumentKey(EncodedPath.decodeResourcePath(((Cursor) obj).getString(0))));
            }
        });
        return documentKeysHolder.keys;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public long getTargetCount() {
        return this.targetCount;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    @Nullable
    public TargetData getTargetData(final com.google.firebase.firestore.core.Target target) {
        String canonicalId = target.getCanonicalId();
        final TargetDataHolder targetDataHolder = new TargetDataHolder(null);
        this.db.query("SELECT target_proto FROM targets WHERE canonical_id = ?").binding(canonicalId).forEach(new Consumer() { // from class: b.e.b.d.d.Ia
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f352a.a(target, targetDataHolder, (Cursor) obj);
            }
        });
        return targetDataHolder.targetData;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void removeMatchingKeys(ImmutableSortedSet<DocumentKey> immutableSortedSet, int i) {
        SQLiteStatement sQLiteStatementPrepare = this.db.prepare("DELETE FROM target_documents WHERE target_id = ? AND path = ?");
        SQLiteLruReferenceDelegate referenceDelegate = this.db.getReferenceDelegate();
        for (DocumentKey documentKey : immutableSortedSet) {
            this.db.execute(sQLiteStatementPrepare, Integer.valueOf(i), EncodedPath.encode(documentKey.getPath()));
            referenceDelegate.removeReference(documentKey);
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void removeMatchingKeysForTargetId(int i) {
        this.db.execute("DELETE FROM target_documents WHERE target_id = ?", Integer.valueOf(i));
    }

    public int removeQueries(long j, final SparseArray<?> sparseArray) {
        final int[] iArr = new int[1];
        this.db.query("SELECT target_id FROM targets WHERE last_listen_sequence_number <= ?").binding(Long.valueOf(j)).forEach(new Consumer() { // from class: b.e.b.d.d.La
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f361a.a(sparseArray, iArr, (Cursor) obj);
            }
        });
        writeMetadata();
        return iArr[0];
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void removeTargetData(TargetData targetData) {
        removeTarget(targetData.getTargetId());
        writeMetadata();
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void setLastRemoteSnapshotVersion(SnapshotVersion snapshotVersion) {
        this.lastRemoteSnapshotVersion = snapshotVersion;
        writeMetadata();
    }

    public void start() {
        Assert.hardAssert(this.db.query("SELECT highest_target_id, highest_listen_sequence_number, last_remote_snapshot_version_seconds, last_remote_snapshot_version_nanos, target_count FROM target_globals LIMIT 1").first(new Consumer() { // from class: b.e.b.d.d.Ha
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f348a.a((Cursor) obj);
            }
        }) == 1, "Missing target_globals entry", new Object[0]);
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void updateTargetData(TargetData targetData) {
        saveTargetData(targetData);
        if (updateMetadata(targetData)) {
            writeMetadata();
        }
    }

    public /* synthetic */ void a(Consumer consumer, Cursor cursor) {
        consumer.accept(decodeTargetData(cursor.getBlob(0)));
    }

    public /* synthetic */ void a(SparseArray sparseArray, int[] iArr, Cursor cursor) {
        int i = cursor.getInt(0);
        if (sparseArray.get(i) == null) {
            removeTarget(i);
            iArr[0] = iArr[0] + 1;
        }
    }

    public /* synthetic */ void a(com.google.firebase.firestore.core.Target target, TargetDataHolder targetDataHolder, Cursor cursor) {
        TargetData targetDataDecodeTargetData = decodeTargetData(cursor.getBlob(0));
        if (target.equals(targetDataDecodeTargetData.getTarget())) {
            targetDataHolder.targetData = targetDataDecodeTargetData;
        }
    }
}
