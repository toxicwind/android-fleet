package com.google.firebase.storage.network;

import android.net.Uri;
import androidx.annotation.NonNull;
import com.google.android.gms.actions.SearchIntents;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;

/* JADX INFO: loaded from: classes.dex */
public class ResumableUploadQueryRequest extends ResumableNetworkRequest {
    public final Uri uploadURL;

    public ResumableUploadQueryRequest(@NonNull StorageReferenceUri storageReferenceUri, @NonNull FirebaseApp firebaseApp, @NonNull Uri uri) {
        super(storageReferenceUri, firebaseApp);
        this.uploadURL = uri;
        this.requestHeaders.put(ResumableNetworkRequest.PROTOCOL, "resumable");
        this.requestHeaders.put(ResumableNetworkRequest.COMMAND, SearchIntents.EXTRA_QUERY);
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    @NonNull
    public String getAction() {
        return NetworkRequest.POST;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    @NonNull
    public Uri getURL() {
        return this.uploadURL;
    }
}
