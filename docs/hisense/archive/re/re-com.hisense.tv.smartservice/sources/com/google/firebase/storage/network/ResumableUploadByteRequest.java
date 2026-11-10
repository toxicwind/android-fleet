package com.google.firebase.storage.network;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;

/* JADX INFO: loaded from: classes.dex */
public class ResumableUploadByteRequest extends ResumableNetworkRequest {
    public final int bytesToWrite;
    public final byte[] chunk;
    public final boolean isFinal;
    public final long offset;
    public final Uri uploadURL;

    public ResumableUploadByteRequest(@NonNull StorageReferenceUri storageReferenceUri, @NonNull FirebaseApp firebaseApp, @NonNull Uri uri, @Nullable byte[] bArr, long j, int i, boolean z) {
        super(storageReferenceUri, firebaseApp);
        if (bArr == null && i != -1) {
            this.mException = new IllegalArgumentException("contentType is null or empty");
        }
        if (j < 0) {
            this.mException = new IllegalArgumentException("offset cannot be negative");
        }
        this.bytesToWrite = i;
        this.uploadURL = uri;
        this.chunk = i <= 0 ? null : bArr;
        this.offset = j;
        this.isFinal = z;
        this.requestHeaders.put(ResumableNetworkRequest.PROTOCOL, "resumable");
        if (this.isFinal && this.bytesToWrite > 0) {
            this.requestHeaders.put(ResumableNetworkRequest.COMMAND, "upload, finalize");
        } else if (this.isFinal) {
            this.requestHeaders.put(ResumableNetworkRequest.COMMAND, "finalize");
        } else {
            this.requestHeaders.put(ResumableNetworkRequest.COMMAND, "upload");
        }
        this.requestHeaders.put(ResumableNetworkRequest.OFFSET, Long.toString(this.offset));
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    @NonNull
    public String getAction() {
        return NetworkRequest.POST;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    @Nullable
    public byte[] getOutputRaw() {
        return this.chunk;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    public int getOutputRawSize() {
        int i = this.bytesToWrite;
        if (i > 0) {
            return i;
        }
        return 0;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    @NonNull
    public Uri getURL() {
        return this.uploadURL;
    }
}
