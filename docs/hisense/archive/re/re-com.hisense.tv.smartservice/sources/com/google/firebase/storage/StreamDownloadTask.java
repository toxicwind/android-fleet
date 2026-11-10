package com.google.firebase.storage;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.GetNetworkRequest;
import com.google.firebase.storage.network.NetworkRequest;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public class StreamDownloadTask extends StorageTask<TaskSnapshot> {
    public static final long PREFERRED_CHUNK_SIZE = 262144;
    public static final String TAG = "StreamDownloadTask";
    public long bytesDownloaded;
    public long bytesDownloadedSnapped;
    public String eTagVerification;
    public InputStream inputStream;
    public StreamProcessor processor;
    public NetworkRequest request;
    public ExponentialBackoffSender sender;
    public StorageReference storageRef;
    public volatile Exception exception = null;
    public volatile int resultCode = 0;
    public long totalBytes = -1;

    public interface StreamProcessor {
        void doInBackground(@NonNull TaskSnapshot taskSnapshot, @NonNull InputStream inputStream);
    }

    public class TaskSnapshot extends StorageTask<TaskSnapshot>.SnapshotBase {
        public final long mBytesDownloaded;

        public TaskSnapshot(Exception exc, long j) {
            super(exc);
            this.mBytesDownloaded = j;
        }

        public long getBytesTransferred() {
            return this.mBytesDownloaded;
        }

        @NonNull
        public InputStream getStream() {
            return StreamDownloadTask.this.inputStream;
        }

        public long getTotalByteCount() {
            return StreamDownloadTask.this.getTotalBytes();
        }
    }

    public StreamDownloadTask(@NonNull StorageReference storageReference) {
        this.storageRef = storageReference;
        FirebaseStorage storage = this.storageRef.getStorage();
        this.sender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getAppCheckProvider(), storage.getMaxDownloadRetryTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InputStream createDownloadStream() throws IOException {
        String str;
        this.sender.reset();
        NetworkRequest networkRequest = this.request;
        if (networkRequest != null) {
            networkRequest.performRequestEnd();
        }
        this.request = new GetNetworkRequest(this.storageRef.getStorageReferenceUri(), this.storageRef.getApp(), this.bytesDownloaded);
        boolean z = false;
        this.sender.sendWithExponentialBackoff(this.request, false);
        this.resultCode = this.request.getResultCode();
        this.exception = this.request.getException() != null ? this.request.getException() : this.exception;
        if (isValidHttpResponseCode(this.resultCode) && this.exception == null && getInternalState() == 4) {
            z = true;
        }
        if (!z) {
            throw new IOException("Could not open resulting stream.");
        }
        String resultString = this.request.getResultString("ETag");
        if (!TextUtils.isEmpty(resultString) && (str = this.eTagVerification) != null && !str.equals(resultString)) {
            this.resultCode = 409;
            throw new IOException("The ETag on the server changed.");
        }
        this.eTagVerification = resultString;
        this.totalBytes = ((long) this.request.getResultingContentLength()) + this.bytesDownloaded;
        return this.request.getStream();
    }

    private boolean isValidHttpResponseCode(int i) {
        return i == 308 || (i >= 200 && i < 300);
    }

    @Override // com.google.firebase.storage.StorageTask
    @NonNull
    public StorageReference getStorage() {
        return this.storageRef;
    }

    public long getTotalBytes() {
        return this.totalBytes;
    }

    @Override // com.google.firebase.storage.StorageTask
    public void onCanceled() {
        this.sender.cancel();
        this.exception = StorageException.fromErrorStatus(Status.RESULT_CANCELED);
    }

    @Override // com.google.firebase.storage.StorageTask
    public void onProgress() {
        this.bytesDownloadedSnapped = this.bytesDownloaded;
    }

    @Override // com.google.firebase.storage.StorageTask, com.google.firebase.storage.ControllableTask
    public boolean pause() {
        throw new UnsupportedOperationException("this operation is not supported on StreamDownloadTask.");
    }

    public void recordDownloadedBytes(long j) {
        this.bytesDownloaded += j;
        if (this.bytesDownloadedSnapped + PREFERRED_CHUNK_SIZE <= this.bytesDownloaded) {
            if (getInternalState() == 4) {
                tryChangeState(4, false);
            } else {
                this.bytesDownloadedSnapped = this.bytesDownloaded;
            }
        }
    }

    @Override // com.google.firebase.storage.StorageTask, com.google.firebase.storage.ControllableTask
    public boolean resume() {
        throw new UnsupportedOperationException("this operation is not supported on StreamDownloadTask.");
    }

    @Override // com.google.firebase.storage.StorageTask
    public void run() {
        if (this.exception != null) {
            tryChangeState(64, false);
            return;
        }
        if (tryChangeState(4, false)) {
            StreamProgressWrapper streamProgressWrapper = new StreamProgressWrapper(new Callable<InputStream>() { // from class: com.google.firebase.storage.StreamDownloadTask.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public InputStream call() {
                    return StreamDownloadTask.this.createDownloadStream();
                }
            }, this);
            this.inputStream = new BufferedInputStream(streamProgressWrapper);
            try {
                streamProgressWrapper.ensureStream();
                if (this.processor != null) {
                    try {
                        this.processor.doInBackground(snapState(), this.inputStream);
                    } catch (Exception e2) {
                        Log.w(TAG, "Exception occurred calling doInBackground.", e2);
                        this.exception = e2;
                    }
                }
            } catch (IOException e3) {
                Log.d(TAG, "Initial opening of Stream failed", e3);
                this.exception = e3;
            }
            if (this.inputStream == null) {
                this.request.performRequestEnd();
                this.request = null;
            }
            if (this.exception == null && getInternalState() == 4) {
                tryChangeState(4, false);
                tryChangeState(128, false);
                return;
            }
            if (tryChangeState(getInternalState() == 32 ? 256 : 64, false)) {
                return;
            }
            StringBuilder sbA = a.a("Unable to change download task to final state from ");
            sbA.append(getInternalState());
            Log.w(TAG, sbA.toString());
        }
    }

    @Override // com.google.firebase.storage.StorageTask
    public void schedule() {
        StorageTaskScheduler.sInstance.scheduleDownload(getRunnable());
    }

    public StreamDownloadTask setStreamProcessor(@NonNull StreamProcessor streamProcessor) {
        Preconditions.checkNotNull(streamProcessor);
        Preconditions.checkState(this.processor == null);
        this.processor = streamProcessor;
        return this;
    }

    @Override // com.google.firebase.storage.StorageTask
    @NonNull
    public TaskSnapshot snapStateImpl() {
        return new TaskSnapshot(StorageException.fromExceptionAndHttpCode(this.exception, this.resultCode), this.bytesDownloadedSnapped);
    }

    static class StreamProgressWrapper extends InputStream {
        public long mDownloadedBytes;
        public Callable<InputStream> mInputStreamCallable;
        public long mLastExceptionPosition;

        @Nullable
        public StreamDownloadTask mParentTask;
        public boolean mStreamClosed;
        public IOException mTemporaryException;

        @Nullable
        public InputStream mWrappedStream;

        public StreamProgressWrapper(@NonNull Callable<InputStream> callable, @Nullable StreamDownloadTask streamDownloadTask) {
            this.mParentTask = streamDownloadTask;
            this.mInputStreamCallable = callable;
        }

        private void checkCancel() throws CancelException {
            StreamDownloadTask streamDownloadTask = this.mParentTask;
            if (streamDownloadTask != null && streamDownloadTask.getInternalState() == 32) {
                throw new CancelException();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean ensureStream() throws IOException {
            checkCancel();
            if (this.mTemporaryException != null) {
                try {
                    if (this.mWrappedStream != null) {
                        this.mWrappedStream.close();
                    }
                } catch (IOException unused) {
                }
                this.mWrappedStream = null;
                if (this.mLastExceptionPosition == this.mDownloadedBytes) {
                    Log.i(StreamDownloadTask.TAG, "Encountered exception during stream operation. Aborting.", this.mTemporaryException);
                    return false;
                }
                StringBuilder sbA = a.a("Encountered exception during stream operation. Retrying at ");
                sbA.append(this.mDownloadedBytes);
                Log.i(StreamDownloadTask.TAG, sbA.toString(), this.mTemporaryException);
                this.mLastExceptionPosition = this.mDownloadedBytes;
                this.mTemporaryException = null;
            }
            if (this.mStreamClosed) {
                throw new IOException("Can't perform operation on closed stream");
            }
            if (this.mWrappedStream != null) {
                return true;
            }
            try {
                this.mWrappedStream = this.mInputStreamCallable.call();
                return true;
            } catch (Exception e2) {
                if (e2 instanceof IOException) {
                    throw ((IOException) e2);
                }
                throw new IOException("Unable to open stream", e2);
            }
        }

        private void recordDownloadedBytes(long j) {
            StreamDownloadTask streamDownloadTask = this.mParentTask;
            if (streamDownloadTask != null) {
                streamDownloadTask.recordDownloadedBytes(j);
            }
            this.mDownloadedBytes += j;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            while (ensureStream()) {
                try {
                    return this.mWrappedStream.available();
                } catch (IOException e2) {
                    this.mTemporaryException = e2;
                }
            }
            throw this.mTemporaryException;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            InputStream inputStream = this.mWrappedStream;
            if (inputStream != null) {
                inputStream.close();
            }
            this.mStreamClosed = true;
            StreamDownloadTask streamDownloadTask = this.mParentTask;
            if (streamDownloadTask != null && streamDownloadTask.request != null) {
                this.mParentTask.request.performRequestEnd();
                this.mParentTask.request = null;
            }
            checkCancel();
        }

        @Override // java.io.InputStream
        public void mark(int i) {
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return false;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            while (ensureStream()) {
                try {
                    int i = this.mWrappedStream.read();
                    if (i != -1) {
                        recordDownloadedBytes(1L);
                    }
                    return i;
                } catch (IOException e2) {
                    this.mTemporaryException = e2;
                }
            }
            throw this.mTemporaryException;
        }

        @Override // java.io.InputStream
        public long skip(long j) throws IOException {
            long j2 = j;
            long j3 = 0;
            while (ensureStream()) {
                while (j2 > StreamDownloadTask.PREFERRED_CHUNK_SIZE) {
                    try {
                        long jSkip = this.mWrappedStream.skip(StreamDownloadTask.PREFERRED_CHUNK_SIZE);
                        if (jSkip < 0) {
                            if (j3 == 0) {
                                return -1L;
                            }
                            return j3;
                        }
                        j3 += jSkip;
                        j2 -= jSkip;
                        recordDownloadedBytes(jSkip);
                        checkCancel();
                    } catch (IOException e2) {
                        this.mTemporaryException = e2;
                    }
                }
                if (j2 > 0) {
                    long jSkip2 = this.mWrappedStream.skip(j2);
                    if (jSkip2 < 0) {
                        if (j3 == 0) {
                            return -1L;
                        }
                        return j3;
                    }
                    j3 += jSkip2;
                    j2 -= jSkip2;
                    recordDownloadedBytes(jSkip2);
                }
                if (j2 == 0) {
                    return j3;
                }
            }
            throw this.mTemporaryException;
        }

        @Override // java.io.InputStream
        public int read(@NonNull byte[] bArr, int i, int i2) throws IOException {
            int i3 = 0;
            while (ensureStream()) {
                while (i2 > StreamDownloadTask.PREFERRED_CHUNK_SIZE) {
                    try {
                        int i4 = this.mWrappedStream.read(bArr, i, 262144);
                        if (i4 == -1) {
                            if (i3 == 0) {
                                return -1;
                            }
                            return i3;
                        }
                        i3 += i4;
                        i += i4;
                        i2 -= i4;
                        recordDownloadedBytes(i4);
                        checkCancel();
                    } catch (IOException e2) {
                        this.mTemporaryException = e2;
                    }
                }
                if (i2 > 0) {
                    int i5 = this.mWrappedStream.read(bArr, i, i2);
                    if (i5 == -1) {
                        if (i3 == 0) {
                            return -1;
                        }
                        return i3;
                    }
                    i += i5;
                    i3 += i5;
                    i2 -= i5;
                    recordDownloadedBytes(i5);
                }
                if (i2 == 0) {
                    return i3;
                }
            }
            throw this.mTemporaryException;
        }
    }
}
