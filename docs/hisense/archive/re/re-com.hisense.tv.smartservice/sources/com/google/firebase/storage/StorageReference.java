package com.google.firebase.storage;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.Slashes;
import com.google.firebase.storage.internal.StorageReferenceUri;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class StorageReference implements Comparable<StorageReference> {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String TAG = "StorageReference";
    public final FirebaseStorage mFirebaseStorage;
    public final Uri mStorageUri;

    public StorageReference(@NonNull Uri uri, @NonNull FirebaseStorage firebaseStorage) {
        Preconditions.checkArgument(uri != null, "storageUri cannot be null");
        Preconditions.checkArgument(firebaseStorage != null, "FirebaseApp cannot be null");
        this.mStorageUri = uri;
        this.mFirebaseStorage = firebaseStorage;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<ListResult> listHelper(@Nullable Integer num, @Nullable String str) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        StorageTaskScheduler.sInstance.scheduleCommand(new ListTask(this, num, str, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    @NonNull
    public StorageReference child(@NonNull String str) {
        Preconditions.checkArgument(!TextUtils.isEmpty(str), "childName cannot be null or empty");
        return new StorageReference(this.mStorageUri.buildUpon().appendEncodedPath(Slashes.preserveSlashEncode(Slashes.normalizeSlashes(str))).build(), this.mFirebaseStorage);
    }

    @NonNull
    public Task<Void> delete() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        StorageTaskScheduler.sInstance.scheduleCommand(new DeleteStorageTask(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public boolean equals(Object obj) {
        if (obj instanceof StorageReference) {
            return ((StorageReference) obj).toString().equals(toString());
        }
        return false;
    }

    @NonNull
    public List<FileDownloadTask> getActiveDownloadTasks() {
        return StorageTaskManager._instance.getDownloadTasksUnder(this);
    }

    @NonNull
    public List<UploadTask> getActiveUploadTasks() {
        return StorageTaskManager._instance.getUploadTasksUnder(this);
    }

    @NonNull
    public FirebaseApp getApp() {
        return getStorage().getApp();
    }

    @NonNull
    public String getBucket() {
        return this.mStorageUri.getAuthority();
    }

    @NonNull
    public Task<byte[]> getBytes(final long j) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        StreamDownloadTask streamDownloadTask = new StreamDownloadTask(this);
        streamDownloadTask.setStreamProcessor(new StreamDownloadTask.StreamProcessor() { // from class: com.google.firebase.storage.StorageReference.3
            @Override // com.google.firebase.storage.StreamDownloadTask.StreamProcessor
            public void doInBackground(StreamDownloadTask.TaskSnapshot taskSnapshot, InputStream inputStream) throws IOException {
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[16384];
                    int i = 0;
                    while (true) {
                        int i2 = inputStream.read(bArr, 0, bArr.length);
                        if (i2 == -1) {
                            byteArrayOutputStream.flush();
                            taskCompletionSource.setResult(byteArrayOutputStream.toByteArray());
                            inputStream.close();
                            return;
                        } else {
                            i += i2;
                            if (i > j) {
                                Log.e(StorageReference.TAG, "the maximum allowed buffer size was exceeded.");
                                throw new IndexOutOfBoundsException("the maximum allowed buffer size was exceeded.");
                            }
                            byteArrayOutputStream.write(bArr, 0, i2);
                        }
                    }
                } catch (Throwable th) {
                    inputStream.close();
                    throw th;
                }
            }
        }).addOnSuccessListener((OnSuccessListener) new OnSuccessListener<StreamDownloadTask.TaskSnapshot>() { // from class: com.google.firebase.storage.StorageReference.2
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public void onSuccess(StreamDownloadTask.TaskSnapshot taskSnapshot) {
                if (taskCompletionSource.getTask().isComplete()) {
                    return;
                }
                Log.e(StorageReference.TAG, "getBytes 'succeeded', but failed to set a Result.");
                taskCompletionSource.setException(StorageException.fromErrorStatus(Status.RESULT_INTERNAL_ERROR));
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.google.firebase.storage.StorageReference.1
            public static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // com.google.android.gms.tasks.OnFailureListener
            public void onFailure(@NonNull Exception exc) {
                taskCompletionSource.setException(StorageException.fromExceptionAndHttpCode(exc, 0));
            }
        });
        streamDownloadTask.queue();
        return taskCompletionSource.getTask();
    }

    @NonNull
    public Task<Uri> getDownloadUrl() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        StorageTaskScheduler.sInstance.scheduleCommand(new GetDownloadUrlTask(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    @NonNull
    public FileDownloadTask getFile(@NonNull Uri uri) {
        FileDownloadTask fileDownloadTask = new FileDownloadTask(this, uri);
        fileDownloadTask.queue();
        return fileDownloadTask;
    }

    @NonNull
    public Task<StorageMetadata> getMetadata() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        StorageTaskScheduler.sInstance.scheduleCommand(new GetMetadataTask(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    @NonNull
    public String getName() {
        String path = this.mStorageUri.getPath();
        int iLastIndexOf = path.lastIndexOf(47);
        return iLastIndexOf != -1 ? path.substring(iLastIndexOf + 1) : path;
    }

    @Nullable
    public StorageReference getParent() {
        String path = this.mStorageUri.getPath();
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        if (path.equals("/")) {
            return null;
        }
        int iLastIndexOf = path.lastIndexOf(47);
        return new StorageReference(this.mStorageUri.buildUpon().path(iLastIndexOf != -1 ? path.substring(0, iLastIndexOf) : "/").build(), this.mFirebaseStorage);
    }

    @NonNull
    public String getPath() {
        return this.mStorageUri.getPath();
    }

    @NonNull
    public StorageReference getRoot() {
        return new StorageReference(this.mStorageUri.buildUpon().path("").build(), this.mFirebaseStorage);
    }

    @NonNull
    public FirebaseStorage getStorage() {
        return this.mFirebaseStorage;
    }

    @NonNull
    public StorageReferenceUri getStorageReferenceUri() {
        return new StorageReferenceUri(this.mStorageUri, this.mFirebaseStorage.getEmulatorSettings());
    }

    @NonNull
    public Uri getStorageUri() {
        return this.mStorageUri;
    }

    @NonNull
    public StreamDownloadTask getStream() {
        StreamDownloadTask streamDownloadTask = new StreamDownloadTask(this);
        streamDownloadTask.queue();
        return streamDownloadTask;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    @NonNull
    public Task<ListResult> list(int i) {
        Preconditions.checkArgument(i > 0, "maxResults must be greater than zero");
        Preconditions.checkArgument(i <= 1000, "maxResults must be at most 1000");
        return listHelper(Integer.valueOf(i), null);
    }

    @NonNull
    public Task<ListResult> listAll() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        final Executor commandPoolExecutor = StorageTaskScheduler.sInstance.getCommandPoolExecutor();
        listHelper(null, null).continueWithTask(commandPoolExecutor, new Continuation<ListResult, Task<Void>>() { // from class: com.google.firebase.storage.StorageReference.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.android.gms.tasks.Continuation
            public Task<Void> then(@NonNull Task<ListResult> task) {
                if (task.isSuccessful()) {
                    ListResult result = task.getResult();
                    arrayList.addAll(result.getPrefixes());
                    arrayList2.addAll(result.getItems());
                    if (result.getPageToken() != null) {
                        StorageReference.this.listHelper(null, result.getPageToken()).continueWithTask(commandPoolExecutor, this);
                    } else {
                        taskCompletionSource.setResult(new ListResult(arrayList, arrayList2, null));
                    }
                } else {
                    taskCompletionSource.setException(task.getException());
                }
                return Tasks.forResult(null);
            }
        });
        return taskCompletionSource.getTask();
    }

    @NonNull
    public UploadTask putBytes(@NonNull byte[] bArr) {
        Preconditions.checkArgument(bArr != null, "bytes cannot be null");
        UploadTask uploadTask = new UploadTask(this, (StorageMetadata) null, bArr);
        uploadTask.queue();
        return uploadTask;
    }

    @NonNull
    public UploadTask putFile(@NonNull Uri uri) {
        Preconditions.checkArgument(uri != null, "uri cannot be null");
        UploadTask uploadTask = new UploadTask(this, null, uri, null);
        uploadTask.queue();
        return uploadTask;
    }

    @NonNull
    public UploadTask putStream(@NonNull InputStream inputStream) {
        Preconditions.checkArgument(inputStream != null, "stream cannot be null");
        UploadTask uploadTask = new UploadTask(this, (StorageMetadata) null, inputStream);
        uploadTask.queue();
        return uploadTask;
    }

    public String toString() {
        StringBuilder sbA = a.a("gs://");
        sbA.append(this.mStorageUri.getAuthority());
        sbA.append(this.mStorageUri.getEncodedPath());
        return sbA.toString();
    }

    @NonNull
    public Task<StorageMetadata> updateMetadata(@NonNull StorageMetadata storageMetadata) {
        Preconditions.checkNotNull(storageMetadata);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        StorageTaskScheduler.sInstance.scheduleCommand(new UpdateMetadataTask(this, taskCompletionSource, storageMetadata));
        return taskCompletionSource.getTask();
    }

    @Override // java.lang.Comparable
    public int compareTo(@NonNull StorageReference storageReference) {
        return this.mStorageUri.compareTo(storageReference.mStorageUri);
    }

    @NonNull
    public FileDownloadTask getFile(@NonNull File file) {
        return getFile(Uri.fromFile(file));
    }

    @NonNull
    public StreamDownloadTask getStream(@NonNull StreamDownloadTask.StreamProcessor streamProcessor) {
        StreamDownloadTask streamDownloadTask = new StreamDownloadTask(this);
        streamDownloadTask.setStreamProcessor(streamProcessor);
        streamDownloadTask.queue();
        return streamDownloadTask;
    }

    @NonNull
    public Task<ListResult> list(int i, @NonNull String str) {
        Preconditions.checkArgument(i > 0, "maxResults must be greater than zero");
        Preconditions.checkArgument(i <= 1000, "maxResults must be at most 1000");
        Preconditions.checkArgument(str != null, "pageToken must be non-null to resume a previous list() operation");
        return listHelper(Integer.valueOf(i), str);
    }

    @NonNull
    public UploadTask putBytes(@NonNull byte[] bArr, @NonNull StorageMetadata storageMetadata) {
        Preconditions.checkArgument(bArr != null, "bytes cannot be null");
        Preconditions.checkArgument(storageMetadata != null, "metadata cannot be null");
        UploadTask uploadTask = new UploadTask(this, storageMetadata, bArr);
        uploadTask.queue();
        return uploadTask;
    }

    @NonNull
    public UploadTask putFile(@NonNull Uri uri, @NonNull StorageMetadata storageMetadata) {
        Preconditions.checkArgument(uri != null, "uri cannot be null");
        Preconditions.checkArgument(storageMetadata != null, "metadata cannot be null");
        UploadTask uploadTask = new UploadTask(this, storageMetadata, uri, null);
        uploadTask.queue();
        return uploadTask;
    }

    @NonNull
    public UploadTask putStream(@NonNull InputStream inputStream, @NonNull StorageMetadata storageMetadata) {
        Preconditions.checkArgument(inputStream != null, "stream cannot be null");
        Preconditions.checkArgument(storageMetadata != null, "metadata cannot be null");
        UploadTask uploadTask = new UploadTask(this, storageMetadata, inputStream);
        uploadTask.queue();
        return uploadTask;
    }

    @NonNull
    public UploadTask putFile(@NonNull Uri uri, @Nullable StorageMetadata storageMetadata, @Nullable Uri uri2) {
        Preconditions.checkArgument(uri != null, "uri cannot be null");
        Preconditions.checkArgument(storageMetadata != null, "metadata cannot be null");
        UploadTask uploadTask = new UploadTask(this, storageMetadata, uri, uri2);
        uploadTask.queue();
        return uploadTask;
    }
}
