package com.google.firebase.storage.network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;
import e.b.d;

/* JADX INFO: loaded from: classes.dex */
public class UpdateMetadataNetworkRequest extends NetworkRequest {
    public final d metadata;

    public UpdateMetadataNetworkRequest(@NonNull StorageReferenceUri storageReferenceUri, @NonNull FirebaseApp firebaseApp, @Nullable d dVar) {
        super(storageReferenceUri, firebaseApp);
        this.metadata = dVar;
        setCustomHeader("X-HTTP-Method-Override", NetworkRequest.PATCH);
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    @NonNull
    public String getAction() {
        return NetworkRequest.PUT;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    @Nullable
    public d getOutputJSON() {
        return this.metadata;
    }
}
