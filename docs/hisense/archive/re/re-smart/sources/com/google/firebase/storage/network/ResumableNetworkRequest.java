package com.google.firebase.storage.network;

import androidx.annotation.NonNull;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;

/* JADX INFO: loaded from: classes.dex */
public abstract class ResumableNetworkRequest extends NetworkRequest {

    @NonNull
    public static final String COMMAND = "X-Goog-Upload-Command";

    @NonNull
    public static final String CONTENT_TYPE = "X-Goog-Upload-Header-Content-Type";

    @NonNull
    public static final String OFFSET = "X-Goog-Upload-Offset";

    @NonNull
    public static final String PROTOCOL = "X-Goog-Upload-Protocol";

    public ResumableNetworkRequest(@NonNull StorageReferenceUri storageReferenceUri, @NonNull FirebaseApp firebaseApp) {
        super(storageReferenceUri, firebaseApp);
    }
}
