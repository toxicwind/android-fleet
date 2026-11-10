package com.google.firebase.storage;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.installations.local.IidStore;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.GetMetadataNetworkRequest;
import e.b.d;

/* JADX INFO: loaded from: classes.dex */
public class GetDownloadUrlTask implements Runnable {

    @NonNull
    public static final String DOWNLOAD_TOKENS_KEY = "downloadTokens";
    public static final String TAG = "GetMetadataTask";
    public TaskCompletionSource<Uri> pendingResult;
    public ExponentialBackoffSender sender;
    public StorageReference storageRef;

    public GetDownloadUrlTask(@NonNull StorageReference storageReference, @NonNull TaskCompletionSource<Uri> taskCompletionSource) {
        Preconditions.checkNotNull(storageReference);
        Preconditions.checkNotNull(taskCompletionSource);
        this.storageRef = storageReference;
        this.pendingResult = taskCompletionSource;
        if (storageReference.getRoot().getName().equals(storageReference.getName())) {
            throw new IllegalArgumentException("getDownloadUrl() is not supported at the root of the bucket.");
        }
        FirebaseStorage storage = this.storageRef.getStorage();
        this.sender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getAppCheckProvider(), storage.getMaxOperationRetryTimeMillis());
    }

    private Uri extractDownloadUrl(d dVar) {
        String strA = dVar.a(DOWNLOAD_TOKENS_KEY, "");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        String str = strA.split(",", -1)[0];
        Uri.Builder builderBuildUpon = this.storageRef.getStorageReferenceUri().getHttpUri().buildUpon();
        builderBuildUpon.appendQueryParameter("alt", "media");
        builderBuildUpon.appendQueryParameter(IidStore.JSON_TOKEN_KEY, str);
        return builderBuildUpon.build();
    }

    @Override // java.lang.Runnable
    public void run() {
        GetMetadataNetworkRequest getMetadataNetworkRequest = new GetMetadataNetworkRequest(this.storageRef.getStorageReferenceUri(), this.storageRef.getApp());
        this.sender.sendWithExponentialBackoff(getMetadataNetworkRequest);
        Uri uriExtractDownloadUrl = getMetadataNetworkRequest.isResultSuccess() ? extractDownloadUrl(getMetadataNetworkRequest.getResultBody()) : null;
        TaskCompletionSource<Uri> taskCompletionSource = this.pendingResult;
        if (taskCompletionSource != null) {
            getMetadataNetworkRequest.completeTask(taskCompletionSource, uriExtractDownloadUrl);
        }
    }
}
