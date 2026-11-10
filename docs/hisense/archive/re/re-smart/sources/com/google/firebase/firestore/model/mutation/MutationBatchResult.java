package com.google.firebase.firestore.model.mutation;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import com.google.protobuf.ByteString;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class MutationBatchResult {
    public final MutationBatch batch;
    public final SnapshotVersion commitVersion;
    public final ImmutableSortedMap<DocumentKey, SnapshotVersion> docVersions;
    public final List<MutationResult> mutationResults;
    public final ByteString streamToken;

    public MutationBatchResult(MutationBatch mutationBatch, SnapshotVersion snapshotVersion, List<MutationResult> list, ByteString byteString, ImmutableSortedMap<DocumentKey, SnapshotVersion> immutableSortedMap) {
        this.batch = mutationBatch;
        this.commitVersion = snapshotVersion;
        this.mutationResults = list;
        this.streamToken = byteString;
        this.docVersions = immutableSortedMap;
    }

    public static MutationBatchResult create(MutationBatch mutationBatch, SnapshotVersion snapshotVersion, List<MutationResult> list, ByteString byteString) {
        Assert.hardAssert(mutationBatch.getMutations().size() == list.size(), "Mutations sent %d must equal results received %d", Integer.valueOf(mutationBatch.getMutations().size()), Integer.valueOf(list.size()));
        ImmutableSortedMap<DocumentKey, ?> immutableSortedMap = DocumentCollections.EMPTY_DOCUMENT_MAP;
        List<Mutation> mutations = mutationBatch.getMutations();
        ImmutableSortedMap<DocumentKey, ?> immutableSortedMapInsert = immutableSortedMap;
        for (int i = 0; i < mutations.size(); i++) {
            immutableSortedMapInsert = immutableSortedMapInsert.insert(mutations.get(i).getKey(), list.get(i).getVersion());
        }
        return new MutationBatchResult(mutationBatch, snapshotVersion, list, byteString, immutableSortedMapInsert);
    }

    public MutationBatch getBatch() {
        return this.batch;
    }

    public SnapshotVersion getCommitVersion() {
        return this.commitVersion;
    }

    public ImmutableSortedMap<DocumentKey, SnapshotVersion> getDocVersions() {
        return this.docVersions;
    }

    public List<MutationResult> getMutationResults() {
        return this.mutationResults;
    }

    public ByteString getStreamToken() {
        return this.streamToken;
    }
}
