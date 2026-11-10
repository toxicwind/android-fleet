package com.google.firebase.storage;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import b.a.a.a.a;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.storage.internal.AdaptiveStreamBuffer;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.internal.Util;
import com.google.firebase.storage.network.NetworkRequest;
import com.google.firebase.storage.network.ResumableUploadByteRequest;
import com.google.firebase.storage.network.ResumableUploadCancelRequest;
import com.google.firebase.storage.network.ResumableUploadQueryRequest;
import com.google.firebase.storage.network.ResumableUploadStartRequest;
import e.b.b;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public class UploadTask extends StorageTask<TaskSnapshot> {
    public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
    public static final int MAXIMUM_CHUNK_SIZE = 33554432;

    @VisibleForTesting
    public static final int PREFERRED_CHUNK_SIZE = 262144;
    public static final String RESUMABLE_FINAL_STATUS = "final";
    public static final String TAG = "UploadTask";
    public static final String X_GOOG_UPLOAD_URL = "X-Goog-Upload-URL";

    @Nullable
    public final InternalAppCheckTokenProvider mAppCheckProvider;

    @Nullable
    public final InternalAuthProvider mAuthProvider;
    public final AtomicLong mBytesUploaded;
    public int mCurrentChunkSize;
    public volatile Exception mException;
    public boolean mIsStreamOwned;
    public volatile StorageMetadata mMetadata;
    public volatile int mResultCode;
    public ExponentialBackoffSender mSender;
    public volatile Exception mServerException;
    public volatile String mServerStatus;
    public final StorageReference mStorageRef;
    public final AdaptiveStreamBuffer mStreamBuffer;
    public final long mTotalByteCount;
    public volatile Uri mUploadUri;
    public final Uri mUri;

    public class TaskSnapshot extends StorageTask<TaskSnapshot>.SnapshotBase {
        public final long mBytesUploaded;
        public final StorageMetadata mMetadata;
        public final Uri mUploadUri;

        public TaskSnapshot(Exception exc, long j, Uri uri, StorageMetadata storageMetadata) {
            super(exc);
            this.mBytesUploaded = j;
            this.mUploadUri = uri;
            this.mMetadata = storageMetadata;
        }

        public long getBytesTransferred() {
            return this.mBytesUploaded;
        }

        @Nullable
        public StorageMetadata getMetadata() {
            return this.mMetadata;
        }

        public long getTotalByteCount() {
            return UploadTask.this.getTotalByteCount();
        }

        @Nullable
        public Uri getUploadSessionUri() {
            return this.mUploadUri;
        }
    }

    public UploadTask(StorageReference storageReference, StorageMetadata storageMetadata, byte[] bArr) {
        this.mBytesUploaded = new AtomicLong(0L);
        this.mCurrentChunkSize = 262144;
        this.mUploadUri = null;
        this.mException = null;
        this.mServerException = null;
        this.mResultCode = 0;
        Preconditions.checkNotNull(storageReference);
        Preconditions.checkNotNull(bArr);
        FirebaseStorage storage = storageReference.getStorage();
        this.mTotalByteCount = bArr.length;
        this.mStorageRef = storageReference;
        this.mMetadata = storageMetadata;
        this.mAuthProvider = storage.getAuthProvider();
        this.mAppCheckProvider = storage.getAppCheckProvider();
        this.mUri = null;
        this.mStreamBuffer = new AdaptiveStreamBuffer(new ByteArrayInputStream(bArr), 262144);
        this.mIsStreamOwned = true;
        this.mSender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), this.mAuthProvider, this.mAppCheckProvider, storage.getMaxDownloadRetryTimeMillis());
    }

    private void beginResumableUpload() {
        String contentType = this.mMetadata != null ? this.mMetadata.getContentType() : null;
        if (this.mUri != null && TextUtils.isEmpty(contentType)) {
            contentType = this.mStorageRef.getStorage().getApp().getApplicationContext().getContentResolver().getType(this.mUri);
        }
        if (TextUtils.isEmpty(contentType)) {
            contentType = "application/octet-stream";
        }
        ResumableUploadStartRequest resumableUploadStartRequest = new ResumableUploadStartRequest(this.mStorageRef.getStorageReferenceUri(), this.mStorageRef.getApp(), this.mMetadata != null ? this.mMetadata.createJSONObject() : null, contentType);
        this.mSender.sendWithExponentialBackoff(resumableUploadStartRequest);
        if (processResultValid(resumableUploadStartRequest)) {
            String resultString = resumableUploadStartRequest.getResultString(X_GOOG_UPLOAD_URL);
            if (TextUtils.isEmpty(resultString)) {
                return;
            }
            this.mUploadUri = Uri.parse(resultString);
        }
    }

    private boolean isValidHttpResponseCode(int i) {
        return i == 308 || (i >= 200 && i < 300);
    }

    private boolean processResultValid(NetworkRequest networkRequest) {
        int resultCode = networkRequest.getResultCode();
        if (this.mSender.isRetryableError(resultCode)) {
            resultCode = -2;
        }
        this.mResultCode = resultCode;
        this.mServerException = networkRequest.getException();
        this.mServerStatus = networkRequest.getResultString("X-Goog-Upload-Status");
        return isValidHttpResponseCode(this.mResultCode) && this.mServerException == null;
    }

    private boolean recoverStatus(boolean z) {
        ResumableUploadQueryRequest resumableUploadQueryRequest = new ResumableUploadQueryRequest(this.mStorageRef.getStorageReferenceUri(), this.mStorageRef.getApp(), this.mUploadUri);
        if (RESUMABLE_FINAL_STATUS.equals(this.mServerStatus)) {
            return false;
        }
        if (z) {
            this.mSender.sendWithExponentialBackoff(resumableUploadQueryRequest);
            if (!processResultValid(resumableUploadQueryRequest)) {
                return false;
            }
        } else if (!send(resumableUploadQueryRequest)) {
            return false;
        }
        if (RESUMABLE_FINAL_STATUS.equals(resumableUploadQueryRequest.getResultString("X-Goog-Upload-Status"))) {
            this.mException = new IOException("The server has terminated the upload session");
            return false;
        }
        String resultString = resumableUploadQueryRequest.getResultString("X-Goog-Upload-Size-Received");
        long j = !TextUtils.isEmpty(resultString) ? Long.parseLong(resultString) : 0L;
        long j2 = this.mBytesUploaded.get();
        if (j2 > j) {
            this.mException = new IOException("Unexpected error. The server lost a chunk update.");
            return false;
        }
        if (j2 >= j) {
            return true;
        }
        try {
            long j3 = j - j2;
            if (this.mStreamBuffer.advance((int) j3) != j3) {
                this.mException = new IOException("Unexpected end of stream encountered.");
                return false;
            }
            if (this.mBytesUploaded.compareAndSet(j2, j)) {
                return true;
            }
            Log.e(TAG, "Somehow, the uploaded bytes changed during an uploaded.  This should nothappen");
            this.mException = new IllegalStateException("uploaded bytes changed unexpectedly.");
            return false;
        } catch (IOException e2) {
            Log.e(TAG, "Unable to recover position in Stream during resumable upload", e2);
            this.mException = e2;
            return false;
        }
    }

    private boolean send(NetworkRequest networkRequest) {
        networkRequest.performRequest(Util.getCurrentAuthToken(this.mAuthProvider), Util.getCurrentAppCheckToken(this.mAppCheckProvider), this.mStorageRef.getApp().getApplicationContext());
        return processResultValid(networkRequest);
    }

    private boolean sendWithRetry(NetworkRequest networkRequest) {
        this.mSender.sendWithExponentialBackoff(networkRequest);
        return processResultValid(networkRequest);
    }

    private boolean serverStateValid() {
        if (!RESUMABLE_FINAL_STATUS.equals(this.mServerStatus)) {
            return true;
        }
        if (this.mException == null) {
            this.mException = new IOException("The server has terminated the upload session", this.mServerException);
        }
        tryChangeState(64, false);
        return false;
    }

    private boolean shouldContinue() {
        if (getInternalState() == 128) {
            return false;
        }
        if (Thread.interrupted()) {
            this.mException = new InterruptedException();
            tryChangeState(64, false);
            return false;
        }
        if (getInternalState() == 32) {
            tryChangeState(256, false);
            return false;
        }
        if (getInternalState() == 8) {
            tryChangeState(16, false);
            return false;
        }
        if (!serverStateValid()) {
            return false;
        }
        if (this.mUploadUri == null) {
            if (this.mException == null) {
                this.mException = new IllegalStateException("Unable to obtain an upload URL.");
            }
            tryChangeState(64, false);
            return false;
        }
        if (this.mException != null) {
            tryChangeState(64, false);
            return false;
        }
        if (!(this.mServerException != null || this.mResultCode < 200 || this.mResultCode >= 300) || recoverStatus(true)) {
            return true;
        }
        if (serverStateValid()) {
            tryChangeState(64, false);
        }
        return false;
    }

    private void uploadChunk() {
        try {
            this.mStreamBuffer.fill(this.mCurrentChunkSize);
            int iMin = Math.min(this.mCurrentChunkSize, this.mStreamBuffer.available());
            ResumableUploadByteRequest resumableUploadByteRequest = new ResumableUploadByteRequest(this.mStorageRef.getStorageReferenceUri(), this.mStorageRef.getApp(), this.mUploadUri, this.mStreamBuffer.get(), this.mBytesUploaded.get(), iMin, this.mStreamBuffer.isFinished());
            if (!send(resumableUploadByteRequest)) {
                this.mCurrentChunkSize = 262144;
                Log.d(TAG, "Resetting chunk size to " + this.mCurrentChunkSize);
                return;
            }
            this.mBytesUploaded.getAndAdd(iMin);
            if (!this.mStreamBuffer.isFinished()) {
                this.mStreamBuffer.advance(iMin);
                if (this.mCurrentChunkSize < 33554432) {
                    this.mCurrentChunkSize *= 2;
                    Log.d(TAG, "Increasing chunk size to " + this.mCurrentChunkSize);
                    return;
                }
                return;
            }
            try {
                this.mMetadata = new StorageMetadata.Builder(resumableUploadByteRequest.getResultBody(), this.mStorageRef).build();
                tryChangeState(4, false);
                tryChangeState(128, false);
            } catch (b e2) {
                Log.e(TAG, "Unable to parse resulting metadata from upload:" + resumableUploadByteRequest.getRawResult(), e2);
                this.mException = e2;
            }
        } catch (IOException e3) {
            Log.e(TAG, "Unable to read bytes for uploading", e3);
            this.mException = e3;
        }
    }

    @Override // com.google.firebase.storage.StorageTask
    public StorageReference getStorage() {
        return this.mStorageRef;
    }

    public long getTotalByteCount() {
        return this.mTotalByteCount;
    }

    @Override // com.google.firebase.storage.StorageTask
    public void onCanceled() {
        this.mSender.cancel();
        final ResumableUploadCancelRequest resumableUploadCancelRequest = this.mUploadUri != null ? new ResumableUploadCancelRequest(this.mStorageRef.getStorageReferenceUri(), this.mStorageRef.getApp(), this.mUploadUri) : null;
        if (resumableUploadCancelRequest != null) {
            StorageTaskScheduler.sInstance.scheduleCommand(new Runnable() { // from class: com.google.firebase.storage.UploadTask.1
                @Override // java.lang.Runnable
                public void run() {
                    resumableUploadCancelRequest.performRequest(Util.getCurrentAuthToken(UploadTask.this.mAuthProvider), Util.getCurrentAppCheckToken(UploadTask.this.mAppCheckProvider), UploadTask.this.mStorageRef.getApp().getApplicationContext());
                }
            });
        }
        this.mException = StorageException.fromErrorStatus(Status.RESULT_CANCELED);
    }

    @Override // com.google.firebase.storage.StorageTask
    public void resetState() {
        this.mException = null;
        this.mServerException = null;
        this.mResultCode = 0;
        this.mServerStatus = null;
    }

    @Override // com.google.firebase.storage.StorageTask
    public void run() {
        this.mSender.reset();
        if (!tryChangeState(4, false)) {
            Log.d(TAG, "The upload cannot continue as it is not in a valid state.");
            return;
        }
        if (this.mStorageRef.getParent() == null) {
            this.mException = new IllegalArgumentException("Cannot upload to getRoot. You should upload to a storage location such as .getReference('image.png').putFile...");
        }
        if (this.mException != null) {
            return;
        }
        if (this.mUploadUri == null) {
            beginResumableUpload();
        } else {
            recoverStatus(false);
        }
        boolean zShouldContinue = shouldContinue();
        while (zShouldContinue) {
            uploadChunk();
            zShouldContinue = shouldContinue();
            if (zShouldContinue) {
                tryChangeState(4, false);
            }
        }
        if (!this.mIsStreamOwned || getInternalState() == 16) {
            return;
        }
        try {
            this.mStreamBuffer.close();
        } catch (IOException e2) {
            Log.e(TAG, "Unable to close stream.", e2);
        }
    }

    @Override // com.google.firebase.storage.StorageTask
    public void schedule() {
        StorageTaskScheduler.sInstance.scheduleUpload(getRunnable());
    }

    @Override // com.google.firebase.storage.StorageTask
    @NonNull
    public TaskSnapshot snapStateImpl() {
        return new TaskSnapshot(StorageException.fromExceptionAndHttpCode(this.mException != null ? this.mException : this.mServerException, this.mResultCode), this.mBytesUploaded.get(), this.mUploadUri, this.mMetadata);
    }

    /* JADX WARN: Code duplicated, block: B:27:0x00b9  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14, types: [long] */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v9, types: [long] */
    public UploadTask(StorageReference storageReference, StorageMetadata storageMetadata, Uri uri, Uri uri2) {
        ?? r8;
        InputStream bufferedInputStream;
        ?? r5;
        long statSize;
        this.mBytesUploaded = new AtomicLong(0L);
        this.mCurrentChunkSize = 262144;
        InputStream inputStreamOpenInputStream = null;
        this.mUploadUri = null;
        this.mException = null;
        this.mServerException = null;
        this.mResultCode = 0;
        Preconditions.checkNotNull(storageReference);
        Preconditions.checkNotNull(uri);
        FirebaseStorage storage = storageReference.getStorage();
        this.mStorageRef = storageReference;
        this.mMetadata = storageMetadata;
        this.mAuthProvider = storage.getAuthProvider();
        this.mAppCheckProvider = storage.getAppCheckProvider();
        this.mUri = uri;
        Context applicationContext = this.mStorageRef.getApp().getApplicationContext();
        InternalAuthProvider internalAuthProvider = this.mAuthProvider;
        ?? r9 = this.mAppCheckProvider;
        this.mSender = new ExponentialBackoffSender(applicationContext, internalAuthProvider, r9, storage.getMaxUploadRetryTimeMillis());
        ?? r6 = -1;
        try {
            try {
                ContentResolver contentResolver = this.mStorageRef.getStorage().getApp().getApplicationContext().getContentResolver();
                try {
                    ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = contentResolver.openFileDescriptor(this.mUri, "r");
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        statSize = parcelFileDescriptorOpenFileDescriptor.getStatSize();
                        try {
                            parcelFileDescriptorOpenFileDescriptor.close();
                            r9 = statSize;
                        } catch (IOException e2) {
                            e = e2;
                            Log.w(TAG, "could not retrieve file size for upload " + this.mUri.toString(), e);
                            r9 = statSize;
                        } catch (NullPointerException e3) {
                            e = e3;
                            Log.w(TAG, "NullPointerException during file size calculation.", e);
                            r9 = -1;
                        }
                    } else {
                        r9 = -1;
                    }
                } catch (IOException e4) {
                    e = e4;
                    statSize = -1;
                } catch (NullPointerException e5) {
                    e = e5;
                }
                inputStreamOpenInputStream = contentResolver.openInputStream(this.mUri);
                r8 = r9;
                if (inputStreamOpenInputStream != null) {
                    if (r9 == -1) {
                        try {
                            int iAvailable = inputStreamOpenInputStream.available();
                            if (iAvailable >= 0) {
                                r6 = iAvailable;
                            } else {
                                r6 = r9;
                            }
                        } catch (IOException unused) {
                        }
                    } else {
                        r6 = r9;
                    }
                    bufferedInputStream = new BufferedInputStream(inputStreamOpenInputStream);
                    r5 = r6;
                } else {
                    bufferedInputStream = inputStreamOpenInputStream;
                    r5 = r8;
                }
            } catch (FileNotFoundException e6) {
                e = e6;
                StringBuilder sbA = a.a("could not locate file for uploading:");
                sbA.append(this.mUri.toString());
                Log.e(TAG, sbA.toString());
                this.mException = e;
                r8 = r9;
            }
        } catch (FileNotFoundException e7) {
            e = e7;
            r9 = r6;
            StringBuilder sbA2 = a.a("could not locate file for uploading:");
            sbA2.append(this.mUri.toString());
            Log.e(TAG, sbA2.toString());
            this.mException = e;
            r8 = r9;
        }
        this.mTotalByteCount = r5;
        this.mStreamBuffer = new AdaptiveStreamBuffer(bufferedInputStream, 262144);
        this.mIsStreamOwned = true;
        this.mUploadUri = uri2;
    }

    public UploadTask(StorageReference storageReference, StorageMetadata storageMetadata, InputStream inputStream) {
        this.mBytesUploaded = new AtomicLong(0L);
        this.mCurrentChunkSize = 262144;
        this.mUploadUri = null;
        this.mException = null;
        this.mServerException = null;
        this.mResultCode = 0;
        Preconditions.checkNotNull(storageReference);
        Preconditions.checkNotNull(inputStream);
        FirebaseStorage storage = storageReference.getStorage();
        this.mTotalByteCount = -1L;
        this.mStorageRef = storageReference;
        this.mMetadata = storageMetadata;
        this.mAuthProvider = storage.getAuthProvider();
        this.mAppCheckProvider = storage.getAppCheckProvider();
        this.mStreamBuffer = new AdaptiveStreamBuffer(inputStream, 262144);
        this.mIsStreamOwned = false;
        this.mUri = null;
        this.mSender = new ExponentialBackoffSender(this.mStorageRef.getApp().getApplicationContext(), this.mAuthProvider, this.mAppCheckProvider, this.mStorageRef.getStorage().getMaxUploadRetryTimeMillis());
    }
}
