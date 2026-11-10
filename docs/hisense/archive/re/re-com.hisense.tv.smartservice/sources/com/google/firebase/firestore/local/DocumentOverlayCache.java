package com.google.firebase.firestore.local;

import androidx.annotation.Nullable;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.Mutation;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface DocumentOverlayCache {
    @Nullable
    Mutation getOverlay(DocumentKey documentKey);

    Map<DocumentKey, Mutation> getOverlays(ResourcePath resourcePath, int i);

    void removeOverlaysForBatchId(int i);

    void saveOverlays(int i, Map<DocumentKey, Mutation> map);
}
