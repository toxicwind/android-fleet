package com.google.firebase.storage.network;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;
import e.b.d;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ResumableUploadStartRequest extends ResumableNetworkRequest {
    public final String contentType;
    public final d metadata;

    public ResumableUploadStartRequest(@NonNull StorageReferenceUri storageReferenceUri, @NonNull FirebaseApp firebaseApp, @Nullable d dVar, @NonNull String str) {
        super(storageReferenceUri, firebaseApp);
        this.metadata = dVar;
        this.contentType = str;
        if (TextUtils.isEmpty(this.contentType)) {
            this.mException = new IllegalArgumentException("mContentType is null or empty");
        }
        this.requestHeaders.put(ResumableNetworkRequest.PROTOCOL, "resumable");
        this.requestHeaders.put(ResumableNetworkRequest.COMMAND, "start");
        this.requestHeaders.put(ResumableNetworkRequest.CONTENT_TYPE, this.contentType);
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    @NonNull
    public String getAction() {
        return NetworkRequest.POST;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    @Nullable
    public d getOutputJSON() {
        return this.metadata;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    @NonNull
    public Map<String, String> getQueryParameters() {
        HashMap map = new HashMap();
        map.put("name", getPathWithoutBucket());
        map.put("uploadType", "resumable");
        return map;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    @NonNull
    public Uri getURL() {
        String authority = getStorageReferenceUri().getGsUri().getAuthority();
        Uri.Builder builderBuildUpon = getStorageReferenceUri().getHttpBaseUri().buildUpon();
        builderBuildUpon.appendPath("b");
        builderBuildUpon.appendPath(authority);
        builderBuildUpon.appendPath("o");
        return builderBuildUpon.build();
    }
}
