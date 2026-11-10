package com.google.firebase.storage;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.ListNetworkRequest;
import e.b.b;

/* JADX INFO: loaded from: classes.dex */
public class ListTask implements Runnable {
    public static final String TAG = "ListTask";

    @Nullable
    public final Integer maxResults;

    @Nullable
    public final String pageToken;
    public final TaskCompletionSource<ListResult> pendingResult;
    public final ExponentialBackoffSender sender;
    public final StorageReference storageRef;

    public ListTask(@NonNull StorageReference storageReference, @Nullable Integer num, @Nullable String str, @NonNull TaskCompletionSource<ListResult> taskCompletionSource) {
        Preconditions.checkNotNull(storageReference);
        Preconditions.checkNotNull(taskCompletionSource);
        this.storageRef = storageReference;
        this.maxResults = num;
        this.pageToken = str;
        this.pendingResult = taskCompletionSource;
        FirebaseStorage storage = this.storageRef.getStorage();
        this.sender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getAppCheckProvider(), storage.getMaxDownloadRetryTimeMillis());
    }

    @Override // java.lang.Runnable
    public void run() {
        ListResult listResultFromJSON;
        ListNetworkRequest listNetworkRequest = new ListNetworkRequest(this.storageRef.getStorageReferenceUri(), this.storageRef.getApp(), this.maxResults, this.pageToken);
        this.sender.sendWithExponentialBackoff(listNetworkRequest);
        if (listNetworkRequest.isResultSuccess()) {
            try {
                listResultFromJSON = ListResult.fromJSON(this.storageRef.getStorage(), listNetworkRequest.getResultBody());
            } catch (b e2) {
                StringBuilder sbA = a.a("Unable to parse response body. ");
                sbA.append(listNetworkRequest.getRawResult());
                Log.e(TAG, sbA.toString(), e2);
                this.pendingResult.setException(StorageException.fromException(e2));
                return;
            }
        } else {
            listResultFromJSON = null;
        }
        TaskCompletionSource<ListResult> taskCompletionSource = this.pendingResult;
        if (taskCompletionSource != null) {
            listNetworkRequest.completeTask(taskCompletionSource, listResultFromJSON);
        }
    }
}
