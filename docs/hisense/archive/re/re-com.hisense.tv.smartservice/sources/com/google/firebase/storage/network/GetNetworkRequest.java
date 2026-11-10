package com.google.firebase.storage.network;

import androidx.annotation.NonNull;
import com.google.common.net.HttpHeaders;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class GetNetworkRequest extends NetworkRequest {
    public static final String TAG = "GetNetworkRequest";

    public GetNetworkRequest(@NonNull StorageReferenceUri storageReferenceUri, @NonNull FirebaseApp firebaseApp, long j) {
        super(storageReferenceUri, firebaseApp);
        if (j != 0) {
            this.requestHeaders.put(HttpHeaders.RANGE, "bytes=" + j + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING);
        }
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    @NonNull
    public String getAction() {
        return NetworkRequest.GET;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    @NonNull
    public Map<String, String> getQueryParameters() {
        return Collections.singletonMap("alt", "media");
    }
}
