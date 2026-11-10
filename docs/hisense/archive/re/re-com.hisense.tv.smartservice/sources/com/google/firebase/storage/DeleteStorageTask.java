package com.google.firebase.storage;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.DeleteNetworkRequest;

/* JADX INFO: loaded from: classes.dex */
public class DeleteStorageTask implements Runnable {
    public static final String TAG = "DeleteStorageTask";
    public TaskCompletionSource<Void> mPendingResult;
    public ExponentialBackoffSender mSender;
    public StorageReference mStorageRef;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public DeleteStorageTask(@NonNull StorageReference storageReference, @NonNull TaskCompletionSource<Void> taskCompletionSource) {
        Preconditions.checkNotNull(storageReference);
        Preconditions.checkNotNull(taskCompletionSource);
        this.mStorageRef = storageReference;
        this.mPendingResult = taskCompletionSource;
        FirebaseStorage storage = this.mStorageRef.getStorage();
        this.mSender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getAppCheckProvider(), storage.getMaxDownloadRetryTimeMillis());
    }

    @Override // java.lang.Runnable
    public void run() {
        DeleteNetworkRequest deleteNetworkRequest = new DeleteNetworkRequest(this.mStorageRef.getStorageReferenceUri(), this.mStorageRef.getApp());
        this.mSender.sendWithExponentialBackoff(deleteNetworkRequest);
        deleteNetworkRequest.completeTask(this.mPendingResult, null);
    }
}
