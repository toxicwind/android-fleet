package com.google.firebase.firestore.local;

import android.database.Cursor;
import androidx.annotation.Nullable;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Function;
import com.google.firestore.v1.Write;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class SQLiteDocumentOverlayCache implements DocumentOverlayCache {
    public final SQLitePersistence db;
    public final LocalSerializer serializer;
    public final String uid;

    public SQLiteDocumentOverlayCache(SQLitePersistence sQLitePersistence, LocalSerializer localSerializer, User user) {
        this.db = sQLitePersistence;
        this.serializer = localSerializer;
        this.uid = user.isAuthenticated() ? user.getUid() : "";
    }

    private void saveOverlay(int i, DocumentKey documentKey, @Nullable Mutation mutation) {
        this.db.execute("INSERT OR REPLACE INTO document_overlays (uid, collection_group, collection_path, document_id, largest_batch_id, overlay_mutation) VALUES (?, ?, ?, ?, ?, ?)", this.uid, documentKey.getCollectionGroup(), EncodedPath.encode(documentKey.getPath().popLast()), documentKey.getPath().getLastSegment(), Integer.valueOf(i), this.serializer.encodeMutation(mutation).toByteArray());
    }

    public /* synthetic */ Mutation a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            return this.serializer.decodeMutation(Write.parseFrom(cursor.getBlob(0)));
        } catch (InvalidProtocolBufferException e2) {
            Assert.fail("Overlay failed to parse: %s", e2);
            throw null;
        }
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    @Nullable
    public Mutation getOverlay(DocumentKey documentKey) {
        return (Mutation) this.db.query("SELECT overlay_mutation FROM document_overlays WHERE uid = ? AND collection_path = ? AND document_id = ?").binding(this.uid, EncodedPath.encode(documentKey.getPath().popLast()), documentKey.getPath().getLastSegment()).firstValue(new Function() { // from class: b.e.b.d.d.E
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return this.f339a.a((Cursor) obj);
            }
        });
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public Map<DocumentKey, Mutation> getOverlays(final ResourcePath resourcePath, int i) {
        String strEncode = EncodedPath.encode(resourcePath);
        final HashMap map = new HashMap();
        this.db.query("SELECT document_id, overlay_mutation FROM document_overlays WHERE uid = ? AND collection_path = ? AND largest_batch_id > ?").binding(this.uid, strEncode, Integer.valueOf(i)).forEach(new Consumer() { // from class: b.e.b.d.d.D
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f335a.a(map, resourcePath, (Cursor) obj);
            }
        });
        return map;
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public void removeOverlaysForBatchId(int i) {
        this.db.execute("DELETE FROM document_overlays WHERE uid = ? AND largest_batch_id = ?", this.uid, Integer.valueOf(i));
    }

    @Override // com.google.firebase.firestore.local.DocumentOverlayCache
    public void saveOverlays(int i, Map<DocumentKey, Mutation> map) {
        for (Map.Entry<DocumentKey, Mutation> entry : map.entrySet()) {
            if (entry.getValue() != null) {
                saveOverlay(i, entry.getKey(), entry.getValue());
            }
        }
    }

    public /* synthetic */ void a(Map map, ResourcePath resourcePath, Cursor cursor) {
        try {
            map.put(new DocumentKey(resourcePath.append(cursor.getString(0))), this.serializer.decodeMutation(Write.parseFrom(cursor.getBlob(1))));
        } catch (InvalidProtocolBufferException e2) {
            Assert.fail("Overlay failed to parse: %s", e2);
            throw null;
        }
    }
}
