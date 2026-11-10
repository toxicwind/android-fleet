package com.google.firebase.storage;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import b.a.a.a.a;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class StorageTaskScheduler {
    public static StorageTaskScheduler sInstance = new StorageTaskScheduler();
    public static BlockingQueue<Runnable> mCommandQueue = new LinkedBlockingQueue();
    public static final ThreadPoolExecutor COMMAND_POOL_EXECUTOR = new ThreadPoolExecutor(5, 5, 5, TimeUnit.SECONDS, mCommandQueue, new StorageThreadFactory("Command-"));
    public static BlockingQueue<Runnable> mUploadQueue = new LinkedBlockingQueue();
    public static final ThreadPoolExecutor UPLOAD_QUEUE_EXECUTOR = new ThreadPoolExecutor(2, 2, 5, TimeUnit.SECONDS, mUploadQueue, new StorageThreadFactory("Upload-"));
    public static BlockingQueue<Runnable> mDownloadQueue = new LinkedBlockingQueue();
    public static final ThreadPoolExecutor DOWNLOAD_QUEUE_EXECUTOR = new ThreadPoolExecutor(3, 3, 5, TimeUnit.SECONDS, mDownloadQueue, new StorageThreadFactory("Download-"));
    public static BlockingQueue<Runnable> mCallbackQueue = new LinkedBlockingQueue();
    public static final ThreadPoolExecutor CALLBACK_QUEUE_EXECUTOR = new ThreadPoolExecutor(1, 1, 5, TimeUnit.SECONDS, mCallbackQueue, new StorageThreadFactory("Callbacks-"));

    static class StorageThreadFactory implements ThreadFactory {
        public final String mNameSuffix;
        public final AtomicInteger threadNumber = new AtomicInteger(1);

        public StorageThreadFactory(@NonNull String str) {
            this.mNameSuffix = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(@NonNull Runnable runnable) {
            StringBuilder sbA = a.a("FirebaseStorage-");
            sbA.append(this.mNameSuffix);
            sbA.append(this.threadNumber.getAndIncrement());
            Thread thread = new Thread(runnable, sbA.toString());
            thread.setDaemon(false);
            thread.setPriority(9);
            return thread;
        }
    }

    static {
        COMMAND_POOL_EXECUTOR.allowCoreThreadTimeOut(true);
        UPLOAD_QUEUE_EXECUTOR.allowCoreThreadTimeOut(true);
        DOWNLOAD_QUEUE_EXECUTOR.allowCoreThreadTimeOut(true);
        CALLBACK_QUEUE_EXECUTOR.allowCoreThreadTimeOut(true);
    }

    public static StorageTaskScheduler getInstance() {
        return sInstance;
    }

    public Executor getCommandPoolExecutor() {
        return COMMAND_POOL_EXECUTOR;
    }

    public void scheduleCallback(Runnable runnable) {
        CALLBACK_QUEUE_EXECUTOR.execute(runnable);
    }

    public void scheduleCommand(Runnable runnable) {
        COMMAND_POOL_EXECUTOR.execute(runnable);
    }

    public void scheduleDownload(Runnable runnable) {
        DOWNLOAD_QUEUE_EXECUTOR.execute(runnable);
    }

    public void scheduleUpload(Runnable runnable) {
        UPLOAD_QUEUE_EXECUTOR.execute(runnable);
    }
}
