package com.google.firebase.firestore.remote;

import b.a.a.a.a;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class RemoteEvent {
    public final Map<DocumentKey, MutableDocument> documentUpdates;
    public final Set<DocumentKey> resolvedLimboDocuments;
    public final SnapshotVersion snapshotVersion;
    public final Map<Integer, TargetChange> targetChanges;
    public final Set<Integer> targetMismatches;

    public RemoteEvent(SnapshotVersion snapshotVersion, Map<Integer, TargetChange> map, Set<Integer> set, Map<DocumentKey, MutableDocument> map2, Set<DocumentKey> set2) {
        this.snapshotVersion = snapshotVersion;
        this.targetChanges = map;
        this.targetMismatches = set;
        this.documentUpdates = map2;
        this.resolvedLimboDocuments = set2;
    }

    public Map<DocumentKey, MutableDocument> getDocumentUpdates() {
        return this.documentUpdates;
    }

    public Set<DocumentKey> getResolvedLimboDocuments() {
        return this.resolvedLimboDocuments;
    }

    public SnapshotVersion getSnapshotVersion() {
        return this.snapshotVersion;
    }

    public Map<Integer, TargetChange> getTargetChanges() {
        return this.targetChanges;
    }

    public Set<Integer> getTargetMismatches() {
        return this.targetMismatches;
    }

    public String toString() {
        StringBuilder sbA = a.a("RemoteEvent{snapshotVersion=");
        sbA.append(this.snapshotVersion);
        sbA.append(", targetChanges=");
        sbA.append(this.targetChanges);
        sbA.append(", targetMismatches=");
        sbA.append(this.targetMismatches);
        sbA.append(", documentUpdates=");
        sbA.append(this.documentUpdates);
        sbA.append(", resolvedLimboDocuments=");
        return a.a(sbA, (Object) this.resolvedLimboDocuments, '}');
    }
}
