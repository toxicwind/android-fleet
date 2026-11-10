package com.google.firebase.storage;

import android.util.Log;
import androidx.annotation.NonNull;
import b.a.a.a.a;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.UpdateMetadataNetworkRequest;
import e.b.b;

/* JADX INFO: loaded from: classes.dex */
public class UpdateMetadataTask implements Runnable {
    public static final String TAG = "UpdateMetadataTask";
    public final StorageMetadata mNewMetadata;
    public final TaskCompletionSource<StorageMetadata> mPendingResult;
    public StorageMetadata mResultMetadata = null;
    public ExponentialBackoffSender mSender;
    public final StorageReference mStorageRef;

    public UpdateMetadataTask(@NonNull StorageReference storageReference, @NonNull TaskCompletionSource<StorageMetadata> taskCompletionSource, @NonNull StorageMetadata storageMetadata) {
        this.mStorageRef = storageReference;
        this.mPendingResult = taskCompletionSource;
        this.mNewMetadata = storageMetadata;
        FirebaseStorage storage = this.mStorageRef.getStorage();
        this.mSender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getAppCheckProvider(), storage.getMaxOperationRetryTimeMillis());
    }

    @Override // java.lang.Runnable
    public void run() {
        UpdateMetadataNetworkRequest updateMetadataNetworkRequest = new UpdateMetadataNetworkRequest(this.mStorageRef.getStorageReferenceUri(), this.mStorageRef.getApp(), this.mNewMetadata.createJSONObject());
        this.mSender.sendWithExponentialBackoff(updateMetadataNetworkRequest);
        if (updateMetadataNetworkRequest.isResultSuccess()) {
            try {
                this.mResultMetadata = new StorageMetadata.Builder(updateMetadataNetworkRequest.getResultBody(), this.mStorageRef).build();
            } catch (b e2) {
                StringBuilder sbA = a.a("Unable to parse a valid JSON object from resulting metadata:");
                sbA.append(updateMetadataNetworkRequest.getRawResult());
                Log.e(TAG, sbA.toString(), e2);
                this.mPendingResult.setException(StorageException.fromException(e2));
                return;
            }
        }
        TaskCompletionSource<StorageMetadata> taskCompletionSource = this.mPendingResult;
        if (taskCompletionSource != null) {
            updateMetadataNetworkRequest.completeTask(taskCompletionSource, this.mResultMetadata);
        }
    }
}
