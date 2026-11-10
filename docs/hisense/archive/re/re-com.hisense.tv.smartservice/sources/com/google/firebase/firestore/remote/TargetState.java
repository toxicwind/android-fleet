package com.google.firebase.firestore.remote;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Assert;
import com.google.protobuf.ByteString;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class TargetState {
    public int outstandingResponses = 0;
    public final Map<DocumentKey, DocumentViewChange.Type> documentChanges = new HashMap();
    public boolean hasChanges = true;
    public ByteString resumeToken = ByteString.EMPTY;
    public boolean current = false;

    /* JADX INFO: renamed from: com.google.firebase.firestore.remote.TargetState$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type = new int[DocumentViewChange.Type.values().length];

        static {
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.MODIFIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public void addDocumentChange(DocumentKey documentKey, DocumentViewChange.Type type) {
        this.hasChanges = true;
        this.documentChanges.put(documentKey, type);
    }

    public void clearChanges() {
        this.hasChanges = false;
        this.documentChanges.clear();
    }

    public boolean hasChanges() {
        return this.hasChanges;
    }

    public boolean isCurrent() {
        return this.current;
    }

    public boolean isPending() {
        return this.outstandingResponses != 0;
    }

    public void markCurrent() {
        this.hasChanges = true;
        this.current = true;
    }

    public void recordPendingTargetRequest() {
        this.outstandingResponses++;
    }

    public void recordTargetResponse() {
        this.outstandingResponses--;
    }

    public void removeDocumentChange(DocumentKey documentKey) {
        this.hasChanges = true;
        this.documentChanges.remove(documentKey);
    }

    public TargetChange toTargetChange() {
        ImmutableSortedSet<DocumentKey> immutableSortedSet = DocumentKey.EMPTY_KEY_SET;
        ImmutableSortedSet<DocumentKey> immutableSortedSetInsert = immutableSortedSet;
        ImmutableSortedSet<DocumentKey> immutableSortedSetInsert2 = immutableSortedSetInsert;
        ImmutableSortedSet<DocumentKey> immutableSortedSetInsert3 = immutableSortedSetInsert2;
        for (Map.Entry<DocumentKey, DocumentViewChange.Type> entry : this.documentChanges.entrySet()) {
            DocumentKey key = entry.getKey();
            DocumentViewChange.Type value = entry.getValue();
            int iOrdinal = value.ordinal();
            if (iOrdinal == 0) {
                immutableSortedSetInsert3 = immutableSortedSetInsert3.insert(key);
            } else if (iOrdinal == 1) {
                immutableSortedSetInsert = immutableSortedSetInsert.insert(key);
            } else {
                if (iOrdinal != 2) {
                    Assert.fail("Encountered invalid change type: %s", value);
                    throw null;
                }
                immutableSortedSetInsert2 = immutableSortedSetInsert2.insert(key);
            }
        }
        return new TargetChange(this.resumeToken, this.current, immutableSortedSetInsert, immutableSortedSetInsert2, immutableSortedSetInsert3);
    }

    public void updateResumeToken(ByteString byteString) {
        if (byteString.isEmpty()) {
            return;
        }
        this.hasChanges = true;
        this.resumeToken = byteString;
    }
}
