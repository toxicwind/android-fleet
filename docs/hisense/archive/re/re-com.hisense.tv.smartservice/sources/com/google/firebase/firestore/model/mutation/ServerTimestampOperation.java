package com.google.firebase.firestore.model.mutation;

import androidx.annotation.Nullable;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.ServerTimestamps;
import com.google.firestore.v1.Value;

/* JADX INFO: loaded from: classes.dex */
public class ServerTimestampOperation implements TransformOperation {
    public static final ServerTimestampOperation SHARED_INSTANCE = new ServerTimestampOperation();

    public static ServerTimestampOperation getInstance() {
        return SHARED_INSTANCE;
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value applyToLocalView(@Nullable Value value, Timestamp timestamp) {
        return ServerTimestamps.valueOf(timestamp, value);
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    public Value applyToRemoteDocument(@Nullable Value value, Value value2) {
        return value2;
    }

    @Override // com.google.firebase.firestore.model.mutation.TransformOperation
    @Nullable
    public Value computeBaseValue(@Nullable Value value) {
        return null;
    }
}
