package com.google.firebase.storage;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import b.a.a.a.a;
import com.google.android.gms.common.api.Status;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.GetNetworkRequest;
import com.google.firebase.storage.network.NetworkRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class FileDownloadTask extends StorageTask<TaskSnapshot> {
    public static final int PREFERRED_CHUNK_SIZE = 262144;
    public static final String TAG = "FileDownloadTask";
    public long mBytesDownloaded;
    public final Uri mDestinationFile;
    public int mResultCode;
    public ExponentialBackoffSender mSender;
    public StorageReference mStorageRef;
    public long mTotalBytes = -1;
    public String mETagVerification = null;
    public volatile Exception mException = null;
    public long mResumeOffset = 0;

    public class TaskSnapshot extends StorageTask<TaskSnapshot>.SnapshotBase {
        public final long mBytesDownloaded;

        public TaskSnapshot(Exception exc, long j) {
            super(exc);
            this.mBytesDownloaded = j;
        }

        public long getBytesTransferred() {
            return this.mBytesDownloaded;
        }

        public long getTotalByteCount() {
            return FileDownloadTask.this.getTotalBytes();
        }
    }

    public FileDownloadTask(@NonNull StorageReference storageReference, @NonNull Uri uri) {
        this.mStorageRef = storageReference;
        this.mDestinationFile = uri;
        FirebaseStorage storage = this.mStorageRef.getStorage();
        this.mSender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getAppCheckProvider(), storage.getMaxDownloadRetryTimeMillis());
    }

    private int fillBuffer(InputStream inputStream, byte[] bArr) {
        int i;
        int i2 = 0;
        boolean z = false;
        while (i2 != bArr.length && (i = inputStream.read(bArr, i2, bArr.length - i2)) != -1) {
            try {
                z = true;
                i2 += i;
            } catch (IOException e2) {
                this.mException = e2;
            }
        }
        if (z) {
            return i2;
        }
        return -1;
    }

    private boolean isValidHttpResponseCode(int i) {
        return i == 308 || (i >= 200 && i < 300);
    }

    private boolean processResponse(NetworkRequest networkRequest) throws IOException {
        FileOutputStream fileOutputStream;
        InputStream stream = networkRequest.getStream();
        if (stream == null) {
            this.mException = new IllegalStateException("Unable to open Firebase Storage stream.");
            return false;
        }
        File file = new File(this.mDestinationFile.getPath());
        if (!file.exists()) {
            if (this.mResumeOffset > 0) {
                throw new IOException("The file to download to has been deleted.");
            }
            if (!file.createNewFile()) {
                StringBuilder sbA = a.a("unable to create file:");
                sbA.append(file.getAbsolutePath());
                Log.w(TAG, sbA.toString());
            }
        }
        boolean z = true;
        if (this.mResumeOffset > 0) {
            StringBuilder sbA2 = a.a("Resuming download file ");
            sbA2.append(file.getAbsolutePath());
            sbA2.append(" at ");
            sbA2.append(this.mResumeOffset);
            Log.d(TAG, sbA2.toString());
            fileOutputStream = new FileOutputStream(file, true);
        } else {
            fileOutputStream = new FileOutputStream(file);
        }
        try {
            byte[] bArr = new byte[262144];
            while (z) {
                int iFillBuffer = fillBuffer(stream, bArr);
                if (iFillBuffer == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, iFillBuffer);
                this.mBytesDownloaded += (long) iFillBuffer;
                if (this.mException != null) {
                    Log.d(TAG, "Exception occurred during file download. Retrying.", this.mException);
                    this.mException = null;
                    z = false;
                }
                if (!tryChangeState(4, false)) {
                    z = false;
                }
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            stream.close();
            return z;
        } catch (Throwable th) {
            fileOutputStream.flush();
            fileOutputStream.close();
            stream.close();
            throw th;
        }
    }

    public long getDownloadedSizeInBytes() {
        return this.mBytesDownloaded;
    }

    @Override // com.google.firebase.storage.StorageTask
    @NonNull
    public StorageReference getStorage() {
        return this.mStorageRef;
    }

    public long getTotalBytes() {
        return this.mTotalBytes;
    }

    @Override // com.google.firebase.storage.StorageTask
    public void onCanceled() {
        this.mSender.cancel();
        this.mException = StorageException.fromErrorStatus(Status.RESULT_CANCELED);
    }

    @Override // com.google.firebase.storage.StorageTask
    public void run() {
        String str;
        if (this.mException != null) {
            tryChangeState(64, false);
            return;
        }
        if (tryChangeState(4, false)) {
            do {
                this.mBytesDownloaded = 0L;
                this.mException = null;
                this.mSender.reset();
                GetNetworkRequest getNetworkRequest = new GetNetworkRequest(this.mStorageRef.getStorageReferenceUri(), this.mStorageRef.getApp(), this.mResumeOffset);
                this.mSender.sendWithExponentialBackoff(getNetworkRequest, false);
                this.mResultCode = getNetworkRequest.getResultCode();
                this.mException = getNetworkRequest.getException() != null ? getNetworkRequest.getException() : this.mException;
                boolean zProcessResponse = isValidHttpResponseCode(this.mResultCode) && this.mException == null && getInternalState() == 4;
                if (zProcessResponse) {
                    this.mTotalBytes = ((long) getNetworkRequest.getResultingContentLength()) + this.mResumeOffset;
                    String resultString = getNetworkRequest.getResultString("ETag");
                    if (!TextUtils.isEmpty(resultString) && (str = this.mETagVerification) != null && !str.equals(resultString)) {
                        Log.w(TAG, "The file at the server has changed.  Restarting from the beginning.");
                        this.mResumeOffset = 0L;
                        this.mETagVerification = null;
                        getNetworkRequest.performRequestEnd();
                        schedule();
                        return;
                    }
                    this.mETagVerification = resultString;
                    try {
                        zProcessResponse = processResponse(getNetworkRequest);
                    } catch (IOException e2) {
                        Log.e(TAG, "Exception occurred during file write.  Aborting.", e2);
                        this.mException = e2;
                    }
                }
                getNetworkRequest.performRequestEnd();
                if (zProcessResponse && this.mException == null && getInternalState() == 4) {
                    tryChangeState(128, false);
                    return;
                }
                File file = new File(this.mDestinationFile.getPath());
                if (file.exists()) {
                    this.mResumeOffset = file.length();
                } else {
                    this.mResumeOffset = 0L;
                }
                if (getInternalState() == 8) {
                    tryChangeState(16, false);
                    return;
                } else if (getInternalState() == 32) {
                    if (tryChangeState(256, false)) {
                        return;
                    }
                    StringBuilder sbA = a.a("Unable to change download task to final state from ");
                    sbA.append(getInternalState());
                    Log.w(TAG, sbA.toString());
                    return;
                }
            } while (this.mBytesDownloaded > 0);
            tryChangeState(64, false);
        }
    }

    @Override // com.google.firebase.storage.StorageTask
    public void schedule() {
        StorageTaskScheduler.sInstance.scheduleDownload(getRunnable());
    }

    @Override // com.google.firebase.storage.StorageTask
    @NonNull
    public TaskSnapshot snapStateImpl() {
        return new TaskSnapshot(StorageException.fromExceptionAndHttpCode(this.mException, this.mResultCode), this.mBytesDownloaded + this.mResumeOffset);
    }
}
