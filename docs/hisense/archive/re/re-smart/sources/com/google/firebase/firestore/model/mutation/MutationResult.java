package com.google.firebase.firestore.model.mutation;

import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firestore.v1.Value;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class MutationResult {
    public final List<Value> transformResults;
    public final SnapshotVersion version;

    public MutationResult(SnapshotVersion snapshotVersion, List<Value> list) {
        if (snapshotVersion == null) {
            throw new NullPointerException();
        }
        this.version = snapshotVersion;
        this.transformResults = list;
    }

    public List<Value> getTransformResults() {
        return this.transformResults;
    }

    public SnapshotVersion getVersion() {
        return this.version;
    }
}
