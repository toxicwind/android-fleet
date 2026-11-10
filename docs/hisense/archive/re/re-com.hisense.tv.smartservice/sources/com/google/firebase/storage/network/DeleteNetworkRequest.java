package com.google.firebase.storage.network;

import androidx.annotation.NonNull;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;

/* JADX INFO: loaded from: classes.dex */
public class DeleteNetworkRequest extends NetworkRequest {
    public DeleteNetworkRequest(@NonNull StorageReferenceUri storageReferenceUri, @NonNull FirebaseApp firebaseApp) {
        super(storageReferenceUri, firebaseApp);
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    @NonNull
    public String getAction() {
        return NetworkRequest.DELETE;
    }
}
