package com.google.firebase.storage.network;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ListNetworkRequest extends NetworkRequest {

    @Nullable
    public final Integer maxPageSize;

    @Nullable
    public final String nextPageToken;

    public ListNetworkRequest(@NonNull StorageReferenceUri storageReferenceUri, @NonNull FirebaseApp firebaseApp, @Nullable Integer num, @Nullable String str) {
        super(storageReferenceUri, firebaseApp);
        this.maxPageSize = num;
        this.nextPageToken = str;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    @NonNull
    public String getAction() {
        return NetworkRequest.GET;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    @NonNull
    public Map<String, String> getQueryParameters() {
        HashMap map = new HashMap();
        String pathWithoutBucket = getPathWithoutBucket();
        if (!pathWithoutBucket.isEmpty()) {
            map.put("prefix", pathWithoutBucket + "/");
        }
        map.put("delimiter", "/");
        Integer num = this.maxPageSize;
        if (num != null) {
            map.put("maxResults", Integer.toString(num.intValue()));
        }
        if (!TextUtils.isEmpty(this.nextPageToken)) {
            map.put("pageToken", this.nextPageToken);
        }
        return map;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    @NonNull
    public Uri getURL() {
        return Uri.parse(getStorageReferenceUri().getHttpBaseUri() + "/b/" + getStorageReferenceUri().getGsUri().getAuthority() + "/o");
    }
}
