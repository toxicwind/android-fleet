package androidx.work.impl.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.os.BuildCompat;
import androidx.work.ForegroundInfo;
import androidx.work.ForegroundUpdater;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class WorkForegroundRunnable implements Runnable {
    public static final String TAG = Logger.tagWithPrefix("WorkForegroundRunnable");
    public final Context mContext;
    public final ForegroundUpdater mForegroundUpdater;
    public final SettableFuture<Void> mFuture = new SettableFuture<>();
    public final TaskExecutor mTaskExecutor;
    public final WorkSpec mWorkSpec;
    public final ListenableWorker mWorker;

    @SuppressLint({"LambdaLast"})
    public WorkForegroundRunnable(@NonNull Context context, @NonNull WorkSpec workSpec, @NonNull ListenableWorker worker, @NonNull ForegroundUpdater foregroundUpdater, @NonNull TaskExecutor taskExecutor) {
        this.mContext = context;
        this.mWorkSpec = workSpec;
        this.mWorker = worker;
        this.mForegroundUpdater = foregroundUpdater;
        this.mTaskExecutor = taskExecutor;
    }

    @NonNull
    public ListenableFuture<Void> getFuture() {
        return this.mFuture;
    }

    @Override // java.lang.Runnable
    @SuppressLint({"UnsafeExperimentalUsageError"})
    public void run() {
        if (!this.mWorkSpec.expedited || BuildCompat.isAtLeastS()) {
            this.mFuture.set(null);
            return;
        }
        final SettableFuture settableFuture = new SettableFuture();
        this.mTaskExecutor.getMainThreadExecutor().execute(new Runnable() { // from class: androidx.work.impl.utils.WorkForegroundRunnable.1
            @Override // java.lang.Runnable
            public void run() {
                settableFuture.setFuture(WorkForegroundRunnable.this.mWorker.getForegroundInfoAsync());
            }
        });
        settableFuture.addListener(new Runnable() { // from class: androidx.work.impl.utils.WorkForegroundRunnable.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ForegroundInfo foregroundInfo = (ForegroundInfo) settableFuture.get();
                    if (foregroundInfo == null) {
                        throw new IllegalStateException(String.format("Worker was marked important (%s) but did not provide ForegroundInfo", WorkForegroundRunnable.this.mWorkSpec.workerClassName));
                    }
                    Logger.get().debug(WorkForegroundRunnable.TAG, String.format("Updating notification for %s", WorkForegroundRunnable.this.mWorkSpec.workerClassName), new Throwable[0]);
                    WorkForegroundRunnable.this.mWorker.setRunInForeground(true);
                    WorkForegroundRunnable.this.mFuture.setFuture(WorkForegroundRunnable.this.mForegroundUpdater.setForegroundAsync(WorkForegroundRunnable.this.mContext, WorkForegroundRunnable.this.mWorker.getId(), foregroundInfo));
                } catch (Throwable th) {
                    WorkForegroundRunnable.this.mFuture.setException(th);
                }
            }
        }, this.mTaskExecutor.getMainThreadExecutor());
    }
}
