package com.google.firebase.storage;

import android.util.Log;
import androidx.annotation.NonNull;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.GetMetadataNetworkRequest;
import e.b.b;

/* JADX INFO: loaded from: classes.dex */
public class GetMetadataTask implements Runnable {
    public static final String TAG = "GetMetadataTask";
    public TaskCompletionSource<StorageMetadata> mPendingResult;
    public StorageMetadata mResultMetadata;
    public ExponentialBackoffSender mSender;
    public StorageReference mStorageRef;

    public GetMetadataTask(@NonNull StorageReference storageReference, @NonNull TaskCompletionSource<StorageMetadata> taskCompletionSource) {
        Preconditions.checkNotNull(storageReference);
        Preconditions.checkNotNull(taskCompletionSource);
        this.mStorageRef = storageReference;
        this.mPendingResult = taskCompletionSource;
        if (storageReference.getRoot().getName().equals(storageReference.getName())) {
            throw new IllegalArgumentException("getMetadata() is not supported at the root of the bucket.");
        }
        FirebaseStorage storage = this.mStorageRef.getStorage();
        this.mSender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getAppCheckProvider(), storage.getMaxDownloadRetryTimeMillis());
    }

    @Override // java.lang.Runnable
    public void run() {
        GetMetadataNetworkRequest getMetadataNetworkRequest = new GetMetadataNetworkRequest(this.mStorageRef.getStorageReferenceUri(), this.mStorageRef.getApp());
        this.mSender.sendWithExponentialBackoff(getMetadataNetworkRequest);
        if (getMetadataNetworkRequest.isResultSuccess()) {
            try {
                this.mResultMetadata = new StorageMetadata.Builder(getMetadataNetworkRequest.getResultBody(), this.mStorageRef).build();
            } catch (b e2) {
                StringBuilder sbA = a.a("Unable to parse resulting metadata. ");
                sbA.append(getMetadataNetworkRequest.getRawResult());
                Log.e("GetMetadataTask", sbA.toString(), e2);
                this.mPendingResult.setException(StorageException.fromException(e2));
                return;
            }
        }
        TaskCompletionSource<StorageMetadata> taskCompletionSource = this.mPendingResult;
        if (taskCompletionSource != null) {
            getMetadataNetworkRequest.completeTask(taskCompletionSource, this.mResultMetadata);
        }
    }
}
