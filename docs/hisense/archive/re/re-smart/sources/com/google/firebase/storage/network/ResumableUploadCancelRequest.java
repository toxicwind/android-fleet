package com.google.firebase.storage.network;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;

/* JADX INFO: loaded from: classes.dex */
public class ResumableUploadCancelRequest extends ResumableNetworkRequest {

    @VisibleForTesting
    public static boolean cancelCalled = false;
    public final Uri uploadURL;

    public ResumableUploadCancelRequest(@NonNull StorageReferenceUri storageReferenceUri, @NonNull FirebaseApp firebaseApp, @NonNull Uri uri) {
        super(storageReferenceUri, firebaseApp);
        cancelCalled = true;
        this.uploadURL = uri;
        this.requestHeaders.put(ResumableNetworkRequest.PROTOCOL, "resumable");
        this.requestHeaders.put(ResumableNetworkRequest.COMMAND, "cancel");
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
